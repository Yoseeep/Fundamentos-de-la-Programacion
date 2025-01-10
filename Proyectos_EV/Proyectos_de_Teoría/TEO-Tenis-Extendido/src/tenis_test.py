from tenis import *

def imprime_nombre_test(num_test):
    def mi_decorador(func):
        def envoltura(*args, **kwargs):
            print(f"\nEJERCICIO {num_test} ==================================================")
            print(f"Test de '{func.__name__.replace("test_","")}'")
            func(*args,**kwargs)
        return envoltura
    return mi_decorador

@imprime_nombre_test(1)
def test_lee_partido_tenis(partidos:List[PartidoTenis]) -> None:
    print("Número total de partidos leídos:", len(partidos))
    print("Mostrando los tres primeros registros leídos:")
    for i in partidos[:3]:
        print(f"\t{i}")

@imprime_nombre_test(2)
def test_partidos_menos_errores(partidos:List[PartidoTenis]) -> None:
    resultado = partidos_menos_errores(partidos)
    print("El partido con menos errores es", resultado)

@imprime_nombre_test(3)
def test_jugador_mas_partidos(partidos:List[PartidoTenis]) -> None:
    resultado = jugador_mas_partidos(partidos)
    print(f"El jugador que ha jugado más partidos es {resultado[0]}, con un total de {resultado[1]} partidos")

@imprime_nombre_test(4)
def test_tenista_mas_victorias(partidos:List[PartidoTenis]) -> None:
    print(tenista_mas_victorias(partidos))
    for f1,f2 in ((None,None),(None,date(2020,1,1)),(date(2020,1,1),None),(date(2013,1,1),date(2020,1,1))): 
        resultado = tenista_mas_victorias(partidos,f1,f2)
        print(f"El tenista con más victorias entre las fechas {f1} y {f2} es {resultado}")

@imprime_nombre_test(5)
def test_media_errores_por_jugador(partidos:List[PartidoTenis]) -> None: 
    print("La media de errores por jugador, de menos a más errores es")
    resultado = media_errores_por_jugador(partidos)
    for i,tupla in enumerate(resultado):
        print(f"{i+1}-{tupla}")

@imprime_nombre_test(6)
def test_jugadores_mayor_porcentaje_victorias(partidos:List[PartidoTenis]) -> None:
    print("El porcentaje de victorias de cada jugador (ordendo de mayor a menor) es")
    resultado = jugadores_mayor_porcentaje_victorias(partidos)
    for i,tupla in enumerate(resultado):
        print(f"{i+1}-{tupla}")

@imprime_nombre_test(7)
def test_n_tenistas_con_mas_errores(partidos:List[PartidoTenis]) -> None: 
    n = 5
    print(f"Los {n} tenistas con mas errores son:")
    resultado = n_tenistas_con_mas_errores(partidos)
    for i,tupla in enumerate(resultado):
        print(f"{i+1}-{tupla}")

@imprime_nombre_test(8)
def test_fechas_ordenadas_por_jugador(partidos:List[PartidoTenis]) -> None:
    print("Las fechas de cada partido por jugador son")
    resultado = fechas_ordenadas_por_jugador(partidos)
    for c,v in resultado.items():
        print(f"{c} --> {v}")
    
@imprime_nombre_test(9)
def test_num_partidos_nombre(partidos:List[PartidoTenis]) -> None:
    for tenista in ("Rafael Nadal","Carlos Alcaraz"):
        print(f"Los partidos jugados y ganados por superficie de {tenista} son")
        resultado = num_partidos_nombre(partidos,tenista)
        for c,v in resultado.items():
            print(f"{c} --> {v}")

@imprime_nombre_test(10)
def test_num_tenistas_distintos_por_superficie(partidos:List[PartidoTenis]) -> None:
    print("El número de tenistas distintos segun cada superficie es")
    resultado = num_tenistas_distintos_por_superficie(partidos)
    for c,v in resultado.items():
        print(f"{c} --> {v}")

@imprime_nombre_test(11)
def test_superficie_con_mas_tenistas_distintos(partidos:List[PartidoTenis]) -> None:
    resultado = superficie_con_mas_tenistas_distintos(partidos)
    print(f"La superficie con más jugadores distintos es {resultado[0]} en la que han jugado {resultado[1]} tenistas")

@imprime_nombre_test(12)
def test_mas_errores_por_jugador(partidos:List[PartidoTenis]) -> None:
    print("El partido con más errores por cada jugador es")
    resultado = mas_errores_por_jugador(partidos)
    for c,v in resultado.items():
        print(f"{c} --> {v}")

@imprime_nombre_test(13)
def test_partido_mas_errores_por_mes(partidos:List[PartidoTenis]) -> None:
    for superficies in (['Sintética'],['Sintética', 'Tierra'],None):
        print(f"Los partidos con mas errores para las superificies {superficies} son")
        resultado = partido_mas_errores_por_mes(partidos,superficies)
        for c,v in resultado.items():
            print(f"{c} --> {v}")

@imprime_nombre_test(14)
def test_n_partidos_mas_errores_por_jugador(partidos:List[PartidoTenis]) -> None:
    n=3
    resultado = n_partidos_mas_errores_por_jugador(partidos,n)
    print(f"Los {n} partidos con mas errores para los jugadores son")
    for c,v in resultado.items():
            print(f"{c} --> {v}")

@imprime_nombre_test(15)
def test_mayor_numero_dias_sin_jugar(partidos:List[PartidoTenis]) -> None:
    jugador = "Carlos Alcaraz"
    resultado = mayor_numero_dias_sin_jugar(partidos,jugador)
    print(f"El mayor número de días que el jugador {jugador} ha estado sin jugar es {resultado} días")

if __name__ == "__main__":
    partidos = lee_partidos_tenis("Proyectos_EV/Proyectos_de_Teoría/TEO-Tenis-Extendido/data/tenis.csv")
    test_lee_partido_tenis(partidos)
    test_partidos_menos_errores(partidos)
    test_jugador_mas_partidos(partidos)
    test_tenista_mas_victorias(partidos)
    test_media_errores_por_jugador(partidos)
    test_jugadores_mayor_porcentaje_victorias(partidos)
    test_n_tenistas_con_mas_errores(partidos)
    test_fechas_ordenadas_por_jugador(partidos)
    test_num_partidos_nombre(partidos)
    test_num_tenistas_distintos_por_superficie(partidos)
    test_superficie_con_mas_tenistas_distintos(partidos)
    test_mas_errores_por_jugador(partidos)
    test_partido_mas_errores_por_mes(partidos)
    test_n_partidos_mas_errores_por_jugador(partidos)
    test_mayor_numero_dias_sin_jugar(partidos)
