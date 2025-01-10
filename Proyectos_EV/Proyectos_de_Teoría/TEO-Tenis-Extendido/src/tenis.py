from typing import NamedTuple,List,Tuple,DefaultDict,Optional,Dict
from datetime import datetime,date
import csv

Parcial = NamedTuple('Parcial', 
                [('juegos_j1',int),
                 ('juegos_j2',int)])
PartidoTenis = NamedTuple('PartidoTenis', 
                [('fecha',datetime.date), 
                 ('jugador1',str), 
                 ('jugador2',str), 
                 ('superficie',str), 
                 ('resultado', list[Parcial]), 
                 ('errores_nf1',int), 
                 ('errores_nf2',int)])



# Ejercicio 1
def parsea_set(enfrentamiento:str) -> Parcial:
    set1,set2 = enfrentamiento.split('-')
    return Parcial(set1,set2)

def lee_partidos_tenis(ruta_fichero:str) -> List[PartidoTenis]:
    res = []
    with open(ruta_fichero, "rt", encoding="utf-8") as f:
        iterador = csv.reader(f,delimiter=';')
        for fecha,jugador1,jugador2,superficie,set1,set2,set3,errores_nf1,errores_nf2 in iterador:
            fecha = datetime.strptime(fecha,"%d/%m/%Y").date()
            resultado = [parsea_set(set) for set in (set1,set2,set3)]
            errores_nf1 = int(errores_nf1)
            errores_nf2 = int(errores_nf2)
            res.append( PartidoTenis(fecha,jugador1,jugador2,superficie,resultado,errores_nf1,errores_nf2) )
    return res



# Ejercicio 2
def partidos_menos_errores(partidos:List[PartidoTenis]) -> PartidoTenis:
    return min( partidos, key= lambda a: a.errores_nf1 + a.errores_nf2 )



# Ejercicio 3
def jugador_mas_partidos(partidos:List[PartidoTenis]) -> Tuple[str,int]:
    res = DefaultDict(int)
    for partido in partidos:
        res[partido.jugador1] += 1
        res[partido.jugador2] += 1
    return max(res.items(), key=lambda a: a[1])



# Ejercicio 4
def ganador(partido:PartidoTenis) -> str:
    resultado = partido.resultado
    ganados1 = 0
    ganados2 = 0

    for set in resultado:
        if set.juegos_j1 > set.juegos_j2:
            ganados1 += 1
            if ganados1 == 2:
                return partido.jugador1
        elif set.juegos_j1 < set.juegos_j2:
            ganados2 += 1
            if ganados2 == 2:
                return partido.jugador2

    
def tenista_mas_victorias(partidos:List[PartidoTenis], fecha1:Optional[date]=None, fecha2:Optional[date]=None) -> Tuple[str,int]:
    res = DefaultDict(int)
    for partido in partidos:
        if (fecha1 == None or fecha1 <= partido.fecha) and (fecha2 == None or partido.fecha <= fecha2):
            gana = ganador(partido)
            res[gana] += 1
    return max(res.items(), key = lambda a: a[1])



# Ejercicio 5
def media_errores_por_jugador(partidos:List[PartidoTenis]) -> List[Tuple[str,float]]:
    res = DefaultDict(list)
    for partido in partidos:
        res[partido.jugador1].append(partido.errores_nf1)
        res[partido.jugador2].append(partido.errores_nf2)

    for c,v in res.items():
        res[c] = sum(v) / len(v)
    
    return sorted(res.items(), key = lambda a: a[1])


            
# Ejercicio 6
def jugadores_mayor_porcentaje_victorias(partidos:List[PartidoTenis]) -> List[Tuple[str,float]]:
    res = DefaultDict(list)
    for partido in partidos: 
        gana = ganador(partido)
        if gana == partido.jugador1:
            res[partido.jugador1].append(1)
            res[partido.jugador2].append(0)
        else:
            res[partido.jugador1].append(0)
            res[partido.jugador2].append(1)
    
    for c,v in res.items():
        res[c] = sum(v) / len(v)

    return sorted(res.items(), key=lambda a: a[1], reverse=True)



