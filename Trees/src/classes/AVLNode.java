/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author nusatres
 */

 public class AVLNode < T >  {

    private AVLNode < T >  left;
    private AVLNode < T >  right;
    private AVLNode < T >  root;

    private T key;
    private int balance;
    private int height;

    public AVLNode( T k ) {
        left = right = root = null;
        balance = 0;
        height = 0;
        key = k;
    }

    /**
    * Contructor vacio de la clase
    */
    public AVLNode ( AVLNode < T >  x) {
        this.key=x.getKey();
        this.left=x.getLeft();
        this.right=x.getRight();
    }

    public AVLNode < T >  getLeft() {
        return left;
    }

    public AVLNode < T >  getRight() {
        return right;
    }

    public AVLNode < T >  getRoot() {
        return root;
    }

    public T getKey() {
        return key;
    }

    public int getBalance() {
        return balance;
    }

    public int getHeight() {
        return height;
    }

    public void setLeft( AVLNode < T >  left ) {
        this.left = left;
    }

    public void setRight( AVLNode < T >  right ) {
        this.right = right;
    }

    public void setRoot( AVLNode < T >  root ) {
        this.root = root;
    }

    public void setKey( T key ) {
        this.key = key;
    }

    public void setBalance( int balance ) {
        this.balance = balance;
    }

    public void setHeight( int height ) {
        this.height = height;
    }

    public String toString() {
        return "" + key;
    }

    public int toInt() {
        return ( int )( Comparable ) key;
    }
}

    