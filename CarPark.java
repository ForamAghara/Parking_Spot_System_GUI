
/**
 * CarPark Class.
 * This class is  responsible for maintaining a list of available parking slots and ensures all the operations of the parking slot such as adding slot, parking car, removing car, listing all slots, etc.,
 * @author - Foram Aghara
 * @version - 2.0.0
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
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

public class CarPark extends JFrame
{
    // Creating array list for the parking slots, its type and status, registration and owner of car and also for the objects of parking spots
    public ArrayList<ParkingSpot> car_park = new ArrayList<ParkingSpot>();
    public ArrayList<String> car_rego = new ArrayList<String>();
    public ArrayList<String> car_owner = new ArrayList<String>();
    public ArrayList<String> parking_slot = new ArrayList<String>();
    public ArrayList<String> parking_type = new ArrayList<String>();
    public ArrayList<String> parking_status = new ArrayList<String>();
    
    // Defining slots for staff and visitor using constructor when creating the object
    public CarPark(int staff, int visitor)
    {
        // Creating slots for Staff Members
        for(int i=1; i <=staff; i++)
        {
            parking_slot.add("S00"+i);
            parking_type.add("staff");
            parking_status.add("not occupied");
            car_rego.add("");
            car_owner.add("");
        }
        
        // Creating slots for Visitors
        for(int i=1; i <=visitor; i++)
        {
            parking_slot.add("V00"+i);
            parking_type.add("visitor");
            parking_status.add("not occupied");
            car_rego.add("");
            car_owner.add("");
        }
    }

    // Function to find a car in the Parking
    public void find_slot(String registration,JFrame frame)
    {
        // Checking for the registration in parked cars 
        if(check_reg(registration))
        {
        int i = car_rego.indexOf(registration);
        
        // Displaying parked car details
        JOptionPane.showMessageDialog(null,"The car with registration "+car_rego.get(i)+" is parked on slot "+parking_slot.get(i)+", and the owner is "+car_owner.get(i));
        }
        else 
        {
            // Displaying error message when no car is parked with registration provided by user
            JOptionPane.showMessageDialog(null,"Please enter valid car registration!!!");
            
        }
    }
    
    // Function to add parking slot
    public void add_slot(String slotid, String Staff,JFrame frame)
    {
        // Checking if the slot already exist or not
        if(check_slot(slotid) && staff_validate(Staff))
        {
            JOptionPane.showMessageDialog(null,"Please enter valid ParkingSlot or Staff member options!!!");
        }
        else
        {
            if(slot_validate(slotid))
            {
                // Adding parking spot if any existing spot not found
                if(Staff.equals("yes"))
                {
                    parking_slot.add(slotid);
                    parking_type.add("staff");
                    parking_status.add("not occupied");
                    car_rego.add("");
                    car_owner.add("");
                }
                else if(Staff.equals("no"))
                {
                    parking_slot.add(slotid);
                    parking_type.add("visitor");
                    parking_status.add("not occupied");
                    car_rego.add("");
                    car_owner.add("");
                }
                JOptionPane.showMessageDialog(null,"The parking slot added successfully");
            }
            else{
                JOptionPane.showMessageDialog(null,"Please enter valid slot id - Staff parking slot starts with 'S' and Visitor parking slot starts with 'V'");
            }
        }
    }
    
    // Function to delete parking spot
    public void delete_slot(String slotid, JFrame frame)
    {
        // Checking whether spot is present or not 
        if(check_slot(slotid))
        {
            // If spot found and is not occupied then deleting the slot else displaying error message
            int i = parking_slot.indexOf(slotid);
            if(parking_status.get(i).equals("not occupied"))
            {
                parking_slot.remove(i);
                parking_type.remove(i);
                car_rego.remove(i);
                car_owner.remove(i);
                parking_status.remove(i);
                JOptionPane.showMessageDialog(null,"The parking slot removed successfully");
            }
            else 
            {
                
                JOptionPane.showMessageDialog(null,"Parking Slot cannot be removed as car is already parked!!!");
            }
        }
        else
        {
            // Displaying message if the Parking Slot not found
            JOptionPane.showMessageDialog(null,"Please enter valid ParkingSlot!!!");
        }
    }
    
    
    // Function to remove car from parking spot
    public void remove_car(String registration,JFrame frame)
    {
        // Checking the registration provided by user whether it is parked or not
        if(check_reg(registration))
        {
            // If car found then removing the car from the spot
            int i = car_rego.indexOf(registration);
            parking_status.set(i, "not occupied");
            car_rego.set(i, "");
            car_owner.set(i, "");
            JOptionPane.showMessageDialog(null,"The car with registration "+car_rego.get(i)+" was removed");
        }
        else
        {
            // If car not found the displaying the error message
            JOptionPane.showMessageDialog(null,"Please enter valid car registration!!!");
        }
    }
    
    
    // Function to add car in parking spot
    public void add_car(String slotid, String Staff, Car cr, JFrame frame)
    {
        // Checking whether the slot is empty and the car with same registration is not parked anywhere else
        if(check_slot(slotid)){
        if (check_slotempty(slotid) && !check_reg(cr.car_registration) )
        {
            int i = parking_slot.indexOf(slotid);
            
            if(registration_validate(cr.car_registration) && staff_validate(Staff))
            {
                // Checking the user input to ensure members park in their allocated slots
                if((parking_type.get(i).equals("staff") && Staff.equals("yes")) || (parking_type.get(i).equals("visitor") && Staff.equals("no")))
                {
                car_park.add(new ParkingSpot(slotid,Staff,cr));
            
                parking_status.set(i, "occupied");
                car_rego.set(i, cr.car_registration);
                car_owner.set(i, cr.car_owner);
                
                JOptionPane.showMessageDialog(null,"The car with registration "+cr.car_registration+" was parked successfully");
                
                }
                else
                {
                    // Displaying message if any conflict found
                    JOptionPane.showMessageDialog(null,"Staff members can park only in Staff parking slots and visitors can park only in Visitors parking slots!!");
                }
            }
        }
        else
        {
            // Checking if the car is parked or not
            if(check_reg(cr.car_registration))
            {
                // Displaying message that car is parked in other spot
                JOptionPane.showMessageDialog(null,"The car with registration "+cr.car_registration+" is already parked at other parking slot");
            }
            else
            {
                // Displaying message if the invalid slot is provided by user
                JOptionPane.showMessageDialog(null,"Please enter valid-empty slot and registration of car again!!!");
            }
        }
    }
    else{
        JOptionPane.showMessageDialog(null,"Please enter valid slot id!!");
    }
    }
    
    
    // Function to display all the parking spots
    public void list_slot(JFrame frame)
    {
        
        for(int i=0; i<parking_slot.size(); i++)
        {   
            // Display parked car details  and only parking spot details when car not parked
            if(parking_status.get(i).equals("occupied"))
            {
                JLabel lbli = new JLabel("SlotID is: "+parking_slot.get(i)+", is for "+parking_type.get(i)+" and is "+parking_status.get(i)+" by a car with reg "+car_rego.get(i));
                lbli.setBounds(50, 30+(i*20), 570, 14);
                frame.getContentPane().add(lbli);
            }
            else
            {
                JLabel lbli = new JLabel("SlotID is: "+parking_slot.get(i)+", is for "+parking_type.get(i)+" and is "+parking_status.get(i));
                lbli.setBounds(50, 30+(i*20), 570, 14);
                frame.getContentPane().add(lbli);
            }
        }
    }
    
    public void list_gui_slot(JFrame frame)
    {
        JPanel bigPanel = new JPanel();
        bigPanel.setBounds(10, 50, 1000,600);
        bigPanel.setBackground(Color.BLACK);
        GridLayout layout = new GridLayout(10,10);
        layout.setHgap(10);
        layout.setVgap(10);
        bigPanel.setLayout(layout);
        
        
        for(int i=0; i<parking_slot.size(); i++)
        {   
            // Display car details is parked and only parking spot details when car not parked
            
            
            if(parking_status.get(i).equals("occupied"))
            {
                JButton btn = new JButton(parking_slot.get(i));
                String msg = "Slot id : " + parking_slot.get(i) + "\nStatus :" + parking_status.get(i) + "\nCar Registration : " + car_rego.get(i) + "\nOwner : " +car_owner.get(i) + "\nMember : " + parking_type.get(i);
                btn.addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent arg0) 
                    {
                        JOptionPane.showMessageDialog(null,msg); 
                    }
                });
                btn.setBackground(Color.RED);
                btn.setSize(150,100);
                bigPanel.add(btn);
            }
            else
            {

                JButton btn = new JButton(parking_slot.get(i));
                String msg = "Slot id : " + parking_slot.get(i) + "\nStatus :" + parking_status.get(i) + "\nCar Registration : " + car_rego.get(i) + "\nOwner : " +car_owner.get(i) + "\nMember : " + parking_type.get(i);
                btn.addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent arg0) 
                    {
                        JOptionPane.showMessageDialog(null,msg); 
                    }
                });
                if(parking_type.get(i).equals("staff")){
                    btn.setBackground(Color.BLUE);
                }
                else{
                    btn.setBackground(Color.ORANGE);
                }
                btn.setSize(150,100);
                bigPanel.add(btn);
                
                
                
            }
        }
        for(int i=parking_slot.size();i<100;i++)
        {
                JPanel pnli = new JPanel();
                pnli.setBackground(Color.BLACK);
                pnli.setSize(150,100);
                bigPanel.add(pnli);
        }
        
        frame.add(bigPanel);
    }
    
    
    // Function to check whether the slot is empty or not
    public boolean check_slotempty(String slotid)
    {
        boolean flag = false;
        for(int i=0; i<parking_slot.size(); i++)
        {
            // Finding the slot id provided by the user
            if(parking_slot.get(i).equals(slotid))
            {
                // Checking whether slot is empty
                if(parking_status.get(i).equals("not occupied"))
                {
                    flag = true;
                }
            }
        }
        return flag;
    }
    
    
    // Function to check valid parking slots
    public boolean check_slot(String slotid)
    {
        boolean flag = false;
        for(int i=0; i<parking_slot.size(); i++)
        {
            // Finding the slot id provided by the user 
            if(parking_slot.get(i).equals(slotid))
            {
                    flag = true;
                
            }
        }
        return flag;
    }
    
    // Function to check parked cars in the parking spots
    public boolean check_reg(String registration)
    {
        boolean flag = false;
        for(int i=0; i<car_rego.size(); i++)
        {  
            // Finding the registration of the car provided by the user
            if(car_rego.get(i).equals(registration))
            {
                flag = true;
            }
        }
        return flag;
    }
   
    // Defining validator to validate slot id provided by user
    public static boolean slot_validate(String slot){
        // creating pattern using regular expression and matching string with the pattern        
        Pattern pattern = Pattern.compile("^([S,V][0-9]{3})$");
        Matcher matcher = pattern.matcher(slot);
        boolean slot_validation = matcher.find();
        
        return slot_validation;
    }
   
    // Defining validator to validate registration number provided by user
    public static boolean registration_validate(String reg){
        // creating pattern using regular expression and matching string with the pattern        
        Pattern pattern = Pattern.compile("^([A-Z][0-9]{5})$");
        Matcher matcher = pattern.matcher(reg);
        boolean reg_validation = matcher.find();
        
        return reg_validation;
    }                

   
    
    // Defining validator to validate staff info provided by user
    public static boolean staff_validate(String isStaff){
        // creating pattern using regular expression and matching string with the pattern        
        Pattern pattern = Pattern.compile("^(yes|no)$");
        Matcher matcher = pattern.matcher(isStaff);
        boolean staff_validation = matcher.find();
    
        return staff_validation;
    }
    
    
    
    
}


