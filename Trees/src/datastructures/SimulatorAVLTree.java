
package datastructures;

import java.util.Iterator;


public class SimulatorAVLTree <T extends Comparable < T >> extends Simulator{

    AVLTree < T > myTree = new AVLTree <  > ();

    public SimulatorAVLTree() {

    }

    @Override
    public void deleteTree(){
        Iterator< T > it=this.myTree.preOrder();
        //String r=msg+"\n";
        while(it.hasNext()){
            delete(it.next());
        }
    }
    
    @Override
          public String isHere( Comparable new_key )    {
         boolean here = this.myTree.find( (T)new_key );
         String r = "El new_key:" + new_key.toString() + "\n";
         r += here ? "Si se encuentra en el arbol":"No se encuentra en el arbol\n";
        
         return( r );
    }

    /*   public String inOrden()
    {
    Iterator< Integer > it=this.myTree.inOrden();
    return (walk(it, "Recorrido InOrden"));
    }

    public String posOrden()
    {
    Iterator< Integer > it=this.myTree.posOrden();
    return (walk(it, "Recorrido PosOrden"));
    }

    public String imprimirPorNiveles()
    {
    Iterator< Integer > it=this.myTree.impNiveles();
    return (walk(it, "Imprimir Por niveles"));
    }*/

    /*

    public String isHere(Integer new_key)
    {
    boolean here=this.myTree.buscar(new_key);
    String r="El new_key:"+new_key.toString()+"\n";
    r+=here?"Si se encuentra en el arbol":"No se encuentra en el arbol\n";
    return (r);
    }

    */

}

