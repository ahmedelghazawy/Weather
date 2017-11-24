import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
public class WeatherPlot extends Application
{
  public static WeatherStation station;
  @Override public void start(Stage stage) {
       stage.setTitle("Line Chart Sample");
       final NumberAxis xAxis = new NumberAxis();
       final NumberAxis yAxis = new NumberAxis();
       xAxis.setLabel("Month");
       yAxis.setLabel("Mean max temp.");
       final LineChart<Number,Number> lc = new LineChart<>(xAxis,yAxis);
       lc.setTitle("Mean max temp. over months");
       XYChart.Series series = new XYChart.Series();
       series.setName("SC16AMEG");

       for(int i=1; i<13; i++)
       {
         double mean = station.meanMaxTemp(i);
         //WeatherRecord tempRecord = station.getRecord(i);
         series.getData().add(new XYChart.Data(i, mean));
       }
       





       Scene scene = new Scene(lc, 1000, 800);
       lc.getData().addAll(series);
       stage.setScene(scene);
       stage.show();
  }
  public static void main(String[] args) throws FileNotFoundException
  {
            station = new WeatherStation(args[0]);
            launch(args);
  }
}
