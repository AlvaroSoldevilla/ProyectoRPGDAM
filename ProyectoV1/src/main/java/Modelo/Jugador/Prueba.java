package Modelo.Jugador;

import Modelo.Bases.Clase;
import Modelo.Misc.Estados;

public class Prueba extends Clase {

    public Prueba() {
        nombre = "Prueba";
        maxSalud = 10;
        maxMana = 5;
        salud = maxSalud;
        mana = maxMana;
        ataque = 3;
    }

    @Override
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

    @Override
    public void aplicarEfectoDeEstados(Estados estado) {
        switch (estado) {
            case VENENO,QUEMADURA -> recibirDmg(estado.getEfecto());
        }
    }
}
