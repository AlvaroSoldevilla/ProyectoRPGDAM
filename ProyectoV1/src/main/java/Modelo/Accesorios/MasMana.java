package Modelo.Accesorios;

import Modelo.Bases.Accesorio;
import Modelo.Bases.Jugador;

public class MasMana extends Accesorio {
    public MasMana(){
        nombre = "Prueba";
        permanente = false;
        inicioTurno = true;
        inicioCombate = true;
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {

    }
}
