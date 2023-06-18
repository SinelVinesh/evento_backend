# Spring Utils

## Introduction

Spring Utils permet de faciliter la création des CRUD des différentes tables, avec REST. Elle permet en quelques lignes de réaliser les tâches monotones des CRUD, donnant ainsi au développeur un gain de temps considérable.

## Installation

Importer le fichier spring-utils.jar dans votre projet.

via **Maven** :

```xml
<dependency>
            <groupId>custom.springutils</groupId>
            <artifactId>spring-utils</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>system</scope>
            <systemPath>${basedir}/lib/spring-utils.jar</systemPath>
        </dependency>
```

Mettre le fichier jar dans un dossier jar à la source du projet

Java 17 est requis minimum.

## Architecture

Le projet est structuré comme suit :

- Model
- Repository
- Service
- Rest Controller

### Model

Un modèle est une entité qui a sa représentation dans la base de données
Tous les modèles doivent être annotés de l’annotation @Entity et héritent de la classe HasId.
Voici un exemple de modèle :

```java
package eval.p13_vehicle.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.String;
import java.lang.Integer;

@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin extends HasId {

	private String password;
	private String name;
	private String email;

}
```

Cependant, les modèles peuvent aussi hériter d’autres classes qui héritent de la classe HasId :

- HasName
- HasFK

Les classes héritant de la classe HasFK permettent de gérer les clés étrangères.
Voici un exemple de modèle héritant de la classe HasFK :

```java
package com.management.students.model.school;

import custom.springutils.exception.CustomException;
import custom.springutils.model.HasFK;
import custom.springutils.model.HasName;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClassLevel extends HasFK<School> {

    private String name;

    private Integer schoolId;

    @Override
    public void setFK(School school) throws CustomException {
        if (school == null) throw new CustomException("school is null");
        setSchoolId(schoolId);
    }
}
```

Tout d’abord, il faut préciser la classe générique qui représente la clé étrangère dans les chevrons. Dans cet exemple, on a ainsi défini la classe School comme étant clé étrangère de la classe ClassLevel.

### Repository

Un repository permet la relation entre la base de données et la couche de métier pour un modèle donnée.
Voici un exemple de repository :

```java
package com.management.students.repository;

import com.management.students.model.LevelSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LevelScheduleRepository extends JpaRepository<LevelSchedule, Long> {

    @Query(value = "SELECT id, subject_id, class_level_id, start_time, end_time, frequency_id, schedule_date, week_day from v_school_schedule where\n" +
            "    school_id = ?1", nativeQuery = true)
    public List<LevelSchedule> getAllForAWeekAndForASchool(Long schoolId);

		public LevelSchedule findAllByClassLevel(ClassLevel classLevel);
}
```

Les fonctions CRUD de base sont déjà incluses, plus besoin de les définir. Si vous avez des requêtes spécifiques, vous pouvez utiliser l’annotation @Query.
Si vous voulez trouver des objets par rapport à ces attributs, vous pouvez définir votre fonction comme la fonction findAllByClassLevel : ici, la classe LevelSchedule a un attribut classLevel.

### Service

Les traitement d’informations, algorithmes sont effectués dans les services.
Dans Spring Utils, il existe déjà des services généralisés que vous pouvez utilisés :

- **CrudService**
- **CrudServiceWithFK**
- **LoginService**

