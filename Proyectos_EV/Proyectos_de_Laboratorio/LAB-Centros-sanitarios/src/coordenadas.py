from typing import NamedTuple,List
from math import radians, sin, cos, sqrt, atan2

Coordenadas = NamedTuple('coordenadas',[('latitud',float), ('longitud',float)])


def calcular_distancia(punto1:Coordenadas,punto2:Coordenadas)->float:
    """
    Recibe dos coordenadas de tipo Coordenadas.
    Devuelve un float que representa la distancia euclídea entre esas dos coordenadas.
    """
    return ( (punto2.latitud-punto1.latitud)**2 + (punto2.longitud-punto1.longitud)**2 )**(1/2)

def calcular_media_coordenadas(listaCoord:List[Coordenadas])->Coordenadas:
    """
    Recibe una lista de Coordenadas.
    Devuelve una tupla de tipo Coordenadas cuya latitud es la media de las latitudes de la lista y cuya longitud es la media de las longitudes de la lista.
    """
    latitudes = list()
    longitudes = list()
    for coord in listaCoord:
        latitudes.append(coord.latitud)
        longitudes.append(coord.longitud)

    return Coordenadas(sum(latitudes)/len(latitudes),sum(longitudes)/len(longitudes))

def distancia_harvesine(punto1:Coordenadas,punto2:Coordenadas):
    """
    Recibe dos coordenadas de tipo Coordenadas.
    Devuelve un float que representa la distancia dada por la fórmula de Haversine entre esas dos coordenadas.
    """
    # Radio de la Tierra en km
    R = 6371.0
    lat1 = radians(punto1.latitud)
    lon1 = radians(punto1.longitud)
    lat2 = radians(punto2.latitud)
    lon2 = radians(punto2.longitud)
    dlon = lon2 - lon1
    dlat = lat2 - lat1
    a = sin(dlat / 2)**2 + cos(lat1) * cos(lat2) * sin(dlon / 2)**2
    c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return R * c



