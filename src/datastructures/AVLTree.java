package datastructures;
 
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author patricia
 * @param <T>
 */
public class AVLTree<T extends Comparable <T>> extends Tree<T>{
 
 private AVLNode < T > root; // the root node
 
/***************************** Core Functions ************************************/
 /**
  * Add a new element with key "k" into the tree.
  * 
  * @param k
  *            The key of the new node.
  */
 
    /**
     * Crea un árbol binario vacío
     *
     */
    public AVLTree() {
       super();
    }                              
             
 @Override
     public boolean add(T k) {

      // create new node
      AVLNode < T >  n = new AVLNode < > (k);
      // start recursive procedure for inserting the node
        
      if (this.find(k)==false){
            return ( add( this.root ,n ) );
      }
      return false;
    }
 
    
    /**
    * Recursive method to insert a node into a tree.
    * 
    * @param p The node currently compared, usually you start with the root.
    * @param q The node to be inserted.
    */
    public boolean add(AVLNode < T > p, AVLNode < T > q) {
      // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree.
      if(p==null) {
           System.out.println("  if avltree.add(AVLNode, AVLNode) q.getKey()" + q.getKey());
           this.root = q;
           System.out.println("  if avltree.add(AVLNode, AVLNode) this.root.getKey()" + this.root.getKey());
      } else {   
          
       // If compare node is smaller, continue with the getLeft() node
       //if(q.toInt()<p.toInt()) {
     System.out.println(" avlnode.add -> q.getKey " + q.getKey()+ " p.getkey " + p.getKey());
     
       if( q.getKey().compareTo(p.getKey()) < 0 ) {    
            if(p.getLeft()==null) {
                 p.setLeft(q);
                 q.setRoot(p);
                 // Node is inserted now, continue checking the balance
                 recursiveBalance(p);
            } else {
                 add(p.getLeft(),q);
            }

      // } else if(q.toInt()>p.toInt()) {
         } else if(q.getKey().compareTo(p.getKey())>0) {
            if(p.getRight()==null) {
                 p.setRight(q);
                 q.setRoot(p);     
                 // Node is inserted now, continue checking the balance
                 recursiveBalance(p);
            } else {
             add(p.getRight(),q);
            }
       } else {
            return false;
       }
      }
       return true;
    }

    /**
    * Check the balance for each node recursivly and call required methods for balancing the tree until the root is reached.
    * 
    * @param cur : The node to check the balance for, usually you start with the root of a leaf.
    */
    public void recursiveBalance(AVLNode < T > cur) {
      // we do not use the balance in this class, but the store it anyway
      setBalance(cur);
      int balance = cur.getBalance();

      // check the balance
      if( balance == -2 ) {
           if( height(cur.getLeft().getLeft()) >= height(cur.getLeft().getRight()) ) {
                cur = rotateRight( cur );
           } else {
                cur = doubleRotateLeftRight( cur );
           }
      } else if( balance == 2 ) {
           if( height(cur.getRight().getRight()) >= height(cur.getRight().getLeft()) ) {
                cur = rotateLeft( cur );
           } else {
                cur = doubleRotateRightLeft( cur );
           }
      }
      // we did not reach the root yet
      if(cur.getRoot()!=null) {
           recursiveBalance(cur.getRoot());
      } else {
           this.root = cur;
           System.out.println("------------ Balancing finished ----------------");
      }
    }

    /**
    * Removes a node from the tree, if it is existent.
    */
 @Override
     public Comparable delete(T k) {
      // First we must find the node, after this we can delete it.
        T aux_key = deleteAVL(this.root, (T)k);
        
      return(aux_key);
    }

