package UI.Interfaces;

import Modelo.Bases.Evento;
import UI.Contenedor;

import javax.swing.*;
import java.awt.*;

public class ElegirCamino extends Contenedor {
    public ElegirCamino(Evento[] eventos, int numEventos) {
        fondo = new ImagenDeFondo("Imagenes/Fondos/Fondo1.png");
        this.eventos = eventos;
        botones = new JButton[numEventos];
        this.numEventos = numEventos;
    }
    Evento[] eventos;
    int numEventos;

    @Override
    public void addElementos() {
        setLayout(new BorderLayout()); // Establece el BorderLayout para el contenedor

        // Agrega la imagen de fondo al centro del contenedor
        add(fondo, BorderLayout.CENTER);

        // Crea un panel para los botones y configura su diseño
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(numEventos, 1));

        // Crea los botones y agrégalos al panel de botones
        botones[0] = new JButton("Camino 1");
        botones[1] = new JButton("Camino 2");
        //botones[2] = new JButton("Camino 3");

        panelBotones.add(botones[0]);
        panelBotones.add(botones[1]);
        //panelBotones.add(botones[2]);

        // Agrega el panel de botones al lado derecho del contenedor
        add(panelBotones, BorderLayout.EAST);

        setVisible(true);
    }
}

