from consumo_electrico import *

def test_lee_facturas(facturas:List[Factura])->None:
    print("\ntest_lee_facturas")
    print("Total facturas:", len(facturas))
    print("Mostrando las dos primeras y las dos últimas:")
    print(facturas[0])
    print(facturas[1])
    print("...")
    print(facturas[-2])
    print(facturas[-1])
    
def test_extrae_precio_por_mes(facturas:List[Factura])->None:
    print("\ntest_extrae_precio_por_mes")
    print("(Se muestra el resultado obtenido para dos llamadas a la función)")

    tipo_tarifa = "única"
    print(f"\nPrecio por mes (tarifa {tipo_tarifa})")
    resultado = extrae_precio_por_mes(facturas,tipo_tarifa)
    for c,v in resultado.items():
        print(f"\t{c}: {v}")
    
    tipo_tarifa = "tramos"
    print(f"\nPrecio por mes (tarifa {tipo_tarifa})")
    resultado = extrae_precio_por_mes(facturas,tipo_tarifa)
    for c,v in resultado.items():
        print(f"\t{c}: {v}")

def test_busca_vivienda_mayor_consumo_acumulado(facturas:Factura)->None:
    print("\ntest_busca_vivienda_mayor_consumo_acumulado")
    resultado = busca_vivienda_mayor_consumo_acumulado(facturas)
    print(f"La vivienda con mayor consumo acumulado es la {resultado[0]} con un consumo total de {resultado[1]} kWh.")

def test_barrios_mayor_consumo_valle_medio(facturas:Factura)->None:
    print("\ntest_barrios_mayor_consumo_valle_medio")
    resultado = barrios_mayor_consumo_valle_medio(facturas,top_n=3)
    print("Los tres barrios con mayor consumo medio en horario valle son:")
    for i in resultado:
        print(f"\t{i}")

def test_compara_importe_tipos_factura(facturas:List[Factura])->None:
    print("\ntest_compara_importe_tipos_factura")
    id_vivienda ="005"
    resultado = compara_importe_tipos_factura(facturas,id_vivienda)
    print(f"La vivienda {id_vivienda}, haciendo un cambio de tarifa {resultado[0]}, había pagado un total de {resultado[2]} en lugar de {resultado[1]} si hubiera cambiado de tipo de tarifa.")

def test_busca_cambios_beneficiosos(facturas:List[Factura]):
    print("\ntest_busca_cambios_beneficiosos")
    resultado = busca_cambios_beneficiosos(facturas)
    print(resultado)

if __name__ == "__main__":
    facturas = lee_facturas("ExamenesPython/Ex_Consumos_eléctricos/data/CSV de la sesión 1.csv")
    test_lee_facturas(facturas)
    test_extrae_precio_por_mes(facturas)
    test_busca_vivienda_mayor_consumo_acumulado(facturas)
    test_barrios_mayor_consumo_valle_medio(facturas)
    test_compara_importe_tipos_factura(facturas)
    test_busca_cambios_beneficiosos(facturas)