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
public class AVLNodeTest  {
    
    /**
     * Test of getLeft method, of class AVLNode.
     */
    @Test
    public void testGetLeft() {
         AVLNode<Integer> left = new AVLNode<> ( 4 );
        AVLNode<Integer> instance = new AVLNode<>( 7);
        instance.setLeft(left);
        AVLNode<Integer> expResult = left;
        AVLNode<Integer> result = instance.getLeft();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRight method, of class AVLNode.
     */
    @Test
    public void testGetRight() {
        AVLNode<Integer> right = new AVLNode<> ( 10 );
        AVLNode<Integer> instance = new AVLNode<>( 7);
        instance.setRight(right);
        AVLNode<Integer> expResult = right;
        AVLNode<Integer> result = instance.getRight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRoot method, of class AVLNode.
     */
    @Test
    public void testGetRoot() {
        AVLNode<Integer>  instance = new AVLNode<> ( 7 );
        AVLNode<Integer>  expResult = new AVLNode<> ();   
        expResult.setRoot(new AVLNode<>( 7 ));
        AVLNode<Integer>  result = instance;
        assertEquals(expResult.getRoot().getKey(), result.getKey());
    }

    /**
     * Test of getKey method, of class AVLNode.
     */
    @Test
    public void testGetKey() {
        AVLNode<Integer>  instance = new AVLNode<> ( 7 );
        Object expResult = 7;
        Object result = instance.getKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBalance method, of class AVLNode.
     */
    @Test
    public void testGetBalance() {
        AVLNode<Integer>  instance = new AVLNode<> ();
        instance.setBalance( 4 );
        int expResult = 4;
        int result = instance.getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeight method, of class AVLNode.
    */
    @Test
    public void testGetHeight() {
        AVLNode<Integer>  instance = new AVLNode<> ();
        instance.setHeight( 12 );
        int expResult = 12;
        int result = instance.getHeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLeft method, of class AVLNode.
     */
    @Test
    public void testSetLeft() {
        AVLNode<Integer>  instance = new AVLNode<> ( );
        Integer left = 7;
        instance.setLeft( new AVLNode<> ( left ) );
        assertEquals( instance.getLeft().getKey(), left );
    }

    /**
     * Test of setRight method, of class AVLNode.
     */
    @Test
    public void testSetRight() {
        AVLNode<Integer>  instance = new AVLNode<> ( );
        Integer right = 7;
         instance.setRight( new AVLNode<> ( right ) );
        assertEquals( instance.getRight().getKey(), right );
    }

    /**
     * Test of setRoot method, of class AVLNode.
     */
    @Test
    public void testSetRoot() {
        AVLNode<Integer>  instance = new AVLNode<> ( );
        Integer root = 7;
        instance.setRoot( new AVLNode<> ( root ) );
        assertEquals( instance.getRoot().getKey(), root );
    }

    /**
     * Test of setKey method, of class AVLNode.
     */
    @Test
    public void testSetKey() {
        Integer key = 12;
        AVLNode<Integer>  instance = new AVLNode<> ();
        instance.setKey( key );
        assertEquals( instance.getKey(), key );
    }

    /**
     * Test of setBalance method, of class AVLNode.
     */
    @Test
    public void testSetBalance() {
        int balance = 13;
        AVLNode<Integer>  instance = new AVLNode<> ( );
        instance.setBalance(13);
        assertEquals( instance.getBalance(), balance );
    }

    /**
     * Test of setHeight method, of class AVLNode.
     */
    @Test
    public void testSetHeight() {
        int height = 0;
        AVLNode<Integer>  instance = new AVLNode<> ( );
        instance.setHeight( height );
        assertEquals( instance.getHeight(), height);
    }

}