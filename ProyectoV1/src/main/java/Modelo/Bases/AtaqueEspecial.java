package Modelo.Bases;

public abstract class AtaqueEspecial {

    protected String nombre;
    protected int coste;
    public abstract boolean hacerAtaque(Entidad objetivo,Entidad atatcante);
    public boolean puedeAtacar(Entidad atatcante) {
        if ((atatcante instanceof Jugador jugador)) {
            if (jugador.getMana()>=coste) {
                jugador.setMana(jugador.getMana()-coste);
                return true;
            } else {
                System.out.println("No tienes mana suficiente");
                return false;
            }
        } else {
            return true;
        }
    }

    public void mostrar() {
        System.out.println("Ataque: " + nombre + "\n" +  "Coste: " + coste);
    }
}
