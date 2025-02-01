package simulation;

import entity.Island;
import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.Boar;
import entity.creature.animal.herbivore.Buffalo;
import entity.creature.animal.herbivore.Caterpillar;
import entity.creature.animal.herbivore.Deer;
import entity.creature.animal.herbivore.Duck;
import entity.creature.animal.herbivore.Goat;
import entity.creature.animal.herbivore.Horse;
import entity.creature.animal.herbivore.Mouse;
import entity.creature.animal.herbivore.Rabbit;
import entity.creature.animal.herbivore.Sheep;
import entity.creature.animal.predator.Bear;
import entity.creature.animal.predator.Boa;
import entity.creature.animal.predator.Eagle;
import entity.creature.animal.predator.Fox;
import entity.creature.animal.predator.Wolf;
import java.util.Random;


public class IslandInitializer {

  private final Island island;

  public IslandInitializer(Island island) {
    this.island = island;
  }

  public void initialize() {
    Random random = new Random();
    int initialCount = Settings.getInt("INITIAL_ANIMAL_COUNT");

    if (initialCount > island.getColumnsCount() * island.getRowsCount()) {
      System.err.println("Error: too many objects for the size of the island.");
      initialCount = island.getColumnsCount() * island.getRowsCount();
    }

    for (int i = 0; i < initialCount; i++) {
      int x = random.nextInt(island.getColumnsCount());
      int y = random.nextInt(island.getRowsCount());
      Location loc = island.getLocation(x, y);

      addEntityToLocation(loc, random, x, y);
    }

    System.out.println("Initialization completed: distributed " + initialCount + " objects.");
  }

  private void addEntityToLocation(Location loc, Random random, int x, int y) {
    int n = random.nextInt(20);

    switch (n) {
      case 0 -> createEntity(new Wolf(loc), "wolf", x, y);
      case 1 -> createEntity(new Rabbit(loc), "rabbit", x, y);
      case 2 -> createEntity(new Mouse(loc), "mouse", x, y);
      case 3 -> createEntity(new Horse(loc), "horse", x, y);
      case 4 -> createEntity(new Goat(loc), "goat", x, y);
      case 5 -> createEntity(new Sheep(loc), "sheep", x, y);
      case 6 -> createEntity(new Deer(loc), "deer", x, y);
      case 7 -> createEntity(new Buffalo(loc), "buffalo", x, y);
      case 8 -> createEntity(new Boar(loc), "boar", x, y);
      case 9 -> createEntity(new Caterpillar(loc), "caterpillar", x, y);
      case 10 -> createEntity(new Duck(loc), "duck", x, y);
      case 11 -> createEntity(new Eagle(loc), "eagle", x, y);
      case 12 -> createEntity(new Fox(loc), "fox", x, y);
      case 13 -> createEntity(new Boa(loc), "boa", x, y);
      case 14 -> createEntity(new Bear(loc), "bear", x, y);
      default -> {
        loc.addPlant();
        System.out.println("Added plant to cell (" + x + ", " + y + ")");
      }
    }
  }

  private void createEntity(Animal entity, String name, int x, int y) {
    System.out.println("Added " + name + " to cell (" + x + ", " + y + ")");
  }
}