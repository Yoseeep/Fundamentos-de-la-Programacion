from typing import NamedTuple,List,Optional,Tuple
from datetime import date,time,datetime
import csv

Ingrediente = NamedTuple("Ingrediente",
					     [("nombre",str),
						  ("cantidad",float),
						  ("unidad",str)])
						 
Receta = NamedTuple("Receta", 
                    [("denominación", str),
                     ("tipo", str),
                     ("dificultad", str),
                     ("ingredientes", Optional[List[Ingrediente]]),
                     ("tiempo", int),
                     ("calorías", int),
                     ("fecha", date),
                     ("precio", float)])

def lee_recetas(nombre_fichero:str)->List[Receta]:
    acum = []
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        iterador = csv.reader(f,delimiter=';')
        next(iterador)
        for denominación,tipo,dificultad,ingredientes,tiempo,calorías,fecha,precio in iterador:
            ingredientes = ingredientes.split(',')
            ingredientes = [ingrediente.split('-') for ingrediente in ingredientes if len(ingrediente) != 0]
            ingredientes = [Ingrediente(ingrediente[0],float(ingrediente[1]),ingrediente[2]) for ingrediente in ingredientes]
            tiempo = int(tiempo)
            calorías = int(calorías)
            fecha = datetime.strptime(fecha,"%d/%m/%Y").date()
            precio = float(precio.replace(',','.'))
            acum.append(Receta(denominación,tipo,dificultad,ingredientes,tiempo,calorías,fecha,precio))
    return acum

def diferentes_ingredientes(datos:List[Receta],unidad_medida:Optional[str]=None,)->int:
    """
    Recibe una lista de tipo Receta y una unidad de medidas de los ingredientes de tipo str, que puede tomar el valor **None**, en cuyo caso no se filtra por unidad
    Devuelve l número de los diferentes ingredientes que se han medido en la unidad dada.
    """
    diferentes = set()
    for dato in datos:
        for ingrediente in dato.ingredientes:
            if unidad_medida == None or ingrediente.unidad == unidad_medida:
                diferentes.add(ingrediente.nombre)
    return len(diferentes)

def recetas_con_ingredientes(datos:List[Receta],nombres:set[str])->List[Tuple[str,int,float]]:
    """
    Recibe una lista de tipo Receta, un conjunto con nombres de ingredientes
    Devuelve una lista de tuplas con las denominaciones, las calorías y los precios de las recetas que entre sus ingredientes existe alguno de los dados como parámetro
    """
    acum = []
    for receta in datos:
        ingredientes = {ingrediente.nombre for ingrediente in receta.ingredientes}
        if len(nombres & ingredientes) != 0:
            acum.append((receta.denominación,receta.calorías,receta.precio))
    return acum

def receta_más_barata(datos:List[Receta],conjunto:set[str],n:Optional[int]=None)->Receta:
    """
    Recibe una lista de tipo Receta, un conjunto con tipos de recetas y un parámetro "n" de tipo entero con valor por defecto **None**
    Devulve la receta más barata de entre las "n" recetas con menos calorías de alguno de los tipos de receta dados como parámetro.
    Si n toma el valor **None** se buscará la receta más barata de entre totas las recetas.
    """   
    filtro = [receta for receta in datos if receta.tipo in conjunto] #Filtro por tipo
    filtro.sort(key=lambda a: a.calorías) #Ordeno de menor a mayor calorías
    #filtro = sorted(filtro,key=lambda a: a.calorías) #Ordeno de menor a mayor calorías
    if n != None: 
        filtro = filtro[:n]
    return min(filtro,key=lambda a: a.precio)
