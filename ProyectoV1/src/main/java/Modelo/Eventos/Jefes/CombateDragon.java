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

/**
 * La clase CombateDragon representa un evento de batalla contra el jefe "Dragón".
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class CombateDragon extends BatallaConJefe {

    /**
     * Constructor para el evento de combate contra el jefe "Dragón".
     *
     * @param jugador  El jugador que participa en el combate.
     * @param interfaz La interfaz del juego.
     */
    public CombateDragon(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Dragon";
        this.jugador = jugador;
        jefe = new DragonFase1();
        opciones = new String[] {"Combatir"};
    }

    /**
     * Inicia el evento de combate contra el jefe "Dragón".
     */
    @Override
    public void empezarEvento() {
        setTexto("Aparece un dragón enorme delante tuya");
        interfaz.cambiarEscena(new UIEvento(Iconos.NIVEL3.getRutaIcono(),this));
        // Espera a que el jugador pulse el botón para empezar el combate
        while (interfaz.botonPulsado() == -1) {
            esperar(10);
        }
        // Inicia el combate contra el jefe "Dragón" en su primera fase
        Combate c = new Combate(jugador,jefe, interfaz);
        interfaz.cambiarEscena(new UICombate(Iconos.NIVEL3.getRutaIcono(),jugador,new Enemigo[]{jefe}));
        c.empezarEvento();

        // Después de derrotar al dragón en su primera fase, se muestra un mensaje y se prepara para la segunda fase
        setTexto("Sigues adelante tras vencer al dragón. pero antes de que puedas seguir, escuchas un rugido detras tuya,\n" +
                " el dragón se ha levantado de nuevo y se prepara para atacar");
        opciones = new String[] {"Seguir luchando"};
        interfaz.cambiarEscena(new UIEvento(Iconos.NIVEL3.getRutaIcono(),this));
        // Espera a que el jugador pulse el botón para continuar luchando
        while (interfaz.botonPulsado() == -1) {
            esperar(10);
        }
        // Inicia el combate contra el jefe "Dragón" en su segunda fase
        jefe = new DragonFase2();
        interfaz.cambiarEscena(new UICombate(Iconos.NIVEL3.getRutaIcono(),jugador,new Enemigo[]{jefe}));
        c = new Combate(jugador,jefe, interfaz);
        c.empezarEvento();
        terminarEvento();
    }

    /**
     * Termina el evento de combate contra el jefe "Dragón".
     */
    @Override
    public void terminarEvento() {
        // Muestra un mensaje de felicitaciones al jugador por completar el juego
        setTitulo("¡¡Felidicades!!");
        setTexto("Has Terminado el juego. ¡¡Felicidades!!");
        opciones = new String[]{"Terminar Juego"};
        interfaz.cambiarEscena(new UIEvento(Iconos.NIVEL3.getRutaIcono(),this));
        // Espera a que el jugador pulse el botón para terminar el juego
        while (interfaz.botonPulsado() == -1) {
            esperar(10);
        }
    }

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
