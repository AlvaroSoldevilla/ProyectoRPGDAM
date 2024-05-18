package Modelo.Equipamiento.AtaquesEspeciales;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

/**
 * La clase Fortaleza representa un ataque especial que aplica el estado de fortaleza al atacante.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class Fortaleza extends AtaqueEspecial {

    /**
     * Constructor que inicializa el ataque.
     */
    public Fortaleza() {
        nombre = "Fortaleza";
        coste = 2;
    }

    /**
     * Aplica el efecto de estado Fortaleza, que aumenta la defensa.
     *
     * @param objetivo Entidad que recibe el ataque
     * @param atacante Entidad que hace el ataque
     * @param interfaz Interfaz del juego, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si el ataque se realiza correctamente.
     */
    @Override
    public boolean hacerAtaque(Entidad objetivo, Entidad atacante, Interfaz interfaz) {
        if (puedeAtacar(atacante,interfaz)) {
        atacante.infligirEstado(Estados.FORTALEZA,interfaz);
        return true;
        } else {
            return false;
        }
    }
}

