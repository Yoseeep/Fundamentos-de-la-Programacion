# Mi intención ha sido hacerlo de la forma más escalable posible, con funciones lo más pequeñas y útiles posibles. 
from typing import NamedTuple,List,Dict,Tuple,DefaultDict,Optional
from datetime import date,datetime
import csv
 
IntervaloFechas = NamedTuple("IntervaloFechas",  
                     [("inicio", date), ("fin", date)]) 
 
Factura = NamedTuple("Factura",  
                     [("id_vivienda", str), 
                      ("tipo_vivienda", str), 
                      ("barrio", str), 
                      ("tipo_tarifa", str), 
                      ("periodo_facturado", IntervaloFechas), 
                      ("coste_potencia", float), 
                      ("consumo_punta", float), 
                      ("consumo_valle", float), 
                      ("precio_punta", float), 
                      ("precio_valle", float), 
                      ("importe_total", float)])

contrario_tipo_tarifa = {"tramos":"única", "única":"tramos"}

# Ejercio 1
def lee_facturas(ruta_fichero: str) -> List[Factura]:
    res = []
    with open(ruta_fichero,'rt',encoding="utf-8") as f:
        iterador = csv.reader(f,delimiter=',')
        next(iterador)
        for id_vivienda,tipo_vivienda,barrio,tipo_tarifa,periodo_inicio,periodo_fin,coste_potencia,consumo_punta,consumo_valle,precio,importe_total in iterador:
            periodo_inicio = datetime.strptime(periodo_inicio,"%Y-%m-%d").date()
            periodo_fin = datetime.strptime(periodo_fin,"%Y-%m-%d").date()
            periodo_facturado = IntervaloFechas(periodo_inicio,periodo_fin)

            coste_potencia = float(coste_potencia)

            consumo_punta,consumo_valle = float(consumo_punta), float(consumo_valle)

            precio = precio.split('/')
            precio = [float(i) for i in precio]
            precio_punta = precio[0]
            if len(precio)>1:
                precio_valle = precio[1]
            else:
                precio_valle = precio_punta
            
            importe_total = float(importe_total)

            res.append( Factura(id_vivienda,tipo_vivienda,barrio,tipo_tarifa,periodo_facturado,coste_potencia,consumo_punta,consumo_valle,precio_punta,precio_valle,importe_total) )
    
    res.sort(key=lambda a: a.periodo_facturado.inicio)
    return res


# Ejercio 2
def extrae_precio_por_mes(facturas: List[Factura], tipo_tarifa: str) -> Dict[str, Tuple[float, float]]:
    agrupación_precios = dict()

    for factura in facturas:
        if factura.tipo_tarifa == tipo_tarifa:
            clave = factura.periodo_facturado.inicio.strftime("%Y-%m")
            if clave not in agrupación_precios:
                valor = (factura.precio_punta,factura.precio_valle)
                agrupación_precios[clave] = valor

    return agrupación_precios


# Ejercio 3
def calcula_consumo_acumulado(facturas:List[Factura])->float:
    consumos = [factura.consumo_punta + factura.consumo_valle for factura in facturas]
    return sum(consumos)

def agrupa_facturas_por_id(facturas:List[Factura])->Dict[str,List[Factura]]:
    acum = DefaultDict(list)
    for factura in facturas:
        acum[factura.id_vivienda].append(factura)
    return acum

def busca_vivienda_mayor_consumo_acumulado(facturas: List[Factura]) -> Tuple[str, float]:
    viviendas = agrupa_facturas_por_id(facturas)

    for id,facturas_asociadas in viviendas.items():
        viviendas[id] = calcula_consumo_acumulado(facturas_asociadas)
    
    vivienda_mayor_consumo = max( viviendas.items(), key= lambda a: a[1] )
    return vivienda_mayor_consumo


# Ejercicio 4
def calcula_consumo_acumulado_horario_valle(facturas:List[Factura])->float:
    consumos_valle = [factura.consumo_valle for factura in facturas]
    return sum(consumos_valle)

def agrupa_facturas_por_barrio(facturas:List[Factura])->Dict[str,List[Factura]]:
    acum = DefaultDict(list)
    for factura in facturas:
        acum[factura.barrio].append(factura)
    return acum

def barrios_mayor_consumo_valle_medio(facturas: List[Factura], top_n: int) -> List[str]:
    barrios = agrupa_facturas_por_barrio(facturas)

    for barrio,facturas_asociadas in barrios.items():
        barrios[barrio] = calcula_consumo_acumulado_horario_valle(facturas_asociadas)/len(facturas_asociadas)
    
    barrios_ordenados_consumo_medio_valle = sorted( barrios.items(), key= lambda a: a[1] ,reverse=True )[:top_n]
    return [barrio for barrio,_ in barrios_ordenados_consumo_medio_valle]


