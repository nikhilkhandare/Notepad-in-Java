import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
public class Notepad extends JFrame implements ActionListener
{
    JFrame p;
    JTextArea t;
    JMenuItem iwrap;
    boolean WordWrapON = false;
    String filename;
    String fileAddress;
    //Font Arial,Georgia,TimesNewRoman;
    //String selectedFont;
    Notepad(){
        p = new JFrame("Untitled");
        t = new JTextArea();
        Font font = new Font("Georgia",Font.BOLD,16);
        t.setFont(font);
        t.setForeground(Color.BLACK);
        t.setBackground(Color.WHITE);
        t.setTabSize(4);

        //TOP MENUBAR
        JMenuBar mb = new JMenuBar();
        //FILE
        JMenu m1 = new JMenu("File");
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi4 = new JMenuItem("SaveAs");
        JMenuItem mi5 = new JMenuItem("Print");
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi4);
        m1.add(mi5);

        //EDIT
        JMenu m2 = new JMenu("Edit");
        JMenuItem mi6 = new JMenuItem("Cut");
        JMenuItem mi7 = new JMenuItem("Copy");
        JMenuItem mi8 = new JMenuItem("Paste");
        JMenuItem mi9 = new JMenuItem("PDF");
        mi6.addActionListener(this);
        mi7.addActionListener(this);
        mi8.addActionListener(this);
        mi9.addActionListener(this);  
        m2.add(mi6);
        m2.add(mi7);
        m2.add(mi8);
        m2.add(mi9);

        //FORMAT
        JMenu m3 = new JMenu("Format");
        iwrap = new JMenuItem("WordWrap OFF");
        iwrap.addActionListener(this);
        m3.add(iwrap);
       
        JMenu m4 = new JMenu("Font Style");
        m3.add(m4);
        JMenuItem mi16 = new JMenuItem("Arial");
        mi16.addActionListener(this);
        m4.add(mi16);
        JMenuItem mi17 = new JMenuItem("Georgia");
        mi17.addActionListener(this);
        m4.add(mi17);
        JMenuItem mi18 = new JMenuItem("TimesNewRoman");
        mi18.addActionListener(this);
        m4.add(mi18);
        JMenu m5 = new JMenu("Font Size");
        m3.add(m5);
        JMenuItem mi11 = new JMenuItem("8");
        mi11.addActionListener(this);
        m5.add(mi11);
        JMenuItem mi12 = new JMenuItem("12");
        mi12.addActionListener(this);
        m5.add(mi12);
        JMenuItem mi13 = new JMenuItem("16");
        mi13.addActionListener(this);
        m5.add(mi13);
        JMenuItem mi14 = new JMenuItem("20");
        mi14.addActionListener(this);
        m5.add(mi14);
        JMenuItem mi15 = new JMenuItem("24");
        mi15.addActionListener(this);
        m5.add(mi15);
        //EXIT 
        JMenuItem close = new JMenuItem("Exit");
        close.addActionListener(this);
        
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
       
