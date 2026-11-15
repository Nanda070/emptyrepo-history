import shape.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Facade class for handling multiple shapes.
 */
public class Shapes {
    /**
     * ArrayList of Shape objects
     */
    private ArrayList<Shape> shapes;

    /**
     * Constructor that sets shapes to a new {@code ArrayList<Shape>}.
     */
    public Shapes() {
        shapes = new ArrayList<>();
    }

    /**
     * Creates and adds Shapes to {@code shapes} by reading the specified file.
     * @param fileName name of the file to read shapes from
     * @throws IllegalArgumentException if file contains an unrecognized shape identifier
     * @throws FileNotFoundException if file specified in {@code fileName} is not found
     * @throws InvalidFormatException if data in the file is not in the correct format
     * @throws UnknownShapeTypeException if an unknown shape identifier is encountered during parsing
     */
    public void readShapes(String fileName) throws IllegalArgumentException, FileNotFoundException, InvalidFormatException, UnknownShapeTypeException {
        int row = 1;
        int col = 1;

        try (Scanner scanner = new Scanner(new File(fileName))) {
            int shapeCount = scanner.nextInt();
            if (scanner.hasNext()) scanner.nextLine();

            for (int i = 0; i < shapeCount; i++) {
                // проверяет, действительно ли сканер имеет больше данных для чтения
                if (scanner.hasNext()) {

                    Scanner lineScanner = new Scanner(scanner.nextLine());

                    // символ, обозначающий тип фигуры
                    char shapeID = lineScanner.next().charAt(0);

                    col++;

                    // x центральной точки
                    int x = lineScanner.nextInt();
                    col++;

                    // y центральной точки
                    int y = lineScanner.nextInt();
                    col++;

                    // радиус или длина стороны
                    int measure = lineScanner.nextInt();
                    col++;

                    // в - круг
                    // s - квадрат
                    // т - равносторонний треугольник
                    // h - правильный шестиугольник
                    switch (shapeID) {
                        case 'c' -> this.shapes.add(new Circle(x, y, measure));
                        case 's' -> this.shapes.add(new Square(x, y, measure));
                        case 't' -> this.shapes.add(new EquilateralTriangle(x, y, measure));
                        case 'h' -> this.shapes.add(new RegularHexagon(x, y, measure));
                        default -> throw new UnknownShapeTypeException("Unknown shape identifier in row: " + row);
                    }
                    col = 1;
                    row++;
                }
            }
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            throw new InvalidFormatException("Invalid format in row: " + row + ", column: " + col);
        }
    }

    /**
     * Selects shape with the biggest area bounding rectangle from the specified.
     * @return Reference to the shape with the biggest area bounding rectangle. if {@code shapes} is empty null is returned.
     */
    public Shape getShapeWithBABR() {
        return this.shapes.stream().max(Comparator.comparing(Shape::boundingArea)).orElse(null);
    }

    /**
     * Utility method for testing.
     * @param n index
     * @return nth element of shapes
     */
    public Shape getNthShape(int n) {
        return this.shapes.get(n);
    }
}
