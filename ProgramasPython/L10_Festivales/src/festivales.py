from typing import NamedTuple, List, Dict, Tuple, Optional, Set
from datetime import date,time,datetime
import csv
 
Artista = NamedTuple("Artista",     
                        [("nombre", str), 
                        ("hora_comienzo", time), 
                        ("cache", int)])

Festival = NamedTuple("Festival", 
                        [("nombre", str),
                        ("fecha_comienzo", date),
                        ("fecha_final", date),
                        ("estado", str),                      
                        ("precio", float),
                        ("entradas_vendidas", int),
                        ("artistas", List[Artista]),
                        ("top", bool)
                    ])

# Lectura y primer ejercicio
def lee_festivales (archivo:str)->List[Festival]:
    festivales = []
    with open(archivo, "rt",encoding='utf-8') as f:
        iterador = csv.reader(f,delimiter=',')
        next(iterador)
        for nombre,fecha_comienzo,fecha_final,estado,precio,entradas_vendidas,artistas,top in iterador:
            fecha_comienzo = datetime.strptime(fecha_comienzo, "%Y-%m-%d").date()
            fecha_final = datetime.strptime(fecha_final, "%Y-%m-%d").date()
            precio = float(precio)
            entradas_vendidas = int(entradas_vendidas)
            artistas = [artista.split('_') for artista in artistas.split('-')]
            artistas = [Artista(nombre,hora_comienzo,cache) for nombre,hora_comienzo,cache in artistas]
            top = top == 'True'
            festivales.append(Festival(nombre,fecha_comienzo,fecha_final,estado,precio,entradas_vendidas,artistas,top))
        festivales.sort(key = lambda festival: festival.fecha_comienzo)
        return festivales
    
def total_facturado(festivales:List[Festival], fecha_ini:Optional[date]=None, fecha_fin:Optional[date]=None)->float:
    return sum( [festival.precio * festival.entradas_vendidas for festival in festivales if (fecha_ini == None or fecha_ini <= festival.fecha_comienzo) and (fecha_fin == None or fecha_fin >= festival.fecha_final) and festival.estado == "CELEBRADO"] )


