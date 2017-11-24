import java.util.Scanner;
import java.lang.String;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class WeatherStation
{
  private String name;
  private Location location;
  private List<WeatherRecord> record = new ArrayList<>();

  /**
  *
  *creates a weather station
  *
  *@param file name acquired from the command line to open a data text file
  *
  *calls the constructor for each class with the convineint parameters to send
  *if filename or file is not found then it throws an exception
  *
  */
  public WeatherStation (String fileName) throws FileNotFoundException
  {
    Scanner input = new Scanner(new File(fileName));
    name = input.nextLine();
    location = new Location(input.nextLine());
      input.nextLine();
      input.nextLine();
    int i=0;
    while(input.hasNextLine())
    {
      WeatherRecord line = new WeatherRecord(input.nextLine());
      record.add(i++, line);
    }

  }

  /**
  *@return location component of this station
  */
  public Location getLocation()
  {
      return location;
  }

  /**
  *@return name component of this station
  */
  public String getName()
  {
    return name;
  }

  /**
  *@return number of weather records in this station
  */
  public int getRecordCount()
  {
    return record.size();
  }

  /**
  *@return a specific record from this station
  */
  public WeatherRecord getRecord(int index)
  {
    return record.get(index);
  }

  /**
  *finds the record containing the sunniest month in the records list
  */
  public WeatherRecord findSunniestMonth()
  {
    WeatherRecord maxRecord = record.get(0);
      double max = maxRecord.getSunHours();
    for (int i=0; i<record.size(); i++)
    {
      WeatherRecord tempRecord = record.get(i);
      double tempHours = tempRecord.getSunHours();
      if (tempHours > max)
      {
        maxRecord = tempRecord;
        max = tempHours;
      }
    }
    return maxRecord;
  }

  /**
  *calculates and returns the toal amount of rain fall during a specific year in the list of records
  *@param the specific year to search for
  */
  public double totalRainfall(int year)
  {
    double total = 0.0;
    for (int i=0; i<record.size(); i++)
    {
      WeatherRecord temp = record.get(i);
      if(temp.getYear() == year)
      {
        total += temp.getRainfall();
      }
    }
    return total;
  }

  /**
  *calculates the average temperature of a specific months throughout all the years
  *
  *@param the month to be searched for and used in calculations
  */
  public double meanMaxTemp(int month)
  {
    double mean =0;
    int monthTimes = 0;
    int size = record.size();
    for(int i=0; i<size; i++)
    {
      WeatherRecord temp = record.get(i);
      if(temp.getMonth() == month)
      {
        mean += temp.getMaxTemp();
        monthTimes++;
      }
    }
    mean /= monthTimes;
    return mean;
  }

  /**
  *finds the year with the most rainfall over the whole record
  *
  *@return the value of the rainfall during that year
  */
  public int findWettestYear()
{
  double maxTotal=0;
  double tempTotal=0;
  int tempYear = 0;
  for (int i=0; i<record.size(); i++)
  {
    WeatherRecord tempRecord = record.get(i);
    tempTotal = totalRainfall(tempRecord.getYear());
    if (tempTotal > maxTotal)
    {
      tempYear = tempRecord.getYear();
      maxTotal = tempTotal;
    }
    tempTotal=0;
  }
  return tempYear;
}

/**
*finds the year with the least rainfall over the whole record
*
*@return the value of the rainfall during that year
*/
  public int findDriestYear()
  {
    double minTotal = totalRainfall(findWettestYear());
    double tempTotal=0;
    int tempYear = 0;
    for (int i=0; i<record.size(); i++)
    {
      WeatherRecord tempRecord = record.get(i);
      tempTotal = totalRainfall(tempRecord.getYear());
              if (tempTotal < minTotal)
              {
                tempYear = tempRecord.getYear();
                minTotal = tempTotal;
              }
              tempTotal=0;
    }
    return tempYear;
  }
}
