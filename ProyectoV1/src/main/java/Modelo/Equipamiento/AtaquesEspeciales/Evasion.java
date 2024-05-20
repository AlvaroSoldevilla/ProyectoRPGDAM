package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;


/**
 * La clase Evasion representa un ataque especial que aplica el estado de evasión al atacante.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class Evasion extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public Evasion() {
        nombre = "Evasión";
        coste = 2;
    }

    /**
     * Aplica el efecto de estado evasion, que hace que los ataques tengan más posibilidad de fallar.
     *
     * @param objetivo  La entidad objetivo del ataque.
     * @param atacante  La entidad que realiza el ataque.
     * @param interfaz  La interfaz del juego.
     * @return true si el ataque se realiza con éxito, false en caso contrario.
     */
    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            interfaz.imprimirMensaje("Evasion");
            atacante.infligirEstado(Estados.EVASION,interfaz);
            return true;
        } else {
            return false;
        }
    }
}