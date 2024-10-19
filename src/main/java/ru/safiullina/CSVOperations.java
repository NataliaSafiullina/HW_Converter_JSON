package ru.safiullina;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVOperations {
    /**
     * Метод parseCSV получает список сотрудников из файла csv
     * @param columnMapping, список колонок для стратегия мапинга
     * @param fileName, имя файла
     * @return - возвращает список сотрудников
     */
    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        // Создаем экземпляр CSVReader
        // Разделитель по умолчанию - запятая
        // Символ выражения по умолчанию - двойные кавычки
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {

            // Объявляем стратегию мапинга
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            // Стратегия - дженерик, задаём тип равный нашему классу
            strategy.setType(Employee.class);
            // Задаём стратегию мапинга, последовательно указываем имена полей класса
            strategy.setColumnMapping(columnMapping);
            // С помощью билдера создаём объект класса десериализации
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            // Метод parse() парсит строчки и возвращает список объектов
            return csv.parse();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
