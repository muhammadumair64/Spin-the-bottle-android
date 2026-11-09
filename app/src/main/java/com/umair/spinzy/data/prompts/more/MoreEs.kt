package com.umair.spinzy.data.prompts.more

import com.umair.spinzy.data.Pack
import com.umair.spinzy.data.prompts.PromptSet
import com.umair.spinzy.data.prompts.bank

/** Es extended bank (100+ per category together with PromptsEs). */
val MoreEs = PromptSet(
    truths = mapOf(
        Pack.CHILL to bank(ES_CHILL_T),
        Pack.PARTY to bank(ES_PARTY_T),
        Pack.SPICY to bank(ES_SPICY_T),
        Pack.COUPLES to bank(ES_COUPLES_T),
        Pack.FAMILY to bank(ES_FAMILY_T),
    ),
    dares = mapOf(
        Pack.CHILL to bank(ES_CHILL_D),
        Pack.PARTY to bank(ES_PARTY_D),
        Pack.SPICY to bank(ES_SPICY_D),
        Pack.COUPLES to bank(ES_COUPLES_D),
        Pack.FAMILY to bank(ES_FAMILY_D),
    ),
)

private const val ES_CHILL_T = """
¿Cuál es tu comida reconfortante después de un día largo?
¿Qué canción siempre te pone de buen humor?
¿Cuál es el mejor consejo que te han dado?
Si pudieras dominar una habilidad de la noche a la mañana, ¿cuál sería?
¿Cómo te gusta pasar una tarde de lluvia?
¿Qué olor te transporta a tu infancia?
¿Cuál es el lugar más bonito que has visto?
¿Eres más de mañanas o de noches?
¿Qué película puedes ver una y otra vez?
¿Cuál sería tu trabajo soñado si el dinero no importara?
¿Qué estación del año te gusta más y por qué?
¿Qué pequeño lujo nunca dejarías?
¿Qué es lo más amable que ha hecho por ti un desconocido?
¿Con qué personaje histórico cenarías?
¿Cuál es tu tradición favorita en las fiestas?
¿Café o té, y cómo lo tomas?
¿Cuál fue el primer concierto al que fuiste?
¿Qué afición empezarías si tuvieras más tiempo?
¿Qué es lo que más te gusta del lugar donde vives?
¿Cuál es la mejor comida que has probado?
¿Qué emoji usas más?
¿Cuál es tu juego de mesa favorito?
Si tu vida tuviera una canción, ¿cuál sería?
¿Cuál es el dato más curioso que conoces?
¿Cuál ha sido tu mejor viaje?
¿Qué esperas con ganas este mes?
¿Qué serie recomendarías a todo el mundo?
¿Cómo sería tu fin de semana perfecto?
¿Cuál es el sueño más raro que recuerdas?
¿Qué idioma te gustaría hablar con fluidez?
¿Qué es lo más antiguo que tienes?
¿Cuál es el mejor cumplido que te han hecho?
¿Qué sabor de helado prefieres?
¿Qué lugar está en tu lista de viajes pendientes?
¿Cuál es tu mejor recuerdo del año pasado?
¿Qué te hace reír siempre?
¿Cuál es tu canción de karaoke?
¿Qué comida odiabas de pequeño y ahora te encanta?
¿Playa o montaña?
¿Qué personaje de ficción sería el mejor compañero de piso?
"""

private const val ES_CHILL_D = """
Haz tu mejor imitación del hombre del tiempo de hoy.
Tararea una canción y que el grupo la adivine.
Di el abecedario al revés lo más rápido posible.
Corre por la sala a cámara lenta.
Habla como un pirata hasta tu próximo turno.
Di diez frutas en diez segundos.
Imita a tu personaje de dibujos animados favorito.
Cuenta tu día usando solo cinco palabras.
Inventa un poema corto sobre la persona de tu derecha.
Haz de camarero y toma nota a todos.
Camina por la sala con un libro en la cabeza.
Haz una visita guiada de dos minutos por la sala como en un museo.
Habla en susurros hasta tu próximo turno.
Haz diez sentadillas contando en otro idioma.
Canta todo lo que digas hasta tu próximo turno.
Lee el último mensaje que recibiste de forma dramática.
Haz de presentador de noticias informando sobre esta sala.
Inventa un saludo secreto con la persona de tu izquierda.
Baila como un robot durante treinta segundos.
Pon un apodo a cada persona de la sala.
Mantén una postura de yoga durante veinte segundos.
Cuenta un dato curioso que nadie conozca.
Dibuja un gato con los ojos cerrados.
Haz como si comieras algo muy picante.
Habla como un robot hasta tu próximo turno.
Actúa como si acabaras de ganar un premio y da un discurso.
Canta cumpleaños feliz con la voz más grave posible.
Inventa un paso de baile y enséñaselo al grupo.
Narra lo que hace la persona de tu izquierda como un comentarista deportivo.
Imita a alguien que llega tarde al trabajo.
Di tres cosas buenas de ti en voz alta.
Haz como si prepararas un bocadillo paso a paso.
Habla en rima hasta tu próximo turno.
Imita a un perro que ve nieve por primera vez.
Describe tu bocadillo ideal como un plato de cinco estrellas.
Camina como un pingüino.
Haz de detective investigando quién se comió los aperitivos.
Inventa un nombre de superhéroe para cada jugador.
Cuenta un cuento que empiece por «Érase una vez» en un minuto.
Haz de entrenador y dirige un ejercicio para todos.
"""

