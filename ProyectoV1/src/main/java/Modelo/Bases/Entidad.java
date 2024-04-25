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
    protected int ataque;
    protected int defensa;
    protected List<AtaqueEspecial> ataques;

    protected Map<Estados, Integer> estadosSufridos = new HashMap<>();

    public void recibirDmg(int dmg) {
        salud -= dmg-defensa;
    }

    public abstract void aplicarEstados();
    public abstract void aplicarEfectoDeEstados(Estados estado);

    public boolean estaMuerto() {
        return salud<=0;
    }
}
