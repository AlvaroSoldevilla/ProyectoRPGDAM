package Modelo.Eventos;

import Modelo.Bases.Evento;
import UI.MenusConsola;

public class Tienda extends Evento {
    public Tienda() {
        titulo = "Combate";
    }
    @Override
    public void empezarEvento() {
        MenusConsola.menuTienda();
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        System.out.println("Sales de la tienda");
    }
}
