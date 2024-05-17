package UI.Interfaces;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Jefes.Lobo;
import Modelo.Enemigos.Jefes.Wendigo;
import Modelo.Enums.Estados;
import Modelo.Enums.Iconos;
import Modelo.Eventos.Hoguera;
import Modelo.Eventos.RecompensaEspecial;
import Modelo.Jugador.Mago;
import UI.Elementos.Contenedor;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Interfaz extends JFrame{
    public Interfaz() {
        setTitle("Juego");
        createAndShowGUI();
    }

    private int width = 1024;
    private int height = 576;
    Contenedor contenedorActual;
    String fondo;

    private void createAndShowGUI() {
        fondo = Iconos.NIVEL1.getRutaIcono();
        contenedorActual = new MenuPrincipal(fondo);
        contenedorActual.addElementos();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(contenedorActual, BorderLayout.CENTER);

        setSize(width,height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cambiarEscena(Contenedor contenedor) {
        getContentPane().removeAll();
        contenedorActual = contenedor;
        contenedorActual.addElementos();
        getContentPane().add(contenedorActual, BorderLayout.CENTER);
        pack();
        setSize(width,height);
    }

    public boolean seguir() {
        return contenedorActual.isSeguir();
    }

    public void setSeguir() {
        contenedorActual.setSeguir(false);
    }
    public void deshabilitarBotones() {
        contenedorActual.deshabilitarBotones();
    }
    public void habilitarBotones() {
        contenedorActual.habilitarBotones();
    }

    public int botonPulsado() {
        return contenedorActual.getElegido();
    }

    public void reiniciarPulsado() {
        contenedorActual.setElegido(-1);
    contenedorActual.setSeguir(false);
    }

    public void imprimirMensaje(String mensaje) {
        contenedorActual.mostrarMensaje(mensaje);
    }
    public void actualizar() {
        contenedorActual.actualizarInterfaz();
    }

    public void cambiarFase(int fase) {
        contenedorActual.actualizarEscena(fase);
        contenedorActual.actualizarInterfaz();
    }
}
