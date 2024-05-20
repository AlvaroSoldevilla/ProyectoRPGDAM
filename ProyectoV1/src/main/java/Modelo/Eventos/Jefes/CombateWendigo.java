package Modelo.Eventos.Jefes;

import Modelo.Equipamiento.Armas.DagaCritica;
import Modelo.Equipamiento.Armas.DagaDoble;
import Modelo.Equipamiento.Armas.DagaRoboVida;
import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Enemigo;
import Modelo.Bases.Entidad;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Perro;
import Modelo.Enemigos.Jefes.Wendigo;
import Modelo.Enums.Iconos;
import Modelo.Eventos.BatallaConJefe;
import Modelo.Jugador.Asesino;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UICombate;
import UI.Interfaces.UIEvento;
import lombok.Getter;

import java.util.HashMap;
import java.util.Random;

public class CombateWendigo extends BatallaConJefe {

    public CombateWendigo(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Wendigo";
        turnoActual = 1;
        this.jugador = jugador;
        enemigos = new Enemigo[3];
        enemigos[0] = new Wendigo();
        opciones = new String[] {"Combatir"};
    }

    int turnoActual;
    int enemigoElegido = 0;
    boolean fintaJugador = false;
    @Getter
    Enemigo[] enemigos;

    Random rng = new Random();

    public void accionJugador(int codAccion, Enemigo enemigo) {
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
                    esperar(1000);
                    fintaJugador = true;
                    accion = true;
                    break;
            }
        }
    }

    public void accionEnemigo(int codAccion, Enemigo enemigo) {
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
                if (!ataqueEspecial(jugador, enemigo)) {
                    esperar(1000);
                } else {
                    interfaz.imprimirMensaje("El ataque especial del enemigo ha sido un éxito");
                    esperar(1000);
                }
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
            case 5:
                interfaz.imprimirMensaje("El enemigo intenta invocar ayuda\n(Pulsa sobre los enemigos para cambiar el objetivo)");
                addEnemigo(new Perro());
                break;
        }
    }

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
                if (!objetivo.getEstadosSufridos().containsKey(Estados.CONTRAATACANDO)) {
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
                interfaz.cambiarFase(2);
                interfaz.reiniciarPulsado();
                while (interfaz.botonPulsado() == -1) {
                    interfaz.habilitarBotones();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (interfaz.botonPulsado() != -1) {
                        ataque = interfaz.botonPulsado();
                    }
                    interfaz.deshabilitarBotones();
                }

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

    private boolean combatir() {
        Enemigo enemigo = enemigos[0];
        jugador.getAccesorios().forEach((a) -> {
            if (a.isInicioCombate()) {
                a.aplicarEfecto(jugador,interfaz);
            }
        });
        while (!jugador.estaMuerto() && !comprobarVictoria()) {

            interfaz.imprimirMensaje("Turno del jugador");

            interfaz.habilitarBotones();
            while (interfaz.botonPulsado() == -1 && !interfaz.seguir()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (interfaz.botonPulsado() > 3) {
                    enemigoElegido = interfaz.botonPulsado() - 4;
                    enemigo = enemigos[enemigoElegido];
                    interfaz.reiniciarPulsado();
                } else {
                    accionJugador(interfaz.botonPulsado() + 1, enemigo);
                }
            }

            interfaz.setSeguir();
            interfaz.deshabilitarBotones();
            interfaz.reiniciarPulsado();
            interfaz.cambiarFase(1);

            for (int i = 0; i < enemigos.length; i++) {
                if (enemigos[i].estaMuerto()) {
                    eliminarEnemigo(i);
                }
            }
            interfaz.actualizar();

            jugador.aplicarEstados(interfaz);
            esperar(1000);

            if (!comprobarVictoria() && !jugador.estaMuerto()) {
                for (int i = 0; i < enemigos.length; i++) {
                    if (enemigos[i] != null) {
                        enemigos[i].finTurno();
                    }
                }
                interfaz.imprimirMensaje("Turno de los enemigos");
                esperar(1000);

                for (int i = 0; i < enemigos.length; i++) {
                    if (enemigos[i] != null) {
                        if (enemigo instanceof Wendigo) {
                            accionEnemigo(rng.nextInt(1, 6), enemigos[i]);
                        } else if (enemigo.estaMuerto()){
                            eliminarEnemigo(i);
                        } else {
                            accionEnemigo(rng.nextInt(1,4),enemigos[i]);
                        }
                        enemigos[i].aplicarEstados(interfaz);
                    }
                }

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
            } else {
                return true;
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

    private void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void empezarEvento() {
        setTexto("Encuentras un ser inexplicable, temes por tu vida");
        interfaz.cambiarEscena(new UIEvento(Iconos.NIVEL2.getRutaIcono(),this));
        while (interfaz.botonPulsado() == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        interfaz.reiniciarPulsado();
        interfaz.cambiarEscena(new UICombate(Iconos.NIVEL2.getRutaIcono(),jugador,enemigos));
        if (combatir()) {
            terminarEvento();
        }
    }

    @Override
    public void terminarEvento() {
        int recompensaOro;
        if (jugador.estaMuerto()) {
            interfaz.imprimirMensaje("Has perdido");
            esperar(2000);
        } else if (jefe.estaMuerto()) {
            recompensaOro = 80;
            interfaz.imprimirMensaje("Has ganado!! Recibes " + recompensaOro + " de oro");
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
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}