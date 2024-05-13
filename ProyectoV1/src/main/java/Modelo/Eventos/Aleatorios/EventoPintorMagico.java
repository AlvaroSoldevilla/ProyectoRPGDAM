package Modelo.Eventos.Aleatorios;

import Modelo.Armas.Palo;
import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

public class EventoPintorMagico extends Aleatorio {
    public EventoPintorMagico(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        texto = "Encuentras a un pintor plasmando sus pensamientos con pintura mágica en un lienzo. El pintor al verte dice que serías un buen lienzo para su arte.";

        opciones = new String[5];
        opciones[0] = "Dejarte pintar de rojo";
        opciones[1] = "Dejarte pintar de azul";
        opciones[2] = "Dejarte pintar de gris";
        opciones[3] = "Dejarte pintar de verde";
        opciones[4] = "No dejarte pintar";

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
                textoFinal = "Ver tu arma pintada de rojo hace que te hierva la sangre. Aumenta el daño en 5" ;
                jugador.setDmg(jugador.getDmg() + 5);
                break;
            case 1:
                textoFinal = "Sientes la pintura azul en tu frente. Sientes más conocimiento. Aumenta el maná máximo en 5";
                jugador.setMaxMana(jugador.getMaxMana() + 5);
                break;
            case 2:
                textoFinal = "Tu armadura fue tintada de gris. Te sientes más seguro. Aumenta tu defensa en 5";
                jugador.setDefensa(jugador.getDefensa() + 5);
                break;
            case 3:
                textoFinal = "Tu cuello es pintado de verde. Te sientes más vivo. Aumenta la vida máxima en 5";
                jugador.setMaxSalud(jugador.getMaxSalud() + 5);
                break;
            case 4:
                textoFinal = "Te negaste a ser pintado. El pintor se despide en alemán?...";
                break;
        }

        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }


}

