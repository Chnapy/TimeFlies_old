Projet_TimeFlies
================

Projet d'un Tactical-RPG tour/tour.

# Informations techniques

TimeFlies (nom provisoire) est développé en Java 1.8. Il utilise la version 1.5.5 de LibGDX (avec ses extensions Box2D et Ai). LibGDX exige l'utilisation de [Gradle](http://fr.wikipedia.org/wiki/Gradle) qui sépare le projet sous forme de plusieurs sous-projets. Dans le cas de Time Flies, le projet sera séparé en deux partie : 'Core' et 'Desktop', le coeur du programme, et la vue coté Desktop. Pour tester le projet, vous devez lancer le 'Desktop'.

# Récupération de l'ensemble du projet

Le meilleur moyen de récupérer l'ensemble du projet est d'utiliser un plugin Git de votre IDE.

Sous Netbeans: dans la barre de menu, cliquez sur Team > Remote > Clone... puis entrez l'url suivante: https://github.com/Chnapy/TimeFlies.git avec vos logs (inscrivez-vous si nécessaire). Sélectionnez un dossier de destination puis validez. Le projet est alors créé.

Sous Eclipse: TODO.

# Mise à jour du projet avec Git

Sous Netbeans :
Dans l'arbre des projets, faîtes un clic droit sur le projet principal, puis dans Git :
 * Commit : Permet d'indiquer les changements effectués. A faire avant un Push.
 * Remote > Push : Permet d'envoyer les fichiers modifiés au projet Git. A faire après un Commit. En cas d'échec du Push, il faut faire un Pull puis réessayer.
 * Remote > Pull : Permet de récupérer les fichiers du projet Git non-possédé ou ayant été modifiés par un collaborateur. Peut être requis avant un Push.
 * Remote > Fetch : Permet de comparer les fichiers locaux aux fichiers du projet Git, et d'indiquer les différences entre eux, lignes par lignes

Sous Eclipse :
TODO

# Détail du jeu, informations de conception

L'ensemble des documents d'aide à la conception (GDD, diagrammes, ...) se trouvent dans le dossier [doc/](https://github.com/Chnapy/TimeFlies/tree/master/doc)


