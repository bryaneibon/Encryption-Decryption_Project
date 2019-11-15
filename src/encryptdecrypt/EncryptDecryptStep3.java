package encryptdecrypt;

import java.util.Scanner;

// Step 3...
public class EncryptDecryptStep3 {
    public static void main(String[] args) {
        userChoice(args);
    }

    private static void userChoice(String [] array){
        Scanner scan = new Scanner(System.in);
        String mode = scan.nextLine();
        String data = scan.nextLine();
        int key = scan.nextInt();

        encryptDecrypt(mode, data, key);
    }

    private static void encryptDecrypt(String typeEncryption, String userInput, int key){
        if(typeEncryption.equals("enc")){
            encryptSequence(userInput, key);
        } else if (typeEncryption.equals("dec")){
            decryptSequence(userInput, key);
        } else {
            System.out.println("Unkown Encryption Type");
        }
    }

    private static void encryptSequence(String userInput, int key){
        String output = "";
        int codePoint = 0;
        char singleChar;
        for (int i = 0; i < userInput.length(); i++) {
            codePoint = Character.codePointAt(userInput, i);

            codePoint = codePoint+key; // Update the codePoint depending on the key value.
            singleChar = (char) codePoint;
            output = output + singleChar;
        }
        toString(output);
    }

    private static void decryptSequence(String userInput, int key){
        String output = "";
        int codePoint = 0;
        char singleChar;
        for (int i = 0; i < userInput.length(); i++) {
            codePoint = Character.codePointAt(userInput, i);

            codePoint = codePoint-key; // Update the codePoint depending on the key value.
            singleChar = (char) codePoint;
            output = output + singleChar;
        }
        toString(output);
    }

    private static void toString(String output){
        System.out.println(output);
    }
}