package file.encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Step 5...
class EncryptDecryptStep5 {
    public static void main(String[] args) {
        userChoice(new String[]{"java", "Main", "-mode", "enc", "-in", "./myFiles/road_to_treasure.txt", "-out", "./myFiles/protected.txt", "-key", "5"});
        //userChoice(args);
    }

    private static void userChoice(String [] array){
        String mode = "";
        String data = "";
        String in = "";
        String out = "";
        int key = 0;

        for (int i = 0; i < array.length; i++) { // Determiner le -mode...
            if(array[i].equals("-mode")){
                mode = array[i+1];
            } else if (array[i].equals("-key")){
                key = Integer.parseInt(array[i+1]);
            } else if (array[i].equals("-data")){
                data = array[i+1];
            } else if (array[i].equals("-in")){
                in = array[i+1];
            } else if (array[i].equals("-out")){
                out = array[i+1];
            }
        }
        if(in.isEmpty() || (!data.isEmpty() && !in.isEmpty())){  //if <in> is empty, we'll use the default encryption mode...
            encryptDecrypt(mode, data, key);
        }
        else{
            encryptDecryptFile(mode, in, out, key);
        }
    }

    private static void encryptDecrypt(String typeEncryption, String userInput, int key){
        if (typeEncryption.equals("dec")){
            decryptSequence(userInput, key);
        } else {
            encryptSequence(userInput, key);
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
            output += singleChar;
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
            output += singleChar;
        }
        toString(output);
    }

    // The section below will handle the encryption when the input is a file.
    private static void encryptDecryptFile(String typeEncryption, String userInput, String output, int key){
        if (typeEncryption.equals("dec")){
            decryptSequenceInFile(userInput, output, key);
        } else {
            encryptSequenceInFile(userInput, output, key);
        }
    }

    private static void encryptSequenceInFile(String userInput, String outputFile, int key){
        String output = "";
        int codePoint = 0;
        char singleChar;

        String fileValue;
        fileValue = changeFileValueIntoString(userInput);

        for (int i = 0; i < fileValue.length(); i++) {
            codePoint = Character.codePointAt(fileValue, i);
            codePoint = codePoint+key; // Update the codePoint depending on the key value.
            singleChar = (char) codePoint;
            output += singleChar;
        }
        outputToCommandPromptOrNot(outputFile, output);
    }

    private static void decryptSequenceInFile(String userInput, String outputFile, int key){
        String output = "";
        int codePoint = 0;
        char singleChar;

        String fileValue;
        fileValue = changeFileValueIntoString(userInput);

        for (int i = 0; i < fileValue.length(); i++) {
            codePoint = Character.codePointAt(fileValue, i);
            codePoint = codePoint-key; // Update the codePoint depending on the key value.
            singleChar = (char) codePoint;
            output += singleChar;
        }
        outputToCommandPromptOrNot(outputFile, output);
    }

    private static String changeFileValueIntoString(String userInput){
        String currentValue = "";
        File file = new File(userInput);

        try (Scanner scanner = new Scanner(file)) {  // it throws FileNotFoundException (checked)
            while (scanner.hasNext()) {
                currentValue = currentValue + scanner.nextLine() + " ";
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + userInput);
        }
        return currentValue;
    }

    private static void toString(String output){
        System.out.println(output);
    }

    private static void outputToCommandPromptOrNot(String outputFILE, String output){
        if(outputFILE.isEmpty()){
            toString(output);
        } else{
            try (FileWriter finalOutput = new FileWriter(outputFILE)) {
                finalOutput.write(output);
            } catch (IOException e) {
                System.out.printf("An exception occurs %s", e.getMessage());
            }
        }
    }
}