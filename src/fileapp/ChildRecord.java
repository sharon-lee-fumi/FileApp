package fileapp;

import java.text.ParseException;

/**
 * A ChildRecord is represented by the name, status and age in one record
 *
 * @author Xiaowen Li
 */
public class ChildRecord extends Record
{
    private int age;
    private char status = 'C';

    public ChildRecord(char status, String name, int age)
    {
        super(name);
        this.age = age;
    }

    /**
     *
     * @param csvRecord to construct a ChildRecord passed by main method
     * @throws java.text.ParseException
     */
    public ChildRecord(String csvRecord) throws ParseException
    {
        try
        {
            String[] fields = csvRecord.split(",");
            if (fields.length != 3)
            {
                throw new ParseException("Too many fields contained in "
                        + " CSV record", 0);
            }
            this.setName(fields[1]);
            this.age = Integer.parseInt(fields[2]);
            if (this.age < 0 || this.age > 18)
            {
                throw new ParseException("The age of the child " + this.getName()
                        + "should be between 0 - 18", 0);
            }
        } catch (Exception e)
        {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    /**
     * @return a message to indicate the record information
     */
    @Override
    public String toCSV()
    {
        return this.status + "," + super.getName() + "," + this.age;
    }

}
