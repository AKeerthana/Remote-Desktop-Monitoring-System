package java_project;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
class SCapture implements Runnable{
    
     Enumeration e,e1;
          String p = null;
         String o = null;
             public static int imgIndex=0;  
              
     public void run() {
         try {
             new SCapture().initialize();
         } catch (IOException ex) {
             Logger.getLogger(SCapture.class.getName()).log(Level.SEVERE, null, ex);
         }
}
     void initialize() throws IOException{
          String ip = JOptionPane.showInputDialog("Please enter client's name for capture");
          for (e = A_Chat_Server.d.keys(),e1 = A_Chat_Server.d.elements(); e.hasMoreElements() && e1.hasMoreElements();){
                   o=(String) e1.nextElement();
                   System.out.println("%%%%%%%%%%"+o);
                   System.out.println(ip);
                 p=(String) e.nextElement();
                 System.out.println(p+"!!!!!!!!!!!!!!!!!!!!!!!!!!1");
                 if(p.equalsIgnoreCase(ip)){
                     break;
                 }
                 
             } 
              System.out.println(p);
        Socket s2 = new Socket(o,5678);
     
        System.out.println("----------------CAPTURE RECIEVED--------------------");
                
         byte[] contents = new byte[2048];
         String name1 = s2.getInetAddress().getHostName();
     Enumeration e,e1;
          String p = null;
         String o = null;
              for (e = A_Chat_Server.d.keys(),e1 = A_Chat_Server.d.elements(); e.hasMoreElements() && e1.hasMoreElements();){
                   o=(String) e1.nextElement(); 
                 p=(String) e.nextElement();
                 
                 if(o.equalsIgnoreCase(name1)){
                     break;
                 }
                 
             }
              
              
        FileOutputStream fos = new FileOutputStream(p+"-"+imgIndex+".jpg");//U need to change the client's name 
        imgIndex++;
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = s2.getInputStream();
        
        //No of bytes read in one read() call
        int bytesRead = 0; 
        
        while((bytesRead=is.read(contents))!=-1)
            bos.write(contents, 0, bytesRead); 
        
        bos.flush();   
              
               
                fos.close();
                bos.close();
                 is.close();
                s2.close();
     }
}
class Shut implements Runnable{

