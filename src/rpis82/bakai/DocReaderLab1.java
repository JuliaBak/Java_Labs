package rpis82.bakai;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static rpis82.bakai.GetChildrenInfo.getInfoAboutChildren;

public class DocReaderLab1 {
    public static void DocRead() {
        try {
            // Получение фабрики, чтобы после получить билдер документов
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, нам необходимым
            Document document = builder.parse(new File("C:\\Users\\User\\IdeaProjects\\XML_Labs\\Menu1"));

            // Считывание имени тега корневого элемента для поиска его в файле
            String searchedElement = String.valueOf(document.getDocumentElement().getTagName());

            try {
                NodeList elementsList = document.getElementsByTagName(searchedElement);

                // Получение первого элемента.
                Node foundedElement = elementsList.item(0);
                System.out.println("Here's the file's elements:");

                // Если есть данные внутри, вызов метода для вывода всей информации
                if (foundedElement.hasChildNodes())
                    getInfoAboutChildren(foundedElement.getChildNodes());
            } catch (NullPointerException e) {
                System.out.println("There are no elements in this file");
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Some problem's occurred");
        }
    }
}