private const val ES_PARTY_T = """
¿Qué es lo más raro que has hecho para impresionar a alguien?
¿A quién de esta sala confiarías la organización de tu cumpleaños?
¿Qué es lo más ridículo que has comprado por internet?
¿Cuál ha sido tu peor desastre cocinando?
¿Alguna vez entraste en el baño equivocado?
¿Qué canción vergonzosa tienes en tu lista?
¿Alguna vez enviaste un mensaje a la persona equivocada?
¿Cuál es el reto más loco que has hecho?
¿Por qué tontería has llorado?
¿Quién de aquí sería el peor compañero de piso?
¿Qué es lo más vergonzoso que te pillaron tus padres haciendo?
¿Cuál es la forma más tonta en que te has hecho daño?
¿Alguna vez fingiste estar enfermo para librarte de algo?
¿Qué serie ves en secreto?
¿Qué es lo más raro que has comido?
¿Cuál ha sido tu peor corte de pelo?
¿Quién del grupo ganaría un reality?
¿Cuál es tu fallo de autocorrector más vergonzoso?
¿Cuál es el peor regalo que has recibido?
¿Has regalado algo que te habían regalado?
¿Cuál ha sido tu cita más incómoda?
¿Alguna vez saludaste a alguien que no te saludaba a ti?
¿Qué moda seguiste que ahora te avergüenza?
¿Te has reído tanto que lloraste en público?
¿Quién de la sala es el más dramático?
¿Cuál es tu hábito más raro?
¿Te han pillado hablando solo?
¿Quién de aquí se perdería primero en un viaje?
¿Cuál ha sido tu peor trabajo?
¿Quién de la sala baila mejor?
¿Cuál es la peor excusa que has dado por llegar tarde?
¿Qué te pasó de vergonzoso en una videollamada?
¿Quién de aquí sería el peor espía?
¿Te has tropezado delante de alguien que te gustaba?
¿Cuál es tu mensaje más cringe?
¿Has fingido reírte de un chiste que no entendiste?
¿Quién del grupo tiene más probabilidades de hacerse millonario?
¿Has culpado a otro de algo que rompiste tú?
¿En qué eres competitivo de forma ridícula?
¿Quién de aquí lloraría primero con una película?
"""

private const val ES_PARTY_D = """
Imita a alguien de la sala y que adivine quién es.
Baila como si nadie te mirara durante treinta segundos.
Haz playback de la canción que elija el grupo.
Habla con acento británico hasta tu próximo turno.
Haz como un presentador de concurso hasta tu próximo turno.
Desfila al ritmo de la canción que elija el grupo.
Finge que le pides matrimonio a la persona de tu izquierda.
Canta una canción en un estilo totalmente distinto.
Deja que el grupo te haga un peinado nuevo.
Actúa como una celebridad en la alfombra roja.
Llama a un amigo y habla solo con letras de canciones.
Ríete en voz alta treinta segundos sin parar.
Baila la Macarena entera.
Haz de DJ animando a la multitud.
Enseña tu mejor baile de TikTok.
Haz una pelea de película a cámara lenta.
Intenta hacer reír a todos en treinta segundos.
Ponte los calcetines en las manos hasta tu próximo turno.
Haz beatbox veinte segundos.
Imita a un cantante famoso hasta que lo adivinen.
Haz un anuncio de teletienda de algo de la mesa.
Rompe con un trozo de pizza de forma dramática.
Finge que estás en un videoclip durante un minuto.
Haz una batalla de baile con la persona de tu izquierda.
Haz tu baile de la victoria.
Quédate como estatua y deja que los demás te pongan en pose.
Haz el monólogo de un villano famoso.
Inventa un rap sobre la persona de tu derecha.
Toca una guitarra invisible como una estrella de rock.
Di todo a cámara lenta hasta tu próximo turno.
Actúa como una gallina hasta que alguien se ría.
Lee un mensaje de tu móvil con voz dramática.
Imita un meme famoso.
Haz de hombre del tiempo durante una tormenta.
Canta tu canción favorita como si fuera ópera.
Haz el gusano o un intento muy seguro.
Haz una entrada triunfal en la sala.
Da una charla TED sobre algo totalmente absurdo.
Haz un brindis por el grupo con voz elegante.
Deja que el grupo elija una palabra que debes decir cada vez que hables.
"""

