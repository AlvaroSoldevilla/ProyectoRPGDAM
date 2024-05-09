package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;

public class Evasion extends AtaqueEspecial {

    public Evasion() {
        nombre = "Evasión";
        coste = 2;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante) {
        if (puedeAtacar(atacante)) {
            atacante.aplicarEfectoDeEstados(Estados.EVASION);
            return true;
        } else {
            return false;
        }
    }



}

