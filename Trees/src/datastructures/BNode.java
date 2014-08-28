package datastructures;

/**
 *
 * @author nusatres
 * @param <T>
 */
public class BNode <T> extends BasicNode<BNode<T>, T>{

    /**
     *
     */
    public BNode() {
        super();
    }
    
    /**
     *
     * @param key
     */
    public BNode(T key) {
        super(key);
    }

    /**
     *
     * @param x
     * @param i
     * @param d
     */
    public BNode ( T x, BNode <T>  i, BNode <T>  d ) { 
        super( x, d, i);        
    }
 
    /**
     *
     * @param x
     */
    public BNode( BNode <T>  x) {
        super( x.getKey(), x.getRight(), x.getLeft());   
             
    }
    
    
}//Fin de la Clase