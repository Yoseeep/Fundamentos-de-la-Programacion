from receta import *

def test_lee_recetas(datos:Receta)->None:
    print("\n"+test_lee_recetas.__name__)
    print(f"Registros leídos: {len(datos)}")
    print(f"Los dos primeros: {datos[:2]}")
    print(f"\nLos dos últimos: {datos[-2:]}")

def test_diferentes_ingredientes(datos:List[Receta])->None:
    print("\ntest_diferentes_ingredientes")
    diferentes = diferentes_ingredientes(datos)
    print(f"Todos los diferentes ingredientes son: {diferentes}")
    diferentes = diferentes_ingredientes(datos,'gr')
    print(f"\nLos diferentes ingredientes que se miden en gr son: {diferentes}")
    diferentes = diferentes_ingredientes(datos,'cl')
    print(f"\nLos diferentes ingredientes que se miden en cl son: {diferentes}")

def test_recetas_con_ingredientes(datos:List[Receta])->None:
    print("\ntest_diferentes_ingredientes")
    conjunto = {'harina','azúcar'}
    lista = recetas_con_ingredientes(datos,conjunto)
    print(f"Las recetas con alguno de los siguientes ingredientes {conjunto} son: {lista}")
    conjunto = {'pimiento','tomate','cebolla'}
    lista = recetas_con_ingredientes(datos,conjunto)
    print(f"\nLas recetas con alguno de los siguientes ingredientes {conjunto} son: {lista}")

def test_receta_más_barata(datos:List[Receta])->None:
    print(f"\ntest_receta_más_barata")
    tipos = {'Entrante', 'Postre'}
    barato = receta_más_barata(datos,tipos)
    print(f"La receta más barata de que sean de alguno de los siguientes tipos {tipos} es {barato}")
    tipos = {'Plato Principal', 'Postre'}
    n = 5
    barato = receta_más_barata(datos,tipos,5)
    print(f"\nLa receta más barata de que las {n} con menos calorías de los siguientes tipos {tipos} es {barato}")
    

if __name__ == "__main__":
    recetas = lee_recetas("ProgramasPython/L05_Recetas/data/recectas.csv")
    test_lee_recetas(recetas)
    test_diferentes_ingredientes(recetas)
    test_recetas_con_ingredientes(recetas)
    test_receta_más_barata(recetas)