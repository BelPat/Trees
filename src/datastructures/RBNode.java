/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import static datastructures.RBNode.BLACK;

/**
 *
 * @author BelPat
 * @param <T>
 */
  public class RBNode<T> extends BasicNode<RBNode<T>, T>{

    /** Possible color for this node */
    public static final int BLACK = 0;
    /** Possible color for this node */
    public static final int RED = 1;

    private RBNode < T >  root;
    private int numLeft = 0;
    private int numRight = 0;
    private int color;
    
    /**
     * Construye un RBNode vacío.
     */
    public RBNode(){
        this.color = BLACK;
        this.numLeft = 0;
        this.numRight = 0;
        this.root = null;
        super.setRight( null);
        super.setLeft( null);   
    }

    /**
     * Construye un RBNode con la clave del nodo.
     * 
     * @param key la clave del nodo
     */
    public RBNode( T key ){
        super(key); 
        this.color = BLACK;
        this.numLeft = 0;
        this.numRight = 0;
        this.root = null;       
       
    }
    
    /**
     * Construye un RBNode con el nodo.
     * 
     * @param aux el nodo
     */
    public RBNode( RBNode<T> aux) {        
        super.setKey(aux.getKey());
        super.setRight( aux.getRight());
        super.setLeft( aux.getLeft());  
        this.color = BLACK;
        this.numLeft = 0;
        this.numRight = 0;
        this.root = null;
   
    }

    /**
     * Establece el número de elementos a la izquierda de cada nodo.
     * 
     * @param numLeft el número de elementos a la izquierda de cada nodo
     */
    public void setNumLeft(int numLeft) {
        this.numLeft = numLeft;
    }

    /**
     * Establece el número de elementos a la derecha de cada nodo.
     * 
     * @param numRight el número de elementos a la derecha de cada nodo
     */
    public void setNumRight(int numRight) {
        this.numRight = numRight;
    }

    /**
     * Retorna el número de elementos a la izquierda de cada nodo.
     * 
     * @return el número de elementos a la izquierda de cada nodo
     */
    public int getNumLeft() {
        return numLeft;
    }

    /**
     * Retorna el número de elementos a la derecha de cada nodo.
     * 
     * @return el número de elementos a la derecha de cada nodo
     */
    public int getNumRight() {
        return numRight;
    }

    /**
     * Retorna el color del nodo.
     * 
     * @return el color del nodo
     */
    public int getColor() {
        return color;
    }

    /**
     * Establece el color del nodo.
     * 
     * @param d el color del nodo
     */
    public void setColor( int d ) {
        this.color = d;
    }
    
    /**
     * Retorna la raíz del nodo.
     * 
     * @return la raíz del nodo
     */
    public RBNode < T >  getRoot() {
        return root;
    }
    
    /**
     * Establece la raíz del nodo.
     * 
     * @param root la raíz del nodo
     */
    public void setRoot( RBNode < T >  root ) {
        this.root = root;
    }
       
    @Override
    public boolean equals(Object o){
    if(o==null) return false;
    if(o==this) return true;
    if(!(o instanceof RBNode)) return false;
    RBNode p=(RBNode) o;
    return !((super.getKey()==null)?(p.getKey()!=null): !super.getKey().equals(p.getKey()));
}
@Override
public int hashCode(){
    final int prim=31;
    int result=17;
    
    if( RBNode.this.getKey()!=null ){
        int code = RBNode.this.getKey().hashCode();
        result = prim * result + code;
    }else{
        result = prim * result ;//+ RBNode.this.getKey().hashCode();  
    }
    return result;
    
}
 
}// end class RedBlackNode

