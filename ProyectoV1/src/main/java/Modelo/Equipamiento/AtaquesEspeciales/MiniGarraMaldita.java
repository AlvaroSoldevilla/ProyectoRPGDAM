package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;


public class MiniGarraMaldita extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public MiniGarraMaldita() {
        nombre = "Version menor de Garra Maldita";
    }

    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            interfaz.imprimirMensaje("Garra maldita");
            objetivo.recibirDmg(atacante.getDmg() / 2,interfaz);
            objetivo.infligirEstado(Estados.MALDITO,interfaz);
            return true;
        } else {
            return false;
        }
    }



}

