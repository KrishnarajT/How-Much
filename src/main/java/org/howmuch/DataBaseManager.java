package org.howmuch;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.opencsv.CSVWriter;
import org.bson.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;


public class DataBaseManager {

    public static String LOCAL_DATAFOLDER = "src/main/resources/data";
    public static String USERDATA_FILEPATH = "src/main/resources/data/user_details.csv";
    public static String MONGO_DATABASE_NAME = "HowMuch";
    public static int MONGO_PORT_NO = 27017;
    public static String MONGO_HOST = "localhost";
    static String currentUsername = "guest";
    static String currentPassword = "guest";
    static int currentScore = 0;
    MongoDatabase database;

    DataBaseManager() {
        System.out.println("Database manager called");

        // Delete the files in the data directory // display them for now
        clearLocalDatabase();

        // Establishing connection with mongo
        establishConnectionWithMongo();

    }

    void establishConnectionWithMongo() {
//        Creating a MongoDB client
        try {
            MongoClient mongo = new MongoClient(MONGO_HOST, MONGO_PORT_NO);
            //Connecting to the database
            database = mongo.getDatabase(MONGO_DATABASE_NAME);
        } catch (Exception e) {
            System.out.println("Couldnt establish connection due to some reason");
        }
    }

    void fetchDataFromMongo() {
//        Creating a collection object
//        MongoCollection<org.bson.Document> collection = database.getCollection("Test-with-Java");
//        //Retrieving the documents
//        FindIterable<org.bson.Document> iterDoc = collection.find();
//        String[] list = new String[7];
//        int i = 0;
//        for (Document document : iterDoc) {
//            System.out.println(document);
//            list[i] = document.values().toString();
//            i++;
//        }
//        for (int j = 0;j< list.length;j++)
//        {
//            System.out.println(list[j]);
//        }
    }

    void clearLocalDatabase() {
        try {

            File data_deleter = new File(LOCAL_DATAFOLDER);
            listFilesForFolder(data_deleter);

        } catch (Exception e) {
            System.out.println("Some io excepition occured");
        }
    }

    public void listFilesForFolder(final File folder) {
        Arrays.stream(folder.listFiles()).forEach(fileEntry -> {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
                System.out.println(fileEntry.getPath());
            }
        });
    }

    public static void writeDataLineByLine(String filePath) {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            // adding header to csv
            String[] header = {"Name", "Class", "Marks"};
            writer.writeNext(header);

            // add data to csv
            String[] data1 = {"Aman", "10", "620"};
            writer.writeNext(data1);
            String[] data2 = {"Suraj", "10", "630"};
            writer.writeNext(data2);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void addUser() {
        System.out.println("gonna add new user");
        File userDatafile = new File(USERDATA_FILEPATH);

        // append the new user to the login file. 
        try (FileWriter userDataFileWriter = new FileWriter(userDatafile, true)) {

            // create CSVWriter object filewriter object as parameter
            try (CSVWriter writer = new CSVWriter(userDataFileWriter)) {

                // adding header to csv only if its empty
                if (userDatafile.length() == 0) {
                    String[] header = {"Username", "Password", "Score"};
                    writer.writeNext(header);
                    System.out.println("added header");
                }

                String[] data = {currentUsername, currentPassword, String.valueOf(currentScore)};
                writer.writeNext(data);
                System.out.println("added header");
            }

        } catch (IOException e) {
            System.out.println("Cant open user data file. ");
        }
    }
}
