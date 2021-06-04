import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.util.Objects;

/**
 * Dialogue popup that lets the user create a filter.
 *
 * @author Caleb Copeland
 * @version 6/4/21
 */
public class FilterCreator extends ModBox
{
    private final FilterCreationSetting type;
    private JComboBox<String> list1, list2;
    private JCheckBox tickBox;

    private static final String
            EITHER = "Either",
            NEITHER = "Neither",
            ADD_FILTER = "Add Filter";

    private Filter[] myFilters;
    private boolean[] setList;

    public FilterCreator(FilterCreationSetting type)
    {
        super(SUPER_STANDARD,2,2);
        this.type = type;
        update();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void update()
    {
        myFilters = FilterBank.getCurrentFilters();
        setList = FilterBank.getFilterStatuses();
    }

    private void setUpUniqueFactors()
    {
        clear();

        addHeader("Filter Designer - " + type);

        String[] options;
        String[] options2 = new String[]{"Error!"};
        boolean hasSecondDropDown = false;
        String[] fullTags;

        switch (type)
        {
            case TONALITY:
            options = new String[]{
                    Chord.MAJOR,
                    Chord.MINOR,
                    EITHER,
                    NEITHER};
            break;

            case NOTE:
            options = TheoryObj.CHROMATIC_SCALE;
            break;

            case CHORD: {
                options = TheoryObj.CHROMATIC_SCALE;
                options2 = Chord.TYPES;
                setGrid(3, 2);
                add(new JLabel(""));
                hasSecondDropDown = true;
                break;
            }

            case TAGS: {
                fullTags = TagsManager.getAllTags();
                options = ArrayHelper.getFiltered(fullTags, "Mode", false);
                break;
            }

            case MODE: {
                fullTags = TagsManager.getAllTags();
                options = ArrayHelper.getFiltered(fullTags, "Mode", true);
                break;
            }

            case SPECIAL: {
                options = new String[]{"Named Keys"};
                break;
            }

            default:
            throw new IllegalStateException("Cannot have invalid FilterCreator!");

        }
        list1 = new JComboBox<>(options);

        add(list1);
        list2 = new JComboBox<>(new String[]{"Dummy"});
        list2.setSelectedIndex(0);

        if (hasSecondDropDown)
        {
            list2 = new JComboBox<>(options2);
            list2.setSelectedIndex(0);
            add(list2);
        }

        tickBox = new JCheckBox("Inverted?");
        add(tickBox);
        addButton(ADD_FILTER,this);
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
            return new Filter((Objects.requireNonNull(
                    list1.getSelectedItem())).toString());

            case SPECIAL:
            {
                if ("Named Keys".equals(opt1)) {
                    return new Filter(FilterType.IS_NAMED, tickBox.isSelected());
                }
                return new Filter("e");
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
            case Chord.MAJOR:
            f = new Filter(5,2);
            f.setDescription("Must be a major key.");
            break;

            case Chord.MINOR:
            f = new Filter(4,2);
            f.setDescription("Must be a minor key.");
            break;

            case EITHER:
            f = new Filter(new int[]{4,5},3);
            f.setDescription("Must be either major or minor.");
            break;

            case NEITHER:
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
            case Chord.MAJOR:
            {
                third = root + 4;
                fifth = root + 7;
                break;
            }
            case Chord.MINOR:
            {
                third = root + 3;
                fifth = root + 7;
                break;
            }
            case Chord.DIMINISHED:
            {
                third = root + 3;
                fifth = root + 6;
                break;
            }
            case Chord.AUGMENTED:
            {
                third = root + 4;
                fifth = root + 8;
                break;
            }
            case Chord.MAJOR_FLAT_5:
            {
                third = root + 4;
                fifth = root + 6;
                break;
            }
            case Chord.SUSPENDED_2:
            {
                third = root + 2;
                fifth = root + 7;
                break;
            }
            case Chord.SUSPENDED_4:
            {
                third = root + 5;
                fifth = root + 7;
                break;

            }
            case Chord.SUSPENDED_2_FLAT_5:
            {
                third = root + 2;
                fifth = root + 6;
                break;
            }
            case Chord.SUSPENDED_4_FLAT_5:
            {
                third = root + 5;
                fifth = root + 6;
                break;

            }
            default:
            {
                throw new IllegalStateException("FilterCreator fork up");
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

    protected void act(String id)
    {
        update();
        if (ADD_FILTER.equals(id)) {
            apply();
            this.dispose();
        } else {
            setUpUniqueFactors();
            appear();
        }
    }

    @Override
    protected void onClosed() {
        // do nothing
    }

    protected void apply()
    {
        String o1 = Objects.requireNonNull(list1.getSelectedItem()).toString();
        String o2 = Objects.requireNonNull(list2.getSelectedItem()).toString();
        Filter[] tempList = ArrayHelper.addX(myFilters,constructFilter(o1,o2));

        boolean[] tempList2 = ArrayHelper.addX(setList,true);

        FilterBank.setFilterStatuses(tempList2);
        FilterBank.setCurFilters(tempList);
        UI.refresh();
        update();
    }

}
