# Backend Spring boot

## Description

Backend du projet web de M1 1er semestre. En **Spring Boot** avec **MuSQL** comme base de donnée et **JPA** comme ORM/ lien avec la base de donnée.  

La base de donnée fonctionne dans un container Docker (port : `3307`).
Le backend ne tourne pas dans un container docker, son port est le `8080` (http://localhost:8080/)

Pour lancer le backend, vous pouvez simplement executé la classe main comme d'habitude, pas de changement ici. 

Identifiant BD si vous en avez besoin : id : `root`, password : `root`

## Ressources :

Celene   
[Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/index.html)


## Sécurité :

Pour faire fonctionne l'authentification, vous aurez besoin de :

### Dépendances
Vous aurez besoin des nouvelles dépendances, pour ca, aller sur votre `pom.xml` et faire un reload de Maven

### ENV
Ajouter à la racine de votre projet (au niveau du `pom.xml`) un fichier `.env` qui contient les lignes suivantes :

```env
JWT_PRIVATE_KEY_PATH=./private_key.pem
JWT_PUBLIC_KEY_PATH=./public_key.pem
JWT_EXPIRATION_MS=36000000
```


## Clés
Ensuite, exécutez la classe `RsaKeyGenerator` dans le package `configuration`.
Il va créer les clés privées/publiques utilisées pour les JWT. Celles-ci se trouveront à la racine, au niveau de `pom.xml`

## Tokens
Enfin, pour récupérer vos Tokens JWT, vous pouvez passer par le fichier de requêtes http `authentication.http` dans le fichier `resources` et exécutez les requêtes de connexion pré faites pour l'étudiant et l'administrateur de base.

Ceux-ci périment au bout de 10h normalement, attention à bien les renouveler s'ils sont plus utilisable

Pour les utiliser, ajouter le champ `Authorization` a votre requête
```http
Authorization: Bearer <JWT récupéré de votre utilisateur>
```