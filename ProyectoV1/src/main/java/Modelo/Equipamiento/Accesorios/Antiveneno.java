package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import Modelo.Enums.Estados;

public class Antiveneno extends Accesorio {

    public Antiveneno(){
        nombre = "Anillo antiveneno";
        inicioTurno = true;
        inicioCombate = true;
    }

    public void aplicarEfecto(Jugador jugador) {
        jugador.infligirEstado(Estados.RESISTENCIAVENENO);
    }
}
