package Modelo.Bases;

import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase abstracta Jugador representa un jugador en el juego.
 * <p>Cada clase Jugador solo modifica las estadísticas.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
@Data
public abstract class Jugador extends Entidad{

    /**
     * Cantidad de dinero que tiene el jugador
     */
    protected int oro;
    /**
     * El mana se usará para decidir si el jugador puede usar los ataques especiales
     */
    protected int mana;
    /**
     * Variable temporal en caso de que el mana del jugador se modifique temporalmente.
     */
    protected int manaTemp = mana;
    /**
     * Mana máximo del jugador
     */
    protected int maxMana;
    /**
     * Arma equipada actualmente
     */
    protected Arma arma;
    /**
     * Armadura equipada actualmente
     */
    protected Armadura armadura;
    /**
     * Lista de armas que tiene el jugador
     */
    protected List<Arma> armas = new ArrayList<>();
    /**
     * Lista de armaduras que tiene el jugador
     */
    protected List<Armadura> armaduras = new ArrayList<>();
    /**
     * Lista de accesorios que tiene el jugador
     */
    protected List<Accesorio> accesorios = new ArrayList<>();

    /**
     * Metodo para aumentar temporalmente el mana de la entidad.
     *
     * @param mana Cantidad de mana que se le tiene que añadir a la entidad.
     */
    public void aumentarMana(int mana) {
        manaTemp = mana;
        this.mana += mana;
    }
    /**
     * Metodo que añade un estado a la lista de estados infligidos.
     * <p>Si el jugador no tiene una armadura inmune al estado, se le añade.
     * <p>Si el jugador tiene una armadura inmune al estado, se imprime un mensaje en la interfaz.
     *
     * @param estado Estado que se le quiere aplicar al enemigo.
     * @param interfaz La interfaz de la aplicacion, se usará principalmente para mostrar mensajes.
     */
    @Override
    public void infligirEstado(Estados estado, Interfaz interfaz) {
        if (armadura.getInmunidades() == null) {
            try {
                estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getDuracion());
            } catch (NullPointerException e) {
                estadosSufridos.put(estado, estado.getDuracion());
            }
        } else {
            if (!armadura.getInmunidades().contains(estado)) {
                try {
                    estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getDuracion());
                } catch (NullPointerException e) {
                    estadosSufridos.put(estado, estado.getDuracion());
                }
            } else {
                interfaz.imprimirMensaje("Eres inmune a " + estado.getNombre());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Método usado para curar una cantidad específica de vida al jugador.
     * @param vida Cantidad de vida que se tiene que curar.
     */
    public void curarVida(int vida) {
        if (salud + vida <= maxSalud) {
            salud += vida;
        } else {
            salud = maxSalud;
        }
    }

    /**
     * Método que restaura por completo la vida del jugador.
     */
    public void restaurarVida() {
        salud=maxSalud;
    }

    /**
     * Método que restaura por completo el mana del jugador.
     */
    public void restaurarMana() {
        mana=maxMana;
    }

    /**
     * Método que modifica el arma actual del jugador y actualiza sus estadísticas
     *
     * @param nueva Nueva arma que se tiene que equipar.
     * @return Devuelve verdadero si se ha podido cambiar la nueva arma.
     */
    public boolean cambiarArma(Arma nueva) {
        if (nueva != null) {
            dmg-=arma.getDmg();
            arma = nueva;
            aplicarEfectosEquipamiento(1);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que guarda un arma en la lista de armas.
     *
     * @param nueva Arma que se tiene que guardar.
     * @return Devuelve verdadero si se ha podido guardar la nueva arma.
     */
    public boolean guardarArma(Arma nueva) {
        if (nueva!=null) {
            armas.add(nueva);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que elimina un arma de la lista de armas.
     *
     * @param index Armadura que se debe eliminar.
     */
    public void eliminarArma(int index) {
        armas.remove(index);
    }

    /**
     * Método que modifica la armadura actual del jugador y actualiza sus estadísticas.
     *
     * @param nueva Nueva armadura que se tiene que equipar.
     * @return Devuelve verdadero si se ha podido cambiar la nueva armadura.
     */
    public boolean cambiarArmadura(Armadura nueva) {
        if (nueva != null) {
            defensa-=armadura.getDefensa();
            armadura = nueva;
            aplicarEfectosEquipamiento(2);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que guarda una armadura en la lista de armas.
     *
     * @param nueva Armadura que se tiene que guardar.
     * @return Devuelve verdadero si se ha podido guardar la nueva armadura.
     */
    public boolean guardarArmadura(Armadura nueva) {
        if (nueva!=null) {
            armaduras.add(nueva);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que elimina una armadura de la lista de armas.
     *
     * @param index Armadura que se debe eliminar.
     */
    public void eliminarArmadura(int index) {
        armaduras.remove(index);
    }

    /**
     * Método que añade una cantidad específica de oro.
     *
     * @param oro Cantidad de oro que se le tiene que dar al jugador.
     */
    public void ganarOro(int oro) {
        this.oro += oro;
    }

    /**
     * Método que quita una cantidad específica de oro.
     *
     * @param oro Cantidad de oro que se le tiene que quitar al jugador.
     */
    public void gastarOro(int oro) {
        this.oro-=oro;
    }

    /**
     * Método que modifica las estadísticas del jugador cuando se modifica su equipamiento.
     *
     * @param cambio Determina que pieza de quipamiento se ha modificado: <p>0-Arma y armadura <p>1-Solo arma <p>2-Solo armadura
     */
    public void aplicarEfectosEquipamiento(int cambio) {
        switch (cambio) {
            case 0:
                defensa+=armadura.getDefensa();
                dmg+=arma.getDmg();
                break;
            case 1:
                dmg+=arma.getDmg();
                break;
            case 2:
                defensa+=armadura.getDefensa();
                break;
        }
        defensaBase = defensa;
        dmgBase = dmg;
    }

    /**
     * Añade un accesorio a la lista de accesorios.
     *
     * @param nuevo Accesorio que se debe añadir
     * @param interfaz La interfaz de la aplicacion, se usará principalmente para mostrar mensajes.
     * @return Devuelve verdadero si se ha podido guardar el nuevo accesorio.
     */
    public boolean addAccesorio(Accesorio nuevo,Interfaz interfaz) {
        if (nuevo!=null) {
            accesorios.add(nuevo);
            if (nuevo.isPermanente()) {
                nuevo.aplicarEfecto(this,interfaz);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Aumenta permanentemente las estadísticas del jugador.
     */
    public void subirNivel() {
        maxSalud *= 1.5;
        maxMana *= 1.5;
        dmg *= 1.5;
        dmgBase = dmg;
        defensa *= 1.5;
        defensaBase = defensa;
        restaurarVida();
        restaurarMana();
    }

    /**
     * Metodo que aumenta o disminuye temporalmente las estadísticas de la entidad.
     *
     * @param multiplicador Cantidad por la que se multiplican las estadísticas de la entidad.
     */
    @Override
    public void multiplicarEstadisticas(double multiplicador) {
        saludTemp = salud;
        salud *= (int) multiplicador;
        manaTemp = mana;
        mana *= (int) multiplicador;
        dmg *= (int) multiplicador;
        defensa *= (int) multiplicador;
    }

    /**
     * Metodo llamado al final de cada turno en un combate, restablece las estadísticas a sus valores reales y devuelve la variable bloqueando a false.
     */
    @Override
    public void finTurno() {
        if (saludTemp != 0) {
            salud = saludTemp;
            saludTemp = 0;
        }
        if (manaTemp != 0) {
            mana = manaTemp;
            manaTemp = 0;
        }
        dmg = dmgBase;
        defensa = defensaBase;
        bloqueando = false;
    }

}