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
public class Simulator <T extends Comparable<T>> {

    BasicTree pTree;
    
    
    public Simulator() {
     
     }
    
    public Simulator(BasicTree t) {
            pTree=t;    
     }

  /*  public Simulator(String tree) {
        
     switch (tree) {
            case "abc":
                pTree.getBTree();
                break;
            case "avl":
                    pTree.getAVLTree();
                break;
            case "rn":
              //  pTree = new  RBTree<> ();
                     pTree.getRBTree();
                break;
        }
     
    }*/
    

    public void deleteTree () {
        Iterator it ;
        it = this.pTree.preOrder ();
        //String r=msg+"\n";
        while ( it.hasNext () ) {
            delete ( (T) it.next ()) ;
        }
    } 
    
    
   public void cutLeaves()    {
        this.pTree.cutLeaves();
    }
    
   public String walk ( Iterator it, String msg )    {
        String r=msg+"\n";
        while ( it.hasNext () ) 
            r += "\t" + it.next().toString() +"\n";
        return ( r ) ;
    }
   
    public String getLeaves()    {
        Iterator it = this.pTree.getLeaves();
        return ( walk( it, "Hojas del Arbol" ) );
    }
    
    public String preOrder () {
        Iterator it = this.pTree.preOrder () ;
        return  ( walk ( it, "Recorrido PreOrden" ) ) ;
    }
    
    public String getFather( T child )    {
        if ( this.pTree.getRoot().getKey().equals( child ) )
            return ("La root no tiene father");
        
        Comparable father = this.pTree.getFather( child );
        
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
  /*  public void loadExamples(int i){
        if(i==1){
            this.add(22);
            this.add(33);
            this.add(44);
            this.add(23);
            this.add(11);
            this.add(15);
            this.add(60);
            this.add(38);
            this.add(72);
        }else if(i==2){
            
        }else if (i==3){
            
        }else if(i==4){
            
        }
    }*/
}