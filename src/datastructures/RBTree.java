/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author nusatres
 * @param <T>
 */
public class RBTree<T extends Comparable<T>> extends BasicTree<RBNode<T>, T>{

// La root se inicializa a empty.
private RBNode<T> empty = new RBNode<>();


    /**
     * Construye un árbol con los nodos vacíos.
     */
    public RBTree() {
        super.setRoot(empty);
        super.getRoot().setLeft(empty);
        super.getRoot().setRight(empty);

    }
 
    /**
     * Realiza una rotación a la izquierda de x
     * 
     * @param: x, El node que va a rotar hacia la izquierda    
     */
    private void leftRotacion(RBNode<T> x){

        leftRotationModifyNum(x);

        RBNode<T> y;
        y = x.getRight();
        x.setRight( y.getLeft() );

        if (!isEmpty(y.getLeft()))
            y.getLeft().setRoot(x);

        y.setRoot(x.getRoot());

        if (isEmpty(x.getRoot())){
            super.setRoot(y);    
        } else if (x.getRoot().getLeft() == x){
            x.getRoot().setLeft(y);
        } else {
            x.getRoot().setRight(y);
        }

        y.setLeft(x);
        x.setRoot(y);
    }

    /**
     * Modifica los números de los hijos de nodo dado
     * 
     * @param x nodo dado
     */
    private void leftRotationModifyNum(RBNode<T> x){

    if (isEmpty(x.getLeft()) && isEmpty((RBNode) x.getRight().getLeft())){
        x.setNumLeft ( 0 );
        x.setNumRight( 0 );
        x.getRight().setNumLeft ( 1 );
    } else if (isEmpty(x.getLeft()) && !isEmpty(x.getRight().getLeft())){
        x.setNumLeft ( 0 );
        x.setNumRight(  1 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight() );
        x.getRight().setNumLeft ( 2 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight() );
    }  else if (!isEmpty(x.getLeft()) && isEmpty(x.getRight().getLeft())){
        x.setNumRight(0); 
        x.getRight().setNumLeft ( 2 + x.getLeft().getNumLeft() + x.getLeft().getNumRight() );

    }
    else{
        x.setNumRight( 1 + x.getRight().getLeft().getNumLeft() +
        x.getRight().getLeft().getNumRight());
        x.getRight().setNumLeft ( 3 + x.getLeft().getNumLeft() + x.getLeft().getNumRight() +
        x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight() );
    }

    }
 
    /**
     * Realiza una rotación a la dereecha de y.
     * 
     * @param y El node que va a rotar hacia la derecha
     * 
     */
    private void rightRotation(RBNode<T> y){
        rightRotationModifyNum(y);

        RBNode<T> x = y.getLeft();
        y.setLeft( x.getRight() );

        if ( !isEmpty ( x.getRight( ) ) )
            x.getRight().setRoot( y );
        x.setRoot( y.getRoot( ) ); 

        if ( isEmpty( y.getRoot( ) ) )
            super.setRoot(x);
        else if ( y.getRoot( ).getRight( ) == y )
            y.getRoot( ).setRight(x); 
        else
            y.getRoot( ).setLeft( x );
        
        x.setRight( y );
        y.setRoot( x );

    }

     /**
     * Modifica los números de los hijos de nodo dado
     * 
     * @param y nodo dado
     */
    private void rightRotationModifyNum(RBNode<T> y){

        if (isEmpty(y.getRight()) && isEmpty(y.getLeft().getRight())){
            y.setNumRight( 0 );
            y.setNumLeft ( 0 );
            y.getLeft().setNumRight( 1 );
        } else if (isEmpty(y.getRight()) && !isEmpty(y.getLeft().getRight())){
            y.setNumRight( 0 );
            y.setNumLeft ( 1 + y.getLeft().getRight().getNumRight() +   y.getLeft().getRight().getNumLeft() );
            y.getLeft().setNumRight( 2 + y.getLeft().getRight().getNumRight()+
            y.getLeft().getRight().getNumLeft());
        } else if (!isEmpty(y.getRight()) && isEmpty(y.getLeft().getRight())){
            y.setNumLeft ( 0 );
            y.getLeft().setNumRight( 2 + y.getRight().getNumRight() +y.getRight().getNumLeft() );

        } else {
            y.setNumLeft ( 1 + y.getLeft().getRight().getNumRight() +  y.getLeft().getRight().getNumLeft() );
            y.getLeft().setNumRight( 3 + y.getRight().getNumRight() +  y.getRight().getNumLeft() +   y.getLeft().getRight().getNumRight() + y.getLeft().getRight().getNumLeft() );
        }

        }// finrightRotationModificarNum(RBNode y)

