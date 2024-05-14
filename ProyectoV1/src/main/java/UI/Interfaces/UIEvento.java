package UI.Interfaces;

import UI.Elementos.Contenedor;
import UI.Elementos.ImagenDeFondo;
import lombok.Data;

import java.awt.*;

@Data
public class UIEvento extends Contenedor {

    public UIEvento(String nombreRutaImagen) {
        super(nombreRutaImagen);
        setLayout(new BorderLayout());
    }

    @Override
    public void addElementos() {

    }

    @Override
    public void actualizarEscena(int fase) {

    }
}
