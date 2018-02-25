package triangles;

import billets.Polygon;

public class EquilateralTriangleMaker implements TriangleFactory {
    @Override
    public Polygon getTriangle() {
        System.out.println("Получили равносторонний треугольник");
        return new Polygon(CreateTriangle.vertex);
    }
}
