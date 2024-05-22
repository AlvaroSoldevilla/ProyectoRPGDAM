package Logica;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Enums.Iconos;
import Modelo.Eventos.BatallaConJefe;
import Modelo.Eventos.Combate;
import Modelo.Eventos.Jefes.CombateWendigo;
import Modelo.Jugador.Asesino;
import Modelo.Jugador.Caballero;
import Modelo.Jugador.Mago;
import Modelo.Mundo.Mapa;
import UI.Interfaces.ElegirCamino;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UICombate;
import UI.Interfaces.UIEvento;
import lombok.Data;

/**
 * La clase Partida es la clase principal del juego.
 * Gestiona el bucle principal del programa y contiene los elementos principales:
 * El jugador, el mapa y la Interfaz
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public class Partida {
    Jugador jugador;
    Mapa mapa;
    Interfaz interfaz = new Interfaz();

    /**
     * Inicia la partida.
     * Crea el mapa, deja elegir el jugador y va generando eventos y gestionando la partida hasta que el jugador muere
     */
    public void iniciar() {

        while (interfaz.botonPulsado()==-1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (interfaz.botonPulsado()!=-1) {
                switch (interfaz.botonPulsado()) {
                    case 1 -> jugador = new Caballero();
                    case 2 -> jugador = new Mago();
                    case 3 -> jugador = new Asesino();
                };
            }
        }
        interfaz.reiniciarPulsado();

        Mapa mapa = new Mapa(jugador,interfaz);
        Evento[] eventosActuales;
        Evento evento;
        String rutaFondo = "";

        while (!jugador.estaMuerto()) {
            eventosActuales = mapa.avanzarSala();
            switch (mapa.getNivelActual()) {
                case 1:
                    rutaFondo = Iconos.NIVEL1.getRutaIcono();
                    break;
                case 2:
                    rutaFondo = Iconos.NIVEL2.getRutaIcono();
                    break;
                case 3:
                    rutaFondo = Iconos.NIVEL3.getRutaIcono();
                    break;
            }

            interfaz.cambiarEscena(new ElegirCamino(rutaFondo,jugador,eventosActuales));
            while (interfaz.botonPulsado() == -1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (interfaz.botonPulsado() != -1) {
                    evento = eventosActuales[interfaz.botonPulsado()];
                    interfaz.reiniciarPulsado();
                    if (evento instanceof Combate combate) {
                        interfaz.cambiarEscena(new UICombate(rutaFondo,jugador,new Enemigo[]{combate.getEnemigo()}));
                    } else if (evento instanceof BatallaConJefe combate) {
                        interfaz.cambiarEscena(new UICombate(rutaFondo,jugador,new Enemigo[]{combate.getJefe()}));
                    } else {
                        interfaz.cambiarEscena(new UIEvento(rutaFondo,evento));
                    }
                    interfaz.reiniciarPulsado();
                    evento.empezarEvento();
                }
            }
            interfaz.reiniciarPulsado();
        }
    }

    public static void main(String[] args) {
        Partida partida;
        while (true) {
            partida = new Partida();
            partida.iniciar();
        }
    }
}
