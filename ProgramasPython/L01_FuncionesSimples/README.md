# Ejercicios Simple de Introducción a Python

Cree una carpeta **src** y dentro de la misma:

Un módulo Python **ejercicios.py** en el que implemente las funciones que se indican a continuación.

Un módulo Python **test_ejercicios.py** en el que incluirá las sentencias necesarias para ir probando las funciones a medida que las implemente.
### Ejercicio 1
Defina una función ``calcula_imc`` que reciba como entrada el peso y la estatura de una persona (en kilogramos y metros, respectivamente), por lo tanto de tipo ``float``, y calcule y devuelva su índice de masa corporal o [IMC](https://es.wikipedia.org/wiki/%C3%8Dndice_de_masa_corporal) que también será de tipo ``float``.

Para probar la función, utilice su peso y estatura y visualice algo como:

```python
Para un peso de 82 y estatura de 1.89 el IMC es: 22.955684331345708
```


### Ejercicio 2
Defina una función ``calcula_estado_nutricional`` que reciba como parámetros el ``peso`` y la ``estatura`` de una persona (en kilogramos y metros, respectivamente), por lo tanto de tipo ``float``, y devuelva una cadena de texto con el estado nutricional de la persona de acuerdo con su IMC y la siguiente tabla:
```python
- imc < 18.5---> Bajo Peso
- 18.5 <= imc < 25---> Normal
- 25 <= imc < 30---> Sobrepeso
- imc >= 30--->Obesidad
```

**NOTA**: Tenga en cuenta que ya dispone de la función ``calcula_imc`` implementada anteriormente deberá importarla desde el módulo ``ejercicio1``.

Pruebe la función con los mismos valores del ejercicio 1 y visualice algo como:
```python
Para un peso de 82 y estatura de 1.89 el estado nutricional es:Normal
```

### Ejercicio 3
Defina una función ``trata_estados_nutricionales`` que reciba como parámetro una lista de tuplas, que representan el peso y la altura de una serie de personas,
 y devuelva una lista de tuplas con el ``imc`` y el ``estado nutricional`` correspondiente :


**NOTA.**defina un tipo ``Datos_nutricionales`` con dos campos ``peso`` y ``estatura`` ambos de tipo ``float``:

Para probar la función, utilice en el test la lista de tuplas que figura a continuación.

```python
datos = [
    Datos_nutricionales(60.0, 1.6),
    Datos_nutricionales(75.4, 1.75),
    Datos_nutricionales(87.9, 1.69),
    Datos_nutricionales(45.1, 1.65)
    ]
```

La salida que visualice el test debe ser algo como:
```python
Para ( 60.0 , 1.6 ) el IMC es 23.437499999999996 y el estado nutricional es: Normal
Para ( 75.4 , 1.75 ) el IMC es 24.620408163265306 y el estado nutricional es: Normal
...
```

### Ejercicio 4
Defina una función ``producto_escalar`` que dado dos vectores (modelados en sendas listas) de números enteros devuelva el producto escalar de ambos que lógicamente será también entero.

**NOTA** Compruebe que ambas vectores son de la misma longitud, sino, la función devolverá **None**

Para probar la función utilice dos juegos de datos a) [2, 3, 1] y [3, 4, 7]  y b) [2, 3] y [3, 4, 7], visualizando:

```python
[2, 3, 1] x [3, 4, 7] es: 25
[2, 3] x [3, 4, 7] es: None
```

### Ejercicio 5
Defina una función ``calcula_promedio_edades_sexo`` que reciba una lista de tuplas de tipo ``Edad``  y un sexo de tipo ``str`` con los valores "H" o "M" y devuelva el promedio de las edades de sexo dado.

Defina el tipo ``Edad`` con dos campos ``edad`` de tipo ``int`` y ``sexo`` de tipo ``str``

**AYUDA** Incluya en una lista auxiliar las edades que pasan el filtro de sexo dado. Después utilice la función ``sum()`` y la función ``len()`` para calcular la ``suma`` y el ``número de elementos`` de dicha lista auxiliar

**NOTA** Si no se puede calcular el promedio (no hay elementos del sexo dado y, por tanto, la lista auxiliar no tendrá elementos), la función devolverá **None**

Para probar la función, utilice la siguiente lista: 
```python
edades=[Edad(23,'M'),
	Edad(30,'M'),
	Edad(56,'H'),
	Edad(18,'H'),
	Edad(34,'M'),
	Edad(7,'M'),
	Edad(95,'H'),
	Edad(37,'M'),
	Edad(36,'H')]
```
En el test debe hacer tres pruebas proporcionado en el segundo parámetro los valores 'M', 'H' y  'J' y debe visualizar, según el caso:
```python
El promedio del sexo M es: 26.2
El promedio del sexo H es: 51.25
El promedio del sexo J es: None
