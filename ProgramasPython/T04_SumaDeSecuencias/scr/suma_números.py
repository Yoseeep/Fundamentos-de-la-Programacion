def sumar_números(n1:int,n2:int,n3:int)-> int:
    """
    Recibe tres números n1,n2,n3 y devuelve la suma de los números comprendidos entre n1 y n2 que sean múltiplos de n3.
    """
    if n1 > n2:
        return None # Error en los datos: el primer número debe ser menor o igual que el segundo.
    else:
        acumula = 0
        for i in range(n1,n2+1):
            if i % n3 == 0:
                acumula += i
        return acumula
