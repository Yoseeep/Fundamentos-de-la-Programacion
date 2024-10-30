from typing import NamedTuple,List,Tuple
from datetime import datetime
import csv

Entreno = NamedTuple('entreno',[('tipo',str),('fechahora',datetime),('ubicación',str),('duración',int),('calorias',int),('distancia',float),('frecuencia',int),('compartido',bool)])

def lee_entrenos(nombre_fichero:str)->List[Entreno]:
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        acum = []
        iterador = csv.reader(f,delimiter=',')
        next(iterador)
        for tipo,fechahora,ubicación,duración,calorías,distancia,frecuencia,compartido in iterador:
            fechahora = datetime.strptime(fechahora,'%d/%m/%Y %H:%M')
            duración = int(duración)
            calorías = int(calorías)
            distancia = float(distancia)
            frecuencia = int(frecuencia)
            compartido = compartido == 'S'
            acum.append(Entreno(tipo,fechahora,ubicación,duración,calorías,distancia,frecuencia,compartido))
        return acum

def filtra_por_tipo(entrenos:List[Entreno],tipo:str)->List[Tuple]:
    """
    Recibe una lista de tipos Entreno y un tipo de entrenamiento
    Devuelve una lista de tuplas con la ubicaicón y la distancia de los entrenamientos del tipo dado
    """
    acum = []
    for entreno in entrenos:
        if entreno.tipo == tipo:
            acum.append((entreno.ubicación,entreno.distancia))
    return acum

def suma_de_calorias(entrenos:List[Entreno],tipo:str)->int:
    """
    Recibe una lista de tuplas de tipo Entreno y en nombre de un tipo de entrenamiento
    Devuelve la suma de las calorías consumidas en los entrenamientos del tipo dado
    """
    acum = []
    for entreno in entrenos:
        if entreno.tipo == tipo:
            acum.append(entreno.calorias)
    return sum(acum)