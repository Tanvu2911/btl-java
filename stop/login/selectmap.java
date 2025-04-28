package login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import carracinggame.cargame;
import test.test;

public class selectmap extends JPanel implements ActionListener {
    private JButton btnmap1, btnthoat, btnchoi;
    private int boardwidth = 380, boardheight = 600;
    
    public Image backgroundImglogin = new ImageIcon(getClass().getResource("/jpn/backgroundlogin.png")).getImage().getScaledInstance(380, 600, Image.SCALE_SMOOTH);
    
    private Image map1 = new ImageIcon(getClass().getResource("/jpn/map1.png")).getImage().getScaledInstance(380, 600, Image.SCALE_SMOOTH);
    private Image map2 = new ImageIcon(getClass().getResource("/jpn/map2.png")).getImage().getScaledInstance(380, 600, Image.SCALE_SMOOTH);
    private Image map3 = new ImageIcon(getClass().getResource("/jpn/map3.png")).getImage().getScaledInstance(380, 600, Image.SCALE_SMOOTH);
    private Image map4 = new ImageIcon(getClass().getResource("/jpn/map4.png")).getImage().getScaledInstance(380, 600, Image.SCALE_SMOOTH);
    private Image map5 = new ImageIcon(getClass().getResource("/jpn/map5.png")).getImage().getScaledInstance(380, 600, Image.SCALE_SMOOTH);

    private Image selectedMap = null;
	private JLabel map;
	private JButton btnde;
	public boolean mode= false;
	public boolean blmap = false;
	private int d1=1;
	private JButton btntraimap;
	private JButton btnphaimap;
	private JButton btnphai;
	private JButton btntrai;
	private int d=0;

    public selectmap() {
        setLayout(null); 
        setPreferredSize(new Dimension(boardwidth, boardheight));
        map = new JLabel();
        map.setBounds(0,0, boardwidth, boardheight);
        map.setIcon(new ImageIcon(backgroundImglogin));
        Image traiImg= new ImageIcon(getClass().getResource("/jpn/trai.png")).getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);    
        Image phaiImg= new ImageIcon(getClass().getResource("/jpn/phai.png")).getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);    
        
        
        btnchoi = new JButton("Chơi");
        add(btnchoi);
        btnchoi.setBackground(new Color(29, 99, 175));
        btnchoi.setForeground(Color.WHITE);
        btnchoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnchoi.setBounds(230, 461, 120, 40);
        btnchoi.addActionListener(this);             
                
        btnthoat = new JButton("Thoát");
        add(btnthoat);
        btnthoat.setBackground(new Color(29, 99, 175));
        btnthoat.setForeground(Color.WHITE);
        btnthoat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnthoat.setBounds(230, 512, 120, 40);
        btnthoat.addActionListener(this);            

        btnde = new JButton("Dễ");
        btnde.setBackground(new Color(29, 99, 175));
        btnde.setForeground(Color.WHITE);
        add(btnde);
        btnde.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnde.setBounds(69, 512,80, 40);
        btnde.addActionListener(this);
        
        btnphai = new JButton("",new ImageIcon(phaiImg));
        add(btnphai);
        btnphai.setBounds(153, 512, 50, 40);
        btnphai.setContentAreaFilled(false);
        btnphai.setBorderPainted(false);
        btnphai.addActionListener(this);
        
        btntrai = new JButton("", new ImageIcon(traiImg));
        add(btntrai);
        btntrai.setBounds(19, 512, 50, 40);
        btntrai.setBorderPainted(false);
        btntrai.setContentAreaFilled(false);
        btntrai.addActionListener(this);
        

        btnmap1 = new JButton("Map");
        btnmap1.setBackground(new Color(29, 99, 175));
        btnmap1.setForeground(Color.WHITE);
        add(btnmap1);
        btnmap1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnmap1.setBounds(69, 461, 80, 40);
        
        btnmap1.addActionListener(this);
        
        btnphaimap = new JButton("",new ImageIcon(phaiImg));
        add(btnphaimap);
        btnphaimap.setBounds(153, 461, 50, 40);
        btnphaimap.setContentAreaFilled(false);
        btnphaimap.setBorderPainted(false);
        btnphaimap.addActionListener(this);
        
         btntraimap = new JButton("<", new ImageIcon(traiImg));
         add(btntraimap);
         btntraimap.setBounds(19, 463, 50, 40);
         btntraimap.setBorderPainted(false);
         btntraimap.setContentAreaFilled(false);
         btntraimap.addActionListener(this);
         add(map);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnphai) {
            d1++;
            if (d1 > 3) {
                d1 = 1;
            }
        }
        if (e.getSource() == btntrai) {
            d1--;
            if (d1 <= 0) {
                d1 = 3;
            }
        }

        if (d1 == 1) {
            btnde.setText("Dễ");
            mode = true;
            cargame.win = 20;
            cargame.defaultEnemySpeed = 5;
            cargame.enemySpeed = cargame.defaultEnemySpeed;
        } else if (d1 == 2) {
            btnde.setText("Trung");
            mode = true;
            cargame.win = 30;
            cargame.defaultEnemySpeed = 5.8;
            cargame.enemySpeed = cargame.defaultEnemySpeed;
        } else if (d1 == 3) {
            btnde.setText("Khó");
            mode = true;
            cargame.win = 38;
            cargame.defaultEnemySpeed = 6.8;
            cargame.enemySpeed = cargame.defaultEnemySpeed;
        }
        
        if (e.getSource() == btnphaimap || e.getSource() == btntraimap) {
        	blmap=true;
        	if (e.getSource() == btnphaimap) {
                d++;
                if (d > 5) {
                    d = 1;
                }
            }
            if (e.getSource() == btntraimap) {
                d--;
                if (d <= 0) {
                    d = 5;
                }
            }
            if (d == 1) {
                cargame.backgroundImg = map1;
                map.setIcon(new ImageIcon(map1));
                btnmap1.setText("Map 1");
            } else if (d == 2) {
                cargame.backgroundImg = map2;
                map.setIcon(new ImageIcon(map2));
                btnmap1.setText("Map 2");
            } else if (d == 3) {
                cargame.backgroundImg = map3;
                map.setIcon(new ImageIcon(map3));
                btnmap1.setText("Map 3");
            }else if (d == 4) {
                cargame.backgroundImg = map4;
                map.setIcon(new ImageIcon(map4));
                btnmap1.setText("Map 4");
            }
            else if (d == 5) {
                cargame.backgroundImg = map5;
                map.setIcon(new ImageIcon(map5));
                btnmap1.setText("Map 5");
            }
        }

        repaint(); 

        if (e.getSource() == btnthoat) {
            test.frame.getContentPane().removeAll();
            test.frame.setContentPane(new login());
            test.frame.pack();
            test.frame.setLocationRelativeTo(null);
            test.frame.setVisible(true);
        } else if (e.getSource() == btnchoi) {
        	if(blmap==true && mode == true) {        		
        		test.frame.getContentPane().removeAll();
        		test.frame.setContentPane(new cargame());
        		test.frame.pack();
        		test.frame.setLocationRelativeTo(null);
        		test.frame.setVisible(true);
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "Vui lòng chọn bản đồ trước khi bắt đầu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        	}
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImglogin, 0, 0, boardwidth, boardheight, null);
    }
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(380, 600);
	    Image logo = new ImageIcon(test.class.getResource("/jpn/logo.png")).getImage();
	    frame.setIconImage(logo);
		frame.setContentPane(new selectmap());
		frame.pack();
	    frame.setLocationRelativeTo(null); 
	    frame.setVisible(true);
	}
}
