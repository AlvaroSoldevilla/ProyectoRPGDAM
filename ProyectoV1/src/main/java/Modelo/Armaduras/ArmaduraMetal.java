package Modelo.Armaduras;

import Modelo.Bases.Armadura;
import Modelo.Enums.Estados;

import java.util.ArrayList;

public class ArmaduraMetal extends Armadura {
    public ArmaduraMetal(){
        nombre = "Armadura de metal";
        defensa=15;
        inmunidades = new ArrayList<>();
        inmunidades.add(Estados.ELECTRIFICADO);
    }
}
