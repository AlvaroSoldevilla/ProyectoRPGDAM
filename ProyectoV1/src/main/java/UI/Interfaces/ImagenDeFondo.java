package UI.Interfaces;

import javax.swing.*;
import java.awt.*;

public class ImagenDeFondo extends JPanel {
    private Image imagenDeFondo;

    public ImagenDeFondo(String nombreRutaImagen) {
        imagenDeFondo = new ImageIcon(nombreRutaImagen).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenDeFondo, 0, 0, getWidth(), getHeight(), this);
    }
}

