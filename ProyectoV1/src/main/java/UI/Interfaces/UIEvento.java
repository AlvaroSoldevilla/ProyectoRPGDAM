package UI.Interfaces;

import Modelo.Bases.Evento;
import UI.Elementos.Escena;
import UI.Elementos.PanelEstadisticas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase UIEvento representa una escena donde se muestra un evento específico y sus opciones al jugador.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class UIEvento extends Escena {

    /**
     * El evento actual
     */
    private Evento evento;
    /**
     * Las opciones del evento
     */
    private String[] opciones;

    /**
     * Constructor que inicializa la escena del evento con la imagen de fondo y el evento proporcionado.
     *
     * @param imagenDeFondo La imagen de fondo de la escena.
     * @param evento        El evento a mostrar en la escena.
     */
    public UIEvento(String imagenDeFondo, Evento evento) {
        super(imagenDeFondo);
        this.evento = evento;
        setLayout(null);
    }

    /**
     * Agrega los elementos gráficos a la escena del evento.
     */
    public void addElementos() {
        botones = new JButton[evento.getOpciones().length];

        // Estadísticas
        JPanel panelEstadisticas = new PanelEstadisticas(evento.getJugador());
        panelEstadisticas.setBounds(0, 0, getWidth(), 50);
        panelEstadisticas.setBackground(new Color(255, 255, 255, 150)); // Fondo semitransparente
        panelEstadisticas.setLayout(null);
        add(panelEstadisticas);

        // Fondo
        JLabel fondo = new JLabel(new ImageIcon(imagenDeFondo));
        fondo.setBounds(0, 0, getWidth(), getHeight());
        add(fondo);

        // Panel del evento
        JPanel eventoPanel = new JPanel();
        eventoPanel.setBounds(50, 100, getWidth() - 100, getHeight() - 150);
        eventoPanel.setBackground(new Color(255, 255, 255, 200)); // Fondo semitransparente
        eventoPanel.setLayout(null);

        // Título del evento
        JLabel tituloLabel = new JLabel(evento.getTitulo());
        tituloLabel.setBounds(0, 20, eventoPanel.getWidth() - 40, 30);
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        eventoPanel.add(tituloLabel);

        // Texto
        JLabel textoLabel = new JLabel(evento.getTexto());
        textoLabel.setBounds(0, 60, eventoPanel.getWidth() - 40, 100);
        textoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        eventoPanel.add(textoLabel);

        int opcionY = eventoPanel.getHeight() - 100;
        int botonWidth = 200;
        int botonHeight = 30;
        int espacioEntreBotones = 40;
        int totalWidth = botonWidth * botones.length + espacioEntreBotones * (botones.length - 1);
        int startX = (eventoPanel.getWidth() - totalWidth) / 2;

        opciones = evento.getOpciones();

        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton(opciones[i]);
            if (botones.length > 4) {
                botonWidth = 150;
                startX = 10;
            }
            botones[i].setBounds(startX + i * (botonWidth + espacioEntreBotones), opcionY, botonWidth, botonHeight);
            eventoPanel.add(botones[i]);
            int finalI = i;
            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    elegido = finalI;
                }
            });
        }

        fondo.add(eventoPanel);
    }

    @Override
    public void actualizarEscena(int fase) {}

    @Override
    public void mostrarMensaje(String mensaje) {}

    @Override
    public void actualizarInterfaz() {
        this.removeAll();
        addElementos();
        this.revalidate();
        this.repaint();
    }

    @Override
    public void deshabilitarBotones() {}

    @Override
    public void habilitarBotones() {}
}
