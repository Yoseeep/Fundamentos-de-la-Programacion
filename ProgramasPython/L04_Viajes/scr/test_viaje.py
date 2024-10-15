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
    print("\ntest_visitan_en_fecha")
    viajesFiltrados = viajes_visitan_en_fecha(datos,ciudad,fecha)
    print("Los viajes que",end="" )
    if fecha == None:
        print(f" visitan {ciudad}",end="")
    else: 
        print(f" están de viaje el {fecha} y visitan {ciudad}",end="")
    print(f" son: {viajesFiltrados}")

def test_viajes_que_visitan_más_ciudades(datos:str):
    print("\ntest_viajes_que_visitan_más_ciudades")
    filtrado = viajes_que_visitan_más_ciudades(datos)
    print(filtrado)

if __name__ == "__main__":
    viajes = lee_viajes("ProgramasPython/L04_Viajes/data/viajes.csv")
    test_lee_viajes(viajes)
    test_ciudades_distintas(viajes)
    test_visitan_en_fecha(viajes,'Berlín')
    test_visitan_en_fecha(viajes,'Roma',date(2024,8,15))
    test_viajes_que_visitan_más_ciudades(viajes)