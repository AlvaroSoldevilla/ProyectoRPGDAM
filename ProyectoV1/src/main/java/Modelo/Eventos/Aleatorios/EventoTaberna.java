package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

/**
 * La clase EventoTaberna representa un evento aleatorio en el juego donde el jugador
 * se encuentra con una taberna y tiene que tomar decisiones que pueden afectar su salud
 * y su economía.
 *
 * @autor Álvaro Soldevilla
 * @autor Diego Gonzalez
 */
public class EventoTaberna extends Aleatorio {

    /**
     * Constructor para el evento de la taberna.
     *
     * @param jugador  El jugador que participa en el evento.
     * @param interfaz La interfaz del juego.
     */
    public EventoTaberna(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "La taberna";
        texto = "En tu camino hayaste una taberna a los pies de un río. Estás sediento y decides entrar";
        opciones = new String[]{"Sentarte en la barra.", "Ir al lavabo y marcharte."};
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
                setTexto("Te sentaste en la barra y te preguntan qué tomarás.");
                opciones = new String[]{"Pedir agua. Coste: 2 de oro.", "Pedir cerveza. Coste: 5 de oro."};
                interfaz.actualizar();
                // Espera a que el jugador elija qué pedir
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
                        // Si el jugador pide agua
                        if (jugador.getOro() < 2) {
                            setTexto("No tienes oro para pagarlo y te echan de la taberna.");
                            opciones = new String[]{"Seguir"};
                            interfaz.actualizar();
                            esperar();
                            break;
                        } else {
                            setTexto("Te pusieron un vaso de agua. Está bastante fresca. Recuperaste 6 de vida");
                            jugador.setOro(jugador.getOro() - 2);
                            jugador.setSalud(jugador.getSalud() + 6);
                            if (jugador.getSalud() > jugador.getMaxSalud()) {
                                jugador.setSalud(jugador.getMaxSalud());
                            }
                            setTexto("Agradeces el trago de agua y te marchas.");
                            opciones = new String[]{"Seguir"};
                            interfaz.actualizar();
                            esperar();
                            break;
                        }
                    case 1:
                        // Si el jugador pide cerveza
                        if (jugador.getOro() < 5) {
                            setTexto("No tienes oro para pagarlo y te echan de la taberna.");
                            opciones = new String[]{"Seguir"};
                            interfaz.actualizar();
                            esperar();
                            break;
                        } else {
                            setTexto("Te pusieron una jarra de cerveza fría. Está bastante fresca pero te sientes algo mareado.");
                            jugador.setOro(jugador.getOro() - 5);
                            opciones = new String[]{"Pedir asistencia a un grupo sentados al fondo de la taberna.", "Levantarte e irte."};
                            interfaz.actualizar();
                            // Espera a que el jugador elija qué hacer después de beber la cerveza
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
                                    // Si el jugador pide asistencia al grupo
                                    setTexto("Te desmayas frente al grupo al terminar de hablar. El grupo al que pediste asistencia era una banda de forajidos. Despiertas fuera de la taberna sin oro.");
                                    jugador.setOro(0);
                                    opciones = new String[]{"Seguir"};
                                    interfaz.actualizar();
                                    esperar();
                                    break;
                                case 1:
                                    // Si el jugador se levanta e irse
                                    setTexto("Al poco tiempo de salir de la taberna caiste al río. Tenía poca profundidad y te caiste sobre una roca pero al menos te sirvió para espabilarte. Pierdes 5 de vida.");
                                    jugador.setSalud(jugador.getSalud() - 5);
                                    opciones = new String[]{"Seguir"};
                                    interfaz.actualizar();
                                    esperar();
                                    break;
                            }
                            break;
                        }
                }
                break;
            case 1:
                // Si el jugador va al lavabo y luego se marcha
                setTexto("Bebiste agua en el baño de la taberna. El agua estaba sucia. Perdiste 2 de vida.");
                jugador.setSalud(jugador.getSalud() - 2);
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