    /**
    * Finds a node and calls a method to remove the node.
    * 
    * @param p The node to start the search.
    * @param q The KEY of node to remove.
    */
    public T deleteAVL(AVLNode < T > p,T q) {
      if( p == null ) {
           // getRight() Wert existiert nicht in diesem Baum, daher ist nichts zu tun
           return null;
      } else {
           /*if(p.toInt()>q)  {
                deleteAVL(p.getLeft(),q);
           } else if(p.toInt()<q) {
                delketeAVL(p.getRight(),q);
           } else if(p.getKey()==q) {
                // we found the node in the tree.. now lets go on!
                deleteNode(p);
           }*/
          System.out.println("Delete_avl p" + p.getKey() + "q " + q);
          if( p.getKey().compareTo( q ) > 0 )  {
                System.out.println("Delete_avl p>q" + p.getKey() + "q " + q);
                deleteAVL(p.getLeft(),q);
           } else if( p.getKey().compareTo( q ) < 0 ) {
                 System.out.println("Delete_avl p<q" + p.getKey() + "q " + q);
                deleteAVL(p.getRight(),q);
           } else if( p.getKey().compareTo( q ) ==0 ) {
                System.out.println("Delete_avl p=q" + p.getKey() + "q " + q);
                // we found the node in the tree.. now lets go on!
                
                deleteNode(p);
           }
       return q;
      }
    }

    /**
    * Removes a node from a AVL-Tree, while balancing will be done if necessary.
    * 
    * @param q The node to be removed.
    */
    public void deleteNode(AVLNode < T > q) {
      AVLNode < T > r;
      // at least one child of q, q will be removed directly
      if( q.getLeft()==null || q.getRight()==null ) {
           // the root is deleted
           if( q.getRoot()==null ) {
                this.root=null;
                q=null;
                return;
           }
           r = q;
      } else {
           // q has two children --> will be replaced by successor
           r = successor( q );
           q.setKey( r.getKey() );
      }
      AVLNode < T > p;
      if(r.getLeft()!=null) {
           p = r.getLeft();
      } else {
           p = r.getRight();
      }

      if(p!=null) {
           p.setRoot( r.getRoot());
      }

      if(r.getRoot()==null) {
           this.root = p;
      } else {
               if(r==r.getRoot().getLeft()) {
                r.getRoot().setLeft(p);
           } else {
                r.getRoot().setRight(p);
           }
           // balancing must be done until the root is reached.
           recursiveBalance(r.getRoot());
      }
      r = null;
    }

    /**
     * Retorna true si existe un dato en el árbol binario, o false en caso
     * contrario. Es necesario para que el método funcione que los objetos
     * almacenados en el árbol binario tengan sobreescrito el método equals.
     *
     * @return un boolean , true si el dato está o false en caso contrario.
     */
    //La clase T debe tener sobreescrito el metodo equals
 @Override
    public boolean find(T key) {
     
     System.out.println("********************** avltree.find ************* " + key);
        return ( find( this.getRoot(),key ) );
    }

    private boolean find(AVLNode<T> r, T key) {
        boolean isleft= false;
        boolean isright = false;
        if (r == null) {
            return (false);
        }
        if (r.getKey().equals(key)) {
            return (true);
        }
        if (r.getLeft() == null) {
            isleft = find(r.getLeft(), key);
        }
        if (r.getRight() == null) {
            isright = find(r.getRight(), key);
        }
        return ( isright || isleft );
    }

     /**
     * Retorna un iterador con las hojas del árbol binario
     *
     * @return un iterador de la clase Iterator de la java.util
     */
    public Iterator<T> getLeaves() {
        SimpleList<T> l = new SimpleList<T>();
        getLeaves(this.getRoot(), l);
        return (l.iterator());
    }

    private void getLeaves(AVLNode<T> r, SimpleList<T> l) {
        if (r != null) {
            if (super.isLeaf(r)) {
                l.addEnd(r.getKey());
            } else {
            }
            getLeaves(r.getLeft(), l);
            getLeaves(r.getRight(), l);
        }

    }

/*
   protected boolean isLeaf(AVLNode<T> x) {
        return (x != null && x.getLeft() == null && x.getRight() == null);
    }
*/

       /**
     * Dado un dato almacenado en el árbol , retorna el padre de ese dato. Se
     * parte de la premisa que el árbol no contiene elementos repetidos.
     *
     * @param key dato que se desea find
     * @return el padre del dato almacenado en el árbol, null en caso no existir
     * el dato
     */


    private AVLNode<T> getFather(AVLNode<T> x, T key) {
        if (x == null) {
            return null;
        }
        if ((x.getLeft() != null && x.getLeft().getKey().equals(key)) || (x.getRight() != null && x.getRight().getKey().equals(key))) {
            return (x);
        }
        AVLNode<T> y = getFather(x.getLeft(), key);
        if (y == null) {
            return (getFather(x.getRight(), key));
        } else {
            return (y);
        }
    }


