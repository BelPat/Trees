package datastructures;

import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JPanel;
import java.util.ArrayList;

/**
 *
 * @author nusatres
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
     *
     */
    public BasicTree() {
        this.root = null;
    }

    /**
     *
     * @param root
     */
    public BasicTree(Node root) {
        this.root = root;
    }
    void setRoot(Node r) {
        this.root = r;
    }

    /**
     *
     * @return
     */
    public Node getRoot() {
        return this.root;
    }
    void setLeft(Node l){
        this.left=l;
    }

    /**
     *
     * @return
     */
    public Node getLeft() {
        return this.left;
    }
    void setRight(Node r){
        this.right=r;
    }

    /**
     *
     * @return
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

    boolean isLeaf(Node x) {
      return (x != null && x.getLeft() == null && x.getRight() == null);
    }

    /**
     *
     * @param key
     * @return
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
     *
     * @return
     */
    public boolean isEmpty() {
        return (this.root == null);
    }

     abstract void cutLeaves();
     
    
 //   abstract boolean find(T key);
    
  //  abstract Comparable delete(T key);
    
 //   abstract boolean add( T key );
    
  //  abstract public Node createNode(T key);
 
    /**
     *
     * @param key
     * @return
     */
     
    public boolean find(T key){
        return true;
    }
    
    /**
     *
     * @param key
     * @return
     */
    public boolean add(T key){
        return true;
    }
    
    /**
     *
     * @param key
     * @return
     */
    public T delete(T key){
        return key;
    }

    /**
     *
     * @return
     */
    public BTree getBTree(){
        return new BTree<>();
    }
    
    /**
     *
     * @return
     */
    public RBTree getRBTree(){
        return new RBTree<>();
    }
    
    /**
     *
     * @return
     */
    public AVLTree getAVLTree(){
        return new AVLTree<>();
    }

    /**
     *
     * @return
     */
    public JPanel getPaint() {
        return new Graphic<>( this );
    }
    
    /**
     *
     * @return
     */
    public HashMap getPositionNodes()  {     
       Graphic ae =new Graphic <>(this);
       return ae.getPositionNodes();
   }
    abstract public Iterator<T> getLeaves() ;
    abstract public void getLeaves(Node r, ArrayList<T> keylist);
   
}