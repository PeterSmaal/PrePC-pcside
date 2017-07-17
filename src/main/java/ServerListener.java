import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by peter on 17-7-2017.
 */
public class ServerListener extends Thread {
    private Socket socket = null;

    public ServerListener(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }

    public void run() {
        try (
                PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String inputLine, outputLine;

            while ((inputLine = serverIn.readLine()) != null) {
                outputLine = inputLine;
                serverOut.println(outputLine);
                if (outputLine.equals("close"))
                    break;
            }
            socket.close();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
