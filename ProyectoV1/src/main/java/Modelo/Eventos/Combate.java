package Modelo.Eventos;

import Modelo.Armas.DagaCritica;
import Modelo.Armas.DagaDoble;
import Modelo.Armas.DagaRoboVida;
import Modelo.Bases.*;
import Modelo.Jugador.Asesino;
import Modelo.Enums.Estados;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;

import java.util.Random;


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
                        System.out.println("El ataque ha fallado");
                    } else {
                        System.out.println("El ataque ha sido un éxito");
                    }
                    accion = true;
                    break;
                case 2:
                    if (!ataqueEspecial(enemigo, jugador)) {
                        System.out.println("El ataque ha fallado");
                    } else {
                        System.out.println("El ataque ha sido un éxito");
                    }
                    accion = true;
                    break;
                case 3:
                    if (bloquear(jugador)) {
                        System.out.println("Te preparas para bloquear el sigiente ataque");
                    } else {
                        System.out.println("Intentas bloquear pero no puedes");
                    }
                    accion = true;
                    break;
                case 4:
                    fintaJugador = true;
                    accion = true;
                    break;
            }
        }
    }

    public void accionEnemigo(int codAccion) {
            switch (codAccion) {
                case 1:
                    if (!atacar(jugador,enemigo)) {
                        System.out.println("El ataque ha fallado");
                    } else {
                        System.out.println("El ataque te ha dado");
                    }
                    break;
                case 2:
                    if (bloquear(enemigo)) {
                        System.out.println("El enemigo se prepara para bloquear el sigiente ataque");
                    } else {
                        System.out.println("EL enemigo intentó bloquear pero no pudo");
                    }
                    break;
                case 3:
                    if (finta(jugador,enemigo)) {
                        System.out.println("Has caido en la trampa del enemigo, estás desorientado");
                    } else {
                        System.out.println("El ataque del enemigo era una finta, por eso, no hace nada");
                    }
                    break;
            }
    }

    public boolean finta(Entidad objetivo,Entidad atacante) {
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

    public boolean atacar(Entidad objetivo,Entidad atacante) {
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
            if (rng.nextInt(0,20/multiplicadorFallo) == 1) {
                if (atacante instanceof Jugador) {
                    if (((Jugador) atacante).getArma() instanceof DagaDoble) {
                        atacante.recibirDmg(atacante.getDmg()/4,interfaz);
                    }
                }
                return false;
            } else {
                if (atacante instanceof Jugador) {
                    if (atacante instanceof Asesino) {

                        objetivo.recibirDmg(atacante.getDmg(),interfaz);
                        objetivo.recibirDmg(atacante.getDmg() / 2,interfaz);

                    } else if (((Jugador) atacante).getArma() instanceof DagaRoboVida) {

                        objetivo.recibirDmg(atacante.getDmg(),interfaz);
                        jugador.curarVida(atacante.getDmg()/3);

                    } else if (((Jugador) atacante).getArma() instanceof DagaCritica) {

                        if (rng.nextInt(0,4) == 1) {
                            objetivo.recibirDmg(atacante.getDmg() * 2,interfaz);
                        } else {
                            objetivo.recibirDmg(atacante.getDmg(),interfaz);
                        }

                    } else {
                        objetivo.recibirDmg(atacante.getDmg(),interfaz);
                    }
                } else {
                    objetivo.recibirDmg(atacante.getDmg(),interfaz);
                }

                if (atacante instanceof Jugador) {
                    if (jugador.getArma().getBonus() != null && !jugador.getArma().getBonus().isEmpty())
                        for (int i = 0; i < jugador.getArma().getBonus().size(); i++) {
                            if (rng.nextInt(0,5) == 1) {
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
            atacante.recibirDmg(atacante.getDmg(),interfaz);
            return true;
        }
        return false;
    }

    public boolean ataqueEspecial(Entidad objetivo,Entidad atacante) {
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
                interfaz.getContenedorActual().actualizarEscena(3);
                while (interfaz.botonPulsado() == -1) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (interfaz.getBotonPulsado() != -1) {
                        ataque = interfaz.getBotonPulsado();
                        interfaz.setBotonPulsado(-1);
                    }
                }

            } else {
                ataque = rng.nextInt(0,atacante.getAtaques().size());
            }

            AtaqueEspecial as = atacante.getAtaques().get(ataque);

            if (rng.nextInt(0,20/multiplicadorFallo) == 1) {
                return false;
            } else {
                as.hacerAtaque(objetivo, atacante,interfaz);
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void empezarEvento() {
        jugador.getAccesorios().forEach((a)->{
            if (a.isInicioCombate()) {
                a.aplicarEfecto(jugador);
            }
        });
        //TODO: Implementar en interfaz gráfica
        while (!jugador.estaMuerto()&&!enemigo.estaMuerto()) {

            System.out.println("Turno del jugador");
            //TODO: Cambiar a interfaz
            interfaz.getContenedorActual().actualizarEscena(1);

            while (interfaz.botonPulsado() == -1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (interfaz.botonPulsado() != -1) {
                    accionJugador(interfaz.botonPulsado());
                    interfaz.setBotonPulsado(-1);
                }
            }

            jugador.aplicarEstados(interfaz);
            System.out.println();

            if (!enemigo.estaMuerto() && !jugador.estaMuerto()) {

                enemigo.finTurno();
                System.out.println("Turno del primer enemigo");


                accionEnemigo(rng.nextInt(1, 4));

                enemigo.aplicarEstados(interfaz);


                jugador.getAccesorios().forEach((a)->{
                    if (a.isInicioTurno()) {
                        a.aplicarEfecto(jugador);
                    }
                });

                if (fintaJugador) {
                    if (finta(enemigo,jugador)) {
                        System.out.println("EL enemigo estaba bloqueando, le has desorientado");
                    } else {
                        System.out.println("La finta Ha fallado");
                    }
                }

                jugador.finTurno();
            }


        }
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        int recompensaOro;
        if (jugador.estaMuerto()) {
            System.out.println("Has perdido");
        } else if (enemigo.estaMuerto()) {
            if (nivel!=-1) {
                recompensaOro = rng.nextInt(10*nivel,20*nivel);
                System.out.println("Has ganado");
                System.out.println("Recibes " + recompensaOro + " de oro");
                jugador.ganarOro(recompensaOro);
                jugador.restaurarMana();

                jugador.getAccesorios().forEach((a) -> {
                    if (a.isFinCombate()) {
                        a.aplicarEfecto(jugador);
                    }
                });
            }
        }
    }
}