private const val ES_SPICY_T = """
¿Cuál es el mayor secreto que le ocultas a tu mejor amigo?
¿A quién stalkeaste por última vez en redes?
¿Cuándo has estado más celoso?
¿Te ha gustado alguna vez un profesor?
¿Qué es lo peor que has dicho de alguien a sus espaldas?
¿Con quién de esta sala tendrías una cita?
¿Qué es lo más romántico que has hecho?
¿Cuál es tu mayor red flag en el amor?
¿Has hecho ghosting a alguien? ¿Por qué?
¿Cuál ha sido tu beso más incómodo?
¿Has mentido para librarte de una cita?
¿Cuál ha sido tu movimiento más atrevido con alguien que te gustaba?
¿Quién fue tu crush famoso de adolescente?
¿Has leído mensajes de otra persona sin permiso?
¿Qué secreto nunca contaste a tus padres?
¿Has hecho trampa en un examen o juego?
¿Quién te parece la persona más atractiva aquí?
¿Te han dejado por mensaje?
¿Cuál es tu mayor arrepentimiento amoroso?
¿Te han gustado dos personas a la vez?
¿Cuál es tu rasgo más tóxico?
¿Qué has hecho que nunca subirías a redes?
¿Cuál ha sido tu peor ruptura?
¿Qué es lo más impulsivo que has hecho?
¿Te ha gustado alguien de esta sala?
¿Cuál es tu mayor inseguridad?
¿Has dicho «te quiero» sin sentirlo?
¿Qué mensaje te arrepientes de haber enviado?
¿Has estado enamorado de tu mejor amigo?
¿Cuál fue la última mentira que dijiste?
¿Qué te daría vergüenza que supiera tu ex?
¿Has tenido una relación secreta?
¿Has creado una cuenta falsa?
¿Qué finges que te gusta pero en realidad odias?
¿Te han pillado mirando a alguien?
¿Cuál es tu tipo, sinceramente?
¿Cuál es la frase para ligar más cursi que has usado?
¿Te han mandado a la friendzone?
¿A quién escribiste por última vez a las 2 de la mañana?
¿Qué pregunta esperas que nadie te haga esta noche?
"""

private const val ES_SPICY_D = """
Enseña tu historial de búsqueda reciente (solo una pantalla).
Lee en voz alta tus tres últimos mensajes enviados.
Deja que la persona de tu derecha envíe un meme a cualquiera de tus chats.
Llama a un amigo, dile que tienes un gran secreto y cuelga.
Deja que el grupo mire tus listas de música un minuto.
Sube a tu historia una foto de cuando eras pequeño.
Envía una nota de voz a tu mejor amigo cantando su nombre.
Deja que el grupo escriba tu próximo pie de foto.
Enseña tu captura de pantalla más reciente y explícala.
Di las tres últimas personas a las que escribiste.
Envía un emoji al azar a tu crush y enseña la respuesta.
Deja que alguien cambie tu foto de perfil durante una hora.
Enseña el selfie más vergonzoso de tu móvil.
Dile a la persona de tu izquierda tu primera impresión sincera de ella.
Guiña el ojo de forma coqueta a todos los de la sala.
Deja que el grupo lea tus notas durante treinta segundos.
Enseña tu tiempo de pantalla de hoy.
Haz una crítica sincera de treinta segundos de la persona de tu derecha.
Escribe a tu madre «tengo algo que contarte» y explícalo después.
Enseña lo último que te gustó en redes sociales.
Dale una serenata a la persona de enfrente.
Di quién llamarías primero si ganaras la lotería.
Enseña el mensaje más antiguo de tu bandeja.
Recrea la pose de tu último selfie.
Di con quién de aquí intercambiarías el móvil un día.
Revela tu canción más escuchada este mes.
Deja que el grupo lea tu biografía y la critique.
Enseña tu último pedido online.
Di algo bonito de cada persona de la sala, una frase cada una.
Cuenta una anécdota vergonzosa que nunca hayas contado aquí.
Enseña tu galería de exactamente hace un año.
Escribe una carta de amor a tu móvil y léela en voz alta.
Da un discurso sobre por qué eres el mejor partido de la sala.
Cuenta tu peor cita en un minuto.
Envía un corazón a la tercera persona de tus chats recientes.
Admite algo sobre lo que hayas mentido esta noche.
Revela qué app usas más.
Haz un baile lento con una pareja imaginaria mientras todos tararean.
Revela un crush del que nunca hayas hablado.
Haz un roast de ti mismo durante treinta segundos.
"""

