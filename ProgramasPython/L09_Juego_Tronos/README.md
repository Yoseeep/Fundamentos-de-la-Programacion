## Fundamentos de Programación
# Ejercicio de laboratorio: Juego de Tronos
### Autor: Fermín L. Cruz
### Revisores: José C. Riquelme, Mariano González, Toñi Reina
### Adaptación para laboratorio: Toñi Reina

Este proyecto es una adaptación del primer parcial del curso 2021/22. 

## Condiciones iniciales

Se dispone de datos relativos a batallas libradas en la serie de libros de Game of Thrones (GoT).
En el proyecto se le facilita una caperta **data** con un archivo denominado ``battles.csv`` con la siguiente estructura de campos. 


* _nombre_: nombre de la batalla, de tipo `str`.
* _rey atacante_: nombre del rey que inicia la batalla, de tipo `str`.
* _rey atacado_: nombre del rey que es atacado, de tipo `str`.
* _gana atacante_: indica si los atacantes ganaron la batalla, de tipo `bool` (verá que en el fichero aparece `win` si ganaron los atacantes, `loss` si ganaron los atacados).
* _muertes principales_: indica si murieron personajes principales de la trama, de tipo `bool` (en el fichero aparece `1` si hubo muerte de personajes principales, y `0`si no las hubo).
* _comandantes atacantes_: lista de nombres de los personajes que lideraron el ataque, de tipo `list`. Puede ocurrir que el campo esté vacío, lo que dará lugar a una lista vacía.
* _comandantes atacados_: lista de nombres de los personajes que lideraron la defensa, de tipo `list`. Puede ocurrir que el campo esté vacío, lo que dará lugar a una lista vacía.
* _región_: nombre de la región donde se llevó a cabo la batalla, de tipo `str`.
* _número de atacantes_: número aproximado de integrantes del ejércitos atacante. Puede ocurrir que el campo esté vacío, en cuyo caso debe tomar el valor `None`.
* número de atacados: número aproximado de integrantes del ejércitos atacado. Puede ocurrir que el campo esté vacío, en cuyo caso debe tomar el valor `None`.

Un ejemplo de línea del fichero es la siguiente:

```
Battle of Torrhen's Square; Robb Stark; Greyjoy; win; 0; Rodrik Cassel, Cley Cerwyn; Dagmer Cleftjaw; The North; 244; 900 
```

Lo que indica que la batalla conocida como `Battle of Torrhen’s Square` fue iniciada por el rey `Robb Stark`, contra el rey `Greyjoy`; la batalla fue ganada por el rey atacante, y no se produjeron muertes entre los personajes principales que participaron en la misma; los comandantes que lideraron el ataque fueron `Rodrik Cassel` y `Cley Cerwyn`, mientras que hubo un único comandante liderando la defensa, `Dagmer Cleftjaw`; por último, la batalla transcurrió en la región `The North` , y en ella participaron `244` atacantes y `900` defensores.


Para almacenar los datos de un entrenamiento se usará obligatoriamente la siguiente ``NamedTuple``:
```
BatallaGoT = NamedTuple('BatallaGoT',
                [('nombre',str),
                 ('rey_atacante',str),
                 ('rey_atacado',str),
                 ('gana_atacante',bool),
                 ('muertes_principales',bool),
                 ('comandantes_atacantes', List[str]),('comandantes_atacados',List[str]),
                 ('region', str),
                 ('num_atacantes',Optional[int]),
                 ('num_atacados',Optional[int])])
```

Cree una carpeta **src** para incluir los siguientes módulos Python:

**got.py** en el que implemente las funciones que se indican a continuación.
 
**got_test.py** en el que incluirá las funciones de tests (con sus parámetros) necesarias para ir probando las funciones a medida que las implemente. 

**1. lee_batallas** _(1,25 puntos)_: que recibe como parámetro el nombre de un fichero (incluida la ruta) y devuelve una lista de tuplas de tipo `BatallaGoT` conteniendo todos los datos almacenados en el fichero.

Resultado esperado:
```
test_lee_batallas
Número de batallas leídas: 34
Primera batalla: BatallaGoT(nombre='Battle of the Golden Tooth', rey_atacante='Joffrey/Tommen Baratheon', rey_atacado='Robb Stark', gana_atacante=True, muertes_principales=True, comandantes_atacantes=['Jaime Lannister'], comandantes_atacados=['Clement Piper', 'Vance'], region='The Westerlands', num_atacantes=15000, num_atacados=4000)

Cuarta batalla: BatallaGoT(nombre='Battle of the Green Fork', rey_atacante='Robb Stark', rey_atacado='Joffrey/Tommen Baratheon', gana_atacante=False, muertes_principales=True, comandantes_atacantes=['Roose Bolton', 'Wylis Manderly', 'Medger Cerwyn', 'Harrion Karstark', 'Halys Hornwood'], comandantes_atacados=['Tywin Lannister', 'Gregor Clegane', 'Kevan Lannister', 'Addam Marbrand'], region='The Riverlands', num_atacantes=18000, num_atacados=20000)

Última batalla: BatallaGoT(nombre='Siege of Raventree', rey_atacante='Joffrey/Tommen Baratheon', rey_atacado='Robb Stark', gana_atacante=True, muertes_principales=False, comandantes_atacantes=['Jonos Bracken', 'Jaime Lannister'], comandantes_atacados=['Tytos Blackwood'], region='The Riverlands', num_atacantes=1500, num_atacados=None)
```
**2. batallas_mas_comandantes** _(2 puntos)_: recibe una lista de tuplas de tipo ``BatallaGoT``, un conjunto con nombre de regiones, con valor por defecto `None`, y un valor entero `n` con valor por defecto `None`, y devuelve una lista de tuplas con los nombres y el total de comandantes participantes de aquellas `n` batallas con mayor número de comandantes participantes (tanto atacantes como atacados), llevadas a cabo en **alguna** de las regiones dadas.No obstante, si las regiones toma el valor `None` se considerarán todas las regiones; por su parte, si el parámetro `n` es `None` se devolverán las tuplas correspondientes a todas las batallas de las regiones escogidas. En todos los casos, la lista devuelta estará ordenada de mayor a menor número de comandantes. 

