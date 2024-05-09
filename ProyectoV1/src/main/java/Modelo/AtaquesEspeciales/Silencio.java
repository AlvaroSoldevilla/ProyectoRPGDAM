package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;

public class Silencio extends AtaqueEspecial {

    public Silencio() {
        nombre = "Silencio";
        coste = 4;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante) {
        if (puedeAtacar(atacante)) {
            objetivo.aplicarEfectoDeEstados(Estados.SILENCIADO);
            return true;
        } else {
            return false;
        }
    }



}


