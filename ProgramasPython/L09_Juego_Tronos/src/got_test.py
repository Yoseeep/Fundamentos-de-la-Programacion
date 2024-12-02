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

if __name__ == "__main__":
    batallas = lee_batallas("ProgramasPython/L09_Juego_Tronos/data/battles.csv")
    test_lee_batallas(batallas)
    test_batallas_mas_comandantes(batallas)