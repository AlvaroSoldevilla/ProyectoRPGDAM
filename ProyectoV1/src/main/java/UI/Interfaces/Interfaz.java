package UI.Interfaces;

import Modelo.Enums.Iconos;
import UI.Elementos.Escena;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

/**
 * La clase Interfaz representa la interfaz gráfica principal del juego.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public class Interfaz extends JFrame {

    private int width = 1024;
    private int height = 576;
    Escena escenaActual;
    String fondo;

    /**
     * Constructor que inicializa la interfaz del juego.
     */
    public Interfaz() {
        setTitle("Juego");
        createAndShowGUI();
    }

    /**
     * Crea y muestra la interfaz gráfica.
     */
    private void createAndShowGUI() {
        fondo = Iconos.NIVEL1.getRutaIcono();
        escenaActual = new MenuPrincipal(fondo);
        escenaActual.addElementos();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(escenaActual, BorderLayout.CENTER);

        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Cambia la escena actual a una nueva escena.
     *
     * @param escena La nueva escena a mostrar.
     */
    public void cambiarEscena(Escena escena) {
        getContentPane().removeAll();
        escenaActual = escena;
        escenaActual.addElementos();
        getContentPane().add(escenaActual, BorderLayout.CENTER);
        pack();
        setSize(width, height);
    }

    /**
     * Deshabilita los botones de la escena actual.
     */
    public void deshabilitarBotones() {
        escenaActual.deshabilitarBotones();
    }

    /**
     * Habilita los botones de la escena actual.
     */
    public void habilitarBotones() {
        escenaActual.habilitarBotones();
    }

    /**
     * Obtiene el índice del botón pulsado en la escena actual.
     *
     * @return Devuelve el índice del botón pulsado.
     */
    public int botonPulsado() {
        return escenaActual.getElegido();
    }

    /**
     * Reinicia el estado del botón pulsado en la escena actual.
     */
    public void reiniciarPulsado() {
        escenaActual.setElegido(-1);
    }

    /**
     * Muestra un mensaje en la escena actual.
     *
     * @param mensaje El mensaje a mostrar.
     */
    public void imprimirMensaje(String mensaje) {
        escenaActual.mostrarMensaje(mensaje);
    }

    /**
     * Actualiza la interfaz de la escena actual.
     */
    public void actualizar() {
        escenaActual.actualizarInterfaz();
    }

    /**
     * Cambia la fase de la escena actual.
     *
     * @param fase La fase a establecer en la escena actual.
     */
    public void cambiarFase(int fase) {
        escenaActual.actualizarEscena(fase);
        escenaActual.actualizarInterfaz();
    }
}