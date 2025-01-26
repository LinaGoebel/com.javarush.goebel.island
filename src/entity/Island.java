package entity;

import configuration.Settings;
import java.util.ArrayList;
import java.util.List;

public class Island {

  private final int columnsCount;
  private final int rowsCount;
  public final Location[][] locations;

  public Island() {
    this.columnsCount = Settings.getInt("COLUMNS_COUNT");
    this.rowsCount = Settings.getInt("ROWS_COUNT");
    locations = new Location[columnsCount][rowsCount];
    initializeLocations();
  }

  private void initializeLocations() {
    for (int x = 0; x < columnsCount; x++) {
      for (int y = 0; y < rowsCount; y++) {
        locations[x][y] = new Location(x, y);
      }
    }
  }

  public Location getLocation(int x, int y) {
    if (x < 0 || x >= columnsCount || y < 0 || y >= rowsCount) {
      throw new IndexOutOfBoundsException("Invalid location: (" + x + ", " + y + ")");
    }
    return locations[x][y];
  }

  public int getColumnsCount() {
    return columnsCount;
  }

  public int getRowsCount() {
    return rowsCount;
  }

  /**
   * Возвращает соседние локации (верх, низ, лево, право).
   */
  public List<Location> getNeighbors(int x, int y) {
    List<Location> neighbors = new ArrayList<>();
    if (x > 0) {
      neighbors.add(locations[x - 1][y]);
    }
    if (x < columnsCount - 1) {
      neighbors.add(locations[x + 1][y]);
    }
    if (y > 0) {
      neighbors.add(locations[x][y - 1]);
    }
    if (y < rowsCount - 1) {
      neighbors.add(locations[x][y + 1]);
    }
    return neighbors;
  }
}
