package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Final Step... In this final step, we'll use the Strategy Design Pattern...

interface ICypherEncryptDecrypt {
    void encryptDecrypt(String typeEncryption, String userInput, int key);
    void encryptDecryptFile(String typeEncryption, String userInput, String output, int key);
}

interface IShiftingEncryptDecrypt {
    void shiftingEncryptDecrypt(String typeEncryption, String userInput, int key);
    void shiftingEncryptDecryptFile (String typeEncryption, String userInput, String output, int key);
}

// UserMethodInput is used as a screen to hide (ICypherEncryptDecrypt & IShiftingEncryptDecrypt)...
interface UserMethodInput extends ICypherEncryptDecrypt, IShiftingEncryptDecrypt {
}

class EncodingAlgorith implements UserMethodInput {
    private String typeEncryption;
    private String userInput;
    private String output;
    private int key;

    public EncodingAlgorith(String typeEncryption, String userInput, int key){
        this.typeEncryption = typeEncryption;
        this.userInput = userInput;
        this.key = key;
    }

    public EncodingAlgorith(String typeEncryption, String userInput, String output, int key){
        this.typeEncryption = typeEncryption;
        this.userInput = userInput;
        this.output = output;
        this.key = key;
    }
    @Override
    public void encryptDecrypt(String typeEncryption, String userInput, int key) {
        if (typeEncryption.equals("dec")) {
            CypherEncoding.decryptSequence(userInput, key);
        } else {
            CypherEncoding.encryptSequence(userInput, key);
        }
    }

    @Override
    public void encryptDecryptFile(String typeEncryption, String userInput, String output, int key){
        if (typeEncryption.equals("dec")){
            CypherEncoding.decryptSequenceInFile(userInput, output, key);
        } else {
            CypherEncoding.encryptSequenceInFile(userInput, output, key);
        }
    }

    @Override
    public void shiftingEncryptDecrypt(String typeEncryption, String userInput, int key){
        if (typeEncryption.equals("dec")){
            ShiftingEncoding.shiftingDecryptSeq(userInput, key);
        } else {
            ShiftingEncoding.shiftingEncryptSeq(userInput, key);
        }
    }

    @Override
    public void shiftingEncryptDecryptFile (String typeEncryption, String userInput, String output, int key){
        if (typeEncryption.equals("dec")){
            ShiftingEncoding.shiftingDecryptSeqInFile(userInput, output, key);
        } else {
            ShiftingEncoding.shiftingEncryptSeqInFile(userInput, output, key);
        }
    }
}

/**
 * Our Context is EncoderContext which references a encoding/decoding method and.
 */
class EncoderContext {

    private UserMethodInput method;

    public void setEncodingMethod(UserMethodInput method) {
        this.method = method;
    }

    public void encryptDecrypt(String typeEncryption, String userInput, int key){
        this.method.encryptDecrypt(typeEncryption, userInput, key);
    }

    public void encryptDecryptFile(String typeEncryption, String userInput, String output, int key) {
        this.method.encryptDecryptFile(typeEncryption, userInput, output, key);
    }

    public void shiftingEncryptDecrypt(String typeEncryption, String userInput, int key) {
        this.method.shiftingEncryptDecrypt(typeEncryption, userInput, key);
    }

    public void shiftingEncryptDecryptFile (String typeEncryption, String userInput, String output, int key) {
        this.method.shiftingEncryptDecryptFile(typeEncryption, userInput, output, key);
    }
}

class MainEncode {
    public static void main(String[] args) {
        // Example ::: EncryptDecryptFinalStep.userChoice(new String[]{"java", "Main", "-mode", "dec", "-in", "./myFiles/road_to_treasure.txt", "-out", "./myFiles/protected.txt", "-key", "5", "-alg", "shift"});
        EncryptDecryptFinalStep.userChoice(args);
    }
}

class EncryptDecryptFinalStep {
    private static EncoderContext userEncodingChoice;

