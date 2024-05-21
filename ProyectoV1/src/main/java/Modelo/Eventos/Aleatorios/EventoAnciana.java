package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

/**
 * La clase EventoAnciana representa un evento aleatorio en el juego donde el jugador
 * encuentra a una anciana y debe decidir cómo actuar.
 *
 * @autor Álvaro Soldevilla
 * @autor Diego Gonzalez
 */
public class EventoAnciana extends Aleatorio {

    /**
     * Constructor para el evento de la anciana.
     *
     * @param jugador  El jugador que participa en el evento.
     * @param interfaz La interfaz del juego.
     */
    public EventoAnciana(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Anciana desamparada";
        texto = "En la distancia, avistas a una anciana tendida en el suelo. Te acercas a ella con paso firme.";
        opciones = new String[]{"Ayudas a la anciana a levantarse", "Pasas de largo"};
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
                setTexto("Ayudaste a la anciana a levantarse, te lo agradece pero te pide que la acompañes hasta su hogar");
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
                        setTexto("Tras acompañarla a su hogar te entrega unas galletas por tu amabilidad. Te curas 8 de vida");
                        jugador.curarVida(8);
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        esperar();
                        break;

                    case 1:
                        setTexto("La anciana se queda extrañada y te observa alejándote, tal vez no pase nadie de buena fe que ayude a la anciana...");
                        jugador.setOro(jugador.getOro() - 5);
                        if (jugador.getOro() < 0) {
                            jugador.setOro(0);
                        }
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        esperar();
                        break;

                    case 2:
                        setTexto("La anciana muestra tristeza pero lo entiende. Se despide de ti");
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        esperar();
                        break;
                }
                break;
            case 1:
                setTexto("Decidiste pasar de largo. La anciana al verte ignorarla te tira una maldición por tu falta de bondad");
                jugador.setMana(jugador.getMana() - 5);
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

