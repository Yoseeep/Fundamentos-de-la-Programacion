# Proyecto T10_TMDB_Movie

### Condiciones Iniciales:
Este es el **exámen** que se dispuso para la **tercera convocatoria** del curso 23/24.

TMDB (The Movie Database) es una base de datos en línea que contiene una amplia colección de información sobre películas, series de televisión y programas de streaming. En este proyecto se trabajará con dos ficheros distintos relacionados con películas registradas en la base de datos TMDB:  

Se facilita una carpeta **data** con la estructura y datos que se indican. Ábralos para ver sus estructuras. 

**•	“movies_fp.csv”**: Cada línea contiene información sobre una película separada por comas.  Esta información  incluye los siguinetes campos: el identificador único de la película (string), el título de la película (string), el idioma original en el que fue producida (string), la fecha de lanzamiento (date), la calificación promedio otorgada por los usuarios (float), la popularidad en la base de datos (int) y una indicación de si sólo es apta  para adultos (boolean).

```
Contenido de las tres primeras líneas del archivo “movies_fp.csv”:
 
id,title,original_language,release_date,vote_average,popularity,adult 
238,The Godfather,en,1972-03-14,8.7,114574,False
278,The Shawshank Redemption,en,1994-09-23,8.7,91998,False
```
**•	“movies_fp_genres.csv”**: Cada línea del fichero contiene un identificador (string) de película y un género al que pertenece la película con ese identificador. Si la película tiene más de un género, su código aparecerá varias veces en el fichero. Por ejemplo, en el siguiente extracto del archivo la película con id 238 tiene los géneros “Drama” y “Fake”. Tambien se observa que una película puede tener más de un género en la misma línea como ocurre con la película 278, en la cuarta línea, que pertenece a los géneros "Drama, Fantasy, Science Fiction".

```
Contenido de las cuatro primeras líneas del archivo movies_fp_genres.csv 

id:genres
238: Drama
238: Fake
278: Drama, Fantasy, Science Fiction
```

Cree una carpeta **src** para incluir los siguientes módulos Python:

**movie.py** en el que implemente las funciones de los ejercicios que se indican a continuación.

**movie_test.py** en el que incluirá las sentencias necesarias para ir probando las funciones a medida que las implemente. No es necesario hacer test de las funciones auxiliares. Si completa y funcionan los test recibirá **1 PUNTO**

### NamedTuple
En el proyecto debe usar el siguiente tipo:
```
Película = NamedTuple('Pelicula', [('id',str),('title',str),('original_language',str),('release_date',date),('vote_average',float),('popularity',int),('adult',bool), ('genres',Set[str])])
```
con el siguiente significado:
```
•	id: Una cadena con el identificador único de la película. 
•	title: Una cadena con el título de la película. 
•	original_language: Una cadena con el idioma original en el que fue producida la película. 
•	reléase _date: Un objeto de tipo fecha con la fecha de lanzamiento de la película. 
•	vote_average: Un valor real con la calificación promedio otorgada por los usuarios. 
•	popularity: Un valor entero con la popularidad de la película en la base de datos. 
•	adult: Un valor booleano que indica si la película es apta para adultos (True o False). 
•	genres: Un conjunto (set) que contiene los géneros a los que pertenece la película
```
### Ejercicio 1 (2 PUNTOS)
Defina una función ``leer_películas`` que reciba como parámetros los dos nombres de ficheros (nombre_archivo_películas, nombre_archivo_genéros) y devuelva una listao de tuplas de tipo Pelicula **fusionando** la información de ambos ficheros.

Recuerde que los nombres de los géneros deben guardarse como conjuntos (sets) para evitar duplicados y que están en un fichero auxiliar (de ahí que la función de lectura de películas reciba dos ficheros).  

Para que el código sea más modular empiece en ``leer_películas`` creando un diccionario mediate la implementación una función auxiliar ``leer_diccionario_géneros`` que reciba el nombre un fichero, con una estructura similar a la del fichero de géneros, y devuelva un diccionario en el que la clave es el id de una película y el valor es un conjunto con los géneros de esa película. Así para el fichero proporcionando, el diccionario resultado de la función debería tener entre sus entradas: **{238:{“Drama”, “Fake”}, …}**.

Después, a medida que vayas leyendo la información de cada película del fichero principal, puedes acceder al diccionario para completar con la información auxiliar de dicha película. 

