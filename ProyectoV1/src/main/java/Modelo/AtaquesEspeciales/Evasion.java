package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class Evasion extends AtaqueEspecial {

    public Evasion() {
        nombre = "Evasión";
        coste = 2;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante)) {
            atacante.infligirEstado(Estados.EVASION);
            return true;
        } else {
            return false;
        }
    }



}

