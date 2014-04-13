
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeModule {
    public static void main(String args[]) {

        ServerSocket ser = null;
        Socket soc = null;
        String str = null;
        String etalon = "time";
        Date now = null;

        try {
            ser = new ServerSocket(8020);
            soc = ser.accept();
            InputStream inputStream = soc.getInputStream();
            ObjectInput input = new ObjectInputStream(inputStream);
            str = (String) input.readObject();
            if (str.equals(etalon)){
                now = new Date();
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                String s = formatter.format(now);
                System.out.println("Current time: " + s);
                OutputStream outputStream = soc.getOutputStream();
                ObjectOutput output = new ObjectOutputStream(outputStream);
                output.writeObject("Current time: " + s);
                output.flush();
                output.close();
            }
            input.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error during serialization");
            System.exit(1);
        }
    }
}
