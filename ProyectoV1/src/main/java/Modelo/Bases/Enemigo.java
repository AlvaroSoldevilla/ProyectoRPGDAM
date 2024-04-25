package Modelo.Bases;


import Modelo.Misc.Estados;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemigo extends Entidad{

    protected List<Estados> inmunidades = new ArrayList<>();

    public void InfligirEstado(Estados estado) {
        if (!inmunidades.contains(estado)) {
            estadosSufridos.put(estado, estadosSufridos.get(estado) + 1);
        } else {
            //TODO:cambiar a mensaje en la interfaz
            System.out.println("El enemigo es inmune a " + estado.getNombre());
        }
    }

    @Override
    public void mostrarEstadisticas() {
        System.out.println("Enemigo:" + "\n" + "Vida: " + salud + "\n" + "Ataque: " + dmg + "\n" + "Defensa: " + defensa);
    }
    @Override
    public void multiplicarEstadisticas(int multiplicador) {
        salud *= multiplicador;
        dmg *= multiplicador;
        defensa *= multiplicador;
    }

}
