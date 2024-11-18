from movie import *

def test_leer_películas(datos:List[Película])->None:
    print("\ntest_leer_películas")
    print(f"Número de resgistros leídos: {len(datos)}")
    print(f"PRIMERA PELÍCULA: {datos[0]}")
    print(f"ÚLTIMA PELÍCULA: {datos[-1]}")

def test_género_más_frecuente(datos:List[Película])->None:
    print("\ntest_género_más_frecuente")
    print(género_más_frecuente(datos))

def test_mejor_valorada_por_idioma(datos:List[Película])->None:
    print("\ntest_mejor_valorada_por_idioma")
    diccionario = mejor_valorada_por_idioma(datos)
    print(f"Mejor en español es ['es']: {diccionario['es']}")

def test_media_calificaciones(datos:List[Película])->None:
    print("\ntest_media_calificaciones")
    géneros = {'Fake', 'Adventure', 'Action'}
    print(f"{géneros} --> {media_calificaciones(datos,géneros)}")
    géneros = {'Thriller', 'Action'}
    print(f"{géneros} --> {media_calificaciones(datos,géneros)}")

def test_top_n_por_género(datos:List[Película])->None: 
    print("\ntest_top_n_por_género")
    n = 2
    print(f"top: {n}")
    diccionario = top_n_por_género(datos,n)
    for c,v in diccionario.items():
        print(f"{c} --> {v}")


if __name__ == "__main__":
    películas = leer_películas("ProgramasPython/T10_TMBD_Movie/data/movies_fp.csv","ProgramasPython/T10_TMBD_Movie/data/movies_fp_genres.csv")
    test_leer_películas(películas)
    test_género_más_frecuente(películas)
    test_mejor_valorada_por_idioma(películas)
    test_media_calificaciones(películas)
    test_top_n_por_género(películas)