1. **CrudService**
    
    Les classes héritant de la classe CrudService héritent de toutes les fonctions basiques de CRUD. A utiliser pour les modèles n’héritant pas de HasFK.
    Voici un exemple de service héritant de la classe CrudService :
    
    ```java
    package eval.p13_vehicle.services;
    
    import eval.p13_vehicle.repositories.AdminRepo;
    import custom.springutils.service.CrudService;
    import jakarta.persistence.EntityManager;
    import org.springframework.stereotype.Service;
    import java.lang.String;
    import java.lang.Integer;
    import eval.p13_vehicle.models.Admin;
    
    @Service
    public class AdminService extends CrudService<Admin, AdminRepo> {
    
        public AdminService(AdminRepo repo, EntityManager manager) {
            super(repo, manager);
        }
    
        @Override
        public Class<Admin> getEntityClass() {
            return Admin.class;
        }
    
    }
    ```
    
    Tout d’abord, il faut préciser la classe générique dont le CRUD sera géré dans les chevrons. Dans cet exemple, le service AdminService contient ainsi toutes les fonctions basiques du CRUD de la classe Admin.
    Ensuite, il faut aussi préciser le repository principal, ici c’est AdminRepo qui sera utilisé pour ce service ( vous pouvez bien sur y appeler d’autres repositories). Ainsi, les fonctions de AdminRepo peuvent être appelées et utilisées dans ce service. 
    Enfin,la fonction **getEntityClass** définit quelle classe sera utilisé pour la **lecture,** à savoir les listes.  ****Vous pouvez par exemple y définir une classe représentant une vue. Ainsi, si on **écrit (CUD), la classe Admin** sera utilisée, **et si on lit, la classe VAdmin** par exemple sera utilisée**.**
    
2. **CrudServiceWithFK**
    
    Les classes héritant de CrudServiceWithFK ne fonctionnent que pour les modèles héritant de HasFK. En effet, en plus des fonctions basiques de CRUD, ce type de service permet de gérer les clés étrangères.
    Voici un exemple de service héritant de CrudServiceWithFK :
    
    ```java
    package eval.p13_vehicle.services;
    
    import custom.springutils.service.CrudServiceWithFK;
    import jakarta.persistence.EntityManager;
    import org.springframework.stereotype.Service;
    
    import java.lang.String;
    import java.lang.Integer;
    import eval.p13_vehicle.models.Brand;
    import eval.p13_vehicle.repositories.BrandRepo;
    import eval.p13_vehicle.models.Vehicle;
    import eval.p13_vehicle.repositories.VehicleRepo;
    
    @Service
    public class VehicleService extends CrudServiceWithFK<Vehicle, Brand, BrandRepo, VehicleRepo> {
    
        public VehicleService(VehicleRepo repo, EntityManager manager, BrandRepo refRepo) {
            super(repo, manager, refRepo);
        }
    
        @Override
        public Object getFKValue(Brand ref) {
            return ref;
        }
    
        @Override
        public Class<Vehicle> getEntityClass() {
            return Vehicle.class;
        }
    
        @Override
        public String getFkName() {
            return "brand";
        }
    }
    ```
    
    Tout d’abord, certaines classes génériques doivent être précisées dans les chevrons :
    
    - **Vehicle** est le modèle principal de ce service
    - **Brand** est la clé étrangère de Vehicle
    - **BrandRepo** est le repository associé à Brand
    - **VehicleRepo** est le repository associé à Vehicle
    
    La fonction **getFKValue** retourne l’objet de référence représentant la clé étrangère. Ici, on retourne directement un objet Brand, mais si dans votre classe Vehicle vous avez mis comme attribut un Integer brandId, alors **getFKValue** retournera un Integer.
    
    Ensuite, la fonction **getFkName** retourne le nom de l’attribut représentant la clé étrangère. Ici, l’attribut de type Brand dans la classe Vehicle s’appelle “brand”. ATTENTION, SI CETTE FONCTION RETOURNE LE NOM D’UN ATTRIBUT NON EXISTANT, IL Y AURA EXCEPTION.
    
    Ainsi, ce service inclut toutes les fonctions basiques de CRUD, permettant de gérer les véhicule de type Vehicle associés à la clé étrangère Brand référencée.
    

 

### Controller

Spring Utils utilise des controllers REST, qui peuvent ensuite être appelés dans d’autres applications, comme une application React ou Angular par exemple.
Dans Spring Utils, il existe déjà des services généralisés que vous pouvez utilisés :

