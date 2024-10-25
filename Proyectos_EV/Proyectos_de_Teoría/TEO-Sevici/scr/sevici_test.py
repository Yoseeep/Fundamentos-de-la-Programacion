from sevici import *

def imprime_nombre(funcion):
    """
    Función decoradora para imprimir los nombres de los test
    """
    def decora(*args,**kwargs):
        print(f'\n{"-"*5+" "}{funcion.__name__}{" "+"-"*5}')
        funcion(*args,**kwargs)
    return decora

@imprime_nombre
def test_lee_estaciones(datos:List[Estacion]):
    print(f"Las tres primeras son:\n {datos[:3]}")
    print(f"Las tres últimas son:\n {datos[-3:]}")

@imprime_nombre
def test_estaciones_bicis_libres(datos:List[Estacion]):
    k = 5
    estaciones = estaciones_bicis_libres(datos,k)
    print(f"Hay {len(estaciones)} estaciones con {k} o más bicis libres y las 5 primeras son:\n {estaciones[:5]}")
    k = 10
    estaciones = estaciones_bicis_libres(datos,k)
    print(f"Hay {len(estaciones)} estaciones con {k} o más bicis libres y las 5 primeras son:\n {estaciones[:5]}")
    k = 1
    estaciones = estaciones_bicis_libres(datos,k)
    print(f"Hay {len(estaciones)} estaciones con {k} o más bicis libres y las 5 primeras son:\n {estaciones[:5]}")

@imprime_nombre
def test_estaciones_cercanas(datos:List[Estacion],coordenadas:Coordenadas):
    k = 5
    estaciones = estaciones_cercanas(datos,coordenadas,k)
    print(f"Las {k} estaciones más cercanas al punto {coordenadas.latitud},{coordenadas.longitud} son:")
    for estacion in estaciones:
        print(estacion)

if __name__ == "__main__":
    estaciones = lee_estaciones("Proyectos_EV/Proyectos_de_Teoría/TEO-Sevici/data/estaciones.csv")
    test_lee_estaciones(estaciones)
    test_estaciones_bicis_libres(estaciones)
    test_estaciones_cercanas(estaciones,Coordenadas(37.357659,-5.9863))

    # Mostramos todas las estaciones
    mapa_estaciones = crea_mapa_estaciones(estaciones, color_azul)
    guarda_mapa(mapa_estaciones, "Proyectos_EV/Proyectos_de_Teoría/TEO-Sevici/out/azul.html")

    # Mostramos también las disponibilidades de las bicis en las estaciones
    mapa_estaciones = crea_mapa_estaciones(estaciones, obten_color_bicis_disponibles)
    guarda_mapa(mapa_estaciones, "Proyectos_EV/Proyectos_de_Teoría/TEO-Sevici/out/estaciones_bicis_disponibles.html")

    # Mostramos también las disponibilidades de las bornetas en las estaciones
    mapa_estaciones = crea_mapa_estaciones(estaciones, obten_color_bornetas_disponibles)
    guarda_mapa(mapa_estaciones, "Proyectos_EV/Proyectos_de_Teoría/TEO-Sevici/out/estaciones_bornetas_disponibles.html")