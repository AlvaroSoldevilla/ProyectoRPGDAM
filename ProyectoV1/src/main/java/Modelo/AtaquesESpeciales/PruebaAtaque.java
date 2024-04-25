package Modelo.AtaquesESpeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;

public class PruebaAtaque extends AtaqueEspecial {

    public PruebaAtaque() {
        nombre = "Prueba";
        coste = 2;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atatcante) {
        if (puedeAtacar(atatcante)) {
            objetivo.recibirDmg(atatcante.getDmg()*2);
            return true;
        } else {
            return false;
        }
    }



}
