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

  // Карта вероятностей поедания для лисы
  private static final Map<Class<? extends Creature>, Double> FOX_EATING_PROBABILITIES = new HashMap<>();

  static {
    // Инициализируем вероятности из вашей таблицы
    FOX_EATING_PROBABILITIES.put(Rabbit.class, 0.70);
    FOX_EATING_PROBABILITIES.put(Mouse.class, 0.90);
    FOX_EATING_PROBABILITIES.put(Duck.class, 0.60);
    FOX_EATING_PROBABILITIES.put(Caterpillar.class, 0.40);
  }

  // Конструктор принимает локацию
  public Fox(Location location) {
    super(location); // Передаём локацию в родительский класс
    this.weight = 8;                 // Вес лисы
    this.satiety = 2;                // Начальная сытость
    this.maxSatiety = 2;             // Максимальная сытость
    this.maxPopulationPerCell = 30;  // Максимум особей на одной клетке
    this.movementSpeed = 2;          // Скорость передвижения (клеток за ход)
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    // Возвращаем вероятность из таблицы или 0.0, если существа нет в карте
    return FOX_EATING_PROBABILITIES.getOrDefault(creature.getClass(), 0.0);
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    // Если съедено животное, возвращаем его вес
    if (creature instanceof Animal victim) {
      return (int) victim.weight;
    }
    // Лиса не ест растения
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    // Создаём нового лисёнка
    return new Fox(location);
  }

  @Override
  public String getSymbol() {
    // Символ лисы для отображения на карте
    return "🦊";
  }
}