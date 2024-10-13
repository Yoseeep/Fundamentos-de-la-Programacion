from película import *

def test_lee_películas(datos:List[Película])->None:
    print('test_lee_películas')
    print('Número de películas leídas:',len(datos))
    print('Las dos primeras:', datos[:2])
    print('Las dos últimas:', datos[-2:])

def test_película_más_cara(datos:List[Película])->None:
    print('\n'+'test_película_más_cara')
    print('La película más cara es:',película_más_cara(datos))
    
def test_película_menos_beneficio(datos:List[Película])->None:
    print("\n"+'test_película_menos_beneficio')
    print("La película con menos beneficio es:",película_menos_beneficio(datos))

def test_n_películas_más_largas(datos:List[Película],n:int)->None:
    print("\n"+'test_n_películas_más_largas')
    print(f"Las {n} películas de más duración son: (una debajo de otra)") 
    for peli in n_películas_más_largas(datos,n):
        print(peli,'\n')
        

if __name__ == "__main__":
    películas = lee_películas("ProgramasPython/L03_Películas/data/películas.csv")
    test_lee_películas(películas)
    test_película_más_cara(películas)
    test_película_menos_beneficio(películas)
    test_n_películas_más_largas(películas,5)