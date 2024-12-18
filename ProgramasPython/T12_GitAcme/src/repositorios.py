from typing import NamedTuple,Set,List,Dict,DefaultDict,Tuple
from datetime import datetime,date
import csv

Commit = NamedTuple("Commit",   
            [("id", str), # Identificador alfanumérico del commit
             ("mensaje", str), # Mensaje asociado al commit
             ("fecha_hora", datetime) # Fecha y hora en la que se registró el commit
             ])
Repositorio = NamedTuple("Repositorio",
            [("nombre", str),  # Nombre del repositorio
             ("propietario", str), # Nombre del usuario propietario
             ("lenguajes", Set[str]),  # Conjunto de lenguajes usados
             ("privado", bool),  # Indica si es privado o público
             ("commits", List[Commit])  # Lista de commits realizados
             ])


# Ejercio 1
def parsea_commits(commits_str: str) -> List[Commit]:
    if len(commits_str) == 0 :
        return list()
    else: 
        acum = []
        for commit in commits_str.split(';'):
            commit = commit.split('^')
            acum.append( Commit(commit[0],commit[1],datetime.strptime(commit[2],"%Y-%m-%d %H:%M:%S")) )
    return sorted(acum,key=lambda a: a.fecha_hora)


def lee_repositorios(csv_filename: str) -> list[Repositorio]:
    res = []
    with open(csv_filename, 'rt', encoding="utf-8") as f: 
        iterador = csv.reader(f, delimiter=',')
        next(iterador)
        for repositorio, propietario, lenguajes, privado, commits in iterador:
            lenguajes = set( lenguajes.strip('"').split(',') )
            privado = "True" == privado
            commits = parsea_commits(commits_str=commits.strip("[]"))
            res.append(Repositorio(repositorio,propietario,lenguajes,privado,commits))
    return res

# Ejercicio 2
def total_commits_por_anyo(repositorios:list[Repositorio])->Dict[int, int]:
    res = DefaultDict(int)
    for repo in repositorios:
        if repo.privado == False:
            for commit in repo.commits:
                res[commit.fecha_hora.year] += 1
    return res

# Ejercio 3
def calcula_tasa_crecimiento(repositorio: Repositorio) -> float:
    commits = repositorio.commits
    if len(commits) == 0:
        return 0
    else: 
        diferencia = (commits[-1].fecha_hora  - commits[0].fecha_hora).days
        if diferencia <1:
            return 0
        else: 
            return len(commits) / diferencia

def n_mejores_repos_por_tasa_crecimiento(repositorios: List[Repositorio],n:int=3)->List[Tuple[str,float]]:
    """
    acum = []
    for repositorio in repositorios:
        tasa = calcula_tasa_crecimiento(repositorio)
        acum.append( (repositorio.nombre,tasa) )
    return sorted(acum,key=lambda a: a[1], reverse = True)
    """
    return sorted([(repositorio.nombre,calcula_tasa_crecimiento(repositorio)) for repositorio in repositorios], key=lambda a: a[1], reverse=True)[:n]
            
# Ejercio 4
def recomendar_lenguajes (repositorios:List[Repositorio],repositorio:Repositorio)->Set[str]:
    recomendados = set()
    for repo in repositorios:
        común = repo.lenguajes & repositorio.lenguajes
        if len(común) != 0: 
            for añade in repo.lenguajes - repositorio.lenguajes:
                recomendados.add(añade)
    return recomendados

# Ejercicio 5
def media_minutos_entre_commits(lista_commits: List[Commit]) -> float:
    lista_commits.sort(key=lambda a: a.fecha_hora)
    if len(lista_commits) <= 1:
        return None
    else: 
        zipea = [commit.fecha_hora for commit in lista_commits]
        return  sum([(f2-f1).total_seconds()/60 for f1,f2 in  zip(zipea,zipea[1:])]) / (len(zipea)-1)

def media_minutos_entre_commits_por_usuario (repositorios:List[Repositorio], fecha_ini:date|None=None, fecha_fin:date|None=None)->Dict[str, float]:
    res = DefaultDict(list)
    for repo in repositorios: 
        for commit in repo.commits:
            if (fecha_ini == None or fecha_ini <= commit.fecha_hora.date()) and (fecha_fin == None or commit.fecha_hora.date() < fecha_fin):
                res[repo.propietario].append(commit)
    
    sol = dict()
    for c,v in res.items():
        media = media_minutos_entre_commits(v)
        if media != None:
            sol[c] = media
            
    return sol