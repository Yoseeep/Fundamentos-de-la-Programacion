import csv
import math 
import folium
from typing import NamedTuple,Tuple,List, Optional
import webbrowser
import os

## 1. Carga de datos

Coordenadas = NamedTuple('coordenadas',[('latitud',float), ('longitud',float)])

Estacion = NamedTuple('estacion', [('nombre',str), ('bornetas',int), ('bornetas_vacias',int), ('bicis_disponibles',int), ('coordenadas',Coordenadas)])

# Función de lectura que crea una lista de Estaciones
def lee_estaciones(fichero:str) -> List[Estacion]:
    ''' Lee el fichero de datos y devuelve una lista de estaciones
    
    ENTRADA: 
       :param fichero: Nombre y ruta del fichero a leer
       :type fichero: str
   
    SALIDA: 
       :return: Lista de tuplas de tipo Estacion
       :rtype: [Estacion(str, int, int, int, Coordenadas(float, float))]
    
    Cada estación se representa con una tupla con los siguientes valores:
    - Nombre de la estación
    - Número total de bornetas
    - Número de bornetas vacías
    - Número de bicicletas disponibles
    - Coordenadas
    Usaremos el módulo csv de la librería estándar de Python para leer el
    fichero de entrada.
    Hay que saltar la línea de encabezado del fichero y comenzar a leer los datos
    a partir de la segunda línea.
    Hay que realizar un pequeño procesamiento con los datos numéricos. Hay que
    pasar del formato cadena (que es lo que se interpreta al leer el csv) a un
    valor numérico (para poder aplicar operaciones matemáticas si fuese necesario).
    También hay que crear una tupla con nombre de tipo Coordenadas
    '''
    estaciones = []
    with open(fichero,"rt",encoding='utf-8') as f:
        iterador = csv.reader(f)
        next(iterador)
        for nombre,bornetas,bornetas_vacias,bicis_disponibles,latitud,longitud in iterador:
            estaciones.append(Estacion(nombre,int(bornetas),int(bornetas_vacias),int(bicis_disponibles),Coordenadas(float(latitud),float(longitud))))
    return estaciones

## 2. Operaciones de consulta

def estaciones_bicis_libres(estaciones:List[Estacion], k:Optional[int]=5)->List[Tuple[int,str]]:
    ''' Estaciones que tienen bicicletas libres
    
    ENTRADA: 
      :param estaciones: lista de estaciones disponibles 
      :type estaciones: [Estacion(str, int, int, int, Coordenadas(float, float))]
      :param k: número mínimo requerido de bicicletas
      :type k: int
    SALIDA: 
      :return: lista de estaciones seleccionadas
      :rtype: [(int, str)] 
    
    Toma como entrada una lista de estaciones y un número k.
    Crea una lista formada por tuplas (número de bicicletas libres, nombre)
    de las estaciones que tienen al menos k bicicletas libres. La lista
    estará ordenada por el número de bicicletas libres.
    '''
    al_menos_k = [(estacion.bicis_disponibles,estacion.nombre) for estacion in estaciones if estacion.bicis_disponibles >= k]
    return sorted(al_menos_k,key=lambda x: x[0])

def calcula_distancia(coordenadas1:Coordenadas, coordenadas2:Coordenadas)->float:
    ''' Distancia entre un punto y una estación
    ENTRADA: 
    :param coordenadas1: coordenadas del primer punto
    :type coordenadas1: Coordenadas(float, float)
    :param coordenadas2: coordenadas del segundo punto
    :type coordenadas2: Coordenadas(float, float)
      
    SALIDA: 
    :return: distancia entre dos coordenadas
    :rtype: float 
    
    Toma como entrada dos coordenadas y calcula la distancia entre ambas aplicando la fórmula
    
        distancia = sqrt((x2-x1)**2 + (y2-y1)**2)
    '''
    return math.sqrt((coordenadas2.latitud-coordenadas1.latitud)**2 + (coordenadas2.longitud-coordenadas1.longitud)**2)

def estaciones_cercanas(estaciones:List[Estacion], coordenadas:Coordenadas, k:Optional[int]=5)->List[Tuple[float,str,int]]:
    ''' Estaciones cercanas a un punto dado
    
    ENTRADA: 
      :param estaciones: lista de estaciones disponibles
      :type estaciones: [Estacion(str, int, int, int, Coordenadas(float, float))]
      :param coordenadas: coordenadas formada por la latitud y la longitud de un punto
      :type coordenadas: Coordenadas(float, float)
      :param k: número de estaciones cercanas a calcular 
      :type k: int
    SALIDA: 
      :return: Una lista de tuplas con la distancia, nombre y bicicletas libres de las estaciones seleccionadas 
      :rtype: [(float, str, int)] 
    
    Toma como entrada una lista de estaciones,  las coordenadas de  un punto y
    un valor k.
    Crea una lista formada por tuplas (distancia, nombre de estación, bicicletas libres)
    con las k estaciones con bicicletas libres más cercanas al punto dado, ordenadas por
    su distancia a las coordenadas dadas como parámetro.
    '''
    ordena_por_distancia = sorted(
        [(calcula_distancia(coordenadas,estacion.coordenadas),estacion.nombre,estacion.bicis_disponibles) for estacion in estaciones],
        key=lambda x: x[0])
    return ordena_por_distancia[:k]

