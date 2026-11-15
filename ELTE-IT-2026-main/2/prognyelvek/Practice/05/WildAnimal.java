// перечисление

/**
 * @since 2022 
 * @author boda
 */

// создание документации
// javadoc -d документ *.java

public enum WildAnimal {
    RACOON("trash", 40),
    ELEPHANT("nuts", 30000),
    MONKE("banana", 420),
    GIRAFFE("leaves",69);

    // последнее ключевое слово: константа (значение указывается только один раз)
    private final String food;
    private final int amount;

    WildAnimal(String food, int amount) {
        this.food = food;
        this.amount = amount;
    }

    // декоратор/аннотация, сигнал переводчику
    // в нашем случае, чтобы что-то перезаписать

    @Override
    public String toString() {
        switch(this) {
            case MONKE: return "monke";
            case GIRAFFE: return "girafee";
            case ELEPHANT: return "elephant";
            case RACOON: return "racoon";
            default: return "animal";
        }
    }

    /**
     * Prints out according to the specification.
     * @return String representation of the enum.
     */


    public static String listAllAnimals() {
        // StringBuilder — более эффективная альтернатива конкатенации.
        StringBuilder sb = new StringBuilder();

        // метод Values ​​возвращает элементы перечисления
        for(WildAnimal animal : WildAnimal.values()) {
            sb.append(animal.ordinal());
            // порядковый метод — это порядковый номер перечисления
            sb.append(": the ").append(animal.toString());
            sb.append(" likes to eat ").append(animal.food).append(" ");
            sb.append(animal.amount).append(" times a week.\n");
        }



        return sb.toString();
    }
}
