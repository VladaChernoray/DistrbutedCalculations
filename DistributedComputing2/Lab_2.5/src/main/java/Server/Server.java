package Server;

import Client.Query;
import Client.QueryType;
import DatabaseConnection.WerehouseDatabaseConnection;
import Werehouse.Section;
import Werehouse.Product;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.*;
import java.util.ArrayList;

public class Server {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "DatabaseQueries";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    public void start() throws IOException, ClassNotFoundException, JMSException {
        connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connec
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private MessageConsumer consumer;
tion.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(subject);
        producer = session.createProducer(destination);
        consumer = session.createConsumer(destination);
        while (true) {
            processQuery();
        }
    }

    private void processQuery() throws JMSException {
        Query query = (Query) ((ObjectMessage) consumer.receive()).getObject();
        QueryType operation = query.queryType;
        ObjectMessage answerMessage = null;
        if (operation.equals(QueryType.addSection)) {
            Boolean result = addSection(query.sectionName, query.sectionNumber);
            answerMessage = session.createObjectMessage(result);
        } else if (operation.equals(QueryType.addProduct)) {
            Boolean result = addProduct(query.productCountry, query.productName, query.productPrice, query.sectionName);
            answerMessage = session.createObjectMessage(result);
        } else if (operation.equals(QueryType.deleteGroup)) {
            Boolean result = deleteGroup(query.sectionName);
            answerMessage = session.createObjectMessage(result);
        } else if (operation.equals(QueryType.deleteProduct)) {
            Boolean result = deleteProduct(query.productID);
            answerMessage = session.createObjectMessage(result);
        } else if (operation.equals(QueryType.changeSectionForProduct)) {
            Boolean result = changeSectionForProduct(query.productID, query.newProductID);
            answerMessage = session.createObjectMessage(result);
        } else if (operation.equals(QueryType.getProductsInCurrentSection)) {
            ObjectMessage message = session.createObjectMessage(getProductInCurrentSection(query.sectionName));
        } else if (operation.equals(QueryType.getProducts)) {
            answerMessage = session.createObjectMessage(getAllProducts());
        } else if (operation.equals(QueryType.getSections)) {
            answerMessage = session.createObjectMessage(getAllSections());
        }
        producer.send(answerMessage);
    }

    private boolean addSection(String name, int number) {
        return WerehouseDatabaseConnection.addSection(name, number);
    }

    private boolean addProduct(String country, String name, int price, String sectionName) {
        return WerehouseDatabaseConnection.addProductToGroup(sectionName, country, name, price);
    }

    private boolean deleteSection(String name) {
        return WerehouseDatabaseConnection.deleteSectionByName(name);
    }

    private boolean deleteProduct(int id) {
        return WerehouseDatabaseConnection.deleteProductByID(id);
    }

    public boolean changeSectionForProduct(int productID, int newSectionID) {
        return WerehouseDatabaseConnection.changeSectionForProduct(newSectionID, productID);
    }

    public ArrayList<Product> getProductInCurrentSections(String sectionName) {
        return WerehouseDatabaseConnection.getProductsBySectionName(sectionName);
    }

    public ArrayList<Product> getAllProducts() {
        return WerehouseDatabaseConnection.getAllProducts();
    }

    public ArrayList<Section> getAllSections() {
        return WerehouseDatabaseConnection.getAllSections();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, JMSException {
        Server server = new Server();
        server.start();
    }

}
