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

if __name__ == "__main__":
    reservas = lee_reservas("ProgramasPython/L06_ReservasHotel/data/reservas.csv")
    test_lee_reservas(reservas)