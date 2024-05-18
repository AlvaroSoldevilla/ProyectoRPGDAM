package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import UI.Interfaces.Interfaz;

/**
 * Accesorio que aumenta temporalmente la defensa
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class MasArmadura extends Accesorio {
    /**
     * Constructor que inicializa el accesorio con sus propiedades.
     */
    public MasArmadura() {
        nombre = "Collar de armadura";
        inicioTurno = true;
        inicioCombate = true;
    }

    /**
     * Aplica el efecto del accesorio al jugador.
     *
     * @param jugador El jugador al que se le aplica el efecto.
     * @param interfaz La interfaz del juego.
     */
    @Override
    public void aplicarEfecto(Jugador jugador, Interfaz interfaz) {
        jugador.aumentarDefensa(5);
    }
}
