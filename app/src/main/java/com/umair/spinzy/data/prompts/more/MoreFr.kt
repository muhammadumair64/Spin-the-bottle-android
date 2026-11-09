package com.umair.spinzy.data.prompts.more

import com.umair.spinzy.data.Pack
import com.umair.spinzy.data.prompts.PromptSet
import com.umair.spinzy.data.prompts.bank

/** Fr extended bank (100+ per category together with PromptsFr). */
val MoreFr = PromptSet(
    truths = mapOf(
        Pack.CHILL to bank(FR_CHILL_T),
        Pack.PARTY to bank(FR_PARTY_T),
        Pack.SPICY to bank(FR_SPICY_T),
        Pack.COUPLES to bank(FR_COUPLES_T),
        Pack.FAMILY to bank(FR_FAMILY_T),
    ),
    dares = mapOf(
        Pack.CHILL to bank(FR_CHILL_D),
        Pack.PARTY to bank(FR_PARTY_D),
        Pack.SPICY to bank(FR_SPICY_D),
        Pack.COUPLES to bank(FR_COUPLES_D),
        Pack.FAMILY to bank(FR_FAMILY_D),
    ),
)

private const val FR_CHILL_T = """
Quel est ton plat réconfortant après une longue journée ?
Quelle chanson te met toujours de bonne humeur ?
Quel est le meilleur conseil qu'on t'ait donné ?
Si tu pouvais maîtriser une compétence du jour au lendemain, laquelle choisirais-tu ?
Comment aimes-tu passer un après-midi pluvieux ?
Quelle odeur te ramène en enfance ?
Quel est le plus bel endroit que tu aies vu ?
Es-tu plutôt du matin ou du soir ?
Quel film peux-tu regarder encore et encore ?
Quel serait ton métier de rêve si l'argent ne comptait pas ?
Quelle est ta saison préférée et pourquoi ?
À quel petit luxe ne renoncerais-tu jamais ?
Quelle est la chose la plus gentille qu'un inconnu ait faite pour toi ?
Avec quel personnage historique aimerais-tu dîner ?
Quelle est ta tradition de fêtes préférée ?
Café ou thé, et comment le prends-tu ?
Quel a été ton premier concert ?
Quel loisir commencerais-tu si tu avais plus de temps ?
Qu'est-ce que tu préfères dans ta ville ?
Quel est le meilleur repas que tu aies mangé ?
Quel emoji utilises-tu le plus ?
Quel est ton jeu de société préféré ?
Si ta vie avait une bande originale, quelle serait la chanson principale ?
Quelle est l'anecdote la plus étonnante que tu connaisses ?
Quel a été ton plus beau voyage ?
Qu'attends-tu avec impatience ce mois-ci ?
Quelle série recommanderais-tu à tout le monde ?
À quoi ressemblerait ton week-end parfait ?
Quel est le rêve le plus bizarre dont tu te souviens ?
Quelle langue aimerais-tu parler couramment ?
Quel est l'objet le plus ancien que tu possèdes ?
Quel est le plus beau compliment qu'on t'ait fait ?
Quel parfum de glace préfères-tu ?
Quel endroit est sur ta liste de voyages ?
Quel est ton meilleur souvenir de l'an dernier ?
Qu'est-ce qui te fait toujours rire ?
Quelle est ta chanson de karaoké ?
Quel aliment détestais-tu enfant et adores maintenant ?
Plage ou montagne ?
Quel personnage de fiction serait le meilleur colocataire ?
"""