- **CrudController**
- **CrudWithFK**

1. **CrudController**
    
    Les controllers héritant de CrudController incluent toutes les fonctions basiques du CRUD du modèle associé.
    Voici un exemple de controller héritant de CrudController :
    
    ```java
    package eval.p13_vehicle.controllers;
    
    import custom.springutils.controller.CrudController;
    import eval.p13_vehicle.services.BrandService;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    import eval.p13_vehicle.models.Brand;
    
    @RestController
    @RequestMapping("/brands")
    public class BrandController extends CrudController<Brand, BrandService, Object> {
    
        public BrandController(BrandService service) {
            super(service);
        }
    
    }
    ```
    
    Tout d’abord, certaines classes génériques doivent être précisées dans les chevrons :
    
    - **Brand** est le modèle associé à ce controller
    - **BrandService** est le service associé au modèle Brand : c’est le service principal de ce controller, touts les fonctions de ce service peuvent être appelées dans ce controller (bien sur, on peut toujours y appeler d’autres services ).
    - **Object** est l’objet utilisé pou le filtre (  voir la section **Recherches Avancées et paginations** pour plus de détails ).
    
    Le controller **BrandController** possède donc déjà toutes les fonctions basiques de CRUD du modèle Brand.
    L’annotation @RequestMapping indique que l’url pour accéder à ces fonctions est “/brands”. Les requêtes qui y sont associées suivent les normes et conventions des requêtes REST.
    Voici les requêtes déjà disponibles pour ce controller :
    
    - **POST /brands** pour créer un nouveau Brand
    - **GET /brands/id** pour obtenir un Brand à partir de son id
    - **GET /brands** pour obtenir tous les Brand
    - **PUT /brands/id** pour modifier un Brand à partir de son id
    - **DELETE /brands/id** pour supprimer un Brand à partir de son id
    
2. **CrudWithFK**
    
    Les controllers héritant de CrudWithFK, comme CrudController incluent aussi toutes les fonctions basiques du CRUD du modèle associé, mais ce modèle associé doit hériter de HasFK.
    Voici un exemple de controller héritant de CrudWithFK :
    
    ```java
    package eval.p13_vehicle.controllers;
    
    import custom.springutils.controller.CrudWithFK;
    import eval.p13_vehicle.models.Vehicle;
    import eval.p13_vehicle.services.VehicleService;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    
    import eval.p13_vehicle.services.BrandService;
    import eval.p13_vehicle.models.Brand;
    
    @RestController
    @RequestMapping("/brands/{fkId}/vehicles")
    public class VehicleController extends CrudWithFK<Brand, BrandService, Vehicle, VehicleService, Object> {
    
        public VehicleController(VehicleService service, BrandService fkService) {
            super(service, fkService);
        }
    
    }
    
    ```
    
    Tout d’abord, certaines classes génériques doivent être précisées dans les chevrons :
    
    - **Brand** est le clé étrangère associé au modèle principal associé à ce controller, Vehicle
    - **BrandService** est le service associé au modèle Brand, clé étrangère :toutes les fonctions de ce service peuvent être appelées dans ce controller (bien sur, on peut toujours y appeler d’autres services ).
    - **Vehicle** est le modèle associé à ce controller
    - **VehicleService** est le service associé au modèle Brand : c’est le service principal de ce controller, touts les fonctions de ce service peuvent être appelées dans ce controller (bien sur, on peut toujours y appeler d’autres services ).
    - **Object** est l’objet utilisé pou le filtre (  voir la section **Recherches Avancées et paginations** pour plus de détails ).
    
    Le controller **VehicleController** possède donc déjà toutes les fonctions basiques de CRUD du modèle Brand.
    L’annotation @RequestMapping indique que l’url pour accéder à ces fonctions est “/brands/fkId/vehicles”. 
    Ici, fkId représente l’id de la clé étrangère référencée. Ainsi, ce controller permet de gérer, non pas tous les Vehicle, mais seulement les Vehicle qui sont liés au Brand dont l’id est fkId..
    Les requêtes qui y sont associées suivent les normes et conventions des requêtes REST.
    Voici les requêtes déjà disponibles pour ce controller :
    
    - **POST /brands/fkId/vehicles** pour créer un nouveau Vehicle dont la marque est le Brand qui possède l’id fkId
    - **GET /brands/fkId/vehicles/id** pour obtenir un Vehicle à partir de son id, sachant que ce Vehicle possède comme Brand celui qui possède l’id fkId
    - **GET /brands/fkId/vehicles** pour obtenir tous les Vehicle dont la marque est le Brand qui possède l’id fkId
    - **PUT /brands/fkId/vehicles/id** pour modifier un Vehicle à partir de son id, sachant que ce Vehicle possède comme Brand celui qui possède l’id fkId
    - **DELETE /brands/fkId/vehicles/id** pour supprimer un Vehicle à partir de son id, sachant que ce Vehicle possède comme Brand celui qui possède l’id fkId
    

