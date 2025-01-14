from rutas import *

def test_lee_rutas(rutas:List[Ruta]) -> None:
    print("\ntest_lee_rutas")
    print("Rutas leídas:", len(rutas))
    print("Las 2 primeras rutas leídas:", rutas[0],rutas[1],rutas[2])
    print("Las 2 últimas rutas leídas:", rutas[-2],rutas[-1])

def test_acumular_kms_por_meses(rutas:List[Ruta]) -> None:
    print("\ntest_acumular_kms_por_meses")
    resultado = acumular_kms_por_meses(rutas)
    print(resultado)

def test_diferencias_kms_meses_anyo(rutas:List[Ruta]) -> None:
    print("\ntest_diferencias_kms_meses_anyo")
    resultado = diferencias_kms_meses_anyo(rutas)
    print(resultado)

def test_top_rutas_lejanas(rutas:List[Ruta]) -> None:
    print("\ntest_test_top_rutas_lejanas")
    n = 2
    c = Coordenada(35.15,-8.76)
    km_min = None
    resultado = top_rutas_lejanas(rutas,n,c,km_min)
    print(resultado)

def test_ciudades_top_tiempo_dificultad(rutas:List[Ruta]) -> None:
    print("\ntest_ciudades_top_tiempo_dificultad")
    n = 3
    resultado = ciudades_top_tiempo_dificultad(rutas,n)
    print(resultado)



if __name__ == "__main__":
    rutas = lee_rutas("ExamenesPython/Ex_rutas_motos/rutas_motos.csv")
    print("\ncompara",rutas[:3])
    test_acumular_kms_por_meses(rutas) # En el enunciado pone que en febrero de 2022 se hizo 1849.68 kilómetros, pero no hay ninguna ruta que se realizara en febrero de 2022
    test_diferencias_kms_meses_anyo(rutas)
    test_top_rutas_lejanas(rutas)
    test_ciudades_top_tiempo_dificultad(rutas)