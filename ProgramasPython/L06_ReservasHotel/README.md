# Proyecto L06_ReservasHotel

### Condiciones Iniciales:
Se facilita una carpeta **data** con el fichero denominado ``reservas.csv`` con datos sobre reservas hoteleras. Ábralo para ver su estructura. 

Cree una carpeta **src** para incluir los siguientes módulos Python:

**reservas.py** en el que implemente las funciones que se indican a continuación.

**test_reservas.py** en el que incluirá las sentencias necesarias para ir probando las funciones a medida que las implemente.

### Ejercicio 1
Defina en ``reservas.py`` los tipos **Fechas_Estancia** y  **Reserva** con los siguientes campos:
```
Fechas_Estancia   --> "fecha_entrada", "fecha_salida" con el siguiente significado y tipo:

*fecha_entrada: fecha de entrada en el hotel de tipo date.
*fecha_salidas: fecha de salida del hotel de tipo date. 
                    
Reserva --> 'nombre', 'dni', 'fechas', 'tipo_habitación', 'num_personas', 'precio_noche', 'servicios_adicionales', con el siguiente significado y tipo:

* nombre: nombre a quien está hecha la reserva, de tipo str
* dni: dni a quien está hecha la reserva, de tipo str
* fechas: tupla que contiene la fecha de entrada y de salida, de tipo Fechas_Estancia. Vea los resultados esperados en el test de lectura 
* tipo_habitación: tipo de habitación para la que se ha hecho la reserva, de tipo str
* num_personas: número de personas que se alojarán en la habitación, de tipo int
* precio_noche: precio por el uso de la habitación durante una noche, de tipo float
* servicios_adicionales: lista con servicios adicionales, de tipo lista de str. En caso de que no se hayan contratado servicios adicionales debe devolver una lista vacía (vea el segundo y tercer registro del test lee_reservas).
```
### Ejercicio 2
Defina una función ``lee_reservas`` que reciba como parámetro el nombre de un fichero con la estructura de ``reservas.csv`` y devuelva una lista de tuplas de tipo **Reserva** con los registros leídos del fichero

**Nota**: Si ha observado cada registro del fichero tiene 8 campos, pero el tipo Reserva tiene 7, debido a que tiene que gestionar adecuadamente la lectura de las ``fechas de entrada y salida``. Es decir, debe leerlas como cadenas pero cuando construya la tupla ``Reserva`` deberán ser una sóla tupla de tipo ``Fechas_Estancia`` que agrupe las dos fechas. Vea los resultados esperados en el test de lectura.

Resultados esperados en el test:
```
test_lee_reservas
Total reservas: 496
Las tres primeras:
Reserva(nombre='Ana Fernández', dni='98762912S', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 1, 2), fecha_salida=datetime.date(2022, 1, 6)), tipo_habitacion='Suite', num_personas=4, precio_noche=202.97, servicios_adicionales=['Parking', 'Gimnasio', 'Spa'])
Reserva(nombre='María Fernández', dni='25061289Y', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 1, 1), fecha_salida=datetime.date(2022, 1, 3)), tipo_habitacion='Familiar', num_personas=4, precio_noche=83.77, servicios_adicionales=[])
Reserva(nombre='Laura López', dni='13728274B', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 1, 2), fecha_salida=datetime.date(2022, 1, 10)), tipo_habitacion='Estandar', num_personas=1, precio_noche=87.58, servicios_adicionales=[])
```

### Ejercicio 3
Defina una función ``total_facturado`` que reciba una lista de tuplas de tipo Reserva, una fecha inicial y una fecha final, y devuelve el total facturado entre todas las reservas cuya fecha de entrada esté comprendida entre esas fechas dadas como parámetros.

**Nota 1:** La cantidad facturada correspondiente a una reserva se calcula multiplicando el número de días totales de la reserva por el precio por noche. 