private const val FR_CHILL_D = """
Fais ta meilleure imitation du présentateur météo.
Fredonne une chanson et laisse le groupe deviner.
Récite l'alphabet à l'envers le plus vite possible.
Cours dans la pièce au ralenti.
Parle comme un pirate jusqu'à ton prochain tour.
Cite dix fruits en dix secondes.
Imite ton personnage de dessin animé préféré.
Raconte ta journée en seulement cinq mots.
Invente un petit poème sur la personne à ta droite.
Joue le serveur et prends la commande de tout le monde.
Marche dans la pièce avec un livre sur la tête.
Fais une visite guidée de deux minutes de la pièce comme dans un musée.
Chuchote jusqu'à ton prochain tour.
Fais dix squats en comptant dans une autre langue.
Chante tout ce que tu dis jusqu'à ton prochain tour.
Lis ton dernier message reçu de façon dramatique.
Sois présentateur du journal et parle de cette pièce.
Invente une poignée de main secrète avec la personne à ta gauche.
Danse comme un robot pendant trente secondes.
Donne un surnom à chaque personne de la pièce.
Tiens une posture de yoga pendant vingt secondes.
Partage une anecdote que personne ne connaît.
Dessine un chat les yeux fermés.
Fais semblant de manger quelque chose de très épicé.
Parle comme un robot jusqu'à ton prochain tour.
Fais comme si tu venais de gagner un prix et fais un discours.
Chante joyeux anniversaire avec la voix la plus grave possible.
Invente un pas de danse et apprends-le au groupe.
Commente ce que fait la personne à ta gauche comme un journaliste sportif.
Imite quelqu'un qui arrive en retard au travail.
Dis trois choses positives sur toi à voix haute.
Fais semblant de préparer un sandwich étape par étape.
Parle en rimes jusqu'à ton prochain tour.
Imite un chien qui voit la neige pour la première fois.
Décris ton goûter idéal comme un plat étoilé.
Marche comme un pingouin.
Joue le détective qui cherche qui a mangé les biscuits.
Invente un nom de super-héros pour chaque joueur.
Raconte une histoire qui commence par « Il était une fois » en une minute.
Joue le coach sportif et dirige un exercice pour tous.
"""

private const val FR_PARTY_T = """
Quelle est la chose la plus bizarre que tu aies faite pour impressionner quelqu'un ?
Qui ici laisserais-tu organiser ton anniversaire ?
Quel est ton achat en ligne le plus ridicule ?
Quel a été ton pire désastre en cuisine ?
T'es-tu déjà trompé de toilettes ?
Quelle chanson gênante est dans ta playlist ?
As-tu déjà envoyé un message à la mauvaise personne ?
Quel est le défi le plus fou que tu aies relevé ?
Pour quelle bêtise as-tu déjà pleuré ?
Qui ici serait le pire colocataire ?
Quelle est la chose la plus gênante que tes parents t'ont surpris à faire ?
Quelle est la façon la plus bête dont tu t'es blessé ?
As-tu déjà fait semblant d'être malade pour éviter quelque chose ?
Quelle série regardes-tu en cachette ?
Quelle est la chose la plus étrange que tu aies mangée ?
Quelle a été ta pire coupe de cheveux ?
Qui du groupe gagnerait une téléréalité ?
Quelle est ta pire faute de correcteur automatique ?
Quel est le pire cadeau que tu aies reçu ?
As-tu déjà offert un cadeau qu'on t'avait offert ?
Quel a été ton rendez-vous le plus gênant ?
As-tu déjà salué quelqu'un qui saluait une autre personne ?
Quelle mode as-tu suivie qui te fait honte aujourd'hui ?
As-tu déjà ri aux larmes en public ?
Qui est le plus dramatique de la pièce ?
Quelle est ta manie la plus bizarre ?
T'a-t-on déjà surpris à parler tout seul ?
Qui ici se perdrait en premier en voyage ?
Quel a été ton pire job ?
Qui danse le mieux dans la pièce ?
Quelle est la pire excuse que tu aies donnée pour un retard ?
Que t'est-il arrivé de gênant en visio ?
Qui ici serait le pire espion ?
As-tu déjà trébuché devant ton crush ?
Quel est ton message le plus gênant ?
As-tu déjà fait semblant de rire à une blague que tu n'as pas comprise ?
Qui du groupe a le plus de chances de devenir millionnaire ?
As-tu déjà accusé quelqu'un d'autre d'une chose que tu as cassée ?
Dans quoi es-tu ridiculement compétitif ?
Qui ici pleurerait en premier devant un film ?
"""

