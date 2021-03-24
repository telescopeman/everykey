
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*; 
import java.util.Arrays;
//import javafx.scene.control.MenuButton;
//import javafx.scene.control.MenuItem;
/**
 * Write a description of class FilterCreator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FilterCreator extends JFrame implements ActionListener
{
    // instance variables - replace the example below with your own
    String type;
    JComboBox list1;
    JComboBox list2;

    Filter[] myFilters;
    boolean[] setList;
    UIStuff ui;

    /**
     * Constructor for objects of class FilterCreator
     */
    public FilterCreator(UIStuff uiref)
    {
        ui = uiref;
        myFilters = uiref.curFilters;
        setList = uiref.filterStatuses;

        setSize(new Dimension(350, 100));
        setLayout(new GridLayout(3,1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.

    }

    private void setUpUniqueFactors(String id)
    {
        //removeAll();

        JLabel title = new JLabel("Filter Designer - " + id, JLabel.CENTER);
        title.setFont(new Font(title.getFont().getFontName(),Font.BOLD,12));
        add(title);
        String[] options = new String[]{"Error!"};
        String[] options2 = new String[]{"Error!"};
        boolean hasSecondDropDown = false;
        switch (id)
        {
            case "Tonality":
            options = new String[]{"Major", "Minor", "Either", "Neither"};
            break;

            case "Note":
            options = Arrays.copyOfRange(MathHelper.notesArr, 1, 12);
            break;

            case "Chord":
            options = Arrays.copyOfRange(MathHelper.notesArr, 1, 12);
            options2 = new String[]{"Major", "Minor", "Either","Neither"};
            hasSecondDropDown = true;
            break;

        }
        list1 = new JComboBox(options);
        list1.setSelectedIndex(0);
        //list1.addActionListener(this);
        add(list1);
        list2 = new JComboBox(new String[]{"Dummy"});
        list2.setSelectedIndex(0);
        if (hasSecondDropDown)
        {
            list2 = new JComboBox(options);
            list2.setSelectedIndex(0);
            //list2.addActionListener(this);
            add(list2);
        }

        JButton adder = new JButton("Add Filter");
        adder.addActionListener(this);
        add(adder);
    }

    public Filter constructFilter(String opt1, String opt2)
    {
        switch (type)
        {
            case "Note":
                return new Filter(list1.getSelectedIndex() + 1);

            case "Tonality":
                switch (opt1)
                {
                    case "Major":
                        return new Filter(5,3);
                        
                    case "Minor":
                        return new Filter(4,3);
                        
                    case "Either":
                        return new Filter(new int[]{4,5},3);
                        
                    case "Neither":
                        return new Filter(new int[]{4,5},3,true);
                }
                

            case "Chord":
                return new Filter("isNamed");
                
            default:
                return new Filter("isNamed");
            
        }
    }

    public static Filter[] addX(Filter[] list, Filter x)
    {
        int i;

        // create a new array of size n+1
        Filter[] newarr = new Filter[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = list[i];

        newarr[list.length] = x;

        return newarr;
    }
    
    public static boolean[] addX(boolean[] list, boolean x)
    {
        int i;

        // create a new array of size n+1
        boolean[] newarr = new boolean[list.length + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < list.length; i++)
            newarr[i] = list[i];

        newarr[list.length] = x;

        return newarr;
    }

    public void actionPerformed(ActionEvent e) {
        String id = e.getActionCommand();
        //System.out.print(id);
        if (id.equals("comboBoxChanged"))
        {

        }
        else if (id.equals("Add Filter"))
        {
            String o1 = list1.getSelectedItem().toString();
            String o2 = list2.getSelectedItem().toString();
            Filter[] tempList = addX(myFilters,constructFilter(o1,o2));
            
            myFilters = tempList;
            
            boolean[] tempList2 = addX(setList,true);
            
            setList = tempList2;
            
            System.out.print("New List:" +tempList);
            ui.refresh();
            //frame.dispose();
            
            this.dispose();

        }
        else
        {
            type = id.substring(10);
            setUpUniqueFactors(type);
            show();
            requestFocusInWindow();
        }

        //System.out.print();
        //System.out.println(e);
    }
}
