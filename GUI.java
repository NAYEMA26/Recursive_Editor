
package notepad;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import java.awt.FileDialog;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javax.swing.undo.UndoManager;


/**
 *
 * @author hp
 */
public class GUI  implements ActionListener{
    JFrame window;
    //textarea
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;
    // top menu bar
    JMenuBar menuBar;
    JMenu menuFile,menuEdit,menuFormat, menuColor,menuAbout;
    //file menu
    JMenuItem itemNew, itemOpen,itemSave, itemSaveAs, itemExit;
    // format menu
    JMenuItem itemWarp,itemArial,itemComic, itemTimes,item8,item12,item16,item20,item24,item26;
    JMenu menuFont,menuFontSize;
    // color menu
    JMenuItem color1, color2, color3;
    // edit menu
    JMenuItem undo,redo;
    //help menu
    JMenuItem info;
    
    FileMacanism  file = new FileMacanism(this);
    FormatMacanism format = new FormatMacanism(this);
    ColorMacanism color = new ColorMacanism (this);
    EditMacanism edit = new EditMacanism(this);
    AboutMacanism about = new AboutMacanism(this);
    
    UndoManager udo = new UndoManager();
    public static void main(String[] args) {
        
        new GUI();
    }
    
    public GUI(){
        
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createFormatMenu();
        createColorMenu();
        createEditMenu();
        createAboutMenu();
        
        format.selectedFont = "Arial";
        format.createFont(26);
        format.wordWrap();
        color.changeColor("White");
        
        window.setVisible(true);
    }
    
    public void createWindow(){
        
        window = new JFrame("Notepad");
        window.setSize(800, 600);
        //window.setLocation(400, 140);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public void createTextArea(){
        
        textArea = new JTextArea();
        
        textArea.getDocument().addUndoableEditListener
        (new UndoableEditListener(){

            public void undoableEditHappened (UndoableEditEvent e)
            {
                udo.addEdit(e.getEdit());
            }
    });
       scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       scrollPane.setBorder(BorderFactory.createEmptyBorder());
       window.add(scrollPane);
        
    }
    
    public void createMenuBar(){
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);
       // menuBar.setBounds(5, 5, 5, 5);

        
        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        //menuFile.add(menuFile);
        
          menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);
        
         menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);
        
         menuColor = new JMenu("Color");
        menuBar.add(menuColor);
        
         menuAbout = new JMenu("About");
        menuBar.add(menuAbout);

    }
    
    public  void createFileMenu(){
        itemNew = new JMenuItem ("New");
        itemNew.addActionListener(this);
        itemNew.setActionCommand("New");
        menuFile.add(itemNew);
        
         itemOpen = new JMenuItem ("Open");
         itemOpen.addActionListener(this);
        itemOpen.setActionCommand("Open");
        menuFile.add(itemOpen);

         itemSave = new JMenuItem ("Save");
           itemSave.addActionListener(this);
        itemSave.setActionCommand("Save");
        menuFile.add(itemSave);

         itemSaveAs = new JMenuItem ("SaveAs");
           itemSaveAs.addActionListener(this);
        itemSaveAs.setActionCommand("SaveAs");
        menuFile.add(itemSaveAs);

         itemExit = new JMenuItem ("Exit");
           itemExit.addActionListener(this);
        itemExit.setActionCommand("Exit");
        menuFile.add(itemExit);
        
    }
    
    public void createFormatMenu(){
        itemWarp = new JMenuItem ("Word Wrap: Off");
        itemWarp.addActionListener(this);
        itemWarp.setActionCommand("Word Wrap");
        menuFormat.add(itemWarp);
        
        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);
        
       itemArial = new JMenuItem("Arial");
       itemArial.addActionListener(this);
       itemArial.setActionCommand("Arial");
       menuFont.add(itemArial);
       
        itemComic = new JMenuItem("Comic Sans MS");
       itemComic.addActionListener(this);
       itemComic.setActionCommand("Comic Sans MS");
       menuFont.add(itemComic);
       
        itemTimes = new JMenuItem("Times New Roman");
       itemTimes.addActionListener(this);
       itemTimes.setActionCommand("Times New Roman");
       menuFont.add(itemTimes);
       
         menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);
        
        item8 = new JMenuItem("8");
           item8.addActionListener(this);
       item8.setActionCommand("8");
       menuFontSize.add(item8);
        
       item12 = new JMenuItem("12");
           item12.addActionListener(this);
       item12.setActionCommand("12");
       menuFontSize.add(item12);
        
       item16 = new JMenuItem("16");
           item16.addActionListener(this);
       item16.setActionCommand("16");
       menuFontSize.add(item16);
        
       item20 = new JMenuItem("20");
           item20.addActionListener(this);
       item20.setActionCommand("20");
       menuFontSize.add(item20);
        
       item24 = new JMenuItem("24");
           item24.addActionListener(this);
       item24.setActionCommand("24");
       menuFontSize.add(item24);
        
       item26 = new JMenuItem("26");
           item26.addActionListener(this);
       item26.setActionCommand("26");
       menuFontSize.add(item26);
        
       
    }
    public  void createColorMenu(){
        
        color1 = new JMenuItem("White");
       color1.addActionListener(this);
       color1.setActionCommand("White");
       menuColor.add(color1);
        
       color2 = new JMenuItem("Black");
       color2.addActionListener(this);
       color2.setActionCommand("Black");
       menuColor.add(color2);
       
        color3 = new JMenuItem("Pink");
       color3.addActionListener(this);
       color3.setActionCommand("Pink");
       menuColor.add(color3);
        
    }
    
    public void createEditMenu(){
        undo = new JMenuItem("Undo");
        undo.addActionListener(this);
        undo.setActionCommand("Undo");
        menuEdit.add(undo);
        
         redo = new JMenuItem("Redo");
        redo.addActionListener(this);
        redo.setActionCommand("Redo");
        menuEdit.add(redo);
        
    }
    
    public void createAboutMenu(){
        info = new JMenuItem("Information");
        info.addActionListener(this);
        info.setActionCommand("Information");
        menuAbout.add(info);
     
    }
    @Override
    public void actionPerformed (ActionEvent e){
        
        String command = e.getActionCommand();
        
        switch(command){
            
            case "New":
            {
                file.newFile();
                break;
            }
            
             case "Open":
            {
                file.open();
                break;
            }
            
              case "SaveAs":
            {
                file.saveAs();
                break;
            }
            
               case "Save":
            {
                file.save();
                break;
            }
               case "Exit":
            {
                file.exit();
                break;
            }

                case "Word Wrap":
            {
                format.wordWrap();
                break;
            }
                case "8":
            {
                format.createFont(8);
                break;
            }
                case "12":
            {
                format.createFont(12);
                break;
            }
                case "16":
            {
                format.createFont(16);
                break;
            }
                case "20":
            {
                format.createFont(20);
                break;
            }
                case "24":
            {
                format.createFont(24);
                break;
            }
                case "26":
            {
                format.createFont(26);
                break;
            }
           
                case "Arial":
            {
                format.setFont(command);
                break;
            }
                 case "Comic Sans MS":
            {
                format.setFont(command);
                break;
            }
                 case "Times New Roman":
            {
                format.setFont(command);
                break;
            }
             case "White":
              {
                color.changeColor(command);
                 break;
              }
              case "Black":
              {
                color.changeColor(command);
                 break;
              }
              case "Pink":
              {
                color.changeColor(command);
                 break;
              }
               case "Undo":
              {
                edit.undo();
                 break;
              }
                case "Redo":
              {
                edit.redo();
                 break;
              }
                case "Information":{
                    about.setAbout();
                    break;
                }
        }
    }
 

public class FileMacanism {
    GUI gui;
    String fileName;
    String fileAddress;
    
    public FileMacanism(GUI gui){
        this.gui = gui;
    }
    
    public void newFile(){
        
        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        fileAddress = null;
        
    }
    
    public void open(){
        
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        
        if(fd.getFile() != null){
            
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
            
        }
        
        System.out.println("File address and File name "+ fileAddress + fileName);
        try{
            
            BufferedReader br =  new BufferedReader(new FileReader(fileAddress + fileName));
            gui.textArea.setText("");
            
            String line = null;
            
            while((line = br.readLine()) != null)
            {
                gui.textArea.append(line +"\n");
            }
            br.close();
            
        }catch(Exception e)
        {
            System.out.println("FILE IS NOT OPENED! ");
        }
    }
    
