package be.kdg.gedistribueerde.chat;

public class Start {
    public static void main(String[] args) {
        ChatServerSkeleton chatServer = new ChatServerSkeleton();
        chatServer.run();

    }
}
