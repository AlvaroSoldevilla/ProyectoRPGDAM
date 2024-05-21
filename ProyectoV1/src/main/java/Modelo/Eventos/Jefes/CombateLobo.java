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

/**
 * La clase CombateLobo representa un evento de batalla contra el jefe "Hombre lobo".
 *
 * @autor Álvaro Soldevilla
 * @autor Diego Gonzalez
 */
public class CombateLobo extends BatallaConJefe {

    /**
     * Constructor para el evento de combate contra el jefe "Hombre lobo".
     *
     * @param jugador  El jugador que participa en el combate.
     * @param interfaz La interfaz del juego.
     */
    public CombateLobo(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Hombre lobo";
        this.jugador = jugador;
        jefe = new Lobo();
        opciones = new String[] {"Combatir"};
    }

    /**
     * Inicia el evento de combate contra el jefe "Hombre lobo".
     */
    @Override
    public void empezarEvento() {
        setTexto("Te encuentras frente a ti un hombre lobo que está decidido a acabar tu aventura");
        interfaz.cambiarEscena(new UIEvento(Iconos.NIVEL1.getRutaIcono(),this));
        // Espera a que el jugador pulse el botón para empezar el combate
        while (interfaz.botonPulsado() == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Inicia el combate contra el jefe "Hombre lobo"
        Combate c = new Combate(jugador,jefe, interfaz);
        interfaz.cambiarEscena(new UICombate(Iconos.NIVEL1.getRutaIcono(),jugador,new Enemigo[]{jefe}));
        c.empezarEvento();
        terminarEvento();
    }

    /**
     * Termina el evento de combate contra el jefe "Hombre lobo".
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
