def factorial(n:int) -> int:
    """
    Calcula el factorial de un número entero no negativo.
    """
    if n < 0:
        return None
    elif n == 0:
        return 1
    else:
        return n * factorial(n-1)

def número_combinatorio(m:int,n:int) -> int:
    """
    Calcula el número combinatorio del primer número entero no negativo sobre el segundo número entero no negativo introducidos.
    """
    if m < 0 or n < 0 or m < n:
        return None
    else:
        return int( factorial(m)/(factorial(n) * factorial(m-n)) )
