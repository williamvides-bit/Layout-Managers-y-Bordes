package sv.edu.ues.ape115.eval.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * APE 115 — Evaluación: Layout Managers y Bordes
 * ─────────────────────────────────────────────
 * Tiempo disponible: 80 minutos
 *
 * INSTRUCCIONES GENERALES:
 *   1. Lee el README.md completo antes de comenzar.
 *   2. Implementa SOLO los Ejercicios que puedas completar bien.
 *      Es mejor 3 ejercicios correctos que 5 incompletos.
 *   3. NO uses null layout ni setBounds() — penalización automática.
 *   4. Ejecuta: mvn test  para ver tu progreso antes de hacer push.
 *   5. Cada método corresponde a un ejercicio distinto e independiente.
 *
 * PAQUETE OBLIGATORIO: sv.edu.ues.ape115.eval.ui
 *
 * @author (escribe tu nombre y carnet aquí)
 */
public class VentanaEvaluacion extends JFrame {

    // ─── Constantes de color (NO modificar) ──────────────────
    static final Color COLOR_PRIMARIO   = new Color(25,  118, 210);  // azul
    static final Color COLOR_SECUNDARIO = new Color(46,  125,  50);  // verde
    static final Color COLOR_ACENTO     = new Color(198,  40,  40);  // rojo
    static final Color COLOR_NEUTRO     = new Color(80,   80,  80);  // gris

    // ─── Campos del formulario (necesarios para los tests) ───
    // Ejercicio 4 — GridBagLayout
    JTextField txtNombre;
    JTextField txtEmail;
    JTextField txtTelefono;
    JComboBox<String> cboDepartamento;

    // Ejercicio 5 — JTabbedPane
    JTabbedPane tabbedPane;

    // Ejercicio 6 — JSplitPane
    JSplitPane splitPane;

