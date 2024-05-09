package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import Modelo.Eventos.Combate;
import UI.MenusConsola;

public class EventoNerd extends Aleatorio {
    public EventoNerd(Jugador jugador , Enemigo enemigo, int nivel) {
        texto = "En tu camino te topas con un chico con gafas que llora. ¿Qué deberías hacer?";

        opciones = new String[3];
        opciones[0] = "Preguntarle que le pasa";
        opciones[1] = "Robarle";
        opciones[2] = "Ignorarlo";

        this.jugador = jugador;
        this.enemigo = enemigo;
        this.nivel = nivel;
    }

    Jugador jugador;
    Enemigo enemigo;
    int nivel;

    @Override
    public void empezarEvento() {
        int opcionElegida;

        System.out.println(texto);

        opcionElegida = MenusConsola.menuEventoAleatorio(opciones);

        switch (opcionElegida) {
            case 0:
                textoFinal = "El chico dice que unos chicos se burlaron de él por su aspecto y por como habla" ;
                opciones = new String[3];
                opciones[0] = "Consolarlo";
                opciones[1] = "Decirle que con razón se burlaron de él, menudas pintas...";
                opciones[2] = "Decirle que te da igual y marcharte";
                opcionElegida = MenusConsola.menuEventoAleatorio(opciones);
                switch (opcionElegida){
                    case 0:
                        textoFinal = "El chico, al secarse las lágrimas, te ofrece una figura de colección como agradecimiento por consolarlo. Parece que podría venderse por un buen precio. Ganas 25 de oro" ;
                        jugador.setOro(jugador.getOro() + 25 );
                    case 1:
                        textoFinal = "El chico se ofende y llora más diciendo: \\\"De hecho *snif* solo tengo 6 dioptrías en cada ojo es algo normal.\\\" ";
                        opciones = new String[2];
                        opciones[0] = "\\\"Tienes razón lo siento.\\\"";
                        opciones[1] = "\\\"Estás cegato chaval.\\\"";
                        opcionElegida = MenusConsola.menuEventoAleatorio(opciones);
                        switch (opcionElegida){
                            case 0:
                                textoFinal = "El chico se marcha con los ojos rojos a causa de las lágrimas";
                                break;
                            case 1:
                                textoFinal = "El chico se enfada y te lanza un cuchillo que tenía escondido pero falla dandole a un monstruo, esto pinta feo...";
                                Combate c = new Combate(jugador,enemigo,nivel);
                                c.empezarEvento();
                        }
                        break;

                    case 2:
                        textoFinal = "Te marchas dejando atrás al niño que sigue llorando.";
                        break;
                }
                break;
            case 1:
                textoFinal = "Eres un ser avaricioso y detestable pero el chico tenía bastante oro. Consigues 20 de oro";
                jugador.setOro(jugador.getOro() + 20 );
                break;
            case 2:
                textoFinal = "Pasaste de largo";
                break;


        }
        if (!jugador.estaMuerto()) {
            terminarEvento();
        }

    }


}
