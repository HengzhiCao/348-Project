import java.io.*;
import java.net.Socket;

public class PlayerSender extends Thread {

    private final Socket socket;

    public PlayerSender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            handleServerSend();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleServerSend() throws IOException, InterruptedIOException {
        // to send data
        PrintStream psOut = new PrintStream(socket.getOutputStream());

        // to read data from the keyboard
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

        while(true){

            String msgIn,msgOut;

            // while not typing EXIT
            while(!(msgOut = kb.readLine()).equalsIgnoreCase("exit")){
                psOut.println(msgOut);
            }

            System.out.println("Exiting\t Closing connection.");

            // close connection
            psOut.close();
            kb.close();
            socket.close();

            // terminate application
            System.exit(0);
        }

    }
}
