package datastructures;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author BelPat
 * @param <T>
 */
public class BTree<T extends Comparable<T>> extends BasicTree<BNode<T>, T>{

    /**
     * Construye un BTree vacío.
     */
    public BTree()    {
        super();
    }

    /**
     * Inserta un nuevo nodo en el árbol binario de búsqueda 
     * 
     * @param key
     * @return  true si el elemento fue insertado o false en caso contrario
     */
    @Override
    public boolean add( T key )    {
        BNode< T > new_node;
        new_node = this.find(key) ? null : add( super.getRoot(), key );
        if( new_node != null ){
            this.setRoot( new_node );
        }
        return ( new_node != null );
    }
    
     /**
     * Busca el nodo padre al que insertarle el nodo 
     * y llama al método para que lo añada 
     * 
     * @param new_node nodo actual que se esta consultando 
     * @param new_key nodo a insertar
     * @return el nodo añadido
     */
    private BNode< T > add( BNode< T >  new_node, T new_key)   {
        if( new_node == null ){
            return( new BNode< > ( new_key , null , null ) );
        }
        int compara = ( new_node.getKey() ).compareTo( new_key );
        if( compara > 0 ) { 
            new_node.setLeft(add(new_node.getLeft(), new_key));
        }  else{
            if( compara < 0 ) {
                new_node.setRight( add( new_node.getRight() , new_key ) );
            }   else{
            }
        }
        return new_node;
    }

    /**
     * Borra un elmento del árbol 
     * 
     * @param x new_key que se desea delete
     * @return el new_key borrado o null si no lo encontro
     */
    @Override
        public T delete( T x )    {
        if(!this.find(x))   {
                    return null;
        }
        BNode <T > z = delete( this.getRoot(),x );
        this.setRoot( z );
        return x;
    }
        
     /* Busca el nodo a borrar del árbol
     * 
     * @param p nodo actual que se esta consultando 
     * @param q es la clave del nodo a borrar
     * @return la clave del nodo borrado
     */    
    private BNode< T > delete( BNode< T > r, T x )    {
        if ( r == null ){
            return null;		
        }
        int compara = ( r.getKey() ).compareTo( x );
        if( compara > 0 ){
            r.setLeft( delete ( r.getLeft() , x ) );
        }  else
            if( compara < 0 ){
                r.setRight( delete ( r.getRight() , x ) );
            }   else   {
                System.out.println("Encontro el new_key:"+x.toString());
                if(r.getLeft()!=null && r.getRight()!=null)       {
                         BNode<T> cambiar=findMin(r.getRight());
                         T aux=cambiar.getKey();
                         cambiar.setKey(r.getKey());
                         r.setKey(aux);
                         r.setRight(delete(r.getRight(),x));
;
                    }    else    {
                        r=(r.getLeft()!=null)? r.getLeft():r.getRight();
                  }
        }
        return r;
    }

    /**
     * Busca el menor new_key del árbol. El menor new_key
     * del árbol se encuentra en el nodo mas Leftuierdo.
     * 
     * @param r
     * @return
     */
    private BNode<T> findMin(BNode<T> r)
    {
        for(; r.getLeft()!=null; r=r.getLeft());
        return(r);
    }

    /**
     * Retorna true si existe un new_key en el árbol binario de búsqueda, o false en caso contrario.
     * 
     * @param x
     * @return un boolean, true si el new_key está o false en caso contrario.
    */
    @Override
        public boolean find(T x)    {
        return(find(this.getRoot(),x));
    }
    
    /**
     * Busca el nodo en el árbol de forma recursiva
     * 
     * @param key clave del nodo
     * @return true si la clave está en el árbol, false en caso contrario
     */
    private boolean find(BNode<T> r, T x)
    {
        if (r==null){
            return (false);
        }

        int compara=(r.getKey().compareTo(x));
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
     * Retorna un interador de todas las hojas del árbol.
     * 
     * @return el interador de todas las hojas del árbol
     */
    @Override
    public Iterator<T> getLeaves() {
        BNode<T> new_node = new BNode<>();
     //   SimpleList<BNode<T>, T> l = new SimpleList<>(new_node);
        ArrayList<T> keylist = new ArrayList<>();
        getLeaves(super.getRoot(), keylist);
        return (keylist.iterator());
    }
     
    /**
     * Busca todas la hojas del árbol de forma recursiva.
     * 
     * @param r nodo dado
     * @param keylist lista de hojas
     */    
    @Override
    public void getLeaves(BNode<T> r, ArrayList<T> keylist) {
        if (r != null) {
            if (this.isLeaf(r)) {
                keylist.add(r.getKey());
            }
            getLeaves(r.getLeft(), keylist);
            getLeaves(r.getRight(), keylist);
        }

    }

} //Fin de clase