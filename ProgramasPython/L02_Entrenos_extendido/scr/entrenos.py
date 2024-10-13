from typing import NamedTuple,List,Tuple
from datetime import datetime,time
import csv

Entreno = NamedTuple('entreno',[('tipo',str),('fechahora',datetime),('ubicación',str),('duración',int),('calorias',int),('distancia',float),('frecuencia',int),('compartido',bool),('peso_antes',float),('peso_después',float)])

def lee_entrenos(nombre_fichero:str)->List[Entreno]:
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        acum = []
        iterador = csv.reader(f,delimiter=';')
        next(iterador)
        for tipo,fechahora,ubicación,duración,calorías,distancia,frecuencia,compartido,peso_antes,peso_después in iterador:
            fechahora = datetime.strptime(fechahora,'%d/%m/%Y %H:%M')
            duración = int(duración)
            calorías = int(calorías)
            distancia = float(distancia.replace(',','.'))
            frecuencia = int(frecuencia)
            compartido = compartido == 'S'
            peso_antes = float(peso_antes.replace(',','.'))
            peso_después = float(peso_después.replace(',','.'))
            acum.append(Entreno(tipo,fechahora,ubicación,duración,calorías,distancia,frecuencia,compartido,peso_antes,peso_después))
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


def promedio_perdida_peso(entrenos:List[Entreno],ubicación:str)->float:
    """
    Recibe una lista de tuplas tipo Entreno y una ubicación
    Devuelve el promedio de los pesos que se han perdido en cada entrenamento
    """
    acum = []
    for entreno in entrenos:
        if entreno.ubicación == ubicación:
            diferencia_pesos = entreno.peso_antes - entreno.peso_después
            acum.append(diferencia_pesos)
    return sum(acum) / len(acum)

def cuenta_distintos_tipos(entrenos:List[Entreno])->int:
    """
    Recibe una lista de tuplas tipo Entreno
    Devuelve el nº de los distintos tipos de entrenamiento que hay en el fichero
    """
    acum = []
    for entreno in entrenos:
        if entreno.tipo not in acum:
            acum.append(entreno.tipo)
    return len(acum)

def obtiene_horas_más_perdida_peso(entrenos:List[Entreno],peso_min_perdido:float)->List[datetime]:
    """
    Recibe una lista de tuplas tipo Entreno y un peso mínimo que debenn perderse en cada entrenamiento
    """
    acum = []
    for entreno in entrenos:
        if entreno.peso_antes - entreno.peso_después >= peso_min_perdido:
            acum.append(time(entreno.fechahora.hour,entreno.fechahora.minute))
    return acum