import org.flywaydb.core.Flyway


fun main() {
    val jdbcUrl = System.getenv("db_url")
    val username = System.getenv("db_superuser_name")
    val pass = System.getenv("db_superuser_pass")
    migrateDb(jdbcUrl, username, pass)
}

fun migrateDb(url: String, username: String, pass: String) {
    val flyway: Flyway = Flyway.configure()
        .dataSource(url, username, pass)
        .baselineOnMigrate(true)
        .load()

    flyway.migrate()
}