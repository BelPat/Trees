
package datastructures;

import java.util.Iterator;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;


public class SimulatorAVLTree <T extends Comparable < T >>{

    AVLTree < T > myTree = new AVLTree < T > ();

    public SimulatorAVLTree() {

    }

    public boolean add( T new_key ){
        return ( this.myTree.add( new_key ) );
    }

    public String delete( T new_key ){
        T x = this.myTree.delete( new_key );

        if ( x == null )
            return ( "No existe el new_key en el arbol\n" );

        return ( "Borrado el new_key : " + x.toString() + "\n" );
    }
    
    public void deleteTree(){
        Iterator< T > it=this.myTree.preOrder();
        //String r=msg+"\n";
        while(it.hasNext()){
            delete(it.next());
        }
    }

    public String preOrder()
    {
        Iterator< T > it=this.myTree.preOrder();
        return (walk(it, "Recorrido PreOrden"));
    }

    public String isHere( T new_key )    {
         boolean here = this.myTree.find( new_key );
         String r = "El new_key:" + new_key.toString() + "\n";
         r += here ? "Si se encuentra en el arbol":"No se encuentra en el arbol\n";
         return ( r );
    }

    public String getLeaves()    {
        Iterator < T > it = this.myTree.getLeaves();
        return ( walk( it, "Hojas del Arbol" ) );
    }

    public String getFather(T child)   {
        if( this.myTree.getRoot().getKey().equals( child ) )
            return ( "La raiz no tiene father" );
        
        T father = this.myTree.getFather( child );
        
        if ( father == null )
            return ("No existe el Dato: " + child.toString() );
        
        return ( "El father de: " + child + "\n es : " + father.toString() );
    }

    private String walk( Iterator < T > it , String msg )    {
        String r = msg + "\n";
        
        while( it.hasNext() )
            r += "\t" + it.next().toString( )+ "\n";
        
        return ( r );
    }

    public void cutLeaves()    {
        this.myTree.cutLeaves();
    }

    public javax.swing.JTree getTree()   {
        return ( this.myTree.getJTree() );
    }

    public JPanel getPaint() {
        return ( this.myTree.getPaint() );                
    }

    public boolean add( T new_key , JInternalFrame jInternalFrame1 ) {
        return ( this.myTree.add( new_key ) );
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