    /**
     * Dado T, se inserta este dato en un árbol
     * 
     * @param key, el dato que se desea add
     * @return true si dato ha sido insertado de forma correcta, false en caso contraio
     */
    @Override
    public boolean add(T key) {
       if ( findNode(key)== null)    {
            add(new RBNode<>(key));
             return true;
        }
        else
            return false;
    }
    
    /**
     * Busca el nodo padre al que insertarle el nodo y modifica 
     * los valores numgetRight() y numgetLeft()
     * 
     * @param: z, El node que se va add en el árbol
    */    
    private void add(RBNode<T> z) {

        RBNode<T> y = empty;
        RBNode<T> x = (RBNode<T>) super.getRoot();

        while (!isEmpty(x)){
            y = x;

            if ( ( z.getKey() ).compareTo( x.getKey() ) < 0){
                x.setNumLeft( x.getNumLeft() + 1 );
                x = x.getLeft();
            } else {
                x.setNumRight( x.getNumRight() + 1 );
                x = x.getRight();
            }
        }
        z.setRoot(y);


        if (isEmpty(y)){
            super.setRoot(z);
        } else if ((z.getKey()).compareTo(y.getKey()) < 0){
            y.setLeft( z );
        }else{
            y.setRight( z );
        }

        z.setLeft( empty );
        z.setRight( empty );
        z.setColor( RBNode.RED );

        addModifyNum(z);

    }


    /**
     * Corrige el posible incumpimiento de las propiedades de inserción 
     * 
     * @param: z, El node que se va ha añadir en el árbol
    */ 
private void addModifyNum( RBNode<T> z ){

RBNode<T> y = empty;
while ( z.getRoot().getColor() == RBNode.RED ){

if (z.getRoot() == z.getRoot().getRoot().getLeft()){

    y = z.getRoot().getRoot().getRight();
    if (y.getColor() == RBNode.RED){
        z.getRoot().setColor( RBNode.BLACK );
        y.setColor( RBNode.BLACK );
        z.getRoot().getRoot().setColor( RBNode.RED );
        z = z.getRoot().getRoot();
    } else if (z == z.getRoot( ).getRight()){

        z = z.getRoot( );
        leftRotacion(z);
    } else{

        z.getRoot( ).setColor( RBNode.BLACK );
        z.getRoot( ).getRoot( ).setColor( RBNode.RED );
       rightRotation(z.getRoot( ).getRoot( ));
    }
}

else{

    y = z.getRoot( ).getRoot( ).getLeft();

    if (y.getColor( ) == RBNode.RED){
        z.getRoot( ).setColor( RBNode.BLACK );
        y.setColor( RBNode.BLACK );
        z.getRoot( ).getRoot( ).setColor( RBNode.RED );
        z = z.getRoot( ).getRoot( );
    } else if (z == z.getRoot( ).getLeft()){
        z = z.getRoot( );
       rightRotation(z);
    } else{
        z.getRoot( ).setColor( RBNode.BLACK );
        z.getRoot( ).getRoot( ).setColor( RBNode.RED );
        leftRotacion(z.getRoot( ).getRoot( ));
    }
}
}   
((RBNode<T>)super.getRoot()).setColor( RBNode.BLACK );

}// fin addModifyNum(RBNode z)

// @param: node, un RBNode<T>
// @return: node, el node de menor valor

    /**
     * Retorna del nodo de menor valor de árbol
     * @param node nodo dado
     * @return nodo de menor valor
     */
    
public RBNode<T> minValue(RBNode<T> node){

    while (!isEmpty(node.getLeft()))
        node = node.getLeft();
    return node;
}

    /**
     * Devuelve el sucesor de un nodo dado en el árbol 
     * 
     * @param x nodo predecesor 
     * @return el sucesor del nodo x
     */ 
    public RBNode<T> treeSuccessor(RBNode<T> x){

    if (!isEmpty(x.getLeft()) )
    return minValue(x.getRight());

    RBNode<T> y = x.getRoot();

    while (!isEmpty(y) && x == y.getRight()){
        x = y;
        y = y.getRoot( );
    }

    return y;
}

