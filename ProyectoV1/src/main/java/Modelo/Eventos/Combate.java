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

@Data
public class Combate extends Evento {
    public Combate(Jugador jugador, Enemigo enemigo, int nivel, Interfaz interfaz) {
        super(interfaz);
        titulo = "Combate";
        turnoActual = 1;
        this.enemigo = enemigo;
        this.jugador = jugador;
        this.nivel = nivel;
        icono = Iconos.COMBATE;
    }

    public Combate(Jugador jugador, Enemigo enemigo, Interfaz interfaz) {
        super(interfaz);
        titulo = "Combate";
        turnoActual = 1;
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

    int turnoActual;
    int nivel = -1;
    boolean fintaJugador = false;
    Jugador jugador;
    Enemigo enemigo;

    Random rng = new Random();

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

    public void accionEnemigo(int codAccion) {
        switch (codAccion) {
            case 1:
                if (!atacar(jugador, enemigo)) {
                    interfaz.imprimirMensaje("El ataque ha fallado");
                    esperar(1000);
                } else {
                    interfaz.imprimirMensaje("El ataque te ha dado");
                    esperar(1000);
                }
                break;
            case 2:
                if (bloquear(enemigo)) {
                    interfaz.imprimirMensaje("El enemigo se prepara para bloquear el siguiente ataque");
                    esperar(1000);
                } else {
                    interfaz.imprimirMensaje("El enemigo intentó bloquear pero no pudo");
                    esperar(1000);
                }
                break;
            case 3:
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

    public boolean finta(Entidad objetivo, Entidad atacante) {
        if (!atacante.getEstadosSufridos().containsKey(Estados.SILENCIADO)) {
            if (objetivo.isBloqueando()) {
                objetivo.infligirEstado(Estados.DESORIENTADO);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean bloquear(Entidad atacante) {
        if (!atacante.getEstadosSufridos().containsKey(Estados.SILENCIADO)) {
            atacante.bloquear();
            return true;
        } else {
            return false;
        }
    }

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
                        atacante.recibirDmg(atacante.getDmg() / 4, interfaz);
                    }
                }
                return false;
            } else {
                if (atacante instanceof Jugador) {
                    if (atacante instanceof Asesino) {

                        objetivo.recibirDmg(atacante.getDmg(), interfaz);
                        objetivo.recibirDmg(atacante.getDmg() / 2, interfaz);

                    } else if (((Jugador) atacante).getArma() instanceof DagaRoboVida) {

                        objetivo.recibirDmg(atacante.getDmg(), interfaz);
                        jugador.curarVida(atacante.getDmg() / 3);

                    } else if (((Jugador) atacante).getArma() instanceof DagaCritica) {

                        if (rng.nextInt(0, 4) == 1) {
                            objetivo.recibirDmg(atacante.getDmg() * 2, interfaz);
                        } else {
                            objetivo.recibirDmg(atacante.getDmg(), interfaz);
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
                                objetivo.infligirEstado(jugador.getArma().getBonus().get(i));
                            }
                        }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    private boolean estaContraatacando(Entidad objetivo, Entidad atacante) {
        if (objetivo.getEstadosSufridos().containsKey(Estados.CONTRAATACANDO)) {
            atacante.recibirDmg(atacante.getDmg(), interfaz);
            return true;
        }
        return false;
    }

    public boolean ataqueEspecial(Entidad objetivo, Entidad atacante) {
        int multiplicadorFallo = 1;
        int ataque = 0;

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

        if (!atacante.getEstadosSufridos().containsKey(Estados.CONGELADO) && !atacante.getEstadosSufridos().containsKey(Estados.SILENCIADO)) {
            if (atacante instanceof Jugador) {
                interfaz.getContenedorActual().actualizarEscena(2);
                while (interfaz.botonPulsado() == -1) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (interfaz.botonPulsado() != -1) {
                        ataque = interfaz.botonPulsado();

                    }
                }
                interfaz.reiniciarPulsado();
            } else {
                ataque = rng.nextInt(0, atacante.getAtaques().size());
            }

            AtaqueEspecial as = atacante.getAtaques().get(ataque);

            if (rng.nextInt(0, 20 / multiplicadorFallo) == 1) {
                return false;
            } else {
                as.hacerAtaque(objetivo, atacante, interfaz);
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void empezarEvento() {
        jugador.getAccesorios().forEach((a) -> {
            if (a.isInicioCombate()) {
                a.aplicarEfecto(jugador);
            }
        });

        while (!jugador.estaMuerto() && !enemigo.estaMuerto()) {

            interfaz.imprimirMensaje("Tu turno");
            interfaz.getContenedorActual().actualizarEscena(1);

            while (interfaz.botonPulsado() == -1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (interfaz.botonPulsado() != -1) {
                    accionJugador(interfaz.botonPulsado() + 1);
                }
            }
            interfaz.reiniciarPulsado();

            jugador.aplicarEstados(interfaz);

            if (!enemigo.estaMuerto() && !jugador.estaMuerto()) {

                enemigo.finTurno();
                interfaz.imprimirMensaje("Turno del enemigo");
                esperar(1000);

                accionEnemigo(rng.nextInt(1, 4));

                enemigo.aplicarEstados(interfaz);

                jugador.getAccesorios().forEach((a) -> {
                    if (a.isInicioTurno()) {
                        a.aplicarEfecto(jugador);
                    }
                });

                if (fintaJugador) {
                    if (finta(enemigo, jugador)) {
                        interfaz.imprimirMensaje("El enemigo estaba bloqueando, le has desorientado");
                        esperar(1000);
                    } else {
                        interfaz.imprimirMensaje("La finta ha fallado");
                        esperar(1000);
                    }
                }
                jugador.finTurno();
            }

        }
        terminarEvento();
    }

    private void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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
                while (interfaz.botonPulsado() == -1) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (enemigo instanceof DragonFase2){
                interfaz.imprimirMensaje("Esta vez te aseguras de que el Dragón ha muerto.");
                interfaz.cambiarFase(3);
                while (interfaz.botonPulsado() == -1) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else  {
                if (nivel != -1) {
                    recompensaOro = rng.nextInt(10 * nivel, 20 * nivel);
                    interfaz.imprimirMensaje("Has Ganado!! \n Recibes " + recompensaOro + " de oro");
                    interfaz.imprimirMensaje("");
                    interfaz.cambiarFase(3);
                    jugador.ganarOro(recompensaOro);
                    jugador.restaurarMana();
                    jugador.setEstadosSufridos(new HashMap<>());

                    jugador.getAccesorios().forEach((a) -> {
                        if (a.isFinCombate()) {
                            a.aplicarEfecto(jugador);
                        }
                    });
                    while (interfaz.botonPulsado() == -1) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

        }
    }
}
