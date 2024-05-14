package UI.Elementos;

import UI.Elementos.ImagenDeFondo;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public abstract class Contenedor extends ImagenDeFondo{

    private Image imagenDeFondo;

    private Contenedor(){}
    public Contenedor(String nombreRutaImagen) {
        imagenDeFondo = new ImageIcon(nombreRutaImagen).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenDeFondo, 0, 0, getWidth(), getHeight(), this);
    }
    protected JButton[] botones;
    protected int elegido = -1;



    public void cargar() {
        addElementos();
    }

    public abstract void addElementos();
    public abstract void actualizarEscena(int fase);
}
