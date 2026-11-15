import java.util.*;

public class Magic {

public static LinkedList<Integer> divisors(int num) {
    LinkedList<Integer> result = new LinkedList<>();
    for (int i = 1; i<=num; ++i) {
        if (num % i == 0) {
            result.add(i);
        }
    }
    return result;
}

public static ArrayList<String> getStrSameBeginningAndEnding(ArrayList<String> arrayList) {
    // лямбда-функции и функциональные элементы
    return arrayList.stream().filter(str -> null != str && str.length() > 0 && str.charAt(0) == str.charAt(str.length()-1)).collect(ArrayList :: new, ArrayList :: add, ArrayList :: addAll);
    // .stream() не работает с исходным объектом => исходный ArrayList не меняется
}


public static void removeStrDifferBeginningAndEnding(ArrayList<String> arrayList) {
    arrayList.removeIf(str -> null != str && str.length() > 0 && str.charAt(0) == str.charAt(str.length()-1));
}

public static void minToFront(ArrayList<Integer> al) {
    if (al.isEmpty()) {
        throw new IllegalArgumentException();
    }
    int min = Collections.min(al);
    // интервал мин = al.min();
    al.remove(min);
    al.add(min,0);
}

public static ArrayList<String> onlyEven(ArrayList<String> arrayList) {
    ArrayList<String> result = new ArrayList<>();
    for (String str : arrayList) {
        if (str.length() % 2 == 0) {
            result.add(str);
        }
    }
    return result;
}




    public static void main(String[] args) {
        //LinkedList<Integer> lL = новый LinkedList<>();
        //дженерики можно использовать только со ссылочными типами
        //если все еще нужно быть примитивным, поможет класс-обертка
        //pl целое число

        System.out.println(divisors(42));

        //HashMap<String, Integer> multiSet = new HashMap<>();
        //два параметра типа ключ-значение

        //this.hm = новый HashMap<>(multiSet);

    }
}