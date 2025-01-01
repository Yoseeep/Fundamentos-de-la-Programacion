from coordenadas import *

def test_calcular_distancia()->None:
    print("\ntest_calcular_distancia")
    p1 = Coordenadas(1,2)
    p2 = Coordenadas(4,2)
    print(f"La distancia entre {p1} y {p2} es: {calcular_distancia(p1,p2)}")

def test_calcular_media_coordenadas()->None:
    print("\ntest_calcular_media_coordenadas")
    lista = [Coordenadas(1,2),Coordenadas(2,3),Coordenadas(3,4)]
    print(f"La media de las coordenadas {lista} es: {calcular_media_coordenadas(lista)}")

if __name__ == "__main__":
    test_calcular_distancia()
    test_calcular_media_coordenadas()