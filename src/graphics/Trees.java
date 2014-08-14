package graphics;

import datastructures.BasicNode;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import datastructures.Simulator;
import java.util.ResourceBundle;
import javax.swing.JTextArea;
import javax.swing.text.Caret;

/**
 *
 * @author BelenPatricia
 * @param <T>
 */
public class Trees  <T extends Comparable> extends javax.swing.JFrame {

    private int WARNING_MESSAGE;
    public String language; //castellano, catalan, ingles
    public String path;
    public String operation;
    public runStep ep = new runStep();
    public Simulator simulator;
    public boolean seleccionarbol = false;
    public static String chosse;
    /**
     * Creates new form ARBRES
     */
    public Trees() {
        initComponents();
        this.setLocationRelativeTo(null);
        frmconfigtree.setLocationRelativeTo(null);
        frmconfigkeys.setLocationRelativeTo(null);

        uncheked();

        btmnext.setEnabled(true);
        btmnext.addActionListener(ep);
        btnspain.setEnabled(false);//por defecto queda arrancado el programa en castellano
        language = "castellano";
    }

    public void reloadScreen() {
        SwingUtilities.updateComponentTreeUI(this);
        this.validate();
        SwingUtilities.updateComponentTreeUI(frmhelp);
        frmhelp.validate();
    }

    public void printConsole(String messageC, String messageI, String messageCat) {
        switch (language) {
            case "castellano":
                txtconsole.append(messageC);
                break;
            case "ingles":
                txtconsole.append(messageI);
                break;
            case "catalan":
                txtconsole.append(messageCat);
                break;
        }
    }

    public void addKey(int key) throws ClassNotFoundException {
        operation = "Insertar";
        printConsole("Insertar (" + key + ") \n", "Add (" + txtkey.getText() + ") \n", "Inserir (" + txtkey.getText() + ") \n");
        //  go (2, txtalgorithm); 
        if (!this.simulator.add(key)) {
            System.out.println("Errorrrrrrr ABB");
        } else {
            this.repaintTree();
        }
    }

    public void findKey(int key) {
        String msgexit ;
        msgexit = this.simulator.isHere(key);
        printConsole(msgexit, msgexit, msgexit);
    }

    public void deleteKey(int key) {
        operation = "Eliminar";
        printConsole("Eliminar (" + key + "," + key + ") \n", "Add (" + txtkey.getText() + ") \n", "Inserir (" + txtkey.getText() + ",) \n");
        String msgexit = "";
        String delete;
        delete = this.simulator.delete(key);
        this.repaintTree();
        printConsole(msgexit, msgexit, msgexit);
    }

    public void cutLeaves() {
        this.simulator.cutLeaves();
        this.repaintTree();
        printConsole("podado", "podado", "podado");
    }

    public void getFather(Integer i) throws InstantiationException, IllegalAccessException {
        String msgexit = "";
        System.out.println(" Treees.getFather " + i);
        msgexit = this.simulator.getFather(i);
        printConsole(msgexit, msgexit, msgexit);
    }

    public void getLeaves() {
        String msgexit = "";
        System.out.println(" Treees.getLeaves ");
        msgexit = this.simulator.getLeaves();
        printConsole(msgexit, msgexit, msgexit);
    }

    public void repaintTree() {
        Rectangle size = this.jInternalFrame1.getBounds();
        this.jInternalFrame1 = null;
        this.jInternalFrame1 = new JInternalFrame("Representación gráfica", true);
        this.jDesktopPane1.add(this.jInternalFrame1, JLayeredPane.DEFAULT_LAYER);
        this.jInternalFrame1.setVisible(true);
        this.jInternalFrame1.setBounds(size);
        this.jInternalFrame1.setEnabled(false);
        this.jInternalFrame1.add(this.simulator.getPaint(), BorderLayout.CENTER);
        jInternalFrame1.getContentPane().setVisible(true);
    }

    public void activeOptions() {
        optinsert.setEnabled(true);
        optdelete.setEnabled(true);
        optsearch.setEnabled(true);
        optsearchfather.setEnabled(true);
        optcut.setEnabled(true);
        optleaves.setEnabled(true);
        txtkey.setEnabled(true);
        jLabel2.setEnabled(true);
        btmnext.setEnabled(true);
    }

    public boolean minimParameters() {
        if (optabc.isSelected() == false && optavl.isSelected() == false && optrn.isSelected() == false) {
            switch (language) {
                case "castellano":
                    JOptionPane.showMessageDialog(null, "SELECCIONA UN TIPO DE ARBOL", "Configuración", WARNING_MESSAGE);
                    break;
                case "ingles":
                    JOptionPane.showMessageDialog(null, "SELECT A TREE", "Configuration", WARNING_MESSAGE);
                    break;
                case "catalan":
                    JOptionPane.showMessageDialog(null, "SELECCIONA UN TIPUS D'ARBRE", "Configuració", WARNING_MESSAGE);
                    break;
            }
            return false;
        } else {
            if (seleccionarbol == false) {
                if (optabc.isSelected()) {
                    this.simulator = new Simulator<>("abc");
                } else if (optavl.isSelected()) {
                    this.simulator = new Simulator<>("avl");
                } else if (optrn.isSelected()) {
                    this.simulator = new Simulator<>("rn");
                }
                seleccionarbol=true;
            }
            //Block controls 
            optabc.setEnabled(false);
            optavl.setEnabled(false);
            optrn.setEnabled(false);
            return true;
        }
    }

