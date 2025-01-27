package entity.creature.animal.predator;

import entity.Location;
import entity.creature.Creature;

import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.Boar;
import entity.creature.animal.herbivore.Buffalo;
import entity.creature.animal.herbivore.Deer;
import entity.creature.animal.herbivore.Duck;
import entity.creature.animal.herbivore.Goat;
import entity.creature.animal.herbivore.Horse;
import entity.creature.animal.herbivore.Mouse;
import entity.creature.animal.herbivore.Rabbit;
import entity.creature.animal.herbivore.Sheep;
import java.util.HashMap;
import java.util.Map;

public class Bear extends Predator {

  private static final Map<Class<? extends Creature>, Double> BEAR_EATING_PROBABILITIES = new HashMap<>();

  static {
    BEAR_EATING_PROBABILITIES.put(Boa.class, 0.80);
    BEAR_EATING_PROBABILITIES.put(Horse.class, 0.40);
    BEAR_EATING_PROBABILITIES.put(Deer.class, 0.80);
    BEAR_EATING_PROBABILITIES.put(Rabbit.class, 0.80);
    BEAR_EATING_PROBABILITIES.put(Mouse.class, 0.90);
    BEAR_EATING_PROBABILITIES.put(Goat.class, 0.70);
    BEAR_EATING_PROBABILITIES.put(Sheep.class, 0.70);
    BEAR_EATING_PROBABILITIES.put(Boar.class, 0.50);
    BEAR_EATING_PROBABILITIES.put(Buffalo.class, 0.20);
    BEAR_EATING_PROBABILITIES.put(Duck.class, 0.10);
  }

  public Bear(Location location) {
    super(location);
    this.weight = 500;
    this.satiety = 80;
    this.maxSatiety = 80;
    this.maxPopulationPerCell = 5;
    this.movementSpeed = 2;

  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    return BEAR_EATING_PROBABILITIES.getOrDefault(creature.getClass(), 0.0);
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Animal victim) {
      return (int) victim.weight;
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    return new Bear(location);
  }


  public String getSymbol() {
    return "🐻";
  }
}

