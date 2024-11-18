## Fundamentos de Programación
# Ejercicio de laboratorio: Youtube
### Autora: Toñi Reina
### Revisores: Fermín L. Cruz, Mariano González 
### Adaptación para laboratorio: Toñi Reina

Este proyecto es una adaptación del primer parcial del curso 2021/22. 

### Enunciado

Se quieren analizar los datos de los vídeos que son tendencia (*trending*) de YouTube. Para ello se dispone, en la carpeta **data**, de un archivo en formato CSV codificado en UTF-8 que recoge datos de videos de tendencia. En cada línea del archivo se recoge la siguiente información de un video (note que todos los videos del fichero son tendencia): un identificador del vídeo (de tipo string); la fecha en la que el vídeo es tendencia (de tipo date); el título del vídeo, el canal en el que se publicó y la categoría del vídeo (todos de tipo string); y el número de visitas, de *likes* y de *dislikes* (de tipo entero). Las primeras líneas son las que se muestran a continuación:
```
id;fecha\_trending;titulo;canal;categoria;visitas;likes;dislikes
SbOwzAl9ZfQ;14/11/2017;Capítulo 12 | MasterChef 2017;MasterChef 2017;Entertainment;310130;4182;361
\_OXDcGPVAa4;14/11/2017;DOG HACKS | MUSAS LESSLIE LOS POLINESIOS;Musas;Howto & Style;499965;57781;681
Dhhp8M5K3UI;14/11/2017;Deleted video;Harrison;Entertainment;53265;194;41
sBmvgi-gd2M;14/11/2017;Así lucen los dobles de estos famosos;Badabun;Entertainment;53340;5266;68
QzlD0W4J8mk;14/11/2017;Sismo de 7.3 sacude Irak;MILENIO;News & Politics;5421;21;7
```
El objetivo del ejercicio es leer estos datos, realizar distintas operaciones con ellos e implementar los test que permitan probarlas. Cada operación se implementará en una función distinta. Se pide implementar las siguientes funciones y sus test correspondientes, teniendo en cuenta que se pueden definir funciones auxiliares cuando se considere necesario. Use la siguiente definición de namedtuple:
```python
from typing import NamedTuple
Video = NamedTuple('Video', 
[('id_video', str),
 ('fecha_trending', date),
 ('titulo',str),
 ('canal', str),
 ('categoria', str),
 ('visitas', int),
 ('likes', int),
 ('dislikes', int)
])
```
Para implementar el proyecto cree una carpeta **src** en la que debe incluir el módulo Python **youtube.py** para realizar los ejercicios que se le propone a continuación y el módulo **youtube_test.py** para ir probando los ejercicios a medida que los desarrolla.

**1. lee_trending_videos**: lee un fichero de entrada en formato CSV y devuelve una lista de tuplas de tipo Video conteniendo todos los datos almacenados en el fichero. **_(1 punto)_**

Resultados esperados:
```
test_lee_trending_videos
Registros leídos: 9064
Los tres primeros vídeos son: [Video(id_video='SbOwzAl9ZfQ', fecha_trending=datetime.date(2017, 11, 14), titulo='Capítulo 12 | MasterChef 2017', canal='MasterChef 2017', categoria='Entertainment', visitas=310130, likes=4182, dislikes=361), Video(id_video='klOV6Xh-DnI', fecha_trending=datetime.date(2017, 11, 14), titulo='ALEXA EX-INTEGRANTE DEL GRUPO TIMBIRICHE RENUNCIA A ¨La Voz Mexico 7¨TELEVISA 11/11/2017', canal='Micky Contreras Martinez', categoria='People & Blogs', visitas=104972, likes=271, dislikes=174), Video(id_video='6L2ZF7Qzsbk', fecha_trending=datetime.date(2017, 11, 14), titulo='LOUIS CKAGÓ - EL PULSO DE LA REPÚBLICA', canal='El Pulso De La República', categoria='News & Politics', visitas=136064, likes=10105, dislikes=266)]
Los tres últimos vídeos son: [Video(id_video='5hrotwT9Rkg', fecha_trending=datetime.date(2017, 12, 31), titulo='Nuestros invitados se atrevieron a todo en Verdad o Reto | Hoy', canal='Hoy', categoria='Entertainment', visitas=22661, likes=299, dislikes=27), Video(id_video='EmUhNptc6a8', fecha_trending=datetime.date(2017, 12, 31), titulo='Sin Tu Mirada | Marina es hija de Prudencia y de Luis', canal='ERICK NOVELAS', categoria='People & Blogs', visitas=149222, likes=944, dislikes=68), Video(id_video='o5XXq4ZUU9w', fecha_trending=datetime.date(2017, 12, 31), titulo='Dragon Ball Super Episódio 122 - Vegeta Supera Seus Limites Legendado PT/BR Fan Animation', canal='Animebr', categoria='Film & Animation', visitas=202774, likes=2266, dislikes=1380)]
```

**2. media_visitas**: recibe una lista de tuplas de tipo Video y una fecha. Devuelve la media de visitas de una fecha dada. Si para esa fecha no hay registros, la función devuelve cero. **_(1 punto)_**

