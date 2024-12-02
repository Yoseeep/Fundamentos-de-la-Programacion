from typing import NamedTuple,Optional,List,Set,Tuple
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

