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
        estadosSufridos.forEach((e,s)-> {
            aplicarEfectoDeEstados(e,interfaz);
            if (e.isDeterioro()) {
                if (s-1==0) {
                    estadosSufridos.remove(e);
                } else {
                    estadosSufridos.replace(e,s-1);
                }
            }
        });
    }

    public void aplicarEfectoDeEstados(Estados estado,Interfaz interfaz) {
        switch (estado) {
            case VENENO,QUEMADURA -> recibirDmg(estado.getEfecto(),interfaz);
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
        if (dmg - defensa >= 0) {
            salud -= dmg-defensa;
        } else {
            salud -= 1;
        }
        interfaz.imprimirMensaje("Recibe " + dmg + " de daño");
    }

    public void eliminarEstadosPerjudiciales() {
        estadosSufridos.forEach((e,n) -> {
            switch (e) {
                case MENOSDEFENSA -> estadosSufridos.remove(e);
                case MALDITO -> estadosSufridos.remove(e);
                case CONGELADO -> estadosSufridos.remove(e);
                case VENENO -> estadosSufridos.remove(e);
                case DESORIENTADO -> estadosSufridos.remove(e);
                case SILENCIADO -> estadosSufridos.remove(e);
                case QUEMADURA -> estadosSufridos.remove(e);
                case CEGADO -> estadosSufridos.remove(e);
                case ELECTRIFICADO -> estadosSufridos.remove(e);
            }
        });
    }

    public boolean estaMuerto() {
        return salud<=0;
    }

    public abstract void infligirEstado(Estados estado);

    public void multiplicarEstadisticas(double multiplicador) {
        saludTemp = salud;
        salud *= (int) multiplicador;
        dmg *= (int) multiplicador;
        defensa *= (int) multiplicador;
    }

    public abstract void mostrarEstadisticas();

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