    public boolean comboSelectionExamples() throws ClassNotFoundException {

        if (cmbexamples.getSelectedIndex() == 0) {
            switch (language) {
                case "castellano":
                    JOptionPane.showMessageDialog(null, "SELECCIONA UN EJEMPLO", "Configuración", WARNING_MESSAGE);
                    break;
                case "ingles":
                    JOptionPane.showMessageDialog(null, "SELECT AN EXAMPLE", "Configuration", WARNING_MESSAGE);
                    break;
                case "catalan":
                    JOptionPane.showMessageDialog(null, "SELECCIONA UN EXEMPLE", "Configuració", WARNING_MESSAGE);
                    break;
            }
            return true;
        } else if ((cmbexamples.getSelectedIndex() == 1) && cmbexamples.isEnabled()) {
            cmbexamples.setEnabled(false);
            //Carga el example 1
            addKey(22);
            addKey(33);
            addKey(44);
            addKey(23);
            addKey(11);
            addKey(15);
            addKey(60);
            addKey(38);
            addKey(72);
            return true;
        } else if (cmbexamples.getSelectedIndex() == 2 && cmbexamples.isEnabled()) {
            cmbexamples.setEnabled(false);
            //Carga el example 2
            addKey(13);
            addKey(8);
            addKey(1);
            addKey(17);
            addKey(15);
            addKey(25);
            addKey(6);
            addKey(11);
            addKey(22);
            addKey(27);
            return true;
        } else if (cmbexamples.getSelectedIndex() == 3 && cmbexamples.isEnabled()) {
            cmbexamples.setEnabled(false);
            //Carga el example 3
            addKey(4);
            addKey(2);
            addKey(6);
            addKey(1);
            addKey(3);
            addKey(5);
            addKey(8);
            addKey(7);
            addKey(9);
            return true;
        } else if (cmbexamples.getSelectedIndex() == 4 && cmbexamples.isEnabled()) {
            cmbexamples.setEnabled(false);
            //Carga el example 4            
            //rojo-negro http://www.cosc.canterbury.ac.nz/research/RG/alg/rbtree.gif
            addKey(38);
            addKey(13);
            addKey(51);
            addKey(10);
            addKey(12);
            addKey(40);
            addKey(84);
            addKey(25);
            return true;
        } else if (cmbexamples.getSelectedIndex() == 5 || !cmbexamples.isEnabled()) {
            cmbexamples.setEnabled(false);
            return false;
        }
        return false;
    }

