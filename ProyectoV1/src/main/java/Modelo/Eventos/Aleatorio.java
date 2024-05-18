package Modelo.Eventos;

import Modelo.Bases.Evento;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import lombok.Data;

/**
 * La clase abstracta de los eventos aleatorios.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public abstract class Aleatorio extends Evento {

    /**
     * Constructor que inicializa el evento aleatorio con la interfaz del juego.
     *
     * @param interfaz La interfaz del juego.
     */
    public Aleatorio(Interfaz interfaz) {
        super(interfaz);
        titulo = "Evento";
        icono = Iconos.ALEATORIO;
    }
}
