/**
 * Created by Optimus on 13.04.2014.
 */

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
	/*
	 * This will wait for a connection to be made to this socket.
         */
            soc = ser.accept();
            InputStream inputStream = soc.getInputStream();
            ObjectInput input = new ObjectInputStream(inputStream);
            str = (String) input.readObject();
            System.out.println(str);
            if (str != null){
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


            // print out what we just received


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error during serialization");
            System.exit(1);
        }
    }
}
