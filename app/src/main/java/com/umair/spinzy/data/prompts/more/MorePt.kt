package com.umair.spinzy.data.prompts.more

import com.umair.spinzy.data.Pack
import com.umair.spinzy.data.prompts.PromptSet
import com.umair.spinzy.data.prompts.bank

/** Pt extended bank (100+ per category together with PromptsPt). */
val MorePt = PromptSet(
    truths = mapOf(
        Pack.CHILL to bank(PT_CHILL_T),
        Pack.PARTY to bank(PT_PARTY_T),
        Pack.SPICY to bank(PT_SPICY_T),
        Pack.COUPLES to bank(PT_COUPLES_T),
        Pack.FAMILY to bank(PT_FAMILY_T),
    ),
    dares = mapOf(
        Pack.CHILL to bank(PT_CHILL_D),
        Pack.PARTY to bank(PT_PARTY_D),
        Pack.SPICY to bank(PT_SPICY_D),
        Pack.COUPLES to bank(PT_COUPLES_D),
        Pack.FAMILY to bank(PT_FAMILY_D),
    ),
)

private const val PT_CHILL_T = """
Qual é a sua comida favorita depois de um dia cansativo?
Que música sempre melhora o seu humor?
Qual foi o melhor conselho que você já recebeu?
Se pudesse dominar uma habilidade da noite para o dia, qual seria?
Como você gosta de passar um dia de chuva?
Que cheiro te leva de volta à infância?
Qual é o lugar mais bonito que você já viu?
Você é mais da manhã ou da noite?
Que filme você assiste mil vezes sem cansar?
Qual seria o trabalho dos seus sonhos se dinheiro não importasse?
Qual é a sua estação do ano favorita e por quê?
Que pequeno luxo você nunca abriria mão?
Qual foi a coisa mais gentil que um desconhecido fez por você?
Com qual personagem histórico você jantaria?
Qual é a sua tradição de fim de ano favorita?
Café ou chá, e como você toma?
Qual foi o primeiro show que você foi?
Que hobby você começaria se tivesse mais tempo?
O que você mais gosta na cidade onde mora?
Qual foi a melhor refeição que você já comeu?
Qual emoji você mais usa?
Qual é o seu jogo de tabuleiro favorito?
Se a sua vida tivesse uma trilha sonora, qual seria a música principal?
Qual é a curiosidade mais legal que você conhece?
Qual foi a sua melhor viagem?
O que você está ansioso para fazer este mês?
Que série você recomendaria para todo mundo?
Como seria o seu fim de semana perfeito?
Qual foi o sonho mais estranho que você lembra?
Que idioma você gostaria de falar fluentemente?
Qual é a coisa mais antiga que você guarda?
Qual foi o melhor elogio que você já recebeu?
Qual é o seu sabor de sorvete favorito?
Que lugar está na sua lista de viagens?
Qual é a sua melhor lembrança do ano passado?
O que sempre te faz rir?
Qual é a sua música de karaokê?
Que comida você odiava quando criança e agora ama?
Praia ou montanha?
Que personagem de ficção seria o melhor colega de quarto?
"""

private const val PT_CHILL_D = """
Faça sua melhor imitação de apresentador da previsão do tempo.
Cantarole uma música e deixe o grupo adivinhar.
Diga o alfabeto de trás para frente o mais rápido possível.
Corra pela sala em câmera lenta.
Fale como um pirata até a sua próxima vez.
Diga dez frutas em dez segundos.
Imite o seu personagem de desenho favorito.
Conte o seu dia usando só cinco palavras.
Invente um poema curto sobre a pessoa à sua direita.
Seja garçom e anote o pedido de todos.
Ande pela sala com um livro na cabeça.
Faça um tour de museu de dois minutos pela sala.
Fale sussurrando até a sua próxima vez.
Faça dez agachamentos contando em outro idioma.
Cante tudo o que disser até a sua próxima vez.
Leia a última mensagem que recebeu de forma dramática.
Seja um apresentador de jornal falando sobre esta sala.
Invente um aperto de mão secreto com a pessoa à sua esquerda.
Dance como um robô por trinta segundos.
Dê um apelido para cada pessoa da sala.
Fique numa posição de ioga por vinte segundos.
Conte uma curiosidade que ninguém conhece.
Desenhe um gato de olhos fechados.
Finja que está comendo algo muito apimentado.
Fale como um robô até a sua próxima vez.
Finja que ganhou um prêmio e faça um discurso.
Cante parabéns com a voz mais grossa possível.
Invente um passo de dança e ensine ao grupo.
Narre o que a pessoa à sua esquerda faz como um narrador de futebol.
Imite alguém chegando atrasado ao trabalho.
Diga três coisas boas sobre você em voz alta.
Finja que está preparando um sanduíche passo a passo.
Fale rimando até a sua próxima vez.
Imite um cachorro vendo neve pela primeira vez.
Descreva o seu lanche ideal como um prato cinco estrelas.
Ande como um pinguim.
Seja um detetive investigando quem comeu os petiscos.
Invente um nome de super-herói para cada jogador.
Conte uma história que comece com «Era uma vez» em um minuto.
Seja personal trainer e comande um exercício para todos.
"""

