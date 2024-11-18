from typing import NamedTuple,List,Union,Optional,DefaultDict,Tuple,Dict
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
            
def media_tiempo_boxes(carreras:List[Carrera],c:str,f:Optional[date]=None)->Union[int,float]:
    res = list()
    for carrera in carreras: 
        if (f == None or f == carrera.fecha_carrera) and c == carrera.ciudad:
            res.append(carrera.tiempo_boxes)
    return sum(res)/len(res) if len(res)>0 else int(0)

def pilotos_menor_tiempo_medio_vueltas_top(carreras:List[Carrera],n:int)->Tuple[str,date]:
    res = []
    for carrera in carreras:
        if -1 != carrera.posición_final: #omitimos quienes no completaron las 6 vueltas
            media = sum(carrera.top_6_vueltas)/len(carrera.top_6_vueltas)
            res.append( (carrera.nombre,carrera.fecha_carrera,media) )
    n_mejores = sorted(res,key=lambda a: a[-1])[:n] 
    return [(nombre,fecha) for nombre,fecha,_ in n_mejores]

def ratio_tiempo_boxes_total(carreras:List[Carrera])->List[Tuple[str,date,float]]:
    tiempo_total = DefaultDict(float)
    for carrera in carreras:
        tiempo_total[carrera.fecha_carrera] += carrera.tiempo_boxes
    return sorted([(carrera.nombre,carrera.fecha_carrera,carrera.tiempo_boxes/tiempo_total[carrera.fecha_carrera]) for carrera in carreras],key=lambda a: a[-1],reverse=True)

def mejor_escuderia_año(carreras:List[Carrera],a:int)->str:
    victorias = dict()
    for carrera in carreras:
        if carrera.fecha_carrera.year == a:
            if carrera.escudería not in victorias:
                victorias[carrera.escudería] = 0
            if carrera.posición_final == 1:
                victorias[carrera.escudería] += 1
    return max(victorias.items(),key=lambda a: a[1])[0]


def puntos(carrera:Carrera)->float:
    """
    Recibe una carrera.
    Devuelve los puntos obtenidos en esa carrera.
    """
    posición = carrera.posición_final
    if posición == 1:
        return 50
    elif posición == 2:
        return 25
    elif posición == 3:
        return 10
    else:
        return 0
        
def puntos_año(carreras:List[Carrera])->Dict[date,float]:
    """
    Recibe una lista de carreras.
    Devuelve un diccionario con claves un año y con valor los puntos de ese año
    """
    res = DefaultDict(int)
    for carrera in carreras:
        res[carrera.fecha_carrera.year] += puntos(carrera)
    return res

def puntos_piloto_años(carreras:List[Carrera])->Dict[str,List[int]]:
    pilotos = DefaultDict(list)
    for carrera in carreras:
        pilotos[carrera.nombre].append(carrera)
    resultado = dict()
    for c,v in pilotos.items():
        puntos_por_año = puntos_año(v)
        resultado[c] = [p for _,p in puntos_por_año.items()]
    return resultado
