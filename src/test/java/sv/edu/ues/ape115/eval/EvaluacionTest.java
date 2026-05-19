package sv.edu.ues.ape115.eval;

import org.junit.jupiter.api.*;
import sv.edu.ues.ape115.eval.ui.VentanaEvaluacion;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * APE 115 — Evaluación: Layout Managers y Bordes
 * Autograder — 18 tests × 100 puntos totales
 *
 * Ejecutar localmente: mvn test
 * Ejecutar en CI:      GitHub Actions (push a main)
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("APE 115 — Evaluación Layout Managers y Bordes")
class EvaluacionTest {

    static VentanaEvaluacion ventana;

    @BeforeAll
    static void setup() {
        System.setProperty("java.awt.headless", "true");
        ventana = new VentanaEvaluacion();
    }

    // ── Helper: busca un componente por tipo dentro de un contenedor ─
    static <T extends Component> Optional<T> find(Container root, Class<T> type) {
        for (Component c : root.getComponents()) {
            if (type.isInstance(c)) return Optional.of(type.cast(c));
            if (c instanceof Container) {
                Optional<T> r = find((Container) c, type);
                if (r.isPresent()) return r;
            }
        }
        return Optional.empty();
    }

    static <T extends Component> List<T> findAll(Container root, Class<T> type) {
        List<T> list = new ArrayList<>();
        for (Component c : root.getComponents()) {
            if (type.isInstance(c)) list.add(type.cast(c));
            if (c instanceof Container) list.addAll(findAll((Container) c, type));
        }
        return list;
    }

    // ════════════════════════════════════════════════════════════
    // EJERCICIO 1 — BorderLayout + LineBorder  (10 pts)
    // ════════════════════════════════════════════════════════════

    @Test @Order(1)
    @DisplayName("T01.1 [3pts] E1: Panel NORTH no es el panel vacío por defecto")
    void t01_1_northNoEsVacio() {
        JPanel north = ventana.ejercicio1_North();
        assertNotNull(north, "ejercicio1_North() no debe retornar null");
        assertTrue(north.getComponentCount() > 0,
            "El panel NORTH debe tener al menos 1 componente (JLabel de título)");
    }

    @Test @Order(2)
    @DisplayName("T01.2 [4pts] E1: Panel NORTH tiene LineBorder de COLOR_PRIMARIO")
    void t01_2_northLineBorder() {
        JPanel north = ventana.ejercicio1_North();
        Border b = north.getBorder();
        assertNotNull(b, "El panel NORTH debe tener un borde (LineBorder)");
        // Acepta LineBorder directo o CompoundBorder que contenga LineBorder
        boolean tieneLineBorder = (b instanceof LineBorder) ||
            (b instanceof CompoundBorder &&
             ((CompoundBorder) b).getOutsideBorder() instanceof LineBorder);
        assertTrue(tieneLineBorder,
            "El borde del panel NORTH debe ser LineBorder (o CompoundBorder con LineBorder exterior)");
        if (b instanceof LineBorder lb) {
            assertEquals(VentanaEvaluacion.COLOR_PRIMARIO, lb.getLineColor(),
                "El LineBorder debe usar COLOR_PRIMARIO");
        }
    }

    @Test @Order(3)
    @DisplayName("T01.3 [3pts] E1: Panel NORTH tiene FlowLayout con alineación LEFT")
    void t01_3_northFlowLayout() {
        JPanel north = ventana.ejercicio1_North();
        LayoutManager lm = north.getLayout();
        assertTrue(lm instanceof FlowLayout,
            "El layout del panel NORTH debe ser FlowLayout, encontrado: " +
            (lm == null ? "null" : lm.getClass().getSimpleName()));
        FlowLayout fl = (FlowLayout) lm;
        assertEquals(FlowLayout.LEFT, fl.getAlignment(),
            "FlowLayout debe tener alineación LEFT (FlowLayout.LEFT = 0)");
    }

    // ════════════════════════════════════════════════════════════
    // EJERCICIO 2 — BoxLayout + CompoundBorder  (15 pts)
    // ════════════════════════════════════════════════════════════

    @Test @Order(4)
    @DisplayName("T02.1 [5pts] E2: Panel WEST tiene BoxLayout Y_AXIS")
    void t02_1_westBoxLayout() {
        JPanel west = ventana.ejercicio2_West();
        assertNotNull(west, "ejercicio2_West() no debe retornar null");
        LayoutManager lm = west.getLayout();
        assertTrue(lm instanceof BoxLayout,
            "El layout del panel WEST debe ser BoxLayout, encontrado: " +
            (lm == null ? "null" : lm.getClass().getSimpleName()));
        BoxLayout bl = (BoxLayout) lm;
        assertEquals(BoxLayout.Y_AXIS, bl.getAxis(),
            "BoxLayout debe ser Y_AXIS");
    }

