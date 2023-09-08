package arboles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MENU extends JFrame implements ActionListener {
    private final JTextArea RECORRIDOS;
    private final JTextArea ARBOL;
    private final ArbolBinario arbol;

    public MENU() {
        // Creacion del arbol con sus nodos
        arbol = new ArbolBinario();
        arbol.raiz = new Nodo(1);
        arbol.raiz.izquierdo = new Nodo(2);
        arbol.raiz.derecho = new Nodo(3);
        arbol.raiz.izquierdo.izquierdo = new Nodo(4);
        arbol.raiz.izquierdo.derecho = new Nodo(5);
        arbol.raiz.derecho.izquierdo = new Nodo(6);
        arbol.raiz.derecho.derecho = new Nodo(7);
        arbol.raiz.izquierdo.izquierdo.izquierdo = new Nodo(8);
        arbol.raiz.izquierdo.izquierdo.derecho = new Nodo(9);
        arbol.raiz.izquierdo.derecho.izquierdo = new Nodo(10);
        arbol.raiz.izquierdo.derecho.derecho = new Nodo(11);
        arbol.raiz.derecho.izquierdo.izquierdo = new Nodo(12);
        arbol.raiz.derecho.izquierdo.derecho = new Nodo(13);
        arbol.raiz.derecho.derecho.izquierdo = new Nodo(14);
        arbol.raiz.derecho.derecho.derecho = new Nodo(15);

        int LARGO = 1000;
        int ANCHO = 500;
        setTitle("Recorridos de Árbol Binario");
        setSize(LARGO, ANCHO);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                OK_SHUTDOWN();
            }
        });

        
        // JPanel para contener los componentes
        JPanel panel = new JPanel(new BorderLayout());

        // JSplitPane para dividir los JTextArea horizontalmente
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // JTextArea para los recorridos
        RECORRIDOS = new JTextArea(1, 70);
        RECORRIDOS.setEditable(false);
        RECORRIDOS.setFont(new Font("Monospaced", Font.BOLD, 15));
        RECORRIDOS.setBackground(Color.LIGHT_GRAY);
        RECORRIDOS.setForeground(Color.BLACK);
        JScrollPane BARRA_RECORRIDO = new JScrollPane(RECORRIDOS);

        // JTextArea para el árbol visual
        ARBOL = new JTextArea(10, 30);
        ARBOL.setEditable(false);
        JScrollPane BARRA_TREE = new JScrollPane(ARBOL);

        // Configurar el JSplitPane con los JTextArea
        splitPane.setLeftComponent(BARRA_RECORRIDO);
        splitPane.setRightComponent(BARRA_TREE);

        // Agregar el JSplitPane al panel principal
        panel.add(splitPane, BorderLayout.CENTER);
        
        JPanel OPCIONES = new JPanel(new FlowLayout());    
        // JButton para los botones de la interfaz
        JButton BTN_PRE = new JButton("Preorden");
        JButton BTN_IN = new JButton("Inorden");
        JButton BTN_POST = new JButton("Postorden");
        JButton VIEW_TREE = new JButton("Mostrar Árbol");
        JButton limpiarButton = new JButton("Limpiar");

        // LLamado de accion para los botones
        BTN_PRE.addActionListener(this);
        BTN_IN.addActionListener(this);
        BTN_POST.addActionListener(this);
        
        VIEW_TREE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarArbolVisualmente(); // Mostrar el árbol visualmente
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CLEAN_TEXT();
            }
        });

        // Añadir botones a la interfaz
        OPCIONES.add(BTN_PRE);
        OPCIONES.add(BTN_IN);
        OPCIONES.add(BTN_POST);
        OPCIONES.add(VIEW_TREE);
        OPCIONES.add(limpiarButton);

        add(OPCIONES, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }

    // Funcion que da utilidad a los botones del programa
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        arbol.limpiarResultado(); // Limpiar el resultado antes de un nuevo recorrido en ArbolBinario
        switch (actionCommand)
        {
            case "Preorden" ->{
                RECORRIDOS.setText("");
                RECORRIDOS.append("\nRecorrido en preorden del árbol binario es:\n");
                arbol.imprimirPreorden(arbol.raiz);
                RECORRIDOS.setForeground(Color.DARK_GRAY);
                RECORRIDOS.append(arbol.obtenerResultado());
            }
            case "Inorden" ->{
                RECORRIDOS.setText("");
                RECORRIDOS.append("\nRecorrido en inorden del árbol binario es:\n");
                arbol.imprimirEnOrden(arbol.raiz);
                RECORRIDOS.setForeground(Color.BLUE);
                RECORRIDOS.append(arbol.obtenerResultado());
            }
            case "Postorden" ->{
                RECORRIDOS.setText("");
                RECORRIDOS.append("\nRecorrido en postorden del árbol binario es:\n");
                RECORRIDOS.setForeground(Color.RED);
                arbol.imprimirPostorden(arbol.raiz);
                RECORRIDOS.append(arbol.obtenerResultado());
            }
            default ->
            {
            }
        }
    }

    //Funcion para llamar al arbol al area de texto
    private void mostrarArbolVisualmente() {
        ARBOL.setText(""); // Limpiar el JTextArea antes de mostrar el árbol
        new VisualArbol(arbol.raiz, ARBOL);
    }

    //Funcion para limpiar areas de texto
    private void CLEAN_TEXT() {
        String[] options = {"Recorridos", "Árbol", "Cancelar"};
        int choice = JOptionPane.showOptionDialog(
            this,
            "¿Qué Espacio desea limpiar?",
            "Limpiar Espacio",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        if (choice == 0) {
            RECORRIDOS.setText("");
        } else if (choice == 1) {
            ARBOL.setText("");
        }
    }
    public static void OK_SHUTDOWN() {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Desea apagar el programa?",
            "Confirmar apagado",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.WARNING_MESSAGE // Agrega el icono de alerta
        );
        if (confirmacion == JOptionPane.OK_OPTION) {
            // El usuario eligió "Aceptar", cerrar la aplicación
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MENU frame = new MENU();
            frame.setVisible(true);
        });
    } 
}
