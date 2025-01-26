package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
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
    // Овца ест только растения с вероятностью 100%
    if (creature instanceof Plant) {
      return 1.0;
    }
    return 0.0; // Овца не ест животных
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      // Допустим, овца может съесть до 5 единиц растения за раз
      int value = Math.min(plant.getQuantity(), 5);
      plant.reduce(value); // Уменьшаем количество растения
      return value;
    }
    return 0; // Если по каким-то причинам растение недоступно
  }

  @Override
  public String getSymbol() {
    return "🐑";
  }
}
