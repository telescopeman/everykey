import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JLabel;

/**
 * Dialogue popup that lets the user create a filter.
 *
 * @author Caleb Copeland
 * @version 5/22/21
 */
public class FilterCreator extends ModBox
{
    // instance variables - replace the example below with your own
    private FilterCreationSetting type = FilterCreationSetting.INVALID;
    private JComboBox list1;
    private JComboBox list2;
    private JMenu listSub;
    private JCheckBox tickBox;

    private Filter[] myFilters;
    private boolean[] setList;

    private static final String[] CHROMATICSCALE = TheoryObj.CHROMATICSCALE;



    public FilterCreator(FilterCreationSetting type)
    {
        this.type = type;
        update();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }



    private void update()
    {
        myFilters = UIStuff.getCurrentFilters();
        setList = UIStuff.getFilterStatuses();
    }

    private void setUpUniqueFactors()
    {
        //removeAll();
        clear();

        addHeader("Filter Designer - " + type);

        String[] options = new String[]{"Error!"};
        String[] options2 = new String[]{"Error!"};
        boolean hasSecondDropDown = false;
        String[] fullTags = {"f"};

        switch (type)
        {
            case TONALITY:
                options = new String[]{"Major", "Minor", "Either", "Neither"};
            break;

            case NOTE:
                options = CHROMATICSCALE;
            break;

            case CHORD:
                options = CHROMATICSCALE;
                options2 = new String[]{"Major", "Minor", "Diminished","Augmented","sus2","sus4","Major ♭5","sus2 ♭5","sus4 ♭5"};
                setGrid(3,2);
                add(new JLabel(""));
                hasSecondDropDown = true;
            break;

            case TAGS:
            fullTags = TagsManager.getAllTags();
            options = ArrayHelper.getFiltered(fullTags,"Mode",false);
            break;

            case MODE:
            fullTags = TagsManager.getAllTags();
            options = ArrayHelper.getFiltered(fullTags,"Mode",true);
            break;

            case SPECIAL:
            options = new String[]{"Named Keys"};
            break;

            default:
                throw new IllegalStateException("Cannot have invalid FilterCreator!");


        }
        list1 = new JComboBox(options);
        //System.out.println(options);

        add(list1);
        list2 = new JComboBox(new String[]{"Dummy"});
        list2.setSelectedIndex(0);

        if (hasSecondDropDown)
        {
            list2 = new JComboBox(options2);
            list2.setSelectedIndex(0);
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
            case NOTE:
            {
                return new Filter(list1.getSelectedIndex() + 1,tickBox.isSelected());
            }

            case TONALITY:
            {
                return constructTonalityFilter(opt1);
            }

            case CHORD:
            {
                return constructChordFilter(opt1,opt2);
            }

            case TAGS:

            case MODE:
                return new Filter((list1.getSelectedItem()).toString());

            case SPECIAL:
            {
                switch (opt1)
                {
                    case "Named Keys":
                    {
                        return new Filter(FilterType.IS_NAMED,tickBox.isSelected());
                    }

                    default:
                    {
                        return new Filter("e");
                    }
                }
            }

            default:
            return new Filter(FilterType.IS_NAMED);

        }
    }

    private Filter constructTonalityFilter(String opt1)
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

    private Filter constructChordFilter(String opt1, String opt2)
    {
        int root = list1.getSelectedIndex() + 1;
        int third;
        int fifth;
        switch (opt2)
        {
            case "Major":
            {
                third = root + 4;
                fifth = root + 7;
                break;
            }
            case "Minor":
            {
                third = root + 3;
                fifth = root + 7;
                break;
            }
            case "Diminished":
            {
                third = root + 3;
                fifth = root + 6;
                break;
            }
            case "Augmented":
            {
                third = root + 4;
                fifth = root + 8;
                break;
            }
            case "Major ♭5":
            {
                third = root + 4;
                fifth = root + 6;
                break;
            }
            case "sus2":
            {
                third = root + 2;
                fifth = root + 7;
                break;
            }
            case "sus4":
            {
                third = root + 5;
                fifth = root + 7;
                break;

            }
            case "sus2 ♭5":
            {
                third = root + 2;
                fifth = root + 6;
                break;
            }
            case "sus4 ♭5":
            {
                third = root + 5;
                fifth = root + 6;
                break;

            }
            default:
            {
                third = root + 2;
                fifth = root + 7;
                break;
            }
        }

        Filter f = new Filter(new int[]{littleParse(root),
                    littleParse(third), littleParse(fifth)});
        f.setDescription("Must contain a " + opt1 + " " + opt2 + " chord.");
        return f;

    }

    private int littleParse(int note)
    {
        return (((note - 1) % 12) + 1);

    }

    public void act(String id)
    {
        update();
        switch (id)
        {
            case "comboBoxChanged":
            {
                break;
            }
            case "Add Filter":
            {
                String o1 = list1.getSelectedItem().toString();
                String o2 = list2.getSelectedItem().toString();
                Filter[] tempList = ArrayHelper.addX(myFilters,constructFilter(o1,o2));

                boolean[] tempList2 = ArrayHelper.addX(setList,true);

                UIStuff.setFilterStatuses(tempList2);
                UIStuff.setCurFilters(tempList);
                UIStuff.refresh();
                update();

                this.dispose();
                break;
            }
            default:
            {

                setUpUniqueFactors();
                appear(SUPER_STANDARD);

            }
        }
    }

}
