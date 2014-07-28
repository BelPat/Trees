package datastructures;

import java.util.HashMap;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Iterator;
import javax.swing.JPanel;

public abstract class Tree<T extends Comparable<T>> {

    private Node<T> root;
    public T value;
    private Node<T> left;
    private Node<T> right;


    /**
     * Crea un árbol con una raíz dada
     *
     * @param root un tipo BNode<T> , almacena la dirección de memoria de un
     * nodo de un árbol binario
     */
    public Tree(Node<T> root) {
        this.root = root;
    }

    /**
     * Crea un árbol binario vacío
     *
     */
    public Tree() {
        this.root = null;
    }

    /**
     * Retorna la raíz del árbol
     *
     * @return un tipo de objeto de la clase BNode<T> que contiene la raíz del árbol
     */
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * Cambia la raíz del árbol por otra
     *
     * @param r recibe una nueva root para el árbol
     */
    public void setRoot(Node<T> r) {
        this.root = r;
    }

    /**
     * Retorna un iterador con el recorrido preOrder del árbol binario
     *
     * @return un iterador de la clase Iterator de la java.util
     */
    public Iterator<T> preOrder() {
        SimpleList<T> l = new SimpleList<T>();
        preOrder(this.getRoot(), l);
        return (l.iterator());

    }

    private void preOrder(Node<T> r, SimpleList<T> l) {
        if (r != null) {
            l.addEnd(r.getKey());
            preOrder(r.getLeft(), l);
            preOrder(r.getRight(), l);
        }
    }

    /** Dado un BNode<T>, nos indica si este es una hoja o no
     * @param x, BNode<T> del cual queremos saber si es hoja o no
     * @return , true en caso de que el nodo sea hoja, false en caso contrario
     */
    public boolean isLeaf(Node<T> x) {
        return (x != null && x.getLeft() == null && x.getRight() == null);
    }

    /**
     * Dado un dato info , retorna el padre de ese dato. 
     * @param info dato que se desea buscar
     * @return el padre del dato almacenado en el árbol, null en caso no existir
     * el dato
     */
    public T getFather(T key) {
        if (key == null || this.root == null) {
            return null;
        }
        Node<T> x = getFather(this.root, key);
        if (x == null) {
            return null;
        }
        return (x.getKey());
    }

    private Node<T> getFather(Node<T> x, T key) {
        if (x == null) {
            return null;
        }
        if ((x.getLeft() != null && x.getLeft().getKey().equals(key)) || (x.getRight() != null && x.getRight().getKey().equals(key))) {
            return (x);
        }
        Node<T> y = getFather(x.getLeft(), key);
        if (y == null) {
            return (getFather(x.getRight(), key));
        } else {
            return (y);
        }
    }

    /**
     * Retorna si el árbol contiene o no elementos.
     *
     * @return true si el árbol binario esta vacío o false en caso contrario
     */
    public boolean isEmpty() {
        return (this.root == null);
    }

    /**
     * Retorna un iterador con las Leaves del árbol binario
     *
     * @return un iterador de la clase Iterator de la java.util
     */
    public Iterator<T> getLeaves() {
        SimpleList<T> l = new SimpleList<T>();
        getLeaves(this.root, l);
        return (l.iterator());
    }

    private void getLeaves(Node<T> r, SimpleList<T> l) {
        if (r != null) {
            if (this.isLeaf(r)) {
                l.addEnd(r.getKey());
            }
            getLeaves(r.getLeft(), l);
            getLeaves(r.getRight(), l);
        }

    }
    
       /**
    * Elimina las hojas(nodos terminales) del árbol binario.
    */
    abstract void cutLeaves();
    
    abstract boolean find(Comparable key);
    
    abstract Comparable delete(Comparable key);
    
    abstract boolean add( Comparable key )  ;
 
    
        /**
     * Retorna el Nodo si existe un dato info en el árbol binario, o null en caso
     * contrario.     *
     * @return un BNode ,si el dato está o null en caso contrario.
   

    public Node<T> findNode(T key) {
        return (findNode(this.root, key));
    }

    private Node<T> findNode(Node<T> r, T key) {
        if (r == null) {
            return (null);
        }  else if (r.getKey().equals(key)) {
            return (r);
        }
        findNode(r.getLeft(), key);        
        findNode(r.getRight(), key);        
        return null;
      }  
  */

 /*   public boolean buscar(T info) {
        return (buscar(this.root, info));
    }

    private boolean buscar(BNode<T> r, T info) {
        if (r == null) {
            return (false);
        }
        if (r.getKey().equals(info)) {
            return (true);
        }
        return (buscar(r.getLeft(), info) || buscar(r.getRight(), info));
    }
*/
    protected DefaultMutableTreeNode createJtree(Node<T> r, String msg) {
        if (isLeaf(r)) {
            return (new DefaultMutableTreeNode(msg + r.getKey().toString()));
        }
        DefaultMutableTreeNode x = new DefaultMutableTreeNode(msg + r.getKey().toString());
        if (r.getLeft() != null) {
            x.add(createJtree(r.getLeft(), "Left->"));
        }
        if (r.getRight() != null) {
            x.add(createJtree(r.getRight(), "Right->"));
        }
        return x;
    }

    /**
     * Crea un árbol de la clase JTree. Esta clase permite dibujar el árbol
     * utilizando una distribución de ficheros y directorios de un explorador de
     * archivos convencional
     *
     * @return un JTree con el árbol binario que se tiene creado
     */
    public JTree getJtree() {
        DefaultMutableTreeNode x = new DefaultMutableTreeNode("ARBOL-VACIO");
        if (this.isEmpty()) {
            return (new JTree(x));
        }
        return (new JTree(createJtree(this.root, "root(T)->")));
    }

    public JPanel getPaint() {
        return new GraphicTree<T>( this );
    }
    
   public HashMap getPositionNodes()  {     
       GraphicTree ae =new GraphicTree < T >(this);
       return ae.getPositionNodes();
   }
}
