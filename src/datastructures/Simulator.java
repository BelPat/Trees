/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Iterator;
import javax.swing.JPanel;


/**
 *
 * @author BelPat
 * @param <T>
 */
public class Simulator <T extends Comparable<T>> {

    BasicTree<?,T> pTree;
    
    /**
     *
     */
    public Simulator() {
     
     }
    
    /**
     *
     * @param t
     */
    public Simulator(BasicTree<?,T> t) {
            pTree=t;    
     }

   
    /**
     *
     */
    public void deleteTree () {
        Iterator<T> it ;
        it = this.pTree.preOrder ();
        //String r=msg+"\n";
        while ( it.hasNext () ) {
            delete ( it.next ()) ;
        }
    } 
    
    /**
     *
     */
    public void cutLeaves()    {
        this.pTree.cutLeaves();
    }
    
    /**
     *
     * @param it
     * @param msg
     * @return
     */
    public String walk ( Iterator it, String msg )    {
        String r=msg+"\n";
        while ( it.hasNext () ) 
            r += "\t" + it.next().toString() +"\n";
        return ( r ) ;
    }
   
    /**
     *
     * @return
     */
    public String getLeaves()    {
        Iterator<T> it = this.pTree.getLeaves();
        return ( walk( it, "Hojas del Arbol" ) );
    }
    
    /**
     *
     * @return
     */
    public String preOrder () {
        Iterator it = this.pTree.preOrder () ;
        return  ( walk ( it, "Recorrido PreOrden" ) ) ;
    }
    
    /**
     *
     * @param child
     * @return
     */
    public String getFather( T child )    {
        if ( this.pTree.getRoot().getKey().equals( child ) )
            return ("La root no tiene father");
        
         T father = this.pTree.getFather( child );
        
        if ( father == null )
            return ( "No existe el Dato: " + child.toString() );
        
        return ( "El father de: "+child+"\n es : "+father.toString() );
    }
    
    /**
     *
     * @param new_key
     * @return
     */
    public String isHere( T new_key )    {
         boolean here = this.pTree.find(new_key );
         String r = "El new_key:" + new_key.toString() + "\n";
         r += here ? "Si se encuentra en el arbol":"No se encuentra en el arbol\n";
        
         return( r );
    }
      
    /**
     *
     * @return
     */
    public JPanel getPaint() {
        return ( this.pTree.getPaint() );                
    }
     
    /**
     *
     * @param new_key
     * @return
     */
    public String delete ( T new_key )    {
        Comparable x = this.pTree.delete( new_key );
       
        if ( x == null )
            return ("No existe el new_key en el arbol\n");
        
        return ("Borrado el new_key: " + x.toString() + "\n");
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean add( T key ){
      return ( this.pTree.add( key ) );

    }

}