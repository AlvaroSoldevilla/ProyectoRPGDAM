package Modelo.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;

public class MasVida extends Accesorio {
    public MasVida(){
        nombre = "Más vida";
        inicioTurno = true;
        inicioCombate = true;
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.setSalud(jugador.getSalud() + 5);

    }
}
