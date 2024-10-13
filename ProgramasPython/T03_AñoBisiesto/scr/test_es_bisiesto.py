from es_bisiesto import *

print("Comprobación de si un año es bisiesto.")
print("*"*20)

año = int(input("Introduce un año o pulsa 0 para salir: "))
while año != 0:
    if es_bisiesto(año):
        print("Es bisiesto.") 
    else:
        print("No es bisiesto.")
    año = int(input("Introduce un año o pulsa 0 para salir: "))