    public void startAlgorithm() throws ClassNotFoundException {

        if (optinsert.isSelected()) {
            switch (language) {
                case "castellano":
                    txtalgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_es_ES").getString("descripcion2"));
                    break;
                case "ingles":
                    txtalgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_en_US").getString("descripcion2"));
                    break;
                case "catalan":
                    txtalgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_ca_ES").getString("descripcion2"));
                    break;
            }
            Integer i = new Integer(txtkey.getText());
            addKey(i.intValue());
        } else if (optdelete.isSelected()) {
            Integer i = new Integer(txtkey.getText());
            operation = "Eliminar";
            printConsole("Eliminar (" + txtkey.getText() + ") \n", "Delete (" + txtkey.getText() + ",) \n", "Eliminar (" + txtkey.getText() + ") \n");
            deleteKey(i.intValue());
        } else if (optsearch.isSelected()) {
            txtalgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_es_ES").getString("descripcion1"));
            Integer i = new Integer(txtkey.getText());
            operation = "Consultar";
            printConsole("Consultar (" + txtkey.getText() + ") \n", "Query (" + txtkey.getText() + ",) \n", "Consultar (" + txtkey.getText() + ") \n");
            findKey(i.intValue());
        } else if (optsearchfather.isSelected()) {
            Integer i = new Integer(txtkey.getText());
            operation = "ConsultarPadre";
            printConsole("Consultar (" + txtkey.getText() + ") \n", "Query (" + txtkey.getText() + ") \n", "Consultar (" + txtkey.getText() + ") \n");
            try {
                getFather(i);
            } catch (InstantiationException ex) {
                Logger.getLogger(Trees.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Trees.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (optcut.isSelected()) {
            operation = "Podar";
            cutLeaves();
        } else if (optleaves.isSelected()) {
            operation = "ConsultarHojas";
            getLeaves();
        }
    }

    public void go(int line, JTextArea txtAlgorithm) {
        String[] t = txtAlgorithm.getText().split("\n");
        int position = 0;
        line = 3;
        txtAlgorithm.setCaretColor(Color.red);

        for (int index = 0; index < line; index++) {
            position += t[index].length();
        }
        txtAlgorithm.setCaretPosition(position + 2);
        txtAlgorithm.moveCaretPosition(position + t[line].length() + 2);
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
        frmhelp = new javax.swing.JFrame();
        btmexithelp = new javax.swing.JButton();
        tabhelp = new javax.swing.JTabbedPane();
        phelpavl = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        phelpabc = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        phelprn = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        tabhelpoperations = new javax.swing.JTabbedPane();
        PAyudaInsertar = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        PAyudaEliminar = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        PAyudaConsultar = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        frmconfigtree = new javax.swing.JFrame();
        btmexitconfig1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        frmconfigkeys = new javax.swing.JFrame();
        jLabel5 = new javax.swing.JLabel();
        btmexitconfig2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        optabc = new javax.swing.JRadioButton();
        optavl = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cmbexamples = new javax.swing.JComboBox<String>();
        btmhelp = new javax.swing.JButton();
        btmreload = new javax.swing.JButton();
        btmplay = new javax.swing.JButton();
        btmnext = new javax.swing.JButton();
        btmstop = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        optinsert = new javax.swing.JRadioButton();
        optsearch = new javax.swing.JRadioButton();
        optdelete = new javax.swing.JRadioButton();
        txtkey = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        optsearchfather = new javax.swing.JRadioButton();
        optleaves = new javax.swing.JRadioButton();
        optcut = new javax.swing.JRadioButton();
        optrn = new javax.swing.JRadioButton();
        btnexit = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtalgorithm = new javax.swing.JTextArea();
        btnspain = new javax.swing.JButton();
        btnenglish = new javax.swing.JButton();
        btncatalan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtconsole = new javax.swing.JTextArea();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jInternalFrame1 = new javax.swing.JInternalFrame();

        frmhelp.setLocationByPlatform(true);
        frmhelp.setMinimumSize(new java.awt.Dimension(675, 513));
        frmhelp.setName("Ayuda"); // NOI18N
        frmhelp.setResizable(false);
        frmhelp.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                frmhelpComponentShown(evt);
            }
        });

        btmexithelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/exit.png"))); // NOI18N
        btmexithelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btmexithelpMouseClicked(evt);
            }
        });
        btmexithelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmexithelpActionPerformed(evt);
            }
        });

        tabhelp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabhelp.setName("Rojo-Negro"); // NOI18N

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(214, 217, 223));
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion2"));
        jTextArea2.setAutoscrolls(false);
        jTextArea2.setBorder(null);
        jTextArea2.setOpaque(false);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout phelpavlLayout = new javax.swing.GroupLayout(phelpavl);
        phelpavl.setLayout(phelpavlLayout);
        phelpavlLayout.setHorizontalGroup(
            phelpavlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phelpavlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap())
        );
        phelpavlLayout.setVerticalGroup(
            phelpavlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phelpavlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
        );

        tabhelp.addTab("AVL", phelpavl);

        jTextArea3.setEditable(false);
        jTextArea3.setBackground(new java.awt.Color(214, 217, 223));
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion1")
        );
        jTextArea3.setOpaque(false);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout phelpabcLayout = new javax.swing.GroupLayout(phelpabc);
        phelpabc.setLayout(phelpabcLayout);
        phelpabcLayout.setHorizontalGroup(
            phelpabcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phelpabcLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        phelpabcLayout.setVerticalGroup(
            phelpabcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phelpabcLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addContainerGap(165, Short.MAX_VALUE))
            .addGroup(phelpabcLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3))
        );

        tabhelp.addTab("ABC", phelpabc);

        jTextArea7.setEditable(false);
        jTextArea7.setBackground(new java.awt.Color(214, 217, 223));
        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jTextArea7.setText(ResourceBundle.getBundle("resources.properties.help_es_ES").getString("descripcion3"));
        jTextArea7.setOpaque(false);
        jScrollPane7.setViewportView(jTextArea7);

        javax.swing.GroupLayout phelprnLayout = new javax.swing.GroupLayout(phelprn);
        phelprn.setLayout(phelprnLayout);
        phelprnLayout.setHorizontalGroup(
            phelprnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phelprnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        phelprnLayout.setVerticalGroup(
            phelprnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );

        tabhelp.addTab("Rojo-Negro", phelprn);

        tabhelpoperations.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        tabhelpoperations.addTab("Insertar", PAyudaInsertar);

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

        tabhelpoperations.addTab("Eliminar", PAyudaEliminar);

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

        tabhelpoperations.addTab("Consultar", PAyudaConsultar);

        javax.swing.GroupLayout frmhelpLayout = new javax.swing.GroupLayout(frmhelp.getContentPane());
        frmhelp.getContentPane().setLayout(frmhelpLayout);
        frmhelpLayout.setHorizontalGroup(
            frmhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmhelpLayout.createSequentialGroup()
                .addGroup(frmhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btmexithelp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frmhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tabhelp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(tabhelpoperations, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        frmhelpLayout.setVerticalGroup(
            frmhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frmhelpLayout.createSequentialGroup()
                .addComponent(tabhelp, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabhelpoperations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btmexithelp))
        );

        tabhelp.getAccessibleContext().setAccessibleName("");
        tabhelp.getAccessibleContext().setAccessibleDescription("");

        frmconfigtree.setMinimumSize(new java.awt.Dimension(337, 139));
        frmconfigtree.setName("Configuración"); // NOI18N
        frmconfigtree.setResizable(false);

        btmexitconfig1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ok.png"))); // NOI18N
        btmexitconfig1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btmexitconfig1MouseClicked(evt);
            }
        });
        btmexitconfig1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmexitconfig1ActionPerformed(evt);
            }
        });

        jLabel4.setText("SELECCIONE UN TIPO DE ÁRBOL!!!");

        javax.swing.GroupLayout frmconfigtreeLayout = new javax.swing.GroupLayout(frmconfigtree.getContentPane());
        frmconfigtree.getContentPane().setLayout(frmconfigtreeLayout);
        frmconfigtreeLayout.setHorizontalGroup(
            frmconfigtreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frmconfigtreeLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(frmconfigtreeLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(btmexitconfig1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        frmconfigtreeLayout.setVerticalGroup(
            frmconfigtreeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frmconfigtreeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btmexitconfig1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        frmconfigkeys.setMinimumSize(new java.awt.Dimension(384, 137));
        frmconfigkeys.setResizable(false);

        jLabel5.setText("INTRODUZCA UNA CLAVE Y UN VALOR CORRECTOS");

        btmexitconfig2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ok.png"))); // NOI18N
        btmexitconfig2.setMaximumSize(new java.awt.Dimension(40, 40));
        btmexitconfig2.setMinimumSize(new java.awt.Dimension(40, 40));
        btmexitconfig2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btmexitconfig2MouseClicked(evt);
            }
        });
        btmexitconfig2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmexitconfig2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frmconfigkeysLayout = new javax.swing.GroupLayout(frmconfigkeys.getContentPane());
        frmconfigkeys.getContentPane().setLayout(frmconfigkeysLayout);
        frmconfigkeysLayout.setHorizontalGroup(
            frmconfigkeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmconfigkeysLayout.createSequentialGroup()
                .addGroup(frmconfigkeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frmconfigkeysLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frmconfigkeysLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btmexitconfig2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        frmconfigkeysLayout.setVerticalGroup(
            frmconfigkeysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frmconfigkeysLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btmexitconfig2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setName("Árboles"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bitstream Vera Sans Mono", 3, 14))); // NOI18N
        jPanel1.setToolTipText("");

        GTrees.add(optabc);
        optabc.setText("ABC");
        optabc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optabcMouseClicked(evt);
            }
        });
        optabc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optabcActionPerformed(evt);
            }
        });

        GTrees.add(optavl);
        optavl.setText("AVL");
        optavl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optavlMouseClicked(evt);
            }
        });
        optavl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optavlActionPerformed(evt);
            }
        });

        jLabel1.setText("Ejemplos:");

        cmbexamples.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<SELECCIONA UNO>", "1", "2", "3", "4", "NUEVO" }));
        cmbexamples.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbexamplesActionPerformed(evt);
            }
        });

        btmhelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1373669794_Folder-Info.png"))); // NOI18N
        btmhelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btmhelpMouseClicked(evt);
            }
        });
        btmhelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmhelpActionPerformed(evt);
            }
        });

        btmreload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1373669670_order.png"))); // NOI18N
        btmreload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btmreloadMouseClicked(evt);
            }
        });
        btmreload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmreloadActionPerformed(evt);
            }
        });

        btmplay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1373669712_start.png"))); // NOI18N
        btmplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btmplayMouseClicked(evt);
            }
        });
        btmplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmplayActionPerformed(evt);
            }
        });

        btmnext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1373669652_hide-right.png"))); // NOI18N
        btmnext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btmnextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btmnextMouseEntered(evt);
            }
        });
        btmnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmnextActionPerformed(evt);
            }
        });

        btmstop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1373669729_stop-red.png"))); // NOI18N
        btmstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmstopActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel9.setText("Opciones:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bitstream Vera Sans Mono", 3, 14))); // NOI18N

        GOperations.add(optinsert);
        optinsert.setText("Insertar");
        optinsert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optinsertMouseClicked(evt);
            }
        });
        optinsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optinsertActionPerformed(evt);
            }
        });

        GOperations.add(optsearch);
        optsearch.setText("Consultar");
        optsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optsearchActionPerformed(evt);
            }
        });

        GOperations.add(optdelete);
        optdelete.setText("Eliminar");
        optdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optdeleteActionPerformed(evt);
            }
        });

        jLabel2.setText("Clave:");

        GOperations.add(optsearchfather);
        optsearchfather.setText("Consultar Padre");
        optsearchfather.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optsearchfatherActionPerformed(evt);
            }
        });

        GOperations.add(optleaves);
        optleaves.setText("Consultar Hojas");
        optleaves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optleavesActionPerformed(evt);
            }
        });

        GOperations.add(optcut);
        optcut.setText("Podar");
        optcut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optcutActionPerformed(evt);
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
                            .addComponent(optdelete)
                            .addComponent(optsearch)
                            .addComponent(optcut)
                            .addComponent(optsearchfather))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtkey, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(optinsert)
                            .addComponent(optleaves))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(optinsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(optsearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(optdelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(optcut)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(38, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(optsearchfather, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(9, 9, 9)
                                .addComponent(txtkey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)))))
                .addComponent(optleaves)
                .addContainerGap())
        );

        GTrees.add(optrn);
        optrn.setText("Rojo y Negro");
        optrn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optrnMouseClicked(evt);
            }
        });
        optrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optrnActionPerformed(evt);
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
                        .addComponent(btmhelp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btmplay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btmnext, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btmstop, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btmreload, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(69, 69, 69)
                        .addComponent(cmbexamples, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(optavl)
                        .addGap(28, 28, 28)
                        .addComponent(optrn))
                    .addComponent(optabc))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optabc)
                    .addComponent(optavl)
                    .addComponent(optrn))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbexamples, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btmnext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btmplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btmhelp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btmstop, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btmreload, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        btnexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/exit.png"))); // NOI18N
        btnexit.setMaximumSize(new java.awt.Dimension(40, 40));
        btnexit.setMinimumSize(new java.awt.Dimension(40, 40));
        btnexit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnexitMouseClicked(evt);
            }
        });
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        txtalgorithm.setColumns(20);
        txtalgorithm.setRows(5);
        txtalgorithm.setSelectedTextColor(new java.awt.Color(255, 51, 51));
        jScrollPane8.setViewportView(txtalgorithm);

        btnspain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/spain.png"))); // NOI18N
        btnspain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnspainMouseClicked(evt);
            }
        });
        btnspain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnspainActionPerformed(evt);
            }
        });

        btnenglish.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/uk.png"))); // NOI18N
        btnenglish.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnenglishMouseClicked(evt);
            }
        });

        btncatalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/catalonia.png"))); // NOI18N
        btncatalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncatalanMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel8.setText("Configuración:");

        txtconsole.setEditable(false);
        txtconsole.setBackground(new java.awt.Color(214, 217, 223));
        txtconsole.setColumns(20);
        txtconsole.setRows(5);
        txtconsole.setAutoscrolls(false);
        txtconsole.setBorder(null);
        txtconsole.setOpaque(false);
        jScrollPane9.setViewportView(txtconsole);

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
                            .addComponent(btnexit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDesktopPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnspain, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnenglish, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncatalan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnenglish, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btncatalan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnspain, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void optavlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optavlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optavlActionPerformed

    private void btmreloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmreloadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmreloadActionPerformed

    private void btmplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmplayActionPerformed

    private void btmplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btmplayMouseClicked
        jInternalFrame1.setVisible(true);
        if (minimParameters() == true) {
            try {
                if (comboSelectionExamples() == false) {
                    if ((optinsert.isSelected() || optdelete.isSelected() || optsearch.isSelected()) && (txtkey.getText().trim().length() == 0)) {
                        //CLAVE O VALOR NO TIENEN NADA O INCLUSO LOS DOS ESTÁN VACÍOS
                        switch (language) {
                            case "castellano":
                                JOptionPane.showMessageDialog(null, "INTRODUZCA UN VALOR CORRECTOS.", "Opciones de Configuración", WARNING_MESSAGE);
                                break;
                            case "ingles":
                                JOptionPane.showMessageDialog(null, "INSERT CORRECT VALUE.", "Configuration Options", WARNING_MESSAGE);
                                break;
                            case "catalan":
                                JOptionPane.showMessageDialog(null, "INTRODUEIX UN VALOR CORRECTE.", "Opcions de Configuració", WARNING_MESSAGE);
                                break;
                        }
                    } else {
                        startAlgorithm();
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Trees.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        activeOptions();
    }//GEN-LAST:event_btmplayMouseClicked
    private void btmhelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btmhelpMouseClicked
        // TODO add your handling code here:
        frmhelp.setLocationRelativeTo(null);
        frmhelp.setVisible(true);

    }//GEN-LAST:event_btmhelpMouseClicked

    private void btmexithelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btmexithelpMouseClicked
        // TODO add your handling code here:
        frmhelp.setVisible(false);
    }//GEN-LAST:event_btmexithelpMouseClicked

    private void btmexithelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmexithelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmexithelpActionPerformed

    private void frmhelpComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_frmhelpComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_frmhelpComponentShown

    private void btmhelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmhelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmhelpActionPerformed

    private void btnexitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnexitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnexitMouseClicked

    private void btmexitconfig1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btmexitconfig1MouseClicked
        // TODO add your handling code here:
        frmconfigtree.setVisible(false);
    }//GEN-LAST:event_btmexitconfig1MouseClicked

    private void btmexitconfig1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmexitconfig1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmexitconfig1ActionPerformed

    private void btmexitconfig2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btmexitconfig2MouseClicked
        // TODO add your handling code here:
        frmconfigkeys.setVisible(false);
    }//GEN-LAST:event_btmexitconfig2MouseClicked

    private void btmexitconfig2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmexitconfig2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmexitconfig2ActionPerformed

    private void btmreloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btmreloadMouseClicked
        // REINICIAMOS TODOS LOS VALORES
        optabc.setEnabled(true);
        optavl.setEnabled(true);
        optrn.setEnabled(true);
        cmbexamples.setEnabled(true);
        optabc.setSelected(false);
        optavl.setSelected(false);
        optrn.setSelected(false);
        cmbexamples.setSelectedIndex(0);
        txtalgorithm.setText("");
        uncheked();
        seleccionarbol=false;
        jInternalFrame1.getContentPane().setVisible(false);
        this.simulator.deleteTree();
        this.simulator = new Simulator<>();
        printConsole("Reiniciamos el programa..... \n", "Restart the program..... \n", "Reiniciem el programa..... \n");
        reloadScreen();

    }//GEN-LAST:event_btmreloadMouseClicked

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnexitActionPerformed

    private void btnenglishMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnenglishMouseClicked

        language = "ingles";
        //activamos el boton del idioma que estaba desactivado y traducimos toda la interfaz.
        btnspain.setEnabled(true);
        btncatalan.setEnabled(true);
        btnenglish.setEnabled(false);
        //jPanel1.setToolTipText("Configuration:");
        jLabel8.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_lbl_configuracion"));
        optabc.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_ABC"));
        optavl.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_AVL"));
        optrn.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_RN"));
        jLabel1.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_lbl_ejemplo"));
        jLabel2.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_lbl_clave"));
