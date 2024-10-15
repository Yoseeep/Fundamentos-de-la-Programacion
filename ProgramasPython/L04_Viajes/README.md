# Proyecto L04_Viajes

### Condiciones Iniciales:
Se facilita una carpeta **data** con el fichero denominado ``viajes.csv`` con datos sobre la contratación de viajes turisticos por Europa. Ábralo para ver su estructura. 

Cree una carpeta **src** para incluir los siguientes módulos Python:

**viaje.py** en el que implemente las funciones que se indican a continuación.

**test_viaje.py** en el que incluirá las sentencias necesarias para ir probando las funciones a medida que las implemente.

### Ejercicio 1
Defina en viaje.py un tipo **Viaje** con los siguientes campos:
```
'código','fec_sal','fec_reg','ciudades','num_per','ppp','seguro', con el siguiente significado y tipo:

* código: código identificativo del viaje, de tipo str
* fec_sal: fecha de salida del viaje, de tipo date
* fec_reg: fecha de regreso del viaje, de tipo date
* ciudades: relación de la ciudades que se visitan en el viaje, de tipo lista de str
* num_per: número de personas (amigos o familiares) que contratan el viaje, de tipo int
* ppp: precio por persona de tipo float
* seguro: Indica si se ha contratado un seguro de viaje. "true" significa que si y "false" que no, de tipo bool
```
### Ejercicio 2
Defina una función ``lee_viajes`` que reciba como parámetro el nombre de un fichero con la estructura de ``viajes.csv`` y devuelva una lista de tuplas de tipo **Viaje** con los registros leídos del fichero

Resultados esperados en el test:
```
test_lee_viajes
El número de viajes leídos es: 260
Los dos primeros: [viaje(código='JKA-2532', fec_sal=datetime.date(2025, 7, 15), fec_reg=datetime.date(2025, 7, 27), ciudades=['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'], num_per=2, ppp=855.74, seguro=False), viaje(código='STV-2203', fec_sal=datetime.date(2025, 1, 23), fec_reg=datetime.date(2025, 2, 4), ciudades=['Londres', 'Ánsterdam', 'Berlín'], num_per=2, ppp=469.05, seguro=True)]
los dos últimos: [viaje(código='NPQ-5725', fec_sal=datetime.date(2024, 2, 2), fec_reg=datetime.date(2024, 2, 17), ciudades=['Atenas', 'Viena', 'Budapest'], num_per=4, ppp=442.42, seguro=False), viaje(código='TVW-6579', fec_sal=datetime.date(2025, 10, 7), fec_reg=datetime.date(2025, 10, 16), ciudades=['Viena', 'Florencia'], num_per=4, ppp=377.86, seguro=True)]
```

### Ejercicio 3
Defina una función ``ciudades_distintas`` que reciba como parámetros una lista de tuplas de tipo Viaje y que devuelva una lista ordenada alfabéticamente con las distintas ciudades que se pueden visitar.

Resultados esperados en el test:
```
test_ciudades_distintas
Las distintas ciudades a las que se puede viajar son: ['Atenas', 'Berlín', 'Budapest', 'Dublin', 'Estambul', 'Florencia', 'Lisboa', 'Londres', 'Oporto', 'París', 'Praga', 'Roma', 'Viena', 'Ánsterdam']
```
### Ejercicio 4
Defina una función ``viajes_visitan_en_fecha`` que reciba como parámetros una lista de tuplas de tipo Viaje, una ciudad y una fecha, que puede tomar el valor **None**,  y que devuelva una lista con los registros que estarán de viaje en la fecha dada, incluyendo también visitar la ciudad que se indique. Si la fecha toma el valor **None** no se tendrán en cuenta la fecha.

