package fileapp;

import java.text.ParseException;

/**
 * A AdultRecord is represented by the name, status and sin number in one record
 *
 * @author Xiaowen Li
 */
public class AdultRecord extends Record
{
    private long sinNumber;
    private char status = 'A';

    public AdultRecord(char status, long sinNumber, String name)
    {
        super(name);
        this.sinNumber = sinNumber;
    }

    /**
     *
     * @param csvRecord to construct a AdultRecord passed by main method
     * @throws java.text.ParseException
     */
    public AdultRecord(String csvRecord) throws ParseException
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
            this.sinNumber = Long.parseLong(fields[2]);
            String sn = Long.toString(this.sinNumber);
            if (sn.length() != 9)
            {
                throw new ParseException("Social insurance number has length "
                        + sn.length() + " but it should have length 9", 0);
            }
        } catch (Exception e)
        {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    public void setSinNumber(long sinNumber)
    {
        this.sinNumber = sinNumber;
    }

    public long getSinNumber()
    {
        return sinNumber;
    }

    /**
     * @return a message to indicate the record information
     */
    @Override
    public String toCSV()
    {
        return this.status + "," + this.getName() + "," + this.sinNumber;
    }
}
