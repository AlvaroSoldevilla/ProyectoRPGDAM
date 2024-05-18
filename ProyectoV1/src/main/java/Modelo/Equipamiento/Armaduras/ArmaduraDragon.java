package Modelo.Equipamiento.Armaduras;

import Modelo.Bases.Armadura;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class ArmaduraDragon extends Armadura {
    public ArmaduraDragon(){
        nombre = "Armadura draconiana";
        defensa=25;
        inmunidades= new ArrayList<>();
        inmunidades.add(Estados.QUEMADURA);
    }
}
