package arboles;

public class ArbolBinario {
    Nodo raiz;
    private final StringBuilder resultado;

    public ArbolBinario() {
        raiz = null;
        resultado = new StringBuilder();
    }

    //Funcion para limpiar el cuadro de texto
    void limpiarResultado() {
        resultado.setLength(0);
    }

    //Funcion que imprime en pre order
    void imprimirPreorden(Nodo nodo) {
        if (nodo == null)
            return;
        resultado.append("<").append(nodo.clave).append("> "); // Encerrar el número en corchetes
        imprimirPreorden(nodo.izquierdo);
        imprimirPreorden(nodo.derecho);
    }

    //Funcion que imprime en in order
    void imprimirEnOrden(Nodo nodo) {
        if (nodo == null)
            return;
        imprimirEnOrden(nodo.izquierdo);
        resultado.append("<").append(nodo.clave).append("> "); // Encerrar el número en corchetes
        imprimirEnOrden(nodo.derecho);
    }

    //Funcion que imprime en post order
    void imprimirPostorden(Nodo nodo) {
        if (nodo == null)
            return;
        imprimirPostorden(nodo.izquierdo);
        imprimirPostorden(nodo.derecho);
        resultado.append("<").append(nodo.clave).append("> "); // Encerrar el número en corchetes
    }

    public String obtenerResultado() {
        return resultado.toString();
    }
}
