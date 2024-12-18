from typing import NamedTuple,List,Union,Optional,Tuple,Dict
from datetime import date,datetime
import csv

Video = NamedTuple('Video', 
[('id_video', str),
 ('fecha_trending', date),
 ('titulo',str),
 ('canal', str),
 ('categoria', str),
 ('visitas', int),
 ('likes', int),
 ('dislikes', int)
])

def lee_trending_videos(nombre_fichero:str)->List[Video]:
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        res = []
        iterador = csv.reader(f,delimiter=';')
        next(iterador)
        for id_video,fecha_trending,titulo,canal,categoria,visitas,likes,dislikes in iterador:
            fecha_trending = datetime.strptime(fecha_trending,"%d/%m/%Y").date()
            visitas = int(visitas)
            likes = int(likes)
            dislikes = int(dislikes)
            res.append(Video(id_video,fecha_trending,titulo,canal,categoria,visitas,likes,dislikes))
    return res

def media_visitas(videos:List[Video],fech:date)->Union[float,int]:
    visitas_fech = [video.visitas for video in videos if video.fecha_trending == fech]
    return sum(visitas_fech)/len(visitas_fech) if len(visitas_fech) > 0 else 0

def video_mayor_ratio_likes_dislikes(videos:List[Video],categoría:Optional[str]=None)->Video:
    filtro = [(video,video.likes/video.dislikes) for video in videos if (categoría == None or categoría == video.categoria) and video.dislikes > 0]
    return max(filtro,key=lambda a: a[1])

def canales_top(videos:List[Video],n:Optional[int]=3)->List[Tuple[str,int]]:
    res = dict()
    for video in videos:
        if video.canal not in res:
            res[video.canal] = 0
        res[video.canal] += 1
    return sorted(res.items(),key=lambda a: a[1],reverse=True)[:n] 

def video_mas_likeability_por_categoria(videos:List[Video],k:Optional[int]=20)->Dict[str,str]:
    res = dict()
    for video in videos:
        if video.categoria not in res:
            res[video.categoria] = []
        likeability = (k*video.likes-video.dislikes)/(k*video.visitas) #Se supone que si un video es trending, tiene al menos una visita.
        res[video.categoria].append( (video.id_video,likeability) )
    for c,v in res.items():
        res[c] = max(v,key=lambda a: a[1])[0]
    return res

def incrementos_visitas(videos:List[Video],canal:str)->List[int]:
    # Diccionario con clave todas las fechas registradas en los datos y valor 0:
    fechas_con_registros = {video.fecha_trending:0 for video in videos}
    # Agrupamos por días las visitas obtenidas del canal:
    fechas_visitas = dict()
    for video in [video for video in videos if video.canal == canal]:
        if video.fecha_trending not in fechas_visitas: 
            fechas_visitas[video.fecha_trending] = 0
        fechas_visitas[video.fecha_trending] += video.visitas
    # Añadimos al primer diccionario los valores del segundo:
    for fecha,visitas in fechas_visitas.items():
        fechas_con_registros[fecha] = visitas
    # Creamos un diccionario con las fechas y los incrementos:
    lista_fechas_visitas = list(fechas_con_registros.items())
    diferencias_fecha_visitas = zip(lista_fechas_visitas,lista_fechas_visitas[1:])
    fechas_incremento = dict([(f1,v2-v1) for (f1,v1),(f2,v2) in diferencias_fecha_visitas])
    # Nos quedamos con los incrementos ordenados por fecha registrada en los datos: 
    return [v for c,v in sorted(fechas_incremento.items())]

    