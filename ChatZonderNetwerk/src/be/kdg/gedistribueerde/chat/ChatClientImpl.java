package be.kdg.gedistribueerde.chat;

import be.kdg.gedistribueerde.chat.communication.NetworkAddress;

public class ChatClientImpl implements ChatClient {
    private final NetworkAddress address;


    public ChatClientImpl(NetworkAddress address) {
        this.address = address;

    }
    
    @Override
    public void send(String message) {
        
    }

    @Override
    public void receive(String message) {

    }

    @Override
    public void register() {

    }

    @Override
    public void unregister() {

    }

    @Override
    public NetworkAddress getAddress() {
        return this.address;
    }

    @Override
    public String getName() {
        return null;
    }
}
