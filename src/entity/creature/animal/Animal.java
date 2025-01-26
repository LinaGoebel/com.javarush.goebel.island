package entity.creature.animal;

import entity.Island;
import entity.Location;
import entity.creature.Creature;
import entity.creature.plant.Plant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Creature {

  protected int satiety;              // Текущая сытость
  protected int maxSatiety;           // Максимальная сытость
  protected int maxPopulationPerCell; // Максимальная популяция на клетке
  protected int movementSpeed;        // Скорость перемещения

  // Текущее местоположение животного
  protected Location location;
  private boolean isDead = false;     // Флаг, указывающий, живо ли животное

  // Конструктор с начальной локацией
  public Animal(Location location) {
    this.location = location;
    if (location != null) {
      location.addAnimal(this);
    }
  }

  /**
   * Уменьшение сытости. Если сытость достигает 0, животное умирает.
   */
  public void decreaseSatiety() {
    this.satiety -= 1; // Уменьшаем сытость на 1
    if (this.satiety <= 0) {
      this.satiety = 0;
      this.die(); // Умираем, если голодны
    }
  }

  /**
   * Проверяет, мертво ли животное.
   * @return true, если животное мертво, иначе false.
   */
  public boolean isDead() {
    return isDead;
  }

  /**
   * Пытается съесть указанное существо.
   * @return true, если еда была успешно съедена, иначе false.
   */
  public boolean eat(Creature creature) {
    if (isDead) return false; // Мёртвое животное не может есть

    if (creature instanceof Plant) {
      Plant plant = (Plant) creature;
      int foodConsumed = Math.min(plant.getQuantity(), this.maxSatiety - this.satiety);
      if (foodConsumed > 0) {
        plant.reduce(foodConsumed); // Уменьшаем количество растения
        this.satiety += foodConsumed; // Увеличиваем сытость
        System.out.println(this.getSymbol() + " съел растение.");
        return true; // Успешно съели растение
      }
    } else if (creature instanceof Animal prey) {
      double eatProbability = calculateEatProbability(prey);
      if (ThreadLocalRandom.current().nextDouble() < eatProbability) {
        this.satiety += prey.weight;
        if (this.satiety > this.maxSatiety) {
          this.satiety = this.maxSatiety; // Ограничиваем максимальную сытость
        }
        System.out.println(this.getSymbol() + " съел " + prey.getSymbol());
        prey.die(); // Жертва умирает
        return true; // Успешно съели животное
      }
    }
    return false; // Не удалось съесть
  }

  /**
   * Перемещение животного на соседнюю локацию.
   */
  public void move(Island island) {
    if (isDead || location == null) return; // Мёртвое животное не может двигаться

    // Получаем соседние локации
    List<Location> neighbors = island.getNeighbors(location.getX(), location.getY());
    if (!neighbors.isEmpty()) {
      Location target = neighbors.get(ThreadLocalRandom.current().nextInt(neighbors.size()));
      location.removeAnimal(this); // Убираем животное из текущей локации
      target.addAnimal(this);      // Добавляем в новую локацию
      this.location = target;      // Обновляем текущую локацию
    }
  }

  /**
   * Размножение животного, если условия соблюдены.
   */
  public Animal reproduce() {
    if (isDead || location == null) return null;

    int sameSpeciesCount = 0;
    for (Animal animal : location.getAnimals()) {
      if (animal.getClass() == this.getClass()) {
        sameSpeciesCount++;
      }
    }

    // Если есть пара, с 80% вероятностью размножаемся
    if (sameSpeciesCount > 1 && ThreadLocalRandom.current().nextDouble() < 0.8) {
      Animal offspring = createOffspring(location); // Создаём потомка
      System.out.println(this.getSymbol() + " размножился!");
      return offspring;
    }
    return null;
  }

  /**
   * Удаляет животное из текущей локации и помечает его как мёртвое.
   */
  public void die() {
    if (location != null) {
      location.removeAnimal(this); // Убираем из текущей локации
      location = null;             // Очищаем ссылку на локацию
    }
    isDead = true; // Помечаем как мёртвое
  }

  // Абстрактные методы, которые необходимо реализовать в наследниках
  protected abstract double calculateEatProbability(Creature creature);

  protected abstract int calculateNutritionValue(Creature creature);

  protected abstract Animal createOffspring(Location location);

  public abstract String getSymbol();
}
