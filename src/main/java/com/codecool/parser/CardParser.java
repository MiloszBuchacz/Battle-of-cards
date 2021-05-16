package com.codecool.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.codecool.card.Card;
import com.codecool.card.StatsType;
import com.codecool.utilities.InputProvider;
import com.codecool.utilities.UI;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CardParser extends XMLParser {

    public CardParser() {
        loadXmlDocument("src/main/java/com/codecool/recources/Cards.xml");
    }

    public List<Card> parse() {
        List<Card> cardsList = new ArrayList<>();
        NodeList cardList = doc.getElementsByTagName("Card");
        for (int i = 0; i < cardList.getLength(); i++) {
            Node cards = cardList.item(i);
            if (cards.getNodeType() == Node.ELEMENT_NODE) {
                Element card = (Element) cards;
                String cardName = card.getAttribute("name");
                int[] statistic = new int[3];
                int index = 0;
                NodeList statsList = card.getChildNodes();
                for (int j = 0; j < statsList.getLength(); j++) {
                    Node stats = statsList.item(j);
                    if (stats.getNodeType() == Node.ELEMENT_NODE) {
                        Element stat = (Element) stats;
                        statistic[index] = Integer.parseInt(stat.getTextContent());
                        index++;
                    }
                }
                cardsList.add(new Card(cardName, statistic[0], statistic[1], statistic[2]));
            }
        }
        return cardsList;
    }

    public void addCard() {
        UI.clearScreen();
        Node newCard = doc.createElement("Card");
        ((Element) newCard).setAttribute("name", InputProvider.getString("Provide a name of card: "));
        for (StatsType stat : StatsType.values()) {
            newCard.appendChild(setStat(stat));
        }
        doc.getDocumentElement().appendChild(newCard);
        saveXML();
        System.out.println("Card added succesfully!");
        UI.pressEnterToContinue();
    }

    private void saveXML() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File("src/main/java/com/codecool/recources/Cards.xml"));
            Source input = new DOMSource(doc);
            transformer.transform(input, output);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element setStat(StatsType statsType) {
        Element stat = doc.createElement(statsType.label);
        stat.setTextContent(Integer.toString(InputProvider.getInt("Set " + statsType.name() + " point's\n")));
        return stat;
    }

    public void deleteCard() {
        UI.clearScreen();
        loadXmlDocument("src/main/java/com/codecool/recources/Cards.xml");
        NodeList nl = doc.getElementsByTagName("Card");
        boolean status = false;
        String name = InputProvider.getString("Provide card name to remove: ");
        for (int i = 0; i < nl.getLength(); i++) {
            Node currentItem = nl.item(i);
            String key = currentItem.getAttributes().getNamedItem("name").getNodeValue();
            if (name.equals(key)){
                currentItem.getParentNode().removeChild(currentItem);
                status = false;
            }
        }
        System.out.println((status) ? "Card not found" : "Card deleted.");
        saveXML();
        UI.pressEnterToContinue();
    }
}
