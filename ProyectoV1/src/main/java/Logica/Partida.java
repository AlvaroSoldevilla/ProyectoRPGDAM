package Logica;

import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Eventos.RecompensaEspecial;
import Modelo.Eventos.Tienda;
import Modelo.Mundo.Mapa;
import UI.Interfaces.ElegirCamino;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UIEvento;
import UI.MenusConsola;
import UI.MenusInterfaz;
import lombok.Data;

@Data
public class Partida {
    Jugador jugador;
    Mapa mapa;
    Interfaz interfaz = new Interfaz();

    public void iniciar() {

        while (interfaz.getBotonPulsado()==-1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (interfaz.getBotonPulsado()!=-1) {
                jugador = MenusInterfaz.menuEleccionJugador(interfaz.getBotonPulsado());
                interfaz.setBotonPulsado(-1);
            }
        }

        Mapa mapa1 = new Mapa(jugador,interfaz);
        Evento[] eventosActuales;
        Evento evento;


        while (!jugador.estaMuerto()) {
            eventosActuales = mapa1.avanzarSala();
            interfaz.cambiarEscena(new ElegirCamino(eventosActuales,eventosActuales.length-1));
            if (eventosActuales != null) {
                while (interfaz.getBotonPulsado() == -1) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (interfaz.getBotonPulsado() != -1) {
                        evento = MenusInterfaz.menuElegirEvento(eventosActuales, interfaz.getBotonPulsado());
                        interfaz.setBotonPulsado(-1);
                        evento.empezarEvento();
                    }
                }
            }
        }
        System.out.println("Has muerto");
    }
}
