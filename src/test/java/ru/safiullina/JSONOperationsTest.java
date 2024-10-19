package ru.safiullina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class JSONOperationsTest {

    // Тест чтения строки из файла
    @Test
    void readStringTest() {
        // given
        String original = "[  {    \"id\": 1," +
                "    \"firstName\": \"John\"," +
                "    \"lastName\": \"Smith\"," +
                "    \"country\": \"USA\"," +
                "    \"age\": 25  }," +
                "  {    \"id\": 2," +
                "    \"firstName\": \"Inav\"," +
                "    \"lastName\": \"Petrov\"," +
                "    \"country\": \"RU\"," +
                "    \"age\": 23  }]";
        // when
        String result = JSONOperations.readString("new_data.json");
        // then
        //Assertions.assertEquals(original, result);
        assertThat(original, equalToIgnoringCase(result));

    }

    // Тест чтения строки из файла
    @ParameterizedTest
    @CsvSource(value = {
            "new_data.json; " +
                    "[  {    \"id\": 1," +
                    "    \"firstName\": \"John\"," +
                    "    \"lastName\": \"Smith\"," +
                    "    \"country\": \"USA\"," +
                    "    \"age\": 25  }," +
                    "  {    \"id\": 2," +
                    "    \"firstName\": \"Inav\"," +
                    "    \"lastName\": \"Petrov\"," +
                    "    \"country\": \"RU\"," +
                    "    \"age\": 23  }]",

            "new_data_2.json; {    \"id\": 1," +
                    "    \"firstName\": \"Martin\"," +
                    "    \"lastName\": \"Walker\"," +
                    "    \"country\": \"GB\"," +
                    "    \"age\": 42}"
    }, delimiter = ';')
    void readStringParameterizedTest(String fileName, String jsonString) {
        //Assertions.assertEquals(jsonString, JSONOperations.readString(fileName));
        assertThat(jsonString, equalToIgnoringCase(JSONOperations.readString(fileName)));
    }

    // Тест преобразования строки в список объектов
    @Test
    void jsonToListTest() {
        // given
        Employee employee = new Employee(124578, "Имя", "Фамилия", "RU", 30);
        List<Employee> originalEmployees = new ArrayList<>();
        originalEmployees.add(employee);
        String json = """
                [
                  {
                    "id": 124578,
                    "firstName": "Имя",
                    "lastName": "Фамилия",
                    "country": "RU",
                    "age": 30
                  }
                ]""";
        // when
        List<Employee> resultEmployees = JSONOperations.jsonToList(json);
        // then
//        Assertions.assertEquals(originalEmployees.getFirst().getAge(), resultEmployees.getFirst().getAge());
//        Assertions.assertEquals(originalEmployees.getFirst().getId(), resultEmployees.getFirst().getId());
//        Assertions.assertEquals(originalEmployees.getFirst().getCountry(), resultEmployees.getFirst().getCountry());
//        Assertions.assertEquals(originalEmployees.getFirst().getFirstName(), resultEmployees.getFirst().getFirstName());
//        Assertions.assertEquals(originalEmployees.getFirst().getLastName(), resultEmployees.getFirst().getLastName());

        assertThat(originalEmployees.getFirst(), samePropertyValuesAs(resultEmployees.getFirst()));

    }

    // Тест на заполненность объекта после преобразования строки в объект
    @Test
    void jsonToListTestOnNotEmpty() {
        // given
        String json = """
                [
                  {
                    "id": 124578,
                    "firstName": "Имя",
                    "lastName": "Фамилия",
                    "country": "RU",
                    "age": 30
                  }
                ]""";
        // when
        List<Employee> resultEmployees = JSONOperations.jsonToList(json);
        // then
        assertThat(resultEmployees, not(empty()));
        //assertThat(resultEmployees, empty());

    }


    // Тест преобразования объекта в строку json
    @Test
    void listToJsonTest() {
        // given
        Employee employee = new Employee(124578, "Имя", "Фамилия", "RU", 30);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        String original = """
                [
                  {
                    "id": 124578,
                    "firstName": "Имя",
                    "lastName": "Фамилия",
                    "country": "RU",
                    "age": 30
                  }
                ]""";
        // when
        String result = JSONOperations.listToJson(employees);
        // then
        //Assertions.assertEquals(original, result);
        assertThat(original, is(result));
    }

}