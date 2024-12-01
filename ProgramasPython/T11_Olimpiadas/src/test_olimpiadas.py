from olimpiadas import *

def test_lee_registros_olimpiadas(datos:List[Registro])->None:
    print("\ntest_lee_registros_olimpiadas")
    print("Registros leídos:", len(datos))
    print("Los dos primeros:", datos[:2])
    print("Los dos últimos:",datos[-2:])

def test_deportes_ambos_generos(datos:List[Registro])->None:
    print("\ntest_deportes_ambos_generos")
    año = 1984
    ambos_generos = deportes_ambos_generos(datos,año)
    print(f"Los deportes con participación de ambos géneros en el año {año} son: {ambos_generos}")
    año = 1992
    ambos_generos = deportes_ambos_generos(datos,año)
    print(f"Los deportes con participación de ambos géneros en el año {año} son: {ambos_generos}")
    año = 1988
    ambos_generos = deportes_ambos_generos(datos,año)
    print(f"Los deportes con participación de ambos géneros en el año {año} son: {ambos_generos}")

def test_deportes_mas_frecuentes(datos:List[Registro])->None:
    print("\ntest_deportes_mas_frecuentes")
    n = 3
    frecuentes = deportes_mas_frecuentes(datos,n)
    print(f"Los {n} deportes de mayor frecuencia son: {frecuentes}")
    n = 4
    género = "HOMBRE"
    frecuentes = deportes_mas_frecuentes(datos,n,género)
    print(f"Los {n} deportes de mayor frecuencia son: {frecuentes}")
    n = 5
    género = "MUJER"
    frecuentes = deportes_mas_frecuentes(datos,n,género)
    print(f"Los {n} deportes de mayor frecuencia son: {frecuentes}")

def test_deporte_femenino_con_mas_paises_distintos_con_oro(datos:List[Registro])->None:
    print("\ntest_deporte_femenino_con_mas_paises_distintos_con_oro")
    deporte = deporte_femenino_con_mas_paises_distintos_con_oro(datos)
    print("Deporte:",deporte)

def test_deportes_mas_participantes_de_genero_por_juego(datos:List[Registro])->None:
    print("\ntest_deportes_mas_participantes_de_genero_por_juego")
    género = "MUJER"
    país = "ESPAÑA"
    resultado = deportes_mas_participantes_de_genero_por_juego(datos,país,género)
    print("Los tres deportes con más registros de género {género} de {país} son:")
    for i,r in enumerate(resultado.items()):
        print(f"{i}-{r}")
    
    género = "HOMBRE"
    país = "PORTUGAL"
    resultado = deportes_mas_participantes_de_genero_por_juego(datos,país,género)
    print("\nLos tres deportes con más registros de género {género} de {país} son:")
    for i,r in enumerate(resultado.items()):
        print(f"{i}-{r}")

def test_deporte_con_todos_los_paises(datos:List[Registro])->None:
    print("\ntest_deporte_con_todos_los_paises")
    print("¿Algún deporte en todos los países?:", deporte_con_todos_los_paises(datos))

def test_año_con_mayor_incremento_participantes_de_pais(datos:List[Registro])->None:
    print("\ntest_año_con_mayor_incremento_participantes_de_pais")
    pais = "ESPAÑA"
    print(f"El año con mayor incremento para {pais}, ha sido:", año_con_mayor_incremento_participantes_de_pais(datos,pais))
    pais = "PORTUGAL"
    print(f"El año con mayor incremento para {pais}, ha sido:", año_con_mayor_incremento_participantes_de_pais(datos,pais))

if __name__ == "__main__":
    registros = lee_registros_olimpiadas("ProgramasPython/T11_Olimpiadas/data/olimpiadas.csv")
    test_lee_registros_olimpiadas(registros)
    test_deportes_ambos_generos(registros)
    test_deportes_mas_frecuentes(registros)
    test_deporte_femenino_con_mas_paises_distintos_con_oro(registros)
    test_deportes_mas_participantes_de_genero_por_juego(registros)
    test_deporte_con_todos_los_paises(registros)
    test_año_con_mayor_incremento_participantes_de_pais(registros)