package Modelo.Bases;

import Modelo.Enums.Estados;

import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La clase abstracta Entidad representa una entidad en el juego, lo que incluye Jugadores y enemigos.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public abstract class Entidad {
    /**
     * Nombre de la entidad.
     */
    protected String nombre;
    /**
     * Vida de la entidad.
     */
    protected int salud;
    /**
     * Variable temporal en caso de que la vida de la entidad se modifique temporalmente.
     */
    protected int saludTemp;
    /**
     * Vida máxima de la entidad.
     */
    protected int maxSalud;
    /**
     * Daño de la entidad.
     */
    protected int dmg;
    /**
     * Daño real de la entidad, usado para devolver el daño a su valor real en caso de que se modifique temporalmente.
     */
    protected int dmgBase;
    /**
     * Daño de la entidad.
     */
    protected int defensa;
    /**
     * Defensa real de la entidad, usado para devolver la defensa a su valor real en caso de que se modifique temporalmente.
     */
    protected int defensaBase;
    /**
     * Variable usada para aumentar la defensa de la entidad quando bloquea un ataque.
     */
    protected int poderBloqueo;
    /**
     * Variable usada para saber si la entidad está bloqueando y poder determinar si una finta es exitosa.
     */
    protected boolean bloqueando = false;
    /**
     * Lista de ataques especiales a los que tiene acceso la entidad.
     */
    protected List<AtaqueEspecial> ataques;
    /**
     * Imagen de la entidad
     */
    protected Iconos icono;

    /**
     * HashMap que contiene los estados que están afectando a la entidad.
     * K -> Estado que afecta a la entida.
     * V -> Numero de turnos restantes hasta que el efecto desaparezca.
     */
    protected Map<Estados, Integer> estadosSufridos = new HashMap<>();

    /**
     * Metodo que recorre el HashMap estadosSufridos y va aplicando los efectos de los estados.
     * Cuando termina la duración de los efectos los elimina.
     *
     * @param interfaz La interfaz de la aplicacion, se usará principalmente para mostrar mensajes.
     */
    public void aplicarEstados(Interfaz interfaz) {
        Estados[] aEliminar = new Estados[estadosSufridos.size() + 1];
        estadosSufridos.forEach((e,s)-> {
            aplicarEfectoDeEstados(e,interfaz);
            if (e.isDeterioro()) {
                if (s-1==0) {
                    for (int i = 0; i < aEliminar.length; i++) {
                        if (aEliminar[i] == null) {
                            aEliminar[i] = e;
                            break;
                        }
                    }
                } else {
                    estadosSufridos.replace(e,s-1);
                }
            }
        });
        eliminarEstados(aEliminar);
    }

    /**
     * Metodo que elimina estados del HashMap estadosSufridos.
     *
     * @param eliminar Array que contiene los estados que se deben eliminar del HashMap estadosSufridos.
     */
    public void eliminarEstados(Estados[] eliminar) {
        for (int i = 0; i < eliminar.length; i++) {
            estadosSufridos.remove(eliminar[i]);
        }
    }

    /**
     * Metod que aplica los efectos de los estados sufridos.
     *
     * @param estado Estado que que se tiene que aplicar.
     * @param interfaz La interfaz de la aplicacion, se usará principalmente para mostrar mensajes.
     */
    public void aplicarEfectoDeEstados(Estados estado,Interfaz interfaz) {
        switch (estado) {
            case VENENO -> {
                if (estadosSufridos.containsKey(Estados.RESISTENCIAVENENO)) {
                    dmgVerdadero(estado.getEfecto()/Estados.RESISTENCIAVENENO.getEfecto(),interfaz);
                }
            }
            case QUEMADURA -> {
                if (estadosSufridos.containsKey(Estados.RESISTENCIAQUEMADURA)) {
                    dmgVerdadero(estado.getEfecto()/Estados.RESISTENCIAQUEMADURA.getEfecto(),interfaz);
                }
            }
            case ELECTRIFICADO -> {
                if (estadosSufridos.containsKey(Estados.RESISTENCIAELECTRICIDAD)) {
                    dmgVerdadero(estado.getEfecto()/Estados.RESISTENCIAELECTRICIDAD.getEfecto(),interfaz);
                }
            }
            case MALDITO -> multiplicarEstadisticas(0.5);
            case BENDITO -> multiplicarEstadisticas(1.5);
            case FORTALEZA -> aumentarDefensa(Estados.FORTALEZA.getEfecto());
            case RABIA -> {
                aumentarDmg(Estados.RABIA.getEfecto());
                recibirDmg(3,interfaz);
            }
            case MENOSDEFENSA -> bajarDefensa(Estados.MENOSDEFENSA.getEfecto());
        }
    }

    /**
     * Metodo para aumentar temporalmente la defensa de la entidad.
     *
     * @param defensa Cantidad de defensa que se le tiene que añadir a la entidad.
     */
    public void aumentarDefensa(int defensa) {
        this.defensa += defensa;
    }

    /**
     * Metodo para reducir temporalmente la defensa de la entidad.
     *
     * @param defensa Cantidad de defensa que se le tiene que quitar a la entidad.
     */
    public void bajarDefensa(int defensa) {
        this.defensa += defensa;
    }

    /**
     * Metodo para aumentar temporalmente el daño de la entidad.
     *
     * @param dmg Cantidad de daño que se le tiene que añadir a la entidad.
     */
    public void aumentarDmg(int dmg) {
        this.dmg += dmg;
    }

    /**
     * Metodo para aumentar temporalmente la vida de la entidad.
     *
     * @param salud Cantidad de vida que se le tiene que añadir a la entidad.
     */
    public void aumentarSalud(int salud) {
        saludTemp = salud;
        this.salud += salud;
    }

    /**
     * Metodo que aumenta temporalmente la defensa de la entidad y determina que la entidad está bloqueando un ataque.
     */
    public void bloquear() {
        defensa += poderBloqueo;
        bloqueando = true;
    }

    /**
     * Metodo que determina la cantidad de daño que se le tiene que hacer y reduce la vida de la entidad.
     * La cantida de daño se determina restando el daño que recibe el método menos la defensa de la entidad.
     * En el caso de que el daño que recibiría la entidad sea igual o menor que cero, se le hace 1 de daño.
     *
     * @param dmg Daño que se le tiene que hacer a la entidad.
     * @param interfaz La interfaz de la aplicacion, se usará principalmente para mostrar mensajes.
     */
    public void recibirDmg(int dmg, Interfaz interfaz) {
        if (dmg - defensa > 0) {
            salud -= dmg-defensa;
            interfaz.imprimirMensaje(dmg-defensa + " de daño");
        } else {
            interfaz.imprimirMensaje("1 de daño");
            salud -= 1;
        }
        interfaz.actualizar();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para hecer una cantidad de daño asegurada a la entidad.
     * Reservado principalmente para el daño de los efectos de estado.
     *
     * @param dmg Daño que se le tiene que hacer a la entidad.
     * @param interfaz La interfaz de la aplicacion, se usará principalmente para mostrar mensajes.
     */
    public void dmgVerdadero(int dmg, Interfaz interfaz) {
        salud -= dmg;
        interfaz.imprimirMensaje(dmg + " de daño");
    }

    /**
     * Metodo que elimina efectos de estado que son considerados perjudiciales.
     */
    public void eliminarEstadosPerjudiciales() {
        estadosSufridos.forEach((e,n) -> {
            switch (e) {
                case
                        MENOSDEFENSA,
                        MALDITO,
                        CONGELADO,
                        VENENO,
                        DESORIENTADO,
                        SILENCIADO,
                        QUEMADURA,
                        CEGADO,
                        ELECTRIFICADO
                        -> estadosSufridos.remove(e);
            }
        });
    }

    /**
     * Metodo que comprueba si la entidad ha muerto.
     * @return Devuelve verdadero si la vida de la entidad es igual o menor a 0.
     */
    public boolean estaMuerto() {
        return salud<=0;
    }

    /**
     * Metodo abstracto para añadir un estado a la lista de estados infligidos.
     *
     * @param estado Estado que se le quiere aplicar al enemigo.
     * @param interfaz La interfaz de la aplicacion, se usará principalmente para mostrar mensajes.
     */
    public abstract void infligirEstado(Estados estado,Interfaz interfaz);

    /**
     * Metodo que aumenta o disminuye temporalmente las estadísticas de la entidad.
     *
     * @param multiplicador Cantidad por la que se multiplican las estadísticas de la entidad.
     */
    public void multiplicarEstadisticas(double multiplicador) {
        saludTemp = salud;
        salud = (int) (salud * multiplicador);
        dmg = (int) (dmg * multiplicador);
        defensa = (int) (defensa * multiplicador);
    }

    /**
     * Metodo llamado al final de cada turno en un combate, restablece las estadísticas a sus valores reales y devuelve la variable bloqueando a false.
     */
    public void finTurno() {
        if (saludTemp != 0) {
            salud = saludTemp;
            saludTemp = 0;
        }
        dmg = dmgBase;
        defensa = defensaBase;
        bloqueando = false;
    }
}