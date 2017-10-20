package gui;

import dot.DotCompiler;
import graph.Graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by Hunter on 2017/9/20.
 */
public class InputPathPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final ImageIcon ICON_STOP = new ImageIcon(
            AppMainWindow.class.getResource("20170925175557.png"));
    public static final ImageIcon ICON_STOP_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925175632.png"));
    public static final ImageIcon ICON_SYNC_NOW = new ImageIcon(
            AppMainWindow.class.getResource("20170925175935.png"));
    public static final ImageIcon ICON_SYNC_NOW_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925175903.png"));


    public static final int MAIN_H_GAP = 25;

    final JTextField textField = new JTextField("",100);



    public static MyIconButton OpenFile;
    public static MyIconButton PreStep;

    public InputPathPanel()
    {
        super(true);
        initialize();
        addComponent();
        addListener();
    }

    private void initialize()
    {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout(5,100));
    }

    private void addComponent()
    {
        this.add(getUpPanel(),BorderLayout.NORTH);
        this.add(getCenterPanel(), BorderLayout.CENTER);
        this.add(getDownPanel(),BorderLayout.SOUTH);
    }

    private JPanel getUpPanel()
    {
        JPanel UpPanel = new JPanel();
        UpPanel.setBackground(Color.WHITE);
        UpPanel.setLayout(new FlowLayout(FlowLayout.CENTER,25,5));
        JLabel TitleLable = new JLabel("Input File Path");
        TitleLable.setFont(new Font("",Font.BOLD,24));
        UpPanel.add(TitleLable);
        return UpPanel;
    }

    private JPanel getCenterPanel()
    {
        JPanel CenterPanel = new JPanel();
        CenterPanel.setBackground(Color.WHITE);
        CenterPanel.setLayout(new GridLayout(1, 1));
        textField.setPreferredSize(new Dimension(10,40));
        textField.setFont(new Font("",0,16));
        CenterPanel.add(textField,BorderLayout.CENTER);

        return CenterPanel;
    }

    private JPanel getDownPanel()
    {
        JPanel DownPanel = new JPanel();
        DownPanel.setBackground(Color.WHITE);
        DownPanel.setLayout(new GridLayout(1,2));
        JPanel panelGrid1 = new JPanel();
        panelGrid1.setBackground(Color.WHITE);
        panelGrid1.setLayout(new FlowLayout(FlowLayout.CENTER,MAIN_H_GAP,15));
        JPanel panelGrid2 = new JPanel();
        panelGrid2.setBackground(Color.WHITE);
        panelGrid2.setLayout(new FlowLayout(FlowLayout.CENTER, MAIN_H_GAP, 15));
        JPanel panelGrid3 = new JPanel();
        panelGrid3.setBackground(Color.WHITE);
        panelGrid3.setLayout(new FlowLayout(FlowLayout.CENTER, MAIN_H_GAP, 15));
        JPanel panelGrid4 = new JPanel();
        panelGrid4.setBackground(Color.WHITE);
        panelGrid4.setLayout(new FlowLayout(FlowLayout.CENTER, MAIN_H_GAP, 15));

        OpenFile = new MyIconButton(ICON_STOP, ICON_STOP_ENABLE,
                ICON_STOP, "");
        PreStep = new MyIconButton(ICON_SYNC_NOW, ICON_SYNC_NOW_ENABLE,
                ICON_SYNC_NOW, "");

        panelGrid3.add(OpenFile);
        panelGrid2.add(PreStep);

        DownPanel.add(panelGrid1);
        DownPanel.add(panelGrid2);
        DownPanel.add(panelGrid3);
        DownPanel.add(panelGrid4);

        return DownPanel;
    }

    private void addListener()
    {
        OpenFile.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    String PathString = textField.getText();
                    System.out.println(PathString);

                    AppMainWindow.graph =  new Graph(PathString);
                    //并转换窗口
                    AppMainWindow.ImagePath = DotCompiler.saveFile(PathString, AppMainWindow.graph.toDot());

                    AppMainWindow.mainPanel.removeAll();
                    AppMainWindow.mainPanel.add(AppMainWindow.functionChoosepanel,BorderLayout.CENTER);
                    AppMainWindow.mainPanel.updateUI();

                } catch (Exception e)
                {
                    AppMainWindow.ReInputFlag = 1;
                    textField.setText("");
                    AppMainWindow.window.ErrorSize();
                    AppMainWindow.mainPanel.removeAll();
                    AppMainWindow.mainPanel.add(AppMainWindow.inputErrorpanel,BorderLayout.CENTER);
                    AppMainWindow.mainPanel.updateUI();
                }

            }
        });

        PreStep.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                AppMainWindow.mainPanel.removeAll();
                AppMainWindow.mainPanel.add(AppMainWindow.fileChoosepanel,BorderLayout.CENTER);
                AppMainWindow.mainPanel.updateUI();
            }
        });

    }
}
