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
Defina una función ``total_facturado`` que reciba una lista de tuplas de tipo Reserva, una fecha inicial y una fecha final que pueden tomar el valor **None**, y devuelve el total facturado entre todas las reservas cuya fecha de entrada esté comprendida entre esas fechas dadas como parámetros.

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
test_reservas_mas_largas
Con n=3 [('Laura López', datetime.date(2022, 1, 2)), ('Sofía García', datetime.date(2022, 1, 4)), ('Miguel Sánchez', datetime.date(2022, 1, 2))]
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
Defina una función ``cliente_mayor_facturacion`` que reciba una lista de tuplas de tipo Reserva y un conjunto de servicios que puede tomar el valor por defecto **None**, y devuelve una tupla(dni, total_facturado) con el dni del cliente al que se le ha facturado más, junto con el total facturado, teniendo en cuenta sólo aquellas reservas en las que se haya contratado **alguno** de los servicios adicionales indicados.
Si el conjunto de servicios toma el valor None  se procesarán todas las reservas.


Resultados esperados en el test
```
test_cliente_mayor_facturacion
Sin filtrar servicios: ('63550791C', 3893.2200000000003)
Con Parking: ('71828448T', 3008.17)
Con Parking o Spa: ('38747931S', 3216.0699999999997)
```

### Ejercicios para resolver con dos diccionarios
### Ejercicio 8
Defina una función ``promedios_dias_estancias_por_tipo`` que reciba una lista de tuplas de tipo Reserva y devuelva un diccionario con el promedio de días en que se reserva cada tipo de habitación.

Resultados esperados en el test
```
{'Suite': 2.9320388349514563, 'Familiar': 3.019607843137255, 'Estandar': 2.875, 'Doble': 2.966292134831461, 'Deluxe': 3.1403508771929824}
```
## Ejercicio 9
Defina una función ``reserva_más_barata_por_número_personas`` que reciba una lista de tuplas de tipo Reserva y un conjunto de servicios adicionales, que por defecto puede valer **None**, y que devuelva un diccionario con la reserva más barata para cada número de personas en las que se han contratado alguno de los servicios dados como parámetro, salvo que se omitan, en cuyo caso se considerarán todas las reservas. 

Resultados esperados en el test
```
treserva_más_barata_por_número_personas
Para cualquier servicio
4 --> Reserva(nombre='María Martínez', dni='72264876A', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 10, 30), fecha_salida=datetime.date(2022, 11, 4)), tipo_habitacion='Deluxe', num_personas=4, precio_noche=82.51, servicios_adicionales=['Gimnasio', 'Spa'])
1 --> Reserva(nombre='Sofía Martín', dni='93141626K', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 1, 20), fecha_salida=datetime.date(2022, 1, 21)), tipo_habitacion='Suite', num_personas=1, precio_noche=80.42, servicios_adicionales=[])
2 --> Reserva(nombre='Laura Martínez', dni='48337470A', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 3, 4), fecha_salida=datetime.date(2022, 3, 5)), tipo_habitacion='Deluxe', num_personas=2, precio_noche=80.86, servicios_adicionales=[])
3 --> Reserva(nombre='Sofía Sánchez', dni='25460473W', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 3, 15), fecha_salida=datetime.date(2022, 3, 20)), tipo_habitacion='Familiar', num_personas=3, precio_noche=80.03, servicios_adicionales=['Parking', 'Gimnasio', 'Spa'])      

Para los servicios {'Gimnasio', 'Spa'}
4 --> Reserva(nombre='María Martínez', dni='72264876A', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 10, 30), fecha_salida=datetime.date(2022, 11, 4)), tipo_habitacion='Deluxe', num_personas=4, precio_noche=82.51, servicios_adicionales=['Gimnasio', 'Spa'])
1 --> Reserva(nombre='Laura Sánchez', dni='03143754E', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 1, 13), fecha_salida=datetime.date(2022, 1, 16)), tipo_habitacion='Deluxe', num_personas=1, precio_noche=81.77, servicios_adicionales=['Gimnasio', 'Spa', 'Parking'])        
2 --> Reserva(nombre='Sofía Fernández', dni='76665848V', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 11, 26), fecha_salida=datetime.date(2022, 11, 29)), tipo_habitacion='Suite', num_personas=2, precio_noche=81.54, servicios_adicionales=['Parking', 'Spa'])
3 --> Reserva(nombre='Sofía Sánchez', dni='25460473W', fechas=Fechas_Estancias(fecha_entrada=datetime.date(2022, 3, 15), fecha_salida=datetime.date(2022, 3, 20)), tipo_habitacion='Familiar', num_personas=3, precio_noche=80.03, servicios_adicionales=['Parking', 'Gimnasio', 'Spa'])
```
### Ejercicio 10
Defina una función ``reserva_más_cara_por_mes`` que reciba una lista de tuplas de tipo Reserva y devuelva un diccionario que a cada **número del mes de la fecha de entrada** le haga corresponder una tupla con el nombre y apellidos, DNI y el total facturado del cliente que ha pagado por una reserva en el mes de que se trate.