        public  void save(){
            
            if(fileName == null)
            {
                saveAs();
            }
            else
            {
                try{
                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                fw.close();

                }catch(Exception e)
                {
                    System.out.println("Something is Wrong");
                }
            }
        }
        
        public  void saveAs(){
            FileDialog fd = new FileDialog(gui.window,"Save", FileDialog.SAVE);
            fd.setVisible(true);
            
            if(fd.getFile() != null)
            {
                fileName = fd.getFile();
                fileAddress = fd.getDirectory();
                
                gui.window.setTitle(fileName);
            }
            
            try{
                
                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                fw.close();
                
            }catch (Exception e)
            {
                System.out.println("SOMETHING IS WRONG");
            }
        }
        
        public void exit(){
            System.exit(0);
        }
}
public class FormatMacanism {
    
    GUI gui ;
    Font arial,comic,times;
    String selectedFont;
    public FormatMacanism(GUI gui){
        this.gui = gui;
    }
    
    public void wordWrap(){
        
        if(gui.wordWrapOn == false)
        {
            gui.wordWrapOn = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.itemWarp.setText("Word Wrap: On");
        }
        else if(gui.wordWrapOn == true)
        {
            gui.wordWrapOn = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.itemWarp.setText("Word Wrap: Off");
        }
    }
    
    public void createFont(int fontSize){
        arial = new Font  ("Arial", Font.PLAIN, fontSize);
        comic = new Font  ("Comic Sans MS", Font.PLAIN, fontSize);
        times = new Font  ("Times New Roman", Font.PLAIN, fontSize);
        
        setFont(selectedFont);
    }
    
    public void setFont(String font){
        selectedFont = font;
        
        switch(selectedFont)
        {
            case "Arial":
            {
                gui.textArea.setFont(arial);
                break;
            }
             case "Comic Sans MS":
            {
                gui.textArea.setFont(comic);
                break;
            }
             case "Times New Roman":
            {
                gui.textArea.setFont(times);
                break;
            }
        }
    }
}
public class ColorMacanism {
    GUI gui;
    
    public ColorMacanism (GUI gui)
    {
        this.gui = gui;
    }
    
    public void changeColor (String color)
    {
        switch(color)
        {
            case "White":
            {
                gui.window.getContentPane().setBackground(Color.white);
                gui.textArea.setBackground(Color.WHITE);
                gui.textArea.setForeground(Color.black);
                
                break;
            }
             case "Black":
            {
                gui.window.getContentPane().setBackground(Color.black);
                gui.textArea.setBackground(Color.black);
                gui.textArea.setForeground(Color.white);
                break;
            }
             case "Pink":
            {
                gui.window.getContentPane().setBackground(Color.YELLOW);
                gui.textArea.setBackground(Color.YELLOW);
                gui.textArea.setForeground(Color.black);
                break;
            }
        }
    }
}
public class EditMacanism {
    GUI gui;
    public EditMacanism(GUI gui){
        this.gui = gui;
    }
    
   public void undo(){
        gui.udo.undo();
    }
    
    public void redo(){
       gui.udo.redo();
    }
}

public class AboutMacanism {
    GUI gui;
    public AboutMacanism(GUI gui){
        this.gui = gui;
    }
    
    public void setAbout(){
        Lab02 l = new Lab02();
        String[] args = null;
        l.main(args);
    }
    
    
}

    public class Lab02{
        
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
            
        }
    
     private static void createAndShowGUI() {
        JFrame frame = new JFrame("Lab02");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton button = new JButton("Visit GitHub");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    openWebpage("https://github.com/NAYEMA26");
                } catch (URISyntaxException ex) {
                    Logger.getLogger(Lab02.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Lab02.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        panel.add(button);

        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    private static void openWebpage(String url) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop(); // Handle the exception appropriately, for instance, display an error message
        if (desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(new URI(url));
        } else {
            // If not supported or not allowed, show a message to the user
            JOptionPane.showMessageDialog(null, "Browsing is not supported on this platform.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }   
    }

}
