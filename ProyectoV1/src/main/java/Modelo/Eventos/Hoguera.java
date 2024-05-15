package Modelo.Eventos;

import Modelo.Bases.Evento;
import Modelo.Bases.Jugador;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

import java.util.Scanner;

public class Hoguera extends Evento {
    public Hoguera(Jugador j, Interfaz interfaz) {
        super(interfaz);
        titulo = "Hoguera";
        texto = "Encuentras una hoguera";
        this.jugador = j;
        icono = Iconos.HOGUERA;
    }

    @Override
    public void empezarEvento() {
        Scanner sc = new Scanner(System.in);
        switch (MenusConsola.menuHoghuera()) {
            case 1:
                jugador.restaurarVida();
                break;
            case 2:
                switch (MenusConsola.menuInventario()) {
                    case 1:
                        jugador.mostrarArmaduras();
                        jugador.cambiarArmadura(jugador.getArmaduras().get(sc.nextInt()));
                        break;
                    case 2:
                        jugador.mostrarArmas();
                        jugador.cambiarArma(jugador.getArmas().get(sc.nextInt()));
                        break;
                }
                break;
        }
    }

    @Override
    public void terminarEvento() {}
}
