/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nusatres
 */
public class AVLTreeTest {
    
       /**
     * Test of getRoot method, of class AVLTree.
     */
    @Test
    public void testGetRoot() {
        AVLTree<Integer> instance = new AVLTree<> ();
        
        instance.add( 4 );
        instance.add( 2 );
        instance.add( 6 );
        instance.add( 1 );
        instance.add( 3 );
        instance.add( 5 );
        instance.add( 8 );
        instance.add( 7 );
        instance.add( 9 );
        
        Integer expResult = new Integer( 4 );
        AVLNode<Integer> result = instance.getRoot();
        assertEquals(expResult, result.getKey());
    }

        /**
     * Test of setRoot method, of class AVLTree.
     */
    @Test
    public void testSetRoot() {
        AVLNode<Integer> raiz = new AVLNode<> ( 23 );
        AVLTree<Integer> instance = new AVLTree<> ();
        
        instance.setRoot(raiz);
        assertEquals(raiz, instance.getRoot());
    }
    
        /**
     * Test of getHojas method, of class AVLTree.
     */
    @Test
    public void testGetHojas() {
        AVLTree<Integer> instance = new AVLTree<> ();
        
        instance.add( 13 );
        instance.add( 8 );
        instance.add( 1 );
        instance.add( 17 );
        instance.add( 15 );
        instance.add( 25 );
        instance.add( 6 );
        instance.add( 11 );
        instance.add( 22 );
        instance.add( 27);
        
        SimpleList<Integer> l = new SimpleList<>();
         
        l.addEnd( 6 );
        l.addEnd( 11 );
        l.addEnd( 17 );
        l.addEnd( 27 );
        
       Iterator expResult = l.iterator();
       Iterator result = instance.getLeaves();
        
       boolean iguales=true;
       Integer a;
       Integer b;
       
       while(result.hasNext())
        {
            a = (Integer) result.next();
            b = (Integer) expResult.next();
            if( !a.equals(b) )
                iguales=false;
        }        

        assertTrue(iguales);
    }
    
     /**
     * Test of buscar method, of class AVLTree.
     */
    @Test
    public void testBuscar() {
        AVLTree<Integer> instance = new AVLTree<> ();
        
        instance.add( 4 );
        instance.add( 2 );
        instance.add( 6 );
        instance.add( 1 );
        instance.add( 3 );
        instance.add( 5 );
        instance.add( 8 );
        instance.add( 7 );
        instance.add( 9 );

        boolean result = instance.find( 9 );
        
        assertTrue(result);
    }
    
        /**
     * Test of esVacio method, of class AVLTree.
     */
    @Test
    public void testEsVacio() {
        AVLTree<Integer> instance = new AVLTree<> ();
        boolean result = instance.isEmpty();
        assertTrue(result);
    }
    
    
    /**
     * Test of getPadre method, of class AVLTree.
     */
    @Test
    public void testGetPadre() {
        Integer info =7;
        Integer expResult = 8;

        AVLTree<Integer> instance = new AVLTree<> ();
        
        instance.add( 4 );
        instance.add( 2 );
        instance.add( 6 );
        instance.add( 1 );
        instance.add( 3 );
        instance.add( 5 );
        instance.add( 8 );
        instance.add( 7 );
        instance.add( 9 );
        
        Object result = instance.getFather( info );
        assertEquals(expResult, result);
    }

       /**
     * Test of preOrden method, of class AVLTree.
     */
    @Test
    public void testPreOrden() {
        AVLTree<Integer> instance = new AVLTree<> ();
        
        instance.add( 4 );
        instance.add( 2 );
        instance.add( 6 );
        instance.add( 1 );
        instance.add( 3 );
        instance.add( 5 );
        instance.add( 8 );
        instance.add( 7 );
        instance.add( 9 );

        
        SimpleList<Integer> l = new SimpleList<> ();
        
        l.addEnd( 4 );
        l.addEnd( 2 );
        l.addEnd( 1 );
        l.addEnd( 3 );
        l.addEnd( 6 );
        l.addEnd( 5 );
        l.addEnd( 8 );
        l.addEnd( 7 );
        l.addEnd( 9 );
         
        Iterator expResult = l.iterator();
        Iterator result = instance.preOrder();
        boolean iguales=true;
        Integer a;
        Integer b;
        
        while(result.hasNext())
        {
            a = (Integer) result.next();
            b = (Integer) expResult.next();
            if(!a.equals(b))
                iguales=false;
        }        

        assertTrue(iguales);
    }
    
}