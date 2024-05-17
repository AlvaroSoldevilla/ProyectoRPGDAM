package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class Silencio extends AtaqueEspecial {

    public Silencio() {
        nombre = "Silencio";
        coste = 4;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            objetivo.infligirEstado(Estados.SILENCIADO,interfaz);
            return true;
        } else {
            return false;
        }
    }



}


