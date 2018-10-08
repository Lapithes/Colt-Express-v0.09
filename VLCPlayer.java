import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import java.util.*;

import javax.sound.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class VLCPlayer extends JPanel{

private EmbeddedMediaPlayerComponent mediaPlayerComponent;

//This is the path for libvlc.dll
	
static String VLCLIBPATH = "C:\\Program Files\\VideoLAN\\VLC";
	
public static void main(String[] args) 
	{
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), VLCLIBPATH);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				VLCPlayer vlcPlayer = new VLCPlayer();
			}
		});

	}
	public VLCPlayer() 
	{

//MAXIMIZE TO SCREEN
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

		JFrame frame = new JFrame("VLC Player");
		JButton b = new JButton();
		b.setBorder(null);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
		b.addActionListener(new ActionListener1());
		frame.add(b);
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		
		frame.setContentPane(mediaPlayerComponent);

		frame.setLocation(0, 0);
		frame.setSize(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		mediaPlayerComponent.getMediaPlayer().playMedia("coltexpressintro2.mp4");//Movie name which want to play
	}
}