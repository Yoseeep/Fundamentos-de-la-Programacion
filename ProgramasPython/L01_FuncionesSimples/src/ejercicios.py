from typing import NamedTuple, List, Tuple
Datos_nutricionales = NamedTuple('datos_nutricionales',[("peso",float),("altura",float)])
Edad = NamedTuple("edad",[('edad',int),('sexo',str)]) # sexo == 'H'/'M'.

def calcula_imc(peso:float,estatura:float)->float:
    """
    Calcula el Índice de Masa Corporal de una persona
    """
    return peso / (estatura**2)

def calcula_estado_nutricional(peso:float, estatura:float)->str:
    """
    Dice el estado nutricional
    """
    imc = calcula_imc(peso,estatura)
    if imc < 18.5:
        return "Bajo Peso"
    elif 18.5 <= imc < 25:
        return "Normal"
    elif 25 <= imc < 30:
        return "Sobrepeso"
    else:
        return "Obesidad"

def trata_estados_nutricionales(datos:List[Datos_nutricionales])->List[Tuple]:
    """
    Recibe una lista de tuplas con el peso y la altura de una serie de personas
    Devuelve una lista de tuplas con el IMC y el estado nutricional de cada tupla
    """
    return [(calcula_imc(persona.peso,persona.altura),calcula_estado_nutricional(persona.peso,persona.altura)) for persona in datos]

def producto_escalar(v1:List[int],v2:List[int])-> int:
    """
    Calcula el producto escalar de dos vectores.
    """
    if len(v1) == len(v2):
        acum = 0
        for i in range(len(v1)):
            acum += v1[i] * v2[i]
        return acum
    else:
        return None
    
def calcula_promedio_edades_sexo(datos:List[Edad],sexo:str)->float:
    """
    Calcula el promedio de las edades del sexo dado, de las personas de la lista.
    """
    aux = []
    for dato in datos:
        if dato.sexo == sexo:
            aux.append(dato.edad)
    if aux == []:
        return None
    else:
        return sum(aux)/len(aux)
    