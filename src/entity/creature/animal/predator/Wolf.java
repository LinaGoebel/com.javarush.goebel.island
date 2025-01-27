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

public class Wolf extends Predator {

  private static final Map<Class<? extends Creature>, Double> WOLF_EATING_PROBABILITIES = new HashMap<>();

  static {
    WOLF_EATING_PROBABILITIES.put(Horse.class, 0.10);
    WOLF_EATING_PROBABILITIES.put(Deer.class, 0.15);
    WOLF_EATING_PROBABILITIES.put(Rabbit.class, 0.60);
    WOLF_EATING_PROBABILITIES.put(Mouse.class, 0.80);
    WOLF_EATING_PROBABILITIES.put(Goat.class, 0.60);
    WOLF_EATING_PROBABILITIES.put(Sheep.class, 0.70);
    WOLF_EATING_PROBABILITIES.put(Boar.class, 0.15);
    WOLF_EATING_PROBABILITIES.put(Buffalo.class, 0.10);
    WOLF_EATING_PROBABILITIES.put(Duck.class, 0.40);
  }

  public Wolf(Location location) {
    super(location);

    this.weight = 50.0;
    this.satiety = 8;
    this.maxSatiety = 8;
    this.maxPopulationPerCell = 30;
    this.movementSpeed = 3;
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    return WOLF_EATING_PROBABILITIES.getOrDefault(creature.getClass(), 0.0);
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
    return new Wolf(location);
  }

  @Override
  public String getSymbol() {
    return "🐺";
  }
}