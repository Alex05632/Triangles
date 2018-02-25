package parser;

import billets.Vertex;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import programExceptions.FileIsNotValidated;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ParserJSON {

    private static final Logger LOG = LoggerFactory.getLogger(ParserJSON.class);

    public static Vertex[] ParseJSON(JSONObject json, int numberOfVertices, int numberOfCoords, String figure) throws FileIsNotValidated {

        JSONArray ArrayOfVertices;
        List<String> arList = new ArrayList<>();
        JSONArray vertex;
        Vertex[] vertexArray;
        boolean flag = true;

        if (json.isEmpty()) {
            LOG.info("Ошибка! Проверьте структуру JSON файла: файл пустой.");
            throw new FileIsNotValidated("Ошибка! Проверьте структуру JSON файла: файл пустой.");
        }

        try {
            if (((JSONArray) json.get("vertices")).size() == 0) {
                LOG.info("Ошибка! Проверьте структуру JSON файла: массив вершин пуст.");
                throw new FileIsNotValidated("Ошибка! Проверьте структуру JSON файла: массив вершин пуст.");
            }

            ArrayOfVertices = (JSONArray) json.get("vertices");

        } catch (NullPointerException e) {
            LOG.error("Ошибка! Проверьте структуру JSON файла: отсутствует массив координат точек с заданным названием \"vertices\".", e);
            throw new FileIsNotValidated("Ошибка! Проверьте структуру JSON файла: отсутствует массив координат точек с заданным названием \"vertices\".");

        }


        if (ArrayOfVertices.size() != numberOfVertices) {
            LOG.info("Ошибка! Невозможно построить " + figure + ". Количество вершин (" + ArrayOfVertices.size() + ") не равно " + numberOfVertices + ".");
            throw new FileIsNotValidated("Ошибка! Невозможно построить " + figure + ". Количество вершин (" + ArrayOfVertices.size() + ") не равно " + numberOfVertices + ".");
        }

        vertexArray = new Vertex[numberOfVertices];

        for (int i = 0; i < ArrayOfVertices.size(); i++) {

            try {
                vertex = (JSONArray) ArrayOfVertices.get(i);
            } catch (ClassCastException e) {
                //System.out.println("Ошибка! Невозможно привести JSON элемент (" + (i+1) + ") к массиву");
                arList.add("Ошибка! Невозможно привести JSON элемент (" + (i + 1) + ") к массиву.\n");
                flag = false;
                continue;
            }

            if (vertex.size() != numberOfCoords) {
                //System.out.println("Ошибка! Невозможно построить " + figure + " в " + numberOfCoords +"-мерном пространстве. Количество координат (" + vertex.size() + ") вершины " + (i+1) + " не равно " + numberOfCoords +".");
                arList.add("Ошибка! Невозможно построить " + figure + " в " + numberOfCoords + "-мерном пространстве. Количество координат (" + vertex.size() + ") вершины " + (i + 1) + " не равно " + numberOfCoords + ".\n");
                flag = false;
                continue;
            }

            /* Если изменить конструктор класса Vertex так, чтобы он принимал на вход коллекцию/массив, то можно
               написать универсальный код для n-мерного пространства координат вместо костыля ниже*/

            Iterator<Long> iteratorCoords = vertex.iterator();
            vertexArray[i] = new Vertex(iteratorCoords.next().intValue(), iteratorCoords.next().intValue());
        }

        if (!flag) {
            LOG.info(arList.toString());
            throw new FileIsNotValidated(arList.toString());
        }

        return vertexArray;

    }
}