    /**
     * Borra un nodo del árbol
     * @param v valor que queremos borrar
     * @return el valor borrado
     */
    @Override
public T delete(T v){

    RBNode<T> z = findNode(v);
    RBNode<T> x = empty;
    RBNode<T> y = empty;

    if (isEmpty(z.getLeft()) || isEmpty(z.getRight())){
        y = z;
    } else {
        y = treeSuccessor(z);
    }

    if (!isEmpty(y.getLeft())){
        x = y.getLeft();
    } else {
        x = y.getRight();
    }

    x.setRoot( y.getRoot( ) );

    if (isEmpty(y.getRoot( ))){
        super.setRoot(x);
    } else if (!isEmpty(y.getRoot( ).getLeft()) && y.getRoot( ).getLeft() == y){
        y.getRoot( ).setLeft( x );
    } else if (!isEmpty(y.getRoot( ).getRight()) && y.getRoot( ).getRight() == y){
        y.getRoot( ).setRight( x );
    }

    if (y != z){
        z.setKey(y.getKey());
    }

    fixNodeData(x,y);

    if (y.getColor() == RBNode.BLACK){
        deleteModificarNum(x);
    }
    return v;
}

    /**
     * Actualiza los valores después de eliminar un nodo del árbol
     * @param x
     * @param y 
     */
private void fixNodeData(RBNode<T> x, RBNode<T> y){

    RBNode<T> current = empty;
    RBNode<T> aux = empty;

    if (isEmpty(x)){
        current = y.getRoot( );
        aux = y;
    }else{
        current = x.getRoot( );
        aux = x;
    }
    int compara;

    while (!isEmpty(current)){

        if (y.getKey() != current.getKey()) {
             compara=(y.getKey()).compareTo(current.getKey());
            if (compara > 0) {
                current.setNumRight(current.getNumRight()-1);
            }
            //if (y.key.compareTo(current.key) < 0)
            if (compara < 0){
                current.setNumLeft(current.getNumLeft()-1);
            }
        }else{
            if (isEmpty(current.getLeft())){
                current.setNumLeft(current.getNumLeft()-1);
            } else if (isEmpty(current.getRight())){
                current.setNumRight(current.getNumRight()-1);
            } else if (aux == current.getRight()){            
                current.setNumRight(current.getNumRight()-1);
            } else if (aux == current.getLeft()){
                current.setNumLeft(current.getNumLeft()-1);
            }
        }

        aux = current;
        current = current.getRoot( );

    }

}


  /**
     * Corrige el posible incumpimiento de las propiedades de borrar 
     * 
     * @param: z, nodo afectado después de eliminar
    */ 

private void deleteModificarNum(RBNode<T> x){

    RBNode<T> w;

    while (x != super.getRoot() && x.getColor( ) == RBNode.BLACK){

    if (x == x.getRoot( ).getLeft()){

        w = x.getRoot( ).getRight();

        if (w.getColor () == RBNode.RED){
            w.setColor( RBNode.BLACK );
            x.getRoot( ).setColor( RBNode.RED );
            leftRotacion(x.getRoot( ));
            w = x.getRoot( ).getRight();
        }

        if (w.getLeft().getColor () == RBNode.BLACK &&
            w.getRight().getColor () == RBNode.BLACK){
            w.setColor( RBNode.RED );
            x = x.getRoot( );
        } else{
               if (w.getRight().getColor () == RBNode.BLACK){
                    w.getLeft().setColor( RBNode.BLACK );
                    w.setColor( RBNode.RED );
                   rightRotation(w);
                    w = x.getRoot( ).getRight();
               }
                w.setColor( x.getRoot( ).getColor () );
                x.getRoot( ).setColor( RBNode.BLACK );
                w.getRight().setColor( RBNode.BLACK );
                leftRotacion(x.getRoot( ));
                x = (RBNode<T>) super.getRoot();
        }
    }else{
        w = x.getRoot( ).getLeft();
        if (w.getColor () == RBNode.RED){
            w.setColor( RBNode.BLACK );
            x.getRoot( ).setColor( RBNode.RED );
           rightRotation(x.getRoot( ));
            w = x.getRoot( ).getLeft();
       }
        if (w.getRight().getColor () == RBNode.BLACK &&
            w.getLeft().getColor () == RBNode.BLACK){
            w.setColor( RBNode.RED );
            x = x.getRoot( );
        }   else{
            if (w.getLeft().getColor () == RBNode.BLACK){
                w.getRight().setColor( RBNode.BLACK );
                w.setColor( RBNode.RED );
                leftRotacion(w);
                w = x.getRoot( ).getLeft();
            }
            w.setColor( x.getRoot( ).getColor () );
            x.getRoot( ).setColor( RBNode.BLACK );
            w.getLeft().setColor( RBNode.BLACK );
           rightRotation(x.getRoot( ));
            x = (RBNode<T>) super.getRoot();
            }
        }
    }// fin while

    x.setColor( RBNode.BLACK );
}
      /**
     * Retorna true si existe un dato en el árbol binario, o false en caso
     * contrario.
     *      
     * @param key valor a buscar
     * @return un boolean , true si el dato está o false en caso contrario.
     */
@Override
    public boolean find(T key){

        RBNode<T> r = (RBNode<T>) super.getRoot();

        int compara;

        while (!isEmpty(r)){
            compara=r.getKey().compareTo(key);
           if (compara==0){
                return true;
           }else if (compara < 0) {
                r = r.getRight();
            }else {
                 r = r.getLeft();
            }
        }
        return false;
    }// fin find( key)

