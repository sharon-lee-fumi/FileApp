package fileapp;

import java.text.ParseException;

/**
 * A record is represented by the name in one record
 *
 * @author Xiaowen Li
 */
public class Record
{
    private String name;

    public Record(String name)
    {
        this.name = name;
    }

    public Record() throws ParseException
    {
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    /**
     * @return a empty message because the class only represent name
     */
    public String toCSV()
    {
        return "Null";
    }
}
