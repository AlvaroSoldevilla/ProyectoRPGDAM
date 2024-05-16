package UI.Interfaces;

import Modelo.Bases.Enemigo;
import Modelo.Bases.Jugador;
import Modelo.Bases.Entidad;
import Modelo.Enums.Estados;
import Modelo.Enums.Iconos;
import UI.Elementos.Contenedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UICombate extends Contenedor {
    private Jugador jugador;
    private Enemigo[] enemigos;
    private JLabel[] imagenesEnemigos;
    private JButton[] botones = new JButton[4];
    private List<JLabel> estados = new ArrayList<>();
    JLabel textoCombateLabel = new JLabel("Empieza El combate");
    private String[] opciones;
    private int fase = 1;

    public UICombate(String imagenDeFondo, Jugador jugador, Enemigo[] enemigos) {
        super(imagenDeFondo);
        this.jugador = jugador;
        this.enemigos = enemigos;
        this.imagenesEnemigos = new JLabel[3]; // Asumimos que hay tres enemigos
        setLayout(null); // Usamos null para posicionar los componentes manualmente
        addElementos();
    }

    public void addElementos() {
        // Fondo
        JLabel backgroundLabel = new JLabel(new ImageIcon(imagenDeFondo));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        // Estados y estadísticas del jugador
        JLabel jugadorLabel = new JLabel(new ImageIcon(jugador.getIcono().getRutaIcono())); // Reemplaza con la ruta correcta
        jugadorLabel.setBounds(50, 120, 150, 198); // Ajustar posición
        jugadorLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Jugador seleccionado");
            }
        });
        backgroundLabel.add(jugadorLabel);

        JPanel estadosJugadorPanel = new JPanel();
        estadosJugadorPanel.setBounds(50, 315, 150, 45); // Ajustar tamaño para dos filas de estados
        estadosJugadorPanel.setLayout(new GridLayout(2, 1)); // GridLayout con 2 filas y 1 columna
        estadosJugadorPanel.setBackground(new Color(255,255,255,120));
        addEstados(estadosJugadorPanel, jugador.getEstadosSufridos());
        backgroundLabel.add(estadosJugadorPanel);

        JPanel estadisticasJugadorPanel = new JPanel();
        estadisticasJugadorPanel.setBounds(50, 370, 150, 50);
        estadisticasJugadorPanel.setLayout(new BoxLayout(estadisticasJugadorPanel, BoxLayout.Y_AXIS));

        addEstadisticas(estadisticasJugadorPanel, jugador);
        estadisticasJugadorPanel.setBackground(new Color(255,255,255,100));
        backgroundLabel.add(estadisticasJugadorPanel);

        // Estados y estadísticas de los enemigos
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null) {
                imagenesEnemigos[i] = new JLabel(new ImageIcon(enemigos[i].getIcono().getRutaIcono())); // Reemplaza con la ruta correcta
                imagenesEnemigos[i].setBounds(getWidth() - 200 * (i + 1), 120, 150, 198); // Ajustar posición
                int finalI = i;
                imagenesEnemigos[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Enemigo seleccionado: " + (finalI + 4));
                    }
                });
                backgroundLabel.add(imagenesEnemigos[i]);

                JPanel estadosEnemigoPanel = new JPanel();
                estadosEnemigoPanel.setBounds(getWidth() - 200 * (i + 1), 315, 150, 46); // Ajustar tamaño para dos filas de estados
                estadosEnemigoPanel.setLayout(new GridLayout(2, 1)); // GridLayout con 2 filas y 1 columna

                addEstados(estadosEnemigoPanel, enemigos[i].getEstadosSufridos());
                estadosEnemigoPanel.setBackground(new Color(255,255,255,120));
                backgroundLabel.add(estadosEnemigoPanel);

                JPanel estadisticasEnemigoPanel = new JPanel();
                estadisticasEnemigoPanel.setBounds(getWidth() - 200 * (i + 1), 370, 150, 50);
                estadisticasEnemigoPanel.setLayout(new BoxLayout(estadisticasEnemigoPanel, BoxLayout.Y_AXIS));

                addEstadisticas(estadisticasEnemigoPanel, enemigos[i]);
                estadisticasEnemigoPanel.setBackground(new Color(255,255,255,100));
                backgroundLabel.add(estadisticasEnemigoPanel);
            }
        }

        // Texto de combate
        JPanel textoCombatePanel = new JPanel();
        textoCombatePanel.setBounds(0, getHeight() - 130, getWidth() - 400, 100); // Sin margen izquierdo ni inferior
        textoCombatePanel.setBackground(Color.WHITE);
        textoCombatePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        textoCombatePanel.add(textoCombateLabel);
        backgroundLabel.add(textoCombatePanel);

        // Opciones de combate
        int opcionY = getHeight() - 130;
        int botonWidth = 150;
        int botonHeight = 30;
        int espacioEntreBotones = 20;
        int startX = getWidth() - 350; // Mover opciones más a la derecha

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
                // Ajustar el botón "Seguir" para que esté en el medio del área donde estarían los 4 botones
                int seguirBotonX = startX + botonWidth + espacioEntreBotones / 2 - botonWidth / 2;
                botones[0] = new JButton(opciones[0]);
                botones[0].setBounds(seguirBotonX, opcionY, botonWidth, botonHeight); // Centrar el botón en el área de los cuatro botones
                backgroundLabel.add(botones[0]);
                botones[0].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Aquí puedes manejar la lógica cuando se selecciona una opción
                        System.out.println(((JButton) e.getSource()).getText() + " seleccionada");
                        elegido = 0;
                        seguir = true;
                    }
                });
                break;
        }
    }

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
                    // Aquí puedes manejar la lógica cuando se selecciona una opción
                    System.out.println(((JButton) e.getSource()).getText() + " seleccionada");
                    elegido = finalI;
                    seguir = true;
                }
            });
        }
    }

    private void addEstados(JPanel panel, Map<Estados, Integer> estadosSufridos) {
        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila1.setOpaque(false);
        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila2.setOpaque(false);
        int count = 0;

        for (Map.Entry<Estados, Integer> entry : estadosSufridos.entrySet()) {
            Estados estado = entry.getKey();
            int duracion = entry.getValue();
            if (estado!=Estados.CONTRAATACANDO) {
                JLabel estadoLabel = new JLabel(new ImageIcon(estado.getIcono().getRutaIcono())); // Agregar el icono del estado
                estadoLabel.setText(String.valueOf(duracion)); // Solo mostrar la duración

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

    private void addEstadisticas(JPanel panel, Entidad entidad) {
        panel.add(new JLabel("Salud: " + entidad.getSalud()));
        panel.add(new JLabel("Daño: " + entidad.getDmg()));
        panel.add(new JLabel("Defensa: " + entidad.getDefensa()));
        // Agrega aquí más estadísticas si es necesario
    }

    @Override
    public void actualizarEscena(int fase) {
        this.fase = fase;
        actualizarInterfaz();
    }

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
    }
}
