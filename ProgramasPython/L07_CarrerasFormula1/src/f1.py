from typing import NamedTuple,List,Union
from datetime import date,datetime
import csv

Carrera = NamedTuple("carrera",[("nombre",str),("escudería",str),("fecha_carrera",date),("temperatura_min",int),("vel_max",float),("duración",float),("posición_final",int),("ciudad",str),("top_6_vueltas",List[Union[int,float]]),("tiempo_boxes",float),("nivel_liquido",bool)])

def lee_carreras(nombre_fichero:str)->List[Carrera]:
    res = []
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        iterador = csv.reader(f,delimiter=';')
        next(iterador)
        for nombre,escudería,fecha_carrera,temperatura_min,vel_max,duración,posición_final,ciudad,top_6_vueltas,tiempo_boxes,nivel_liquido in iterador:
            fecha_carrera = datetime.strptime(fecha_carrera,"%d-%m-%y").date()
            temperatura_min = int(temperatura_min)
            vel_max = float(vel_max)
            duración = float(duración)
            posición_final = int(posición_final)
            top_6_vueltas = top_6_vueltas.strip('[]').replace('-','0').split('/ ')
            top_6_vueltas = [int(i) if i == '0' else float(i) for i in top_6_vueltas]
            tiempo_boxes = float(tiempo_boxes)
            nivel_liquido = '1' == nivel_liquido
            res.append(Carrera(nombre,escudería,fecha_carrera,temperatura_min,vel_max,duración,posición_final,ciudad,top_6_vueltas,tiempo_boxes,nivel_liquido))
    return res
            
