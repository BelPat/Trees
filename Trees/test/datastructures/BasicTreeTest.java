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
public class BasicTreeTest {
    
    /**
     * Test of getRaiz method, of class BasicTree.
     */
    @Test
    public void testGetRaiz() {
        BasicTree<Integer> instance = new BasicTree<> ();
        BTree<Integer> arbol = new BTree<> ();
  
        arbol.add( 4 );
        arbol.add( 2 );
        arbol.add( 6 );
        arbol.add( 1 );
        arbol.add( 3 );
        arbol.add( 5 );
        arbol.add( 8 );
        arbol.add( 7 );
        arbol.add( 9 );
        
        BNode<Integer> expResult = new BNode<>( 4 );
        BNode<Integer> result = arbol.getRoot();
        assertEquals( expResult.getKey(), result.getKey() );
    }
     /**
     * Test of setRaiz method, of class BasicTree.
     */
    @Test
    public void testSetRaiz() {
        BasicTree<Integer> instance = new BasicTree<> ();
        BNode<Integer> expResult = new BNode<>( 4 );
        instance.setRoot(expResult);
        
        assertEquals(expResult, instance.getRoot());
    }

     /**
     * Test of getHojas method, of class BasicTree.
     */
    @Test
    public void testGetHojas() {
        BTree<Integer> instance = new BTree<>();
        
        instance.add( 13 );
        instance.add( 8 );
        instance.add( 1 );
        instance.add( 17 );
        instance.add( 15 );
        instance.add( 25 );
        instance.add( 6 );
        instance.add( 11 );
        instance.add( 22 );
        instance.add( 27 );

        SimpleList<Integer>  l = new SimpleList<> ();
        l.addEnd( 6 );
        l.addEnd( 11 );
        l.addEnd( 15 );
        l.addEnd( 22 );
        l.addEnd( 27 );        

       Iterator expResult = l.iterator();
       Iterator result = instance.getLeaves();
        
       boolean iguales=true;
       Integer a;
       Integer b;
       
       while(result.hasNext())  {
            a = (Integer) result.next();
            b = (Integer) expResult.next();
            if( !a.equals(b) )
                iguales=false;
        }
        
        assertTrue(iguales);
    }
    
        /**
     * Test of esta method, of class BasicTree.
     */
    @Test
    public void testBuscar() {
        Integer info = 17;
        BTree<Integer> instance = new BTree<> ();
        
        instance.add( 14 );
        instance.add( 15 );
        instance.add( 4 );
        instance.add( 9 );
        instance.add( 3 );
        instance.add( 18 );
        instance.add( 20 );
        instance.add( 16 );
        instance.add( 7 );
        instance.add( 5 );
        instance.add( 17 );
        
        boolean result = instance.find( info );
        
        assertTrue(result);
    }
    
    /**
     * Test of esVacio method, of class BasicTree.
     */
    @Test
    public void testEsVacio() {
        BasicTree<Integer> instance = new BasicTree<> ();
    
        boolean result = instance.isEmpty();
        assertTrue(result);
    }

        /**
     * Test of getPadre method, of class BasicTree.
     */
    @Test
    public void testGetPadre() {
        Integer info = 7;
        Integer expResult = 8;

        BTree<Integer> instance = new BTree<>();
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
     * Test of preOrden method, of class BasicTree.
     */
    @Test
    public void testPreOrden() {
        BTree<Integer> instance = new BTree<>();
        
        instance.add( 14 );
        instance.add( 15 );
        instance.add( 4 );
        instance.add( 9 );
        instance.add( 3 );
        instance.add( 18 );
        instance.add( 20 );
        instance.add( 16 );
        instance.add( 7 );
        instance.add( 5 );
        instance.add( 17 );

        SimpleList<Integer> l = new SimpleList<>();
        
        l.addEnd( 14 );
        l.addEnd( 4 );
        l.addEnd( 3 );
        l.addEnd( 9 );
        l.addEnd( 7 );
        l.addEnd( 5 );
        l.addEnd( 15 );
        l.addEnd( 18 );
        l.addEnd( 16 );
        l.addEnd( 17 );
        l.addEnd( 20 );        

       Iterator expResult = l.iterator();
       Iterator result = instance.preOrder();
        
       boolean iguales=true;
       Integer a;
       Integer b;
       
       while(result.hasNext())   {
            a = (Integer)result.next();
            b = (Integer)expResult.next();
            
            if(!a.equals(b))
                iguales=false;
        }       

        assertTrue(iguales);
    }
}