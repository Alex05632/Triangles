package triangles;

import billets.Polygon;

public class VersatileTriangleMaker implements TriangleFactory {
    @Override
    public Polygon getTriangle() {
        System.out.println("Получили разносторонний треугольник");
        return new Polygon(CreateTriangle.vertex);
    }
}
