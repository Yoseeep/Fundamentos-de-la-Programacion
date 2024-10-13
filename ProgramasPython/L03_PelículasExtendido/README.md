# Proyecto L03_PelículasExtendido 

### Condiciones Iniciales:
Copie y pegue del proyecto ``L03_Películas`` la carpeta **src**, en la que deberían estar los módulos película.py y test_película.py con todas las funciones pedidas hasta el momento.

En la carpeta **data** se le facilita el fichero ``películas_extendido.csv``. Ábralo para ver su estructura (tiene un campo más al final). 

En **películas.py** debe realizar los ejercicios que se piden a continuación.

En **test_películas.py** se incluirán las sentencias necesarias para ir probando los ejercicios a medida que los vaya resolviendo.
**ADVERTENCIA**: a la hora de facilitar la ruta recuerde que el proyecto y el fichero ya no se llaman como anteriormente


### Ejercicio 1
En **películas.py** modifique el tipo ``Película`` (no le cambie de nombre) para añadir al final el campo **premiada** (observe que en el fichero tiene los valores **true** o **false** en minúsculas. Los tipos **bool en Python** son **True** y **False** (en mayúsculas):
```
fecha de estreno: fecha en la que se estrenó la película, de tipo date.
título: título de la película, de tipo str.
director: nombre del director, de tipo str.
géneros: géneros de la película, separados por hashtag o almohadilla (#), de tipo List de str.
duración: duración de la película (horas y minutos), de tipo time.
presupuesto: presupuesto de producción de la película en millones de dólares, de tipo float.
recaudación: recaudación de la película en millones de dólares, de tipo float.
reparto: actores principales de la película, separados por guiones (-), de tipo List de str.
premiada: si la película ha recibido algún tipo de premio, de tipo bool.
```
### Ejercicio 2
En **película.py** modifique la función ``lee_películas`` que reciba como parámetro el nombre de un fichero con la estructura de ``películas_extendido.csv`` y devuelva una lista de tuplas de tipo **Película** con los registros leídos del fichero.

Resultados esperados en el test (observe que al final de cada tupla debe haber **True** o **False** con mayúsculas):
```
test_lee_películas
Número de películas leídas: 36
Las dos primeras: [Peli(fecha_estreno=datetime.date(2019, 12, 19), título='Star Wars: The Rise of Skywalker', director='J.J. Abrams', género=['Acción', 'Aventura', 'Fantasía'], duración=datetime.time(2, 22), presupuesto=275.7, recaudación=1074.7, reparto=['Daisy Ridley', 'Adam Driver', 'John Boyega'], premiada=False), Peli(fecha_estreno=datetime.date(2019, 5, 23), título='Joker', director='Todd Phillips', género=['Drama'], duración=datetime.time(2, 2), presupuesto=55.5, recaudación=980.4, reparto=['Joaquin Phoenix', 'Robert De Niro', 'Zazie Beetz'], premiada=True)]
Las dos últimas: [Peli(fecha_estreno=datetime.date(2008, 11, 28), título='Twilight', director='Catherine Hardwicke', género=['Drama', 'Fantasía', 'Romance'], duración=datetime.time(2, 2), presupuesto=37.3, recaudación=392.7, reparto=['Kristen Stewart', 'Robert Pattinson', 'Billy Burke'], premiada=False), Peli(fecha_estreno=datetime.date(2002, 5, 2), título='Spider-Man', director='Sam Raimi', género=['Acción', 'Aventura', 'Fantasía'], duración=datetime.time(2, 1), presupuesto=139.7, recaudación=820.9, reparto=['Tobey Maguire', 'Willem Dafoe', 'Kirsten Dunst'], premiada=True)]
```

### Ejercicio 3
Añada a **películas.py** la función ``última_película_premiada`` que reciba como parámetros una lista de tuplas de tipo Película y devuelva una tupla con el título, la fecha de estreno, la duración y los géneros de la película premiada que se estrenó más recientemente.

Resultados esperados en el test:
```
test_última_película_premiada
La última película con premio es: ('Frozen II', datetime.date(2019, 11, 22), datetime.time(1, 43), ['Animación', 'Aventura', 'Comedia'])
```
### Ejercicio 4
Añada a **películas.py** la función ``n_premiadas_mayor_recaudación`` que reciba como parámetros una lista de tuplas de tipo Vacuna, un valor "SI" o "NO" de tipo str y un valor "n" de tipo entero, y devuelva una lista de tuplas con el título, la recaudación, y el director de las "n" películas con mayor recaudación según hayan sido premiadas o no.

Resultados esperados en el test
```
test_n_premiadas_mayor_recaudación
Las 3 películas con premio SI son: [('Avengers: Endgame', 2798.0, 'Anthony Russo'), ('Avatar', 2789.1, 'James Cameron'), ('Furious 7', 1516.4, 'James Wan')]
Las 2 películas con premio NO son: [('Star Wars: The Rise of Skywalker', 1074.7, 'J.J. Abrams'), ('Doctor Strange', 678.0, 'Scott Derrickson')]
```
