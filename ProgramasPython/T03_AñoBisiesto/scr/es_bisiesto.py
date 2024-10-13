def es_bisiesto(entero:int) -> bool:
    '''
    Comprueba si un año es bisiesto o no
    '''
    return (entero % 400 == 0) or (entero % 4 == 0 and entero % 100 != 0)