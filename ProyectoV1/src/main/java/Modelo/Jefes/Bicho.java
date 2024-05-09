package Modelo.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Eventos.BatallaConJefe;

public class Bicho extends BatallaConJefe {

    public Bicho(Jugador jugador) {
        this.jugador = jugador;
    }

    Jugador jugador;
    Enemigo bicho = new PruebaEnemigo();

    @Override
    public void empezarEvento() {

    }

    @Override
    public void terminarEvento() {

    }

    @Override
    protected void cambiarFase() {

    }
}
