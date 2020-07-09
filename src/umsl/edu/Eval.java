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
import java.awt.print.PrinterException;
import java.sql.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import org.apache.derby.jdbc.*;


public class Eval extends JFrame implements ActionListener, ChangeListener
        {
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
            String myteamname;
            // String courseName;
            double Q1T = 4;
            double Q2U = 4;
            double Q3C = 4;
            double Q4O = 4;
            double teamavg;
            boolean avgcalculated;
            String teamcomments;
            // instance variables used to manipulate database
            private Connection myConnection;
            private Statement myStatement;
            private ResultSet myResultSet;    


        public static void main(String args[])
        {
            String databaseDriver = "org.apache.derby.jdbc.ClientDriver";
            String databaseURL = "jdbc:derby://localhost:1527/Eval";

            Eval eval = new Eval( databaseDriver, databaseURL );
            eval.createUserInterface();
            eval.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
            

        }
            //CONSTRUCTOR: WE SET UP OUR DATABASE HERE THEN MAKE A CALL
        //TO A FUNCTION CALLED CREATEUSERINTERFACE TO BUILD OUR GUI
            public Eval(String databaseDriver, String databaseURL)
                {
                // establish connection to database
                try
                {
                // load Sun driver
                //Class.forName( "org.apache.derby.jdbc.ClientDriver");
                DriverManager.registerDriver(new ClientDriver());
                // connect to database
                myConnection = DriverManager.getConnection( "jdbc:derby://localhost:1527/Eval" );

                // create Statement for executing SQL
                myStatement = myConnection.createStatement();
                }
                catch ( SQLException exception )
                {
                exception.printStackTrace();
                }
                //catch ( ClassNotFoundException exception )
                // {
                // exception.printStackTrace();
                //}

                createUserInterface(); // set up GUI
                
                
            }
            
              
            private void createUserInterface()
            {
              // get content pane for attaching GUI components
              Container contentPane = getContentPane();
              contentPane.setBackground(Color.LIGHT_GRAY);
              // enable explicit positioning of GUI components
              contentPane.setLayout( null );

              //Panel/Set up for Team Label and Dropbox
              teamPanel = new JPanel();
              teamPanel.setBounds(50,20,300,50);
              //teamPanel.setLayout(null);
              teamPanel.setBackground(Color.LIGHT_GRAY);

              contentPane.add( teamPanel );
              //teamLabel
              teamLabel = new JLabel();
              teamLabel.setBounds (20,50,76,15);
              teamLabel.setText("TEAMS");
              teamPanel.add( teamLabel );
              //team dropbox
              teamComboBox = new JComboBox();
              teamComboBox.setBounds(115,50,200,40);
              teamComboBox.addItem("");
              teamComboBox.setSelectedIndex(0);
              teamPanel.add( teamComboBox );
              teamComboBox.setPrototypeDisplayValue("TEAM NAME");
              teamComboBox.addItem("Team01");
              teamComboBox.addItem("Team02");
              teamComboBox.addItem("Team03");
              teamComboBox.addItem("Team04");
              EventListener el = new EventListener();
              teamComboBox.addItemListener(el);

              //Panel/Set up for Q1 Label and Slider 1
              question1Panel = new JPanel();
              question1Panel.setBounds(50,81,300,50 );
              question1Panel.setBackground(Color.LIGHT_GRAY);      
              contentPane.add( question1Panel );

              question1Label = new JLabel();
              question1Label.setBounds(50,90,76,15);
              question1Label.setText("Q1: Technical");
              question1Panel.add( question1Label );

              jSlider1 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
              jSlider1.setBounds(115,81,200,20);
              jSlider1.setValue(4);
              //paint the ticks and tarcks 
              jSlider1.setPaintTrack(true); 
              jSlider1.setPaintTicks(true); 
              jSlider1.setPaintLabels(true);   
              // set spacing 
              jSlider1.setMajorTickSpacing(1); 
              jSlider1.setMinorTickSpacing(1);

              question1Panel.add( jSlider1 );
              jSlider1.setBackground(Color.LIGHT_GRAY);
              // add event listener to Slider Q1
              eventSlider1 ej1 = new eventSlider1();  
              jSlider1.addChangeListener(ej1);









              //Panel/Set up for Q2 Label and Slider 2
              question2Panel = new JPanel();
              question2Panel.setBounds(50,151,300,50 );
              //question2Panel.setLayout(null);
              contentPane.add( question2Panel );
              question2Panel.setBackground(Color.LIGHT_GRAY);

              question2Label = new JLabel();
              question2Label.setBounds(50,151,76,15);
              question2Label.setText("Q2:  Useful  ");
              //question2Label.setBackground(Color.YELLOW);

              question2Panel.add( question2Label );

              jSlider2 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
              jSlider2.setBounds(115,151,200,20);
              jSlider2.setValue(4);
              //paint the ticks and tarcks 
              jSlider2.setPaintTrack(true); 
              jSlider2.setPaintTicks(true); 
              jSlider2.setPaintLabels(true);   
              // set spacing 
              jSlider2.setMajorTickSpacing(1); 
              jSlider2.setMinorTickSpacing(1);
              question2Panel.add( jSlider2 );
              jSlider2.setBackground(Color.LIGHT_GRAY);

              eventSlider2 ej2 = new eventSlider2();  
              jSlider2.addChangeListener(ej2);

              //Panel/Set up for Q3 Label and Slider 3
              question3Panel = new JPanel();
              question3Panel.setBounds(50,221,300,50 );
              //question3Panel.setLayout(null);
              contentPane.add( question3Panel );

              question3Label = new JLabel();
              question3Label.setBounds(50,221,76,15);
              question3Label.setText("Q3: Clarity ");
              question3Panel.add( question3Label );
              question3Panel.setBackground(Color.LIGHT_GRAY);


              jSlider3 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
              jSlider3.setBounds(115,291,200,20);
              //paint the ticks and tarcks 
              jSlider3.setPaintTrack(true); 
              jSlider3.setPaintTicks(true); 
              jSlider3.setPaintLabels(true);   
              // set spacing 
              jSlider3.setMajorTickSpacing(1); 
              jSlider3.setMinorTickSpacing(1);
              jSlider3.setValue(4);
              question3Panel.add( jSlider3 );
              jSlider3.setBackground(Color.LIGHT_GRAY);
              
              eventSlider3 ej3 = new eventSlider3();  
              jSlider3.addChangeListener(ej3);

              //Panel/Set up for Q4 Label and Slider 4
              question4Panel = new JPanel();
              question4Panel.setBounds(50,291,300,50 );

              //question4Panel.setBackground(Color.MAGENTA);
              //question4Panel.setLayout(null);
              contentPane.add( question4Panel );

              question4Label = new JLabel();
              question4Label.setBounds(50,291,76,15);
              question4Label.setText("Q4: Overall  ");
              question4Panel.add( question4Label );
              question4Panel.setBackground(Color.LIGHT_GRAY);


              jSlider4 = new JSlider(JSlider.HORIZONTAL, 1, 8, 1);
              jSlider4.setBounds(115,291,200,20);
              //paint the ticks and tarcks 
              jSlider4.setPaintTrack(true); 
              jSlider4.setPaintTicks(true); 
              jSlider4.setPaintLabels(true);   
              // set spacing 
              jSlider4.setMajorTickSpacing(1); 
              jSlider4.setMinorTickSpacing(1);
              jSlider4.setValue(4);
              question4Panel.add( jSlider4 );
              jSlider4.setBackground(Color.LIGHT_GRAY);
              eventSlider4 ej4 = new eventSlider4();  
              jSlider4.addChangeListener(ej4);

              //Panel/Set up for Comments Label and Box
              commentPanel = new JPanel();
              commentPanel.setBounds(50,361,300,100);
              commentPanel.setBackground(Color.LIGHT_GRAY);
              //commentPanel.setLayout(null);
              contentPane.add( commentPanel);

              commentLabel = new JLabel();
              commentLabel.setBounds(50,375,76,15);
              commentLabel.setText("Comments:");
              commentPanel.add( commentLabel );

              commentBox = new JTextArea();
              commentBox.setBounds(115,365,200,50);
              commentBox.setLineWrap(true);
              commentBox.setWrapStyleWord(true);
              //commentBox.setBackground(Color.GREEN);
              commentPanel.add( commentBox );
              commentBox.setPreferredSize( new Dimension(200,75));
              commentBox.setToolTipText("Add any comments here");
              commentBox.setEditable(true);
              FocusListener1 al = new FocusListener1();
              commentBox.addFocusListener(al);
              
              
              //commentBox.getDocument().addDocumentListener(this);
              
              

              //Panel/Setup for Calc Avg Button and Display
              calcAvgPanel = new JPanel();
              calcAvgPanel.setBounds(50,500,300,100);
              calcAvgPanel.setBackground(Color.LIGHT_GRAY);
              //calcAvgPanel.setLayout(null);
              contentPane.add( calcAvgPanel);

              calcAvgButton = new JButton("Calc Avg");
              calcAvgButton.setBounds(50,461,76,25);
              calcAvgButton.setVisible(true);
              calcAvgPanel.add(calcAvgButton);
              event2 ev = new event2();
              calcAvgButton.addActionListener(ev);


             calcAvgDisplay = new JTextField();
             calcAvgDisplay.setBounds(200,461,76,25);
             calcAvgPanel.add( calcAvgDisplay);
             calcAvgDisplay.setPreferredSize( new Dimension(50,50));
             calcAvgDisplay.setEditable(false);

             //Panel/Setup for Submit & Clear Buttons
             buttonPanel = new JPanel();
             buttonPanel.setBounds( 50,625,300,35 );
             buttonPanel.setBackground(Color.LIGHT_GRAY);

             //buttonPanel.setLayout( null );
             contentPane.add( buttonPanel );

             submitButton = new JButton( "SUBMIT" );
             submitButton.setBounds(75,519,100,25);
             
             
             submitButton.setVisible(true);
             buttonPanel.add(submitButton);
             //event4 evt4 = new event4();
             submitButton.addActionListener(this);
             //submitButton.setEnabled(false);
             //submitButton.setBackground(Color.LIGHT_GRAY);

             clearButton = new JButton( "CLEAR" );
             clearButton.setBounds(150,519,100,25);
             clearButton.setVisible(true);
             buttonPanel.add(clearButton);
             event3 evt = new event3();
             clearButton.addActionListener(evt); 
             
                setTitle( "EVAL" ); // set title bar string
                setSize( 400, 900 ); // set window size
                setVisible( true ); // display window
             //loadTeams();
            }
             
            public class FocusListener1 implements FocusListener
            {
                
                @Override
                    public void focusGained(FocusEvent al)
                    {
                        JTextArea source = (JTextArea) al.getSource();
                        System.out.println("Gained focus");
                         teamcomments = source.getText().trim();
                        
                    }
                    
                 @Override
                    public void focusLost(FocusEvent al)
                    {
                        JTextArea source = (JTextArea) al.getSource();
                        System.out.println("Lost focus");
                        source.selectAll();
                        teamcomments = source.getSelectedText();
                        System.out.println(teamcomments);
                    }
            }
            
                public class EventListener implements ItemListener
            {
                   @Override
                    public void itemStateChanged(ItemEvent el1)
                    {
                        JComboBox source = (JComboBox) el1.getSource();
                        
                            //System.out.println("You Selected: " + source.getSelectedItem());
                            myteamname = (String) source.getSelectedItem();
                            System.out.println(myteamname);
                            //System.out.println("ComboBox Changed");
                        
                    }
            }
            @Override
                            //submit button action listener
                    public void actionPerformed(ActionEvent evt4) 
                    {
                        if (avgcalculated == true)
                        {
                           //updateTeams();
                           System.out.println("Teams Updated");
                           //commentBox.revalidate();
                          // teamcomments = Eval.commentBox.getText();
                           //System.out.println(teamcomments);
                          
                            System.out.println(teamcomments);
                            System.out.println(commentBox.getText());
                            
                            
                           
                            

                        }
                        else 
                        {
                           
                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Calculate An Average Before Submitting", "Message", JOptionPane.WARNING_MESSAGE);
                            
                        }
                     }
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }

            @Override
            public void stateChanged(ChangeEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }



            
                    
        //    @Override
        //public void actionPerformed(ActionEvent evt)
        //{
        ////q1 = sliderq1.getValue();
        //  if(evt.getSource().equals(calcAvgButton))
        //{
        //   
        //System.out.println("Calculating average...");
        //System.out.println(calcAvg());
        //avgcalculated = true;
        //
        //}   
        //
        //    
        //// courseName = (String)courseComboBox.getSelectedItem();
        //if(evt.getSource().equals(submitButton))
        //{
        //myteamname = (String)teamComboBox.getSelectedItem();
        //}
        //}

            //ChangeListener to handle Events and store values in sliders
        //    public class event implements ChangeListener 
        //    {
                            public class eventSlider1 implements ChangeListener 
                            {       
                                @Override
                                public void stateChanged(ChangeEvent evt)
                                {

                                    JSlider source = (JSlider)evt.getSource();
                                
                                 if (!source.getValueIsAdjusting()) 
                                 {
                                     Q1T = source.getValue();
                                    //System.out.println(Q1T + " is Q1T");
                                     
                                 }
                
                                }
                            }
                            public class eventSlider2 implements ChangeListener 
                            {       
                                @Override
                                public void stateChanged(ChangeEvent ej2)
                                {

                                    JSlider source2 = (JSlider)ej2.getSource();
                                
                                 if (!source2.getValueIsAdjusting()) 
                                 {
                                     Q2U = source2.getValue();
                                    // System.out.println(Q2U + " is Q2U");
                                     
                                 }
                
                                }
                            }
                                  public class eventSlider3 implements ChangeListener 
                            {       
                                @Override
                                public void stateChanged(ChangeEvent ej3)
                                {

                                    JSlider source3 = (JSlider)ej3.getSource();
                                
                                 if (!source3.getValueIsAdjusting()) 
                                 {
                                     Q3C = source3.getValue();
                                    // System.out.println(Q3C + " is Q3C");
                                     
                                 }
                
                                }
                            }      public class eventSlider4 implements ChangeListener 
                            {       
                                @Override
                                public void stateChanged(ChangeEvent ej4)
                                {

                                    JSlider source4 = (JSlider)ej4.getSource();
                                
                                 if (!source4.getValueIsAdjusting()) 
                                 {
                                     Q4O = source4.getValue();
                                     //System.out.println(Q4O + " is Q4O");
                                     
                                 }
                
                                }
                            }
                            


                public class event2 implements ActionListener {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        calcAvgDisplay.setText(Double.toString(calcAvg()));
                        //submitButton.setEnabled(true);
                        
                        
                       
                    }
                    
                }
                public class event3 implements ActionListener {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       clear();
                     }
                }
               
                //public class event4 implements ActionListener {
                  
               // }

      
  
        private double calcAvg(){
//            Q1T = (double) jSlider1.getValue();
//            Q2U = (double) jSlider2.getValue();
//            Q3C = (double) jSlider3.getValue();
//            Q4O = (double) jSlider4.getValue();
        
            double avg = (double) (Q1T + Q2U + Q3C + Q4O) / 4;
            //int avg2 = jSlider1.getValue() + jSlider2.getValue() / 2;
            avgcalculated = true;                      
            teamavg = avg;
            //submitButton.setEnabled(true);
            //submitButton.setBackground(Color.blue);
            return avg;
            
            
            }                  

        private void clear(){
                     createUserInterface();
                     resetSliders(4);
                     

        }

        private void updateTeams()
{
    // update balance in database
    try
        {
        // Below is an example of creating a SQL statement that updated more than a single field in one statement.
        String sql1 = "UPDATE APP.TEAMS SET Q1T = " + Q1T
        + ", Q2U = " + Q2U
        + ", Q3C = " + Q3C
        + ", Q4O = " + Q4O
        + ", TEAMAVG = " + teamavg
        + ", COMMENTS = " + "'" + teamcomments
        + "'" + "WHERE " + "TEAMS = " + "'" + myteamname + "'";
        String sql2 = "UPDATE APP.TEAMS SET Q2U = " + Q2U + " WHERE " + "TEAMS = " + "'" + myteamname + "'";
        // String sql3;
        // String sql4;
        // String sql5;
        // String sql6 = "UPDATE APP.TEAM SET COMMENTS = " + "'" + teamcomments + "'" + " WHERE " + "TEAMS = " + "'" + myteamname + "'";
        myStatement.executeUpdate(sql1);


        }
        catch ( SQLException exception )
            {
            exception.printStackTrace();
            }
}
        
        private void loadTeams()
        {
            // get all account numbers from database
            try
            {

            //myResultSet = myStatement.executeQuery( "SELECT DISTINCT lastname FROM InstEval" );
            myResultSet = myStatement.executeQuery( "SELECT TEAMNAME FROM APP.TEAMS" );
            // add account numbers to accountNumberJComboBox
            while ( myResultSet.next() )
            {
            //instructorComboBox.addItem( myResultSet.getString( "lastname" ) );
            teamComboBox.addItem( myResultSet.getString( "TEAMNAME" ) );
            }

            myResultSet.close(); // close myResultSet

            } // end try

            catch ( SQLException exception )
            {
            System.out.println(exception.getMessage());
            }
        }
         public void resetSliders (int sliderDefaultValue)
        {
            Q1T = 4;
            Q2U = 4;
            Q3C = 4;
            Q4O = 4;
            jSlider1.setValue((int) (Q1T));
            jSlider2.setValue((int) (Q2U));
            jSlider3.setValue((int) (Q3C));
            jSlider4.setValue((int) (Q4O));
//            System.out.println("set Q1T to: " + Q1T);
//            System.out.println("set Q2U to: " + Q2U);
//            System.out.println("set Q3C to: " + Q3C);
//            System.out.println("set Q4O to: " + Q4O);
          
            
        }
         public void updateDB()
         {
             
         }
}

