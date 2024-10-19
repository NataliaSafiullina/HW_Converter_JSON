package ru.safiullina;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JSONOperations {

    /**
     * Метод listToJson полученный список преобразует в строчку в формате JSON
     * @param list список объектов Java
     * @return строку String
     */
    public static String listToJson(List<Employee> list) {
        // Создаём билдер для json
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        // Получить JSON из экземпляра класса
        return gson.toJson(list);
    }

    /**
     * Метод writeString записывает полученную в виде строки структуру JSON в файл
     * @param json тип String
     * @param fileName тип String, имя файла
     */
    public static void writeString(String json, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод получает JSON из файла с использованием BufferedReader и FileReader.
     * Метод возвращает прочитанный из файла JSON типа String.
     * @param fileName тип String
     * @return String jsonString
     */
    public static String readString(String fileName) {
        // Сделаем строку-конструктор для более оптимизированного добавления данных в конец
        StringBuilder jsonString = new StringBuilder();
        // Читаем файл в созданный буфер
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            String s = null;
            // Считываем данные из буфера по-строчно
            while ((s = buffer.readLine()) != null) {
                // Добавляем строку из файла к строке-конструктору
                jsonString.append(s);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // Преобразуем строку-конструктор к обычной строке и возвращаем её
        return jsonString.toString();
    }

    /**
     * Метод преобразует JSON-строку в список сотрудников
     * @param jsonString тип String
     * @return list тип список объектов класса Employee
     */
    public static List<Employee> jsonToList(String jsonString) {
        // GsonBuilder используется исключительно для создания экземпляра Gson
        GsonBuilder builder = new GsonBuilder();
        // Объект gson нужен для десериализации объектов Java
        Gson gson = builder.create();
        // Тут какая-то магия с типом, так мы определили тип для коллекции при десериализации
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        // Вызываем метод fromJson, который из строки jsonString получает список объектов (без парсера и array)
        List<Employee> employees = gson.fromJson(jsonString, listType);
        return employees;
    }

}
