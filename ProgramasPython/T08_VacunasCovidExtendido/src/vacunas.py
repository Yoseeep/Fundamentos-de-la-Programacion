from typing import NamedTuple,List,Tuple
from datetime import date,datetime
import csv


Vacuna = NamedTuple("vacuna",[('usuario',str),('fecha_ncto',date),('provincia',str),('marca',str),('importe',float),('fecha_admón',date),('pauta_completa',bool),('reacciones',List[str])])

import test_vacunas

def lee_vacunas(nombre_fichero:str)->List[Vacuna]:
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        res = []
        iterador = csv.reader(f,delimiter=';')
        next(iterador)
        for usuario,fecha_ncto,provincia,marca,importe,fecha_admón,pauta_completa,reacciones in iterador:
            fecha_ncto = datetime.strptime(fecha_ncto,"%d/%m/%Y")
            fecha_ncto = date(fecha_ncto.year,fecha_ncto.month,fecha_ncto.day)
            importe = float(importe)
            fecha_admón = datetime.strptime(fecha_admón,"%d/%m/%Y")
            fecha_admón = date(fecha_admón.year,fecha_admón.month,fecha_admón.day)
            pauta_completa = pauta_completa == 'S'
            reacciones = reacciones.split('-')
            res.append(Vacuna(usuario,fecha_ncto,provincia,marca,importe,fecha_admón,pauta_completa,reacciones))
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

def vacunados_con_al_menos_reacciones_que(datos:List[Vacuna],numReac:int)->int:
    """
    Recibe una lista de tuplas de tipo Vacuna y un número entero
    Devuelve el número de vacunados que han tenido, al menos, ese número de reacciones
    """
    acum = 0
    for dato in datos:
        if len(dato.reacciones) >= numReac:
            acum += 1
    return acum

def vacunados_según_pauta_y_reacción(datos:List[Vacuna],valPauta:str,reacción:str)->List[Tuple[str,str,List[str]]]:
    """
    Recibe una lista de tuplas de tipo Vacuna, un valor "S" o "N" para indicar pauta completa si o no y una reacción de tipo str
    Devuelve una lista de tuplas con el nombre del usuario, la marca de la vacuna recibida y la lista de reacciones, que sean de la pauta indicada y que entre su reacciones se encuentra la dada
    """
    valPauta = valPauta == 'S'
    acum = []
    for dato in datos:
        if dato.pauta_completa == valPauta and reacción in dato.reacciones:
            acum.append((dato.usuario,dato.marca,dato.reacciones))
    return acum
    

def n_vacunados_mayores(datos:List[Vacuna], n:int)->List[Tuple[str,date]]:
    """
    Recibe una lista de tuplas de tipo Vacuna y un valor "n" entero
    Devuelve una lista de tuplas con el nombre del usuario y la fecha de nacimiento de los "n" vacunados de más edad
    """
    datos_ordenados = sorted(datos,key=lambda a:a.fecha_ncto)
    return [(dato_ordenado.usuario,dato_ordenado.fecha_ncto) for dato_ordenado in datos_ordenados[:n]]

def el_último_vacunado(datos:List[Vacuna])->Vacuna:
    """
    Recibe una lista de tuplas de tipo Vacuna
    Devuelve el registro del vacunado más reciente
    """
    return max(datos,key=lambda a:a.fecha_admón)