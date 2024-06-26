import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;

import static java.lang.System.out;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Server {
    public static void main(String[] args) throws Exception {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        String outMessage= "Hello ";
        out.println("Creating a socket for talking");
        ServerSocket mySocket = new ServerSocket(3256);
        Socket actualSocket = mySocket.accept();
        ObjectOutputStream objOut = new ObjectOutputStream(out);

        DopeDatta queue = new DopeDatta();
        DataReader myDataReader = new DataReader(actualSocket, queue);
        ProgramLogicDoer myProgramLogicDoer = new ProgramLogicDoer(queue);

        Thread dataReadThread = new Thread(myDataReader);
        Thread programLogicThread = new Thread(myProgramLogicDoer);

        dataReadThread.start();
        programLogicThread.start();

        objOut.writeObject("Connected");



        }
    }


