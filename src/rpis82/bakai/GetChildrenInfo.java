package rpis82.bakai;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetChildrenInfo {
    public static void getInfoAboutChildren(NodeList nodesList) {
        for (int i = 0; i < nodesList.getLength(); i++) {
            Node node = nodesList.item(i);

            //Проверяем, является ли узел элемента текстовой информацией
            if (node.getNodeType() == Node.TEXT_NODE) {

                // Корректируем информацию, удаляем пробелы и переносы строчек
                String textInformation = node.getNodeValue().replace("\n", "").trim();
                if(!textInformation.isEmpty())
                    System.out.println("\n THere's some text inside the element: " + node.getNodeValue());
            }

            // Если это не текст, а элемент, то обрабатываем его как элемент
            else {
                System.out.println("Element: " + node.getNodeName() + " and its attributes");
                // Получение атрибутов
                NamedNodeMap attributes = node.getAttributes();

                // Вывод информации про все атрибуты
                for (int attr = 0; attr < attributes.getLength(); attr++)
                    System.out.println(attributes.item(attr).getNodeName() + " = " + attributes.item(attr).getNodeValue());
            }

            // Если у данного элемента еще остались узлы, то выводим всю информацию про все его узлы
            if (node.hasChildNodes())
                getInfoAboutChildren(node.getChildNodes());
        }
    }
}
