package ru.job4j.optimization;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 04.03.18.
 */
public class SimpleTransformer {

    private static final int NUMBER_OF_RECORDS = 10;

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS test;";

    private static final String CREATE_TABLE = "CREATE TABLE test(field INTEGER PRIMARY KEY);";

    private static final String PATH = "./chapter_007/src/test/java/ru/job4j/resources/";

    private static final String URL = "jdbc:sqlite:./chapter_007/src/test/java/ru/job4j/resources/entry.db";

    private long sum = 0;

    private void createXml(Entries entries) throws JAXBException {
        File xml = new File(PATH + "1.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(entries, xml);
    }

    public void transform() {
        Entries entries = new Entries();
        try (Connection connection = DriverManager.getConnection(URL)) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate(DROP_TABLE);
            statement.executeUpdate(CREATE_TABLE);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO test (field) VALUES(?);");
            fillTable(preparedStatement);
            connection.commit();
            createEntryList(entries.getEntries(), statement);
            createXml(entries);
            transformXml();
            parseXml();
            printSum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printSum() {
        System.out.printf("Sum of all values %d", sum);
    }

    private void parseXml() throws ParserConfigurationException, SAXException, IOException {
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (qName.equals("entry")) {
                    sum += Integer.parseInt(attributes.getValue("field"));
                }
            }
        };
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(PATH + "2.xml", handler);
    }

    private void transformXml() throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer(new StreamSource(PATH + "xsl.xsl"));
        transformer.transform(new StreamSource(PATH + "1.xml"), new StreamResult(PATH + "2.xml"));
    }

    private void createEntryList(List<Entry> entries, Statement statement) throws SQLException {
        ResultSet rs;
        String selectAll = "SELECT field FROM test";
        rs = statement.executeQuery(selectAll);
        while (rs.next()) {
            Entry entry = new Entry();
            entry.setField(rs.getInt("field"));
            entries.add(entry);
        }
    }

    private void fillTable(PreparedStatement preparedStatement) throws SQLException {
        for (int i = 1; i <= NUMBER_OF_RECORDS; i++) {
            preparedStatement.setInt(1, i);
            preparedStatement.executeUpdate();
        }
    }
}