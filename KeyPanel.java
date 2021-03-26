            import javax.swing.*;
            import java.awt.*; 
            /**
             * Write a description of class KeyPanel here.
             *
             * @author (your name)
             * @version (a version number or a date)
             */
            public class KeyPanel extends EasyPanel
            {
                // instance variables - replace the example below with your own
            
                MusicPlayer playr;
                
                
                
                public void resize(int num, int dummy) //sets tempo
                {
                    playr.setTempo(num);
                }
                
                
                public int[] getEnclosers(String str, String special)
                {
                    if (! (special.length() == 2))
                    {
                        return new int[]{-1,-1};
                        
                    }
                    return new int[]{str.indexOf(special.substring(0,1)),str.indexOf(special.substring(1,2))};
                
                }
                
                /**
                 * Filters out tags
                 */
                public String parse(String name)
                {
                    int[] pt = getEnclosers(name,"[]");
                    String dispName;
                    if (pt[0] > -1)
                    {
                        dispName =  quickSubstring(name,pt);
                    }
                    else
                    {
                        dispName =  name;
                    }
                    for(boolean i = true; i == true;)
                    {
                        pt = getEnclosers(dispName,"{}");
                        i = false;
                        if (pt[0] > -1 && pt[1] > -1)
                        {
                        dispName = dispName.substring(0,pt[0]) 
                            + dispName.substring(pt[1]+1); //name + notes
                        i = true;
                    }
                        
                    }
                    return dispName;
                    
                }
                
                
                    
                    

                
                private String quickSubstring(String name, int[] pts)
                {
                    String newName =  name.substring(0,pts[0]-1) + name.substring(pts[1]+1);
                    return newName;
                    
                }
                
                /**
                 * Constructor for objects of class KeyPanel
                 */
                public KeyPanel(int num, int[] key, String name)
                {
                    
                    super();
                    
                    String lbl = "#" + String.format("%03d",num) + ": " + parse(name);
            
                    
                    //JButton jb1 = new JButton("Chords");    
                    ChordViewer chrds = new ChordViewer(key,name);
            
                    
                    chrds.myKey = key;
            
                    JButton jb2 = new JButton("Intervals");
                    
                    playr = new MusicPlayer(key);
                    //jb3.addActionListener(playr);
                    
                    JButton jb4 = new JButton("Button 4");
                    JButton jb5 = new JButton("Button 5");
            
                    
                    
                    
                    
            
                    JLabel label = new JLabel(lbl);
                    int[] pt = getEnclosers(name,"[]");
                    if (pt[0] > -1 && pt[1] > -1)
                    {
                        label.setToolTipText(name.substring(pt[0]+1,pt[1]));
                    }
                    //System.out.println(key.toString());
        //System.out.println(parse(name).substring(0,11));
        if (parse(name).substring(0,11).equals("Unnamed Key"))
        {
            label.setFont(new Font(label.getFont().getFontName(),Font.ITALIC,12));
        }
        add(label);//add(jb3);
        addButton("Chords",chrds); addButton("Listen",playr); 
    }

}
