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

    public UIEvento(String imagenDeFondo, Evento evento) {
        super(imagenDeFondo);
        this.evento = evento;
        this.botones = new JButton[3]; // Asumimos que hay tres opciones
        setLayout(null); // Usamos null para posicionar los componentes manualmente
    }

    public void addElementos() {
        // Fondo
        JLabel backgroundLabel = new JLabel(new ImageIcon(imagenDeFondo));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        // Caja de estadísticas
        JPanel estadisticasPanel = new JPanel();
        estadisticasPanel.setBounds(0, 0, getWidth(), 50);
        estadisticasPanel.setBackground(new Color(255, 255, 255, 255)); // Fondo semitransparente
        estadisticasPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel estadisticasLabel = new JLabel("Aquí irían ciertas estadísticas");
        estadisticasPanel.add(estadisticasLabel);
        backgroundLabel.add(estadisticasPanel);

        // Panel del evento
        JPanel eventoPanel = new JPanel();
        eventoPanel.setBounds(50, 100, getWidth() - 100, getHeight() - 150);
        eventoPanel.setBackground(new Color(255, 255, 255, 128)); // Fondo semitransparente
        eventoPanel.setLayout(null);

        JLabel tituloLabel = new JLabel(evento.getTitulo());
        tituloLabel.setBounds(20, 20, eventoPanel.getWidth() - 40, 30);
        eventoPanel.add(tituloLabel);

        JLabel textoLabel = new JLabel("<html>" + evento.getTexto().replace("\n", "<br>") + "</html>");
        textoLabel.setBounds(20, 60, eventoPanel.getWidth() - 40, 100);
        eventoPanel.add(textoLabel);

        int opcionY = eventoPanel.getHeight() - 100;
        int botonWidth = 200;
        int botonHeight = 30;
        int espacioEntreBotones = 40;
        int totalWidth = botonWidth * botones.length + espacioEntreBotones * (botones.length - 1);
        int startX = (eventoPanel.getWidth() - totalWidth) / 2;

        String[] opciones = {"Opción 1", "Opción 2", "Opción 3"};
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton(opciones[i]);
            botones[i].setBounds(startX + i * (botonWidth + espacioEntreBotones), opcionY, botonWidth, botonHeight);
            eventoPanel.add(botones[i]);
            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aquí puedes manejar la lógica cuando se selecciona una opción
                    System.out.println(((JButton) e.getSource()).getText() + " seleccionada");
                }
            });
        }

        backgroundLabel.add(eventoPanel);
    }

    @Override
    public void actualizarEscena(int fase) {
        // Implementar si es necesario
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        // Implementar si es necesario
    }
}


