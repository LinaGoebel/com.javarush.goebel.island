package simulation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Settings {

  private static final Properties properties = new Properties();

  static {
    properties.setProperty("COLUMNS_COUNT", "100");
    properties.setProperty("ROWS_COUNT", "20");
    properties.setProperty("SIMULATION_DURATION", "100");
    properties.setProperty("INITIAL_ANIMAL_COUNT", "10");
    properties.setProperty("PLANT_GROWTH_RATE", "1");
  }

  public static void loadFromFile(String filePath) {
    try (var input = new FileInputStream(filePath)) {
      properties.load(input);
      System.out.println("Settings loaded from file: " + filePath);
    } catch (Exception e) {
      System.err.println("Could not load settings from file: " + filePath
          + " (using default settings)");
    }
  }

  public static void saveToFile(String filePath) {
    try (var output = new FileOutputStream(filePath)) {
      properties.store(output, "Island Simulation Settings");
      System.out.println("Settings saved to file: " + filePath);
    } catch (Exception e) {
      System.err.println("Could not save settings to file: " + filePath);
    }
  }

  public static int getInt(String key) {
    return Integer.parseInt(properties.getProperty(key));
  }

  public static double getDouble(String key) {
    return Double.parseDouble(properties.getProperty(key));
  }

  public static void updateSetting(String key, String value) {
    properties.setProperty(key, value);
  }
}
