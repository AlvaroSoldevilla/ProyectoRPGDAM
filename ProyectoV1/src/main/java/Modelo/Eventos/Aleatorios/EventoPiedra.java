package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

/**
 * La clase EventoPiedra representa un evento aleatorio en el juego donde el jugador
 * se encuentra con una piedra en el camino y debe tomar decisiones que afectarán el
 * desarrollo del juego.
 *
 * @autor Álvaro Soldevilla
 * @autor Diego Gonzalez
 */
public class EventoPiedra extends Aleatorio {

    /**
     * Constructor para el evento de la piedra en el camino.
     *
     * @param jugador  El jugador que participa en el evento.
     * @param interfaz La interfaz del juego.
     */
    public EventoPiedra(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Dualidad del hombre";
        texto = "Hay una piedra en el camino. ¿Qué quieres hacer?";
        opciones = new String[]{"Patearla", "Tirarla al río", "Pasar de largo"};
        this.jugador = jugador;
    }

    /**
     * Comienza el evento, esperando la acción del jugador y actuando en consecuencia.
     */
    @Override
    public void empezarEvento() {
        interfaz.actualizar();
        while (interfaz.botonPulsado() == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (interfaz.botonPulsado() != -1) {
                opcion = interfaz.botonPulsado();
            }
        }
        interfaz.reiniciarPulsado();
        switch (opcion) {
            case 0:
                setTexto("La piedra estaba muy dura y te hiciste daño. Pierdes 3 de vida.");
                interfaz.actualizar();
                opciones = new String[]{"Seguir"};
                jugador.setSalud(jugador.getSalud() - 3);
                esperar();
                break;
            case 1:
                setTexto("Lanzar la piedra al río te hace sentirte bien contigo mismo. Aumenta tu daño en 5");
                jugador.setDmg(jugador.getDmg() + 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 2:
                setTexto("Continúas tu camino");
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
        }
    }

    /**
     * Termina el evento. Este método puede ser sobreescrito en caso de necesitar
     * lógica adicional al finalizar el evento.
     */
    @Override
    public void terminarEvento() {}

    /**
     * Espera a que el jugador pulse un botón en la interfaz.
     */
    private void esperar() {
        while (interfaz.botonPulsado() == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
