package edu.au.cpsc.module4;

import java.io.*;

public class AirlineDatabaseIO {

    public static final File DEFAULT_FILE = new File("scheduledflights.dat");

    public static void save(AirlineDatabase ad, OutputStream strm) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(strm);
        oos.writeObject(ad);
    }

    public static AirlineDatabase load(InputStream strm) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(strm);
        return (AirlineDatabase) ois.readObject();
    }
}
