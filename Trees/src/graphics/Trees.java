/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import datastructures.SimulatorAVLTree;
import datastructures.SimulatorBinaryTree;
import datastructures.SimulatorRBTree;
import java.awt.Color;
import java.util.ResourceBundle;
import javax.swing.JTextArea;
import javax.swing.text.Caret;

/**
 *
 * @author BelenPatricia
 */
public class Trees <T extends Comparable < T > >extends javax.swing.JFrame {
    //private int ERROR_MESSAGE;

    private int WARNING_MESSAGE;
    public String language; //castellano, catalan, ingles
    public String path;
    public String operation;
    public runStep ep = new runStep();
  // private JFrame dibujoEmergente = new JFrame("Representación gráfica");
   // private ArbolBB<Integer> miArbol=new ArbolBB <Integer>();
   // private AVLTree<Integer> miArbolAVL=new AVLTree <Integer>();
    public SimulatorBinaryTree<Integer> simulator = new SimulatorBinaryTree<>();
    public SimulatorAVLTree<Integer> AVLsimulator =new SimulatorAVLTree<>();
    public SimulatorRBTree<Integer> RBSimulator = new SimulatorRBTree<>();

    /**
     * Creates new form ARBRES
     */
    public Trees() {
        initComponents();
        this.setLocationRelativeTo( null );
        FrmConfigTree.setLocationRelativeTo( null );
        FrmConfigKeys.setLocationRelativeTo( null );
        
        uncheked();

        BtmNext.setEnabled( true );
        BtmNext.addActionListener( ep );
        //PAyudaRN.setName("Rojo-Negro");
        BtnSpain.setEnabled( false );//por defecto queda arrancado el programa en castellano
        language = "castellano";
    }

    /*  public T newElement(Class<T> cT) throws InstantiationException, IllegalAccessException {
     * 
     * T result = null;
     * 
     * result = cT.newInstance();
     * 
     * return result;
     * } */
    
    public void reloadScreen() {        
        SwingUtilities.updateComponentTreeUI( this );
        this.validate();
        SwingUtilities.updateComponentTreeUI( FrmHelp );
        FrmHelp.validate();
    }

    public void printConsole( String messageC, String messageI, String messageCat ) {
        switch (language) {
            case "castellano":
                TxtConsole.append( messageC );
                break;
            case "ingles":
                TxtConsole.append( messageI );
                break;
            case "catalan":
                TxtConsole.append(messageCat);
                break;
        }
    }
   
    public void addKey( int key ) {
        operation = "Insertar";
        printConsole("Insertar (" + key + "," +key + ") \n", "Add (" + TxtKey.getText() + ",) \n", "Inserir (" + TxtKey.getText() + ",) \n");
        go (2, txtAlgorithm);
        if( optABC.isSelected() ) {
            //go(2,txtAlgorithm);
            if ( this.simulator.add( key ) ) { this.repaintTree(); }
            else { System.out.println("Errorrrrrrr ABB");        }
        }
        if( optAVL.isSelected() )  {            
            System.out.println("add avl");
            if(this.AVLsimulator.add(key)){  this.repaintTree();}
            else { System.out.println("Errorrrrrrr AVL");        }
        }
        if( optRN.isSelected() )  {           
            System.out.println("add RN");
            if(this.RBSimulator.add(key)){  this.repaintTree();}
              else { System.out.println("Errorrrrrrr AVL");        }
        }       
    }
    
    public void findKey ( int key ){
        String msgexit = new String ();
        if( optABC.isSelected() )   {
           msgexit = this.simulator.isHere( key );
        } 
        if( optAVL.isSelected() )  {
              msgexit = this.AVLsimulator.isHere( key );
        }
        if( optRN.isSelected() )  {
              msgexit = this.RBSimulator.isHere( key );
        }
          printConsole( msgexit , msgexit , msgexit );
    }        

    public void deleteKey(int key) {
        operation = "Eliminar";
        printConsole("Eliminar (" + key + "," +key + ") \n", "Add (" + TxtKey.getText() + ") \n", "Inserir (" + TxtKey.getText() + ",) \n");
        String msgexit = "";
        if( optABC.isSelected() ){
             msgexit = this.simulator.delete( key );
        }
        if( optAVL.isSelected() )  {
            System.out.println("eliminar avl");
            msgexit = this.AVLsimulator.delete( key );
        }
        if(optRN.isSelected())  {
            System.out.println("eliminar avl");
             msgexit="";
             this.RBSimulator.delete(key);
        }
        this.repaintTree();
        printConsole( msgexit , msgexit , msgexit );     
    }
    
     public void cutLeaves()   {
        if( optABC.isSelected() )   {
            this.simulator.cutLeaves();
        }
        if(optAVL.isSelected())  {
          this.AVLsimulator.cutLeaves();
        }
        if(optRN.isSelected())  {
          this.RBSimulator.cutLeaves();
        }
        this.repaintTree();
        printConsole( "podado","podado","podado" );
      }

     public void getFather( Integer i ) throws InstantiationException, IllegalAccessException {
        String msgexit="";
        System.out.println(" Treees.getFather " + i);
        if(optABC.isSelected()){           
           msgexit=this.simulator.getFather(i);   
        }
        if(optAVL.isSelected())  {
           msgexit=this.AVLsimulator.getFather(i);
        }
        if(optRN.isSelected())  {
          msgexit = this.RBSimulator.getFather(i);
        }
        printConsole( msgexit,msgexit,msgexit);
    }

