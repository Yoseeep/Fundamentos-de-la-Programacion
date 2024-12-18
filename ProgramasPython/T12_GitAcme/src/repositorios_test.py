from repositorios import *

def test_lee_repositorios(datos:List[Repositorio])->None: 
    print("\ntest_lee_repositorios")
    print("Número de registros:", len(datos))
    print(f"Los dos primeros son:\n----> {datos[0]}\n----> {datos[1]}")
    print(f"Los dos últimos son:\n----> {datos[-2]}\n----> {datos[-1]}")

def test_total_commits_por_anyo(datos:List[Repositorio])->None:
    print("\ntest_total_commits_por_anyo")
    recorre = total_commits_por_anyo(datos).items()
    for c,v in recorre:
        print(f"-->{c}={v}")
        
def test_n_mejores_repos_por_tasa_crecimiento(datos:List[Repositorio])->None:
    print("\ntest_n_mejores_repos_por_tasa_crecimiento")
    recorre = n_mejores_repos_por_tasa_crecimiento(datos)
    for t in recorre:
        print(f"-->{t}")

def test_recomendar_lenguajes(datos:List[Repositorio])->None:
    print("\ntest_recomendar_lenguajes")

    def encuentra_repositorio(nombre_repositorio:str,lista:List[Repositorio])->Repositorio:
        for i in datos:
            if i.nombre == nombre_repositorio:
                return i

    repositorio = encuentra_repositorio("ProjectX",datos)
    print(f"Para el repositorio {repositorio.nombre} que usa los lenguajes {repositorio.lenguajes} se recomiendan")
    print(recomendar_lenguajes(datos,repositorio))
    repositorio = encuentra_repositorio("MusicLibrary",datos)
    print(f"Para el repositorio {repositorio.nombre} que usa los lenguajes {repositorio.lenguajes} se recomiendan")
    print(recomendar_lenguajes(datos,repositorio))
    repositorio = encuentra_repositorio("MyProject",datos)
    print(f"Para el repositorio {repositorio.nombre} que usa los lenguajes {repositorio.lenguajes} se recomiendan")
    print(recomendar_lenguajes(datos,repositorio))    

def test_media_minutos_entre_commits_por_usuario(datos:List[Repositorio])->None:
    print("\ntest_media_minutos_entre_commits_por_usuario")
    print("Media de minutos para la fecha_ini=None y fecha_fin=None")
    for i in media_minutos_entre_commits_por_usuario(datos).items():
        print("-->",i)
    print("\nMedia de minutos para la fecha_ini=None y fecha_fin=2021-12-31")
    for i in media_minutos_entre_commits_por_usuario(datos,fecha_fin=date(2021,12,31)).items():
        print("-->",i)
    print("\nMedia de minutos para la fecha_ini=2023-09-01 y fecha_fin=None")
    for i in media_minutos_entre_commits_por_usuario(datos,fecha_ini=date(2023,9,1)).items():
        print("-->",i)
    print("\nMedia de minutos para la fecha_ini=2023-01-01 y fecha_fin=2023-11-01")
    for i in media_minutos_entre_commits_por_usuario(datos,fecha_ini=date(2023,1,1),fecha_fin=date(2023,11,1)).items():
        print("-->",i)



if __name__ == "__main__":
    repositorios = lee_repositorios("ProgramasPython/T12_GitAcme/data/repositorios.csv")
    test_lee_repositorios(repositorios)
    test_total_commits_por_anyo(repositorios)
    test_n_mejores_repos_por_tasa_crecimiento(repositorios)
    test_recomendar_lenguajes(repositorios)
    test_media_minutos_entre_commits_por_usuario(repositorios)