        mb.add(close);
        p.setJMenuBar(mb);
        p.add(t);
        p.setSize(800,900);
        p.setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.setVisible(true);


    }
    /*
    public void setFont(String font)
    {
        selectedFont = font;
    }
    public void createFont(int fontSize)
    {
        Arial = new Font("Arial",Font.PLAIN,fontSize);
        Georgia = new Font("Georgia",Font.PLAIN,fontSize);
        TimesNewRoman = new Font("TimesNewRoman",Font.PLAIN,fontSize);
    }
    */
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if(s.equals("New")){
            t.setText("");  
        }
        /*
        else if(s.equals("Arial"))
        {
            Arial  = new Font("Arial",Font.PLAIN);
            format.setFont(s);
        } 
        else if(s.equals("Georgia"))
        {
            Georgia = new Font("Georgia",Font.PLAIN);
            t.setFont(Font);
        }
        else if(s.equals("TimesNewRoman"))
        {
            TimesNewRoman = new Font("TimesNewRoman",Font.PLAIN);
            t.setFont(Font);
        }
        */
        else if(s.equals("WordWrap OFF"))
        {
            if(WordWrapON == false){
                WordWrapON = true;
                t.setLineWrap(true);
                t.setWrapStyleWord(true);
                iwrap.setText("WordWrap ON");
            }
            else if(WordWrapON == true){
                WordWrapON = false;
                t.setLineWrap(false);
                t.setWrapStyleWord(false);
                iwrap.setText("WordWrap OFF");
            }

        }

        /*else if(s.equals("PDF"))
        {
            Document pdfDoc = new Document(PageSize.A4);
            PdfWriter.getInstance(pdfDoc, new FileOutputStream("src/output/txt.pdf"))
            .setPdfVersion(PdfWriter.PDF_VERSION_1_7);
            pdfDoc.open();

            Font myfont = new Font("Goregia",Font.BOLD,16);
            pdfDoc.add(new Paragraph("\n"));

        }
*/
        else if(s.equals("Exit"))
        {
            System.exit(0);
        }
        else if(s.equals("Open")){
            JFileChooser j = new JFileChooser();
            int r = j.showOpenDialog(null);
            if(r == JFileChooser.APPROVE_OPTION){
                try {
                    File f1 = new File(j.getSelectedFile().getAbsolutePath());
                    filename = f1.getName();
                    fileAddress = f1.getName();
                    JOptionPane.showMessageDialog(null,filename);
                    JOptionPane.showMessageDialog(null,fileAddress);
                    String s1 = "",sl = "";
                    FileReader fr = new FileReader(f1);
                    BufferedReader br = new BufferedReader(fr);
                    while((s1 = br.readLine())!=null){
						sl = sl+s1+"\n";
					}
                    t.setText(sl);
                    p.setTitle(filename);
                } catch (Exception e4) {
                   JOptionPane.showMessageDialog(null,e4.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"User Cancelled");
            }
        }
        else if(s.equals("SaveAs")){
          FileDialog fd = new FileDialog(p,"save",FileDialog.SAVE);
          fd.setVisible(true);

          if(fd.getFile()!=null)
          {
              filename = fd.getFile();
               fileAddress = fd.getDirectory();
              p.setTitle(filename);
          }
          try{
            FileWriter fw = new FileWriter(fileAddress + filename);
            fw.write(t.getText());
            fw.close();
          }
          catch(Exception e10)
          {
            JOptionPane.showMessageDialog(null,e10.getMessage());
          }
        }

        else if(s.equals("Save"))
        {
            JOptionPane.showMessageDialog(null,filename);
          if(filename == null)
          {
            JOptionPane.showMessageDialog(null,"File name is null");
            FileDialog fd = new FileDialog(p,"save",FileDialog.SAVE);
            fd.setVisible(true);
  
            if(fd.getFile()!=null)
            {
                filename = fd.getFile();
                 fileAddress = fd.getDirectory();
                p.setTitle(filename);
            }
            try{
              FileWriter fw = new FileWriter(fileAddress + filename);
              fw.write(t.getText());
              fw.close();
            }
            catch(Exception e10)
            {
              JOptionPane.showMessageDialog(null,e10.getMessage());
            }
          }
          else{
            try {
                FileWriter fw = new FileWriter(fileAddress + filename);
                fw.write(t.getText());
                p.setTitle(filename);
                fw.close();
            } catch (Exception e11) {
                JOptionPane.showMessageDialog(null,"Something Wrong");
            }

          }

        }
        
      
        else if(s.equals("Print")){
            try {
                t.print();
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null,e2.getMessage());
            }
        }
        else if(s.equals("Cut")){
            t.cut();
        }
        else if(s.equals("Copy")){
            t.copy();
        }
        else if(s.equals("Paste")){
            t.paste();
        }

    }
    public static void main(String args[])
    {
        Notepad np = new Notepad();
    }

}