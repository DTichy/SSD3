package ssd;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

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

    //Games node from given xml file
    private Node gamesNode;

    //Game node to be generated
    private Element gameEle;

    //String for player name
    private String playerName;

    //String for question attribute
    private String question;

    //Variable for all answers given
    private ArrayList<String> answers;

    //Defines current element - Changed in startElement()
    private String currentElement;


	
    
	
    public JeopardyMoveHandler(Document doc) {
        jeopardyDoc = doc;

        //Extract der games node from given xml file
        NodeList jeopardyNodes = jeopardyDoc.getDocumentElement().getChildNodes();
        for(int i = 0; i<jeopardyNodes.getLength();i++){
            if(jeopardyNodes.item(i).getNodeName().equals("games")){
                gamesNode = jeopardyNodes.item(i);
            }
        }

        //Initialize game node variable
        gameEle = jeopardyDoc.createElement("game");
        //Initialize answers list
        answers = new ArrayList<String>();
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

    @Override
    public void startDocument() throws SAXException {
        System.out.println("///Start parsing Document///");
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        System.out.println("<" + qName + ">");

        //Set session attribute in game
        if(currentElement.equals("move")){
            gameEle.setAttribute("session", attributes.getValue("session"));
        }

    }
    @Override
    /**
     * SAX calls this method to pass in character data
     * Extract text values of specific elements
     */
    public void characters(char[] text, int start, int length) throws SAXException {

        eleText = new String(text, start, length);
        //Set player name
        if(currentElement.equals("player")){
            playerName = eleText;
        } else if(currentElement.equals("question")){//Set question attribute
            question=eleText;
        } else if(currentElement.equals("answer")){//Insert answer text
            answers.add(eleText);
        }
        System.out.println(eleText);
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("</" + qName + ">");
        currentElement = "";
    }


    @Override
    public void endDocument() throws SAXException {
        System.out.println("///End parsing Document///");
        //Set player element and attribute
        Element playerElement = jeopardyDoc.createElement("player");
        playerElement.setAttribute("ref",playerName);
        //Set asked element and attribute
        Element askedElement = jeopardyDoc.createElement("asked");
        askedElement.setAttribute("question",question);

        //For each answer given, set a givenanswer element with attribute player and answer text
        for(String s : answers){
            Element givenAnswer = jeopardyDoc.createElement("givenanswer");
            givenAnswer.setAttribute("player",playerName);
            givenAnswer.setTextContent(s);
            askedElement.appendChild(givenAnswer);
        }
        //Set player element under game
        gameEle.appendChild(playerElement);
        //Set asked element under game
        gameEle.appendChild(askedElement);
        //Set new generated game element under games
        gamesNode.appendChild(gameEle);

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException{

    }
}

