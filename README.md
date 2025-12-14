# TP3 – Sécurisation d’une Application E-Learning
OAuth2 / OpenID Connect – Keycloak, Spring Boot, React

---

## Objectif général

Mettre en place une authentification et une autorisation sécurisées basées sur
les standards **OAuth2** et **OpenID Connect**.

L’architecture repose sur :
- **Keycloak** comme serveur d’identité
- **Spring Boot** comme backend sécurisé (OAuth2 Resource Server)
- **React** comme frontend SPA

Les rôles gérés sont :
- **STUDENT** : consultation des cours
- **ADMIN** : consultation et gestion des cours

---

## Architecture

Navigateur
|
v
React (http://localhost:3000)
|
| Authentification OIDC
v
Keycloak (http://localhost:8080)
|
| JWT (Access Token)
v
Spring Boot API (http://localhost:8081)

yaml
Copy code

---

## Rôles et accès

| Rôle | Accès autorisés |
|------|----------------|
| STUDENT | Consulter les cours |
| ADMIN | Consulter et ajouter des cours |

Les rôles sont définis dans Keycloak et transmis dans le **token JWT**.

---

## Configuration Keycloak

- Realm : `elearning-realm`
- Client :
    - ID : `react-client`
    - Type : Public
    - Standard Flow (OIDC) : activé
    - Redirect URI : `http://localhost:3000/*`
- Rôles :
    - `ROLE_STUDENT`
    - `ROLE_ADMIN`
- Utilisateurs :
    - `user1` → ROLE_STUDENT
    - `admin1` → ROLE_ADMIN

---

## Backend Spring Boot

Le backend agit comme **OAuth2 Resource Server** et valide les JWT émis par Keycloak.



### Captures d’écran
Authentification via Keycloak
Lorsqu’un utilisateur accède au frontend sans être authentifié, il est redirigé vers Keycloak.

img/1.png
Accès ADMIN après authentification
Après connexion avec un compte ADMIN, l’accès aux fonctionnalités est autorisé.
img/2.png

