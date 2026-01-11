# 🔧 Guide de Dépannage Rapide

## Problème actuel : Erreur 500 du Config Service

### ✅ Ce qui a été corrigé :

1. **Mot de passe PostgreSQL** : Mis à jour à `1994` dans tous les fichiers de configuration
2. **Username PostgreSQL** : Corrigé (il était vide `""` dans post-service)
3. **Driver PostgreSQL** : Ajouté explicitement dans toutes les configurations
4. **Dialect Hibernate** : Ajouté pour PostgreSQL

### 🚀 Étapes de résolution :

#### 1. Arrêter tous les services en cours
```bash
# Fermez toutes les fenêtres de terminal Spring Boot
# Ou exécutez :
stop-all-services.bat
```

#### 2. Vérifier PostgreSQL
```bash
# Assurez-vous que PostgreSQL est démarré et que vous pouvez vous connecter avec :
# Username: postgres
# Password: 1994
```

#### 3. Créer les bases de données (si pas encore fait)
```sql
CREATE DATABASE artisana_user_service;
CREATE DATABASE artisana_profile_service;
CREATE DATABASE artisana_post_service;
CREATE DATABASE artisana_job_service;
CREATE DATABASE artisana_chat_service;
CREATE DATABASE artisana_notification_service;
CREATE DATABASE artisana_search_service;
```

#### 4. Redémarrer le Config Service
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\config-service
mvn clean install -DskipTests
mvn spring-boot:run
```

**Attendez** que vous voyiez dans les logs :
```
Started ConfigServiceApplication in X seconds
```

#### 5. Tester le Config Service
Double-cliquez sur `test-config-service.bat`

Ou ouvrez dans votre navigateur :
```
http://localhost:8888/post-service/default
```

Vous devez voir du JSON avec la configuration !

#### 6. Démarrer le Post Service
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\post-service
mvn spring-boot:run
```

---

## 🔍 Si l'erreur 500 persiste :

### Vérifier les logs du Config Service

Cherchez dans les logs du config-service :
- ❌ `FileNotFoundException` → Le chemin vers config-repo est incorrect
- ❌ `IOException` → Problème de lecture des fichiers
- ❌ `No directory at file://...` → Le dossier config-repo n'existe pas

### Solution : Vérifier le chemin
Le fichier `config-service/src/main/resources/application.properties` doit contenir :
```properties
spring.profiles.active=native
spring.cloud.config.server.native.search-locations=file:///C:/Users/Meskour/Desktop/ArtisaLink-Project/artisa-link/config-repo
```

### Vérifier que les fichiers existent
```bash
dir C:\Users\Meskour\Desktop\ArtisaLink-Project\artisa-link\config-repo
```

Vous devez voir :
- application.properties
- post-service.properties
- user-service.properties
- etc.

---

## 🐛 Erreurs courantes

### Erreur : "Failed to determine a suitable driver class"
**Cause** : Le service ne reçoit pas la configuration du config-service

**Solution** :
1. Vérifiez que le config-service est démarré
2. Testez http://localhost:8888/actuator/health
3. Attendez 30 secondes après le démarrage du config-service
4. Redémarrez le service concerné

### Erreur : "Connection refused" à PostgreSQL
**Cause** : PostgreSQL n'est pas démarré ou le mot de passe est incorrect

**Solution** :
1. Démarrez PostgreSQL
2. Vérifiez le mot de passe : `1994`
3. Testez la connexion avec pgAdmin ou psql

### Erreur : "Database does not exist"
**Cause** : La base de données n'a pas été créée

**Solution** :
```sql
CREATE DATABASE artisana_post_service;
```

---

## 📝 Checklist de vérification

- [ ] PostgreSQL est démarré
- [ ] Les bases de données sont créées
- [ ] Le mot de passe PostgreSQL est `1994`
- [ ] Le config-service démarre sans erreur
- [ ] http://localhost:8888/actuator/health retourne `{"status":"UP"}`
- [ ] http://localhost:8888/post-service/default retourne du JSON
- [ ] J'attends 30 secondes après le démarrage du config-service
- [ ] Le post-service démarre après le config-service

---

## 🆘 Contact

Si le problème persiste après avoir suivi ces étapes :
1. Copiez les logs complets du config-service
2. Vérifiez les erreurs dans les logs
3. Partagez le message d'erreur exact
