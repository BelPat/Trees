/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author nusatres
 */
  public class RBNode < T extends Comparable < T > >  extends Node<T>{

    /** Possible color for this node */
    public static final int BLACK = 0;
    /** Possible color for this node */
    public static final int RED = 1;
    // the key of each node

  //  private T key;

    private RBNode < T >  root;
    //private RBNode < T >  left, right;
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
        super.setRight( null);
        super.setLeft( null);   
    }

    // Constructor which sets key to the argument.
    RBNode( T key ){
        super(key); 
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        root = null;
        
       
    }
    
    RBNode( Node<T> aux) {        
        super.setKey(aux.getKey());
        super.setRight( aux.getRight());
        super.setLeft( aux.getLeft());   
        this.root = null;       
     
    }


    @Override
    public RBNode < T > getLeft() {
        RBNode< T > aux = null;
        if(super.getLeft().getKey() != null){
            aux = new RBNode<> (super.getLeft());
            aux.numLeft = this.getNumLeft();
            aux.numRight = this.getNumRight();
            aux.root = this.getRoot();
            aux.color  = this.getColor();       
        }
        return aux;
    }

    @Override
    public RBNode < T > getRight() {
        RBNode< T > aux = null;
        System.out.println(super.getRight().getKey());
        if(super.getRight().getKey() != null){
            aux = new RBNode<> (super.getRight());
            aux.numLeft = this.getNumLeft();
            aux.numRight = this.getNumRight();
            aux.root = this.getRoot();
            aux.color  = this.getColor(); 
        }  
        return aux;
    } 

    public int getColor() {
        return color;
    }

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


    
    