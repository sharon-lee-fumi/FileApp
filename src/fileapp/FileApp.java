package fileapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author Xiaowen Li
 */
public class FileApp
{

    /**
     * This program reads in two files, each one a CSV-formatted file
     * representing a series of records. Then produce a new file which contains
     * only the records which exist in the first file but do not exist in the
     * second file
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.text.ParseException
     */
    public static void main(String[] args)
            throws FileNotFoundException, ParseException
    {
        ArrayList<Record> records1 = new ArrayList<>();
        ArrayList<Record> records2 = new ArrayList<>();
        JFileChooser fc = new JFileChooser();
        try
        {
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                File f = fc.getSelectedFile();

                System.out.println(f.getAbsolutePath() + " was selected");
                Scanner fileContents = new Scanner(f);

                while (fileContents.hasNextLine())
                {
                    String record = fileContents.nextLine();

                    try
                    {
                        if (record.startsWith("A"))
                        {
                            records1.add(new AdultRecord(record));
                        } else if (record.startsWith("C"))
                        {
                            records1.add(new ChildRecord(record));
                        } else
                        {
                            throw new ParseException("Invalid type of record occurred.", 0);
                        }
                    } catch (Exception e)
                    {
                        throw new ParseException(e.getMessage(), 0);
                    }
                }
                fileContents.close();
            }

            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                File f = fc.getSelectedFile();
                System.out.println(f.getAbsolutePath() + " was selected");
                Scanner fileContents = new Scanner(f);
                while (fileContents.hasNextLine())
                {
                    String record = fileContents.nextLine();

                    try
                    {
                        if (record.startsWith("A"))
                        {
                            records2.add(new AdultRecord(record));
                        } else if (record.startsWith("C"))
                        {
                            records2.add(new ChildRecord(record));
                        } else
                        {
                            throw new ParseException("Invalid type of record occurred.", 0);
                        }
                    } catch (Exception e)
                    {
                        throw new ParseException(e.getMessage(), 0);
                    }
                }

                int x = 0;
                while (x < records1.size())
                {
                    Record a = records1.get(x);
                    x = x + 1;
                    for (Record b : records2)
                    {
                        if (b.toCSV().equals(a.toCSV()))
                        {
                            records1.remove(a);
                            x = x - 1;
                            break;
                        }
                    }
                }
                fileContents.close();
            } else
            {
                System.out.println("No file was selected");
                System.exit(1); //quit immediately
            }
        } catch (Exception e)
        {
            System.out.println("Something is wrong: " + e.getMessage());
            System.exit(1); //quit immediately
        }

        try
        {
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                File f = fc.getSelectedFile();
                PrintWriter out = new PrintWriter(f);
                for (Record a : records1)
                {
                    out.println(a.toCSV());
                }
                out.close();
            } else
            {
                System.out.println("File was not saved");
            }
        } catch (Exception e)
        {
            System.out.println("Problem saving: " + e.getMessage());
        }
    }
}
