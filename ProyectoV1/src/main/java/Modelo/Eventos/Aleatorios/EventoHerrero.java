package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

public class EventoHerrero extends Aleatorio {
    public EventoHerrero(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        texto = "A lo lejos escuchas el sonido de un martillo golpeando el acero. Hallaste a un viejo herrero.";

        opciones = new String[3];
        opciones[0] = "Pedirle que refuerce tu arma por 10 monedas de oro";
        opciones[1] = "Pedirle que refuerce tu armadura por 10 monedas de oro";
        opciones[2] = "Saludarle e irte.";

        this.jugador = jugador;
    }

    Jugador jugador;
    @Override
    public void empezarEvento() {
        int opcionElegida;

        System.out.println(texto);

        opcionElegida = MenusConsola.menuEventoAleatorio(opciones);

        switch (opcionElegida) {
            case 0:
                textoFinal = "El herrero accedió";
                jugador.getArma().setDmg(jugador.getArma().getDmg() + 10);
                break;
            case 1:
                textoFinal = "El herrero accedió";
                jugador.getArmadura().setDefensa(jugador.getArmadura().getDefensa() + 10);
                break;
            case 2:
                textoFinal = "El herrero te saludó de vuelta y siguió trabajando";
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }
}

