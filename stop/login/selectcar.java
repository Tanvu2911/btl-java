package login;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import carracinggame.cargame;
import test.test;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class selectcar extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton left;
	private JButton right;
    private int boardwidth = 380, boardheight = 600;
	public List<Image> imgcar = new ArrayList<>();
	public Image backgroundImglogin = new ImageIcon(getClass().getResource("/jpn/city.png")).getImage().getScaledInstance(380, 600, Image.SCALE_SMOOTH);
	
	public static int s = 1, n = 6;
	private JLabel lb;
	private JButton select;
	private JButton btnthoat;
	private JLabel map;


	public selectcar() {
		setLayout(null); 
	    setPreferredSize(new Dimension(boardwidth, boardheight));	
        Image traiImg= new ImageIcon(getClass().getResource("/jpn/trai.png")).getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);    
        Image phaiImg= new ImageIcon(getClass().getResource("/jpn/phai.png")).getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);    
        Image chonImg= new ImageIcon(getClass().getResource("/jpn/chon.png")).getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);    
	    map = new JLabel(); map.setBounds(0,0, boardwidth, boardheight);map.setIcon(new ImageIcon(backgroundImglogin));
		left = new JButton("", new ImageIcon(traiImg));
		left.setBounds(24, 233, 100, 50);
		left.addActionListener(this); 
		left.setBorderPainted(false);
		left.setContentAreaFilled(false);
		add(left);
		right = new JButton("", new ImageIcon(phaiImg));
		right.setBounds(259, 233, 100, 50);
		right.addActionListener(this);
		right.setBorderPainted(false);
		right.setContentAreaFilled(false);
		add(right);
		lb = new JLabel("");
		lb.setBounds(147, 193, 89, 143);
		add(lb);
		select = new JButton("", new ImageIcon(chonImg));
		select.setBounds(134, 358, 112, 50);
		select.addActionListener(this); 
		select.setBorderPainted(false);
		select.setContentAreaFilled(false);
		add(select);
		btnthoat = new JButton("<", new ImageIcon(traiImg));
		btnthoat.setBounds(10, 11, 89, 50);
		btnthoat.addActionListener(this); 
		btnthoat.setBorderPainted(false);
		btnthoat.setContentAreaFilled(false);
		add(btnthoat);
		for (int i = 1; i <= n; i++) imgcar.add(new ImageIcon(getClass().getResource("/jpn/xe" + i + ".png")).getImage());
		lb.setIcon(new ImageIcon(imgcar.get(s)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == left) {
			s--;
			if (s < 0) s = n ;
			lb.setIcon(new ImageIcon(imgcar.get(s)));
		}
		if (e.getSource() == right) {
			s++;
			if (s >= n) s = 1;
			lb.setIcon(new ImageIcon(imgcar.get(s)));
		}
		if(e.getSource() == select) {
			cargame.imgplayer = imgcar.get(s);
			test.frame.getContentPane().removeAll();
            test.frame.setContentPane(new login());
            test.frame.pack();
            test.frame.setLocationRelativeTo(null);
            test.frame.setVisible(true);
		}
		if(e.getSource() == btnthoat) {
			test.frame.getContentPane().removeAll();
            test.frame.setContentPane(new login());
            test.frame.pack();
            test.frame.setLocationRelativeTo(null);
            test.frame.setVisible(true);
		}
	}
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImglogin, 0, 0, 380, 600, null);
    }
}
