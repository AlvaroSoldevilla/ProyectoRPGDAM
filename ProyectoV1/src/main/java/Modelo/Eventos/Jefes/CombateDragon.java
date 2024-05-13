package Modelo.Eventos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Jefes.DragonFase1;
import Modelo.Enemigos.Jefes.DragonFase2;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Eventos.BatallaConJefe;
import Modelo.Eventos.Combate;
import UI.Interfaces.Interfaz;

public class CombateDragon extends BatallaConJefe {

    public CombateDragon(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Dragon";
        this.jugador = jugador;
        jefe = new DragonFase1();
    }

    Jugador jugador;

    @Override
    public void empezarEvento() {
        System.out.println("Aparece un dragón enorme delante tuya");
        Combate c = new Combate(jugador,jefe, interfaz);
        c.empezarEvento();

        System.out.println("Sigues adelante tras vencer al dragón, pero antes de que puedas seguir, escuchas un rugido detras tuya," +
                " el dragón se ha levantado de nuevo y se prepara para atacar");

        jefe = new DragonFase2();
        c = new Combate(jugador,jefe, interfaz);
        c.empezarEvento();
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        System.out.println("Has ganado");
    }
}
