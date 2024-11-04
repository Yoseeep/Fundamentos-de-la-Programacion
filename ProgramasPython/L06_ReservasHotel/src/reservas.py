from typing import NamedTuple, List,Optional,Dict,Set,Tuple
from datetime import datetime,date
import csv

Fecha_Estancia = NamedTuple('fecha_estancia', [('fecha_entrada', date), ('fecha_salida', date)])
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
            servicios_adicionales.strip('"')
            servicios_adicionales = servicios_adicionales.split(',') if len(servicios_adicionales)!= 0 else []
            acum.append(Reserva(nombre,dni,fechas,tipo_habitación,num_personas,precio_noche,servicios_adicionales))
    return acum

def total_facturado(reservas:List[Reserva],f_i:Optional[date]=None,f_f:Optional[date]=None)->float:
    """
    Recibe una lista de tuplas de tipo Reserva, una fecha inicial y una fecha final.
    Devuelve el total facturado entre todas las reservas cuya fecha de entrada esté comprendida entre esas fechas dadas como parámetros.
    - Nota 1: La cantidad facturada correspondiente a una reserva se calcula multiplicando el número de días totales de la reserva por el precio por noche.
    - Nota 2: Si la fecha inicial es None se hace el cálculo sin limitar la fecha mínima de las reservas. Si la fecha final es None se hace el cálculo sin limitar la fecha máxima de las reservas.
    """
    res = 0
    for reserva in reservas:
        if (f_i == None or f_i <= reserva.fechas.fecha_entrada) and (f_f == None or reserva.fechas.fecha_entrada <= f_f):
            res = res +  (reserva.fechas.fecha_salida - reserva.fechas.fecha_entrada).days * reserva.precio_noche
    return res

def servicios_adicionales(reservas:List[Reserva])->List[str]:
    """
    Recibe una lista de tuplas de tipo Reserva.
    Devuelve una lista ordenada alfabéticamente de los distintos servicios adiccionales.
    """
    servicios = set()
    for reserva in reservas:
        if len(reserva.servicios_adicionales)>0:
            for servicio in reserva.servicios_adicionales:
                servicios.add(servicio)
    return sorted(servicios)

def reservas_más_largas(reservas:List[Reserva],n:int)->List[Reserva]:
    """
    Recibe una lista de tuplas de tipo Reserva y un entero n
    Devuelve las n tuplas (nombre, fecha_entrada) más largas. Es decir, con mayor número de días entre la fecha de entrada y la fecha de salida.
    """
    return sorted(reservas,reverse=True,key=lambda a: (a.fechas.fecha_salida-a.fechas.fecha_entrada).days)[:n]

def dni_por_tipo(reservas:List[Reserva],servicio:str)->Dict[str,Set[str]]:
    """
    Recibe una lista de tuplas de tipo Reserva, un servicio adicional.
    Devuelve un diccionario con los dni's que se han alojado en cada tipo de habitación y que la reserva incluya el servicio dado.
    """
    tipos_dni = dict()
    for reserva in reservas:
        if servicio in reserva.servicios_adicionales:
            if reserva.tipo_habitación not in tipos_dni:
                tipos_dni[reserva.tipo_habitación] = {reserva.dni}
            else:
                tipos_dni[reserva.tipo_habitación].add(reserva.dni)
    return tipos_dni

def cliente_mayor_facturacion(reservas:List[Reserva],servicios:Optional[Set[str]]=None)->Tuple[str,float]:
    """
    Recibe una lista de tuplas de tipo Reserva y un conjunto de servicios que pode tomar el valor None
    Devuelve una tupla(dni, total_facturado) con el dni del cliente al que se le ha facturado más, junto con el total facturado, 
        teniendo en cuenta sólo aquellas reservas en las que se haya contratado alguno de los servicios adicionales indicados. 
        Si el conjunto de servicios toma el valor None se procesarán todas las reservas.
    """
    res = dict()
    for reserva in reservas:
        if servicios == None or len( servicios & set(reserva.servicios_adicionales) ) > 0:
            facturado = (reserva.fechas.fecha_salida - reserva.fechas.fecha_entrada).days * reserva.precio_noche
            if reserva.dni not in res:
                res[reserva.dni] = facturado
            else:
                res[reserva.dni] =  res[reserva.dni] + facturado
    return max(res.items(), key=lambda a: a[1])