Resultados esperados en el test:
```
test_leer_películas
Número de registros leídos:  203
PRIMERA PELÍCULA: Pelicula(id='238', title='The Godfather', original_language='en', release_date=datetime.date(1972, 3, 14), vote_average=8.7, popularity=114574, adult=False, genres={'Drama', 'Fake'})
ÚLTIMA PELÍCULA: Pelicula(id='347688', title='Scooby-Doo! and KISS: Rock and Roll Mystery', original_language='en', release_date=datetime.date(2015, 7, 10), vote_average=8.1, popularity=16717, adult=False, genres={'Drama', 'Adventure'})
```

### Ejercicio 2 (1.5 PUNTOS)
Defina una función ``género_más_frecuente`` que reciba una lista de tuplas de tipo Pelicula y devuleva una tupla con el nombre del género más frecuente y su frecuencia.

Resultados esperados en el test:
```
test_género_más_frecuente
('Adventure', 174)
```
### Ejercicio 3 (2 PUNTOS)
Defina una función ``mejor_valorada_por_idioma``  que reciba una lista de tuplas de tipo Pelicula y devuelva un diccionario en el que las claves sean los idiomas y el valor asociado a cada clave sea la película mejor valorada en el idioma al que hace referencia la clave. 
La película mejor valorada en un idioma se define como aquella que tenga la mayor popularidad, y en caso de empate en popularidad, se seleccionará la película con la mejor calificación promedio otorgada por los usuarios. 

Resultados esperados en el test
```
test_mejor_valorada_por_idioma
Mejor en español es ['es']: Pelicula(id='1010581', title='My Fault', original_language='es', release_date=datetime.date(2023, 6, 8), vote_average=8.3, popularity=1034594, adult=False, genres={'Adventure', 'Science Fiction', 'Action'}
```
### Ejercicio 4 (1.5 PUNTOS)
Defina una función ``media_calificaciones`` que reciba lista de tuplas de tipo Pelicula y un conjunto de géneros, y devuelva la media de las calificaciones promedio (vote_average) de las películas que contengan **todos** los géneros dados como parámetro.  

