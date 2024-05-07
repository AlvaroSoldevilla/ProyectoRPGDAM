package Modelo.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import Modelo.Misc.Estados;

public class Antirayos extends Accesorio {

    public Antirayos(){
        nombre = "Anillo antirayos";
        inicioTurno = true;
        inicioCombate = true;
    }

    public void aplicarEfecto(Jugador jugador) {
        jugador.aplicarEfectoDeEstados(Estados.RESISTENCIAELECTRICIDAD);
    }
}
