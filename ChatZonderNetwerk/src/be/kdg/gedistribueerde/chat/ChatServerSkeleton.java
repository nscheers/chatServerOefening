package be.kdg.gedistribueerde.chat;

import be.kdg.gedistribueerde.chat.communication.MessageManager;
import be.kdg.gedistribueerde.chat.communication.MethodCallMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatServerSkeleton {
    private final MessageManager messageManager;
    private List<ChatClient> clients;

    public ChatServerSkeleton() {
        this.messageManager = new MessageManager();
        System.out.println("my address = " + messageManager.getMyAddress());
        this.clients = new ArrayList<ChatClient>();
    }


    private void handleRegister(MethodCallMessage request) {
        ChatClient client = new ChatClientImpl(request.getOriginator());
        clients.add(client);
        sendAll(client.getAddress() + " has entered the room");
    }

    private void handleUnregister(MethodCallMessage request) {
        ChatClient client = new ChatClientImpl(request.getOriginator());
        clients.remove(client);
        sendAll(client.getAddress() + " has left the room");
    }

    private void handleSend(MethodCallMessage request) {
        sendAll(request.getOriginator() + request.getParameter("message"));
    }

    private void sendAll(String message){
        MethodCallMessage msg = new MethodCallMessage(messageManager.getMyAddress(), "send");
        msg.setParameter("message", message);
        for(ChatClient c : clients) {
            messageManager.send(msg, c.getAddress());
        }
    }

    private void handleRequest(MethodCallMessage request) {
        //System.out.println("ContactsSkeleton:handleRequest(" + request + ")");
        String methodName = request.getMethodName();
        if ("register".equals(methodName)) {
            handleRegister(request);
        } else if ("unregister".equals(methodName)) {
            handleUnregister(request);
        } else if ("send".equals(methodName)) {
            handleSend(request);
        } else {
            System.out.println("ContactsSkeleton: received an unknown request:");
            System.out.println(request);
        }
    }


    public void run() {
        while (true) {
            MethodCallMessage request = messageManager.wReceive();
            handleRequest(request);
        }
    }
}
