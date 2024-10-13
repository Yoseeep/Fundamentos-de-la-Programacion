# Proyecto L02_EntrenosExtendido

### Condiciones Iniciales:
Para la realización de este proyecto copie y pegue la carpeta **src del proyecto L02_Entrenos** en este proyecto. De esta forma tendrá todas las funcionalidades que ya haya programado.

En la carpeta **data** de este proyecto se facilita el fichero ``entrenos_extendido.csv``.
Si lo abre podrá observar la siguiente diferencias respecto al fichero entrenos.csv que ya ha trabajado:
```
Se han añadido dos campos nuevos a los registros: peso_antes y peso_después, ambos de tipo float.
Todos los campos reales (float) ya no tienen separada la parte entera de la decimal por un punto, sino por una coma.
Los campos del fichero ya no están separados por coma, sino por punto y coma.
La ruta del fichero ha cambiado, ya que el nombre del proyecto y la denominación del fichero han cambiado.
```
Deberá modificar los módulos: **entrenos.py** y **test_entrenos.py** para resolver los siguientes ejercicios

### Ejercicio 1
Modifique la definición del tipo **Entreno** para que incopore los dos nuevos campos, pero no le cambie de nombre al tipo.



### Ejercicio 2
Modifique la función ``lee_entrenos`` para acomodarla a los cambios descritos en el apartado **Condiciones iniciales**

Resultados esperados en el test:
```Test_lee_entrenos
Número de registros leídos: 200
Los dos primeros: [entreno(tipo='Elíptica', fechahora=datetime.datetime(2019, 4, 5, 16, 19), ubicación='Sevilla', duración=47, calorías=216, distancia=9.32, frecuencia=90, compartido=True, peso_antes=66.73, peso_después=61.93), entreno(tipo='Tenis', fechahora=datetime.datetime(2020, 9, 27, 10, 17), ubicación='Huelva', duración=97, calorías=338, distancia=16.41, frecuencia=115, compartido=True, peso_antes=62.4, peso_después=58.8)]
Los dos últimos: [entreno(tipo='Remo', fechahora=datetime.datetime(2019, 11, 23, 10, 25), ubicación='Sevilla', duración=109, calorías=304, distancia=6.39, frecuencia=113, compartido=True, peso_antes=60.7, peso_después=59.0), entreno(tipo='Senderismo', fechahora=datetime.datetime(2020, 5, 4, 21, 55), ubicación='Córdoba', duración=126, calorías=338, distancia=15.5, frecuencia=136, compartido=False, peso_antes=76.7, peso_después=75.7)]
```
### Ejercicio 3
Añada una función ``promedio_perdida_peso`` que reciba como parámetros una lista de tuplas de tipo Entreno y una ubicación de tipo str, y devuelva el promedio de los pesos que se han perdido en cada entrenamiento.

Resultados esperados en el test:
```
test_promedio_perdida_peso
El promedio de perdidad de peso en: Sevilla es: 2.7780487804878047
El promedio de perdidad de peso en: Huelva es: 2.939999999999999
```
### Ejercicio 4
Defina una función ``cuenta_distintos_tipos`` que reciba como parámetros una lista de tuplas de tipo Entreno y devuelva el número de los distintos tipos de entrenamiento que hay en el fichero.

Resultados esperados en el test
```
test_cuenta_distintos_tipos
El número de distinto tipos de entrenamiento es: 11
```

### Ejercicio 5
Defina una función ``obtiene_horas_más_perdida_peso`` que reciba como parámetros una lista de tuplas de tipo Entreno y un valor de tipo float que represente el peso mínimo que deben perderse en cada entrenamiento, y devuelva una lista con las horas en que comenzaron los entrenamientos en que se perdieron mas kilos de los indicados en el parámetro.

Resultados esperados en el test
```
test_obtiene_horas_más_perdida_peso
Las horas con más perdida de peso que 4.9 kg. son: [datetime.time(11, 13), datetime.time(9, 51), datetime.time(21, 53), datetime.time(6, 58), datetime.time(7, 32), datetime.time(19, 14), datetime.time(14, 38), datetime.time(0, 42), datetime.time(11, 32), datetime.time(16, 40)]
```