package Modelo.Enemigos;

import Modelo.Bases.Enemigo;
import Modelo.Misc.Estados;

public class Prueba extends Enemigo {
    public Prueba() {
        nombre = "Prueba";
        maxSalud = 10;
        salud = maxSalud;
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