Resultados esperados en el test:
```
test_viajes_visitan_en_fecha
Los viajes que visitan Berlín son:
viaje(código='STV-2203', fec_sal=datetime.date(2025, 1, 23), fec_reg=datetime.date(2025, 2, 4), ciudades=['Londres', 'Ánsterdam', 'Berlín'], num_per=2, ppp=469.05, seguro=True)
viaje(código='LMN-7281', fec_sal=datetime.date(2024, 2, 12), fec_reg=datetime.date(2024, 2, 20), ciudades=['Ánsterdam', 'Berlín', 'Atenas', 'Viena', 'Roma'], num_per=6, ppp=816.33, seguro=True)
viaje(código='WXY-4874', fec_sal=datetime.date(2025, 2, 22), fec_reg=datetime.date(2025, 3, 9), ciudades=['Ánsterdam', 'Lisboa', 'Berlín', 'Viena', 'Budapest'], num_per=6, ppp=846.72, seguro=False)
viaje(código='HJK-9699', fec_sal=datetime.date(2025, 5, 27), fec_reg=datetime.date(2025, 6, 3), ciudades=['Londres', 'Ánsterdam', 'Berlín'], num_per=2, ppp=529.33, seguro=False)
viaje(código='RST-7543', fec_sal=datetime.date(2025, 3, 25), fec_reg=datetime.date(2025, 4, 2), ciudades=['Roma', 'Estambul', 'Berlín', 'Budapest'], num_per=6, ppp=729.07, seguro=True)
viaje(código='PQR-3170', fec_sal=datetime.date(2025, 6, 8), fec_reg=datetime.date(2025, 6, 21), ciudades=['Florencia', 'Berlín', 'Oporto', 'Budapest'], num_per=6, ppp=749.27, seguro=True)
viaje(código='PQR-9419', fec_sal=datetime.date(2025, 6, 9), fec_reg=datetime.date(2025, 6, 23), ciudades=['Roma', 'Atenas', 'Berlín'], num_per=5, ppp=437.15, seguro=False)

Los viajes que estan de viaje el 2024-08-15 y visitan Roma son:
viaje(código='MNP-3246', fec_sal=datetime.date(2024, 8, 4), fec_reg=datetime.date(2024, 8, 17), ciudades=['Londres', 'Ánsterdam', 'Roma'], num_per=2, ppp=475.87, seguro=True)
viaje(código='KLM-1469', fec_sal=datetime.date(2024, 8, 11), fec_reg=datetime.date(2024, 8, 25), ciudades=['Londres', 'Ánsterdam', 'Roma'], num_per=3, ppp=456.63, seguro=True)
viaje(código='PQR-0333', fec_sal=datetime.date(2024, 8, 14), fec_reg=datetime.date(2024, 8, 22), ciudades=['Roma', 'Atenas', 'Florencia'], num_per=6, ppp=509.52, seguro=False)
```
### Ejercicio 5
Defina una función ``viajes_que_visitan_más_ciudades`` que reciba como parámetros una lista de tuplas de tipo Viaje  y que devuelva una lista de tuplas con el código, las fechas de salida y de regreso (ambas con formato str: dia/mes/año) y la lista de las ciudades visitadas.

**Ayuda:** busque el número de ciudades de uno de los viajes que visite el máximo número de ciudades. Después filtre los viajes con el mismo número de ciudades

