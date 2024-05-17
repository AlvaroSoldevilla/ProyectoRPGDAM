package UI.Interfaces;

import Modelo.Enums.Iconos;
import UI.Elementos.Contenedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPrincipal extends Contenedor {

    public MenuPrincipal(String nombreRutaImagen) {
        super(nombreRutaImagen);
        setLayout(new BorderLayout());
    }

    @Override
    public void addElementos() {
        JLabel backgroundLabel = new JLabel(new ImageIcon(imagenDeFondo));
        backgroundLabel.setBounds(0, 0, width, height);
        add(backgroundLabel);

        // Personaje 1
        JButton character1Button = new JButton(new ImageIcon(Iconos.CABALLERO.getRutaIcono()));
        character1Button.setBounds(100, 150, 150, 198); // Ajusta las posiciones y tamaños según sea necesario
        character1Button.setBorderPainted(false);
        character1Button.setContentAreaFilled(false);
        character1Button.setFocusPainted(false);
        character1Button.setOpaque(false);
        backgroundLabel.add(character1Button);

        // Personaje 2
        JButton character2Button = new JButton(new ImageIcon(Iconos.MAGO.getRutaIcono()));
        character2Button.setBounds(430, 150, 150, 198);
        character2Button.setBorderPainted(false);
        character2Button.setContentAreaFilled(false);
        character2Button.setFocusPainted(false);
        character2Button.setOpaque(false);
        backgroundLabel.add(character2Button);

        // Personaje 3
        JButton character3Button = new JButton(new ImageIcon(Iconos.ASESINO.getRutaIcono()));
        character3Button.setBounds(750, 150, 150, 198);
        character3Button.setBorderPainted(false);
        character3Button.setContentAreaFilled(false);
        character3Button.setFocusPainted(false);
        character3Button.setOpaque(false);
        backgroundLabel.add(character3Button);

        // Añadir acción a los botones
        character1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para el personaje 1
                System.out.println("Caballero seleccionado");
                elegido = 1;
            }
        });

        character2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para el personaje 2
                System.out.println("Mago seleccionado");
                elegido = 2;
            }
        });

        character3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para el personaje 3
                System.out.println("Asesino seleccionado");
                elegido = 3;
            }
        });
    }

    @Override
    public void actualizarEscena(int fase) {}

    @Override
    public void mostrarMensaje(String mensaje) {

    }

    @Override
    public void actualizarInterfaz() {

    }

    @Override
    public void deshabilitarBotones() {

    }

    @Override
    public void habilitarBotones() {

    }
}