private const val FR_PARTY_D = """
Imite quelqu'un de la pièce et laisse deviner qui c'est.
Danse comme si personne ne te regardait pendant trente secondes.
Fais un playback sur la chanson choisie par le groupe.
Parle avec un accent anglais jusqu'à ton prochain tour.
Joue l'animateur de jeu télé jusqu'à ton prochain tour.
Défile sur la chanson choisie par le groupe.
Fais semblant de demander en mariage la personne à ta gauche.
Chante une chanson dans un style complètement différent.
Laisse le groupe te faire une nouvelle coiffure.
Joue une star sur le tapis rouge.
Appelle un ami et parle uniquement avec des paroles de chansons.
Ris aux éclats trente secondes sans t'arrêter.
Danse la Macarena en entier.
Joue le DJ qui chauffe la foule.
Montre ta meilleure danse TikTok.
Fais une bagarre de film au ralenti.
Essaie de faire rire tout le monde en trente secondes.
Mets des chaussettes sur tes mains jusqu'à ton prochain tour.
Fais du beatbox pendant vingt secondes.
Imite un chanteur célèbre jusqu'à ce qu'on devine.
Fais une pub de téléachat pour un objet sur la table.
Romps avec une part de pizza de façon dramatique.
Fais comme si tu étais dans un clip pendant une minute.
Fais une battle de danse avec la personne à ta gauche.
Fais ta danse de la victoire.
Deviens une statue et laisse les autres te mettre en pose.
Joue le monologue d'un méchant célèbre.
Invente un rap sur la personne à ta droite.
Joue d'une guitare invisible comme une rock star.
Dis tout au ralenti jusqu'à ton prochain tour.
Fais la poule jusqu'à ce que quelqu'un rie.
Lis un message de ton téléphone avec une voix dramatique.
Imite un mème célèbre.
Joue le présentateur météo pendant une tempête.
Chante ta chanson préférée comme un opéra.
Fais la danse du ver ou une tentative très assurée.
Fais une entrée triomphale dans la pièce.
Fais une conférence TED sur un sujet totalement absurde.
Porte un toast au groupe avec une voix chic.
Laisse le groupe choisir un mot que tu dois dire chaque fois que tu parles.
"""

private const val FR_SPICY_T = """
Quel est le plus grand secret que tu caches à ton meilleur ami ?
Qui as-tu stalké en dernier sur les réseaux ?
Quand as-tu été le plus jaloux ?
As-tu déjà eu un faible pour un prof ?
Quelle est la pire chose que tu aies dite dans le dos de quelqu'un ?
Avec qui dans cette pièce irais-tu à un rendez-vous ?
Quelle est la chose la plus romantique que tu aies faite ?
Quel est ton plus gros red flag en amour ?
As-tu déjà ghosté quelqu'un ? Pourquoi ?
Quel a été ton bisou le plus gênant ?
As-tu déjà menti pour éviter un rendez-vous ?
Quel a été ton geste le plus audacieux envers un crush ?
Qui était ton crush célèbre à l'adolescence ?
As-tu déjà lu les messages de quelqu'un sans permission ?
Quel secret n'as-tu jamais dit à tes parents ?
As-tu déjà triché à un examen ou à un jeu ?
Qui trouves-tu le plus séduisant ici ?
T'a-t-on déjà quitté par message ?
Quel est ton plus grand regret amoureux ?
As-tu déjà craqué pour deux personnes en même temps ?
Quel est ton trait le plus toxique ?
Qu'as-tu fait que tu ne posterais jamais ?
Quelle a été ta pire rupture ?
Quelle est la chose la plus impulsive que tu aies faite ?
As-tu déjà eu un faible pour quelqu'un de cette pièce ?
Quelle est ta plus grande insécurité ?
As-tu déjà dit « je t'aime » sans le penser ?
Quel message regrettes-tu d'avoir envoyé ?
As-tu déjà été amoureux de ton meilleur ami ?
Quel est le dernier mensonge que tu as dit ?
Qu'est-ce qui te gênerait si ton ex l'apprenait ?
As-tu déjà eu une relation secrète ?
As-tu déjà créé un faux compte ?
Qu'est-ce que tu fais semblant d'aimer mais que tu détestes ?
T'a-t-on déjà surpris à fixer quelqu'un ?
C'est quoi ton type, honnêtement ?
Quelle est la phrase de drague la plus ringarde que tu aies utilisée ?
T'es-tu déjà retrouvé dans la friendzone ?
À qui as-tu écrit à 2 h du matin la dernière fois ?
Quelle question espères-tu qu'on ne te pose pas ce soir ?
"""