private const val PT_PARTY_T = """
Qual foi a coisa mais estranha que você fez para impressionar alguém?
Quem desta sala você deixaria organizar o seu aniversário?
Qual foi a compra online mais ridícula que você já fez?
Qual foi o seu maior desastre na cozinha?
Você já entrou no banheiro errado?
Que música vergonhosa está na sua playlist?
Você já mandou mensagem para a pessoa errada?
Qual foi o desafio mais louco que você já topou?
Por qual bobagem você já chorou?
Quem aqui seria o pior colega de quarto?
Qual foi a coisa mais vergonhosa que seus pais te pegaram fazendo?
Qual foi o jeito mais bobo que você já se machucou?
Você já fingiu estar doente para escapar de algo?
Que série você assiste escondido?
Qual foi a coisa mais estranha que você já comeu?
Qual foi o seu pior corte de cabelo?
Quem do grupo ganharia um reality show?
Qual foi o seu erro de corretor mais vergonhoso?
Qual foi o pior presente que você já ganhou?
Você já repassou um presente que ganhou?
Qual foi o seu encontro mais constrangedor?
Você já acenou para alguém que acenava para outra pessoa?
Que moda você seguiu e hoje tem vergonha?
Você já riu tanto que chorou em público?
Quem desta sala é o mais dramático?
Qual é o seu hábito mais esquisito?
Já te pegaram falando sozinho?
Quem aqui se perderia primeiro numa viagem?
Qual foi o seu pior emprego?
Quem desta sala dança melhor?
Qual foi a pior desculpa que você deu por chegar atrasado?
O que de vergonhoso já aconteceu com você numa videochamada?
Quem aqui seria o pior espião?
Você já tropeçou na frente do seu crush?
Qual é a sua mensagem mais cringe?
Você já fingiu rir de uma piada que não entendeu?
Quem do grupo tem mais chance de ficar milionário?
Você já culpou outra pessoa por algo que você quebrou?
Em que você é competitivo de um jeito ridículo?
Quem aqui choraria primeiro num filme?
"""

private const val PT_PARTY_D = """
Imite alguém da sala e deixe adivinharem quem é.
Dance como se ninguém estivesse olhando por trinta segundos.
Faça dublagem da música que o grupo escolher.
Fale com sotaque britânico até a sua próxima vez.
Aja como apresentador de programa de auditório até a sua vez.
Desfile ao som da música que o grupo escolher.
Finja pedir em casamento a pessoa à sua esquerda.
Cante uma música num estilo totalmente diferente.
Deixe o grupo fazer um penteado novo em você.
Aja como uma celebridade no tapete vermelho.
Ligue para um amigo e fale só com letras de música.
Gargalhe por trinta segundos sem parar.
Dance a Macarena inteira.
Seja um DJ animando a galera.
Mostre a sua melhor dança de TikTok.
Faça uma luta de filme em câmera lenta.
Tente fazer todos rirem em trinta segundos.
Coloque meias nas mãos até a sua próxima vez.
Faça beatbox por vinte segundos.
Imite um cantor famoso até adivinharem.
Faça um comercial de televendas de algo da mesa.
Termine com uma fatia de pizza de forma dramática.
Finja que está num videoclipe por um minuto.
Faça uma batalha de dança com a pessoa à sua esquerda.
Faça a sua dança da vitória.
Vire estátua e deixe os outros te colocarem em pose.
Faça o monólogo de um vilão famoso.
Invente um rap sobre a pessoa à sua direita.
Toque uma guitarra imaginária como um astro do rock.
Fale tudo em câmera lenta até a sua próxima vez.
Aja como uma galinha até alguém rir.
Leia uma mensagem do seu celular com voz dramática.
Imite um meme famoso.
Seja o moço do tempo durante uma tempestade.
Cante sua música favorita como se fosse ópera.
Faça a dança da minhoca ou uma tentativa bem confiante.
Faça uma entrada triunfal na sala.
Dê uma palestra TED sobre algo totalmente absurdo.
Faça um brinde ao grupo com voz chique.
Deixe o grupo escolher uma palavra que você deve dizer toda vez que falar.
"""

