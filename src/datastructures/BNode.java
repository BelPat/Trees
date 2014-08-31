package datastructures;

/**
 *
 * @author BelPat
 * @param <T>
 */
public class BNode <T> extends BasicNode<BNode<T>, T>{

    /**
     * Construye un BNode vacío.
     */
    public BNode() {
        super();
    }
    
    /**
     * Construye un BNode con la clave del nodo.
     * 
     * @param key la clave del nodo
     */
    public BNode(T key) {
        super(key);
    }

    /**
     * Construye un BNode con la clave del nodo, el nodo izquierdo 
     * y el nodo derecho.
     * 
     * @param x la clave del nodo
     * @param i el nodo izquierdo
     * @param d el nodo derecho
     */
    public BNode ( T x, BNode <T>  i, BNode <T>  d ) { 
        super( x, d, i);        
    }
 
    /**
     * Construye un BNode con el nodo.
     * 
     * @param x el nodo
     */
    public BNode( BNode <T>  x) {
        super( x.getKey(), x.getRight(), x.getLeft());   
             
    }
    
    
}//Fin de la Clase