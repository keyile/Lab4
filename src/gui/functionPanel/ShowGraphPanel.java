package gui.functionPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import gui.AppMainWindow;
/**
 * Created by Hunter on 2017/9/22.
 */

public class ShowGraphPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 455758487872282992L;

	public JFrame graphWindow = new JFrame();

    public static final int MAIN_WINDOW_X = 240;
    public static final int MAIN_WINDOW_Y = 50;
    public static final int MAIN_WINDOW_WIDTH = 1200;
    public static final int MAIN_WINDOW_HEIGHT = 960;

    private JLabel GraphLabel;

    public ShowGraphPanel()
    {
        super(true);
        this.setLayout(new BorderLayout());
        initialize();
        addComponent();
    }

    private void initialize()
    {

        graphWindow.setBounds(MAIN_WINDOW_X,MAIN_WINDOW_Y,MAIN_WINDOW_WIDTH,
                MAIN_WINDOW_HEIGHT);
        graphWindow.setBackground(Color.WHITE);
        graphWindow.setLayout(new GridLayout(1,1));
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //AppMainWindow window = new AppMainWindow();
                    graphWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    graphWindow.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //File f = new File(AppMainWindow.ImagePath);
    }

    private void addComponent()
    {
        graphWindow.add(getGraphpanel(),BorderLayout.CENTER);
    }

    private JLabel getGraphpanel()
    {

        GraphLabel = new JLabel();
        GraphLabel.setIcon(null);
        GraphLabel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
        GraphLabel.setSize(600, 450);

        //展示第一张图
        ImageIcon image = new ImageIcon((AppMainWindow.ImagePath));

        Image scaledImage = image.getImage().getScaledInstance(1100,860,Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        GraphLabel.setIcon(scaledIcon);
        GraphLabel.setHorizontalAlignment(0);
        GraphLabel.setVerticalAlignment(0);

        return GraphLabel;
    }
}
