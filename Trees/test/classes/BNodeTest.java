/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nusatres
 */
public class BNodeTest {
    
    /**
     * Test of getKey method, of class BNode.
     */
    @Test
    public void testGetKey() {
        BNode<Integer> instance = new BNode<>( 4 );
        Integer expResult = 4;
        Integer result = instance.getKey();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getLeft method, of class BNode.
     */
    @Test
    public void testGetLeft() {
        BNode<Integer> left = new BNode<>( 4 );
        BNode<Integer> right = new BNode<> ( 10 );
        BNode<Integer> instance = new BNode<> ( 7 , left , right );
        BNode<Integer> expResult = left;
        BNode<Integer> result = instance.getLeft();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getRight method, of class BNode.
     */
    @Test
    public void testGetRight() {
        BNode <Integer> left = new BNode<> ( 4 );
        BNode <Integer> right = new BNode<> ( 10 );
        BNode <Integer> instance = new BNode<>( 7 , left , right );
        BNode <Integer> expResult = right;
        BNode <Integer> result = instance.getRight();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setLeft method, of class BNode.
     */
    @Test
    public void testSetLeft() {
        BNode <Integer> instance = new BNode<> ();
        instance.setLeft( new BNode<> ( 5 ) );
        
        assertEquals( ( Integer ) instance.getLeft().getKey(), ( Integer ) 5 );
    }

    /**
     * Test of setRight method, of class BNode.
     */
    @Test
    public void testSetRight() {
        BNode <Integer> instance = new BNode<> ();
        instance.setRight( new BNode<> ( 5 ) );
        
        assertEquals( ( Integer ) instance.getRight().getKey(), ( Integer )  5 );
    }

    /**
     * Test of setKey method, of class BNode.
     */
    @Test
    public void testSetKey() {
        Integer d = 5;
        BNode<Integer>  instance = new BNode<> ();
        instance.setKey( d);
        
        assertEquals( ( Integer ) instance.getKey(), ( Integer ) 5 );
    }
}