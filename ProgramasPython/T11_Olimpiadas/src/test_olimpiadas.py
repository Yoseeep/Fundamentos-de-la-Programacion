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

if __name__ == "__main__":
    registros = lee_registros_olimpiadas("ProgramasPython/T11_Olimpiadas/data/olimpiadas.csv")
    test_lee_registros_olimpiadas(registros)
    test_deportes_ambos_generos(registros)
    test_deportes_mas_frecuentes(registros)
    test_deporte_femenino_con_mas_paises_distintos_con_oro(registros)