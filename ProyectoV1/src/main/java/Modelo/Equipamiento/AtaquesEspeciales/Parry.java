package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class Parry extends AtaqueEspecial {

    public Parry() {
        nombre = "Parry";
        coste = 2;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            atacante.infligirEstado(Estados.CONTRAATACANDO,interfaz);
        }
        return false;
    }
}
