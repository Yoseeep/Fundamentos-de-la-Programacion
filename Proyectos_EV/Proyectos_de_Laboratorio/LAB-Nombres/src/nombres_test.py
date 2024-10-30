from nombres import *

def imprime_nombre(funcion):
    def decorador(*args,**kwargs):
        print(f'\n{'-'*5} TEST de \'{funcion.__name__}\' {'-'*5}\n')
        funcion(*args,**kwargs)
    return decorador

@imprime_nombre
def test_leer_frecuencias_nombres(datos:List[FrecuenciaNombre])->None:
    print(f"Leídos {len(datos)} registros")
    print(f"Mostrando los 3 primeros:")
    print(f"\t{datos[:3]}")
    print(f"\nMostrando los 3 últimos:")
    print(f"\t{datos[-3:]}")

@imprime_nombre
def test_filtrar_por_genero(datos:List[FrecuenciaNombre])->None:
    genero = 'Hombre'
    datos_filtrados = filtrar_por_genero(datos,genero)
    print(f"\t- Número de registros para '{genero}': {len(datos_filtrados)}")
    genero = 'Mujer'
    datos_filtrados = filtrar_por_genero(datos,genero)
    print(f"\t- Número de registros para '{genero}': {len(datos_filtrados)}")

@imprime_nombre
def test_calcular_nombres(datos:List[FrecuenciaNombre])->None:
    nombres = calcular_nombres(datos)
    print(f"\t- Ambos géneros: {sorted(nombres)[:10]}")
    nombres = calcular_nombres(datos,'Hombre')
    print(f"\t- Hombres: {sorted(nombres)[:10]}")
    nombres = calcular_nombres(datos,'Mujer')
    print(f"\t- Mujeres: {sorted(nombres)[:10]}")

def test_calcular_top_nombres_de_año(datos:List[FrecuenciaNombre])->None:
    año = 2008
    n = 10
    nombres = calcular_top_nombres_de_año(datos,año,n)
    print(f"\n{"-"*5} TEST de 'calcular_top_nombres_de_año' para {año} {'-'*5}\n")
    print(f"\t- Ambos géneros: {nombres}")
    genero = 'Hombre'
    nombres = calcular_top_nombres_de_año(datos,año,n,genero)
    print(f"\t- {genero}s: {nombres}")
    genero = 'Mujer'
    nombres = calcular_top_nombres_de_año(datos,año,n,genero)
    print(f"\t- {genero}es: {nombres}")

@imprime_nombre
def test_calcular_nombres_ambos_generos(datos:List[FrecuenciaNombre])->None:
    nombres = calcular_nombres_ambos_generos(datos)
    print(f"\t- Nombres: {nombres}")

@imprime_nombre
def test_calcular_nombres_compuestos(datos:List[FrecuenciaNombre])->None:
    nombres = calcular_nombres_compuestos(datos)
    print(f"\t- Ambos géneros: {sorted(nombres)}")
    genero = 'Hombre'
    nombres = calcular_nombres_compuestos(datos,genero)
    print(f"\t- Ambos géneros: {sorted(nombres)}")
    genero = 'Mujer'
    nombres = calcular_nombres_compuestos(datos,genero)
    print(f"\t- Ambos géneros: {sorted(nombres)}")

@imprime_nombre
def test_calcular_frecuencia_media_nombre_años(datos:List[Tuple[FrecuenciaNombre]])->None:
    nombre = "NEREA"
    f1 = 2005
    f2 = 2010
    frecuencia = calcular_frecuencia_media_nombre_años(datos,nombre,f1,f2)
    print(f"\t- La frecuencia media del nombre {nombre} entre {f1} y {f2} es {frecuencia}")

@imprime_nombre
def test_calcular_nombre_mas_frecuente_año_genero(datos:List[FrecuenciaNombre])->None:
    año = 2017
    genero = 'Mujer'
    frecuente = calcular_nombre_mas_frecuente_año_genero(datos,año,genero)
    print(f"\t- El nombre más frecuente del año {año} y género {genero} es {frecuente}")

