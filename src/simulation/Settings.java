package simulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Settings {

  private static final Properties properties = new Properties();

  static {
    resetToDefaults();
  }

  public static void loadFromFile(String filePath) {
    File file = new File(filePath);
    if (!file.exists()) {
      System.err.println("Settings file not found: " + filePath + " (using default settings)");
      return;
    }

    try (var input = new FileInputStream(filePath)) {
      properties.load(input);
      System.out.println("Settings loaded from file: " + filePath);
      properties.forEach((key, value) ->
          System.out.println("Loaded setting: " + key + " = " + value)
      );
    } catch (Exception e) {
      System.err.println("Could not load settings from file: " + filePath
          + " (using default settings). Error: " + e.getMessage());
    }
  }

  public static void saveToFile(String filePath) {
    File file = new File(filePath);
    try {
      if (!file.exists()) {
        file.createNewFile();
      }

      try (var output = new FileOutputStream(filePath)) {
        properties.store(output, "Island Simulation Settings");
        System.out.println("Settings saved to file: " + filePath);
      }
    } catch (Exception e) {
      System.err.println("Could not save settings to file: " + filePath
          + ". Error: " + e.getMessage());
    }
  }

  public static int getInt(String key) {
    try {
      return Integer.parseInt(properties.getProperty(key));
    } catch (NumberFormatException e) {
      System.err.println("Invalid integer value for key: " + key + ". Using default value 0.");
      return 0;
    }
  }

  public static double getDouble(String key) {
    try {
      return Double.parseDouble(properties.getProperty(key));
    } catch (NumberFormatException e) {
      System.err.println("Invalid double value for key: " + key + ". Using default value 0.0.");
      return 0.0;
    }
  }

  public static void updateSetting(String key, String value) {
    if (properties.containsKey(key)) {
      properties.setProperty(key, value);
      System.out.println("Updated setting: " + key + " = " + value);
    } else {
      System.err.println("Invalid setting key: " + key);
    }
  }

  public static void resetToDefaults() {
    properties.setProperty("COLUMNS_COUNT", "100");
    properties.setProperty("ROWS_COUNT", "20");
    properties.setProperty("SIMULATION_DURATION", "100");
    properties.setProperty("INITIAL_ANIMAL_COUNT", "10");
    properties.setProperty("PLANT_GROWTH_RATE", "1");
    System.out.println("Settings reset to default values.");
  }
}