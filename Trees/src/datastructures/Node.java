/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author 
 */
class Node<T extends Comparable < T > > implements java.io.Serializable{
    private T key;
    private Node<T> right, left;
	
    /**
     * Contructor vacío . Inicializa el campo key  y el campo right en 
     * null
     */
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
	
    /**
     * Contructor inicializa los miembros datos de Nodo
     * @param key Objeto de tipo T
     * @param right Rightuiente Nodo
     */
    
        Node(T key, Node<T> right, Node<T> left) {        
        this.key = key;
        this.right = right;
        this.left = left;         
    }
       
            
    Node(T key, Node<T> right) {        
        this.key = key;
        this.right = right;
    
    }
        
      
 	
    /**
     * Obtiene la keyrmación del nodo
     * @return Un objeto tipo T
     */
    T getKey() {        
        return this.key;        
    }
	
    /**
     * Obtiene el Nodo Righ
     * @return El nodo righ o null si el último nodo o si es una Lista
     * con cardinalidad 1
     */
    Node<T> getRight() {        
        return this.right;        
    }
    
        /**
     * Obtiene el Nodo left
     * @return El nodo left o null si el último nodo o si es una Lista
     * con cardinalidad 1
     */
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
	
    /**
     * Cambia la keyrmaci�n del Nodo del campo Key
     * @param new_node Nuevo objeto que desea almacenarse en el Nodo
   */
    void setKey(T new_node) {        
        this.key = new_node;        
    }
	
    /**
     * Cambia el nodo righ
     * @param new_node Nuevo Nodo Righ
     */
    void setRight(Node<T> new_node) {        
        this.right = new_node;        
    }
    
    	
    /**
     * Cambia el nodo left
     * @param new_node Nuevo Nodo Righ
     */
    void setLeft(Node<T> new_node) {        
        this.left = new_node;        
    }

    
        /**
     * Cambia el nodo root
     * @param new_node Nuevo Nodo Righ
    
    void setRoot(Node<T> new_node) {        
        this.root = new_node;        
    } */
}//Fin de la Clase
