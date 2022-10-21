package org.howmuch;

import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class DataBaseManager{

    public static String LOCAL_DATAFOLDER = "src/main/resources/data";
    public static String LOCAL_CSV_FOLDER = "src/main/resources/data/csvs";
    public static String LOCAL_IMG_FOLDER = "src/main/resources/data/images";
    public static String USERDATA_FILEPATH = "src/main/resources/data/user_details.csv";
    public static String MONGO_DATABASE_NAME = "HowMuch";
    public static int MONGO_PORT_NO = 27017;
    public static String MONGO_HOST = "localhost";
    static String currentUsername = "guest";
    static int USER_INDEX = -1;
    static String currentPassword = "guest";
    static int currentScore = 0;
    MongoDatabase database;

    DataBaseManager() {
        System.out.println("Database manager called");

        // Delete the files in the data directory // display them for now
//        clearLocalDatabase();

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

    /**
     * Brutally Clear the images and csv in the local Database and start fresh with only files.
     * **/
    public static void clearLocalDatabase() {
        try {
            // Delete all pre existing images
            File data_deleter = new File(LOCAL_IMG_FOLDER);
            listFilesForFolder(data_deleter);
            for (File subfile : Objects.requireNonNull(data_deleter.listFiles())) {
                if (subfile.isDirectory()) {
                    for (File f :
                            Objects.requireNonNull(subfile.listFiles())) {
                        f.delete();
                    }
                }
            }
            data_deleter = new File(LOCAL_CSV_FOLDER);
            listFilesForFolder(data_deleter);
            for (File subfile : Objects.requireNonNull(data_deleter.listFiles())) {
                subfile.delete();
            }
            File createfiles = new File(LOCAL_CSV_FOLDER + "/fashion.csv");
            createfiles.createNewFile();
            createfiles = new File(LOCAL_CSV_FOLDER + "/technology.csv");
            createfiles.createNewFile();
            createfiles = new File(LOCAL_CSV_FOLDER + "/household.csv");
            createfiles.createNewFile();
            createfiles = new File(LOCAL_CSV_FOLDER + "/miscellaneous.csv");
            createfiles.createNewFile();
        } catch (Exception e) {
            System.out.println("Some io excepition occured");
        }
    }

    public static void listFilesForFolder(final File folder) {
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

    public static void addNewUser() {
        System.out.println("gonna add new user");
        File userDatafile = new File(USERDATA_FILEPATH);

        // append the new user to the login file. 
        try (FileWriter userDataFileWriter = new FileWriter(userDatafile, true)) {

            // create CSVWriter object filewriter object as parameter
            try (CSVWriter writer = new CSVWriter(userDataFileWriter,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END)) {

                String[] data = {currentUsername, currentPassword, String.valueOf(currentScore)};
                writer.writeNext(data);
                System.out.println("added new user");
            }

        } catch (IOException e) {
            System.out.println("Cant open user data file. ");
        }
    }

    public static void updateCSV(String fileToUpdate, String replace,
                                 int row, int col) throws IOException {

        File inputFile = new File(fileToUpdate);

// Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
        List<String[]> csvBody = reader.readAll();
// get CSV row column  and replace with by using row and column
        csvBody.get(row)[col] = replace;
        reader.close();

// Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }

    public static boolean doesUsernameExist(String username) {
        File inputFile = new File(USERDATA_FILEPATH);
        try (CSVReader reader = new CSVReader(new FileReader(inputFile), ',')) {
            List<String[]> csvBody = reader.readAll();
            for (String[] s :
                    csvBody) {
                if (s[0].equals(username)) {
                    System.out.println("User Already Exists");
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("couldnt create csvreader in username exists checker method. ");
        }
        return false;
    }

    public static boolean doesPasswordMatch(String username, String password) {
        File inputFile = new File(USERDATA_FILEPATH);
        try (CSVReader reader = new CSVReader(new FileReader(inputFile), ',')) {
            List<String[]> csvBody = reader.readAll();
            for (int i = 0; i < csvBody.size(); i++) {
                String[] s = csvBody.get(i);
                if (s[0].equals(username)) {
                    System.out.println("User Found");
                    if (s[1].equals(password)) {
                        System.out.println("Password Matches");
                        USER_INDEX = i;
                        return true;
                    } else return false;
                }
            }
        } catch (IOException e) {
            System.out.println("couldnt create csvreader in password matching method");
        }
        return false;
    }

    public static int getStoredUserScore(String username) {
        File inputFile = new File(USERDATA_FILEPATH);
        try (CSVReader reader = new CSVReader(new FileReader(inputFile), ',')) {
            List<String[]> csvBody = reader.readAll();
            if (USER_INDEX == -2) {
                return currentScore;
            }
            if (USER_INDEX == -1) {
                for (int i = 0; i < csvBody.size(); i++) {
                    String[] s = csvBody.get(i);
                    if (s[0].equals(username)) {
                        USER_INDEX = i;
                    }
                }
            } else {
                return Integer.parseInt(csvBody.get(USER_INDEX)[2]);
            }
        } catch (IOException e) {
            System.out.println("couldnt create csvreader in userscore method");
        }
        return 0;
    }

    public static void updateUserScore() {
        File inputFile = new File(USERDATA_FILEPATH);

        List<String[]> csvBody;
        try (CSVReader reader = new CSVReader(new FileReader(inputFile), ',')) {
            csvBody = reader.readAll();
            csvBody.get(USER_INDEX)[2] = String.valueOf(currentScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',')) {
            writer.writeAll(csvBody);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
