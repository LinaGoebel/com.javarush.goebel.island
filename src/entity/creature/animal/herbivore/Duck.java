package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore {

  public Duck(Location location) {
    super(location);
    this.weight = 1;
    this.maxSatiety = 1;
    this.satiety = 1;
    this.maxPopulationPerCell = 200;
    this.movementSpeed = 4;
  }

  @Override
  public boolean eat(Creature creature) {
    if (creature instanceof Plant) {
      super.eat(creature);
    }

    if (creature instanceof Caterpillar) {
      double eatProb = 0.90;
      if (ThreadLocalRandom.current().nextDouble() < eatProb) {
        this.satiety++;
        if (this.satiety > this.maxSatiety) {
          this.satiety = this.maxSatiety;
        }
      }
    }
    return false;
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    if (creature instanceof Plant) {
      return 1.0;
    }
    if (creature instanceof Caterpillar) {
      return 0.90;
    }
    return 0.0;
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      int value = Math.min(plant.getQuantity(), 1);
      plant.reduce(value);
      return value;
    } else if (creature instanceof Caterpillar) {
      return 1;
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    return new Duck(location);
  }

  public String getSymbol() {
    return "🦆";
  }
}