Resultados esperados:
```
test_media_visitas
La media de visitas del día: 15/11/2017 es 190649.58549222798
La media de visitas del día: 11/01/2000 es 0
```
**3. video_mayor_ratio_likes_dislikes**: recibe una lista de tuplas de tipo Video y una categoría con valor por defecto **None**. Devuelve la tupla de tipo Video de la categoría dada como parámetro que ha tenido una mayor ratio likes/dislikes. Si la categoría toma el valor None, se devolverá la tupla de tipo Video con mayor ratio *likes*/*dislikes* de todas. La ratio *likes*/*dislikes* se calcula como el cociente entre el número de *likes* y el número de *dislikes*. Tenga en cuenta que puede haber vídeos que no hayan recibido *dislikes*, y que no deben ser tenidos en cuenta en el cálculo del máximo. **_(1,5 puntos)_**

Resultados esperados:
```
test_video_mayor_ratio_likes_dislikes
El video con mayor ratio de todos es: Video(id_video='JmQUmUHq2k8', fecha_trending=datetime.date(2017, 12, 11), titulo='[BT21] Meet BT21', canal='BT21', categoria='People & Blogs', visitas=80550, likes=22637, dislikes=7)

El video con mayor ratio de la catgoría Education es: Video(id_video='F6oX3ozIqfo', fecha_trending=datetime.date(2017, 12, 15), titulo='CORTE GRAPADO - Makeup FX', canal='SARA G', categoria='Education', visitas=16225, likes=2118, dislikes=5)
```
**4. canales_top**: recibe una lista de tuplas de tipo Video y un número entero n, con valor por defecto **3**. Devuelve una lista de tuplas con los n canales que tienen más vídeos *trending*. Cada tupla contiene el nombre del canal y el número de vídeos *trending* de ese canal. Tenga en cuenta que, si un vídeo es *trending* durante "d" días contará "d" veces para el canal. La lista estará ordenada de mayor a menor número de vídeos *trending*. _**(1,5 puntos)**_

Resultados esperados:
```
test_canales_top
El top-3 de canales es:
[('Exatlón', 48), ('Cracks', 42), ('Badabun', 41)]

El top-5 de canales es:
[('Exatlón', 48), ('Cracks', 42), ('Badabun', 41), ('Troom Troom Es', 40), ('Enamorándonos', 40)]
```

**5. video_mas_likeability_por_categoria**: recibe una lista de tuplas de tipo Video y un número entero "k", que representa una constante con valor por defecto **20**. Devuelve un diccionario que asocia las categorías (claves), con el *id_video* del vídeo de mayor índice *likeability* de esa categoría. El índice *likeability* se calcula según la fórmula que se indica a continuación. _**(2 puntos)**_

$`likeability= \frac{k *likes-dislikes} {k*visitas}`$

Resultados esperados:
```
test_video_mas_likeability_por_categoria
Vídeo con más likeability por categoría con constante 20
Entertainment ---> ODjB1sL_D2E
People & Blogs ---> OUKUYrWOrtU
News & Politics ---> iS_pOZel65c
Howto & Style ---> ikP-ovgZ_N8
Music ---> qGohZu-Bh4A
Comedy ---> 5cdpF-j0R4Q
Sports ---> ubyXi8c097Q
Autos & Vehicles ---> XhLVE-IEXtA
Film & Animation ---> Xew3Ci5IRdY
Nonprofits & Activism ---> aHmmsOQMBfE
Education ---> 9dm10rgFKmA
Science & Technology ---> JBZx03342eM
Gaming ---> s6AQL58y9vE
Pets & Animals ---> 1gPXAR-w-Nw
Travel & Events ---> lVL8EVbbHiA
Shows ---> tGPP0F6uqOE
```

**6. incrementos\_visitas**: recibe una lista de tuplas de tipo Video y un canal. Devuelve una lista con el incremento (o decremento) del total de visitas diarias de los vídeos *trending* de un día con respecto al día anterior para el rango de fechas en que hay mediciones. Note que puede haber días para el que se hayan tomado mediciones en los que no aparezca ningún video de un canal concreto, ya que los videos de ese canal no han sido tendencia ese día. Sin embargo, esos días habrá que tenerlos en cuenta en el cálculo de los incrementos. Por ejemplo, el canal Mr. Tops no tiene ningún vídeo *trending* el día 20/11/2017, y el día 21/11/2017 ha obtenido 310425 visitas en videos que son tendencia. En este caso, en la lista resultado debe aparecer un incremento de 310425, aunque no se haya registrado ningún dato de ese canal el día 20/11/2017. _**(2 puntos)**_

Resultados esperados:
```
test_incrementos_visitas
Incrementos de visitas del canal Exatlón
[-325232, 16670, -27297, 203424, -195337, 298667, -256420, -21585, 11724, 62358, 328537, -320609, 392661, 153879, -604864, 333205, 61758, 69525, -479489, 403893, 165957, -569500, 338264, -54478, -266704, -30019, 443346, -384826, -21858, -19084, 286399, -210520, -57257, 494904, -523057, -39327, 114209, -50784, 555004, -597681, 595337, -522645, 462916, -458246, -56157, 224648, -184478]

Incrementos de visitas del canal Mr. Tops
[-231503, 0, 0, 0, 0, 0, 310425, -310425, 184459, -184459, 0, 0, 0, 132521, -132521, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200487, -53308, -147179, 0, 80380, -80380, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
```

**7. youtube_test.py** La realización de los test tiene una puntuación de _**(1 punto)**_

