package Modelo.Bases;

import Modelo.Misc.Estados;
import lombok.Data;

import java.util.List;

@Data
public abstract class Armadura extends Equipamiento {
    protected int defensa;
    protected List<Estados> inmunidades;
    protected List<Estados> debilidades;

}
