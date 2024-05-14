package Modelo.Eventos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Jefes.Lobo;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Eventos.BatallaConJefe;
import Modelo.Eventos.Combate;
import UI.Interfaces.Interfaz;

public class CombateLobo extends BatallaConJefe {

    public CombateLobo(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Hombre lobo";
        this.jugador = jugador;
        jefe = new Lobo();
    }

    Jugador jugador;
    @Override
    public void empezarEvento() {
        System.out.println("Te encuentras frente a ti un hombre lobo que decidido a acabar tu aventura");
        Combate c = new Combate(jugador,jefe, interfaz);
        c.empezarEvento();
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        System.out.println("Has ganado");
    }
}
