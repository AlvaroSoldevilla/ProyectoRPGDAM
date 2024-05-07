package Modelo.Accesorios;

import Modelo.Armaduras.ArmaduraCuero;
import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import Modelo.Misc.Estados;

public class MasArmadura extends Accesorio {
    public MasArmadura() {
        nombre = "Prueba";
        permanente = false;
        inicioTurno = true;
        inicioCombate = true;
    }
    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.setDefensaTemp(jugador.getDefensaTemp()+5);
    }
}
