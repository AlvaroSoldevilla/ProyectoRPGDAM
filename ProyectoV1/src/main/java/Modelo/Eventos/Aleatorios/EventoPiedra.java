package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

public class EventoPiedra extends Aleatorio {
    public EventoPiedra(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        texto = "Hay una piedra en el camino. ¿Que quieres hacer?";

        opciones = new String[3];
        opciones[0] = "Patearla";
        opciones[1] = "Tirarla al río";
        opciones[2] = "Pasar de largo";

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
                textoFinal = "La piedra estaba muy dura y te hiciste daño. Pierdes 3 de vida.";
                jugador.setSalud(jugador.getSalud() - 3);
                break;
            case 1:
                textoFinal = "Lanzar la piedra al río te hace sentirte bien contigo mismo. Aumenta tu daño en 5";
                jugador.setDmg(jugador.getDmg() + 5);
                break;
            case 2:
                textoFinal = "Continúas tu camino";
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }
}
