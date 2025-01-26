import configuration.Settings;
import configuration.SimulationEngine;
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
import entity.creature.plant.Plant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {

  // Чтобы удобно передать остров в другие классы
  public static Island applicationIsland;

  public static void main(String[] args) {
    // 1. Загружаем настройки
    Settings.loadFromFile("settings.properties");

    // 2. Создаём остров
    Island island = new Island();
    applicationIsland = island; // Сохраняем ссылку на остров

    // 3. Разбрасываем начальное количество животных и растений
    initializeIsland(island);

    // 4. Запускаем движок симуляции
    int simulationDuration = Settings.getInt("SIMULATION_DURATION");
    SimulationEngine engine = new SimulationEngine(island, simulationDuration);
    engine.start();
  }

  /**
   * Метод для начального распределения животных и растений по острову.
   */
  private static void initializeIsland(Island island) {
    Random random = new Random();
    int initialCount = Settings.getInt("INITIAL_ANIMAL_COUNT");

    // Ограничиваем количество объектов по размеру острова
    if (initialCount > island.getColumnsCount() * island.getRowsCount()) {
      System.err.println("Ошибка: слишком много объектов для размера острова.");
      initialCount = island.getColumnsCount() * island.getRowsCount();
    }

    for (int i = 0; i < initialCount; i++) {
      int x = random.nextInt(island.getColumnsCount());
      int y = random.nextInt(island.getRowsCount());
      Location loc = island.getLocation(x, y);

      // Генерируем случайное животное или растение
      int n = random.nextInt(20); // Увеличиваем вероятность появления растений
      switch (n) {
        case 0 -> {
          new Wolf(loc);
          System.out.println("Добавлен волк в клетку (" + x + ", " + y + ")");
        }
        case 1 -> {
          new Rabbit(loc);
          System.out.println("Добавлен кролик в клетку (" + x + ", " + y + ")");
        }
        case 2 -> {
          new Mouse(loc);
          System.out.println("Добавлена мышь в клетку (" + x + ", " + y + ")");
        }
        case 3 -> {
          new Horse(loc);
          System.out.println("Добавлена лошадь в клетку (" + x + ", " + y + ")");
        }
        case 4 -> {
          new Goat(loc);
          System.out.println("Добавлена коза в клетку (" + x + ", " + y + ")");
        }
        case 5 -> {
          new Sheep(loc);
          System.out.println("Добавлена овца в клетку (" + x + ", " + y + ")");
        }
        case 6 -> {
          new Deer(loc);
          System.out.println("Добавлен олень в клетку (" + x + ", " + y + ")");
        }
        case 7 -> {
          new Buffalo(loc);
          System.out.println("Добавлен буйвол в клетку (" + x + ", " + y + ")");
        }
        case 8 -> {
          new Boar(loc);
          System.out.println("Добавлен кабан в клетку (" + x + ", " + y + ")");
        }
        case 9 -> {
          new Caterpillar(loc);
          System.out.println("Добавлена гусеница в клетку (" + x + ", " + y + ")");
        }
        case 10 -> {
          new Duck(loc);
          System.out.println("Добавлена утка в клетку (" + x + ", " + y + ")");
        }
        case 11 -> {
          new Eagle(loc);
          System.out.println("Добавлен орёл в клетку (" + x + ", " + y + ")");
        }
        case 12 -> {
          new Fox(loc);
          System.out.println("Добавлена лиса в клетку (" + x + ", " + y + ")");
        }
        case 13 -> {
          new Boa(loc);
          System.out.println("Добавлен удав в клетку (" + x + ", " + y + ")");
        }
        case 14 -> {
          new Bear(loc);
          System.out.println("Добавлен медведь в клетку (" + x + ", " + y + ")");
        }
        case 15, 16, 17, 18, 19 -> {
          loc.addPlant();
          System.out.println("Добавлено растение в клетку (" + x + ", " + y + ")");
        }
        default -> loc.addPlant();
      }
    }

    System.out.println("Инициализация завершена: распределено " + initialCount + " объектов.");
  }
}