package com.umair.spinzy.data.prompts.more

import com.umair.spinzy.data.Pack
import com.umair.spinzy.data.prompts.PromptSet
import com.umair.spinzy.data.prompts.bank

/** De extended bank (100+ per category together with PromptsDe). */
val MoreDe = PromptSet(
    truths = mapOf(
        Pack.CHILL to bank(DE_CHILL_T),
        Pack.PARTY to bank(DE_PARTY_T),
        Pack.SPICY to bank(DE_SPICY_T),
        Pack.COUPLES to bank(DE_COUPLES_T),
        Pack.FAMILY to bank(DE_FAMILY_T),
    ),
    dares = mapOf(
        Pack.CHILL to bank(DE_CHILL_D),
        Pack.PARTY to bank(DE_PARTY_D),
        Pack.SPICY to bank(DE_SPICY_D),
        Pack.COUPLES to bank(DE_COUPLES_D),
        Pack.FAMILY to bank(DE_FAMILY_D),
    ),
)

private const val DE_CHILL_T = """
Was ist dein Lieblingsessen nach einem langen Tag?
Welches Lied hebt immer deine Laune?
Was ist der beste Rat, den du je bekommen hast?
Welche Fähigkeit würdest du gern über Nacht beherrschen?
Wie verbringst du am liebsten einen verregneten Nachmittag?
Welcher Geruch erinnert dich an deine Kindheit?
Was ist der schönste Ort, den du je gesehen hast?
Bist du eher Frühaufsteher oder Nachteule?
Welchen Film kannst du immer wieder schauen?
Was wäre dein Traumjob, wenn Geld keine Rolle spielte?
Welche Jahreszeit magst du am liebsten und warum?
Auf welchen kleinen Luxus würdest du nie verzichten?
Was ist das Netteste, das ein Fremder je für dich getan hat?
Mit welcher historischen Person würdest du gern essen gehen?
Was ist deine liebste Feiertagstradition?
Kaffee oder Tee, und wie trinkst du ihn?
Was war dein erstes Konzert?
Welches Hobby würdest du anfangen, wenn du mehr Zeit hättest?
Was magst du an deinem Wohnort am meisten?
Was war das beste Essen deines Lebens?
Welches Emoji benutzt du am häufigsten?
Was ist dein Lieblingsbrettspiel?
Wenn dein Leben einen Soundtrack hätte, welches Lied wäre das Titellied?
Was ist die coolste Tatsache, die du kennst?
Was war deine schönste Reise?
Worauf freust du dich diesen Monat?
Welche Serie würdest du allen empfehlen?
Wie sähe dein perfektes Wochenende aus?
Was ist der seltsamste Traum, an den du dich erinnerst?
Welche Sprache würdest du gern fließend sprechen?
Was ist das älteste Ding, das du besitzt?
Was ist das schönste Kompliment, das du je bekommen hast?
Welche Eissorte magst du am liebsten?
Welcher Ort steht auf deiner Reiseliste?
Was ist deine schönste Erinnerung an letztes Jahr?
Was bringt dich immer zum Lachen?
Was ist dein Karaoke-Lied?
Welches Essen hast du als Kind gehasst und liebst es jetzt?
Strand oder Berge?
Welche fiktive Figur wäre der beste Mitbewohner?
"""

