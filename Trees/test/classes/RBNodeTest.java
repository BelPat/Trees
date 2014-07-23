/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nusatres
 */
public class RBNodeTest {
    
    /**
     * Test of setNumLeft method, of class RBNode.
     */
    @Test
    public void testSetNumLeft() {
        int numLeft = 0;
        RBNode<Integer> instance = new RBNode<>();
        instance.setNumLeft(numLeft);
        // TODO review the generated test code and remove the default call to fail.
         assertEquals(instance.getNumLeft(), numLeft);
    }

    /**
     * Test of setNumRight method, of class RBNode.
     */
    @Test
    public void testSetNumRight() {
        int numRight = 0;
        RBNode<Integer> instance = new RBNode<> ();
        instance.setNumRight(numRight);
        
        assertEquals( instance.getNumRight(), numRight );
    }

    /**
     * Test of getKey method, of class RBNode.
     */
    @Test
    public void testGetKey() {
        RBNode<Integer>  instance = new RBNode<>( 12 );
        Integer expResult = 12;
        Integer result = instance.getKey();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getLeft method, of class RBNode.
     */
    @Test
    public void testGetLeft() {
        RBNode<Integer> instance = new RBNode<>( );
        RBNode<Integer> left = new RBNode<>( 13 );
        instance.setLeft(left);
        Integer expResult = 13;
        Integer result = instance.getLeft().getKey();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getRight method, of class RBNode.
     */
    @Test
    public void testGetRight() {
        RBNode<Integer>  instance = new RBNode<>( );
        Integer expResult = 7;
        RBNode<Integer> right = new RBNode<>( expResult );
        instance.setRight( right );
        Integer result = instance.getRight().getKey();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setLeft method, of class RBNode.
     */
    @Test
    public void testSetLeft() {
        RBNode<Integer> instance = new RBNode<>();
        instance.setLeft( new RBNode<> ( 5 ) ); 
        
        assertEquals( ( Integer ) instance.getLeft().getKey(), ( Integer ) 5 );
    }

    /**
     * Test of setRight method, of class RBNode.
     */
    @Test
    public void testSetRight() {
        RBNode<Integer> instance = new RBNode<> ();
        instance.setRight( new RBNode<> ( 5 ) );
        
        assertEquals( ( Integer ) instance.getRight().getKey(), ( Integer )  5 );
    }

    /**
     * Test of setKey method, of class RBNode.
     */
    @Test
    public void testSetKey() {
        Integer d = 5;
        RBNode<Integer> instance = new RBNode<> ();
        instance.setKey( d );
        
        assertEquals( ( Integer ) instance.getKey(), ( Integer ) 5 );
    }

    /**
     * Test of getColor method, of class RBNode.
     */
    @Test
    public void testGetColor() {
        RBNode<Integer> instance = new RBNode<>();
        int expResult = 13;
        instance.setColor( expResult );
        int result = instance.getColor();
        
        assertEquals(expResult, result);

    }

    /**
     * Test of setColor method, of class RBNode.
     */
    @Test
    public void testSetColor() {
        int d = 0;
        RBNode<Integer> instance = new RBNode<> ();
        instance.setColor(d);
        
         assertEquals(instance.getColor(), d);
    }

    /**
     * Test of getRoot method, of class RBNode.
     */
    @Test
    public void testGetRoot() {      
        RBNode<Integer> instance = new RBNode<> ( 8 );
        RBNode<Integer> expResult = new RBNode<> ();   
        expResult.setRoot(new RBNode<>( 8 ));
        RBNode<Integer> result = instance;
        
        assertEquals(expResult.getRoot().getKey(), result.getKey());
    }

    /**
     * Test of setRoot method, of class RBNode.
     */
    @Test
    public void testSetRoot() {
        RBNode<Integer>  instance = new RBNode<> ( );
        Integer root = 7;
        instance.setRoot( new RBNode<> ( root ) );
        
        assertEquals( instance.getRoot().getKey(), root );
    }
}