package Modelo.Eventos.Aleatorios;

import Modelo.Equipamiento.Armas.Palo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

/**
 * La clase EventoScammer representa un evento aleatorio en el juego donde el jugador
 * se encuentra con un mago enigmático que ofrece un intercambio de armas, con resultados
 * inciertos.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class EventoScammer extends Aleatorio {

    /**
     * Constructor para el evento del estafador.
     *
     * @param jugador  El jugador que participa en el evento.
     * @param interfaz La interfaz del juego.
     */
    public EventoScammer(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Un intercambio";
        texto = "Un enigmático mago aparece frente ante ti, te ofrece un arma de gran poder a cambio de la tuya. Su fiabilidad es cuestionable...";
        opciones = new String[]{"Aceptar el trato", "No fiarte e ingorarlo"};
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
                setTexto("Tras aceptar el trato, el mago conjura un arma nueva y desaparece junto con la tuya. Sin embargo, lo que te entrega resulta ser un palo, poco útil en comparación.");
                int antigua;
                antigua = jugador.getArmas().indexOf(jugador.getArma());
                jugador.cambiarArma(new Palo());
                jugador.eliminarArma(antigua);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 1:
                setTexto("Decidiste ignorarlo. El mago parece decepcionado y desaparece.");
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
