package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.plant.Plant;


public class Sheep extends Herbivore {

  public Sheep(Location location) {
    super(location);
    if (location == null) {
      throw new IllegalArgumentException("Location cannot be null for Sheep");
    }
    this.weight = 70;
    this.maxSatiety = 15;
    this.satiety = 15;
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
  public String getSymbol() {
    return "🐑";
  }
}
