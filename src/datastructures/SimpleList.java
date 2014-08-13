
package datastructures;
import java.util.Iterator;

public class SimpleList <Node extends BasicNode<Node, T>, T extends Comparable<T>>  implements Iterable < T > {
    private BasicNode<Node,T> head;
    private int size;

    public SimpleList() {        
        this.head = null;
        this.size = 0;    
    }

    public void addInit( T x ){        
        this.head = new BasicNode<Node,T> ( x, (Node) this.head);
        this.size++;         
    }
 
   public void addEnd(T x) {
               if( this.head == null ){
            this.addInit(x);
        }else {
            try {
                BasicNode ult = this.getPos( this.size-1 );
                ult.setRight( new BasicNode<Node,T> ( x, null ) );
                ult.setRight( new BasicNode<Node,T> ( x, null ));
                this.size++;
            }catch( ExceptionUFPS e ) {                
                System.err.println( e.getMessage() );                
            }   
        }   
    }

    public void addOrdenado(T info) {        
        if ( this.isEmpty() ){
            this.addInit( info );        
        } else {            
            BasicNode<Node,T> x = this.head;
            BasicNode<Node,T> y = x;

            while(x != null) {                
                //Comparable<T> comparador = ( Comparable )info;
                int rta = info.compareTo( (T) x.getKey() );

                if( rta >= 0 ) {
                } else {
                    break;
                }

                y = x;
                x = (BasicNode) x.getRight();
            }

            if(x == y){
                this.addInit(info);
            } else {                
                y.setRight( (Node) new BasicNode ( info, x ));
                this.size++;                
             }            
        }    
    }
      
    public void set( int i, T dato ){        
    try{            
        BasicNode<Node,T> t = this.getPos( i );
          t.setKey(dato);            
        }catch( ExceptionUFPS e ) {            
          System.err.println( e.getMessage() );            
        }        
    }    

    public T get( int i ) {        
        try {
            BasicNode<Node,T> t = this.getPos( i );
            return ( t.getKey() );

        }catch( ExceptionUFPS e ) {            
          System.err.println( e.getMessage() );            
        }        
        return( null );        
    }

    private BasicNode<Node,T> getPos( int i ) throws ExceptionUFPS {

        if( this.isEmpty() || i > this.size  || i < 0 )
        throw new ExceptionUFPS("El indice solicitado no existe en la Lista Simple");

        BasicNode<Node,T> t = this.head;

        for ( int cont=0; cont< i; cont++)
        t= t.getRight();

        return t;        
    }


    public T remove( int i ) {

        if( this.isEmpty() )
        return null;

        BasicNode<Node,T>  t = this.head;

        if(i == 0){
           this.head = this.head.getRight();
        } else {

            try {
                BasicNode<Node,T> y = this.getPos( i-1 );
                t = y.getRight();
                y.setRight( t.getRight() );

            }catch(ExceptionUFPS e) {                
             System.err.println( e.getMessage() );                
            }            
        }

        t.setRight( null );

        this.size--;

        return(T) ( t.getKey() );        
    }

    public void removeAll(){        
        this.head = null; 
        this.size = 0;           
    }

    public boolean isEmpty() {        
        return( this.head == null );        
    }

    public int getSize() {
        return ( this.size );        
    }

    @Override
    public String toString() {        
        if (this.isEmpty())
        return ("Lista Vacia");        
        String r = "";

        for( BasicNode  x = this.head;x != null;x = (BasicNode) x.getRight() )
        r += x.getKey().toString()+"->";

        return( r );        
    }

    public int indexOf( T info ) {        
        int i = 0;

        for( BasicNode  x = this.head ; x != null ; x = (BasicNode) x.getRight() ) {

        if(x.getKey().equals(info))
        return (i);            
        i++;            
        }        
        return ( -1 );        
    }

    @Override
    public Iterator < T >  iterator() {        
    return new IteratorLS <  > (this.head);        
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {

    if( this.isEmpty() )
    return ( null );

    Object vector[] = new Object[ this.size ];
    int i = 0;
    Iterator < T >  it = this.iterator();
    while(it.hasNext())
    vector[i++] = it.next();

    return( ( T[] )  vector );

    }

    public boolean containTo( T info ) {        
    return ( this.indexOf ( info ) != -1 );        
    }


    public class IteratorLS < T extends Comparable < T > >  implements Iterator < T > 
    {

        private BasicNode<Node,T>  posicion;

        IteratorLS ( BasicNode<Node,T>  posicion ) {            
            this.posicion = posicion;	            
        }

        @Override
        public void remove()
        {}

        @Override
        public boolean hasNext(){            
            return ( posicion != null );

        }

        @Override
        public T next() {            
            if( !this.hasNext() ) {                
            System.err.println("Error no hay mas elementos");
            return null;

            }

            BasicNode<Node,T> actual = posicion;
            posicion = (BasicNode<Node, T>) posicion.getRight();

            return actual.getKey();
        }

    }//Fin de la Clase

}//Fin de la Clase
