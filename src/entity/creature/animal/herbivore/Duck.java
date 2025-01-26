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
    this.maxSatiety = 1; // Максимальная сытость
    this.satiety = 1;    // Начальная сытость
    this.maxPopulationPerCell = 200;
    this.movementSpeed = 4; // Перемещение на 4 клетки
  }

  @Override
  public boolean eat(Creature creature) {
    // Ест растения как обычное травоядное
    if (creature instanceof Plant) {
      super.eat(creature);
    }

    // Дополнительно ест гусениц
    if (creature instanceof Caterpillar) {
      double eatProb = 0.90; // Вероятность из таблицы
      if (ThreadLocalRandom.current().nextDouble() < eatProb) {
        this.satiety++; // Утка получает сытость
        if (this.satiety > this.maxSatiety) {
          this.satiety = this.maxSatiety;
        }
      }
    }
    return false;
  }

  @Override
  protected double calculateEatProbability(Creature creature) {
    // Для растений 100% вероятность
    if (creature instanceof Plant) {
      return 1.0;
    }
    // Для гусениц 90% вероятность
    if (creature instanceof Caterpillar) {
      return 0.90;
    }
    return 0.0; // Ничего другого не ест
  }

  @Override
  protected int calculateNutritionValue(Creature creature) {
    if (creature instanceof Plant plant) {
      int value = Math.min(plant.getQuantity(), 1); // Ест 1 единицу растения
      plant.reduce(value);
      return value;
    } else if (creature instanceof Caterpillar) {
      return 1; // Гусеница добавляет 1 единицу сытости
    }
    return 0;
  }

  @Override
  protected Animal createOffspring(Location location) {
    return new Duck(location);
  }

  public String getSymbol() {
    return "🦆"; // Юникод-символ утки
  }
}