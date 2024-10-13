from typing import NamedTuple,List,Tuple
from datetime import datetime, date,time
import csv

Película = NamedTuple('película',[('fecha_de_estreno',date),('título',str),('director',str),('género',List[str]),('duración',time),('presupuesto',float),('recaudación',float),('reparto',List[str])])

def lee_películas(fichero_path:str)->List[Película]:
    with open(fichero_path,'rt',encoding='utf-8') as f:
        acum = []
        iterador = csv.reader(f,delimiter=';')
        next(iterador)
        for fecha_de_estreno,título,director,géneros,duración,presupuesto,recaudación,reparto in iterador:
            fecha_de_estreno = datetime.strptime(fecha_de_estreno,"%d-%m-%Y")
            fecha_de_estreno = date(fecha_de_estreno.year,fecha_de_estreno.month,fecha_de_estreno.day)
            géneros = géneros.split('#')
            duración = datetime.strptime(duración,"%H:%M")
            duración = time(duración.hour,duración.minute)
            presupuesto = float(presupuesto)
            recaudación = float(recaudación.replace(',','.'))
            reparto = reparto.strip("-")
            acum.append(Película(fecha_de_estreno,título,director,géneros,duración,presupuesto,recaudación,reparto))
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