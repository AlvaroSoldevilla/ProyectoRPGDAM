package Modelo.Eventos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Jefes.Lobo;
import Modelo.Enums.Iconos;
import Modelo.Eventos.BatallaConJefe;
import Modelo.Eventos.Combate;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UICombate;
import UI.Interfaces.UIEvento;

public class CombateLobo extends BatallaConJefe {

    public CombateLobo(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Hombre lobo";
        this.jugador = jugador;
        jefe = new Lobo();
        opciones = new String[] {"Combatir"};
    }
    @Override
    public void empezarEvento() {
        setTexto("Te encuentras frente a ti un hombre lobo que decidido a acabar tu aventura");
        interfaz.cambiarEscena(new UIEvento(Iconos.NIVEL1.getRutaIcono(),this));
        while (interfaz.botonPulsado() == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Combate c = new Combate(jugador,jefe, interfaz);
        interfaz.cambiarEscena(new UICombate(Iconos.NIVEL1.getRutaIcono(),jugador,new Enemigo[]{jefe}));
        c.empezarEvento();
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        System.out.println("Has ganado");
    }
}