private const val FR_SPICY_D = """
Montre ton historique de recherche récent (un seul écran).
Lis à voix haute tes trois derniers messages envoyés.
Laisse la personne à ta droite envoyer un mème dans une de tes conversations.
Appelle un ami, dis-lui que tu as un grand secret et raccroche.
Laisse le groupe regarder tes playlists pendant une minute.
Poste en story une photo de toi enfant.
Envoie un vocal à ton meilleur ami en chantant son prénom.
Laisse le groupe écrire ta prochaine légende.
Montre ta dernière capture d'écran et explique-la.
Cite les trois dernières personnes à qui tu as écrit.
Envoie un emoji au hasard à ton crush et montre la réponse.
Laisse quelqu'un changer ta photo de profil pendant une heure.
Montre le selfie le plus gênant de ton téléphone.
Dis à la personne à ta gauche ta première impression sincère d'elle.
Fais un clin d'œil charmeur à tout le monde.
Laisse le groupe lire tes notes pendant trente secondes.
Montre ton temps d'écran d'aujourd'hui.
Fais une critique honnête de trente secondes de la personne à ta droite.
Écris à ta mère « j'ai un truc à te dire » et explique ensuite.
Montre la dernière chose que tu as likée.
Fais une sérénade à la personne en face de toi.
Dis qui tu appellerais en premier si tu gagnais au loto.
Montre le plus vieux message de ta boîte de réception.
Refais la pose de ton dernier selfie.
Dis avec qui ici tu échangerais ton téléphone pour une journée.
Révèle ta chanson la plus écoutée ce mois-ci.
Laisse le groupe lire ta bio et la critiquer.
Montre ta dernière commande en ligne.
Dis une chose gentille sur chaque personne, une phrase chacun.
Raconte une histoire gênante jamais racontée ici.
Montre ta galerie d'il y a exactement un an.
Écris une lettre d'amour à ton téléphone et lis-la.
Fais un discours sur pourquoi tu es le meilleur parti de la pièce.
Raconte ton pire rendez-vous en une minute.
Envoie un cœur à la troisième personne de tes conversations récentes.
Avoue un mensonge que tu as dit ce soir.
Révèle l'appli que tu utilises le plus.
Fais un slow avec un partenaire imaginaire pendant que tout le monde fredonne.
Révèle un crush dont tu n'as jamais parlé.
Moque-toi de toi-même pendant trente secondes.
"""