Resultados esperados en el test:
```
test_viajes_que_visitan_más_ciudades
('JKA-2532', '15/07/2025', '27/07/2025', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('WXY-2562', '11/10/2025', '25/10/2025', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('HJK-3023', '10/06/2025', '22/06/2025', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('LMN-7281', '12/02/2024', '20/02/2024', ['Ánsterdam', 'Berlín', 'Atenas', 'Viena', 'Roma'])
('GHJ-9634', '07/03/2025', '21/03/2025', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('WXY-4874', '22/02/2025', '09/03/2025', ['Ánsterdam', 'Lisboa', 'Berlín', 'Viena', 'Budapest'])
('WXY-5364', '27/06/2024', '05/07/2024', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('DFG-0353', '20/03/2024', '04/04/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('TVW-9693', '02/04/2024', '13/04/2024', ['Budapest', 'Londres', 'París', 'Florencia', 'Roma'])
('JKL-4246', '04/09/2024', '18/09/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('GHJ-7211', '01/02/2024', '12/02/2024', ['Budapest', 'Londres', 'París', 'Roma', 'Estambul'])
('KLM-4041', '23/01/2024', '05/02/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('FGH-6421', '18/09/2024', '01/10/2024', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('JKL-1959', '27/06/2024', '09/07/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('GHJ-6225', '26/05/2025', '06/06/2025', ['Budapest', 'Londres', 'París', 'Florencia', 'Roma'])
('JKL-9768', '07/10/2024', '20/10/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('NPQ-6531', '28/05/2025', '07/06/2025', ['Budapest', 'Londres', 'París', 'Roma', 'Estambul'])
('CDF-6414', '27/07/2025', '10/08/2025', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('STV-8214', '25/05/2024', '06/06/2024', ['Budapest', 'Londres', 'París', 'Florencia', 'Roma'])
('PQR-4406', '25/08/2025', '05/09/2025', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Roma'])
('XYZ-4497', '24/01/2025', '31/01/2025', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('WXY-2139', '06/05/2024', '18/05/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('CDF-9316', '23/07/2025', '06/08/2025', ['Budapest', 'Londres', 'París', 'Florencia', 'Roma'])
('TVW-7363', '27/06/2024', '12/07/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('XYZ-5469', '25/04/2025', '04/05/2025', ['Budapest', 'Praga', 'París', 'Florencia', 'Estambul'])
('TVW-4256', '27/03/2025', '05/04/2025', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('NPQ-0832', '25/06/2025', '09/07/2025', ['Budapest', 'Londres', 'Praga', 'Florencia', 'Estambul'])
('CDF-3780', '21/06/2025', '04/07/2025', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('NPQ-1308', '05/03/2025', '19/03/2025', ['Budapest', 'Londres', 'París', 'Florencia', 'Roma'])
('DFG-0615', '24/05/2024', '08/06/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('WAY-7979', '11/05/2025', '23/05/2025', ['Praga', 'Londres', 'París', 'Florencia', 'Estambul'])
('HJK-6937', '28/05/2024', '08/06/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('FGH-7740', '21/06/2024', '30/06/2024', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('GHJ-1298', '15/01/2024', '30/01/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('KST-5664', '10/03/2025', '21/03/2025', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('NPQ-2588', '21/03/2025', '31/03/2025', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Praga'])
('FGH-9197', '22/06/2025', '07/07/2025', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('MNP-1763', '15/05/2024', '28/05/2024', ['Praga', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('LMN-2285', '18/01/2024', '31/01/2024', ['Budapest', 'Londres', 'París', 'Praga', 'Estambul'])
('HJK-5007', '28/01/2025', '10/02/2025', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('GHJ-6795', '04/03/2024', '11/03/2024', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('HJK-9535', '05/02/2025', '15/02/2025', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
('FGH-3425', '13/03/2024', '21/03/2024', ['Budapest', 'Londres', 'París', 'Florencia', 'Estambul'])
('FGH-5429', '13/07/2024', '27/07/2024', ['Ánsterdam', 'Lisboa', 'Atenas', 'Viena', 'Budapest'])
```
### Ejercicio 6
Defina una función ``n_viajes_más_importe`` que reciba como parámetros una lista de tuplas de tipo Viaje, un año de salida y un entero "n" con valor por defecto 4. La función debe devolver una lista de tuplas con el código del viaje, los días de duración del viaje, el número de viajeros, el precio por persona y el importe total. La lista deberá estar ordenada de mayor a menor importe total.


Resultados esperados en el test
```
test_n_viajes_más_importe
Los cuatro viajes de más importe del 2025 son:
('FGH-9197', 15, 8, 841.7, 6733.6)
('NPQ-2588', 10, 8, 817.26, 6538.08)
('CDF-6414', 14, 8, 807.99, 6463.92)
('GHJ-6225', 11, 7, 892.15, 6245.05)

Los 5 viajes de más importe del 2024 son:
('FGH-3425', 8, 8, 854.57, 6836.56)
('JKL-1959', 12, 8, 830.79, 6646.32)
('WXY-2139', 12, 8, 817.91, 6543.28)
('GHJ-7211', 11, 7, 908.82, 6361.740000000001)
('FGH-7740', 9, 7, 906.02, 6342.139999999999)
```
