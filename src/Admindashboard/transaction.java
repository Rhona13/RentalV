/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admindashboard;

import Config.DBConnector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.JOptionPane;
import static javax.swing.SwingWorker.StateValue.STARTED;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author SSC-COLLEGE
 */
public class transaction extends javax.swing.JInternalFrame {

    /**
     * Creates new form transaction
     */
    public transaction() {
        initComponents();
    
     gslip();
          this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        
       
         
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
     STARTED.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.setTime((Date) evt.getNewValue());
                if (selectedDate.before(currentDate)) {
                    STARTED.setDate(currentDate.getTime());
                     DUEDATE.setDate(currentDate.getTime());
                }
            }
     
     });
     
    }
   
    private Connection connect;
    private void getlease(){
                   int houseID= Integer.parseInt(HID.getText());
                   try{
                    connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/renatal", "root", "");
                    PreparedStatement pst = connect.prepareStatement("SELECT * FROM houses where h_id =?");
                    pst.setInt(1, houseID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()){
                    id.setText(rs.getString("h_id"));
                    hn.setText(rs.getString("h_number"));
                    price.setText(rs.getString("h_price"));
                    elec.setText(rs.getString("h_electricity"));
                    water.setText(rs.getString("h_water"));
                    security.setText(rs.getString("h_security"));
                    maintenance.setText(rs.getString("h_maintenance"));
                    total.setText(rs.getString("h_total"));
                      HouseError.setText("");
                    }else{
                    HouseError.setText("INVALID HOUSE ID");
                    }
                   }catch (SQLException ex){
                   ex.printStackTrace();

                   }
                   }

                   private void gettenants(){
                   int rentID= Integer.parseInt(OID.getText());
                   try{
                    connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/renatal", "root", "");
                    PreparedStatement pst = connect.prepareStatement("SELECT * FROM occupant where o_id =?");
                    pst.setInt(1,rentID );
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()){
                    oid.setText(rs.getString("o_id"));
                    ofn.setText(rs.getString("o_firstname"));
                    oln.setText(rs.getString("o_lastname"));
                    ocn.setText(rs.getString("o_contact"));
                    onm.setSelectedItem(rs.getString("o_members"));
                    OccupantError.setText("");
                    }else{
                    OccupantError.setText("INVALID OCCUPANT ID");
                            }
                   }catch (SQLException ex){
                   ex.printStackTrace();
                   }
                   }
                   
                    public boolean start(){
                       boolean started = false;
                   int houseID= Integer.parseInt(HID.getText());
                    int rentID = Integer.parseInt(OID.getText());
                    String housenumber= hn.getText();
                    String ofirstname=ofn.getText();
                    String olastname=oln.getText();

                    java.util.Date tstareddate= STARTED.getDate();
                    java.util.Date tenddate=DUEDATE.getDate();

                    Long l1= tstareddate.getTime();
                    long l2=tenddate.getTime();

                    java.sql.Date datestarted = new java.sql.Date(l1);
                    java.sql.Date dateended = new java.sql.Date(l2);

                       try {
                       connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/renatal", "root", "");
                       String sql = "insert into issue(h_id,o_id,started_date,end_date,is_status) values(?,?,?,?,?)";

                       PreparedStatement pst = connect.prepareStatement(sql);
                    pst.setInt(1, houseID);
                    pst.setInt(2, rentID);
                    pst.setDate(3, datestarted);
                    pst.setDate(4, dateended);
                    pst.setString(5, "Available");


                    int rowCount= pst.executeUpdate();
                    if(rowCount > 0){
                     started = true;
                    }else{
                    started = false;
                    }

                       } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);

                       }
                    return started;
                   }

                   //CHECK IF BOOK IS ISSUED OR NOT
                   public boolean alreadyissued(){

                   boolean alreadyissued = false;
                    int houseID= Integer.parseInt(HID.getText());
                    int rentID= Integer.parseInt(OID.getText());

                       try {
                            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/renatal", "root", "");
                            String sql = "select * from issue where h_id = ? and o_id = ? and status =?";
                            PreparedStatement pst = connect.prepareStatement(sql);

                          pst.setInt(1, houseID);
                          pst.setInt(2, rentID);
                          pst.setString(3, "Available");

                         

                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   return alreadyissued;
                   }
                    public boolean validation(){
                  String house = HID.getText();
                  String tenant = OID.getText();
                   if (house.equals("")){
                   JOptionPane.showMessageDialog(this, "Please Enter House ID");
                   return false;
                   }
                   if(tenant.equals("")){
                   JOptionPane.showMessageDialog(this, "Please Enter Tenant ID");
                   return false;
                   }     
                   if(STARTED.getDate() == null){
                   JOptionPane.showMessageDialog(this, "Please Enter Date Started");
                   return false;
                   }    
                   if(DUEDATE.getDate() == null){
                   JOptionPane.showMessageDialog(this, "Please Enter Due Date");
                   return false;
                   }  
                     return true;  
                   }
                    
                     public void updatedetail(){
                   int houseID= Integer.parseInt(HID.getText());
                       try {
                            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/renatal", "root", "");
                            String sql = "update houses set h_price = h_price  where h_id =?";
                            PreparedStatement pst = connect.prepareStatement(sql);
                            pst.setInt(1, houseID);

                          int rowcount = pst.executeUpdate();

                            if(rowcount>0){
                            JOptionPane.showMessageDialog(this, "DETAILS UPDATED");
                            int initialcount = Integer.parseInt(price.getText());
                            price.setText(Integer.toString(initialcount - 1));
                            }else{
                            JOptionPane.showMessageDialog(this, "DETAILS FAILED TO UPDATED");
                            }
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
                         public void gslip(){

                    slip.setText(slip.getText()+"\n\t-------------------------------------------------------------------\n\t");
                    slip.setText(slip.getText()+"\n\t-------------------RECEIPT--------------------\n\t");
                    slip.setText(slip.getText()+"\n\t-------------------------------------------------------------------\n\t");

                   Date date = new Date();  

                          DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
                          String formattedDate = dateFormat.format(date);


                    slip.setText(slip.getText()+"\n\tDATE: "+formattedDate+"\n\t");
                    slip.setText(slip.getText()+"\n\tHouse ID: "+HID.getText()+"\n\t");
                    slip.setText(slip.getText()+"\n\tHouse #: "+ hn.getText()+"\n\t");
                    slip.setText(slip.getText()+"\n\tTenant ID: "+ OID.getText()+"\n\t");
                    slip.setText(slip.getText()+"\n\tTenant Name: "+ ofn.getText()+"\n\t");
                    slip.setText(slip.getText()+"\n\tDate Stareted: "+  STARTED.getDate()+"\n\t");
                    slip.setText(slip.getText()+"\n\tDUE DATE: "+  DUEDATE.getDate()+"\n\t");


                    slip.setText(slip.getText()+"\n\tSIGNATURE OVER PRINTED NAME:\n\n\t\t");
                    slip.setText(slip.getText()+"\n\t____________\t_____________\n\t");
                    slip.setText(slip.getText()+"Landlord\t\tTenant\n");


                   }




                      //print
                   public void print(){

                       try {
                           slip.print();
                       } catch (Exception e) {
                       JOptionPane.showMessageDialog(null, "print failed"+e);
                       }




                   }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        oid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ofn = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        oln = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ocn = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        hn = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        elec = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        security = new javax.swing.JTextField();
        maintenance = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        water = new javax.swing.JTextField();
        onm = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        OccupantError = new javax.swing.JLabel();
        HouseError = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        OID = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        STARTED = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        saveR = new javax.swing.JButton();
        DUEDATE = new com.toedter.calendar.JDateChooser();
        saveR1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        slip = new javax.swing.JTextArea();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Occupant ID");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(20, 0, 100, 40);

        oid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        oid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oidActionPerformed(evt);
            }
        });
        jPanel1.add(oid);
        oid.setBounds(20, 40, 100, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("First Name");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(140, 0, 100, 40);

        ofn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ofn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ofnActionPerformed(evt);
            }
        });
        jPanel1.add(ofn);
        ofn.setBounds(140, 40, 140, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Last Name");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(300, 0, 120, 30);

        oln.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(oln);
        oln.setBounds(300, 40, 180, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Contact Number");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(80, 80, 130, 30);

        ocn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(ocn);
        ocn.setBounds(70, 120, 140, 30);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 210, 490, 20);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("House ID");
        jPanel1.add(jLabel23);
        jLabel23.setBounds(10, 260, 110, 34);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("House Number");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(170, 260, 150, 34);

        id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        jPanel1.add(id);
        id.setBounds(10, 300, 130, 40);

        hn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        hn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(hn);
        hn.setBounds(170, 300, 140, 40);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Electricity");
        jPanel1.add(jLabel29);
        jLabel29.setBounds(10, 350, 100, 40);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Water");
        jPanel1.add(jLabel26);
        jLabel26.setBounds(150, 350, 90, 40);

        elec.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        elec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(elec);
        elec.setBounds(10, 390, 130, 40);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Maintenance");
        jPanel1.add(jLabel27);
        jLabel27.setBounds(10, 440, 130, 34);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Security");
        jPanel1.add(jLabel28);
        jLabel28.setBounds(340, 350, 90, 34);

        security.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        security.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(security);
        security.setBounds(320, 390, 140, 40);

        maintenance.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        maintenance.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(maintenance);
        maintenance.setBounds(10, 480, 140, 40);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Price");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(340, 260, 70, 34);

        price.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        jPanel1.add(price);
        price.setBounds(340, 300, 140, 40);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Total");
        jPanel1.add(jLabel24);
        jLabel24.setBounds(210, 440, 220, 30);

        total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });
        jPanel1.add(total);
        total.setBounds(190, 480, 260, 40);

        water.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        water.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(water);
        water.setBounds(170, 390, 140, 40);

        onm.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        onm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "None" }));
        onm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onmActionPerformed(evt);
            }
        });
        jPanel1.add(onm);
        onm.setBounds(240, 120, 180, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Number of Members");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(240, 80, 150, 30);

        OccupantError.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        OccupantError.setForeground(new java.awt.Color(255, 51, 51));
        OccupantError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(OccupantError);
        OccupantError.setBounds(60, 160, 360, 40);

        HouseError.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        HouseError.setForeground(new java.awt.Color(255, 51, 51));
        HouseError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(HouseError);
        HouseError.setBounds(50, 550, 390, 40);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 620));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TENANTS LEASE");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 20, 348, 50);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Occupant ID");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(10, 120, 150, 40);

        OID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        OID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        OID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        OID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                OIDFocusLost(evt);
            }
        });
        OID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OIDMouseClicked(evt);
            }
        });
        OID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OIDActionPerformed(evt);
            }
        });
        OID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OIDKeyPressed(evt);
            }
        });
        jPanel2.add(OID);
        OID.setBounds(10, 160, 150, 30);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setText("House ID");
        jPanel2.add(jLabel25);
        jLabel25.setBounds(10, 190, 110, 34);

        HID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        HID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        HID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        HID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                HIDFocusLost(evt);
            }
        });
        HID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HIDActionPerformed(evt);
            }
        });
        HID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HIDKeyPressed(evt);
            }
        });
        jPanel2.add(HID);
        HID.setBounds(10, 230, 150, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Due Date");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(10, 350, 200, 40);

        STARTED.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        STARTED.setDateFormatString("MM/ dd/ yy");
        STARTED.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jPanel2.add(STARTED);
        STARTED.setBounds(10, 300, 150, 40);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Date Started");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(10, 260, 200, 40);

        saveR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        saveR.setText("RENTED");
        saveR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveRMouseClicked(evt);
            }
        });
        saveR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveRActionPerformed(evt);
            }
        });
        jPanel2.add(saveR);
        saveR.setBounds(10, 470, 140, 50);

        DUEDATE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        DUEDATE.setDateFormatString("MM/ dd/ yy");
        DUEDATE.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jPanel2.add(DUEDATE);
        DUEDATE.setBounds(10, 390, 150, 40);

        saveR1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        saveR1.setText("PRINT");
        saveR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveR1ActionPerformed(evt);
            }
        });
        jPanel2.add(saveR1);
        saveR1.setBounds(220, 550, 140, 50);

        slip.setColumns(20);
        slip.setRows(5);
        jScrollPane1.setViewportView(slip);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(180, 100, 200, 440);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 400, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void oidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oidActionPerformed

    private void ofnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ofnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ofnActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void onmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_onmActionPerformed

    private void OIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OIDActionPerformed
        
    }//GEN-LAST:event_OIDActionPerformed

    private void HIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HIDActionPerformed

    private void saveRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveRActionPerformed
          if(validation()==true){
          if(price.getText().equals("0")){
        JOptionPane.showMessageDialog(this, "HOUSE IS NOT AVAILABLE");
    }else{
    
    
        if(alreadyissued() == false){
     
        if(start()== true){
        JOptionPane.showMessageDialog(this, "SUCCESSFULLY ISSUED");
            gslip();
        updatedetail();
       
        }else{
        JOptionPane.showMessageDialog(this, " DENIED ISSUED ");
      
         }
   
     alreadyissued();
     }
}
      }
    }//GEN-LAST:event_saveRActionPerformed

    private void saveR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveR1ActionPerformed
        print();
    }//GEN-LAST:event_saveR1ActionPerformed

    private void OIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OIDKeyPressed
          char c =evt.getKeyChar();
        if(Character.isLetter(c)){
        OID.setEditable(false);
        JOptionPane.showMessageDialog(this, "Please enter number only");
        }else{
        OID.setEditable(true);
        }
    }//GEN-LAST:event_OIDKeyPressed

    private void HIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HIDKeyPressed
          char c =evt.getKeyChar();
        if(Character.isLetter(c)){
        HID.setEditable(false);
        JOptionPane.showMessageDialog(this, "Please enter number only");
        }else{
        HID.setEditable(true);
        }
    }//GEN-LAST:event_HIDKeyPressed

    private void OIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OIDMouseClicked
       
    }//GEN-LAST:event_OIDMouseClicked

    private void saveRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveRMouseClicked
       
    }//GEN-LAST:event_saveRMouseClicked

    private void OIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_OIDFocusLost
        // TODO add your handling code here:
           if(!OID.getText().equals("")){
       gettenants();
       }
        
        
    }//GEN-LAST:event_OIDFocusLost

    private void HIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_HIDFocusLost
        // TODO add your handling code here:
        if(!HID.getText().equals("")){
       getlease();
       }
        
    }//GEN-LAST:event_HIDFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DUEDATE;
    public static final javax.swing.JTextField HID = new javax.swing.JTextField();
    private javax.swing.JLabel HouseError;
    private javax.swing.JTextField OID;
    private javax.swing.JLabel OccupantError;
    private com.toedter.calendar.JDateChooser STARTED;
    private javax.swing.JTextField elec;
    private javax.swing.JTextField hn;
    public static final javax.swing.JTextField id = new javax.swing.JTextField();
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField maintenance;
    private javax.swing.JTextField ocn;
    private javax.swing.JTextField ofn;
    private javax.swing.JTextField oid;
    private javax.swing.JTextField oln;
    private javax.swing.JComboBox<String> onm;
    private javax.swing.JTextField price;
    private javax.swing.JButton saveR;
    private javax.swing.JButton saveR1;
    private javax.swing.JTextField security;
    private javax.swing.JTextArea slip;
    private javax.swing.JTextField total;
    private javax.swing.JTextField water;
    // End of variables declaration//GEN-END:variables
}
