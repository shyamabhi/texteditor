import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class texteditor extends JFrame implements ActionListener {

    JScrollPane scrollpane;
    JTextArea textArea;
    JSpinner fontsizespinner;
    JComboBox fontbox;
    JMenuBar menuBar;
    JMenu filemenu;
    JMenuItem save;
    JMenuItem exit;
    JLabel fontsizelabel;

    texteditor(){
        this.setSize(500,600);
        this.setTitle("TextEditor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        // text area
        textArea = new JTextArea();
        textArea.setSelectedTextColor(Color.black);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));

        // pane
        scrollpane = new JScrollPane(textArea);
        scrollpane.setPreferredSize(new Dimension(450, 450));

        // font size pinner

        fontsizespinner = new JSpinner();
//        fontsizespinner.setPreferredSize(new Dimension(50, 25));
        fontsizespinner.setSize(new Dimension(50, 25));
        fontsizespinner.setValue(18);

        fontsizespinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontsizespinner.getValue()));
            }
        });

        // add color option on your own
        // add font style option on your own (Bold, Plain, Italics)
        // add autosave, to the same file.
        // you can ask over Whatsapp group if you find it difficult to implement. I will share its code as well.

        // font scroll bar

        String[] fonts = GraphicsEnvironment. getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontbox = new JComboBox(fonts);
        fontbox.addActionListener(this);

        // creating menu bar
        menuBar = new JMenuBar();
        filemenu = new JMenu("File");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        save.addActionListener(this);
        filemenu.add(save);
        filemenu.add(exit);
        menuBar.add(filemenu);

        // add all the elements in my Jframe

        fontsizelabel = new JLabel("Font size:");

        this.setJMenuBar(menuBar);
        this.add(fontsizelabel);
        this.add(fontsizespinner);
        this.add(fontbox);
        this.add(scrollpane);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fontbox){
            textArea.setFont(new Font((String) fontbox.getSelectedItem(), Font.PLAIN, (int) fontsizespinner.getValue()));
        }

        if(e.getSource() == exit){
            System.exit(29);
        }

        if(e.getSource() == save){
            JFileChooser file_chooser = new JFileChooser();
            file_chooser.setCurrentDirectory(new File("."));

            int response = file_chooser.showSaveDialog(null);

            if(response == 0){
                // I need to save this file
                File file = new File(file_chooser.getSelectedFile().getAbsolutePath());
                PrintWriter text;
                try {
                    text = new PrintWriter(file);
                    text.println(textArea.getText());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                text.close();
            }
        }
    }
}