    public void getLeaves()  {
        String msgexit="";
        if(optABC.isSelected())    {              
          msgexit=this.simulator.getLeaves();
        }
        if(optAVL.isSelected())  {
           msgexit=this.AVLsimulator.getLeaves();
        }
        if(optRN.isSelected())  {
          msgexit=this.RBSimulator.getLeaves();
        }
        printConsole( msgexit , msgexit , msgexit );
   }

      
    public void repaintTree() {
        Rectangle size = this.jInternalFrame1.getBounds();
        this.jInternalFrame1 = null;
        this.jInternalFrame1 = new JInternalFrame( "Representación gráfica" , true );
        this.jDesktopPane1.add( this.jInternalFrame1 , JLayeredPane.DEFAULT_LAYER );
        this.jInternalFrame1.setVisible( true );
        this.jInternalFrame1.setBounds( size );
        this.jInternalFrame1.setEnabled( false );
        if( optABC.isSelected() )    {
            System.out.println( " repaintTree ArobolAbc" ); 
            this.jInternalFrame1.add( this.simulator.getPaint() , BorderLayout.CENTER );
        }
        if( optAVL.isSelected() )  {
            System.out.print("ARBRES.repaintTree avl");
            this.jInternalFrame1.add( this.AVLsimulator.getPaint() , BorderLayout.CENTER );
        }
        if( optRN.isSelected() )      {
            System.out.print("ARBRES.repaintTree RN");
            this.jInternalFrame1.add( this.RBSimulator.getPaint() , BorderLayout.CENTER );
        }
        jInternalFrame1.getContentPane().setVisible( true );
    }
    public void activeOptions(){
        OptInsert.setEnabled( true );
        OptDelete.setEnabled( true );
        OptSearch.setEnabled( true );
        OptSearchFather.setEnabled( true ); 
        OptCut.setEnabled( true );
        OptLeaves.setEnabled( true );
        TxtKey.setEnabled( true );
        jLabel2.setEnabled( true );
        BtmNext.setEnabled( true );
    }
    public boolean minimParameters(){
        if ( optABC.isSelected() == false && optAVL.isSelected() == false && optRN.isSelected() == false ) {
            switch ( language ) {
                case "castellano":
                    JOptionPane.showMessageDialog (null, "SELECCIONA UN TIPO DE ARBOL", "Configuración", WARNING_MESSAGE );
                    break;
                case "ingles":
                    JOptionPane.showMessageDialog( null, "SELECT A TREE", "Configuration", WARNING_MESSAGE );
                    break;
                case "catalan":
                    JOptionPane.showMessageDialog( null, "SELECCIONA UN TIPUS D'ARBRE", "Configuració", WARNING_MESSAGE );
                    break;
            }
            return false;
        } else {
            //Block controls 
            optABC.setEnabled( false );
            optAVL.setEnabled( false );
            optRN.setEnabled( false );
            return true;
        }
    }
    
public boolean comboSelectionExamples(){
    if ( CmbExamples.getSelectedIndex() == 0 ) {
                switch ( language ) {
                    case "castellano":
                        JOptionPane.showMessageDialog( null, "SELECCIONA UN EJEMPLO", "Configuración", WARNING_MESSAGE );
                        break;
                    case "ingles":
                        JOptionPane.showMessageDialog( null, "SELECT AN EXAMPLE", "Configuration", WARNING_MESSAGE );
                        break;
                    case "catalan":
                        JOptionPane.showMessageDialog( null, "SELECCIONA UN EXEMPLE", "Configuració", WARNING_MESSAGE );
                        break;
                }
                return true;
            } else if ( ( CmbExamples.getSelectedIndex() == 1 ) && CmbExamples.isEnabled() ) {               
                CmbExamples.setEnabled( false );
                //Carga el example 1
                addKey( 22 );
                addKey( 33 );
                addKey( 44 );
                addKey( 23 );
                addKey( 11 );
                addKey( 15 );
                addKey( 60 );
                addKey( 38 );
                addKey( 72 );
                return true;
            } else if ( CmbExamples.getSelectedIndex() == 2 && CmbExamples.isEnabled() ) {
                CmbExamples.setEnabled( false );
                //Carga el example 2
                addKey( 13 );
                addKey( 8 );
                addKey( 1 );
                addKey( 17 );
                addKey( 15 );
                addKey( 25 );
                addKey( 6 );
                addKey( 11 );
                addKey( 22 );
                addKey( 27 );
                return true;
            } else if ( CmbExamples.getSelectedIndex() == 3 && CmbExamples.isEnabled() ) {
                CmbExamples.setEnabled( false );
                //Carga el example 3
                addKey( 4 );
                addKey( 2 );
                addKey( 6 );
                addKey( 1 );
                addKey( 3 );
                addKey( 5 );
                addKey( 8 );
                addKey( 7 );
                addKey( 9 );
                return true;
            } else if ( CmbExamples.getSelectedIndex() == 4 && CmbExamples.isEnabled() ) {
                CmbExamples.setEnabled( false );
                //Carga el example 4            
                //rojo-negro http://www.cosc.canterbury.ac.nz/research/RG/alg/rbtree.gif
                
                addKey( 38 );
                addKey( 13 );
                addKey( 51 );
                addKey( 10 );
                addKey( 12 );
                addKey( 40 );
                addKey( 84 );
                addKey( 25 );
                return true;
            } else if ( CmbExamples.getSelectedIndex() == 5 || !CmbExamples.isEnabled() ) {
                CmbExamples.setEnabled( false );
                return false;
            }
        return false;
}
public void startAlgorithm(){
    if ( OptInsert.isSelected() ) {
                        switch ( language ) {
                        case "castellano":
                            txtAlgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_es_ES").getString("descripcion2"));
                            break;
                        case "ingles":
                            txtAlgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_en_US").getString("descripcion2"));
                            break;
                        case "catalan":
                            txtAlgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_ca_ES").getString("descripcion2"));
                            break;
                        }
                        Integer i = new Integer( TxtKey.getText() );
                        addKey( i.intValue() );
                    } else if ( OptDelete.isSelected() ) {
                        Integer i = new Integer( TxtKey.getText() );
                        operation = "Eliminar";
                        printConsole("Eliminar (" + TxtKey.getText() + ") \n", "Delete (" + TxtKey.getText() + ",) \n", "Eliminar (" + TxtKey.getText() + ") \n");
                        deleteKey( i.intValue() );
                    } else if ( OptSearch.isSelected() ) {
                        txtAlgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_es_ES").getString("descripcion1"));
                        Integer i = new Integer( TxtKey.getText() );
                        operation = "Consultar";
                        printConsole("Consultar (" + TxtKey.getText() + ") \n", "Query (" + TxtKey.getText() + ",) \n", "Consultar (" + TxtKey.getText() + ") \n");
                        findKey( i.intValue() );
                    } else if ( OptSearchFather.isSelected() ) {
                        Integer i = new Integer( TxtKey.getText() );operation = "ConsultarPadre";
                        printConsole("Consultar (" + TxtKey.getText() + ") \n", "Query (" + TxtKey.getText() + ") \n", "Consultar (" + TxtKey.getText() + ") \n");
                        try {   
                            getFather(i);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(Trees.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(Trees.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }  else if ( OptCut.isSelected() ) {
                        operation = "Podar";
                        cutLeaves();
                    }  else if ( OptLeaves.isSelected() ) {
                        operation = "ConsultarHojas";
                        getLeaves();
                    }
}

  public void go(int line, JTextArea txtAlgorithm){
        String[]t=txtAlgorithm.getText().split("\n");
        int position = 0;
        line = 3;
        txtAlgorithm.setCaretColor(Color.red);
                
        for(int index=0 ; index < line; index++ ){
	   position += t[index].length();
	}
	txtAlgorithm.setCaretPosition( position + 2 );
       // TxtAlgorithm.insert("-- " + position + "--", position);
        //int aux =  t[line].length()+position;
        //TxtAlgorithm.insert("** " +aux + "**", t[line].length()+position);
        txtAlgorithm.moveCaretPosition( position + t[line].length() + 2);
        Caret txtseleccion = txtAlgorithm.getCaret();
        txtseleccion.setSelectionVisible(true);
        txtseleccion.setVisible(true);
       
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GTrees = new javax.swing.ButtonGroup();
        GOperations = new javax.swing.ButtonGroup();
        FrmHelp = new javax.swing.JFrame();
        BtmExitHelp = new javax.swing.JButton();
        TabHelp = new javax.swing.JTabbedPane();
        PHelpAVL = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        PHelpABC = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        PHelpRN = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        TabHelpOperations = new javax.swing.JTabbedPane();
        PAyudaInsertar = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        PAyudaEliminar = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        PAyudaConsultar = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        FrmConfigTree = new javax.swing.JFrame();
        BtmExitConfig1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        FrmConfigKeys = new javax.swing.JFrame();
        jLabel5 = new javax.swing.JLabel();
        BtmExitConfig2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        optABC = new javax.swing.JRadioButton();
        optAVL = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        CmbExamples = new javax.swing.JComboBox<String>();
        BtmHelp = new javax.swing.JButton();
        BtmReload = new javax.swing.JButton();
        BtmPlay = new javax.swing.JButton();
        BtmNext = new javax.swing.JButton();
        BtmStop = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        OptInsert = new javax.swing.JRadioButton();
        OptSearch = new javax.swing.JRadioButton();
        OptDelete = new javax.swing.JRadioButton();
        TxtKey = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        OptSearchFather = new javax.swing.JRadioButton();
        OptLeaves = new javax.swing.JRadioButton();
        OptCut = new javax.swing.JRadioButton();
        optRN = new javax.swing.JRadioButton();
        BtnExit = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtAlgorithm = new javax.swing.JTextArea();
        BtnSpain = new javax.swing.JButton();
        BtnEnglish = new javax.swing.JButton();
        BtnCatalan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TxtConsole = new javax.swing.JTextArea();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jInternalFrame1 = new javax.swing.JInternalFrame();

        FrmHelp.setLocationByPlatform(true);
        FrmHelp.setMinimumSize(new java.awt.Dimension(675, 513));
        FrmHelp.setName("Ayuda"); // NOI18N
        FrmHelp.setResizable(false);
        FrmHelp.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                FrmHelpComponentShown(evt);
            }
        });

        BtmExitHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/exit.png"))); // NOI18N
        BtmExitHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtmExitHelpMouseClicked(evt);
            }
        });
        BtmExitHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtmExitHelpActionPerformed(evt);
            }
        });

        TabHelp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TabHelp.setName("Rojo-Negro"); // NOI18N

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(214, 217, 223));
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion2"));
        jTextArea2.setAutoscrolls(false);
        jTextArea2.setBorder(null);
        jTextArea2.setOpaque(false);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout PHelpAVLLayout = new javax.swing.GroupLayout(PHelpAVL);
        PHelpAVL.setLayout(PHelpAVLLayout);
        PHelpAVLLayout.setHorizontalGroup(
            PHelpAVLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PHelpAVLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );
        PHelpAVLLayout.setVerticalGroup(
            PHelpAVLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PHelpAVLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
        );

        TabHelp.addTab("AVL", PHelpAVL);

        jTextArea3.setEditable(false);
        jTextArea3.setBackground(new java.awt.Color(214, 217, 223));
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion1")
        );
        jTextArea3.setOpaque(false);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout PHelpABCLayout = new javax.swing.GroupLayout(PHelpABC);
        PHelpABC.setLayout(PHelpABCLayout);
        PHelpABCLayout.setHorizontalGroup(
            PHelpABCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PHelpABCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PHelpABCLayout.setVerticalGroup(
            PHelpABCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PHelpABCLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addContainerGap(165, Short.MAX_VALUE))
            .addGroup(PHelpABCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3))
        );

        TabHelp.addTab("ABC", PHelpABC);

        jTextArea7.setEditable(false);
        jTextArea7.setBackground(new java.awt.Color(214, 217, 223));
        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jTextArea7.setText(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion3"));
        jTextArea7.setOpaque(false);
        jScrollPane7.setViewportView(jTextArea7);

        javax.swing.GroupLayout PHelpRNLayout = new javax.swing.GroupLayout(PHelpRN);
        PHelpRN.setLayout(PHelpRNLayout);
        PHelpRNLayout.setHorizontalGroup(
            PHelpRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PHelpRNLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PHelpRNLayout.setVerticalGroup(
            PHelpRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );

        TabHelp.addTab("Rojo-Negro", PHelpRN);

        TabHelpOperations.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextArea4.setEditable(false);
        jTextArea4.setBackground(new java.awt.Color(214, 217, 223));
        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setText(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion4"));
        jTextArea4.setAutoscrolls(false);
        jTextArea4.setBorder(null);
        jTextArea4.setOpaque(false);
        jScrollPane4.setViewportView(jTextArea4);

        javax.swing.GroupLayout PAyudaInsertarLayout = new javax.swing.GroupLayout(PAyudaInsertar);
        PAyudaInsertar.setLayout(PAyudaInsertarLayout);
        PAyudaInsertarLayout.setHorizontalGroup(
            PAyudaInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PAyudaInsertarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );
        PAyudaInsertarLayout.setVerticalGroup(
            PAyudaInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PAyudaInsertarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
        );

        TabHelpOperations.addTab("Insertar", PAyudaInsertar);

        jTextArea5.setEditable(false);
        jTextArea5.setBackground(new java.awt.Color(214, 217, 223));
        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jTextArea5.setText(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion5"));
        jTextArea5.setAutoscrolls(false);
        jTextArea5.setBorder(null);
        jTextArea5.setOpaque(false);
        jScrollPane5.setViewportView(jTextArea5);

        javax.swing.GroupLayout PAyudaEliminarLayout = new javax.swing.GroupLayout(PAyudaEliminar);
        PAyudaEliminar.setLayout(PAyudaEliminarLayout);
        PAyudaEliminarLayout.setHorizontalGroup(
            PAyudaEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PAyudaEliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );
        PAyudaEliminarLayout.setVerticalGroup(
            PAyudaEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PAyudaEliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
        );

        TabHelpOperations.addTab("Eliminar", PAyudaEliminar);

        jTextArea6.setEditable(false);
        jTextArea6.setBackground(new java.awt.Color(214, 217, 223));
        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jTextArea6.setText(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion6"));
        jTextArea6.setAutoscrolls(false);
        jTextArea6.setBorder(null);
        jTextArea6.setOpaque(false);
        jScrollPane6.setViewportView(jTextArea6);

        javax.swing.GroupLayout PAyudaConsultarLayout = new javax.swing.GroupLayout(PAyudaConsultar);
        PAyudaConsultar.setLayout(PAyudaConsultarLayout);
        PAyudaConsultarLayout.setHorizontalGroup(
            PAyudaConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PAyudaConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );
        PAyudaConsultarLayout.setVerticalGroup(
            PAyudaConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PAyudaConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
        );

        TabHelpOperations.addTab("Consultar", PAyudaConsultar);

        javax.swing.GroupLayout FrmHelpLayout = new javax.swing.GroupLayout(FrmHelp.getContentPane());
        FrmHelp.getContentPane().setLayout(FrmHelpLayout);
        FrmHelpLayout.setHorizontalGroup(
            FrmHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrmHelpLayout.createSequentialGroup()
                .addGroup(FrmHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtmExitHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FrmHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TabHelp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(TabHelpOperations, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        FrmHelpLayout.setVerticalGroup(
            FrmHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FrmHelpLayout.createSequentialGroup()
                .addComponent(TabHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabHelpOperations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtmExitHelp))
        );

        TabHelp.getAccessibleContext().setAccessibleName("");
        TabHelp.getAccessibleContext().setAccessibleDescription("");

        FrmConfigTree.setMinimumSize(new java.awt.Dimension(337, 139));
        FrmConfigTree.setName("Configuración"); // NOI18N
        FrmConfigTree.setResizable(false);

        BtmExitConfig1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ok.png"))); // NOI18N
        BtmExitConfig1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtmExitConfig1MouseClicked(evt);
            }
        });
        BtmExitConfig1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtmExitConfig1ActionPerformed(evt);
            }
        });

        jLabel4.setText("SELECCIONE UN TIPO DE ÁRBOL!!!");

        javax.swing.GroupLayout FrmConfigTreeLayout = new javax.swing.GroupLayout(FrmConfigTree.getContentPane());
        FrmConfigTree.getContentPane().setLayout(FrmConfigTreeLayout);
        FrmConfigTreeLayout.setHorizontalGroup(
            FrmConfigTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FrmConfigTreeLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(FrmConfigTreeLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(BtmExitConfig1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        FrmConfigTreeLayout.setVerticalGroup(
            FrmConfigTreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FrmConfigTreeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtmExitConfig1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        FrmConfigKeys.setMinimumSize(new java.awt.Dimension(384, 137));
        FrmConfigKeys.setResizable(false);

        jLabel5.setText("INTRODUZCA UNA CLAVE Y UN VALOR CORRECTOS");

        BtmExitConfig2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ok.png"))); // NOI18N
        BtmExitConfig2.setMaximumSize(new java.awt.Dimension(40, 40));
        BtmExitConfig2.setMinimumSize(new java.awt.Dimension(40, 40));
        BtmExitConfig2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtmExitConfig2MouseClicked(evt);
            }
        });
        BtmExitConfig2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtmExitConfig2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FrmConfigKeysLayout = new javax.swing.GroupLayout(FrmConfigKeys.getContentPane());
        FrmConfigKeys.getContentPane().setLayout(FrmConfigKeysLayout);
        FrmConfigKeysLayout.setHorizontalGroup(
            FrmConfigKeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrmConfigKeysLayout.createSequentialGroup()
                .addGroup(FrmConfigKeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FrmConfigKeysLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FrmConfigKeysLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(BtmExitConfig2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FrmConfigKeysLayout.setVerticalGroup(
            FrmConfigKeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FrmConfigKeysLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtmExitConfig2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setName("Árboles"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bitstream Vera Sans Mono", 3, 14))); // NOI18N
        jPanel1.setToolTipText("");

        GTrees.add(optABC);
        optABC.setText("ABC");
        optABC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optABCMouseClicked(evt);
            }
        });
        optABC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optABCActionPerformed(evt);
            }
        });

        GTrees.add(optAVL);
        optAVL.setText("AVL");
        optAVL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optAVLMouseClicked(evt);
            }
        });
        optAVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAVLActionPerformed(evt);
            }
        });

        jLabel1.setText("Ejemplos:");

        CmbExamples.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<SELECCIONA UNO>", "1", "2", "3", "4", "NUEVO" }));
        CmbExamples.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbExamplesActionPerformed(evt);
            }
        });

        BtmHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1373669794_Folder-Info.png"))); // NOI18N
        BtmHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtmHelpMouseClicked(evt);
            }
        });
        BtmHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtmHelpActionPerformed(evt);
            }
        });

        BtmReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1373669670_order.png"))); // NOI18N
        BtmReload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtmReloadMouseClicked(evt);
            }
        });
        BtmReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtmReloadActionPerformed(evt);
            }
        });

        BtmPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1373669712_start.png"))); // NOI18N
        BtmPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtmPlayMouseClicked(evt);
            }
        });
        BtmPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtmPlayActionPerformed(evt);
            }
        });

        BtmNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1373669652_hide-right.png"))); // NOI18N
        BtmNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtmNextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtmNextMouseEntered(evt);
            }
        });
        BtmNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtmNextActionPerformed(evt);
            }
        });

        BtmStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1373669729_stop-red.png"))); // NOI18N
        BtmStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtmStopActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel9.setText("Opciones:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bitstream Vera Sans Mono", 3, 14))); // NOI18N

        GOperations.add(OptInsert);
        OptInsert.setText("Insertar");
        OptInsert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OptInsertMouseClicked(evt);
            }
        });
        OptInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptInsertActionPerformed(evt);
            }
        });

        GOperations.add(OptSearch);
        OptSearch.setText("Consultar");
        OptSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptSearchActionPerformed(evt);
            }
        });

        GOperations.add(OptDelete);
        OptDelete.setText("Eliminar");
        OptDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptDeleteActionPerformed(evt);
            }
        });

        jLabel2.setText("Clave:");

        GOperations.add(OptSearchFather);
        OptSearchFather.setText("Consultar Padre");
        OptSearchFather.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptSearchFatherActionPerformed(evt);
            }
        });

        GOperations.add(OptLeaves);
        OptLeaves.setText("Consultar Hojas");
        OptLeaves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptLeavesActionPerformed(evt);
            }
        });

        GOperations.add(OptCut);
        OptCut.setText("Podar");
        OptCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptCutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(OptDelete)
                            .addComponent(OptSearch)
                            .addComponent(OptCut)
                            .addComponent(OptSearchFather))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(TxtKey, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(OptInsert)
                            .addComponent(OptLeaves))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(OptInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OptSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OptDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OptCut)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(38, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(OptSearchFather, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(9, 9, 9)
                                .addComponent(TxtKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)))))
                .addComponent(OptLeaves)
                .addContainerGap())
        );

        GTrees.add(optRN);
        optRN.setText("Rojo y Negro");
        optRN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optRNMouseClicked(evt);
            }
        });
        optRN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optRNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtmHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(BtmPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtmNext, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtmStop, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtmReload, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(69, 69, 69)
                        .addComponent(CmbExamples, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(optAVL)
                        .addGap(28, 28, 28)
                        .addComponent(optRN))
                    .addComponent(optABC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optABC)
                    .addComponent(optAVL)
                    .addComponent(optRN))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CmbExamples, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(BtmNext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(BtmPlay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(BtmHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtmStop, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtmReload, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        BtnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/exit.png"))); // NOI18N
        BtnExit.setMaximumSize(new java.awt.Dimension(40, 40));
        BtnExit.setMinimumSize(new java.awt.Dimension(40, 40));
        BtnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnExitMouseClicked(evt);
            }
        });
        BtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExitActionPerformed(evt);
            }
        });

        txtAlgorithm.setColumns(20);
        txtAlgorithm.setRows(5);
        txtAlgorithm.setSelectedTextColor(new java.awt.Color(255, 51, 51));
        jScrollPane8.setViewportView(txtAlgorithm);

        BtnSpain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/spain.png"))); // NOI18N
        BtnSpain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnSpainMouseClicked(evt);
            }
        });
        BtnSpain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSpainActionPerformed(evt);
            }
        });

        BtnEnglish.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/uk.png"))); // NOI18N
        BtnEnglish.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnEnglishMouseClicked(evt);
            }
        });

        BtnCatalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/catalonia.png"))); // NOI18N
        BtnCatalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCatalanMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel8.setText("Configuración:");

        TxtConsole.setEditable(false);
        TxtConsole.setBackground(new java.awt.Color(214, 217, 223));
        TxtConsole.setColumns(20);
        TxtConsole.setRows(5);
        TxtConsole.setAutoscrolls(false);
        TxtConsole.setBorder(null);
        TxtConsole.setOpaque(false);
        jScrollPane9.setViewportView(TxtConsole);

        jInternalFrame1.setVisible(true);
        jInternalFrame1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jInternalFrame1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        jInternalFrame1.setBounds(0, 0, 790, 310);
        jDesktopPane1.add(jInternalFrame1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                            .addComponent(BtnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDesktopPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnSpain, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnEnglish, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnCatalan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnEnglish, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnCatalan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnSpain, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void optAVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAVLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optAVLActionPerformed

    private void BtmReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtmReloadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtmReloadActionPerformed

    private void BtmPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtmPlayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtmPlayActionPerformed

    private void BtmPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtmPlayMouseClicked
        jInternalFrame1.setVisible( true );
        if (minimParameters()==true){
            if (comboSelectionExamples()==false){
                if ( ( OptInsert.isSelected() || OptDelete.isSelected() || OptSearch.isSelected() ) && ( TxtKey.getText().trim().length() == 0 ) ) {                      
                //CLAVE O VALOR NO TIENEN NADA O INCLUSO LOS DOS ESTÁN VACÍOS
                    switch ( language ) {
                        case "castellano":
                            JOptionPane.showMessageDialog( null, "INTRODUZCA UN VALOR CORRECTOS.", "Opciones de Configuración", WARNING_MESSAGE );
                            break;
                        case "ingles":
                            JOptionPane.showMessageDialog( null, "INSERT CORRECT VALUE.", "Configuration Options", WARNING_MESSAGE );
                            break;
                        case "catalan":
                            JOptionPane.showMessageDialog( null, "INTRODUEIX UN VALOR CORRECTE.", "Opcions de Configuració", WARNING_MESSAGE );
                            break;
                    }
                } else {
                    startAlgorithm();
                }
            }
        }
        activeOptions();
    }//GEN-LAST:event_BtmPlayMouseClicked
    private void BtmHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtmHelpMouseClicked
        // TODO add your handling code here:
        FrmHelp.setLocationRelativeTo( null );
        FrmHelp.setVisible(true);

    }//GEN-LAST:event_BtmHelpMouseClicked

    private void BtmExitHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtmExitHelpMouseClicked
        // TODO add your handling code here:
        FrmHelp.setVisible(false);
    }//GEN-LAST:event_BtmExitHelpMouseClicked

    private void BtmExitHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtmExitHelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtmExitHelpActionPerformed

    private void FrmHelpComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_FrmHelpComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_FrmHelpComponentShown

    private void BtmHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtmHelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtmHelpActionPerformed

    private void BtnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnExitMouseClicked
        // TODO add your handling code here:
        System.exit( 0 );
    }//GEN-LAST:event_BtnExitMouseClicked

    private void BtmExitConfig1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtmExitConfig1MouseClicked
        // TODO add your handling code here:
        FrmConfigTree.setVisible(false);
    }//GEN-LAST:event_BtmExitConfig1MouseClicked

    private void BtmExitConfig1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtmExitConfig1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtmExitConfig1ActionPerformed

    private void BtmExitConfig2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtmExitConfig2MouseClicked
        // TODO add your handling code here:
        FrmConfigKeys.setVisible(false);
    }//GEN-LAST:event_BtmExitConfig2MouseClicked

    private void BtmExitConfig2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtmExitConfig2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtmExitConfig2ActionPerformed

    private void BtmReloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtmReloadMouseClicked
        // REINICIAMOS TODOS LOS VALORES
        optABC.setEnabled( true );
        optAVL.setEnabled( true );
        optRN.setEnabled( true );
        CmbExamples.setEnabled( true );
        optABC.setSelected( false );
        optAVL.setSelected( false );
        CmbExamples.setSelectedIndex( 0 );       
        txtAlgorithm.setText("");
        uncheked();       
        jInternalFrame1.getContentPane().setVisible( false );
        //Escribimos en la consola:        
        this.simulator.deleteTree();
        this.AVLsimulator.deleteTree();
        this.RBSimulator.deleteTree();
        printConsole("Reiniciamos el programa..... \n", "Restart the program..... \n", "Reiniciem el programa..... \n");
        reloadScreen();
        this.repaintTree();
    }//GEN-LAST:event_BtmReloadMouseClicked

    private void BtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnExitActionPerformed

    private void BtnEnglishMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnEnglishMouseClicked

            language = "ingles";
            //activamos el boton del idioma que estaba desactivado y traducimos toda la interfaz.
            BtnSpain.setEnabled( true );
            BtnCatalan.setEnabled( true );
            BtnEnglish.setEnabled( false );
            //jPanel1.setToolTipText("Configuration:");
            jLabel8.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_lbl_configuracion"));
            optABC.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_ABC"));
            optAVL.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_AVL"));
            optRN.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_RN"));
            jLabel1.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_lbl_ejemplo"));
            jLabel2.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_lbl_clave"));
