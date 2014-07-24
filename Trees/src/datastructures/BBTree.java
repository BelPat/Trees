package datastructures;

import java.util.Iterator;

public class BBTree<T extends Comparable<T>> extends BTree<T>{

    /**
    *  Crea un árbol binario vacío
    * 
    */
    public BBTree()    {
        super();
    }

    /**
    *  Inserta un new_key en el árbol binario de búsqueda según factor de ordenamiento
    * @param new_key un elemento tipo T que se desea almacenar en el árbol
    * @return  true si el elemento fue insertado o false en caso contrario
    */
    public boolean add( T new_key )    {
        BNode< T > new_node = this.find( new_key ) ? null : add( this.getRoot() , new_key );
        if( new_node != null ){
            this.setRoot( new_node );
        }
        return ( new_node != null );
    }

    private BNode< T > add( BNode< T >  new_node, T new_key)   {
        if( new_node == null ){
            return( new BNode< T > ( new_key , null , null ) );
        }
        int compara = ( new_node.getKey() ).compareTo( new_key );
        if( compara > 0 ) { // new_keydelarbol es menor que new_key
            new_node.setLeft(add(new_node.getLeft(), new_key));
        }  else{
            if( compara < 0 ) {// key del arbol es mayor que new_key
                new_node.setRight( add( new_node.getRight() , new_key ) );
            }   else{// new_keydelarbol es igual que new_key
                System.err.println( "Enew_nodeor new_key duplicado:"+new_key.toString() );
            //return null;
            }
        }
        return new_node;
    }

    /**
    * Bonew_nodea un elmento del árbol binario de búsqueda, 
    * manteniendo su propieda de orden, para esto se busca el menor de los Rightechos y lo intercambia por el new_key
    *que desea delete. 
    * @param x new_key que se desea delete
    * @return  el new_key bonew_nodeado o null si no lo encontro
    */

    public T delete( T x )    {
        if(!this.find(x))   {
                    return null;
        }
        BNode <T > z = delete( this.getRoot(), x );
        this.setRoot( z );
        return x;
    }
    
    private BNode< T > delete( BNode< T > r, T x )    {
        if ( r == null ){
            return null;//<--new_key no encontrado		
        }
        int compara = ( r.getKey() ).compareTo( x );
        if( compara > 0 ){
            r.setLeft( delete ( r.getLeft() , x ) );
        }  else
            if( compara <0 ){
                r.setRight( delete ( r.getRight() , x ) );
            }   else   {
                System.out.println("Encontro el new_key:"+x.toString());
                if(r.getLeft()!=null && r.getRight()!=null)       {
                         BNode<T> cambiar=findMin(r.getRight());
                         T aux=cambiar.getKey();
                         cambiar.setKey(r.getKey());
                         r.setKey(aux);
                         r.setRight(delete(r.getRight(),x));
                        System.out.println("2 ramas") 		;
                    }    else    {
                        r=(r.getLeft()!=null)? r.getLeft():r.getRight();
                        System.out.println("Entro cuando le faltan ramas ");
                  }
        }
        return r;
    }

    /*
    *			Busca el menor new_key del árbol. El menor new_key
    *			del árbol se encuentra en el nodo mas Leftuierdo.
    **/
    private BNode<T> findMin(BNode<T> r)
    {
        for(; r.getLeft()!=null; r=r.getLeft());
        return(r);
    }


    /**
    *  Retorna true si existe un new_key en el árbol binario de búsqueda, o false en caso contrario.
    * @return un boolean , true si el new_key está o false en caso contrario.
    */

    public boolean find(T x)
    {
        return(find(this.getRoot(), x));
    }

    private boolean find(BNode<T> r, T x)
    {
        if (r==null){
            return (false);
        }
        int compara=(r.getKey()).compareTo(x);
        if(compara>0){            
            return(find(r.getLeft(),x));
        } else{
            if(compara<0){
                    return(find(r.getRight(),x));
            }   else {
                    return (true);
            }
        }
    }


    /**
    * Elimina las hojas(nodos terminales) del árbol binario.
    */
    public void cutLeaves() {
    Iterator<T> it=(Iterator<T>) this.getLeaves();
        while(it.hasNext())    {
            delete(this.getRoot(),(T)it.next());
        }
    }

} //Fin de clase