package Modelo.Bases;

import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import lombok.Data;

/**
 * La clase abstracta Evento representa un evento en el juego, esto incluye combates, eventos aleatorios o eventos específicos como la tienda.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public abstract class Evento {
    /**
     * Constructor por defecto.
     */
    public Evento(){}

    /**
     * Constructor principal del evento, contiene la interfaz del juego.
     *
     * @param interfaz Interfaz del juego.
     */
    public Evento(Interfaz interfaz) {
        this.interfaz = interfaz;
    }

    /**
     * Interfaz del juego.
     */
    protected Interfaz interfaz;
    /**
     * Titulo del evento.
     */
    protected String titulo;
    /**
     * Texto que se mostrará en la interfaz, servirá para explicar el contexto y las opciones del evento.
     */
    protected String texto;
    /**
     * Variable que contiene el jugador para aplicarle distintos efectos.
     */
    protected Jugador jugador;
    /**
     * Imagen que se mostrará cuando se va a elegir el evento.
     */
    protected Iconos icono;
    /**
     * Opciones que se podrán elegir durante el evento.
     */
    protected String[] opciones;
    /**
     * Variable usada para guardar la opcion elegida por el jugador.
     */
    protected int opcion;

    /**
     * Método abstracto que determina el comportamiento del evento.
     */
    public abstract void empezarEvento();

    /**
     * Método abstracto que determina el comportamiento del evento cuando termina.
     */
    public abstract void terminarEvento();
}
