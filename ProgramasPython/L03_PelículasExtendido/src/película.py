from typing import NamedTuple,List,Tuple
from datetime import datetime, date,time
import csv

Película = NamedTuple('película',[('fecha_de_estreno',date),('título',str),('director',str),('género',List[str]),('duración',time),('presupuesto',float),('recaudación',float),('reparto',List[str]),('premiada',bool)])

def lee_películas(fichero_path:str)->List[Película]:
    with open(fichero_path,'rt',encoding='utf-8') as f:
        acum = []
        iterador = csv.reader(f,delimiter=';')
        next(iterador)
        for fecha_de_estreno,título,director,géneros,duración,presupuesto,recaudación,reparto,premiada in iterador:
            fecha_de_estreno = datetime.strptime(fecha_de_estreno,"%d-%m-%Y")
            fecha_de_estreno = date(fecha_de_estreno.year,fecha_de_estreno.month,fecha_de_estreno.day)
            géneros = géneros.split('#')
            duración = datetime.strptime(duración,"%H:%M")
            duración = time(duración.hour,duración.minute)
            presupuesto = float(presupuesto)
            recaudación = float(recaudación.replace(',','.'))
            reparto = reparto.strip("-")
            premiada = premiada == "true"
            acum.append(Película(fecha_de_estreno,título,director,géneros,duración,presupuesto,recaudación,reparto,premiada))
        return acum
    
def película_más_cara(datos:List[Película])->Película:
    """
    Recibe una lista de tuplas de tipo Película
    Devuelve el registro con la película que tuvo mayor presupuesto
    """
    return max(datos,key=lambda a:a.presupuesto)

def película_menos_beneficio(datos:List[Película])->Tuple[str,float]:
    """
    Recibe una lista de tuplas de tipo Película
    Devuelve una tupla con el título y el beneficio de la película con menos beneficio
    """
    peli = min(datos,key=lambda a:a.recaudación-a.presupuesto)
    return (peli.título,peli.recaudación-peli.presupuesto)

def n_películas_más_largas(datos:List[Película],n:int)->List[Película]:
    """
    Recibe una lista de tuplas de tipo Película y un número "n" entero
    Devuelve una lista con las "n" películas más largas
    """
    return sorted(datos,key=lambda a:a.duración,reverse=True)[0:n]

def última_película_premieda(datos:List[Película])->Tuple[str,date,time,List[str]]:
    """
    Recibe una lista de tuplas de tipo Película
    Devuelve una tupla con el título, la fecha de estreno, la duración y los géneros de la película premiada que se estrenó más recientemente
    """
    datos = filter(lambda a: a.premiada,datos)
    peli = max(datos,key=lambda a: a.fecha_de_estreno)
    return (peli.título,peli.fecha_de_estreno,peli.duración,peli.género)

def n_premiadas_mayor_recaudación(datos:List[Película],valor:str,n:int)-> List[Tuple[str,float,str]]:
    """
    Recibe una lista de tuplas de tipo Vacuna, un valor "SI" o "NO" de tipo str y un valor "n" de tipo entero
    Devuelve una lista de tuplas con el título, la recaudación, y el director de las "n" películas con mayor recaudación según hayan sido premiadas o no
    """
    datos_valor = list(filter(lambda dato:dato.premiada == (valor == "SI"),datos))
    nConMásRecaudación = sorted(datos_valor,key=lambda a: a.recaudación,reverse=True)
    return [(peli.título,peli.recaudación,peli.director) for peli in nConMásRecaudación[0:n]]
