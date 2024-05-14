package Modelo.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class Rabia extends AtaqueEspecial {

    public Rabia() {
        nombre = "Rabia";
        coste = 2;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante)) {
            atacante.infligirEstado(Estados.FORTALEZA);
            return true;
        } else {
            return false;
        }
    }



}
