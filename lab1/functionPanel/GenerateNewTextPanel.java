package lab1.functionPanel;

import lab1.AppMainWindow;
import lab1.MyIconButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hunter on 2017/9/22.
 */
public class GenerateNewTextPanel extends JPanel {

    public final static ImageIcon ICON_STOP = new ImageIcon(
            AppMainWindow.class.getResource("20170925175557.png"));
    public final static ImageIcon ICON_STOP_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925175632.png"));
    public final static ImageIcon ICON_SYNC_NOW = new ImageIcon(
            AppMainWindow.class.getResource("20170925175935.png"));
    public final static ImageIcon ICON_SYNC_NOW_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925175903.png"));

    public static JTextField InputArea;
    public static JTextField OutputArea;

    public  static JPanel downPanel = new JPanel();

    public static MyIconButton ok;
    public static MyIconButton clear;

    public GenerateNewTextPanel()
    {
        super(true);
        initialize();
        addComponent();
        addListener();
    }

    private void initialize()
    {
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(3,1));
    }

    private void addComponent()
    {
        this.add(getUpPanel(),BorderLayout.NORTH);
        this.add(getCenterPanel(), BorderLayout.CENTER);
        this.add(getDownPanel(),BorderLayout.SOUTH);
    }

    private JPanel getUpPanel()
    {
        JPanel upPanel = new JPanel();
        upPanel.setBackground(Color.WHITE);
        upPanel.setLayout(new GridLayout(1,1));
        InputArea = new JTextField();
        InputArea.setBackground(Color.lightGray);
        InputArea.setFont(new Font("",0,16));
        //InputArea.setPreferredSize(new Dimension(200,50));
        upPanel.add(InputArea);
        return upPanel;
    }

    private JPanel getCenterPanel()
    {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new GridLayout(1,2));
        JPanel gridPanel1 = new JPanel();
        gridPanel1.setBackground(Color.WHITE);
        gridPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel gridPanel2 = new JPanel();
        gridPanel2.setBackground(Color.WHITE);
        gridPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));

        ok = new MyIconButton(ICON_STOP, ICON_STOP_ENABLE,
                ICON_STOP, "");
        clear = new MyIconButton(ICON_SYNC_NOW, ICON_SYNC_NOW_ENABLE,
                ICON_SYNC_NOW, "");

        gridPanel1.add(clear);
        gridPanel2.add(ok);

        centerPanel.add(gridPanel1);
        centerPanel.add(gridPanel2);

        return centerPanel;
    }

    private JPanel getDownPanel()
    {

        downPanel.setBackground(Color.WHITE);
        downPanel.setLayout(new GridLayout(1,1));
        OutputArea = new JTextField();
        OutputArea.setBackground(Color.lightGray);
        OutputArea.setFont(new Font("",0,16));
        downPanel.add(OutputArea);
        downPanel.setVisible(false);
        return downPanel;
    }

    private void addListener()
    {
        ok.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String inputText = InputArea.getText().toString().trim();
                String outputText = AppMainWindow.graph.generateNewText(inputText);
                OutputArea.setText(outputText);
                downPanel.setVisible(true);
            }
        });

        clear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                InputArea.setText("");
                OutputArea.setText("");
                downPanel.setVisible(false);
                AppMainWindow.mainPanel.removeAll();
                AppMainWindow.mainPanel.add(AppMainWindow.functionChoosepanel,BorderLayout.CENTER);
                AppMainWindow.mainPanel.updateUI();
            }
        });
    }
}
