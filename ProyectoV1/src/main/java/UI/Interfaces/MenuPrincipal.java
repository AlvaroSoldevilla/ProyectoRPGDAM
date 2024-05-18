package UI.Interfaces;

import Modelo.Enums.Iconos;
import UI.Elementos.Escena;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase MenuPrincipal representa la escena del menú principal donde el jugador puede seleccionar un personaje.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class MenuPrincipal extends Escena {

    /**
     * Constructor que inicializa la escena del menú principal con la imagen de fondo proporcionada.
     *
     * @param nombreRutaImagen La imagen de fondo de la escena.
     */
    public MenuPrincipal(String nombreRutaImagen) {
        super(nombreRutaImagen);
        setLayout(new BorderLayout());
    }

    /**
     * Agrega los elementos gráficos a la escena del menú principal.
     */
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

        // Botones
        character1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegido = 1;
            }
        });

        character2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegido = 2;
            }
        });

        character3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegido = 3;
            }
        });
    }

    @Override
    public void actualizarEscena(int fase) {}

    @Override
    public void mostrarMensaje(String mensaje) {}

    @Override
    public void actualizarInterfaz() {}

    @Override
    public void deshabilitarBotones() {}

    @Override
    public void habilitarBotones() {}
}
