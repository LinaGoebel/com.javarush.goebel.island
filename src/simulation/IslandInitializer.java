package simulation;

import entity.Island;
import entity.Location;
import entity.creature.Creature;
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
      System.err.println("Ошибка: слишком много объектов для размера острова.");
      initialCount = island.getColumnsCount() * island.getRowsCount();
    }

    for (int i = 0; i < initialCount; i++) {
      int x = random.nextInt(island.getColumnsCount());
      int y = random.nextInt(island.getRowsCount());
      Location loc = island.getLocation(x, y);

      addEntityToLocation(loc, random, x, y);
    }

    System.out.println("Инициализация завершена: распределено " + initialCount + " объектов.");
  }

  private void addEntityToLocation(Location loc, Random random, int x, int y) {
    int n = random.nextInt(20);

    switch (n) {
      case 0 -> createEntity(new Wolf(loc), "волк", x, y);
      case 1 -> createEntity(new Rabbit(loc), "кролик", x, y);
      case 2 -> createEntity(new Mouse(loc), "мышь", x, y);
      case 3 -> createEntity(new Horse(loc), "лошадь", x, y);
      case 4 -> createEntity(new Goat(loc), "коза", x, y);
      case 5 -> createEntity(new Sheep(loc), "овца", x, y);
      case 6 -> createEntity(new Deer(loc), "олень", x, y);
      case 7 -> createEntity(new Buffalo(loc), "буйвол", x, y);
      case 8 -> createEntity(new Boar(loc), "кабан", x, y);
      case 9 -> createEntity(new Caterpillar(loc), "гусеница", x, y);
      case 10 -> createEntity(new Duck(loc), "утка", x, y);
      case 11 -> createEntity(new Eagle(loc), "орёл", x, y);
      case 12 -> createEntity(new Fox(loc), "лиса", x, y);
      case 13 -> createEntity(new Boa(loc), "удав", x, y);
      case 14 -> createEntity(new Bear(loc), "медведь", x, y);
      case 15, 16, 17, 18, 19 -> {
        loc.addPlant();
        System.out.println("Добавлено растение в клетку (" + x + ", " + y + ")");
      }
      default -> loc.addPlant();
    }
  }

  private void createEntity(Creature entity, String name, int x, int y) {
    System.out.println("Добавлен " + name + " в клетку (" + x + ", " + y + ")");
  }
}

