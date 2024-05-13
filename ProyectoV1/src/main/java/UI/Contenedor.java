package UI;

import UI.Interfaces.ImagenDeFondo;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public abstract class Contenedor extends JPanel{

    protected ImagenDeFondo fondo;
    protected JButton[] botones;
    int elegido;

    public void cargar() {
        addElementos();
    }

    public abstract void addElementos();
}
