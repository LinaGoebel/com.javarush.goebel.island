package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;

public class Mouse extends Herbivore {

  public Mouse(Location location) {
    super(location);
    this.weight = 0.05;
    this.maxSatiety = 1;
    this.satiety = 1; // начинаем «сытыми»
    this.maxPopulationPerCell = 500;
    this.movementSpeed = 1;
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    if (creature instanceof Plant) {
      return 1.0; // Мышь всегда ест растения
    }
    return 0.0; // Мышь не ест других существ
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      int value = Math.min(plant.getQuantity(), 1); // Ест 1 единицу растения
      plant.reduce(value);
      return value;
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    return new Mouse(location);
  }

  @Override
  public String getSymbol() {
    return "🐭";
  }
}