# Proyecto T08_VacunasCovid 

### Condiciones Iniciales:
Se facilita una carpeta **data** con el fichero denominado ``vacunas_covid.csv``. Ábralo para ver su estructura. 

Cree una carpeta **src** para incluir los siguientes módulos Python:

**vacunas.py** en el que implemente las funciones que se indican a continuación.

**test_vacunas.py** en el que incluirá las sentencias necesarias para ir probando las funciones a medida que las implemente.

### Ejercicio 1
Defina en vacunas.py un tipo **Vacuna** con los siguientes campos:
```
usuario de tipo str
fecha_ncto de tipo date
provincia de tipo str
marca de tipo str
importe de tipo float
fecha_admón de tipo date
pauta_completa de tipo bool
```
### Ejercicio 2
Defina una función ``lee_vacunas`` que reciba como parámetro el nombre de un fichero con la estructura de ``vacunas_covid.csv`` y devuelva una lista de tuplas de tipo **Vacuna** con los registros leídos del fichero

Resultados esperados en el test:
```test_lee_vacunas
Número de registros leídos: 1000
Los dos primeros: [vacuna(usuario='Tillie', fecha_ncto=datetime.date(1977, 12, 18), provincia='Madrid', marca='JANSSEN', importe=1.22, fecha_admón=datetime.date(2021, 5, 13), pauta_completa=True), vacuna(usuario='Kelsey', 
fecha_ncto=datetime.date(1969, 11, 13), provincia='Almería', marca='ASTRAZENECA', importe=0.97, fecha_admón=datetime.date(2021, 4, 9), pauta_completa=True)]
Los dos últimos: [vacuna(usuario='Constancy', fecha_ncto=datetime.date(1998, 12, 5), provincia='Burgos', marca='PFIZER', importe=1.4, fecha_admón=datetime.date(2021, 9, 21), pauta_completa=True), vacuna(usuario='Kevyn', fecha_ncto=datetime.date(1984, 3, 8), provincia='Ourense', marca='PFIZER', importe=1.4, fecha_admón=datetime.date(2021, 7, 10), pauta_completa=True)
```

### Ejercicio 3
Defina una función ``cuenta_vacunas_de_marca`` que reciba como parámetros una lista de tuplas de tipo Vacuna y el nombre de una marca de tipo str, y devuelva el número de vacunas de dicha marca.

Resultados esperados en el test:
```
test_cuenta_vacuna_de_marca
El número de vacunas de la marca JANSSEN es: 247
El número de vacunas de la marca MODERNA es: 242
```
### Ejercicio 4
Defina una función ``obtener_vacunados_el`` que reciba como parámetros una lista de tuplas de tipo Vacuna y una fecha de tipo date, y devuelva una lista de tuplas con el nombre del usuario y la fecha de nacimiento de los vacunados en la fecha dada como parámetro.

Resultados esperados en el test (**Nota**: para probar cree una fecha de la siguiente manera ```fecha=date(2021,5,8)```
```
test_obtener_vacunados_el
Los vacunados el 2021-05-08 son: [('Galina', datetime.date(1994, 3, 17)), ('Davide', datetime.date(2009, 11, 7))]
```
