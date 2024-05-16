package Modelo.Equipamiento.Armaduras;

import Modelo.Bases.Armadura;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class ArmaduraCuero extends Armadura {
    public ArmaduraCuero(){
        nombre = "Armadura de cuero";
        defensa=10;
        debilidades = new ArrayList<>();
        debilidades.add(Estados.VENENO);
    }
}
