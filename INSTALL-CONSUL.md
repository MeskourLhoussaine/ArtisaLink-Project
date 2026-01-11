# 🚀 Installation de Consul pour ArtisaLink

## Qu'est-ce que Consul ?

Consul est un service de découverte de services (Service Discovery). Il permet au gateway de savoir sur quels ports les microservices sont disponibles.

## 📥 Installation de Consul

### Option 1 : Téléchargement manuel

1. Téléchargez Consul depuis : https://www.consul.io/downloads
2. Extrayez le fichier ZIP
3. Placez `consul.exe` dans `C:\consul\`
4. Ajoutez `C:\consul` au PATH

### Option 2 : Via Chocolatey (Windows)

```bash
choco install consul
```

## 🚀 Démarrage de Consul

### Démarrage en mode développement (pour tests)

```bash
consul agent -dev
```

Consul sera accessible sur :
- Interface Web : http://localhost:8500
- API : http://localhost:8500/v1/

## ⚙️ Configuration des services

Vos microservices sont déjà configurés pour s'enregistrer dans Consul via :
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

## 📋 Ordre de démarrage avec Consul

1. **Consul** (port 8500)
2. **Config Service** (port 8888)
3. **Microservices** (user, post, profile, job, etc.)
4. **Gateway Service** (port 9999)

## 🔍 Vérification

Une fois Consul et vos services démarrés :

1. Ouvrez http://localhost:8500
2. Vous devriez voir tous vos services enregistrés
3. Le gateway pourra alors router les requêtes

## 🎯 Test du Gateway

```bash
# Via le gateway
curl http://localhost:9999/api/posts

# Directement (sans gateway)
curl http://localhost:8083/api/posts
```

## 🛑 Arrêter Consul

```bash
# Dans le terminal où Consul tourne
Ctrl + C
```

## 📝 Alternative : Gateway sans Consul

Si vous ne voulez pas utiliser Consul, vous pouvez configurer le gateway pour pointer directement vers les URLs des services. Voir le fichier `GATEWAY-SANS-CONSUL.md`.
