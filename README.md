# netology_javacore_specialfiles_XML-JSON
Converting files from XML into JSON
## Задача: XML - JSON парсер
[Ссылка](https://github.com/A-Sakhmina/netology_javacore_specialfiles_XML-JSON/tree/master/src/main/java) на код 
### Задание
Произвести запись в файл JSON объекта, полученного из XML файла `data.xml`.
### Реализация
Метод `parseXML()` возвращет список `Employee` сотрудников из считываемого CSV файла. Принимает **в качестве аргумента**  _стринговое значение `String fileName` 
с названием файла_ с исходными данными `data.xml`. 
Возвращает `List<Employee>`.

Метод `listToJson()` полученный список сотрудников преобразует в строчку в формате JSON. Принимает в качестве аргумента список сотрудников `List<Employee>`, возвращает `String`.

Метод `writeString()` записывает строчку в формате JSON в полученный в качестве аргумента JSON-файл `data.json`. Принимает в качестве аргумента название JSON-файла, 
в который будет осуществляться запись информации о сотрудниках.
