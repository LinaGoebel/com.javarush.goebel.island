package configuration;

import entity.Island;
import entity.Location;
import entity.creature.plant.Plant;

public class StatisticsPrinter {

  private final Island island;

  public StatisticsPrinter(Island island) {
    this.island = island;
  }

  public void print() {
    printStatistics();
    printMap();
  }

  private void printStatistics() {
    int totalAnimals = 0;
    int totalPlants = 0;

    for (int x = 0; x < island.getColumnsCount(); x++) {
      for (int y = 0; y < island.getRowsCount(); y++) {
        Location loc = island.getLocation(x, y);
        totalAnimals += loc.getAnimals().size();
        for (Plant plant : loc.getPlants()) {
          totalPlants += plant.getQuantity();
        }
      }
    }

    System.out.println("[STATISTICS] Animals: " + totalAnimals + ", Plants: " + totalPlants);
  }

  private void printMap() {
    System.out.println("=== Map of the island ===");
    for (int y = 0; y < island.getRowsCount(); y++) {
      for (int x = 0; x < island.getColumnsCount(); x++) {
        Location loc = island.getLocation(x, y);

        if (!loc.getAnimals().isEmpty() && !loc.getPlants().isEmpty()) {
          System.out.print(loc.getAnimals().get(0).getSymbol() + "🌱 ");
        } else if (!loc.getAnimals().isEmpty()) {
          System.out.print(loc.getAnimals().get(0).getSymbol() + " ");
        } else if (!loc.getPlants().isEmpty()) {
          System.out.print("🌱 ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
    System.out.println("======================");
  }
}



