package entity.creature.animal.predator;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.Duck;
import entity.creature.animal.herbivore.Mouse;
import entity.creature.animal.herbivore.Rabbit;
import java.util.HashMap;
import java.util.Map;

public class Boa extends Predator {

  private static final Map<Class<? extends Creature>, Double> BOA_EATING_PROBABILITIES = new HashMap<>();

  static {
    BOA_EATING_PROBABILITIES.put(Fox.class, 0.15);
    BOA_EATING_PROBABILITIES.put(Rabbit.class, 0.2);
    BOA_EATING_PROBABILITIES.put(Mouse.class, 0.4);
    BOA_EATING_PROBABILITIES.put(Duck.class, 0.1);
  }

  public Boa(Location location) {
    super(location);
    this.weight = 15;
    this.satiety = 3;
    this.maxSatiety = 3;
    this.maxPopulationPerCell = 30;
    this.movementSpeed = 1;
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    // Возвращаем вероятность из таблицы или 0.0, если существа нет в карте
    return BOA_EATING_PROBABILITIES.getOrDefault(creature.getClass(), 0.0);
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
    // Создаём нового лисёнка
    return new Boa(location);
  }

  public String getSymbol() {
    return "🐍";
  }
}


