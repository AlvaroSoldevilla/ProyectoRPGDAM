package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import Modelo.Eventos.Combate;
import UI.MenusConsola;

public class EventoGitanos extends Aleatorio {
    public EventoGitanos(Jugador jugador, Enemigo enemigo,int nivel) {
        texto = "Illo dame todo tu dinero";

        opciones = new String[3];
        opciones[0] = "Dar todo tu dinero";
        opciones[1] = "Dar 5 de oro";
        opciones[2] = "Negarte y correr";
        opciones[3] = "Enfrentarlos";

        this.jugador = jugador;
        this.enemigo = enemigo;
        this.nivel = nivel;
    }

    Jugador jugador;
    Enemigo enemigo;
    int nivel;
    @Override
    public void empezarEvento() {
        int opcionElegida;

        System.out.println(texto);

        opcionElegida = MenusConsola.menuEventoAleatorio(opciones);

        switch (opcionElegida) {
            case 0:
                textoFinal = "Les diste todo tu oro y saliste ileso pero arruinado.";
                jugador.setOro(0);
                break;
            case 1:
                textoFinal = "Les diste pena y se fueron sin dudar que podías tener más oro.";
                jugador.setOro(jugador.getOro() - 5);
                if (jugador.getOro()<0) {
                    jugador.setOro(0);
                }
                break;
            case 2:
                textoFinal = "Saliste corriendo, uno de ellos te alcanzó por poco y te golpeó con su navaja, perdiste 6 de vida.";
                jugador.setSalud(jugador.getSalud() - 6);
                break;
            case 3:
                textoFinal = "Decidiste enfrentarlos.";
                Combate c = new Combate(jugador,enemigo,nivel);
                c.empezarEvento();
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }


}