@imprime_nombre
def test_calcular_año_mas_frecuencia_nombre(datos:List[FrecuenciaNombre])->None:
    nombre = 'VERA'
    año_nombre = calcular_año_mas_frecuencia_nombre(datos,nombre)
    print(f"\t- El año con mayor frecuencia del nombre {nombre} es {año_nombre}")

@imprime_nombre
def test_calcular_nombres_mas_frecuentes(datos:List[FrecuenciaNombre],genero:str,década:Optional[None],n:Optional[int]=5)->None:
    nombres = calcular_nombres_mas_frecuentes(datos,genero,década,n)
    print(f"\t- Los {n} nombres más frecuentes del género {genero} y década {década} son {nombres}")

@imprime_nombre
def test_calcular_año_frecuencia_por_nombre(datos:List[FrecuenciaNombre])->None:
    genero = 'Mujer'
    recorre = calcular_año_frecuencia_por_nombre(datos,genero)
    for clave,valores in recorre.items():
        print(f"\t\t- {clave}: {valores}")
    
@imprime_nombre
def test_calcular_nombre_mas_frecuente_por_año(datos:List[FrecuenciaNombre])->None:
    genero = 'Hombre'
    resultado = calcular_nombre_mas_frecuente_por_año(datos,genero)
    print(f"\t- {genero}s: {resultado}")
    genero = 'Mujer'
    resultado = calcular_nombre_mas_frecuente_por_año(datos,genero)
    print(f"\t- {genero}es: {resultado}")

@imprime_nombre
def test_calcular_frecuencia_por_año(datos:List[FrecuenciaNombre])->None:
    nombre = 'IKER'
    resultado = calcular_frecuencia_por_año(datos,nombre)
    print(f"\t- {nombre}: {resultado}")

@imprime_nombre
def test_mostrar_evolucion_por_año(datos:List[FrecuenciaNombre])->None:
    nombre = 'IKER'
    mostrar_evolucion_por_año(datos,nombre)

@imprime_nombre
def test_calcular_frecuencias_por_nombre(datos:List[FrecuenciaNombre])->None:
    resultado = calcular_frecuencias_por_nombre(datos)
    print(f"\t- Frecuencias: {resultado}")

@imprime_nombre
def test_mostrar_frecuencias_nombres(datos:List[FrecuenciaNombre])->None:
    limite = 20
    mostrar_frecuencias_nombres(datos,limite)

if __name__ == '__main__':
    frecuencias = leer_fecuencias_nombres('Proyectos_EV/Proyectos_de_Laboratorio/LAB-Nombres/data/frecuencias_nombres.csv')
    test_leer_frecuencias_nombres(frecuencias)
    test_filtrar_por_genero(frecuencias)
    test_calcular_nombres(frecuencias)
    test_calcular_top_nombres_de_año(frecuencias)
    test_calcular_nombres_ambos_generos(frecuencias)
    test_calcular_nombres_compuestos(frecuencias)
    test_calcular_frecuencia_media_nombre_años(frecuencias)
    test_calcular_nombre_mas_frecuente_año_genero(frecuencias)
    test_calcular_año_mas_frecuencia_nombre(frecuencias)
    test_calcular_nombres_mas_frecuentes(frecuencias,'Hombre',2000,3)
    test_calcular_nombres_mas_frecuentes(frecuencias,'Mujer',2010)
    test_calcular_año_frecuencia_por_nombre(frecuencias)
    test_calcular_nombre_mas_frecuente_por_año(frecuencias)
    test_calcular_frecuencia_por_año(frecuencias)
    test_mostrar_evolucion_por_año(frecuencias)
    test_calcular_frecuencias_por_nombre(frecuencias)
    test_mostrar_frecuencias_nombres(frecuencias)