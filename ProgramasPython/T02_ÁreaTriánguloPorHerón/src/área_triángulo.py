from math import sqrt
def areaTriangulo(a:float,b:float,c:float)->float:
    """
    Función que calcula el área de un triángulo cualquiera en función de las medidas de sus lados, usando la fórmula de Herón. 
    """
    s = (a+b+c)/2 # Semiperímetro.
    if all(map(lambda l: l>0,[a,b,c])) and all(map(lambda l: s>l,[a,b,c])): # Si a,b,c > 0 y s-a,s-b,s-c > 0.
        return sqrt(s*(s-a)*(s-b)*(s-c)) # Devuelve el área.
    else:
        return None # No devuelve el área pues no existe el triángulo. 