## 3. Visualización de estaciones
def crea_mapa(latitud, longitud, zoom=9):
    '''
    Función que crea un mapa folium que está centrado en la latitud y longitud
    dados como parámetro y mostrado con el nivel de zoom dado.
    ENTRADA:
        :param latitud: latitud del centro del mapa en pantalla
        :type latitud:float
        :param longitud: longitud del centro del mapa  en pantalla
        :type longitud: float
        :param zoom: nivel del zoom con el que se muestra el mapa
        :type zoom: int
    SALIDA:
        :return: objeto mapa creado
        :rtype: folium.Map
    '''
    mapa = folium.Map(location=[latitud, longitud], 
                      zoom_start=zoom)
    return mapa    

def crea_marcador (latitud, longitud, etiqueta, color):
    '''
    Función que crea un marcador rojo con un icono de tipo señal de información.
    El marcador se mostrará en el punto del mapa dado por la latitud y longitud
    y cuandos se mueva el ratón sobre él, se mostrará una etiqueta con el texto
    dado por el parámetro etiqueta
    ENTRADA:
        :param latitud: latitud del marcador
        :type latitud: float
        :param longitud: longitud del marcador
        :type longitud: float
        :param etiqueta: texto de la etiqueta que se asociará al marcador 
        :type etiqueta: str
    SALIDA:
        :return: objeto marcador creado
        :rtype:folium.Marker
    '''
    marcador = folium.Marker([latitud,longitud], 
                   popup=etiqueta, 
                   icon=folium.Icon(color=color, icon='info-sign')) 
    return marcador

def media_coordenadas (estaciones:List[Estacion])->Coordenadas:
    '''Devuelve una coordenada cuya latitud es la media de las latitudes y
    cuya longitud es la media de las longitudes.
    ENTRADA
        :param estaciones: lista de estaciones disponibles
        :ptype estaciones: [Estacion(str, int, int, int, Coordenadas(float, float))]
    SALIDA
        :return: Una coordenada cuya longitud es la media de las longitudes, y la 
             latitud la media de las latitudes
        :rtype: Coordenadas(float, float)
    '''
    return Coordenadas(sum([estacion.coordenadas.latitud for estacion in estaciones])/len(estaciones),
                       sum([estacion.coordenadas.longitud for estacion in estaciones])/len(estaciones))

def crea_mapa_estaciones(estaciones, funcion_color):
    '''Genera un objeto de tipo folium.Map con un marcador por cada
    estación dada como parámetro. El marcador tendrá como etiqueta
    el nombre de la estación, y su color se obtendrá a partir de la 
    función ```funcion_color``` que se pasa como parámetro
    ENTRADA
        :param estaciones: lista de estaciones disponibles
        :type estaciones: [Estacion(str, int, int, int, Coordenadas(float, float))]
        :param funcion_color: Función que se aplica a una estación y devuelve una cadena
            que representa el color en el que se dibuja el marcador
        :type funcion_color: function(Estacion)->str
    SALIDA
        :return: objeto mapa creado con los marcadores
        :rtype: folium.Map
    '''
    #Calculamos la media de las coordenadas de las estaciones, para poder centrar el 
    #mapa
    centro_mapa = media_coordenadas(estaciones)
    # creamos el mapa con folium
    mapa = crea_mapa(centro_mapa.latitud, centro_mapa.longitud, 13)

    for estacion in estaciones:
        etiqueta = estacion.nombre
        color = funcion_color(estacion)
        marcador = crea_marcador (estacion.coordenadas.latitud, estacion.coordenadas.longitud, etiqueta, color)
        marcador.add_to(mapa)
    
    return mapa

def guarda_mapa(mapa, ruta_fichero):
    '''Guard un mapa como archivo html

    :param mapa: Mapa a guardar
    :type mapa: folium.Map
    :param ruta_fichero: Nombre y ruta del fichero
    :type ruta_fichero: str
    '''
    mapa.save(ruta_fichero)
    # Abre el fichero creado en un navegador web
    webbrowser.open("file://" + os.path.realpath(ruta_fichero))


def color_azul(estacion):
   '''Función que devuelve siempre azul
   ENTRADA
      :param estacion: Estación para la que quiero averiguar el color
      :type estacion: Estacion(str, int, int, int, Coordenadas(float, float))
   SALIDA
      :return: El color azul
      :rtype: str
   '''
   return "blue"