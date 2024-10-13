# Proyecto L03_Películas

### Condiciones Iniciales:
Se facilita una carpeta **data** con el fichero denominado ``películas.csv``. Ábralo para ver su estructura. 

Cree una carpeta **src** para incluir los siguientes módulos Python:

**película.py** en el que implemente las funciones que se indican a continuación.

**test_película.py** en el que incluirá las sentencias necesarias para ir probando las funciones a medida que las implemente.

### Ejercicio 1
Defina en película.py un tipo **Película** con los siguientes campos:
``` 
fecha de estreno: fecha en la que se estrenó la película, de tipo date.
título: título de la película, de tipo str.
director: nombre del director, de tipo str.
géneros: géneros de la película, separados por hashtag o almohadilla (#), de tipo List de str.
duración: duración de la película (horas y minutos), de tipo time.
presupuesto: presupuesto de producción de la película en millones de dólares, de tipo float.
recaudación: recaudación de la película en millones de dólares, de tipo float.
reparto: actores principales de la película, separados por guiones (-), de tipo List de str.
```

La siguiente línea del fichero: 

``````
19-12-2019;Star Wars: The Rise of Skywalker;J.J. Abrams;Acción#Aventura#Fantasía;2:22;275.7;1074,7;Daisy Ridley-Adam Driver-John Boyega
``````

Indica que "Star Wars: The Rise of Skywalker" fue estrenada el 19 de diciembre de 2019, estuvo dirigida por J.J. Abrams, sus géneros son "Acción", "Aventura" y "Fantasía", dura 2 hora y 22 minutos, su presupuesto fue de 275.7 millones de dólares, recaudó 1074.7 millones de dólares y su reparto lo encabezan "Daisy Ridley", "Adam Driver" y "John Boyega".



### Ejercicio 2
Defina una función ``lee_películas`` que reciba como parámetro el nombre de un fichero (incluida la ruta), con la estructura de ``películas.csv`` y devuelva una lista de tuplas de tipo **Película** con los registros leídos del fichero

Resultados esperados en el test:
```test_lee_películas
Número de películas leídas: 36
Las dos primeras: [Peli(fecha_estreno=datetime.date(2019, 12, 19), título='Star Wars: The Rise of Skywalker', director='J.J. Abrams', género=['Acción', 'Aventura', 'Fantasía'], duración=datetime.time(2, 22), presupuesto=275.7, recaudación=1074.7, reparto=['Daisy Ridley', 'Adam Driver', 'John Boyega']), Peli(fecha_estreno=datetime.date(2019, 5, 23), título='Joker', director='Todd Phillips', género=['Drama'], duración=datetime.time(2, 2), presupuesto=55.5, recaudación=980.4, reparto=['Joaquin Phoenix', 'Robert De Niro', 'Zazie Beetz'])]
Las dos últimas: [Peli(fecha_estreno=datetime.date(2008, 11, 28), título='Twilight', director='Catherine Hardwicke', género=['Drama', 'Fantasía', 'Romance'], duración=datetime.time(2, 2), presupuesto=37.3, recaudación=392.7, reparto=['Kristen Stewart', 'Robert Pattinson', 'Billy Burke']), Peli(fecha_estreno=datetime.date(2002, 5, 2), título='Spider-Man', director='Sam Raimi', género=['Acción', 'Aventura', 'Fantasía'], duración=datetime.time(2, 1), presupuesto=139.7, recaudación=820.9, reparto=['Tobey Maguire', 'Willem Dafoe', 
'Kirsten Dunst'])]
```
### Ejercicio 3
Defina una función ``película_más_cara`` que reciba como parámetros una lista de tuplas de tipo Película y devuelva el registro con la película que tuvo mayor presupuesto.

Resultados esperados en el test:
```
test_película_más_cara
La película más cara es: Peli(fecha_estreno=datetime.date(2011, 5, 27), título='Pirates of the Caribbean: On Stranger Tides', director='Rob Marshall', género=['Aventura', 'Fantasía'], duración=datetime.time(2, 16), presupuesto=410.4, recaudación=1045.2, reparto=['Johnny Depp', 'Penélope Cruz', 'Ian McShane'])
```
### Ejercicio 4
Defina una función ``película_menos_beneficio`` que reciba como parámetros una lista de tuplas de tipo Película y devuelva una tupla con el título y el beneficio de la película con menos beneficio. **Se advierte** que el beneficio es recaudación-presupuesto.

Resultados esperados en el test
```
test_película_menos_beneficio
La película con menos beneficio es: ('The Prestige', 69.19999999999999)
```
### Ejercicio 5
Defina una función ``n_peliculas_más_largas`` que reciba como parámetros una lista de tuplas de tipo Película y un número "n" entero, y devuelva una lista con las "n" películas más largas.

Resultados esperados en el test
```
Las 5 películas de más duración son (una debajo de otra):

--> Peli(fecha_estreno=datetime.date(2003, 12, 25), título='The Lord of the Rings: The Return of the King', director='Peter Jackson', género=['Aventura', 'Fantasía'], duración=datetime.time(3, 21), presupuesto=94.0, recaudación=1142.2, reparto=['Elijah Wood', 'Ian McKellen', 'Viggo Mortensen'])

--> Peli(fecha_estreno=datetime.date(2019, 5, 2), título='Avengers: Endgame', director='Anthony Russo', género=['Acción', 'Aventura', 'Fantasía'], duración=datetime.time(3, 1), presupuesto=356.2, recaudación=2798.0, reparto=['Robert Downey Jr.', 'Chris Evans', 'Mark Ruffalo'])

--> Peli(fecha_estreno=datetime.date(2013, 12, 20), título='The Wolf of Wall Street', director='Martin Scorsese', género=['Drama', 'Comedia'], duración=datetime.time(3, 0), presupuesto=100.0, recaudación=392.2, reparto=['Leonardo DiCaprio', 'Jonah Hill', 'Margot Robbie'])

--> Peli(fecha_estreno=datetime.date(2014, 11, 7), título='Interstellar', director='Christopher Nolan', género=['Drama', 'Aventura', 'Cienciaficción'], duración=datetime.time(2, 49), presupuesto=165.3, recaudación=677.0, reparto=['Matthew McConaughey', 'Anne Hathaway', 'Jessica Chastain'])

--> Peli(fecha_estreno=datetime.date(2012, 7, 8), título='The Dark Knight Rises', director='Christopher Nolan', género=['Acción', 'Aventura', 'Crimen'], duración=datetime.time(2, 44), presupuesto=250.3, recaudación=1081.5, reparto=['Christian Bale', 'Tom Hardy', 'Anne Hathaway'])
```