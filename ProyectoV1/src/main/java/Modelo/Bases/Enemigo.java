package Modelo.Bases;


import Modelo.Misc.Estados;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemigo extends Entidad{

    protected List<Estados> inmunidades = new ArrayList<>();

    @Override
    public void infligirEstado(Estados estado) {
        if (inmunidades == null) {
            try {
                estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getDuracion());
            } catch (NullPointerException e) {
                estadosSufridos.put(estado, estado.getDuracion());
            }
        } else {
            if (!inmunidades.contains(estado)) {
                estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getDuracion());
            } else {
                //TODO:cambiar a mensaje en la interfaz
                System.out.println("El enemigo es inmune a " + estado.getNombre());
            }
        }
    }

    @Override
    public void mostrarEstadisticas() {
        System.out.println("Enemigo:" + "\n" + "Vida: " + salud + "\n" + "Ataque: " + dmg + "\n" + "Defensa: " + defensa);
    }


}
