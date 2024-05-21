package UI.Interfaces;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import Modelo.Enums.Iconos;
import UI.Elementos.Escena;
import UI.Elementos.PanelEstadisticas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * La clase UICombate representa una escena de combate en el juego, mostrando al jugador, los enemigos y las opciones de combate.
 *
 * @author Álvaro Soldevilla
 * @author Diego Gonzalez
 */
public class UICombate extends Escena {

    /**
     * El jugador que participa en el combate
     */
    private Jugador jugador;
    /**
     * Los enemigos que participan en el combate
     */
    private Enemigo[] enemigos;
    /**
     * Las imagenes de los enemigos
     */
    private JLabel[] imagenesEnemigos;
    /**
     * Los botones de las acciones
     */
    private JButton[] botones = new JButton[4];
    /**
     * La lista con los estados de cada entidad
     */
    JLabel textoCombateLabel = new JLabel("Empieza El combate");
    /**
     * El texto de las opciones
     */
    private String[] opciones;
    /**
     * Las distintas versiones de la escena
     * <p>1-Elección de las acciones
     * <p>2-Elección del ataque especial
     * <p>3-Fin del combate
     */
    private int fase = 1;

    /**
     * Constructor que inicializa la escena de combate con la imagen de fondo, el jugador y los enemigos.
     *
     * @param imagenDeFondo La imagen de fondo de la escena.
     * @param jugador       El jugador que participa en el combate.
     * @param enemigos      Los enemigos en el combate.
     */
    public UICombate(String imagenDeFondo, Jugador jugador, Enemigo[] enemigos) {
        super(imagenDeFondo);
        this.jugador = jugador;
        this.enemigos = enemigos;
        this.imagenesEnemigos = new JLabel[3];
        setLayout(null);
        addElementos();
    }

