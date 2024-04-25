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
    protected int maxSalud;
    protected int dmg;
    protected int defensa;
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
        }
    }

    public void recibirDmg(int dmg) {
        salud -= dmg-defensa;
    }

    public boolean estaMuerto() {
        return salud<=0;
    }
    public abstract void multiplicarEstadisticas(int multiplicador);
    public abstract void mostrarEstadisticas();

}
