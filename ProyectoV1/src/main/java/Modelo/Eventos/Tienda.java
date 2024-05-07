package Modelo.Eventos;

import Modelo.Accesorios.Antifuego;
import Modelo.Accesorios.Antirayos;
import Modelo.Accesorios.Antiveneno;
import Modelo.Accesorios.MonedaOro;
import Modelo.Armaduras.ArmaduraDragon;
import Modelo.Armaduras.ArmaduraEspinas;
import Modelo.Armaduras.ArmaduraMetal;
import Modelo.Armas.*;
import Modelo.Bases.*;
import Modelo.Jugador.Asesino;
import Modelo.Jugador.Caballero;
import Modelo.Jugador.Mago;
import UI.MenusConsola;

import java.util.Random;

public class Tienda extends Evento {
    public Tienda(Jugador jugador,int nivel) {
        titulo = "Tienda";
        this.jugador = jugador;
        this.nivel = nivel;
    }
    Random rng = new Random();
    Jugador jugador;
    int nivel;

    @Override
    public void empezarEvento() {

        int compra;
        boolean compraRealizada = false;

        Equipamiento equipamiento;
        Accesorio accesorio = null;
        int mejora;

        String estadisticaMejora = "";

        equipamiento = generarEquipamiento();
        accesorio = generarAccesorio();
        //Valores provisionales
        int precio1 = rng.nextInt(20*nivel,50*nivel);
        int precio2 = rng.nextInt(20*nivel,50*nivel);
        int precio3 = rng.nextInt(20*nivel,50*nivel);

        do {
            System.out.println("Tu oro: " + jugador.getOro());
            compra = MenusConsola.menuTienda(equipamiento.getNombre(), precio1, accesorio.getNombre(), precio2, estadisticaMejora, precio3);
            switch (compra) {
                case 1:
                    if (jugador.getOro() >= precio1) {
                        jugador.gastarOro(precio1);
                        compraRealizada = true;
                    } else {
                        //TODO: Cambiar a mensaje en la GUI
                        System.out.println("No tienes suficiente oro");
                    }
                    break;
                case 2:
                    if (jugador.getOro() >= precio2) {
                        jugador.gastarOro(precio2);
                        compraRealizada = true;
                    } else {
                        //TODO: Cambiar a mensaje en la GUI
                        System.out.println("No tienes suficiente oro");
                    }
                    break;
                case 3:
                    if (jugador.getOro() >= precio3) {
                        jugador.gastarOro(precio3);
                        compraRealizada = true;
                    } else {
                        //TODO: Cambiar a mensaje en la GUI
                        System.out.println("No tienes suficiente oro");
                    }
                    break;
            }
        } while (compra!=0 && !compraRealizada);
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        System.out.println("Sales de la tienda");
    }

    private Accesorio generarAccesorio() {
        switch (rng.nextInt(0,4)) {
            case 0:
                return new Antiveneno();
            case 1:
                return new MonedaOro();
            case 2:
                return new Antirayos();
            case 3:
                return new Antifuego();
        }
        return null;
    }

    private Equipamiento generarEquipamiento() {
        switch (rng.nextInt(0,2)) {
            case 0:
                if (jugador instanceof Caballero) {
                    switch (nivel) {
                        case 1:
                            return new EspadaSerpiente();
                        case 2:
                            return new EspadaGwyn();
                        case 3:
                            return new Matadragones();
                    }
                } else if (jugador instanceof Mago) {
                    switch (nivel) {
                        case 1:
                            return new BastonCurativo();
                        case 2:
                            return new BastonCongelado();
                        case 3:
                            return new BastonMaldito();
                    }
                } else if (jugador instanceof Asesino) {
                    //TODO: Implementar armas del asesino
                    switch (nivel) {
                        case 1:
                            return new DagaDoble();
                        case 2:
                            return new DagaRoboVida();
                        case 3:
                            return null;
                    }
                } else {
                    switch (nivel) {
                        case 1:
                            return switch (rng.nextInt(0, 3)) {
                                case 0 -> new EspadaSerpiente();
                                case 1 -> new BastonCurativo();
                                case 3 -> new DagaDoble();
                                default -> null;
                            };
                        case 2:
                            return switch (rng.nextInt(0, 3)) {
                                case 0 -> new EspadaGwyn();
                                case 1 -> new BastonCongelado();
                                case 2 -> new DagaRoboVida();
                                default -> null;
                            };
                        case 3:
                            return switch (rng.nextInt(0, 2)) {
                                case 0 -> new Matadragones();
                                case 1 -> new BastonMaldito();
                                default -> null;
                            };
                    }
                }
                break;
            case 1:
                switch (nivel) {
                    case 1:
                        if (rng.nextInt(0, 3) == 1) {
                            return new ArmaduraEspinas();
                        }
                        return new ArmaduraMetal();
                    case 2:
                        switch (rng.nextInt(0,2)) {
                            case 1:
                                return new ArmaduraEspinas();
                            case 2:
                                return new ArmaduraDragon();
                        }
                    case 3:
                        if (rng.nextInt(0, 3) == 1) {
                            return new ArmaduraDragon();
                        }
                        return new ArmaduraMetal();
                }
                break;
        }
        return null;
    }

}
