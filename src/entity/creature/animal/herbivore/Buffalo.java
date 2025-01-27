package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;

public class Buffalo extends Herbivore {

  public Buffalo(Location location) {
    super(location);
    this.weight = 700;
    this.maxSatiety = 100;
    this.satiety = 100;
    this.maxPopulationPerCell = 10;
    this.movementSpeed = 3;
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
      int value = Math.min(plant.getQuantity(), 20);
      plant.reduce(value);
      return value;
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    return new Buffalo(location);
  }

  @Override
  public String getSymbol() {
    return "🐂";
  }
}