Resultados esperados en el test
```
test_reserva_más_cara_por_mes
1 --> ('03360550C', 'Miguel Sánchez', 1483.3799999999999)
2 --> ('71193098W', 'Miguel Fernández', 1248.8500000000001)
3 --> ('02325669R', 'Luis Gómez', 1154.4499999999998)
4 --> ('27595453F', 'Sofía López', 1181.05)
5 --> ('63910637P', 'Ana García', 1199.6)
6 --> ('38747931S', 'Javier Fernández', 1216.7)
7 --> ('63550791C', 'Javier Martínez', 1140.9)
8 --> ('71828448T', 'Carmen García', 1212.1)
9 --> ('94336582N', 'Miguel González', 944.95)
10 --> ('26889506E', 'Luis López', 1241.1)
11 --> ('10208905X', 'Miguel García', 1055.6)
12 --> ('20210823X', 'María González', 1238.0500000000002)
```

## Ejercicio 11
Defina una función ``clientes_por_servicio`` que reciba una lista de tuplas de tipo Reserva, un número entero "n" y un tipo de habitación que puede tomar por defecto el valor **None** y devuelva un diccionario que a cada servicio adiccional le haga corresponder una lista de tuplas con el dni, el nombre del cliente y el precio noche de las reservas, **ordenada por el precio**, de los "n" clientes que han contratado el servicio adiccional de que se trate y se han alojado en el tipo de habitación dado, salvo que tome el valor por defecto, en cuyo caso se tendrá en cuenta todo tipo de habitación. 

Resultados esperados en el test
```
test_clientes_por_servicio
Para n=3, todos los tipos
Parking --> [('25460473W', 'Sofía Sánchez', 80.03), ('76665848V', 'Sofía Fernández', 81.54), ('03143754E', 'Laura Sánchez', 81.77)]
Gimnasio --> [('25460473W', 'Sofía Sánchez', 80.03), ('03143754E', 'Laura Sánchez', 81.77), ('72264876A', 'María Martínez', 82.51)]
Spa --> [('25460473W', 'Sofía Sánchez', 80.03), ('76665848V', 'Sofía Fernández', 81.54), ('03143754E', 'Laura Sánchez', 81.77)]
Piscina --> [('26889506E', 'Luis López', 82.94), ('22080652P', 'Luis Martínez', 91.35), ('02325669R', 'Luis Gómez', 94.95)]

Para n=4, y tipo Doble
Parking --> [('22080652P', 'Luis Martínez', 91.35), ('93141626K', 'Sofía Martín', 101.53), ('27595453F', 'Sofía López', 107.59), ('62911458H', 'María Pérez', 113.66)]
Spa --> [('22080652P', 'Luis Martínez', 91.35), ('00967117J', 'Juan López', 96.16), ('23053985G', 'María López', 112.95), ('62911458H', 'María Pérez', 113.66)]
Gimnasio --> [('22080652P', 'Luis Martínez', 91.35), ('00967117J', 'Juan López', 96.16), ('15542641T', 'Miguel Martínez', 102.1), ('27595453F', 'Sofía López', 107.59)]
Piscina --> [('22080652P', 'Luis Martínez', 91.35), ('93141626K', 'Sofía Martín', 101.53), ('27595453F', 'Sofía López', 107.59), ('04812247A', 'Carmen Sánchez', 117.65)]
```
