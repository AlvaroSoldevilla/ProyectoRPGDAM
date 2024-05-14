package UI.Interfaces;

import UI.Elementos.Contenedor;
import UI.Elementos.ImagenDeFondo;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Interfaz extends JFrame{
    public Interfaz() {
        setTitle("Juego");
        createAndShowGUI();
    }

    private int width = 660;
    private int height = 450;
    int botonPulsado = -1;
    Contenedor contenedorActual;
    ImagenDeFondo fondo;

    private void createAndShowGUI() {
        contenedorActual = new MenuPrincipal("Imagenes/Fondos/Fondo1.png");
        contenedorActual.addElementos();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(contenedorActual, BorderLayout.CENTER);
        pack();

        setSize(width,height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cambiarEscena(Contenedor contenedor) {
        getContentPane().removeAll();
        contenedorActual = contenedor;
        contenedorActual.addElementos();
        getContentPane().add(contenedorActual, BorderLayout.CENTER);
        pack();
        setSize(width,height);
    }

    public int botonPulsado() {
        return contenedorActual.getElegido();
    }

    public void setBotonPulsado(int botonPulsado) {
        this.botonPulsado = botonPulsado;
        contenedorActual.setElegido(-1);
    }

    public void imprimirMensaje(String mensaje) {

    }
}
