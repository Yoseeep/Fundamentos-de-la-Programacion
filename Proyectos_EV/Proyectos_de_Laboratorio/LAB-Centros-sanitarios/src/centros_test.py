from centros import *

def test_leer_centros(datos:List[CentroSanitario])->None:
    print("\ntest_leer_centros")
    print("\tLos dos primeros:",datos[:1])
    print("\tLos dos últimos:",datos[-2:])

def test_calcular_total_camas_centros_accesibles(datos:List[CentroSanitario])->None:
    print("\ntest_calcular_total_camas_centros_accesibles")
    print(f"El total de camas de los centros accesibles es: {calcular_total_camas_centros_accesibles(datos)}")

def test_obtener_centros_con_uci_cercanos_a(datos:List[CentroSanitario])->None:
    punto = Coordenadas(36.52016726032299, -6.151330284937666)
    umbral = 10
    print("\ntest_obtener_centros_con_uci_cercanos_a")
    print(f"Los centros con UCI cercanos a {punto} son:")
    for centro in obtener_centros_con_uci_cercanos_a(datos, punto, umbral):
        print(f"\t{centro[0]} en {centro[1]}")

def test_generar_mapa(datos:List[Tuple[str,str,Coordenadas]])->None:
    print("\ntest_generar_mapa")
    print("Generando mapa...")
    ruta = "/home/josema/Escritorio/Universidad/Fundamentos-de-la-Programacion/Proyectos_EV/Proyectos_de_Laboratorio/LAB-Centros-sanitarios-main/mapa.html"
    generar_mapa(datos, ruta)
    print("Mapa generado")

if __name__ == "__main__":
    datos = leer_centros("data/centrosSanitarios.csv")
    test_leer_centros(datos)
    test_calcular_total_camas_centros_accesibles(datos)
    test_obtener_centros_con_uci_cercanos_a(datos)
    test_generar_mapa([(centro.nombre, centro.localidad, centro.ubicacion) for centro in datos])
    #test_generar_mapa([info for info in obtener_centros_con_uci_cercanos_a(datos, punto=Coordenadas(36.52016726032299, -6.151330284937666), umbral=10)])