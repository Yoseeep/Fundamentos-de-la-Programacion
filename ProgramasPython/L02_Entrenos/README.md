# Proyecto L02_Entrenos

### Condiciones Iniciales:
Se facilita una carpeta **data** con el fichero denominado ``entrenos.csv``. Ábralo para ver su estructura. 

Cree una carpeta **src** para incluir los siguientes módulos Python:

**entrenos.py** en el que implemente las funciones que se indican a continuación.

**test_entrenos.py** en el que incluirá las sentencias necesarias para ir probando las funciones a medida que las implemente.

### Ejercicio 1
Defina en entrenos.py un tipo **Entreno** con los siguientes campos:
```: 
* tipo: tipo de entrenamiento realizado, de tipo str. 
* fechahora: fecha y hora del entrenamiento, de tipo datetime. 
* ubicación: lugar donde se ha realizado el entrenamiento, de tipo str 
* duración: duración del entrenamiento en minutos, de tipo int. 
* calorías: número de kilocalorías activas quemadas en el entrenamiento, de tipo int. 
* distancia: distancia en kilómetros recorrida en el entrenamiento, de tipo float. 
* frecuencia: frecuencia cardiaca media registrada en el entrenamiento, de tipo int. 
* compartido: indica si el entrenamiento ha sido compartido por el usuario, de tipo bool ("S" si ha sido compartido, "N" si no lo ha sido). 
```

La siguiente línea del fichero: 

```Andar,12/11/2021 8:14,Sevilla,48,155,3.49,89,N```

Indica que el 12 de noviembre de 2021 a las 8:14 horas, el usuario realizó un entrenamiento de tipo ‘Andar’
durante 48 minutos, quemando 155 kilocalorías activas y recorriendo un total de 3.49 kilómetros con una
frecuencia cardiaca media de 89 lpm, y que no compartió este entreno.



### Ejercicio 2
Defina una función ``lee_entrenos`` que reciba como parámetro el nombre de un fichero (incluida la ruta), con la estructura de ``entrenos.csv`` y devuelva una lista de tuplas de tipo **Entreno** con los registros leídos del fichero

Resultados esperados en el test:
```Test_lee_entrenos
Número de registros leídos: 200
Los dos primeros: [entreno(tipo='Elíptica', fechahora=datetime.datetime(2019, 4, 5, 16, 19), ubicación='Sevilla', duración=47, calorías=216, distancia=9.32, frecuencia=90, compartido=True), entreno(tipo='Tenis', fechahora=datetime.datetime(2020, 9, 27, 10, 17), ubicación='Huelva', duración=97, calorías=338, distancia=16.41, frecuencia=115, compartido=True)]
Los dos últimos: [entreno(tipo='Remo', fechahora=datetime.datetime(2019, 11, 23, 10, 25), ubicación='Sevilla', duración=109, calorías=304, distancia=6.39, frecuencia=113, compartido=True), entreno(tipo='Senderismo', fechahora=datetime.datetime(2020, 5, 4, 21, 55), ubicación='Córdoba', duración=126, calorías=338, distancia=15.5, frecuencia=136, compartido=False)]
```
### Ejercicio 3
Defina una función ``filtra_por_tipo`` que reciba como parámetros una lista de tuplas de tipo Entreno y el nombre de un tipo de entrenamiento de tipo str, y devuelva una lista de tuplas con la ubicación y la distancia de los entrenamientos del tipo dado como parámetro.

Resultados esperados en el test:
```
test_filtra_por_tipo
Los entrenamientos con el tipo Remo son: [('Málaga', 18.83), ('Córdoba', 14.64), ('Córdoba', 17.57), ('Córdoba', 2.9), ('Cádiz', 13.01), ('Cádiz', 13.8), ('Málaga', 2.54), ('Málaga', 14.85), ('Cádiz', 14.48), ('Huelva', 17.94), ('Sevilla', 14.48), ('Sevilla', 16.23), ('Córdoba', 4.26), ('Sevilla', 9.18), ('Córdoba', 4.27), ('Málaga', 16.66), ('Cádiz', 15.82), ('Sevilla', 6.39)]
```
### Ejercicio 4
Defina una función ``suma_de_calorias`` que reciba como parámetros una lista de tuplas de tipo Entreno y el nombre de un tipo de entrenamiento de tipo str, y devuelva la suma de las calorias consumidas en los entrenamientos del tipo dado como parámetro

Resultados esperados en el test
```
test_suma_calorías
Los entrenamientos con el tipo Senderismo han consumido: 4563 calorias
Los entrenamientos con el tipo Andar han consumido: 6324 calorias
```