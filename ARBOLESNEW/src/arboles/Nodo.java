
package arboles;
// Clase que contiene el hijo izquierdo y derecho del nodo actual
// y el valor de la clave
public class Nodo {
    int clave;
    Nodo izquierdo, derecho;

    public Nodo(int elemento) {
        clave = elemento;
        izquierdo = derecho = null;
    }
}
