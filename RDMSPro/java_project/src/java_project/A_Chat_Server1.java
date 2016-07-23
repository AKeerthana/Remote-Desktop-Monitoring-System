/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_project;

/**
 *
 * @author Thatte Manasa
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import static java_project.Server_GUI.SMainWindow;
import static java_project.Server_GUI.STF_Message;
import javax.swing.JOptionPane;

/**
 *
 * @author Thatte Manas
 */

public class A_Chat_Server1 implements Runnable{
  Socket SSOCK;
    Scanner SINPUT;
    Scanner SSEND=new Scanner(System.in);
    PrintWriter SOUT;
    public static String[] usr;
   
    
    public A_Chat_Server1(Socket X){
        this.SSOCK=X;
    }
    public A_Chat_Server1(){
        
    }
        
    
    public void run(){
        try{
            try{
               
                SINPUT=new Scanner(SSOCK.getInputStream());
                SOUT=new PrintWriter(SSOCK.getOutputStream());
                SOUT.flush();
                SCheckStream();
                
            }
            finally{
                SSOCK.close();
            }
            
        }
        catch(Exception X){
            System.out.print(X);
            
        }
    }
    
    public void SDISCONNECT() throws IOException{
        SOUT.println(Server_GUI.UserName+" - "+SSOCK.getLocalAddress().getHostName()+" has disconnected!");
        SOUT.flush();
        SSOCK.close();
        JOptionPane.showMessageDialog(null,"You disconnected");
        System.exit(0);
        
    } 
    
    public void SCheckStream(){
        while(true){
            SRECIEVE();
        }
    }
    public void SRECIEVE() {
        String MESSAGE="";
        if(SINPUT.hasNext()){
            MESSAGE=SINPUT.nextLine();
            
            if(MESSAGE.contains("?#!")){
                String TEMP1=MESSAGE.substring(3);
                TEMP1=TEMP1.replace("[","");
                 TEMP1=TEMP1.replace("]","");
               
               String[] CurrentUsers1=TEMP1.split(", ");
                Server_GUI.SJL_ONLINE.setListData(CurrentUsers1);
                
            }
          
            else{
                Server_GUI.STA_CONVERSATION.append(MESSAGE+"\n"); 
                
                if(MESSAGE.contains("command")){
                  usr=MESSAGE.split(" ");
                  System.out.print(usr[1]);
                }
                
                if(MESSAGE.contains("disconnected")){
                String[] TEMP2=MESSAGE.split(" ");
                A_Chat_Server.CurrentUsers.remove(TEMP2[0]);
               // Server_GUI.SJL_ONLINE.removeAll();
                Server_GUI.SJL_ONLINE.setListData(A_Chat_Server.CurrentUsers.toArray());
                
                
                        try{
                         for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++){
            Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
          PrintWriter OUT=new PrintWriter(TEMP_SOCK.getOutputStream()); 
           OUT.println("?#!"+A_Chat_Server.CurrentUsers);
           OUT.flush();
        }
                        }catch(Exception A){
                            System.out.print(A);
                        }
                
                }
            }
            
                
        }
        
    }
    
    public void SSEND(String X){
        SOUT.println(Server_GUI.UserName+": "+X);
        SOUT.flush();
        Server_GUI.STF_Message.setText("");
    }

  

}