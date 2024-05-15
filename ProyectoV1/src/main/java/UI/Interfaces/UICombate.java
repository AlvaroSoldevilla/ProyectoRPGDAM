package UI.Interfaces;

import Modelo.Bases.Evento;
import UI.Elementos.Contenedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UICombate extends Contenedor {
    private Evento evento;
    private JButton[] botones;

    public UICombate(String imagenDeFondo, Evento evento) {
        super(imagenDeFondo);
        this.evento = evento;
        this.botones = new JButton[4]; // Asumimos que hay cuatro opciones
        setLayout(null); // Usamos null para posicionar los componentes manualmente
        addElementos();
    }

    public void addElementos() {
        // Fondo
        JLabel backgroundLabel = new JLabel(new ImageIcon(imagenDeFondo));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        // Caja de estadísticas
        JPanel estadisticasPanel = new JPanel();
        estadisticasPanel.setBounds(0, 0, getWidth(), 50);
        estadisticasPanel.setBackground(new Color(128, 128, 128, 128)); // Fondo semitransparente
        estadisticasPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel estadisticasLabel = new JLabel("Aquí irían ciertas estadísticas");
        estadisticasPanel.add(estadisticasLabel);
        backgroundLabel.add(estadisticasPanel);

        // Estados del jugador
        JLabel jugadorLabel = new JLabel(new ImageIcon("ruta/a/imagenJugador.png")); // Reemplaza con la ruta correcta
        jugadorLabel.setBounds(50, 100, 150, 198);
        backgroundLabel.add(jugadorLabel);

        JLabel estadosJugadorLabel = new JLabel("<html>Estados del Jugador<br>Estadísticas del Jugador</html>");
        estadosJugadorLabel.setBounds(50, 310, 150, 100);
        backgroundLabel.add(estadosJugadorLabel);

        // Estados del enemigo
        JLabel enemigoLabel = new JLabel(new ImageIcon("ruta/a/imagenEnemigo.png")); // Reemplaza con la ruta correcta
        enemigoLabel.setBounds(getWidth() - 200, 100, 150, 198);
        backgroundLabel.add(enemigoLabel);

        JLabel estadosEnemigoLabel = new JLabel("<html>Estados del enemigo<br>Estadísticas del enemigo</html>");
        estadosEnemigoLabel.setBounds(getWidth() - 200, 310, 150, 100);
        backgroundLabel.add(estadosEnemigoLabel);

        // Texto de combate
        JPanel textoCombatePanel = new JPanel();
        textoCombatePanel.setBounds(0, getHeight() - 130, getWidth() - 400, 80); // Sin margen izquierdo ni inferior
        textoCombatePanel.setBackground(Color.WHITE);
        textoCombatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel textoCombateLabel = new JLabel("Aquí va el texto del combate");
        textoCombatePanel.add(textoCombateLabel);
        backgroundLabel.add(textoCombatePanel);

        // Opciones de combate
        int opcionY = getHeight() - 130;
        int botonWidth = 150;
        int botonHeight = 30;
        int espacioEntreBotones = 20;
        int totalWidth = botonWidth * 2 + espacioEntreBotones * 1;
        int startX = getWidth() - 350; // Mover opciones más a la derecha

        String[] opciones = {"Opción 1", "Opción 2", "Opción 3", "Opción 4"};
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton(opciones[i]);
            if (i < 2) {
                botones[i].setBounds(startX + i * (botonWidth + espacioEntreBotones), opcionY, botonWidth, botonHeight);
            } else {
                botones[i].setBounds(startX + (i - 2) * (botonWidth + espacioEntreBotones), opcionY + 40, botonWidth, botonHeight);
            }
            backgroundLabel.add(botones[i]);
            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aquí puedes manejar la lógica cuando se selecciona una opción
                    System.out.println(((JButton) e.getSource()).getText() + " seleccionada");
                }
            });
        }
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