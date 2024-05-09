package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;

public class Purificacion extends AtaqueEspecial {

    public Purificacion() {
        nombre = "Purificación";
        coste = 10;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante) {
        if (puedeAtacar(atacante)) {
            atacante.eliminarEstadosPerjudiciales();
            return true;
        } else {
            return false;
        }
    }



}

