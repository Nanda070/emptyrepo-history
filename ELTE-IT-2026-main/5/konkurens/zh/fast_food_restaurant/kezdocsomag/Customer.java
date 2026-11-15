import java.util.concurrent.ThreadLocalRandom;

public class Customer extends Thread {
    private static final String[] GIVEN_NAMES = {"János", "József", "András", "Pál", "Péter", "Zoltán", "Béla", "László", "Géza", "Tamás", "Mihály", "Miklós", "Imre", "Dániel",
                                                 "Mária", "Anna", "Judit", "Zsófia", "Zsuzsanna", "Barbara", "Ilona", "Anikó", "Enikő", "Anita", "Andrea", "Orsolya", "Katalin", "Júlia"};
    private static final String[] FAMILY_NAMES = {"Nagy", "Kiss", "Kovács", "Szabó", "Tóth", "Németh", "Pintér", "Fazekas", "Ács", "Végh", "Tímár"};
    // Раскомментируйте задачу 2:
    // Private static Final String[] FOOD_NAMES = {"гамбургер", "хот-дог", "салат", "жареное мясо", "сэндвич"};

    public Customer() {
        super(FAMILY_NAMES[ThreadLocalRandom.current().nextInt(FAMILY_NAMES.length)] + " " +
              GIVEN_NAMES[ThreadLocalRandom.current().nextInt(GIVEN_NAMES.length)]);
    }

    @Override
    public void run() {
        System.out.println(getName() + ": Éhes vagyok, ennék valamit. Bemegyek ebbe az étterembe, beállos a sorba, remélem, hogy hamar sorra kerülök.");

        // TODO1: Давайте встанем в очередь!

        // Уберите комментарий к заданию 1:
        // System.out.println(getName() + ": я стою в очереди и жду, пока заберут мой заказ.");

        // TODO2: Подождем, пока они заберут наш заказ!

        // Уберите комментарий к заданию 2:
        // Строка RequestFood = FOOD_NAMES[ThreadLocalRandom.current().nextInt(FOOD_NAMES.length)];
        // System.out.println(getName() + ": Мне хотелось бы вот это: a " + RequestFood + "!");

        // ЗАДАЧА3: Давайте создадим будущую строку символов, которая будет содержать название готового блюда!
        // Уберите комментарий к заданию 3:
        // мытьРуки();
    
        // TODO3: Листинг:
        // TODO3: System.out.println(getName() + ": Это " + <название готовой еды> + " очень вкусно!");
    }   
    
    // Уберите комментарий к заданию 3:
    // частная пустотаwashHands() {
    // System.out.println(getName() + ": я мою руки перед едой.");
    // пытаться {
    // Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
    // } catch (InterruptedException e) {
    // // Нам не следует сюда заходить
    // е.printStackTrace();
    // возвращаться;
    //     }
    // }
}
