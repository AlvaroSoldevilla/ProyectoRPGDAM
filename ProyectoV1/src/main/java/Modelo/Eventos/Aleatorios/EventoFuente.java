package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

/**
 * La clase EventoFuente representa un evento aleatorio en el juego donde el jugador
 * encuentra una fuente ancestral y debe decidir si beber de ella o ignorarla.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class EventoFuente extends Aleatorio {

    /**
     * Constructor para el evento de la fuente ancestral.
     *
     * @param jugador  El jugador que participa en el evento.
     * @param interfaz La interfaz del juego.
     */
    public EventoFuente(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "¿Fuente ancestral?";
        texto = "Ante ti se alza una fuente desgastada por el paso de los siglos.\n" +
                " De su centro emana un flujo constante de agua transparente.\n" +
                " Desconoces si este agua es potable.";
        opciones = new String[]{"Ignorar la fuente", "Beber de la fuente"};
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
                setTexto("Decidiste ignorar esa fuente, no hubo consecuencias.");
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 1:
                setTexto("Bebiste del agua de la fuente, sientes como purifica tu interior. Tu salud se recuperó 10 puntos.");
                jugador.curarVida(10);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
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
