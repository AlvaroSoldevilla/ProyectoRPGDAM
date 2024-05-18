package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import UI.Interfaces.Interfaz;

/**
 * Accesorio que aumenta temporalmente la vida
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class MasVida extends Accesorio {

    /**
     * Constructor que inicializa el accesorio con sus propiedades.
     */
    public MasVida(){
        nombre = "Collar de vida";
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
        jugador.aumentarSalud(5);

    }
}
