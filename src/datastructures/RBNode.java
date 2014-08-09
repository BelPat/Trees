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

    private RBNode < T >  root;
    private int numLeft = 0;
    private int numRight = 0;
    private int color;
    
        RBNode(){
        this.color = BLACK;
        this.numLeft = 0;
        this.numRight = 0;
        this.root = null;
        super.setRight( null);
        super.setLeft( null);   
    }

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
        this.color = BLACK;
        this.numLeft = 0;
        this.numRight = 0;
        this.root = null;
   
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
    }

    @Override
    public RBNode < T > getRight() {
            return (RBNode<T>) (super.getRight());
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
       

 public boolean getNode(){
       if (this.getKey()==null){
            return true;
        }else return false;
    }
}// end class RedBlackNode


    
    