//            jLabel3.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_lbl_valor"));
        jLabel9.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_lbl_opciones"));
        optinsert.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_insertar"));
        optdelete.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_eliminar"));
        optsearch.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_consultar"));
        optsearchfather.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_padre"));
        optcut.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_podar"));
        optleaves.setText(ResourceBundle.getBundle("resources.properties.file_en_US").getString("etiqueta_opt_hojas"));
        if (optinsert.isSelected() == true) {
            txtalgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_en_US").getString("descripcion2"));
        }
        if (optsearch.isSelected() == true) {
            txtalgorithm.setText(ResourceBundle.getBundle("resources.properties.functions_en_US").getString("descripcion1"));
        }
        //FALTA AGREGAR EL RESTO DE PSEUDOCÓDIGOS
        cmbexamples.removeAllItems();
        cmbexamples.addItem("<SELECT ONE>");
        cmbexamples.addItem("1");
        cmbexamples.addItem("2");
        cmbexamples.addItem("3");
        cmbexamples.addItem("4");
        cmbexamples.addItem("New");
        //Ahora vamos a actualizar los messages de otros pantallas.
        tabhelp.setTitleAt(2, "Red-Black");
        tabhelpoperations.setTitleAt(0, "Insert");
        tabhelpoperations.setTitleAt(1, "Delete");
        tabhelpoperations.setTitleAt(2, "Query");
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
    }//GEN-LAST:event_btnenglishMouseClicked

    private void btnspainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnspainMouseClicked
        language = "castellano";
        //activamos el boton del idioma que estaba desactivado y traducimos toda la interfaz.
        btnspain.setEnabled(false);
        btncatalan.setEnabled(true);
        btnenglish.setEnabled(true);
        jLabel8.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_lbl_configuracion"));
        optabc.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_ABC"));
        optavl.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_AVL"));
        optrn.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_RN"));
        jLabel1.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_lbl_ejemplo"));
        jLabel2.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_lbl_clave"));
