package login;

import java.awt.*;
import javax.swing.*;

import carracinggame.cargame;
import test.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton btnChoi, btnChonXe;
    public Image backgroundImglogin;
    int boardwidth =380;
    int boardheight = 600;
    public login() {
        backgroundImglogin = new ImageIcon(getClass().getResource("/jpn/backgroundlogin.png")).getImage();

        setLayout(null);
        setPreferredSize(new Dimension(380, 600));

        JLabel lblTitle = new JLabel("ĐUA XE ĐƯỜNG PHỐ");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(33, 69, 323, 80);
        add(lblTitle);

        ImageIcon playIcon = new ImageIcon(getClass().getResource("/jpn/Play.png"));
        Image playImg = playIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        btnChoi = new JButton(new ImageIcon(playImg));
        btnChoi.setBounds(250, 320, 120, 120);
        btnChoi.addActionListener(this);
        btnChoi.setBorderPainted(false); 
        btnChoi.setContentAreaFilled(false);
        btnChoi.setFocusPainted(false); 
        btnChoi.setOpaque(false);
        add(btnChoi);

        ImageIcon selectIcon = new ImageIcon(getClass().getResource("/jpn/Select.png"));
        Image selectImg = selectIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        btnChonXe = new JButton(new ImageIcon(selectImg));
        btnChonXe.setBounds(280, 450, 60, 60);
        btnChonXe.addActionListener(this);
        btnChonXe.setBorderPainted(false);
        btnChonXe.setContentAreaFilled(false);
        btnChonXe.setFocusPainted(false);
        btnChonXe.setOpaque(false);
        add(btnChonXe);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		  if (e.getSource() == btnChoi) {
			  	cargame.checkPause = false;
			  	test.frame.getContentPane().removeAll();
	        	test.frame.setContentPane(new selectmap());
	        	test.frame.pack();
	        	test.frame.setLocationRelativeTo(null);
	        	test.frame.setVisible(true);
	        } else if (e.getSource() == btnChonXe) {
	        	test.frame.getContentPane().removeAll();
	        	test.frame.setContentPane(new selectcar());
	        	test.frame.pack();
	        	test.frame.setLocationRelativeTo(null);
	        	test.frame.setVisible(true);
	            
	        }
	}
	public void paintComponent(Graphics g) {
		g.drawImage(backgroundImglogin, 0, 0,boardwidth, boardheight, null );
		
	}
	
	
	
	 public static void main(String[] args) {
	        JFrame frame = new JFrame("Đăng nhập đua xe");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	        frame.setContentPane(new login());
	        frame.pack();
	        frame.setLocationRelativeTo(null); 
	        frame.setVisible(true);
	}
}