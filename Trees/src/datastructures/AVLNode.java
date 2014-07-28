/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author nusatres
 */

 public class AVLNode < T extends Comparable < T >  > extends Node  {

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
    
    
    public AVLNode ( AVLNode < T >  x ) {
       super ( x.getKey(), x.getRight() ,x.getLeft());
               this.root = null;
    }

   AVLNode( Node<T> aux) {        
        super.setKey(aux.getKey());
        super.setRight( aux.getRight());
        super.setLeft( aux.getLeft());   
         this.root = null;       
     
    }
    @Override
    public AVLNode < T >  getLeft() {
        AVLNode< T > aux = new AVLNode<> (super.getLeft());
        aux.balance = this.getLeft().getBalance();
        aux.height = this.getLeft().getHeight();
        aux.root = this.getLeft().getRoot();
        
        return aux;
    }
    @Override
      public AVLNode < T >  getRight() {
        AVLNode< T > aux = new AVLNode<> (super.getRight());
        aux.balance = this.getRight().getBalance();
        aux.height = this.getRight().getHeight();
        aux.root = this.getRight().getRoot();
        
        return aux;
    }
      public AVLNode < T >  getRoot() {
        
        return root;
    }
/*
    public AVLNode < T >  getRight() {
        return right;
    }

    public AVLNode < T >  getRoot() {
        return root;
    }

    public T getKey() {
        return key;
    }
*/
    public int getBalance() {
        return balance;
    }

    public int getHeight() {
        return height;
    }

 /*   public void setLeft( AVLNode < T >  left ) {
        this.left = left;
    }

    public void setRight( AVLNode < T >  right ) {
        this.right = right;
    }
*/
    public void setRoot( AVLNode < T >  root ) {
        this.root = root;
    }
/*
    public void setKey( T key ) {
        this.key = key;
    }
*/
    public void setBalance( int balance ) {
        this.balance = balance;
    }

    public void setHeight( int height ) {
        this.height = height;
    }

}

    