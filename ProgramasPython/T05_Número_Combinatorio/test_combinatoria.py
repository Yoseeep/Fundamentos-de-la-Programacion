import combinatoria

print("Programa que calcula el número combinatorio de dos número enteros no negativos.")
n1 = int(input("Teclea el primer número: "))
n2 = int(input("Teclea el segundo número: "))

if n1 < n2:
    print("El numerador es más pequeño que el denominador.")
elif n1 < 0 or n2 < 0:
    print("No se permiten números negativos.")
else:
    resultado = combinatoria.número_combinatorio(n1,n2)
    print(f"{n1} sobre {n2} es {resultado}")