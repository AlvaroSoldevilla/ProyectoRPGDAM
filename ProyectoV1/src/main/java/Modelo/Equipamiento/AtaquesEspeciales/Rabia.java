package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

/**
 * La clase Rabia representa un ataque especial que aplica el estado de rabia al atacante.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class Rabia extends AtaqueEspecial {

    public Rabia() {
        nombre = "Rabia";
        coste = 2;
    }

    /**
     * Aplica el efecto Rabia, que inflige daño a la entidad a la vez que aumenta su daño.
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace el ataque.
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            interfaz.imprimirMensaje("Rabia");
            atacante.infligirEstado(Estados.RABIA,interfaz);
            return true;
        } else {
            return false;
        }
    }
}