private const val FR_COUPLES_T = """
À quel moment précis as-tu su que je te plaisais ?
De quoi te souviens-tu de notre première conversation ?
Quelle est ta photo préférée de nous deux ?
Qu'est-ce que je fais qui te fait toujours sourire ?
Quel a été notre meilleur rendez-vous ?
Quelle chanson te fait penser à moi ?
Qu'as-tu toujours voulu me dire ?
Quelle est la chose la plus romantique que j'aie faite pour toi ?
Qu'aimerais-tu que l'on fasse plus souvent ?
Où aimerais-tu voyager avec moi ?
Quelle est ma manie la plus mignonne ?
Quel petit geste de ma part compte beaucoup pour toi ?
Quelle a été notre dispute la plus drôle ?
Quel rêve as-tu pour notre avenir ?
Qu'aimes-tu faire avec moi lors d'une journée paresseuse ?
Quel film te fait penser à nous ?
Quel est le cadeau le plus spécial que je t'aie fait ?
Comment décrirais-tu notre couple en trois mots ?
Quelle habitude as-tu prise de moi ?
Qu'as-tu pensé la première fois qu'on s'est tenu la main ?
Quel est ton surnom préféré pour moi ?
Quelle nouveauté aimerais-tu essayer ensemble ?
Comment pourrais-je te faire sentir plus aimé ?
Qu'est-ce que tu admires le plus chez moi ?
Quand as-tu été fier de moi ?
À quoi ressemblerait notre escapade parfaite ?
Quelle tradition aimerais-tu qu'on commence ?
Qu'est-ce qui te fait manquer de moi ?
Quelle est la première chose que tu as remarquée chez moi ?
Comment aimes-tu être réconforté quand tu es triste ?
Quelle est la plus grosse bêtise qu'on ait faite ensemble ?
Quel objectif aimerais-tu qu'on atteigne cette année ?
Qu'est-ce qui t'a agréablement surpris chez moi ?
Quelle est notre blague privée préférée ?
Qu'est-ce qui t'a fait tomber amoureux de moi ?
Comment penses-tu que nous avons grandi depuis notre rencontre ?
Quand t'es-tu senti totalement en confiance avec moi ?
Quel est le plus beau message que je t'aie envoyé ?
Quel nom donnerais-tu à notre futur animal ?
Comment nous imagines-tu quand nous serons vieux ?
"""

private const val FR_COUPLES_D = """
Serre ton partenaire dans tes bras trente secondes sans lâcher.
Dis trois choses que tu aimes chez ton partenaire en lui tenant la main.
Refaites la pose de votre photo préférée.
Donne à ton partenaire une bouchée de quelque chose de sucré.
Écris un petit mot d'amour et lis-le à voix haute.
Dansez ensemble sur la première chanson qui passe.
Fais un bisou sur le front de ton partenaire.
Imite le rire de ton partenaire.
Regardez-vous dans les yeux et dites une chose dont vous êtes reconnaissants.
Chante quelques lignes de « votre chanson ».
Raconte votre premier rendez-vous en une minute de ton point de vue.
Fais un massage des mains d'une minute à ton partenaire.
Chuchote à l'oreille de ton partenaire sa plus belle qualité.
Dessine ton partenaire en trente secondes.
Planifiez votre prochain rendez-vous à voix haute en soixante secondes.
Donne un nouveau surnom à ton partenaire et utilise-le toute la soirée.
Faites un slow sans musique pendant trente secondes.
Prenez un selfie mignon et mettez-le en fond d'écran une journée.
Envoie un vocal à ton partenaire pour dire pourquoi tu l'aimes.
Fais un baisemain à ton partenaire comme un prince.
Fais rire ton partenaire sans le toucher.
Raconte à ton partenaire un rêve que tu ne lui as jamais confié.
Concours de regards ; le perdant fait un compliment.
Faites des retrouvailles de film en courant dans la pièce.
Chante une chanson d'amour avec le prénom de ton partenaire.
Promets à ton partenaire un petit service pour demain.
Rejouez la scène d'un couple de film célèbre.
Cite cinq choses que tu aimes chez ton partenaire en dix secondes.
Fais comme si tu rencontrais ton partenaire pour la première fois.
Fais une bise sur la joue de ton partenaire.
Faites une posture de yoga à deux.
Dis « je t'aime » dans trois langues.
Formez ensemble un cœur avec vos mains.
Inventez un salut secret de couple.
Faites-vous un câlin en vous balançant le temps d'une chanson.
Prépare la boisson préférée de ton partenaire.
Partagez un dessert avec une seule cuillère.
Dessinez ensemble la maison de vos rêves.
Fabrique une bague en papier et offre-la à ton partenaire.
Fais un long câlin bien chaleureux à ton partenaire.
"""

