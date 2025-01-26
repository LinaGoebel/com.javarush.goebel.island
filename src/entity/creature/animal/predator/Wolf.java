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

  // Карта вероятностей поедания — может быть заполнена из настроек или статически
  private static final Map<Class<? extends Creature>, Double> WOLF_EATING_PROBABILITIES = new HashMap<>();

  static {
    // Заполнение на основе таблицы: (примерно)
    // Волк может съесть:
    // Horse (лошадь) - 10%
    // Deer (олень) - 15%
    // Rabbit (кролик) - 60%
    // Mouse (мышь) - 80%
    // Goat (коза) - 60%
    // Sheep (овца) - 70%
    // Boar (кабан) - 15%
    // Buffalo (буйвол) - 10%
    // Duck (утка) - 40%
    // Caterpillar (гусеница) - 0%
    // Растения волк не ест, поэтому 0%
    WOLF_EATING_PROBABILITIES.put(Horse.class, 0.10);
    WOLF_EATING_PROBABILITIES.put(Deer.class, 0.15);
    WOLF_EATING_PROBABILITIES.put(Rabbit.class, 0.60);
    WOLF_EATING_PROBABILITIES.put(Mouse.class, 0.80);
    WOLF_EATING_PROBABILITIES.put(Goat.class, 0.60);
    WOLF_EATING_PROBABILITIES.put(Sheep.class, 0.70);
    WOLF_EATING_PROBABILITIES.put(Boar.class, 0.15);
    WOLF_EATING_PROBABILITIES.put(Buffalo.class, 0.10);
    WOLF_EATING_PROBABILITIES.put(Duck.class, 0.40);
    // Остальные, не входящие в мапу, по умолчанию получат 0.0
  }

  public Wolf(Location location) {
    super(location);
    // Сетап характеристик
    this.weight = 50.0;        // Вес волка (кг)
    this.satiety = 8;         // Текущее значение сытости
    this.maxSatiety = 8;      // Макс. сытость
    this.maxPopulationPerCell = 30;
    this.movementSpeed = 3;   // Макс. скорость перемещения (клеток/ход)
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    // Ищем вероятность в таблице WOLF_EATING_PROBABILITIES
    return WOLF_EATING_PROBABILITIES.getOrDefault(creature.getClass(), 0.0);
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    // Если съедено животное, вернём целочисленную часть от его веса
    if (creature instanceof Animal victim) {
      return (int) victim.weight;
    }
    // Волк не ест растения, так что 0
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    // Создаём волчонка в той же локации
    return new Wolf(location);
  }

  @Override
  public String getSymbol() {
    // Unicode-символ волка
    return "🐺";
  }
}