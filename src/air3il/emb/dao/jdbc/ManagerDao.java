package air3il.emb.dao.jdbc;

import air3il.commun.dao.IManagerDao;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManagerDao implements IManagerDao {

    // Logger
    private static final Logger logger = Logger.getLogger(ManagerDao.class.getName());

    // Champs
    private final Map<Class, Object> mapDaos = new HashMap<>();

    private Properties propertiesConnection;

    private DataSource dataSource;

    private Connection connection;

    // Constructeur
    public ManagerDao() {

        try {
            propertiesConnection = new Properties();
            InputStream in = this.getClass().getResourceAsStream("/META-INF/jdbc.properties");
            propertiesConnection.load(in);
            in.close();
            String nomJndi = propertiesConnection.getProperty("jdbc.dataSource");
            if (nomJndi != null) {
                try {
                    // Essai de récupération d'une DataSource via JNDI
                    Context nc = new InitialContext();
                    dataSource = (DataSource) nc.lookup(nomJndi);
                    // Message de log
                    String logMessage = "Parmètres de connexion à la base de données :";
                    logMessage += "\n  datasource : " + nomJndi;
                    logger.log(Level.CONFIG, logMessage);
                } catch (NamingException e) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connect();
    }

    // Propiétés
    public Connection getConnection() {
        return connection;
    }

    // getDao()
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getDao(Class<T> type) {

        // Recherche dans la map
        Object dao = mapDaos.get(type);

        // Si pas trouvé dans la map
        if (dao == null) {
            try {
                // Détermine le type à instancier
                Class<T> typeImpl;
                String nomImpl = type.getSimpleName().substring(1);
                String nomPackage = this.getClass().getPackage().getName();
                nomImpl = nomPackage + "." + nomImpl;
                typeImpl = (Class<T>) Class.forName(nomImpl);
                Constructor<T> constructor = typeImpl.getConstructor(new Class[]{ManagerDao.class});
                // Instancie un objet et lui injecte les dépendances
                dao = constructor.newInstance(new Object[]{this});
                // Ajoute l'objet à la map
                mapDaos.put(type, dao);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return (T) dao;
    }

    // Actions
    @Override
    public void transactionBegin() {
        try {
            connection.setAutoCommit(false);
            logger.finer("Transaction BEGIN");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transactionCommit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            logger.finer("Transaction COMMIT");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transactionRollback() {
        try {
            connection.rollback();;
            connection.setAutoCommit(true);
            logger.finer("Transaction ROLLBACK");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void threadAfterBegin() {
        if (connection == null) {
            connect();
        }
    }

    @Override
    public void threadBeforeEnd() {
        if (dataSource != null) {
            disconnect();
        }
    }

    @Override
    public void close() {
        disconnect();
        if (dataSource != null) {
            dataSource = null;
        }
    }

    // Méthodes auxiliaires
    private void connect() {

        disconnect();

        try {
            if (dataSource == null) {
                // Si dataSource non récupérée, essai de connexion à partir d'un fichier properties
                String driver = propertiesConnection.getProperty("jdbc.driver");
                String url = propertiesConnection.getProperty("jdbc.url");
                String user = propertiesConnection.getProperty("jdbc.user");
                String password = propertiesConnection.getProperty("jdbc.password");
                // Message de log
                String logMessage = "Parmètres de connexion à la base de données :";
                logMessage += "\n  driver : " + driver;
                logMessage += "\n  url    : " + url;
                logMessage += "\n  user   : " + user;
                logger.log(Level.CONFIG, logMessage);
                // Connexion
                if (driver != null) {
                    Class.forName(driver);
                }
                connection = DriverManager.getConnection(url, user, password);
            } else {
                connection = dataSource.getConnection();
                if (!connection.isValid(0)) {
                    connection = dataSource.getConnection();
                }
            }
            logger.log(Level.CONFIG, "Connexion à la base OK");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
                mapDaos.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
