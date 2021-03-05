package com.musila;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame {
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message="";
    private String serverIP;
    private Socket connection;

    //constructor
    public  Client(String host){
        super("Client MOFO!");
        serverIP=host;
        userText=new JTextField();
        userText.setEditable(false);
        userText.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent event) {
                        sendMessage(event.getActionCommand());
                        userText.setText("");
                    }
                }
        );
        add(userText,BorderLayout.NORTH);
        chatWindow=new JTextArea();
        add(new JScrollPane(chatWindow),BorderLayout.CENTER);
        setSize(400,600);
        setVisible(true);

    }
    //connect to server

    public void startRunning(){
        try{
            connectToServer();
            setUpStreams();
            whileChatting();
        }
        catch (EOFException eofException){
            showMessage("\n Client Terminated the connnection");

        }catch (IOException ioException){
            ioException.printStackTrace();
        }finally{
            closeCrap();

        }
    }
    //connect to server
    private void connectToServer() throws  IOException{
        showMessage("Attempting Connection ...\n");
        connection=new Socket(InetAddress.getByName(serverIP),6789);
        showMessage("Connected to: " + connection.getInetAddress().getHostName());

    }

    //set up streams
    private void setUpStreams() throws  IOException{
        output=new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input=new ObjectInputStream(connection.getInputStream());
        showMessage("\n Dude your streams are now go to go \n");

    }

    //while chatting with server

    public void whileChatting() throws IOException{
        ableToType(true);
        do{
            try{
                message=(String) input.readObject();
                showMessage("\n" + message);
            }
            catch (ClassNotFoundException classNotFoundException){
                showMessage("\n i dont know that object type");
            }

        }while(!message.equals("SERVER - END"));
    }
    //close the streams

    private  void closeCrap(){
        showMessage("\n closing crap down ....");
        ableToType(false);
        try{
            output.close();
            input.close();
            connection.close();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    //send messages to server
    private void sendMessage(String message){
        try{
            output.writeObject("CLIENT- " + message);
            output.flush();
            showMessage("\nCLIENT - " + message);
        }
        catch(IOException ioException){
            chatWindow.append("\n Something happend while sending the message");
        }
    }

    //chane /update chatwindow
    private  void showMessage(final String m){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        chatWindow.append(m);
                    }
                }
        );
    }

    //gives user permission to type
    private void ableToType(final boolean tof){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        userText.setEditable(tof);
                    }
                }
        );
    }

}
