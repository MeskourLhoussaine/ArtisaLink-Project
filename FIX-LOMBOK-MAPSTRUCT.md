# 🔧 Fix Lombok + MapStruct Incompatibilité Java 21

## ❌ Erreur rencontrée

```
java.lang.NoSuchFieldError: Class com.sun.tools.javac.tree.JCTree$JCImport 
does not have member field 'com.sun.tools.javac.tree.JCTree qualid'
```

## 🔍 Cause

Cette erreur se produit lorsque **Lombok** et **MapStruct** utilisent des versions incompatibles avec **Java 21**. 

Le problème vient de :
- Lombok 1.18.24 (trop ancien pour Java 21)
- MapStruct 1.5.2 (trop ancien pour Java 21)
- Absence du binding entre Lombok et MapStruct

## ✅ Solution appliquée

### Mises à jour des versions

| Dépendance | Ancienne version | Nouvelle version |
|------------|------------------|------------------|
| Lombok | 1.18.24 | **1.18.36** ✅ |
| MapStruct | 1.5.2.Final | **1.6.3** ✅ |
| Lombok-MapStruct-Binding | ❌ Absent | **0.2.0** ✅ |
| Maven Compiler Plugin | (par défaut) | **3.13.0** ✅ |

### Changements dans le pom.xml

1. **Mise à jour de Lombok** :
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.36</version>
    <optional>true</optional>
</dependency>
```

2. **Mise à jour de MapStruct** :
```xml
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.6.3</version>
</dependency>

<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct-processor</artifactId>
    <version>1.6.3</version>
    <scope>provided</scope>
</dependency>
```

3. **Ajout du Lombok-MapStruct-Binding** (IMPORTANT) :
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok-mapstruct-binding</artifactId>
    <version>0.2.0</version>
    <scope>provided</scope>
</dependency>
```

4. **Configuration du Maven Compiler Plugin** :
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.13.0</version>
    <configuration>
        <source>21</source>
        <target>21</target>
        <annotationProcessorPaths>
            <!-- ORDRE IMPORTANT: Lombok d'abord ! -->
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.36</version>
            </path>
            <path>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>1.6.3</version>
            </path>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok-mapstruct-binding</artifactId>
                <version>0.2.0</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

## 🚀 Comment appliquer la correction

### Option 1 : Script automatique (RECOMMANDÉ)

```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\profile-service
fix-lombok-mapstruct.bat
```

Ce script va :
1. Supprimer les fichiers compilés
2. Nettoyer le cache Maven
3. Télécharger les nouvelles versions
4. Recompiler le projet
5. Démarrer le service

### Option 2 : Manuel

```bash
cd C:\Users\Meskour\Desktop\ArtisaLink-Project\profile-service

# Nettoyer complètement
mvn clean

# Supprimer le dossier target
rmdir /s /q target

# Recompiler avec les nouvelles dépendances
mvn clean install -U -DskipTests

# Démarrer
mvn spring-boot:run
```

## ⚠️ Points importants

### 1. Ordre des annotation processors

**L'ordre est CRUCIAL** dans le plugin compiler :
```
1. Lombok (traite les @Data, @Getter, etc.)
2. MapStruct (utilise les classes générées par Lombok)
3. Lombok-MapStruct-Binding (fait le lien entre les deux)
```

### 2. Lombok-MapStruct-Binding

Ce binding est **essentiel** pour que MapStruct puisse :
- Accéder aux getters/setters générés par Lombok
- Utiliser les builders Lombok
- Comprendre les annotations Lombok

Sans ce binding, MapStruct ne "voit" pas les méthodes générées par Lombok.

### 3. Versions compatibles avec Java 21

| Java Version | Lombok minimum | MapStruct minimum |
|--------------|----------------|-------------------|
| Java 17 | 1.18.22 | 1.5.0 |
| Java 21 | **1.18.30+** | **1.6.0+** |

## 🧪 Test après correction

Une fois le service démarré, vérifiez dans les logs :
```
Started ProfileServiceApplication in X seconds
```

Testez l'endpoint :
```
http://localhost:8082/api/profiles
```

## 📝 Si l'erreur persiste

1. **Supprimer complètement le dossier target** :
```bash
rmdir /s /q C:\Users\Meskour\Desktop\ArtisaLink-Project\profile-service\target
```

2. **Nettoyer le cache Maven** :
```bash
rmdir /s /q %USERPROFILE%\.m2\repository\org\projectlombok
rmdir /s /q %USERPROFILE%\.m2\repository\org\mapstruct
```

3. **Recompiler** :
```bash
mvn clean install -U -DskipTests
```

## 🔄 Mettre à jour les autres services

Les autres services (user, post, job) utilisent aussi Lombok. Mettez à jour leur version :

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.36</version>
    <optional>true</optional>
</dependency>
```

Et dans le compiler plugin :
```xml
<path>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.36</version>
</path>
```

## ✅ Résultat attendu

Après correction :
- ✅ Compilation sans erreur
- ✅ MapStruct génère correctement les implémentations
- ✅ Lombok génère les getters/setters
- ✅ Le service démarre normalement

## 🎯 Versions finales (compatibles Java 21)

```xml
<properties>
    <java.version>21</java.version>
    <lombok.version>1.18.36</lombok.version>
    <mapstruct.version>1.6.3</mapstruct.version>
    <lombok-mapstruct-binding.version>0.2.0</lombok-mapstruct-binding.version>
</properties>
```
