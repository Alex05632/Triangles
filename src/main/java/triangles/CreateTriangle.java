package triangles;

import billets.Polygon;
import billets.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.JSONObject;
import programExceptions.FileIsNotValidated;

import static parser.ParserJSON.*;


public class CreateTriangle {

    public final static int NUMBER_OF_VERTICES = 3;
    public final static int NUMBER_OF_COORDS = 2;
    public final static String FIGURE = "треугольник";

    private static final Logger LOG = LoggerFactory.getLogger(CreateTriangle.class);

    static Vertex[] vertex;

    public Polygon createTriangle(JSONObject json) throws FileIsNotValidated {

        Vertex[] vertex = ParseJSON(json, NUMBER_OF_VERTICES, NUMBER_OF_COORDS, FIGURE);
        this.vertex = vertex;

        TriangleFactory triangle;

        double l1 = Math.sqrt(Math.abs((vertex[1].x - vertex[0].x) * (vertex[1].x - vertex[0].x)) + Math.abs((vertex[1].y - vertex[0].y) * (vertex[1].y - vertex[0].y)));
        double l2 = Math.sqrt(Math.abs((vertex[2].x - vertex[0].x) * (vertex[2].x - vertex[0].x)) + Math.abs((vertex[2].y - vertex[0].y) * (vertex[2].y - vertex[0].y)));
        double l3 = Math.sqrt(Math.abs((vertex[2].x - vertex[1].x) * (vertex[2].x - vertex[1].x)) + Math.abs((vertex[2].y - vertex[1].y) * (vertex[2].y - vertex[1].y)));

        if (l1 < l2 + l3 && l2 < l1 + l3 && l3 < l1 + l2)   // если треугольник
        {
            //ввел погрешность 0.1 для равностороннего треугольника из-за int-овых координат вершин
            if (Math.abs(l1 - l2) <= 0.1 && Math.abs(l2 - l3) <= 0.1) {
                //Равносторонний треугольник
                triangle = new EquilateralTriangleMaker();
            } else if (Math.abs(l1 - l2) == 0 || Math.abs(l1 - l3) == 0 || Math.abs(l2 - l3) == 0) {
                //Равнобедренный треугольник
                triangle = new IsoscelesTriangleMaker();
            } else {
                //Разносторонний треугольник
                triangle = new VersatileTriangleMaker();
            }
        } else {
            LOG.info("Невозможно построить треугольник. Точки с заданными координатами совпадают, либо лежат на одной прямой");
            throw new FileIsNotValidated("Невозможно построить треугольник. Точки с заданными координатами совпадают, либо лежат на одной прямой");
        }
        return triangle.getTriangle();
    }

    public boolean isTriangleGet(Polygon polygon) {
        return polygon != null ? true : false;
    }
}