## Méthodes sur les listes

Spring Utils facilite également les listes. En effet, Spring Utils gère déjà les paginations, les recherches multi-critères, ainsi que le tri.
Pour illustrer ces fonctionnalités, prenons comme exemple le modèle Vehicle :

```java
package eval.p13_vehicle.models;

import custom.springutils.model.HasFK;
import custom.springutils.exception.CustomException;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.lang.String;
import java.lang.Integer;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
public class Vehicle extends HasFK<Brand> {

	private String registrationNumber;
	private Integer brandId;

    @Override
    public void setFK(eval.p13_vehicle.models.Brand fk) throws CustomException {
        if (fk == null || fk.getId() == null) {
            throw new CustomException("eval.p13_vehicle.models.Brand is null");
        }
        setBrandId(fk.getId().intValue());
    }
}
```

### Paginations

Tout d’abord, vous pouvez définir le nombre d’éléments par page dans le service du modèle concerné, ici VehicleService, avec la fonction **getPageSize() :**

```java
package eval.p13_vehicle.services;

import custom.springutils.service.CrudServiceWithFK;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.lang.String;
import java.lang.Integer;
import eval.p13_vehicle.models.Brand;
import eval.p13_vehicle.repositories.BrandRepo;
import eval.p13_vehicle.models.Vehicle;
import eval.p13_vehicle.repositories.VehicleRepo;

@Service
public class VehicleService extends CrudServiceWithFK<Vehicle, Brand, BrandRepo, VehicleRepo> {

    public VehicleService(VehicleRepo repo, EntityManager manager, BrandRepo refRepo) {
        super(repo, manager, refRepo);
    }

    @Override
    public Object getFKValue(Brand ref) {
        return ref.getId();
    }

    @Override
    public Class<Vehicle> getEntityClass() {
        return Vehicle.class;
    }

    @Override
    public String getFkName() {
        return "brandId";
    }

    @Override
    public int getPageSize() {
        return 1;
    }
}
```

Ici, on a redéfini **getPageSize()** pour qu’il retourne 1, c’est-à-dire 1 élément par page. Mais vous n’êtes pas obligé de redéfinir cette fonction, en effet par défaut, cette fonction retourne 10, soit 10 éléments par page.

Ensuite, il suffit d’appeler l’url de findAll du controller concerné en ajoutant un paramètre page ( la première page est 1 ) :

 **GET /brands/fkId/vehicles?page=1**

Notons que en plus de la liste, vous aurez également le numéro de page, le nombre d’éléments sur la page et le nombre d’éléments total.

### Recherches avancées

Spring Utils facilite également beaucoup les recherches avancées.
Tout d’abord, voici tous les opérateurs possibles :

