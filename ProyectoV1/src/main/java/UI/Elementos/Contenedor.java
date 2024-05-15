package UI.Elementos;

import lombok.Data;

import javax.swing.*;

@Data
public abstract class Contenedor extends JPanel{

    protected String imagenDeFondo;

    private Contenedor(){}
    public Contenedor(String fondo) {
        imagenDeFondo = fondo;
    }
    protected JButton[] botones;
    protected int elegido = -1;
    protected int width = 1024;
    protected int height = 576;


    public abstract void addElementos();
    public abstract void actualizarEscena(int fase);
    public abstract void mostrarMensaje(String mensaje);
}
