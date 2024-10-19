package ru.safiullina;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

import static ru.safiullina.CSVOperations.parseCSV;
import static ru.safiullina.JSONOperations.*;
import static ru.safiullina.XMLOperations.parseXML;

public class Main {
    public static void main(String[] args) throws IOException, SAXException {

        // ЗАДАЧА 1
        // Описание колонок в файле csv
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        // Имя файла с данными, файл в корне проекта
        String fileName = "data.csv";
        // Вызываем парсер CSV. Получить список сотрудников
        List<Employee> list = parseCSV(columnMapping, fileName);
        // Запишем объекты Employees в JSON-файл
        writeEmployeesToJsonFile(list, "data.json");

        // ЗАДАЧА 2
        // Теперь берём имя файла XML
        fileName = "data.xml";
        // Вызываем парсер XML
        list = parseXML(fileName);
        // Запишем объекты Employees в JSON-файл
        writeEmployeesToJsonFile(list, "data2.json");

        // ЗАДАЧА 3
        // Получение JSON из файла
        String json = readString("new_data.json");
        // Прочитанный JSON необходимо преобразовать в список сотрудников
        List<Employee> listEmployees = jsonToList(json);
        // Выведите содержимое полученного списка в консоль
        listEmployees.forEach(System.out::println);

    }

    private static void writeEmployeesToJsonFile(List<Employee> list, String fileName) {
        // Преобразуем список объектов в строку с JSON разметкой
        String json = listToJson(list);
        // Запишем JSON в файл
        writeString(json, fileName);
    }

}