from entrenos import *

def test_lee_entrenos(datos:List[Entreno])->None:
    print('test_lee_entrenos')
    print('Números de registros leidos', len(datos))
    print('Los dos primeros:', datos[0:2])
    print('Los dos últimos:', datos[-2::])

def test_filtra_por_tipo(datos:List[Entreno],tipo:str)->None:
    print('test_filtra_por_tipo')
    print(f"Los entrenamientos con el tipo {tipo} son:")
    filtrados = filtra_por_tipo(datos,tipo)
    print(filtrados)

def test_suma_de_calorias(datos:List[Entreno],*tipos:str)->None:
    print('test_suma_de_calorias')
    for tipo in tipos:
        calorias_sumadas = suma_de_calorias(entrenos,tipo)
        print(f"Los entrenamientos con el tipo {tipo} han consumido: {calorias_sumadas} calorias")

def test_promedio_perdida_peso(datos:List[Entreno],*ubicaciones:str)->None:
    print('test_promedio_perdida_peso')
    for ubicación in ubicaciones:
        promedios = promedio_perdida_peso(entrenos,ubicación)
        print(f"El promedio de perdidad de peso en {ubicación} es: {promedios}")

def test_cuenta_distintos_tipos(entrenos:List[Entreno])->None:
    print("test_cuenta_distintos_tipos")
    contados = cuenta_distintos_tipos(entrenos)
    print(f"El número de distintos tipos de entrenamiento es: {contados}")

def test_obtiene_horas_más_perdida_peso(entrenos:List[Entreno],peso_min_perdido:int)->None:
    print("test_obtiene_horas_más_perdida_peso")
    horas = obtiene_horas_más_perdida_peso(entrenos,peso_min_perdido)
    print(f"Las horas con más perdida de peso que {peso_min_perdido} kg. son: {horas}")

if __name__ == '__main__':
    entrenos = lee_entrenos('ProgramasPython/L02_Entrenos_extendido/data/entrenos_extendido.csv')
    test_lee_entrenos(entrenos)
    test_filtra_por_tipo(entrenos,'Remo')
    test_suma_de_calorias(entrenos,'Senderismo','Andar')
    test_promedio_perdida_peso(entrenos,'Sevilla','Huelva')
    test_cuenta_distintos_tipos(entrenos)
    test_obtiene_horas_más_perdida_peso(entrenos,4.9)
