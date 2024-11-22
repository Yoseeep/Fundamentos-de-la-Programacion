from typing import NamedTuple,List,Set,Optional,Tuple
from datetime import date,datetime
import csv

Medallas = NamedTuple('Medallas',[('oro',int),('plata',int),('bronce',int)]) 
Registro = NamedTuple('Registro', [('ciudad_olimpica',str),('fecha_inicio',date),('pais',str),('deporte',str),('num_participantes',int),('genero',str),('medallas',Medallas),('sede',bool)])  

def lee_registros_olimpiadas(ruta:str)->List[Registro]:
    res = []
    with open(ruta,'rt',encoding='utf-8') as f:
        iterador = csv.reader(f,delimiter=',')
        next(iterador)
        for ciudad_olimpica,fecha_inicio,pais,deporte,num_participantes,genero,medallas,sede in iterador:
            fecha_inicio = datetime.strptime(fecha_inicio,"%Y-%m-%d").date()
            num_participantes = int(num_participantes)
            medallas = [int(i) for i in medallas.split('-')]
            medallas = Medallas(medallas[0],medallas[1],medallas[2])
            sede = sede == "SI"
            res.append( Registro(ciudad_olimpica,fecha_inicio,pais,deporte,num_participantes,genero,medallas,sede) )
    return res

def deportes_ambos_generos(registros: List[Registro], año:int )->Set[str]:
    filtro_año = [registro for registro in registros if registro.fecha_inicio.year == año]
    hombres = set()
    mujeres = set()
    for registro in filtro_año:
        if registro.genero == "HOMBRE":
            hombres.add(registro.deporte)
        else:
            mujeres.add(registro.deporte)
    return hombres & mujeres

def deportes_mas_frecuentes(registros: List[Registro],n:int,género:Optional[str]=None )->List[Tuple[str,int]]:
    res = dict()
    for registro in registros:
        if género == None or registro.genero == género:
            if registro.deporte not in res:
                res[registro.deporte] = 0
            res[registro.deporte] += 1
    return sorted(res.items(),key=lambda a: a[1],reverse=True)[:n]

def deporte_femenino_con_mas_paises_distintos_con_oro(registros: List[Registro])-> str:
    res = dict()
    for registro in registros:
        if registro.genero == "MUJER" and registro.medallas.oro > 0:
            if registro.deporte not in res:
                res[registro.deporte] = set()
            res[registro.deporte].add(registro.pais)
    return max(res.items(),key=lambda a: len(a[1]))[0]

