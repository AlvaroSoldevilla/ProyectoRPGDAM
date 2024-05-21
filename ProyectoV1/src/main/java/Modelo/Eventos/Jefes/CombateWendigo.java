package Modelo.Eventos.Jefes;

import Modelo.Equipamiento.Armas.DagaCritica;
import Modelo.Equipamiento.Armas.DagaDoble;
import Modelo.Equipamiento.Armas.DagaRoboVida;
import Modelo.Bases.AtaqueEspecial;
import Modelo.Bases.Enemigo;
import Modelo.Bases.Entidad;
import Modelo.Bases.Jugador;
import Modelo.Enemigos.Perro;
import Modelo.Enemigos.Jefes.Wendigo;
import Modelo.Enums.Iconos;
import Modelo.Eventos.BatallaConJefe;
import Modelo.Eventos.Combate;
import Modelo.Jugador.Asesino;
import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UICombate;
import UI.Interfaces.UIEvento;
import lombok.Getter;

import java.util.HashMap;
import java.util.Random;

public class CombateWendigo extends BatallaConJefe {

    public CombateWendigo(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Wendigo";
        this.jugador = jugador;
        jefe = new Wendigo();
        opciones = new String[] {"Combatir"};
    }


    @Override
    public void empezarEvento() {
        setTexto("Encuentras un ser inexplicable, temes por tu vida");
        interfaz.cambiarEscena(new UIEvento(Iconos.NIVEL2.getRutaIcono(),this));
        while (interfaz.botonPulsado() == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Combate c = new Combate(jugador,jefe, interfaz);
        interfaz.cambiarEscena(new UICombate(Iconos.NIVEL2.getRutaIcono(),jugador,new Enemigo[]{jefe}));
        c.empezarEvento();
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        interfaz.imprimirMensaje("Has Terminado el juego. ¡¡Felicidades!!");
        interfaz.actualizar();
        esperar(1000);
    }

    public void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}