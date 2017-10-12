package lab1.functionPanel;

import lab1.AppMainWindow;
import lab1.MyIconButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Hunter on 2017/9/22.
 */
public class FunctionChoosePanel extends JPanel
{

    public final static ImageIcon ICON_STOP = new ImageIcon(
            AppMainWindow.class.getResource("20170925180126.png"));

    public final static ImageIcon ICON_STOP_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925180158.png"));
    // 停止 失效
    public final static ImageIcon ICON_STOP_DISABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925180257.png"));
    // 立即同步 默认
    public final static ImageIcon ICON_SYNC_NOW = new ImageIcon(
            AppMainWindow.class.getResource("20170925180226.png"));
    // 立即同步 激活
    public final static ImageIcon ICON_SYNC_NOW_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925180406.png"));
    // 立即同步 失效
    public final static ImageIcon ICON_SYNC_NOW_DISABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925180437.png"));

    public final static ImageIcon ICON7 = new ImageIcon(
            AppMainWindow.class.getResource("20170925180555.png"));
    public final static ImageIcon ICON8 = new ImageIcon(
            AppMainWindow.class.getResource("20170925180529.png"));
    public final static ImageIcon ICON9 = new ImageIcon(
            AppMainWindow.class.getResource("20170925180619.png"));
    public final static ImageIcon ICON10 = new ImageIcon(
            AppMainWindow.class.getResource("20170925180655.png"));

    public final static int MAIN_H_GAP = 25;

    public static MyIconButton ShowGraphButton;
    public static MyIconButton QueryBridgeWordsButton;
    public static MyIconButton GenerateNewTextButton;
    public static MyIconButton CalcShortestPathButton;
    public static MyIconButton RandomWalkButton;

    private Image Img;

    public FunctionChoosePanel()
    {
        super(true);
        initialize();
        addComponent();
        addListener();
    }

    private void initialize()
    {
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(5,1));
    }

    private void addComponent()
    {
        this.add(getfirstPanel());

        this.add(getthirdPanel());

        this.add(getfifthPanel());

        this.add(getseventh());

        this.add(getninth());
    }

    private JPanel getfirstPanel()
    {
        JPanel onePanel = new JPanel();
        onePanel.setBackground(Color.WHITE);
        onePanel.setLayout(new BorderLayout(5,5));
        ShowGraphButton = new MyIconButton(ICON_STOP, ICON_STOP_ENABLE,
                ICON_STOP_DISABLE, "");
        onePanel.add(ShowGraphButton,BorderLayout.CENTER);
        return onePanel;
    }


    private JPanel getthirdPanel()
    {
        JPanel onePanel = new JPanel();
        onePanel.setBackground(Color.WHITE);
        onePanel.setLayout(new BorderLayout(5,5));
        QueryBridgeWordsButton = new MyIconButton(ICON_STOP_DISABLE, ICON_SYNC_NOW,
                ICON_STOP_DISABLE, "");
        onePanel.add(QueryBridgeWordsButton,BorderLayout.CENTER);
        return onePanel;
    }


    private JPanel getfifthPanel()
    {
        JPanel onePanel = new JPanel();
        onePanel.setBackground(Color.WHITE);
        onePanel.setLayout(new BorderLayout(5,5));
        GenerateNewTextButton = new MyIconButton(ICON_SYNC_NOW_ENABLE, ICON_SYNC_NOW_DISABLE,
                ICON_STOP_DISABLE, "");
        onePanel.add(GenerateNewTextButton,BorderLayout.CENTER);
        return onePanel;
    }


    private JPanel getseventh()
    {
        JPanel onePanel = new JPanel();
        onePanel.setBackground(Color.WHITE);
        onePanel.setLayout(new BorderLayout(5,5));
        CalcShortestPathButton = new MyIconButton(ICON7, ICON8,
                ICON_STOP_DISABLE, "");
        onePanel.add(CalcShortestPathButton,BorderLayout.CENTER);
        return onePanel;
    }


    private JPanel getninth()
    {
        JPanel onePanel = new JPanel();
        onePanel.setBackground(Color.WHITE);
        onePanel.setLayout(new BorderLayout(5,5));
        RandomWalkButton = new MyIconButton(ICON9, ICON10,
                ICON_STOP_DISABLE, "");
        onePanel.add(RandomWalkButton,BorderLayout.CENTER);
        return onePanel;
    }

    private void addListener()
    {
        ShowGraphButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        System.out.println(AppMainWindow.ImagePath);
                        ShowGraphPanel a = new ShowGraphPanel();
                    }
                });
            }
        });

        QueryBridgeWordsButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                AppMainWindow.mainPanel.removeAll();
                AppMainWindow.mainPanel.add(AppMainWindow.queryBridgewordsPanel,BorderLayout.CENTER);
                AppMainWindow.mainPanel.updateUI();
            }
        });

        GenerateNewTextButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                AppMainWindow.mainPanel.removeAll();
                AppMainWindow.mainPanel.add(AppMainWindow.generateNewtextPanel,BorderLayout.CENTER);
                AppMainWindow.mainPanel.updateUI();
            }
        });

        CalcShortestPathButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                AppMainWindow.mainPanel.removeAll();
                AppMainWindow.mainPanel.add(AppMainWindow.calShortestPathpanel,BorderLayout.CENTER);
                AppMainWindow.mainPanel.updateUI();
            }
        });

        RandomWalkButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                AppMainWindow.randomText = AppMainWindow.graph.randomWalk();
                //System.out.println(AppMainWindow.randomText);
                AppMainWindow.mainPanel.removeAll();
                AppMainWindow.randomWalkpanel.addContent();
                AppMainWindow.mainPanel.add(AppMainWindow.randomWalkpanel,BorderLayout.CENTER);
                AppMainWindow.mainPanel.updateUI();
            }
        });
    }
}
