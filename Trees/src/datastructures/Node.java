package datastructures;

/**
 *
 * @author 
 */
class Node<T extends Comparable < T > > implements java.io.Serializable{
    private T key;
    private Node<T> right, left;

    Node() {        
        this.key = null;
        this.right = null;   
        this.left = null;   
       // this.root = null;  
    }
    
    Node(T key) {        
        this.key = key;
        this.right = null;   
        this.left = null;   
       // this.root = null;  
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
   /**
     * Obtiene el Nodo Root
     * @return El nodo root o null si el último nodo o si es una Lista
     * con cardinalidad 1
  
    Node<T> getRoot() {        
        return this.root;        
    }*/

    void setKey(T new_node) {        
        this.key = new_node;        
    }

    void setRight(Node<T> new_node) {        
        this.right = new_node;        
    }
   
    void setLeft(Node<T> new_node) {        
        this.left = new_node;        
    }

}//Fin de la Clase
