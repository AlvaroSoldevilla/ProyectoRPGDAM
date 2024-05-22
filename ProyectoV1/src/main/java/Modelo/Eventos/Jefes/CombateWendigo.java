package Modelo.Eventos.Jefes;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Jefes.Wendigo;
import Modelo.Enums.Iconos;
import Modelo.Eventos.BatallaConJefe;
import Modelo.Eventos.Combate;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UICombate;
import UI.Interfaces.UIEvento;

/**
 * La clase CombateWendigo representa un evento de batalla contra el jefe "Wendigo".
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class CombateWendigo extends BatallaConJefe {

    /**
     * Constructor para el evento de combate contra el jefe "Wendigo".
     *
     * @param jugador  El jugador que participa en el combate.
     * @param interfaz La interfaz del juego.
     */
    public CombateWendigo(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Wendigo";
        this.jugador = jugador;
        jefe = new Wendigo();
        opciones = new String[] {"Combatir"};
    }

    /**
     * Inicia el evento de combate contra el jefe "Wendigo".
     */
    @Override
    public void empezarEvento() {
        setTexto("Encuentras un ser inexplicable, temes por tu vida");
        interfaz.cambiarEscena(new UIEvento(Iconos.NIVEL2.getRutaIcono(),this));
        // Espera a que el jugador pulse el botón para empezar el combate
        while (interfaz.botonPulsado() == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Inicia el combate contra el jefe "Wendigo"
        Combate c = new Combate(jugador,jefe, interfaz);
        interfaz.cambiarEscena(new UICombate(Iconos.NIVEL2.getRutaIcono(),jugador,new Enemigo[]{jefe}));
        c.empezarEvento();
        terminarEvento();
    }

    /**
     * Termina el evento de combate contra el jefe "Wendigo".
     */
    @Override
    public void terminarEvento() {}

    /**
     * Espera un tiempo determinado en milisegundos.
     *
     * @param tiempo El tiempo en milisegundos que se desea esperar.
     */
    public void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
