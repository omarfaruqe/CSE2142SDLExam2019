
import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
    public static String getString(){
        String line = Constants.empty;
        try{
            BufferedReader bufferReader = new BufferedReader(
            new InputStreamReader(
                    new FileInputStream(Constants.file)));
            line = bufferReader.readLine();
            bufferReader.close();
        }catch(IOException E){

        }
        return line;
    }
    public static void main(String[] args) {
        if(args.length == 0){
            ArgumentMessage();
        }
        // Check arguments
        else if (args[0].equals(Constants.showAll)) {
            //this will print the list of all students
            System.out.println(Constants.loading);
            String studentList = getString();
            String names[] = studentList.split(Constants.delimator);
            for (String name : names) {  
                System.out.println(name);
            }
            System.out.println(Constants.loaded);
        } else if (args[0].equals(Constants.random)) {
            //this will print a random student
            System.out.println(Constants.loading);
            String names[] = getString().split(Constants.delimator);
            System.out.println(names[new Random().nextInt(4)]);
            System.out.println(Constants.loaded);
        } else if (args[0].contains(Constants.plus)) {
            //this will add a student in the list
            System.out.println(Constants.loading);
            try {
                BufferedWriter bufferWriter = new BufferedWriter(
                        new FileWriter(Constants.file, true));
                bufferWriter.write(Constants.delimator + args[0].substring(1) + Constants.update + new SimpleDateFormat(Constants.dateFormat).format(new Date()));
                bufferWriter.close();
            } catch (Exception e) {
            }
            System.out.println(Constants.loaded);
        } else if (args[0].contains(Constants.questionMark)) {
            //search for a student in the list 
            System.out.println(Constants.loading);
            try {
                String studentList = getString();
                String names[] = studentList.split(Constants.delimator);
                String name = args[0].substring(1);
                for (int idx = 0; idx < names.length; idx++) {
                    if (names[idx].equals(name)) {
                        System.out.println(Constants.found);
                        break;
                    }
                }
            } catch (Exception e) {
            }
            System.out.println(Constants.loaded);
        } else if (args[0].contains(Constants.count)) {
            System.out.println(Constants.loading);
            try {
                String line = getString();  
                int count = 1;
                for (Character c : line.toCharArray()) {
                    if (c == Constants.space) {
                        count++; 
                    }
                }
                System.out.println(count + Constants.wordsFound + line.length());
            } catch (Exception e) {
            }
            System.out.println(Constants.loaded);
        }else{
            ArgumentMessage();
        }
    }

    private static void ArgumentMessage() {
        //show possible options
        System.out.println(Constants.argumentError);
        System.out.println(Constants.list);
        System.out.println(Constants.a);
        System.out.println(Constants.r);
        System.out.println(Constants.addX);
        System.out.println(Constants.askX);
    }
}
