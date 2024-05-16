package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import UI.Interfaces.Interfaz;

public class PruebaAtaque extends AtaqueEspecial {

    public PruebaAtaque() {
        nombre = "Prueba";
        coste = 2;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante)) {
            objetivo.recibirDmg(atacante.getDmg()*2,interfaz);
            return true;
        } else {
            return false;
        }
    }



}
