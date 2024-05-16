package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;

public class EventoPintorMagico extends Aleatorio {
    public EventoPintorMagico(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        texto = "Encuentras a un pintor plasmando sus pensamientos con pintura mágica en un lienzo. El pintor al verte dice que serías un buen lienzo para su arte.";

        opciones = new String[]{"Dejarte pintar de rojo","Dejarte pintar de azul","Dejarte pintar de gris","Dejarte pintar de verde","No dejarte pintar"};

        this.jugador = jugador;
    }

    Jugador jugador;
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
                setTexto("Ver tu arma pintada de rojo hace que te hierva la sangre. Aumenta el daño en 5");
                jugador.setDmg(jugador.getDmg() + 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                break;
            case 1:
                setTexto("Sientes la pintura azul en tu frente. Sientes más conocimiento. Aumenta el maná máximo en 5");
                jugador.setMaxMana(jugador.getMaxMana() + 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                break;
            case 2:
                setTexto("Tu armadura fue tintada de gris. Te sientes más seguro. Aumenta tu defensa en 5");
                jugador.setDefensa(jugador.getDefensa() + 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                break;
            case 3:
                setTexto("Tu cuello es pintado de verde. Te sientes más vivo. Aumenta la vida máxima en 5");
                jugador.setMaxSalud(jugador.getMaxSalud() + 5);
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                break;
            case 4:
                setTexto("Te negaste a ser pintado.");
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                break;
        }

        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }


}

