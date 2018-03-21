package ru.job4j.parser;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


/**
 * @author Dmitry Belokursky
 * @since 11.03.18.
 */
public class Parser extends TimerTask {

    private static final int NUMBER_OF_PAGES_FOR_FIRST_LAUNCH = 10;

    private static final int NUMBER_OF_PAGES_FOR_SUBSEQUENT_LAUNCHES = 2;

    private static final String JAVA_SCRIPT = "[Jj][Aa][Vv][Aa].?[Ss][Cc]";

    private static final String JAVA = "[Jj][Aa][Vv][Aa]";

    private static final String TODAY = "[с][е][г][о][д][н][я][,][\\s][\\d]{1,2}[:][\\d]{1,2}";

    private static final String YESTERDAY = "[в][ч][е][р][а][,][\\s][\\d]{1,2}[:][\\d]{1,2}";

    private static final String BASE_URL = "http://www.sql.ru/forum/job-offers/";

    private static final String PATH = "./chapter_007/src/main/java/ru/job4j/parser/resources/";

    private static final Logger LOGGER = Logger.getLogger("Parser.class");

    private List<String> urls = new ArrayList<>();

    private List<Element> elements = new ArrayList<>();

    @Override
    public void run() {
        org.apache.log4j.PropertyConfigurator.configure(PATH + "log4j.properties");
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PATH + "parser.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"))) {
            executeInitSql(connection);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100;
            String thisYear = "[\\d]{1,2}[ ][а-я][а-я][а-я][ ]" + currentYear + "[,][ ][\\d]{1,2}[:][\\d]{1,2}";
            fillUrlList(connection, thisYear, properties);
            fillElementsList(thisYear);

            for (Element element : elements) {
                if (element.getElementsMatchingOwnText(JAVA).hasText()) {
                    String name = element.getElementsByTag("a").first().text();
                    String url = element.getElementsByTag("a").first().attr("href");
                    String lastUpdateTime = element.getElementsByClass("altCol").last().text();
                    Date todayDate = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(todayDate);
                    calendar.add(Calendar.DATE, -1);
                    Date yesterdayDate = calendar.getTime();
                    SimpleDateFormat parser = new SimpleDateFormat("dd MMM yy, HH:mm", new Locale("ru", "RU"));
                    DateFormat formatter = new SimpleDateFormat("dd MMM yy,", new Locale("ru", "RU"));
                    if (lastUpdateTime.contains("вчера")) {
                        String time = lastUpdateTime.substring(lastUpdateTime.indexOf(" "), lastUpdateTime.length());
                        lastUpdateTime = lastUpdateTime.replaceAll(YESTERDAY, formatter.format(yesterdayDate) + time);
                    }
                    if (lastUpdateTime.contains("сегодня")) {
                        String time = lastUpdateTime.substring(lastUpdateTime.indexOf(" "), lastUpdateTime.length());
                        lastUpdateTime = lastUpdateTime.replaceAll(TODAY, formatter.format(todayDate) + time);
                    }
                    Date date = parser.parse(lastUpdateTime);
                    insertRecord(connection, name, url, date);
                }
            }
            connection.commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    private void executeInitSql(Connection connection) {
        PrintStream stdOut = System.out;
        try (PrintStream ps = new PrintStream(new File(PATH + "init.sql.log"))) {
            System.setOut(ps);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        try (Reader reader = new BufferedReader(new FileReader(PATH + "init.sql"))) {
            scriptRunner.runScript(reader);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            System.setOut(stdOut);
        }

    }

    private void insertRecord(Connection connection, String name, String url, Date date) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO joboffers (name, url, last_update_time) VALUES (?, ?, ?) ON CONFLICT DO NOTHING");
        ps.setString(1, name);
        ps.setString(2, url);
        ps.setTimestamp(3, new Timestamp(date.getTime()));
        ps.executeUpdate();
    }

    private void fillElementsList(String thisYear) throws IOException {
        Document document;
        for (String url : urls) {
            document = Jsoup.connect(url).get();
            Elements elementsByTag = document.getElementsByTag("tr");
            for (Element element : elementsByTag) {
                if (element.getElementsMatchingOwnText(JAVA_SCRIPT).hasText()) {
                    continue;
                }
                if (contains(thisYear, element)) {
                    elements.add(element);
                }
            }
        }
    }

    private void fillUrlList(Connection connection, String thisYear, Properties properties) {
        Document document;
        int numOfUrls = 0;
        if (isFirstLaunch(connection)) {
            numOfUrls = NUMBER_OF_PAGES_FOR_FIRST_LAUNCH;
        } else {
            numOfUrls = NUMBER_OF_PAGES_FOR_SUBSEQUENT_LAUNCHES;
        }
        for (int i = 1; i < numOfUrls; i++) {
            String url = new StringBuilder(BASE_URL).append(i).toString();
            try {
                document = Jsoup.connect(url)
                        .userAgent(properties.getProperty("userAgent"))
                        .referrer(properties.getProperty("referrer"))
                        .get();
                Elements elements = document.getElementsByTag("tr");
                for (Element element : elements) {
                    if (contains(thisYear, element)) {
                        urls.add(url);
                        break;
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private boolean contains(String thisYear, Element element) {
        if (element.getElementsMatchingOwnText(JAVA).hasText()
                && element.getElementsByAttributeValue("class", "postslisttopic").hasText()) {
            if (element.getElementsMatchingOwnText(thisYear).hasText()
                    || element.getElementsMatchingOwnText(TODAY).hasText()
                    || element.getElementsMatchingOwnText(YESTERDAY).hasText()) {
                return true;
            }
        }
        return false;
    }

    private boolean isFirstLaunch(Connection connection) {
        boolean firstLaunch = false;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM joboffers;");
            if (!resultSet.next()) {
                firstLaunch = true;
            }
            return firstLaunch;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return firstLaunch;
    }
}
