package Modelo.Armaduras;

import Modelo.Bases.Armadura;
import Modelo.Misc.Estados;

import java.util.ArrayList;

public class ArmaduraDragon extends Armadura {
    public ArmaduraDragon(){
        nombre = "Armadura draconiana";
        defensa=25;
        inmunidades= new ArrayList<>();
        inmunidades.add(Estados.QUEMADURA);
        debilidades = new ArrayList<>();
        debilidades.add(Estados.ELECTRIFICADO);
    }
}
