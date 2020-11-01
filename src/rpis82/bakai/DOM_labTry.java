package rpis82.bakai;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DOM_labTry {
        public static void main(String[] args) {
    try {
        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.parse(new File("C:\\Users\\User\\IdeaProjects\\XML_Labs\\Menu1"));
        // Считывание имени тега для поиска его в файле
        String element = String.valueOf(document.getDocumentElement().getTagName());


        // Получение списка элементов, однако для удобства будем рассматривать только первое совпадение в документе.
        // Так же заметьте, что мы ищем элемент внутри документа, а не рут элемента. Это сделано для того, чтобы рут элемент тоже искался.

        // Даже если элемента нет, всегда будет возвращаться список, просто он будет пустым.
        // Потому, чтобы утверждать, что элемента нет в файле, достаточно проверить размер списка.

        try {
            NodeList matchedElementsList = document.getElementsByTagName(element);
            // Получение первого элемента.
            Node foundedElement = matchedElementsList.item(0);
            System.out.println("Here's the file's elements:");

            // Если есть данные внутри, вызов метода для вывода всей информации
            if (foundedElement.hasChildNodes())
                printInfoAboutAllChildNodes(foundedElement.getChildNodes());
        } catch (NullPointerException e) {
            System.out.println("There are no elements in this file");
        }
    }
    catch (ParserConfigurationException | SAXException | IOException e)
    {
        System.out.println("Some problem's occurred");
    }
        }

        public static void printInfoAboutAllChildNodes(NodeList list) {
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

// У элементов есть два вида узлов - другие элементы или текстовая информация. Потому нужно разбираться две ситуации отдельно.
                if (node.getNodeType() == Node.TEXT_NODE) {

// Фильтрация информации, так как пробелы и переносы строчек нам не нужны. Это не информация.
                    String textInformation = node.getNodeValue().replace("\n", "").trim();
                    if(!textInformation.isEmpty())
                        System.out.println("\n THere's some text inside the element: " + node.getNodeValue());
                }

// Если это не текст, а элемент, то обрабатываем его как элемент.
                else {
                    System.out.println("Element: " + node.getNodeName() + " and its attributes");
// Получение атрибутов

                    NamedNodeMap attributes = node.getAttributes();
// Вывод информации про все атрибуты
                        for (int k = 0; k < attributes.getLength(); k++)
                            System.out.println(attributes.item(k).getNodeName() + " = " + attributes.item(k).getNodeValue());
                }

// Если у данного элемента еще остались узлы, то вывести всю информацию про все его узлы.
                if (node.hasChildNodes())
                    printInfoAboutAllChildNodes(node.getChildNodes());
            }
        }

}

