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

def test_vacunados_con_al_menos_reacciones_que(datos:List[Vacuna],*numReac:int)->None:
    print('\n'+"test_vacunados_con_al_menos_reacciones_que")
    for n in numReac:
        print(f"El número de vacunados con {n} reacciones o más es: {vacunados_con_al_menos_reacciones_que(datos,n)}")

def test_vacunados_según_pauta_y_reacción(datos:List[Vacuna],valPauta:str,reacción:str)->None:
    print('\n'+'test_vacunados_según_pauta_y_reacción')
    listado = vacunados_según_pauta_y_reacción(datos,valPauta,reacción)
    print(f"Los vacunados con pauta completa {valPauta} y reacción {reacción} son: {listado}")

def test_n_vacunados_mayores(datos:List[Vacuna], n:int)->None:
    print('\n'+"test_n_vacunados_mayores")
    listado = n_vacunados_mayores(datos,n)
    print(f"Los {n} vacunados de mayor edad son: {listado}")

def test_el_último_vacunado(datos:List[Vacuna])->None:
    print('\n'+"test_el_último_vacunado")
    ultimo_vacunado = el_último_vacunado(datos)
    print(f"El último vacunado es: {ultimo_vacunado}")

if __name__ == "__main__":
    vacunas = lee_vacunas("ProgramasPython/T08_VacunasCovidExtendido/data/vacunas_covid_extendido.csv")
    test_lee_vacunas(vacunas)
    test_cuenta_vacunas_de_marca(vacunas,"JANSSEN")
    test_cuenta_vacunas_de_marca(vacunas,"MODERNA")
    test_obtener_vacunados_el(vacunas)
    test_vacunados_con_al_menos_reacciones_que(vacunas,2,3)
    test_vacunados_según_pauta_y_reacción(vacunas,'S','Mareos')
    test_vacunados_según_pauta_y_reacción(vacunas,'N','Dolor de cabeza')
    test_n_vacunados_mayores(vacunas,4)
    test_el_último_vacunado(vacunas)



