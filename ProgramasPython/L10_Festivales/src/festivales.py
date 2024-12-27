from typing import NamedTuple, List, Dict, Tuple, Optional, Set, DefaultDict
from datetime import date,time,datetime
import csv
 
Artista = NamedTuple("Artista",     
                        [("nombre", str), 
                        ("hora_comienzo", time), 
                        ("cache", int)])

Festival = NamedTuple("Festival", 
                        [("nombre", str),
                        ("fecha_comienzo", date),
                        ("fecha_final", date),
                        ("estado", str),                      
                        ("precio", float),
                        ("entradas_vendidas", int),
                        ("artistas", List[Artista]),
                        ("top", bool)
                    ])

meses = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"]

# Ejercicio 1
def lee_festivales (archivo:str)->List[Festival]:
    festivales = []
    with open(archivo, "rt",encoding='utf-8') as f:
        iterador = csv.reader(f,delimiter=',')
        next(iterador)
        for nombre,fecha_comienzo,fecha_final,estado,precio,entradas_vendidas,artistas,top in iterador:
            fecha_comienzo = datetime.strptime(fecha_comienzo, "%Y-%m-%d").date()
            fecha_final = datetime.strptime(fecha_final, "%Y-%m-%d").date()
            precio = float(precio)
            entradas_vendidas = int(entradas_vendidas)
            artistas = [artista.split('_') for artista in artistas.split('-')]
            artistas = [Artista(nombre,hora_comienzo,cache) for nombre,hora_comienzo,cache in artistas]
            top = top == 'True'
            festivales.append(Festival(nombre,fecha_comienzo,fecha_final,estado,precio,entradas_vendidas,artistas,top))
        festivales.sort(key = lambda festival: festival.fecha_comienzo)
        return festivales

# Ejercicio 2    
def total_facturado(festivales:List[Festival], fecha_ini:Optional[date]=None, fecha_fin:Optional[date]=None)->float:
    return sum( [festival.precio * festival.entradas_vendidas for festival in festivales if (fecha_ini == None or fecha_ini <= festival.fecha_comienzo) and (fecha_fin == None or fecha_fin >= festival.fecha_final) and festival.estado == "CELEBRADO"] )

# Ejercicio 3
def artista_top(festivales: List[Festival])->Tuple[int, str]:
    res = dict()
    for festival in festivales:
        if festival.estado == "CELEBRADO":
            for artista in festival.artistas:
                if artista.nombre not in res:
                    res[artista.nombre] = 0
                res[artista.nombre] += 1
    artistaTop = max(res.items(),key=lambda a: a[1])
    return (artistaTop[1],artistaTop[0])

# Ejercicio 4
def artistas_comunes(festivales: List[Festival], festi1: str, festi2:str)->List[str]:
    localiza_festivales = [festival for festival in festivales if festival.nombre in (festi1,festi2)]
    if len(localiza_festivales) == 2: # Si los dos festivales están en la lista
        comunes = {artista.nombre for artista in localiza_festivales[0].artistas} & {artista.nombre for artista in localiza_festivales[1].artistas}
        return list(comunes)
    else: 
        return []

# Ejercicio 5
def festivales_top_calidad_por_duracion(festivales: List[Festival], n: int=3) -> Dict[int, List[str]]:
    res = DefaultDict(list)
    for festival in festivales:
        if len(festival.artistas) > 0: # Por si no hubiera artistas
            duración = (festival.fecha_final - festival.fecha_comienzo).days
            res[duración].append( (festival.entradas_vendidas / len(festival.artistas), festival.nombre) ) 

    for c,v in res.items():
        v.sort(reverse=True) # ordena por el primer argumento de la tupla, el ratio    
        v = [nombre for _,nombre in v]
        if len(v) < n:
            res[c] = v
        else:
            res[c] = v[:n]
    
    return res

# Ejercicio 6
def calcula_beneficio(festival:Festival):
    entradas_vendidas = festival.entradas_vendidas
    pago_artistas = sum([ int(artista.cache) for artista in festival.artistas])
    return entradas_vendidas - pago_artistas
festival0 = Festival(nombre='Ultra Music Festival', fecha_comienzo=date(2024, 3, 29), fecha_final=date(2024, 3, 31), estado='CANCELADO', precio=300.89, entradas_vendidas=50000, artistas=[Artista(nombre='Armin van Buuren', hora_comienzo=time(20, 0), cache=700), Artista(nombre='Skrillex', hora_comienzo=time(21, 30), cache=750), Artista(nombre='Steve Aoki', hora_comienzo=time(23, 0), cache=800), Artista(nombre='Calvin Harris', hora_comienzo=time(0, 30), cache=850), Artista(nombre='Martin Garrix', hora_comienzo=time(2, 0), cache=900)], top=True)

def mes_mayor_beneficio_medio(festivales: List[Festival])->str:
    res = DefaultDict(list)
    for festival in festivales:
        res[festival.fecha_comienzo.month].append( calcula_beneficio(festival) )

    for c,v in res.items():
        res[c] = sum(v) / len(v) # Calculo el beneficio medio
    
    numero_mes = max(res.items(), key=lambda a: a[1])[0]
    return meses[numero_mes-1]

# Ejercicio 7
def variación_mensual_asistentes(festivales:List[Festival])->List[Tuple[str,int]]:
    acum = DefaultDict(int) 
    for festival in festivales:
        numero_mes = festival.fecha_comienzo.month
        nombre_mes = meses[numero_mes-1]
        acum[nombre_mes] += festival.entradas_vendidas
    
    mes_asistentes = list(acum.items())
    diferencias = zip(mes_asistentes,mes_asistentes[1:])
    diferencias = [(m1,a1-a0) for (m0,a0),(m1,a1) in diferencias]
    return diferencias
