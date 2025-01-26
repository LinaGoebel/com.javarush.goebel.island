package entity.creature.plant;

import entity.creature.Creature;

public class Plant extends Creature {

  private int quantity;        // Текущее количество "единиц" растения
  private int growthRate;      // Скорость роста
  private int nutritionValue;  // Пищевая ценность

  // Конструктор с начальными значениями
  public Plant() {
    this.quantity = 1;       // Начальное количество растения
    this.growthRate = 1;     // Скорость роста
    this.nutritionValue = 1; // Пищевая ценность
  }

  // Геттер для пищевой ценности
  public int getNutritionValue() {
    return nutritionValue;
  }

  // Метод роста растения
  public void grow(int rate) {
    this.quantity += rate * this.growthRate; // Увеличиваем количество на основе скорости роста
    if (this.quantity > 20) { // Ограничиваем максимальное количество
      this.quantity = 20;
    }
  }

  // Установка количества "единиц" растения
  public void setQuantity(int quantity) {
    if (quantity < 0) {
      this.quantity = 0; // Нельзя установить отрицательное количество
    } else if (quantity > 20) { // Ограничиваем максимальное значение
      this.quantity = 20;
    } else {
      this.quantity = quantity;
    }
  }

  // Геттер для количества растения
  public int getQuantity() {
    return quantity;
  }

  // Установка скорости роста
  public void setGrowthRate(int growthRate) {
    if (growthRate < 0) {
      this.growthRate = 0; // Нельзя установить отрицательную скорость
    } else {
      this.growthRate = growthRate;
    }
  }

  // Уменьшение количества растения
  public void reduce(int amount) {
    this.quantity -= amount;
    if (this.quantity < 0) {
      this.quantity = 0; // Количество не может быть меньше 0
    }
  }

  public String getSymbol() {
    return "🌱";
  }
}

