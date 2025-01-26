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
    this.satiety = 60; // Начинаем с максимальной сытости
    this.maxPopulationPerCell = 20;
    this.movementSpeed = 4; // Лошадь перемещается на 4 клетки
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    if (creature instanceof Plant) {
      return 1.0; // Лошадь ест только растения
    }
    return 0.0;
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      int value = Math.min(plant.getQuantity(), 15); // Лошадь ест до 15 единиц за раз
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

