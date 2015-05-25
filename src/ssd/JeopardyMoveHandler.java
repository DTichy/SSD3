package ssd;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * TODO: Implement this content handler.
 */
public class JeopardyMoveHandler extends DefaultHandler {
	/**
	 * Use this xPath variable to create xPath expression that can be
	 * evaluated over the XML document.
	 */
	//private static XPath xPath = XPathFactory.newInstance().newXPath();
	
	/**
	 * Store and manipulate the Jeopardy XML document here.
	 */
	private Document jeopardyDoc;
	
	/**
	 * This variable stores the text content of XML Elements.
	 */
	private String eleText;

	//***TODO***
	//Insert local variables here

    private Node gamesNode;


	
    
	
    public JeopardyMoveHandler(Document doc) {
        jeopardyDoc = doc;
        NodeList jeopardyNodes = jeopardyDoc.getDocumentElement().getChildNodes();
        for(int i = 0; i<jeopardyNodes.getLength();i++){
            if(jeopardyNodes.item(i).getNodeName().equals("games")){
                gamesNode = jeopardyNodes.item(i);
            }
        }

    }

    @Override
    /**
     * SAX calls this method to pass in character data
     */
  	public void characters(char[] text, int start, int length) throws SAXException {
  		eleText = new String(text, start, length);
        System.out.println(eleText);
    }

    /**
     * 
     * Return the current stored Jeopardy document
     * 
     * @return XML Document
     */
	public Document getDocument() {
		return jeopardyDoc;
	}
    
    //***TODO***
	//Specify additional methods to parse the move document and modify the jeopardyDoc


    @Override
    public void startDocument() throws SAXException {
        System.out.println("///Start parsing Document///");
    }


    @Override
    public void endDocument() throws SAXException {
        System.out.println("///End parsing Document///");
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("<" + qName + ">");
        if(qName.equals("game")){

        }

    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("</" + qName + ">");
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException{

    }
}

