import java.io.*;
import java.net.Socket;

public class PlayerListener extends Thread {

    private final Socket socket;
    public PlayerListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            handleServerListen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleServerListen() throws IOException, InterruptedIOException {


        //to read data
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        while(true){

            String msgIn,msgOut;

            //while Msg In is not null
            while((msgIn = br.readLine())!=null){
                System.out.println("Player:\t"+msgIn);
            }

            // close connection
            br.close();
            socket.close();

            // terminate application
            System.exit(0);
        }

    }
}
