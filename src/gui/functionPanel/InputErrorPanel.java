package gui.functionPanel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import gui.AppMainWindow;
import gui.MyIconButton;

import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Hunter on 2017/9/24.
 */
public class InputErrorPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7985442746398666876L;
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


    private Image img;

    public static MyIconButton quit;
    public static MyIconButton reInput;

    public  InputErrorPanel()
    {
        super(true);
        initialize();
        addComponent();
        addListener();
    }

    private void initialize()
    {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout(5,5));
    }

    private  void addComponent()
    {
        this.add(getUpPanel(),BorderLayout.CENTER);
        this.add(getCenterPanel(),BorderLayout.SOUTH);
    }

    private JPanel getUpPanel()
    {
        JPanel UpPanel = new JPanel()
        {
            /**
			 * 
			 */
			private static final long serialVersionUID = -5005953500910041064L;

			public  void paint(Graphics g)
            {
                try
                {
                    img = ImageIO.read(new File(AppMainWindow.class.getResource("20170925191435.png").toURI()));
                    g.drawImage(img,0,0,350,173,null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        return UpPanel;
    }

    private JPanel getCenterPanel()
    {
        JPanel CenterPanel = new JPanel();
        CenterPanel.setBackground(Color.WHITE);
        CenterPanel.setLayout(new GridLayout(1,2));

        JPanel gridPanel1 = new JPanel();
        gridPanel1.setBackground(Color.WHITE);
        gridPanel1.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        JPanel gridPanel2 = new JPanel();
        gridPanel2.setBackground(Color.WHITE);
        gridPanel2.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        quit = new MyIconButton(Quit_Now, Quit_Now_ENABLE,
                Quit_Now, "");
        reInput = new MyIconButton(ReInput_NOW, ReInput_NOW_ENABLE,
                ReInput_NOW, "");

        gridPanel1.add(reInput);
        gridPanel2.add(quit);

        CenterPanel.add(gridPanel1);
        CenterPanel.add(gridPanel2);

        return CenterPanel;
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

        reInput.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                AppMainWindow.window.DefaultSize();
                AppMainWindow.mainPanel.removeAll();
                switch (AppMainWindow.ReInputFlag)
                {
                    case 1:
                        AppMainWindow.mainPanel.add(AppMainWindow.inputPathpanel,BorderLayout.CENTER);
                        break;
                    case 2:
                        AppMainWindow.mainPanel.add(AppMainWindow.queryBridgewordsPanel,BorderLayout.CENTER);
                        break;
                    case 3:
                        AppMainWindow.mainPanel.add(AppMainWindow.calShortestPathpanel,BorderLayout.CENTER);
                        break;
                    case 4:
                        AppMainWindow.mainPanel.add(AppMainWindow.queryBridgewordsPanel,BorderLayout.CENTER);
                        break;
                    default: break;
                }
                AppMainWindow.mainPanel.updateUI();
            }
        });

    }
}
