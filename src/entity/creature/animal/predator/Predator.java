package entity.creature.animal.predator;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import java.util.HashMap;
import java.util.Map;

class Predator extends Animal {

  protected Map<Class<? extends Creature>, Double> eatingProbabilities = new HashMap<>();

  public Predator(Location location) {

    super(location);
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    return eatingProbabilities.getOrDefault(creature.getClass(), 0.0);
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    // Для животного — условно равен его весу (округлим до int)
    if (creature instanceof Animal a) {
      return (int) a.weight;
    }
    // Для растений — пусть равно их nutritionValue
    if (creature instanceof Plant p) {
      return p.getNutritionValue();
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    // Дефолтная реализация (возвращаем null).
    // Конкретные хищники переопределят под себя
    return null;
  }

  @Override
  public String getSymbol() {
    return "P"; // Поскольку абстрактный тип хищника
  }
}