    public static void userChoice(String[] array) {
        String mode = "";
        String data = "";
        String in = "";
        String out = "";
        String alg = "";
        int key = 0;

        for (int i = 0; i < array.length; i++) { // Determiner le -mode...
            if (array[i].equals("-mode")) {
                mode = array[i + 1];
            } else if (array[i].equals("-key")) {
                key = Integer.parseInt(array[i + 1]);
            } else if (array[i].equals("-data")) {
                data = array[i + 1];
            } else if (array[i].equals("-in")) {
                in = array[i + 1];
            } else if (array[i].equals("-out")) {
                out = array[i + 1];
            } else if (array[i].equals("-alg")) {
                alg = array[i + 1];
            }
        }

        boolean encodeCondition = in.isEmpty() || (!data.isEmpty() && !in.isEmpty());

        if (alg.equals("unicode")) {
            if (encodeCondition) {  //if <in> is empty, we'll use the default encryption mode...
                userEncodingChoice.setEncodingMethod(new EncodingAlgorith(mode, data, key));
                userEncodingChoice.encryptDecrypt(mode, data, key);
            } else {
                userEncodingChoice.setEncodingMethod(new EncodingAlgorith(mode, in, out, key));
                userEncodingChoice.encryptDecryptFile(mode, in, out, key);
            }
        } else if (alg.equals("shift")) {
            if (encodeCondition) {  //if <in> is empty, we'll use the default encryption mode...
                userEncodingChoice.setEncodingMethod(new EncodingAlgorith(mode, data, key));
                userEncodingChoice.shiftingEncryptDecrypt(mode, data, key);
            } else {
                userEncodingChoice.setEncodingMethod(new EncodingAlgorith(mode, in, out, key));
                userEncodingChoice.shiftingEncryptDecryptFile(mode, in, out, key);
            }
        } else {
            if (encodeCondition) {  // If there is no -alg you should default it to shift.
                userEncodingChoice.setEncodingMethod(new EncodingAlgorith(mode, data, key));
                userEncodingChoice.shiftingEncryptDecrypt(mode, data, key);
            } else {
                userEncodingChoice.setEncodingMethod(new EncodingAlgorith(mode, in, out, key));
                userEncodingChoice.shiftingEncryptDecryptFile(mode, in, out, key);
            }
        }
    }
}

class ShiftingEncoding implements IShiftingEncryptDecrypt{
    @Override
    public void shiftingEncryptDecrypt(String typeEncryption, String userInput, int key){
        if (typeEncryption.equals("dec")){
            ShiftingEncoding.shiftingDecryptSeq(userInput, key);
        } else {
            ShiftingEncoding.shiftingEncryptSeq(userInput, key);
        }
    }

    @Override
    public void shiftingEncryptDecryptFile (String typeEncryption, String userInput, String output, int key){
        if (typeEncryption.equals("dec")){
            ShiftingEncoding.shiftingDecryptSeqInFile(userInput, output, key);
        } else {
            ShiftingEncoding.shiftingEncryptSeqInFile(userInput, output, key);
        }
    }

    public static void shiftingEncryptSeq(String userInput, int key){
        char[] chars = userInput.toCharArray();

        char a = 'a';
        char z = 'z';
        int size = 26;

        for (char item : chars) {
            if (item >= a && item <= z) {
                char shiftItem = (char) (((item - a + key) % size) + a);
                System.out.print(shiftItem);
            } else {
                System.out.print(item);
            }
        }
    }

    public static void shiftingDecryptSeq(String userInput, int key){
        char[] chars = userInput.toCharArray();

        char a = 'a';
        char z = 'z';
        int size = 26;

        for (char item : chars) {
            if (item >= a && item <= z) {
                char shiftItem = (char) (((item - a - key) % size) + a);
                System.out.print(shiftItem);
            } else {
                System.out.print(item);
            }
        }
    }

