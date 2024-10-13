from typing import NamedTuple,List,Tuple
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
