from typing import NamedTuple, List, Tuple
import csv

Población = NamedTuple('población', [('país',str), ('código',str), ('año',int), ('censo',int)])

def lee_poblaciones(nombre_fichero:str)-> List[Población]:
    """
    Recibe 1 parámetros:
    Devuelve: una lista de tuplas de tipo Población
    """
    res = []
    with open(nombre_fichero, 'rt',encoding='utf-8') as f:
        iterador = csv.reader(f,delimiter=',')
        next(iterador)
        for país,código,año,censo in iterador:
            res.append(Población(país,código, int(año),int(censo)))
    return res




def filtra_por_país(listaDatos:List[NamedTuple], país:str)->List[NamedTuple]:
    """
    Recibe 2 parámetros: - una lista de tuplas de tipo Población con datos de poblaciones
                         - un país de tipo str
    Devuelve: otra lista con tuplas de las poblaciones que son del país dado
    """
    aux = []
    for dato in listaDatos:
        if dato.país == país:
            aux.append(dato)
    return aux

def filtra_por_año_y_umbral_de_censo(listaDatos:List[NamedTuple], año:int, umbral:int)->List[NamedTuple]:
    """
    Recibe 3 parámetros: - una lista de tuplas de tipo Población con datos de poblaciones
                         - un año de tipo int
                         - un umbral de tipo int
    Devuelve: otra lista con las tuplas de las poblaciones que en el año dado tuvieron un censo superior al umbral dado
    """
    aux = []
    for dato in listaDatos:
        if dato.año == año and dato.censo > umbral:
            aux.append(dato)
    return aux

def obtiene_país_y_censo(listaDatos: List[Población])->List[Tuple]:
    """
    Recibe 1 parámetros: - una lista de tuplas de tipo Población con datos de poblaciones
    Devuelve: otra lista con tuplas que contengan el nombre del país y el censo
    """
    aux = []
    for dato in listaDatos:
        aux.append((dato.país,dato.censo))
    return aux

def obtiene_código_y_censo_de_año(listaDatos:List[Población], año:int)->List[Tuple]:
    """
    Recibe 2 parámetros: - una lista de tuplas de tipo Población con datos de poblaciones
                         - un año de tipo int
    Devuelve: otra lista con tuplas que contenga el código y el censo del año dado
    """
    aux = []
    for dato in listaDatos:
        if dato.año == año:
            aux.append((dato.código, dato.censo))
    return aux

def suma_población_de_año(listaDatos:List[Población],año:int)->int:
    """
    Recibe 2 parámetros: - una lista de tuplas de tipo Población con datos de poblaciones
                         - un año de tipo int
    Devuelve: la suma del censo de ese año
    """
    suma = 0
    for dato in listaDatos:
        if dato.año == año:
            suma += dato.censo
    return suma

def promedio_población_de_año(listaDatos:List[Población],año:int)->float:
    """
    Recibe 2 parámetros: - una lista de tuplas de tipo Población con datos de poblaciones
                         - un año de tipo int
    Devuelve: el promedio del censo de ese año
    """
    suma = 0
    cantidad = 0
    for dato in listaDatos:
        if dato.año == año:
            suma += dato.censo
            cantidad += 1
    if cantidad == 0:
        return None
    else:
        return suma / cantidad

