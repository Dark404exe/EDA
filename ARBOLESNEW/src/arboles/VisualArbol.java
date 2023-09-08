package arboles;

import javax.swing.*;
import java.awt.*;

public class VisualArbol extends JFrame {

    private final JTextArea treeTextArea;

    // Funcion encargada de imprimir el arbol de manera visual en el cuadro de texto
    public VisualArbol(Nodo raiz, JTextArea arbolTextArea) {
        treeTextArea = arbolTextArea;
        treeTextArea.setBackground(Color.BLACK);
        treeTextArea.setForeground(Color.GREEN);
        treeTextArea.setFont(new Font("Monospaced", Font.BOLD, 15));
        treeTextArea.setEditable(false);
        construirArbolVisual(raiz, "", true);
    }

    // Funcion encargada de construir el arbol de manera visual para el cuadro de texto
    private void construirArbolVisual(Nodo nodo, String prefix, boolean isLeft) {
        if (nodo != null) {
            treeTextArea.append(prefix);
            treeTextArea.append(isLeft ? "├── " : "└── ");
            treeTextArea.append("[" + nodo.clave + "]\n");

            String newPrefix = prefix + (isLeft ? "│   " : "    ");
            construirArbolVisual(nodo.derecho, newPrefix, true);
            construirArbolVisual(nodo.izquierdo, newPrefix, false);

        }
    }

}
