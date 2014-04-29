package classes;


public class BNode < T > {
    
    private T key;
    private BNode < T >  left, right;
    
    /**
     * Contructor vacio de la clase
     */
    public BNode() {
    }
    
       
    /**
     * Constructor con parametros de la clase
     * @param x es de tipo T el cual posee la keyrmación del nodo del arbol
     * @param i es de tipo Nodo < T >  el cual posee el nodo del lado leftuierdo
     * @param d es de tipo Nodo < T >  el cual posee el nodo del lado rightecho
     */
    public BNode ( T x, BNode < T >  i, BNode < T >  d ) {        
        this.key = x;
        this.left = i;
        this.right = d;        
    }
        
    /**
     * Contructor vacio de la clase
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
     * Método que retona la keyrmación del nodo
     * @return un tipo T que contiene la keyrmación del nodo
     */
    public T getKey() {        
        return this.key;        
    }
    
    /**
     * Método el cual retona el nodo leftuierdo
     * @return un tipo Nodo < T >  el cual contiene el nodo leftuierdo
     */
    
    public BNode < T >  getLeft() {        
        return this.left;        
    }
    
    /**
     * Método el cual retona el nodo rightecho
     * @return un tipo Nodo  < T >  el cual contiene el nodo rightecho
     */
    public BNode < T >  getRight() {        
        return this.right;        
    }
    
    /**
     * Método que modifica el nodo leftuierdo
     * @param i es de tipo BNode < T >  que contiene el nodo leftuierdo
     */
    public void setLeft ( BNode < T >  i ) {        
        this.left = i;        
    }
    
    /**
     * Método que modifica el nodo rightecho
     * @param d es de tipo NodoN < T >  que contiene el nodo rightecho
     */
    
    public void setRight( BNode < T >  d ) {        
        this.right = d;        
    }
    
    /**
     * Método que modifica el contenido del nodo
     * @param d de tipo T y contiene la keyrmación del nodo
     */
    public void setKey( T d ) {        
        this.key = d;        
    }
}//Fin de la Clase
