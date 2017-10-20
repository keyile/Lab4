package gui.functionPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dot.DotCompiler;
import gui.AppMainWindow;
import gui.MyIconButton;

/**
 * Created by Hunter on 2017/9/22.
 */
public class CalcShortestPathPanel extends JPanel {

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

    public static JPanel DownPanel = new JPanel();

    public static MyIconButton ok;
    public static MyIconButton clear;
    public static JTextField FirstWord;
    public static JTextField SecondWord;
    public static JTextField QueryWord;

    public CalcShortestPathPanel()
    {
        super(true);
        initialize();
        addComponent();
        addListener();
    }

    private void initialize()
    {
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout(5,50));
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
        JLabel TitleLable = new JLabel("Calculate Shortest Path");
        TitleLable.setFont(new Font("",1,24));
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
        gridPanel1.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        gridPanel2.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
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
        ok = new MyIconButton(ICON_STOP, ICON_STOP_ENABLE,
                ICON_STOP, "");
        clear = new MyIconButton(ICON_SYNC_NOW, ICON_SYNC_NOW_ENABLE,
                ICON_SYNC_NOW, "");
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
        DownPanel.setLayout(new GridLayout(1,1));

        return DownPanel;
    }

    private void addListener()
    {
        ok.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                String fw = null;
                String sw = null;
                fw = FirstWord.getText().toString().trim();
                sw = SecondWord.getText().toString().trim();
                System.out.println(fw);
                System.out.println(sw);
                AppMainWindow.FirstShortestPathfilePath = null;
                if (!AppMainWindow.graph.exist(fw))
                {
                    AppMainWindow.ReInputFlag = 3;
                    FirstWord.setText("");
                    SecondWord.setText("");
                    AppMainWindow.window.ErrorSize();
                    AppMainWindow.mainPanel.removeAll();
                    AppMainWindow.mainPanel.add(AppMainWindow.inputErrorpanel,BorderLayout.CENTER);
                    AppMainWindow.mainPanel.updateUI();
                } else if(AppMainWindow.graph.exist(fw) && !AppMainWindow.graph.exist(sw))
                {
                    LinkedList<LinkedList<String>> dotSource = AppMainWindow.graph.toDot(fw);
                    DotCompiler.clearCache();
                    int i = 0;
                    int j = 0;

                    for (LinkedList<String> toCertainWordSource : dotSource) {
                        j = 0;
                        for (String source : toCertainWordSource) {
                            String filename = DotCompiler.saveTempFile("tempfile " + i
                                    + "-" + (j++), source);
                            if (AppMainWindow.FirstShortestPathfilePath == null)
                                AppMainWindow.FirstShortestPathfilePath = filename;
                        }
                        ++i;
                    }
                    new ShowShortestPathPanel();
//第一个地址为空 就不现实  不为空就 下一张这么来
                } else if (AppMainWindow.graph.exist(fw) && AppMainWindow.graph.exist(sw))
                {
                    int j = 0;
                    DotCompiler.clearCache();
                    LinkedList<String> dotSource = AppMainWindow.graph.toDot(fw, sw);
                    //String filePath = null;
                    for (String path : dotSource) {
                        String filename = DotCompiler.saveTempFile("tempfile " + (j++), path);
                        if (AppMainWindow.FirstShortestPathfilePath == null)
                            AppMainWindow.FirstShortestPathfilePath = filename;
                    }
                    new ShowShortestPathPanel();
                    // 同上
                }
                DownPanel.setVisible(true);
            }
        });

        clear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                FirstWord.setText("");
                SecondWord.setText("");
                AppMainWindow.mainPanel.removeAll();
                AppMainWindow.mainPanel.add(AppMainWindow.functionChoosepanel,BorderLayout.CENTER);
                AppMainWindow.mainPanel.updateUI();
            }
        });
    }
}
