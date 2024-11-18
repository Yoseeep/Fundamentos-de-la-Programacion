from typing import NamedTuple,Dict, Set,List, DefaultDict,Tuple
from datetime import date,datetime
import csv

Película = NamedTuple('Pelicula', [('id',str),('title',str),('original_language',str),('release_date',date),('vote_average',float),('popularity',int),('adult',bool), ('genres',Set[str])])


def leer_diccionario_géneros(nombre_archivo_genéros:str)->Dict[str,Set[str]]:
    géneros = dict()
    with open(nombre_archivo_genéros,'rt',encoding='utf-8') as f_g: 
        iterador = csv.reader(f_g,delimiter=':')
        next(iterador)
        for id,genre in iterador:
            genres = genre.split(',')
            for genre in genres:
                if id not in géneros: 
                    géneros[id] = {genre.strip()}
                else: 
                    géneros[id].add(genre.strip())
    return géneros

def leer_películas(nombre_archivo_películas:str,nombre_archivo_genéros:str)->List[Película]:
    géneros = leer_diccionario_géneros(nombre_archivo_genéros)

    res = []
    with open(nombre_archivo_películas, 'rt',encoding='utf-8') as f_p:
        iterador = csv.reader(f_p, delimiter=',')
        next(iterador)
        for id,title,original_language,release_date,vote_average,popularity,adult in iterador:
            release_date = datetime.strptime(release_date,"%Y-%m-%d").date()
            vote_average = float(vote_average)
            popularity = int(popularity)
            adult = not("False" == adult) #No hay ejemplo de cuándo SÍ es de adulto, sólo sé qué pone cuando no lo es.
            genres = géneros[id]
            res.append(Película(id,title,original_language,release_date,vote_average,popularity,adult,genres))
    return res

def género_más_frecuente(películas:List[Película])->Tuple[str,int]:
    res = DefaultDict(int)
    for película in películas:
        for género in película.genres:
            res[género] += 1
    return max(res.items(),key=lambda a: a[1])

def mejor_valorada_por_idioma(películas:List[Película])->Dict[str,Película]:
    res = DefaultDict(list)
    for película in películas:
        res[película.original_language].append(película)
    for c,v in res.items():
        valor_mayor_popularidad = max(v, key=lambda a: a.popularity).popularity
        por_popularidad = [i for i in v if i.popularity == valor_mayor_popularidad]
        res[c] = max(por_popularidad,key= lambda a: a.vote_average)
    return res

def media_calificaciones(películas:List[Película],géneros)->float:
    filtro = [película.vote_average for película in películas if géneros <= set(película.genres)]
    if len(filtro)>0:
        return sum(filtro)/len(filtro)
    else: 
        return 0.0
    
def top_n_por_género(películas:List[Película],n:int)->Dict[str,List[Película]]:
    res = dict()
    for película in películas:
        for género in película.genres:
            if género not in res:
                res[género] = []
            res[género].append(película)
    for c,v in res.items():
        res[c] = sorted(v,key=lambda a: a.vote_average,reverse=True)[:n]
    return res