Resultados esperados en el test
```
test_media_calificaciones
{'Fake', 'Adventure', 'Action'} --> 0.0
{'Thriller', 'Action'} --> 8.45
```
### Ejercicio 5 (2 PUNTOS)
Defina una función ``top_n_por_género``  que reciba una lista de tuplas de tipo Pelicula  y un valor entero n, y devuelva un diccionario en el que las claves sean los géneros y el valor asociado a cada clave sea una lista con las "n" películas de género de que se trate con mayor calificación promedio (vote_average). 
```
test_top_n_por_género
top: 2
Drama --> [Pelicula(id='238', title='The Godfather', original_language='en', release_date=datetime.date(1972, 3, 14), vote_average=8.7, popularity=114574, adult=False, genres={'Drama', 'Fake'}), Pelicula(id='278', title='The Shawshank Redemption', original_language='en', release_date=datetime.date(1994, 9, 23), vote_average=8.7, popularity=91998, adult=False, genres={'Fantasy', 'Drama', 'Science Fiction'})]
Fake --> [Pelicula(id='238', title='The Godfather', original_language='en', release_date=datetime.date(1972, 3, 14), 
vote_average=8.7, popularity=114574, adult=False, genres={'Drama', 'Fake'})]
Fantasy --> [Pelicula(id='278', title='The Shawshank Redemption', original_language='en', release_date=datetime.date(1994, 9, 23), vote_average=8.7, popularity=91998, adult=False, genres={'Fantasy', 'Drama', 'Science Fiction'}), Pelicula(id='19404', title='Dilwale Dulhania Le Jayenge', original_language='hi', release_date=datetime.date(1995, 10, 20), vote_average=8.6, popularity=28989, adult=False, genres={'Fantasy', 'Family', 'Adventure'})]
Science Fiction --> [Pelicula(id='278', title='The Shawshank Redemption', original_language='en', release_date=datetime.date(1994, 9, 23), vote_average=8.7, popularity=91998, adult=False, genres={'Fantasy', 'Drama', 'Science Fiction'}), Pelicula(id='820067', title='The Quintessential Quintuplets Movie', original_language='ja', release_date=datetime.date(2022, 5, 20), vote_average=8.4, popularity=95199, adult=False, genres={'Action', 'Adventure', 'Science Fiction'})]
Family --> [Pelicula(id='19404', title='Dilwale Dulhania Le Jayenge', original_language='hi', release_date=datetime.date(1995, 10, 20), vote_average=8.6, popularity=28989, adult=False, genres={'Fantasy', 'Family', 'Adventure'}), Pelicula(id='389', title='12 Angry Men', original_language='en', release_date=datetime.date(1957, 4, 10), vote_average=8.5, popularity=37808, adult=False, genres={'Family', 'Adventure'})]
Adventure --> [Pelicula(id='19404', title='Dilwale Dulhania Le Jayenge', original_language='hi', release_date=datetime.date(1995, 10, 20), vote_average=8.6, popularity=28989, adult=False, genres={'Fantasy', 'Family', 'Adventure'}), Pelicula(id='389', title='12 Angry Men', original_language='en', release_date=datetime.date(1957, 4, 10), vote_average=8.5, popularity=37808, adult=False, genres={'Family', 'Adventure'})]
Action --> [Pelicula(id='129', title='Spirited Away', original_language='ja', release_date=datetime.date(2001, 7, 20), vote_average=8.5, popularity=72546, adult=False, genres={'Action', 'Adventure'}), Pelicula(id='155', title='The Dark Knight', original_language='en', release_date=datetime.date(2008, 7, 14), vote_average=8.5, popularity=92305, adult=False, genres={'Action', 'Thriller', 'Adventure'})]
Documentary --> [Pelicula(id='372058', title='Your Name.', original_language='ja', release_date=datetime.date(2016, 8, 26), vote_average=8.5, popularity=83288, adult=False, genres={'Documentary'}), Pelicula(id='761053', title="Gabriel's Inferno: Part III", original_language='en', release_date=datetime.date(2020, 11, 19), vote_average=8.4, popularity=38293, adult=False, genres={'Documentary'})]
Crime --> [Pelicula(id='497', title='The Green Mile', original_language='en', release_date=datetime.date(1999, 12, 10), vote_average=8.5, popularity=66633, adult=False, genres={'Crime', 'Thriller', 'Drama'}), Pelicula(id='680', title='Pulp Fiction', original_language='en', release_date=datetime.date(1994, 9, 10), vote_average=8.5, popularity=64962, 
adult=False, genres={'Crime', 'Drama'})]
Thriller --> [Pelicula(id='497', title='The Green Mile', original_language='en', release_date=datetime.date(1999, 12, 10), vote_average=8.5, popularity=66633, adult=False, genres={'Crime', 'Thriller', 'Drama'}), Pelicula(id='155', title='The Dark Knight', original_language='en', release_date=datetime.date(2008, 7, 14), vote_average=8.5, popularity=92305, adult=False, genres={'Action', 'Thriller', 'Adventure'})]
Horror --> [Pelicula(id='429', title='The Good, the Bad and the Ugly', original_language='it', release_date=datetime.date(1966, 12, 23), vote_average=8.5, popularity=55566, adult=False, genres={'Horror', 'Thriller'})]
Comedy --> [Pelicula(id='704264', title='Primal: Tales of Savagery', original_language='en', release_date=datetime.date(2019, 11, 21), vote_average=8.5, popularity=16109, adult=False, genres={'Comedy'}), Pelicula(id='769', title='GoodFellas', original_language='en', release_date=datetime.date(1990, 9, 12), vote_average=8.5, popularity=45908, adult=False, genres={'Comedy', 'Romance'})]
Romance --> [Pelicula(id='769', title='GoodFellas', original_language='en', release_date=datetime.date(1990, 9, 12), 
vote_average=8.5, popularity=45908, adult=False, genres={'Comedy', 'Romance'}), Pelicula(id='11216', title='Cinema Paradiso', original_language='it', release_date=datetime.date(1988, 11, 17), vote_average=8.5, popularity=31799, adult=False, genres={'Romance', 'Drama'})]
Animation --> [Pelicula(id='696374', title="Gabriel's Inferno", original_language='en', release_date=datetime.date(2020, 5, 29), vote_average=8.5, popularity=20108, adult=False, genres={'Adventure', 'Animation', 'Comedy'}), Pelicula(id='637', title='Life Is Beautiful', original_language='it', release_date=datetime.date(1997, 12, 20), vote_average=8.5, popularity=37286, adult=False, genres={'Family', 'Animation'})]
History --> [Pelicula(id='311', title='Once Upon a Time in America', original_language='en', release_date=datetime.date(1984, 5, 23), vote_average=8.4, popularity=33891, adult=False, genres={'Adventure', 'Drama', 'History'})]
```
