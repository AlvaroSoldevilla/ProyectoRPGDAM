package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import UI.Interfaces.Interfaz;

/**
 * La clase Purificacion representa un ataque especial que elimina los estados perjudiciales del atacante.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class Purificacion extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public Purificacion() {
        nombre = "Purificación";
        coste = 10;
    }

    /**
     * Elimina los estados perjudiciales del atacante.
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace el ataque.
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            atacante.eliminarEstadosPerjudiciales();
            return true;
        } else {
            return false;
        }
    }
}

