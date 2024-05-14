package Modelo.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import UI.Interfaces.Interfaz;

public class MasVida extends Accesorio {
    public MasVida(){
        nombre = "Collar de vida";
        inicioTurno = true;
        inicioCombate = true;
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.setSalud(jugador.getSalud() + 5);

    }
}