    // ─────────────────────────────────────────────────────────
    public VentanaEvaluacion() {
        super("APE 115 — Evaluación Layout Managers y Bordes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 680);
        setMinimumSize(new Dimension(800, 540));
        setLocationRelativeTo(null);
        construirUI();
    }

    /**
     * Ensambla todos los paneles en el JFrame.
     * NO modificar la estructura de este método.
     */
    private void construirUI() {
        JPanel root = new JPanel(new BorderLayout(6, 6));
        root.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        setContentPane(root);

        root.add(ejercicio1_North(),  BorderLayout.NORTH);
        root.add(ejercicio2_West(),   BorderLayout.WEST);
        root.add(ejercicio3_Center(), BorderLayout.CENTER);
        root.add(ejercicio7_South(),  BorderLayout.SOUTH);
    }

    // ═══════════════════════════════════════════════════════════
    // EJERCICIO 1 — BorderLayout + LineBorder (10 pts)
    // ═══════════════════════════════════════════════════════════
    /**
     * Crea el panel NORTH de la ventana.
     *
     * Requisitos:
     *   - Layout: FlowLayout(FlowLayout.LEFT, 12, 6)
     *   - Borde: LineBorder de COLOR_PRIMARIO, grosor 2
     *   - Contenido:
     *       · JLabel con el texto "Sistema de Registro — APE 115"
     *         fuente: Segoe UI, BOLD, 16pt, color COLOR_PRIMARIO
     *       · Box.createHorizontalStrut(20)  (separador)
     *       · JLabel con el texto "Evaluación: Layout Managers y Bordes"
     *         fuente: Segoe UI, PLAIN, 12pt, color COLOR_NEUTRO
     *
     * @return JPanel configurado
     */
    JPanel ejercicio1_North() {
        // TODO: implementar
        // Hint: new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6))
        //       setBorder(new LineBorder(COLOR_PRIMARIO, 2))
        return new JPanel(); // reemplazar con la implementación correcta
    }

    // ═══════════════════════════════════════════════════════════
    // EJERCICIO 2 — BoxLayout(Y_AXIS) + CompoundBorder (15 pts)
    // ═══════════════════════════════════════════════════════════
    /**
     * Crea el panel WEST con menú lateral usando BoxLayout vertical.
     *
     * Requisitos:
     *   - Layout: BoxLayout(panel, BoxLayout.Y_AXIS)
     *   - Ancho preferido: 170px
     *   - Borde: CompoundBorder de:
     *       · Exterior: TitledBorder("Módulos", EtchedBorder.LOWERED)
     *                   con fuente Segoe UI Bold 11pt, color COLOR_PRIMARIO
     *       · Interior: EmptyBorder(6, 8, 6, 8)
     *   - Contenido (en orden, usando el método crearBotonMenu):
     *       · "📋 Registros"    → COLOR_PRIMARIO
     *       · "👤 Empleados"    → COLOR_PRIMARIO
     *       · "📦 Inventario"   → COLOR_PRIMARIO
     *       · "📊 Reportes"     → COLOR_PRIMARIO
     *       · Box.createVerticalGlue()   (empuja el último botón al fondo)
     *       · "⚙ Configuración" → COLOR_NEUTRO
     *   - Entre cada botón: Box.createVerticalStrut(4)
     *   - Cada botón: setAlignmentX(LEFT_ALIGNMENT)
     *                 setMaximumSize(new Dimension(Integer.MAX_VALUE, 36))
     *
     * @return JPanel configurado
     */
    JPanel ejercicio2_West() {
        // TODO: implementar
        return new JPanel(); // reemplazar
    }

    // ═══════════════════════════════════════════════════════════
    // EJERCICIO 3 — JSplitPane + JTabbedPane (30 pts)
    // ═══════════════════════════════════════════════════════════
    /**
     * Crea el área CENTER con JSplitPane que divide:
     *   - Izquierda: ejercicio4_FormularioGBL() (formulario GridBagLayout)
     *   - Derecha:   ejercicio5_TabbedPane()    (panel con pestañas)
     *
     * Requisitos del JSplitPane:
     *   - Orientación: HORIZONTAL_SPLIT
     *   - setDividerLocation(380)
     *   - setResizeWeight(0.45)
     *   - setOneTouchExpandable(true)
     *   - Asignar a: this.splitPane
     *
     * @return JSplitPane configurado
     */
    JComponent ejercicio3_Center() {
        // TODO: implementar
        // Hint: splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        //                                 ejercicio4_FormularioGBL(),
        //                                 ejercicio5_TabbedPane());
        return new JPanel(); // reemplazar
    }

    // ═══════════════════════════════════════════════════════════
    // EJERCICIO 4 — GridBagLayout + MatteBorder (20 pts)
    // ═══════════════════════════════════════════════════════════
    /**
     * Crea el panel izquierdo del JSplitPane: formulario con GridBagLayout.
     *
     * Requisitos:
     *   - Layout: GridBagLayout
     *   - Borde: CompoundBorder de:
     *       · Exterior: TitledBorder("Datos del Empleado", EtchedBorder.LOWERED)
     *                   fuente Segoe UI Bold 11pt, color COLOR_PRIMARIO
     *       · Interior: EmptyBorder(8, 10, 8, 10)
     *   - GridBagConstraints base: insets = Insets(5, 6, 5, 6), anchor = WEST
     *
     *   Fila 0:
     *     col 0: JLabel "Nombre *:"
     *     col 1-3: this.txtNombre = new JTextField(20)
     *              gridwidth=3, fill=HORIZONTAL, weightx=1.0
     *
     *   Fila 1:
     *     col 0: JLabel "Email *:"
     *     col 1-3: this.txtEmail = new JTextField(20)
     *              gridwidth=3, fill=HORIZONTAL, weightx=1.0
     *
     *   Fila 2:
     *     col 0: JLabel "Teléfono:"
     *     col 1: this.txtTelefono = new JTextField(12)
     *              gridwidth=1, fill=HORIZONTAL, weightx=0.5
     *     col 2: JLabel "Departamento:"
     *     col 3: this.cboDepartamento = new JComboBox<>(DEPARTAMENTOS)
     *              fill=HORIZONTAL, weightx=0.5
     *
     *   Fila 3 (relleno vertical):
     *     col 0, gridwidth=4: panel vacío, fill=BOTH, weighty=1.0
     *
     *   Constante DEPARTAMENTOS (ya definida abajo, no modificar):
     *     {"Seleccionar...","Administración","Tecnología","Recursos Humanos","Finanzas","Ventas"}
     *
     * @return JPanel configurado
     */
    static final String[] DEPARTAMENTOS = {
        "Seleccionar...", "Administración", "Tecnología",
        "Recursos Humanos", "Finanzas", "Ventas"
    };

    JPanel ejercicio4_FormularioGBL() {
        // TODO: implementar
        return new JPanel(); // reemplazar
    }

    // ═══════════════════════════════════════════════════════════
    // EJERCICIO 5 — JTabbedPane + GridLayout + EtchedBorder (20 pts)
    // ═══════════════════════════════════════════════════════════
    /**
     * Crea el panel derecho del JSplitPane: JTabbedPane con 3 pestañas.
     *
     * Requisitos del JTabbedPane:
     *   - Posición de pestañas: JTabbedPane.TOP
     *   - Asignar a: this.tabbedPane
     *
     *   Pestaña 1 — "📋 Resumen":
     *     - JPanel con GridLayout(3, 2, 8, 8)
     *     - Borde: EtchedBorder(EtchedBorder.LOWERED)
     *     - EmptyBorder(10,10,10,10) exterior
     *     - 6 JLabel: "Empleados activos:", "142",
     *                 "Departamentos:",     "6",
     *                 "Último registro:",   "12/05/2026"
     *       Los valores ("142", "6", "12/05/2026") en negrita, color COLOR_PRIMARIO
     *
     *   Pestaña 2 — "📝 Notas":
     *     - JScrollPane con JTextArea(6, 20)
     *     - JTextArea: setLineWrap(true), setWrapStyleWord(true)
     *     - Borde del JScrollPane: TitledBorder("Observaciones")
     *
     *   Pestaña 3 — "ℹ Info":
     *     - JPanel con FlowLayout(FlowLayout.CENTER)
     *     - JLabel con texto "APE 115 — Ciclo III / 2026"
     *     - Borde: MatteBorder(2,0,2,0, COLOR_ACENTO) — solo arriba y abajo
     *
     * @return JTabbedPane configurado
     */
    JTabbedPane ejercicio5_TabbedPane() {
        // TODO: implementar
        // Hint: tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        return new JTabbedPane(); // reemplazar
    }

    // ═══════════════════════════════════════════════════════════
    // EJERCICIO 6 — BevelBorder + SoftBevelBorder (5 pts BONUS)
    // ═══════════════════════════════════════════════════════════
    /**
     * Crea un JPanel de demostración de bordes BONUS.
     * Este método es llamado por el test T06 solamente.
     *
     * Requisitos:
     *   - Layout: GridLayout(1, 2, 10, 0)
     *   - Panel izquierdo:
     *       · BevelBorder(BevelBorder.RAISED)
     *       · JLabel centrado: "BevelBorder RAISED"
     *   - Panel derecho:
     *       · SoftBevelBorder(SoftBevelBorder.LOWERED)
     *       · JLabel centrado: "SoftBevelBorder LOWERED"
     *
     * @return JPanel con 2 sub-paneles que demuestran los bordes
     */
    JPanel ejercicio6_BevelBorder() {
        // TODO: implementar (BONUS — 5 pts extra)
        return new JPanel(); // reemplazar
    }

    // ═══════════════════════════════════════════════════════════
    // EJERCICIO 7 — FlowLayout(RIGHT) + MatteBorder SOUTH (5 pts)
    // ═══════════════════════════════════════════════════════════
    /**
     * Crea el panel SOUTH con los botones de acción.
     *
     * Requisitos:
     *   - Layout: FlowLayout(FlowLayout.RIGHT, 8, 6)
     *   - Borde: MatteBorder(1, 0, 0, 0, new Color(200, 200, 200))
     *            (solo borde superior — separador visual)
     *   - Contenido (en orden, usando crearBoton):
     *       · "Limpiar"  → COLOR_NEUTRO
     *       · "Eliminar" → COLOR_ACENTO
     *       · "Guardar"  → COLOR_SECUNDARIO
     *   - Los tres botones deben estar en el panel
     *
     * @return JPanel configurado
     */
    JPanel ejercicio7_South() {
        // TODO: implementar
        return new JPanel(); // reemplazar
    }

    // ═══════════════════════════════════════════════════════════
    // HELPERS — NO modificar estos métodos
    // ═══════════════════════════════════════════════════════════

    /**
     * Crea un botón del menú lateral con estilo estándar.
     * NO modificar.
     */
    static JButton crearBotonMenu(String label, Color color) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        return btn;
    }

    /**
     * Crea un botón de acción con estilo estándar APE115.
     * NO modificar.
     */
    static JButton crearBoton(String label, Color color) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(7, 18, 7, 18));
        return btn;
    }

    /** Punto de entrada — NO modificar. */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaEvaluacion().setVisible(true));
    }
}
