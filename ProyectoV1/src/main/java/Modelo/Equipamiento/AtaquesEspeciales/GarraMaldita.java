package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class GarraMaldita extends AtaqueEspecial {

    public GarraMaldita() {
        nombre = "Garra Maldita";
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            objetivo.recibirDmg(atacante.getDmg(),interfaz);
            objetivo.infligirEstado(Estados.MALDITO,interfaz);
            return true;
        } else {
            return false;
        }
    }



}

