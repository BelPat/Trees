/*
 * SimulatorBinaryTree.java
 
 */

package classes;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JPanel;

public class SimulatorBinaryTree {

    BBTree < Integer > myTree = new BBTree < Integer > ();

    public SimulatorBinaryTree() {

    }

    public boolean add ( Integer new_key )  {
        return ( this.myTree.add( new_key ) );
    }

    public String delete ( Integer new_key )    {
        Integer x = this.myTree.delete( new_key );
       
        if ( x == null )
            return ("No existe el new_key en el arbol\n");
        
        return ("Borrado el new_key: " + x.toString() + "\n");
    }
     
    public void deleteTree(){
        Iterator < Integer > it = this.myTree.preOrder();
        //String r=msg+"\n";
      
        while( it.hasNext() ){
            delete( it.next() );
        }
    }

    public String preOrder()   {
        Iterator < Integer > it = this.myTree.preOrder();
       
        return ( recorrido( it, "Recorrido PreOrden" ) );
    }

    public String getLeaves()   {
        Iterator < Integer > it = this.myTree.getLeaves();
        
        return ( recorrido( it, "Leaves del Arbol" ) );
    }

    public String getFather( Integer child )    {
        if ( this.myTree.getRoot().getKey().equals( child ) )
            return ("La root no tiene father");
        
        Integer father = this.myTree.getFather( child );
        
        if ( father == null )
            return ( "No existe el Dato: " + child.toString() );
        
        return ( "El father de: "+child+"\n es : "+father.toString() );
    }

    public String isHere( Integer new_key )    {
         boolean here = this.myTree.find( new_key );
         String r = "El new_key:" + new_key.toString() + "\n";
         r += here ? "Si se encuentra en el arbol":"No se encuentra en el arbol\n";
        
         return( r );
    }

    public BNode findNode( Integer new_key )   {
         BNode here = this.myTree.findNode( new_key );

         return ( here );
    }
    
    private String recorrido( Iterator < Integer > it , String msg ) {
    String r = msg + "\n";
    
    while( it.hasNext() )
        r += "\t" + it.next().toString() + "\n";
    
    return( r );
    }

    public void cutLeaves() {
        this.myTree.cutLeaves();
    }

    public javax.swing.JTree getTree()  {
        return ( this.myTree.getJtree() );
    }

    public JPanel getPaint() {
        return ( this.myTree.getPaint() );                
    }

    public HashMap getPositionNodes() { 
        return ( this.myTree.getPositionNodes() ); 
    }

}