    @Override
    public void run() {
         Enumeration e,e1;
          String p = null;
         String o = null;
             
        String ip = JOptionPane.showInputDialog("Please enter client's name for capture");
        for (e = A_Chat_Server.d.keys(),e1 = A_Chat_Server.d.elements(); e.hasMoreElements() && e1.hasMoreElements();){
                   o=(String) e1.nextElement();
                   System.out.println("%%%%%%%%%%"+o);
                   System.out.println(ip);
                 p=(String) e.nextElement();
                 System.out.println(p+"!!!!!!!!!!!!!!!!!!!!!!!!!!1");
                 if(p.equalsIgnoreCase(ip)){
                     break;
                 }
                 
             }
        try {
            Socket tu = new Socket(o,3456);
        } catch (IOException ex) {
            Logger.getLogger(Shut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
class GetFile implements Runnable{
    ServerSocket ss;
    Socket s;
    public static int fileIndex=0;  
        @Override
        public void run() {
            try {
                ACTION_SB_HELP1();
            } catch (IOException ex) {
               // Logger.getLogger(GetFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CryptoException ex) {
            Logger.getLogger(GetFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(GetFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        void ACTION_SB_HELP1() throws IOException, CryptoException, InvalidKeyException{
            ss = new ServerSocket(1234);
            while(true){
           s = ss.accept();
           String name = s.getInetAddress().getHostName();
             Enumeration e,e1;
          String p = null;
         String o = null;
              for (e = A_Chat_Server.d.keys(),e1 = A_Chat_Server.d.elements(); e.hasMoreElements() && e1.hasMoreElements();){
                   o=(String) e1.nextElement(); 
                 p=(String) e.nextElement();
                 
                 if(o.equalsIgnoreCase(name)){
                     break;
                 }
                 
             }
             System.out.println(p);
      //     File file;
                //Scanner cin;
                //PrintWriter out;
           System.out.println("Connected!");
           byte[] key = new byte[]{'M','a','r','y',' ','h','a','s',' ','o','n','e',' ','c','a','t'}; 
          // String pq = "Mary has one cat";
           //key = (byte[]) pq;
           byte[] contents = new byte[10000];
           File encryptedFile = new File(p+".txt");
       FileOutputStream fos = new FileOutputStream(encryptedFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = s.getInputStream();
        int bytesRead = 0; 
        System.out.println("Receiving file");
        while((bytesRead=is.read(contents))!=-1)
            bos.write(contents, 0, bytesRead);  
           System.out.println("Received file");
           is.close();
       
        bos.close();
         fos.close();
        System.out.println("Saved file");
        
               File decryptedFile = new File(p+fileIndex+"1.txt");
               fileIndex++;
             SCryptoUtils.decrypt(key, encryptedFile, decryptedFile);
                   System.out.println("LINE 6");
                s.close();
                System.out.println("LINE 7");
        }
        }
          
      }

class Server_GUI extends JFrame{
            private static A_Chat_Server1 ChatServer;
            public static String UserName="Anonymous";
PrintStream p;

           public Server_GUI() {
        SinitComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
 
   public static JFrame SMainWindow=new JFrame();
       private static JLabel SL_Message = new JLabel();
       public static JTextField STF_Message = new JTextField();
       private static JLabel SL_LoggedInAs = new JLabel();
       private static JButton SB_DISCONNECT = new JButton();
       private static JButton B_CONNECT = new JButton();
       private static JButton SB_SSEND = new JButton();
       private static JButton SB_ABOUT = new JButton();
       private static JButton SB_HELP = new JButton();
       private static JLabel SL_Conversation = new JLabel();
       private static JLabel SL_ONLINE = new JLabel();
       private static JScrollPane SSP_ONLINE = new JScrollPane();
       public static JList SJL_ONLINE = new JList();
       private static JScrollPane SSP_CONVERSATION = new JScrollPane();
       public static JTextArea STA_CONVERSATION = new JTextArea();
       // private static JTextField SSL_LoggedInAsBox = new JTextField();
         private static JScrollPane sjScrollPane1 = new javax.swing.JScrollPane();
        private static JScrollPane sjScrollPane2 = new javax.swing.JScrollPane();
        private static String Commands[]={"","Client Chat","Remote Control","Get Capture","Shut down"};
        private static JComboBox CommandDropdown = new JComboBox(Commands);
        
        
        
        static Server_GUI sgui;
        
        public static void BuildSA_Chat_Client_GUI(){
            sgui=new Server_GUI();
                sgui.setVisible(true);
                SInitialize();
                sgui.setTitle(UserName+"'s Chat Box");
                SMainWindow_Action();
                
                sgui.setResizable(false);
                
        }
        
        
        

public static void main(String[] args){
//BuildSMainWindow();
java.awt.EventQueue.invokeLater(new Runnable() {
      
            public void run() {
             sgui=new Server_GUI();
                sgui.setVisible(true);
                SInitialize();
                sgui.setTitle(UserName+"'s Chat Box");
                SMainWindow_Action();
                sgui.setResizable(false);
                
           }
        });

}

public static void SConnect(){
try{
 final int PORT=444;
final String HOST="SaiHarini";
Socket SSOCK=new Socket(HOST,PORT);
//System.out.println("You connected to: "+HOST);

ChatServer=new A_Chat_Server1(SSOCK);

//SSEND name to add to online list
PrintWriter SOUT=new PrintWriter(SSOCK.getOutputStream());
SOUT.println(UserName);
SOUT.flush();

Thread X=new Thread(ChatServer);
X.start();

}
catch(Exception X)
{
 System.out.print(X);
JOptionPane.showMessageDialog(null,"Server not responding!");
System.exit(0);
}
}
public static void SInitialize(){
     SB_SSEND.setEnabled(true);
          SB_DISCONNECT.setEnabled(true);
           // B_CONNECT.setEnabled(true);
 SMainWindow.setTitle("Server's Chat Box");
       // LogInWindow.setVisible(false);
        SB_SSEND.setEnabled(true);
        SB_DISCONNECT.setEnabled(true);
}


public void SinitComponents(){
    
            
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(889, 526));

        SL_Message.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        SL_Message.setText("       Message");

        SB_DISCONNECT.setBackground(new java.awt.Color(0, 0, 255));
        SB_DISCONNECT.setForeground(new java.awt.Color(255, 255, 255));
        SB_DISCONNECT.setText("DISCONNECT");
        SB_DISCONNECT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    
        STF_Message.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        STF_Message.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, java.awt.Color.black));
        STF_Message.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        SL_Conversation.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        SL_Conversation.setText("                  Conversation");

        SL_LoggedInAs.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        SL_LoggedInAs.setText("     Enter  command ...");

        B_CONNECT.setBackground(new java.awt.Color(0, 0, 255));
        B_CONNECT.setForeground(new java.awt.Color(255, 255, 255));
        B_CONNECT.setText("BEGIN");
        B_CONNECT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        SB_SSEND.setBackground(new java.awt.Color(0, 0, 255));
        SB_SSEND.setForeground(new java.awt.Color(255, 255, 255));
        SB_SSEND.setText("SEND");
        SB_SSEND.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        SB_ABOUT.setBackground(new java.awt.Color(0, 0, 255));
        SB_ABOUT.setForeground(new java.awt.Color(255, 255, 255));
        SB_ABOUT.setText("ABOUT");
        SB_ABOUT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        SB_HELP.setBackground(new java.awt.Color(0, 0, 255));
        SB_HELP.setForeground(new java.awt.Color(255, 255, 255));
        SB_HELP.setText("COMMAND");
        SB_HELP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
         

        SL_ONLINE.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        SL_ONLINE.setText("           Currently Online");

        SJL_ONLINE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SJL_ONLINE.setFont(new java.awt.Font("Algerian", 0, 18)); // NOI18N
        SJL_ONLINE.setForeground(new java.awt.Color(0, 0, 255));
     
        sjScrollPane2.setViewportView(SJL_ONLINE);

         STA_CONVERSATION.setEditable(false);
        STA_CONVERSATION.setColumns(20);
        STA_CONVERSATION.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        STA_CONVERSATION.setForeground(new java.awt.Color(0, 0, 255));
        STA_CONVERSATION.setRows(5);
        STA_CONVERSATION.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SSP_CONVERSATION.setViewportView(STA_CONVERSATION);
        SSP_CONVERSATION.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        SSP_CONVERSATION.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        

        /*SSL_LoggedInAsBox.setFont(new java.awt.Font("Comic Sans MS", 2, 14)); // NOI18N
        SSL_LoggedInAsBox.setForeground(new java.awt.Color(204, 0, 0));
        SSL_LoggedInAsBox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SSL_LoggedInAsBox.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 153, 153), new java.awt.Color(102, 0, 102))));
*/
        CommandDropdown.setFont(new java.awt.Font("Comic Sans MS", 2, 14)); // NOI18N
        CommandDropdown.setForeground(new java.awt.Color(204, 0, 0));
        //CommandDropdown.setHorizontalAlignment(javax.swing.JTextField.CENTER);
         CommandDropdown.setAlignmentY(javax.swing.JTextField.CENTER);
        CommandDropdown.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 153, 153), new java.awt.Color(102, 0, 102))));

        
        
          javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane());
       getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SSP_CONVERSATION)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sjScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(SL_Conversation, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SL_ONLINE, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(SL_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(STF_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SL_LoggedInAs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CommandDropdown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(SB_DISCONNECT, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(B_CONNECT, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SB_SSEND, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SB_ABOUT, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SB_HELP, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SL_LoggedInAs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CommandDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SL_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(STF_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SB_DISCONNECT, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B_CONNECT, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SB_SSEND, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SB_ABOUT, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SB_HELP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SL_ONLINE, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SL_Conversation, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SSP_CONVERSATION)
                    .addComponent(sjScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
 
        

        pack();
        
}

  

      public static void SMainWindow_Action(){
          SB_SSEND.addActionListener(
          new java.awt.event.ActionListener(){
              public void actionPerformed(java.awt.event.ActionEvent evt){
                  ACTION_SB_SSEND();
              }
          });
          
          SB_DISCONNECT.addActionListener(
          new java.awt.event.ActionListener(){
              public void actionPerformed(java.awt.event.ActionEvent evt){
                  String c=STA_CONVERSATION.getText();
                  PrintWriter pw=null;
                  int p;
                  //System.out.println(c);
                  File f=null;
                  f=new File("Backups.txt");
                  try
                  {
                      pw=new PrintWriter(new BufferedWriter(new FileWriter(f,true)));
                  pw.println(c);
                  } catch (IOException e) {
                      System.out.println(e);
                  }
                  finally{pw.close();}
                   try{
                       A_Chat_Server1 a = new A_Chat_Server1();
              a.SDISCONNECT();
              B_CONNECT.setEnabled(false);
              SB_SSEND.setEnabled(false);
               SB_DISCONNECT.setEnabled(false);
                SB_ABOUT.setEnabled(false);
              //JL_ONLINE.removeAll();
          }
          catch(Exception Y){
              Y.printStackTrace();
          }
                  ACTION_SB_DISCONNECT();
              }
          });
          
       B_CONNECT.addActionListener(
          new java.awt.event.ActionListener(){
              public void actionPerformed(java.awt.event.ActionEvent evt){
                  UserName="Server";
                  sgui.setTitle("Server's Chat Box");
                //  SSL_LoggedInAsBox.setText("SERVER");
                //  A_Chat_Server.CurrentUsers.add("UserN");
                  SB_SSEND.setEnabled(true);
        SB_DISCONNECT.setEnabled(true);
       B_CONNECT.setEnabled(false);
       SConnect();
       
        SMainWindow.setTitle("Server's Chat Box");
              }
          });
          
          SB_HELP.addActionListener(
          new java.awt.event.ActionListener(){
              
              public void actionPerformed(java.awt.event.ActionEvent evt){
                  try {
                      // System.out.println("harini");
                      ACTION_SB_HELP();
                  } catch (IOException ex) {
                      Logger.getLogger(Server_GUI.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          });
          
        SB_ABOUT.addActionListener(
          new java.awt.event.ActionListener(){
              public void actionPerformed(java.awt.event.ActionEvent evt){
                 /* ServerInitiator s = new ServerInitiator();
                   String ip = JOptionPane.showInputDialog("Please enter server IP");
                  try {
                      s.initialize(ip);
                  } catch (IOException ex) {
                      Logger.getLogger(Server_GUI.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (InterruptedException ex) {
                      Logger.getLogger(Server_GUI.class.getName()).log(Level.SEVERE, null, ex);
                  }*/
                  JOptionPane.showMessageDialog(null, "Remote Desktop Monitoring System");
                 
                  
              }
          });
      }
     
      public static void ACTION_SB_SSEND(){
          if(!STF_Message.getText().equals("")){
              ChatServer.SSEND(STF_Message.getText());
              STF_Message.requestFocus();
          }
      }
      
      public static void ACTION_SB_DISCONNECT(){
          try{
              /* for(int i=0;i<=A_Chat_Server.ConnectionArray.size();i++){
            Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
            PrintWriter TEMP_OUT=new PrintWriter(TEMP_SOCK.getOutputStream());
            TEMP_OUT.println("Server: disconnected!");
            TEMP_OUT.flush();
            System.out.println(" Server disconnected!");
          //sgui.STA_CONVERSATION.append(TEMP_SOCK.getLocalAddress().getHostName()+" :disconnected!");
            

}*/
              ChatServer.SDISCONNECT();
          }
          catch(Exception Y){
              Y.printStackTrace();
          }
      }
      
      public static void ACTION_SB_ABOUT() throws IOException{
          
           System.out.println("hdjhj");
           JOptionPane p=new JOptionPane();
           SMainWindow.setEnabled(false);
           p.showMessageDialog(null,"Remote Desktop Monitoring System!");
          SMainWindow.setEnabled(true);
           
       }
      
       public static void ACTION_SB_HELP() throws IOException{
        if(!CommandDropdown.getSelectedItem().equals("")){
             System.out.println("inside ACTION_SB_HELP!!");
            if(CommandDropdown.getSelectedItem().equals("Get Capture")){
                 System.out.println("started capture!!");
               Thread p = new Thread(new SCapture());
               p.start();
            }
            if(CommandDropdown.getSelectedItem().equals("Shut down")){
                Thread t = new Thread(new Shut());
                t.start();
            }
            if(CommandDropdown.getSelectedItem().equals("Client Chat")){
               Thread c = new Thread(new serverSingleChat());
               c.start();
            }
            
            if(CommandDropdown.getSelectedItem().equals("Remote Control")){
           
            ServerInitiator s1 = new ServerInitiator();
                   String ip = JOptionPane.showInputDialog("Please enter client's name");
                  try {
                      s1.initialize(ip);
                  } catch (Exception ex) {
                      Logger.getLogger(Server_GUI.class.getName()).log(Level.SEVERE, null, ex);
                  }
            
            
        }   
         
              CommandDropdown.setSelectedIndex(0);
        }
}
   
}