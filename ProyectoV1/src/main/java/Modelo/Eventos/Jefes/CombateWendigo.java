package Modelo.Eventos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Eventos.BatallaConJefe;
import UI.Interfaces.Interfaz;

public class CombateWendigo extends BatallaConJefe {

    public CombateWendigo(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Wendigo";
        this.jugador = jugador;
    }

    Jugador jugador;
    Enemigo Wendigo = new PruebaEnemigo();

    @Override
    public void empezarEvento() {
        System.out.println("TBD");
    }

    @Override
    public void terminarEvento() {

    }
}