private const val PT_SPICY_T = """
Qual é o maior segredo que você esconde do seu melhor amigo?
Quem foi a última pessoa que você stalkeou nas redes?
Quando você sentiu mais ciúmes?
Você já teve crush em um professor?
Qual foi a pior coisa que você falou de alguém pelas costas?
Com quem desta sala você sairia num encontro?
Qual foi a coisa mais romântica que você já fez?
Qual é a sua maior red flag no amor?
Você já deu ghosting em alguém? Por quê?
Qual foi o seu beijo mais constrangedor?
Você já mentiu para escapar de um encontro?
Qual foi a sua investida mais ousada num crush?
Quem era o seu crush famoso na adolescência?
Você já leu mensagens de alguém sem permissão?
Que segredo você nunca contou aos seus pais?
Você já colou numa prova ou trapaceou num jogo?
Quem você acha a pessoa mais atraente aqui?
Já terminaram com você por mensagem?
Qual é o seu maior arrependimento amoroso?
Você já gostou de duas pessoas ao mesmo tempo?
Qual é o seu traço mais tóxico?
O que você fez que nunca postaria?
Qual foi o seu pior término?
Qual foi a coisa mais impulsiva que você já fez?
Você já teve crush em alguém desta sala?
Qual é a sua maior insegurança?
Você já disse «eu te amo» sem sentir?
Que mensagem você se arrepende de ter enviado?
Você já foi apaixonado pelo seu melhor amigo?
Qual foi a última mentira que você contou?
O que te deixaria com vergonha se o seu ex soubesse?
Você já teve um relacionamento secreto?
Você já criou um perfil falso?
O que você finge gostar mas na verdade odeia?
Já te pegaram encarando alguém?
Qual é o seu tipo, sinceramente?
Qual foi a cantada mais brega que você já usou?
Você já foi colocado na friendzone?
Para quem você mandou mensagem às 2 da manhã pela última vez?
Que pergunta você espera que ninguém te faça hoje?
"""

private const val PT_SPICY_D = """
Mostre o seu histórico de pesquisa recente (só uma tela).
Leia em voz alta as suas três últimas mensagens enviadas.
Deixe a pessoa à sua direita mandar um meme para qualquer conversa sua.
Ligue para um amigo, diga que tem um grande segredo e desligue.
Deixe o grupo olhar suas playlists por um minuto.
Poste nos stories uma foto sua de criança.
Mande um áudio para o seu melhor amigo cantando o nome dele.
Deixe o grupo escrever a sua próxima legenda.
Mostre o seu print mais recente e explique.
Diga as três últimas pessoas com quem você conversou.
Mande um emoji aleatório para o seu crush e mostre a resposta.
Deixe alguém mudar a sua foto de perfil por uma hora.
Mostre a selfie mais vergonhosa do seu celular.
Diga à pessoa à sua esquerda a sua primeira impressão sincera dela.
Pisque de forma charmosa para todos da sala.
Deixe o grupo ler as suas notas por trinta segundos.
Mostre o seu tempo de tela de hoje.
Faça uma crítica sincera de trinta segundos da pessoa à sua direita.
Mande para sua mãe «preciso te contar uma coisa» e explique depois.
Mostre a última coisa que você curtiu nas redes.
Faça uma serenata para a pessoa à sua frente.
Diga para quem você ligaria primeiro se ganhasse na loteria.
Mostre a mensagem mais antiga da sua caixa de entrada.
Recrie a pose da sua última selfie.
Diga com quem daqui você trocaria de celular por um dia.
Revele a música que você mais ouviu este mês.
Deixe o grupo ler a sua bio e criticar.
Mostre o seu último pedido online.
Diga algo legal sobre cada pessoa da sala, uma frase para cada.
Conte uma história vergonhosa que nunca contou aqui.
Mostre a sua galeria de exatamente um ano atrás.
Escreva uma carta de amor para o seu celular e leia em voz alta.
Faça um discurso de por que você é o melhor partido da sala.
Conte o seu pior encontro em um minuto.
Mande um coração para a terceira pessoa das suas conversas recentes.
Admita algo sobre o que mentiu hoje.
Revele o app que você mais usa.
Dance lento com um par imaginário enquanto todos cantarolam.
Revele um crush que você nunca mencionou.
Faça piada de si mesmo por trinta segundos.
"""

