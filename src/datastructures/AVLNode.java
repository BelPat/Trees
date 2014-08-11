
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

    public AVLNode( T k ) {
        super (k);
        this.balance = 0;
        this.height = 0;
        this.root = null;
    }
    
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
  @Override
    public AVLNode < T >  getLeft() {
     AVLNode< T > aux =null;
        if (super.getLeft() != null){
           return (AVLNode<T>) super.getLeft();
        }
        return aux;
    }
    @Override
      public AVLNode < T >  getRight() {
        AVLNode< T > aux = null;
            if (super.getRight() != null){
                return (AVLNode<T>) super.getRight();
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
 /*   @Override
public boolean equals(Object o){
    if(o==null) return false;
    if(o==this) return true;
    if(!(o instanceof BasicNode)) return false;
    AVLNode p=(AVLNode) o;
    if(!super.getKey().equals(p.getKey()) && !super.getLeft().equals(p.getLeft()) && !super.getRight().equals(p.getRight()) && !this.root.equals(p.root) && this.balance!=p.balance && this.height!=p.height){
        return false;
    }else
        return true;
    //if((super.getKey()==null)?(p.getKey()!=null): !super.getKey().equals(p.getKey())) return false;
    //return true;
}
@Override
public int hashCode(){
    final int prim=31;
    int result=17;
    result = prim * result;// + BasicNode.this.getKey().hashCode();
    return result;
    
}*/

}

    