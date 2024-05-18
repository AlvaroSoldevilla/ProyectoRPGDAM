package Modelo.Eventos;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Evento;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import lombok.Getter;

/**
 * La clase abstracta BatallaConJefe representa un evento de batalla contra un jefe.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public abstract class BatallaConJefe extends Evento {

    /**
     * Constructor que inicializa el evento de batalla con un jefe con la interfaz del juego.
     *
     * @param interfaz La interfaz del juego.
     */
    public BatallaConJefe(Interfaz interfaz) {
        super(interfaz);
        icono = Iconos.BATALLAJEFE;
    }

    /**
     * El enemigo principal de la batalla.
     */
    @Getter
    protected Enemigo jefe;
}