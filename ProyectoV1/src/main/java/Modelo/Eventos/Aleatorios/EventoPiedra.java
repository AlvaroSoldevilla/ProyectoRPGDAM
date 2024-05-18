package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

public class EventoPiedra extends Aleatorio {
    public EventoPiedra(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        titulo="Dualidad del hombre";

        texto = "Hay una piedra en el camino. ¿Que quieres hacer?";

        opciones = new String[]{"Patearla","Tirarla al río","Pasar de largo"};

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
                setTexto("La piedra estaba muy dura y te hiciste daño. Pierdes 3 de vida.");
                interfaz.actualizar();
                opciones = new String[]{"Seguir"};
                jugador.setSalud(jugador.getSalud() - 3);
                esperar();
                break;
            case 1:
                setTexto("Lanzar la piedra al río te hace sentirte bien contigo mismo. Aumenta tu daño en 5");
                jugador.setDmg(jugador.getDmg() + 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 2:
                setTexto("Continúas tu camino");
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
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