private const val ES_COUPLES_T = """
¿En qué momento exacto supiste que te gustaba?
¿Qué recuerdas de nuestra primera conversación?
¿Cuál es tu foto favorita de los dos?
¿Qué hago yo que siempre te hace sonreír?
¿Cuál ha sido nuestra mejor cita?
¿Qué canción te recuerda a mí?
¿Qué es algo que siempre quisiste decirme?
¿Qué es lo más romántico que he hecho por ti?
¿Qué te gustaría que hiciéramos más a menudo?
¿A qué lugar te gustaría viajar conmigo?
¿Cuál es mi manía más adorable?
¿Qué pequeño gesto mío significa mucho para ti?
¿Cuál ha sido nuestra discusión más graciosa?
¿Qué sueño tienes para nuestro futuro?
¿Qué te gusta hacer conmigo en un día perezoso?
¿Qué película te recuerda a nosotros?
¿Cuál es el regalo más especial que te he hecho?
¿Cómo describirías nuestra relación en tres palabras?
¿Qué costumbre mía se te ha pegado?
¿Qué pensaste la primera vez que nos dimos la mano?
¿Cuál es tu apodo favorito para mí?
¿Qué cosa nueva te gustaría probar juntos?
¿Cómo podría hacerte sentir más querido?
¿Qué es lo que más admiras de mí?
¿Cuándo te sentiste orgulloso de mí?
¿Cómo sería nuestra escapada perfecta?
¿Qué tradición te gustaría que empezáramos?
¿Qué te hace echarme de menos?
¿Qué fue lo primero en lo que te fijaste de mí?
¿Cómo te gusta que te consuele cuando estás triste?
¿Cuál es la tontería más grande que hemos hecho juntos?
¿Qué meta te gustaría que lográramos este año?
¿Qué te sorprendió de mí para bien?
¿Cuál es nuestra broma interna favorita?
¿Qué me hizo enamorarte?
¿Cómo crees que hemos crecido desde que nos conocimos?
¿En qué momento te sentiste totalmente seguro conmigo?
¿Cuál es el mensaje más bonito que te he enviado?
¿Qué nombre le pondrías a nuestra futura mascota?
¿Cómo te imaginas que seremos de mayores?
"""

private const val ES_COUPLES_D = """
Abraza a tu pareja treinta segundos sin soltarla.
Dile a tu pareja tres cosas que te encantan mientras le das la mano.
Recread la pose de vuestra foto favorita.
Dale a tu pareja un bocado de algo dulce.
Escribe una nota de amor corta y léela en voz alta.
Bailad juntos la primera canción que suene.
Dale a tu pareja un beso en la frente.
Imita la risa de tu pareja.
Miraos a los ojos y decid algo por lo que estáis agradecidos.
Canta unas líneas de «vuestra canción».
Cuenta vuestra primera cita en un minuto desde tu punto de vista.
Dale a tu pareja un masaje de manos de un minuto.
Susúrrale al oído su mejor cualidad.
Dibuja a tu pareja en treinta segundos.
Planead vuestra próxima cita en voz alta en sesenta segundos.
Ponle a tu pareja un apodo nuevo y úsalo toda la noche.
Bailad lento sin música treinta segundos.
Haceos un selfie bonito y ponedlo de fondo un día.
Envía a tu pareja una nota de voz diciendo por qué la quieres.
Besa la mano de tu pareja como en la realeza.
Haz reír a tu pareja sin tocarla.
Cuéntale a tu pareja un sueño que nunca le hayas contado.
Concurso de miradas; el que pierda hace un cumplido.
Haz un reencuentro de película corriendo por la sala.
Canta una canción de amor con el nombre de tu pareja.
Prométele a tu pareja un pequeño favor para mañana.
Recread la escena de una pareja famosa de película.
Di cinco cosas que te encantan de tu pareja en diez segundos.
Finge que conoces a tu pareja por primera vez.
Dale un beso en la mejilla a tu pareja.
Haced una postura de yoga en pareja.
Di «te quiero» en tres idiomas.
Haced juntos un corazón con las manos.
Inventad un saludo secreto de pareja.
Abrazaos y balanceaos durante una canción.
Prepara a tu pareja su bebida favorita.
Compartid un postre con una sola cuchara.
Dibujad juntos la casa de vuestros sueños.
Hazle a tu pareja un anillo de papel y dáselo.
Dale a tu pareja un abrazo largo y cálido.
"""

