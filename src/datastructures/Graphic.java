package datastructures;


import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author BelPat
 * @param <Node>
 * @param <T>
 */
public class Graphic<Node extends BasicNode<Node, T>, T extends Comparable<T>> extends JPanel {
    private BasicTree<Node,T> myTree;
    private HashMap<BasicNode<Node,T>, Rectangle> positionNodes = null;
    private HashMap<BasicNode<Node,T>, Dimension> subtreeSizes;
    private boolean dirty = true;
    private int parent2child = 20, child2child = 30;
    private Dimension empty = new Dimension(0,0);
    private FontMetrics fm = null;
    
        /**
     * Constructor de la clase GraphicTree.
     * El constructor permite inicializar los atributos de la clase GraphicTree
     * y llama al método repaint(), que es el encargado de pintar el Arbol.
     * @param myTree
     */
    public Graphic(BasicTree<Node,T> myTree)    {      
        this.subtreeSizes = null;
          this.myTree = myTree;
          this.setBackground(Color.WHITE);
          positionNodes = new HashMap<>();
          subtreeSizes = new HashMap<>();
       //   dirty = true;
          repaint();      
    }
    
        /**
     * Calcula las posiciones de los respectivos subárboles y de cada nodo que 
     * forma parte de ese subárbol, para conocer en que posición van a ir dibujados
     * los rectángulos representativos del árbol de la expresión.
     */
    private void foundPositions()    {
         positionNodes.clear();
         subtreeSizes.clear();
         BasicNode<Node,T> root;
        root = this.myTree.getRoot();
         if (root != null)   {
             foundSizeSubTree(root);
             foundPosition(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }
    
    
        /**
     * Calcula el tamaño de cada subárbol y lo agrega al objeto subtreeSizes de la clase
     * de tipo HashMap que va a contener la coleccion de todos los 
     * subárboles que contiene un arbol.
     * @param n:Objeto de la clase BasicNode <T> que se utiliza como
     * referencia calcular el tamaño de cada subárbol.
     * @return Dimension con el tamaño de cada subárbol.
     */
    private Dimension foundSizeSubTree(BasicNode<Node,T> n)    {
          if (n==null || n.isNull()) {
              return new Dimension(0,0);
          }
          Dimension ld = foundSizeSubTree( n.getLeft());
          Dimension rd = foundSizeSubTree( n.getRight());          
          int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
          int w = ld.width + child2child + rd.width;          
          Dimension d = new Dimension(w, h);
          subtreeSizes.put(n, d);          
          return d;
    }
    
        /**
     * Calcula la ubicación de cada nodo de cada subárbol y agrega cada nodo con 
     * un objeto de tipo Rectangule que tiene la ubicación y la Keyrmación específica de dónde 
     * va a ser dibujado.
     * @param n: Objeto de tipo BasicNode <T> que se utiliza como
     * referencia para calcular la ubicación de cada nodo.
     * @param left: int con alineación y orientación a la Leftuierda.
     * @param right: int con alineación y orientación a la rightecha.
     * @param top: int con el tope.
     */
        private void foundPosition(BasicNode<Node,T> n, int left, int right, int top)     {
          if (n==null || n.isNull()) {
              return;
          }

          Dimension ld = (Dimension) subtreeSizes.get(n.getLeft());
          if (ld == null) {
              ld = empty;
          }

          Dimension rd = (Dimension) subtreeSizes.get(n.getRight());
          if (rd == null) 
              rd = empty;

          int center = 0;

          if (right != Integer.MAX_VALUE){
              center = right - rd.width - child2child/2;
          }else if (left != Integer.MAX_VALUE){
              center = left + ld.width + child2child/2;
          }
          int width = fm.stringWidth(n.getKey().toString());

          positionNodes.put(n,new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight()));

          foundPosition( n.getLeft(), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
          foundPosition( n.getRight(), center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
    }
        
        /**
     * Dibuja el árbol teniendo en cuenta las ubicaciones de los nodos y los 
     * subárboles calculadas anteriormente.
     * @param g: Objeto de la clase Graphics2D que permite realizar el dibujo de las líneas, rectangulos y del String de la Keyrmación que contiene el BNode<T>.
     * @param n: Objeto de la clase BasicNode <T> que se utiliza como referencia para dibujar el árbol.
     * @param position_x: int con la posición en x desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param position_y: int con la posición en y desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param yoffs: int con la altura del FontMetrics.
     */
    private void getPaint(Graphics2D g, BasicNode<Node,T> n, int position_x, int position_y, int yoffs)    {
        System.out.println("!!!!!!!!!!!1  get paint ********************"); 
        if (n==null || n.isNull()) {
             return;
         }     
         Rectangle r = (Rectangle) positionNodes.get(n);
         g.setStroke(new BasicStroke(2));
         
         g.setColor(this.getNodeColor(n));
         g.draw(r);
         g.drawString(n.getKey().toString(), r.x + 3, r.y + yoffs);   

         if (position_x != Integer.MAX_VALUE){
            g.drawLine(position_x, position_y, (int)(r.x + r.width/2), r.y);
         }

         getPaint(g, n.getLeft(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
         getPaint(g, n.getRight(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
     }  
    
    private Color getNodeColor(BasicNode<Node,T> n) {
        if(n instanceof RBNode){
            @SuppressWarnings("unchecked")
            RBNode<T> rbnode = (RBNode<T>) n;
            if (rbnode.getColor()== RBNode.RED)
                return Color.RED;
        }
        return Color.BLACK;
    }
    
       /**
     * Sobreescribe el metodo paint y se encarga de pintar todo el árbol.
     * @param g: Objeto de la clase Graphics.
     */
    @Override
   public void paint(Graphics g)    {
         super.paint(g);
         fm = g.getFontMetrics();

         if (dirty)    {
           foundPositions();
           dirty = false;
         }
         
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(getWidth() / 2, parent2child);
         getPaint(g2d, this.myTree.getRoot(), Integer.MAX_VALUE, Integer.MAX_VALUE,fm.getLeading() + fm.getAscent());
         fm = null;
   }
    
    /**
     *
     * @return
     */
    public HashMap getPositionNodes()
    {
        return positionNodes;
    }
}