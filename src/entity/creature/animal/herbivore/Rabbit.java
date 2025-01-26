package entity.creature.animal.herbivore;


import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import java.util.concurrent.ThreadLocalRandom;


public class Rabbit extends Herbivore {

  public Rabbit(Location location) {
    super(location);
    if (location == null) {
      throw new IllegalArgumentException("Location cannot be null for Rabbit");
    }
    this.weight = 2;
    this.maxSatiety = 1;
    this.satiety = 1;
    this.maxPopulationPerCell = 150;
    this.movementSpeed = 2;
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    // Кролик ест только растения, вероятность 100%
    if (creature instanceof Plant) {
      return 1.0;
    }
    return 0.0; // Кролик не ест животных
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      // Кролик съедает до 2 единиц растения за раз
      int value = Math.min(plant.getQuantity(), 2);
      plant.reduce(value); // Уменьшаем количество растения
      return value;
    }
    return 0; // Если по какой-то причине растение недоступно
  }

  @Override
  public Animal createOffspring(Location location) {
    if (ThreadLocalRandom.current().nextDouble() < 0.8) { // 80% шанс на размножение
      return new Rabbit(location);
    }
    return null; // Не всегда рождается потомок
  }

  @Override
  public String getSymbol() {
    return "🐇";
  }
}