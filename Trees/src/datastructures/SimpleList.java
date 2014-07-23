
package datastructures;
import java.util.Iterator;

public class SimpleList < T extends Comparable < T > >  implements Iterable < T > {
    private Node < T >  head;
    private int size;

    /**
    * Constructor de la Clase Lista Simple Enlazada, por defecto Inicializa
    * la headeza de la lista en null.
    */
    public SimpleList() {        
        this.head = null;
        this.size = 0;    
    }

    /**
    * Adiciona un Elemento al Inicio de la Lista
    * @param x Keyrmaci�n que desea almacenar en la Lista. La informacipon
    * debe ser un Objeto.
    */
    public void addInit( T x ){        
        this.head = new Node <  > ( x, this.head );
        this.size++;         
    }

    /**
    * Inserta un Elemento al Final de la Lista
    * @param x Keyrmación que desea almacenar en la Lista. La informacipon
    * debe ser un Objeto.
    */
    public void addEnd( T x ) {

        if( this.head == null ){
            this.addInit( x );
        }else {
            try {
                Node < T >  ult = this.getPos( this.size-1 );
                ult.setRight( new Node <  > ( x, null ) );
                this.size++;
            }catch( ExceptionUFPS e ) {                
                System.err.println( e.getMessage() );                
            }   
        }        
    }

    /**
    * Ingresa un elemento ordenado en la lista
    * @param info elemento a ingresar
    */
    public void addOrdenado(T info) {        
        if ( this.isEmpty() ){
            this.addInit( info );        
        } else {            
            Node < T >  x = this.head;
            Node < T >  y = x;

            while(x != null) {                
                //Comparable<T> comparador = ( Comparable )info;
                int rta = info.compareTo( x.getKey() );

                if( rta < 0 )
                break;

                y = x;
                x = x.getRight();
            }

            if(x == y){
                this.addInit(info);
            } else {                
                y.setRight( new Node <  > ( info, x ) );
                this.size++;                
             }            
        }    
    }

    /**
    * Modifica el elemento que se encuentre en una posici�n dada
    * @param i Una Posición dentro de la Lista
    * @param dato es el nuevo valor que toma el elmento en la lista
    */        
    public void set( int i, T dato ){        
    try{            
        Node < T >  t = this.getPos( i );
          t.setKey(dato);            
        }catch( ExceptionUFPS e ) {            
          System.err.println( e.getMessage() );            
        }        
    }    

    /**
    * Retorna el elemento que se encuentre en una posici�n dada
    * @param i Una Posición dentro de la Lista
    * @return El objeto que se encuentra en esa posición. El objeto
    * retorna su valor parametrizada "T". Si la posición no se
    * encuentra en la Lista retorna null.
    */
    public T get( int i ) {        
        try {
            Node < T >  t = this.getPos( i );
            return ( t.getKey() );

        }catch( ExceptionUFPS e ) {            
          System.err.println( e.getMessage() );            
        }        
        return( null );        
    }

    /**
    * Elemento privado de la clase que devuelve al elemento en la posicion
    * @param i Una posici�n en la Lista
    * @return El elemento encontrado. Si la posici�n no es v�lida
    * retorna null
    */
    private Node < T >  getPos( int i ) throws ExceptionUFPS {

        if( this.isEmpty() || i > this.size  || i < 0 )
        throw new ExceptionUFPS("El indice solicitado no existe en la Lista Simple");

        Node < T >  t = this.head;

        for ( int cont=0; cont< i; cont++)
        t= t.getRight();

        return( t );        
    }

    /**
    * Borra un elemento dada una posición
    * @param i Una posición en la Lista
    * @return El elemento que borro. Si la posición no es válida
    * retorna null
    */
    public T remove( int i ) {

        if( this.isEmpty() )
        return null;

        Node < T >  t = this.head;

        if(i == 0){
           this.head = this.head.getRight();
        } else {

            try {
                Node < T >  y = this.getPos( i-1 );
                t = y.getRight();
                y.setRight( t.getRight() );

            }catch(ExceptionUFPS e) {                
             System.err.println( e.getMessage() );                
            }            
        }

        t.setRight( null );

        this.size--;

        return( t.getKey() );        
    }

    /**
    * Borra la Lista Simple
    */
    public void removeAll(){        
    this.head = null; 
    this.size = 0;           
    }