//            jLabel3.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_lbl_valor"));
        jLabel9.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_lbl_opciones"));
        optinsert.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_insertar"));
        optdelete.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_eliminar"));
        optsearch.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_consultar"));
        optsearchfather.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_padre"));
        optcut.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_podar"));
        optleaves.setText(ResourceBundle.getBundle("resources.properties.file_es_ES").getString("etiqueta_opt_hojas"));

        cmbexamples.removeAllItems();
        cmbexamples.addItem("<SELECCIONA UNO>");
        cmbexamples.addItem("1");
        cmbexamples.addItem("2");
        cmbexamples.addItem("3");
        cmbexamples.addItem("4");
        cmbexamples.addItem("Nuevo");
        //Ahora vamos a actualizar los messages de otros pantallas.
        tabhelp.setTitleAt(2, "Rojo-Negro");
        tabhelpoperations.setTitleAt(0, "Insertar");
        tabhelpoperations.setTitleAt(1, "Eliminar");
        tabhelpoperations.setTitleAt(2, "Consultar");
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
    }//GEN-LAST:event_btnspainMouseClicked

    private void btncatalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncatalanMouseClicked

        language = "catalan";
        //activamos el boton del idioma que estaba desactivado y traducimos toda la interfaz.
        btnspain.setEnabled(true);
        btncatalan.setEnabled(false);
        btnenglish.setEnabled(true);
        //jPanel1.setToolTipText("Configuration:");
        jLabel8.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_lbl_configuracion"));
        optabc.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_ABC"));
        optavl.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_AVL"));
        optrn.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_RN"));
        jLabel1.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_lbl_ejemplo"));
        jLabel2.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_lbl_clave"));
