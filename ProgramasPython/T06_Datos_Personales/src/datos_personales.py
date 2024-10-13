from typing import NamedTuple

def filtra_por_edad(listaDeTuplas:list[NamedTuple],edad:int)->list[NamedTuple]:
    """
    Filtra de una lista de NamedTuple de Personas con el atributo edad si es menor que la edad introducida.
    """
    return list(filter(lambda person: person.edad < edad, listaDeTuplas))

def obtiene_dni_y_nombres(listaDeTuplas:list[NamedTuple])->list[tuple[str]]:
    """
    De una lista de NamedTuple de Personas con los atributos dni y nombre, devuelve una lista de tuplas con esos valores.
    """ 
    return [(person.dni,person.nombre) for person in listaDeTuplas]

