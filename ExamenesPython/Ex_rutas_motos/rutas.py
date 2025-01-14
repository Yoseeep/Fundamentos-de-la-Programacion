from typing import NamedTuple,List,Dict,DefaultDict,Optional
from datetime import date,datetime
import csv


Ruta = NamedTuple('Ruta', [('ciudad_inicio',str), 
                           ('coordenada',float), 
                           ('fecha_ruta',date), 
                           ('km',float), 
                           ('gasolineras',int), 
                           ('dificultad',str), 
                           ('zona_descanso',bool), 
                           ('vel_max',int), 
                           ('vel_min',int)]) 

Coordenada = NamedTuple('Coordenada', [('latitud',float), ('longitud',float)])


# Ejercicio 1
def lee_rutas(nombre_fichero:str) -> List[Ruta]:
    res = []
    with open(nombre_fichero,"rt",encoding="utf-8") as f:
        iterador = csv.reader(f,delimiter=';')
        next(iterador)

        for ciudad_inicio,coordenada,fecha_ruta,km,gasolineras,dificultad,zona_descanso,vel_max,vel_min in iterador:

            ciudad_inicio = ciudad_inicio.strip()

            coordenada = coordenada.split('/')
            coordenada = Coordenada(float(coordenada[0]), float(coordenada[1]))

            if fecha_ruta != "":
                fecha_ruta = datetime.strptime(fecha_ruta,"%m/%d/%Y").date()

            km = float(km)

            gasolineras = int(gasolineras)

            zona_descanso = zona_descanso == "True"

            vel_max = int(vel_max)

            vel_min = int(vel_min)

            res.append( Ruta(ciudad_inicio,coordenada,fecha_ruta,km,gasolineras,dificultad,zona_descanso,vel_max,vel_min) )  

    return res




# Ejercicio 2
def acumular_kms_por_meses(rutas:List[Ruta]) -> Dict[int,List[float]]:
    años_registrados = {ruta.fecha_ruta.year for ruta in rutas if ruta.fecha_ruta != ''} 

    registros = {año:[0.0 for _ in range(12)] for año in años_registrados}

    for ruta in rutas:
        if ruta.fecha_ruta != '':
            año = ruta.fecha_ruta.year
            mes = ruta.fecha_ruta.month
            km = ruta.km
            registros[año][mes-1] = registros[año][mes-1] + km
    
    return registros



# Ejercicio 3
def diferencias_kms_meses_anyo(rutas:List[Ruta]) -> Dict[int,List[float]]:
    registros = acumular_kms_por_meses(rutas)

    for c,v in registros.items():
        diferencias = zip(v,v[1:])
        diferencias = [km1-km0 for km0,km1 in diferencias]
        registros[c] = diferencias

    return registros



# Ejercicio 4
def distanciaManhattan(coord1:Coordenada,coord2:Coordenada) -> float:
    d = abs(coord1.latitud - coord2.latitud) + abs(coord1.longitud - coord2.longitud)
    return d

def top_rutas_lejanas(rutas:List[Ruta],n:int,c:Coordenada,km_min:Optional[int]=None) -> List[Ruta]:
    if km_min != None:
        filtro = [ruta for ruta in rutas if km_min <= distanciaManhattan(ruta.coordenada,c)]
    else:
        filtro = rutas[:]

    filtro.sort(key=lambda a: distanciaManhattan(a.coordenada,c),reverse=True)

    return filtro[:n]



# Ejercicio 5
def duración_ruta(ruta:Ruta) -> float: 
    """
    Ruponemos que la velocidad de las rutas ha sido siempre constante y con valor vel_min
    """
    return ruta.km / ruta.vel_min

def ciudades_top_tiempo_dificultad(rutas:List[Ruta],n:int) -> Dict[str,List[str]]:
    res = DefaultDict(list)
    for ruta in rutas:
        if ruta.zona_descanso:
            dificultad = ruta.dificultad
            duración = duración_ruta(ruta)
            ciudad_inicial = ruta.ciudad_inicio
            res[dificultad].append( (duración,ciudad_inicial) )

    for c,v in res.items():
        v.sort()
        v = [ciudad for _,ciudad in v]
        res[c] = v[:n]

    return dict(res)

        