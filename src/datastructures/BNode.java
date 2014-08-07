package datastructures;


public class BNode < T extends Comparable < T > > extends Node<T>{

    public BNode() {
        super();
    }

    public BNode ( T x, Node < T >  i, Node < T >  d ) { 
        super( x, d, i);        
    }
 
    public BNode( Node < T >  x) {
        super( x.getKey(), x.getRight(), x.getLeft());   
             
    }
 
    public BNode(T x) {   
        super (x);
   
    }
    
}//Fin de la Clase
