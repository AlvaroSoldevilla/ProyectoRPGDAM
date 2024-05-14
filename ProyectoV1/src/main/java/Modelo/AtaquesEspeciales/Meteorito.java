package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class Meteorito extends AtaqueEspecial {

    public Meteorito() {
        nombre = "Meteorito";
        coste = 15;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante)) {
            objetivo.recibirDmg(atacante.getDmg()*2,interfaz);
            atacante.infligirEstado(Estados.MENOSDEFENSA);
            return true;
        } else {
            return false;
        }
    }



}
