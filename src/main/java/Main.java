import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        //опредеение имени для считываемого XML файла:
        String fileName = "data.xml";

        //получение списка сотрудников с помощью parseXML()
        List<Employee> list = parseXML(fileName);

        //преобразование списка сотрудников в строчку в формате JSON
        String json = listToJson(list);

        String jsonFileName = "data2.json";
        //запись строчки JSON c сотрудниками в файл JSON
        writeString(json, jsonFileName);
    }


    private static List<Employee> parseXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        //создадим список объектов Employee для записи в него всех сотрудников из файла fileName
        List<Employee> list = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(fileName));
        //Получим корневой узел документа
        Node root = doc.getDocumentElement();
        //получаем узлы корневого узла
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            //получаем первый дочерний узел
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                //получаем узлы узла employee
                NodeList employeeList = node_.getChildNodes();

                //массив строк для записи значений объекта Employee
                String[] empTextContent = new String[5];
                //счётчик уже найденных значений объекта Employee
                int iEmp = 0;

                for (int j = 0; j < employeeList.getLength(); j++) {
                    Node node_emp = employeeList.item(j);
                    if (Node.ELEMENT_NODE == node_emp.getNodeType()) {
                        //атрибуты employee
                        empTextContent[iEmp] = node_emp.getTextContent();
                        iEmp++;
                    }
                }

                //приводим полученные значения объекта Employee из строки в нужный нам тип
                long id = Long.parseLong(empTextContent[0]);
                String firstName = empTextContent[1];
                String lastName = empTextContent[2];
                String country = empTextContent[3];
                int age = Integer.parseInt(empTextContent[4]);

                //создаём объект класса Employee на основе полученных данных
                Employee emp = new Employee(id, firstName, lastName, country, age);
                //добавляем созданного сотрудника в список
                list.add(emp);
            }
        }
        return list;
    }

    //методов, осуществляющий сериализацию списка сотрудников List<Employee> list в строку в формате JSON
    public static String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        String json = gson.toJson(list, listType);
        return json;
    }

    //метода, записывающий строку json в файл JSON jsonFileName
    public static void writeString(String json, String jsonFileName) {
        try (FileWriter file = new FileWriter(jsonFileName)) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}