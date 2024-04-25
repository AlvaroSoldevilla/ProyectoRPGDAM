package Logica;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Jugador;
import Modelo.Bases.Enemigo;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;
import UI.MenusConsola;

import java.util.Random;

public class Combate {
    public Combate(Jugador jugador, Enemigo enemigo) {
        turnoActual = 1;
        this.enemigo = enemigo;
        this.jugador = jugador;
    }
    int turnoActual;
    Jugador jugador;
    Enemigo enemigo;

    Random rng = new Random();

    public void combate() {
        //TODO: Implementar en interfaz gráfica
        jugador.mostrarEstadisticas();
        System.out.println();
        enemigo.mostrarEstadisticas();
        System.out.println();
        while (!jugador.estaMuerto()&&!enemigo.estaMuerto()) {
            System.out.println("Turno del jugador");
            accionJugador(MenusConsola.menuCombate());
            enemigo.mostrarEstadisticas();
            System.out.println();
            if (!enemigo.estaMuerto()) {
                System.out.println("Turno del enemigo");
                accionEnemigo(rng.nextInt(1,3));
                jugador.mostrarEstadisticas();
                System.out.println();
            }
        }
        if (jugador.estaMuerto()) {
            System.out.println("Has perdido");
        }
        if (enemigo.estaMuerto()) {
            System.out.println("Has ganado");
        }
        jugador.mostrarEstadisticas();
    }

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

    public void darRecompensa() {

    }

    public boolean atacar(Entidad objetivo,Entidad atacante) {
        int multiplicadorFallo = 1;

        if (atacante.getEstadosSufridos().containsKey(Estados.CEGADO)) {
            multiplicadorFallo *= Estados.CEGADO.getEfecto();
        }
        if (!atacante.getEstadosSufridos().containsKey(Estados.CONGELADO)) {
            if (rng.nextInt(0,20/multiplicadorFallo) == 1) {
                return false;
            } else {
                objetivo.recibirDmg(atacante.getDmg());
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

        if (!atacante.getEstadosSufridos().containsKey(Estados.CONGELADO)) {
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
}
