from typing import NamedTuple,List,Tuple
from datetime import datetime,date,timedelta
import csv

Viaje = NamedTuple('viaje',[('código',str),('fec_sal',date),('fec_reg',date),('ciudades',List[str]),('num_per',int),('ppp',float),('seguro',bool)])

def lee_viajes(nombreFichero:str)->List[Viaje]:
    with open(nombreFichero,'rt',encoding='utf-8') as f:
        acum = []
        iterador = csv.reader(f,delimiter=';')
        next(iterador)
        for código, fec_sal, fec_reg, ciudades, num_per, ppp, seguro in iterador:
            fec_sal = datetime.strptime(fec_sal,"%d/%m/%y").date()
            fec_reg = datetime.strptime(fec_reg,"%Y-%m-%d").date()
            ciudades = ciudades.replace(' - ',',')
            ciudades = ciudades[1:len(ciudades)-1]
            ciudades = ciudades.split(',')
            num_per = int(num_per)
            ppp = ppp.replace(',','.')
            ppp = float(ppp)
            seguro = seguro == "true"
            acum.append(Viaje(código, fec_sal, fec_reg, ciudades, num_per, ppp, seguro))
        return acum
    
def ciudades_distintas(datos:List[Viaje])->List[str]:
    """
    Recibe una lista de tuplas de tipo Viaje
    Devuelve una lista ordenada alfabéticamente con las distintas ciudades que se pueden visitar
    """
    acum = []
    for dato in datos:
        for ciudad in dato.ciudades:
            if ciudad not in acum:
                acum.append(ciudad)
    return sorted(acum)

def viajes_visitan_en_fecha(datos:List[Viaje],ciudad:str,fecha:date=None)->None:
    """
    Recibe una lista de tuplas de tipo Viaje, una ciudad y una fecha, que puede tomar el valor **None**
    Devuelve una lista con los registros que estarán de viaje en la fecha dada, incluyendo también visitar la ciudad que se indique. Si la fecha toma el valor **None** no se tendrán en cuenta la fecha.
    """
    acum = []
    for dato in datos:
        if ciudad in dato.ciudades and (fecha == None or dato.fec_sal <= fecha <= dato.fec_reg):
            acum.append(dato)
    return acum
""" Otra forma de hacerlo:
    datos = filter(lambda a: ciudad in a.ciudades, datos)
    if fecha != None:
        datos = filter(lambda a: a.fec_sal <= fecha and fecha <= a.fec_reg,list(datos))
    return list(datos)"""

def viajes_que_visitan_más_ciudades(datos:List[Viaje])->List[Tuple[str,str,List[str]]]:
    """
    Recibe una lista de tuplas de tipo Viaje
    Devuelve una lista de tuplas con el código, las fechas de salida y de regreso (ambas con formato str: dia/mes/año) y la lista de las ciudades visitadas
    """
    viajes_nmax = max({len(dato.ciudades) for dato in datos})
    filtro_nmax = list(filter(lambda a: len(a.ciudades)==viajes_nmax,datos))
    acum = []
    for dato in filtro_nmax:
        código = dato.código
        f_sal = dato.fec_sal.strftime("%d/%m/%Y")
        f_reg = dato.fec_reg.strftime("%d/%m/%Y")
        ciudades = dato.ciudades
        acum.append((código, f_sal, f_reg, ciudades))
    return acum

def n_viajes_más_importe(datos:List[Viaje],año_salida:int,n:int=4)->List[Tuple[str,timedelta,int,float,float]]:
    """
    Recibe una lista de tuplas de tipo Viaje, un año de salida y un entero "n" con valor por defecto 4
    Devuelve una lista de tuplas con el código del viaje, los días de duración del viaje, el número de viajeros, el precio por persona y el importe total. 
    La lista deberá estar ordenada de mayor a menor importe total.
    """
    filtro_year = [dato for dato in datos if dato.fec_sal.year == año_salida]
    ordenado_importe = sorted(filtro_year,key=lambda a: a.num_per * a.ppp,reverse=True)[:n]
    return [(viaje.código, (viaje.fec_reg-viaje.fec_sal).days, viaje.num_per, viaje.ppp, viaje.num_per * viaje.ppp) for viaje in ordenado_importe]
