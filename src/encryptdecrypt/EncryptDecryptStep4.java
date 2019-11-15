package encryptdecrypt;

// Step 4...
public class EncryptDecryptStep4 {
    public static void main(String[] args) {
        userChoice(args);
        //System.out.println(Arrays.toString(args));
    }

    private static void userChoice(String [] array){
        String mode = "";
        String data = "";
        int key = 0;

        for (int i = 0; i < array.length; i++) { // Determiner le -mode...
            if(array[i].equals("-mode")){
                mode = array[i+1];
            } else if (array[i].equals("-key")){
                key = Integer.parseInt(array[i+1]);
            } else if (array[i].equals("-data")){
                data = array[i+1];
            }
        }
        encryptDecrypt(mode, data, key);
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

    private static void toString(String output){
        System.out.println(output);
    }
}