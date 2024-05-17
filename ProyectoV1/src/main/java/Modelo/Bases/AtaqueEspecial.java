package Modelo.Bases;

import UI.Interfaces.Interfaz;
import lombok.Data;

@Data
public abstract class AtaqueEspecial {

    protected String nombre;
    protected int coste;
    public abstract boolean hacerAtaque(Entidad objetivo, Entidad atatcante, Interfaz interfaz);

    public boolean puedeAtacar(Entidad atatcante, Interfaz interfaz) {
        if ((atatcante instanceof Jugador jugador)) {
            if (jugador.getMana()>=coste) {
                jugador.setMana(jugador.getMana()-coste);
                return true;
            } else {
                interfaz.imprimirMensaje("No tienes mana suficiente");
                return false;
            }
        } else {
            return true;
        }
    }

    public void mostrar() {
        System.out.println("Ataque: " + nombre + "\n" +  "Coste: " + coste);
    }

    @Override
    public String toString() {
        return nombre + " " + coste;
    }
}
