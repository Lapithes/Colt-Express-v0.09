//Aryan Singh
//Period 5

import java.util.*;


import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.sound.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.*;
import java.io.*;
import javax.swing.*;
//import javax.swing.Timer;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
public class GUI extends JPanel
{
	JFrame f;
	//JButton btn;
	JFrame p1;
	JTextArea ta;
	JPanel panel2;
	JPanel pa1;
	JPanel pa2;
	JPanel pa3;
	JPanel pa4;
	JTextArea t1;
	JTextArea t2;
	JTextArea t3;
	JTextArea t4;
	HashMap<Integer, JPanel> playerMap;
	final ArrayList<Player> originalOrder;
	String tempstr;
	int[][] xpositions;
	int[] ypositions;
	//create map of panels for players//find out how to overlay jpanels
	boolean clicked;
	Card currentCard;
	public GUI(ArrayList<Player> playerOrder, Train train)
	{
		originalOrder = playerOrder;
		playerMap = new HashMap<Integer, JPanel>();
		
		tempstr = "";
		xpositions  = new int[5][4];
		xpositions[0][0] = 1100;
		for(int x=0; x<4; x++)
		{
			xpositions[x+1][0] = xpositions[x][0]-200;
		}
		for(int x=0; x<3; x++)
		{
			xpositions[0][x+1] = xpositions[0][x]-20;
			xpositions[1][x+1] = xpositions[1][x]-20;
			xpositions[2][x+1] = xpositions[2][x]-20;
			xpositions[3][x+1] = xpositions[3][x]-20;
			xpositions[4][x+1] = xpositions[4][x]-20;
		}
		ypositions = new int[2];
		ypositions[0] = 550;
		ypositions[1] = 450;
		currentCard = null;
		clicked = false;
		f = new JFrame();

		JButton b = new JButton();
		f.add(b);
		b.setBorder(null);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
       
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setSize(1280,720);
        f.setSize(1300, 740);
		f.setVisible(true);
		
		
		String arg = "coltexpressintro2 (2).gif";
        
        JPanel p = new JPanel();
        JLabel l = new JLabel();
        
        ImageIcon icon = new ImageIcon(arg);
        icon.setImage(icon.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT));
        l.setIcon(icon);
        p.add(l);
        f.getContentPane().add(p);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        
        try {
            File file = new File("coltexpressintro2.wav");
            Clip clip = AudioSystem.getClip();
            
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
            Timer t = new Timer();
            t.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            
                            clip.close();
                            p.removeAll();
                            f.getContentPane().remove(p);
                            f.getContentPane().setSize(1500,720);
                            f.setSize(1500, 740);
                            f.setVisible(false);
                            f.setVisible(true);
                            b.setVisible(false);
                            t.cancel();

                        }
                    }, 120000);
           
            
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    clip.close();
                    p.removeAll();
                    f.getContentPane().remove(p);
                    f.getContentPane().setSize(1500,720);
                    f.setSize(1500, 740);
                    f.setVisible(false);
                    f.setVisible(true);
                    b.setVisible(false);
                    t.cancel();
                }
            });
        
        } catch (Exception e) {
            System.err.println("lmao");
        }
        
        
        
        pa1 = new JPanel();
        pa1.setSize(100,100);
        pa1.setLocation(1340,280);
        pa1.setVisible(true);
        
        pa2 = new JPanel();
        pa2.setSize(100,100);
        pa2.setLocation(1340,380);
        pa2.setVisible(true);
        
        pa3 = new JPanel();
        pa3.setSize(100,100);
        pa3.setLocation(1340,480);
        pa3.setVisible(true);
        
        pa4 = new JPanel();
        pa4.setSize(100,100);
        pa4.setLocation(1340,580);
        pa4.setVisible(true);
        
        t1 = new JTextArea();
        t2 = new JTextArea();
        t3 = new JTextArea();
        t4 = new JTextArea();
        
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        
        t1.setText(playerOrder.get(0).getCharacterName()+"------\nBullets: 6\nTotalMoney: 250\nBag: x1\nJewel: x0\nLockbox: x0");
        t2.setText(playerOrder.get(1).getCharacterName()+"------\nBullets: 6\nTotalMoney: 250\nBag: x1\nJewel: x0\nLockbox: x0");
        t3.setText(playerOrder.get(2).getCharacterName()+"------\nBullets: 6\nTotalMoney: 250\nBag: x1\nJewel: x0\nLockbox: x0");
        t4.setText(playerOrder.get(3).getCharacterName()+"------\nBullets: 6\nTotalMoney: 250\nBag: x1\nJewel: x0\nLockbox: x0");
        
        t1.setVisible(true);
        t2.setVisible(true);
        t3.setVisible(true);
        t4.setVisible(true);
        
        pa1.add(t1);
        f.add(pa1);
        
        pa2.add(t2);
        f.add(pa2);
        
        pa3.add(t3);
        f.add(pa3);
        
        pa4.add(t4);
        f.add(pa4);
        
        /*for(int x=0; x<4; x++)
		{
			playerMap.put(x, new JPanel());
		}
		for(int x=0; x<4; x++)
		{
			JLabel l1 = new JLabel();
            ImageIcon icon2 = new ImageIcon(originalOrder.get(x).getCharacterName()+".png");
            icon2.setImage(icon2.getImage().getScaledInstance(30,80, Image.SCALE_DEFAULT));
            l1.setIcon(icon2);
            l1.setVisible(true);
            playerMap.get(x).setOpaque(false);
            playerMap.get(x).add(l1);
            playerMap.get(x).setSize(30,80);
            playerMap.get(x).setVisible(true);
            f.add(playerMap.get(x));
		}
		f.revalidate();
		//train.update();
		updatePosition(train);*/
        
        for(Player player: playerOrder)
        {
        	JPanel p1 = new JPanel();
            JLabel l1 = new JLabel();
            ImageIcon icon2 = new ImageIcon(player.getCharacterName()+".png");
            icon2.setImage(icon2.getImage().getScaledInstance(30,80, Image.SCALE_DEFAULT));
            l1.setIcon(icon2);
            l1.setVisible(true);
            p1.setOpaque(false);
            p1.add(l1);
            int xpos = 0;
            int ypos = 0;
            if(player.getIsTop())
            {
            	xpos = xpositions[player.getTrainCarNum()][train.getCarList().get(player.getTrainCarNum()).getTopPlayers().indexOf(player)];
            	ypos = ypositions[1];
            }
            else
            {
            	xpos = xpositions[player.getTrainCarNum()][train.getCarList().get(player.getTrainCarNum()).getBotPlayers().indexOf(player)];
            	ypos = ypositions[0];
            }
            
            p1.setLocation(xpos,ypos);
            p1.setSize(30,80);
            p1.setVisible(true);
            f.add(p1);
        }
        
		JPanel p0 = new JPanel();
        JLabel l0 = new JLabel();
        ImageIcon icon0 = new ImageIcon("CEBackground.png");
        icon0.setImage(icon0.getImage().getScaledInstance(1250, 520, Image.SCALE_DEFAULT));
        l0.setIcon(icon0);
        l0.setVisible(true);
        p0.add(l0);
        p0.setLocation(20,250);
        p0.setSize(1250,520);
        p0.setVisible(true);
        f.add(p0);
        
        
        p1 = new JFrame();
        
        f.getContentPane().setLayout(null);
        p1.setLocation(1500,0);
        JLabel outout = new JLabel("console");
        //outout.setIgnoreRepaint(true);
        p1.add( outout , BorderLayout.NORTH );
        outout.setLocation(1400, 400);
        

        ta = new JTextArea();
       // ta.setIgnoreRepaint(true);
        //ta.setEditable(false);
        TextAreaOutputStream taos = new TextAreaOutputStream( ta, 60 );
        PrintStream ps = new PrintStream( taos );
        System.setOut( ps );
        System.setErr( ps );

        JScrollPane sp = new JScrollPane(ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, //
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        //sp.setIgnoreRepaint(true);
        //sp.setWheelScrollingEnabled(true);
        p1.add(sp);
        p1.setVisible( true );
        p1.setSize(400,400);
        p1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /////////////////////
        
        /*JTextArea textArea = new JTextArea(24, 80);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        System.setOut(new PrintStream(new OutputStream() {
          @Override
          public void write(int b) throws IOException {
            textArea.append(String.valueOf((char) b));
          }
        }));
        JScrollPane scp = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, //
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        p1.add(scp);
        f.add(p1);*/
        
        /////////////////////
        
        panel2 = new JPanel();
	    f.add(panel2);
        panel2.setLayout(new GridLayout(1,25));
        panel2.setSize(1200,200);
        panel2.setLocation(20, 20);
        panel2.setVisible(true);
        
        
	}
	
	/*public static void main(String[] args) throws IOException
	{
		
        
        
        
	}*/
	public void updateMenu(JComboBox<String> cb, String[] s)
	{
		cb = new JComboBox<String>(s);
	}
	
	public void init(ArrayList<Player> order, Train train, int sheriff)// throws IOException
	{
		System.out.println(">Welcome to Colt Express!                                                                        ");
	    for(int x=0; x<5; x++)
	    {
	    	System.out.println();
	    }
	    System.out.println(">TRAINCARS: 4 <-- 3 <-- 2 <-- 1 <-- 0");
	    for(int x=1; x<5; x++)
	    {
	    	System.out.println(">Player "+x+": "+order.get(x-1).getCharacterName());
	    }
	    System.out.println(">Let's Begin!");
	    
	}

	public void audio() {
		
        try {
            File file = new File("coltexpressintro2.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        
        } catch (Exception e) {
            System.err.println("t");
        }
    }

  
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try{
		File imageFile = new File("IMG_2390P.png");
		BufferedImage image = ImageIO.read(imageFile);
		} catch (IOException e){System.out.println("sup");}
		//g.drawImage(image, 250, 250, null);
	}
	
	
	
	public void ask(Player p, String face) throws InterruptedException
	{
		
		//show selection dropdown menu for player
		//int userinput = mouseclicked button number index
		//next
		System.out.println(">"+p.getCharacterName()+"'s Turn...");
		JPanel panel1 = new JPanel();

	    ArrayList<String> tempchoices = new ArrayList<String>();
	    for(Card c: p.getHand())
	    {
	    	tempchoices.add(c.getSName());
	    }
	    Collections.sort(tempchoices);
	    tempchoices.add("Draw3");
	    String[] choices = new String[tempchoices.size()];
	    for(int x=0; x<choices.length; x++)
	    {
	    	choices[x] = tempchoices.remove(0);
	    }
	    

	    JComboBox<String> cb = new JComboBox<String>(choices);
	    
	    cb.setVisible(true);
	    cb.setSelectedIndex(0);
	    JLabel picLabel = new JLabel();
	    picLabel.setVisible(true);
	    
	    ImageIcon temp = new ImageIcon((String)cb.getSelectedItem()+".png");
	    temp.setImage(temp.getImage().getScaledInstance(130, 180, Image.SCALE_DEFAULT));
	    picLabel.setIcon(temp);
	    cb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	if(e.getSource() == cb)
            	{
            		String str = "";
            		str = (String)cb.getSelectedItem()+".png";
					ImageIcon icon1 = new ImageIcon(str);
					icon1.setImage(icon1.getImage().getScaledInstance(130, 180, Image.SCALE_DEFAULT));
					picLabel.setIcon(icon1);
            		
            	}
                
            }
        });
	    
	    JButton btn = new JButton("Next");
	    panel1.add(cb);
	    panel1.add(picLabel);
	    panel1.add(btn);
	    panel1.setVisible(true);
	    panel1.setSize(150,300);
	    panel1.setLocation(1300, 5);
	    f.add(panel1);
	    panel1.revalidate();
	    //ta.revalidate();
	    
	    //ArrayList<Card> tempcard = new ArrayList<Card>();
	    
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String action = (String)cb.getSelectedItem();
                if(action.equals("Draw3"))
                {
                	p.drawThree();
                	System.out.println(">"+p.getCharacterName()+" drew 3 cards");
                	
                	currentCard = null;
                }
                else
                {
                	currentCard = p.getCard(action);
            		if(face.equals("up"))
            		{
            			JLabel cardLabel = new JLabel();
            			String str = "";
                		str = action+".png";
    					ImageIcon icon = new ImageIcon(str);
    					icon.setImage(icon.getImage().getScaledInstance(130, 180, Image.SCALE_DEFAULT));
    					cardLabel.setIcon(icon);
    					panel2.add(cardLabel);
    					cardLabel.setVisible(true);
    					
    					System.out.println(str);
    					
            		}			
            		else
            		{
            			JLabel cardLabel = new JLabel();
            			String str = "";
                		str = "---------";
    					ImageIcon icon = new ImageIcon(str);
    					icon.setImage(icon.getImage().getScaledInstance(130, 180, Image.SCALE_DEFAULT));
    					cardLabel.setIcon(icon);
    					panel2.add(cardLabel);
    					cardLabel.setVisible(true);
    					
    					System.out.println(str);
            		}
                }
                f.remove(panel1);
                clicked = true;
            }
        });
	   
        
        
	    /*while(!clicked)
	    {
	    	try {
	    	       Thread.sleep(100);
	    	    } catch(InterruptedException e) {
	    	    	
	    	    }
	    }*/

	   // return currentCard;
		//return null;
	}
	
	public void askMove(Player p, ArrayList<String> tempchoices) throws InterruptedException
	{
		
		//show selection dropdown menu for player
		//int userinput = mouseclicked button number index
		//next
		System.out.println(">Select traincar number");
		JPanel panel1 = new JPanel();
		
	    Collections.sort(tempchoices);
	   
	    String[] choices = new String[tempchoices.size()];
	    for(int x=0; x<choices.length; x++)
	    {
	    	choices[x] = tempchoices.remove(0);
	    }
	    
	    JComboBox<String> cb = new JComboBox<String>(choices);
	    
	    cb.setVisible(true);
	    cb.setSelectedIndex(0);
	    JLabel picLabel = new JLabel();
	    picLabel.setVisible(true);
	    
	    ImageIcon temp = new ImageIcon(p.getCharacterName()+".png");
	    temp.setImage(temp.getImage().getScaledInstance(130, 180, Image.SCALE_DEFAULT));
	    picLabel.setIcon(temp);
	    
	    JButton btn = new JButton("Next");
	    panel1.add(cb);
	    panel1.add(picLabel);
	    panel1.add(btn);
	    panel1.setVisible(true);
	    panel1.setSize(150,300);
	    panel1.setLocation(1300, 5);
	    f.add(panel1);
	    panel1.revalidate();
	    //ta.revalidate();
	    
	    //ArrayList<Card> tempcard = new ArrayList<Card>();
	    
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String action = (String)cb.getSelectedItem();
                tempstr = action;
                f.remove(panel1);
                clicked = true;
            }
        });
	   
        
        
	    /*while(!clicked)
	    {
	    	try {
	    	       Thread.sleep(100);
	    	    } catch(InterruptedException e) {
	    	    	
	    	    }
	    }*/

	   // return currentCard;
		//return null;
	}
	
	public void updatePosition(Train train)
	{
		for(int x=0; x<originalOrder.size(); x++)
		{
			int xpos = 0;
            int ypos = 0;
            Player player = originalOrder.get(x);
            if(player.getIsTop())
            {
            	xpos = xpositions[player.getTrainCarNum()][train.getCarList().get(player.getTrainCarNum()).getTopPlayers().indexOf(player)];
            	ypos = ypositions[1];
            }
            else
            {
            	xpos = xpositions[player.getTrainCarNum()][train.getCarList().get(player.getTrainCarNum()).getBotPlayers().indexOf(player)];
            	ypos = ypositions[0];
            }
            
            playerMap.get(x).setLocation(xpos,ypos);
            //f.revalidate();
		}
	}
	
	public synchronized void ask2(Player p, String face, JComboBox<String> cb)
	{
		 
	}
	
}