private const val DE_CHILL_D = """
Mach deine beste Imitation eines Wettermoderators.
Summe ein Lied und lass die Gruppe raten.
Sag das Alphabet so schnell wie möglich rückwärts.
Lauf in Zeitlupe durch den Raum.
Sprich bis zu deinem nächsten Zug wie ein Pirat.
Nenne zehn Obstsorten in zehn Sekunden.
Imitiere deine liebste Zeichentrickfigur.
Beschreibe deinen Tag mit nur fünf Wörtern.
Denk dir ein kurzes Gedicht über die Person rechts von dir aus.
Spiel den Kellner und nimm die Bestellung aller auf.
Lauf mit einem Buch auf dem Kopf durch den Raum.
Mach eine zweiminütige Museumsführung durch den Raum.
Flüstere bis zu deinem nächsten Zug.
Mach zehn Kniebeugen und zähle in einer anderen Sprache.
Sing alles, was du sagst, bis zu deinem nächsten Zug.
Lies deine letzte Nachricht dramatisch vor.
Sei Nachrichtensprecher und berichte über diesen Raum.
Erfinde einen geheimen Handschlag mit der Person links von dir.
Tanz dreißig Sekunden lang wie ein Roboter.
Gib jeder Person im Raum einen Spitznamen.
Halte zwanzig Sekunden eine Yoga-Pose.
Erzähl eine Tatsache, die niemand kennt.
Zeichne eine Katze mit geschlossenen Augen.
Tu so, als würdest du etwas sehr Scharfes essen.
Sprich bis zu deinem nächsten Zug wie ein Roboter.
Tu so, als hättest du einen Preis gewonnen, und halte eine Rede.
Sing Happy Birthday mit der tiefsten Stimme, die du hast.
Erfinde einen Tanzschritt und bring ihn der Gruppe bei.
Kommentiere wie ein Sportreporter, was die Person links von dir macht.
Spiel jemanden, der zu spät zur Arbeit kommt.
Sag laut drei gute Dinge über dich.
Tu so, als würdest du Schritt für Schritt ein Sandwich machen.
Sprich bis zu deinem nächsten Zug in Reimen.
Spiel einen Hund, der zum ersten Mal Schnee sieht.
Beschreibe deinen Lieblingssnack wie ein Sternekoch.
Watschel wie ein Pinguin.
Spiel einen Detektiv, der herausfindet, wer die Kekse gegessen hat.
Erfinde für jeden Spieler einen Superheldennamen.
Erzähl in einer Minute eine Geschichte, die mit „Es war einmal“ beginnt.
Spiel den Fitnesstrainer und leite eine Übung für alle an.
"""

private const val DE_PARTY_T = """
Was ist das Seltsamste, das du getan hast, um jemanden zu beeindrucken?
Wen hier würdest du deinen Geburtstag planen lassen?
Was ist dein lächerlichster Online-Kauf?
Was war dein größtes Kochdesaster?
Bist du schon mal in die falsche Toilette gegangen?
Welches peinliche Lied ist in deiner Playlist?
Hast du schon mal eine Nachricht an die falsche Person geschickt?
Was ist die verrückteste Mutprobe, die du gemacht hast?
Wegen welcher Kleinigkeit hast du schon geweint?
Wer hier wäre der schlimmste Mitbewohner?
Wobei haben dich deine Eltern am peinlichsten erwischt?
Wie hast du dich auf die dümmste Art verletzt?
Hast du schon mal so getan, als wärst du krank, um etwas zu schwänzen?
Welche Serie schaust du heimlich?
Was ist das Seltsamste, das du je gegessen hast?
Was war dein schlimmster Haarschnitt?
Wer aus der Gruppe würde eine Reality-Show gewinnen?
Was ist dein peinlichster Autokorrektur-Fehler?
Was ist das schlimmste Geschenk, das du je bekommen hast?
Hast du schon mal ein Geschenk weiterverschenkt?
Was war dein unangenehmstes Date?
Hast du schon mal jemandem zurückgewinkt, der gar nicht dir gewinkt hat?
Welchem Trend bist du gefolgt, für den du dich heute schämst?
Hast du schon mal in der Öffentlichkeit vor Lachen geweint?
Wer im Raum ist am dramatischsten?
Was ist deine seltsamste Angewohnheit?
Wurdest du schon mal beim Selbstgespräch erwischt?
Wer hier würde sich auf einer Reise zuerst verlaufen?
Was war dein schlimmster Job?
Wer im Raum tanzt am besten?
Was ist die schlechteste Ausrede, die du fürs Zuspätkommen hattest?
Was ist dir in einem Videocall Peinliches passiert?
Wer hier wäre der schlechteste Spion?
Bist du schon mal vor deinem Schwarm gestolpert?
Was ist deine peinlichste Nachricht?
Hast du schon mal über einen Witz gelacht, den du nicht verstanden hast?
Wer aus der Gruppe wird am ehesten Millionär?
Hast du schon mal jemand anderem die Schuld für etwas gegeben, das du kaputt gemacht hast?
Wobei bist du lächerlich ehrgeizig?
Wer hier würde bei einem Film zuerst weinen?
"""

