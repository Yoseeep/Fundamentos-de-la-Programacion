from coordenadas import *
import csv
from typing import Tuple
from mapas import *


CentroSanitario = NamedTuple("centroSanitario", [("nombre",str), ("localidad",str), ("ubicacion",Coordenadas), ("estado",str), ("num_camas",int), ("acceso_discapacitados",bool), ("tiene_uci",bool)])

def leer_centros(nombre_fichero:str)->List[CentroSanitario]:
    """
    Recibe el nombre de un fichero de texto.
    Devuelve una lista de tuplas de tipo CentroSanitario con los datos leídos del fichero.
    """
    with open(nombre_fichero, "rt", encoding="utf-8") as f:
        res = list()
        iterador = csv.reader(f,delimiter=";")
        next(iterador)
        for nombre, localidad, latitud, longitud, estado, num_camas, acceso_discapacitados, tiene_uci in iterador:
            localidad =  localidad.strip()
            latitud = float(latitud.strip())
            longitud = float(longitud.strip())
            estado = estado.strip()
            num_camas = int(num_camas.strip())
            acceso_discapacitados = acceso_discapacitados.strip()=="true"
            tiene_uci = tiene_uci.strip()=="true"
            res.append(CentroSanitario(nombre, localidad, Coordenadas(latitud,longitud), estado, int(num_camas), acceso_discapacitados, tiene_uci))
        return res
    
def calcular_total_camas_centros_accesibles(centros:List[CentroSanitario])->int:
    """
    Recibe una lista de tuplas de tipo CentroSanitario.
    Devuelve un entero correspondiente al número total de camas de los centros sanitarios accesibles para discapacitados.
    """
    total = 0
    for centro in centros:
        if centro.acceso_discapacitados:
            total += centro.num_camas
    return total


def obtener_centros_con_uci_cercanos_a(centros:List[CentroSanitario], punto:Coordenadas, umbral:float)->List[Tuple[str,str,Coordenadas]]:
    """
    Recibe una lista de tuplas de tipo CentroSanitario;una tupla de tipo Coordenadas, que representa un punto; y un float, que representa un umbral de distancia.
    Devuelve una lista de tuplas (str, str, Coordenadas) con el nombre, del centro, la localidad y la ubicacion de los centros con uci situados a una distancia de las coordenadas dadas como parámetro menor o igual que el umbral dado.
    """
    res = list()
    for centro in centros:
        if centro.tiene_uci:
            distancia = calcular_distancia(punto, centro.ubicacion)
            if distancia <= umbral:
                res.append((centro.nombre, centro.localidad, centro.ubicacion))
    return res

def generar_mapa(datos:List[Tuple[str,str,Coordenadas]], ruta:str):
    """
    Recibe una lista de tuplas (str, str, Coordenadas(float, float)) con el nombre, del centro, la localidad y la ubicación del centro; y una cadena, que representa la ruta de un fichero html,se generará con los centros geolocalizados.
    Generará un fichero html con un mapa que muestra los centros geolocalizados.
    """
    coordenada_media = calcular_media_coordenadas([dato[2] for dato in datos])
    mapa = crea_mapa(coordenada_media)

    for nombre, localidad, ubicacion in datos:
        agrega_marcador(mapa, ubicacion, f"{nombre} en {localidad}. Coordenadas: {ubicacion}", "blue")

    guarda_mapa(mapa, ruta)