package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import UI.Interfaces.Interfaz;

public class Purificacion extends AtaqueEspecial {

    public Purificacion() {
        nombre = "Purificación";
        coste = 10;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante)) {
            atacante.eliminarEstadosPerjudiciales();
            return true;
        } else {
            return false;
        }
    }



}

