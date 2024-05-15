package UI.Interfaces;

import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Enums.Iconos;
import Modelo.Eventos.Aleatorios.EventoFuente;
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
    int botonPulsado = -1;
    Contenedor contenedorActual;

    private void createAndShowGUI() {
        contenedorActual = new MenuPrincipal(Iconos.NIVEL1.getRutaIcono());
        contenedorActual.addElementos();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(contenedorActual, BorderLayout.CENTER);

        setSize(width,height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        Jugador j = new Mago();
        Evento evento = new Hoguera(j,this);
        cambiarEscena(new UICombate(Iconos.NIVEL1.getRutaIcono(),evento));
    }

    public void cambiarEscena(Contenedor contenedor) {
        getContentPane().removeAll();
        contenedorActual = contenedor;
        contenedorActual.addElementos();
        getContentPane().add(contenedorActual, BorderLayout.CENTER);
        pack();
        setSize(width,height);
    }

    public int botonPulsado() {
        return contenedorActual.getElegido();
    }

    public void setBotonPulsado(int botonPulsado) {
        this.botonPulsado = botonPulsado;
        contenedorActual.setElegido(-1);
    }

    public void imprimirMensaje(String mensaje) {
        contenedorActual.mostrarMensaje(mensaje);
    }

    public void CambiarFase(int fase) {
        contenedorActual.actualizarEscena(fase);
    }
}
