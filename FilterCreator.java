
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*; 
import java.util.Arrays;


//import javafx.scene.control.MenuButton;
//import javafx.scene.control.MenuItem;
/**
 * Dialogue popup that lets the user create a filter.
 *
 * @author Caleb Copeland
 * @version (a version number or a date)
 */
public class FilterCreator extends ModBox
{
    // instance variables - replace the example below with your own
    String type;
    JComboBox list1;
    JComboBox list2;
    JMenu listSub;
    JCheckBox tickBox;
    
    
    Filter[] myFilters;
    boolean[] setList;
    //private UIStuff ui;

    /**
     * Constructor for objects of class FilterCreator
     */
    public FilterCreator(UIStuff uiref)
    {
        super(uiref);
        update();

        
        //setLayout(new GridLayout(3,1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.

    }
    
    private void update()
    {
        myFilters = ui.curFilters;
        setList = ui.filterStatuses;
    }

    private void setUpUniqueFactors(String id)
    {
        //removeAll();
        clear();

        addHeader("Filter Designer - " + id);
        
        String[] options = new String[]{"Error!"};
        String[] options2 = new String[]{"Error!"};
        boolean hasSecondDropDown = false;
        String[] fullTags = {"f"};
        
        switch (id)
        {
            case "Tonality":
                options = new String[]{"Major", "Minor", "Either", "Neither"};
                break;

            case "Note":
                options = CHROMATICSCALE;
                break;

            case "Chord":
                options = CHROMATICSCALE;
                options2 = new String[]{"Major", "Minor"};
                setLayout(new GridLayout(3,2));
                add(new JLabel(""));
                hasSecondDropDown = true;
                break;
            
            case "Tags":
                fullTags = TagsManager.getAllTags();
                options = ArrayHelper.getFiltered(fullTags,"Mode",false);
                break;
                
            case "Mode":
                fullTags = TagsManager.getAllTags();
                options = ArrayHelper.getFiltered(fullTags,"Mode",true);
                break;
                
            case "Special":
                options = new String[]{"Named Keys"};
                break;
            
            default:
                break;

        }
        list1 = new JComboBox(options);
        System.out.println(options);
       
        
        add(list1);
        list2 = new JComboBox(new String[]{"Dummy"});
        list2.setSelectedIndex(0);
        
        if (hasSecondDropDown)
        {
            list2 = new JComboBox(options2);
            list2.setSelectedIndex(0);
            
            //list2.addActionListener(this);
            add(list2);
        }
        
        
        tickBox = new JCheckBox("Inverted?");
        add(tickBox);
        addButton("Add Filter",this);
    }

    public Filter constructFilter(String opt1, String opt2)
    {
        switch (type)
        {
            case "Note":
                return new Filter(list1.getSelectedIndex() + 1,tickBox.isSelected());

            case "Tonality":
            {
                Filter f;
                switch (opt1)
                {
                    case "Major":
                        f = new Filter(5,2);
                        f.setDescription("Must be a major key.");
                        break;
                        
                    case "Minor":
                        f = new Filter(4,2);
                        f.setDescription("Must be a minor key.");
                        break;
                        
                    case "Either":
                        f = new Filter(new int[]{4,5},3);
                        f.setDescription("Must be either major or minor.");
                        break;
                        
                    case "Neither":
                        f = new Filter(new int[]{4,5},3,true);
                        f.setDescription("Must be neither major nor minor.");
                        break;
                        
                    default:
                        f = new Filter("isNamed");
                        break;
                }
                return f;
            }

            case "Chord":
            {
                int root = list1.getSelectedIndex() + 1;
                int third;
                switch (opt2)
                {
                    case "Major":
                    {
                        third = root + 4;
                        break;
                    }
                    case "Minor":
                    {
                        third = root + 3;
                        break;
                    }
                    default:
                    {
                        third = root + 2;
                        break;
                    }
                }
                int fifth = root + 7;
                Filter f = new Filter(new int[]{littleParse(root),
                    littleParse(third), littleParse(fifth)});
                f.setDescription("Must contain a " + opt1 + " " + opt2 + " chord.");
                return f;
            }
                
            case "Tags":
                return new Filter((list1.getSelectedItem()).toString());
                
            case "Mode":
                return new Filter((list1.getSelectedItem()).toString());
                
            case "Special":
            {
                switch (opt1)
                {
                    case "Named Keys":
                    {
                        return new Filter("isNamed",tickBox.isSelected());
                    }
                    
                    default:
                    {
                        return new Filter("isNamed",tickBox.isSelected());
                    }
                }
            }
                
            default:
                return new Filter("isNamed");
            
        }
    }

    private int littleParse(int note)
    {
        return (((note - 1) % 12) + 1);
        
    }
    
    private void setCurFilters(Filter[] f)
    {
        ui.setCurFilters(f);
    }

    
    
    
    
    
    public void actionPerformed(ActionEvent e) {
        String id = e.getActionCommand();
        update();
        //System.out.print(id);
        if (id.equals("comboBoxChanged"))
        {

        }
        else if (id.equals("Add Filter"))
        {
            String o1 = list1.getSelectedItem().toString();
            String o2 = list2.getSelectedItem().toString();
            Filter[] tempList = arr.addX(myFilters,constructFilter(o1,o2));
            
            boolean[] tempList2 = arr.addX(setList,true);
            
            ui.setFilterStatuses(tempList2);
            //System.out.println("SUPER: " + super.toString());
            ui.setCurFilters(tempList);
            
            
            
            //System.out.print("New List:" +tempList);
            ui.refresh();
            update();
            //frame.dispose();
            
            this.dispose();

        }
        else
        {
            
            type = id.substring(10);
            setUpUniqueFactors(type);
            appear(SUPERSTANDARD);
            
        }

        //System.out.print();
        //System.out.println(e);
    }
}
