package Client;

import Werehouse.Product;

import java.util.ArrayList;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Client {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "DatabaseQueries";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private MessageConsumer consumer;

    public Client() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        destination = session.createQueue(subject);

        producer = session.createProducer(destination);
        consumer = session.createConsumer(destination);
    }

    public boolean addSection(String name, int number, throws JMSException {
        Query query = new Query();
        query.queryType = QueryType.addGroup;
        query.sectionName = name;
        query.sectionNumber = number;
        ObjectMessage objectMessage = session.createObjectMessage(query);
        producer.send(objectMessage);
        Message message = consumer.receive();
        Boolean answer = (Boolean) (((ObjectMessage) message).getObject());
        if (answer != null) {
            return answer;
        } else {
            return false;
        }
    }

    public boolean addProduct(String country, String name, int price, String sectionName) throws JMSException {
        Query query = new Query();
        query.queryType = QueryType.addProduct;
        query.studentFirstName = country;
        query.studentLastName = name;
        query.studentAge = price;
        query.groupName = sectionName;
        ObjectMessage objectMessage = session.createObjectMessage(query);
        producer.send(objectMessage);
        Message message = consumer.receive();
        Boolean answer = (Boolean) (((ObjectMessage) message).getObject());
        if (answer != null) {
            return answer;
        } else {
            return false;
        }
    }

    public boolean deleteProduct(int productID) throws JMSException {
        Query query = new Query();
        query.queryType = QueryType.deleteProduct;
        query.productID = productID;
        ObjectMessage objectMessage = session.createObjectMessage(query);
        producer.send(objectMessage);
        Message message = consumer.receive();
        Boolean answer = (Boolean) (((ObjectMessage) message).getObject());
        if (answer != null) {
            return answer;
        } else {
            return false;
        }
    }

    public ArrayList<Product> getAllProducts() throws JMSException {
        Query query = new Query();
        query.queryType = QueryType.getProducts;
        ObjectMessage objectMessage = session.createObjectMessage(query);
        producer.send(objectMessage);
        Message message = consumer.receive();
        ObjectMessage reply = (ObjectMessage) message;
        return (ArrayList<Student>) reply.getObject();
    }

    public void disconnect() throws JMSException {
        session.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            ArrayList<Product> products = client.getAllProducts();
            for (Product product : products) {
                System.out.println(product);
            }
            client.disconnect();
        } catch (JMSException e) {
            System.out.println("Connection error");
        }
    }
}
