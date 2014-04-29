package classes;


  
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class GraphicAVLTree<T> extends JPanel{
    private AVLTree<T> myTree;
    private HashMap positionNodes = null;
    private HashMap subtreeSizes = null;
    private boolean dirty = true;
    private int parent2child = 20, child2child = 30;
    private Dimension empty = new Dimension(0,0);
    private FontMetrics fm = null;
    
    
    /**
     * Constructor de la clase ArbolExpresionGrafico.
     * El constructor permite inicializar los atributos de la clase ArbolExpresionGrafico
     * y llama al método repaint(), que es el encargado de pintar el Arbol.
     * @param miExpresion: dato de tipo ArbolExpresion que contiene el Arbol a
     * dibujar.
     */
    public GraphicAVLTree(AVLTree<T> myTree)     {
          this.myTree = myTree;
          this.setBackground(Color.WHITE);
          positionNodes = new HashMap();
          subtreeSizes = new HashMap();
          dirty = true;
          repaint();      
    }


    /**
     * Calcula las posiciones de los respectivos subárboles y de cada nodo que 
     * forma parte de ese subárbol, para conocer en que posición van a ir dibujados
     * los rectángulos representativos del árbol de la expresión.
     */
    private void foundPositions()     {
    //    System.out.println("INICIO arbolavlexpresiongrafico.foundPositions");
         positionNodes.clear();
         subtreeSizes.clear();
         AVLNode<T> root = this.myTree.getRoot();         
         if (root != null)   {
             // System.out.println("root != null");
             foundSizeTree(root);
             foundPosition(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }         
 //                System.out.println("FIN arbolavlexpresiongrafico.foundPositions");
    }
    
       
    /**
     * Calcula el tamaño de cada subárbol y lo agrega al objeto subtreeSizes de la clase
     * de tipo HashMap que va a contener la coleccion de todos los 
     * subárboles que contiene un arbol.
     * @param n:Objeto de la clase NodoB <T> que se utiliza como
     * referencia calcular el tamaño de cada subárbol.
     * @return Dimension con el tamaño de cada subárbol.
     */
    private Dimension foundSizeTree(AVLNode<T> n)    {
        
     //     System.out.println("INICIO arbolavlexpresiongrafico.foundSizeTree");
          if (n == null) {
              return new Dimension(0,0);
          }
 
          Dimension ld = foundSizeTree(n.getLeft());
          Dimension rd = foundSizeTree(n.getRight());
          
          int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
          int w = ld.width + child2child + rd.width;
          
          Dimension d = new Dimension(w, h);
          subtreeSizes.put(n, d);
      //    System.out.println("FIN arbolavlexpresiongrafico.foundSizeTree");
          return d;
    }
    
    
    /**
     * Calcula la ubicación de cada nodo de cada subárbol y agrega cada nodo con 
     * un objeto de tipo Rectangule que tiene la ubicación y la información específica de dónde 
     * va a ser dibujado.
     * @param n: Objeto de tipo NodoB <T> que se utiliza como
     * referencia para calcular la ubicación de cada nodo.
     * @param left: int con alineación y orientación a la izquierda.
     * @param right: int con alineación y orientación a la derecha.
     * @param top: int con el tope.
     */
    private void foundPosition(AVLNode<T> n, int left, int right, int top)     {     

         //   System.out.println("INICIO foundPosition ");
          if (n == null)     {
               //  System.out.println("n == null");
              return;
          }

        //  Dimension ld = (Dimension) subtreeSizes.get(n.getLeft());

          Dimension ld = (Dimension) subtreeSizes.get(n.getLeft());
          if (ld == null)   { //   System.out.println("ld == null");
              ld = empty;
          }

          Dimension rd = (Dimension) subtreeSizes.get(n.getRight());
          if (rd == null)  {  //  System.out.println("rd == null");
              rd = empty;
          }
          int center = 0;

          if (right != Integer.MAX_VALUE) {//  System.out.println("(right != Integer.MAX_VALUE)");
              center = right - rd.width - child2child/2; 
          }  else if (left != Integer.MAX_VALUE) {  //System.out.println(" (left != Integer.MAX_VALUE)l");
            center = left + ld.width + child2child/2; 
          }
          int width = fm.stringWidth(n.getKey().toString());
          positionNodes.put(n,new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight()));
          foundPosition(n.getLeft(), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
          foundPosition(n.getRight(), center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);

          // System.out.println("FIN foundPosition ");
    }

    /**
     * Dibuja el árbol teniendo en cuenta las ubicaciones de los nodos y los 
     * subárboles calculadas anteriormente.
     * @param g: Objeto de la clase Graphics2D que permite realizar el dibujo de las líneas, rectangulos y del String de la información que contiene el NodoB<T>.
     * @param n: Objeto de la clase NodoB <T> que se utiliza como referencia para dibujar el árbol.
     * @param puntox: int con la posición en x desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param puntoy: int con la posición en y desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param yoffs: int con la altura del FontMetrics.
     */
    private void paintTree(Graphics2D g, AVLNode<T> n, int puntox, int puntoy, int yoffs)     {
         if (n == null) {
             return;
         }

         Rectangle r = (Rectangle) positionNodes.get(n);
         g.setStroke(new BasicStroke(2));

         g.draw(r);
         g.drawString(n.getKey().toString(), r.x + 3, r.y + yoffs);

         if (puntox != Integer.MAX_VALUE){
             g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), r.y);
         }

         paintTree(g, n.getLeft(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
         paintTree(g, n.getRight(), (int)(r.x + r.width/2), r.y + r.height, yoffs);     
   }
    

   /**
     * Sobreescribe el metodo paint y se encarga de pintar todo el árbol.
     * @param g: Objeto de la clase Graphics.
     */
   public void paint(Graphics g) 
   {
         super.paint(g);
         fm = g.getFontMetrics();

         if (dirty)       {
           foundPositions();
           dirty = false;
         }
         
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(getWidth() / 2, parent2child);
         paintTree(g2d, this.myTree.getRoot(), Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
         fm = null;
   }
   
 }




