# Sample-Url-Shortener-Main

Please use schema.sql to create table
Need to update application.properties file following details

server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/sample_url_shortner_db_v001?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=password

app-short-url-domain=http://localhost:8080/
app-description=Sample URL Shortener 
app-title=Sample URL Shortner
app-allow-cross-origin-domain=http://localhost:4200/
