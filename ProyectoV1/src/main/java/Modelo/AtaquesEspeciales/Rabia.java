package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;

public class Rabia extends AtaqueEspecial {

    public Rabia() {
        nombre = "Rabia";
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
