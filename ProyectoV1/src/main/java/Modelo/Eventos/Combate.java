package Modelo.Eventos;

import Modelo.Equipamiento.Armas.DagaCritica;
import Modelo.Equipamiento.Armas.DagaDoble;
import Modelo.Equipamiento.Armas.DagaRoboVida;
import Modelo.Bases.*;
import Modelo.Enemigos.Jefes.DragonFase1;
import Modelo.Enemigos.Jefes.DragonFase2;
import Modelo.Enemigos.Jefes.Lobo;
import Modelo.Enemigos.Jefes.Wendigo;
import Modelo.Jugador.Asesino;
import Modelo.Enums.Estados;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import lombok.Data;

import java.util.HashMap;
import java.util.Random;

/**
 * La clase Combate representa un combate en el juego.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public class Combate extends Evento {

    /**
     * Constrictor para los combates normales.
     *
     * @param jugador  El jugador que participa en el combate.
     * @param enemigo  El enemigo que participa en el combate.
     * @param nivel    El nivel del combate.
     * @param interfaz La interfaz del juego.
     */
    public Combate(Jugador jugador, Enemigo enemigo, int nivel, Interfaz interfaz) {
        super(interfaz);
        titulo = "Combate";
        this.enemigo = enemigo;
        this.jugador = jugador;
        this.nivel = nivel;
        icono = Iconos.COMBATE;
    }

    /**
     * Constrictor para los combates con jefes.
     *
     * @param jugador  El jugador que participa en el combate.
     * @param enemigo  El enemigo que participa en el combate.
     * @param interfaz La interfaz del juego.
     */
    public Combate(Jugador jugador, Enemigo enemigo, Interfaz interfaz) {
        super(interfaz);
        titulo = "Combate";
        this.enemigo = enemigo;
        this.jugador = jugador;
        if (enemigo instanceof Lobo) {
            nivel = 3;
        }else if (enemigo instanceof Wendigo) {
            nivel = 5;
        }else if (enemigo instanceof DragonFase2) {
            nivel = 7;
        }
    }

    /**
     * Nivel del combate, determinará la recompensa
     */
    int nivel = -1;

    /**
     * Variable para determinar si el jugador está haciendo una finta.
     */
    boolean fintaJugador = false;

    /**
     * El jugador que participa en el combate
     */
    Jugador jugador;

    /**
     * El enemigo que participa en el combate
     */
    Enemigo enemigo;

    /**
     * Generador de numeros aleatorios
     */
    Random rng = new Random();

    /**
     * Determina la acción del jugador
     * <p>1-Ataque normal
     * <p>2-Ataque especial
     * <p>3-Bloquear
     * <p>4-Finta
     *
     * @param codAccion La acción que va a realizar el jugador
     */
    public void accionJugador(int codAccion) {
        boolean accion = false;
        while (!accion) {
            switch (codAccion) {
                case 1:
                    if (!atacar(enemigo, jugador)) {
                        interfaz.imprimirMensaje("El ataque ha fallado");
                        esperar(1000);
                    } else {
                        interfaz.imprimirMensaje("El ataque ha sido un éxito");
                        esperar(1000);
                    }
                    accion = true;
                    break;
                case 2:
                    if (!ataqueEspecial(enemigo, jugador)) {
                        interfaz.imprimirMensaje("El ataque ha fallado");
                        esperar(1000);
                    } else {
                        interfaz.imprimirMensaje("El ataque ha sido un éxito");
                        esperar(1000);
                    }
                    accion = true;
                    break;
                case 3:
                    if (bloquear(jugador)) {
                        interfaz.imprimirMensaje("Te preparas para bloquear el siguiente ataque");
                        esperar(1000);
                    } else {
                        interfaz.imprimirMensaje("Intentas bloquear pero no puedes");
                        esperar(1000);
                    }
                    accion = true;
                    break;
                case 4:
                    interfaz.imprimirMensaje("Te preparas para hacer una finta al oponente");
                    fintaJugador = true;
                    accion = true;
                    break;
            }
        }
    }

    /**
     * Determina la acción del enemigo
     * <p>1-Ataque normal
     * <p>2-Ataque especial
     * <p>3-Bloquear
     * <p>4-Finta
     *
     * @param codAccion La acción que va a realizar el enemigo
     */
    public void accionEnemigo(int codAccion) {
        switch (codAccion) {
            case 1:
                if (!atacar(jugador, enemigo)) {
                    interfaz.imprimirMensaje("El ataque del enemigo ha fallado");
                    esperar(1000);
                } else {
                    interfaz.imprimirMensaje("El ataque del enemigo te ha dado");
                    esperar(1000);
                }
                break;
            case 2:
                if (!ataqueEspecial(jugador, enemigo)) {
                    esperar(1000);
                } else {
                    interfaz.imprimirMensaje("El ataque especial del enemigo ha sido un éxito");
                    esperar(1000);
                }
                break;
            case 3:
                if (bloquear(enemigo)) {
                    interfaz.imprimirMensaje("El enemigo se prepara para bloquear el siguiente ataque");
                    esperar(1000);
                } else {
                    interfaz.imprimirMensaje("El enemigo intentó bloquear pero no pudo");
                    esperar(1000);
                }
                break;
            case 4:
                if (finta(jugador, enemigo)) {
                    interfaz.imprimirMensaje("Has caído en la trampa del enemigo, estás desorientado");
                    esperar(1000);
                } else {
                    interfaz.imprimirMensaje("El ataque del enemigo era una finta, por eso, no hace nada");
                    esperar(1000);
                }
                break;
        }
    }

    /**
     * Método para hacer la finta.
     * <p>Si el objetivo está bloqueando, se le aplicará el efecto desorientado, que hace que sea mas facil fallar un ataque
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace la finta.
     * @return Devuelve verdadero si el objetivo está bloqueando.
     */
    public boolean finta(Entidad objetivo, Entidad atacante) {
        if (!atacante.getEstadosSufridos().containsKey(Estados.SILENCIADO)) {
            if (objetivo.isBloqueando()) {
                objetivo.infligirEstado(Estados.DESORIENTADO,interfaz);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método para bloquear.
     * <p>Aumenta la defensa del atacante ligeramente
     *
     * @param atacante La entidad que quiere bloquear.
     * @return Devuelve veradero si el atacante no está silenciado.
     */
    public boolean bloquear(Entidad atacante) {
        if (!atacante.getEstadosSufridos().containsKey(Estados.SILENCIADO)) {
            atacante.bloquear();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para hacer el ataque básico.
     * <p>Primero calcula el multiplicador de fallo.
     * <p>Tras eso comprueba si el atacante está congelado, lo que le impide hacer el ataque.
     * <p>Hecho eso, comprueba si el objetivo está contraatacando.
     * <p>Si no es el caso comprueba si el ataque ha fallado.
     * <p>Si el ataque no falla, le hace daño al objetivo.
     * <p>(El Asesino tiene distintas mecánicas dependiendo del arma que lleve equipada, además de una mecánica general que hace que ataque dos veces cada vez que ataca)
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace el ataque.
     * @return Devuelve verdadero si el ataque se ha realizado correctamente
     */
    public boolean atacar(Entidad objetivo, Entidad atacante) {
        int multiplicadorFallo = 1;

        if (estaContraatacando(objetivo, atacante)) return false;

        if (atacante.getEstadosSufridos().containsKey(Estados.CEGADO)) {
            multiplicadorFallo *= Estados.CEGADO.getEfecto();
        }

        if (atacante.getEstadosSufridos().containsKey(Estados.DESORIENTADO)) {
            multiplicadorFallo *= Estados.DESORIENTADO.getEfecto();
        }

        if (objetivo.getEstadosSufridos().containsKey(Estados.EVASION)) {
            multiplicadorFallo *= Estados.EVASION.getEfecto();
        }

        if (!atacante.getEstadosSufridos().containsKey(Estados.CONGELADO)) {
            if (rng.nextInt(0, 20 / multiplicadorFallo) == 1) {
                if (atacante instanceof Jugador) {
                    if (((Jugador) atacante).getArma() instanceof DagaDoble) {
                        interfaz.imprimirMensaje("Te haces daño a ti mismo");
                        esperar(500);
                        atacante.recibirDmg(atacante.getDmg() / 4, interfaz);
                    }
                }
                return false;
            } else {
                if (!objetivo.getEstadosSufridos().containsKey(Estados.CONTRAATACANDO)) {
                    if (atacante instanceof Jugador) {
                        if (atacante instanceof Asesino) {

                            objetivo.recibirDmg(atacante.getDmg(), interfaz);
                            objetivo.recibirDmg(atacante.getDmg() / 2, interfaz);

                        } else if (((Jugador) atacante).getArma() instanceof DagaRoboVida) {

                            objetivo.recibirDmg(atacante.getDmg()/2, interfaz);
                            objetivo.recibirDmg(atacante.getDmg()/3,interfaz);
                            jugador.curarVida(atacante.getDmg() / 3);

                        } else if (((Jugador) atacante).getArma() instanceof DagaCritica) {

                            if (rng.nextInt(0, 4) == 1) {
                                objetivo.recibirDmg(atacante.getDmg() * 2, interfaz);
                            } else {
                                objetivo.recibirDmg(atacante.getDmg(), interfaz);
                            }
                            if (rng.nextInt(0, 4) == 1) {
                                objetivo.recibirDmg(atacante.getDmg(), interfaz);
                            } else {
                                objetivo.recibirDmg(atacante.getDmg()/2, interfaz);
                            }
                        } else {
                            objetivo.recibirDmg(atacante.getDmg(), interfaz);
                        }
                    } else {
                        objetivo.recibirDmg(atacante.getDmg(), interfaz);
                    }

                    if (atacante instanceof Jugador) {
                        if (jugador.getArma().getBonus() != null && !jugador.getArma().getBonus().isEmpty())
                            for (int i = 0; i < jugador.getArma().getBonus().size(); i++) {
                                if (rng.nextInt(0, 5) == 1) {
                                    objetivo.infligirEstado(jugador.getArma().getBonus().get(i),interfaz);
                                }
                            }
                    }

                    return true;
                } else {
                    interfaz.imprimirMensaje("Contraataque");
                    esperar(500);
                    atacante.recibirDmg(objetivo.getDmg(), interfaz);
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Determina si el objetivo está contraatacando, en cuyo caso, le hace daño al atacante.
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace el ataque.
     * @return Devuelve verdadero si el objetivo está contraatacando.
     */
    private boolean estaContraatacando(Entidad objetivo, Entidad atacante) {
        if (objetivo.getEstadosSufridos().containsKey(Estados.CONTRAATACANDO)) {
            atacante.recibirDmg(atacante.getDmg(), interfaz);
            return true;
        }
        return false;
    }

    /**
     * Método para hacer el ataque especial.
     * <p>Igual que el ataque básico, pero despues de comprobar si el atacante está congelado y si el atacante es un jugador, espera a que se seleccione el ataque especial
     *
     * @param objetivo Entidad que recibe el ataque.
     * @param atacante Entidad que hace el ataque.
     * @return Devuelve verdadero si el ataque se ha realizado correctamente
     */
    public boolean ataqueEspecial(Entidad objetivo, Entidad atacante) {
        int multiplicadorFallo = 1;
        int ataque = 0;

        if (estaContraatacando(objetivo, atacante)) {
            return false;
        }

        if (atacante.getEstadosSufridos().containsKey(Estados.CEGADO)) {
            multiplicadorFallo *= Estados.CEGADO.getEfecto();
        }

        if (atacante.getEstadosSufridos().containsKey(Estados.DESORIENTADO)) {
            multiplicadorFallo *= Estados.DESORIENTADO.getEfecto();
        }

        if (objetivo.getEstadosSufridos().containsKey(Estados.EVASION)) {
            multiplicadorFallo *= Estados.EVASION.getEfecto();
        }

        if (!atacante.getEstadosSufridos().containsKey(Estados.CONGELADO) && !atacante.getEstadosSufridos().containsKey(Estados.SILENCIADO)) {
            if (atacante instanceof Jugador) {
                interfaz.cambiarFase(2);
                interfaz.reiniciarPulsado();
                interfaz.habilitarBotones();
                while (interfaz.botonPulsado() == -1) {
                    esperar(10);
                    if (interfaz.botonPulsado() != -1) {
                        ataque = interfaz.botonPulsado();
                    }
                }
                interfaz.deshabilitarBotones();
            } else {
                ataque = rng.nextInt(0, atacante.getAtaques().size());
                interfaz.imprimirMensaje("El enemigo usa el etaque especial " + atacante.getAtaques().get(ataque).getNombre());
            }
            if (!objetivo.getEstadosSufridos().containsKey(Estados.CONTRAATACANDO)) {
                AtaqueEspecial as = atacante.getAtaques().get(ataque);
                if (rng.nextInt(0, 20 / multiplicadorFallo) == 1) {
                    return false;
                } else {
                    as.hacerAtaque(objetivo, atacante, interfaz);
                    return true;
                }
            } else {
                atacante.recibirDmg(objetivo.getDmg(), interfaz);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Bucle del combate.
     * <p>El primer turno es del jugador y se van alternando acciones. El enemigo hace acciones aleatorias mientras que el jugador va eligiendo sus acciones.
     * <p>Al final de cada turno se aplican los efectos de estado de cada Participante.
     * <p>Se repite hasta que uno de los dos participantes muere.
     */
    @Override
    public void empezarEvento() {
        jugador.getAccesorios().forEach((a) -> {
            if (a.isInicioCombate()) {
                a.aplicarEfecto(jugador,interfaz);
            }
        });

        while (!jugador.estaMuerto() && !enemigo.estaMuerto()) {

            interfaz.imprimirMensaje("Tu turno");

            interfaz.habilitarBotones();
            while (interfaz.botonPulsado() == -1) {
                esperar(10);
                if (interfaz.botonPulsado() != -1) {
                    accionJugador(interfaz.botonPulsado() + 1);
                }
            }
            interfaz.deshabilitarBotones();
            interfaz.reiniciarPulsado();
            interfaz.cambiarFase(1);

            jugador.aplicarEstados(interfaz);
            interfaz.actualizar();
            esperar(500);

            if (!enemigo.estaMuerto() && !jugador.estaMuerto()) {

                enemigo.finTurno();
                interfaz.imprimirMensaje("Turno del enemigo");
                esperar(1000);

                accionEnemigo(rng.nextInt(1, 5));

                enemigo.aplicarEstados(interfaz);

                jugador.getAccesorios().forEach((a) -> {
                    if (a.isInicioTurno()) {
                        a.aplicarEfecto(jugador,interfaz);
                    }
                });
                interfaz.actualizar();

                if (fintaJugador) {
                    if (finta(enemigo, jugador)) {
                        interfaz.imprimirMensaje("El enemigo estaba bloqueando, le has desorientado");
                        esperar(1000);
                    } else {
                        interfaz.imprimirMensaje("La finta ha fallado");
                        esperar(1000);
                    }
                    fintaJugador = false;
                }
                jugador.finTurno();
            }

        }
        terminarEvento();
    }

    /**
     * Método para esperar una cantidad de tiempo.
     *
     * @param tiempo Milisegundos que tiene que esperar.
     */
    private void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lógica del final del combate.
     * <p>Si el jugador ha muerto, se imprime un mensaje por pantalla.
     * <p>Si el enemigo ha muerto, se imprime un mensaje por pantalla y se le da una cantidad de oro al jugador dependiendo del nivel del combate.
     */
    @Override
    public void terminarEvento() {
        int recompensaOro;
        if (jugador.estaMuerto()) {
            interfaz.imprimirMensaje("Has Perdido");
            esperar(2000);
        } else if (enemigo.estaMuerto()) {
            if (enemigo instanceof DragonFase1) {
                interfaz.imprimirMensaje("Te diriges hacia la salida");
                interfaz.cambiarFase(3);
                interfaz.habilitarBotones();
                while (interfaz.botonPulsado() == -1) {
                    esperar(10);
                }
            } else if (enemigo instanceof DragonFase2){
                interfaz.imprimirMensaje("Esta vez te aseguras de que el Dragón ha muerto.");
                interfaz.cambiarFase(3);
                interfaz.habilitarBotones();
                while (interfaz.botonPulsado() == -1) {
                    esperar(10);
                }
            } else  {
                if (nivel != -1) {
                    recompensaOro = rng.nextInt(10 * nivel, 20 * nivel);

                    interfaz.imprimirMensaje("Has Ganado!! \n Recibes " + recompensaOro + " de oro");
                    interfaz.cambiarFase(3);

                    jugador.ganarOro(recompensaOro);
                    jugador.restaurarMana();
                    jugador.setEstadosSufridos(new HashMap<>());

                    jugador.getAccesorios().forEach((a) -> {
                        if (a.isFinCombate()) {
                            a.aplicarEfecto(jugador,interfaz);
                        }
                    });
                    interfaz.habilitarBotones();
                    while (interfaz.botonPulsado() == -1) {
                        esperar(10);
                    }
                }
            }

        }
    }
}