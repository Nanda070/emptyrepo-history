class Id {
    // static = метод/переменная/класс уровня класса
    private int id;
    private static int id_count;

    public Id () {
        this.id = ++id_count;
    }
}
