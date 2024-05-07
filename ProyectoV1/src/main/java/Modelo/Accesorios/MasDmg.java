package Modelo.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;

public class MasDmg extends Accesorio {
    public MasDmg(){
        nombre = "Más daño";
        inicioTurno = true;
        inicioCombate = true;
    }
    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.setDmg(jugador.getDmg() + 5);

    }
}
