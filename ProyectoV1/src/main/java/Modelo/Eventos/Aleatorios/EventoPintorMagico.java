package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

/**
 * La clase EventoPintorMagico representa un evento aleatorio en el juego donde el jugador
 * se encuentra con un pintor mágico que ofrece pintarlo con colores especiales que otorgan
 * beneficios adicionales al jugador.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class EventoPintorMagico extends Aleatorio {

    /**
     * Constructor para el evento del pintor mágico.
     *
     * @param jugador  El jugador que participa en el evento.
     * @param interfaz La interfaz del juego.
     */
    public EventoPintorMagico(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Pintor Mágico";
        texto = "Encuentras a un pintor plasmando sus pensamientos con pintura mágica en un lienzo. El pintor al verte dice que serías un buen lienzo para su arte.";
        opciones = new String[]{"Pintarte de rojo", "Pintarte de azul", "Pintarte de gris", "Pintarte de verde", "No dejarte pintar"};
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
                setTexto("Ver tu arma pintada de rojo hace que te hierva la sangre. Aumenta el daño en 5");
                jugador.setDmg(jugador.getDmg() + 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 1:
                setTexto("Sientes la pintura azul en tu frente. Sientes más conocimiento. Aumenta el maná máximo en 5");
                jugador.setMaxMana(jugador.getMaxMana() + 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 2:
                setTexto("Tu armadura fue tintada de gris. Te sientes más seguro. Aumenta tu defensa en 5");
                jugador.setDefensa(jugador.getDefensa() + 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 3:
                setTexto("Tu cuello es pintado de verde. Te sientes más vivo. Aumenta la vida máxima en 5");
                jugador.setMaxSalud(jugador.getMaxSalud() + 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 4:
                setTexto("Te negaste a ser pintado.");
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
