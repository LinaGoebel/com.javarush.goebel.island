package entity.creature.animal.predator;

import entity.Location;
import entity.creature.Creature;

import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.Duck;
import entity.creature.animal.herbivore.Mouse;
import entity.creature.animal.herbivore.Rabbit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Eagle extends Predator {
  private static final Map<Class<? extends Creature>, Double> EAGLE_EATING_PROBABILITIES = new HashMap<>();

  static {
    EAGLE_EATING_PROBABILITIES.put(Fox.class, 0.10);
    EAGLE_EATING_PROBABILITIES.put(Rabbit.class, 0.90);
    EAGLE_EATING_PROBABILITIES.put(Mouse.class, 0.90);
    EAGLE_EATING_PROBABILITIES.put(Duck.class, 0.80);
  }
  public Eagle(Location location) {
    super(location);
    this.weight = 6;
    this.satiety = 1;
    this.maxSatiety = 1;
    this.maxPopulationPerCell = 20;
    this.movementSpeed = 3;
  }

  protected double calculateEatProbability(Creature creature) {
    // Возвращаем вероятность из таблицы или 0.0, если существа нет в карте
    return EAGLE_EATING_PROBABILITIES.getOrDefault(creature.getClass(), 0.0);
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    // Если съедено животное, возвращаем его вес
    if (creature instanceof Animal victim) {
      return (int) victim.weight;
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {

    return new Eagle(location);
  }

  public String getSymbol() {
    return "🦅";
  }
}


