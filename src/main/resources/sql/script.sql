CREATE SCHEMA peaas;
SHOW TABLES;
DROP TABLE peaas.user;
DROP TABLE peaas.tweet;

SELECT * FROM peaas.user;
SELECT * FROM peaas.tweet;

#https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
#http://localhost:8080/api/v1/users/1/tweets/?page=0&size=2
#http://localhost:8080/api/v1/users/1/tweets/?page=1&size=1