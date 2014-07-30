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
     //   BBTree < Integer > myTree = new BBTree < Integer > ();
    BTree < T > myTree = new BTree <  > ();
    //Tree < T > myTreeB;
    AVLTree < T > myTreeAvl = new AVLTree <> ();
    RBTree < T > myTreeRB = new RBTree <  > () ;

    public Simulator() {
     
     }

    public void deleteTree () {
        Iterator< T > it ;
        it = this.myTree.preOrder ();
        //String r=msg+"\n";
        while ( it.hasNext () ) {
            delete ( it.next () ) ;
        }
    } 
    
    
   public void cutLeaves()    {
        this.myTree.cutLeaves();
    }
    
   public String walk ( Iterator< T > it, String msg )    {
        String r=msg+"\n";
        while ( it.hasNext () ) 
            r += "\t" + it.next().toString() +"\n";
        return ( r ) ;
    }
   
    public String getLeaves()    {
        Iterator < T > it = this.myTree.getLeaves();
        return ( walk( it, "Hojas del Arbol" ) );
    }
    
    public String preOrder () {
        Iterator< T > it = this.myTree.preOrder () ;
        return  ( walk ( it, "Recorrido PreOrden" ) ) ;
    }
    
    public String getFather( T child )    {
        if ( this.myTree.getRoot().getKey().equals( child ) )
            return ("La root no tiene father");
        
        T father = this.myTree.getFather( child );
        
        if ( father == null )
            return ( "No existe el Dato: " + child.toString() );
        
        return ( "El father de: "+child+"\n es : "+father.toString() );
    }
    
      public String isHere( T new_key )    {
         boolean here = this.myTree.find(new_key );
         String r = "El new_key:" + new_key.toString() + "\n";
         r += here ? "Si se encuentra en el arbol":"No se encuentra en el arbol\n";
        
         return( r );
    }
      
     public JPanel getPaint() {
        return ( this.myTree.getPaint() );                
    }
     
     public String delete ( T new_key )    {
        Comparable x = this.myTree.delete( new_key );
       
        if ( x == null )
            return ("No existe el new_key en el arbol\n");
        
        return ("Borrado el new_key: " + x.toString() + "\n");
    }

    /*public boolean add( T new_key ){
        return ( this.myTree.add( new_key ) );
    }*/
    public boolean add(T key, String tree){
        switch (tree) {
            case "abc":
                return ( this.myTree.add( key ) );
                //break;
            case "avl":
                return (this.myTreeAvl.add(key));
                //break;
            case "rn":
                return ( this.myTreeRB.add(key) );
                //break;
        }
        return false;
    }
}
