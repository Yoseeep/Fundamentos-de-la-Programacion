from vacunas import *

def test_lee_vacunas(datos:List[Vacuna])->None:
    print(f"Número de registros leídos: {len(datos)}")
    print(f"Los dos primeros: {datos[0]}, {datos[1]}")
    print(f"Los dos últimos: {datos[-2]},{datos[-1]}")

def test_cuenta_vacunas_de_marca(datos:List[Vacuna],marca:str)->int:
    vacuna_de_marca_contadas = cuenta_vacunas_de_marca(datos,marca)
    print(f"El número de vacunas de la marca {marca} es: {vacuna_de_marca_contadas}")

def test_obtener_vacunados_el(datos:List[Vacuna])->None:
    fecha=date(2021,5,8)
    personas = obtener_vacunados_el(datos,fecha)
    print(f"Los vacunados el {fecha} son: {personas}")


if __name__ == "__main__":
    vacunas = lee_vacunas("ProgramasPython/T08_VacunasCovid/data/vacunas_covid.csv")
    test_lee_vacunas(vacunas)
    test_cuenta_vacunas_de_marca(vacunas,"JANSSEN")
    test_cuenta_vacunas_de_marca(vacunas,"MODERNA")
    test_obtener_vacunados_el(vacunas)


