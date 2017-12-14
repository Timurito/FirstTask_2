package filetools;


import utils.MyCustomIOException;

import java.io.*;

public class FileHandler {
    public static StringBuilder readFile(String fileName) throws MyCustomIOException {
        StringBuilder res = new StringBuilder();
        String temp;
        try (BufferedReader r = new BufferedReader(new FileReader((fileName)))) {
            while ((temp = r.readLine()) != null) {
                res.append(temp).append("\n");
            }
        } catch (FileNotFoundException e) { // is this catch necessary since we've already checked file existence?
            System.out.println("Input file not found!");
        } catch (IOException e) {
            // log exception
            MyCustomIOException myEx = new MyCustomIOException();
            myEx.initCause(e);
            throw myEx;
        }
        return res;
    }

    public static void writeFile(String fileName, String data, boolean append) throws MyCustomIOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, append))) {
            bw.write(data);
        } catch (IOException e) {
            // log exception
            MyCustomIOException myEx = new MyCustomIOException();
            myEx.initCause(e);
            throw myEx;
        }
    }
}
