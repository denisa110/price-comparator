package ro.accesa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.StringHelper.isNotEmpty;

/**
 * Utility service for executing SQL migration scripts using JPA's EntityManager.
 * Designed to load and run SQL files for data import or schema setup.
 */
public class SQLScriptRunner {

    private final Logger logger = Logger.getLogger("SQLScriptRunner");
    private final EntityManager entityManager;

    public SQLScriptRunner(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void runAllScripts() {
        String[] scripts = {
                "migrations/V1__lidl_2025-05-01.sql",
                "migrations/V2__kaufland_2025-05-01.sql",
                "migrations/V3__profi_2025-05-01.sql",
                "migrations/V4__lidl_2025-05-08.sql",
                "migrations/V5__kaufland_2025-05-08.sql",
                "migrations/V6__profi_2025-05-08.sql",
        };

        for (String scriptPath : scripts) {
            runScript(scriptPath);
        }
        logger.log(Level.INFO, "The data was imported successfully!");
    }

    /**
     * Executes the SQL statements from the specified script file.
     * Rolls back the transaction on failure.
     *
     * @param scriptPath the relative path to the SQL script file
     */
    private void runScript(String scriptPath) {
        EntityTransaction transaction = entityManager.getTransaction();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(scriptPath))))) {

            String sqlContent = reader.lines().collect(Collectors.joining("\n"));
            String[] statements = sqlContent.split(";");

            transaction.begin();

            for (String statement : statements) {
                String cleaned = statement.trim();
                if (isNotEmpty(cleaned)) {
                    entityManager.createNativeQuery(cleaned).executeUpdate();
                }
            }

            transaction.commit();
            logger.log(Level.INFO, "Executed script: {} ", scriptPath);

        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            logger.log(Level.SEVERE, "Failed to execute script {}", scriptPath + ": " + e.getMessage());
        }
    }
}