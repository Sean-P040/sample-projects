/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsl.edu;

/**
 *
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import org.apache.derby.jdbc.*;

public class Eval2 extends JFrame implements ActionListener {

    public JLabel teamLabel;
    private JComboBox teamComboBox;
    private JPanel teamPanel;

    private JLabel question1Label;
    private JLabel question2Label;
    private JLabel question3Label;
    private JLabel question4Label;

    private JPanel question1Panel;
    private JPanel question2Panel;
    private JPanel question3Panel;
    private JPanel question4Panel;

    private JSlider jSlider1;
    private JSlider jSlider2;
    private JSlider jSlider3;
    private JSlider jSlider4;

    private JPanel commentPanel;
    private JLabel commentLabel;
    private JTextArea commentBox;

    private JPanel calcAvgPanel;
    private JButton calcAvgButton;
    private JTextField calcAvgDisplay;

    private JPanel buttonPanel;
    private JButton submitButton;
    private JButton clearButton;

    //instance variables to hold our data from the gui object to update the database
    String teamname = "";
    // String courseName;
    int Q1T = 4;
    int Q2U = 4;
    int Q3C = 4;
    int Q4O = 4;
    double teamavg;
    boolean avgcalculated;
    String teamcomments;
    // instance variables used to manipulate database
    private Connection myConnection;
    private Statement myStatement;
    private ResultSet myResultSet;
  //  JTextArea commentbox;

    public static void main(String args[]) {
        String databaseDriver = "org.apache.derby.jdbc.ClientDriver";
        String databaseURL = "jdbc:derby://localhost:1527/Eval";

        Eval2 eval2 = new Eval2(databaseDriver, databaseURL);
        eval2.createUserInterface();
        eval2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //CONSTRUCTOR: WE SET UP OUR DATABASE HERE THEN MAKE A CALL
//TO A FUNCTION CALLED CREATEUSERINTERFACE TO BUILD OUR GUI
    public Eval2(String databaseDriver, String databaseURL) {
        // establish connection to database
        try {
            // load Sun driver
            //Class.forName( "org.apache.derby.jdbc.ClientDriver");
            DriverManager.registerDriver(new ClientDriver());
            // connect to database
            myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Eval");

            // create Statement for executing SQL
            myStatement = myConnection.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        //catch ( ClassNotFoundException exception )
        // {
        // exception.printStackTrace();
        //}

        createUserInterface(); // set up GUI

    }

    private void createUserInterface() {
        // get content pane for attaching GUI components
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.LIGHT_GRAY);
        // enable explicit positioning of GUI components
        contentPane.setLayout(null);

        //Panel/Set up for Team Label and Dropbox
        teamPanel = new JPanel();
        teamPanel.setBounds(50, 20, 300, 50);
        //teamPanel.setLayout(null);
        teamPanel.setBackground(Color.LIGHT_GRAY);

        contentPane.add(teamPanel);
        //teamLabel
        teamLabel = new JLabel();
        teamLabel.setBounds(20, 50, 76, 15);
        teamLabel.setText("TEAMS");
        teamPanel.add(teamLabel);
        //team dropbox
        teamComboBox = new JComboBox();
        teamComboBox.setBounds(115, 50, 200, 40);
        teamComboBox.addItem("Choose A Team");
        teamComboBox.setSelectedIndex(0);
        teamPanel.add(teamComboBox);
        //teamComboBox.setPrototypeDisplayValue("TEAM NAME");
        //teamComboBox.addItemListener(this);
        EventListener el = new EventListener();
        teamComboBox.addItemListener(el);

        //Panel/Set up for Q1 Label and Slider 1
        question1Panel = new JPanel();
        question1Panel.setBounds(50, 81, 300, 50);
        question1Panel.setBackground(Color.LIGHT_GRAY);
        contentPane.add(question1Panel);

        question1Label = new JLabel();
        question1Label.setBounds(50, 90, 76, 15);
        question1Label.setText("Q1: Technical ");
        question1Panel.add(question1Label);

        jSlider1 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
        jSlider1.setBounds(115, 81, 200, 20);

        //paint the ticks and tarcks 
        jSlider1.setPaintTrack(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setPaintLabels(true);
        // set spacing 
        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setValue(4);
        question1Panel.add(jSlider1);
        jSlider1.setBackground(Color.LIGHT_GRAY);
        eventSlider1 ej1 = new eventSlider1();
        jSlider1.addChangeListener(ej1);

        //Panel/Set up for Q2 Label and Slider 2
        question2Panel = new JPanel();
        question2Panel.setBounds(50, 151, 300, 50);
        //question2Panel.setLayout(null);
        contentPane.add(question2Panel);
        question2Panel.setBackground(Color.LIGHT_GRAY);

        question2Label = new JLabel();
        question2Label.setBounds(50, 151, 76, 15);
        question2Label.setText("Q2:  Useful  ");
        //question2Label.setBackground(Color.YELLOW);

        question2Panel.add(question2Label);

        jSlider2 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
        jSlider2.setBounds(115, 151, 200, 20);
        jSlider2.setValue(4);
        //paint the ticks and tarcks 
        jSlider2.setPaintTrack(true);
        jSlider2.setPaintTicks(true);
        jSlider2.setPaintLabels(true);
        // set spacing 
        jSlider2.setMajorTickSpacing(1);
        jSlider2.setMinorTickSpacing(1);
        question2Panel.add(jSlider2);
        jSlider2.setBackground(Color.LIGHT_GRAY);
        eventSlider2 ej2 = new eventSlider2();
        jSlider2.addChangeListener(ej2);

        //Panel/Set up for Q3 Label and Slider 3
        question3Panel = new JPanel();
        question3Panel.setBounds(50, 221, 300, 50);
        //question3Panel.setLayout(null);
        contentPane.add(question3Panel);

        question3Label = new JLabel();
        question3Label.setBounds(50, 221, 76, 15);
        question3Label.setText("Q3: Clarity ");
        question3Panel.add(question3Label);
        question3Panel.setBackground(Color.LIGHT_GRAY);

        jSlider3 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
        jSlider3.setBounds(115, 291, 200, 20);
        //paint the ticks and tarcks 
        jSlider3.setPaintTrack(true);
        jSlider3.setPaintTicks(true);
        jSlider3.setPaintLabels(true);
        // set spacing 
        jSlider3.setMajorTickSpacing(1);
        jSlider3.setMinorTickSpacing(1);
        jSlider3.setValue(4);
        question3Panel.add(jSlider3);
        jSlider3.setBackground(Color.LIGHT_GRAY);
        eventSlider3 ej3 = new eventSlider3();
        jSlider3.addChangeListener(ej3);

        //Panel/Set up for Q4 Label and Slider 4
        question4Panel = new JPanel();
        question4Panel.setBounds(50, 291, 300, 50);

        //question4Panel.setBackground(Color.MAGENTA);
        //question4Panel.setLayout(null);
        contentPane.add(question4Panel);

        question4Label = new JLabel();
        question4Label.setBounds(50, 291, 76, 15);
        question4Label.setText("Q4: Overall  ");
        question4Panel.add(question4Label);
        question4Panel.setBackground(Color.LIGHT_GRAY);

        jSlider4 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
        jSlider4.setBounds(115, 291, 200, 20);
        //paint the ticks and tarcks 
        jSlider4.setPaintTrack(true);
        jSlider4.setPaintTicks(true);
        jSlider4.setPaintLabels(true);
        // set spacing 
        jSlider4.setMajorTickSpacing(1);
        jSlider4.setMinorTickSpacing(1);
        jSlider4.setValue(4);
        question4Panel.add(jSlider4);
        jSlider4.setBackground(Color.LIGHT_GRAY);
        eventSlider4 ej4 = new eventSlider4();
        jSlider4.addChangeListener(ej4);

        //Panel/Set up for Comments Label and Box
        commentPanel = new JPanel();
        commentPanel.setBounds(50, 361, 300, 100);
        commentPanel.setBackground(Color.LIGHT_GRAY);
        //commentPanel.setLayout(null);
        contentPane.add(commentPanel);

        commentLabel = new JLabel();
        commentLabel.setBounds(50, 375, 76, 15);
        commentLabel.setText("Comments:");
        commentPanel.add(commentLabel);

        
        //commentBox = new JTextField(180);
        commentBox = new JTextArea();
        //teamcomments = commentBox.getText();
        commentBox.setBounds(115, 365, 200, 50);
        commentBox.setLineWrap(true);
        //commentBox.setBackground(Color.GREEN);
        commentPanel.add(commentBox);
        commentBox.setPreferredSize(new Dimension(200, 75));
        commentBox.setToolTipText("Add any comments here");
        FocusListener1 al = new FocusListener1();
        commentBox.addFocusListener(al);

        
        //Panel/Setup for Calc Avg Button and Display
        calcAvgPanel = new JPanel();
        calcAvgPanel.setBounds(50, 500, 300, 100);
        calcAvgPanel.setBackground(Color.LIGHT_GRAY);
        //calcAvgPanel.setLayout(null);
        contentPane.add(calcAvgPanel);

        calcAvgButton = new JButton("Calc Avg");
        calcAvgButton.setBounds(50, 461, 76, 25);
        calcAvgButton.setVisible(true);
        calcAvgPanel.add(calcAvgButton);
        calcAvgButton.addActionListener(this);

        calcAvgDisplay = new JTextField();
        calcAvgDisplay.setBounds(200, 461, 76, 25);
        calcAvgPanel.add(calcAvgDisplay);
        calcAvgDisplay.setPreferredSize(new Dimension(35, 30));
        calcAvgDisplay.setEditable(false);

        //Panel/Setup for Submit & Clear Buttons
        buttonPanel = new JPanel();
        buttonPanel.setBounds(50, 625, 300, 35);
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        //buttonPanel.setLayout( null );
        contentPane.add(buttonPanel);

        submitButton = new JButton("SUBMIT");
        submitButton.setBounds(75, 519, 100, 25);
        submitButton.setVisible(true);
        submitButton.setEnabled(true);
        buttonPanel.add(submitButton);
        submitButton.addActionListener(this);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(150, 519, 100, 25);
        clearButton.setVisible(true);
        buttonPanel.add(clearButton);
        clearButton.addActionListener(this);

        loadTeams();
        setTitle( "EVAL" ); // set title bar string
        setSize( 400, 900 ); // set window size
        setVisible( true ); // display window

    }
    
//    @Override
//        public void itemStateChanged(ItemEvent evt)
//        {
//           
//                System.out.println("ComboBox Selection has Changed");
//                System.out.println(teamComboBox.getName());
//            
//        }
    
   
    public class eventSlider1 implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent evt) {

            JSlider source = (JSlider) evt.getSource();

            if (!source.getValueIsAdjusting()) {
                Q1T = source.getValue();
            //    System.out.println("Q1: Technical rating has been changed to " + Q1T );

            }

        }
    }

    public class eventSlider2 implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ej2) {

            JSlider source2 = (JSlider) ej2.getSource();

            if (!source2.getValueIsAdjusting()) {
                Q2U = source2.getValue();
             //   System.out.println("Q2: Useful rating has been changed to " + Q2U );

            }

        }
    }

    public class eventSlider3 implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ej3) {

            JSlider source3 = (JSlider) ej3.getSource();

            if (!source3.getValueIsAdjusting()) {
                Q3C = source3.getValue();
           //     System.out.println("Q3: Clarity rating has been changed to " + Q3C );

            }

        }
    }

    public class eventSlider4 implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ej4) {

            JSlider source4 = (JSlider) ej4.getSource();

            if (!source4.getValueIsAdjusting()) {
                Q4O = source4.getValue();
         //       System.out.println("Q4: Overall rating has been changed to " + Q4O );

            }

        }
    }
    
//    public void itemStateChanged(ItemEvent event)
//    {
//        if (event.getSource() == commentBox && event.getStateChange() == ItemEvent.SELECTED)
//        {
//            teamcomments = (commentBox.getText());
     /*   }
        else if (event.getSource() == rb2 && event.getStatechange() == ItemEvent.SELECTED)
        {
            Q1T = Integer.parseInt(rb2.getText());
        }
        else if (event.getSource() -- rb3 && event.getStateChange() == ItemEvent.SELECTED)
        {
            Q1T = Integer.parseInt(rb3.getText());
        }
        else if ( event.getSource() == rb1 && event.getStateChange() == ItemEvent.DESELECTED)
                {
                JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green",);
  
            *///}
   // }
  public class FocusListener1 implements FocusListener
            {
                
                @Override
                    public void focusGained(FocusEvent al)
                    {
                        JTextArea source = (JTextArea) al.getSource();
                        //System.out.println("Gained focus");
                         teamcomments = source.getText().trim();
                        
                    }
                    
                 @Override
                    public void focusLost(FocusEvent al)
                    {
                        JTextArea source = (JTextArea) al.getSource();
                        //System.out.println("Lost focus");
                        source.selectAll();
                        teamcomments = source.getSelectedText();
                       
                    }
            }
  
            
    
    public class EventListener implements ItemListener
            {
                   @Override
                    public void itemStateChanged(ItemEvent el1)
                    {
                        JComboBox source = (JComboBox) el1.getSource();
                        
                            //System.out.println("You Selected: " + source.getSelectedItem());
                            teamname = (String) source.getSelectedItem();
              //              System.out.println(teamname);
                            //System.out.println("ComboBox Changed");
                        
                    }
            }

    
    
    
    

    @Override
    public void actionPerformed(ActionEvent event) {

        //issue with correct source from component
        if (event.getActionCommand().equals(calcAvgButton.getText())) {
           // System.out.println(commentBox.getText());
            //System.out.println("CalcAvg");
            calcAvgDisplay.setText(Double.toString(calcAvg()));

            //submitButton.setEnabled(false);
        } else if (event.getActionCommand().equals(clearButton.getText())) {
            createUserInterface();
            avgcalculated = false;
//            teamComboBox.setSelectedIndex(0);
//            jSlider1.setValue(4);
//            jSlider2.setValue(4);
//            jSlider3.setValue(4);
//            jSlider4.setValue(4);
//            commentBox.setText("");
//            calcAvgDisplay.setText("");
            
           // System.out.println("Clear");
        } else if (event.getActionCommand().equals(submitButton.getText())) {
//            teamname = (String)teamComboBox.getSelectedItem();
//            
//            
//            System.out.println(commentBox.getText());
//            System.out.println("Submit Button");

            if (avgcalculated == true) {
                submitButton.setEnabled(true);
                //System.out.println(teamname);
                updateTeams();
        //        System.out.println("Submit");
            } else {

                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Calculate An Average Before Submitting", "Message", JOptionPane.WARNING_MESSAGE);
            }

        }

//teamname = (String)teamComboBox.getSelectedItem();
//Q1T = jSlider1.getValue();
//Q2U = jSlider2.getValue();
//Q3C = jSlider3.getValue();
//Q4O = jSlider4.getValue();
    }

    private double calcAvg() {
//    Q1T = (double) jSlider1.getValue();System.out.println(jSlider1.getValue());
//    Q2U = (double) jSlider2.getValue();
//    Q3C = (double) jSlider3.getValue();
//    Q4O = (double) jSlider4.getValue();
        teamavg = (double) (Q1T + Q2U + Q3C + Q4O) / 4;
        
        avgcalculated = true;
       
        
        return teamavg;
    }

    private void updateTeams(){
// update balance in database
//try
{
// Below is an example of creating a SQL statement that updated more than a single field in one statement.
    //String sql1 = "UPDATE TEAMS SET TEAMNAME = ?, Q1T = ?, Q2U = ?, Q3C = ?, Q4O = ?, TEAMAVG = ?, COMMENTS = ? WHERE TEAMNAME = ?";
     //pstmt = myConnection.prepareStatement(sql1);
   //PreparedStatement pstmt = myConnection.prepareStatement("Insert into TEAMS  SET Q1T = ?, Q2U = ?, Q3C = ?, Q4O = ?, TEAMAVG = ?, COMMENTS = ? WHERE TEAMNAME = ?");
    
    
    
        try {
          /*  teamname = "Hello";
            String sql1 = "UPDATE APP.TEAMS SET Q1T = " + Q1T
                    + ", Q2U = " + Q2U
                    + ", Q3C = " + Q3C
                    + ", Q4O = " + Q4O
                    + ", TEAMAVG = " + teamavg
                    + ", COMMENTS = " + "'" + teamcomments
                    + "'" + "WHERE " + "TEAMNAME = " + "'" + teamname + "'";
            String sq12 = "UPDATE APP.TEAM SET Q2USEFUL = " + Q2U + " WHERE " + "TEAMS = " + "'" + teamname + "'";
            myStatement.executeUpdate(sql1);
            
        }
        catch ( SQLException exception )
        {
           // exception.PrintStackTrace();
        }
            */
            
            
            //teamname = "whoppi";
            //commentBox.getText()
            //teamcomments = commentBox.getText();
            // teamcomments = "It works";
            //System.out.println(commentBox.getText());
            //System.out.println(teamname);
           // String sql1 = "UPDATE TEAMS" + "( Q1T, Q2U, Q3C, Q4O, TEAMAVG, COMMENTS) values (?,?,?,?,?,?,?)" + " " + "WHERE " + "TEAMNAME= " + "'" + teamname + "'";
           
           String sql1 = "UPDATE APP.TEAMS" + " SET Q1T = " + Q1T + ","
                   + " Q2U = " + Q2U + "," 
                   + "Q3C = " + Q3C + "," + 
                   "Q4O = " + Q4O + "," + 
                   "TEAMAVG = " + teamavg  +
                   "," + "COMMENTS= " + "'" + teamcomments + "'" +
                   " WHERE TEAMNAME = " + "'" + teamname + "'";
          //UPDATE APP.TEAMS SET Q1T= 3, Q2U= 5, Q3C= 7, Q4O= 9 WHERE TEAMNAME = 'Team02';
           PreparedStatement pstmt = myConnection.prepareStatement(sql1);
//            //pstmt.setString(1, teamname);
//            pstmt.setInt(1, Q1T);
//            pstmt.setInt(2, Q2U);
//            pstmt.setInt(3, Q3C);
//            pstmt.setInt(4, Q4O);
//            pstmt.setDouble(5, teamavg);
//          //  String input = commentBox.getText();
//            pstmt.setString(6, "Hello");
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Database Updated", "Submission Success", JOptionPane.INFORMATION_MESSAGE);
            
        
           
    /*    } catch (SQLException ex) {
            Logger.getLogger(Eval.class.getName()).log(Level.SEVERE, null, ex);
        }
  */
    /*
   Statement myStmt = myConnection.createStatement();
    
    
   String sql = "insert into TEAMS "
            + "(Q1T, Q2U, Q3C, Q4O, TEAMAVG, COMMENTS)"
            + "values(Q1T, Q2U, Q3C, Q4O, teamavg, teamcomments)";
    myStmt.executeUpdate(sql);
    System.out.println("Insert complete)");
   */
  /*
    pstmt.setInt(1, Q1T);
   pstmt.setInt(2, Q2U);
   pstmt.setInt(3, Q3C);
   pstmt.setInt(4, Q4O);
   pstmt.setDouble(5, teamavg);
   pstmt.setString(6, teamcomments);
   pstmt.setString(7, teamname);
   
   pstmt.executeUpdate();
    */
  /* 
    pstmt.setString(1, "teamname");
pstmt.setInt(2, 2);
pstmt.setInt(3, 2);
pstmt.setInt(4, 2);
pstmt.setInt(5, 2);
pstmt.setInt(6, 2);
pstmt.setDouble(7, 2);
pstmt.setString(8, "teamname2");
pstmt.executeUpdate();
*/
//myStatement.executeUpdate(pstmt);
//JOptionPane.showMessageDialog(this, "Evaluation Submitted");



    /*    try {
// Below is an example of creating a SQL statement that updated more than a single field in one statement.
            String sql1 = "update TEAMS "
                    + "set TEAMNAME = ?, Q1T = ?, Q2U = ?, Q3C = ?, Q4O = ?, TEAMAVG = ?, COMMENTS = ? WHERE TEAMNAME = ?";
            PreparedStatement pstmt = myConnection.prepareStatement(sql1);
            pstmt.setString(1, "TEAM NAME");
            pstmt.setInt(2, Q1T);
            pstmt.setInt(3, Q2U);
            pstmt.setInt(4, Q3C);
            pstmt.setInt(5, Q4O);
            pstmt.setDouble(6, teamavg);
            pstmt.setString(7, "COMMENTS");
            pstmt.setString(8, "teamname3");
            pstmt.executeUpdate();
          
          
//myStatement.executeUpdate(pstmt);
            JOptionPane.showMessageDialog(this, "Team evaluation logged");
*/
             } catch (SQLException exception) {
            exception.printStackTrace();

       }

}
    } // end method updateBalance

    private void loadTeams() {
// get all account numbers from database
        try {

//myResultSet = myStatement.executeQuery( "SELECT DISTINCT lastname FROM InstEval" );
            myResultSet = myStatement.executeQuery("SELECT TEAMNAME FROM APP.TEAMS");
// add account numbers to accountNumberJComboBox
            while (myResultSet.next()) {
//instructorComboBox.addItem( myResultSet.getString( "lastname" ) );
                teamComboBox.addItem(myResultSet.getString("TEAMNAME"));
            }

            myResultSet.close(); // close myResultSet

        } // end try
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
