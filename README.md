# Projet Java

## Sujet g&eacute;n&eacute;ral 

[...] Nous souhaitons nous concentrer sur quatre types d’utilisateurs : **Gestionnaire**, **Enseignant**, **Administratif** et **Etudiant**.

### **Gestionnaire** 
* [X] Authentification pr&eacute;alable  
* [ ] Cr&eacute;er un cours : planning hebdomadaire, masse horaire r&eacute;partie en Amphi, TD, TP ou Examen.  
    Un cours a un nom, une masse horaire et une r&eacute;partition en nombre d’heures (Amphi, TD, TP ou Examen) r&eacute;partie par semaine pour une session de 10 semaines, et est dispens&eacute; par un enseignant.  
     - Ajouter, supprimer, modifier les caract&eacute;ristiques d’un cours  
* [X] Cr&eacute;er un &eacute;tudiant avec sa filière (Classique ou Apprentissage), son nom, son pr&eacute;nom et une adresse mail.
    - Ajouter, supprimer, modifier le profil d’un &eacute;tudiant
* [X] Cr&eacute;er un enseignant.   
Un enseignant est d&eacute;fini par son nom, son pr&eacute;nom et un num&eacute;ro de t&eacute;l&eacute;phone. Un enseignant dispense un ou plusieurs cours.  
    - Ajouter, supprimer, modifier le profil d’un enseignant
* [X] Cr&eacute;er un groupe d’&eacute;tudiant.  
Un groupe est d&eacute;fini par son num&eacute;ro et a une capacit&eacute; maximale. 
Un groupe suit des cours. Un cours pour un groupe est dispens&eacute; par un unique enseignant. 
Un groupe suit un planning de cours hebdomadaire constant sur une session.  
* [ ] Traiter un justificatif d’absence
* [X] Cr&eacute;er les types d’absence
* [ ] D&eacute;finir les quotas d’absence pour les &eacute;tudiants
* [ ] Mettre &agrave;jour la liste des absences d’un &eacute;tudiant: justifi&eacute;e, non justifi&eacute;e, quota ou hors quota (Cf.
règlement d’assiduit&eacute;).
* [ ] D&eacute;clencher les rattrapages pour les absences justifi&eacute;es lors d’un examen : notifier &agrave;l’&eacute;tudiant qu’il aura
une session de rattrapage.
* [ ] Attribuer la note ZERO pour une absence non justifi&eacute;e lors d’un examen
* [ ] Attribuer les p&eacute;nalit&eacute;s &agrave;chaque d&eacute;passement de seuil (quota) et d&eacute;clencher un courrier type pour
l’&eacute;tudiant (cf. le règlement d’assiduit&eacute;)

### **Enseignant**  
* [X] Authentification pr&eacute;alable  
* [ ] Consulter son planning et la liste de ses cours
* [ ] Faire l’appel : d&eacute;clarer une absence pour un &eacute;tudiant
* [ ] Consulter la synthèse des absences pour ses cours pour un &eacute;tudiant donn&eacute;  

### **Etudiant** 
* [X] Authentification pr&eacute;alable  
* [ ] Consulter son planning et la liste de ses cours
* [X] Consulter la liste de ses absences
* [X] D&eacute;poser un justificatif pour une ou plusieurs absences  
* [X] Anticiper une ou plusieurs absences   

## Lot 

Un &eacute;tudiant peut être physiquement absent, mais suivre le cours &agrave;distance via une plateforme d&eacute;di&eacute;e.
Dans ce cas, il n’est pas not&eacute; absent, mais pr&eacute;sent en « distanciel ».  
Cette d&eacute;marche n’est pas applicable dans le cas d’un examen.

L’**&eacute;tudiant** concern&eacute; doit pouvoir :
* [X] D&eacute;clarer son absence physique en d&eacute;posant un justificatif pour une p&eacute;riode donn&eacute;e (pas le
même d&eacute;pôt qu’une absence classique).
* [ ] V&eacute;rifier que le gestionnaire a valid&eacute; sa pr&eacute;sence en distanciel (si ce n’est pas le cas, il est not&eacute;
absent pour tous les cours concern&eacute;s (cas trait&eacute; dans l’application commune)).    
Les cours concern&eacute;s apparaissent toujours dans son planning avec un lien (fictif !) vers la r&eacute;union
dans la plateforme.
La liste de ses absences n’est pas impact&eacute;e.  

Le **gestionnaire** doit pouvoir :
* [ ] Valider ou invalider le justificatif de l’&eacute;tudiant (pas dans la même liste que les justificatifs
d’absences classiques).
* [ ] Dans le cas où il valide, cr&eacute;er pour chaque cours un lien (fictif !) vers une r&eacute;union dans la
plateforme.  
> **Note** :
On considère que l’enseignant est alert&eacute; de la situation en voyant apparaître le lien dans le cours
concern&eacute;.   
Il v&eacute;rifiera alors que l’&eacute;tudiant est bien connect&eacute; pendant toute la dur&eacute;e du cours.