private const val DE_PARTY_D = """
Imitiere jemanden im Raum und lass raten, wer es ist.
Tanz dreißig Sekunden, als würde niemand zuschauen.
Mach Playback zu einem Lied, das die Gruppe aussucht.
Sprich bis zu deinem nächsten Zug mit britischem Akzent.
Spiel bis zu deinem nächsten Zug einen Quizshow-Moderator.
Lauf wie auf dem Laufsteg zu einem Lied, das die Gruppe wählt.
Mach der Person links von dir einen gespielten Heiratsantrag.
Sing ein Lied in einem völlig anderen Stil.
Lass dir von der Gruppe eine neue Frisur machen.
Spiel einen Star auf dem roten Teppich.
Ruf einen Freund an und sprich nur in Liedzeilen.
Lach dreißig Sekunden lang laut ohne Pause.
Tanz die komplette Macarena.
Spiel einen DJ, der die Menge anheizt.
Zeig deinen besten TikTok-Tanz.
Spiel einen Filmkampf in Zeitlupe.
Versuche, alle in dreißig Sekunden zum Lachen zu bringen.
Trag Socken an den Händen bis zu deinem nächsten Zug.
Mach zwanzig Sekunden Beatbox.
Imitiere einen berühmten Sänger, bis jemand rät.
Mach eine Teleshopping-Werbung für etwas auf dem Tisch.
Mach dramatisch Schluss mit einem Stück Pizza.
Tu eine Minute lang so, als wärst du in einem Musikvideo.
Mach ein Tanzduell mit der Person links von dir.
Zeig deinen Siegestanz.
Werde zur Statue und lass dich von den anderen in Pose bringen.
Spiel den Monolog eines berühmten Bösewichts.
Erfinde einen Rap über die Person rechts von dir.
Spiel Luftgitarre wie ein Rockstar.
Sag bis zu deinem nächsten Zug alles in Zeitlupe.
Spiel ein Huhn, bis jemand lacht.
Lies eine Nachricht von deinem Handy mit dramatischer Stimme vor.
Stell ein berühmtes Meme nach.
Spiel einen Wettermoderator mitten im Sturm.
Sing dein Lieblingslied wie eine Oper.
Mach den Wurm oder einen sehr selbstbewussten Versuch.
Mach einen triumphalen Auftritt im Raum.
Halte einen TED-Talk über etwas völlig Absurdes.
Bring mit vornehmer Stimme einen Toast auf die Gruppe aus.
Lass die Gruppe ein Wort wählen, das du bei jedem Satz sagen musst.
"""

private const val DE_SPICY_T = """
Welches ist das größte Geheimnis, das du vor deinem besten Freund hast?
Wen hast du zuletzt in sozialen Medien gestalkt?
Wann warst du am eifersüchtigsten?
Warst du schon mal in eine Lehrkraft verknallt?
Was ist das Schlimmste, das du hinter jemandes Rücken gesagt hast?
Mit wem in diesem Raum würdest du auf ein Date gehen?
Was ist das Romantischste, das du je getan hast?
Was ist deine größte Red Flag in der Liebe?
Hast du schon mal jemanden geghostet? Warum?
Was war dein peinlichster Kuss?
Hast du schon mal gelogen, um ein Date abzusagen?
Was war dein mutigster Schritt bei einem Schwarm?
Wer war dein Promi-Schwarm als Teenager?
Hast du schon mal ohne Erlaubnis die Nachrichten von jemandem gelesen?
Welches Geheimnis hast du deinen Eltern nie erzählt?
Hast du schon mal bei einer Prüfung oder einem Spiel geschummelt?
Wen findest du hier am attraktivsten?
Wurde schon mal per Nachricht mit dir Schluss gemacht?
Was bereust du in der Liebe am meisten?
Warst du schon mal in zwei Leute gleichzeitig verknallt?
Was ist deine toxischste Eigenschaft?
Was hast du getan, das du nie posten würdest?
Was war deine schlimmste Trennung?
Was ist das Impulsivste, das du je getan hast?
Warst du schon mal in jemanden hier verknallt?
Was ist deine größte Unsicherheit?
Hast du schon mal „Ich liebe dich“ gesagt, ohne es zu meinen?
Welche Nachricht bereust du, geschickt zu haben?
Warst du schon mal in deinen besten Freund verliebt?
Was war die letzte Lüge, die du erzählt hast?
Was wäre dir peinlich, wenn dein Ex es erfahren würde?
Hattest du schon mal eine heimliche Beziehung?
Hast du schon mal einen Fake-Account erstellt?
Was tust du so, als würdest du es mögen, obwohl du es hasst?
Wurdest du schon mal beim Anstarren erwischt?
Was ist dein Typ, ganz ehrlich?
Was war dein kitschigster Anmachspruch?
Bist du schon mal in der Friendzone gelandet?
Wem hast du zuletzt um 2 Uhr nachts geschrieben?
Welche Frage hoffst du, heute Abend nicht gestellt zu bekommen?
"""

