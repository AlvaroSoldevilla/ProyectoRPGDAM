package Logica;

import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Jugador.PruebaJugador;

public class Main {
    public static void main(String[] args) {
        Combate c = new Combate(new PruebaJugador(),new PruebaEnemigo());
        c.combate();
    }
}