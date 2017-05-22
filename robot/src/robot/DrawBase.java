package robot;
import java.applet.Applet;
import java.awt.event.*;  // 마우스 이벤트관련 라이브러리


public class DrawBase extends Applet implements Runnable, KeyListener{



    public void init(){


              addKeyListener(this);  // 리스너 추가

     }




     public void keyPressed(KeyEvent e){
                int keycode = e.getKeyCode();

                switch(keycode){
                           case KeyEvent.VK_UP:
                            updirect = true;
                           break;

                           case KeyEvent.VK_DOWN:
                            downdirect = true;
                           break;

                           case KeyEvent.VK_LEFT:
                            leftdirect = true;
                           break;

                           case KeyEvent.VK_RIGHT:
                            rightdirect = true;
                           break;
                }


                str = "KEY Press : "+e.getKeyChar();


     }





     public void keyReleased(KeyEvent e){
                int keycode = e.getKeyCode();

                switch(keycode){
                           case KeyEvent.VK_UP:
                            updirect = false;
                           break;
                           case KeyEvent.VK_DOWN:
                            downdirect = false;
                           break;
                           case KeyEvent.VK_LEFT:
                            leftdirect = false;
                           break;
                           case KeyEvent.VK_RIGHT:
                            rightdirect = false;
                           break;
                }



                str = "KEY Press : None";

     }



     public void keyTyped(KeyEvent e){
                int keycode = e.getKeyCode();
     }


}