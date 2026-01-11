# Config Service - Mode Native (Local)

## 📌 Configuration actuelle

Le config-service est configuré en **mode NATIVE** pour lire les fichiers de configuration directement depuis le système de fichiers local, **SANS avoir besoin de Git**.

### Chemin de configuration
```
C:/Users/Meskour/Desktop/ArtisaLink-Project/artisa-link/config-repo
```

## 🚀 Comment ça fonctionne

1. Le config-service lit les fichiers `.properties` directement depuis le dossier `config-repo`
2. Aucune initialisation Git n'est nécessaire
3. Les modifications dans les fichiers sont prises en compte immédiatement (ou après redémarrage du service)

## 📝 Modifier une configuration

1. Ouvrez le fichier concerné dans `config-repo/` (ex: `post-service.properties`)
2. Modifiez les propriétés
3. Sauvegardez le fichier
4. Redémarrez le service concerné ou utilisez `/actuator/refresh` (si configuré)

## 🔄 Basculer entre Native et Git

### Mode actuel : NATIVE (local)
Dans `config-service/src/main/resources/application.properties` :
```properties
spring.profiles.active=native
spring.cloud.config.server.native.search-locations=file:///C:/Users/Meskour/Desktop/ArtisaLink-Project/artisa-link/config-repo
```

### Pour passer en mode Git
Remplacez par :
```properties
spring.cloud.config.server.git.uri=file:///C:/Users/Meskour/Desktop/ArtisaLink-Project/artisa-link/config-repo
spring.cloud.config.server.git.default-label=main
```

## 🧪 Tester le config-service

Une fois le config-service démarré sur le port 8888, testez :

### Configuration par défaut d'un service
```
http://localhost:8888/post-service/default
http://localhost:8888/user-service/default
http://localhost:8888/profile-service/default
```

### Configuration avec profil
```
http://localhost:8888/post-service/dev
http://localhost:8888/post-service/prod
```

### Health check
```
http://localhost:8888/actuator/health
```

## ✅ Avantages du mode Native

- ✅ Pas besoin d'initialiser Git
- ✅ Pas besoin de commit
- ✅ Modifications visibles immédiatement
- ✅ Plus simple pour le développement local
- ✅ Pas de conflit Git

## ⚠️ Important

En mode native, les fichiers de configuration doivent avoir l'extension `.properties` ou `.yml` et être dans le dossier `config-repo`.