private const val DE_SPICY_D = """
Zeig deinen aktuellen Suchverlauf (nur ein Bildschirm).
Lies deine letzten drei gesendeten Nachrichten laut vor.
Lass die Person rechts von dir ein Meme in einen deiner Chats schicken.
Ruf einen Freund an, sag, dass du ein großes Geheimnis hast, und leg auf.
Lass die Gruppe eine Minute lang deine Playlists ansehen.
Poste ein Kinderfoto von dir in deiner Story.
Schick deinem besten Freund eine Sprachnachricht, in der du seinen Namen singst.
Lass die Gruppe deine nächste Bildunterschrift schreiben.
Zeig deinen neuesten Screenshot und erkläre ihn.
Nenne die letzten drei Personen, denen du geschrieben hast.
Schick deinem Schwarm ein zufälliges Emoji und zeig die Antwort.
Lass jemanden für eine Stunde dein Profilbild ändern.
Zeig das peinlichste Selfie auf deinem Handy.
Sag der Person links von dir deinen ehrlichen ersten Eindruck von ihr.
Zwinker jedem im Raum charmant zu.
Lass die Gruppe dreißig Sekunden deine Notizen lesen.
Zeig deine heutige Bildschirmzeit.
Gib der Person rechts von dir dreißig Sekunden ehrliches Feedback.
Schreib deiner Mutter „Ich muss dir was sagen“ und erkläre es später.
Zeig das Letzte, das du in sozialen Medien geliket hast.
Bring der Person gegenüber ein Ständchen.
Sag, wen du zuerst anrufen würdest, wenn du im Lotto gewinnst.
Zeig die älteste Nachricht in deinem Posteingang.
Stell die Pose deines letzten Selfies nach.
Sag, mit wem hier du für einen Tag das Handy tauschen würdest.
Verrate dein meistgehörtes Lied diesen Monat.
Lass die Gruppe deine Bio lesen und bewerten.
Zeig deine letzte Online-Bestellung.
Sag über jede Person hier einen netten Satz.
Erzähl eine peinliche Geschichte, die du hier noch nie erzählt hast.
Zeig deine Galerie von genau vor einem Jahr.
Schreib einen Liebesbrief an dein Handy und lies ihn vor.
Halte eine Rede, warum du die beste Partie im Raum bist.
Erzähl in einer Minute dein schlimmstes Date.
Schick ein Herz an die dritte Person in deinen letzten Chats.
Gib eine Lüge von heute Abend zu.
Verrate, welche App du am meisten nutzt.
Tanz einen langsamen Tanz mit einem unsichtbaren Partner, während alle summen.
Verrate einen Schwarm, über den du nie gesprochen hast.
Mach dich dreißig Sekunden lang über dich selbst lustig.
"""

