
package java_project;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ServerInitiator {
    //Main server frame
     Socket socket = null;
    private JFrame frame = new JFrame();
    //JDesktopPane represents the main container that will contain all
    //connected clients' screens
    private JDesktopPane desktop = new JDesktopPane();

    /*public static void main(String args[]){
        String port = JOptionPane.showInputDialog("Please enter listening port");
        new ServerInitiator().initialize(Integer.parseInt(port));
    }*/

    public void initialize(String name) throws IOException, InterruptedException{

        drawGUI();
        Enumeration e,e1;
          String p = null;
         String o = null;
              for (e = A_Chat_Server.d.keys(),e1 = A_Chat_Server.d.elements(); e.hasMoreElements() && e1.hasMoreElements();){
                   o=(String) e1.nextElement();
                   System.out.println("%%%%%%%%%%"+o);
                   System.out.println(name);
                 p=(String) e.nextElement();
                 System.out.println(p+"!!!!!!!!!!!!!!!!!!!!!!!!!!1");
                 if(p.equalsIgnoreCase(name)){
                     break;
                 }
                 
             } 
              System.out.println(p);
            System.out.println("Connecting to server ..........");
            
            Socket client = new Socket(o,12345);
             System.out.println("Connection Established.");
             sleep(100);
         //     while(true){
            new ClientHandler(client,desktop);
       // }
    }

    /*
     * Draws the main server GUI
     */
    public void drawGUI(){
            frame.add(desktop,BorderLayout.CENTER);
                    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                int confirm = JOptionPane.showOptionDialog(
             null, "Are You Sure to Close Application?", 
             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
             JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
           frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
              
   }
        });
            //Show the frame in a maximized state
            frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
    }
}
