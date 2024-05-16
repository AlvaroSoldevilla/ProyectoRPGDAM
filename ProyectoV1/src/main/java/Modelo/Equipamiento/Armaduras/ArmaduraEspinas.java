package Modelo.Equipamiento.Armaduras;

import Modelo.Bases.Armadura;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class ArmaduraEspinas extends Armadura {
    public ArmaduraEspinas(){
        nombre = "Armadura de espinas";
        defensa=20;
        inmunidades= new ArrayList<>();
        inmunidades.add(Estados.ESPINAS);
        debilidades = new ArrayList<>();
        debilidades.add(Estados.QUEMADURA);
    }
}
