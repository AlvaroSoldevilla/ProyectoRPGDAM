package Modelo.Eventos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Jefes.DragonFase1;
import Modelo.Enemigos.Jefes.DragonFase2;
import Modelo.Enums.Iconos;
import Modelo.Eventos.BatallaConJefe;
import Modelo.Eventos.Combate;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UICombate;
import UI.Interfaces.UIEvento;

public class CombateDragon extends BatallaConJefe {

    public CombateDragon(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Dragon";
        this.jugador = jugador;
        jefe = new DragonFase1();
        opciones = new String[] {"Combatir"};
    }

    @Override
    public void empezarEvento() {
        setTexto("Aparece un dragón enorme delante tuya");
        interfaz.cambiarEscena(new UIEvento(Iconos.NIVEL3.getRutaIcono(),this));
        while (interfaz.botonPulsado() == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Combate c = new Combate(jugador,jefe, interfaz);
        interfaz.cambiarEscena(new UICombate(Iconos.NIVEL3.getRutaIcono(),jugador,new Enemigo[]{jefe}));
        c.empezarEvento();

        setTexto("Sigues adelante tras vencer al dragón, pero antes de que puedas seguir, escuchas un rugido detras tuya," +
                " el dragón se ha levantado de nuevo y se prepara para atacar");
        opciones = new String[] {"Seguir luchando"};
        interfaz.cambiarEscena(new UIEvento(Iconos.NIVEL3.getRutaIcono(),this));
        while (interfaz.botonPulsado() == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        jefe = new DragonFase2();
        interfaz.cambiarEscena(new UICombate(Iconos.NIVEL3.getRutaIcono(),jugador,new Enemigo[]{jefe}));
        c = new Combate(jugador,jefe, interfaz);
        c.empezarEvento();
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        System.out.println("Has ganado");
    }
}
