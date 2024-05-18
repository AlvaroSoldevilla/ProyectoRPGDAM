package UI.Elementos;

import lombok.Data;

import javax.swing.*;

/**
 * La clase abstracta Escena representa una escena en el juego.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public abstract class Escena extends JPanel {

    /**
     * La imagen de fondo de la escena.
     */
    protected String imagenDeFondo;

    /**
     * Constructor privado para evitar la creación de instancias sin fondo.
     */
    private Escena() {}

    /**
     * Constructor que inicializa la escena con la imagen de fondo proporcionada.
     *
     * @param fondo La imagen de fondo de la escena.
     */
    public Escena(String fondo) {
        imagenDeFondo = fondo;
    }

    /**
     * Los botones de la escena.
     */
    protected JButton[] botones;

    /**
     * El botón elegido por el usuario.
     */
    protected int elegido = -1;

    /**
     * El ancho de la escena.
     */
    protected int width = 1024;

    /**
     * La altura de la escena.
     */
    protected int height = 576;

    /**
     * Indica si se debe continuar la interacción en la escena.
     */
    protected boolean seguir = false;

    /**
     * Método abstracto para agregar elementos a la escena.
     */
    public abstract void addElementos();

    /**
     * Método abstracto para actualizar la escena según la fase actual.
     *
     * @param fase La fase actual de la escena.
     */
    public abstract void actualizarEscena(int fase);

    /**
     * Método abstracto para mostrar un mensaje en la escena.
     *
     * @param mensaje El mensaje a mostrar.
     */
    public abstract void mostrarMensaje(String mensaje);

    /**
     * Método abstracto para actualizar la interfaz de la escena.
     */
    public abstract void actualizarInterfaz();

    /**
     * Método abstracto para deshabilitar los botones de la escena.
     */
    public abstract void deshabilitarBotones();

    /**
     * Método abstracto para habilitar los botones de la escena.
     */
    public abstract void habilitarBotones();
}
