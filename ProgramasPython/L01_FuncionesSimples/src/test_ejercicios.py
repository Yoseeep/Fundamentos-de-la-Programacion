from ejercicios import *

datos = [
    Datos_nutricionales(60.0, 1.6),
    Datos_nutricionales(75.4, 1.75),
    Datos_nutricionales(87.9, 1.69),
    Datos_nutricionales(45.1, 1.65)
    ]

edades=[Edad(23,'M'),
	Edad(30,'M'),
	Edad(56,'H'),
	Edad(18,'H'),
	Edad(34,'M'),
	Edad(7,'M'),
	Edad(95,'H'),
	Edad(37,'M'),
	Edad(36,'H')]

def test_calcula_imc()->None:
  print("\n"+"calcula_imc")
  peso = 73
  altura = 1.7
  print(f"Para un peso de {peso} y estatura de {altura} el IMC es: {calcula_imc(peso,altura)}")
  
def test_calcula_estado_nutricional() -> None:
  print("\n"+"calcula_estado_nutricional")
  peso = 73
  altura = 1.7
  print(f"Para un peso de {peso} y estatura de {altura} el estado nutricional es: {calcula_estado_nutricional(peso,altura)}")

def test_trata_estados_nutricionales(datos:List[Datos_nutricionales]) -> None:
  print("\n"+"trata_estados_nutricionales")
  personas_tratadas = trata_estados_nutricionales(datos)
  for dato,persona_tratada in zip(datos,personas_tratadas):
    print(f"Para ({dato.peso},{dato.altura}) el IMC es {persona_tratada[0]} y el estado nutricional es: {persona_tratada[1]}")

def test_producto_escalar()->None:
  print("\n"+"producto_escalar")
  v1 = [2, 3, 1]
  v2 = [3, 4, 7]
  prod_esc = producto_escalar(v1,v2)
  print(v1,"x",v2,"es:",prod_esc)
  v1 = [2, 3]
  v2 = [3, 4, 7]
  prod_esc = producto_escalar(v1,v2)
  print(v1,"x",v2,"es:",prod_esc)

def test_calcula_promedio_edades_sexo(datos:List[Edad])->None:
  print("\n"+"calcula_promedio_edades_sexo")
  sexo = 'M'
  promedio = calcula_promedio_edades_sexo(datos,sexo)
  print(f"El promedio del sexo {sexo} es: {promedio}")
  sexo = 'H'
  promedio = calcula_promedio_edades_sexo(datos,sexo)
  print(f"El promedio del sexo {sexo} es: {promedio}")
  sexo = 'J'
  promedio = calcula_promedio_edades_sexo(datos,sexo)
  print(f"El promedio del sexo {sexo} es: {promedio}")





#El programa empieza a ejecutarse por aquí
if __name__=='__main__':
  test_calcula_imc()
  test_calcula_estado_nutricional()
  test_trata_estados_nutricionales(datos)
  test_producto_escalar()
  test_calcula_promedio_edades_sexo(edades)
