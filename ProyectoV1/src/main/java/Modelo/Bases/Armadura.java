package Modelo.Bases;

import Modelo.Misc.Estados;
import lombok.Data;

import java.util.List;

@Data
public abstract class Armadura {
    int defensa;
    List<Estados> inmunidades;
}
