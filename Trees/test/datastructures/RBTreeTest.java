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
public class RBTreeTest {
    
        /**
     * Test of getRaiz method, of class RBTree.
     */
    @Test
    public void testGetRaiz() {
        RBTree<Integer> instance = new RBTree<> ();
        
        instance.add( 38 );
        instance.add( 51 );
        instance.add( 10 );
        instance.add( 12 );
        instance.add( 40 );
        instance.add( 84 );
        instance.add( 25 );  
        
        Integer expResult =  38;
        RBNode<Integer> result = instance.getRoot();
        assertEquals( expResult, result.getKey() );
    }
    
    
    /**
     * Test of getHojas method, of class RBTree.
     */
    @Test
    public void testGetHojas() {
        RBTree<Integer>  instance = new RBTree<> ();
        instance.add( 38 );
        instance.add( 51 );
        instance.add( 10 );
        instance.add( 12 );
        instance.add( 40 );
        instance.add( 84 );
        instance.add( 25 );        
                
         ArrayList l = new ArrayList ();
        l.add( 10 );
        l.add( 25 );
        l.add( 40 );
        l.add( 84 );        
        
        Iterator expResult = l.iterator();
        Iterator result = instance.getLeaves();
        
       boolean iguales=true;
       Integer a;
       Integer b;
       
       while( result.hasNext() )       {
            a = (Integer) result.next();
            b = (Integer) expResult.next();
            if(!a.equals(b))
                iguales=false;
        }        

        assertTrue(iguales);
    }

       /**
     * Test of buscar method, of class RBTree.
     */
    @Test
    public void testBuscar() {
        Integer info = 12;
        
        RBTree<Integer> instance = new RBTree<> ();
        instance.add( 38 );
        instance.add( 13 );
        instance.add( 51 );
        instance.add( 10 );
        instance.add( 12 );
        instance.add( 40 );
        instance.add( 84 );
        instance.add( 25 );

        boolean result = instance.find( info );
        
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }
             
        /**
     * Test of padre method, of class RBTree.
     */
    @Test
    public void testGetPadre() {
        Integer info = 13;
        Integer expResult = 12;
        RBTree<Integer> instance = new RBTree<>();
        instance.add( 38 );
        instance.add( 13 );
        instance.add( 51 );
        instance.add( 10 );
        instance.add( 12 );
        instance.add( 40 );
        instance.add( 84 );
        instance.add( 25 );

        Object result = instance.getFather( info );
        assertEquals(expResult, result);
    }
    
        /**
     * Test of preOrden method, of class RBTree.
     */
    @Test
    public void testPreOrden() {
        RBTree<Integer> instance = new RBTree<> ();
        instance.add( 4 );
        instance.add( 2 );
        instance.add( 6 );
        instance.add( 1 );
        instance.add( 3 );
        instance.add( 5 );
        instance.add( 8 );
        instance.add( 7 );
        instance.add( 9 );

        ArrayList l = new ArrayList ();
        l.add( 4 );
        l.add( 2 );
        l.add( 1 );
        l.add( 3 );
        l.add( 6 );
        l.add( 5 );
        l.add( 8 );
        l.add( 7);
        l.add( 9);
        
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

