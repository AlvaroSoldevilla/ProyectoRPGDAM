package UI.Interfaces;

import UI.Elementos.Contenedor;
import lombok.Data;

@Data
public class UICombate extends Contenedor {

    public UICombate(String nombreRutaImagen) {
        super(nombreRutaImagen);
    }

    @Override
    public void addElementos() {

    }

    @Override
    public void actualizarEscena(int fase) {

    }
}