**Nota 2:** Si la fecha inicial es **None** se hace el cálculo sin limitar la fecha mínima de las reservas. Si la fecha final es **None** se hace el cálculo sin limitar la fecha máxima de las reservas.

Resultados esperados en el test:
```
test_total_facturado
Total facturado en todas las fechas:244275.89000000028
Total facturado entre 2022-02-01 y 2022-02-28: 19098.12
Total facturado desde el 2022-02-01: 221532.13000000015
Total facturado hasta el 2022-02-28: 41841.88
```
### Ejercicio 4
Defina una función ``servicios_adicionales`` que reciba como parámetro una lista de tuplas de tipo Reserva  y devuelva una lista ordenada alfabéticamente de los distintos servicios adiccionales

Resultados esperados en el test:
```
test_servicios_adicionales
Los distintos servicios adicionales son: ['Gimnasio', 'Parking', 'Piscina', 'Spa']
```
### Ejercicio 5
Defina una función ``reservas_más_largas`` que reciba una lista de tuplas de tipo Reserva y un entero n, y devuelve las n tuplas (nombre, fecha_entrada) más largas. Es decir, con mayor número de días entre la fecha de entrada y la fecha de salida.

Resultados esperados en el test:
```
test_reservas_más_largas
Con n=3 [('Laura López', datetime.date(2022, 1, 2), 8, 87.58), ('Sofía García', datetime.date(2022, 1, 4), 7, 171.58), ('Miguel Sánchez', datetime.date(2022, 1, 2), 6, 247.23)]
```
### Ejercicio 6
Defina una función ``dni_por_tipo`` que reciba como parámetro una lista de tuplas de tipo Reserva, un servicio adicional  y devuelva un diccionario con los dni's que se han alojado en cada tipo de habitación y que la reserva incluya el servicio dado.

Resultados esperados en el test:
```
Los distintos dni's con servicio adicional de Piscina, por tipo de habitación son:
Suite --> {'48337470A', '76665848V', '65680492J', '71494621H', '52230529J', '04324992A', '88692655D', '72264876A', '13728274B', '51199390X', '89565833S', '52801249B', '36283527S', '63910637P', '26889506E', '96641529Z', '98513684S', '27595453F'}
Familiar --> {'65213761K', '04324992A', '52103097R', '04847825T', '02325669R', '23053985G', '76188479J', '98831781E', '36283527S', '26889506E', '03143754E', '12527462Y', '60489278Z'}
Deluxe --> {'48337470A', '93407846Q', '04812247A', '03360550C', '73575244S', '43257294K', '22080652P', '04264926J', '94336582N', 
'65680492J', '33150540L', '67017895N', '81378994A', '96641529Z', '63550791C', '60489278Z', '52230529J', '34452687K', '10208905X', '38645040A'}
Doble --> {'98762912S', '71970039A', '08437903P', '13359010N', '04812247A', '20210823X', '15361035W', '63550791C', '36283527S', '93141626K', '25061289Y', '12527462Y', '81312679C', '27595453F', '22080652P'}
Estandar --> {'07424130Y', '13359010N', '35963657Y', '70563584K', '04264926J', '43257294K', '22881672F'}
```
### Ejercicio 7
Defina una función ``cliente_mayor_facturacion`` que reciba una lista de tuplas de tipo Eeserva y un conjunto de servicios que pode tomar el valor **None**, y devuelve una tupla(dni, total_facturado) con el dni del cliente al que se le ha facturado más, junto con el total facturado, teniendo en cuenta sólo aquellas reservas en las que se haya contratado **alguno** de los servicios adicionales indicados.
Si el conjunto de servicios toma el valor None  se procesarán todas las reservas.


Resultados esperados en el test
```
test_cliente_mayor_facturacion
Sin filtrar servicios: ('63550791C', 3893.2200000000003)
Con Parking: ('71828448T', 3008.17)
Con Parking o Spa: ('38747931S', 3216.0699999999997)
```
