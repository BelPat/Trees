package datastructures;

import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JPanel;
import java.util.ArrayList;

/**
 *
 * @author BelPat
 * @param <Node>
 * @param <T>
 */
public abstract class BasicTree<Node extends BasicNode<Node, T>, T extends Comparable<T>> {

    private Node root, left,right;

    /**
     *
     */
    public T value;

    /**
     * Construye un BasicTree vacío.
     */
    public BasicTree() {
        this.root = null;
    }

    /**
     * Construye un BasicTree con el nodo raíz del árbol.
     * 
     * @param root el nodo raíz del árbol
     */
    public BasicTree(Node root) {
        this.root = root;
    }
    
    /**
     * Establece el nodo raíz del árbol.
     * 
     * @param r el nodo raíz del árbol
     */
    public void setRoot(Node r) {
        this.root = r;
    }

    /**
     * Retorna el nodo raíz del árbol.
     * 
     * @return el nodo raíz del árbol
     */
    public Node getRoot() {
        return this.root;
    }
    
    /**
     * Establece el nodo izquierdo del árbol.
     * 
     * @param l el nodo izquierdo del árbol
     */
    public void setLeft(Node l){
        this.left=l;
    }

    /**
     * Retorna el nodo izquierdo del árbol
     * 
     * @return el nodo izquierdo del árbol
     */
    public Node getLeft() {
        return this.left;
    }
    
    /**
     * Establece el nodo derecho del árbol.
     * 
     * @param r el nodo derecho del árbol
     */
    public void setRight(Node r){
        this.right=r;
    }

    /**
     * Retorna el nodo derecho del árbol.
     * 
     * @return el nodo derecho del árbol
     */
    public Node getRight() {
        return this.right;
    }
    
    /**
     *
     * @return
     */
    public Iterator<T> preOrder() {
        
        ArrayList<T> keylist = new ArrayList<>();
        preOrder(this.getRoot(), keylist);
        return (keylist.iterator());

    }

    private void preOrder(Node r, ArrayList<T> keylist) {
        if (r != null) {
            keylist.add(r.getKey());
            preOrder(r.getLeft(), keylist);
            preOrder(r.getRight(), keylist);        
        }
    }

    /**
     * Determina si el nodo es una hoja.
     * 
     * @return <CODE>true</CODE> si el nodo es una hoja,
     * <CODE>false</CODE> en caso contrario
     */
    boolean isLeaf(Node x) {
      return (x != null && x.getLeft() == null && x.getRight() == null);
    }

    /**
     * Retorna el padre del árbol con la clave.
     * 
     * @param key la clave
     * @return el padre del àrbol
     */
    public T getFather(T key) {
        if (key == null || this.root == null) {
            return null;
        }
        Node x = getFather(this.root, key);
        if (x == null) {
            return null;
        }
        return (x.getKey());
    }

     /**
     * Retorna .
     * 
     * @param key
     * @return 
     */
    private Node getFather(Node x, T key) {
        if (x == null) {
            return null;
        }
        if ((x.getLeft() != null && x.getLeft().getKey().equals(key)) || (x.getRight() != null && x.getRight().getKey().equals(key))) {
            return (x);
        }
        Node y = getFather(x.getLeft(), key);
        if (y == null) {
            return (getFather(x.getRight(), key));
        } else {
            return (y);
        }
    }

    /**
     * Determina si el árbol es vacío.
     * 
     * @return <CODE>true</CODE> si el árbol es vacío,
     * <CODE>false</CODE> en caso contrario
     */
    public boolean isEmpty() {
        return (this.root == null);
    }

 
    /**
     *
     * @return
     */
    public JPanel getPaint() {
        return new Graphic<>( this );
    }
    
    /**
     * Retorna todas las posiciones de los nodos.
     * 
     * @return las posiciones de los nodos
     */
    public HashMap getPositionNodes()  {     
       Graphic ae =new Graphic <>(this);
       return ae.getPositionNodes();
   }
    
        /**
     * Elimina las hojas(nodos terminales) del árbol binario.
     */
    public void cutLeaves() {
    Iterator<T> it = this.getLeaves();
        while(it.hasNext())    {
            delete(it.next());
        }
    }
    
    abstract boolean find(T key);
    abstract Comparable delete(T key);
    abstract boolean add( T key );
    abstract public Iterator<T> getLeaves() ;
    abstract public void getLeaves(Node r, ArrayList<T> keylist);
   
}