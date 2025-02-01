package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;

abstract class Herbivore extends Animal {

  public Herbivore(Location location) {
    super(location);
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    if (creature instanceof Plant) {
      return 0.8;
    }
    return 0.0;
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      int value = Math.min(plant.getQuantity(), 5);
      return value;
    }
    return 0;
  }

  @Override
  public abstract String getSymbol();

  @Override
  protected Animal createOffspring(Location location) {
    return null;
  }
}
