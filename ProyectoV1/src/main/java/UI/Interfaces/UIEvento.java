package UI.Interfaces;

import Modelo.Bases.Evento;
import UI.Elementos.Contenedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIEvento extends Contenedor {
    private Evento evento;
    private JButton[] botones;
    private String[] opciones;

    public UIEvento(String imagenDeFondo, Evento evento) {
        super(imagenDeFondo);
        this.evento = evento;
        setLayout(null); // Usamos null para posicionar los componentes manualmente
    }

    public void addElementos() {
        botones = new JButton[evento.getOpciones().length];
        // Fondo
        JLabel backgroundLabel = new JLabel(new ImageIcon(imagenDeFondo));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        // Panel del evento
        JPanel eventoPanel = new JPanel();
        eventoPanel.setBounds(50, 100, getWidth() - 100, getHeight() - 150);
        eventoPanel.setBackground(new Color(255, 255, 255, 128)); // Fondo semitransparente
        eventoPanel.setLayout(null);

        //Titulo del evento
        JLabel tituloLabel = new JLabel(evento.getTitulo());
        tituloLabel.setBounds(20, 20, eventoPanel.getWidth() - 40, 30);
        eventoPanel.add(tituloLabel);

        //Texto
        JLabel textoLabel = new JLabel(evento.getTexto());
        textoLabel.setBounds(20, 60, eventoPanel.getWidth() - 40, 100);
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
            botones[i].setBounds(startX + i * (botonWidth + espacioEntreBotones), opcionY, botonWidth, botonHeight);
            eventoPanel.add(botones[i]);
            int finalI = i;
            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aquí puedes manejar la lógica cuando se selecciona una opción
                    System.out.println(((JButton) e.getSource()).getText() + " seleccionada");
                    elegido = finalI;
                }
            });
        }

        backgroundLabel.add(eventoPanel);
    }

    @Override
    public void actualizarEscena(int fase) {

    }

    @Override
    public void mostrarMensaje(String mensaje) {
        // Implementar si es necesario
    }

    @Override
    public void actualizarInterfaz() {
        this.removeAll();
        addElementos();
        this.revalidate();
        this.repaint();
    }
}


