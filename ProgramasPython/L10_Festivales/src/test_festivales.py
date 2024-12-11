from festivales import *

def test_lee_festivales(festivales:List[Festival])->None:
    print("test_lee_festivales")
    print("Registros leídos:",len(festivales))
    print("Los 3 primeros:")
    for festival in festivales[:3]:
        print("\t",festival)

def test_total_facturado(festivales:List[Festival])->None:
    print("test_total_facturado")
    f1 = None
    f2 = None
    print(f"Entre {f1} y {f2} el total es: {total_facturado(festivales,f1,f2)}")
    f1 = None
    f2 = date(2024,6,15)
    print(f"Entre {f1} y {f2} el total es: {total_facturado(festivales,f1,f2)}")
    f1 = date(2024,6,15)
    f2 = None
    print(f"Entre {f1} y {f2} el total es: {total_facturado(festivales,f1,f2)}")
    f1 = date(2024,6,1)
    f2 = date(2024,6,15)
    print(f"Entre {f1} y {f2} el total es: {total_facturado(festivales,f1,f2)}")
    f1 = date(2024,6,1)
    f2 = date(2024,6,23)
    print(f"Entre {f1} y {f2} el total es: {total_facturado(festivales,f1,f2)}")

if __name__ == "__main__":
    festivales = lee_festivales("ProgramasPython/L10_Festivales/data/festivales.csv")
    test_lee_festivales(festivales)
    test_total_facturado(festivales)