private const val PT_COUPLES_T = """
Em que momento exato você soube que gostava de mim?
O que você lembra da nossa primeira conversa?
Qual é a sua foto favorita de nós dois?
O que eu faço que sempre te faz sorrir?
Qual foi o nosso melhor encontro?
Que música te lembra de mim?
O que você sempre quis me dizer?
Qual foi a coisa mais romântica que eu já fiz por você?
O que você gostaria que fizéssemos mais vezes?
Para onde você gostaria de viajar comigo?
Qual é a minha mania mais fofa?
Que pequeno gesto meu significa muito para você?
Qual foi a nossa briga mais engraçada?
Que sonho você tem para o nosso futuro?
O que você gosta de fazer comigo num dia preguiçoso?
Que filme te lembra de nós?
Qual foi o presente mais especial que eu te dei?
Como você descreveria nosso relacionamento em três palavras?
Que hábito meu você acabou pegando?
O que você pensou quando seguramos as mãos pela primeira vez?
Qual é o seu apelido favorito para mim?
Que coisa nova você gostaria de experimentar juntos?
Como eu poderia fazer você se sentir mais amado?
O que você mais admira em mim?
Quando você sentiu orgulho de mim?
Como seria a nossa viagem perfeita?
Que tradição você gostaria que começássemos?
O que te faz sentir saudade de mim?
Qual foi a primeira coisa que você notou em mim?
Como você gosta de ser consolado quando está triste?
Qual foi a maior bobagem que fizemos juntos?
Que meta você gostaria que alcançássemos este ano?
O que te surpreendeu positivamente em mim?
Qual é a nossa piada interna favorita?
O que fez você se apaixonar por mim?
Como você acha que crescemos desde que nos conhecemos?
Quando você se sentiu totalmente seguro comigo?
Qual foi a mensagem mais bonita que eu já te mandei?
Que nome você daria ao nosso futuro bichinho?
Como você imagina que seremos quando formos velhinhos?
"""

private const val PT_COUPLES_D = """
Abrace o seu par por trinta segundos sem soltar.
Diga três coisas que você ama no seu par segurando a mão dele.
Recriem a pose da sua foto favorita juntos.
Dê ao seu par uma mordida de algo doce.
Escreva um bilhete de amor curto e leia em voz alta.
Dancem juntos a primeira música que tocar.
Dê um beijo na testa do seu par.
Imite a risada do seu par.
Olhem nos olhos um do outro e digam algo pelo qual são gratos.
Cante alguns versos da «música de vocês».
Conte o primeiro encontro de vocês em um minuto do seu ponto de vista.
Faça uma massagem de um minuto nas mãos do seu par.
Sussurre no ouvido do seu par a melhor qualidade dele.
Desenhe o seu par em trinta segundos.
Planejem o próximo encontro em voz alta em sessenta segundos.
Dê um apelido novo ao seu par e use a noite toda.
Dancem lento sem música por trinta segundos.
Tirem uma selfie fofa e coloquem como papel de parede por um dia.
Mande um áudio ao seu par dizendo por que o ama.
Beije a mão do seu par como na realeza.
Faça o seu par rir sem tocá-lo.
Conte ao seu par um sonho que nunca contou.
Façam um duelo de olhares; quem perder faz um elogio.
Façam um reencontro de cinema correndo pela sala.
Cante uma música de amor com o nome do seu par.
Prometa ao seu par um pequeno favor para amanhã.
Recriem a cena de um casal famoso do cinema.
Diga cinco coisas que ama no seu par em dez segundos.
Finja que está conhecendo o seu par pela primeira vez.
Dê um beijo na bochecha do seu par.
Façam uma pose de ioga em dupla.
Diga «eu te amo» em três idiomas.
Façam juntos um coração com as mãos.
Inventem um cumprimento secreto de casal.
Abracem-se e balancem durante uma música.
Prepare a bebida favorita do seu par.
Dividam uma sobremesa com uma colher só.
Desenhem juntos a casa dos sonhos de vocês.
Faça um anel de papel e entregue ao seu par.
Dê ao seu par um abraço longo e quentinho.
"""

