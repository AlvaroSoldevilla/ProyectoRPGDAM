package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import UI.Interfaces.Interfaz;

public class GolpeAplastante extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public GolpeAplastante() {
        nombre = "Golpe aplastante";
        coste = 3;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            objetivo.recibirDmg(atacante.getDmg()+5,interfaz);
            return true;
        } else {
            return false;
        }
    }



}

