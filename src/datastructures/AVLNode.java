package datastructures;

/**
 *
 * @author BelPat
 * @param <T>
 */

 public class AVLNode <T> extends BasicNode<AVLNode<T>, T>  {

    private AVLNode <T>  root;

    /*  parámetro utilizado para controlar que la altura de 
    la rama izquierda no difiere en más de una unidad 
    de la altura de la rama derecha o viceversa*/
    private int balance;
    private int height;

    /**
     * Construye un AVLNode con la clave del nodo.
     * 
     * @param k la clave del nodo
     */
    public AVLNode( T k ) {
        super (k);
        this.balance = 0;
        this.height = 0;
        this.root = null;
    }
    
    /**
     * Construye un AVLNode vacío.
     */
    public AVLNode( ) {
        super ();
        this.balance = 0;
        this.height = 0;
        this.root = null;
    }

    /**
     * Construye un AVLNode con el nodo.
     * 
     * @param aux el nodo
     */
    public AVLNode( AVLNode<T> aux) {        
        super.setKey(aux.getKey());
        super.setRight( aux.getRight());
        super.setLeft( aux.getLeft());   
        this.balance = 0;
        this.height = 0;
        this.root = null;
    
    }

    /**
     * Retorna la raíz del nodo.
     * 
     * @return la raíz del nodo
     */
    public AVLNode < T >  getRoot() {        
        return root;
    }

    /**
     * Retorna el balanceo del nodo.
     * 
     * @return el balanceo del nodo
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Retorna la altura del nodo.
     * 
     * @return la altura del nodo
     */
    public int getHeight() {
        return height;
    }

    /**
     * Establece la raíz del nodo.
     * 
     * @param root la raíz del nodo
     */
    public void setRoot( AVLNode < T >  root ) {
        this.root = root;
    }

    /**
     * Establece el balanceo del nodo.
     * 
     * @param balance el balanceo del nodo
     */
    public void setBalance( int balance ) {
        this.balance = balance;
    }

    /**
     * Establece la altura del nodo.
     * 
     * @param height la altura del nodo
     */
    public void setHeight( int height ) {
        this.height = height;
    }
    
   
    @Override
    public int hashCode(){
        final int prim=31;
        int result=17;
        result = prim * result + AVLNode.this.getKey().hashCode();
        return result;    
    }

}