     /**
     * Retorna el Nodo si existe el dato en el árbol o null en caso contrario.    
     * 
     * @param key valor a buscar
     * @return un Nodo ,si el dato está o null en caso contrario.
     */
    public RBNode<T> findNode(T key){
        RBNode<T> current = new RBNode<>(  super.getRoot());

        int compara;

        while (!isEmpty(current)){
            compara=current.getKey().compareTo(key);

            if (compara==0){
                 return current;
            }else if (compara < 0){
                current = current.getRight();
            }else{
                 current = current.getLeft();
            }
        }
        return null;

    }

    /**
     * Determina si el árbol es vacío.
     * 
     * @node node nodo dado
     * @return true si el árbol es vacío, false en caso contrario
     */
    private boolean isEmpty(RBNode node){
     return node.getKey()==null;
    }


     /**
     * Retorna un iterador con el recorrido preOrder del árbol binario
     *
     * @return un iterador de la clase Iterator de la java.util
     */
@Override
    public Iterator<T> preOrder() {
        ArrayList<T> keylist = new ArrayList<>();
        preOrder(super.getRoot(), keylist);
        return (keylist.iterator());

    }

    private void preOrder(RBNode<T> r, ArrayList<T> keylist) {
        if (r.getKey() != null) {
            keylist.add(r.getKey());
            preOrder(r.getLeft(), keylist);
            preOrder(r.getRight(), keylist);

        }

    }
    
    /**
     * Dado nodo retorna si este es hoja o no.
     * @param x nodo del que se desea saber si es hoja
     * @return true si el node es una hoja, false en caso no existir el dato
     */

@Override
    public boolean isLeaf(RBNode<T> x) {
       // return (x.getLeft().getKey() == null && x.getRight().getKey() == null);
        return (isEmpty(x.getRight()) && isEmpty(x.getLeft()));
    }

            
/**
     * Retorna el padre del dato pasado como parametro ese dato.
     *
     * @param key dato que se desea find
     * @return el padre del dato almacenado en el árbol, null en caso no existir
     * el dato
     */    
    
     private RBNode<T> getFather(RBNode<T> x, T key) {
        if (isEmpty(x)) {
            return null;
        }
        if ((x.getLeft().getKey()!= null && x.getLeft().getKey().equals(key)) || (x.getRight().getKey() != null && x.getRight().getKey().equals(key))) {
            return (x);
        }
        RBNode<T> y = getFather(x.getLeft(), key);
        if (y == null) {
            return (getFather(x.getRight(), key));
        } else {
            return (y);
        }
    }
     
     /**
     * Retorna un iterador con las hojas del árbol binario
     *
     * @return un iterador de la clase Iterator de la java.util
     */

@Override
    public Iterator<T> getLeaves() {
        ArrayList<T> keylist = new ArrayList<>();
        getLeaves(super.getRoot(), keylist);
        return (keylist.iterator());
    }
        
    /**
     * Busca todas la hojas del árbol de forma recursiva.
     * 
     *  @param r  nodo a recorrer hasta llegar a las hojas
     * @param keylist
     */
@Override
    public void getLeaves(RBNode<T> r, ArrayList<T> keylist) {
         if (!isEmpty(r)) {
            if (isLeaf(r)) {
                keylist.add(r.getKey());
            }
            getLeaves(r.getLeft(), keylist);
            getLeaves(r.getRight(), keylist);
        }
    }
    
 
    
}// fin class RBTree