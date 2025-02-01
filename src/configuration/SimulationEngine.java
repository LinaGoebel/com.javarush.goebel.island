package configuration;

import entity.Island;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class SimulationEngine {

  private final Island island;
  private ScheduledExecutorService scheduler;

  private final PlantManager plantManager;
  private final AnimalManager animalManager;
  private final StatisticsPrinter statisticsPrinter;

  public SimulationEngine(Island island, int simulationDuration) {
    this.island = island;
    this.plantManager = new PlantManager(island);
    this.animalManager = new AnimalManager(island);
    this.statisticsPrinter = new StatisticsPrinter(island);
  }

  public void start() {
    scheduler = Executors.newScheduledThreadPool(3);

    scheduler.scheduleAtFixedRate(plantManager::growPlants, 0, 1, TimeUnit.SECONDS);
    scheduler.scheduleAtFixedRate(animalManager::updateAnimals, 0, 2, TimeUnit.SECONDS);
    scheduler.scheduleAtFixedRate(statisticsPrinter::print, 0, 5, TimeUnit.SECONDS);

    System.out.println("Simulation running!");
  }

  public void stop() {
    if (scheduler != null && !scheduler.isShutdown()) {
      try {
        scheduler.shutdown();
        if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
          scheduler.shutdownNow();
          System.out.println("Forced termination of the simulation.");
        } else {
          System.out.println("The simulation has been stopped.");
        }
      } catch (InterruptedException e) {
        scheduler.shutdownNow();
        System.err.println("Error when stopping the simulation: " + e.getMessage());
      }
    }
  }
}