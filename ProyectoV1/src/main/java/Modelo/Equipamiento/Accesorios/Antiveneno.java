package Modelo.Equipamiento.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

/**
 * Accesorio que proporciona resistencia al estado envenenado
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class Antiveneno extends Accesorio {

    /**
     * Constructor que inicializa el accesorio con sus propiedades.
     */
    public Antiveneno(){
        nombre = "Anillo antiveneno";
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
    public void aplicarEfecto(Jugador jugador,Interfaz interfaz) {
        jugador.infligirEstado(Estados.RESISTENCIAVENENO, interfaz);
    }
}
