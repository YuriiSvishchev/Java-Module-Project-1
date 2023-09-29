import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("На скольких человек необходимо разделить счёт ?");

            int number;
            while (true) {
                number = getNumber(scanner);

                if (number == 1) {
                    System.out.println("Ошибка! Один гость, нет смысла считать.");
                    System.out.println("На скольких человек необходимо разделить счёт ?");

                } else if (number < 1) {
                    System.out.println("Ошибка! Некорректное значение для подсчёта.");
                    System.out.println("На скольких человек необходимо разделить счёт ?");

                } else {
                    System.out.println("Количество гостей: " + number + "\n");
                    break;
                }
            }
            System.out.println("Укажите название товара и через пробел его стоимость");


            String choice;
            scanner.nextLine();
            List<Menu> list = new ArrayList<>();

            while (true) {
                choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("Завершить")) {
                    break;
                } else  {
                    try {
                        String priceStr = choice.replaceAll("[\\s+а-яА-Я :]", "");
                        double price = Double.parseDouble(priceStr);
                        if (price < 0) {
                            System.out.println("Вы указали отрицательное число. Пожалуйста, введите положительное число.");
                            continue;
                        }
                        String name = choice.replace(priceStr, "");
                        Menu item = new Menu(name, price);
                        list.add(item);
                        System.out.println("Товар успешно добален. Хотите добавить ещё один товар ?");
                        System.out.println("Для завершения процесса добавления товара, введите \"завершить\" ");
                    } catch (Exception e) {
                        System.out.println("Не могу понять, что вы написали. Укажите название товара и через пробел его стоимость");
                    }
                }
            }

            double fullPrice = 0.0;
            StringBuilder check = new StringBuilder();
        check.append("Добаленные товары:\n");
            for (Menu item : list) {
                fullPrice += item.coast;
                check.append(item.name).append(" - ").append(ending(item.coast)).append("\n");
            }
        check.append("Гости должны заплатить по: ").append(ending(fullPrice / number));
            System.out.println(check);

        scanner.close();
        }

        public static int getNumber(Scanner scanner) {
            while (true) {
                if (scanner.hasNextInt()) {
                    return scanner.nextInt();
                } else {
                    scanner.next();
                    System.out.println("Вы ввели не число!");
                }
            }
        }

        public static String ending(double j) {
            double floored = Math.floor(j%10);
            if (floored == 1) {
                return String.format("%.2f", j) + " рубль";
            }
            if (floored == 0) {
                return String.format("%.2f", j) + " рублей";
            }
            else if (floored < 5) {
                return String.format("%.2f", j) + " рубля";
            }
            return String.format("%.2f", j) + " рублей";
        }

    }

