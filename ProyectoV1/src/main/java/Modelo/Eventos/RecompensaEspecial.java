package Modelo.Eventos;

import Modelo.Accesorios.*;
import Modelo.Bases.Accesorio;
import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

import java.util.Random;

public class RecompensaEspecial extends Evento {
    public RecompensaEspecial(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Tesoro";
        this.jugador = jugador;
    }

    Jugador jugador;

    @Override
    public void empezarEvento() {
        System.out.println("Se te ofrecen tres accesorios");
        Accesorio[] accesorios = new Accesorio[3];
        String[] opciones = new String[3];
        accesorios[0] = generarAccesorio();
        accesorios[1] = generarAccesorio();
        while (accesorios[1].getNombre().equals(accesorios[0].getNombre())) {
            accesorios[1] = generarAccesorio();
        }
        accesorios[2] = generarAccesorio();
        while (accesorios[2].getNombre().equals(accesorios[0].getNombre()) || accesorios[2].getNombre().equals(accesorios[1].getNombre())) {
            accesorios[2] = generarAccesorio();
        }

        for (int i = 0; i < accesorios.length; i++) {
            opciones[i] = accesorios[i].getNombre();
        }

        int elegido = MenusConsola.menuEventoAleatorio(opciones);

        jugador.addAccesorio(accesorios[elegido]);
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        System.out.println("Continuas con tu camino");
    }

    private Accesorio generarAccesorio() {
        Random rng = new Random();
        return switch (rng.nextInt(0, 4)) {
            case 0 -> new Antiveneno();
            case 1 -> new MonedaOro();
            case 2 -> new Antirayos();
            case 3 -> new Antifuego();
            default -> null;
        };
    }
}
