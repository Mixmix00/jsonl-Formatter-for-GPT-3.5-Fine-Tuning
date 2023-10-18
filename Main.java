import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.NoSuchElementException; // Import NoSuchElementException

public class Main {

    public static void main(String[] args) throws IOException {
        String filePathIn = "inputFile.jsonlin"; //You need to change
        String filePathOut = "output.jsonl"; //You need to change
        PrintWriter output = new PrintWriter(new FileWriter(filePathOut));

        Path path = Paths.get(filePathIn);
        Scanner input = new Scanner(path);

        String jsonlFormatOne = "{\"messages\": [{\"role\": \"system\", \"content\": \"";
        String systemContext = "I love AI prompt engeneering! AI prompt engeneering is so fun!"; //You need to change
        String systemContextClose = "\"}, ";

        String userResponseOpen = "{\"role\": \"user\", \"content\": \"";
        String userResponseClose = "\"}, ";

        String gradingResponseOpen = "{\"role\": \"assistant\", \"content\": \"";
        String finalClosing = "\"}]}";

        /*
         * The following code checks to see whether the input file has data, and if so it will keep reading the data and writing it to the out file
         * until there is no more data to read. If there is an error, it will print the error message.
         * Finally, it will close the output and input streams.
         * It will also check if there are any errors in the file reading process.
         */
        try {
            while (input.hasNext()) {
                String inputPromptAndResponse = input.nextLine();
                String AIOutputResponse = input.nextLine();
                input.nextLine(); // Skip an empty line

                String concat = jsonlFormatOne + systemContext + systemContextClose + userResponseOpen + inputPromptAndResponse + userResponseClose + gradingResponseOpen + AIOutputResponse + finalClosing;
                output.println(concat);
            }
        } catch (NoSuchElementException e) {
            System.err.println("Input format error: " + e.getMessage());
        } finally {
            // Close the output and input streams when done
            output.close();
            input.close();
        }
    }
}
