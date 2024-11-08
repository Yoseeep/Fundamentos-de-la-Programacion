from typing import NamedTuple,List,Tuple,Set,Dict,Optional
from datetime import datetime,date,timedelta
import csv


ITV = NamedTuple('itv',[('f_insp',date),('estación',str),('número',int),('f_límite',date),('matrícula',str),('tipo',str),('f_matr',date),('resultado',bool),('importe',float)])

def lee_inspecciones(nombre_fichero:str)->List[ITV]:
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        acum = []
        iterador = csv.reader(f,delimiter=',')
        next(iterador)
        for f_insp, estación, número, f_límite, matrícula, tipo, f_matr, resultado, importe in iterador:
            f_insp = datetime.strptime(f_insp,"%d/%m/%Y").date()
            número = int(número)
            f_límite = datetime.strptime(f_límite,"%d-%m-%Y").date()
            f_matr = datetime.strptime(f_matr,"%d/%m/%y").date()
            resultado = resultado == "S"
            importe = float(importe)
            acum.append(ITV(f_insp,estación,número,f_límite,matrícula,tipo,f_matr,resultado,importe))
        return acum
    
def inspecciones_entre_fechas(datos:List[ITV], f1:date = None, f2:date = None)-> List[Tuple]:
    """
    Recibe una lista de tuplas de tipo ITV y dos fechas con valor por defecto **None**
    Devuelve una lista de tuplas con la matrícula, el tipo de vehículo y la fecha de la inspección de los vehículos inspeccionados entre esas fechas (incluidas)
    No se tendrá en cuenta la fecha que tome el valor **None**
    """
    acum = []
    for dato in datos:
        if (f1 == None or f1<=dato.f_insp) and (f2 == None or dato.f_insp<=f2):
            acum.append( (dato.matrícula,dato.tipo,dato.f_insp) )
    return acum

def promedio_de_días_de_adelanto(datos:List[ITV],valor_inspección:str,estación:str = "todas",)->float:
    """
    Recibe una lista de tuplas de tipo ITV, una estación y "fav" o "des" para indicar si la inspección ha sido o no favorable
    Devuelve el promedio de los días que hay entre la fecha de inspección y la fecha límite de las inspecciones de la estación dada, según se pidan de inspecciones favorables o desfavorables.
    No obstante, este tercer parámetro tendrá como valor por defecto la cadena **"todas"**, en cuyo caso no se filtra por dicho parámetro.
    Si no es posible calcular el promedio se debe devolver **0** (un cero).
    """
    datos = list(filter(lambda a: a.resultado == ("fav" == valor_inspección), datos)) # filtra según el valor dado de valor_inspección
    if estación != "todas": # si se ha modificado la estación
        datos = list(filter(lambda a: a.estación == estación, datos)) # filtra por la estación
    if len(list(datos)) == 0:
        return 0
    else:
        return sum([(dato.f_límite - dato.f_insp).days for dato in datos]) / len(datos) # devuelve la media final


def importes_comunes(datos:List[ITV],estación1:str,estación2:str)->Set:
    """
    Recibe una lista de tuplas de tipo ITV y dos estaciónes
    Devuelve un conjunto con los importes que son comunes a las dos estaciones
    """
    filtro_estación1 = filter(lambda a:a.estación == estación1,datos)
    filtro_estación2 = filter(lambda a:a.estación == estación2,datos)
    importe1 = set(map(lambda a:a.importe,filtro_estación1))
    importe2 = set(map(lambda a:a.importe,filtro_estación2))
    return importe1 & importe2

def diferencia_entre_importes(datos:List[ITV])->List[Tuple[float,float,float]]:
    """
    Recibe una lista de tuplas de tipo ITV y calcule la diferencia de precios entre dos precios consecutivos
    Devuelve una lista de tuplas con un precio, el inmeditamente inferior a él y la diferencia entre ellos
    """
    importes = list({dato.importe for dato in datos})
    importes_ordenados = sorted(importes)
    recorre = zip(importes_ordenados,importes_ordenados[1:])
    return [(precioMayor,precioMenor,precioMayor-precioMenor) for precioMenor,precioMayor in recorre]
    
