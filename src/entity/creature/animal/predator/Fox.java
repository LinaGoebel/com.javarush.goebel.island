package entity.creature.animal.predator;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.Caterpillar;
import entity.creature.animal.herbivore.Duck;
import entity.creature.animal.herbivore.Mouse;
import entity.creature.animal.herbivore.Rabbit;

import java.util.HashMap;
import java.util.Map;

public class Fox extends Predator {

  private static final Map<Class<? extends Creature>, Double> FOX_EATING_PROBABILITIES = new HashMap<>();

  static {
    FOX_EATING_PROBABILITIES.put(Rabbit.class, 0.70);
    FOX_EATING_PROBABILITIES.put(Mouse.class, 0.90);
    FOX_EATING_PROBABILITIES.put(Duck.class, 0.60);
    FOX_EATING_PROBABILITIES.put(Caterpillar.class, 0.40);
  }

  public Fox(Location location) {
    super(location);
    this.weight = 8;
    this.satiety = 2;
    this.maxSatiety = 2;
    this.maxPopulationPerCell = 30;
    this.movementSpeed = 2;
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    return FOX_EATING_PROBABILITIES.getOrDefault(creature.getClass(), 0.0);
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
    return new Fox(location);
  }

  @Override
  public String getSymbol() {
    return "🦊";
  }
}