public class LineMain {
    public static void main(String[] args) {
        Line l1 = new Line(1, -1, 0);

        Point origin = new Point(0, 0);
        Point p1 = new Point(5, 3);

        System.out.println("Contains:");
        System.out.println(l1.contains(origin));
        System.out.println(l1.contains(p1));

        Line l2 = new Line(1, 1, 0);
        Line l3 = new Line(-1, -1, 0);
        Line l4 = new Line(1, -1, 5);
        Line l5 = new Line(2, 3, 9);

        System.out.println("l1:");
        System.out.println(l1.isParellelWith(l1)); // не с самим собой
        System.out.println(l1.isParellelWith(l2)); // нет
        System.out.println(l1.isParellelWith(l3)); // нет
        System.out.println(l1.isParellelWith(l4)); // снова
        System.out.println(l1.isParellelWith(l5)); // нет

        System.out.println();

        System.out.println(l1.isOrthogonalTo(l1)); // сам
        System.out.println(l1.isOrthogonalTo(l2)); // снова
        System.out.println(l1.isOrthogonalTo(l3)); // снова
        System.out.println(l1.isOrthogonalTo(l4)); // нет
        System.out.println(l1.isOrthogonalTo(l5)); // нет

        System.out.println("l2:");
        System.out.println(l2.isParellelWith(l1)); // нет
        System.out.println(l2.isParellelWith(l2)); // сам
        System.out.println(l2.isParellelWith(l3)); // сам
        System.out.println(l2.isParellelWith(l4)); // нет
        System.out.println(l2.isParellelWith(l5)); // нет

        System.out.println();

        System.out.println(l2.isOrthogonalTo(l1)); // снова
        System.out.println(l2.isOrthogonalTo(l2)); // сам
        System.out.println(l2.isOrthogonalTo(l3)); // сам
        System.out.println(l2.isOrthogonalTo(l4)); // снова
        System.out.println(l2.isOrthogonalTo(l5)); // нет
    }
}