# Ejercicio 5
def filtra_facturas_de_id_dado(facturas:List[Factura],id_vivienda:str)->List[Factura]|None:
    filtro = []
    for factura in facturas:
        if factura.id_vivienda == id_vivienda:
            filtro.append(factura)
    
    return None if filtro == [] else filtro

def imprime_cambio_tipo_factura(factura:Factura)->str:
    tipo_factura = factura.tipo_tarifa
    cambio_tipo_factura = f"{tipo_factura}->{contrario_tipo_tarifa[tipo_factura]}"
    return cambio_tipo_factura

def calcula_importe_total_factura(tipo_tarifa:str,consumo_punta:float,consumo_valle:float,coste_potencia:float,precio_punta:float,precio_valle:float)->float:
    if tipo_tarifa == "única":
        importe_total = consumo_punta * precio_punta + coste_potencia
    else:
        importe_punta = consumo_punta * precio_punta 
        importe_valle = consumo_valle * precio_valle
        importe_total = importe_punta + importe_valle + coste_potencia
    return importe_total

def calcula_precios_tarifa_original(facturas:List[Factura])->float:
    precio_original = sum( [factura.importe_total for factura in facturas] )
    return precio_original

def calcula_precios_tarifa_contraria(facturas:List[Factura],añoMes_precioPuntaValle_tipo_contrario:Dict[str,Tuple[float,float]])->Tuple[float, float]: 
    acum = 0
    tipo_tarifa = contrario_tipo_tarifa[facturas[0].tipo_tarifa]
    for factura in facturas:
        consumo_punta = factura.consumo_punta
        consumo_valle = factura.consumo_valle
        coste_potencia = factura.coste_potencia
        mesAño = factura.periodo_facturado.inicio.strftime("%Y-%m")
        precio_punta, precio_valle = añoMes_precioPuntaValle_tipo_contrario[mesAño]
        acum += calcula_importe_total_factura(tipo_tarifa,consumo_punta,consumo_valle,coste_potencia,precio_punta,precio_valle)
    return acum

def compara_importe_tipos_factura(facturas_completo: List[Factura], id_vivienda: str) -> Optional[Tuple[str, float, float]]:
    facturas_filtradas = filtra_facturas_de_id_dado(facturas_completo,id_vivienda) 
    if facturas_filtradas == []:
        return None
    
    precio_original = calcula_precios_tarifa_original(facturas_filtradas)

    añoMes_preciosPuntaValle = extrae_precio_por_mes(facturas_completo, tipo_tarifa = contrario_tipo_tarifa[ facturas_filtradas[0].tipo_tarifa ])
    precio_contrario = calcula_precios_tarifa_contraria(facturas_filtradas, añoMes_precioPuntaValle_tipo_contrario = añoMes_preciosPuntaValle)

    mensaje = imprime_cambio_tipo_factura(facturas_filtradas[0])

    return (mensaje,precio_original,precio_contrario)


# Ejercicio 6
"""
def calcula_diferencia_cambios_beneficiosos_de_tipos_tarifa(facturas:List[Factura],facturas_de_id_dado:List[Factura]):
    precio_original, precio_contrario = calcula_precios_originales_y_contrarios_de_facturas(facturas,facturas_de_id_dado)
    return precio_contrario - precio_original
    

def busca_cambios_beneficiosos(facturas: List[Factura]) -> List[Tuple[str,int, float]]:
    facturas_agrupadas = agrupa_facturas_por_id(facturas)

    acum_tramos_a_única = list()
    acum_única_a_tramos = list()
    for _,valor in facturas_agrupadas.items():
        diferencia_precio = calcula_diferencia_cambios_beneficiosos_de_tipos_tarifa(facturas,valor)
        if diferencia_precio >= 0:
            if valor[0].tipo_tarifa == "tramos":
                acum_tramos_a_única.append( (imprime_cambio_tipo_factura(valor[0]), diferencia_precio) )
            else:
                acum_única_a_tramos.append( (imprime_cambio_tipo_factura(valor[0]), diferencia_precio) )
    
    return [ ("tramos->única",len(acum_tramos_a_única),acum_tramos_a_única[1]), ("única->tramos",len(acum_única_a_tramos),acum_única_a_tramos[1]) ]
"""
        

        



    

