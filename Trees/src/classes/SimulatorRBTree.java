
package classes;

import java.util.Iterator;
import javax.swing.JPanel;


public class SimulatorRBTree {

    RBTree < Integer > myTree = new RBTree < Integer > () ;

    public SimulatorRBTree () {

    }

    public boolean add ( Integer new_key ) {
        return ( this.myTree.add ( new_key ) ) ;
    }

    public void delete ( Integer new_key ) {       
        this.myTree.delete ( new_key ) ;
    }

    public void deleteTree () {
        Iterator< Integer > it=this.myTree.preOrder () ;
        //String r=msg+"\n";
        while ( it.hasNext () ) {
            delete ( it.next () ) ;
        }
    }

    public String preOrder () {
        Iterator< Integer > it = this.myTree.preOrder () ;
        return  ( walk ( it, "Recorrido PreOrden" ) ) ;
    }

    public String getLeaves () {
        Iterator< Integer > it = this.myTree.getLeaves () ;
        return  ( walk ( it, "Leaves del Arbol" ) ) ;
    }


    public String getFather ( Integer child ) {
        if ( this.myTree.getRoot().getKey().equals ( child )  ) 
            return  ( "La raiz no tiene father" ) ;

        Integer father = this.myTree.getFather ( child ) ;

        if  ( father == null ) 
            return  ( "No existe el Dato: " + child.toString () ) ;

        return  ( "El father de: " + child + "\n es : " + father.toString () ) ;
    }

    public String isHere ( Integer new_key ) {
         boolean here = this.myTree.find ( new_key ) ;
         String r = "El new_key:" + new_key.toString () +"\n";
         r += here ? "Si se encuentra en el arbol":"No se encuentra en el arbol\n";

         return  ( r ) ;
    }

    private String walk ( Iterator< Integer > it, String msg )    {
        String r=msg+"\n";
        while ( it.hasNext () ) 
            r += "\t" + it.next().toString() +"\n";
        return ( r ) ;
    }

    public void cutLeaves ()  {
        this.myTree.cutLeaves () ;
    }

    public JPanel getPaint ()  {
        return this.myTree.getPaint () ;                
    }
    /*    public javax.swing.JTree darTree () 
    {
    return  ( this.myTree.darJTree ()  ) ;
    }
    */  


    /* 
    public String inOrden () 
    {
    Iterator< Integer > it=this.myTree.inOrden () ;
    return  ( walk ( it, "Recorrido InOrden" )  ) ;
    }

    public String posOrden () 
    {
    Iterator< Integer > it=this.myTree.posOrden () ;
    return  ( walk ( it, "Recorrido PosOrden" )  ) ;
    }

    public String imprimirPorNiveles () 
    {
    Iterator< Integer > it=this.myTree.impNiveles () ;
    return  ( walk ( it, "Imprimir Por niveles" )  ) ;
    }
    **/

}
