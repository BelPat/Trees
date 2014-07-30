
package datastructures;


public class SimulatorAVLTree <T extends Comparable < T >> extends Simulator<T>{

    AVLTree < T > myTreeAvl = new AVLTree <> ();

    public SimulatorAVLTree() {

    }

   /* @Override
    public void deleteTree(){
        Iterator< T > it=this.myTree.preOrder();
        while(it.hasNext()){
            delete(it.next());
        }
    }*/
    
    /**
     *
     * @param new_key
     * @return
     */
   /*
    public String isHere( T new_key )    {
         boolean here = this.myTree.find( new_key );
         String r = "El new_key:" + new_key.toString() + "\n";
         r += here ? "Si se encuentra en el arbol":"No se encuentra en el arbol\n";
        
         return( r );
    }*/

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

