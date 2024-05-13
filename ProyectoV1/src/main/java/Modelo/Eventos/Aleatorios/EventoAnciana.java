package Modelo.Eventos.Aleatorios;

import Modelo.Armas.Palo;
import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import UI.Interfaces.Interfaz;
import UI.MenusConsola;

public class EventoAnciana extends Aleatorio {
    public EventoAnciana(Jugador jugador, Interfaz interfaz) {
        super(interfaz);
        texto = "En la distancia, avistas a una anciana tendida en el suelo. Te acercas a ella con paso firme.";
        opciones = new String[3];
        opciones[0] = "Ayudas a la anciana a levantarse";
        opciones[1] = "Pasas de largo";

        this.jugador = jugador;
    }

    Jugador jugador;
    Enemigo enemigo;
    @Override
    public void empezarEvento() {
        int opcionElegida;


        System.out.println(texto);

        opcionElegida = MenusConsola.menuEventoAleatorio(opciones);


        switch (opcionElegida) {
            case 0:
                textoFinal = "Ayudaste a la anciana a levantarse, te lo agradece pero te pide que la acompañes hasta su hogar" ;
                opciones = new String[3];
                opciones[0] = "Acompañarla";
                opciones[1] = "Darle oro para pagar a una carreta que pase por allí";
                opciones[2] = "Negarte, estás muy ocupado actualmente";
                opcionElegida = MenusConsola.menuEventoAleatorio(opciones);
                switch (opcionElegida){
                    case 0:
                        textoFinal = "Tras acompañarla a su hogar te entrega unas galletas por tu amabilidad. Te curas 8 de vida" ;
                        jugador.curarVida(8);
                        break;

                    case 1:
                        textoFinal = "La anciana se queda extrañada y te observa alejandote, tal vez no pase nadie de buena fé que ayude a la anciana..." ;
                        jugador.setOro(jugador.getOro() - 5);
                        if (jugador.getOro()<0) {
                            jugador.setOro(0);
                        }
                        break;

                    case 2:
                        textoFinal = "La anciana muestra tristeza pero lo entiende. Se despide de ti" ;

                        break;
               }
                break;
            case 1:
                textoFinal = "Decidiste pasar de largo. La anciana al verte ignorarla te tira una maldición por tu falta de bondad";
                jugador.setMana(jugador.getMana() - 5);
                break;
        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }


}
