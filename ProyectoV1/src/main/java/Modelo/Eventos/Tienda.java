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
    int nivel;

    @Override
    public void empezarEvento() {

        int compra = 0;
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

        //Valores de oro provisionales
        int precio1 = rng.nextInt(20 * nivel,50 * nivel);
        int precio2 = rng.nextInt(20 * nivel,50 * nivel);
        int precio3 = rng.nextInt(20 * nivel,50 * nivel);

        setTexto("¿Que quieres comprar?");

        do {
            opciones = new String[] {
                    "Salir",
                    equipamiento.getNombre() + " " + precio1,
                    accesorio.getNombre() + " " + precio2,
                    estadisticaMejora + " " + precio3
            };
            interfaz.actualizar();
            System.out.println("Tu oro: " + jugador.getOro());
            interfaz.reiniciarPulsado();
            while (interfaz.botonPulsado() == -1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (interfaz.botonPulsado() != -1) {
                    compra = interfaz.botonPulsado();
                }
            }

            switch (compra) {
                case 1:
                    if (jugador.getOro() >= precio1) {
                        int accion = -1;
                        jugador.gastarOro(precio1);
                        if (equipamiento instanceof Arma arma) {
                            setTexto("¿Quieres cambiar tu arma actual?");
                            opciones = new String[] {"Si", "No"};
                            interfaz.actualizar();
                            interfaz.reiniciarPulsado();
                            while (interfaz.botonPulsado() == -1) {
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                if (interfaz.botonPulsado() != -1) {
                                    accion = interfaz.botonPulsado();
                                }
                            }
                            if (accion == 0) {
                                jugador.cambiarArma(arma);
                            } else {
                                jugador.guardarArma(arma);
                            }
                        } else if (equipamiento instanceof Armadura armadura) {
                            System.out.println("¿Quieres cambiar tu armadura actual? 1-Si 0-No");
                            opciones = new String[] {"Si", "No"};
                            interfaz.actualizar();
                            interfaz.reiniciarPulsado();
                            while (interfaz.botonPulsado() == -1) {
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                if (interfaz.botonPulsado() != -1) {
                                    accion = interfaz.botonPulsado();
                                }
                            }
                            if (accion == 0) {
                                jugador.cambiarArmadura(armadura);
                            } else {
                                jugador.guardarArmadura(armadura);
                            }
                        }
                        compraRealizada = true;
                    } else {
                        setTexto("No tienes suficiente oro");
                    }
                    break;
                case 2:
                    if (jugador.getOro() >= precio2) {
                        jugador.gastarOro(precio2);
                        jugador.addAccesorio(accesorio,interfaz);
                        compraRealizada = true;
                    } else {
                        setTexto("No tienes suficiente oro");
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
                        setTexto("No tienes suficiente oro");
                    }
                    break;
            }
        } while (compra!=0 && !compraRealizada);
        terminarEvento();
    }

    @Override
    public void terminarEvento() {
        opciones = new String[]{"Seguir"};
        setTexto("Sales de la tienda");
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
