package Modelo.Equipamiento.Armas;

import Modelo.Bases.Arma;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class Matadragones extends Arma {
    public Matadragones(){
        nombre = "Matadragones";
        dmg=35;
        bonus = new ArrayList<>();
        bonus.add(Estados.ELECTRIFICADO);
    }
}