    /**
     * Elimina las hojas(nodos terminales) del árbol binario.
     */
     public void cutLeaves() {

        Iterator<T> it=(Iterator<T>) this.getLeaves();        
        while(it.hasNext())
        {
         //   delete(it.next().intValue());
            delete(it.next());
        }
    }

    /*
    private int[] cutLeaves(AVLNode<T> x) {

        int aux;
        int hojas[] = null;
        int cont_hojas=0;

        if (x == null) {
            return null;
        }
        if (this.esLeaf(x.getLeft())) {
            hojas[cont_hojas]=x.getLeft().toInt();
            cont_hojas++;
        }
        if (this.esLeaf(x.getRight())) {
            aux=x.getRight().toInt();
            hojas[cont_hojas]= aux;
            cont_hojas++;
        }
        cutLeaves(x.getLeft());
        cutLeaves(x.getRight());

        return hojas;


    }


    private void cutLeaves(AVLNode<T> x) {

        AVLNode<T> aux_getLeft()=null;
        AVLNode<T> aux_getRight()=null;
        int max=this.height(this.root);
        int hojas[] = null;
        int cont_hojas=0;

        if (x == null) {
            return;
        }
        if (this.esLeaf(x.getLeft())) {
               hojas[cont_hojas]=x.getLeft().toInt();
               cont_hojas++;
        }
        if (this.esLeaf(x.getRight())) {
               hojas[cont_hojas]=x.getRight().toInt();
               cont_hojas++;
        }
        cutLeaves(x.getLeft());
        cutLeaves(x.getRight());

        for(int cont=0;cont<cont_hojas;cont++)
        {
            delete(hojas[cont]);
        }

    }*/

    /*   private void cutLeaves(AVLNode<T> x) {

        AVLNode<T> aux_getLeft()=null;
        AVLNode<T> aux_getRight()=null;
        if (x == null) {
            return;
        }
        if (this.esLeaf(x.getLeft())) {
            aux_getLeft()=new AVLNode<T>(x.getLeft());
            delete(x.getLeft().toInt());
            cutLeaves(aux_getLeft());
        }
        else{
                cutLeaves(x.getLeft());
        }
        if (this.esLeaf(x.getRight())) {
            aux_getRight()=new AVLNode<T>(x.getRight());
            delete(x.getRight().toInt());
            cutLeaves(aux_getRight());
        }
      else{
        cutLeaves(x.getRight());
    }     

    }*/


    /**
    * Left rotation using the given node.
    * 
    * 
    * @param n
    *            The node for the rotation.
    * 
    * @return The root of the rotated tree.
    */
    public AVLNode<T> rotateLeft(AVLNode < T > n) {
      AVLNode < T > v = n.getRight();
      v.setRoot( n.getRoot() ) ;
      n.setRight( v.getLeft() );
      if( n.getRight() != null ) {
           n.getRight().setRoot(n);
      }
      v.setLeft( n );
      n.setRoot ( v );
      if( v.getRoot() != null ) {
           if( v.getRoot().getRight() == n ) {
                v.getRoot().setRight(v) ;
           } else if( v.getRoot().getLeft() == n ) {
                v.getRoot().setLeft(v);
           }
      }
      setBalance(n);
      setBalance(v);
      return v;
    }

    /**
    * Right rotation using the given node.
    * 
    * @param n
    *            The node for the rotation
    * 
    * @return The root of the new rotated tree.
    */
    public AVLNode<T> rotateRight( AVLNode < T > n ) {  
      AVLNode < T > v = n.getLeft();
      v.setRoot( n.getRoot());  
      n.setLeft( v.getRight() );  
      if( n.getLeft() != null ) {
           n.getLeft().setRoot(n);
      }
      v.setRight(n);
      n.setRoot(v);
      if( v.getRoot() != null ) {
           if( v.getRoot().getRight() == n ) {
                v.getRoot().setRight(v);
           } else if( v.getRoot().getLeft() == n ) {
                v.getRoot().setLeft(v);
           }
      }
      setBalance(n);
      setBalance(v);

      return v;
    }
    /**
    * 
    * @param u The node for the rotation.
    * @return The root after the double rotation.
    */
    public AVLNode<T> doubleRotateLeftRight(AVLNode < T > u) {
        u.setLeft( rotateLeft( u.getLeft()) );
        return rotateRight( u );
    }

