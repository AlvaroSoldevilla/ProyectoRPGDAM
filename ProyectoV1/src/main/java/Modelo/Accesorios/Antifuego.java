package Modelo.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import Modelo.Misc.Estados;

public class Antifuego extends Accesorio {

    public Antifuego(){
        nombre = "Anillo antifuego";
        inicioTurno = true;
        inicioCombate = true;
    }

    public void aplicarEfecto(Jugador jugador) {
        jugador.aplicarEfectoDeEstados(Estados.RESISTENCIAQUEMADURA);
    }
}
