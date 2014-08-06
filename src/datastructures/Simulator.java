/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author nusatres
 */
public class Simulator <T extends Comparable < T >>{

    Tree < T > pTree;
    

    public Simulator() {
     
     }

    public Simulator(String tree) {
        
     switch (tree) {
            case "abc":
               pTree = new  BTree<> ();
                break;
            case "avl":
               pTree = new  AVLTree<> ();
                break;
            case "rn":
               pTree = new  RBTree<> ();
                break;
        }
    }
    

    public void deleteTree () {
        Iterator< T > it ;
        it = this.pTree.preOrder ();
        //String r=msg+"\n";
        while ( it.hasNext () ) {
            delete ( it.next () ) ;
        }
    } 
    
    
   public void cutLeaves()    {
        this.pTree.cutLeaves();
    }
    
   public String walk ( Iterator< T > it, String msg )    {
        String r=msg+"\n";
        while ( it.hasNext () ) 
            r += "\t" + it.next().toString() +"\n";
        return ( r ) ;
    }
   
    public String getLeaves()    {
        Iterator < T > it = this.pTree.getLeaves();
        return ( walk( it, "Hojas del Arbol" ) );
    }
    
    public String preOrder () {
        Iterator< T > it = this.pTree.preOrder () ;
        return  ( walk ( it, "Recorrido PreOrden" ) ) ;
    }
    
    public String getFather( T child )    {
        if ( this.pTree.getRoot().getKey().equals( child ) )
            return ("La root no tiene father");
        
        T father = this.pTree.getFather( child );
        
        if ( father == null )
            return ( "No existe el Dato: " + child.toString() );
        
        return ( "El father de: "+child+"\n es : "+father.toString() );
    }
    
      public String isHere( T new_key )    {
         boolean here = this.pTree.find(new_key );
         String r = "El new_key:" + new_key.toString() + "\n";
         r += here ? "Si se encuentra en el arbol":"No se encuentra en el arbol\n";
        
         return( r );
    }
      
     public JPanel getPaint() {
        return ( this.pTree.getPaint() );                
    }
     
     public String delete ( T new_key )    {
        Comparable x = this.pTree.delete( new_key );
       
        if ( x == null )
            return ("No existe el new_key en el arbol\n");
        
        return ("Borrado el new_key: " + x.toString() + "\n");
    }

    public boolean add( T key ){
      return ( this.pTree.add( key ) );

    }
}