    @Test @Order(5)
    @DisplayName("T02.2 [5pts] E2: Panel WEST tiene CompoundBorder con TitledBorder")
    void t02_2_westCompoundBorder() {
        JPanel west = ventana.ejercicio2_West();
        Border b = west.getBorder();
        assertNotNull(b, "El panel WEST debe tener un borde CompoundBorder");
        assertTrue(b instanceof CompoundBorder,
            "El borde del WEST debe ser CompoundBorder");
        CompoundBorder cb = (CompoundBorder) b;
        assertTrue(cb.getOutsideBorder() instanceof TitledBorder ||
                   cb.getInsideBorder() instanceof TitledBorder,
            "CompoundBorder debe contener un TitledBorder");
    }

    @Test @Order(6)
    @DisplayName("T02.3 [5pts] E2: Panel WEST tiene al menos 5 botones de menú")
    void t02_3_westBotones() {
        JPanel west = ventana.ejercicio2_West();
        List<JButton> botones = findAll(west, JButton.class);
        assertTrue(botones.size() >= 5,
            "El menú lateral debe tener al menos 5 botones, encontrados: " + botones.size());
        // Verificar que usan LEFT_ALIGNMENT
        long conLeftAlign = botones.stream()
            .filter(b -> b.getAlignmentX() == Component.LEFT_ALIGNMENT)
            .count();
        assertTrue(conLeftAlign >= 4,
            "Al menos 4 botones deben tener setAlignmentX(LEFT_ALIGNMENT)");
    }

    // ════════════════════════════════════════════════════════════
    // EJERCICIO 3 — JSplitPane  (10 pts)
    // ════════════════════════════════════════════════════════════

    @Test @Order(7)
    @DisplayName("T03.1 [5pts] E3: splitPane es un JSplitPane HORIZONTAL_SPLIT")
    void t03_1_splitPaneHorizontal() {
        ventana.ejercicio3_Center();
        assertNotNull(ventana.splitPane,
            "this.splitPane debe asignarse en ejercicio3_Center()");
        assertEquals(JSplitPane.HORIZONTAL_SPLIT, ventana.splitPane.getOrientation(),
            "JSplitPane debe ser HORIZONTAL_SPLIT");
    }

    @Test @Order(8)
    @DisplayName("T03.2 [5pts] E3: JSplitPane tiene oneTouchExpandable=true y resizeWeight definido")
    void t03_2_splitPaneConfig() {
        ventana.ejercicio3_Center();
        assertNotNull(ventana.splitPane, "splitPane no debe ser null");
        assertTrue(ventana.splitPane.isOneTouchExpandable(),
            "JSplitPane debe tener setOneTouchExpandable(true)");
        // resizeWeight debe estar entre 0.3 y 0.6 (no en el defecto 0.0)
        double rw = ventana.splitPane.getResizeWeight();
        assertTrue(rw > 0.0 && rw <= 1.0,
            "JSplitPane.setResizeWeight() debe haberse configurado (valor > 0.0), encontrado: " + rw);
    }

    // ════════════════════════════════════════════════════════════
    // EJERCICIO 4 — GridBagLayout + campos del formulario  (20 pts)
    // ════════════════════════════════════════════════════════════

    @Test @Order(9)
    @DisplayName("T04.1 [5pts] E4: Formulario usa GridBagLayout")
    void t04_1_formularioGridBag() {
        JPanel form = ventana.ejercicio4_FormularioGBL();
        assertNotNull(form, "ejercicio4_FormularioGBL() no debe retornar null");
        LayoutManager lm = form.getLayout();
        assertTrue(lm instanceof GridBagLayout,
            "El formulario debe usar GridBagLayout, encontrado: " +
            (lm == null ? "null" : lm.getClass().getSimpleName()));
    }

    @Test @Order(10)
    @DisplayName("T04.2 [5pts] E4: Formulario tiene los 3 JTextField creados")
    void t04_2_formularioCampos() {
        ventana.ejercicio4_FormularioGBL();
        assertNotNull(ventana.txtNombre,
            "this.txtNombre debe asignarse en ejercicio4_FormularioGBL()");
        assertNotNull(ventana.txtEmail,
            "this.txtEmail debe asignarse en ejercicio4_FormularioGBL()");
        assertNotNull(ventana.txtTelefono,
            "this.txtTelefono debe asignarse en ejercicio4_FormularioGBL()");
    }

