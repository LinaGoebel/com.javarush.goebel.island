package configuration;

import entity.Island;
import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class SimulationEngine {

  private final Island island;
  private ScheduledExecutorService scheduler;

  public SimulationEngine(Island island, int simulationDuration) {
    this.island = island;
  }

  public void start() {
    scheduler = Executors.newScheduledThreadPool(3);

    scheduler.scheduleAtFixedRate(this::growPlants, 0, 1, TimeUnit.SECONDS);
    scheduler.scheduleAtFixedRate(this::updateAnimals, 0, 2, TimeUnit.SECONDS);
    scheduler.scheduleAtFixedRate(() -> {
      printStatistics();
      printMap();
    }, 0, 5, TimeUnit.SECONDS);

    System.out.println("Симуляция запущена!");
  }

  public void stop() {
    if (scheduler != null && !scheduler.isShutdown()) {
      try {
        scheduler.shutdown();
        if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
          scheduler.shutdownNow();
          System.out.println("Принудительное завершение симуляции.");
        } else {
          System.out.println("Симуляция остановлена.");
        }
      } catch (InterruptedException e) {
        scheduler.shutdownNow();
        System.err.println("Ошибка при остановке симуляции: " + e.getMessage());
      }
    }
  }

  private void growPlants() {
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

  private void updateAnimals() {
    for (int x = 0; x < island.getColumnsCount(); x++) {
      for (int y = 0; y < island.getRowsCount(); y++) {
        Location loc = island.getLocation(x, y);
        List<Animal> animals = new ArrayList<>(loc.getAnimals());

        for (Animal animal : animals) {
          animal.decreaseSatiety();

          if (animal.isDead()) {
            loc.removeAnimal(animal);
            continue;
          }

          boolean atePlant = false;
          for (Plant plant : loc.getPlants()) {
            if (animal.eat(plant)) {
              atePlant = true;
              break;
            }
          }

          if (!atePlant) {
            for (Animal other : new ArrayList<>(loc.getAnimals())) {
              if (animal != other && animal.eat(other)) {
                loc.removeAnimal(other);
                break;
              }
            }
          }

          animal.move(island);

          Animal offspring = animal.reproduce();
          if (offspring != null) {
            System.out.println(animal.getSymbol() + " размножился!");
            loc.addAnimal(offspring);
          }
        }
      }
    }
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

    System.out.println("[СТАТИСТИКА] Животных: " + totalAnimals + ", Растений: " + totalPlants);
  }

  private void printMap() {
    System.out.println("=== Карта острова ===");
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