package texturas;

import java.io.File;
import java.io.IOException;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class Renderer extends KeyAdapter implements GLEventListener  {

   private GLU glu = new GLU();
   private float eixox,eixoy,eixoz;
   private int textura;
   
   @Override
   public void display(GLAutoDrawable drawable) {
   
      GL2 gl = drawable.getGL().getGL2();
      gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
      gl.glLoadIdentity(); // Reset The View
      gl.glTranslatef(0f, 0f, -5.0f);
		
      gl.glRotatef(eixox, 1.0f, 1.0f, 1.0f);
      gl.glRotatef(eixoy, 0.0f, 1.0f, 0.0f);
      gl.glRotatef(eixoz, 0.0f, 0.0f, 1.0f);
		
      gl.glBindTexture(GL2.GL_TEXTURE_2D, textura);
      gl.glBegin(GL2.GL_QUADS);
      //feita a primeira face do cubo - depois só repetir e mudar valores
      gl.glTexCoord2f(0.0f, 0.0f); 
      gl.glVertex3f(-1.0f, -1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f( 1.0f, -1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f( 1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(0.0f, 1.0f); 
      gl.glVertex3f(-1.0f, 1.0f, 1.0f);

      gl.glTexCoord2f(1.0f, 0.0f); 
      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
      gl.glTexCoord2f(1.0f, 1.0f); 
      gl.glVertex3f(-1.0f, 1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 1.0f); 
      gl.glVertex3f( 1.0f, 1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 0.0f); 
      gl.glVertex3f( 1.0f, -1.0f, -1.0f);
      
      gl.glTexCoord2f(0.0f, 0.0f); 
      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f(-1.0f, -1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f(-1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(0.0f, 1.0f);
      gl.glVertex3f(-1.0f, 1.0f, -1.0f);
      
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f( 1.0f, -1.0f, -1.0f);
      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f( 1.0f, 1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 1.0f);
      gl.glVertex3f( 1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(0.0f, 0.0f);
      gl.glVertex3f( 1.0f, -1.0f, 1.0f);

      gl.glTexCoord2f(0.0f, 1.0f); 
      gl.glVertex3f(-1.0f, 1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 0.0f); 
      gl.glVertex3f(-1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f( 1.0f, 1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f( 1.0f, 1.0f, -1.0f);

      gl.glTexCoord2f(1.0f, 1.0f);
      gl.glVertex3f(-1.0f, -1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 1.0f);
      gl.glVertex3f( 1.0f, -1.0f, -1.0f);
      gl.glTexCoord2f(0.0f, 0.0f);
      gl.glVertex3f( 1.0f, -1.0f, 1.0f);
      gl.glTexCoord2f(1.0f, 0.0f);
      gl.glVertex3f(-1.0f, -1.0f, 1.0f);

            
      gl.glEnd();
      gl.glFlush();

      
   }
   
   @Override
   public void dispose(GLAutoDrawable drawable) {
      // method body
   }
   
   
   @Override
   public void init(GLAutoDrawable drawable) {
	
      GL2 gl = drawable.getGL().getGL2();
      
      gl.glClearColor(0f, 0f, 0f, 0f);
      gl.glClearDepth(1.0f);
      gl.glEnable(GL2.GL_DEPTH_TEST);
    
      gl.glEnable(GL2.GL_TEXTURE_2D);
      try{
		
         File im = new File("src\\texturas\\textura.jpg ");
         Texture t = TextureIO.newTexture(im, true);
         textura = t.getTextureObject(gl);
          
      }catch(IOException e){
         e.printStackTrace();
         JOptionPane.showMessageDialog(null,"Erro na leitura do arquivo textura.jpg");
      }
   }
      
   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
   
      // TODO Auto-generated method stub
      GL2 gl = drawable.getGL().getGL2();
      if(height == 0)
         height = 1;
			
      float h = (float) width / (float) height;
      gl.glViewport(0, 0, width, height);
      gl.glMatrixMode(GL2.GL_PROJECTION);
      gl.glLoadIdentity();
		
      glu.gluPerspective(45.0f, h, 1.0, 20.0);
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity();
   }
   
   public static void main(String[] args) {
   
      // TODO Auto-generated method stub
      GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      
      // The canvas
      GLCanvas glcanvas = new GLCanvas(capabilities);
      Renderer r = new Renderer();
		
      glcanvas.addGLEventListener(r);
      glcanvas.setSize(400, 400);
		
      JFrame frame = new JFrame (" Cubo com Textura");
      frame.getContentPane().add(glcanvas);
      frame.setSize(frame.getContentPane().getPreferredSize());
      frame.setVisible(true);
      frame.addKeyListener(r);
      FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
		
      animator.start();
   }
   
   @Override
   public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_NUMPAD3:
			eixox = eixox + 1;
			break;
		case KeyEvent.VK_NUMPAD7:
			eixox = eixox - 1;
			break;
		case KeyEvent.VK_NUMPAD8:
			eixoz = eixoz -1;
			break;
		case KeyEvent.VK_NUMPAD2:
			eixoz = eixoz + 1;
			break;
		case KeyEvent.VK_NUMPAD4:
			eixoy = eixoy + 1;
			break;
		case KeyEvent.VK_NUMPAD6:
			eixoy = eixoy -1 ;
			break;
                    

		}
                
	}
	
}