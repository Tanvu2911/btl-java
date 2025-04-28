package stop;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import carracinggame.cargame;
import login.login;
import login.selectmap;
import test.test;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class lbstop extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static JButton btntieptuc;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public lbstop() {
		
		
//		this.setSize(200,200);
		this.setBounds(140,150,124,285);
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setOpaque(false);
		this.setLayout(null);
		
		
		ImageIcon resumeIcon = new ImageIcon(getClass().getResource("/jpn/resume.png"));
        Image resumeImg = resumeIcon.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
		btntieptuc = new JButton(new ImageIcon(resumeImg));
		btntieptuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargame.checkPause = false;
				cargame.backgroundclip.start();
				
			}
		});
		btntieptuc.setBounds(10, 11, 100, 80);
		btntieptuc.setContentAreaFilled(false);
		btntieptuc.setBorderPainted(false);
		btntieptuc.setFocusPainted(false);
		btntieptuc.setOpaque(false);
		this.add(btntieptuc);
		
		
        Image exitImg= new ImageIcon(getClass().getResource("/jpn/exit.png")).getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
		JButton btnthoat = new JButton(new ImageIcon(exitImg));
		btnthoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnthoat) {
					cargame.checkPause = true;
			         cargame.timer.stop();
			         cargame.backgroundclip.close();
			         test.frame.getContentPane().removeAll();
			         test.frame.setContentPane(new login()); 
			         test.frame.revalidate();  
			         test.frame.repaint();  

				}
			}
		});
		btnthoat.setBounds(10, 170, 100,80);
		btnthoat.setContentAreaFilled(false);
		btnthoat.setBorderPainted(false);
		btnthoat.setFocusPainted(false);
		btnthoat.setOpaque(false);
		this.add(btnthoat);
		
		
		ImageIcon restartIcon = new ImageIcon(getClass().getResource("/jpn/restart.png"));
        Image restartImg = restartIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		JButton btnreset = new JButton(new ImageIcon(restartImg));
		btnreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnreset) {
					cargame.checkPause = false;
					cargame.timer.stop(); 
			         cargame.backgroundclip.stop(); 
			         cargame.backgroundclip.close();
			         test.frame.getContentPane().removeAll();
			         test.frame.setContentPane(new cargame());
			         test.frame.revalidate();  
			         test.frame.repaint(); 

				}
			}
		});
		btnreset.setBounds(20, 91, 80, 80);
		btnreset.setContentAreaFilled(false);
		btnreset.setBorderPainted(false);
		btnreset.setFocusPainted(false);
		btnreset.setOpaque(false);
		this.add(btnreset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public static void main(String[] args) {
		lbstop stop = new lbstop();
	}

}
