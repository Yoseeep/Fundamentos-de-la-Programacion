from typing import NamedTuple,Set,Optional,Tuple,Dict
from typing import List
import csv
import matplotlib.pyplot as plt


FrecuenciaNombre = NamedTuple('FrecuenciaNombre',[('año',int),('nombre',str),('frecuencia',int),('genero',str)])

def leer_fecuencias_nombres(nombre_fichero:str)->List[FrecuenciaNombre]:
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        res = []
        iterardor = csv.reader(f,delimiter=',')
        next(iterardor)
        for año,nombre,frecuencia,genero in iterardor:
            res.append(FrecuenciaNombre(int(año),nombre,int(frecuencia),genero))
        return res

def filtrar_por_genero(datos:List[FrecuenciaNombre],genero:str)->List[FrecuenciaNombre]:
    """
    Recibe  una lista de tuplas de tipo FrecuenciaNombre y un género de tipo str
    Devuelve una lista de tuplas de tipo FrecuenciaNombre con los registros del género recibido como parámetro
    """
    return [dato for dato in datos if dato.genero == genero]

def calcular_nombres(datos:List[FrecuenciaNombre],genero:Optional[str] = None)->Set[str]:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre y un género de tipo str
    Devuelve un conjunto {str} con los nombres del género recibido como parámetro
    El género puede ser 'Hombre', 'Mujer' o tener un valor None, en cuyo caso se incluyen en el conjunto todos los nombres. El valor por defecto del género es None.
    """
    return {dato.nombre for dato in datos if (genero == dato.genero) or (genero == None)}

def calcular_top_nombres_de_año(datos:List[FrecuenciaNombre],año:int,n:Optional[int]=10,genero:Optional[str]=None)->List[Tuple[str,int]]:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre, un año de tipo int, un número límite de tipo int y un género de tipo str
    Devuelve una lista de tuplas (nombre, frecuencia) de tipo (str, int) con los nombres más frecuentes del año y el género dados,
        ordenada de mayor a menor frecuencia, y con un máximo de límite nombres. El género puede ser 'Hombre', 'Mujer' o tener un 
        valor None, en cuyo caso se incluyen en la lista todos los nombres. El valor por defecto del límite es 10 y el del género es None.
    """
    nombres = [dato for dato in datos if dato.año == año and (genero == dato.genero or genero == None)]
    nombres.sort(key=lambda x: x.frecuencia,reverse=True)
    return [(nombre.nombre,nombre.frecuencia) for nombre in nombres[:n]]

def calcular_nombres_ambos_generos(datos:List[FrecuenciaNombre])->Set[str]:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre
    Devuelve un conjunto {str} con los nombres que aparecen en ambos géneros
    """
    nombres_hombres = set(calcular_nombres(datos,'Hombre'))
    nombres_mujeres = set(calcular_nombres(datos,'Mujer'))
    return nombres_hombres & nombres_mujeres

def calcular_nombres_compuestos(datos:List[FrecuenciaNombre],genero:Optional[str]=None)->Set[str]:
    """
    Recibe unalista de tuplas de tipo FrecuenciaNombre y un género de tipo str
    Devuelve un conjunto {str} con los nombres que continen más de una palabra. 
    El género puede ser 'Hombre', 'Mujer' o tener un valor None, en cuyo caso se incluyen en el conjunto todos los nombres. 
    El valor por defecto del género es None.
    """
    return {dato.nombre for dato in datos if (genero == dato.genero or genero == None) and ' ' in dato.nombre}

def calcular_frecuencia_media_nombre_años(datos:List[Tuple[FrecuenciaNombre]],nombre:str,año_inicial:int,año_final:int)->float:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre, un nombre, un año inicial y un año final.
    Calcula la frecuencia media del nombre dado como parámetro en el rango de años [año_inicial, 
    año_final) formado por el año inicial y el año final dados como parámetro. Si no se puede calcular la media devuelve 0.
    """
    frecuencias = [dato.frecuencia for dato in datos if nombre == dato.nombre and año_inicial <= dato.año < año_final]
    return sum(frecuencias)/len(frecuencias) if len(frecuencias) != 0 else 0

def calcular_nombre_mas_frecuente_año_genero(datos:List[FrecuenciaNombre],año:int,genero:str)->str:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre, un año y un género.
    Devuelve el nombre más frecuente en el año dado como parámetro del género dado como parámetro.
    """
    filtro = [dato for dato in datos if dato.año == año and dato.genero == genero]
    return max(filtro,key= lambda a: a.frecuencia).nombre

def calcular_año_mas_frecuencia_nombre(datos:List[FrecuenciaNombre],nombre:str)->int:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre y un nombre
    Devuelve el año con mayor frecuencia del nombre dado como parámetro
    """
    filtro = [dato for dato in datos if dato.nombre == nombre]
    return max(filtro,key = lambda a: a.frecuencia).año

