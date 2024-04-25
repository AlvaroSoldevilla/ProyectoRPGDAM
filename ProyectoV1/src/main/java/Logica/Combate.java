package Logica;

import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Clase;
import Modelo.Bases.Enemigo;
import Modelo.Bases.Entidad;
import Modelo.Misc.Estados;
import UI.MenusConsola;

import java.util.Random;

public class Combate {
    public Combate(Clase jugador, Enemigo enemigo) {
        turnoActual = 1;
        this.enemigo = enemigo;
        this.jugador = jugador;
    }
    int turnoActual;
    Clase jugador;
    Enemigo enemigo;

    Random rng = new Random();

    public void combate() {

    }

    public void accionJugador(int codAccion) {
        switch (codAccion) {
            case 1:
                atacar(jugador,enemigo);
                break;
            case 2:

                break;
            case 3:

                break;
        }
    }

    public void accionEnemigo(int codAccion) {
        switch (codAccion) {
            case 1:
                atacar(jugador,enemigo);
                break;
            case 2:

                break;
            case 3:

                break;
        }
    }

    public void darRecompensa() {

    }

    public boolean atacar(Entidad objetivo,Entidad atatcante) {
        int multiplicadorFallo = 1;

        if (atatcante.getEstadosSufridos().containsKey(Estados.CEGADO)) {
            multiplicadorFallo *= Estados.CEGADO.getEfecto();
        }

        if (rng.nextInt(0,20/multiplicadorFallo) == 5) {
            return false;
        } else {
            objetivo.recibirDmg(atatcante.getAtaque());
            return true;
        }
    }

    public boolean ataqueEspecial(Entidad objetivo,Entidad atatcante) {
        int multiplicadorFallo = 1;
        int ataque = MenusConsola.elegirAtaqueEspecial();
        AtaqueEspecial as = atatcante.getAtaques().get(ataque);

        if (atatcante.getEstadosSufridos().containsKey(Estados.CEGADO)) {
            multiplicadorFallo *= Estados.CEGADO.getEfecto();
        }

        if (rng.nextInt(0,20/multiplicadorFallo) == 5) {
            return false;
        } else {
            as.hacerAtaque(objetivo, atatcante);
            return true;
        }
    }
}
