package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

/**
 * La clase GarraMaldita representa un ataque especial que inflige daño y aplica el estado de maldición al objetivo.
 * Ataque especial del segundo jefe.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class GarraMaldita extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public GarraMaldita() {
        nombre = "Garra Maldita";
    }

    /**
     * Hace daño al objetivo y le aplica el estado maldito, que reduce las estadísticas del objetivo.
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace el ataque.
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
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

