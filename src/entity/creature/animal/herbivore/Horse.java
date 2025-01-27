package entity.creature.animal.herbivore;


import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;


public class Horse extends Herbivore {

  public Horse(Location location) {
    super(location);
    this.weight = 400;
    this.maxSatiety = 60;
    this.satiety = 60;
    this.maxPopulationPerCell = 20;
    this.movementSpeed = 4;
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
      int value = Math.min(plant.getQuantity(), 15);
      plant.reduce(value);
      return value;
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    return new Horse(location);
  }

  @Override
  public String getSymbol() {
    return "🐎";
  }
}

