package Modelo.Eventos;

import Modelo.Armas.DagaCritica;
import Modelo.Armas.DagaDoble;
import Modelo.Armas.DagaRoboVida;
import Modelo.Bases.*;
import Modelo.Jugador.Asesino;
import Modelo.Misc.Estados;
import UI.MenusConsola;

import java.util.Random;

public class Combate extends Evento {
    public Combate(Jugador jugador, Enemigo enemigo) {
        titulo = "Combate";
        turnoActual = 1;
        this.enemigo = enemigo;
        this.jugador = jugador;
    }
    int turnoActual;
    Jugador jugador;
    Enemigo enemigo;

    Random rng = new Random();

    public void accionJugador(int codAccion) {
        switch (codAccion) {
            case 1:
                if (!atacar(enemigo,jugador)) {
                    System.out.println("El ataque ha fallado");
                }
                break;
            case 2:
                if (!ataqueEspecial(enemigo,jugador)) {
                    System.out.println("El ataque ha fallado");
                }
                break;
        }
    }

    public void accionEnemigo(int codAccion) {
        switch (codAccion) {
            case 1:
                if (!atacar(jugador,enemigo)) {
                    System.out.println("El ataque ha fallado");
                }
                break;
            case 2:
                if (!ataqueEspecial(jugador,enemigo)) {
                    System.out.println("El ataque ha fallado");
                }
                break;
        }
    }

    public boolean atacar(Entidad objetivo,Entidad atacante) {
        int multiplicadorFallo = 1;

        if (atacante.getEstadosSufridos().containsKey(Estados.CEGADO)) {
            multiplicadorFallo *= Estados.CEGADO.getEfecto();
        }

        if (!atacante.getEstadosSufridos().containsKey(Estados.CONGELADO)) {
            if (rng.nextInt(0,20/multiplicadorFallo) == 1) {
                if (atacante instanceof Jugador) {
                    if (((Jugador) atacante).getArma() instanceof DagaDoble) {
                        atacante.recibirDmg(atacante.getDmg()/4);
                    }
                }
                return false;
            } else {
                if (atacante instanceof Jugador) {
                    if (atacante instanceof Asesino) {
                        objetivo.recibirDmg(atacante.getDmg());
                        objetivo.recibirDmg(atacante.getDmg());
                    } else if (((Jugador) atacante).getArma() instanceof DagaRoboVida) {
                        jugador.curarVida(atacante.getDmg()/3);
                    } else if (((Jugador) atacante).getArma() instanceof DagaCritica) {
                        if (rng.nextInt(0,4) == 1) {
                            objetivo.recibirDmg(atacante.getDmg() * 2);
                        }
                    } else {
                        objetivo.recibirDmg(atacante.getDmg());
                    }
                } else {
                    objetivo.recibirDmg(atacante.getDmg());
                }
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean ataqueEspecial(Entidad objetivo,Entidad atacante) {
        int multiplicadorFallo = 1;
        int ataque;

        if (atacante.getEstadosSufridos().containsKey(Estados.CEGADO)) {
            multiplicadorFallo *= Estados.CEGADO.getEfecto();
        }

        if (!atacante.getEstadosSufridos().containsKey(Estados.CONGELADO) && !atacante.getEstadosSufridos().containsKey(Estados.SILENCIADO)) {
            if (atacante instanceof Jugador) {
                ataque = MenusConsola.elegirAtaqueEspecial(atacante);
            } else {
                ataque = rng.nextInt(0,atacante.getAtaques().size());
            }

            AtaqueEspecial as = atacante.getAtaques().get(ataque);

            if (rng.nextInt(0,20/multiplicadorFallo) == 1) {
                return false;
            } else {
                as.hacerAtaque(objetivo, atacante);
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
            jugador.mostrarEstadisticas();
            System.out.println();
            enemigo.mostrarEstadisticas();
            System.out.println();
            System.out.println("Turno del jugador");
            accionJugador(MenusConsola.menuCombate());
            jugador.aplicarEstados();
            System.out.println();
            if (!enemigo.estaMuerto() && !jugador.estaMuerto()) {
                System.out.println("Turno del enemigo");
                accionEnemigo(rng.nextInt(1,3));
                enemigo.aplicarEstados();
                jugador.getAccesorios().forEach((a)->{
                    if (a.isInicioTurno()) {
                        a.aplicarEfecto(jugador);
                    }
                });
            }

        }
        if (jugador.estaMuerto()) {
            System.out.println("Has perdido");
        }
        if (enemigo.estaMuerto()) {
            System.out.println("Has ganado");
        }
        jugador.mostrarEstadisticas();
        jugador.restaurarMana();
        terminarEvento();
    }

    @Override
    public void terminarEvento() {

    }
}
