package Modelo.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Eventos.BatallaConJefe;

public class Lobo extends BatallaConJefe {

    public Lobo(Jugador jugador) {
        this.jugador = jugador;
    }

    Jugador jugador;
    Enemigo lobo = new PruebaEnemigo();
    @Override
    public void empezarEvento() {
        System.out.println("TBD");
    }

    @Override
    public void terminarEvento() {

    }

    @Override
    protected void cambiarFase() {

    }
}
