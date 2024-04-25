package Modelo.Armas;

import Modelo.Bases.Arma;
import Modelo.Misc.Estados;

import java.util.ArrayList;

public class Matadragones extends Arma {
    public Matadragones(){
        nombre = "Matadragones";
        dmg=35;
        bonus = new ArrayList<>();
        bonus.add(Estados.ELECTRIFICADO);
    }
}
