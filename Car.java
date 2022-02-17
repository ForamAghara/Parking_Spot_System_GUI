/**
 * Car class
 * This class stores the data of each unique car 
 * @author - Foram Aghara
 * @version - 2.0.0
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Car extends JFrame
{
    // Declaring the variables for registration and owner of the car and also to determine whether owner is staff member or not
    public String car_registration;
    public String car_owner;
    public boolean owner_isStaff;

    // initialising the class variables using constructors
    public Car(String reg,String owner,String staff, JFrame frame)
    {
        
        if(reg_validate(reg) && staff_validate(staff))
        {
            this.car_registration = reg;
            this.car_owner = owner;
            if(staff.equals("yes"))
            {
                this.owner_isStaff = true;
            }
            else if(staff.equals("no"))
            {
                this.owner_isStaff = false;
            }
        }
    }
    
    // Defining validator to validate registration number provided by user
    public boolean reg_validate(String reg){
                           
        Pattern pattern = Pattern.compile("^([A-Z][0-9]{5})$");
        Matcher matcher = pattern.matcher(reg);
        boolean reg_validation = matcher.find();
         // creating pattern using regular expression and matching string with the pattern 
         if(reg_validation == false)
         {
             JOptionPane.showMessageDialog(null,"Please enter valid registration value!!");
         }
         return reg_validation;
     }
                       
    // Defining validator to validate staff info provided by user
    public boolean staff_validate(String staff){ 
        Pattern pattern = Pattern.compile("^(yes|no)$");
        Matcher matcher = pattern.matcher(staff);
        boolean staff_validation = matcher.find();
                       
        // Checking and ensuring that user enters the following options only
        if(staff_validation == false)
        {
            JOptionPane.showMessageDialog(null,"Please enter valid 'yes' or 'no' value!!");
        }
                        
        return staff_validation;
    }
}
