from typing import NamedTuple, List
from datetime import datetime,date
import csv

Fecha_Estancia = NamedTuple('fecha_estancia', [('frecha_entrada', date), ('fecha_salida', date)])
Reserva = NamedTuple('reserva', [('nombre', str), ('dni', str), ('fechas', Fecha_Estancia),('tipo_habitación', str), ('num_personas',int),('precio_noche',float),('servicios_adicionales',List[str])])

def lee_reservas(nombre_fichero:str)->List[Reserva]:
    acum = []
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        iterador = csv.reader(f,delimiter=',')
        next(iterador)
        for nombre,dni,fecha_entrada,fecha_salida,tipo_habitación,num_personas,precio_noche,servicios_adicionales in iterador:
            fecha_entrada = datetime.strptime(fecha_entrada,"%Y-%m-%d").date()
            fecha_salida = datetime.strptime(fecha_salida,"%Y-%m-%d").date()
            fechas = Fecha_Estancia(fecha_entrada,fecha_salida)
            num_personas = int(num_personas)
            precio_noche = float(precio_noche)
            servicios_adicionales = servicios_adicionales.split(',') if len(servicios_adicionales)!= 0 else []
            acum.append(Reserva(nombre,dni,fechas,tipo_habitación,num_personas,precio_noche,servicios_adicionales))
    return acum

