package Modelo.Mundo;

import Logica.Combate;
import Logica.Partida;
import Modelo.Enemigos.PruebaEnemigo;
import Modelo.Eventos.Hoguera;
import lombok.Data;

import java.util.Random;

@Data
public class Mapa {
    int nivelActual;
    int sala;

    void avanzarSala() {
        if (distanciaAJefe() == 1) {
            generarEvento(5);
        } else {
            generarEvento();
        }
        sala++;
    }

    int distanciaAJefe() {
        return (5+(2*nivelActual)) - sala;
    }

    void generarEvento() {
        Random rng = new Random();
        int codEvento = rng.nextInt(0,4);

        switch (codEvento) {
            case 0:
                //Combate
                Combate c = new Combate(Partida.getJugador(),new PruebaEnemigo());
                c.combate();
                break;
            case 1:
                //Evento aleatorio
                break;
            case 2:
                //Tienda
                break;
            case 3:
                //Hoguera
                Hoguera h = new Hoguera(Partida.getJugador());
                h.empezarEvento();
                break;
        }
        avanzarSala();
    }

    void generarEvento(int codEvento) {
        switch (codEvento) {
            case 0:
                //Combate
                Combate c = new Combate(Partida.getJugador(),new PruebaEnemigo());
                c.combate();
                break;
            case 1:
                //Evento aleatorio
                break;
            case 2:
                //Tienda
                break;
            case 3:
                //Hoguera
                Hoguera h = new Hoguera(Partida.getJugador());
                h.empezarEvento();
                break;
            case 4:
                //Recompensa especial
                break;
            case 5:
                //Jefe
                break;
        }
        avanzarSala();
    }
}