private const val DE_COUPLES_T = """
In welchem Moment wusstest du, dass du mich magst?
Woran erinnerst du dich bei unserem ersten Gespräch?
Was ist dein Lieblingsfoto von uns beiden?
Was mache ich, das dich immer zum Lächeln bringt?
Was war unser bestes Date?
Welches Lied erinnert dich an mich?
Was wolltest du mir schon immer sagen?
Was ist das Romantischste, das ich je für dich getan habe?
Was sollten wir öfter zusammen machen?
Wohin würdest du gern mit mir reisen?
Was ist meine süßeste Macke?
Welche kleine Geste von mir bedeutet dir viel?
Was war unser lustigster Streit?
Welchen Traum hast du für unsere Zukunft?
Was machst du an faulen Tagen am liebsten mit mir?
Welcher Film erinnert dich an uns?
Was ist das besonderste Geschenk, das ich dir gemacht habe?
Wie würdest du unsere Beziehung in drei Worten beschreiben?
Welche Angewohnheit hast du von mir übernommen?
Was hast du gedacht, als wir uns das erste Mal an der Hand hielten?
Was ist dein liebster Kosename für mich?
Was Neues würdest du gern mit mir ausprobieren?
Wie könnte ich dir mehr Liebe zeigen?
Was bewunderst du am meisten an mir?
Wann warst du stolz auf mich?
Wie sähe unser perfekter Kurzurlaub aus?
Welche Tradition sollten wir beginnen?
Wann vermisst du mich am meisten?
Was ist dir als Erstes an mir aufgefallen?
Wie möchtest du getröstet werden, wenn du traurig bist?
Was ist das Albernste, das wir zusammen gemacht haben?
Welches Ziel sollten wir dieses Jahr gemeinsam erreichen?
Was hat dich an mir positiv überrascht?
Was ist unser liebster Insider-Witz?
Warum hast du dich in mich verliebt?
Wie sind wir seit unserem Kennenlernen gewachsen?
Wann hast du dich bei mir völlig sicher gefühlt?
Was ist die schönste Nachricht, die ich dir je geschickt habe?
Wie würdest du unser zukünftiges Haustier nennen?
Wie stellst du dir uns im Alter vor?
"""

private const val DE_COUPLES_D = """
Umarme deinen Partner dreißig Sekunden lang, ohne loszulassen.
Halte die Hand deines Partners und nenne drei Dinge, die du an ihm liebst.
Stellt die Pose eures Lieblingsfotos nach.
Füttere deinen Partner mit etwas Süßem.
Schreib einen kurzen Liebeszettel und lies ihn laut vor.
Tanzt zusammen zum ersten Lied, das läuft.
Gib deinem Partner einen Kuss auf die Stirn.
Imitiere das Lachen deines Partners.
Schaut euch in die Augen und sagt, wofür ihr dankbar seid.
Sing ein paar Zeilen aus „eurem Lied“.
Erzähl in einer Minute euer erstes Date aus deiner Sicht.
Gib deinem Partner eine einminütige Handmassage.
Flüstere deinem Partner seine beste Eigenschaft ins Ohr.
Zeichne deinen Partner in dreißig Sekunden.
Plant in sechzig Sekunden laut euer nächstes Date.
Gib deinem Partner einen neuen Kosenamen und benutze ihn den ganzen Abend.
Tanzt dreißig Sekunden langsam ohne Musik.
Macht ein süßes Selfie und nutzt es einen Tag als Hintergrundbild.
Schick deinem Partner eine Sprachnachricht, warum du ihn liebst.
Küss deinem Partner die Hand wie ein Adliger.
Bring deinen Partner zum Lachen, ohne ihn zu berühren.
Erzähl deinem Partner einen Traum, den du nie geteilt hast.
Macht ein Anstarr-Duell; wer verliert, macht ein Kompliment.
Spielt ein filmreifes Wiedersehen und rennt durch den Raum.
Sing ein Liebeslied mit dem Namen deines Partners.
Versprich deinem Partner einen kleinen Gefallen für morgen.
Spielt die Szene eines berühmten Filmpaares nach.
Nenne in zehn Sekunden fünf Dinge, die du an deinem Partner liebst.
Tu so, als würdest du deinen Partner zum ersten Mal treffen.
Gib deinem Partner einen Kuss auf die Wange.
Macht zusammen eine Partner-Yoga-Pose.
Sag „Ich liebe dich“ in drei Sprachen.
Formt zusammen ein Herz mit euren Händen.
Erfindet einen geheimen Paar-Gruß.
Umarmt euch und schaukelt während eines ganzen Liedes.
Mach deinem Partner sein Lieblingsgetränk.
Teilt euch ein Dessert mit nur einem Löffel.
Zeichnet zusammen euer Traumhaus.
Bastle einen Papierring und schenk ihn deinem Partner.
Gib deinem Partner eine lange, warme Umarmung.
"""

