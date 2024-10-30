from población import *

def test_lee_poblaciones(datos:List[Población])->None:
    print("\nTotal poblaciones leídas:",len(datos)) 
    print("Las dos primeras: ",datos[:2]) 
    print("Las dos últimas:",datos[-2:])

def test_filtra_por_país(datos:List[Población])->None: 
    print("\nfiltra_por_país") 
    poblaciones_filtradas=filtra_por_país(datos,"Spain") 
    for p in poblaciones_filtradas:
        print(p)

def test_filtra_por_año_y_umbral_de_censo(datos:List[Población])->None: 
    print("\nfiltra_por_año_y_umbral_de_censo") 
    poblaciones_filtradas=filtra_por_año_y_umbral_de_censo(datos,2002,40000000) 
    for p in poblaciones_filtradas:
        print(p)

def test_obtiene_país_y_censo(datos:List[NamedTuple])->None:
    print("\nobtiene_país_y_censo")
    poblaciones_obtenidas = obtiene_país_y_censo(datos)
    for p in poblaciones_obtenidas:
        print(p)

def test_obtiene_código_y_censo_de_año(datos:List[NamedTuple]):
    print("\nobtine_código_y_censo_de_año")
    poblaciones_obtenidas = obtiene_código_y_censo_de_año(datos,2000)
    for p in poblaciones_obtenidas:
        print(p)

def test_suma_población_de_año(datos:List[Población])->int:
    print("\n"+"suma_población_de_año")
    poblaciones_sumadas = suma_población_de_año(datos,2000)
    print(poblaciones_sumadas)
    poblaciones_sumadas = suma_población_de_año(datos,2025)
    print(poblaciones_sumadas)

def test_promedio_población_de_año(datos:List[Población])->float:
    print("\n"+"promedio_población_de_año")
    poblaciones_promedios = promedio_población_de_año(datos,2000)
    print(poblaciones_promedios)
    poblaciones_promedios = promedio_población_de_año(datos,2025)
    print(poblaciones_promedios)

if __name__=='__main__': 
    poblaciones=lee_poblaciones("ProgramasPython/T07_Población/data/poblaciones2.csv") 
    test_lee_poblaciones(poblaciones) 
    test_filtra_por_país(poblaciones) 
    test_filtra_por_año_y_umbral_de_censo(poblaciones)
    test_obtiene_país_y_censo(poblaciones)
    test_obtiene_código_y_censo_de_año(poblaciones)
    test_suma_población_de_año(poblaciones)
    test_promedio_población_de_año(poblaciones)