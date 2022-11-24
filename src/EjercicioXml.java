import  org.w3c.dom.*;

import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class EjercicioXml {

    public static void main(String[] args) {
        new EjercicioXml();
    }
    Document document;
    public EjercicioXml() {
        iniciar("departamentos");
        construir("departamento","dept_no","10","nombre","Contabilidad","loc","Logroño");
        construir("departamento","dept_no","20","nombre","Produccion","loc","Logroño");
        crearFicheroXML();
    }

    private void crearFicheroXML() {
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("departamentos.xml"));
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source,result);
            // ___________ final

            Result console = new StreamResult(System.out);
            transformer.transform(source,console);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private void construir(String padre,String... valor) {
        Element element = document.createElement(padre);
        document.getDocumentElement().appendChild(element);

        for (int i = 0; i < valor.length; i += 2) {

            addElement(valor[i],valor[i+1],element);
        }

    }

    private void iniciar(String raiz) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation  = builder.getDOMImplementation();

            document = implementation.createDocument(null,raiz,null);
            document.setXmlVersion("1.0");

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void addElement(String dato, String value, Element raiz){
        Element elem = document.createElement(dato);
        Text text = document.createTextNode(value);
        raiz.appendChild(elem);
        elem.appendChild(text);

    }
}
