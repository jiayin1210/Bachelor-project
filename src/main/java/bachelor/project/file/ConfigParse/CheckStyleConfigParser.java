package bachelor.project.file.ConfigParse;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.*;

public class CheckStyleConfigParser {
	
	// ein einfacher typ , um die einfahce Eigenschften zu sperichern

    public record ConfigNode(
            String name,
            String path,
            Map<String, String> properties,
            List<ConfigNode> children
    ) {}
    
    public CheckStyleConfigParser() throws Exception{}

    // wird Root der Konfigurationsdatei zurüchgesendet,durch den Root man kann 
    // den ganzen baum von Konfigurationdatei laufen
    public static ConfigNode parse(File configFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Sicherheit: externe DTDs deaktivieren
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setExpandEntityReferences(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(configFile);
        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();

        if (!"module".equals(root.getTagName())) {
            throw new IllegalArgumentException("Root-Element muss <module> sein");
        }

        return parseModule(root, "");
    }

    private static ConfigNode parseModule(Element moduleElement, String parentPath) {
        String name = moduleElement.getAttribute("name");

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Module ohne name-Attribut gefunden");
        }

        String path = parentPath.isBlank() ? name : parentPath + "/" + name;

        Map<String, String> properties = new LinkedHashMap<>();
        List<ConfigNode> children = new ArrayList<>();

        NodeList nodes = moduleElement.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element element = (Element) node;

            switch (element.getTagName()) {
                case "property" -> {
                    String propName = element.getAttribute("name");
                    String propValue = element.getAttribute("value");
                    properties.put(propName, propValue);
                }
                case "module" -> children.add(parseModule(element, path));
                default -> throw new IllegalArgumentException(
                        "Unbekanntes Element: " + element.getTagName()
                );
            }
        }

        return new ConfigNode(name, path, properties, children);
    }

    public static void printNodes(ConfigNode node, int indent) {
        String prefix = " ".repeat(indent);

        System.out.println(prefix + "- " + node.name());
        System.out.println(prefix + "  Pfad: " + node.path());

        if (!node.properties().isEmpty()) {
            System.out.println(prefix + "  Properties: " + node.properties());
        }

        for (ConfigNode child : node.children()) {
        	printNodes(child, indent + 2);
        }
    }


}