package lab1.functionPanel;

import lab1.AppMainWindow;
import lab1.MyIconButton;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hunter on 2017/9/22.
 */
public class QueryBridgeWordsPanel extends JPanel {

    public final static ImageIcon ICON_STOP = new ImageIcon(
            AppMainWindow.class.getResource("20170925175935.png"));
    public final static ImageIcon ICON_STOP_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925175903.png"));
    public final static ImageIcon ICON_SYNC_NOW = new ImageIcon(
            AppMainWindow.class.getResource("20170925175557.png"));
    public final static ImageIcon ICON_SYNC_NOW_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925175632.png"));

    public final static int MAIN_H_GAP = 25;

    public static JPanel DownPanel = new JPanel();

    public static MyIconButton ok;
    public static MyIconButton clear;
    public static JTextField FirstWord;
    public static JTextField SecondWord;
    public static JTextField QueryWord;

    public static String QueryWords;

    public QueryBridgeWordsPanel()
    {
        super(true);
        initialize();
        addComponent();
        addListener();
    }

    private void initialize()
    {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout(5,30));
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
        JLabel TitleLable = new JLabel("Query Bridge Words");
        TitleLable.setFont(new Font("",Font.BOLD,24));
        UpPanel.add(TitleLable);
        return UpPanel;
    }

    private JPanel getCenterPanel()
    {
        JPanel  CenterPanel = new JPanel();
        CenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
        CenterPanel.setBackground(Color.WHITE);
        JPanel gridPanel1 = new JPanel();
        JPanel gridPanel2 = new JPanel();
        gridPanel1.setBackground(Color.WHITE);
        gridPanel2.setBackground(Color.WHITE);
        gridPanel1.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
        gridPanel2.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
        JLabel word1 = new JLabel("first word");
        word1.setFont(new Font("",0,16));
        JLabel word2 = new JLabel("second word");
        word2.setFont(new Font("",0,16));
        FirstWord = new JTextField();
        FirstWord.setFont(new Font("",0,16));
        FirstWord.setPreferredSize(new Dimension(260,40));
        SecondWord = new JTextField();
        SecondWord.setFont(new Font("",0,16));
        SecondWord.setPreferredSize(new Dimension(260,40));
        gridPanel1.add(word1);
        gridPanel1.add(FirstWord);
        gridPanel2.add(word2);
        gridPanel2.add(SecondWord);

        JPanel gridPanel3 = new JPanel();
        gridPanel3.setBackground(Color.WHITE);
        gridPanel3.setLayout(new FlowLayout(FlowLayout.CENTER,60,0));
        ok = new MyIconButton(ICON_SYNC_NOW,ICON_SYNC_NOW_ENABLE ,
                ICON_SYNC_NOW, "");
        clear = new MyIconButton(ICON_STOP, ICON_STOP_ENABLE,
                ICON_STOP, "");
        gridPanel3.add(clear);
        gridPanel3.add(ok);

        CenterPanel.add(gridPanel1);
        CenterPanel.add(gridPanel2);
        CenterPanel.add(gridPanel3);
        return CenterPanel;
    }

    private JPanel getDownPanel()
    {
        DownPanel.setBackground(Color.WHITE);
        DownPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        JLabel queryWord = new JLabel("query words: ");
        queryWord.setFont(new Font("",0,16));
        QueryWord = new JTextField();
        QueryWord.setPreferredSize(new Dimension(450,60));
        DownPanel.add(queryWord);
        DownPanel.add(QueryWord);
        //DownPanel.setVisible(false);
        return DownPanel;
    }

    private void addListener()
    {
        ok.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String fw = FirstWord.getText().toString().trim();
                String sw = SecondWord.getText().toString().trim();
                if( Objects.equals(fw, "") || Objects.equals(sw,""))
                {
                    AppMainWindow.ReInputFlag = 2;
                    FirstWord.setText("");
                    SecondWord.setText("");
                    AppMainWindow.window.ErrorSize();
                    AppMainWindow.mainPanel.removeAll();
                    AppMainWindow.mainPanel.add(AppMainWindow.inputErrorpanel,BorderLayout.CENTER);
                    AppMainWindow.mainPanel.updateUI();
                }
                else
                {
                    QueryWords = AppMainWindow.graph.queryBridgeWords(fw, sw);
                    QueryWord.setText(QueryWords);
                }
                //DownPanel.setVisible(true);
            }
        });

        clear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                FirstWord.setText("");
                SecondWord.setText("");
                QueryWord.setText("");
                AppMainWindow.mainPanel.removeAll();
                AppMainWindow.mainPanel.add(AppMainWindow.functionChoosepanel,BorderLayout.CENTER);
                AppMainWindow.mainPanel.updateUI();
            }
        });
    }
}
