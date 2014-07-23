/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author nusatres
 */
  public class RBNode < T extends Comparable < T > >  {

    /** Possible color for this node */
    public static final int BLACK = 0;
    /** Possible color for this node */
    public static final int RED = 1;
    // the key of each node

    private T key;

    private RBNode < T >  root;
    private RBNode < T >  left, right;
    // the number of elements to the left of each node
    private int numLeft = 0;
    // the number of elements to the right of each node
    private int numRight = 0;

    public void setNumLeft(int numLeft) {
        this.numLeft = numLeft;
    }

    public void setNumRight(int numRight) {
        this.numRight = numRight;
    }

    public int getNumLeft() {
        return numLeft;
    }

    public int getNumRight() {
        return numRight;
    }
    // the color of a node
    private int color;

    RBNode(){
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        root = null;
        left = null;
        right = null;
    }

    // Constructor which sets key to the argument.
    RBNode( T key ){
        this();
        this.key = key;
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

    public RBNode < T > getLeft() {
        return this.left;
    }

    /**
    * Método el cual retona el nodo rightecho
    * @return un tipo Nodo  < T >  el cual contiene el nodo rightecho
    */
    public RBNode < T > getRight() {
        return this.right;
    }

    /**
    * Método que modifica el nodo leftuierdo
    * @param i es de tipo RBNode < T >  que contiene el nodo leftuierdo
    */
    public void setLeft( RBNode < T > i ) {
        this.left = i;
    }

    /**
    * Método que modifica el nodo rightecho
    * @param d es de tipo NodoN < T >  que contiene el nodo rightecho
    */

    public void setRight( RBNode < T > d ) {
        this.right = d;
    }

    /**
    * Método que modifica el contenido del nodo
    * @param d de tipo T y contiene la keyrmación del nodo
    */
    public void setKey( T d ) {
        this.key = d;
    }

    public int getColor() {
        return color;
    }

    /**
    * Método que modifica el contenido del nodo
    * @param d de tipo T y contiene la keyrmación del nodo
    */
    public void setColor( int d ) {
        this.color = d;
    }
    
    public RBNode < T >  getRoot() {
        return root;
    }
    
       public void setRoot( RBNode < T >  root ) {
        this.root = root;
    }
}// end class RedBlackNode


    
    
