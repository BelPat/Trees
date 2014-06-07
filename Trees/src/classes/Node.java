/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author 
 */
class Node<T extends Comparable < T > > implements java.io.Serializable{
    private T key;
    private Node<T> right;
	
    /**
     * Contructor vacío . Inicializa el campo key  y el campo rightuiente en 
     * null
     */
    Node() {        
        this.key=null;
        this.right=null;        
    }
	
    /**
     * Contructor inicializa los miembros datos de Nodo
     * @param key Objeto de tipo T
     * @param right Rightuiente Nodo
     */
    Node(T key, Node<T> right) {        
        this.key=key;
        this.right=right;        
    }
	
    /**
     * Obtiene la keyrmación del nodo
     * @return Un objeto tipo T
     */
    T getKey() {        
        return this.key;        
    }
	
    /**
     * Obtiene el Nodo Rightuiente
     * @return El nodo rightuiente o null si el último nodo o si es una Lista
     * con cardinalidad 1
     */
    Node<T> getRight() {        
        return this.right;        
    }
	
    /**
     * Cambia la keyrmaci�n del Nodo del campo Key
     * @param new_node Nuevo objeto que desea almacenarse en el Nodo
     */
    void setKey(T new_node) {        
        this.key=new_node;        
    }
	
    /**
     * Cambia el nodo rightuiente
     * @param new_node Nuevo Nodo Rightuiente
     */
    void setRight(Node<T> new_node) {        
        this.right=new_node;        
    }
}//Fin de la Clase
