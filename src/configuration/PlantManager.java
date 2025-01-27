package configuration;

import entity.Island;
import entity.Location;
import entity.creature.plant.Plant;

public class PlantManager {

  private final Island island;

  public PlantManager(Island island) {
    this.island = island;
  }

  public void growPlants() {
    for (int x = 0; x < island.getColumnsCount(); x++) {
      for (int y = 0; y < island.getRowsCount(); y++) {
        Location loc = island.getLocation(x, y);
        for (Plant plant : loc.getPlants()) {
          plant.grow(1);
          if (plant.getQuantity() > 20) {
            plant.setQuantity(20);
          }
        }
      }
    }
  }
}