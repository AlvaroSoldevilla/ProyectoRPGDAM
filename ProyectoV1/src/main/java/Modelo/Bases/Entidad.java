package Modelo.Bases;

import Modelo.Misc.Estados;

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

    protected Map<Estados, Integer> estadosSufridos = new HashMap<>();


    public void aplicarEstados() {
        estadosSufridos.forEach((e,s)-> {
            aplicarEfectoDeEstados(e);
            if (e.isDeterioro()) {
                if (s-1==0) {
                    estadosSufridos.remove(e);
                } else {
                    estadosSufridos.replace(e,s-1);
                }
            }
        });
    }

    public void aplicarEfectoDeEstados(Estados estado) {
        switch (estado) {
            case VENENO,QUEMADURA -> recibirDmg(estado.getEfecto());
            case MALDITO -> multiplicarEstadisticas(0.5);
            case BENDITO -> multiplicarEstadisticas(1.5);
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
    }

    public void recibirDmg(int dmg) {
        if (dmg - defensa >= 0) {
            salud -= dmg-defensa;
        } else {
            salud -= 1;
        }
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
        salud = saludTemp;
        dmg = dmgBase;
        defensa = defensaBase;
        bloqueando = false;
    }
}