```java
public enum SearchOperator {
    min(">"),
    mineq(">="),
    eq("="),
    max("<"),
    maxeq("<="),
    like("like"),
    noteq("!="),
    ilike("iLike"),
    isnull("is null"),
    isnotnull(" is not null");

    public final String value;

    private SearchOperator(String value) {
        this.value = value;
    }
}
```

Dans la classe **VehicleFilter** que nous allons créer, nous n’allons mettre que les attributs concernés par la recherche. Ensuite, si vous voulez utiliser les opérateurs ci-dessus, nommez l’attribut comme suit : **OperatorName_AttributeName**
Voici le code source de la classe **VehicleFilter** :

```java
package eval.p13_vehicle.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleFilter {

    private Integer id;
    private Boolean isnull_registrationNumber;

}
```

Notons tout d’abord que pour l’opérateur = , vous n’êtes pas obliger d’écrire le préfixe **eq** devant le nom de l’attribut. Ainsi, dans notre exemple, i**d** équivaut à **eq_id**.
Ensuite remarquons que **isnull_registrationNumber** retourne un **Boolean**. En effet, le type d’opérateur de tous vos attributs doivent être les mêmes que les attributs de base, sauf si vous utiliser les opérateurs **isnull** et **isnotnull,** là vos attributs retournent un **Boolean**.

Ensuite, vous devez préciser dans le controller associé à Vehicle l’objet de filtre utilisé, ici **VehicleFilter**.
Voici le code source de **VehicleController** :

```java
package eval.p13_vehicle.controllers;

import custom.springutils.controller.CrudWithFK;
import eval.p13_vehicle.models.Vehicle;
import eval.p13_vehicle.models.VehicleFilter;
import eval.p13_vehicle.services.VehicleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eval.p13_vehicle.services.BrandService;
import eval.p13_vehicle.models.Brand;

@RestController
@RequestMapping("/brands/{fkId}/vehicles")
public class VehicleController extends CrudWithFK<Brand, BrandService, Vehicle, VehicleService, VehicleFilter> {

    public VehicleController(VehicleService service, BrandService fkService) {
        super(service, fkService);
    }

}
```

On a ainsi précisé dans les chevrons **VehicleFilter**.

Maintenant, pour faire une recherche, il suffit d’ajouter des paramètres à l’url de findAll du controller concerné :

**GET /brands/fkId/vehicles?id=1&isnull_registrationNumber=true**

Ainsi, on obtient les véhicules dont **l’id est 1** et **registrationNumber est null.**

Cependant, vous n’êtes pas obligés d’utiliser les mêmes noms d’attributs que ceux de l’entité. Vous pouvez utiliser l’annotation **@Filter**

```java
public class VehicleFilter {

		@Filter("id")
    private Integer identifiant;

		@Filter("isnull_registrationNumber")
    private Boolean matriculeNull;

}
```

**GET /brands/fkId/vehicles?identifiant=1&matriculeNull=true**

Comme précédemment, on obtient les véhicules dont **l’id est 1** et **registrationNumber est null.**

Cependant, si votre recherche n’est pas réalisable avec les simples opérateurs, notamment parce que vous avez une suite de conditions or et non and, vous pouvez toujours préciser manuellement vos conditions en ajoutant cette fonction dans le service **VehicleService** :

```java
@Override
    public List<Condition> getAdditionalConditionFrom(Object filter) throws Exception {
        List<Condition> list = super.getAdditionalConditionFrom(filter);
        Condition condition = new Condition();
        condition.setCondition(" and (registrationNumber is null or id=1)");
        list.add(condition);
        return list;
    }
```

Vous pouvez y ajouter autant de conditions que vous le voulez. Notons que ces conditions doivent être écrits en **JPQL**. Ainsi, si vous appelez  l’url de findAll du controller concerné, même sans paramètres, les conditions de **getAdditionalConditionFrom** seront prises en compte.
Exemple :

**GET /brands/fkId/vehicles**

