/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import static datastructures.RBNode.BLACK;

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
  //  private RBNode < T >  left, right;
    // the number of elements to the left of each node
    private int numLeft = 0;
    // the number of elements to the right of each node
    private int numRight = 0;
        // the color of a node
    private int color;
    
        RBNode(){
        this.color = BLACK;
        this.numLeft = 0;
        this.numRight = 0;
        this.root = null;
        super.setRight( null);
        super.setLeft( null);   
    }

    // Constructor which sets key to the argument.
    RBNode( T key ){
        super(key); 
        this.color = BLACK;
        this.numLeft = 0;
        this.numRight = 0;
        this.root = null;       
       
    }
    
    RBNode( Node<T> aux) {        
        super.setKey(aux.getKey());
        super.setRight( aux.getRight());
        super.setLeft( aux.getLeft());   
   
    }


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


    @Override
    public RBNode < T > getLeft() {
            return (RBNode<T>) (super.getLeft());
           /* aux.numLeft = this.getNumLeft();
            aux.numRight = this.getNumRight();
            aux.root = this.getRoot();
            aux.color  = this.getColor();     */  
    }

    @Override
    public RBNode < T > getRight() {
            return (RBNode<T>) (super.getRight());
           /* aux.numLeft = this.getNumLeft();
            aux.numRight = this.getNumRight();
            aux.root = this.getRoot();
            aux.color  = this.getColor(); */
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


    
    