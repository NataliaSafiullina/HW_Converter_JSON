package ru.safiullina;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLOperations {
    /**
     * Метод parseXML получает список сотрудников из файла csv
     * @param xmlName, имя файла
     * @return listEmployees, возвращает список сотрудников
     * @throws IOException
     * @throws SAXException
     */
    public static List<Employee> parseXML(String xmlName) throws IOException, SAXException {

        List<Employee> listEmployees = new ArrayList<>();

        try {
            // Конфигурируем фабрику, которая будет читать документ (ДОМ)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Создаем билдер
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Получаем объект документ
            Document doc = builder.parse(new File(xmlName));

            Node root = doc.getDocumentElement();
            read(root, listEmployees);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
        return listEmployees;
    }

    /**
     * Метод проходится по списку узлов и получает из employees Element.
     * У элементов получает значения, с помощью которых создает экземпляр класса Employee.
     * @param node
     * @param list
     */
    private static void read(Node node, List<Employee> list) {
        // Извлекаем списка дочерних узлов
        NodeList nodeList = node.getChildNodes();
        // Запускаем цикл по всем дочерним элементам
        for (int i = 0; i < nodeList.getLength(); i++) {
            // Для каждого узла получаем тип узла и проверяем равен ли его тип "элемент"
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                // Получаем имя текущего узла
                String nodeName = node_.getNodeName();
                if (nodeName.equals("employee")) {
                    // Для узла employee получаем элемент
                    Element element = (Element) node_;
                    // Создаем объект сотрудник
                    Employee employee = new Employee(
                            Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()),
                            element.getElementsByTagName("firstName").item(0).getTextContent(),
                            element.getElementsByTagName("lastName").item(0).getTextContent(),
                            element.getElementsByTagName("country").item(0).getTextContent(),
                            Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent())
                    );
                    // Добавляем сотрудника в список
                    list.add(employee);
                }
                read(node_, list);
            }
        }
    }
}
