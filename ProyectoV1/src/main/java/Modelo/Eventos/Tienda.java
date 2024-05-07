package Modelo.Eventos;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Arma;
import Modelo.Bases.Armadura;
import Modelo.Bases.Evento;
import UI.MenusConsola;

public class Tienda extends Evento {
    public Tienda() {
        titulo = "Tienda";
    }
    @Override
    public void empezarEvento() {
        Arma arma;
        Armadura armadura;
        Accesorio accesorio1;
        Accesorio accesorio2;
        int mejora;
        String estadisticaMejora;
        MenusConsola.menuTienda("","","");
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        System.out.println("Sales de la tienda");
    }


}
