package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;

public class Fortaleza extends AtaqueEspecial {

    public Fortaleza() {
        nombre = "Fortaleza";
        coste = 2;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante) {
        if (puedeAtacar(atacante)) {
        atacante.aplicarEfectoDeEstados(Estados.FORTALEZA);
        return true;
        } else {
            return false;
        }
    }



}

