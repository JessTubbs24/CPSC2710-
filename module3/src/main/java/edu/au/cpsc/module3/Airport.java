package edu.au.cpsc.module3;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;


    public class Airport {

        private String ident;
        private String type;
        private String name;
        private Integer elevationFt;
        private String continent;
        private String isoCountry;
        private String isoRegion;
        private String municipality;
        private String gpsCode;
        private String iataCode;
        private String localCode;
        private String coordinates;
        private String latitude;
        private String longitude;

        public String getIdent() {
            return ident;
        }

        public void setIdent(String ident) {
            this.ident = ident;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getElevationFt() {
            return elevationFt;
        }

        public void setElevationFt(Integer elevationFt) {
            this.elevationFt = elevationFt;
        }

        public String getContinent() {
            return continent;
        }

        public void setContinent(String continent) {
            this.continent = continent;
        }

        public String getIsoCountry() {
            return isoCountry;
        }

        public void setIsoCountry(String isoCountry) {
            this.isoCountry = isoCountry;
        }

        public String getIsoRegion() {
            return isoRegion;
        }

        public void setIsoRegion(String isoRegion) {
            this.isoRegion = isoRegion;
        }

        public String getMunicipality() {
            return municipality;
        }

        public void setMunicipality(String municipality) {
            this.municipality = municipality;
        }

        public String getGpsCode() {
            return gpsCode;
        }

        public void setGpsCode(String gpsCode) {
            this.gpsCode = gpsCode;
        }

        public String getIataCode() {
            return iataCode;
        }

        public void setIataCode(String iataCode) {
            this.iataCode = iataCode;
        }

        public String getLocalCode() {
            return localCode;
        }

        public void setLocalCode(String localCode) {
            this.localCode = localCode;
        }

        public String getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(String coordinates) {
            this.coordinates = coordinates;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }



        public static <CSVReader> List<Airport> readAll() throws IOException {
            List<Airport> airports = new ArrayList<>();


            try (com.opencsv.CSVReader csvReader = new CSVReaderBuilder(new FileReader("./airport-codes.csv")).build()) {
                String[] headers = csvReader.readNext(); // Read column headers

                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    Airport airport = new Airport();

                    for (int i = 0; i < headers.length; i++) {
                        switch (headers[i]) {
                            case "ident":
                                airport.setIdent(line[i]);
                                break;
                            case "type":
                                airport.setType(line[i]);
                                break;
                            case "name":
                                airport.setName(line[i]);
                                break;
                            case "elevation_ft":
                                airport.setElevationFt(line[i].isEmpty() ? null : Integer.parseInt(line[i]));
                                break;
                            case "continent":
                                airport.setContinent(line[i]);
                                break;
                            case "iso_country":
                                airport.setIsoCountry(line[i]);
                                break;
                            case "iso_region":
                                airport.setIsoRegion(line[i]);
                                break;
                            case "municipality":
                                airport.setMunicipality(line[i]);
                                break;
                            case "gps_code":
                                airport.setGpsCode(line[i]);
                                break;
                            case "iata_code":
                                airport.setIataCode(line[i]);
                                break;
                            case "local_code":
                                airport.setLocalCode(line[i]);
                                break;
                            case "coordinates":
                                // Split the coordinates into latitude and longitude
                                String latitude = line[i];
                                String longitude = line[i+1];
                                airport.setLatitude(latitude);
                                airport.setLongitude(longitude);
                                break;
                        }
                    }
                    airports.add(airport);
                }
            } catch (CsvValidationException e) {
                System.out.println("it failed to read");
                throw new RuntimeException(e);
            }
            return airports;
        }

    }