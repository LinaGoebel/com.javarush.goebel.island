package entity.creature.animal.herbivore;


import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import java.util.concurrent.ThreadLocalRandom;


public class Rabbit extends Herbivore {

  public Rabbit(Location location) {
    super(location);
    if (location == null) {
      throw new IllegalArgumentException("Location cannot be null for Rabbit");
    }
    this.weight = 2;
    this.maxSatiety = 1;
    this.satiety = 1;
    this.maxPopulationPerCell = 150;
    this.movementSpeed = 2;
  }

  @Override
  protected double calculateEatProbability(Creature creature) {

    if (creature instanceof Plant) {
      return 1.0;
    }
    return 0.0;
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      int value = Math.min(plant.getQuantity(), 2);
      plant.reduce(value);
      return value;
    }
    return 0;
  }

  @Override
  public Animal createOffspring(Location location) {
    if (ThreadLocalRandom.current().nextDouble() < 0.8) {
      return new Rabbit(location);
    }
    return null;
  }

  @Override
  public String getSymbol() {
    return "🐇";
  }
}