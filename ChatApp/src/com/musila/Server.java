package com.musila;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame {
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;


    public Server() {
        super("Joses instant Messsenger");
        userText = new JTextField();
        userText.setEditable(false);
        userText.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        sendMessage(event.getActionCommand());
                        userText.setText("");
                    }
                }
        );
        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(400, 600);
        setVisible(true);

    }
    //setting up and running the server

    public void startRunning() {
        try {

            server = new ServerSocket(6789, 100);
            while (true) {


                try {
                    waitForConnection();
                    setUpStreams();
                    whileChatting();
                } catch (EOFException eofException) {
                    showMessage("\n server ended the connection");
                } finally {
                    closeCrap();
                }

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void waitForConnection() throws IOException{
        showMessage("waiting for someone to connect.......\n");
        connection=server.accept();
        showMessage("Now connnected to "+ connection.getInetAddress().getHostName());


    }
    //get stream to send and receive data

    private void setUpStreams() throws IOException{
        output=new ObjectOutputStream(connection.getOutputStream());//pathway out
        output.flush();
        input=new ObjectInputStream(connection.getInputStream());//pathway in
        showMessage("\n Streams are now setup! \n");
    }

    //during the chat

    private void whileChatting() throws IOException{
        String message="you are now connected";
        sendMessage(message);
        ableToType(true);
        do{
            //have convo
            try{
                message=(String) input.readObject();
                showMessage("\n " + message);

            }catch (ClassNotFoundException classNotFoundException){
                showMessage("\n idk wtf that user sent !");
            }
        }while (!message.equals("CLIENT - END"));

    }

    //close streams and sockets after you are done chatting

    private void closeCrap(){
        showMessage("\n Closing connection......\n");
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
    //send message

    private  void sendMessage(String message){
        try{
            output.writeObject("SERVER- "+ message);
            output.flush();
            showMessage("\n SERVER_ " + message);
        }
        catch (IOException ioException){
            chatWindow.append("\n ERROR: DUDE I CANT SENT THAT MESSAGE ");
        }

    }

    //updates chatWindow
    private  void showMessage(final String text){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        chatWindow.append(text);
                    }
                }
        );
    }
    //let the user type in their box
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
