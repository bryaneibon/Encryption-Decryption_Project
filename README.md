Encryption & Decryption ::: Project 3:
Le but de ce projet est de mettre l'accent sur différents modes d'encryption.
1. L'étape 1 étant la plus basique.

2. L'étape 2 introduit la notion la notion de clé, afin de donner la possibilité à l'utilisateur de définir le niveau d'encryption de son message.

3. L'étape 3 devient beaucoup plus structuré en terme de code, car j'introduis la notion de décryption. Un message peut donc désormais être encrypté en entrée, et décrypté en sortie selon le choix de l'utilisateur (L'utilisateur interagit ici avec la classe Scanner.).

4. Cette étape devient beaucoup plus intéressante car nous n'utilisons plus la classe Scanner, mais l'invite de commande afin de procéder à une encryption ou une décryption.
Exemple : java Main -mode enc -data "Welcome to my GitHub profile" -key 5

5. L'étape 5 complète l'étape 4 en gardant le précédent comportement, mais cette fois-ci, on a la possibilité de compléter la commande avec un fichier en entrée et un fichier en sortie.
Exemple : java Main -mode enc -in road_to_treasure.txt -out protected.txt -key 5
