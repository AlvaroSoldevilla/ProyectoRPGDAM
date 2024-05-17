package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class Antirayos extends Accesorio {

    public Antirayos(){
        nombre = "Anillo antirayos";
        inicioTurno = true;
        inicioCombate = true;
    }

    public void aplicarEfecto(Jugador jugador, Interfaz interfaz) {
        jugador.infligirEstado(Estados.RESISTENCIAELECTRICIDAD,interfaz);
    }
}
