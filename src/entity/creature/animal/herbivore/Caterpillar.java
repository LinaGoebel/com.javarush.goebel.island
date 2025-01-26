package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;

public class Caterpillar extends Herbivore {

  public Caterpillar(Location location) {
    super(location);
    this.weight = 0.01;
    this.maxSatiety = 0;
    this.satiety = 0; // Гусеница всегда "голодна", но ей не требуется пища
    this.maxPopulationPerCell = 1000; // Множество гусениц на одной клетке
    this.movementSpeed = 0; // Гусеница не перемещается
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    if (creature instanceof Plant) {
      return 1.0; // Гусеница ест только растения
    }
    return 0.0;
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      int value = Math.min(plant.getQuantity(), 1); // Ест по 1 единице растения
      plant.reduce(value);
      return value;
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    return new Caterpillar(location);
  }


  @Override
  public String getSymbol() {
    return "🐛";
  }
}
