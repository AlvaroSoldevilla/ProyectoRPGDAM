package UI.Interfaces;

import UI.Contenedor;
import lombok.Data;

import java.awt.*;

@Data
public class UIEvento extends Contenedor {
    public UIEvento() {
        fondo = new ImagenDeFondo("Imagenes/Fondos/Fondo2.png");
        setLayout(new BorderLayout());
    }
    @Override
    public void addElementos() {

    }
}
