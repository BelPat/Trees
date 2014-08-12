/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Iterator;
import javax.swing.JPanel;


public class RBTree<T extends Comparable<T>> extends BasicTree<RBNode<T>, T>{

// La root se inicializa a empty.
private RBNode<T> empty = new RBNode<>();
//private RBNode<T> root = empty;


    /**
     * Crea un árbol con los nodes a empty
     *
     */
    public RBTree() {
        super.setRoot(empty);
        super.getRoot().setLeft(empty);
        super.getRoot().setRight(empty);
      /*  root.setLeft( empty );
        root.setRight( empty );
        root.setRoot ( empty );*/
    }
                  

    /**
     * Retorna la raíz del árbol
     *
     * @return un tipo de objeto de la clase RBNode<T> con la dirección de
     * memoria de la raíz del árbol
   
@Override
    public RBNode<T> getRoot() {
       
        return (RBNode<T>)super.getRoot();
    }

  */
 
    
    /**
     * Realiza una rotación a la getLeft()uierda de x
    * @param: x, El node que va a rotar hacia la getLeft()uierda
    * 
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
    }// fin leftRotacion(RBNode x)


  /**
     * Modifica el valor de numgetLeft() y numgetRight()
    * @param: x, El node que va a rotar hacia la getLeft()uierda
    * 
   */
    private void leftRotationModifyNum(RBNode<T> x){

    if (isEmpty(x.getLeft()) && isEmpty((RBNode) x.getRight().getLeft())){
        x.setNumLeft ( 0 );
        x.setNumRight( 0 );
        x.getRight().setNumLeft ( 1 );
    }

    else if (isEmpty(x.getLeft()) && !isEmpty(x.getRight().getLeft())){
        x.setNumLeft ( 0 );
        x.setNumRight(  1 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight() );
        x.getRight().setNumLeft ( 2 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight() );
    }

    else if (!isEmpty(x.getLeft()) && isEmpty(x.getRight().getLeft())){
        x.setNumRight(0); 
        x.getRight().setNumLeft ( 2 + x.getLeft().getNumLeft() + x.getLeft().getNumRight() );

    }
    else{
        x.setNumRight( 1 + x.getRight().getLeft().getNumLeft() +
        x.getRight().getLeft().getNumRight());
        x.getRight().setNumLeft ( 3 + x.getLeft().getNumLeft() + x.getLeft().getNumRight() +
        x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight() );
    }

    }// fin leftRotationModifyNum(RBNode x)

    
  /**
     * Realiza una rotación a la dereecha de y
    * @param: y, El node que va a rotar hacia la getRight()echa
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

}// finrightRotation(RBNode y)

  /**
     * Modifica el valor de numgetLeft() y numgetRight()
    * @param: y, El node que va a rotar hacia la getRight()echa
    * 
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
     * Dado T, se inserta este dato en un RBTree<T> y retorna si se ha hecho de forma correcta.
     * @param key, el dato que se desea add
     * @return true si dato ha sido insertado de forma correcta, false en caso contraio
     */
@Override
    public boolean add(T key) {
    
    System.out.println(" --- > rb.add " + key);
        
      //  findNode(key);
        if ( findNode(key)== null)    {
            add(new RBNode<>(key));
             return true;
        }
        else
            return false;
    }
    
// @param: z, El node que se va add en el árbol
// Inserta z en la posición adecuada del RBTree y modifica los valores numgetRight() y numgetLeft()
    
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

}// fin add(RBNode z)


// @param: z, el node insertado en el árbol y igual ha causado el incumplimiento de alguna 
//de las propiedades del RBTree
// Corrige el posible incumlimiento de las propiedades tras la inserción

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

public RBNode<T> minValue(RBNode<T> node){

while (!isEmpty(node.getLeft()))
    node = node.getLeft();
return node;
}// fin minValue(RBNode node)



// @param: x, a RBNode whose successor we must find
// @return: return's the node the with the next largest key
// from x.key
public RBNode<T> treeSuccessor(RBNode<T> x){

if (!isEmpty(x.getLeft()) )
return minValue(x.getRight());

RBNode<T> y = x.getRoot();

while (!isEmpty(y) && x == y.getRight()){
    x = y;
    y = y.getRoot( );
}

return y;
}// fin minValue(RBNode x)


// @param: v, T el valor que queremos delete del RBTree
// Borramos v del RBTree
@Override
public Comparable delete(T v){

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
}// fin delete(RBNode z)



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

}//fin fixNodeData()


