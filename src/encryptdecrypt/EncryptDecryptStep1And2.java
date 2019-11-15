package encryptdecrypt;

import java.util.Scanner;

// Step 1 & 2 ...
public class EncryptDecryptStep1And2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine(); //"welcome to hyperskill";
        int userKey = scan.nextInt(); //5;
        reverseWord(userInput);
        knowledgeIsKey(userInput, userKey);
    }

    private static void knowledgeIsKey(String word, int key){
        String englishAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String finalWord = "";
        int tempKey = 0;
        int delimiteur = englishAlphabet.length() - key;

        for (int i = 0; i < word.length(); i++) {

            if(word.charAt(i) == ' '){ // Rajouter un espace entre chaque mot lorsqu'on en trouve.
                finalWord = finalWord + " ";
            }

            for (int j = 0; j < englishAlphabet.length(); j++) {
                boolean sameChar = word.charAt(i) == englishAlphabet.charAt(j);
                if(sameChar && ((int)(word.charAt(i)) > englishAlphabet.length()/2+97) && (key > englishAlphabet.length()/2)){
                    tempKey = j - (englishAlphabet.length() - key);
                    finalWord = finalWord + englishAlphabet.charAt(tempKey);
                    break; // Si notre condition est vérifiée, pas besoin de continuer a boucler sur l'alphabet.
                           // On itère au prochain élément de la 1ere boucle.
                } else if(sameChar && ((int)(word.charAt(i)) <= englishAlphabet.length()/2+97) && (key > englishAlphabet.length()/2)){
                    if( (j+key) < englishAlphabet.length()){
                        finalWord = finalWord + englishAlphabet.charAt(j+key);
                    } else {
                        tempKey = j - (englishAlphabet.length() - key);
                        finalWord = finalWord + englishAlphabet.charAt(tempKey);
                    }
                    break;
                } else if(sameChar && ((int)(word.charAt(i)) > englishAlphabet.length()/2+97) && (key < englishAlphabet.length()/2)){
                    if( (j+key) < englishAlphabet.length()){
                        finalWord = finalWord + englishAlphabet.charAt(j+key);
                    } else {
                        tempKey = j - (englishAlphabet.length() - key);
                        finalWord = finalWord + englishAlphabet.charAt(tempKey);
                    }
                    break;
                } else if(sameChar && ((int)(word.charAt(i)) <= englishAlphabet.length()/2+97) && (key < englishAlphabet.length()/2)){
                    finalWord = finalWord + englishAlphabet.charAt(j+key);
                    break;
                }
            }
        }
        System.out.println(finalWord);
    }

    private static void reverseWord(String word){

        String finalWord = "";

        for (int i = 0; i < word.length(); i++) {
           if(word.charAt(i) == 'a'){
               finalWord = finalWord + "z";
           } else if (word.charAt(i) == 'b'){
               finalWord = finalWord + "y";
           } else if (word.charAt(i) == 'c'){
               finalWord = finalWord + "x";
           } else if (word.charAt(i) == 'd'){
               finalWord = finalWord + "w";
           } else if (word.charAt(i) == 'e'){
               finalWord = finalWord + "v";
           } else if (word.charAt(i) == 'f'){
               finalWord = finalWord + "u";
           } else if (word.charAt(i) == 'g'){
               finalWord = finalWord + "t";
           } else if (word.charAt(i) == 'h'){
               finalWord = finalWord + "s";
           } else if (word.charAt(i) == 'i'){
               finalWord = finalWord + "r";
           } else if (word.charAt(i) == 'j'){
               finalWord = finalWord + "q";
           } else if (word.charAt(i) == 'k'){
               finalWord = finalWord + "p";
           } else if (word.charAt(i) == 'l'){
               finalWord = finalWord + "o";
           } else if (word.charAt(i) == 'm'){
               finalWord = finalWord + "n";
           } else if (word.charAt(i) == 'n'){
               finalWord = finalWord + "m";
           } else if (word.charAt(i) == 'o'){
               finalWord = finalWord + "l";
           } else if (word.charAt(i) == 'p'){
               finalWord = finalWord + "k";
           } else if (word.charAt(i) == 'q'){
               finalWord = finalWord + "j";
           } else if (word.charAt(i) == 'r'){
               finalWord = finalWord + "i";
           } else if (word.charAt(i) == 's'){
               finalWord = finalWord + "h";
           } else if (word.charAt(i) == 't'){
               finalWord = finalWord + "g";
           } else if (word.charAt(i) == 'u'){
               finalWord = finalWord + "f";
           } else if (word.charAt(i) == 'v'){
               finalWord = finalWord + "e";
           } else if (word.charAt(i) == 'w'){
               finalWord = finalWord + "d";
           } else if (word.charAt(i) == 'x'){
               finalWord = finalWord + "c";
           } else if (word.charAt(i) == 'y'){
               finalWord = finalWord + "b";
           } else if (word.charAt(i) == 'z'){
               finalWord = finalWord + "a";
           } else if (word.charAt(i) == '!'){
               finalWord = finalWord + "!";
           } else{
               finalWord = finalWord + " ";
           }
        }
        System.out.println(finalWord);
    }
}
