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

def promedios_dias_estancias_por_tipo(reservas:List[Reserva])->Dict[str,float]:
    """
    Recibe una lista de tuplas de tipo Reserva
    Devuelve un diccionario con el promedio de días en que se reserva cada tipo de habitación
    """
    res = dict()
    for reserva in reservas:
        diferencia_dias = reserva.fechas.fecha_salida-reserva.fechas.fecha_entrada
        if reserva.tipo_habitación not in res:
            res[reserva.tipo_habitación] = [diferencia_dias.days]
        else:
            res[reserva.tipo_habitación].append(diferencia_dias.days)
    
    for tipo,días in res.items():
        res[tipo]=sum(días)/len(días)
    
    return res

def reserva_más_barata_por_número_personas(reservas:List[Reserva],servicios:Optional[Set[str]]=None)->Dict[int,Reserva]:
    """
    Recibe una lista de tuplas de tipo Reserva y un conjunto de servicios adicionales, que por defecto puede valer **None**.
    Devuelve un diccionario con la reserva más barata para cada número de personas en las que se han contratado alguno de los 
        servicios dados como parámetro, salvo que se omitan, en cuyo caso se considerarán todas las reservas.
    """
    res = dict()
    for reserva in reservas:
        if servicios == None or  len(servicios & set(reserva.servicios_adicionales))>0:
            if reserva.num_personas not in res:
                res[reserva.num_personas] = [reserva]
            else:
                res[reserva.num_personas].append(reserva)
    for c,v in res.items():
        res[c] = min(v,key= lambda a: a.precio_noche / a.num_personas )
    return res

def reserva_más_cara_por_mes(reservas:List[Reserva])->Dict[int,Tuple[str,str,float]]:
    """
    Recibe una lista de tuplas de tipo Reserva.
    Devuelve un diccionario que a cada número del mes de la fecha de entrada le haga corresponder una tupla con el nombre y apellidos, DNI y el total facturado del cliente que ha pagado por una reserva en el mes de que se trate.
    """
    res = dict()
    for reserva in reservas:
        facturado = (reserva.fechas.fecha_salida - reserva.fechas.fecha_entrada).days * reserva.precio_noche
        if reserva.fechas.fecha_entrada.month not in res:
            res[reserva.fechas.fecha_entrada.month] = []
        res[reserva.fechas.fecha_entrada.month].append( (reserva.dni,reserva.nombre,facturado) )
    for c,v in res.items():
        res[c] = max(v,key= lambda a: a[2])
    return res

def clientes_por_servicio(reservas:List[Reserva],n:int,tipo:Optional[str]=None)->Dict[str,List[Tuple[str,str,float]]]:
    """
    Recibe una lista de tuplas de tipo Reserva, un número entero "n" y un tipo de habitación que puede tomar por defecto el valor None
    Devuelve un diccionario que a cada servicio adiccional le haga corresponder una lista de tuplas con el dni, el nombre del cliente y el precio noche de las reservas, 
        ordenada por el precio, de los "n" clientes que han contratado el servicio adiccional de que se trate y se han alojado en el tipo de habitación dado, 
        salvo que tome el valor por defecto, en cuyo caso se tendrá en cuenta todo tipo de habitación.
    """
    res = dict()
    for reserva in reservas:
        if (tipo == None or reserva.tipo_habitación == tipo) and len(reserva.servicios_adicionales)>0:
            información = (reserva.dni,reserva.nombre,reserva.precio_noche)
            for servicio in reserva.servicios_adicionales:
                if servicio not in res:
                    res[servicio] = [información]
                else:
                    res[servicio].append(información)
    for c,v in res.items():
        res[c] = sorted(v,key = lambda a: a[2])[:n]
    return res