private const val DE_FAMILY_T = """
Was ist deine liebste Erinnerung an einen Familienurlaub?
Wer kocht in der Familie am besten?
Was ist das Lustigste, das jemand aus der Familie je gesagt hat?
Wenn unsere Familie eine Serie wäre, wie hieße sie?
Was ist deine liebste Familientradition?
Wer aus der Familie wird am ehesten berühmt?
Was ist das beste Familienrezept?
Wer gibt die besten Umarmungen?
Was war der albernste Familienstreit?
Wohin würdest du mit der ganzen Familie reisen, wenn alles ginge?
Wer schnarcht in der Familie am lautesten?
Was ist dein Lieblingsfamilienfoto?
Was war dein schönster Geburtstag?
Wer gewinnt immer bei Brettspielen?
Was ist unser liebster Familienfilm?
Wer erzählt die besten Geschichten?
Was magst du an unserem Zuhause am meisten?
Wer aus der Familie würde einen Tanzwettbewerb gewinnen?
Was würdest du gern als Familie ausprobieren?
Wer ist der Mutigste in der Familie?
Wenn unsere Familie ein Maskottchen hätte, welches Tier wäre es?
Wer würde auf einer einsamen Insel am längsten überleben?
Was hast du von deinen Eltern gelernt?
Was ist der lustigste Spitzname in der Familie?
Wer aus der Familie wäre der beste Lehrer?
Welche Familienregel findest du lustig?
Wer aus der Familie wäre der beste Astronaut?
Was war deine liebste Gutenachtgeschichte?
Was würdest du in eine Familien-Zeitkapsel legen?
Wer wäre der beste Detektiv in der Familie?
Was hat jemand aus der Familie kürzlich für dich getan?
Wenn unsere Familie eine Band wäre, wie würde sie heißen?
Was ist das Lustigste, das ein Haustier der Familie je gemacht hat?
Welcher Beruf würde zu jedem in der Familie passen?
Was ist deine liebste Erinnerung an eine Familienautofahrt?
Wer steht in der Familie am frühesten auf?
Was wäre das Motto unserer Familie?
Was würdest du dem Jüngsten hier gern beibringen?
Was würdest du gern vom Ältesten hier lernen?
Was gefällt dir heute Abend am Zusammensein am meisten?
"""

private const val DE_FAMILY_D = """
Imitiere jemanden aus der Familie und lass die anderen raten.
Spiel dreißig Sekunden lang einen Affen.
Erzähl einen Klopf-Klopf-Witz.
Mach einen lustigen Gang durch den Raum.
Interviewe den jüngsten Spieler wie ein Reporter.
Zieh zehn Sekunden lang die lustigste Grimasse.
Hüpf wie ein Känguru durch den Raum.
Spiel einen Großelternteil, der eine Geschichte erzählt.
Zeichne die ganze Familie in dreißig Sekunden.
Zeig deine beste Superhelden-Pose.
Watschel wie ein Pinguin zur Tür und zurück.
Sag das Alphabet auf, während du auf einem Bein hüpfst.
Imitiere einen Elefanten.
Gib allen im Raum ein High Five.
Spiel den Lehrer und halte eine einminütige Stunde.
Mach fünfzehn Sekunden lang einen Freudentanz.
Imitiere ein Tier und lass die anderen raten.
Tu so, als wärst du ein Saugroboter.
Mach zehn Hampelmänner.
Erfinde in einer Minute eine Geschichte über eine fliegende Katze.
Mach der Person rechts von dir ein Kompliment.
Brüll wie ein Löwe.
Spiel einen Piraten auf Schatzsuche.
Tu so, als wärst du ein Baum im Wind.
Balanciere über ein unsichtbares Seil.
Tu so, als würdest du ein riesiges unsichtbares Sandwich essen.
Imitiere einen Dinosaurier.
Falte ein Papierboot oder einen Papierflieger.
Tu so, als wärst du ein schmelzender Schneemann.
Führe einen erfundenen Zaubertrick vor.
Tanz wie ein Roboter.
Mach den Ententanz.
Tu so, als wärst du ein Astronaut im All.
Sing das Alphabet so schnell wie möglich.
Lauf wie ein Krebs seitwärts durch den Raum.
Sei ein Papagei und wiederhole alles, was der nächste Spieler sagt.
Spiel einen glücklichen Welpen, der mit dem Schwanz wedelt.
Bastle eine Papierkrone und schenk sie jemandem.
Tu so, als wärst du eine Raupe, die zum Schmetterling wird.
Erfinde einen Tanz namens „Der Spaghetti“.
"""
