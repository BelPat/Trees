package datastructures;

/**
 *
 * @author BelPat
 */
class Node<T extends Comparable < T > > implements java.io.Serializable{
    private T key;
    private Node<T> right, left;

    Node() {        
        this.key = null;
        this.right = null;   
        this.left = null;   
    }
    
    Node(T key) {        
        this.key = key;
        this.right = null;   
        this.left = null;   
    }

    
    Node(T key, Node<T> right, Node<T> left) {        
        this.key = key;
        this.right = right;
        this.left = left;         
    }
       
            
    Node(T key, Node<T> right) {        
        this.key = key;
        this.right = right;
    
    } 
     T getKey() {        
        return this.key;        
    }

    Node<T> getRight() {        
        return this.right;        
    }

    Node<T> getLeft() {        
        return this.left;        
    }
 
    void setKey(T new_node) {        
        this.key = new_node;        
    }

    void setRight(Node<T> new_node) {        
        this.right = new_node;        
    }
   
    void setLeft(Node<T> new_node) {        
        this.left = new_node;        
    }
@Override
public boolean equals(Object o){
    if(o==null) return false;
    if(o==this) return true;
    if(!(o instanceof Node)) return false;
    Node p=(Node) o;
    if((key==null)?(p.key!=null): !key.equals(p.key)) return false;
    return true;
}
@Override
public int hashCode(){
    final int prim=31;
    int result=17;
    result = prim * result + Node.this.getKey().hashCode();
    return result;
    
}

}//Fin de la Clase
