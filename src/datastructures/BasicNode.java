package datastructures;

/**
 *
 * @author BelPat
 */
public class BasicNode<Node, T > {
    private T key;
    private Node right, left;

    public BasicNode() {        
        this.key = null;
        this.right = null;   
        this.left = null;   
    }
    
    public BasicNode(T key) {        
        this.key = key;
        this.right = null;   
        this.left = null;   
    }

    
    public BasicNode(T key, Node right, Node left) {        
        this.key = key;
        this.right = right;
        this.left = left;         
    }
       
            
    public BasicNode(T key, Node right) {        
        this.key = key;
        this.right = right;
    
    } 
    public T getKey() {        
        return this.key;        
    }

    public Node getRight() {        
        return this.right;        
    }

    public Node getLeft() {        
        return this.left;        
    }
 
    void setKey(T new_node) {        
        this.key = new_node;        
    }

    void setRight(Node new_node) {        
        this.right = new_node;        
    }
   
    void setLeft(Node new_node) {        
        this.left = new_node;        
    }
@Override
public boolean equals(Object o){
    if(o==null) return false;
    if(o==this) return true;
    if(!(o instanceof BasicNode)) return false;
    BasicNode p=(BasicNode) o;
    return !((key==null)?(p.key!=null): !key.equals(p.key));
}
@Override
public int hashCode(){
    final int prim=31;
    int result=17;
    result = prim * result + BasicNode.this.getKey().hashCode();
    return result;
    
}
public boolean getNode(){
        if (this != null) return false;else {
            return true;
        }
}

}//Fin de la Clase
