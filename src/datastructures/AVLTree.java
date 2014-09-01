package datastructures;
 
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author BelPat
 * @param <T>
 */
public class AVLTree<T extends Comparable<T>> extends BasicTree<AVLNode<T>, T>{
  
    /**
     * Construye un árbol AVLTree vacío.
     */
    public AVLTree() {
       super();
    }                              
             
    /**
     * Inserta la clave k en el árbol
     * 
     * @param k la clave del nodo
     * @return true si se ha insertado correctamente
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
     * Busca el nodo padre al que insertarle el nodo 
     * y llama al método para que lo añada y a las operaciones necesarias
     * para mantener el balanceo
     * 
     * @param p nodo actual que se esta consultando 
     * @param q nodo a insertar
     * @return true si se ha insertado correctamente
     */
    public boolean add(AVLNode < T > p, AVLNode < T > q) {
      // If  node to compare is null, the node is inserted. If the root is null, it is the root of the tree.
      if(p==null) {
           super.setRoot(q);
      } else {    
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
     * Comprueba el balanceo para cada nodo de forma recursiva y
     * llama a los métodos necesarios para hacer el balanceo del árbol 
     * hasta llegar a la raiz
     * 
     * @param cur nodo actual
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
      }
    }

    /**
     * Borra un nodo del árbol
     * 
     * @param k es la clave del nodo a borrar
     * @return la clave del nodo borrado
     */
    @Override
     public T delete(T k) {
        T aux_key = deleteAVL((AVLNode<T>)super.getRoot(), (T)k);
      return(aux_key);
    }

    /**
     * Busca el nodo a borrar y llama al método para que lo elimine
     * 
     * @param p nodo actual que se esta consultando 
     * @param q es la clave del nodo a borrar
     * @return la clave del nodo borrado
     */
    public T deleteAVL(AVLNode <T> p,T q) {
      if( p == null ) {
           return null;
      } else {
          System.out.println("Comparar clave a borrar" + p.getKey() + " y nodo actual" + q);
          if( p.getKey().compareTo( q ) > 0 )  {
                System.out.println("La clave es mayor");
                deleteAVL(p.getLeft(),q);
           } else if( p.getKey().compareTo( q ) < 0 ) {
                 System.out.println("La clave es menor");
                deleteAVL(p.getRight(),q);
           } else if( p.getKey().compareTo( q ) ==0 ) {
                System.out.println("El nodo actual es el que queremos borra");
                deleteNode(p);
           }
       return q;
      }
    }

    /**
     * Borra el nodo del árbol y llama las operaciones necesarias
     * para mantener el balanceo
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
     * Busca el nodo en el árbol
     * 
     * @param key clave del nodo
     * @return true si la clave está en el árbol, false en caso contrario
     */
    @Override
    public boolean find(T key) {
        return ( find((AVLNode<T>) super.getRoot(),key ) );
    }
    
     /**
     * Busca el nodo en el árbol de forma recursiva
     * 
     * @param key clave del nodo
     * @return true si la clave está en el árbol, false en caso contrario
     */
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
     * Retorna un interador de todas las hojas del árbol.
     * 
     * @return el interador de todas las hojas del árbol
     */
    @Override
     public Iterator<T> getLeaves() {
        AVLNode<T> new_node = new AVLNode<>();
        ArrayList<T> keylist = new ArrayList<>();
        getLeaves(super.getRoot(), keylist);
        return (keylist.iterator());
    }

    /**
     * Busca todas la hojas del árbol de forma recursiva.
     * 
     * @param r nodo dado
     * @param keylist lista de hojas
     */     
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

    /**
     * Realiza la rotación a la izquierda del nodo dado y llama a las operaciones
     * necesarias para mantener el balance del árbol
     * 
     * @param n nodo a aplicar la rotación
     * @return nodo resultante de la rotación
     */
    public AVLNode<T> rotateLeft(AVLNode <T> n) {
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
     * Realiza la rotación a la derecha del nodo dado y llama a las operaciones
     * necesarias para mantener el balance del árbol
     * 
     * @param n nodo a aplicar la rotación
     * @return nodo resultante de la rotación
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
     * Realiza la rotación a la izquierda y a la derecha del nodo dado 
     * 
     * @param n nodo dado 
     * @return nodo resultante de la rotación
     */
    public AVLNode<T> doubleRotateLeftRight(AVLNode < T > n) {
        n.setLeft( rotateLeft( n.getLeft()) );
        return rotateRight( n );
    }

    /**
     * Realiza la rotación a la derecha y a la izquierda del nodo dado 
     * 
     * @param n nodo dado 
     * @return nodo resultante de la rotación
     */
    public AVLNode<T> doubleRotateRightLeft( AVLNode < T >  n) {
        n.setRight( rotateRight( n.getRight()) );
        return rotateLeft( n );
    }

    /**
     * Devuelve el sucesor de un nodo dado en el árbol de forma recursiva. 
     * 
     * @param q nodo predecesor 
     * @return el sucesor del nodo q
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
     * Devuelve la altura de un nodo dado. 
     * 
     * @param q nodo dado 
     * @return la altura del nodo, retorna nulo si no existe el nodo
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
     * Retorna el valor maximo entre el primer valor y el segundo valor.
     * 
     * @param a primer valor
     * @param b segundo valor
     * @return el valor maximo
     */
    private int maximum(int a, int b) {
      if(a>=b) {
           return a;
      } else {
           return b;
      }
    }

    /**
     * Calcula el balanceo del nodo dado.
     * 
     * @param cur nodo dado
     */
    private void setBalance(AVLNode < T > cur) {
       int heightleft = 0;
       int heightright = 0;
       
       if(cur.getLeft()!= null){
        System.out.println("Calculando balanceo del nodo izquierdo" + cur.getLeft().getKey());
        heightleft = height(cur.getLeft()); 
       }
        if(cur.getRight()!= null)        {
           System.out.println("Calculando balanceo del nodo derecho" + cur.getRight().getKey());
           heightright = height(cur.getRight());
           
        }       
        cur.setBalance( heightright - heightleft);
    }

    @Override
    public Iterator<T> preOrder() {
        ArrayList<T> keylist = new ArrayList<>();
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

}