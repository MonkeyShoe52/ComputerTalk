import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import static java.lang.System.out;


public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Connecting to the server");
        Socket newSocket = new Socket("10.37.153.179",3256);
        Socket actualSocket = newSocket;

        OutputStream out = newSocket.getOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(out);


        DopeDatta queue = new DopeDatta();
        DataReader myDataReader = new DataReader(actualSocket, queue);
        ProgramLogicDoer myProgramLogicDoer = new ProgramLogicDoer(queue);

        Thread dataReadThread = new Thread(myDataReader);
        Thread programLogicThread = new Thread(myProgramLogicDoer);

        dataReadThread.start();
        programLogicThread.start();

        objOut.writeObject("What we doing");
        objOut.writeObject("Yo?");

        System.out.println("Done sending messages");
    }
}
