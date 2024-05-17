package Modelo.Bases;


import Modelo.Enums.Estados;
import UI.Interfaces.Interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Enemigo extends Entidad{

    protected Random rng = new Random();
    protected List<Estados> inmunidades = new ArrayList<>();

    @Override
    public void infligirEstado(Estados estado, Interfaz interfaz) {
        if (inmunidades == null) {
            try {
                estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getDuracion());
            } catch (NullPointerException e) {
                estadosSufridos.put(estado, estado.getDuracion());
            }
        } else {
            if (!inmunidades.contains(estado)) {
                if (estadosSufridos.containsKey(estado)) {
                    estadosSufridos.put(estado, estadosSufridos.get(estado) + estado.getDuracion());
                } else {
                    estadosSufridos.put(estado, estado.getDuracion());
                }
            } else {
                interfaz.imprimirMensaje("El enemigo es inmune a " + estado.getNombre());
            }
        }
    }

    @Override
    public void mostrarEstadisticas() {
        System.out.println();
    }

    @Override
    public String getEstadisticas() {
        return "Enemigo:" + "\n" + "Vida: " + salud + "\n" + "Ataque: " + dmg + "\n" + "Defensa: " + defensa;
    }
}
