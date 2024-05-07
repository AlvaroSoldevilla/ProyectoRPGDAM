package Modelo.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;

public class MasVida extends Accesorio {
    public MasVida(){
        nombre = "Prueba";
        permanente = false;
        inicioTurno = true;
        inicioCombate = true;
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {

    }
}
