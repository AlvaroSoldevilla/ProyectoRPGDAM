package Modelo.Eventos;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Enums.Iconos;
import Modelo.Equipamiento.Accesorios.Antifuego;
import Modelo.Equipamiento.Accesorios.Antirayos;
import Modelo.Equipamiento.Accesorios.Antiveneno;
import Modelo.Equipamiento.Accesorios.MonedaOro;
import UI.Interfaces.Interfaz;
import UI.Interfaces.UIEvento;
import UI.MenusConsola;

import java.util.Random;

public class RecompensaEspecial extends Evento {
    public RecompensaEspecial(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo = "Tesoro";
        texto = "Se te ofrecen tres accesorios";
        this.jugador = jugador;
        icono = Iconos.TESORO;
    }

    Jugador jugador;

    @Override
    public void empezarEvento() {
        Accesorio[] accesorios = new Accesorio[3];
        opciones = new String[3];
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
        interfaz.cambiarEscena(new UIEvento(interfaz.getFondo(),this));
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
