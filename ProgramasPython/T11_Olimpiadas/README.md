# Proyecto T11_Olimpiadas

### Condiciones Iniciales:
Este es uno de los **exámenes** que se dispuso para la **primera convocatoria** del curso 23/24.

Se dipone de información de participaciones de diversos países por deportes y géneros en determinados juegos olímpicos (JJOO).

Se facilita una carpeta **data** con el fichero ```olimpiadas.csv```. Ábralo para ver su estructura. 

Contenido de las siete primeras líneas del archivo:
```
Ciudad Olímpica,Fecha de Inauguración,País,Deporte,Número de Participantes,Género,Oro,Plata,Bronce,Sede
Seúl,1988-09-17,ALEMANIA,tiro,12,MUJER,0-2-1,NO
Barcelona,1992-07-25,HOLANDA,hípica,6,MUJER,3-0-1,NO
Tokio,1964-10-10,HOLANDA,boxeo,13,HOMBRE,3-0-0,NO
Los Ángeles,1984-07-28,HOLANDA,esgrima,9,MUJER,2-1-3,NO
Sydney,2000-09-15,FRANCIA,atletismo,4,MUJER,0-0-0,NO
Rio de Janeiro,2016-08-05,HOLANDA,tiro,16,HOMBRE,0-2-3,SI
```
La segunda línea de los datos mostrada arriba indica que en los JJOO celebrados en Seúl, ALEMANIA participó en el deporte tiro con 12 participantes del género mujer. La primera competición **para este deporte** fue el 24 de septiembre de 1988. ALEMANIA no obtuvo ninguna medallas de oro, dos medallas de plata y una de bronce en este deporte para el género dado. Finalmente, esta competición no se celebró en la sede olímpica.

Cree una carpeta **src** para incluir los siguientes módulos Python:

**olimpiadas.py** en el que implemente las funciones de los ejercicios que se indican a continuación.

**olimpiadas_test.py** en el que incluirá las sentencias necesarias para ir probando las funciones a medida que las implemente.
### NamedTuples
En el proyecto debe usar los siguientes tipos:
```
Medallas = NamedTuple('Medallas',[('oro',int),('plata',int),('bronce',int)]) 
```
```
Registro = NamedTuple('Registro', [('ciudad_olimpica',str),('fecha_inicio',date),('pais',str),('deporte',str),('num_participantes',int),('genero',str),('medallas',Medallas),('sede',bool)])  
```
con el siguiente significado:
```
• ciudad olímpica: sede principal en la que se disputaron los JJOO, y que da nombre a los juegos. 
• fecha inicio: fecha en la que comenzó la competición del deporte al que hace referencia la fila del csv. 
• país: país para el que se recogen los participantes y las medallas. 
• deporte: deporte para el que se recogen los participantes y las medallas. 
• número participantes: número de participantes del país, deporte y género dados. 
• género: género de los participantes. Puede ser HOMBRE o MUJER. 
• medallas: número de medallas de oro, plata y bronce (separados por guión) para el país, deporte y género dados. 
• sede: contiene el valor SI si todas la competición para el deporte, país y género dados tuvo lugar en la sede principal, y NO si hubo alguna competición que se celebró en una ciudad distinta a la sede principal. 
```

### Ejercicio 1 (1 PUNTO)
Defina una función ´´lee_registros_olimpiadas`` Dado el nombre y ruta de un archivo csv (filename), devuelve una lista de tuplas de tipo Registro con los datos leídos del archivo. Defina las funciones auxiliares de
parseo que crea convenientes. Le puede ser de ayuda la función datetime.strptime(fecha_str,'%Y-%m-%d')  para el parseo de fechas. 

``def lee_registros_olimpiadas(ruta:str)->List[Registro]``

Resultados esperados en el test:
```
test_lee_registros_olimpiadas
Registros leídos: 199
Los dos primeros: [Registro(ciudad_olimpica='Seúl', fecha_inicio=datetime.date(1988, 9, 17), pais='ALEMANIA', deporte='tiro', num_participantes=12, genero='MUJER', medallas=Medalla(oro=0, plata=2, bronce=1), sede=False), Registro(ciudad_olimpica='Barcelona', fecha_inicio=datetime.date(1992, 7, 25), pais='HOLANDA', deporte='hípica', num_participantes=6, genero='MUJER', medallas=Medalla(oro=3, plata=0, bronce=1), sede=False)]

Los dos últimos: [Registro(ciudad_olimpica='Sydney', fecha_inicio=datetime.date(2000, 9, 15), pais='HOLANDA', deporte='atletismo', num_participantes=15, genero='HOMBRE', medallas=Medallas(oro=1, plata=1, bronce=0), sede=False), Registro(ciudad_olimpica='Barcelona', fecha_inicio=datetime.date(1992, 7, 25), pais='ALEMANIA', deporte='atletismo', num_participantes=8, genero='MUJER', medallas=Medallas(oro=0, plata=3, bronce=1), sede=False)]
```

### Ejercicio 2 (1 PUNTO)
Defina una función ``deporte_ambos_generos`` que dadas una lista de tuplas de tipo Registro y el año de celebración de los JJOO, devuelva un conjunto con los deportes de la edición de los JJOO celebrada en ese año en los que participaron tanto hombres como mujeres.

``def deportes_ambos_generos(registros: List[Registro], año:int )->Set[str]`` 

