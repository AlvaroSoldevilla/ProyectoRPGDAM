package UI.Interfaces;

import UI.Elementos.Contenedor;
import UI.Elementos.ImagenDeFondo;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class MenuPrincipal extends Contenedor {

    public MenuPrincipal(String nombreRutaImagen) {
        super(nombreRutaImagen);
        setLayout(new BorderLayout());
    }

    @Override
    public void addElementos() {
        JPanel panelBotones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton boton1 = new JButton("Botón 1");
        JButton boton2 = new JButton("Botón 2");
        JButton boton3 = new JButton("Botón 3");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotones.add(boton1, gbc);

        gbc.gridy = 1;
        panelBotones.add(boton2, gbc);

        gbc.gridy = 2;
        panelBotones.add(boton3, gbc);

        add(panelBotones, BorderLayout.SOUTH);

    }

    @Override
    public void actualizarEscena(int fase) {}
}

