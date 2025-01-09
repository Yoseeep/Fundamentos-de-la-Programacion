from jugadores import *

"""def imprime_nombre_función(función):

    print("--*",función.__name__,"**--")


@imprime_nombre_función()"""
def test_lee_jugadores(jugadores:List[Jugador]) -> None:
    print("\ntest_lee_jugadores")
    print("Registros leídos:", len(jugadores))
    print("Los dos primeros:")
    for i in jugadores[:2]:
        print(i)
    print("Los dos últimos:")
    for i in jugadores[-2:]:
        print(i)    

def test_mejores_jugadores(jugadores:List[Jugador]) -> None:
    print("\ntest_mejores_jugadores")
    año = 1969
    n = 4
    resultado = mejores_jugadores(jugadores,año,n)
    print(f"Los {n} mejores jugadores nacidos en {año} son: {resultado}")

def test_jugadores_por_golpes(jugadores:List[Jugador]) -> None:
    print("\ntest_jugadores_por_golpes")
    resultado = jugadores_por_golpes(jugadores)
    print("Jugadores por golpes")
    for i in resultado:
        print(i)

def test_promedio_ultimos_resultados(jugadores:List[Jugador]) -> None: 
    print("\ntest_promedio_ultimos_resultados")
    f1 = date(2020,3,1)
    f2 = date(2020,5,31)
    resultado = promedio_ultimos_resultados(jugadores,f1,f2)
    print(f"El promedio de cada jugador senior con fecha de actualización entre {f1} y {f2} son:")
    print(*resultado)

def test_jugador_menor_handicap_por_federacion(jugadores:List[Jugador]) -> None: 
    print("\ntest_jugador_menor_handicap_por_federacion")
    resultado = jugador_menor_handicap_por_federacion(jugadores)
    print("Los mejores jugadores de cada federación son:")
    for i in resultado.items():
        print(f"\t{i}")

def test_comparativa_de_mejores_resultados_segun_handicap(jugadores:List[Jugador]) -> None:
    print("\ntest_comparativa_de_mejores_resultados_segun_handicap")
    resultado = comparativa_de_mejores_resultados_segun_handicap(jugadores)
    print(resultado)

if __name__ == "__main__":
    jugadores = lee_jugadores("ExamenesPython/Ex_Jugadores/data/Datos para el ejercicio de Python.csv")
    test_lee_jugadores(jugadores)
    test_mejores_jugadores(jugadores)
    test_jugadores_por_golpes(jugadores)
    test_promedio_ultimos_resultados(jugadores)
    test_jugador_menor_handicap_por_federacion(jugadores)
    test_comparativa_de_mejores_resultados_segun_handicap(jugadores)