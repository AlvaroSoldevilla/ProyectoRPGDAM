package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

/**
 * Accesorio que proporciona resistencia al estado electrocutado
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class Antirayos extends Accesorio {

    /**
     * Constructor que inicializa el accesorio con sus propiedades.
     */
    public Antirayos(){
        nombre = "Anillo antirayos";
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
        jugador.infligirEstado(Estados.RESISTENCIAELECTRICIDAD,interfaz);
    }
}
