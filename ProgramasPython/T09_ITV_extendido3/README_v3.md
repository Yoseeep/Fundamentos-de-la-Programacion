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
### Ejercicio 5
Defina una función ``importes_comunes`` que reciba como parámetros una lista de tuplas de tipo ITV y dos estaciónes, y devuelva un conjunto con los importes que son comunes a las dos estaciones.

Resultados esperados en el test
```
test_importes_comunes
Los importes comunes a Sevilla-Gelves y a Sevilla-El Pino son: {104.9, 25.7, 35.2, 69.2}
Los importes comunes a La Rinconada y a Utrera son: {66.2}
```
### Ejercicio 6
Defina una función ``diferencia_entre_importes`` que reciba como parámetros una lista de tuplas de tipo ITV y calcule la diferencia de precios entre dos precios consecutivos, por ello, la función debe devolver una lista de tuplas con un precio, el inmeditamente inferior a él y la diferencia entre ellos.

Resultados esperados en el test. (Ojo vea que los resultados están acompañados de un contador)
```
test_diferencia_entre_importes
1. la diferencia entre 21.1 y 20.0 es 1.1000000000000014
2. la diferencia entre 21.9 y 21.1 es 0.7999999999999972
3. la diferencia entre 22.4 y 21.9 es 0.5
4. la diferencia entre 22.5 y 22.4 es 0.10000000000000142
5. la diferencia entre 24.3 y 22.5 es 1.8000000000000007
...
...
...
206. la diferencia entre 182.7 y 182.3 es 0.39999999999997726
207. la diferencia entre 185.2 y 182.7 es 2.5
208. la diferencia entre 186.8 y 185.2 es 1.6000000000000227
209. la diferencia entre 193.0 y 186.8 es 6.199999999999989
210. la diferencia entre 193.4 y 193.0 es 0.4000000000000057
211. la diferencia entre 198.7 y 193.4 es 5.299999999999983
```

### ------------->DICCIONARIOS

### Ejercicio 7 
Defina una función ``número_inspecciones_por_estación`` que reciba como parámetros una lista de tuplas de tipo ITV y devuelva un diccionario que cada estación le haga corresponder el número de inspecciones realizadas en la estación de que se trate.

**Nota**: La función devuelve Dict[str,int]

Resultados esperados en el test.
```
test_número_inspecciones_por_estación
El número de inspecciones es: {'Sevilla-El Pino': 36, 'Sevilla-Gelves': 46, 'La Rinconada': 41, 'Alcalá de Guadaíra': 36, 'Utrera': 16, 'Sanlucar la Mayor': 56, 'Cazalla de la Sierra': 18, 'Lebrija': 7, 'Carmona': 14}
```

### Ejercicio 8 
Defina una función ``recaudación_por_año`` que reciba como parámetros una lista de tuplas de tipo ITV y un resultado de inspección ("F" favorable y "D" desfavorable) y devuelva un diccionario que cada año le haga corresponder la suma de los importes recaudados, según el resultado dado como parametro del año de que se trate.

**Nota**: La función devuelve Dict[int,float]

Resultados esperados en el test.
```
test_recaudación_por_año
Los importes por año con inspección F son: {2022: 2864.300000000001, 2021: 2033.1000000000001, 2020: 2069.2999999999997, 2023: 2252.2, 2019: 2388.5, 2024: 2238.1000000000004}
Los importes por año con inspección D son: {2022: 399.0, 2017: 49.8, 2020: 534.4, 2023: 1061.3999999999999, 2024: 521.8000000000001, 2021: 934.4, 2019: 1193.3000000000002}
```

### Ejercicio 9 
Defina una función ``matrículas_por_tipo`` que reciba como parámetros una lista de tuplas de tipo ITV y dos fechas que por defecto pueden valor **None**, y devuelva un diccionario que cada tipo de vehículo le haga corresponder la lista de las matrículas de los vehículos inspeccionados entre las fechas dadas (ambas incluidas). No se contemplará filtro para la fecha que tome el valor por defecto.

**Nota1**: La función devuelve Dict[str,List[str]]

**Nota2**: En el test visualice el resultado recorriendo con un ``for clave, valor in ...`` el diccionario aplicándole el método items(). Así cada pareja clave valor se visulizará en una línea

