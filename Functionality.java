import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * class containing all functionality of the program,
 * userinterface gets this class and its methods to execute the program.
 *
 *
 */
public class Functionality {
    String outputFilePath= "./moviedatabase.txt";

    /**
     * Method scans filepath for userinput match
     *
     * @param title
     * @throws IOException
     */
    public void searchTitle(String title) throws  IOException {
        Scanner sc;
        //call to search movie database based on input

        File file = new File(outputFilePath);

        try{
            sc = new Scanner(file);

            while (sc.hasNextLine()){
                final String lineFromFile=sc.nextLine();
                final String line= lineFromFile.toLowerCase();
                //dividing the string into title and review score
                int lastIndex = line.length()-1;
                String onlyTitle = lineFromFile.substring(0,lastIndex);
                String onlyScore = lineFromFile.substring(lastIndex,lastIndex+1);
                if(lineFromFile.contains(title.toLowerCase())) {
                    System.out.println("Titel: "+onlyTitle + "Betyg: "+ onlyScore+"/5.");
                }
            }
        }catch(Exception e){
            return;

        }

        sc.close();



    }

    /**
     * Method scans databasefile for movies by given rating. Returns all movies with minimum input rating.
     * @param review
     */
    public void searchReviewScore(int review) {
        Scanner sc;
        File file = new File(outputFilePath);

        try{
            sc = new Scanner(file);

            while (sc.hasNextLine()){
                final String lineFromFile=sc.nextLine();
                //dividing the string into title and review score
                int lastIndex = lineFromFile.length()-1;
                String onlyTitle = lineFromFile.substring(0,lastIndex);
                String stringScore = lineFromFile.substring(lastIndex,lastIndex+1);
                int intScore = Integer.parseInt(stringScore);
                if(intScore >=review) {
                    System.out.println("Titel: "+onlyTitle + "Betyg: "+ intScore+"/5.");
                }

            }

        }catch(Exception e){
            return;

        }
    }

    /**
     * Method checks if a databasefille exists if not
     * it creates one and then proceeds to add the user input to the file.
     *
     * @param title
     * @param reviewScore
     * @throws IOException
     */

    public void addMovie(String title, int reviewScore) throws IOException {
        Path newTextFilePath = Paths.get(outputFilePath);

        if (!Files.exists(newTextFilePath)) {
            Files.createFile(newTextFilePath);
        }

        List<String> allLines = Files.readAllLines(newTextFilePath);

        allLines.add(title+"\t"+reviewScore);

        Files.write(newTextFilePath, allLines);

        if(Files.exists(newTextFilePath)) {
            System.out.println(title + " lades till i databasen med betyget " + reviewScore + ".");
        }
    }
}





        