private const val ES_FAMILY_T = """
¿Cuál es tu recuerdo favorito de unas vacaciones en familia?
¿Quién cocina mejor en la familia?
¿Qué es lo más gracioso que ha dicho alguien de la familia?
Si nuestra familia fuera una serie, ¿cómo se llamaría?
¿Cuál es tu tradición familiar favorita?
¿Quién de la familia tiene más probabilidades de hacerse famoso?
¿Cuál es la mejor receta familiar?
¿Quién da los mejores abrazos?
¿Cuál ha sido la discusión familiar más tonta?
Si pudieras llevar a toda la familia a cualquier sitio, ¿adónde?
¿Quién ronca más fuerte en la familia?
¿Cuál es tu foto familiar favorita?
¿Cuál ha sido tu mejor cumpleaños?
¿Quién gana siempre a los juegos de mesa?
¿Cuál es la película favorita para ver en familia?
¿Quién cuenta las mejores historias?
¿Qué es lo que más te gusta de nuestra casa?
¿Quién de la familia ganaría un concurso de baile?
¿Qué te gustaría probar como familia?
¿Quién es el más valiente de la familia?
Si nuestra familia tuviera una mascota oficial, ¿qué animal sería?
¿Quién sobreviviría más en una isla desierta?
¿Qué has aprendido de tus padres?
¿Cuál es el apodo más gracioso de la familia?
¿Quién de la familia sería el mejor profesor?
¿Qué norma familiar te parece graciosa?
¿Quién de la familia sería el mejor astronauta?
¿Cuál era tu cuento favorito para dormir?
¿Qué meterías en una cápsula del tiempo familiar?
¿Quién sería el mejor detective de la familia?
¿Qué hizo alguien de la familia por ti hace poco?
Si nuestra familia formara una banda, ¿cómo se llamaría?
¿Qué es lo más divertido que ha hecho una mascota de la familia?
¿Qué trabajo haría genial cada miembro de la familia?
¿Cuál es tu recuerdo favorito de un viaje en coche en familia?
¿Quién de la familia es el más madrugador?
¿Cuál sería el lema de nuestra familia?
¿Qué te gustaría enseñar al más pequeño de aquí?
¿Qué te gustaría aprender del mayor de aquí?
¿Qué es lo que más te gusta de estar juntos esta noche?
"""

private const val ES_FAMILY_D = """
Imita a alguien de la familia y que los demás adivinen.
Actúa como un mono durante treinta segundos.
Cuenta un chiste de «toc toc».
Da un paseo gracioso por la sala.
Entrevista al jugador más pequeño como un reportero.
Pon la cara más graciosa que puedas diez segundos.
Salta como un canguro por la sala.
Imita a un abuelo contando una historia.
Dibuja a toda la familia en treinta segundos.
Haz tu mejor pose de superhéroe.
Camina como un pingüino hasta la puerta y vuelve.
Di el abecedario saltando a la pata coja.
Imita a un elefante.
Choca los cinco con todos los de la sala.
Haz de profesor y da una clase de un minuto.
Baila de felicidad quince segundos.
Imita un animal y que los demás lo adivinen.
Finge ser una aspiradora robot.
Haz diez saltos de tijera.
Inventa una historia sobre un gato volador en un minuto.
Dile un cumplido a la persona de tu derecha.
Ruge como un león.
Haz de pirata buscando un tesoro.
Finge ser un árbol movido por el viento.
Camina por una cuerda floja imaginaria.
Finge comerte un bocadillo gigante invisible.
Imita a un dinosaurio.
Haz un barco o un avión de papel.
Finge ser un muñeco de nieve derritiéndose.
Haz un truco de magia inventado.
Baila como un robot.
Haz el baile del pollito.
Finge ser un astronauta flotando en el espacio.
Canta el abecedario lo más rápido posible.
Finge ser un loro que repite todo lo que dice el siguiente jugador.
Imita a un perrito feliz moviendo la cola.
Haz una corona de papel y regálasela a alguien.
Finge ser una oruga que se convierte en mariposa.
Inventa un baile llamado «el espagueti».
"""
