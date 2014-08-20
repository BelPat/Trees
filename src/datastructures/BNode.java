package datastructures;


public class BNode <T> extends BasicNode<BNode<T>, T>{

    public BNode() {
        super();
    }
    
    public BNode(T key) {
        super(key);
    }

    public BNode ( T x, BNode <T>  i, BNode <T>  d ) { 
        super( x, d, i);        
    }
 
    public BNode( BNode <T>  x) {
        super( x.getKey(), x.getRight(), x.getLeft());   
             
    }
    
}//Fin de la Clase