private const val PT_FAMILY_T = """
Qual é a sua lembrança favorita de férias em família?
Quem cozinha melhor na família?
Qual foi a coisa mais engraçada que alguém da família já disse?
Se a nossa família fosse uma série, qual seria o nome?
Qual é a sua tradição familiar favorita?
Quem da família tem mais chance de ficar famoso?
Qual é a melhor receita da família?
Quem dá os melhores abraços?
Qual foi a discussão mais boba da família?
Se pudesse levar a família toda para qualquer lugar, para onde seria?
Quem ronca mais alto na família?
Qual é a sua foto de família favorita?
Qual foi o seu melhor aniversário?
Quem sempre ganha nos jogos de tabuleiro?
Qual é o filme favorito para ver em família?
Quem conta as melhores histórias?
O que você mais gosta na nossa casa?
Quem da família ganharia um concurso de dança?
O que você gostaria de experimentar em família?
Quem é o mais corajoso da família?
Se a nossa família tivesse um mascote, que animal seria?
Quem sobreviveria mais tempo numa ilha deserta?
O que você aprendeu com os seus pais?
Qual é o apelido mais engraçado da família?
Quem da família seria o melhor professor?
Que regra da família você acha engraçada?
Quem da família seria o melhor astronauta?
Qual era a sua história favorita antes de dormir?
O que você colocaria numa cápsula do tempo da família?
Quem seria o melhor detetive da família?
O que alguém da família fez por você recentemente?
Se a nossa família tivesse uma banda, qual seria o nome?
Qual foi a coisa mais engraçada que um bichinho da família fez?
Que profissão combinaria com cada pessoa da família?
Qual é a sua lembrança favorita de uma viagem de carro em família?
Quem da família acorda mais cedo?
Qual seria o lema da nossa família?
O que você gostaria de ensinar ao mais novo daqui?
O que você gostaria de aprender com o mais velho daqui?
O que você mais gosta de estarmos juntos hoje?
"""

private const val PT_FAMILY_D = """
Imite alguém da família e deixe os outros adivinharem.
Aja como um macaco por trinta segundos.
Conte uma piada de «toc toc».
Faça uma caminhada engraçada pela sala.
Entreviste o jogador mais novo como um repórter.
Faça a careta mais engraçada que conseguir por dez segundos.
Pule como um canguru pela sala.
Imite um avô contando uma história.
Desenhe a família toda em trinta segundos.
Faça a sua melhor pose de super-herói.
Ande como um pinguim até a porta e volte.
Diga o alfabeto pulando num pé só.
Imite um elefante.
Bata na mão de todos da sala.
Seja professor e dê uma aula de um minuto.
Faça uma dança de alegria por quinze segundos.
Imite um animal e deixe os outros adivinharem.
Finja ser um aspirador robô.
Faça dez polichinelos.
Invente uma história sobre um gato voador em um minuto.
Faça um elogio à pessoa à sua direita.
Ruja como um leão.
Seja um pirata procurando um tesouro.
Finja ser uma árvore balançando ao vento.
Ande numa corda bamba imaginária.
Finja comer um sanduíche gigante invisível.
Imite um dinossauro.
Faça um barquinho ou aviãozinho de papel.
Finja ser um boneco de neve derretendo.
Faça um truque de mágica inventado.
Dance como um robô.
Faça a dança do passarinho.
Finja ser um astronauta flutuando no espaço.
Cante o alfabeto o mais rápido possível.
Finja ser um papagaio que repete tudo o que o próximo jogador diz.
Imite um cachorrinho feliz abanando o rabo.
Faça uma coroa de papel e dê para alguém.
Finja ser uma lagarta virando borboleta.
Invente uma dança chamada «o espaguete».
"""
