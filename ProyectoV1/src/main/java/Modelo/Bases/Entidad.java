package Modelo.Bases;

import Modelo.Enums.Estados;

import Modelo.Enums.Iconos;
import UI.Interfaces.Interfaz;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public abstract class Entidad {
    protected String nombre;
    protected int salud;
    protected int saludTemp;
    protected int maxSalud;
    protected int dmg;
    protected int dmgBase;
    protected int defensa;
    protected int defensaBase = defensa;
    protected int poderBloqueo;
    protected boolean bloqueando = false;
    protected List<AtaqueEspecial> ataques;
    protected Iconos icono;

    protected Map<Estados, Integer> estadosSufridos = new HashMap<>();


    public void mostrarEstados() {
        estadosSufridos.forEach((e,n) -> System.out.println(e.getNombre() + " " + e.getDuracion()));
    }

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
    public void eliminarEstados(Estados[] eliminar) {
        for (int i = 0; i < eliminar.length; i++) {
            estadosSufridos.remove(eliminar[i]);
        }
    }

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

    public void aumentarDefensa(int defensa) {
        this.defensa += defensa;
    }

    public void bajarDefensa(int defensa) {
        this.defensa += defensa;
    }

    public void aumentarDmg(int dmg) {
        this.dmg += dmg;
    }

    public void bloquear() {
        defensa += poderBloqueo;
        bloqueando = true;
    }

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
    public void dmgVerdadero(int dmg, Interfaz interfaz) {
        salud -= dmg;
        interfaz.imprimirMensaje(dmg + " de daño");
    }

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

    public boolean estaMuerto() {
        return salud<=0;
    }

    public abstract void infligirEstado(Estados estado,Interfaz interfaz);

    public void multiplicarEstadisticas(double multiplicador) {
        saludTemp = salud;
        salud *= (int) multiplicador;
        dmg *= (int) multiplicador;
        defensa *= (int) multiplicador;
    }

    public abstract void mostrarEstadisticas();
    public abstract String getEstadisticas();

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