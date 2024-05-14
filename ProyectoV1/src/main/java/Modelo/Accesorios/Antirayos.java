package Modelo.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import Modelo.Enums.Estados;

public class Antirayos extends Accesorio {

    public Antirayos(){
        nombre = "Anillo antirayos";
        inicioTurno = true;
        inicioCombate = true;
    }

    public void aplicarEfecto(Jugador jugador) {
        jugador.infligirEstado(Estados.RESISTENCIAELECTRICIDAD);
    }
}
