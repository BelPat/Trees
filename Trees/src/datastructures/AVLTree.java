package datastructures;
 
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author patricia
 * @param <T>
 */
public class AVLTree<T extends Comparable<T>> extends BasicTree<AVLNode<T>, T>{
 
// private AVLNode < T > root; 
 
    /**
     *
     */
     
 
    public AVLTree() {
       super();
    }                              
             
    /**
     *
     * @param k
     * @return
     */
    @Override
     public boolean add(T k) {

      AVLNode < T >  n = new AVLNode < > (k);
             
      if (this.find(k)==false){
            return ( add( (AVLNode<T>)super.getRoot() ,n ) );
      }
      return false;
    }
 
    /**
     *
     * @param p
     * @param q
     * @return
     */
    public boolean add(AVLNode < T > p, AVLNode < T > q) {
      // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree.
      if(p==null) {
           System.out.println("  if avltree.add(AVLNode, AVLNode) q.getKey()" + q.getKey());
           super.setRoot(q);
        //   System.out.println("  if avltree.add(AVLNode, AVLNode) this.root.getKey()" + this.root.getKey());
      } else {   
          
     System.out.println(" avlnode.add -> q.getKey " + q.getKey()+ " p.getkey " + p.getKey());
     
       if( q.getKey().compareTo(p.getKey()) < 0 ) {    
            if(p.getLeft()==null) {
                 p.setLeft(q);
                 q.setRoot(p);
                 recursiveBalance(p);
            } else {
                 add(p.getLeft(),q);
            }

         } else if(q.getKey().compareTo(p.getKey())>0) {
            if(p.getRight()==null) {
                 p.setRight(q);
                 q.setRoot(p);     
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
     *
     * @param cur
     */
    public void recursiveBalance(AVLNode < T > cur) {
      setBalance(cur);
      int balance = cur.getBalance();
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
      if(cur.getRoot()!=null) {
           recursiveBalance(cur.getRoot());
      } else {
           super.setRoot(cur);
           System.out.println("------------ Balancing finished ----------------");
      }
    }

    /**
     *
     * @param k
     * @return
     */
    @Override
     public T delete(T k) {
        T aux_key = deleteAVL((AVLNode<T>)super.getRoot(), (T)k);
      return(aux_key);
    }

    /**
     *
     * @param p
     * @param q
     * @return
     */
    public T deleteAVL(AVLNode < T > p,T q) {
      if( p == null ) {
           return null;
      } else {
          System.out.println("Delete_avl p" + p.getKey() + "q " + q);
          if( p.getKey().compareTo( q ) > 0 )  {
                System.out.println("Delete_avl p>q" + p.getKey() + "q " + q);
                deleteAVL(p.getLeft(),q);
           } else if( p.getKey().compareTo( q ) < 0 ) {
                 System.out.println("Delete_avl p<q" + p.getKey() + "q " + q);
                deleteAVL(p.getRight(),q);
           } else if( p.getKey().compareTo( q ) ==0 ) {
                System.out.println("Delete_avl p=q" + p.getKey() + "q " + q);
                deleteNode(p);
           }
       return q;
      }
    }

    /**
     *
     * @param q
     */
    public void deleteNode(AVLNode < T > q) {
      AVLNode < T > r;
      if( q.getLeft()==null || q.getRight()==null ) {
           if( q.getRoot()==null ) {
                super.setRoot(null);
                q=null;
                return;
           }
           r = q;
      } else {
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
           super.setRoot(p);
      } else {
               if(r==r.getRoot().getLeft()) {
                r.getRoot().setLeft(p);
           } else {
                r.getRoot().setRight(p);
           }
           recursiveBalance(r.getRoot());
      }
      r = null;
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public boolean find(T key) {
     
     System.out.println("********************** avltree.find ************* " + key);
        return ( find((AVLNode<T>) super.getRoot(),key ) );
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
        if (r.getLeft() != null) {
            isleft = find(r.getLeft(), key);
        }
        if (r.getRight() != null) {
            isright = find(r.getRight(), key);
        }
        return ( isright || isleft );
    }
   
    /**
     *
     * @return
     */
    @Override
     public Iterator<T> getLeaves() {
        AVLNode<T> new_node = new AVLNode<>();
        ArrayList<T> keylist = new ArrayList<>();
        getLeaves(super.getRoot(), keylist);
        return (keylist.iterator());
    }

    @Override
    public void getLeaves(AVLNode<T> r, ArrayList<T> keylist) {
        if (r != null) {
            if (super.isLeaf(r)) {
                keylist.add(r.getKey());
            } else {
            }
            getLeaves(r.getLeft(), keylist);
            getLeaves(r.getRight(), keylist);
        }

    }

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
     *
     */
    @Override
     public void cutLeaves() {

        Iterator<T> it= this.getLeaves();        
        while(it.hasNext())
        {
            delete(it.next());
        }
    }

    /**
     *
     * @param n
     * @return
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
     *
     * @param n
     * @return
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
     * @param u
     * @return
     */
    public AVLNode<T> doubleRotateLeftRight(AVLNode < T > u) {
        u.setLeft( rotateLeft( u.getLeft()) );
        return rotateRight( u );
    }

    /**
     *
     * @param u
     * @return
     */
    public AVLNode<T> doubleRotateRightLeft( AVLNode < T > u ) {
        u.setRight( rotateRight( u.getRight()) );
        return rotateLeft( u );
    }

    /**
     *
     * @param q
     * @return
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

    private int maximum(int a, int b) {
      if(a>=b) {
           return a;
      } else {
           return b;
      }
    }

    /**
     *
     * @return
     */
    @Override
    public JPanel getPaint() {
        return new Graphic<>( this );
    }

    /**
     *
     * @return
     */
    @Override
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
     *
     * @return
     */
    @Override
    public Iterator<T> preOrder() {
    //    SimpleList<AVLNode<T>,T> l = new SimpleList<>();
        ArrayList<T> keylist = new ArrayList<T>();
        preOrder(super.getRoot(), keylist);
        return (keylist.iterator());
    }

    private void preOrder(AVLNode<T> r, ArrayList<T> keylist) {
        if (r != null) {
            keylist.add(r.getKey());
            preOrder(r.getLeft(), keylist);
            preOrder(r.getRight(), keylist);
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public AVLNode<T> createNode(T key){
        return new AVLNode<>(key);
    }
/*
 @Override
     public AVLNode < T > getRoot() {
        return (AVLNode<T>) super.getRoot();
    }

    public void setRoot(AVLNode < T > root) {
        this.setRoot(root);
    }*/

 
}