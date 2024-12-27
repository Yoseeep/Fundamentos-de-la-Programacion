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

def test_artista_top(festivales:List[Festival])->None:
    print("\ntest_artista_top")
    print("El artista que ha actuado en más festivales es",artista_top(festivales))

def test_artistas_comunes(festivales:List[Festival])->None:
    print("\ntest_artistas_comunes")
    festival1 = "Creamfields"
    festival2 = "Tomorrowland"
    print(f"Los artistas comunes entre {festival1} y {festival2} son: {artistas_comunes(festivales, festival1,festival2)}")
    festival1 = "Primavera Sound"
    festival2 = "Coachella"
    print(f"Los artistas comunes entre {festival1} y {festival2} son: {artistas_comunes(festivales, festival1,festival2)}")
    festival1 = "Iconica Fest"
    festival2 = "Primavera Sound"
    print(f"Los artistas comunes entre {festival1} y {festival2} son: {artistas_comunes(festivales, festival1,festival2)}")

def test_festivales_top_calidad_por_duracion(festivales:List[Festival])->None:
    print("\ntest_festivales_top_calidad_por_duracion")
    n = 1
    print(f"Para n={n}, los festivales top son:")
    for c,v in festivales_top_calidad_por_duracion(festivales,n).items():
        print(c,"-->",v)
    n = 4
    print(f"\nPara n={n}, los festivales top son:")
    for c,v in festivales_top_calidad_por_duracion(festivales,n).items():
        print(c,"-->",v)

def test_mes_mayor_beneficio_medio(festivales:List[Festival])->None:
    print("\ntest_mes_mayor_beneficio_medio")
    print("El mes de mayor beneficio medio es:", mes_mayor_beneficio_medio(festivales))

def test_variación_mensual_asistentes(festivales:List[Festival])->None:
    print("\ntest_variación_mensual_asistentes")
    print(variación_mensual_asistentes(festivales))

if __name__ == "__main__":
    festivales = lee_festivales("ProgramasPython/L10_Festivales/data/festivales.csv")
    test_lee_festivales(festivales)
    test_total_facturado(festivales)
    test_artista_top(festivales)
    test_artistas_comunes(festivales)
    test_festivales_top_calidad_por_duracion(festivales)
    test_mes_mayor_beneficio_medio(festivales)
    test_variación_mensual_asistentes(festivales)
