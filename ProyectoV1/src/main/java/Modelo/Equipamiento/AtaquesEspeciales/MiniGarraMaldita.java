package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class MiniGarraMaldita extends AtaqueEspecial {

    public MiniGarraMaldita() {
        nombre = "Version menor de Garra Maldita";
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante)) {
            objetivo.recibirDmg(atacante.getDmg() / 2,interfaz);
            objetivo.infligirEstado(Estados.MALDITO);
            return true;
        } else {
            return false;
        }
    }



}

