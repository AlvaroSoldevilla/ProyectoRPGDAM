package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import UI.Interfaces.Interfaz;

/**
 * Accesorio que proporciona mas oro al final del combate
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class MonedaOro extends Accesorio {

    /**
     * Constructor que inicializa el accesorio con sus propiedades.
     */
    public MonedaOro(){
        nombre = "Moneda de oro";
        finCombate= true;
    }

    /**
     * Aplica el efecto del accesorio al jugador.
     *
     * @param jugador El jugador al que se le aplica el efecto.
     * @param interfaz La interfaz del juego.
     */
    @Override
    public void aplicarEfecto(Jugador jugador, Interfaz interfaz) {
        jugador.ganarOro(5);
    }
}
