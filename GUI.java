package notepad;
/**
 *
 * @author NAYEMA FERDOUSHI
 * PATUAKHALI SCIENCE & TECHNOLOGY UNIVERSITY
 * ID 2102026
 * PROJECT NAME : RECURSIVE EDITOR 
 * 2nd SEMESTER PROJECT IN JAVA LANGUAGE
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
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
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.undo.UndoManager;

/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.undo.*;
 */
/**
 *
 * @author hp
 */
public class GUI implements ActionListener {

    JFrame window;
    //textarea
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;

    // top menu bar
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuTheme , menuAbout;
    //file menu
    JMenuItem itemNew, itemTodo, itemOpen, itemSave, itemSaveAs, itemExit;
    // format menu
    JMenuItem itemWarp, itemArial, itemComic, itemTimes, item8, item12, item16, item20, item24, item26;
    JMenu menuFont, menuFontSize, menuNew;
    // color menu
    JMenuItem color1, color2, color3;
    // edit menu
    JMenuItem undo, redo;
    //About menu
    JMenuItem info, feedback, website;

    FileMacanism file = new FileMacanism(this);
    FormatMacanism format = new FormatMacanism(this);
    ColorMacanism color = new ColorMacanism(this);
    EditMacanism edit = new EditMacanism(this);
    AboutMacanism about = new AboutMacanism(this);

    UndoManager udo = new UndoManager();

    public static void main(String[] args) {

        new GUI();
    }

    public GUI() {

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
        color.changeColor("Light Mode");

        window.setVisible(true);
    }