    /**
    * Verifica si la Lista esta o no vac�a
    * @return true si la lista esta vac�o , false si no
    */
    public boolean isEmpty() {        
    return( this.head == null );        
    }

    /**
    * Invierte la Lista en ella misma
    */
    public void Invertir() {        
        Node < T >  a,p;
        a = p = null;

        while( this.head != null ) {
        a = this.head;
        this.head = this.head.getRight();
        a.setRight( p );
        p = a;            
        }
        this.head = a;        
    }

    /**
    * Obtiene la cantidad de elementos de la Lista
    * @return int con el tamaño de la lista. Si la Lista
    * esta vacía retorna 0
    */
    public int getSize() {
    return ( this.size );        
    }

    /**
    * Devuelve un String con toda la informaci�n de los elementos
    * de la Lista. Recomendaci�n: La clase que se almacena en la Lista
    * debe tener sobreescrito el m�todo toString()
    * @return Un String con los datos de los elementos de la Lista
    */
    @Override
    public String toString() {        
        if (this.isEmpty())
        return ("Lista Vacia");        
        String r = "";

        for( Node < T >  x = this.head;x != null;x = x.getRight() )
        r += x.getKey().toString()+"->";

        return( r );        
    }

    /**
    * Obtiene la posición de un objeto en la Lista. Se recomienda
    * que la clase tenga sobre escrito el método equals.
    * @param info Objeto que se desea buscar en la Lista
    * @return un int con la posición del elemento,-1 si el elemento no se 
    * encuentra en la Lista
    */
    public int indexOf( T info ) {        
        int i = 0;

        for( Node < T >  x = this.head ; x != null ; x = x.getRight() ) {

        if(x.getKey().equals(info))
        return (i);            
        i++;            
        }        
        return ( -1 );        
    }

    /**
    * Retorna la lista simple en un elemento Iterator 
    * @return Un iterator tipo  < T >  de la lista
    */
    @Override
    public Iterator < T >  iterator() {        
    return new IteratorLS <  > (this.head);        
    }

    /**
    * Retorna la lista simple en un array 
    * @return un Array Object  de la lista
    */
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

    /**
    * busca un elemento en la lista si lo encuentra retorna true, de lo contrario false
    * @param info el cual contiene el valor del parametro a buscar en la lista
    * @return un boolean, si es true encontro el dato en la lista y si es 
    * false no.
    */
    public boolean containTo( T info ) {        
    return ( this.indexOf ( info ) != -1 );        
    }

    /** NO se utiliza
    * Concatena dos listas simples. La lista2 , se borra. 
    * @param lista1 es la lista en la que se va a concatenar la sengunda lista
    * @param lista2 es la lista que sera concatenada en la primera 
    * @return un boolean, si es true concateno las listas y si es 
    * false no.
    */
    /*   public boolean concat( SimpleList lista2 ) {

    if( !this.isEmpty() ) {            
    for( Iterator < T >  l =  lista2.iterator(); l.hasNext(); )
    this.addFin( l.next() );            
    }else return false;

    return true;

    }*/

    public class IteratorLS < T extends Comparable < T > >  implements Iterator < T > 
    {

        private Node < T >  posicion;

        /**
        * Constructor con parametros de la clase iterador de la clase lista simple
        * @param posicion es de tipo Node < T >  el cual contiene un nodo de la lista a iterar
        */
        IteratorLS ( Node < T >  posicion ) {            
            this.posicion = posicion;	            
        }

        /**
        * Método que remueve un Node del iterador
        * No implementado
        */	
        @Override
        public void remove()
        {}

        /**
        * Método que informa si existe otro elemento en la lista para seguir iterando
        * @return un tipo boolean que informa si existe o no un dato en la lista, desde la posición 
        * actual del cursor.
        */
        @Override
        public boolean hasNext(){            
            return ( posicion != null );

        }

        /**
        * Método que retorna un dato de la posición actual del cursor del iterador.
        * El cursor queda en la siguiente posición.
        * @return un tipo T que contiene el dato actual
        */
        @Override
        public T next() {            
            if( !this.hasNext() ) {                
            System.err.println("Error no hay mas elementos");
            return null;

            }

            Node < T >  actual = posicion;
            posicion = posicion.getRight();

            return( actual.getKey() );
        }

    }//Fin de la Clase

}//Fin de la Clase

