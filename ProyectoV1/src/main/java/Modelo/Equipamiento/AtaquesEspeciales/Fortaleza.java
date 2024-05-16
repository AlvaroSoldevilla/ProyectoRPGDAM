package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class Fortaleza extends AtaqueEspecial {

    public Fortaleza() {
        nombre = "Fortaleza";
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