//            jLabel3.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_lbl_valor"));
            jLabel9.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_lbl_opciones"));
            OptInsert.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_insertar"));
            OptDelete.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_eliminar"));
            OptSearch.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_consultar"));
            OptSearchFather.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_padre"));
            OptCut.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_podar"));
            OptLeaves.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_hojas"));
            if (OptInsert.isSelected()==true)txtAlgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_en_US").getString("descripcion2"));
            if (OptSearch.isSelected()==true)txtAlgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_en_US").getString("descripcion1")); 
            //FALTA AGREGAR EL RESTO DE PSEUDOCÓDIGOS
            CmbExamples.removeAllItems();
            CmbExamples.addItem( "<SELECT ONE>" );
            CmbExamples.addItem( "1" );
            CmbExamples.addItem( "2" );
            CmbExamples.addItem( "3" );
            CmbExamples.addItem( "4" );
            CmbExamples.addItem( "New" );
            //Ahora vamos a actualizar los messages de otros pantallas.
            TabHelp.setTitleAt( 2, "Red-Black" );
            TabHelpOperations.setTitleAt( 0, "Insert" );
            TabHelpOperations.setTitleAt( 1, "Delete" );
            TabHelpOperations.setTitleAt( 2, "Query" );
            jTextArea2.setText("");
            jTextArea3.setText("");
            jTextArea4.setText("");
            jTextArea5.setText("");
            jTextArea6.setText("");
            jTextArea7.setText("");
            jTextArea3.append(ResourceBundle.getBundle("resources.properties.help_en_US").getString("descripcion1"));
            jTextArea2.append(ResourceBundle.getBundle("resources.properties.help_en_US").getString("descripcion2"));
            jTextArea7.append(ResourceBundle.getBundle("resources.properties.help_en_US").getString("descripcion3"));
            jTextArea4.append(ResourceBundle.getBundle("resources.properties.help_en_US").getString("descripcion4"));
            jTextArea5.append(ResourceBundle.getBundle("resources.properties.help_en_US").getString("descripcion5"));
            jTextArea6.append(ResourceBundle.getBundle("resources.properties.help_en_US").getString("descripcion6"));
            reloadScreen();
    }//GEN-LAST:event_BtnEnglishMouseClicked

    private void BtnSpainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnSpainMouseClicked
            language = "castellano";
            //activamos el boton del idioma que estaba desactivado y traducimos toda la interfaz.
            BtnSpain.setEnabled( false );
            BtnCatalan.setEnabled( true );
            BtnEnglish.setEnabled( true );
            jLabel8.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_lbl_configuracion"));
            optABC.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_ABC"));
            optAVL.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_AVL"));
            optRN.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_RN"));
            jLabel1.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_lbl_ejemplo"));
            jLabel2.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_lbl_clave"));
