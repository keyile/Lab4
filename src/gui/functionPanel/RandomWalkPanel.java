package gui.functionPanel;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import gui.AppMainWindow;
import gui.MyIconButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hunter on 2017/9/22.
 */
public class RandomWalkPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2653400213526079160L;
	// 停止 默认
    public static final ImageIcon Quit_Now = new ImageIcon(
            AppMainWindow.class.getResource("20170925190614.png"));
    // 停止 激活
    public static final ImageIcon Quit_Now_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925190543.png"));
    // 立即同步 默认
    public static final ImageIcon ReInput_NOW = new ImageIcon(
            AppMainWindow.class.getResource("20170925175935.png"));
    // 立即同步 激活
    public static final ImageIcon ReInput_NOW_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("20170925175903.png"));

    public static final int MAIN_H_GAP = 25;

    public static MyIconButton preStep;
    public static MyIconButton ok;

    public static MyIconButton quit;
    public static MyIconButton prestep;

    public JTextArea randomTextarea;

    public RandomWalkPanel()
    {
        super(true);
        initialize();
        addComponent();
        addListener();
    }

    private void initialize()
    {
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout());
    }

    private void addComponent()
    {
        this.add(getCenterPanel());
        this.add(getDownPanel());
    }

    private JPanel getCenterPanel()
    {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);

        randomTextarea = new JTextArea();
        randomTextarea.setPreferredSize(new Dimension(500,350));
        randomTextarea.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));

        //System.out.println(AppMainWindow.graph == null);
        //randomText = AppMainWindow.graph.randomWalk();
        //

        //
        centerPanel.add(randomTextarea);

        return centerPanel;
    }

    public void addContent()
    {
        System.out.println(AppMainWindow.randomText);
        randomTextarea.setText(AppMainWindow.randomText);
    }

    private JPanel getDownPanel()
    {
        JPanel DownPanel = new JPanel();
        DownPanel.setBackground(Color.WHITE);
        DownPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));

        quit = new MyIconButton(Quit_Now, Quit_Now_ENABLE,
                Quit_Now, "");
        preStep = new MyIconButton(ReInput_NOW, ReInput_NOW_ENABLE,
                ReInput_NOW, "");

        DownPanel.add(preStep);
        DownPanel.add(quit);

        return DownPanel;
    }

    private void addListener()
    {
        quit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                AppMainWindow.window.windowSetVisible(false);
                System.exit(0);
            }
        });

        preStep.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                randomTextarea.setText("");
                AppMainWindow.mainPanel.removeAll();
                AppMainWindow.mainPanel.add(AppMainWindow.functionChoosepanel,BorderLayout.CENTER);
                AppMainWindow.mainPanel.updateUI();
            }
        });
    }
}
