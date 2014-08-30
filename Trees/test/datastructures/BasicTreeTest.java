/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;
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
        BTree<Integer> instance = new BTree<> ();
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

        ArrayList l = new ArrayList ();
        l.add( 6 );
        l.add( 11 );
        l.add( 15 );
        l.add( 22 );
        l.add( 27 );        

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
        BTree<Integer> instance = new BTree<> ();
    
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
        
        instance.add( new Integer(4) );
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

        ArrayList l = new ArrayList ();
        
        l.add( 14 );
        l.add( 4 );
        l.add( 3 );
        l.add( 9 );
        l.add( 7 );
        l.add( 5 );
        l.add( 15 );
        l.add( 18 );
        l.add( 16 );
        l.add( 17 );
        l.add( 20 );        

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