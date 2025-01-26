package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;

public class Deer extends Herbivore {

  public Deer(Location location) {
    super(location);
    this.weight = 300;
    this.maxSatiety = 50;
    this.satiety = 50; // Начальная сытость
    this.maxPopulationPerCell = 20;
    this.movementSpeed = 4; // Перемещение на 4 клетки
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    if (creature instanceof Plant) {
      return 1.0; // Олень ест только растения
    }
    return 0.0;
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      int value = Math.min(plant.getQuantity(), 10); // Ест до 10 единиц растений
      plant.reduce(value);
      return value;
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    return new Deer(location);
  }

  public String getSymbol() {
    return "🦌";
  }
}