    @Test @Order(11)
    @DisplayName("T04.3 [5pts] E4: Formulario tiene JComboBox con los departamentos")
    void t04_3_formularioComboBox() {
        ventana.ejercicio4_FormularioGBL();
        assertNotNull(ventana.cboDepartamento,
            "this.cboDepartamento debe asignarse en ejercicio4_FormularioGBL()");
        assertEquals(VentanaEvaluacion.DEPARTAMENTOS.length,
            ventana.cboDepartamento.getItemCount(),
            "JComboBox debe cargarse con DEPARTAMENTOS (" +
            VentanaEvaluacion.DEPARTAMENTOS.length + " ítems)");
    }

    @Test @Order(12)
    @DisplayName("T04.4 [5pts] E4: Formulario tiene CompoundBorder (TitledBorder + EmptyBorder)")
    void t04_4_formularioBorde() {
        JPanel form = ventana.ejercicio4_FormularioGBL();
        Border b = form.getBorder();
        assertNotNull(b, "El formulario debe tener un borde CompoundBorder");
        assertTrue(b instanceof CompoundBorder,
            "El borde del formulario debe ser CompoundBorder");
        CompoundBorder cb = (CompoundBorder) b;
        boolean tieneTitled = (cb.getOutsideBorder() instanceof TitledBorder) ||
                              (cb.getInsideBorder() instanceof TitledBorder);
        assertTrue(tieneTitled, "CompoundBorder debe incluir un TitledBorder");
        boolean tieneEmpty  = (cb.getOutsideBorder() instanceof EmptyBorder) ||
                              (cb.getInsideBorder() instanceof EmptyBorder);
        assertTrue(tieneEmpty, "CompoundBorder debe incluir un EmptyBorder (padding interior)");
    }

    // ════════════════════════════════════════════════════════════
    // EJERCICIO 5 — JTabbedPane + GridLayout + EtchedBorder  (20 pts)
    // ════════════════════════════════════════════════════════════

    @Test @Order(13)
    @DisplayName("T05.1 [6pts] E5: tabbedPane es un JTabbedPane con 3 pestañas")
    void t05_1_tabbedPaneTresPestanas() {
        ventana.ejercicio5_TabbedPane();
        assertNotNull(ventana.tabbedPane,
            "this.tabbedPane debe asignarse en ejercicio5_TabbedPane()");
        assertEquals(3, ventana.tabbedPane.getTabCount(),
            "JTabbedPane debe tener exactamente 3 pestañas");
    }

    @Test @Order(14)
    @DisplayName("T05.2 [7pts] E5: Pestaña Resumen tiene GridLayout(3,2)")
    void t05_2_pestanaResumenGridLayout() {
        ventana.ejercicio5_TabbedPane();
        assertNotNull(ventana.tabbedPane, "tabbedPane no debe ser null");
        // Buscar un JPanel con GridLayout dentro del tabbedPane
        boolean tieneGridLayout = false;
        for (int i = 0; i < ventana.tabbedPane.getTabCount(); i++) {
            Component tab = ventana.tabbedPane.getComponentAt(i);
            if (tab instanceof JPanel) {
                LayoutManager lm = ((JPanel) tab).getLayout();
                if (lm instanceof GridLayout gl && gl.getRows() == 3 && gl.getColumns() == 2) {
                    tieneGridLayout = true;
                    break;
                }
            }
            // Buscar en profundidad
            if (tab instanceof Container) {
                for (Component inner : ((Container) tab).getComponents()) {
                    if (inner instanceof JPanel) {
                        LayoutManager lm = ((JPanel) inner).getLayout();
                        if (lm instanceof GridLayout gl && gl.getRows() == 3 && gl.getColumns() == 2) {
                            tieneGridLayout = true;
                            break;
                        }
                    }
                }
            }
        }
        assertTrue(tieneGridLayout,
            "La pestaña Resumen debe contener un JPanel con GridLayout(3, 2)");
    }

