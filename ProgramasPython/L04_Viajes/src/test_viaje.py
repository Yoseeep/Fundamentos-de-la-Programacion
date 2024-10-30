from viaje import *

def test_lee_viajes(datos:str)->None:
    print("\ntest_lee_viajes")
    print(f"El número de viajes leídos es: {len(datos)}",end="")
    print(f"\nLos dos primeros: {datos[:2]}",end="")
    print(f"\nLos dos últimos: {datos[:2]}")

def test_ciudades_distintas(datos:str)->None:
    print("\ntest_ciudades_distintas")
    print(f"Las distintas ciudades a las que se puede viajar son: {ciudades_distintas(datos)}")

def test_visitan_en_fecha(datos:str,ciudad:str,fecha:date=None)->None:
    # No lo he hecho exactamente como pone en el REAME pero así me parece más reutilizable
    print("\ntest_visitan_en_fecha")
    viajesFiltrados = viajes_visitan_en_fecha(datos,ciudad,fecha)
    print("Los viajes que",end="" )
    if fecha == None:
        print(f" visitan {ciudad}",end="")
    else: 
        print(f" están de viaje el {fecha} y visitan {ciudad}",end="")
    print(f" son: {viajesFiltrados}")

def test_viajes_que_visitan_más_ciudades(datos:str)->None:
    print("\ntest_viajes_que_visitan_más_ciudades")
    filtrado = viajes_que_visitan_más_ciudades(datos)
    for i in filtrado:
        print(i)

def test_n_viajes_más_importe(datos:str)->None:
    print("\ntest_n_viajes_más_importante")
    año_salida = 2025
    viajes = n_viajes_más_importe(datos,año_salida)
    print(f"Los cuatro viajes de más importe del {año_salida} son: {viajes}")
    año_salida = 2024
    n = 5
    viajes = n_viajes_más_importe(datos,año_salida,n=5)
    print(f"\nLos {n} viajes de más importe del {año_salida} son: {viajes}")

if __name__ == "__main__":
    viajes = lee_viajes("ProgramasPython/L04_Viajes/data/viajes.csv")
    test_lee_viajes(viajes)
    test_ciudades_distintas(viajes)
    test_visitan_en_fecha(viajes,'Berlín')
    test_visitan_en_fecha(viajes,'Roma',date(2024,8,15))
    test_viajes_que_visitan_más_ciudades(viajes)
    test_n_viajes_más_importe(viajes)