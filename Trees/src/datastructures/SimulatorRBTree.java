
package datastructures;

import java.util.Iterator;
import javax.swing.JPanel;


public class SimulatorRBTree <T extends Comparable < T >> extends Simulator<T>{

    RBTree < T > myTreeRB = new RBTree <  > () ;

    public SimulatorRBTree () {

    }

  @Override
    public void deleteTree () {
        Iterator< T > it=this.myTreeRB.preOrder () ;
        //String r=msg+"\n";
        while ( it.hasNext () ) {
            delete ( it.next () ) ;
        }
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
