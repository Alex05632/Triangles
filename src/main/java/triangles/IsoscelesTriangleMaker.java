package triangles;

import billets.Polygon;

public class IsoscelesTriangleMaker implements TriangleFactory {
    @Override
    public Polygon getTriangle() {
        System.out.println("Получили равнобедренный треугольник");
        return new Polygon(CreateTriangle.vertex);
    }
}
