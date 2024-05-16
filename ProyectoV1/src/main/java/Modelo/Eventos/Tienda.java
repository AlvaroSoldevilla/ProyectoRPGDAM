package Modelo.Eventos;

import Modelo.Equipamiento.Accesorios.MasArmadura;
import Modelo.Equipamiento.Accesorios.MasDmg;
import Modelo.Equipamiento.Accesorios.MasMana;
import Modelo.Equipamiento.Accesorios.MasVida;
import Modelo.Equipamiento.Armaduras.ArmaduraDragon;
import Modelo.Equipamiento.Armaduras.ArmaduraEspinas;
import Modelo.Equipamiento.Armaduras.ArmaduraMetal;
import Modelo.Bases.*;
import Modelo.Equipamiento.Armas.*;
import Modelo.Jugador.Asesino;
import Modelo.Jugador.Caballero;
import Modelo.Jugador.Mago;
import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

import java.util.Random;
import java.util.Scanner;

public class Tienda extends Evento {
    public Tienda(Jugador jugador, int nivel, Interfaz interfaz) {
        super(interfaz);
        titulo = "Tienda";
        this.jugador = jugador;
        this.nivel = nivel;
        icono = Iconos.TIENDA;
        opciones = new String[4];
    }
    Random rng = new Random();
    Jugador jugador;
    int nivel;

    @Override
    public void empezarEvento() {

        int compra;
        boolean compraRealizada = false;

        Equipamiento equipamiento;
        Accesorio accesorio;
        int mejora = 0;

        String estadisticaMejora = "";
        int estadistica;

        equipamiento = generarEquipamiento();
        accesorio = generarAccesorio();

        estadistica = rng.nextInt(0,4);
        switch (estadistica) {
            case 0:
                estadisticaMejora = "Salud";
                mejora = rng.nextInt(5*nivel,10*nivel);
                break;
            case 1:
                estadisticaMejora = "Defensa";
                mejora = rng.nextInt(2*nivel,6*nivel);
                break;
            case 2:
                estadisticaMejora = "Daño base";
                mejora = rng.nextInt(3*nivel,6*nivel);
                break;
            case 3:
                estadisticaMejora = "Maná";
                mejora = rng.nextInt(6*nivel,15*nivel);
                break;
        }

        //Valores provisionales
        int precio1 = rng.nextInt(20*nivel,50*nivel);
        int precio2 = rng.nextInt(20*nivel,50*nivel);
        int precio3 = rng.nextInt(20*nivel,50*nivel);

        do {
            System.out.println("Tu oro: " + jugador.getOro());
            compra = MenusConsola.menuTienda(equipamiento.getNombre(), precio1, accesorio.getNombre(), precio2, estadisticaMejora, mejora, precio3);
            switch (compra) {
                case 1:
                    if (jugador.getOro() >= precio1) {
                        Scanner sc = new Scanner(System.in);
                        int accion;
                        jugador.gastarOro(precio1);
                        if (equipamiento instanceof Arma arma) {
                            System.out.println("¿Quieres cambiar tu arma actual? 1-Si 0-No");
                            accion = sc.nextInt();
                            if (accion == 1) {
                                jugador.cambiarArma(arma);
                            } else {
                                jugador.guardarArma(arma);
                            }
                        } else if (equipamiento instanceof Armadura armadura) {
                            System.out.println("¿Quieres cambiar tu armadura actual? 1-Si 0-No");
                            accion = sc.nextInt();
                            if (accion == 1) {
                                jugador.cambiarArmadura(armadura);
                            } else {
                                jugador.guardarArmadura(armadura);
                            }
                        }
                        compraRealizada = true;
                    } else {
                        //TODO: Cambiar a mensaje en la GUI
                        System.out.println("No tienes suficiente oro");
                    }
                    break;
                case 2:
                    if (jugador.getOro() >= precio2) {
                        jugador.gastarOro(precio2);
                        jugador.addAccesorio(accesorio);
                        compraRealizada = true;
                    } else {
                        //TODO: Cambiar a mensaje en la GUI
                        System.out.println("No tienes suficiente oro");
                    }
                    break;
                case 3:
                    if (jugador.getOro() >= precio3) {
                        jugador.gastarOro(precio3);
                        switch (estadistica) {
                            case 0:
                                jugador.setMaxSalud(jugador.getMaxSalud() + mejora);
                                jugador.curarVida(mejora);
                                break;
                            case 1:
                                jugador.setDefensa(jugador.getDefensa() + mejora);
                                jugador.setDefensaBase(jugador.getDefensa());
                                break;
                            case 2:
                                jugador.setDmg(jugador.getDmg() + mejora);
                                jugador.setDmgBase(jugador.getDmg());
                                break;
                            case 3:
                                jugador.setMaxMana(jugador.getMaxMana() + mejora);
                                jugador.restaurarMana();
                                break;
                        }
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
        return switch (rng.nextInt(0, 4)) {
            case 0 -> new MasArmadura();
            case 1 -> new MasDmg();
            case 2 -> new MasMana();
            case 3 -> new MasVida();
            default -> null;
        };
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
                            return new DagaCritica();
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
                            return switch (rng.nextInt(0, 3)) {
                                case 0 -> new Matadragones();
                                case 1 -> new BastonMaldito();
                                case 2 -> new DagaCritica();
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
