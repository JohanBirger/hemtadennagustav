
    import java.util.Scanner;


    /**
     *
     */
    public class delete {

        /**
         * @param args not used
         */
        public static void main(String[] args) {
            run();
        }

        /**
         *
         */
        public static void run() {

            Scanner sc = new Scanner(System.in);
            String user_input = "";
            Boolean quit = false;

            VocabularyArray vocabularyArray = new VocabularyArray();
            String[] swe_words = vocabularyArray.getSwe_words();
            String[] eng_words = vocabularyArray.getEng_words();


            final String ANSI_GREEN = "\u001B[32m";
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_RED = "\u001B[31m";

            while (!quit) {
                introduction();
                int j = 0;
                int index = 0;
                int score = 0;
                int eng_word_length = eng_words[j].length();
                double CloseToCorrectMargin = 0.75;

                //ahfhfhf
                for (j = 0; j == index; j++) {
                    System.out.println(swe_words[j] + ":");
                    user_input = sc.next().trim();

                    if (user_input.equalsIgnoreCase("Q")) {
                        System.out.println("Du svarade på totalt " + index + " glosor och hade " + score + " rätt. Välkommen åter!");
                        quit = true;}

                        else if (user_input.equalsIgnoreCase(eng_words[j])) {
                            index++;
                            score++;

                            if (index == eng_word_length) {
                                System.out.println(ANSI_GREEN + "Korrekt!" + ANSI_RESET + score + " rätt av " + index + " ord." + "\nDu svarade på totalt " + index + " och hade " + score + " rätt. Välkommen åter!");
                                quit = true;
                            } else {
                                System.out.println(ANSI_GREEN + "Korrekt!" + ANSI_RESET + score + " rätt av " + index + " ord.");

                            }
                        }
                        //blbl
                        else if (user_input.compareToIgnoreCase(eng_words[j].substring(0, eng_word_length)) >= (CloseToCorrectMargin * eng_word_length)) {
                            index++;
                            if (index == eng_word_length) {
                                System.out.println("Nästan rätt. Korrekt svar är " + eng_words);
                                System.out.println("Du svarade på totalt " + index + " glosor och hade " + score + " rätt. Välkommen åter!");
                            } else {
                                System.out.println("Nästan rätt. Korrekt svar är " + eng_words);
                            }


                        } else {
                            index++;
                            if (index == eng_word_length) {
                                System.out.println(ANSI_RED + "Fel svar!" + ANSI_RESET + " Korrekt svar är " + eng_words);
                                System.out.println("Du svarade på totalt " + index + " och hade " + score + "rätt. Välkomen åter!");
                            } else {
                                System.out.println("Fel svar!. Rätt svar är " + eng_words + ".");


                            }

                        }
                    }
                }
            }

        public static void introduction() {
            System.out.println("** GLOSÖVNING - ENGELSKA **");
            System.out.println("Skriv det engelska ordet. Avsluta programmet genom att skriva Q");
        }


    }

       


    class VocabularyArray {

        private String[] swe_words = {"Bil","Bok","Mus","Dator","Fönster","Kopp","Penna","Växt","Gitarr","Glasögon"};
        private String[] eng_words = {"Car","Book","Mouse","Computer","Window","Cup","Pen","Plant","Guitar","Glasses"};

        public String[] getSwe_words() {
            return swe_words.clone();
        }

        public String[] getEng_words() {
            return eng_words.clone();
        }
    }




