# Proyecto T09_ITV 

### Condiciones Iniciales:
Se facilita una carpeta **data** con el fichero denominado ``inspecciones.csv`` con datos sobre Inspecciones Técnicas de Vehículos, conocidas coloquialmente como ITVs. Ábralo para ver su estructura. 

Cree una carpeta **src** para incluir los siguientes módulos Python:

**itv.py** en el que implemente las funciones que se indican a continuación.

**test_itv.py** en el que incluirá las sentencias necesarias para ir probando las funciones a medida que las implemente.

### Ejercicio 1
Defina en itv.py un tipo **ITV** con los siguientes campos:
```
'f_insp', 'estación', 'número', 'f_límite', 'matrícula', 'tipo', 'f_matr', 'resultado' y 'importe'
con el siguiente significado y tipo:

* f_insp: fecha en que se realizo la inspección de tipo date
* estación: estación en la que se realizó la inspección de tipo str
* número: número de vez en que se realiza la inspección de tipo int
* f_límite: fecha límite para realizar la inspección de tipo date
* matrícula: matrícula del vehículo inspeccionado de tipo str
* tipo: tipo de vehículo inspeccionado de tipo str
* f_matr: fecha de matriculación del vehículo de tipo date
* resultado: resultado de la inspección. "S" ha sido favorable y "N" desfavorable, de tipo bool
* importe: importe pagado por realizar la inspección de tipo float
```
### Ejercicio 2
Defina una función ``lee_inspecciones`` que reciba como parámetro el nombre de un fichero con la estructura de ``inspecciones.csv`` y devuelva una lista de tuplas de tipo **ITV** con los registros leídos del fichero

Resultados esperados en el test:
```
Número de registros leídos: 270
Los dos primeros [itv(f_insp=datetime.date(2022, 6, 7), estación='Sevilla-El Pino', número=1, f_límite=datetime.date(2022, 6, 14), matrícula='3595KLW', tipo='Turismo gasolina', f_matr=datetime.date(2018, 6, 14), resultado=True, importe=48.0), itv(f_insp=datetime.date(2022, 2, 23), estación='Sevilla-Gelves', número=1, f_límite=datetime.date(2022, 2, 28), matrícula='6577KJP', tipo='Turismo gasolina', f_matr=datetime.date(2018, 2, 28), resultado=False, importe=64.0)]        
Los dos útimos [itv(f_insp=datetime.date(2020, 2, 20), estación='Sevilla-Gelves', número=1, f_límite=datetime.date(2020, 4, 20), matrícula='1691GHJ', tipo='Camión', f_matr=datetime.date(2016, 4, 20), resultado=True, importe=126.3), itv(f_insp=datetime.date(2019, 5, 10), estación='Sanlucar la Mayor', número=1, f_límite=datetime.date(2019, 7, 10), matrícula='0171LMN', tipo='Turismo diésel', f_matr=datetime.date(2015, 7, 10), resultado=True, importe=64.3)]
```

### Ejercicio 3
Defina una función ``inspecciones_entre_fechas`` que reciba como parámetros una lista de tuplas de tipo ITV y dos fechas con valor por defecto **None**, y que devuelva una lista de tuplas con la matrícula, el tipo de vehículo y la fecha de la inspección de los vehículos inspeccionados entre esas fechas (incluidas).
No se tendrá en cuenta la fecha que tome el valor **None**.

Resultados esperados en el test:
```
inspecciones_entre_fechas
Las inspecciones realizadas después de 2024-09-20 son:[('3251PQR', 'Turismo gasolina', datetime.date(2024, 9, 28)), ('4473WXY', 'Turismo gasolina', datetime.date(2024, 10, 23)), ('6114CYZ', 'Turismo diésel', datetime.date(2024, 10, 23)), ('5732BYZ','Turismo gasolina', datetime.date(2024, 10, 15)), ('1099NPQ', 'Turismo diésel', datetime.date(2024, 10, 5))]

Las inspecciones realizadas antes de 2019-01-15 son:[('4545FNY', 'Turismo diésel', datetime.date(2017, 4, 5)), ('2849JKL', 'Turismo diésel', datetime.date(2019, 1, 2)), ('6104WXY', 'Turismo diésel', datetime.date(2019, 1, 1)), ('3943NPQ', 'Turismo gasolina', datetime.date(2019, 1, 2))]

Las inspecciones realizadas entre de 2022-03-25 y 2022-04-20 son:[('7030WXY', 'Turismo diésel', datetime.date(2022, 4, 8)), ('0062XYZ', 'Turismo diésel', datetime.date(2022, 4, 7)), ('9733CDF', 'Turismo gasolina', datetime.date(2022, 4, 8)), ('8849KZK', 'Turismo diésel', datetime.date(2022, 4, 4))]
```
### Ejercicio 4
Defina una función ``promedio_de_días_de_adelanto`` que reciba como parámetros una lista de tuplas de tipo ITV, una estación y "fav" o "des" para indicar si la inspección ha sido o no favorable, y devuelva el promedio de los días que hay entre la fecha de inspección y la fecha límite de las inspecciones de la estación dada, según se pidan de inspecciones favorables o desfavorables. No obstante, este tercer parámetro tendrá como valor por defecto la cadena **"todas"**, en cuyo caso no se filtra por dicho parámetro.
Si no es posible calcular el promedio se debe devolver **0** (un cero).

Resultados esperados en el test
```
test_promedio_de_días_de_adelanto
El promedio de dias de todas la estaciones con resultado fav es: 58.51485148514851
El promedio de dias con resultado des de la estación Sevilla-Gelves es: 49.0
El promedio de dias con resultado fav de la estación Sevilla-Gelves es: 57.926829268292686
El promedio de dias con resultado fav de la estación New York es: 0
```
