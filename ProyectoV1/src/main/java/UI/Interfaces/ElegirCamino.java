package UI.Interfaces;

import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import UI.Elementos.Escena;
import UI.Elementos.PanelEstadisticas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase ElegirCamino representa la escena donde el jugador elige el siguiente evento.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class ElegirCamino extends Escena {

    Jugador jugador;
    Evento[] eventos;
    JButton[] botones;
    int minHeight = 0;

    /**
     * Constructor que inicializa la escena con la imagen de fondo, el jugador y los eventos disponibles.
     *
     * @param imagenDeFondo La imagen de fondo de la escena.
     * @param jugador       El jugador que participa en la escena.
     * @param eventos       Los eventos disponibles en la escena.
     */
    public ElegirCamino(String imagenDeFondo, Jugador jugador, Evento[] eventos) {
        super(imagenDeFondo);
        setLayout(new BorderLayout());
        this.jugador = jugador;
        this.eventos = eventos;
        botones = new JButton[eventos.length];
    }

    /**
     * Agrega los elementos gráficos a la escena.
     */
    @Override
    public void addElementos() {
        JLabel backgroundLabel = new JLabel(new ImageIcon(imagenDeFondo));
        backgroundLabel.setBounds(0, 0, width, height);
        add(backgroundLabel);

        // Barra de estadísticas
        JPanel panelEstadisticas = new PanelEstadisticas(jugador);
        panelEstadisticas.setBounds(0, 0, getWidth(), 50);
        panelEstadisticas.setBackground(new Color(255, 255, 255, 150)); // Fondo semitransparente
        panelEstadisticas.setLayout(null);
        add(panelEstadisticas);

        // Personaje 1
        JButton character1Button = new JButton(new ImageIcon(jugador.getIcono().getRutaIcono()));
        character1Button.setBounds(50, 200, 150, 198);
        character1Button.setBorderPainted(false);
        character1Button.setContentAreaFilled(false);
        character1Button.setFocusPainted(false);
        character1Button.setOpaque(false);
        backgroundLabel.add(character1Button);

        int numCaminos = eventos.length;

        switch (eventos.length) {
            case 1:
                minHeight = 270;
                break;
            case 2:
                minHeight = 200;
                break;
            case 3:
                minHeight = 150;
                break;
        }

        // Mostrar los botones de camino según el número de eventos
        for (int i = 0; i < numCaminos; i++) {
            botones[i] = new JButton(new ImageIcon(eventos[i].getIcono().getRutaIcono())); // Seleccionar la imagen secuencialmente
            botones[i].setBounds(740, minHeight + i * 120, 150, 98); // Ajustar posiciones verticalmente
            botones[i].setBorderPainted(false);
            botones[i].setContentAreaFilled(false);
            botones[i].setFocusPainted(false);
            botones[i].setOpaque(false);
            backgroundLabel.add(botones[i]);

            // Añadir acción al botón del camino
            int finalI = i;
            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    elegido = finalI;
                }
            });
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {}

    @Override
    public void actualizarEscena(int fase) {}

    @Override
    public void actualizarInterfaz() {}

    @Override
    public void deshabilitarBotones() {}

    @Override
    public void habilitarBotones() {}
}