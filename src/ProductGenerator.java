import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator
{
    public static void main(String[] args)
    {
        //Declarations
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");
        ArrayList<String> products = new ArrayList<>();
        boolean done = false;
        Scanner in = new Scanner(System.in);

        String ID = "";
        String name ="";
        String description = "";
        String record = "";
        double cost = 0;


        do
        {
            //Goes to SafeInput and checks for non-zero length string
            ID = SafeInput.getNonZeroLenString(in, "Please enter the ID (6 digits)" );
            name = SafeInput.getNonZeroLenString(in, "Please enter the name" );
            description = SafeInput.getNonZeroLenString(in, "Please enter the description" );


            //Goes to SafeInput and checks to make sure birth year is 4 digits long
            cost = SafeInput.getDouble(in, "Please enter the cost");

            //formats information user input
            record = ID + ", " + name + ", " + description + ", " + cost;

            //records in array list
            products.add(record);

            done = SafeInput.getYNConfirm(in, "Are you done entering information?");

        }
        while(!done);

        for(String p: products)
        {
            System.out.println(p);
        }

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : products) //enhanced for loop
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();  // adds the new line

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}