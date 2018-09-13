# Spring Boot Hikaricp

Dans le tutorial précédent(https://github.com/WifekRaissi/spring-boot-rest-data-mysql) on a commencé l'intégration d'une base de données MySQL à l'application Spring Boot. 
Pour accéder à  une base de données on peut utiliser une Datasource qui est une interface permettant l'accés aux sources de données physiques.

# Implémentation d'une DataSourse
L'implémentation d'une DataSource permet de facilement gérer les propriétés des sources de données. En plus que les instances de Connection fournies par les DataSouce ont des capacités étendues.
Il existe trois grands types d'implémentation pour une DataSource:

   ##     Implémentations Basiques:
   ces implémentations produisent des instances de Connection normales, telles que l'on pourrait les obtenir avec DriverManager

   ##    Implémentations par des Transactions distribuées:
   ces implémentations produisent des instances de Connection, qui peuvent être utilisées pour des transactions distribuées. 
:
   
##     Implémentation par les Pools de connexions:
ces implémentations produisent des instances de Connection qui appartiennent à un pool.

C'est ce dernier type qui nous intéresse.

 " La création systématique de nouvelles instances de Connection peut parfois devenir très lourd en consommation de ressources.
Un pool de connexions est un mécanisme permettant de réutiliser les connexions créées. En effet, la création systématique de nouvelles instances de Connection peut parfois devenir très lourd en consommation de ressources. Pour éviter cela, un pool de connexions ne ferme pas les connexions lors de l'appel à la méthode close(). Au lieu de fermer directement la connexion, celle-ci est « retournée » au pool et peut être utilisée ultérieurement".
https://java.developpez.com/faq/jdbc/?page=Sources-de-donnees-moins-DataSources#Pourquoi-utiliser-une-DataSource
Avec un pool de connexion on peut assurer une meilleure performance dans l'execution des commandes sur une base de données.
  #  Gestion d'un pool de connexion
  
  Pour la gestion d'un pool de connexion il existe plusieurs pools de connexion, les plus connus sont:
  
   - Tomcat: est un conteneur web libre de servlets et JSP Java EE. Issu du projet Jakarta. Pour Spring Boot 1.x, Tomcat a été le pool de connexion par défaut.
   -HikariCP : Le ppol de connexion par défaut pour Spring Boot 2.x. C'est un pool de connexion JDBC fiable et performant.
Ils existent plusieurs articles qui ont comparé la performance des deux pools.
L'image suivante a été obtenue de https://www.baeldung.com/hikaricp.

   ![alt text](https://github.com/WifekRaissi/spring-boot-Hikaricp/blob/master/src/main/resources/images/hikaricp.png)
   
 D'après cette comparaisons et beaucoups d'autres, il est clair que HikariCP est le pool de connexion le plus performant.
 
 ## Configuration de HikariCP
La configuration dépend de la version de Spring Boot, si on travaille avec Spring Boot 1.x on doit ajouter la dépendance suivante:

```
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>3.1.0</version>
</dependency> 

```
On doit ajouter aussi la configuration suivante à application.properties:

spring.datasource.type=com.zaxxer.hikari.HikariDataSource

## Spring Boot 2

A partir de Spring 2.x pas besoin d'ajouter la dépendance puisque HikariCP est configuré par défaut avec Spring Boot.


on peut aussi configurer d'autres propriétés parmi la liste suivante:

autoCommit

connectionTimeout

idleTimeout

maxLifetime

connectionTestQuery

connectionInitSql

validationTimeout

maximumPoolSize

poolName

allowPoolSuspension

readOnly

transactionIsolation

leakDetectionThreshold

Pour s'assurer qu'on est entrain d'utiliser HikariCP on peut afficher la Datasource:


``import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHikaricpApplication implements CommandLineRunner {
    private final DataSource dataSource;

    public SpringBootHikaricpApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootHikaricpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataSource = " + dataSource);
    }
}
```
```


   ![alt text](https://github.com/WifekRaissi/spring-boot-Hikaricp/blob/master/src/main/resources/images/hikariex.PNG)