Resultados esperados en el test.
```
test_matrículas_por_tipo
Las matrículas por tipos desde el 2024-01-01 son:
Turismo gasolina --> ['3251PQR', '1303PQR', '4473WXY', '2779KLM', '4631HJK', '5999TVW', '5732BYZ']
Turismo eléctrico --> ['1696MNP', '4388KLM', '0232FGH', '1433BCD', '4528STV', '3713QRS', '2554QRS', '0116JKL']
Camión --> ['6578WXY', '2678PQR', '4279GHJ', '7353FGH', '8056HJK', '8721NPQ', '7590LMN', '0024DFG']
Turismo diésel --> ['9974MNP', '8839LLZ', '1504RST', '6114CYZ', '3931WXY', '8763KLM', '2153FGH', '8355KLM', '4122RST', '1099NPQ']
Moto --> ['7183BCD', '7683HJK', '4311QRS', '8547HJK', '1164CDF', '8797JKL', '5402XYZ']

Las matrículas por tipos hasta el 2019-10-31 son
Turismo diésel --> ['4545FNY', '5636BCD', '5917KLM', '9769NPQ', '2849JKL', '5161PQR', '9963VWX', '1401KLM', '5252BYZ', '6307BCD', '4530QRS', 
'2218WXY', '6104WXY', '9465BCD', '4505QRS', '0171LMN']
Turismo eléctrico --> ['5474LMN', '8806XYZ', '6191MNP', '4804VWX', '2084TVW']
Turismo gasolina --> ['6181BCD', '4524DFG', '9452FGH', '7206JKL', '5872PQR', '5187MNP', '2451NPQ', '2582XYZ', '5114VWX', '0119FDZ', '1711QRS', '1533BCD', '9533BCD', '3943NPQ', '3085GHJ']
Camión --> ['8110WXY', '2791DFG', '3259XYZ', '3268PQR', '9926TVW', '5882JKL', '3206FGH', '6696MNP', '4046DFG', '0104KYZ']
Moto --> ['0475PQR', '1727BCD', '0241JKL']

Las matrículas por tipos entre el 2023-06-01 y el 2023-09-30 son
Camión --> ['5906LMN', '8115XYZ', '2067PQR', '1611XYZ']
Turismo gasolina --> ['6999DFG', '1497BCD', '6932TVW', '2231STV', '6903QRS', '6693BYZ', '7869TVW', '8556STV', '7199QRS', '8930VWX']
Turismo diésel --> ['6387QRS', '3609XYZ', '1697PQR', '5383HJK', '6369WXY', '1630LMN']
Moto --> ['4494VWX']
Turismo eléctrico --> ['8545BBZ', '5179WXY', '5601BCD']
```

### Ejercicio 10 
Defina una función ``promedio_de_facturación_por_estación`` que reciba como parámetros una lista de tuplas de tipo ITV y dos fechas que por defecto pueden valor **None**, y devuelva un diccionario que cada estación le haga corresponder el promedio facturado entre las fechas dadas (ambas incluidas). No se contemplará filtro para la fecha que tome el valor por defecto.

Resultados esperados en el test.
```
promedio_de_facturación_por_estación
La facturación desde el 2024-01-01 es: {'Sevilla-El Pino': 42.083333333333336, 'Sevilla-Gelves': 83.58571428571429, 'Lebrija': 56.6, 'Sanlucar la Mayor': 83.17142857142858, 'La Rinconada': 62.38333333333333, 'Utrera': 68.53333333333335, 'Alcalá de Guadaíra': 89.39999999999999, 'Cazalla de la Sierra': 42.6, 'Carmona': 39.4}

Las facturación hasta hasta el 2019-10-31 es: {'Sevilla-El Pino': 58.40322580645163, 'Sevilla-Gelves': 80.31750000000001, 'La Rinconada': 63.29142857142858, 'Alcalá de Guadaíra': 79.40344827586203, 'Utrera': 74.4909090909091, 'Sanlucar la Mayor': 56.6276595744681, 'Cazalla de la Sierra': 65.36666666666666, 'Lebrija': 106.7, 'Carmona': 56.23333333333333}

Las facturación entre el 2023-06-01 y el 2023-09-30 es: {'Sevilla-Gelves': 86.85000000000001, 'Sanlucar la Mayor': 54.5, 'Alcalá de Guadaíra': 74.99999999999999, 'La Rinconada': 48.05, 'Sevilla-El Pino': 43.95, 'Cazalla de la Sierra': 48.8, 'Carmona': 53.85, 'Lebrija': 114.7, 'Utrera': 181.8}
```

### Ejercicio 11 
Defina una función ``n_matrículas_más_importe_por_tipo`` que reciba como parámetros una lista de tuplas de tipo ITV y un entero n con valor por defecto 3, y devuelva un diccionario que cada tipo de vehículo le haga corresponder una lista con las n tuplas con las matrículas y los importes que más han pagado.

Resultados esperados en el test.
```
n_matrículas_más_importe_por_tipo
Las matrículas tres matrículas son:
Turismo gasolina --> [('4473WXY', 75.0), ('6724JPP', 74.9), ('5999TVW', 74.7)]
Turismo diésel --> [('9443LLZ', 74.4), ('7832NPQ', 74.4), ('5383HJK', 74.2)]
Turismo eléctrico --> [('5601BCD', 44.9), ('4812HJK', 44.8), ('3539DFG', 44.8)]
Moto --> [('9654MNP', 44.6), ('0600LBZ', 44.4), ('2052PQR', 44.0)]
Camión --> [('7492HJK', 198.7), ('5933QRS', 193.4), ('9926TVW', 193.0)]

Las matrículas cuatro matrículas son:
Turismo gasolina --> [('4473WXY', 75.0), ('6724JPP', 74.9), ('5999TVW', 74.7), ('5114VWX', 72.6)]
Turismo diésel --> [('9443LLZ', 74.4), ('7832NPQ', 74.4), ('5383HJK', 74.2), ('3097STV', 73.9)]
Turismo eléctrico --> [('5601BCD', 44.9), ('4812HJK', 44.8), ('3539DFG', 44.8), ('9871STV', 44.7)]
Moto --> [('9654MNP', 44.6), ('0600LBZ', 44.4), ('2052PQR', 44.0), ('5810NPQ', 43.5)]
Camión --> [('7492HJK', 198.7), ('5933QRS', 193.4), ('9926TVW', 193.0), ('3268PQR', 186.8)]
```

### Ejercicio 12 
Defina una función ``tipo_más_facturación_favorable`` que reciba como parámetros una lista de tuplas de tipo ITV y devuelva una tupla con tipo y el promedio de facturación de dicho tipo entre todas las inspecciones favorables realizadas.

Resultados esperados en el test.
```
test_tipo_más_facturación_favorable
El tipo que más facturación con inspecciones favorables es: ('Camión', 145.34358974358975)
```
