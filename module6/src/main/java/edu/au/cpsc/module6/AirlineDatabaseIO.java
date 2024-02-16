package edu.au.cpsc.module6;

import javafx.scene.Parent;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AirlineDatabaseIO {

    public static final File DEFAULT_FILE = new File("scheduledflights.dat");

    public static void save(AirlineDatabase ad, OutputStream stream) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(new ArrayList<ScheduledFlight>(ad.getScheduledFlights()));
        oos.close();
    }

    public static AirlineDatabase load(InputStream stream) throws IOException, ClassNotFoundException {
        AirlineDatabase emptyDb;
        if (stream != null) {
            ObjectInputStream ois = new ObjectInputStream(stream);
            emptyDb = new AirlineDatabase();
            ArrayList<ScheduledFlight> list = (ArrayList<ScheduledFlight>) ois.readObject();
            for (ScheduledFlight scheduledFlight : list) {
                emptyDb.addScheduledFlight(scheduledFlight);
            }
            ois.close();
            System.out.println("success");

        } else {
            System.out.println("failed");
            emptyDb = new AirlineDatabase();
        }
        return emptyDb;

    }

}
