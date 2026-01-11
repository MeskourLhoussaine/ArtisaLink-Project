# 🔍 Diagnostic Gateway - http://localhost:9999/api/posts ne fonctionne pas

## ✅ Checklist de diagnostic

### 1. Le post-service fonctionne-t-il directement ?

Testez d'abord SANS le gateway :
```
http://localhost:8083/api/posts
```

**Si ça fonctionne** → Le problème est dans le gateway
**Si ça ne fonctionne pas** → Le problème est dans le post-service

---

### 2. Le post-service est-il démarré ?

Vérifiez dans vos terminaux/consoles que le post-service :
- Est démarré
- Affiche : `Tomcat started on port 8083`
- Affiche : `Started PostServiceApplication in X seconds`
- N'a pas d'erreurs

**Test rapide :**
```
curl http://localhost:8083/api/posts
```

---

### 3. Le gateway-service est-il démarré ?

Vérifiez que le gateway :
- Est démarré sur le port 9999
- Affiche : `Netty started on port 9999`
- N'a pas d'erreurs de configuration

**Test rapide :**
```
curl http://localhost:9999/actuator/health
```

---

### 4. Consul est-il nécessaire ?

**IMPORTANT** : J'ai désactivé Consul dans la nouvelle configuration.

Si vous voyez cette erreur :
```
Connection refused: localhost/127.0.0.1:8500
```

C'est normal, Consul est désactivé. Le gateway utilise maintenant des URLs directes.

---

## 🚀 Solution : Redémarrer avec la nouvelle configuration

### Étape 1 : Arrêter tous les services
```bash
# Exécutez ce script :
stop-all-services.bat
```

### Étape 2 : Démarrer dans l'ordre

**1. Config Service**
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\config-service
mvn spring-boot:run
```
Attendez : `Started ConfigServiceApplication`

**2. Post Service**
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\post-service
mvn spring-boot:run
```
Attendez : `Started PostServiceApplication`

**3. Gateway Service**
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\gateway-service
mvn spring-boot:run
```
Attendez : `Netty started on port 9999`

### Étape 3 : Tester

**Test direct du post-service :**
```
http://localhost:8083/api/posts
```

**Test via le gateway :**
```
http://localhost:9999/api/posts
```

---

## 🐛 Erreurs courantes

### Erreur 1 : "Connection refused" sur 8083

**Cause** : Le post-service n'est pas démarré

**Solution** :
```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\post-service
mvn spring-boot:run
```

---

### Erreur 2 : "404 Not Found" via le gateway

**Cause** : La route n'est pas correctement configurée

**Solution** : Vérifiez le fichier `gateway-service/src/main/resources/application.properties`

Il doit contenir :
```properties
spring.cloud.gateway.routes[2].id=post-service
spring.cloud.gateway.routes[2].uri=http://localhost:8083
spring.cloud.gateway.routes[2].predicates[0]=Path=/api/posts/**
```

---

### Erreur 3 : "503 Service Unavailable"

**Cause** : Le gateway ne peut pas joindre le post-service

**Solutions possibles** :
1. Le post-service n'est pas démarré → Démarrez-le
2. Le post-service est sur un autre port → Vérifiez les logs
3. Firewall/antivirus bloque la connexion → Désactivez temporairement

---

### Erreur 4 : "Connection refused: localhost/127.0.0.1:8500"

**Cause** : Le gateway essaie de se connecter à Consul qui n'est pas démarré

**Solution** : J'ai déjà ajouté ces lignes dans `application.properties` :
```properties
spring.cloud.consul.enabled=false
spring.cloud.consul.discovery.enabled=false
```

Si l'erreur persiste, redémarrez le gateway.

---

## 🎯 Test complet avec script

Exécutez :
```bash
test-gateway.bat
```

Ce script teste automatiquement toutes les routes.

---

## 📋 Ordre de démarrage correct

1. ✅ Config Service (port 8888)
2. ✅ Post Service (port 8083)
3. ✅ Gateway Service (port 9999)

---

## 🆘 Si rien ne fonctionne

### Option 1 : Script automatique
```bash
restart-clean.bat
```

### Option 2 : Logs détaillés

Dans le terminal du gateway, cherchez :
```
Mapped [/api/posts/**] to Route[id=post-service, ...]
```

Si vous ne voyez pas ça, la route n'est pas chargée.

### Option 3 : Test direct

Oubliez le gateway temporairement et testez directement :
```
http://localhost:8083/api/posts
```

Si ça fonctionne, le problème est uniquement dans le gateway.

---

## ✅ Configuration finale

**gateway-service/src/main/resources/application.properties** :
- Port : 9999
- Consul : Désactivé
- Routes : URLs directes (http://localhost:8083)

**post-service/src/main/resources/application.properties** :
- Port : 8083
- Base de données : artisana_post_service
- Password PostgreSQL : 1994
