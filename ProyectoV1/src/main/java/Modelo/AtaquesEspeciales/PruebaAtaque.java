package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;

public class PruebaAtaque extends AtaqueEspecial {

    public PruebaAtaque() {
        nombre = "Prueba";
        coste = 2;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante) {
        if (puedeAtacar(atacante)) {
            objetivo.recibirDmg(atacante.getDmg()*2);
            return true;
        } else {
            return false;
        }
    }



}
