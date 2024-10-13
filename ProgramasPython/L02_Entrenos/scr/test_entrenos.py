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


if __name__ == '__main__':
    entrenos = lee_entrenos('ProgramasPython/L02_Entrenos/data/entrenos.csv')
    test_lee_entrenos(entrenos)
    test_filtra_por_tipo(entrenos,'Remo')
    test_suma_de_calorias(entrenos,'Senderismo','Andar')