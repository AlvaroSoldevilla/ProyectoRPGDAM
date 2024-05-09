package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;

public class Parry extends AtaqueEspecial {

    public Parry() {
        nombre = "Aplomo";
        coste = 2;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante) {
        if (puedeAtacar(atacante)) {
            atacante.infligirEstado(Estados.CONTRAATACANDO);
        }
        return false;
    }
}
