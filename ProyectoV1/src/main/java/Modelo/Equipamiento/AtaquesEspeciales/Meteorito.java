package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

public class Meteorito extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public Meteorito() {
        nombre = "Meteorito";
        coste = 40;
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            interfaz.imprimirMensaje("Meteorito");
            objetivo.recibirDmg(atacante.getDmg()*2,interfaz);
            atacante.infligirEstado(Estados.MENOSDEFENSA,interfaz);
            return true;
        } else {
            return false;
        }
    }



}
