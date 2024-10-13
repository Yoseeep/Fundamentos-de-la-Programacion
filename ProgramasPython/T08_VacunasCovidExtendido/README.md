# Proyecto T08_VacunasCovidExtendido 

### Condiciones Iniciales:
Copie y pegue del proyecto ``T08_VacunasCovid`` la carpeta **src**, en la que deberían estar los módulos vacunas.py y test_vacunas.py con todas las funciones pedidas hasta el momento.

En la carpeta **data** se le facilita el fichero ``vacunas_covid_extendido.csv``. Ábralo para ver su estructura (tiene un campo más al final). 

En **vacunas.py** debe realizar los ejercicios que se piden a continuación.

En **test_vacunas.py** se incluirán las sentencias necesarias para ir probando los ejercicios a medida que los vaya resolviendo.
**ADVERTENCIA**: a la hora de facilitar la ruta recuerde que el proyecto y el fichero ya no se llaman como anteriormente


### Ejercicio 1
En **vacunas.py** modifique el tipo ``Vacuna`` (no le cambie de nombre) para añadir al final el campo **reacciones**:
```
usuario de tipo str
fecha_ncto de tipo date
provincia de tipo str
marca de tipo str
importe de tipo float
fecha_admón de tipo date
pauta_completa de tipo bool
reacciones de tipo lista de str
```
### Ejercicio 2
En **vacunas.py** modifique la función ``lee_vacunas`` que reciba como parámetro el nombre de un fichero con la estructura de ``vacunas_covid_extendido.csv`` y devuelva una lista de tuplas de tipo **Vacuna** con los registros leídos del fichero.

Resultados esperados en el test (observe que al final de cada tupla debe haber una lista de recciones):
```
test_lee_vacunas
Número de registros leídos: 1000
Los dos primeros: [vacuna(usuario='Tillie', fecha_ncto=datetime.date(1977, 12, 18), provincia='Madrid', marca='JANSSEN', importe=1.22, fecha_admón=datetime.date(2021, 5, 13), pauta_completa=True, reacciones=['Mareos', 'Fiebre']), vacuna(usuario='Kelsey', fecha_ncto=datetime.date(1969, 11, 13), provincia='Almería', marca='ASTRAZENECA', importe=0.97, 
fecha_admón=datetime.date(2021, 4, 9), pauta_completa=True, reacciones=['Mareos', 'Dolor de cabeza', 'Dolor muscular', 'Fiebre'])]
Los dos últimos: [vacuna(usuario='Constancy', fecha_ncto=datetime.date(1998, 12, 5), provincia='Burgos', marca='PFIZER', importe=1.4, fecha_admón=datetime.date(2021, 9, 21), pauta_completa=True, reacciones=['Fiebre']), vacuna(usuario='Kevyn', fecha_ncto=datetime.date(1984, 3, 8), provincia='Ourense', marca='PFIZER', importe=1.4, fecha_admón=datetime.date(2021, 7, 10), pauta_completa=True, reacciones=['Mareos', 'Dolor muscular'])]
```

### Ejercicio 3
Añada a **vacunas.py** la función ``vacunados_con_al_menos_reacciones_que`` que reciba como parámetros una lista de tuplas de tipo Vacuna y un número entero, y devuelva el número de vacunados que han tenido, al menos, ese número de reacciones.

Resultados esperados en el test:
```
test_vacunados_con_al_menos_reacciones_que
El número de vacunados con 2 reacciones o más es: 677
El número de vacunados con 3 reacciones o más es: 339
```
### Ejercicio 4
Añada a **vacunas.py** la función ``vacunados_según_pauta_y_reacción`` que reciba como parámetros una lista de tuplas de tipo Vacuna, un valor "S" o "N" para indicar pauta completa si o no y una reacción de tipo str, y devuelva una lista de tuplas con el nombre del usuario, la marca de la vacuna recibida y la lista de reacciones, que sean de la pauta indicada y que entre su reacciones se encuentra la dada.

Resultados esperados en el test (**se muestran los primeros registros**)
```
test_vacunados_según_pauta_y_reacción

Los vacunados con pauta completa S y reacción Mareos son: [('Tillie', 'JANSSEN', ['Mareos', 'Fiebre']), ('Kelsey', 'ASTRAZENECA', ['Mareos', 'Dolor de cabeza', 'Dolor muscular', 'Fiebre']), ('Milo', 'JANSSEN', ['Mareos', 'Fiebre']), ('Lorry', 'MODERNA', ['Mareos', 'Fiebre']), ('Kerr', 'ASTRAZENECA', ['Mareos', 'Fiebre', 'Dolor muscular']), ('Jeramey', 'JANSSEN', ['Mareos', 'Fiebre', 'Dolor muscular']), ('Galina', 'PFIZER', ['Mareos', 'Fiebre', 'Dolor muscular']), ('Giovanni', 'MODERNA', ['Mareos', 'Dolor de cabeza']), ('Sharai', 'JANSSEN', ['Mareos', 'Fiebre']), ('Claybourne', 'MODERNA', ['Mareos', 'Fiebre', 'Dolor muscular']),...]

Los vacunados con pauta completa N y reacción Dolor de cabeza son: [('Miran', 'ASTRAZENECA', ['Mareos', 'Dolor de cabeza']), ('Padraic', 'ASTRAZENECA', ['Mareos', 'Dolor de cabeza']), ('Yurik', 'MODERNA', ['Mareos', 'Dolor de cabeza']), ('Towney', 'ASTRAZENECA', ['Mareos', 'Dolor de cabeza', 'Dolor muscular', 'Fiebre']), ('Ceciley', 'JANSSEN', ['Mareos', 'Dolor de cabeza', 'Dolor muscular', 'Fiebre']), ('Herrick', 'MODERNA', ['Mareos', 'Dolor de cabeza']), ('Hamel', 'JANSSEN', ['Mareos', 'Dolor de cabeza', 'Dolor muscular', 'Fiebre']), ('Raquel', 'JANSSEN', ['Mareos', 'Dolor de cabeza']),...]
```
### Ejercicio 5
Añada a **vacunas.py** la función ``n_vacunados_mayores`` que reciba como parámetros una lista de tuplas de tipo Vacuna y un valor "n" entero, y devuelva una lista de tuplas con el nombre del usuario y la fecha de nacimiento de los "n" vacunados de más edad.

Resultados esperados en el test
```
test_n_vacunados_mayores
Los 4 vacunados de mayor edad son: [('Nahum', datetime.date(1950, 5, 14)), ('Edithe', datetime.date(1950, 5, 30)), ('Vlad', datetime.date(1950, 7, 7)), ('Myron', datetime.date(1950, 7, 18))]
```

### Ejercicio 6
Añada a **vacunas.py** la función ``el_último_vacunado`` que reciba como parámetros una lista de tuplas de tipo Vacuna y devuelva el registro del vacunado más reciente.

Resultados esperados en el test
```
El último vacunado es: vacuna(usuario='Humbert', fecha_ncto=datetime.date(1991, 4, 11), provincia='Melilla', marca='JANSSEN', importe=1.22, fecha_admón=datetime.date(2021, 12, 31), pauta_completa=False, reacciones=['Mareos', 'Fiebre'])
```