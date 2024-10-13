from typing import NamedTuple
from datos_personales import *

Persona = NamedTuple("persona", [("dni",str), ("nombre",str), ("apellidos",str), ("edad",int), ("localidad",str), ("provincia",str)])

lista_personas = [('12345678A','JUAN','AFAN POSTIGO',22,'SEVILLA','SEVILLA'), 
            ('12345678B','NICOLAS','AGUILAR SAUCEDO',20,'DOS HERMANAS','SEVILLA'), 
            ('12345678C','LUCAS','ACEJO GARCÍA',20,'UTRERA','SEVILLA'), 
            ('12345678D','CLAUDIA','ÁLVAREZ GARCÍA',21,'VISO DEL ALCOR','SEVILLA'), 
            ('12345678E','PAULA','ALBENDÍN CAMINO',19,'TOMARES','SEVILLA'), 
            ('12345678F','ANA','LOBATO ÁLVAREZ',18,'PUNTA UMBRÍA','HUELVA'), 
            ('12345678G','ANTONIO','DÍAZ NARANJO',18,'CHIPIONA','CADIZ'), 
            ('12345678H','SOFÍA','GUERRERO CANTARERO',20,'CHIPIONA','CADIZ')]

lista_personas = list(map(lambda a: Persona(*a), lista_personas)) # Asignamos el tipo Persona a las tuplas de la lista_personas


# Test de la función filtra_por_edad
edad_a_filtrar = 20
test_filtra_por_edad = filtra_por_edad(lista_personas,edad_a_filtrar)
print("<<< Test de la función filtra_por_edad >>>")
for i in test_filtra_por_edad:
    print(i)

print('-'*20) # ------------------

# Test de la función obtine_dni_y_nombres
test_obtiene_dni_y_nombres = obtiene_dni_y_nombres(lista_personas)
print("<<< Test de la función obtine_dni_y_nombres >>>")
for i in test_obtiene_dni_y_nombres:
    print(i)