// @param: x, BasicNode afectado tras delete el valor v
// Restaura las propiedades incompatibles tras delete un node

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
}// fin deleteModificarNum(RBNode x)




      /**
     * Retorna true si existe un dato en el árbol binario, o false en caso
     * contrario.
     * @param key
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
     * Retorna el BasicNode si existe un dato en el árbol binario, o null en caso
 contrario.     
     *  @param: key, T dato a find en el AbolRN
     * @return un RBNode ,si el dato está o null en caso contrario.
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



    // @param: node, el RBNode que queremos comprobar si es empty
    // @return: true si el node es igual a empty o false en caso contrario
    private boolean isEmpty(RBNode node){

        if (node.getKey()==null){
            return true;
        }else return false;
            
    //return node == empty;

    }// fin isEmpty(RBNode node)


     /**
     * Retorna un iterador con el recorrido preOrder del árbol binario
     *
     * @return un iterador de la clase Iterator de la java.util
     */
    public Iterator<T> preOrder() {
        SimpleList<RBNode<T>,T> l = new SimpleList<>();
        preOrder(super.getRoot(), l);
        return (l.iterator());

    }

    private void preOrder(RBNode<T> r, SimpleList<RBNode<T>,T> l) {
        if (r.getKey() != null) {
            l.addEnd(r.getKey());
            preOrder(r.getLeft(), l);
            preOrder(r.getRight(), l);

        }

    }
    
    /**
     * Dado un RBNode<T>, retorna si este es hoja o no.
     * @param x RBNode<T> del que se desea saber si es hoja
     * @return true si el node es una hoja, false en caso no existir
     * el dato
     */

    public boolean isLeaf(RBNode<T> x) {
       // return (x.getLeft().getKey() == null && x.getRight().getKey() == null);
        return (isEmpty(x.getRight()) && isEmpty(x.getLeft()));
    }

       
         /**
     * Elimina las hojas(nodes terminales) del árbol .
     */

@Override
    public void cutLeaves() {
         Iterator<T> it= this.getLeaves();
          while(it.hasNext())
          { delete(it.next());
          }
    }
    
    /**
     * @return retorna un nuevo GraphicRBTree
     */
    
      public JPanel getPaint() {
        return new Graphic<>(this);
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
        SimpleList<RBNode<T>,T> l = new SimpleList<>();
        getLeaves(super.getRoot(), l);
        return (l.iterator());
    }
        
    /**
     * 
     *  @param r RBNode<T>, node a recorrer hasta llegar a las hojas
     * @param l, lista que contendrá las hojas del RBTree
     */
    private void getLeaves(RBNode<T> r, SimpleList<RBNode<T>,T> l) {
       // if (r != null) {
         if (!isEmpty(r)) {
            if (isLeaf(r)) {
                l.addEnd(r.getKey());
            }
            getLeaves(r.getLeft(), l);
            getLeaves(r.getRight(), l);
        }
    }
    
    /*

// @return: return's the size of the tree
// Return's the # of nodes including the root which the RBTree
// rooted at root has.
public int size(){

// Return the number of nodes to the root's getLeft() + the number of
// nodes on the root's getRight() + the root itself.
return root.numgetLeft() + root.numgetRight() + 1;
}// fin size()

*/
    
    /**
     * 
     
     * Crea un árbol de la clase JTree. Esta clase permite dibujar el árbol
     * utuloizando una distribución de ficheros y directorios de un explorador de
     * archivos convencional
     *
     * @return un JTree con el árbol binario que se tiene creado
   
    public JTree darJTree() {
        DefaultMutableTreeNode x = new DefaultMutableTreeNode("ARBOL-VACIO");
        if (this.esVacio()) {
            return (new JTree(x));
        }
        return (new JTree(crearJtree(this.root, "root(T)->")));
    }
    
      protected DefaultMutableTreeNode crearJtree(RBNode<T> r, String msg) {

          
        if (esLeaf(r)) {
            return (new DefaultMutableTreeNode(msg + r.getKey().toString()));
        }
        DefaultMutableTreeNode x = new DefaultMutableTreeNode(msg + r.getKey().toString());
        if (r.getLeft()!= null) {
            x.add(crearJtree(r.getLeft(), "getLeft()->"));
        }
        if (r.getRight()!= null) {
            x.add(crearJtree(r.getRight(), "getRight()->"));
        }
        return x;

    }
      
     */
      /**
* Returns sorted list of keys greater than key. Size of list
* wulol not exceed maxReturned
* @param key Key to find for
* @param maxReturned Maximum number of results to return
* @return List of keys greater than key. List may not exceed maxReturned

    public List<T> getGreaterThan(T key, Integer maxReturned) {
        List<T> list = new ArrayList<T>();
        getGreaterThan(root, key, list);
        return list.subList(0, Math.min(maxReturned, list.size()));
    }


    private void getGreaterThan(RBNode<T> node, T key,
                                List<T> list) {
        if (isEmpty(node)) {
            return;
        } else if (node.key.compareTo(key) > 0) {
            getGreaterThan(node.getLeft(), key, list);
            list.add(node.key);
            getGreaterThan(node.getRight(), key, list);
        } else {
            getGreaterThan(node.getRight(), key, list);
        }
    }
*/
    
  
    
}// fin class RBTree