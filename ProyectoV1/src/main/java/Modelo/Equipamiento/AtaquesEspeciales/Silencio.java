package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

/**
 * La clase Silencio representa un ataque especial que aplica el estado de silenciado al objetivo.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class Silencio extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public Silencio() {
        nombre = "Silencio";
        coste = 4;
    }

    /**
     * Aplica el estado silenciado, que impide al objetivo hacer ciertas acciones.
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace el ataque.
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
            interfaz.imprimirMensaje("Silencio");
            objetivo.infligirEstado(Estados.SILENCIADO,interfaz);
            return true;
        } else {
            return false;
        }
    }
}


