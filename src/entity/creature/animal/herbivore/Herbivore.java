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
    // Травоядные едят только растения (вероятность = 1.0),
    // но если это животное — 0
    if (creature instanceof Plant) {
      return 1.0;
    }
    return 0.0;
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      // Условно пусть животное за раз «съедает» 5 единиц растения
      int value = Math.min(plant.getQuantity(), 5);
      return value;
    }
    return 0;
  }

  @Override
  public abstract String getSymbol();

  @Override
  protected Animal createOffspring(Location location) {
    // Переопределять в конкретных классах
    return null;
  }
}
