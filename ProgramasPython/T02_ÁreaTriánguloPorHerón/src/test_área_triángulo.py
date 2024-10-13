import área_triángulo
print("Cálculo del Área de un triángulo conociendo sus tres lados.")
a = float(input("Teclea el primer lado: "))
b = float(input("Teclea el segundo lado: "))
c = float(input("Teclea el tercer lado: "))

area = área_triángulo.areaTriangulo(a,b,c)
if area == None:
    print("Ese triángulo no existe.")
else:
    print(f"El área es {área_triángulo.areaTriangulo(a,b,c)}")