    /**
     * Agrega los elementos gráficos a la escena de combate.
     */
    public void addElementos() {
        // Barra Estadísticas
        JPanel panelEstadisticas = new PanelEstadisticas(jugador);
        panelEstadisticas.setBounds(0, 0, getWidth(), 50);
        panelEstadisticas.setBackground(new Color(255, 255, 255, 150));
        panelEstadisticas.setLayout(null);
        add(panelEstadisticas);

        // Fondo
        JLabel backgroundLabel = new JLabel(new ImageIcon(imagenDeFondo));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        // Estados y estadísticas del jugador
        JLabel jugadorLabel = new JLabel(new ImageIcon(jugador.getIcono().getRutaIcono()));
        jugadorLabel.setBounds(50, 120, 150, 198);
        backgroundLabel.add(jugadorLabel);

        JPanel estadosJugadorPanel = new JPanel();
        estadosJugadorPanel.setBounds(50, 315, 150, 45);
        estadosJugadorPanel.setLayout(new GridLayout(2, 1));
        estadosJugadorPanel.setBackground(new Color(255, 255, 255, 120));
        addEstados(estadosJugadorPanel, jugador.getEstadosSufridos());
        backgroundLabel.add(estadosJugadorPanel);

        JPanel estadisticasJugadorPanel = new JPanel();
        estadisticasJugadorPanel.setBounds(50, 370, 150, 65);
        estadisticasJugadorPanel.setLayout(new BoxLayout(estadisticasJugadorPanel, BoxLayout.Y_AXIS));

        addEstadisticas(estadisticasJugadorPanel, jugador);
        estadisticasJugadorPanel.setBackground(new Color(255, 255, 255, 100));
        backgroundLabel.add(estadisticasJugadorPanel);

        // Estados y estadísticas de los enemigos
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null) {
                imagenesEnemigos[i] = new JLabel(new ImageIcon(enemigos[i].getIcono().getRutaIcono()));
                imagenesEnemigos[i].setBounds(getWidth() - 200 * (i + 1), 120, 150, 198);
                int finalI = i;
                imagenesEnemigos[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        elegido = finalI + 4;
                    }
                });
                backgroundLabel.add(imagenesEnemigos[i]);

                JPanel estadosEnemigoPanel = new JPanel();
                estadosEnemigoPanel.setBounds(getWidth() - 200 * (i + 1), 315, 150, 46);
                estadosEnemigoPanel.setLayout(new GridLayout(2, 1));

                addEstados(estadosEnemigoPanel, enemigos[i].getEstadosSufridos());
                estadosEnemigoPanel.setBackground(new Color(255, 255, 255, 120));
                backgroundLabel.add(estadosEnemigoPanel);

                JPanel estadisticasEnemigoPanel = new JPanel();
                estadisticasEnemigoPanel.setBounds(getWidth() - 200 * (i + 1), 370, 150, 50);
                estadisticasEnemigoPanel.setLayout(new BoxLayout(estadisticasEnemigoPanel, BoxLayout.Y_AXIS));

                addEstadisticas(estadisticasEnemigoPanel, enemigos[i]);
                estadisticasEnemigoPanel.setBackground(new Color(255, 255, 255, 100));
                backgroundLabel.add(estadisticasEnemigoPanel);
            }
        }

        // Texto de combate
        JPanel textoCombatePanel = new JPanel();
        textoCombatePanel.setBounds(0, getHeight() - 130, getWidth() - 400, 100);
        textoCombatePanel.setBackground(Color.WHITE);
        textoCombatePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        textoCombatePanel.add(textoCombateLabel);
        backgroundLabel.add(textoCombatePanel);

        // Opciones de combate
        int opcionY = getHeight() - 130;
        int botonWidth = 150;
        int botonHeight = 30;
        int espacioEntreBotones = 20;
        int startX = getWidth() - 350;

        switch (fase) {
            case 1:
                opciones = new String[]{"Ataque", "Ataque especial", "Defender", "Finta"};
                addBotones(backgroundLabel, opcionY, botonWidth, botonHeight, espacioEntreBotones, startX);
                break;
            case 2:
                opciones = new String[]{
                        jugador.getAtaques().get(0).toString(),
                        jugador.getAtaques().get(1).toString(),
                        jugador.getAtaques().get(2).toString(),
                        jugador.getAtaques().get(3).toString()
                };
                addBotones(backgroundLabel, opcionY, botonWidth, botonHeight, espacioEntreBotones, startX);
                break;
            case 3:
                opciones = new String[]{"Seguir"};
                int seguirBotonX = startX + botonWidth + espacioEntreBotones / 2 - botonWidth / 2;
                botones[0] = new JButton(opciones[0]);
                botones[0].setBounds(seguirBotonX, opcionY, botonWidth, botonHeight);
                backgroundLabel.add(botones[0]);
                botones[0].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        elegido = 0;
                    }
                });
                break;
        }
    }

    /**
     * Añade los botones de opciones a la escena.
     *
     * @param backgroundLabel      El label de fondo donde se añaden los botones.
     * @param opcionY              La coordenada Y de los botones.
     * @param botonWidth           El ancho de los botones.
     * @param botonHeight          La altura de los botones.
     * @param espacioEntreBotones  El espacio entre los botones.
     * @param startX               La coordenada X inicial de los botones.
     */
    private void addBotones(JLabel backgroundLabel, int opcionY, int botonWidth, int botonHeight, int espacioEntreBotones, int startX) {
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton(opciones[i]);
            if (i < 2) {
                botones[i].setBounds(startX + i * (botonWidth + espacioEntreBotones), opcionY, botonWidth, botonHeight);
            } else {
                botones[i].setBounds(startX + (i - 2) * (botonWidth + espacioEntreBotones), opcionY + 40, botonWidth, botonHeight);
            }
            backgroundLabel.add(botones[i]);
            int finalI = i;
            botones[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    elegido = finalI;
                }
            });
        }
    }

    /**
     * Añade los estados de una entidad al panel proporcionado.
     *
     * @param panel            El panel donde se añaden los estados.
     * @param estadosSufridos  Los estados sufridos por la entidad.
     */
    private void addEstados(JPanel panel, Map<Estados, Integer> estadosSufridos) {
        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila1.setOpaque(false);
        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila2.setOpaque(false);
        int count = 0;

        for (Map.Entry<Estados, Integer> entry : estadosSufridos.entrySet()) {
            Estados estado = entry.getKey();
            int duracion = entry.getValue();
            if (estado != Estados.CONTRAATACANDO) {
                JLabel estadoLabel = new JLabel(new ImageIcon(estado.getIcono().getRutaIcono()));
                if (duracion != 0) {
                    estadoLabel.setText(String.valueOf(duracion));
                }

                if (count < 4) {
                    fila1.add(estadoLabel);
                } else {
                    fila2.add(estadoLabel);
                }
                count++;
            }
        }

        panel.add(fila1);
        panel.add(fila2);
    }

    /**
     * Añade las estadísticas de una entidad al panel proporcionado.
     *
     * @param panel    El panel donde se añaden las estadísticas.
     * @param entidad  La entidad cuyas estadísticas se añaden.
     */
    private void addEstadisticas(JPanel panel, Entidad entidad) {
        panel.add(new JLabel(String.valueOf(entidad.getSalud()), new ImageIcon(Iconos.SALUD.getRutaIcono()), SwingConstants.LEFT));
        panel.add(new JLabel(String.valueOf(entidad.getDmg()), new ImageIcon(Iconos.DMG.getRutaIcono()), SwingConstants.LEFT));
        panel.add(new JLabel(String.valueOf(entidad.getDefensa()), new ImageIcon(Iconos.DEFENSA.getRutaIcono()), SwingConstants.LEFT));
        if (entidad instanceof Jugador j) {
            panel.add(new JLabel(String.valueOf(j.getMana()), new ImageIcon(Iconos.MANA.getRutaIcono()), SwingConstants.LEFT));
        }
    }

    @Override
    public void actualizarEscena(int fase) {
        this.fase = fase;
        actualizarInterfaz();
    }

    /**
     * Muestra un mensaje en el panel inferior izquierdo
     *
     * @param mensaje El mensaje a mostrar.
     */
    @Override
    public void mostrarMensaje(String mensaje) {
        textoCombateLabel.setText(mensaje);
        actualizarInterfaz();
    }

    @Override
    public void actualizarInterfaz() {
        this.removeAll();
        addElementos();
        this.revalidate();
        this.repaint();
        deshabilitarBotones();
    }

    @Override
    public void deshabilitarBotones() {
        for (JButton boton : botones) {
            boton.setEnabled(false);
        }
    }

    @Override
    public void habilitarBotones() {
        for (JButton boton : botones) {
            boton.setEnabled(true);
        }
    }
}