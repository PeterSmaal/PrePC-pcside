import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

/**
 * Created by peter on 17-7-2017.
 */
public class ServerStartListening {
    private static final int portNumber = 2017;
    private static String pcName;

    private static String getComputerName()
    {
        Map<String, String> env = System.getenv();
        if (env.containsKey("COMPUTERNAME"))
            return env.get("COMPUTERNAME");
        else
            return "Unknown Computer";
    }

    public ServerStartListening(){
        boolean listening = true;
        System.out.println("Server Started");

        try(ServerSocket serverSocket = new ServerSocket(portNumber)){
            System.out.println("Server Started");
            while (listening) {
                new ServerListener(serverSocket.accept()).start();
            }
        }catch (IOException e) {
        System.err.println("Could not listen on port " + portNumber);
        System.exit(-1);
        }
    }
}
