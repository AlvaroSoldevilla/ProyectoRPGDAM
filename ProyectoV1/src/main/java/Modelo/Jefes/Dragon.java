package Modelo.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Eventos.BatallaConJefe;

public class Dragon extends BatallaConJefe {

    public Dragon(Jugador jugador) {
        this.jugador = jugador;
    }

    Jugador jugador;
    Enemigo dragon = new PruebaEnemigo();

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