Ainsi, on obtient les véhicules dont **l’id est 1** OU **registrationNumber est null.**

Cela fonctionne aussi si vos attributs sont des entités.
Par exemple, mettons un attribut chauffeur de type **Driver** dans **Vehicle.** Voici alors notre **VehicleFilter** :

```java
package eval.p13_vehicle.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleFilter {

    private Integer id;
    private Boolean isnotnull_registrationNumber;
    private Driver driver;

}
```

Cependant, vous ne pouvez que utiliser l’id de Driver dans vos recherches, comme suit :

**GET /brands/fkId/vehicles?driver.id=1**

Ainsi, on obtient les véhicules dont **l’id du chauffeur est 1.**
Notons également que tous les opérateurs vus précédemment fonctionnent aussi, mais seulement sur la colonne **id**. 
Exemple, utilisons l’opérateur **max** :

```java
private Driver max_driver;
```

Et on appelle :

**GET /brands/fkId/vehicles?max_driver.id=3**

Ainsi, on obtient les véhicules dont **l’id du chauffeur est inférieur à 3.**

Mais si vous utiliser les opérateurs **isnull** ou **isnotnull,** on procède comme suit **:**

```java
private Boolean isnull_driver;
```

Et on appelle :

**GET /brands/fkId/vehicles?isnull_driver=true**

Ainsi, on obtient les véhicules qui n’ont pas de chauffeurs**.**

Vous pouvez aussi envoyer des paramètres qui ne seront pas utilisés pour le filtre, avec l’annotation IgnoreMapping.
Voici un exemple : 

```java
package eval.p13_vehicle.models;

import lombok.Getter;
import lombok.Setter;
import custom.springutils.util.map.IgnoreMapping;

@Getter
@Setter
public class VehicleFilter {

    private Integer id;
    private Boolean isnull_registrationNumber;

		@IgnoreMapping
		private String search;

		public void setSearch(String search) {
        if (search!= null) {
            this.search= search;
            setId(Integer.valueOf(search));
        }

    }

}
```

Et on appelle :

**GET /brands/fkId/vehicles?search=1**

Ici, nous avons envoyer le paramètre **search**, **qui n’est pas un attribut** de notre entité **Vehicle**. Nous avons donc annoté l’attribut **search de VehicleFilter** avec l’annotation **IgnoreMapping**.
Ici, search sera alors ensuite traité dans son setter.
Ainsi, on obtient les véhicules qui dont l’id est 1**.**

### Tri

Spring Utils facilite également beaucoup le tri, et ce sur les colonne que vous souhaitez. Pour cela, il faut juste que votre classe de filtre, ici **VehicleFilter**, hérite de **FilterObject**.

```java

public class VehicleFilter extends FilterObject {

    private Integer id;
    private Boolean isnull_registrationNumber;

}
```

Il suffit ensuite de définit les paramètres **field** et **method** :
**field représente l’attribut** de l’entité sur lequel le tri va s’appliquer
**method** représente la méthode du tri : **ASC** ou **DESC**

**GET /brands/fkId/vehicles?field=registrationNumber&method=ASC**

On obtient ainsi une liste triée par **registrationNumber** dans l’ordre **croissant**.

Notons également que si l’attribut est une entité, vous pouvez utiliser l’id de cette entité :

**GET /brands/fkId/vehicles?field=driver.id&method=DESC**

On obtient ainsi une liste triée par l’**id du chauffeur** dans l’ordre **décroissant.**

## Authentification

Spring Utils facilite également beaucoup les tâches d’authentification, comme le login, les tokens et l’inscription.

Nous allons suivre l’architecture de Spring Utils pour comprendre comment l’utiliser.

### Model

Comme toutes les entités de Spring Utils, le modèle correspondant à l’utilisateur qui va s`authentifier doit au moins hérité de **HasId,** mais doit aussi implémenter **LoginEntity** :

```java
package eval.p13_vehicle.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import custom.springutils.LoginEntity;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.String;
import java.lang.Integer;

