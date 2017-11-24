import java.util.Scanner;
import java.lang.String;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class WeatherInfo
{
  public static void main(String[] args) throws FileNotFoundException
  {
      try
      {
        WeatherStation station = new WeatherStation(args[0]);
        System.out.println("Name: " + station.getName());
        Location place = station.getLocation();
        System.out.println("Location: " + place.toString());
        System.out.println(station.getRecordCount() + " months of data available");
        WeatherRecord sunny = station.findSunniestMonth();
        System.out.println("Sunniest month: " + sunny.getMonthName() + " " + sunny.getYear() + " (" + sunny.getSunHours() + " hours)");
        System.out.printf("Mean max temp in August: %.1f deg C\n", station.meanMaxTemp(8));
        System.out.println("Wettest year: " + station.findWettestYear() + "(" + station.totalRainfall(station.findWettestYear()) + " mm)");
        System.out.printf("Driest year: %d (%.1f mm)\n", station.findDriestYear(), station.totalRainfall(station.findDriestYear()));
      }
      catch (FileNotFoundException error)
      {
        System.out.println("Error: cannot access input file\n");
      }
      catch (Exception error)
      {
        System.out.println("Usage: java WeatherInfo <filename>\n");
      }
  }
}