//            jLabel3.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_lbl_valor"));
        jLabel9.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_lbl_opciones"));
        optinsert.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_insertar"));
        optdelete.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_eliminar"));
        optsearch.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_consultar"));
        optsearchfather.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_padre"));
        optcut.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_podar"));
        optleaves.setText(ResourceBundle.getBundle("resources.properties.file_ca_ES").getString("etiqueta_opt_hojas"));

        cmbexamples.removeAllItems();
        cmbexamples.addItem("<TRIA UN>");
        cmbexamples.addItem("1");
        cmbexamples.addItem("2");
        cmbexamples.addItem("3");
        cmbexamples.addItem("4");
        cmbexamples.addItem("Nou");
        //Ahora vamos a actualizar los messages de otros pantallas.
        //PAyudaRN.getAccessibleContext().setAccessibleName("Roig-Negre");
        tabhelp.setTitleAt(2, "Roig-Negre");
        tabhelpoperations.setTitleAt(0, "Inserir");
        tabhelpoperations.setTitleAt(1, "Eliminar");
        tabhelpoperations.setTitleAt(2, "Consultar");
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
    }//GEN-LAST:event_btncatalanMouseClicked

    private void uncheked() {
        optinsert.setSelected(false);
        optsearch.setSelected(false);
        optsearchfather.setSelected(false);
        optcut.setSelected(false);
        optleaves.setSelected(false);
        optdelete.setSelected(false);
        optinsert.setEnabled(false);
        optdelete.setEnabled(false);
        optsearch.setEnabled(false);
        optleaves.setEnabled(false);
        optcut.setEnabled(false);
        optsearchfather.setEnabled(false);
        txtkey.setText(null);
        txtkey.setEnabled(false);
        jLabel2.setEnabled(false);
    }

    private void cmbexamplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbexamplesActionPerformed
        Integer index = cmbexamples.getSelectedIndex();
        if (index > 0 && index <= 4) {
            uncheked();
            String cast = String.format("Seleccionamos el ejemplo nº %1$d\n", index);
            String eng = String.format("Select the example number %1$d\n", index);
            String cat = String.format("Triem l'exemple nº %1$d\n", index);
            printConsole(cast, eng, cat);
        } else if (index == 5) {
            optinsert.setSelected(true);
            txtkey.setText(null);
            txtkey.setEnabled(true);
            optinsert.setEnabled(true);
            btmnext.setEnabled(true);
            jLabel2.setEnabled(true);
            printConsole("Creamos un árbol desde cero --> \n", "Make a new tree --> \n", "Creem un arbre de zero --> \n");
        }
    }//GEN-LAST:event_cmbexamplesActionPerformed

    private void btmnextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btmnextMouseClicked
    }//GEN-LAST:event_btmnextMouseClicked

    private void optabcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optabcMouseClicked
        //Imprimimos por la consola la acción seleccionada
        printConsole("Seleccionamos un árbol ABC \n", "Select an ABC tree \n", "Triem un arbre ABC \n");
    }//GEN-LAST:event_optabcMouseClicked

    private void optavlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optavlMouseClicked
        printConsole("Seleccionamos un árbol AVL \n", "Select an AVL tree \n", "Triem un arbre AVL \n");
    }//GEN-LAST:event_optavlMouseClicked

    private void optrnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optrnMouseClicked
        printConsole("Seleccionamos un árbol Rojo-Negro \n", "Select an Red-Black tree \n", "Triem un arbre Roig-Negre \n");
    }//GEN-LAST:event_optrnMouseClicked

    private void optinsertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optinsertMouseClicked
    }//GEN-LAST:event_optinsertMouseClicked

    private void optsearchfatherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optsearchfatherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optsearchfatherActionPerformed

    private void optleavesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optleavesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optleavesActionPerformed

    private void optcutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optcutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optcutActionPerformed

    private void optabcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optabcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optabcActionPerformed

    private void optinsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optinsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optinsertActionPerformed

    private void optdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optdeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optdeleteActionPerformed

    private void optrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optrnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optrnActionPerformed

    private void btmnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmnextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmnextActionPerformed

    private void jInternalFrame1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jInternalFrame1MouseClicked
        // TODO add your handling code here:        
        //  
    }//GEN-LAST:event_jInternalFrame1MouseClicked

    private void btmnextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btmnextMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btmnextMouseEntered

    private void btmstopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmstopActionPerformed
        // TODO add your handling code here:        
        ep.reinitCount();
        ep.example1 = new int[9];
        ep.example2 = new int[9];
        ep = new runStep();
        btmnext.addActionListener(ep);
        // REINICIAMOS TODOS LOS VALORES
        optabc.setEnabled(true);
        optavl.setEnabled(true);
        optrn.setEnabled(true);
        cmbexamples.setEnabled(true);
        optabc.setSelected(false);
        optavl.setSelected(false);
        cmbexamples.setSelectedIndex(0);
        uncheked();
        jInternalFrame1.getContentPane().setVisible(false);
        //Escribimos en la consola:    
        this.simulator.deleteTree();
        printConsole("Reiniciamos el programa..... \n", "Restart the program..... \n", "Reiniciem el programa..... \n");
        reloadScreen();

        Rectangle size = this.jInternalFrame1.getBounds();
        this.jInternalFrame1 = null;
        this.jInternalFrame1 = new JInternalFrame("Representación gráfica", true);
        this.jDesktopPane1.add(this.jInternalFrame1, JLayeredPane.DEFAULT_LAYER);
        this.jInternalFrame1.setVisible(true);
        this.jInternalFrame1.setBounds(size);
        this.jInternalFrame1.setEnabled(false);
        jInternalFrame1.getContentPane().setVisible(true);
    }//GEN-LAST:event_btmstopActionPerformed

    private void optsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optsearchActionPerformed

    private void btnspainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnspainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnspainActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Trees().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GOperations;
    private javax.swing.ButtonGroup GTrees;
    private javax.swing.JPanel PAyudaConsultar;
    private javax.swing.JPanel PAyudaEliminar;
    private javax.swing.JPanel PAyudaInsertar;
    private javax.swing.JButton btmexitconfig1;
    private javax.swing.JButton btmexitconfig2;
    private javax.swing.JButton btmexithelp;
    private javax.swing.JButton btmhelp;
    private javax.swing.JButton btmnext;
    private javax.swing.JButton btmplay;
    private javax.swing.JButton btmreload;
    private javax.swing.JButton btmstop;
    private javax.swing.JButton btncatalan;
    private javax.swing.JButton btnenglish;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnspain;
    private javax.swing.JComboBox<String> cmbexamples;
    private javax.swing.JFrame frmconfigkeys;
    private javax.swing.JFrame frmconfigtree;
    private javax.swing.JFrame frmhelp;
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
    private javax.swing.JRadioButton optabc;
    private javax.swing.JRadioButton optavl;
    private javax.swing.JRadioButton optcut;
    private javax.swing.JRadioButton optdelete;
    private javax.swing.JRadioButton optinsert;
    private javax.swing.JRadioButton optleaves;
    private javax.swing.JRadioButton optrn;
    private javax.swing.JRadioButton optsearch;
    private javax.swing.JRadioButton optsearchfather;
    private javax.swing.JPanel phelpabc;
    private javax.swing.JPanel phelpavl;
    private javax.swing.JPanel phelprn;
    private javax.swing.JTabbedPane tabhelp;
    private javax.swing.JTabbedPane tabhelpoperations;
    private javax.swing.JTextArea txtalgorithm;
    private javax.swing.JTextArea txtconsole;
    private javax.swing.JTextField txtkey;
    // End of variables declaration//GEN-END:variables

    class runStep implements ActionListener {

        int cont = 0;
        int[] example1 = new int[9];
        int[] example2 = new int[9];
        int[] example3 = new int[4];
        int[] example4 = new int[4];

        public runStep() {
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

            example1[0] = 1;
            example1[1] = 8;
            example1[2] = 5;
            example1[3] = 4;
            example1[4] = 5;
            example1[5] = 3;
            example1[6] = 7;
            example1[7] = 2;
            example1[8] = 9;

            example2[0] = 13;
            example2[1] = 14;
            example2[2] = 17;
            example2[3] = 15;
            example2[4] = 25;
            example2[5] = 16;
            example2[6] = 11;
            example2[7] = 22;
            example2[8] = 27;
        }

        public void reinitCount() {
            cont = 0;
            System.out.println("key cont INICIALIZADO " + cont);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("key conT " + cont);
            if ((cmbexamples.getSelectedIndex() == 1 && cont < example1.length)) {
                cmbexamples.setEnabled(false);
                try {
                    //Carga el example 1
                    addKey(example1[ cont]);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Trees.class.getName()).log(Level.SEVERE, null, ex);
                }
                cont++;
            } else if (cmbexamples.getSelectedIndex() == 2 && cont < example2.length) {
                cmbexamples.setEnabled(false);
                try {
                    //Carga el example 2
                    addKey(example2[ cont]);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Trees.class.getName()).log(Level.SEVERE, null, ex);
                }
                cont++;
            }
            if (optdelete.isSelected()) {
                Integer i = new Integer(txtkey.getText());
                operation = "Eliminar";
                printConsole("Eliminar (" + txtkey.getText() + ") \n", "Delete (" + txtkey.getText() + ") \n", "Eliminar (" + txtkey.getText() + ") \n");
                deleteKey(i);
            }

            optinsert.setEnabled(true);
            optdelete.setEnabled(true);
            optsearch.setEnabled(true);
            optsearchfather.setEnabled(true);
            optcut.setEnabled(true);
            optleaves.setEnabled(true);
            txtkey.setEnabled(true);
            //     TxtValue.setEnabled( true );
            jLabel2.setEnabled(true);
            //   jLabel3.setEnabled( true );
            btmnext.setEnabled(true);

        }
    }
}