@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin extends HasId implements LoginEntity {

	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String name;

	@Column(nullable = false)
	private String email;

	public void setPassword(String password) throws CustomException {
		if (password == null) throw new CustomException("please add password");
		this.password = DigestUtils.sha1Hex(password);
	}

}
```

**NB** : Il est préférable que les attributs contenant les informations de connexion s’appellent **email** ET **password.**
Cependant, si vous les appelez autrement, vous devrez **redéfinir les fonctions** :

```java
@Override
public String getEmail() {
	return yourEmailAttribute;
}

@Override
public String getPassword() {
	return your yourPasswordAttribute;
}
```

- **@Column(nullable = false)**   pour dire que les attributs email et password ne peuvent être nulls
- **@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)**     pour dire que password est uniquement accessible en écriture

Notons également que dans **setPassword**, nous hashons le password avec **sha1** car le mot de passe stocké dans la base de données est crypté de cette manière.

Vous devrez aussi créer le modèle représentant le token :

```java

package eval.p13_vehicle.models;

import custom.springutils.model.TokenEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AdminToken extends TokenEntity {

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}

```

Le modèle du token doit hériter de **TokenEntity**. Ainsi, votre modèle possède déjà les attributs **token** (String) et **expirationDate** (Timestamp). La table admin_token doit donc impérativement contenir les colonnes **token** et **expiration_date**. De plus, la **clé primaire** de cette table est la colonne **token**.

### Repository

Le repository de Admin doit non seulement hérité de JpaRepository, mais aussi de **LoginRepo** :

```java
package eval.p13_vehicle.repositories;

import custom.springutils.LoginRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import eval.p13_vehicle.models.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long>, LoginRepo<Admin> {
}
```

**NB** : Il est préférable que les attributs contenant les informations de connexion s’appellent **email** ET **password.**
Cependant, si vous les appelez autrement, vous devrez **redéfinir les fonctions** :

```java

public List<Admin> findByYourEmailAttribute(String yourEmailAttribute);

@Override
    default List<Admin> findByEmail(String email) {
        return this.findByYourEmailAttribute(email);
    }
```

Vous devez aussi créer le repository pour le token :

```java
package eval.p13_vehicle.repositories;

import eval.p13_vehicle.models.AdminToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminTokenRepo extends JpaRepository<AdminToken, String> {
}
```

### Service

Sachez tout d’abord que le service **AdminLoginService** que nous allons créer n’inclut pas les fonctions basiques de CRUD. Si vous voulez utiliser les fonctions de CRUD, il faudra créer un **autre service AdminService héritant de CrudService ou CrudServiceWithFK** comme on a vu précédemment. 
Voici le service **AdminLoginService  :**

```java
package eval.p13_vehicle.services;

import custom.springutils.model.TokenEntity;
import custom.springutils.service.LoginService;
import eval.p13_vehicle.models.Admin;
import eval.p13_vehicle.models.AdminToken;
import eval.p13_vehicle.repositories.AdminRepo;
import eval.p13_vehicle.repositories.AdminTokenRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AdminLoginService extends LoginService<Admin, AdminRepo, AdminToken, AdminTokenRepo> {

    public AdminLoginService(AdminRepo repo, AdminTokenRepo tokenRepo) {
        super(repo, tokenRepo, AdminToken.class);
    }
}
```

Ce service inclut déjà les fonctions **login, register, isConnected, saveToken, logout**. Cependant, si vous avez ajouter d’autres attributs dans votre Token et vous voulez que ces attributs soient prises en compte lors de la sauvegarde du token, vous pouvez redéfinir la fonction **createToken**.

```java
@Override
    protected AdminToken createToken(Admin entity) throws InstantiationException, IllegalAccessException {
        AdminToken token = super.createToken(entity);
        token.setAdmin(entity);
        return token;
    }
