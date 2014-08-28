/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import static datastructures.RBNode.BLACK;

/**
 *
 * @author nusatres
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
     *
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
     *
     * @param key
     */
    public RBNode( T key ){
        super(key); 
        this.color = BLACK;
        this.numLeft = 0;
        this.numRight = 0;
        this.root = null;       
       
    }
    
    /**
     *
     * @param aux
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
     *
     * @param numLeft
     */
    public void setNumLeft(int numLeft) {
        this.numLeft = numLeft;
    }

    /**
     *
     * @param numRight
     */
    public void setNumRight(int numRight) {
        this.numRight = numRight;
    }

    /**
     *
     * @return
     */
    public int getNumLeft() {
        return numLeft;
    }

    /**
     *
     * @return
     */
    public int getNumRight() {
        return numRight;
    }

    /**
     *
     * @return
     */
    public int getColor() {
        return color;
    }

    /**
     *
     * @param d
     */
    public void setColor( int d ) {
        this.color = d;
    }
    
    /**
     *
     * @return
     */
    public RBNode < T >  getRoot() {
        return root;
    }
    
    /**
     *
     * @param root
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
    @Override
 public boolean isNull(){
       if (this.getKey()==null){
            return true;
        }else return false;
    }
 
}// end class RedBlackNode

