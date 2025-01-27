package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;

public class Boar extends Herbivore {

  public Boar(Location location) {
    super(location);
    this.weight = 400;
    this.maxSatiety = 50;
    this.satiety = 50;
    this.maxPopulationPerCell = 50;
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
      int value = Math.min(plant.getQuantity(), 10);
      plant.reduce(value);
      return value;
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    return new Boar(location);
  }


  @Override
  public String getSymbol() {
    return "🐗";
  }
}