import configuration.SimulationEngine;
import entity.Island;
import simulation.IslandInitializer;
import simulation.Settings;

public class Application {

  public static Island applicationIsland;

  public static void main(String[] args) {
    Settings.loadFromFile("settings.properties");

    Island island = new Island();
    applicationIsland = island;

    IslandInitializer initializer = new IslandInitializer(island);
    initializer.initialize();

    int simulationDuration = Settings.getInt("SIMULATION_DURATION");
    SimulationEngine engine = new SimulationEngine(island, simulationDuration);
    engine.start();
  }
}