def número_inspecciones_por_estación(datos:List[ITV])-> Dict[str,int]:
    """
    Recibe una lista de tuplas de tipo ITV
    Devuelve un diccionario que cada estación le haga corresponder el número de inspecciones realizadas en la estación de que se trate.
    """
    acum = dict()
    for dato in datos:
        if dato.estación not in acum:
            acum[dato.estación] = 1
        else:
            acum[dato.estación] += 1
    return acum

def recaudación_por_año(datos:List[ITV], resultado:str)->Dict[int,float]:
    """
    Recibe una lista de tuplas de tipo ITV y un resultado de inspección ("F" favorable y "D" desfavorable)
    Devuelve un diccionario que cada año le haga corresponder la suma de los importes recaudados, según el resultado dado como parametro del año de que se trate.
    """
    acum = dict()
    for dato in datos:
        if dato.resultado == ("F" == resultado):
            if dato.f_insp.year not in acum:
                acum[dato.f_insp.year] = dato.importe
            else:
                acum[dato.f_insp.year] += dato.importe
    return acum

def matrículas_por_tipo(datos:List[ITV],f1:Optional[date]=None,f2:Optional[date]=None)->Dict[str,List[str]]:
    """
    Recibe una lista de tuplas de tipo ITV y dos fechas que por defecto pueden valor **None**
    Devuelve un diccionario que cada tipo de vehículo le haga corresponder la lista de las matrículas de los vehículos inspeccionados entre las fechas dadas (ambas incluidas). No se contemplará filtro para la fecha que tome el valor por defecto.
    """
    acum = dict()
    for dato in datos:
        if (f1 == None or f1 <= dato.f_insp) and (f2 == None or dato.f_insp < f2):
            if dato.tipo not in acum:
                acum[dato.tipo] = [dato.matrícula]
            else:
                acum[dato.tipo].append(dato.matrícula)
    return acum

def promedio_de_facturación_por_estación(datos:List[ITV], f0:Optional[date]=None,f1:Optional[date]=None)->Dict[str,float]:
    """
    Recibe una lista de tuplas de tipo ITV y dos fechas que por defecto pueden valor **None**
    Devuelve un diccionario que cada estación le haga corresponder el promedio facturado entre las fechas dadas (ambas incluidas). 
        No se contemplará filtro para la fecha que tome el valor por defecto.
    """    
    res = dict()
    for dato in datos:
        if (f0 == None or f0 <= dato.f_insp) and (f1 == None or dato.f_insp <= f1):
            if dato.estación not in res:
                res[dato.estación] = [dato.importe]
            else:
                res[dato.estación].append(dato.importe)
    
    promedio = dict()
    for estación,importes in res.items():
        promedio[estación] = sum(importes)/len(importes)
    
    return promedio

def n_matrículas_más_importe_por_tipo(datos:List[ITV],n:Optional[int]=3)->Dict[str,List[Tuple[str,float]]]:
    """
    Recibe una lista de tuplas de tipo ITV y un entero n con valor por defecto 3
    Devuelve un diccionario que cada tipo de vehículo le haga corresponder una lista con las n tuplas con las matrículas y los importes que más han pagado.
    """
    res = dict()
    for dato in datos:
        if dato.tipo not in res:
            res[dato.tipo] = []
        res[dato.tipo].append( (dato.matrícula,dato.importe) )

    ordenado = dict()
    for tipo,tupla in res.items():
        ordenado[tipo] = sorted(tupla,key=lambda a: a[1],reverse=True)[:n]
    
    return ordenado

def tipo_más_facturación_favorable(datos:List[ITV])->Tuple[str,float]:
    """
    Recibe una lista de tuplas de tipo ITV
    Devuelve una tupla con tipo y el promedio de facturación de dicho tipo entre todas las inspecciones favorables realizadas
    """
    res = dict()
    for dato in datos:
        if dato.resultado:
            if dato.tipo not in res:
                res[dato.tipo] = [dato.importe]
            else:
                res[dato.tipo].append(dato.importe)
    
    promedio = dict()
    for tipo,importes in res.items():
        promedio[tipo] = sum(importes)/len(importes)
    
    return promedio