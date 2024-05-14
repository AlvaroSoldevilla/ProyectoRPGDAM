package Modelo.Eventos.Jefes;

import Modelo.Armas.DagaCritica;
import Modelo.Armas.DagaDoble;
import Modelo.Armas.DagaRoboVida;
import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Enemigo;
import Modelo.Bases.Entidad;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Enemigo2;
import Modelo.Enemigos.Jefes.Wendigo;
import Modelo.Eventos.BatallaConJefe;
import Modelo.Jugador.Asesino;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

import java.util.Random;

public class CombateWendigo extends BatallaConJefe {

    public CombateWendigo(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Wendigo";
        turnoActual = 1;
        this.jugador = jugador;
        enemigos = new Enemigo[3];
        wendigo = new Wendigo();
        enemigos[0] = wendigo;
    }

    Jugador jugador;
    Enemigo wendigo;

    int turnoActual;
    boolean fintaJugador = false;
    Enemigo[] enemigos;

    Random rng = new Random();

    public void accionJugador(int codAccion,Enemigo enemigo) {
        boolean accion = false;
        while (!accion) {
            interfaz.getContenedorActual().actualizarEscena(1);
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

    public void accionEnemigo(int codAccion,Enemigo enemigo) {
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
            case 4:
                addEnemigo(new Enemigo2());
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

    private boolean combatir() {
        Enemigo enemigo = null;
        jugador.getAccesorios().forEach((a)->{
            if (a.isInicioCombate()) {
                a.aplicarEfecto(jugador);
            }
        });
        //TODO: Implementar en interfaz gráfica
        while (!jugador.estaMuerto()&&!comprobarVictoria()) {


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
                    enemigo = enemigos[interfaz.botonPulsado()];
                    interfaz.setBotonPulsado(-1);
                }
            }

            while (interfaz.botonPulsado() == -1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (interfaz.botonPulsado() != -1) {
                    accionJugador(interfaz.botonPulsado(), enemigo);
                    interfaz.setBotonPulsado(-1);
                }
            }

            for (int i = 0; i < enemigos.length; i++) {
                if (enemigos[i].estaMuerto()) {
                    eliminarEnemigo(i);
                }
            }

            jugador.aplicarEstados(interfaz);
            System.out.println();

            if (!comprobarVictoria() && !jugador.estaMuerto()) {
                for (int i = 0; i < enemigos.length; i++) {
                    if (enemigos[i] != null) {
                        enemigos[i].finTurno();
                    }
                }
                System.out.println("Turno del primer enemigo");

                for (int i = 0; i < enemigos.length; i++) {
                    if (enemigos[i] != null) {
                        if (enemigo instanceof Wendigo) {
                            accionEnemigo(rng.nextInt(1, 5), enemigos[i]);
                        }
                        enemigos[i].aplicarEstados(interfaz);
                    }
                }


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
        return false;
    }

    private void eliminarEnemigo(int pos) {
        if (enemigos[pos] != null) {
            enemigos[pos] = null;
        }
    }

    private void addEnemigo(Enemigo enemigo) {
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] == null) {
                enemigos[i] = enemigo;
                break;
            }
        }
    }

    private boolean comprobarVictoria() {
        if (enemigos[0].estaMuerto()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void empezarEvento() {
        System.out.println("Encuentras un ser inexplicable, temes por tu vida");
        if (combatir()) {
            terminarEvento();
        }
    }

    @Override
    public void terminarEvento() {
        System.out.println("Ganaste");
    }
}