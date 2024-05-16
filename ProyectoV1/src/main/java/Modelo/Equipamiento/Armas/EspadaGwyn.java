package Modelo.Equipamiento.Armas;

import Modelo.Bases.Arma;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class EspadaGwyn extends Arma {
    public EspadaGwyn(){
        nombre = "Espada del Señor de la ceniza";
        dmg=30;
        bonus = new ArrayList<>();
        bonus.add(Estados.QUEMADURA);
    }
}
