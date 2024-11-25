from youtube import *

def test_lee_trending_videos(datos:List[Video])->None:
    print("\ntest_lee_trending_videos")
    print("Registros leídos:",len(datos))
    print(f"Los tres primeros vídeos son: {datos[:3]}")
    print(f"Los tres últimos vídeos son: {datos[-3:]}")

def test_media_visitas(datos:List[Video])->None:
    print("\ntest_media_visitas")
    fecha = date(2017,11,15)
    media = media_visitas(datos,fecha)
    print(f"La media de visitas del día: {fecha.strftime("%d/%m/%Y")} es {media}")
    fecha = date(2000,1,11)
    media = media_visitas(datos,fecha)
    print(f"La media de visitas del día: {fecha.strftime("%d/%m/%Y")} es {media}")

def test_video_mayor_ratio_likes_dislikes(datos:List[Video])->None:
    print("\ntest_video_mayor_ratio_likes_dislikes")
    video = video_mayor_ratio_likes_dislikes(datos)
    print("El video con mayor ratio de todos es:",video)
    categoría = "Education"
    video = video_mayor_ratio_likes_dislikes(datos,categoría)
    print("El video con mayor ratio de todos es:",video)

def test_canales_top(datos:List[Video])->None:
    print("\ntest_canales_top")
    n = 3
    print(f"El top-{n} de canales es:")
    print(canales_top(datos,n))
    n = 5
    print(f"El top-{n} de canales es:")
    print(canales_top(datos,n))

def test_video_mas_likeability_por_categoria(datos:List[Video])->None:
    print("\ntest_video_mas_likeability_por_categoria")
    print("Vídeo con más likeability por categoría con constante 20")
    for c,v in video_mas_likeability_por_categoria(datos,k=20).items():
        print(f"{c} ---> {v}")

def test_incrementos_visitas(datos:List[Video])->None:
    print("\ntest_incrementos_visitas")
    canal = "Exatlón"
    print(f"Incrementos de visitas del canal {canal}")
    print(incrementos_visitas(datos,canal))
    canal = "Mr. Tops"
    print(f"Incrementos de visitas del canal {canal}")
    print(incrementos_visitas(datos,canal))


if __name__ == "__main__":
    videos  = lee_trending_videos("ProgramasPython/L08_Youtube/data/MX_Youtube_2017_utf8.csv")
    test_lee_trending_videos(videos)
    test_media_visitas(videos)
    test_video_mayor_ratio_likes_dislikes(videos)
    test_canales_top(videos)
    test_video_mas_likeability_por_categoria(videos)
    test_incrementos_visitas(videos)