```

Comme nous avons ajouter un attribut admin, alors nous ajoutons la ligne **token.setAdmin(entity)** dans la fonction **createToken.**

### Controller

Sachez tout d’abord que le conroller **AdminLoginController** que nous allons créer n’inclut pas les fonctions basiques de CRUD. Si vous voulez utiliser les fonctions de CRUD, il faudra créer un **autre controller AdminController héritant de CrudController ou CrudWithFK** comme on a vu précédemment. 
Voici le controller **AdminLoginController  :**

```java
package eval.p13_vehicle.controllers;

import custom.springutils.controller.LoginController;
import eval.p13_vehicle.models.Admin;
import eval.p13_vehicle.repositories.AdminRepo;
import eval.p13_vehicle.services.AdminLoginService;

@RestController
@RequestMapping("/admins-authentification")
public class AdminLoginController extends LoginController<Admin, AdminLoginService> {

    public AdminLoginController(AdminLoginService service) {
        super(service);
    }

    @Override
    public String getRequestHeaderKey() {
        return "admin_token";
    }
}
```

Notons tout d’abord que la fonction **getRequestHeaderKey** doit être redéfini : il s’agit du nom de la clé dans le header de la requête contenant par exemple le token de l’admin.

Voici donc les fonctionnalités incluses dans ce controller :

1. **Login**
    1. **Requête**
        
        **POST /admins-authentification/login**
        
    2. **Input**
        
        Un JSON de la forme :
        
        ```json
        {
            "email": "andy@mail.com",
            "password": "1234"
        }
        ```
        
        Ce sont les informations que l’admin a entré dans un formulaire par exemple
        
2. **Logout**
    1. **Requête**
        
        **DELETE /admins-authentification/logout**
        
    2. **Input**
        
        Mettre dans le header une clé nommée "admin_token" (  ce que retourne **getRequestHeaderKey**  ) contenant le token de l’admin par exemple
        
3. **isConnected**
    1. **Requête**
        
        **POST /admins-authentification/isconnected**
        
    2. **Input**
        
        Mettre dans le header une clé nommée "admin_token" (  ce que retourne **getRequestHeaderKey**  ) contenant le token de l’admin par exemple
        
4. **Register**
    1. **Requête**
        
        **POST /admins-authentification/register**
        
    2. **Input**
        
        Un JSON représentant l’admin de la forme :
        
    
    ```json
    {
        "name": "andy",
        "email": "andy@mail.com",
        "password": "1234"
    }
    ```
    
    Ce sont les informations que l’admin a entré dans un formulaire par exemple
    

## PDF

Spring Utils permet égalemet de générer des PDF facilement pour une entité.

### Model

Creer le modele et annoter les getters avec l’annotation @PdfColumn

```java
public class Person extends HasId {

	private String name;
	private Integer age;

	@PDFColumn(value = "Nom", width = 50, order=0)
	public String getName() {
		return name;
	}

	@PDFColumn(value = "Age", width = 50, order=1)
	public Integer getAge() {
		return age;
	}

}

```

- value: nom du colonne dans la table
- width(%): pourcentage
- ordre: ordre dans la table

### Service

Le service de l’entité, s’il **hérite de CrudService**, inclut déjà les fonctions **createPDF**, **getPdfPath** et **getPdfTitle**.

- **getPdfPath** retourne par défaut ****`"pdf/" + this.getClassName() + ".pdf"` (redéfinissable)
- **getPdfTitle** retourne par défaut ****`"List of " + this.getClassName().toLowerCase()` (redéfinissable)

### Controller

Le controller de l’entité, s’il hérite de CrudController, inclut inclut déjà la fonction **createPDF, accessible via l’url** `"/pdf"`.

**GET /persons/pdf**

****

[Person.pdf](Spring%20Utils%20ed6b82517d184d718db8799a5e193330/Person.pdf)