//            jLabel3.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_lbl_valor"));
            jLabel9.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_lbl_opciones"));
            OptInsert.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_insertar"));
            OptDelete.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_eliminar"));
            OptSearch.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_consultar"));
            OptSearchFather.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_padre"));
            OptCut.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_podar"));
            OptLeaves.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_hojas"));
            
            CmbExamples.removeAllItems();
            CmbExamples.addItem( "<SELECCIONA UNO>" );
            CmbExamples.addItem( "1" );
            CmbExamples.addItem( "2" );
            CmbExamples.addItem( "3" );
            CmbExamples.addItem( "4" );
            CmbExamples.addItem( "Nuevo" );
            //Ahora vamos a actualizar los messages de otros pantallas.
            TabHelp.setTitleAt( 2, "Rojo-Negro" );
            TabHelpOperations.setTitleAt( 0, "Insertar" );
            TabHelpOperations.setTitleAt( 1, "Eliminar" );
            TabHelpOperations.setTitleAt( 2, "Consultar" );
            jTextArea2.setText("");
            jTextArea3.setText("");
            jTextArea4.setText("");
            jTextArea5.setText("");
            jTextArea6.setText("");
            jTextArea7.setText("");
            
            jTextArea3.append(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion1"));
            jTextArea2.append(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion2"));
            jTextArea7.append(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion3"));
            jTextArea4.append(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion4"));
            jTextArea5.append(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion5"));
            jTextArea6.append(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion6"));
            
            reloadScreen();
    }//GEN-LAST:event_BtnSpainMouseClicked

    private void BtnCatalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCatalanMouseClicked

            language = "catalan";
            //activamos el boton del idioma que estaba desactivado y traducimos toda la interfaz.
            BtnSpain.setEnabled( true );
            BtnCatalan.setEnabled( false );
            BtnEnglish.setEnabled( true );
            //jPanel1.setToolTipText("Configuration:");
            jLabel8.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_lbl_configuracion"));
            optABC.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_ABC"));
            optAVL.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_AVL"));
            optRN.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_RN"));
            jLabel1.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_lbl_ejemplo"));
            jLabel2.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_lbl_clave"));
//            jLabel3.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_lbl_valor"));
            jLabel9.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_lbl_opciones"));
            OptInsert.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_insertar"));
            OptDelete.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_eliminar"));
            OptSearch.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_consultar"));
            OptSearchFather.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_padre"));
            OptCut.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_podar"));
            OptLeaves.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_hojas"));
            
            CmbExamples.removeAllItems();
            CmbExamples.addItem( "<TRIA UN>" );
            CmbExamples.addItem( "1" );
            CmbExamples.addItem( "2" );
            CmbExamples.addItem( "3" );
            CmbExamples.addItem( "4" );
            CmbExamples.addItem( "Nou" );
            //Ahora vamos a actualizar los messages de otros pantallas.
            //PAyudaRN.getAccessibleContext().setAccessibleName("Roig-Negre");
            TabHelp.setTitleAt( 2, "Roig-Negre" );
            TabHelpOperations.setTitleAt( 0, "Inserir" );
            TabHelpOperations.setTitleAt( 1, "Eliminar" );
            TabHelpOperations.setTitleAt( 2, "Consultar" );
            jTextArea2.setText("");
            jTextArea3.setText("");
            jTextArea4.setText("");
            jTextArea5.setText("");
            jTextArea6.setText("");
            jTextArea7.setText("");
            
            jTextArea3.append(ResourceBundle.getBundle("resources.properties.help_ca_ES").getString("descripcion1"));
            jTextArea2.append(ResourceBundle.getBundle("resources.properties.help_ca_ES").getString("descripcion2"));
            jTextArea7.append(ResourceBundle.getBundle("resources.properties.help_ca_ES").getString("descripcion3"));
            jTextArea4.append(ResourceBundle.getBundle("resources.properties.help_ca_ES").getString("descripcion4"));
            jTextArea5.append(ResourceBundle.getBundle("resources.properties.help_ca_ES").getString("descripcion5"));
            jTextArea6.append(ResourceBundle.getBundle("resources.properties.help_ca_ES").getString("descripcion6"));
            reloadScreen();
    }//GEN-LAST:event_BtnCatalanMouseClicked

    private void uncheked()
    {
        OptInsert.setSelected( false );
        OptSearch.setSelected( false );
        OptSearchFather.setSelected( false );
        OptCut.setSelected( false );
        OptLeaves.setSelected( false );
        OptDelete.setSelected( false );
        OptInsert.setEnabled( false );
        OptDelete.setEnabled( false );
        OptSearch.setEnabled( false );
        OptLeaves.setEnabled( false );
        OptCut.setEnabled( false );
        OptSearchFather.setEnabled( false );
        TxtKey.setText( null );
        TxtKey.setEnabled( false );
//        TxtValue.setText( null );
       // TxtValue.setEnabled( false );
        jLabel2.setEnabled( false );
      //  jLabel3.setEnabled( false );     
    }    
    
    private void CmbExamplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbExamplesActionPerformed
        // TODO add your handling code here:
        Integer indice = CmbExamples.getSelectedIndex();
        if ( indice.equals( 1 ) ) {    //Ejemplo nº 1
            uncheked();
            printConsole("Seleccionamos el example nº 1 \n", "Select the example number 1 \n", "Triem l'exemple nº 1 \n");
            //Iniciamos el example:
        } else if ( indice.equals( 2 ) ) {          //Ejemplo nº 2
            uncheked();
            printConsole("Seleccionamos el example nº 2 \n", "Select the example number 2 \n", "Triem l'exemple nº 2 \n");
            //Iniciamos el example:
        } else if ( indice.equals( 3 ) ) {//Ejemplo nº 3
            uncheked();
            printConsole("Seleccionamos el example nº 3 \n", "Select the example number 3 \n", "Triem l'exemple nº 3 \n");
            //Iniciamos el example:
        } else if ( indice.equals( 4 ) ) {  //Ejemplo nº 4  
            uncheked();
            printConsole("Seleccionamos el example nº 4 \n", "Select the example number 4 \n", "Triem l'exemple nº 4 \n");
            //Iniciamos el example:
        } else if ( indice.equals( 5 ) ) {
            //Introducimos un arbol desde cero nosotros mismos, por lo que hay 
            //que desbloquear el cuadro de opciones
            OptInsert.setSelected( true );
            TxtKey.setText( null );
            TxtKey.setEnabled( true );
            OptInsert.setEnabled( true );
            BtmNext.setEnabled( true );            
        //    TxtValor.setText( null );
        //    TxtValor.setEnabled( true );
            jLabel2.setEnabled( true );
           // jLabel3.setEnabled( true );
            printConsole("Creamos un árbol desde cero --> \n", "Make a new tree --> \n", "Creem un arbre de zero --> \n");
        }
    }//GEN-LAST:event_CmbExamplesActionPerformed

    private void BtmNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtmNextMouseClicked

    
    }//GEN-LAST:event_BtmNextMouseClicked

    private void optABCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optABCMouseClicked
        //Imprimimos por la consola la acción seleccionada
        printConsole("Seleccionamos un árbol ABC \n", "Select an ABC tree \n", "Triem un arbre ABC \n");
    }//GEN-LAST:event_optABCMouseClicked

    private void optAVLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optAVLMouseClicked
        printConsole("Seleccionamos un árbol AVL \n", "Select an AVL tree \n", "Triem un arbre AVL \n");
    }//GEN-LAST:event_optAVLMouseClicked

    private void optRNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optRNMouseClicked
        printConsole("Seleccionamos un árbol Rojo-Negro \n", "Select an Red-Black tree \n", "Triem un arbre Roig-Negre \n");
    }//GEN-LAST:event_optRNMouseClicked

    private void OptInsertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OptInsertMouseClicked
    }//GEN-LAST:event_OptInsertMouseClicked

    private void OptSearchFatherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptSearchFatherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OptSearchFatherActionPerformed

    private void OptLeavesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptLeavesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OptLeavesActionPerformed

    private void OptCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptCutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OptCutActionPerformed

    private void optABCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optABCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optABCActionPerformed

    private void OptInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OptInsertActionPerformed

    private void OptDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OptDeleteActionPerformed

    private void optRNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optRNActionPerformed

    private void BtmNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtmNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtmNextActionPerformed

    private void jInternalFrame1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInternalFrame1MouseClicked
        // TODO add your handling code here:        
          //  
    }//GEN-LAST:event_jInternalFrame1MouseClicked

    private void BtmNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtmNextMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BtmNextMouseEntered

    private void BtmStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtmStopActionPerformed
        // TODO add your handling code here:        
        ep.reinitCount();
        ep.example1 = new int[ 9 ];
        ep.example2 = new int[ 9 ];
        ep = new runStep();
        BtmNext.addActionListener( ep );            
         // REINICIAMOS TODOS LOS VALORES
        optABC.setEnabled( true );
        optAVL.setEnabled( true );
        optRN.setEnabled( true );
        CmbExamples.setEnabled( true );
        optABC.setSelected( false );
        optAVL.setSelected( false );
        CmbExamples.setSelectedIndex( 0 );        
        uncheked();       
        jInternalFrame1.getContentPane().setVisible( false );
        //Escribimos en la consola:    
        this.simulator.deleteTree();
        this.AVLsimulator.deleteTree();
        this.RBSimulator.deleteTree();        
        printConsole("Reiniciamos el programa..... \n", "Restart the program..... \n", "Reiniciem el programa..... \n");
        reloadScreen();
        
        Rectangle size = this.jInternalFrame1.getBounds();
        this.jInternalFrame1 = null;
        this.jInternalFrame1 = new JInternalFrame( "Representación gráfica" , true );
        this.jDesktopPane1.add( this.jInternalFrame1, JLayeredPane.DEFAULT_LAYER );
        this.jInternalFrame1.setVisible( true );
        this.jInternalFrame1.setBounds( size );
        this.jInternalFrame1.setEnabled( false );  
        jInternalFrame1.getContentPane().setVisible( true );        
    }//GEN-LAST:event_BtmStopActionPerformed

    private void OptSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OptSearchActionPerformed

    private void BtnSpainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSpainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSpainActionPerformed
 
    
    /**
     * @param args the command line arguments
     */
    public static void main( String args[] ) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Trees().setVisible( true );
            }
        });
    }

   /* public void go(int line, JTextArea txtAlgorithm){
        String[]t=txtAlgorithm.getText().split("\n");
        int position=0;
        for(int index=0;index<t.length;index++){
		if(index == line-1)break;
		if(t[index].length()!=0)
		        position+=t[index].length();
	}
	txtAlgorithm.moveCaretPosition(position+line);
}*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtmExitConfig1;
    private javax.swing.JButton BtmExitConfig2;
    private javax.swing.JButton BtmExitHelp;
    private javax.swing.JButton BtmHelp;
    private javax.swing.JButton BtmNext;
    private javax.swing.JButton BtmPlay;
    private javax.swing.JButton BtmReload;
    private javax.swing.JButton BtmStop;
    private javax.swing.JButton BtnCatalan;
    private javax.swing.JButton BtnEnglish;
    private javax.swing.JButton BtnExit;
    private javax.swing.JButton BtnSpain;
    private javax.swing.JComboBox<String> CmbExamples;
    private javax.swing.JFrame FrmConfigKeys;
    private javax.swing.JFrame FrmConfigTree;
    private javax.swing.JFrame FrmHelp;
    private javax.swing.ButtonGroup GOperations;
    private javax.swing.ButtonGroup GTrees;
    private javax.swing.JRadioButton OptCut;
    private javax.swing.JRadioButton OptDelete;
    private javax.swing.JRadioButton OptInsert;
    private javax.swing.JRadioButton OptLeaves;
    private javax.swing.JRadioButton OptSearch;
    private javax.swing.JRadioButton OptSearchFather;
    private javax.swing.JPanel PAyudaConsultar;
    private javax.swing.JPanel PAyudaEliminar;
    private javax.swing.JPanel PAyudaInsertar;
    private javax.swing.JPanel PHelpABC;
    private javax.swing.JPanel PHelpAVL;
    private javax.swing.JPanel PHelpRN;
    private javax.swing.JTabbedPane TabHelp;
    private javax.swing.JTabbedPane TabHelpOperations;
    private javax.swing.JTextArea TxtConsole;
    private javax.swing.JTextField TxtKey;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JRadioButton optABC;
    private javax.swing.JRadioButton optAVL;
    private javax.swing.JRadioButton optRN;
    private javax.swing.JTextArea txtAlgorithm;
    // End of variables declaration//GEN-END:variables

class runStep implements ActionListener {    
    int cont=0;
    int[] example1=new int[ 9 ];
    int[] example2=new int[ 9 ];
    int[] example3=new int[ 4 ];
    int[] example4=new int[ 4 ];
    public runStep ()    {
        /* example1[0]=22;
        example1[1]=33;
        example1[2]=44;
        example1[3]=23;
        example1[4]=11;
        example1[5]=15;
        example1[6]=60;
        example1[7]=38;
        example1[8]=72;

        example2[0]=13;
        example2[1]=1;
        example2[2]=17;
        example2[3]=15;
        example2[4]=25;
        example2[5]=6;
        example2[6]=11;
        example2[7]=22;
        example2[8]=27;*/

        example1[0]=1;
        example1[1]=8;
        example1[2]=5;
        example1[3]=4;
        example1[4]=5;
        example1[5]=3;
        example1[6]=7;
        example1[7]=2;
        example1[8]=9;

        example2[0]=13;
        example2[1]=14;
        example2[2]=17;
        example2[3]=15;
        example2[4]=25;
        example2[5]=16;
        example2[6]=11;
        example2[7]=22;
        example2[8]=27;               
    }
    
    public void reinitCount()    {            
        cont=0; 
        System.out.println("key cont INICIALIZADO " + cont);
    }

    @Override
    public void actionPerformed(ActionEvent e) {            
        System.out.println("key conT " + cont);
        if ( (CmbExamples.getSelectedIndex() == 1 && cont<example1.length)) {
            CmbExamples.setEnabled( false );
            //Carga el example 1
            addKey( example1[ cont ] );
            cont++;
        } else if ( CmbExamples.getSelectedIndex() == 2  && cont < example2.length ) {
            CmbExamples.setEnabled( false );
            //Carga el example 2
            addKey( example2[ cont ] );
            cont++;
        } 
        if ( OptDelete.isSelected() ) {
            Integer i = new Integer ( TxtKey.getText() );
            operation = "Eliminar";
            printConsole("Eliminar (" + TxtKey.getText() + ") \n", "Delete (" + TxtKey.getText() + ") \n", "Eliminar (" + TxtKey.getText() + ") \n");
            deleteKey( i.intValue() );
        }

        OptInsert.setEnabled( true );
        OptDelete.setEnabled( true );
        OptSearch.setEnabled( true );
        OptSearchFather.setEnabled( true ); 
        OptCut.setEnabled( true );
        OptLeaves.setEnabled( true );
        TxtKey.setEnabled( true );
   //     TxtValue.setEnabled( true );
        jLabel2.setEnabled( true );
     //   jLabel3.setEnabled( true );
        BtmNext.setEnabled( true );

    }
    
    }



}