# Ejercicio 7
def n_tenistas_con_mas_errores(partidos:List[PartidoTenis],n:Optional[int]=None) -> List[Tuple[str,int]]:
    res = DefaultDict(int)
    for partido in partidos: 
        res[partido.jugador1] += partido.errores_nf1
        res[partido.jugador2] += partido.errores_nf2

    ordenados = sorted(res.items(), key=lambda a: a[1], reverse=True)
    if n == None: 
        return ordenados
    else: 
        return ordenados[:n]
    


# Ejercicio 8
def fechas_ordenadas_por_jugador(partidos:List[PartidoTenis]) -> Dict[str,List[date]]:
    res = DefaultDict(list)
    for partido in partidos:
        res[partido.jugador1].append(partido.fecha)
        res[partido.jugador2].append(partido.fecha)
    
    for c,v in res.items():
        res[c] = sorted(v)

    return res



# Ejercicio 9    
def num_partidos_nombre(partidos:List[PartidoTenis],tenista:str) -> Dict[str,Tuple[int,int]]:
    res = DefaultDict(list)
    for partido in partidos:
        if tenista in (partido.jugador1,partido.jugador2):
            gana = ganador(partido)
            if gana == tenista:
                res[partido.superficie].append(1)
            else:
                res[partido.superficie].append(0)
    
    for c,v in res.items():
        res[c] = (len(v), sum(v))

    return res



# Ejercicio 10
def num_tenistas_distintos_por_superficie(partidos:List[PartidoTenis]) -> Dict[str,int]:
    res = DefaultDict(set)
    for partido in partidos:
        res[partido.superficie].add(partido.jugador1)
        res[partido.superficie].add(partido.jugador2)
    
    for c,v in res.items():
        res[c] = len(v)

    return res



# Ejercicio 11
def superficie_con_mas_tenistas_distintos(partidos:List[PartidoTenis]) -> Tuple[str,int]:
    superficie_numTenistasDistintos = num_tenistas_distintos_por_superficie(partidos)
    return max(superficie_numTenistasDistintos.items(), key=lambda a: a[1])


# Ejercicio 12
def mas_errores_por_jugador(partidos:List[PartidoTenis]) -> Dict[str,PartidoTenis]:
    res = DefaultDict(list)
    for partido in partidos:
        res[partido.jugador1].append( (partido.errores_nf1,partido) )
        res[partido.jugador2].append( (partido.errores_nf2,partido) )

    for c,v in res.items():
        res[c] = max(v)[1]

    return res



# Ejercicio 13
def partido_mas_errores_por_mes(partidos:List[PartidoTenis],superficies:Optional[List[str]]=None) -> Dict[str,Tuple[date,str,str]]:
    res = DefaultDict(list)
    for partido in partidos:
        if superficies == None or partido.superficie in superficies:
            num_mes = partido.fecha.month
            res[num_mes].append(partido)
    
    for c,v in res.items():
        partido_elegido = max(v, key=lambda a: a.errores_nf1 + a.errores_nf2)
        res[c] = (partido_elegido.fecha,partido_elegido.jugador1,partido_elegido.jugador2)
    
    return res



# Ejercicio 14
def n_partidos_mas_errores_por_jugador(partidos:List[PartidoTenis],n:int) -> Dict[str,List[PartidoTenis]]:
    res = DefaultDict(list)
    for partido in partidos:
        res[partido.jugador1].append( (partido.errores_nf1,partido) )
        res[partido.jugador2].append( (partido.errores_nf2,partido) )

    for c,v in res.items():
        n__mayores_errores = sorted(v, reverse=True)[:n]
        res[c] = [partido for _,partido in n__mayores_errores]

    return res



# Ejercicio 15
def mayor_numero_dias_sin_jugar(partidos:List[PartidoTenis],jugador:str) -> int | None:
    res = []
    for partido in partidos:
        if jugador in (partido.jugador1,partido.jugador2):
            res.append(partido.fecha)
    
    if len(res) <= 1:
        return None
    else:
        res.sort()

        diferencia_dias = zip(res[1:],res)
        diferencia_dias = [(f1-f0).days for f1,f0 in diferencia_dias]
        return max(diferencia_dias)