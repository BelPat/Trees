
package datastructures;

import java.util.Iterator;
import javax.swing.JPanel;


public class SimulatorRBTree <T extends Comparable < T >> {

    RBTree < T > myTree = new RBTree <  > () ;

    public SimulatorRBTree () {

    }

    public boolean add ( T new_key ) {
        return ( this.myTree.add ( new_key ) ) ;
    }

    public void delete ( T new_key ) {       
        this.myTree.delete ( new_key ) ;
    }

    public void deleteTree () {
        Iterator< T > it=this.myTree.preOrder () ;
        //String r=msg+"\n";
        while ( it.hasNext () ) {
            delete ( it.next () ) ;
        }
    }

    public String preOrder () {
        Iterator< T > it = this.myTree.preOrder () ;
        return  ( walk ( it, "Recorrido PreOrden" ) ) ;
    }

    public String getLeaves () {
        Iterator< T > it = this.myTree.getLeaves () ;
        return  ( walk ( it, "Leaves del Arbol" ) ) ;
    }


    /**
     *
     * @param child
     * @return
     */
    public String getFather ( T child ) {
        if ( this.myTree.getRoot().getKey().equals ( child )  ) 
            return  ( "La raiz no tiene padre" ) ;

        T father = this.myTree.getFather ( child ) ;

        if  ( father == null ) 
            return  ( "No existe el Dato: " + child.toString () ) ;

        return  ( "El padre de: " + child + "\n es : " + father.toString () ) ;
    }

    /**
     *
     * @param new_key
     * @return
     */
    public String isHere ( T new_key ) {
         boolean here ;
        here = this.myTree.find ( new_key );
         String r = "El new_key:" + new_key.toString () +"\n";
         r += here ? "Si se encuentra en el arbol":"No se encuentra en el arbol\n";

         return  ( r ) ;
    }

    private String walk ( Iterator< T > it, String msg )    {
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
