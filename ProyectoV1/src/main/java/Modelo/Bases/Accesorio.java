package Modelo.Bases;

import UI.Interfaces.Interfaz;
import lombok.Data;

/**
 * La clase abstracta Accesorio representa un objeto con distintos efectos en el juego.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public abstract class Accesorio extends Equipamiento {
    /**
     * Indica si el efecto del accesorio se debe aplicar al inicio de cada turno.
     */
    protected boolean inicioTurno = false;
    /**
     * Indica si el efecto del accesorio se debe aplicar al inicio de cada combate.
     */
    protected boolean inicioCombate = false;
    /**
     * Indica si el efecto del accesorio se debe aplicar al final de cada combate.
     */
    protected boolean finCombate = false;
    /**
     * Indica si el efecto del accesorio se debe aplicar fuera de combate, normalmente al ser obtenido.
     */
    protected boolean permanente = false;

    /**
     * Método abstracto que dicta el efecto del accesorio.
     *
     * @param jugador El jugador al que se le aplicará el efecto.
     * @param interfaz La interfaz de la aplicacion, se usará principalmente para mostrar mensajes.
     */
    public abstract void aplicarEfecto(Jugador jugador,Interfaz interfaz);

}
