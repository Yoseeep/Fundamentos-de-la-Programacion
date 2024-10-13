import suma_números

print("Programa que pide por teclado 3 números enteros y devuelva la suma de los números comprendidos entre los 2 primeros y que sean múltiplo del tercero.")

a = int(input("Teclea el primer número: "))
b = int(input("Teclea el segundo número: "))
c = int(input("Teclea el tercer número: "))

resultado = suma_números.sumar_números(a,b,c)
if resultado == None:
    print("Error en los datos")
else:
    print(f"La suma de los números entre {a} y {b} múltiplos de {c} es: {resultado}")