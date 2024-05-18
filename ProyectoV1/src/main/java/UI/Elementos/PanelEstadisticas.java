package UI.Elementos;

import Modelo.Bases.Jugador;
import Modelo.Enums.Iconos;
import javax.swing.*;

/**
 * La clase PanelEstadisticas representa un panel de estadísticas que muestra información sobre el jugador.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class PanelEstadisticas extends JPanel {

    /**
     * El jugador del que se muestran las estadísticas.
     */
    Jugador jugador;

    /**
     * Constructor que inicializa el panel de estadísticas con el jugador proporcionado.
     *
     * @param jugador El jugador cuyas estadísticas se muestran.
     */
    public PanelEstadisticas(Jugador jugador) {
        this.jugador = jugador;
        addElementos();
    }

    /**
     * Agrega los elementos gráficos al panel.
     */
    public void addElementos() {
        JLabel oro = new JLabel(new ImageIcon(Iconos.ORO.getRutaIcono()));
        oro.setBounds(5, 7, 32, 32);
        add(oro);
        JLabel oroText = new JLabel(String.valueOf(jugador.getOro()));
        oroText.setBounds(37, 7, 40, 30);
        add(oroText);
    }
}