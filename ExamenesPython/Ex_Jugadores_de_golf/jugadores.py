from typing import NamedTuple,List,Tuple,Set,DefaultDict,Optional,Dict
from datetime import date,datetime
import csv

Jugador = NamedTuple('Jugador', [("ape_nom",str), 
                                 ("licencia",str), 
                                 ("fecha_ncto",date), 
                                 ("federacion",str), 
                                 ("handicap",float), 
                                 ("fec_hor_act",datetime), 
                                 ("senior",bool), 
                                 ("resultados",List[int])])


# Ejercicio 1
def lee_jugadores(nombre_fichero:str)->List[Jugador]:
    res = []
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        iterador = csv.reader(f,delimiter=';')
        next(iterador)
        for ape_nom, licencia, fecha_ncto, federacion, handicap, fec_hor_act, senior, resultados in iterador:
            fecha_ncto = datetime.strptime(fecha_ncto,"%d/%m/%Y").date()
            handicap = float(handicap)
            fec_hor_act = datetime.strptime(fec_hor_act,"%d/%m/%Y %H:%M:%S")
            senior = senior == 'S'
            resultados = resultados.split(',')
            resultados = [int(k) for k in resultados]
            res.append(Jugador(ape_nom, licencia, fecha_ncto, federacion, handicap, fec_hor_act, senior, resultados))
    return res


# Ejercicio 2
def mejores_jugadores(jugadores:List[Jugador],año:int,n:int) -> List[Tuple[str,str,float]]:
    res = []
    for jugador in jugadores:
        if jugador.fecha_ncto.year == año:
            res.append( (jugador.licencia,jugador.ape_nom,jugador.handicap) )
    
    res.sort(key= lambda a: a[2])

    return res[:n]


# Ejercicio 3
def jugadores_por_golpes(jugadores:List[Jugador]) -> List[Tuple[int,Set[str]]]:
    res = DefaultDict(set)
    for jugador in jugadores: 
        golpes = jugador.resultados
        for golpe in golpes:
            res[golpe].add(jugador.licencia)
    
    return sorted(res.items(),reverse=True)



# Ejercicio 4
def promedio_ultimos_resultados(jugadores:List[Jugador],f1:Optional[date]=None,f2:Optional[date]=None) -> List[Tuple[str,float]]:
    res = []
    for jugador in jugadores:
        if (f1 == None or f1 <= jugador.fec_hor_act.date()) and (f2 == None or jugador.fec_hor_act.date() <= f2) and jugador.senior:
            promedio = sum(jugador.resultados) / len(jugador.resultados)
            res.append( (jugador.licencia,promedio) )
    return res



# Ejercicio 5
def jugador_menor_handicap_por_federacion(jugadores:List[Jugador]) -> Dict[str,Tuple[str,float]]:
    res = DefaultDict(list)
    for jugador in jugadores:
        res[jugador.federacion].append( (jugador.ape_nom,jugador.handicap) )

    for c,v in res.items():
        res[c] = min(v,key=lambda a: a[1])
    
    return res



# Ejercicio 6
def promedio_mejores_resultados_por_hándicap(jugadores:List[Jugador]) -> Dict[float,float]:
    res = DefaultDict(list)
    for jugador in jugadores:
        mejor_resultado = min(jugador.resultados)
        res[jugador.handicap].append(mejor_resultado)
    
    for c,v in res.items():
        res[c] = sum(v) / len(v)
    
    return res

def diferencias_promedio_mejores_resultados_por_hándicap(promedio_mejores_resultados_por_hándicap:Dict[float,float]) -> List[Tuple[str,float]]:
    referencia = sorted( promedio_mejores_resultados_por_hándicap.items() )
    referencia = zip(referencia,referencia[1:])
    diferencia = [(f"{hándicap1} vs {hándicap2}",promedio1-promedio2) for (hándicap1,promedio1),(hándicap2,promedio2) in referencia]
    return diferencia

def comparativa_de_mejores_resultados_segun_handicap(jugadores:List[Jugador]) -> List[Tuple[str,float]]:
    hádicaps_promedios = promedio_mejores_resultados_por_hándicap(jugadores)
    return diferencias_promedio_mejores_resultados_por_hándicap(hádicaps_promedios)

