package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

public class EventoHerrero extends Aleatorio {
    public EventoHerrero(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo="André el herrero";

        texto = "A lo lejos escuchas el sonido de un martillo golpeando el acero. Hallaste a un viejo herrero.";

        opciones = new String[]{"Pedirle que refuerce tu arma","Pedirle que refuerce tu armadura","Saludarle e irte."};

        this.jugador = jugador;
    }
    @Override
    public void empezarEvento() {
        interfaz.actualizar();
        while (interfaz.botonPulsado()==-1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (interfaz.botonPulsado() != -1) {
                opcion = interfaz.botonPulsado();
            }
        }
        interfaz.reiniciarPulsado();
        switch (opcion) {
            case 0:
                setTexto("El herrero accedió");
                jugador.getArma().setDmg(jugador.getArma().getDmg() + 10);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 1:
                setTexto("El herrero accedió");
                jugador.getArmadura().setDefensa(jugador.getArmadura().getDefensa() + 10);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 2:
                setTexto("El herrero te saludó de vuelta y siguió trabajando");
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }

    @Override
    public void terminarEvento() {}

    private void esperar() {
        while (interfaz.botonPulsado()==-1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

