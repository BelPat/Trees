package classes;


public class BNode < T extends Comparable < T > > {
    
    private T key;
    private BNode < T >  left, right;
    
    /**
     * Contructor vacio de la clase
     */
    public BNode() {
    }
    
       
    /**
     * Constructor con parametros de la clase
     * @param x es de tipo T el cual posee la información del nodo del arbol
     * @param i es de tipo Nodo < T >  el cual posee el nodo del lado izquierdo
     * @param d es de tipo Nodo < T >  el cual posee el nodo del lado derecho
     */
    public BNode ( T x, BNode < T >  i, BNode < T >  d ) {        
        this.key = x;
        this.left = i;
        this.right = d;        
    }
        
    /**
     * Contructor de la clase
     */
    public BNode( BNode < T >  x) {
        this.key = x.getKey();
        this.left = x.getLeft();
        this.right = x.getRight();        
    }
    
    /**
     * Contructor con parametros de la clase que genera una hoja del arbol
     * @param x 
     */
    public BNode(T x) {        
        this.key = x;
        this.left = this.right = null;        
    }
    
    /**
     * Método que retona la información del nodo
     * @return un tipo T que contiene la información del nodo
     */
    public T getKey() {        
        return this.key;        
    }
    
    /**
     * Método el cual retona el nodo izquierdo
     * @return un tipo Nodo < T >  el cual contiene el nodo izquierdo
     */
    
    public BNode < T >  getLeft() {        
        return this.left;        
    }
    
    /**
     * Método el cual retona el nodo derecho
     * @return un tipo Nodo  < T >  el cual contiene el nodo derecho
     */
    public BNode < T >  getRight() {        
        return this.right;        
    }
    
    /**
     * Método que modifica el nodo izquierdo
     * @param i es de tipo BNode < T >  que contiene el nodo izquierdo
     */
    public void setLeft ( BNode < T >  i ) {        
        this.left = i;        
    }
    
    /**
     * Método que modifica el nodo derecho
     * @param d es de tipo NodoN < T >  que contiene el nodo derecho
     */
    
    public void setRight( BNode < T >  d ) {        
        this.right = d;        
    }
    
    /**
     * Método que modifica el contenido del nodo
     * @param d de tipo T y contiene la información del nodo
     */
    public void setKey( T d ) {        
        this.key = d;        
    }
}//Fin de la Clase
