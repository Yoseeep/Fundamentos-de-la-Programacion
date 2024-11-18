from f1 import *


def test_lee_carreras(datos:List[Carrera])->None:
    print("\ntest_lee_carreras")
    print("Total registros leídos:",len(datos))
    print("Mostrando los dos primeros registros:")
    for c in datos[:2]:
        print(f"\t{c}")

def test_media_tiempo_boxes(datos:List[Carrera])->None:
    print("\ntest_media_tiempo_boxes")
    ciudad = "Barcelona"
    print(f"La media de tiempo de boxes en la ciudad de {ciudad} es de {media_tiempo_boxes(datos,ciudad)} segundos.")
    ciudad = "Barcelona"
    fecha = date(2023,5,7)
    print(f"La media de tiempo de boxes en la ciudad de {ciudad} es de {media_tiempo_boxes(datos,ciudad,fecha)} segundos.")
    ciudad = "Lepe"
    print(f"La media de tiempo de boxes en la ciudad de {ciudad} es de {media_tiempo_boxes(datos,ciudad)} segundos.")

def test_pilotos_menor_tiempo_medio_vueltas_top(datos:List[Carrera])->None:
    print("\ntest_pilotos_menor_tiempo_medio_vueltas_top")
    n = 4
    print(f"Los {n} pilotos con menos tiempo medio son {pilotos_menor_tiempo_medio_vueltas_top(datos,n)}")

def test_ratio_tiempo_boxes_total(datos:List[Carrera])->None:
    print("\ntest_ratio_tiempo_boxes_total")
    tuplas = ratio_tiempo_boxes_total(datos)
    for nom,fech,ratio in tuplas:
        print(f"({nom}, {fech.strftime("%Y-%m-%d")}, {ratio:.3f})")

def test_mejor_escuderia_año(datos:List[Carrera])->None:
    print("\ntest_mejor_escuderia_año")
    año = 2022
    print(f"La mejor escudería en el año {año} ha sido {mejor_escuderia_año(datos,año)}.")

def test_puntos_piloto_años(datos:List[Carrera])->None:
    print("\ntest_puntos_piloto_años")
    diccionario = puntos_piloto_años(datos)
    for c,v in diccionario.items():
        print(f"{c} --> {v}")

if __name__ == "__main__":
    carreras = lee_carreras("ProgramasPython/L07_CarrerasFormula1/data/f1.csv")
    test_lee_carreras(carreras)
    test_media_tiempo_boxes(carreras)
    test_pilotos_menor_tiempo_medio_vueltas_top(carreras)
    test_ratio_tiempo_boxes_total(carreras)
    test_mejor_escuderia_año(carreras)
    test_puntos_piloto_años(carreras)