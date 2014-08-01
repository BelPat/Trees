package datastructures;

import java.util.HashMap;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Iterator;
import javax.swing.JPanel;

public abstract class Tree<T extends Comparable<T>> {

    private Node<T> root, left,right;
    public T value;


    Tree() {
        this.root = null;
    }
    Tree(Node<T> root) {
        this.root = root;
    }
    void setRoot(Node<T> r) {
        this.root = r;
    }
    Node<T> getRoot() {
        return this.root;
    }
    void setLeft(Node<T> l){
        this.left=l;
    }
    Node<T> getLeft() {
        return this.left;
    }
    void setRight(Node<T> r){
        this.right=r;
    }
    Node<T> getRight() {
        return this.right;
    }
    

    public Iterator<T> preOrder() {
        
        SimpleList<T> l = new SimpleList<>();
        preOrder(this.getRoot(), l);
        return (l.iterator());

    }

    private void preOrder(Node<T> r, SimpleList<T> l) {
        System.out.println(r);
        System.out.println("*******************************");
        if (r != null) {
            l.addEnd(r.getKey());
            preOrder(r.getLeft(), l);
            System.out.println("Left");
            System.out.println(r.getLeft());
            preOrder(r.getRight(), l);
            System.out.println("Right");
            System.out.println(r.getRight());
            
        }
    }

    boolean isLeaf(Node<T> x) {
        return (x != null && x.getLeft() == null && x.getRight() == null);
    }

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

    public boolean isEmpty() {
        return (this.root == null);
    }

    public Iterator<T> getLeaves() {
        SimpleList<T> l = new SimpleList<>();
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

    abstract void cutLeaves();
    
    abstract boolean find(T key);
    
    abstract Comparable delete(T key);
    
    abstract boolean add( T key )  ;
 
    protected DefaultMutableTreeNode createJtree(Node<T> r, String msg) {
        if (this.isLeaf(r)) {
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

    public JTree getJtree() {
        DefaultMutableTreeNode x = new DefaultMutableTreeNode("ARBOL-VACIO");
        if (this.isEmpty()) {
            return (new JTree(x));
        }
        return (new JTree(createJtree(this.root, "root(T)->")));
    }

    public JPanel getPaint() {
        return new GraphicTree<>( this );
    }
    
   public HashMap getPositionNodes()  {     
       GraphicTree ae =new GraphicTree <>(this);
       return ae.getPositionNodes();
   }
}
