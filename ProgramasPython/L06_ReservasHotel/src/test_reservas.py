from reservas import *

def imprime_nombres(funcion):
    def decorador(*args,**kwargs):
        print(f"\n{funcion.__name__}")
        funcion(*args,**kwargs)
    return decorador

@imprime_nombres
def test_lee_reservas(datos:List[Reserva])->None:
    print(f"Total reservas: {len(datos)}")
    print("Las tres primeras:")
    for dato in datos[:3]:
        print(dato)
    print("Las tres últimas:")
    for dato in datos[-3:]:
        print(dato)

@imprime_nombres
def test_total_facturado(datos:List[Reserva])->None:
    facturado = total_facturado(datos)
    print(f"Total faturado en todas las fechas: {facturado}")
    f_i = date(2022,2,1)
    f_f = date(2022,2,28)
    facturado = total_facturado(datos,f_i,f_f)
    print(f"Total faturado entre el {f_i} y {f_f}: {facturado}")
    f_i = date(2022,2,1)
    f_f = None
    facturado = total_facturado(datos,f_i,f_f)
    print(f"Total faturado desde el {f_i}: {facturado}")
    f_i = None
    f_f = date(2022,2,28)
    facturado = total_facturado(datos,f_i,f_f)
    print(f"Total faturado hasta el {f_f}: {facturado}")

@imprime_nombres
def test_servicios_adicionales(datos:List[Reserva])->None:
    print(f"Los distintos servicios adiconales son: {servicios_adicionales(datos)}")

@imprime_nombres
def test_reservas_más_largas(datos:List[Reserva])->None:
    n = 3
    print(f"Con n={n} {reservas_más_largas(datos,n)}")

@imprime_nombres
def test_dni_por_tipo(datos:List[Reserva])->None:
    servicio = "Piscina"
    información = dni_por_tipo(datos,servicio)
    print(f"Los distintos dni's con servicio adicional {servicio}, por tipo de habitación son:")
    for tipo,dnis in  información.items():
        print(tipo, "-->", dnis)

@imprime_nombres
def test_cliente_mayor_facturacion(datos:List[Reserva])->None:
    print("Sin filtrar servicios:",cliente_mayor_facturacion(datos))
    servicios = {"Parking"}
    print(f"Con \"Parking\": {cliente_mayor_facturacion(datos,servicios)}")
    servicios = {"Parking","Spa"}
    print(f"Con Parking o Spa: {cliente_mayor_facturacion(datos,servicios)}")


if __name__ == "__main__":
    reservas = lee_reservas("ProgramasPython/L06_ReservasHotel/data/reservas.csv")
    test_lee_reservas(reservas)
    test_total_facturado(reservas)
    test_servicios_adicionales(reservas)
    test_reservas_más_largas(reservas)
    test_dni_por_tipo(reservas)
    test_cliente_mayor_facturacion(reservas)