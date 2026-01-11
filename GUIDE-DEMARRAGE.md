# 🚀 Guide de Démarrage - ArtisaLink Project

## ⚠️ IMPORTANT : Localisation du projet

Vous devez utiliser le projet situé dans :
```
C:\Users\Meskour\Desktop\ArtisaLink-Project
```

**PAS** le projet dans `Documents` ! Les deux projets ne sont pas synchronisés.

---

## 📋 Ordre de démarrage des services

### 1️⃣ Config Service (PRIORITAIRE - port 8888)

Le config-service DOIT démarrer en premier car tous les autres services en dépendent.

**Démarrage :**
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\config-service
mvn spring-boot:run
```

Ou double-cliquez sur : `start-config-service.bat`

**Vérification :**
- Ouvrez http://localhost:8888/actuator/health
- Testez http://localhost:8888/post-service/default

✅ Si vous voyez du JSON, le config-service fonctionne !

---

### 2️⃣ Les autres microservices (dans n'importe quel ordre)

Une fois le config-service démarré, vous pouvez lancer :

#### User Service (port 8081)
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\user-service
mvn spring-boot:run
```

#### Profile Service (port 8082)
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\profile-service
mvn spring-boot:run
```

#### Post Service (port 8083)
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\post-service
mvn spring-boot:run
```

#### Job Service (port 8084)
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\job-service
mvn spring-boot:run
```

#### Chat Service (port 8085)
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\chat-service
mvn spring-boot:run
```

#### Notification Service (port 8086)
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\notification-service
mvn spring-boot:run
```

#### Search Service (port 8087)
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\search-service
mvn spring-boot:run
```

#### Gateway Service (port 8080) - EN DERNIER
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\gateway-service
mvn spring-boot:run
```

---

## 🗄️ Prérequis PostgreSQL

Assurez-vous que PostgreSQL est installé et démarré, puis créez les bases de données :

```sql
CREATE DATABASE artisana_user_service;
CREATE DATABASE artisana_profile_service;
CREATE DATABASE artisana_post_service;
CREATE DATABASE artisana_job_service;
CREATE DATABASE artisana_chat_service;
CREATE DATABASE artisana_notification_service;
CREATE DATABASE artisana_search_service;
```

---

## 🔧 Résolution des problèmes courants

### Erreur : "500 Internal Server Error" du config-service

**Cause :** Le config-service ne peut pas lire les fichiers de config-repo

**Solution :**
1. Vérifiez que le dossier existe :
   ```
   C:\Users\Meskour\Desktop\ArtisaLink-Project\artisa-link\config-repo
   ```

2. Vérifiez que le fichier application.properties du config-service contient :
   ```properties
   spring.profiles.active=native
   spring.cloud.config.server.native.search-locations=file:///C:/Users/Meskour/Desktop/ArtisaLink-Project/artisa-link/config-repo
   ```

3. Redémarrez le config-service

---

### Erreur : "Failed to determine a suitable driver class"

**Cause :** Le service ne peut pas se connecter à PostgreSQL ou la config n'est pas chargée

**Solution :**
1. Vérifiez que PostgreSQL est démarré
2. Vérifiez que le config-service fonctionne (étape 1)
3. Vérifiez que la base de données existe
4. Vérifiez les credentials (postgres/postgres par défaut)

---

### Erreur : "Could not locate PropertySource"

**Cause :** Le config-service n'est pas démarré ou ne répond pas

**Solution :**
1. Démarrez d'abord le config-service
2. Attendez qu'il soit complètement démarré (vérifiez http://localhost:8888/actuator/health)
3. Ensuite seulement, démarrez les autres services

---

## 📝 Ports utilisés

| Service | Port |
|---------|------|
| Gateway | 8080 |
| User | 8081 |
| Profile | 8082 |
| Post | 8083 |
| Job | 8084 |
| Chat | 8085 |
| Notification | 8086 |
| Search | 8087 |
| **Config** | **8888** |

---

## 🎯 Accès aux services via le Gateway

Une fois tous les services démarrés, vous pouvez y accéder via le Gateway (port 8080) :

- Users: http://localhost:8080/api/users
- Profiles: http://localhost:8080/api/profiles
- Posts: http://localhost:8080/api/posts
- Jobs: http://localhost:8080/api/jobs

---

## ✅ Checklist de démarrage

- [ ] PostgreSQL est installé et démarré
- [ ] Toutes les bases de données sont créées
- [ ] Config-service est démarré et répond sur http://localhost:8888
- [ ] Les autres microservices peuvent récupérer leur configuration
- [ ] Gateway-service est démarré en dernier

---

## 🆘 Besoin d'aide ?

Si un service ne démarre pas :
1. Vérifiez les logs dans la console
2. Assurez-vous que le config-service fonctionne
3. Vérifiez que PostgreSQL est accessible
4. Vérifiez que le port n'est pas déjà utilisé