    @Test @Order(15)
    @DisplayName("T05.3 [7pts] E5: Pestaña Notas tiene JScrollPane con JTextArea")
    void t05_3_pestanaNotas() {
        ventana.ejercicio5_TabbedPane();
        assertNotNull(ventana.tabbedPane, "tabbedPane no debe ser null");
        List<JScrollPane> scrollPanes = findAll(ventana.tabbedPane, JScrollPane.class);
        assertTrue(scrollPanes.size() >= 1,
            "Debe haber al menos un JScrollPane en el tabbedPane (pestaña Notas)");
        boolean tieneTextArea = scrollPanes.stream().anyMatch(sp -> {
            Component view = sp.getViewport().getView();
            return view instanceof JTextArea;
        });
        assertTrue(tieneTextArea,
            "Al menos un JScrollPane debe contener un JTextArea (pestaña Notas)");
    }

    // ════════════════════════════════════════════════════════════
    // EJERCICIO 6 — BevelBorder BONUS  (5 pts)
    // ════════════════════════════════════════════════════════════

    @Test @Order(16)
    @DisplayName("T06.1 [5pts BONUS] E6: Panel bevel tiene BevelBorder en algún sub-panel")
    void t06_1_bevelBorder() {
        JPanel bevel = ventana.ejercicio6_BevelBorder();
        assertNotNull(bevel, "ejercicio6_BevelBorder() no debe retornar null");
        if (bevel.getComponentCount() == 0) {
            // Si no está implementado, el test falla silenciosamente (es BONUS)
            fail("BONUS no implementado — ejercicio6_BevelBorder() no tiene componentes");
        }
        List<JPanel> subPanels = findAll(bevel, JPanel.class);
        // Incluye el propio bevel como JPanel
        subPanels.add(0, bevel);
        boolean tieneBevel = subPanels.stream().anyMatch(p -> {
            Border b = p.getBorder();
            return (b instanceof BevelBorder) || (b instanceof SoftBevelBorder);
        });
        assertTrue(tieneBevel,
            "Al menos un sub-panel debe tener BevelBorder o SoftBevelBorder");
    }

    // ════════════════════════════════════════════════════════════
    // EJERCICIO 7 — FlowLayout(RIGHT) + MatteBorder SOUTH  (5 pts)
    // ════════════════════════════════════════════════════════════

    @Test @Order(17)
    @DisplayName("T07.1 [3pts] E7: Panel SOUTH tiene FlowLayout RIGHT y MatteBorder")
    void t07_1_southFlowLayoutYMatteBorder() {
        JPanel south = ventana.ejercicio7_South();
        assertNotNull(south, "ejercicio7_South() no debe retornar null");

        LayoutManager lm = south.getLayout();
        assertTrue(lm instanceof FlowLayout,
            "El panel SOUTH debe usar FlowLayout, encontrado: " +
            (lm == null ? "null" : lm.getClass().getSimpleName()));
        FlowLayout fl = (FlowLayout) lm;
        assertEquals(FlowLayout.RIGHT, fl.getAlignment(),
            "FlowLayout del SOUTH debe tener alineación RIGHT");

        Border b = south.getBorder();
        assertNotNull(b, "El panel SOUTH debe tener un borde MatteBorder");
        boolean tieneMatte = (b instanceof MatteBorder) ||
            (b instanceof CompoundBorder cb &&
             (cb.getOutsideBorder() instanceof MatteBorder ||
              cb.getInsideBorder() instanceof MatteBorder));
        assertTrue(tieneMatte,
            "El panel SOUTH debe tener MatteBorder (solo borde superior)");
    }

    @Test @Order(18)
    @DisplayName("T07.2 [2pts] E7: Panel SOUTH tiene al menos 3 botones")
    void t07_2_southTresBotones() {
        JPanel south = ventana.ejercicio7_South();
        List<JButton> botones = findAll(south, JButton.class);
        assertTrue(botones.size() >= 3,
            "El panel SOUTH debe tener 3 botones (Limpiar, Eliminar, Guardar), " +
            "encontrados: " + botones.size());
    }

    // ════════════════════════════════════════════════════════════
    // REGLA ANTI-TRAMPAS: null layout = penalización
    // ════════════════════════════════════════════════════════════

    @Test @Order(19)
    @DisplayName("PENALIZACIÓN: Ningún panel puede usar null layout")
    void pNullLayout() {
        List<JPanel> todos = findAll(ventana.getContentPane(), JPanel.class);
        List<String> infractores = todos.stream()
            .filter(p -> p.getLayout() == null)
            .map(p -> p.getClass().getSimpleName() + "@" + System.identityHashCode(p))
            .toList();
        assertTrue(infractores.isEmpty(),
            "PENALIZACIÓN: Se encontraron paneles con null layout: " + infractores +
            ". Usar null layout o setBounds() genera penalización de -10 pts.");
    }
}
