/**
 * MyGUI Class.
 * This class ensures all the users interaction with the code.
 * @author - Foram Aghara
 * @version - V2.0
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class GUI_Application extends JFrame implements ActionListener 
{
    // Declaring some local variables
    private JFrame frame;
    private JTextField textField,textField1,textField2,textField3;
    int slots_Staff;
    int slots_Visitor;
    int menu_option;
    boolean option_flag = true;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_Application window = new GUI_Application();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GUI_Application() {
        initialize();
    }

    /**
     * Initialize the contents of the frame for the first window of the application.
     */
    private void initialize() {
        frame = new JFrame("Create Parking Slots");
        frame.setBounds(100, 100, 600, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblTitle = new JLabel("Create Parking Slots for Staff Members and Visitors upto 100 max spaces in total.");
        lblTitle.setBounds(50, 30, 600, 14);
        frame.getContentPane().add(lblTitle);
        
        // Take the number of parking slots from the user for staff and visitor
        
        JLabel lblStaff = new JLabel("Parking Spaces for staff:");
        lblStaff.setBounds(50, 67, 370, 14);
        frame.getContentPane().add(lblStaff);
        
        JLabel lblVisitor = new JLabel("Parking Spaces for visitor:");
        lblVisitor.setBounds(50, 90, 370, 14);
        frame.getContentPane().add(lblVisitor);
                
        JButton btnSubmit = new JButton("Create Slots");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Creating carpark object and redirecting to another frame
                slots_Staff = Integer.parseInt(textField.getText());
                slots_Visitor = Integer.parseInt(textField1.getText());
                CarPark cp = new CarPark(slots_Staff,slots_Visitor);
                frame.setVisible(false);
                MenuInterface(cp, slots_Staff, slots_Visitor); 
            }
        });
        btnSubmit.setBounds(200, 121, 130, 23);
        frame.getContentPane().add(btnSubmit);
        
        // textfields used to take input from user
        
        textField = new JTextField();
        textField.setBounds(300, 64, 167, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField1 = new JTextField();
        textField1.setBounds(300, 90, 167, 20);
        frame.getContentPane().add(textField1);
        textField1.setColumns(10);

    }
    
    // Main Interface of Program where user can go through various functions from GUI.
    private void MenuInterface(CarPark cp, int staff_slots, int visitor_slots)
    {
        // Declaring frame and local variables
        JFrame frame = new JFrame("Menu Interface");
        frame.setSize(400,300);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBackground(Color.RED);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button,button1, button2, button3,button4,button5, button6,buttonui;
        
        
        // Printing the output of created parking slots initially
        frame.getContentPane().setLayout(null);
        JLabel lblSslots = new JLabel("The "+staff_slots+" slots for staff are created successfully");
        lblSslots.setBounds(50, 140, 70, 14);
        frame.getContentPane().add(lblSslots);
 
        JLabel lblVslots = new JLabel("The "+visitor_slots+" slots for visitors are created successfully");
        lblVslots.setBounds(50, 180, 70, 14);
        frame.getContentPane().add(lblVslots);
        
        // Different buttons for different functionalities
        
        button = new JButton("List all car slots");
        button1 = new JButton("Park a car");
        button2 = new JButton("Find a car");
        button3 = new JButton("Remove a car");
        button4 = new JButton("Add a car slot");
        button5 = new JButton("Delete a car slot");
        buttonui = new JButton("GUI Application");
        button6 = new JButton("Exit");
        
        //Adding ActionListener for the buttons and redirecting to the respective functions on button click event
        
        frame.getContentPane().setLayout(new FlowLayout());
        
        
        button.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0)
            {
                choice1(cp); 
            }
        });
        frame.add(button);
        
        button1.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                choice2(cp); 
            }
        });
        frame.add(button1);
        
        button2.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                choice3(cp); 
            }
        });
        frame.add(button2);
        
        button3.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                choice4(cp); 
            }
        });        
        frame.add(button3);
        
        button4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                choice5(cp); 
            }
        });
        frame.add(button4);
        
        button5.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                choice6(cp); 
            }
        });
        frame.add(button5);
        
        buttonui.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                choice8(cp); 
            }
        });
        frame.add(buttonui);
        
        button6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                choice7(frame); 
            }
        });
        frame.add(button6);
        
        
        
        
    }
    
    private void choice1(CarPark cp)
    {
        // Creating frame and declaring back button for back propogation
        JFrame frame = new JFrame("Menu Interface - List Slots");
        JScrollBar s=new JScrollBar();  
        frame.setSize(600,300);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {                
                frame.setVisible(false);              
            }
        });
        btnBack.setBounds(5, 5, 100, 20);
        frame.getContentPane().add(btnBack);
        
        // Calling list_slot() function from Car Park
        cp.list_slot(frame);
    }
    
    
    
    private void choice2(CarPark cp)
    {
        // Creating frame and declaring back button for back propogation
        JFrame frame = new JFrame("Menu Interface - Park a car");
        frame.setSize(600,300);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                frame.setVisible(false);              
            }
        });
        btnBack.setBounds(5, 5, 100, 20);
        frame.getContentPane().add(btnBack);
        
        //Creating form to get information of car
        JLabel lblTitle = new JLabel("Fill the form below to park a car");
        lblTitle.setBounds(50, 30, 370, 14);
        frame.getContentPane().add(lblTitle);
        
        JLabel lblSlot = new JLabel("Please enter the slot ID you want to car park at (e.g. S001): ");
        lblSlot.setBounds(50, 60, 370, 14);
        frame.getContentPane().add(lblSlot);
        
        JLabel lblReg = new JLabel("Please enter the car registration number (e.g. W12345): ");
        lblReg.setBounds(50, 90, 370, 14);
        frame.getContentPane().add(lblReg);
        
        JLabel lblStaff = new JLabel("Is the car registered for the staff member? (yes/no): ");
        lblStaff.setBounds(50, 120, 370, 14);
        frame.getContentPane().add(lblStaff);        
        
        JLabel lblOwner = new JLabel("Who is the owner of the car?");
        lblOwner.setBounds(50, 150, 370, 14);
        frame.getContentPane().add(lblOwner);        
        
        JButton btnSubmit = new JButton("Park Car");
        btnSubmit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                // Creating car object and calling add_car() function from CarPark to add car in parking slot
                Car cr = new Car(textField1.getText(),textField3.getText(), textField2.getText(),frame);
                cp.add_car(textField.getText(),textField2.getText(), cr, frame);
                 
            }
        });
        btnSubmit.setBounds(220, 200, 100, 23);
        frame.getContentPane().add(btnSubmit);
        
        //TextFields to take input from user
        textField = new JTextField();
        textField.setBounds(400, 60, 167, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField1 = new JTextField();
        textField1.setBounds(400, 90, 167, 20);
        frame.getContentPane().add(textField1);
        textField1.setColumns(10);

        textField2 = new JTextField();
        textField2.setBounds(400, 120, 167, 20);
        frame.getContentPane().add(textField2);
        textField2.setColumns(10);
        
        textField3 = new JTextField();
        textField3.setBounds(400, 150, 167, 20);
        frame.getContentPane().add(textField3);
        textField3.setColumns(10);        

    }
    
    private void choice3(CarPark cp)
    {
        // Creating frame and declaring back button for back propogation
        JFrame frame = new JFrame("Menu Interface - Find a car");
        frame.setSize(600,300);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                frame.setVisible(false);              
            }
        });
        btnBack.setBounds(5, 5, 100, 20);
        frame.getContentPane().add(btnBack);
        
        // Getting Car registration number for search in parking slots
        JLabel lblTitle = new JLabel("Fill the form below to find a car");
        lblTitle.setBounds(50, 30, 370, 14);
        frame.getContentPane().add(lblTitle);
        
        JLabel lblReg = new JLabel("Please enter the car registration number (e.g. W12345): ");
        lblReg.setBounds(50, 80, 370, 14);
        frame.getContentPane().add(lblReg);
        
        
        JButton btnSubmit = new JButton("Find Car");
        btnSubmit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                // find_slot() function called to find car in parking slot.
                cp.find_slot(textField.getText(),frame);
                 
            }
        });
        btnSubmit.setBounds(220, 150, 100, 23);
        frame.getContentPane().add(btnSubmit);
        
        textField = new JTextField();
        textField.setBounds(400, 80, 167, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
    
    }
    
    private void choice4(CarPark cp)
    {
        // Creating frame and declaring back button for back propogation
        JFrame frame = new JFrame("Menu Interface - Remove a car");
        frame.setSize(600,300);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                frame.setVisible(false);              
            }
        });
        btnBack.setBounds(5, 5, 100, 20);
        frame.getContentPane().add(btnBack);
        
        // Getting details for removing car from parking
        JLabel lblTitle = new JLabel("Fill the form below to remove a car");
        lblTitle.setBounds(50, 30, 370, 14);
        frame.getContentPane().add(lblTitle);
        
        JLabel lblReg = new JLabel("Please enter the car registration number (e.g. W12345): ");
        lblReg.setBounds(50, 80, 370, 14);
        frame.getContentPane().add(lblReg);
        
        
        JButton btnSubmit = new JButton("Remove Car");
        btnSubmit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                // remove_car function called to remove car
                cp.remove_car(textField.getText(), frame);
            }
        });
        btnSubmit.setBounds(220, 150, 110, 23);
        frame.getContentPane().add(btnSubmit);
        
        textField = new JTextField();
        textField.setBounds(400, 80, 167, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
   
    }
    
    private void choice5(CarPark cp)
    {
        // Creating frame and declaring back button for back propogation
        JFrame frame = new JFrame("Menu Interface - Add a car slot");
        frame.setSize(600,300);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                frame.setVisible(false);              
            }
        });
        btnBack.setBounds(5, 5, 100, 20);
        frame.getContentPane().add(btnBack);
        
        // form to get details for adding new slot in parking
        JLabel lblTitle = new JLabel("Fill the form below to add a car slot");
        lblTitle.setBounds(50, 30, 370, 14);
        frame.getContentPane().add(lblTitle);
        
        JLabel lblSlot = new JLabel("Please enter the slot ID you want to car park at (e.g. S001): ");
        lblSlot.setBounds(50, 80, 370, 14);
        frame.getContentPane().add(lblSlot);
        
        JLabel lblStaff = new JLabel("Is the car registered for the staff member? (yes/no): ");
        lblStaff.setBounds(50, 110, 370, 14);
        frame.getContentPane().add(lblStaff);     
        
        JButton btnSubmit = new JButton("Add Slot");
        btnSubmit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                // add_slot function of CarPark adds the new slots to parking space
                cp.add_slot(textField.getText(),textField1.getText(),frame);
            }
        });
        btnSubmit.setBounds(220, 150, 100, 23);
        frame.getContentPane().add(btnSubmit);
        
        textField = new JTextField();
        textField.setBounds(400, 80, 167, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
        textField1 = new JTextField();
        textField1.setBounds(400, 110, 167, 20);
        frame.getContentPane().add(textField1);
        textField1.setColumns(10);
    
    }
    
    private void choice6(CarPark cp)
    {
        //Creating frame and declaring back button for back propogation
        JFrame frame = new JFrame("Menu Interface - Delete a car slot");
        frame.setSize(600,300);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0)
            {
                frame.setVisible(false);              
            }
        });
        btnBack.setBounds(5, 5, 100, 20);
        frame.getContentPane().add(btnBack);
        
        // Deleting particular car slot
        JLabel lblTitle = new JLabel("Fill the form below to delete a car slot");
        lblTitle.setBounds(50, 30, 370, 14);
        frame.getContentPane().add(lblTitle);
        
        JLabel lblSlot = new JLabel("Please enter the slot ID you want to car park at (e.g. S001): ");
        lblSlot.setBounds(50, 80, 370, 14);
        frame.getContentPane().add(lblSlot);
          
        
        JButton btnSubmit = new JButton("Delete Slot");
        btnSubmit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0)
            {
                // delete_slot function removes the parking slot which is provided by user
                cp.delete_slot(textField.getText(),frame);
            }
        });
        btnSubmit.setBounds(220, 150, 100, 23);
        frame.getContentPane().add(btnSubmit);
        
        textField = new JTextField();
        textField.setBounds(400, 80, 167, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        
    
    
    }
    
    private void choice7(JFrame frame)
    {
        //Closing the application
        frame.setVisible(false);
    }
    
    // Creating graphical page to help user better interpret the data
    private void choice8(CarPark cp)
    {
        // Creating frame and declaring back button for back propogation
        JFrame frame = new JFrame("Menu Interface - Graphical User Interface");
        frame.setSize(1040,700);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                frame.setVisible(false);              
            }
        });
        btnBack.setBounds(5, 5, 100, 20);
        frame.getContentPane().add(btnBack);
        
        // Printing labels to understande the table grid in the ui
        JPanel staff = new JPanel();
        JLabel st = new JLabel("Staff Parking Slots");
        staff.setBackground(Color.BLUE);
        staff.setBounds(120, 5, 250, 30);
        staff.add(st);
        frame.getContentPane().add(staff);
        
        JPanel visit = new JPanel();
        JLabel vis = new JLabel("Visitor Parking Slots");
        visit.setBackground(Color.ORANGE);
        visit.setBounds(400, 5, 250, 30);
        visit.add(vis);
        frame.getContentPane().add(visit);
        
        JPanel occup = new JPanel();
        JLabel oc = new JLabel("Occupied Parking Slots");
        occup.setBackground(Color.RED);
        occup.setBounds(700, 5, 300, 30);
        occup.add(oc);
        frame.getContentPane().add(occup);
        
        // listing the slots in gui.
        cp.list_gui_slot(frame);
        
    }
    
    public void actionPerformed(ActionEvent e)
        {}
}
