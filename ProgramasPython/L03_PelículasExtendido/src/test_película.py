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
        print('\n',peli)

def test_última_película_premiada(datos:List[Película])->None:
    print("\n"+'test_última_película_premiada')
    print("La última película con premio es:",última_película_premieda(datos))
        
def test_n_premiadas_mayor_recaudación(datos:List[Película])->None:
    print("\n"+"test_n_premiadas_mayor_recaudación")
    valor:str = 'SI'
    n:int = 3
    pelis = n_premiadas_mayor_recaudación(datos, valor,n)
    print(f"Las {n} películas mcon premio {valor} son : {pelis}")
    valor:str = 'NO'
    n:int = 2
    pelis = n_premiadas_mayor_recaudación(datos, valor,n)
    print(f"Las {n} películas mcon premio {valor} son : {pelis}")

if __name__ == "__main__":
    películas = lee_películas("ProgramasPython/L03_PelículasExtendido/data/películas_extendido.csv")
    test_lee_películas(películas)
    test_película_más_cara(películas)
    test_película_menos_beneficio(películas)
    test_n_películas_más_largas(películas,5)
    test_última_película_premiada(películas)
    test_n_premiadas_mayor_recaudación(películas)
   
   