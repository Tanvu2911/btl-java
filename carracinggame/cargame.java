package carracinggame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import stop.lbstop;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import test.test;



public class cargame extends JPanel implements ActionListener,KeyListener {
    public static Timer timer;
    public Car playerCar;
    public static int heightcar = 68;
    public static int widthcar = 45;
    public int speechplayer = 5;
    private ArrayList<Car> enemyCars;
    private ArrayList<Money> Moneys;
    private int score =0;
    public static int win=20;
    public boolean gameOver = false;
    public boolean moveLeft;
    public boolean moveRight;
    public boolean moveup;
    public boolean movedown;
    int boardwidth = 380;
    int boardheight = 600;
    public static Image backgroundImg = null;
    public static Image imgplayer= new ImageIcon(cargame.class.getResource("/jpn/xe1.png")).getImage();
    Image carImg;
    Image obstaclesImg;
    private int gameTime = 0;  
    private int spawnDelay = 100;
    private int lastSpawnTime = 0;
    //thay đổi tốc độ
	public static double defaultEnemySpeed=5;
	public static double enemySpeed;
    private int speedIncreaseInterval = 2000; 
    private long lastSpeedIncreaseTime = System.currentTimeMillis();
    //tần suất spam
    private int spawnChance = 3; 
    private int maxSpawnChance = 20;  
    private int spawnIncreaseInterval = 3800;   
    private long lastSpawnIncreaseTime = System.currentTimeMillis();
    
    
    private int backgroundY = 0;
    private double backgroundSpeed = enemySpeed* 0.8;
    
    public List<Image> khois = new ArrayList<>();
    public int countk=0;
	private boolean showkhoi= true;
	private JButton pause;
    
    public static lbstop stop = new lbstop();
    public static boolean checkPause = false;
    
	public static Clip clip;
	public static Clip backgroundclip;
	
	public static Timer firetimer;
	public static Timer khoitimer;
	public static Timer gameover;



    public cargame() {
        setPreferredSize(new Dimension(boardwidth, boardheight));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        playerCar = new Car(170, 500, 45,heightcar, imgplayer);
        enemyCars = new ArrayList<>();
        Moneys = new ArrayList<>();
        
        
        Image menuImg= new ImageIcon(getClass().getResource("/jpn/menu.png")).getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);    
        pause = new JButton(new ImageIcon(menuImg));
        pause.addActionListener(this);
        pause.setBorderPainted(false);
        pause.setContentAreaFilled(false);
        pause.setBounds(280, 12, 50, 40);
        add(pause);
        
        
        
        if (timer != null) timer.stop();
        timer = new Timer(10, this);
        timer.start();

        
        
        if (checkPause == false) {
            playSound("/jpn/nhacnen.wav");
        }
        
        for (int i = 1; i <= 5; i++) {
            khois.add(new ImageIcon(getClass().getResource("/jpn/khoi"+i+ ".png")).getImage());
        }
        