def calcular_nombres_mas_frecuentes(datos:List[FrecuenciaNombre],genero:str,década:int,n:Optional[int]=5)->List[str]:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre, un género, un entero que representa una década y un número n.
    Devuelve una lista con los n nombres más frecuentes, de mayor a menor frecuencia, del género dado en la década 
        dada. Por defecto, debe devolver los 5 más frecuentes. La década se da con cuatro dígitos: 1960, 1970...
    """
    filtro = [dato for dato in datos if dato.genero == genero and década <= dato.año < década + 10]
    res = {}
    for i in filtro:
        if i.nombre not in res:
            res[i.nombre] = i.frecuencia
        else:
            res[i.nombre] += i.frecuencia
    return [nombre for nombre,_ in sorted(res.items(),key=lambda a: a[1],reverse=True)[:n]]

def calcular_año_frecuencia_por_nombre(datos:List[FrecuenciaNombre],genero:str)->Dict[str,List[Tuple[int,int]]]:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre y un género.
    Devuelve un diccionario en el que las claves son los nombres y los valores una lista de tuplas (año, frecuencia) para cada nombre del género dado como parámetro.
    """
    res = {}
    filtro = [dato for dato in datos if dato.genero == genero]
    for i in filtro:
        if i.nombre not in res:
            res[i.nombre] = [(i.año,i.frecuencia)]
        else:
            res[i.nombre].append((i.año,i.frecuencia))
    return res

def calcular_nombre_mas_frecuente_por_año(datos:List[FrecuenciaNombre],genero:str)->List[Tuple[int,str,int]]:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre y un género de tipo str
    Devuelve una lista de tuplas (año, nombre, frecuencia) de tipo (int, str, int) ordenada por año con el nombre más frecuente de cada año. 
    El género puede ser 'Hombre' o 'Mujer'.
    """
    años = {dato.año for dato in datos if dato.genero == genero} # Por si resulta que un año no tiene datos de algún género.
    res = []
    for año in años:
        filtro = [dato for dato in datos if dato.año == año and dato.genero == genero]
        información = max(filtro,key=lambda a: a.frecuencia)
        res.append((año,información.nombre,información.frecuencia))
    res.sort(key=lambda a: a[0])
    return res

def calcular_frecuencia_por_año(datos:List[FrecuenciaNombre],nombre:str)->List[Tuple[int,int]]:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre y un nombre de tipo str
    Devuelve una lista de tuplas (año, frecuencia) de tipo (int, int) ordenada por año con la frecuencia del nombre en cada año. 
        En el caso de que un nombre se use para hombres y mujeres, se sumarán ambas frecuencias
    """
    información = {dato for dato in datos if dato.nombre == nombre}
    res = {}
    for i in información:
        if i.año not in res:
            res[i.año] = i.frecuencia
        else:
            res[i.año] += i.frecuencia
    return sorted(res.items(),key=lambda a: a[0])

def mostrar_evolucion_por_año(datos:List[FrecuenciaNombre],nombre:str)->None:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre y un nombre de tipo str
    Genera un gráfico con la evolución de la frecuencia del nombre a lo largo de los años(Figura 1)
    """
    años = []
    frecuencias = []
    for año,frecuencia in calcular_frecuencia_por_año(datos,nombre):
        años.append(año)
        frecuencias.append(frecuencia)
    plt.plot(años, frecuencias)
    plt.title("Evolución del nombre '{}'".format(nombre))
    plt.show()

def calcular_frecuencias_por_nombre(datos:List[FrecuenciaNombre])->Dict[str,int]:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre
    Devuelve un diccionario {str: int} que relaciona cada nombre con la frecuencia acumulada del nombre
    """
    res = {}
    for dato in datos:
        if dato.nombre not in res:
            res[dato.nombre] = dato.frecuencia
        else:
            res[dato.nombre] += dato.frecuencia
    return res

def mostrar_frecuencias_nombres(datos:List[FrecuenciaNombre],limite:Optional[int]=10)->None:
    """
    Recibe una lista de tuplas de tipo FrecuenciaNombre y un número límite de tipo int
    Genera un diagrama de barras con las frecuencias de los nombres más populares, en orden decreciente de popularidad y con un máximo de límite nombres (Figura 2). 
        El valor por defecto del límite es 10.
    """
    información = calcular_frecuencias_por_nombre(datos)
    información = sorted(información.items(),key=lambda a: a[1],reverse=True)[:limite]
    nombres = []
    frecuencias = []
    for nombre,frecuencia in información:
        nombres.append(nombre)
        frecuencias.append(frecuencia)
    plt.bar(nombres,frecuencias)
    plt.xticks(rotation=80)
    plt.title("Frecuencia de los {} nombres más comunes".format(limite))
    plt.show()
