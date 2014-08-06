/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author nusatres
 */

 public class AVLNode < T extends Comparable < T >  > extends Node<T>  {

   /* private AVLNode < T >  left;
    private AVLNode < T >  right;*/
    private AVLNode < T >  root;

  //  private T key;
    private int balance;
    private int height;

    public AVLNode( T k ) {
        super (k);
        //left = right = root = null;
        this.balance = 0;
        this.height = 0;
        this.root = null;
       // key = k;
    }
    
 public AVLNode( ) {
        super ();
        //left = right = root = null;
        this.balance = 0;
        this.height = 0;
        this.root = null;
    }
    
    
   /* public AVLNode ( AVLNode < T >  x ) {
       super ( x.getKey(), x.getRight() ,x.getLeft());

    }*/

   AVLNode( Node<T> aux) {        
        super.setKey(aux.getKey());
        super.setRight( aux.getRight());
        super.setLeft( aux.getLeft());   
    
    }
  @Override
    public AVLNode < T >  getLeft() {
     AVLNode< T > aux =null;
        if (super.getLeft() != null){
           return (AVLNode<T>) super.getLeft();
          /*  aux.balance = this.getBalance();
            aux.height = this.getHeight();
            aux.root = this.getRoot();*/
        }
        return aux;
    }
    @Override
      public AVLNode < T >  getRight() {
        AVLNode< T > aux = null;
            if (super.getRight() != null){
                return (AVLNode<T>) super.getRight();
         /*   aux = new AVLNode<> (super.getRight());
            aux.balance = this.getBalance();
            aux.height = this.getHeight();
            aux.root = this.getRoot();*/
         }
                      
        return aux;
    }
      public AVLNode < T >  getRoot() {
        
        return root;
    }

    public int getBalance() {
        return balance;
    }

    public int getHeight() {
        return height;
    }

    public void setRoot( AVLNode < T >  root ) {
        this.root = root;
    }

    public void setBalance( int balance ) {
        this.balance = balance;
    }

    public void setHeight( int height ) {
        this.height = height;
    }

}

    