Resultado esperado

```
test_batallas_mas_comandantes
Para las regiones {'The Riverlands', 'The North'} y n=4, las batallas con más comandantes son:
[('Battle of the Green Fork', 9), ('Battle of the Fords', 9), ('Battle of the Camps', 5), ('Sack of Winterfell', 5)]
```
**3. reyes_mayor_menor_ejercito** _(2 puntos)_: que recibe una lista de tuplas de tipo ``BatallaGoT`` y devuelve una tupla con dos cadenas correspondientes a los nombres de los reyes con el mayor y el menor ejército, respectivamente. Para calcular el tamaño del ejército de un rey se deben sumar los números de atacantes o de atacados de todas las batallas en las que ha participado dicho rey ya sea como atacante o como atacado.

Resultado esperado:
```
test_reyes_mayor_menor_ejercito
('Stannis Baratheon', 'Mance Rayder')
```

**4. rey_mas_victorias** _(2,75 puntos)_: recibe una lista de tuplas de tipo ``BatallaGoT`` y una cadena ``rol``, con valor por defecto **ambos**, y devuelve una tuplas con el nombre del rey que acumula más victorias y el número de victorias que ha conseguido.

Tenga en cuenta que un rey puede ganar una batalla en la que actúa como atacante, en cuyo caso el campo ``gana_atacante`` será `True`, o si actúa como atacado, será `False`. Si el parámetro ``rol`` es igual a **atacante**, se devolverá el nombre del rey que acumula más victorias sólo como atacante; si ``rol`` es igual a **atacado**, se devolverá el nombre del rey que acumula más victorias solo como atacado; si ``rol`` es igual a **ambos**, se devolveré el nombre del rey que acumula más victorias en todas las batallas en las que ha participado (sumando sus victorias como atacante y como atacado).

Por último, **si ningún rey acumula victorias** del rol especificado en la lista de batallas recibida, la función devuelve `None`.

Resultado esperado:

```
test_rey_mas_victorias
El rey con más victorias es: ('Joffrey/Tommen Baratheon', 15)
El rey atacante con más victorias es: ('Joffrey/Tommen Baratheon', 12)
El rey atacado con más victorias es: ('Joffrey/Tommen Baratheon', 3)
```

**5. rey_mas_victorias_por_region** _(2 puntos)_: recibe una lista de tuplas de tipo ``BatallaGoT`` y una cadena ``rol``, con valor por defecto **ambos**, y devuelve un diccionario que relaciona cada región con el nombre del rey o reyes que acumula más victorias en batallas ocurridas en esa región. El parámetro ``rol`` tiene el mismo significado que en la función anterior.

Si para alguna región **no hay ningún rey** que haya ganado una batalla con el rol especificado, en el diccionario aparecerá el valor `None` asociado a dicha región.

Se el **recomienda** usar la función anterior ``rey_mas_victorias`` para resolver este ejercicio, ya que si se le envía una lista de batallas, devuelve el rey con más victoria. Tenga cuidado que esta función puede devolver ``None`` en lugar de una tupla en la que estaría el nombre del rey que busca.

Resultado esperado:
```
test_rey_mas_victorias_por_region

Reyes con más victorias por región
The Westerlands --> Robb Stark
The Riverlands --> Joffrey/Tommen Baratheon
The North --> Balon/Euron Greyjoy
The Stormlands --> Joffrey/Tommen Baratheon
The Crownlands --> Joffrey/Tommen Baratheon
Beyond the Wall --> Mance Rayder
The Reach --> Balon/Euron Greyjoy

Reyes atacantes con más victorias por región
The Westerlands --> Robb Stark
The Riverlands --> Joffrey/Tommen Baratheon
The North --> Balon/Euron Greyjoy
The Stormlands --> Joffrey/Tommen Baratheon
The Crownlands --> None
Beyond the Wall --> None
The Reach --> Balon/Euron Greyjoy

Reyes atacados con más victorias por región
The Westerlands --> None
The Riverlands --> Joffrey/Tommen Baratheon
The North --> None
The Stormlands --> None
The Crownlands --> Joffrey/Tommen Baratheon
Beyond the Wall --> Mance Rayder
The Reach --> None
```

