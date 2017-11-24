import java.util.Scanner;
import java.lang.String;
import java.util.InputMismatchException;
import java.util.Objects;
public class WeatherRecord implements Comparable<WeatherRecord>
{
  //private fields used to represent a weather record

  private int year;
  private int month;
  private double maxTemp;
  private double minTemp;
  private int frostDays;
  private double rainfall;
  private double sunHours;

  /**
  *creates a weather record
  *
  *@param line for the input line from the text file
  *
  *splits every line into fragments and checks how many fragments exist in each line
  *if line has less than 7 fragments then an exception is thrown for input mismatch
  */

  public WeatherRecord(String line) throws InputMismatchException
  {
    Scanner info = new Scanner (line).useDelimiter("\\s+");
    int parameters = 0;
    while(info.hasNext())
    {
      parameters++;
      info.next();
    }
    if(parameters == 7)
    {
      Scanner splitter = new Scanner(line).useDelimiter("\\s+");
      year = splitter.nextInt();
      month = splitter.nextInt();
      maxTemp = splitter.nextDouble();
      minTemp = splitter.nextDouble();
      frostDays = splitter.nextInt();
      rainfall = splitter.nextDouble();
      sunHours = splitter.nextDouble();
    }
    else{
      throw new InputMismatchException("invalid parameter amount");
    }
  }

  /**
  *@return year component of this weather record
  */
  public int getYear()
  {
    return year;
  }

  /**
  *@return month component of this weather record
  */
  public int getMonth()
  {
    return month;
  }

  /**
  *@return maxTemp component of this weather record
  */
  public double getMaxTemp()
  {
    return maxTemp;
  }

  /**
  *@return minTemp component of this weather record
  */
  public double getMinTemp()
  {
    return minTemp;
  }

  /**
  *@return frostDays component of this weather record
  */
  public int getFrostDays()
  {
    return frostDays;
  }

  /**
  *@return rainfall component of this weather record
  */
  public double getRainfall()
  {
    return rainfall;
  }

  /**
  *@return sunHours component of this weather record
  */
  public double getSunHours()
  {
    return sunHours;
  }

  /**
  *returns a string with the name of the calendar month
  *tests the value of the month to know which string to return
  */

  public String getMonthName()
  {
    if (month == 1)
      return "January";
    else if (month == 2)
      return "February";
    else if (month == 3)
      return "March";
    else if (month == 4)
      return "April";
    else if (month == 5)
      return "May";
    else if (month == 6)
      return "June";
    else if (month == 7)
      return "July";
    else if (month == 8)
      return "August";
    else if (month == 9)
      return "September";
    else if (month == 10)
      return "October";
    else if (month == 11)
      return "November";
    else
      return "December";
  }


  /**
   * Compares this weather record with another.
   *
   * <p>This is required by the Comparable interface and allows weather records
   * to be sorted in lists and used with TreeMap and TreeSet.</p>
   *
   * @param other The other weather record being compared with this one
   * @return -1 if this time comes before the other, 0 if they are
   *   the same, +1 if this time comes after the other
   */
  @Override
  public int compareTo(WeatherRecord record1)
  {
    int comp = Integer.compare(year, record1.getYear());
    if (comp==0)
      comp = Integer.compare(month, record1.getMonth());
    return comp;
  }

  /**
   * Indicates whether this weather record is equal to another object.
   *
   * <p>In this context, 'equal' means 'also a WeatherRecord object with the same
   * values for all parameters.</p>
   *
   * @param other Object being compared with this weather record
   * @return true if this record is equal to the other, false otherwise
   */
  @Override
  public boolean equals(Object other)
  {
    if (other == this)
      {
        return true;
      }
      else if (!(other instanceof WeatherRecord))
      {
        return false;
      }
      else
      {
        WeatherRecord otherRecord = (WeatherRecord) other;
        return otherRecord.getMonth() == month &&
               otherRecord.getYear() == year &&
               otherRecord.getMaxTemp() == maxTemp &&
               otherRecord.getMinTemp() == minTemp &&
               otherRecord.getFrostDays() == frostDays &&
               otherRecord.getRainfall() == rainfall &&
               otherRecord.getSunHours() == sunHours;
      }
    }

    /**
     * Generates a hashcode for this weather record.
     *
     * <p>The hashcode is an integer that characterises a Time object and,
     * in this case, is guaranteed to be unique for each object.  It is
     * needed when using WeatherRecord objects as keys in hash-based collections.</p>
     *
     * @return Hashcode
     */
  @Override
  public int hashCode()
  {
    double value = year*12;
    value += month + 15;
    value += maxTemp + 23;
    value += minTemp + 73;
    value += frostDays - 20;
    value += rainfall + 54;
    value += sunHours + 64;
    return (int) value;
  }
}
