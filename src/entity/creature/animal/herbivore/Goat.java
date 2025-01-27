package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;

public class Goat extends Herbivore {

  public Goat(Location location) {
    super(location);
    this.weight = 60;
    this.maxSatiety = 10;
    this.satiety = 10;
    this.maxPopulationPerCell = 140;
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
      int value = Math.min(plant.getQuantity(), 5);
      plant.reduce(value);
      return value;
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    return new Goat(location);
  }

  @Override
  public String getSymbol() {
    return "🐐";
  }
}
