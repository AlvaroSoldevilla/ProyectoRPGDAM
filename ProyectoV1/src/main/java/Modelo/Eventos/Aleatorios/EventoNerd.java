package Modelo.Eventos.Aleatorios;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Eventos.Aleatorio;
import Modelo.Eventos.Combate;
import UI.Interfaces.Interfaz;

public class EventoNerd extends Aleatorio {
    public EventoNerd(Jugador jugador , Enemigo enemigo, int nivel, Interfaz interfaz) {
        super(interfaz);
        titulo="Niño lloriqueando";

        texto = "En tu camino te topas con un chico con gafas que llora. ¿Qué deberías hacer?";

        opciones = new String[]{"Preguntarle que le pasa","Robarle","Ignorarlo"};

        this.jugador = jugador;
        this.enemigo = enemigo;
        this.nivel = nivel;
    }
    Enemigo enemigo;
    int nivel;

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
                setTexto("El chico dice que unos chicos se burlaron de él por su aspecto y por como habla");
                opciones = new String[]{"Consolarlo","Decirle que con razón se burlaron de él, menudas pintas...","Decirle que te da igual y marcharte"};

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
                switch (opcion){
                    case 0:
                        setTexto("El chico, al secarse las lágrimas, te ofrece una figura de colección como agradecimiento por consolarlo. Parece que podría venderse por un buen precio. Ganas 25 de oro");
                        jugador.setOro(jugador.getOro() + 25 );
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        esperar();
                        break;
                        case 1:
                        setTexto("El chico se ofende y llora más diciendo: \\\"De hecho *snif* solo tengo 6 dioptrías en cada ojo es algo normal.\\\" ");
                        opciones = new String[]{"\\\"Tienes razón lo siento.\\\"","\\\"Estás cegato chaval.\\\""};

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
                        switch (opcion){
                            case 0:
                                setTexto("El chico se marcha con los ojos rojos a causa de las lágrimas");
                                opciones = new String[]{"Seguir"};
                                interfaz.actualizar();
                                esperar();
                                break;
                            case 1:
                                setTexto("El chico se enfada y te lanza un cuchillo que tenía escondido pero falla dandole a un monstruo, esto pinta feo...");
                                Combate c = new Combate(jugador,enemigo,nivel,interfaz);
                                c.empezarEvento();
                        }
                        break;

                    case 2:
                        setTexto("Te marchas dejando atrás al niño que sigue llorando.");
                        opciones = new String[]{"Seguir"};
                        interfaz.actualizar();
                        esperar();
                        break;
                }
                break;
            case 1:
                setTexto("Eres un ser avaricioso y detestable pero el chico tenía bastante oro. Consigues 20 de oro");
                jugador.setOro(jugador.getOro() + 20 );
                opciones = new String[]{"Seguir"};
                interfaz.actualizar();
                esperar();
                break;
            case 2:
                setTexto("Pasaste de largo");
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
