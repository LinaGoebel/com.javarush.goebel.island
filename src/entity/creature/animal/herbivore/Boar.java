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
    this.satiety = 50; // Начальная сытость
    this.maxPopulationPerCell = 50; // Кабаны образуют крупные группы
    this.movementSpeed = 2; // Перемещение на 2 клетки
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    if (creature instanceof Plant) {
      return 1.0; // Кабан ест только растения
    }
    return 0.0;
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      int value = Math.min(plant.getQuantity(), 10); // Кабан ест до 10 единиц растения за раз
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
    return "🐗"; // Юникод-символ свиньи
  }
}