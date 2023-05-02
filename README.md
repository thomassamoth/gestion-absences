# Projet Java

## Sujet général 

[...] Nous souhaitons nous concentrer sur quatre types d’utilisateurs : **Gestionnaire**, **Enseignant**, **Administratif** et **Etudiant**.

### **Gestionnaire** 
* [X] Authentification préalable  
* [ ] Créer un cours : planning hebdomadaire, masse horaire répartie en Amphi, TD, TP ou Examen.  
    Un cours a un nom, une masse horaire et une répartition en nombre d’heures (Amphi, TD, TP ou Examen) répartie par semaine pour une session de 10 semaines, et est dispensé par un enseignant.  
     - Ajouter, supprimer, modifier les caractéristiques d’un cours  
* [X] Créer un étudiant avec sa filière (Classique ou Apprentissage), son nom, son prénom et une adresse mail.
    - Ajouter, supprimer, modifier le profil d’un étudiant
* [X] Créer un enseignant.   
Un enseignant est défini par son nom, son prénom et un numéro de téléphone. Un enseignant dispense un ou plusieurs cours.  
    - Ajouter, supprimer, modifier le profil d’un enseignant
* [X] Créer un groupe d’étudiant.  
Un groupe est défini par son numéro et a une capacité maximale. 
Un groupe suit des cours. Un cours pour un groupe est dispensé par un unique enseignant. 
Un groupe suit un planning de cours hebdomadaire constant sur une session.  
* [ ] Traiter un justificatif d’absence
* [X] Créer les types d’absence
* [ ] Définir les quotas d’absence pour les étudiants
* [ ] Mettre à jour la liste des absences d’un étudiant: justifiée, non justifiée, quota ou hors quota (Cf.
règlement d’assiduité).
* [ ] Déclencher les rattrapages pour les absences justifiées lors d’un examen : notifier à l’étudiant qu’il aura
une session de rattrapage.
* [ ] Attribuer la note ZERO pour une absence non justifiée lors d’un examen
* [ ] Attribuer les pénalités à chaque dépassement de seuil (quota) et déclencher un courrier type pour
l’étudiant (cf. le règlement d’assiduité)

### **Enseignant**  
* [X] Authentification préalable  
* [ ] Consulter son planning et la liste de ses cours
* [ ] Faire l’appel : déclarer une absence pour un étudiant
* [ ] Consulter la synthèse des absences pour ses cours pour un étudiant donné  

### **Etudiant** 
* [X] Authentification préalable  
* [ ] Consulter son planning et la liste de ses cours
* [X] Consulter la liste de ses absences
* [X] Déposer un justificatif pour une ou plusieurs absences  
* [ ] Anticiper une ou plusieurs absences

## Lot 

Un étudiant peut être physiquement absent, mais suivre le cours à distance via une plateforme dédiée.
Dans ce cas, il n’est pas noté absent, mais présent en « distanciel ».  
Cette démarche n’est pas applicable dans le cas d’un examen.

L’**étudiant** concerné doit pouvoir :
* [ ] Déclarer son absence physique en déposant un justificatif pour une période donnée (pas le
même dépôt qu’une absence classique).
* [ ] Vérifier que le gestionnaire a validé sa présence en distanciel (si ce n’est pas le cas, il est noté
absent pour tous les cours concernés (cas traité dans l’application commune)).    
Les cours concernés apparaissent toujours dans son planning avec un lien (fictif !) vers la réunion
dans la plateforme.
La liste de ses absences n’est pas impactée.  

Le **gestionnaire** doit pouvoir :
* [ ] Valider ou invalider le justificatif de l’étudiant (pas dans la même liste que les justificatifs
d’absences classiques).
* [ ] Dans le cas où il valide, créer pour chaque cours un lien (fictif !) vers une réunion dans la
plateforme.  
> **Note** :
On considère que l’enseignant est alerté de la situation en voyant apparaître le lien dans le cours
concerné.   
Il vérifiera alors que l’étudiant est bien connecté pendant toute la durée du cours.
