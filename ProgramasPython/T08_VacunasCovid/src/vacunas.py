from typing import NamedTuple,List,Tuple
from datetime import date,datetime
import csv


Vacuna = NamedTuple("vacuna",[('usuario',str),('fecha_ncto',date),('provincia',str),('marca',str),('importe',float),('fecha_admón',date),('pauta_completa',bool)])

import test_vacunas

def lee_vacunas(nombre_fichero:str)->List[Vacuna]:
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        res = []
        iterador = csv.reader(f,delimiter=';')
        next(iterador)
        for usuario,fecha_ncto,provincia,marca,importe,fecha_admón,pauta_completa in iterador:
            fecha_ncto = datetime.strptime(fecha_ncto,"%d/%m/%Y")
            fecha_ncto = date(fecha_ncto.year,fecha_ncto.month,fecha_ncto.day)
            importe = float(importe)
            fecha_admón = datetime.strptime(fecha_admón,"%d/%m/%Y")
            fecha_admón = date(fecha_admón.year,fecha_admón.month,fecha_admón.day)
            pauta_completa = pauta_completa == 'S'
            res.append(Vacuna(usuario,fecha_ncto,provincia,marca,importe,fecha_admón,pauta_completa))
        return res
    
def cuenta_vacunas_de_marca(datos:List[Vacuna], marca:str) -> int:
    """
    Recibe una lista de tipo Vacuna y una marca de vacuna
    Devuelve el número de vacunas de dicha marca
    """
    acum = 0
    for dato in datos:
        if dato.marca == marca:
            acum += 1
    return acum

def obtener_vacunados_el(datos:List[Vacuna],fecha:date)->List[Tuple]:
    """
    Recibe una lista de tipo Vacuna y una fecha de tipo date
    Devuelve el nombre del usuario y la fecha de nacimiento de los vacunados en la fecha dada como parámetro
    """
    acum = []
    for dato in datos:
        if dato.fecha_admón == fecha:
            acum.append((dato.marca,dato.fecha_ncto))
    return acum
    