Resultados esperados en el test:
```
test_deportes_ambos_generos
Los deportes con participación de ambos géneros en el año 1984 son: {'esgrima', 'halterofilia', 'boxeo', 'gimnasia', 'hípica', 'ciclismo', 'vela', 'remo'}
Los deportes con participación de ambos géneros en el año 1992 son: {'esgrima', 'remo', 'tiro'}
Los deportes con participación de ambos géneros en el año 1988 son: {'esgrima', 'halterofilia', 'boxeo', 'gimnasia', 'atletismo', 'judo', 'ciclismo', 'tiro'}
```
### Ejercicio 3 (1 PUNTO)
Defina una función ``deportes_mas_frecuentes`` que dadas una lista de tuplas de tipo Registro, un número entero "n" y un género, que puede tomar el valor **None**, devuelva una lista de tuplas con el nombre y la frecuencia con los "n" deportes más frecuentes para el género dado La lista deberá estar ordenada de mayor a menor frecuencia. Si el género es **None** se tiene en cuenta cualquier género.

``def deportes_mas_frecuentes(registros: List[Registro],n:int,género:Optional[str]=None )->List[Tuple[str,int]]`` 

Resultados esperados en el test
```
test_deportes_mas_frecuentes
Los 3 deportes de mayor frecuencia son: [('tiro', 24), ('esgrima', 22), ('boxeo', 20)]
Los 4 deportes de mayor frecuencia para HOMBRE son: [('tiro', 12), ('esgrima', 12), ('remo', 11), ('boxeo', 10)]
Los 5 deportes de mayor frecuencia para MUJER son: [('tiro', 12), ('hípica', 12), ('ciclismo', 11), ('esgrima', 10), ('boxeo', 10)]
```
### Ejercicio 4 (1.5 PUNTOS)
Defina una función ``deporte_femenino_con_mas_paises_distintos_con_oro`` que dada una lista de tuplas de tipo Registro, devuelva el deporte practicado por mujeres en los distintos países que hayan ganado una medalla de oro.

``def deporte_femenino_con_mas_paises_distintos_con_oro(registros: List[Registro])-> str``

Resultados esperados en el test:
```
test_deporte_femenino_con_mas_paises_distintos_con_oro
Deporte: esgrima
```
### Ejercicio 5 (1.5 PUNTOS)
Defina una función ``deportes_mas_participantes_de_genero_por_juego`` que dada una lista de tuplas de tipo Registro, un país y un género, devuelva un diccionario en el que las claves sean los identificadores de los JJOO y los valores listas de tuplas con el deporte y los participantes, de los 3 deportes como máximo, del país y género dados con más participantes. La lista de los valores debe estar ordenada de más a menos participantes.

A estos efectos, el **identificador** de unos juegos se forma con la **ciudad olímpica seguido de los dos últimos dígitos del año** de celebración de los juegos (_vealo en los resultados esperados_).

``def deportes_mas_participantes_de_genero_por_juego(registros: List[Registro],pais:str, genero:str) -> Dict[str, List[str]]`` 

Resultados esperados en el test:
```
test_deportes_mas_participantes_de_genero_por_juego
Los tres deportes con más registros de género {género} de {país} son:
0-('Seúl88', [('atletismo', 16), ('ciclismo', 14), ('boxeo', 9)])
1-('Tokio64', [('esgrima', 17), ('hípica', 10), ('vela', 4)])
2-('Rio de Janeiro16', [('esgrima', 16), ('tiro', 5)])
3-('Sydney00', [('hípica', 12), ('boxeo', 11), ('halterofilia', 7)])
4-('Los Ángeles84', [('boxeo', 18), ('atletismo', 16), ('remo', 16)])
5-('Barcelona92', [('atletismo', 15), ('boxeo', 6)])

Los tres deportes con más registros de género {género} de {país} son:
0-('Sydney00', [('remo', 20), ('boxeo', 18), ('judo', 14)])
1-('Seúl88', [('tiro', 19), ('vela', 14), ('ciclismo', 13)])
2-('Rio de Janeiro16', [('atletismo', 13), ('vela', 7)])
3-('Los Ángeles84', [('remo', 17), ('hípica', 10), ('vela', 4)])
4-('Barcelona92', [('gimnasia', 9), ('esgrima', 8)])
5-('Tokio64', [('halterofilia', 19)])
```
### Ejercicio 6 (2 PUNTOS)
Defina una función ``deporte_con_todos_los_paises`` que dada una lista de tuplas de tipo Registro, devuelva cierto (_True_) si hay algún deporte en el que hayan participado todos los países, o falso (_False_) en otro caso.

``def deporte_con_todos_los_paises(registros: List[Registro]) ->  bool`` 

Resultados esperados en el test:
```
test_deporte_con_todos_los_paises
¿Algún deporte en todos los países?: True
```
### Ejercicio 7 (2 PUNTOS)
Defina una función ``año_con_mayor_incremento_participantes_de_pais`` que dada una lista de tuplas de tipo Registro y un país, devuelva una tupla con el incremento y el año en el que mayor incremento de participantes (contando hombre y mujeres de cualquier deporte) se ha producido con respecto al año de celebración anterior.

``def anyo_con_mayor_incremento_participantes_de_pais(registros: List[Registro],pais: str) -> Tuple[int, int] `` 

Resultados esperados en el test:
```
test_año_con_mayor_incremento_participantes_de_pais
El año con mayor incremento para ESPAÑA, ha sido: (57, 2016)  
El año con mayor incremento para PORTUGAL, ha sido: (37, 1988)
```