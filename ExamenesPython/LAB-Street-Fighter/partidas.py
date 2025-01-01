from typing import NamedTuple,List,Tuple,DefaultDict
from collections import Counter
from datetime import datetime
import csv 

dias_semana = ["Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"]

Partida = NamedTuple("Partida", [
    ("pj1", str),
    ("pj2", str),
    ("puntuacion", int),
    ("tiempo", float),
    ("fecha_hora", datetime),
    ("golpes_pj1", List[str]),
    ("golpes_pj2", List[str]),
    ("movimiento_final", str),
    ("combo_finish", bool),
    ("ganador", str),
    ])

def lee_partidas(nombre_fichero:str)->List[Partida]:
    res = []
    with open(nombre_fichero, 'rt', encoding="utf-8") as f:
        iterador = csv.reader(f)
        next(iterador)
        for pj1,pj2,puntuacion,tiempo,fecha_hora,golpes_pj1,golpes_pj2,movimiento_final,combo_finish,ganador in iterador:
            puntuacion = int(puntuacion)
            tiempo = float(tiempo)
            fecha_hora = datetime.strptime(fecha_hora, "%Y-%m-%d %H:%M:%S")
            golpes_pj1 = golpes_pj1.strip("[]").split(", ")
            golpes_pj2 = golpes_pj2.strip("[]").split(", ")
            combo_finish = combo_finish == '1'
            res.append( Partida(pj1,pj2,puntuacion,tiempo,fecha_hora,golpes_pj1,golpes_pj2,movimiento_final,combo_finish,ganador) )
    return res

def victoria_mas_rapida(partidas:List[Partida])->Tuple[str,str,float]:
    resultado = partidas[0]
    for partida in partidas[1:]:
        if resultado.tiempo > partida.tiempo:
            resultado = partida
    return (resultado.pj1,resultado.pj2,resultado.tiempo)

def top_ratio_medio_personajes(partidas:List[Partida],n:int)->List[str]:
    res = DefaultDict(list)
    for partida in partidas:
        if partida.tiempo > 0:
            ratio = partida.puntuacion / partida.tiempo
            res[partida.ganador].append(ratio)
    
    for c,v in res.items():
        res[c] = sum(v)/len(v)
    
    ordenados = sorted(res.items(),key=lambda a: a[1])[:n]
    return [nombre for nombre,_ in ordenados]

def enemigos_mas_debiles(partidas:List[Partida],personaje:str)->Tuple[List[str],int]:
    res = DefaultDict(int)
    for partida in partidas:
        if partida.ganador == personaje:
            oponente = list({partida.pj1,partida.pj2}-{personaje})
            res[oponente[0]] += 1
    
    victorias_maximas = max( list(res.values()) )
    enemigos = []
    for c,v in res.items():
        if v == victorias_maximas:
            enemigos.append(c)  

    return (enemigos,victorias_maximas)

#* Hecho con Counter: *
"""
def enemigos_mas_debiles(partidas:List[Partida],personaje:str)->Tuple[List[str],int]:
    res = list()
    for partida in partidas:
        if partida.ganador == personaje:
            oponente = list({partida.pj1,partida.pj2}-{personaje})
            res.append(oponente[0])
    
    res = Counter(res)
    victorias_maximas = max( list(res.values()) )
    enemigos = []
    for c,v in res.items():
        if v == victorias_maximas:
            enemigos.append(c)  

    return (enemigos,victorias_maximas)
"""

def movimientos_comunes(partidas:List[Partida],personaje1:str,personaje2:str)->List[str]:
    personaje1_mov = list()
    personaje2_mov = list()

    for partida in partidas:
        if personaje1 == partida.pj1:
            personaje1_mov.extend(partida.golpes_pj1)
        elif personaje1 == partida.pj2:
            personaje1_mov.extend(partida.golpes_pj2)
        elif personaje2 == partida.pj1:
            personaje2_mov.extend(partida.golpes_pj1)
        elif personaje2 == partida.pj2:
            personaje2_mov.extend(partida.golpes_pj2)
    
    return list( set(personaje1_mov) & set(personaje2_mov) )

def convierte_dia_semana(partida:Partida)->str:
    num_semana = partida.fecha_hora.weekday()
    return dias_semana[num_semana]

def dia_mas_combo_finish(partidas:List[Partida])->str:
    res = DefaultDict(int)
    for partida in partidas:
        if partida.combo_finish:
            dia_semana = convierte_dia_semana(partida)
            res[dia_semana] += 1
    return max(res.items(),key=lambda a: a[1])[0]

