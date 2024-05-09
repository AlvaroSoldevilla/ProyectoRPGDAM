package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;

public class Meteorito extends AtaqueEspecial {

    public Meteorito() {
        nombre = "Meteorito";
        coste = 15;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante) {
        if (puedeAtacar(atacante)) {
            objetivo.recibirDmg(atacante.getDmg()*2);
            atacante.aplicarEfectoDeEstados(Estados.MENOSDEFENSA);
            return true;
        } else {
            return false;
        }
    }



}