    public static void shiftingEncryptSeqInFile(String userInput, String outputFile, int key){
        String output = "";

        String fileValue;
        fileValue = FormatConstructor.changeFileValueIntoString(userInput);

        char[] chars = fileValue.toCharArray();

        char a = 'a';
        char z = 'z';
        int size = 26;

        for (char item : chars) {
            if (item >= a && item <= z) {
                char shiftItem = (char) (((item - a + key) % size) + a);
                output += shiftItem;
            } else {
                output += item;
            }
        }
        FormatConstructor.outputToCommandPromptOrNot(outputFile, output);
    }

    public static void shiftingDecryptSeqInFile(String userInput, String outputFile, int key){
        String output = "";

        String fileValue;
        fileValue = FormatConstructor.changeFileValueIntoString(userInput);

        char[] chars = fileValue.toCharArray();

        char a = 'a';
        char z = 'z';
        int size = 26;

        for (char item : chars) {
            if (item >= a && item <= z) {
                char shiftItem = (char) (((item - a - key) % size) + a);
                output += shiftItem;
            } else {
                output += item;
            }
        }
        FormatConstructor.outputToCommandPromptOrNot(outputFile, output);
    }
}

class CypherEncoding implements ICypherEncryptDecrypt{
    @Override
    public void encryptDecrypt(String typeEncryption, String userInput, int key) {
        if (typeEncryption.equals("dec")) {
            CypherEncoding.decryptSequence(userInput, key);
        } else {
            CypherEncoding.encryptSequence(userInput, key);
        }
    }

    @Override
    public void encryptDecryptFile(String typeEncryption, String userInput, String output, int key){
        if (typeEncryption.equals("dec")){
            CypherEncoding.decryptSequenceInFile(userInput, output, key);
        } else {
            CypherEncoding.encryptSequenceInFile(userInput, output, key);
        }
    }

    public static void encryptSequence(String userInput, int key) {
        String output = "";
        int codePoint = 0;
        char singleChar;
        for (int i = 0; i < userInput.length(); i++) {
            codePoint = Character.codePointAt(userInput, i);

            codePoint = codePoint + key; // Update the codePoint depending on the key value.
            singleChar = (char) codePoint;
            output += singleChar;
        }
        FormatConstructor.toString(output);
    }

    public static void decryptSequence(String userInput, int key) {
        String output = "";
        int codePoint = 0;
        char singleChar;
        for (int i = 0; i < userInput.length(); i++) {
            codePoint = Character.codePointAt(userInput, i);

            codePoint = codePoint - key; // Update the codePoint depending on the key value.
            singleChar = (char) codePoint;
            output += singleChar;
        }
        FormatConstructor.toString(output);
    }

    public static void encryptSequenceInFile(String userInput, String outputFile, int key) {
        String output = "";
        int codePoint = 0;
        char singleChar;

        String fileValue;
        fileValue = FormatConstructor.changeFileValueIntoString(userInput);

        for (int i = 0; i < fileValue.length(); i++) {
            codePoint = Character.codePointAt(fileValue, i);
            codePoint = codePoint + key; // Update the codePoint depending on the key value.
            singleChar = (char) codePoint;
            output += singleChar;
        }
        FormatConstructor.outputToCommandPromptOrNot(outputFile, output);
    }

    public static void decryptSequenceInFile(String userInput, String outputFile, int key) {
        String output = "";
        int codePoint = 0;
        char singleChar;

        String fileValue;
        fileValue = FormatConstructor.changeFileValueIntoString(userInput);

        for (int i = 0; i < fileValue.length(); i++) {
            codePoint = Character.codePointAt(fileValue, i);
            codePoint = codePoint - key; // Update the codePoint depending on the key value.
            singleChar = (char) codePoint;
            output += singleChar;
        }
        FormatConstructor.outputToCommandPromptOrNot(outputFile, output);
    }
}

class FormatConstructor {
    public static String changeFileValueIntoString(String userInput){
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

    public static void toString(String output){
        System.out.println(output);
    }

    public static void outputToCommandPromptOrNot(String outputFILE, String output){
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