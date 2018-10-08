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

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
public class MouseListener1 extends JPanel {

	public MouseListener1()
	{
		addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	            System.out.println(me); 
	          } 
	        }); 
	}
	
	
}