    public void createWindow() {

        window = new JFrame("Recursive Editor ");
        window.setSize(800, 600);
        //window.setLocation(400, 140);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void createTextArea() {

        textArea = new JTextArea();

        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {

            public void undoableEditHappened(UndoableEditEvent e) {
                udo.addEdit(e.getEdit());
            }
        });
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);

    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        //menuFile.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        menuTheme = new JMenu("Theme");
        menuBar.add(menuTheme);

        menuAbout = new JMenu("About");
        menuBar.add(menuAbout);

    }

    public void createFileMenu() {

        menuNew = new JMenu("New");
        menuFile.add(menuNew);

        itemNew = new JMenuItem("NewPage");
        itemNew.addActionListener(this);
        itemNew.setActionCommand("New");
        menuNew.add(itemNew);

        itemTodo = new JMenuItem("Todo List");
        itemTodo.addActionListener(this);
        itemTodo.setActionCommand("Todo List");
        menuNew.add(itemTodo);

        itemOpen = new JMenuItem("Open");
        itemOpen.addActionListener(this);
        itemOpen.setActionCommand("Open");
        menuFile.add(itemOpen);

        itemSave = new JMenuItem("Save");
        itemSave.addActionListener(this);
        itemSave.setActionCommand("Save");
        menuFile.add(itemSave);

        itemSaveAs = new JMenuItem("SaveAs");
        itemSaveAs.addActionListener(this);
        itemSaveAs.setActionCommand("SaveAs");
        menuFile.add(itemSaveAs);

        itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(this);
        itemExit.setActionCommand("Exit");
        menuFile.add(itemExit);

    }

    public void createFormatMenu() {

        itemWarp = new JMenuItem("Word Wrap: Off");
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

    public void createColorMenu() {

        color1 = new JMenuItem("Light Mode");
        color1.addActionListener(this);
        color1.setActionCommand("Light Mode");
        menuTheme.add(color1);

        color2 = new JMenuItem("Dark Mode");
        color2.addActionListener(this);
        color2.setActionCommand("Dark Mode");
        menuTheme.add(color2);

        color3 = new JMenuItem("Coustomised");
        color3.addActionListener(this);
        color3.setActionCommand("Coustomised");
        menuTheme.add(color3);

    }

    public void createEditMenu() {
        undo = new JMenuItem("Undo");
        undo.addActionListener(this);
        undo.setActionCommand("Undo");
        menuEdit.add(undo);

        redo = new JMenuItem("Redo");
        redo.addActionListener(this);
        redo.setActionCommand("Redo");
        menuEdit.add(redo);

    }

    public void createAboutMenu() {
        info = new JMenuItem("Information");
        info.addActionListener(this);
        info.setActionCommand("Information");
        menuAbout.add(info);

        feedback = new JMenuItem("Feedback ");
        feedback.addActionListener(this);
        feedback.setActionCommand("Feedback");
        menuAbout.add(feedback);

        website = new JMenuItem("PSTU CSE");
        website.addActionListener(this);
        website.setActionCommand("PSTU CSE");
        menuAbout.add(website);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch (command) {

            case "New": {
                file.newFile();
                break;
            }

            case "Todo List": {
                file.todo();
                break;
            }

            case "Open": {
                file.open();
                break;
            }

            case "SaveAs": {
                file.saveAs();
                break;
            }

            case "Save": {
                file.save();
                break;
            }
            case "Exit": {
                file.exit();
                break;
            }

            case "Word Wrap": {
                format.wordWrap();
                break;
            }
            case "8": {
                format.createFont(8);
                break;
            }
            case "12": {
                format.createFont(12);
                break;
            }
            case "16": {
                format.createFont(16);
                break;
            }
            case "20": {
                format.createFont(20);
                break;
            }
            case "24": {
                format.createFont(24);
                break;
            }
            case "26": {
                format.createFont(26);
                break;
            }

            case "Arial": {
                format.setFont(command);
                break;
            }
            case "Comic Sans MS": {
                format.setFont(command);
                break;
            }
            case "Times New Roman": {
                format.setFont(command);
                break;
            }
            case "Light Mode": {
                color.changeColor(command);
                break;
            }
            case "Dark Mode": {
                color.changeColor(command);
                break;
            }
            case "Coustomised": {
                color.changeColor(command);
                break;
            }
            case "Undo": {
                edit.undo();
                break;
            }
            case "Redo": {
                edit.redo();
                break;
            }
            case "Information": {
                about.setAbout();
                break;
            }

            case "Feedback": {
                about.feedback();
                break;
            }

            case "PSTU CSE": {
                about.pstuWeb();
                break;
            }
        }
    }

    public class FileMacanism {

        GUI gui;
        String fileName;
        String fileAddress;

        public FileMacanism(GUI gui) {
            this.gui = gui;
        }

        public void newFile() {

            gui.textArea.setText("");
            gui.window.setTitle("New");
            fileName = null;
            fileAddress = null;

        }

        public void todo() {
            ToDoList todoList = new ToDoList();

        }

        public void open() {

            FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
            fd.setVisible(true);

            if (fd.getFile() != null) {

                fileName = fd.getFile();
                fileAddress = fd.getDirectory();
                gui.window.setTitle(fileName);

            }

            System.out.println("File address and File name " + fileAddress + fileName);
            try {

                BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
                gui.textArea.setText("");

                String line = null;

                while ((line = br.readLine()) != null) {
                    gui.textArea.append(line + "\n");
                }
                br.close();

            } catch (Exception e) {
                System.out.println("FILE IS NOT OPENED! ");
            }
        }

        public void save() {

            if (fileName == null) {
                saveAs();
            } else {
                try {
                    FileWriter fw = new FileWriter(fileAddress + fileName);
                    fw.write(gui.textArea.getText());
                    fw.close();

                } catch (Exception e) {
                    System.out.println("Something is Wrong");
                }
            }
        }

        public void saveAs() {
            FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
            fd.setVisible(true);

            if (fd.getFile() != null) {
                fileName = fd.getFile();
                fileAddress = fd.getDirectory();

                gui.window.setTitle(fileName);
            }

            try {

                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                fw.close();

            } catch (Exception e) {
                System.out.println("SOMETHING IS WRONG");
            }
        }

        public void exit() {
            System.exit(0);
        }
    }

    public class FormatMacanism {

        GUI gui;
        Font arial, comic, times;
        String selectedFont;

        public FormatMacanism(GUI gui) {
            this.gui = gui;
        }

        public void wordWrap() {

            if (gui.wordWrapOn == false) {
                gui.wordWrapOn = true;
                gui.textArea.setLineWrap(true);
                gui.textArea.setWrapStyleWord(true);
                gui.itemWarp.setText("Word Wrap: On");
            } else if (gui.wordWrapOn == true) {
                gui.wordWrapOn = false;
                gui.textArea.setLineWrap(false);
                gui.textArea.setWrapStyleWord(false);
                gui.itemWarp.setText("Word Wrap: Off");
            }
        }

        public void createFont(int fontSize) {
            arial = new Font("Arial", Font.PLAIN, fontSize);
            comic = new Font("Comic Sans MS", Font.PLAIN, fontSize);
            times = new Font("Times New Roman", Font.PLAIN, fontSize);

            setFont(selectedFont);
        }

        public void setFont(String font) {
            selectedFont = font;

            switch (selectedFont) {
                case "Arial": {
                    gui.textArea.setFont(arial);
                    break;
                }
                case "Comic Sans MS": {
                    gui.textArea.setFont(comic);
                    break;
                }
                case "Times New Roman": {
                    gui.textArea.setFont(times);
                    break;
                }
            }
        }
    }

    public class ColorMacanism {

        GUI gui;

        public ColorMacanism(GUI gui) {
            this.gui = gui;
        }

        public void changeColor(String color) {
            switch (color) {
                case "Light Mode": {
                    gui.window.getContentPane().setBackground(Color.white);
                    gui.textArea.setBackground(Color.WHITE);
                    gui.textArea.setForeground(Color.black);

                    break;
                }
                case "Dark Mode": {
                    gui.window.getContentPane().setBackground(Color.black);
                    gui.textArea.setBackground(Color.black);
                    gui.textArea.setForeground(Color.white);
                    break;
                }
                case "Coustomised": {
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

        public EditMacanism(GUI gui) {
            this.gui = gui;
        }

        public void undo() {
            gui.udo.undo();
        }

        public void redo() {
            gui.udo.redo();
        }
    }

    public class AboutMacanism {

        GUI gui;

        public AboutMacanism(GUI gui) {
            this.gui = gui;
        }

        public void setAbout() {
            Lab02 l = new Lab02();
            String[] args = null;
            l.main(args);
        }

        public void feedback() {
            ShowInfo sms = new ShowInfo();

        }

        public void pstuWeb() {
            PSTUWebsite pstu = new PSTUWebsite();
            String[] args = null;
            pstu.main(args);
        }

    }

    public class Lab02 {

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                createAndShowGUI();
            });

        }

        private static void createAndShowGUI() {
            JFrame frame = new JFrame("LINK");

            frame.setVisible(true);
            frame.setSize(200, 200);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
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

    public class ShowInfo extends JFrame implements ActionListener {

        private JTextField txtapi, txtmsg, txtsender, txtnumber;

        public ShowInfo() {
            setTitle("Send Feedback SMS ");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));

            JLabel labelApiKey = new JLabel("Sender Number :");
            txtapi = new JTextField();
            JLabel lblMessage = new JLabel("Recipient Number :");
            txtmsg = new JTextField();
            JLabel lblSender = new JLabel("Message :");
            txtsender = new JTextField();
            JLabel lblNumber = new JLabel("Rating* :");
            txtnumber = new JTextField();

            /*
        JLabel labelApiKey = new JLabel("API Key:");
        txtapi = new JTextField();
        JLabel lblMessage = new JLabel("Message:");
        txtmsg = new JTextField();
        JLabel lblSender = new JLabel("Sender:");
        txtsender = new JTextField();
        JLabel lblNumber = new JLabel("Recipient Number:");
        txtnumber = new JTextField();
             */
            JButton btnSend = new JButton("Send");
            btnSend.addActionListener(this);

            panel.add(labelApiKey);
            panel.add(txtapi);
            panel.add(lblMessage);
            panel.add(txtmsg);
            panel.add(lblSender);
            panel.add(txtsender);
            panel.add(lblNumber);
            panel.add(txtnumber);
            panel.add(new JLabel());
            panel.add(btnSend);

            add(panel);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Send")) {
                try {
                    String apiKey = "apikey=" + URLEncoder.encode(txtapi.getText(), "UTF-8");
                    String message = "&message=" + URLEncoder.encode(txtmsg.getText(), "UTF-8");
                    String sender = "&sender=" + URLEncoder.encode(txtsender.getText(), "UTF-8");
                    String numbers = "&numbers=" + URLEncoder.encode(txtnumber.getText(), "UTF-8");

                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();

                    String data = apiKey + numbers + message + sender;

                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));

                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;

                    while ((line = rd.readLine()) != null) {
                        JOptionPane.showMessageDialog(null, "Message sent successfully.");
                    }

                    rd.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        }

        public static void main(String[] args) {

        }
    }

    public class ToDoList extends JFrame {

        TitleBar title = new TitleBar();
        ButtonPanel buttonPanel = new ButtonPanel();
        List list = new List();
        private JButton addtask;
        private JButton clear;

        public ToDoList() {

            this.setSize(450, 700);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setLocationRelativeTo(null);

            this.add(this.title, BorderLayout.NORTH);
            this.add(this.buttonPanel, BorderLayout.SOUTH);
            this.add(this.list, BorderLayout.CENTER);

            addtask = buttonPanel.getAddTaskButton();
            clear = buttonPanel.getClearButton();

            //  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            addListener();
        }

        public void addListener() {

            addtask.addMouseListener(new MouseAdapter() {

                public void mousePressed(MouseEvent e) {
                    Task task = new Task();
                    list.add(task);
                    list.indexNumber();
                    revalidate();

                    JButton done = task.getDone();
                    done.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            task.doneStatus();
                            revalidate();
                        }

                    });

                    JButton remove = task.getRemove();
                    remove.addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            list.remove(task);
                            list.indexNumber();
                            revalidate();
                            repaint();
                        }

                    });
                }
            });

            clear.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    Component[] taskList = list.getComponents();
                    for (int i = 0; i < taskList.length; i++) {
                        if (taskList[i] instanceof Task) {
                            list.remove((Task) taskList[i]);

                        }
                    }
                    revalidate();
                    repaint();
                }

            });
        }

    }

    class Main {

        public static void main(String[] args) {

        }
    }

    class Task extends JPanel {

        private JLabel index;
        private JTextField taskName;
        private JButton done;
        private JButton remove;

        public Task() {
            GridLayout layoutTask = new GridLayout(1, 4);
            layoutTask.setHgap(5);
            this.setPreferredSize(new Dimension(450, 20));
            this.setBackground(Color.WHITE);
            this.setLayout(layoutTask);

            index = new JLabel("");

            //  index.setPreferredSize(new Dimension(2,2));
            index.setHorizontalAlignment(JLabel.LEFT);
            index.setBackground(Color.pink);
            this.add(this.index);

            taskName = new JTextField("Enter task");
            taskName.setPreferredSize(new Dimension(20, 20));
            taskName.setBorder(BorderFactory.createEmptyBorder());
            taskName.setBackground(Color.WHITE);
            this.add(taskName);

            done = new JButton("Done");
            done.setPreferredSize(new Dimension(10, 20));
            this.add(this.done);

            remove = new JButton("Remove");
            remove.setPreferredSize(new Dimension(10, 20));
            this.add(remove);

        }

        public void writeIndex(int n) {
            this.index.setText(String.valueOf(n));
            this.revalidate();
        }

        public JButton getDone() {
            return this.done;
        }

        public JButton getRemove() {
            return this.remove;
        }

        public void doneStatus() {
            this.taskName.setBackground(Color.green);
            this.index.setBackground(Color.green);
            this.remove.setBackground(Color.green);
            this.done.setBackground(Color.green);
            this.setBackground(Color.green);
            revalidate();
        }
    }

    class List extends JPanel {

        public List() {
            GridLayout layout = new GridLayout(10, 1);
            layout.setVgap(5);
            this.setLayout(layout);
            // this.setBackground(Color.LIGHT_GRAY);

        }

        public void indexNumber() {
            Component[] listComponent = this.getComponents();
            for (int i = 0; i < listComponent.length; i++) {
                if (listComponent[i] instanceof Task) {
                    ((Task) listComponent[i]).writeIndex(i + 1);
                }
            }
        }
    }

    class ButtonPanel extends JPanel {

        private JButton addtask;
        private JButton clear;

        Border emptyborder = BorderFactory.createEmptyBorder();

        public ButtonPanel() {
            this.setPreferredSize(new Dimension(450, 80));
            this.setBackground(new Color(129, 20, 207));

            addtask = new JButton("Add Task");
            addtask.setBorder(emptyborder);

            addtask.setFont(new Font("Sans-serif", Font.PLAIN, 20));
            this.add(addtask);

            this.add(Box.createHorizontalStrut(20));

            clear = new JButton("Clear All Task");
            clear.setBorder(emptyborder);
            clear.setFont(new Font("Sans-serif", Font.PLAIN, 20));
            this.add(clear);

        }

        public JButton getAddTaskButton() {
            return addtask;
        }

        public JButton getClearButton() {
            return clear;
        }
    }

    class TitleBar extends JPanel {

        JLabel TitleText = new JLabel("Todo List");

        public TitleBar() {
            this.setPreferredSize(new Dimension(450, 80));
            this.setBackground(new Color(90, 143, 123));

            TitleText.setPreferredSize(new Dimension(200, 80));
            TitleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
            TitleText.setHorizontalAlignment(JLabel.CENTER);
            this.add(this.TitleText);

        }

    }
}