        if (khoitimer != null) {
            khoitimer.stop();
            for (ActionListener al : khoitimer.getActionListeners()) {
                khoitimer.removeActionListener(al);
            }
        }
        khoitimer = new Timer(80, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countk++;
                if(countk > 5) {
                	countk =0;
                }
            }
        });
        khoitimer.start();
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	requestFocusInWindow();
        if (!gameOver && checkPause== false) {
            if (moveLeft && playerCar.x > 90) {
                playerCar.movex(-speechplayer);
            }
            if (moveRight && playerCar.x < 250) {
                playerCar.movex(speechplayer);
            }
            if(moveup && playerCar.y > 0) {
            	playerCar.movey(-speechplayer);
            }
            if(movedown && playerCar.y <boardheight - playerCar.height) {
            	playerCar.movey(speechplayer);
            }
            spawnEnemyCars();
            moveEnemyCars();
            checkCollisions();
            if (System.currentTimeMillis() - lastSpeedIncreaseTime > speedIncreaseInterval) {
                enemySpeed += 0.2;
                backgroundSpeed = enemySpeed*0.8;
                lastSpeedIncreaseTime = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - lastSpawnIncreaseTime > spawnIncreaseInterval) {
                if (spawnChance < maxSpawnChance) {
                    spawnChance += 1; 
                }
                lastSpawnIncreaseTime = System.currentTimeMillis();
            }
            if (e.getSource() == pause) {
            	checkPause = true;
            }
            if(score == win) {
            	checkPause = true;
            }
            if(checkPause == true) {
            	add(stop);
            	if(gameOver == true) {
            		stop.remove(lbstop.btntieptuc);
            	}
            	else {
            		stop.add(lbstop.btntieptuc);
            	}
            	backgroundclip.stop();
            }else if(checkPause == false) {
            	remove(stop);
            	if(backgroundclip!= null && backgroundclip.isRunning() == false) {
            		backgroundclip.start();
            	}
            }
            
            spawmMoneys();
            moveMoneys();
            repaint();

        }
        
        
        
    }

  
    public void spawmMoneys() {
    	int a[] = {95,165,235};
    	if (new Random().nextInt(100) < 1) { 
            int xPos =  a[new Random().nextInt(3)];
            int spacemoney = 30;
            int yPos=0;
            boolean canSpawn = true;
            for (Money coin : Moneys) {
				if (Math.abs(coin.y - 0) < spacemoney) { 
                    canSpawn = false;
                    break;
                }
            }
            if (canSpawn) {
            	for(int i=0;i<3;i++) {
                	Moneys.add(new Money(xPos+10, yPos,35,35));
                	yPos -=40;
                }
            }
    	}
    }
    public void moveMoneys() {
        for (int i = 0; i < Moneys.size(); i++) {
            Money coin = Moneys.get(i);
            
            if(checkPause == false) {
            	
            	coin.y += enemySpeed;
            	if (coin.y > 600) {
            		Moneys.remove(i);
            		i--;
            	}
            }
        }
    }
 
    
    
    private int img[]= {1,2,3,4,5,6,7,8};
    private int minEnemyDistance = 230;
    private void spawnEnemyCars() {
    	int a[] = {100,170,240};
    	if (new Random().nextInt(100) < spawnChance) { 
            int xPos =  a[new Random().nextInt(3)];
            boolean canSpawn = true;

            
            for (Car enemy : enemyCars) {
				if (Math.abs(enemy.y - 0) < minEnemyDistance) { 
                    canSpawn = false;
                    break;
                }
            }

            if (canSpawn) {
            	enemyCars.add(new Car(xPos, 0, widthcar, heightcar, 
            		    new ImageIcon(getClass().getResource("/jpn/xe" +img[new Random().nextInt(8)] +".png")).getImage()));
            	

            }
        }
        
    }
    public void moveEnemyCars() {
        for (int i = 0; i < enemyCars.size(); i++) {
            Car car = enemyCars.get(i);
            if (!checkPause) {
            	car.y += enemySpeed;
            	if (car.y > 600) {
            		enemyCars.remove(i);
            		i--;
            	}
            }
        }
    }
    
    public static List<Image> fires= new ArrayList<>();
	private boolean showfires  = false;
	private int count;

    public void checkCollisions() {
        for (Car enemyCar : enemyCars) {
            if (playerCar.getBounds().intersects(enemyCar.getBounds())) {
                gameOver = true;
                                
                backgroundSpeed = 0; 
                playSound("/jpn/gameover.wav");
                
                checkPause = true;
                double y = playerCar.y;
                if (gameover != null) {
                    gameover.stop();
                    for (ActionListener al : gameover.getActionListeners()) {
                        gameover.removeActionListener(al);
                    }
                }
                gameover = new Timer(16, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (playerCar.y < y+50) {
                			playerCar.y += 2; 
                		} else {
                			((Timer) e.getSource()).stop();
                		}
                		repaint();
					}
                });
                gameover.start();
                showfires = true;
                showkhoi = false;
                count =0;
                if (firetimer != null) {
                    firetimer.stop();
                    for (ActionListener al : firetimer.getActionListeners()) {
                        firetimer.removeActionListener(al);
                    }
                }
                
                fires.clear();
                for(int i=1;i<=7 ;i++){
                    fires.add(new ImageIcon(getClass().getResource("/jpn/fire"+i+".png")).getImage());                	
                }
                firetimer = new Timer(100, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                    	if(showfires == true) {
                    		count++;
                    		if(count >6) {
                    			count =  0;
                    		}
                    	}
                        repaint();
                    }
                });
                firetimer.start();
            }
        }
        for (int i = 0; i < Moneys.size(); i++) {
            if (playerCar.getBounds().intersects(Moneys.get(i).getBounds())) {
                score += 1;
                playSound("/jpn/nhatxu.wav");
                Moneys.remove(i);
                i--;
            }
        }
        if(score == win) {
        	gameOver = true;
        	timer.stop();
        	clip.stop();
        }
    }


    public void  restartGame() {
        gameOver = false;
        showkhoi = true;
        showfires = false;
        count= 0;
        countk=0;
        
        checkPause = false;
        
        if (firetimer != null) {
            firetimer.stop();
            firetimer = null;
        }
        if (gameover != null) {
            gameover.stop();
            gameover = null;
        }
        fires.clear();

        
        if (khoitimer == null) {
            khoitimer = new Timer(80, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    countk++;
                    if(countk > 5) {
                        countk = 0;
                    }
                }
            });
            khoitimer.start();
        } else {
            khoitimer.restart(); 
        }
        
        cargame.enemySpeed = cargame.defaultEnemySpeed;
        backgroundSpeed = enemySpeed*0.8;
        playerCar.x = 175; 
        playerCar.y = 500;
        enemyCars.clear();
        Moneys.clear();
        score = 0;
        
        moveRight = false;
        moveLeft = false;
        moveup = false;
        movedown = false;
        
        if (backgroundclip != null) {
            backgroundclip.stop();
            backgroundclip.setFramePosition(0);
            backgroundclip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        if (timer != null) {
            timer.stop();
            for (ActionListener al : timer.getActionListeners()) {
                timer.removeActionListener(al);
            }
        }
        timer.stop();
        timer = new Timer(10, this);
        timer.start();


    }

	Image  coinimg= new ImageIcon(getClass().getResource("/jpn/money.png")).getImage();
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
        g.drawImage(backgroundImg, 0, backgroundY, boardwidth, boardheight, null);
        g.drawImage(backgroundImg, 0, backgroundY - boardheight, boardwidth, boardheight, null);

        backgroundY += backgroundSpeed;
        if (backgroundY >= boardheight) {
            backgroundY = 0;
        }
    	
    	
    	for (Money coin : Moneys) {
    	    g.drawImage(coin.image,coin.x,coin.y,coin.width, coin.height ,null); 
    	     
    	}

    	
    	g.setColor(Color.WHITE);
    	g.setFont(new Font("Arial", Font.ITALIC, 20));
    	g.drawString("x " + score, 50, 35);
    	g.drawImage(coinimg,90,12,35,35,null);
    	
    	
    	for ( Car x : enemyCars) {    		
    		g.drawImage(x.image, (int)x.x, (int)x.y,x.width, x.height, null );
    	}
    	if (gameOver && score < win) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.ITALIC,38));
            g.drawString("GAME OVER!", 68, 150);
        }
    	if( score == win) {
    		g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.ITALIC, 38));
            g.drawString("YOU WIN!", 100, 150);
            gameOver= true;
    	}
		if(showkhoi && countk <khois.size() ) {		
			g.drawImage(khois.get(countk), (int)playerCar.x+ widthcar/2 - 10 , (int)playerCar.y+playerCar.height - 5, widthcar, 30, null);
		}
		
    	g.drawImage(playerCar.image, (int)playerCar.x, (int)playerCar.y,playerCar.width,playerCar.height , null );
    	if (showfires && count < fires.size()) {
    		g.drawImage(fires.get(count), (int)playerCar.x ,(int)playerCar.y - (playerCar.height/3) , widthcar , 38, null);
    	}


    }
    public void playSound(String soundFile) {
        try {
        	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundFile));
        	if(soundFile == "/jpn/nhacnen.wav") {
        		backgroundclip = AudioSystem.getClip();
        		backgroundclip.open(audioInputStream);
        		backgroundclip.loop(Clip.LOOP_CONTINUOUSLY);
        	}
        	else {
        		
        		clip = AudioSystem.getClip();
        		clip.open(audioInputStream);
        		clip.start();
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                moveLeft = true;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                moveRight = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                moveup = true;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                movedown = true;
            }
        }
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			restartGame();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                moveLeft = false;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                moveRight = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP) { 
                moveup = false;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) { 
                movedown = false;
            }

        }
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
        JFrame frame = new JFrame("Car Racing Game");
        cargame game = new cargame();

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