private const val FR_FAMILY_T = """
Quel est ton souvenir de vacances en famille préféré ?
Qui cuisine le mieux dans la famille ?
Quelle est la chose la plus drôle qu'un membre de la famille ait dite ?
Si notre famille était une série, comment s'appellerait-elle ?
Quelle est ta tradition familiale préférée ?
Qui dans la famille a le plus de chances de devenir célèbre ?
Quelle est la meilleure recette de famille ?
Qui fait les meilleurs câlins ?
Quelle a été la dispute familiale la plus bête ?
Si tu pouvais emmener toute la famille n'importe où, où irais-tu ?
Qui ronfle le plus fort dans la famille ?
Quelle est ta photo de famille préférée ?
Quel a été ton meilleur anniversaire ?
Qui gagne toujours aux jeux de société ?
Quel est le film préféré à regarder en famille ?
Qui raconte les meilleures histoires ?
Qu'est-ce que tu préfères dans notre maison ?
Qui dans la famille gagnerait un concours de danse ?
Qu'aimerais-tu essayer en famille ?
Qui est le plus courageux de la famille ?
Si notre famille avait une mascotte, quel animal serait-ce ?
Qui survivrait le plus longtemps sur une île déserte ?
Qu'as-tu appris de tes parents ?
Quel est le surnom le plus drôle de la famille ?
Qui dans la famille ferait le meilleur prof ?
Quelle règle de la maison trouves-tu drôle ?
Qui dans la famille ferait le meilleur astronaute ?
Quelle était ton histoire du soir préférée ?
Que mettrais-tu dans une capsule temporelle familiale ?
Qui serait le meilleur détective de la famille ?
Qu'a fait un membre de la famille pour toi récemment ?
Si notre famille formait un groupe de musique, quel serait son nom ?
Quelle est la chose la plus drôle qu'un animal de la famille ait faite ?
Quel métier irait parfaitement à chaque membre de la famille ?
Quel est ton souvenir préféré d'un trajet en voiture en famille ?
Qui se lève le plus tôt dans la famille ?
Quelle serait la devise de notre famille ?
Qu'aimerais-tu apprendre au plus jeune ici ?
Qu'aimerais-tu apprendre du plus âgé ici ?
Qu'est-ce que tu aimes le plus dans le fait d'être ensemble ce soir ?
"""

private const val FR_FAMILY_D = """
Imite un membre de la famille et laisse les autres deviner.
Fais le singe pendant trente secondes.
Raconte une blague « toc toc ».
Fais une démarche rigolote dans la pièce.
Interviewe le plus jeune joueur comme un journaliste.
Fais la grimace la plus drôle possible pendant dix secondes.
Saute comme un kangourou dans la pièce.
Imite un grand-parent qui raconte une histoire.
Dessine toute la famille en trente secondes.
Prends ta meilleure pose de super-héros.
Marche comme un pingouin jusqu'à la porte et reviens.
Récite l'alphabet en sautant à cloche-pied.
Imite un éléphant.
Tape dans la main de tout le monde dans la pièce.
Joue le prof et donne un cours d'une minute.
Fais une danse de joie pendant quinze secondes.
Imite un animal et laisse les autres deviner.
Fais semblant d'être un aspirateur robot.
Fais dix sauts en étoile.
Invente une histoire sur un chat volant en une minute.
Fais un compliment à la personne à ta droite.
Rugis comme un lion.
Joue un pirate à la recherche d'un trésor.
Fais semblant d'être un arbre dans le vent.
Marche sur une corde raide imaginaire.
Fais semblant de manger un sandwich géant invisible.
Imite un dinosaure.
Fabrique un bateau ou un avion en papier.
Fais semblant d'être un bonhomme de neige qui fond.
Fais un tour de magie inventé.
Danse comme un robot.
Fais la danse des canards.
Fais semblant d'être un astronaute qui flotte dans l'espace.
Chante l'alphabet le plus vite possible.
Marche comme un crabe dans la pièce.
Fais le perroquet qui répète tout ce que dit le joueur suivant.
Imite un petit chien heureux qui remue la queue.
Fabrique une couronne en papier et offre-la à quelqu'un.
Fais semblant d'être une chenille qui devient papillon.
Invente une danse appelée « le spaghetti ».
"""
