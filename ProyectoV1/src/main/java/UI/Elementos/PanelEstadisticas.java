package UI.Elementos;

import Modelo.Bases.Jugador;
import Modelo.Enums.Iconos;
import javax.swing.*;


public class PanelEstadisticas extends JPanel {
    public PanelEstadisticas(Jugador jugador) {
        this.jugador = jugador;
        addElementos();
    }

    Jugador jugador;

    public void addElementos() {
        JLabel oro = new JLabel(new ImageIcon(Iconos.ORO.getRutaIcono()));
        oro.setBounds(5,7,32,32);
        add(oro);
        JLabel oroText = new JLabel(String.valueOf(jugador.getOro()));
        oroText.setBounds(37,7,40,30);
        add(oroText);
    }
}
