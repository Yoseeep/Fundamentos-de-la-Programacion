from f1 import *


def test_lee_carreras(datos:List[Carrera])->None:
    print("\ntest_lee_carreras")
    print("Total registros leídos:",len(datos))
    print("Mostrando los dos primeros registros:")
    for c in datos[:2]:
        print(f"\t{c}")


if __name__ == "__main__":
    carreras = lee_carreras("ProgramasPython/L07_CarrerasFormula1/data/f1.csv")
    test_lee_carreras(carreras)