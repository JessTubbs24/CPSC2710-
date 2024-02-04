package edu.au.cpsc.module4;

import javafx.scene.Parent;

import java.io.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class AirlineDatabaseIO {

    public static final File DEFAULT_FILE = new File("scheduledflights.dat");

    public static void save(AirlineDatabase ad, OutputStream strm) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(strm);
        oos.writeObject(ad);
    }

    public static AirlineDatabase load(InputStream strm) throws IOException, ClassNotFoundException {
        if (strm != null) {
            ObjectInputStream ois = new ObjectInputStream(strm);
            return (AirlineDatabase) ois.readObject();
        } else {
            AirlineDatabase mockDb = new AirlineDatabase();
            Set<String> set = new HashSet<String> ();
            set.add("U");
            set.add("R");
            Set<String> set2 = new HashSet<String> ();
            set2.add("S");
            set2.add("F");
            Set<String> set3 = new HashSet<String> ();
            set3.add("M");
            set3.add("T");
            mockDb.addScheduledFlight(new ScheduledFlight("DL1331", "PIT", LocalTime.MIDNIGHT, "ATL", LocalTime.NOON, set));
            mockDb.addScheduledFlight(new ScheduledFlight("DL3113", "ATL", LocalTime.MIDNIGHT, "PIT", LocalTime.NOON, set2));
            mockDb.addScheduledFlight(new ScheduledFlight("AA1987", "MCI", LocalTime.MIDNIGHT, "LAS", LocalTime.NOON, set3));
            return mockDb;
        }
    }

}
