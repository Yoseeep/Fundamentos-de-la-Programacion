from partidas import *

def test_lee_partidas(partidas:List[Partida])->None:
    print("\ntest_lee_partidas")
    print("Total registros leídos:", len(partidas))
    print("Mostrando los tres primeros:")
    for i in partidas[:3]:
        print("\t",i)
    
def test_victoria_mas_rapida(partidas:List[Partida])->None:
    print("\ntest_victoria_mas_rapida")
    resultado = victoria_mas_rapida(partidas)
    print(f"La partida más rápida fue una entre {resultado[0]} y {resultado[1]} que duró {resultado[2]} segundos.")

def test_top_ratio_medio_personajes(partidas:List[Partida])->None:
    print("\ntest_top_ratio_medio_personajes")
    n = 3
    resultado = top_ratio_medio_personajes(partidas, n)
    print(f"El top {n} de ratios medios es: {resultado}")

def test_enemigos_mas_debiles(partidas:List[Partida])->None:
    print("\ntest_enemigos_mas_debiles")
    pj = "Ken"
    resultado = enemigos_mas_debiles(partidas, pj)
    print(f"Los enemigos más débiles de {pj} son {resultado}")

def test_movimientos_comunes(partidas:List[Partida])->None:
    print("\ntest_movimientos_comunes")
    personaje1 = "Ryu"
    personaje2 = "Ken"
    resultado = movimientos_comunes(partidas,personaje1,personaje2)
    print(f"Los movimientos repetidos entre {personaje1} y {personaje2} son: {set(resultado)}")

def test_dia_mas_combo_finish(partidas:List[Partida])->None:
    print("\ntest_dia_mas_combo_finish")
    resultado = dia_mas_combo_finish(partidas)
    print("El día que más Ultra Combo Finish ha habido es el", resultado)

if __name__ == "__main__": 
    partidas = lee_partidas("ExamenesPython/LAB-Street-Fighter/data/games.csv")
    test_lee_partidas(partidas)
    test_victoria_mas_rapida(partidas)
    test_top_ratio_medio_personajes(partidas)
    test_enemigos_mas_debiles(partidas)
    test_movimientos_comunes(partidas)
    test_dia_mas_combo_finish(partidas)