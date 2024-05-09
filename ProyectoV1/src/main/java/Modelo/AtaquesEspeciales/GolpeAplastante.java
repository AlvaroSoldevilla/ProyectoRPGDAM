package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;

public class GolpeAplastante extends AtaqueEspecial {

    public GolpeAplastante() {
        nombre = "Golpe aplastante";
        coste = 3;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante) {
        if (puedeAtacar(atacante)) {
            objetivo.recibirDmg(atacante.getDmg()+5);
            return true;
        } else {
            return false;
        }
    }



}

