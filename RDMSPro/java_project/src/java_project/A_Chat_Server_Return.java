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

/**
 *
 * @author Thatte Manasa
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import static java_project.A_Chat_Server.sgui;
/**
 *
 * @author Thatte Manasa
 */
public class A_Chat_Server_Return implements Runnable {
    Socket SOCK;
    private Scanner INPUT;
    private PrintWriter OUT;
    String MESSAGE="";
    
    public A_Chat_Server_Return(Socket X){
        this.SOCK=X;
    }
    public void CheckConnection() throws IOException{
    if(!SOCK.isConnected()){
        for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++)
        {
           
        if(A_Chat_Server.ConnectionArray.get(i)==SOCK){
            A_Chat_Server.ConnectionArray.remove(i);
            
            
        } 
       
    }
        for(int i=0;i<=A_Chat_Server.ConnectionArray.size();i++){
            
            Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
            PrintWriter TEMP_OUT=new PrintWriter(TEMP_SOCK.getOutputStream());
            TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName()+" :disconnected!");
            
            
            TEMP_OUT.flush();
            System.out.println(TEMP_SOCK.getLocalAddress().getHostName()+" :disconnected!");
          //sgui.STA_CONVERSATION.append(TEMP_SOCK.getLocalAddress().getHostName()+" :disconnected!");

}
    
}
}
    public void run(){
        try{
            try{
                INPUT=new Scanner(SOCK.getInputStream());
                OUT=new PrintWriter(SOCK.getOutputStream());
                while(true)
                {
                    CheckConnection();
                    if(!INPUT.hasNext()){
                        return;
                    }
                    MESSAGE=INPUT.nextLine();
                   // System.out.println("MESSAGE :" +MESSAGE);
                   // sgui.STA_CONVERSATION.append("MESSAGE :" +MESSAGE);
                    
                    for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++)
                    {
                        Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
                        PrintWriter TEMP_OUT=new PrintWriter(TEMP_SOCK.getOutputStream());
                        TEMP_OUT.println(MESSAGE);
                        TEMP_OUT.flush();
                        System.out.println("Sent to :"+TEMP_SOCK.getLocalAddress().getHostName());
                        //sgui.STA_CONVERSATION.append("Sent to :"+TEMP_SOCK.getLocalAddress().getHostName());
                    }
                        
                }
            }
            catch(Exception ex){
                System.out.print(ex);
            }
           /* finally
            {
                SOCK.close();
            }*/
        }
        catch(Exception X){
         System.out.print(X);
        }
    }
}
