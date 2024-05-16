package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;

public class MasArmadura extends Accesorio {
    public MasArmadura() {
        nombre = "Collar de armadura";
        inicioTurno = true;
        inicioCombate = true;
    }
    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.setDefensa(jugador.getDefensa()+5);
    }
}
