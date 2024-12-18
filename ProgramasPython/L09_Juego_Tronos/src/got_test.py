from got import *

def test_lee_batallas(datos:List[BatallaGoT])->None:
    print("\ntest_lee_batallas")
    print("Número de batallas leídas:",len(datos))
    print("Primera batalla:", datos[0])
    print("\nCuarta batalla:", datos[3])
    print("\nÚltima batalla:", datos[-1])

def test_batallas_mas_comandantes(datos:List[BatallaGoT])->None:
    print("\test_batallas_mas_comandantes")
    regiones = {'The Riverlands', 'The North'}
    n = 4
    print(f"Para las regiones {regiones} y n={n}, las batallas con más comandantes son:")
    print(batallas_mas_comandantes(datos,regiones,n))

def test_reyes_mayor_menor_ejercito(datos:List[BatallaGoT])->None:
    print("\ntest_reyes_mayor_menor_ejercito")
    print(reyes_mayor_menor_ejercito(datos))

def test_rey_mas_victorias(datos:List[BatallaGoT])->None:
    print("\ntest_rey_mas_victorias")
    print("El rey con más victorias es:", rey_mas_victorias(datos))
    print("El rey atacante con más victorias es:", rey_mas_victorias(datos,"atacante"))
    print("El rey atacado con más victorias es:", rey_mas_victorias(datos,"atacado"))

def test_rey_mas_victorias_por_region(datos:List[BatallaGoT])->None:
    print("\ntest_rey_mas_victorias_por_region")
    print("\nReyes con más victorias por región")
    respuesta = rey_mas_victorias_por_region(datos)
    for c,v in respuesta.items():
        print(c,"-->",v)

    print("\nReyes atacantes con más victorias por región")
    respuesta = rey_mas_victorias_por_region(datos,rol="atacante")
    for c,v in respuesta.items():
        print(c,"-->",v)
    
    print("\nReyes atacados con más victorias por región")
    respuesta = rey_mas_victorias_por_region(datos,rol="atacado")
    for c,v in respuesta.items():
        print(c,"-->",v)


if __name__ == "__main__":
    batallas = lee_batallas("ProgramasPython/L09_Juego_Tronos/data/battles.csv")
    test_lee_batallas(batallas)
    test_batallas_mas_comandantes(batallas)
    test_reyes_mayor_menor_ejercito(batallas)
    test_rey_mas_victorias(batallas)
    test_rey_mas_victorias_por_region(batallas)