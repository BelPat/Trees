package datastructures;

import java.util.HashMap;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Iterator;
import javax.swing.JPanel;

public class BasicTree<Node extends BasicNode<Node, T>, T extends Comparable<T>> {

    private Node root, left,right;
    public T value;


    public BasicTree() {
        this.root = null;
    }
    public BasicTree(Node root) {
        this.root = root;
    }
    void setRoot(Node r) {
        this.root = r;
    }
    public Node getRoot() {
        return this.root;
    }
    void setLeft(Node l){
        this.left=l;
    }
    public Node getLeft() {
        return this.left;
    }
    void setRight(Node r){
        this.right=r;
    }
    public Node getRight() {
        return this.right;
    }
    

    public Iterator<T> preOrder() {
        
        SimpleList<Node, T> l = new SimpleList<>();
        preOrder(this.getRoot(), l);
        return (l.iterator());

    }

    private void preOrder(Node r, SimpleList<Node, T> l) {
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

    boolean isLeaf(Node x) {
      return (x != null && x.getLeft() == null && x.getRight() == null);
    }

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

    public boolean isEmpty() {
        return (this.root == null);
    }

    public Iterator<T> getLeaves() {
        SimpleList<Node, T> l = new SimpleList<>();
        getLeaves(this.root, l);
        return (l.iterator());
    }

    private void getLeaves(Node r, SimpleList<Node, T> l) {
        if (r != null) {
            if (this.isLeaf(r)) {
                l.addEnd(r.getKey());
            }
           if(r.getLeft()!=null) getLeaves(r.getLeft(), l);
           if(r.getRight()!=null)getLeaves(r.getRight(), l);
        }

    }

  //  abstract void cutLeaves();
    
 //   abstract boolean find(T key);
    
  //  abstract Comparable delete(T key);
    
 //   abstract boolean add( T key );
    
  //  abstract public Node createNode(T key);
 
    public boolean find(T key){
        return true;
    }
    
    public boolean add(T key){
        return true;
    }
    
    public Comparable delete(T key){
        return key;
    }
    public BTree getBTree(){
        return new BTree<>();
    }
    
    public RBTree getRBTree(){
        return new RBTree<>();
    }
    
    public AVLTree getAVLTree(){
        return new AVLTree<>();
    }
    public JPanel getPaint() {
        return new Graphic<>( this );
    }
    
   public HashMap getPositionNodes()  {     
       Graphic ae =new Graphic <>(this);
       return ae.getPositionNodes();
   }

    void cutLeaves() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
}
