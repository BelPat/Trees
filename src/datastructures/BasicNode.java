package datastructures;

/**
 *
 * @author BelPat
 * @param <Node>
 * @param <T>
 */
public class BasicNode<Node extends BasicNode<Node, T>, T> {
    private T key;
    private Node right, left;

    /**
     * Constructor vacio
     */
    public BasicNode() {        
        this.key = null;
        this.right = null;   
        this.left = null;   
    }
    
    /**
     * Construye un BasicNode con clave key
     * @param key clave del nodo 
     */
    public BasicNode(T key) {        
        this.key = key;
        this.right = null;   
        this.left = null;   
    }
    

    /**
     * Construye un BasicNode con clave key un nodo derecho right y un nodo izquierdo left
     * @param key clave del nodo
     * @param right nodo derecho del BasicNode
     * @param left nodo izquierdo del BasicNode
     */
    public BasicNode(T key, Node right, Node left) {        
        this.key = key;
        this.right = right;
        this.left = left;         
    }    


    /**
     * Retorna la clave key de tipo T de BasicNode
     * @return clave key de tipo T de BasicNode
     */
    public T getKey() {        
        return this.key;        
    }

    /**
     * Retorna el nodo derecho right de tipo Node
     * @return nodo derecho right de tipo Node
     */
    public Node getRight() {        
        return this.right;        
    }

    /**
     * Retorna el nodo izquierdo left de tipo Node
     * @return nodo izquierdo left de tipo Node
     */
    public Node getLeft() {        
        return this.left;        
    }
    
     /**
     * Establece la clave key del Node
     * @param key clave del nodo
     */
    void setKey(T key) {        
        this.key = key;        
    } 
        
     /**
     * Establece Node de BasicNode con clave key y nodo derecho right
     * @param right nodo derecho de tipo Node
     */
    void setRight(Node right) {        
        this.right = right;        
    }
    
    /**
     *
     * @param key
     * @param right
     */
    public void setNode(T key, Node right) {     
        this.key = key;
        this.right = right;    
    } 
    
        
     /**
     * Establece Node de BasicNode con clave key y nodo derecho right
     * @param left nodo izquierdo de tipo Node
     */
    void setLeft(Node left) {        
        this.left = left;        
    }

    /**
     * Retorna true si el Node es nulo y falso en caso contrario
     * @return true indica nodo nulo
     */
    public boolean isNull(){
        return this == null;
    }
    
//  abstract public Node createNode();
    
    

}//Fin de la Clase