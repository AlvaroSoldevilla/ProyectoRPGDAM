package Logica;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Enums.Iconos;
import Modelo.Eventos.BatallaConJefe;
import Modelo.Eventos.Combate;
import Modelo.Eventos.Hoguera;
import Modelo.Eventos.Jefes.CombateWendigo;
import Modelo.Mundo.Mapa;
import UI.Interfaces.ElegirCamino;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UICombate;
import UI.Interfaces.UIEvento;
import UI.MenusInterfaz;
import lombok.Data;

@Data
public class Partida {
    Jugador jugador;
    Mapa mapa;
    Interfaz interfaz = new Interfaz();

    public void iniciar() {

        while (interfaz.botonPulsado()==-1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (interfaz.botonPulsado()!=-1) {
                jugador = MenusInterfaz.menuEleccionJugador(interfaz.botonPulsado());
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
                    evento = MenusInterfaz.menuElegirEvento(eventosActuales, interfaz.botonPulsado());
                    interfaz.reiniciarPulsado();
                    if (evento instanceof CombateWendigo combateWendigo) {
                        interfaz.cambiarEscena(new UICombate(rutaFondo,jugador, combateWendigo.getEnemigos()));
                    } else if (evento instanceof Combate combate) {
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
}
