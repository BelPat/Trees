package datastructures;

/**
 *
 * @author nusatres
 * @param <T>
 */

 public class AVLNode <T> extends BasicNode<AVLNode<T>, T>  {

    private AVLNode <T>  root;

    private int balance;
    private int height;

    /**
     *
     * @param k
     */
    public AVLNode( T k ) {
        super (k);
        this.balance = 0;
        this.height = 0;
        this.root = null;
    }
    
    /**
     *
     */
    public AVLNode( ) {
        super ();
        this.balance = 0;
        this.height = 0;
        this.root = null;
    }

   AVLNode( AVLNode<T> aux) {        
        super.setKey(aux.getKey());
        super.setRight( aux.getRight());
        super.setLeft( aux.getLeft());   
        this.balance = 0;
        this.height = 0;
        this.root = null;
    
    }

    /**
     *
     * @return
     */
    public AVLNode < T >  getRoot() {        
        return root;
    }

    /**
     *
     * @return
     */
    public int getBalance() {
        return balance;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param root
     */
    public void setRoot( AVLNode < T >  root ) {
        this.root = root;
    }

    /**
     *
     * @param balance
     */
    public void setBalance( int balance ) {
        this.balance = balance;
    }

    /**
     *
     * @param height
     */
    public void setHeight( int height ) {
        this.height = height;
    }
    
    @Override
public boolean equals(Object o){
    if(o==null) return false;
    if(o==this) return true;
    if(!(o instanceof BasicNode)) return false;
    AVLNode p=(AVLNode) o;
    return !((super.getKey()==null)?(p.getKey()!=null): !super.getKey().equals(p.getKey()));
}
    
    @Override
public int hashCode(){
    final int prim=31;
    int result=17;
    result = prim * result + AVLNode.this.getKey().hashCode();
    return result;
    
}

}