package UI.Interfaces;

import Modelo.Bases.Evento;
import UI.Elementos.BotonOculto;
import UI.Elementos.Contenedor;
import UI.Elementos.ImagenDeFondo;

import javax.swing.*;
import java.awt.*;

public class ElegirCamino extends Contenedor {
    public ElegirCamino(Evento[] eventos, int numEventos,String nombreRutaImagen) {
        super(nombreRutaImagen);
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

        // Crea un panel para los botones y configura su diseño
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(numEventos, 1));

        for (int i = 0; i < eventos.length; i++) {
            botones[i] = new BotonOculto(new ImagenDeFondo(eventos[i].getIcono().getRutaIcono()));
        }

        // Crea los botones y agrégalos al panel de botones

        for (int i = 0; i < botones.length; i++) {
            panelBotones.add(botones[i]);
        }

        // Agrega el panel de botones al lado derecho del contenedor
        add(panelBotones, BorderLayout.EAST);

        setVisible(true);
    }

    @Override
    public void actualizarEscena(int fase) {

    }
}