    /**
    * 
    * @param u The node for the rotation.
    * @return The root after the double rotation.
    */
    public AVLNode<T> doubleRotateRightLeft( AVLNode < T > u ) {
        u.setRight( rotateRight( u.getRight()) );
        return rotateLeft( u );
    }

    /***************************** Helper Functions ************************************/

    /**
    * Returns the successor of a given node in the tree (search recursivly).
    * 
    * @param q The predecessor.
    * @return The successor of node q.
    */
    public AVLNode<T> successor(AVLNode < T > q) {
      if( q.getRight() != null ) {
           AVLNode < T > r = q.getRight();
           while( r.getLeft() != null ) {
                r = r.getLeft();
           }
           return r;
      } else {
           AVLNode < T > p = q.getRoot();
           while( p!=null && q==p.getRight() ) {
                q = p;
                p = q.getRoot();
           }
           return p;
      }
    }

    /**
    * Calculating the "height" of a node.
    * 
    * @param cur
    * @return The height of a node (-1, if node is not existent eg. NULL).
    */
    private int height(AVLNode < T > cur) {
      if(cur==null) {
           return -1;
      }
      if( cur.getLeft() == null && cur.getRight() == null ) {
           return 0;
      } else if ( cur.getLeft() == null ) {
           return 1 + height( cur.getRight() );
      } else if( cur.getRight() == null ) {
           return 1 + height( cur.getLeft() );
      } else {
           return 1 + maximum( height ( cur.getLeft() ) , height( cur.getRight())  );
      }
    }

    /**
    * Return the maximum of two integers.
    */
    private int maximum(int a, int b) {
      if(a>=b) {
           return a;
      } else {
           return b;
      }
    }

    /** 
    * Only for debugging purposes. Gives all keyrmation about a node.

    * @param n The node to write keyrmation about.
    */
 /*   public void debug(AVLNode n) {
      T l;
      T r;
      T p;
      if(n.getLeft()!=null) {
           l = (T) n.getLeft().getKey();
      }
      if(n.getRight()!=null) {
           r = (T) n.getRight().getKey();
      }
      if(n.getRoot()!=null) {
           p = (T) n.getRoot().getKey();
      }

     // System.out.println("Left: "+l+" Key: "+n+" Right: "+r+" Parent: "+p+" Balance: "+n.getBalance());

      if(n.getLeft()!=null) {
           debug(n.getLeft());
      }
      if(n.getRight()!=null) {
           debug(n.getRight());
      }
    }*/

    /**
     * Crea un árbol de la clase JTree. Esta clase permite dibujar el árbol
     * utilizando una distribución de ficheros y directorios de un explorador de
     * archivos convencional
     *
     * @return un JTree con el árbol binario que se tiene creado
     */
    public JTree getJTree() {
        DefaultMutableTreeNode x = new DefaultMutableTreeNode("ARBOL-VACIO");
        if (this.isEmpty()) {
            return (new JTree(x));
        }
        return (new JTree(createJtree(super.getRoot(), "Root(T)->")));
    }

    protected DefaultMutableTreeNode createJtree(AVLNode<T> r, String msg) {
        if (super.isLeaf(r)) {
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

    public JPanel getPaint() {
        return new Graphic<>( this );
    }

    public boolean isEmpty() {
        return ( this.getRoot() == null );
    }


    private void setBalance(AVLNode < T > cur) {
       int heightleft = 0;
       int heightright = 0;
       
       if(cur.getLeft()!= null)
           heightleft = height(cur.getLeft());
       

        if(cur.getRight()!= null)        {
                   System.out.println("setBalance " + cur.getRight().getKey());
           heightright = height(cur.getRight());
           
        }
       
        cur.setBalance( heightright - heightleft);
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

    private void preOrder(AVLNode<T> r, SimpleList<T> l) {
        if (r != null) {
            l.addEnd(r.getKey());
            preOrder(r.getLeft(), l);
            preOrder(r.getRight(), l);
        }
    }

 @Override
     public AVLNode < T > getRoot() {
        return root;
    }

    public void setRoot(AVLNode < T > root) {
        this.root = root;
    }
 
}

