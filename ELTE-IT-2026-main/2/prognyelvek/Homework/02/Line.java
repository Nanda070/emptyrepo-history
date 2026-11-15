class Line {
    // топор + по = с
    double a;
    double b;
    double c;

    Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean contains(Point p) {
        return this.a * p.x + this.b * p.y == c;
    }

    public boolean isParellelWith(Line l) {
        // их наклоны одинаковы, но c разное
        return (this.a / this.b == l.a / l.b) && (this.c != l.c);
    }

    public boolean isOrthogonalTo(Line l) {
        // наклоны обратны друг другу (-1).
        return (this.a / this.b == (-1) / (l.a / l.b));
    }
}
