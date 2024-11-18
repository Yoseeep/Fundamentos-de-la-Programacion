from typing import NamedTuple,List,Union,Optional
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
