package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enums.Iconos;
import Modelo.Eventos.Aleatorio;
import Modelo.Eventos.Combate;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UICombate;

/**
 * La clase EventoNerd representa un evento aleatorio en el juego donde el jugador
 * se encuentra con un niño lloriqueando y debe tomar decisiones que afectarán el
 * desarrollo del juego.
 *
 * @autor Álvaro Soldevilla
 * @autor Diego Gonzalez
 */
public class EventoNerd extends Aleatorio {

    /**
     * Constructor para el evento del encuentro con el niño lloriqueando.
     *
     * @param jugador  El jugador que participa en el evento.
     * @param enemigo  El enemigo que puede aparecer como consecuencia de las acciones del jugador.
     * @param nivel    El nivel del enemigo.
     * @param interfaz La interfaz del juego.
     */
    public EventoNerd(Jugador jugador, Enemigo enemigo, int nivel, Interfaz interfaz) {
        super(interfaz);
        titulo = "Niño lloriqueando";
        texto = "En tu camino te topas con un chico con gafas que llora. ¿Qué deberías hacer?";
        opciones = new String[]{"Preguntarle qué le pasa", "Robarle", "Ignorarlo"};
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.nivel = nivel;
    }

    Enemigo enemigo;
    int nivel;

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
                setTexto("El chico dice que unos chicos se burlaron de él por su aspecto y por cómo habla");
                opciones = new String[]{"Consolarlo", "Decirle que con razón se burlaron de él, menudas pintas...", "Decirle que te da igual y marcharte"};

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
                        setTexto("El chico, al secarse las lágrimas, te ofrece una figura de colección como agradecimiento por consolarlo. Parece que podría venderse por un buen precio. Ganas 25 de oro");
                        jugador.setOro(jugador.getOro() + 25);
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        esperar();
                        break;
                    case 1:
                        setTexto("El chico se ofende y llora más diciendo: \"De hecho *snif* solo tengo 6 dioptrías en cada ojo, es algo normal.\" ");
                        opciones = new String[]{"\"Tienes razón, lo siento.\"", "\"Estás cegato, chaval.\""};

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
                                setTexto("El chico se marcha con los ojos rojos a causa de las lágrimas");
                                opciones = new String[]{"Seguir"};
                                interfaz.actualizar();
                                esperar();
                                break;
                            case 1:
                                setTexto("El chico se enfada y te lanza un cuchillo que tenía escondido pero falla dándole a un monstruo, esto pinta feo...");
                                opciones = new String[]{"Combatir"};
                                interfaz.actualizar();
                                esperar();
                                Combate c = new Combate(jugador, enemigo, nivel, interfaz);
                                interfaz.cambiarEscena(new UICombate(Iconos.NIVEL2.getRutaIcono(),jugador,new Enemigo[]{enemigo}));
                                c.empezarEvento();
                                break;
                        }
                        break;

                    case 2:
                        setTexto("Te marchas dejando atrás al niño que sigue llorando.");
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        esperar();
                        break;
                }
                break;
            case 1:
                setTexto("Eres un ser avaricioso y detestable pero el chico tenía bastante oro. Consigues 20 de oro");
                jugador.setOro(jugador.getOro() + 20);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 2:
                setTexto("Pasaste de largo");
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
