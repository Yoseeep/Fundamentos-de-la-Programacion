from typing import NamedTuple,Optional,List,Set,Tuple,Dict
import csv

BatallaGoT = NamedTuple('BatallaGoT',
                [('nombre',str),
                 ('rey_atacante',str),
                 ('rey_atacado',str),
                 ('gana_atacante',bool),
                 ('muertes_principales',bool),
                 ('comandantes_atacantes', List[str]),
                 ('comandantes_atacados',List[str]),
                 ('region', str),
                 ('num_atacantes',Optional[int]),
                 ('num_atacados',Optional[int])])

def lee_batallas(nombre_fichero:str)->List[BatallaGoT]:
    with open(nombre_fichero,'rt',encoding='utf-8') as f:
        res = []
        iterador = csv.reader(f,delimiter=',')
        next(iterador)
        for nombre,rey_atacante,rey_atacado,gana_atacante,muertes_principales,comandantes_atacantes,comandantes_atacados,region,num_atacantes,num_atacados in iterador:
            gana_atacante = gana_atacante == "win"
            muertes_principales = muertes_principales == "1"
            comandantes_atacantes =  (comandantes_atacantes.strip('"')).split(", ") if len(comandantes_atacantes) > 0 else []
            comandantes_atacados = (comandantes_atacados.strip('"')).split(", ") if len(comandantes_atacados) > 0 else []
            num_atacantes = int(num_atacantes) if len(num_atacantes) > 0 else None
            num_atacados = int(num_atacados) if len(num_atacados) > 0 else None
            res.append( BatallaGoT(nombre,rey_atacante,rey_atacado,gana_atacante,muertes_principales,comandantes_atacantes,comandantes_atacados,region,num_atacantes,num_atacados) )
        return res
    
def batallas_mas_comandantes(batallas:List[BatallaGoT],regiones:Optional[Set]=None,n:Optional[int]=None)->List[Tuple[str,int]]:
    filtro = [(batalla.nombre,len(batalla.comandantes_atacados)+len(batalla.comandantes_atacantes)) for batalla in batallas if (regiones == None or batalla.region in regiones)]
    filtro.sort(key=lambda a: a[1],reverse=True)
    return filtro if n == None else filtro[:n]


def reyes_mayor_menor_ejercito(batallas:List[BatallaGoT])->Tuple[str,str]:
    res = dict()
    for batalla in batallas:
        if batalla.rey_atacante not in res:
            res[batalla.rey_atacante] = 0
        if batalla.num_atacantes != None:
            res[batalla.rey_atacante] += batalla.num_atacantes

        if batalla.rey_atacado not in res:
            res[batalla.rey_atacado] = 0
        if batalla.num_atacados != None:
            res[batalla.rey_atacado] += batalla.num_atacados

    return ( max(res.items(),key=lambda a: a[1])[0] , min(res.items(),key=lambda a: a[1])[0] )

""" *UN INTENTO QUE NO SALIÓ:*
def rey_mas_victorias(batallas:List[BatallaGoT],rol:Optional[str]="ambos")->Tuple[str,int]:
    # Los reyes que hay
    reyes = set()
    for batalla in batallas:
        reyes.add(batalla.rey_atacante)
        reyes.add(batalla.rey_atacado)

    # Diccionario base con el que trabajar
    res = {rey:None for rey in reyes}

    # Rellenamos el diccionario base
    for batalla in batallas:
        if rol == "ambos" or (rol == "atacante" and batalla.gana_atacante):
            if res[batalla.rey_atacante] == None:
                res[batalla.rey_atacante] = 0
            res[batalla.rey_atacante] += 1
        if rol == "ambos" or (rol == "atacado" and not(batalla.gana_atacante)):
            if res[batalla.rey_atacado] == None:
                res[batalla.rey_atacado] = 0
            res[batalla.rey_atacado] += 1
    
    return max(res.items(),key=lambda a: a[1])
"""


""" *OTRA FORMA DE HACERLO: *
def rey_mas_victorias(batallas:List[BatallaGoT],rol:Optional[str]="ambos")->Tuple[str,int]:
    res = dict()
    for batalla in batallas:
        if rol == "atacante" and batalla.gana_atacante:
            if batalla.rey_atacante not in res:
                res[batalla.rey_atacante] = 0
            res[batalla.rey_atacante] += 1
        elif rol == "atacado" and not(batalla.gana_atacante):
            if batalla.rey_atacado not in res:
                res[batalla.rey_atacado] = 0
            res[batalla.rey_atacado] += 1
        else: # si rol == "ambos"
            if batalla.gana_atacante:
                if batalla.rey_atacante not in res:
                    res[batalla.rey_atacante] = 0
                res[batalla.rey_atacante] += 1
            else:
                if batalla.rey_atacado not in res:
                    res[batalla.rey_atacado] = 0
                res[batalla.rey_atacado] += 1

    return max(res.items(),key=lambda a: a[1]) if len(res)>0 else None
"""


def rey_mas_victorias(batallas:List[BatallaGoT],rol:Optional[str]="ambos")->Tuple[str,int]:
    res = dict()
    for batalla in batallas:
        if (rol == "ambos" or rol == "atacante") and batalla.gana_atacante:
            if batalla.rey_atacante not in res:
                res[batalla.rey_atacante] = 0
            res[batalla.rey_atacante] += 1
        elif (rol == "ambos" or rol == "atacado") and not(batalla.gana_atacante):
            if batalla.rey_atacado not in res:
                res[batalla.rey_atacado] = 0
            res[batalla.rey_atacado] += 1

    return max(res.items(),key=lambda a: a[1]) if len(res)>0 else None


def rey_mas_victorias_por_region(batallas:List[BatallaGoT],rol:Optional[str]="ambos")->Dict[str,str]:
    regiones = {batalla.region for batalla in batallas}
    
    res = dict()
    for region in regiones:
        informacion = rey_mas_victorias([batalla for batalla in batallas if batalla.region == region], rol)
        res[region] = informacion[0] if informacion != None else None
    
    return res


            