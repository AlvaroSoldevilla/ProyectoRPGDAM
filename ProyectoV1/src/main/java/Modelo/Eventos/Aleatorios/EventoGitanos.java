package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enums.Iconos;
import Modelo.Eventos.Aleatorio;
import Modelo.Eventos.Combate;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UICombate;

/**
 * La clase EventoGitanos representa un evento aleatorio en el juego donde el jugador
 * se encuentra con un grupo de gitanos y debe tomar una decisión sobre cómo enfrentar la situación.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class EventoGitanos extends Aleatorio {

    private Enemigo enemigo;
    private int nivel;

    /**
     * Constructor para el evento de encuentro con los gitanos.
     *
     * @param jugador  El jugador que participa en el evento.
     * @param enemigo  El enemigo con el que el jugador puede enfrentarse.
     * @param nivel    El nivel del enemigo.
     * @param interfaz La interfaz del juego.
     */
    public EventoGitanos(Jugador jugador, Enemigo enemigo, int nivel, Interfaz interfaz) {
        super(interfaz);
        titulo = "Un encuentro desafortunado";
        texto = "Illo dame todo tu dinero";
        opciones = new String[]{"Dar todo tu dinero", "Dar 5 de oro", "Negarte y correr", "Enfrentarlos"};
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.nivel = nivel;
    }

    /**
     * Comienza el evento, esperando la acción del jugador y actuando en consecuencia.
     */
    @Override
    public void empezarEvento() {
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
                setTexto("Les diste todo tu oro y saliste ileso pero arruinado.");
                jugador.setOro(0);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 1:
                setTexto("Les diste pena y se fueron sin dudar que podías tener más oro.");
                jugador.setOro(jugador.getOro() - 5);
                if (jugador.getOro() < 0) {
                    jugador.setOro(0);
                }
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 2:
                setTexto("Saliste corriendo, uno de ellos te alcanzó por poco y te golpeó con su navaja, perdiste 6 de vida.");
                jugador.setSalud(jugador.getSalud() - 6);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 3:
                setTexto("Decidiste enfrentarlos.");
                opciones = new String[]{"Luchar"};
                interfaz.actualizar();
                esperar();
                interfaz.reiniciarPulsado();
                interfaz.cambiarEscena(new UICombate(Iconos.NIVEL2.getRutaIcono(), jugador, new Enemigo[]{enemigo}));
                Combate combate = new Combate(jugador, enemigo, nivel, interfaz);
                combate.empezarEvento();
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
