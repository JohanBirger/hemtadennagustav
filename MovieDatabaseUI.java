package moviedatabase;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;



/**
 * A command line user interface for a movie database.
 */
public class MovieDatabaseUI {
    private Scanner _scanner;
    String filePath= "./database.txt";

    /**
     *
     * Construct a MovieDatabaseUI.
     */
    public MovieDatabaseUI() {

    }
    /**
     * Start the movie database UI.
     */
    public void startUI() throws IOException {
        _scanner = new Scanner(System.in);
        int input;
        boolean quit = false;

        System.out.println("** FILMDATABAS **");

        while(!quit) {
            input = getNumberInput(_scanner, 1, 4, getMainMenu());

            switch(input) {
                case 1: searchTitel(); break;
                case 2: searchReviewScore(); break;
                case 3: addMovie(); break;
                case 4: quit = true;
            }
        }
        //Close scanner to free resources
        _scanner.close();
    }


    /**
     * Get input and translate it to a valid number.
     *
     * @param scanner the Scanner we use to get input
     * @param min the lowest correct number
     * @param max the highest correct number
     * @param message message to user
     * @return the chosen menu number
     */
    private int getNumberInput(Scanner scanner, int min, int max, String message) {
        int input = -1;

        while(input < 0) {
            System.out.println(message);
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            }
            catch(NumberFormatException nfe) {
                input = -1;
            }
            if(input < min || input > max) {
                System.out.println("Ogiltigt värde.");
            }
        }
        return input;
    }

    /**
     * Get search string from user, search title in the movie
     * database and present the search result.
     * @throws IOException
     */


    private void searchTitel() throws IOException {
        System.out.print("Ange sökord: ");
        String title = _scanner.nextLine().trim();
        Scanner sc;
        //call to search movie database based on input

        File file = new File(filePath);

        try{
            sc = new Scanner(file);

            while (sc.hasNextLine()){
                final String lineFromFile=sc.nextLine();
                final String line= lineFromFile.toLowerCase();
                //trying to mimic python indexing
                int endIndex = line.length()-1;
                String theTitle = lineFromFile.substring(0,endIndex);
                String theScore = lineFromFile.substring(endIndex,endIndex+1);
                if(lineFromFile.contains(title.toLowerCase())) {
                    System.out.println("Titel: "+theTitle + "Betyg: "+ theScore+"/5.");
                }
            }
        }catch(Exception e){

            return;
        }

        sc.close();
    }



    /**
     * Get search string from user, search review score in the movie
     * database and present the search result.
     * @throws IOException
     */
    private void searchReviewScore() {
        int review = getNumberInput(_scanner, 1, 5, "Ange minimibetyg (1 - 5): ");
        Scanner sc;
        File file = new File(filePath);

        try{
            sc = new Scanner(file);

            while (sc.hasNextLine()){
                final String lineFromFile=sc.nextLine();
                int endIndex = lineFromFile.length()-1;
                String theTitle = lineFromFile.substring(0,endIndex);
                String theScoreStr = lineFromFile.substring(endIndex,endIndex+1);
                int theScoreInt = Integer.parseInt(theScoreStr);
                if(theScoreInt >=review) {
                    System.out.println("Titel: "+theTitle + "Betyg: "+ theScoreInt+"/5.");
                }


            }

        }catch(Exception e){
            return;

        }
    }


    /**
     * Get information from user on the new movie and add
     * it to the database.
     *
     * @throws IOException
     */
    private void addMovie() throws IOException {
        System.out.print("Titel: ");
        String title = _scanner.nextLine().trim();
        int reviewScore = getNumberInput(_scanner, 1, 5, "Betyg (1 - 5): ");

        Path databasePath = Paths.get(filePath);

        if (!Files.exists(databasePath)) {
            Files.createFile(databasePath);
        }

        List<String> readAllLines = Files.readAllLines(databasePath);

        readAllLines.add(title+" "+reviewScore);

        Files.write(databasePath, readAllLines);

        if(Files.exists(databasePath)) {
            System.out.println("Filmen lagrades.");
        }
    }


    /**
     * Return the main menu text.
     *
     * @return the main menu text
     */
    private String getMainMenu() {
        return  "-------------------\n" +
                "1. Sök på titel\n" +
                "2. Sök på betyg\n" +
                "3. Lägg till film\n" +
                "-------------------\n" +
                "4. Avsluta";
    }






}

