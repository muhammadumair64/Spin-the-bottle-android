package com.umair.spinzy.data.prompts.more

import com.umair.spinzy.data.Pack
import com.umair.spinzy.data.prompts.PromptSet
import com.umair.spinzy.data.prompts.bank

/** English extended bank — together with PromptsEn this gives 500+ questions per category. */
val MoreEn = PromptSet(
    truths = mapOf(
        Pack.CHILL to bank(EN_CHILL_T),
        Pack.PARTY to bank(EN_PARTY_T),
        Pack.SPICY to bank(EN_SPICY_T),
        Pack.COUPLES to bank(EN_COUPLES_T),
        Pack.FAMILY to bank(EN_FAMILY_T),
    ),
    dares = mapOf(
        Pack.CHILL to bank(EN_CHILL_D),
        Pack.PARTY to bank(EN_PARTY_D),
        Pack.SPICY to bank(EN_SPICY_D),
        Pack.COUPLES to bank(EN_COUPLES_D),
        Pack.FAMILY to bank(EN_FAMILY_D),
    ),
)

private const val EN_CHILL_T = """
What's your go-to comfort food after a long day?
Which song always puts you in a good mood?
What's the best piece of advice you've ever been given?
If you could master one skill overnight, what would it be?
What's your favourite way to spend a rainy afternoon?
Which smell instantly takes you back to childhood?
What's the most beautiful place you've ever seen?
Are you a morning person or a night owl?
What's a movie you can watch over and over again?
What's the last book or article that really stuck with you?
What's your dream job if money didn't matter?
Which season do you love most and why?
What's a small luxury you'd never give up?
What's the nicest thing a stranger has ever done for you?
If you could have dinner with any historical figure, who would it be?
What's your hidden talent that most people don't know about?
What's the most useful thing you learned in school?
What's your favourite holiday tradition?
Coffee or tea, and how do you take it?
What was the first concert or live show you went to?
What hobby would you pick up if you had more time?
What's your favourite thing about where you live?
What's the best meal you've ever had?
What's a word you always misspell?
Which emoji do you use the most?
What's your favourite board game or card game?
If your life had a theme song, what would it be?
What's the most interesting fact you know?
What's the best trip you've ever taken?
What's something you're looking forward to this month?
What's your favourite snack at the cinema?
What's a TV show you'd recommend to everyone?
What do you usually do to relax before bed?
What would your perfect weekend look like?
What's the strangest dream you remember?
What's your favourite animal and why?
If you could speak any language fluently, which one?
What's your favourite kind of weather?
What's the oldest thing you own?
What's the best compliment you've ever received?
What did you want to be when you grew up?
What's a skill you're secretly proud of?
What's your favourite ice cream flavour?
What's your favourite way to exercise, if any?
What's a place on your travel bucket list?
What's your favourite memory from last year?
What's one thing that always makes you laugh?
Which superpower would be most useful in your daily life?
What's your go-to karaoke song?
What's your favourite type of music to work to?
What's the best gift you've ever given someone?
What's a food you hated as a kid but love now?
What app do you open first in the morning?
If you could live in any era, which would you choose?
What's your favourite thing to cook?
Do you prefer the beach or the mountains?
What's the funniest thing that happened to you this week?
What's your favourite quote or saying?
Who was your favourite teacher and why?
What's a habit you're proud of?
What's your favourite holiday destination so far?
What's the best purchase you've made under twenty dollars?
What's a fictional world you'd love to live in?
What's something you always carry with you?
What was your first phone?
What's your favourite childhood toy?
What's the longest you've ever stayed awake?
What's your favourite thing about your best friend?
What would you name a pet dragon?
What's the most adventurous food you've tried?
If you won the lottery, what's the first thing you'd buy?
What's a skill you'd love to teach someone?
What's your favourite season for travelling?
What's your favourite type of dessert?
Which fictional character would make the best roommate?
What's your idea of a perfect breakfast?
What's your favourite podcast or YouTube channel?
What's something that instantly calms you down?
What do you think you'll be doing in ten years?
What's the most interesting job you've ever had?
What's your favourite sport to watch?
What's your most-used phrase?
What's the best concert you've ever been to?
What's your favourite thing to do with friends?
What's a small win you had recently?
Which game from your childhood would you play again today?
What's a movie that made you cry?
What's your favourite colour and has it always been?
What's something you've changed your mind about recently?
What's the best city you've visited?
What's your favourite way to celebrate your birthday?
What's a trend you never understood?
What's your favourite kind of pizza topping?
Do you sing in the shower?
What's the last thing you bought online?
What's something you're really good at cooking?
What's the best advice you'd give your younger self?
What's your favourite flower?
What's the most relaxing sound you know?
Do you prefer sunrise or sunset?
What's one thing on your desk or nightstand right now?
What's your favourite word?
What's your favourite thing about weekends?
What's an underrated movie everyone should watch?
If you could only eat one cuisine forever, which one?
What's your favourite memory with a grandparent?
What's a local spot you love that tourists don't know about?
What's your favourite hot drink?
What's your favourite kind of weather for a walk?
What's a game you're surprisingly good at?
What's the best show you binge-watched?
Which cartoon character do you most resemble?
What's your favourite thing about your job or studies?
What's a sound that annoys you the most?
What's the most useful app on your phone?
What's your favourite holiday food?
What did you do on your last day off?
What's your favourite fruit?
If you opened a café, what would you call it?
What's something you collect or used to collect?
What's the best thing you've learned from a friend?
What's your favourite way to start the day?
What's a place that feels like home to you?
What's the cutest animal video you've seen recently?
What's your favourite snack when you're studying or working?
Do you prefer books or movies?
What's your favourite thing to do alone?
What would you do with an extra hour every day?
What's a smell you love that others think is weird?
What's your favourite type of shoe?
What's your favourite game to play on your phone?
What's the coolest thing you've ever seen in nature?
Which famous person would you love to meet?
What's a skill that took you a long time to learn?
What's your favourite thing about your family?
What's your favourite festival or event?
What's your favourite sandwich?
If you could design a theme park ride, what would it be?
What's your earliest memory?
What's your favourite sitcom?
What's your favourite type of weather to sleep in?
What's the best compliment you could give yourself?
What's your favourite thing about this group?
Which planet would you visit if you could?
What's your favourite thing to buy at the supermarket?
What's a hobby you've tried and quit?
What's your favourite meal of the day?
What's the best road trip you've been on?
What's your favourite thing to do on a sunny day?
What's the most interesting museum you've visited?
What's a tradition you'd like to start?
What's your favourite kind of soup?
What's your favourite thing about the place you grew up?
What's one thing you can't live without?
What's your favourite type of tree?
What's your favourite song from childhood?
What's a goal you achieved that you're proud of?
What's your favourite way to give gifts?
What's your favourite late-night snack?
What's your favourite outfit you own?
If you could change your name, what would it be?
What's the best surprise you've ever had?
What's your favourite animated movie?
What's your favourite pastime on a long flight?
What's the best homemade gift you've received?
What's your favourite hot sauce or spice?
What's your favourite thing about mornings?
What's your favourite thing about evenings?
What's your favourite memory from school trips?
If animals could talk, which would be the rudest?
What's your favourite type of cake?
What's your favourite emoji combination?
What's your favourite thing to watch on a lazy Sunday?
What's your favourite household chore?
What's your least favourite household chore?
What's the most random thing in your bag right now?
What's your favourite sound in nature?
What's your favourite holiday movie?
What's the best name you've heard for a pet?
What's your favourite board game strategy?
What's your favourite thing about friendships?
What's a skill you'd love to have for parties?
What's your favourite way to unwind after work?
If you could be any age for a week, what age would you pick?
What's your favourite thing to draw or doodle?
What's your favourite street food?
What's your favourite thing about the internet?
What's the best fact you learned this year?
What's your favourite morning drink?
What's your favourite type of puzzle?
Which invention do you think is the most underrated?
What's your favourite way to spend a holiday at home?
If you were a kitchen appliance, which would you be?
What's your favourite thing to do in the rain?
What's your favourite thing about being an adult?
What's your favourite thing about being a kid?
What's your favourite mythical creature?
What's your favourite cereal?
What's your favourite thing about technology today?
What's your favourite sound from your childhood home?
If you had a boat, what would you name it?
What's your favourite kind of bread?
What's your favourite thing to see at a zoo?
What's your favourite song to dance to?
What's your favourite type of tea or juice?
What would your superhero name be?
What's your favourite movie snack combo?
What's your favourite memory with a pet?
What's your favourite constellation or space fact?
What's your favourite holiday decoration?
What's your favourite board game to win?
What's your favourite way to learn new things?
What's your favourite comfort TV show?
What's your favourite piece of furniture you own?
What's your favourite book from childhood?
What's your favourite outdoor activity?
What's your favourite indoor activity?
What's your favourite type of party?
What's your favourite thing to order at a restaurant?
What's your favourite thing about your neighbourhood?
What's your favourite sound when you wake up?
What's one thing you'd like to get better at this year?
What's your favourite kind of photo to take?
What's your favourite flavour of chips or crisps?
What's your favourite type of vacation?
What's your favourite memory from a wedding or celebration?
What's your favourite thing about learning a new hobby?
What's your favourite meme of all time?
What's your favourite way to say thank you?
If you could swap jobs with anyone for a day, who would it be?
What's your favourite thing to do at the beach?
What's your favourite midnight thought?
What's the kindest thing you've done recently?
What's your favourite small talk topic?
What's your favourite plant to keep at home?
What's your favourite thing about long weekends?
What's your favourite thing you've ever built or made?
What's your favourite store to wander around?
What's your favourite card game at family gatherings?
What's your favourite thing about your city at night?
What's your favourite type of sky: stormy, sunny or starry?
What's your favourite way to be surprised?
What's your favourite thing about meeting new people?
What's your favourite thing to celebrate?
"""

private const val EN_CHILL_D = """
Do your best weather forecaster impression for today.
Hum a song and let the group guess it.
Say the alphabet backwards as fast as you can.
Do your best slow-motion run across the room.
Speak like a pirate until your next turn.
Name ten fruits in ten seconds.
Do an impression of your favourite cartoon character.
Try to touch your nose with your tongue.
Tell a story about your day using only five words.
Make up a short poem about the person on your right.
Pretend to be a waiter and take everyone's order.
Do your best impression of a celebrity until someone guesses who.
Balance a book on your head and walk across the room.
Give a two-minute tour of the room like a museum guide.
Do your best animal walk and let the others guess the animal.
Talk in a whisper until your next turn.
Do ten squats while counting in another language.
Describe your phone's home screen like an art critic.
Sing everything you say until your next turn.
Do a dramatic reading of the last text you received.
Pretend to be a news anchor and report on this room.
Try to juggle three small objects.
Make up a new handshake with the person on your left.
Do your best robot dance for thirty seconds.
Act out your morning routine without words.
Spell your full name backwards out loud.
Give everyone in the room a nickname.
Do your best yoga pose and hold it for twenty seconds.
Tell the group a fun fact they probably don't know.
Pretend you are a statue until someone makes you laugh.
Draw a cat with your eyes closed.
Say a tongue twister five times fast.
Pretend to be a tour guide describing the snacks on the table.
Do your best evil laugh, then your best good laugh.
Count to twenty in a different language.
Do a cartwheel or your best attempt at one.
Mime eating a very spicy meal.
Make a paper airplane and see how far it flies.
Talk like a robot until your next turn.
Do your best impression of a baby learning to walk.
Tell a joke in the style of a serious news reporter.
Share the funniest photo in your gallery.
Freeze in a funny pose until the next person finishes their turn.
Recite the lyrics of a song as if it were a poem.
Do an impression of someone on a rollercoaster.
Pretend to sell the person on your left a pen.
Walk across the room like a supermodel.
Describe the person on your right using only food words.
Do ten jumping jacks while laughing.
Pretend you're a cat for the next minute.
Act like you just won an award and give a speech.
Sing happy birthday in the deepest voice you can.
Make up a dance move and teach it to the group.
Pretend you're stuck in a box for thirty seconds.
Do your best impression of a strict teacher.
List five things you're grateful for right now.
Name a song for every letter of your name.
Narrate what the person on your left is doing like a sports commentator.
Act out a famous movie scene using only your hands.
Do an impression of someone waking up late for work.
Say three nice things about yourself out loud.
Mime making a sandwich step by step.
Pretend to be a very slow internet connection.
Do the floss dance for fifteen seconds.
Rap a verse about your favourite snack.
Pretend to be a lost tourist asking for directions.
Make a funny face and hold it while someone takes a photo.
Try to say the months of the year backwards.
Do your best opera singing for ten seconds.
Pretend you're walking on the moon.
Speak in rhymes until your next turn.
Mime playing an instrument for thirty seconds.
Tell the group your most embarrassing nickname.
Do an impression of a famous cartoon villain.
Pretend you're a chef on a cooking show.
Show the group your best dance move from school days.
Act like a sloth for the next thirty seconds.
Pretend you're a commentator at a snail race.
Do your best impression of a game show host.
Make up a short song about the person opposite you.
Draw a self-portrait in twenty seconds.
Walk backwards around the room once.
Pretend to be a squirrel hiding nuts.
Say the name of everyone in the room in reverse order.
Talk without using the letter S for one minute.
Do a magic trick, real or completely fake.
Speak only in movie quotes until your next turn.
Pretend you're a GPS giving directions to the kitchen.
Hold a plank for twenty seconds.
Act out how you'd react to winning a million dollars.
Imitate the sound of three different instruments.
Pretend to be a mime trapped in an invisible box.
Do a TV commercial for your shoes.
Sing the chorus of your favourite song in a whisper.
Tell a story that starts with "Once upon a time" in one minute.
Pretend to be a fitness instructor and lead everyone in one exercise.
Do your best impression of a dog seeing snow for the first time.
Describe your perfect sandwich like it's a five-star dish.
Pretend you're on the phone with your favourite celebrity.
Try to whistle a whole song.
Act like a superhero landing.
Hop like a bunny to the door and back.
Do your best impression of a phone ringing.
Write your name in the air with your elbow.
Pretend you're a chicken laying an egg.
Do a dramatic weather report about tomorrow.
Pretend you're an alien seeing humans for the first time.
Make a crown out of paper and wear it until your next turn.
Do an impression of the last person who spoke.
Say something nice to every player in under thirty seconds.
Act out your favourite emoji.
Read a random product label like it's a love letter.
Pretend to be a famous painter painting the person on your right.
Try to stand on one leg with your eyes closed for fifteen seconds.
Sing a nursery rhyme as a rock song.
Speak in a movie trailer voice until your next turn.
Do your best penguin waddle.
Pretend you're a detective investigating the snacks.
Make up a superhero name for every player.
Do your best impression of a cartoon sound effect.
Act like a plant growing in fast motion.
Tell the group the plot of a movie in ten seconds.
Mime brushing a crocodile's teeth.
Do your best slow clap for the person on your left.
Pretend you're a cowboy for the next minute.
Describe today's weather like a poet.
Pretend you're a statue in a museum and let people guess who you are.
Do an impression of someone trying to stay awake in a meeting.
Make up a new word and explain what it means.
Pretend to be a famous YouTuber starting a video.
Hum the national anthem of any country.
Draw a map of your dream house in thirty seconds.
Pretend you're a teacher marking everyone present.
Do your best impression of an excited puppy.
Show the group your most-used sticker or GIF.
Pretend you're a commentator for a chess match.
Act out brushing your teeth in slow motion.
Spin around five times and try to walk straight.
Recite your phone number using animal sounds.
Pretend you're a reporter interviewing the person on your right.
Do a short stand-up comedy routine for thirty seconds.
Make a sound effect for every step you take until your next turn.
Pretend you're a royal giving a speech to the kingdom.
Describe your favourite food without saying its name.
Do the chicken dance.
Pretend you're a tiny mouse looking for cheese.
Speak like a sports coach giving a pep talk.
Act like you're seeing the ocean for the first time.
Draw the person on your left using only circles.
Clap a rhythm and have everyone copy it.
Do an impression of a fashion designer judging outfits.
Act like a T-rex trying to make a bed.
Sing a song using only "la".
Show the last photo you took and explain it.
Pretend to be the narrator of everyone's lives for a minute.
Do your best impression of a smartphone voice assistant.
Walk around the room like you're in a fancy restaurant.
Tell the group a riddle.
Pretend to be a famous chef tasting the room's snacks.
Do your best ballet move.
Act like you're stuck in slow-moving traffic.
Hold your breath for fifteen seconds while smiling.
Pretend you're a cat that just knocked something off a table.
Do your best impression of a sports mascot.
Make up a cheer for the group.
Describe the room as if you were writing a mystery novel.
Pretend you're a lifeguard watching the room.
Do your best impression of a toddler's tantrum.
Pretend to be a news reporter in a hurricane.
Act out a sneeze in slow motion.
Make a fake advertisement for water.
Name five things that are blue in this room.
Pretend you're a famous explorer discovering the kitchen.
Do an impression of an old radio announcer.
Tell everyone what superpower they would have.
Hum a song with your nose plugged.
Pretend to be a flight attendant giving safety instructions.
Do your best karate moves in slow motion.
Act like a balloon slowly losing air.
Do an impression of a famous movie hero.
Pretend you're a farmer calling the cows home.
Do a dramatic death scene from a soap opera.
Pretend you're the world's worst magician.
Say a sentence where every word starts with the same letter.
Do a fashion show of what you're wearing.
Pretend to be a vending machine.
Make up a jingle for your favourite snack.
Act out an elevator ride with a stranger.
Do an impression of someone opening a gift they don't like.
Pretend to be a snowman melting.
Recite the days of the week in a dramatic voice.
Make bird sounds until someone guesses the bird.
Act out a slow-motion high five with the person on your left.
Pretend to be a very serious judge on a talent show.
Do an interpretive dance about your day.
Pretend you're a camera and take everyone's picture with sound effects.
Mime walking a very energetic dog.
Pretend you're a kid on the first day of school.
Talk about your favourite movie without saying its name.
Describe a colour to someone who has never seen it.
Pretend to be a security guard checking everyone's tickets.
Do your best moonwalk.
Do an impression of a famous athlete celebrating.
Pretend you're a cook flipping pancakes.
Act like a superhero who is afraid of mice.
Do a mini workout routine and make everyone join for ten seconds.
Pretend you're a statue that sneezes.
Tell a ghost story in thirty seconds.
Act out the lyrics of a song as the group sings it.
Pretend you're the host of a cooking competition.
Imitate the sound of rain using only your hands.
Pretend you're a robot running out of battery.
Do a dramatic reading of a grocery list.
Pretend to be a talk-show guest promoting a new book.
Mime being caught in a strong wind.
Do your best impression of a snake.
Pretend to be a famous pop star arriving at an airport.
Act out your favourite sport without equipment.
Pretend you're a lighthouse keeper on a stormy night.
Do an impression of a goat.
Say "unique New York" ten times fast.
Make up a short rhyme about pizza.
Pretend to be a waiter dropping a tray in slow motion.
Show the group how you'd dance at a wedding.
Pretend you're a referee and blow a pretend whistle at someone.
Draw a house without lifting your pen.
Act like a baby bird learning to fly.
Speak like a medieval knight until your next turn.
Pretend to be a tired parent at the end of a long day.
Act out making a cup of tea in thirty seconds.
Do your best impression of a cheerleader.
Pretend you're a famous scientist explaining gravity.
Do a commercial for the room you're in.
Make up a secret code word for the group.
Pretend you're a chef who forgot the recipe.
Pretend you're a librarian shushing the room.
Mime opening a stuck jar for twenty seconds.
Pretend you're a tour bus driver pointing out sights in the room.
Do your best impression of a happy goldfish.
Pretend you are a sports commentator describing someone drinking water.
Mime folding a giant invisible bedsheet.
Pretend to be a tiny robot exploring the room.
Act like you're a famous chef judging a glass of water.
Make up a weather report for the inside of the fridge.
Pretend to be a museum statue that blinks when no one looks.
Describe your shoes like they are a luxury car.
Do your best impression of a dramatic soap actor saying hello.
"""

private const val EN_PARTY_T = """
What's the weirdest thing you've ever done to impress someone?
Who in this room would you trust to plan your birthday party?
What's the most ridiculous thing you've bought online?
What's the longest you've gone without checking your phone?
What's your worst cooking disaster?
Have you ever walked into the wrong bathroom?
What's the most embarrassing song on your playlist?
Have you ever sent a text to the wrong person?
What's the craziest dare you've ever done?
What's your most awkward first-day-at-a-new-place story?
What's the silliest thing you've cried about?
Who here would be the worst roommate?
What's the most embarrassing thing your parents caught you doing?
What's the dumbest way you've ever hurt yourself?
Have you ever pretended to be sick to skip something?
What's the most childish thing you still do?
What's your guilty-pleasure TV show?
What's the weirdest thing you've eaten?
Have you ever fallen asleep somewhere embarrassing?
What's the worst haircut you've ever had?
Who in this group would win a reality TV show?
What's the strangest rumour you've heard about yourself?
What's your most embarrassing autocorrect fail?
What's the worst gift you've ever received?
Have you ever re-gifted something?
Who here would survive the longest without the internet?
What's the most awkward date you've been on?
Have you ever waved at someone who wasn't waving at you?
What's the funniest thing you've done while half-asleep?
What's the most embarrassing thing you've said to a teacher?
What's the most ridiculous lie you told as a kid?
What's the worst fashion trend you followed?
What's your most embarrassing social media post?
Have you ever laughed so hard you cried in public?
What's something you'd never do again, even for money?
Who in this room is the biggest drama queen or king?
What's the strangest place you've fallen asleep?
What's the weirdest habit you have?
Have you ever been caught talking to yourself?
What's the most embarrassing thing in your search history?
What's the worst thing you've done at a sleepover?
Who here would be the first to get lost on a trip?
What's the biggest food mess you've made in public?
What's your most embarrassing moment on stage?
Have you ever pretended to know someone you didn't?
What's the funniest thing you've seen at a wedding?
What's the worst job you've ever had?
What's something you did in school that you hope no one remembers?
Who in this room has the best dance moves?
What's the most useless thing you know a lot about?
What's the worst excuse you've given for being late?
Have you ever stalked a celebrity online for hours?
What's the most embarrassing thing that happened on a video call?
What's the craziest thing you've done at a party?
Who here would make the worst spy?
What's the weirdest thing you've done when you were bored?
Have you ever tripped in front of a crush?
What's your most cringe-worthy text?
What's the funniest misunderstanding you've had?
Which person here would be the best at keeping a secret?
What's the dumbest argument you've ever had?
What's the worst present you gave someone?
Have you ever pretended to laugh at a joke you didn't get?
What's the strangest thing you've done for free food?
What's your worst habit at the dinner table?
Who here is most likely to become a millionaire?
What's the most awkward thing you've said to a stranger?
What's the silliest thing you're afraid of?
Have you ever fallen asleep during a movie at the cinema?
What's the most embarrassing nickname you've had?
What's your most embarrassing childhood photo?
Who in this group texts the most?
What's the strangest thing you've done to avoid someone?
What's the most chaotic holiday you've had?
Have you ever broken something and blamed someone else?
What's the funniest thing that's happened to you in a taxi or ride-share?
What's something you're weirdly competitive about?
Who here would be the first to cry at a movie?
What's the worst thing you've done while driving or cycling?
Have you ever eaten food off the floor at a party?
What's your weirdest talent?
What's the most embarrassing way you've been woken up?
What's the craziest thing you've done on a holiday?
Who in this room would you swap wardrobes with?
What's the worst song you secretly love?
Have you ever been kicked out of a shop or restaurant?
What's the most dramatic exit you've ever made?
What's your go-to dance move when no one is watching?
Who here would be the worst at a game show?
What's the weirdest message you've ever received?
What's the most embarrassing thing you've done in a lift?
Have you ever pretended to be on the phone to avoid talking?
What's the funniest thing that happened in your class at school?
Who in this group is always late?
What's the strangest thing you've ever won?
What's your most embarrassing moment at the gym?
Have you ever got lost in your own neighbourhood?
What's the most embarrassing thing you've said in your sleep?
Which celebrity do people say you look like?
What's the worst thing you've ever cooked for someone?
Have you ever forgotten someone's name right after meeting them?
What's the most embarrassing thing that happened on a first date?
Who in this room would be the funniest stand-up comedian?
What's the oddest thing you've done to pass a test?
Have you ever accidentally liked an old photo while scrolling?
What's the most embarrassing thing you've worn to a party?
What's the funniest prank you've pulled?
What's the funniest prank someone pulled on you?
Who here would win in a staring contest?
What's the strangest compliment you've received?
What's your secret skill at parties?
Have you ever crashed a party?
What's your most embarrassing moment with a pet?
What's the weirdest thing in your fridge right now?
Who in this group would survive a horror movie?
What's the most embarrassing thing you've done for a photo?
Have you ever said "you too" when it made no sense?
What's the funniest thing you've overheard?
What's something you're secretly proud of that's a bit silly?
Who here would be the worst at keeping a plant alive?
What's the biggest lie you told to get out of trouble?
What's the most awkward silence you've experienced?
Have you ever pretended to read a book you never finished?
What's your worst holiday photo?
What's the most embarrassing thing you've posted and deleted?
Who in this room would be the best parent?
What's the funniest thing you've done to get attention?
Have you ever sung loudly in the car and been seen?
What's the craziest thing you've done for a friend?
What's your strangest food craving?
Who here gives the best advice?
What's the most embarrassing thing you've done at work?
Have you ever had a wardrobe malfunction in public?
What's the worst movie you've ever watched?
What's the most embarrassing thing you've done to impress a teacher?
Who in this group would go viral first?
What's your most embarrassing moment on public transport?
Have you ever been caught singing into a hairbrush?
What's the strangest thing you believed as a kid?
What's the most childish argument you've had recently?
Who here would be the worst at keeping their phone charged?
What's the worst thing you've done when you were hangry?
Have you ever made up a fake fact and people believed it?
What's your most embarrassing sports moment?
Who in this room would you call in an emergency?
What's the funniest typo you've ever made?
Have you ever got stuck somewhere embarrassing?
What's the most awkward family dinner you've had?
What's a secret talent no one here knows about?
Who in this group is the biggest foodie?
What's the most embarrassing thing you've done in front of your boss?
Have you ever forgotten your own birthday or anniversary?
What's the weirdest thing you've collected?
Who here would be the best detective?
What's the worst advice you've ever followed?
Have you ever laughed at a funeral or serious moment?
What's the funniest thing you've done while trying to be cool?
Who in this room would make the best talk show host?
What's your worst online shopping fail?
Have you ever eaten something that fell in the bin?
What's the most embarrassing thing that happened on your birthday?
Who here would be the worst at a camping trip?
What's the craziest rumour you started?
Have you ever got caught lying about your age?
What's your strangest superstition?
Who in this group would be the best pop star?
What's the funniest way you've been rejected?
Have you ever pretended to understand a language?
What's the dumbest thing you've done for a bet?
Who here takes the most selfies?
What's your worst memory from a school dance?
Have you ever got the giggles at the worst time?
What's the weirdest thing you've Googled this week?
Who in this room would be the worst driver?
What's your most embarrassing karaoke moment?
Have you ever walked into a glass door?
What's the most expensive thing you've broken?
Who here would last longest without sugar?
What's the strangest job you'd secretly enjoy?
Have you ever hidden from a neighbour?
What's the worst thing you've said by accident?
Who in this group would be the best actor?
What's the most ridiculous thing you've cried at in a movie?
Have you ever been caught making faces in a mirror?
What's your most embarrassing fan moment?
Who here would make the best influencer?
What's the funniest thing that happened during an exam?
Have you ever got in trouble for laughing?
What's the most awkward hug you've had?
Who in this room is secretly the funniest?
What's the oddest thing you've done on a dare?
Have you ever finished a whole pizza alone?
What's your most embarrassing voice message?
Who here would be the worst at hide and seek?
What's the strangest food combination you've invented?
Have you ever been caught dancing alone?
What's the worst present you pretended to love?
Who in this group would you want on your quiz team?
What's your most embarrassing moment at a family event?
Have you ever forgotten where you parked for hours?
What's the most ridiculous fight you've had with a sibling?
Who here would be the best chef?
What's the funniest thing you've done in a dressing room?
Have you ever got stuck in a piece of clothing?
What's the silliest reason you've been grounded?
Who in this room laughs the loudest?
What's the most embarrassing thing you've done in front of a crush?
Have you ever pretended to be someone else online?
What's the craziest thing you'd do for free concert tickets?
Who here would be the worst at a cooking show?
What's the strangest thing you've done at 3am?
Have you ever been caught pretending to work?
What's the most embarrassing thing you've done on holiday?
Who in this group would be the best at reality TV drama?
What's your most awkward moment with a waiter?
Have you ever left a party without saying goodbye?
What's the funniest thing you've seen someone do drunk on sugar?
Who here would be the last to get out of bed?
What's the weirdest place you've used your phone?
Have you ever been the only one dressed up at a party?
What's the most memorable party you've been to?
Who in this room would be the best party planner?
What's the most out-of-character thing you've ever done?
Who in this room would you want as your partner in a heist movie?
What's the cringiest thing you did to look cool as a teenager?
What's the strangest thing you've done during a power cut?
Who here would most likely forget their own surprise party?
What's the most embarrassing sound you've made in public?
What's a song you know all the lyrics to but would never admit?
What's the craziest excuse you've heard from a friend?
Who in this group would be the first to quit a reality show?
What's the funniest thing you've ever posted by mistake?
What's the strangest compliment you've given someone?
Have you ever practiced a conversation in the mirror?
What's the most awkward group photo you're in?
Who in this room would you pick to share a tiny tent with?
What's the weirdest thing you've done to stay awake?
Have you ever been caught pretending to be asleep?
What's the most ridiculous thing you've argued about online?
Who here would win a burping contest?
What's the most embarrassing gift you've bought someone?
Have you ever answered a question you didn't hear properly?
What's the worst thing you've said while meeting someone's parents?
Who in this group is most likely to start a dance party?
What's the funniest way you've injured yourself at a party?
Have you ever hidden food so you didn't have to share?
What's the most chaotic group chat you're in?
Who here would most likely get lost in a shopping mall?
What's your weirdest phone wallpaper ever?
Have you ever sung the wrong lyrics confidently in public?
"""

private const val EN_PARTY_D = """
Do your best impression of someone in this room and let them guess.
Dance like nobody is watching for thirty seconds.
Let the group choose a song for you to lip-sync.
Speak in a fake British accent until your next turn.
Do a dramatic soap-opera slap reaction, no contact.
Put on a sock puppet show using your hand.
Let someone draw a moustache on you with a washable marker.
Talk like a game show host until your next turn.
Do your best runway walk to a song the group picks.
Pretend to propose to the person on your left.
Sing a song in a completely random genre.
Text a friend "I know what you did" and show the reply later.
Let the group give you a new hairstyle.
Do an interpretive dance of your last relationship or crush.
Act like a celebrity on a red carpet interview.
Call a friend and talk only in song lyrics.
Pretend to be a fitness influencer filming a workout.
Do your best impression of a cat video.
Laugh out loud for thirty seconds without stopping.
Do the macarena from start to finish.
Pretend you're a DJ hyping the crowd.
Show the group your best TikTok dance.
Do a slow-motion action-movie fight scene.
Try to make everyone laugh in thirty seconds.
Let the person on your right post a status for you (keep it kind).
Wear your socks on your hands until your next turn.
Do your best beatbox for twenty seconds.
Imitate a famous singer until someone guesses who.
Do an infomercial for something on the table.
Talk without closing your mouth for thirty seconds.
Act out a breakup with a slice of pizza.
Pretend to be a waiter at a fancy restaurant and serve someone a drink.
Do a backwards walk around the room while singing.
Show the last five emojis you used and explain them.
Pretend you're in a music video for one minute.
Do a dramatic poetry reading of a fast-food menu.
Take a selfie with the funniest face possible and show the group.
Hold a dance-off with the person on your left.
Wear a funny hat or headband until your next turn.
Do your best victory dance.
Pretend you're a statue and let others pose you.
Do a fake cooking show with whatever is nearby.
Let the group pick a ringtone for you for the rest of the day.
Do an impression of a famous movie villain monologue.
Act like you're auditioning for a musical.
Pretend to be a sports commentator for the next turn.
Do a spin and a bow after every sentence until your next turn.
Make up a rap about the person on your right.
Pretend you're a rock star smashing an invisible guitar.
Let someone tickle you for ten seconds without laughing.
Do a dramatic movie trailer voice for the party.
Say everything in slow motion until your next turn.
Do an impression of your favourite YouTuber.
Act like a chicken until someone laughs.
Pretend to be the life coach of the person on your left.
Do your best Bollywood dance move.
Show the group your best dance from a wedding.
Pretend to cry over spilled milk, literally.
Do your best air guitar solo.
Read a random text from your phone in a dramatic voice.
Act out your favourite meme.
Pretend you're a weather presenter during a storm.
Do your best impression of a rap battle.
Let the group style your outfit using items in the room.
Talk in an Australian accent for the next round.
Do your best impression of a strict sports coach.
Sing your favourite song as an opera.
Walk like a penguin to the kitchen and back.
Do a cheerleading routine for the next player.
Pretend to be a reality TV star in a confession booth.
Do the worm, or a very confident attempt.
Act like you're in a horror movie for thirty seconds.
Imitate three different laughs.
Do a celebrity impression chosen by the group.
Give a dramatic speech about why you love snacks.
Do a karaoke performance without music.
Act out a scene from a famous romance movie.
Pretend you're a magician pulling a rabbit out of a hat.
Take a group selfie with everyone making silly faces.
Pretend you're a talk show host interviewing the player on your left.
Do a moonwalk across the room.
Let someone else control your hand gestures while you talk.
Do a fake infomercial for your favourite app.
Pretend to be stuck in a speech-to-text error.
Sing your answers to every question until your next turn.
Act like a toddler who just had too much sugar.
Make a dramatic entrance into the room.
Give a TED talk about something completely silly.
Act out what you'd do if you met your idol.
Do your best breakdance move.
Imitate a famous dance trend.
Pretend you're a pirate captain giving orders.
Do a slow-motion replay of someone's last move.
Do your best impression of a famous athlete.
Act like you're on a cooking competition with ten seconds left.
Let the group choose a word you must say every time you speak.
Talk like a robot for the next two minutes.
Dance to the next song that plays, whatever it is.
Pretend you're a famous fashion designer critiquing the group.
Make a toast to the group in your best posh voice.
Do your best impression of a cartoon character for one minute.
Act out the plot of a movie and let others guess.
Walk around the room like a crab.
Do your best impression of a sports mascot.
Hold a pretend press conference about your love life.
Make up and perform a commercial jingle.
Pretend to be a flight attendant during turbulence.
Do your best impression of a dramatic sitcom character.
Act like you're seeing snow for the first time.
Show your best party trick.
Pretend you're in a slow-motion slow-dance alone.
Do a comedy roast of yourself for thirty seconds.
Sing a lullaby to the person on your right.
Do your best impression of a game character.
Pretend to be a security guard and check everyone's IDs.
Act out falling in love at first sight.
Do an impression of someone stepping on a LEGO brick.
Rap the alphabet.
Pretend you are a famous chef tasting someone's cooking.
Do the robot while saying something sweet.
Let the group choose a pose for your next profile picture.
Pretend to be an over-excited game show contestant.
Act out a hilarious slip on a banana peel.
Do your best impression of a radio DJ.
Make a paper hat and wear it proudly.
Hug the person on your left like you haven't seen them in years.
Do an impression of a stand-up comedian telling a bad joke.
Pretend you're a cat who just saw a cucumber.
Show the group your worst dance move.
Speak only in questions for one minute.
Do your best impression of a famous politician's speech style, no names.
Pretend to be a news anchor reporting on this party.
Give the person on your right a pep talk.
Act out a dramatic proposal to a houseplant.
Do the YMCA.
Pretend you're stuck in a giant bubble.
Do a dramatic rendition of a commercial jingle.
Imitate a famous movie scream.
Let the group pick your next three emojis to send someone.
Do a fake yoga class for the group.
Pretend to be a fortune teller and predict someone's future.
Make up a dance for the word "party".
Do a celebrity walk into the room.
Pretend you're filming a cooking tutorial for your favourite snack.
Talk in third person until your next turn.
Do an impression of someone who just won the lottery.
Sing "happy birthday" in three different styles.
Pretend you're a statue in a park and pigeons land on you.
Act out a fight scene in slow motion with an invisible enemy.
Pretend to be a waiter with very bad memory.
Show the group your strangest talent.
Act like you're on a rollercoaster.
Make a speech as if you just won an Oscar.
Do your best impression of a famous animated character's voice.
Pretend you're a lifeguard rescuing someone.
Act out a commercial for socks.
Do a dramatic re-enactment of your morning.
Dance with an imaginary partner.
Pretend you're a robot learning to dance.
Act like a zombie trying to eat cake.
Do an impression of the funniest person you know.
Let the group choose a silly walk for you until your next turn.
Pretend to be a rock star in a hotel room.
Do a dance battle with the person opposite you.
Act like a ballet dancer with hiccups.
Do your best car alarm impression.
Say a sentence while laughing the whole time.
Do your best impression of a monkey.
Pretend you're on a game show and lose dramatically.
Give a tour of your phone's camera roll highlights.
Pretend you're an influencer unboxing an empty box.
Sing a love song to an object in the room.
Act out the last movie you watched in thirty seconds.
Do an impression of a famous dance move from a music video.
Pretend you're a referee and give someone a yellow card.
Do a celebratory dance after every turn until your next turn.
Pretend to be your favourite superhero ordering coffee.
Make up a cheer for the person on your right.
Act out a chase scene from an action movie.
Do your best impression of a TV chef shouting.
Pretend you're a statue that occasionally winks.
Narrate the room like a wildlife documentary.
Do a fake auction selling something on the table.
Act out a sad violin solo.
Pretend you're in a dance class and nobody follows you.
Do your best impression of an excited sports fan.
Pretend you're a pop star singing to your biggest fan.
Do the conga around the room.
Act like you're in a silent film.
Make a dramatic weather forecast for the party.
Let someone choose an accent for you until your next turn.
Pretend to be a spy sneaking across the room.
Do a group hug with everyone.
Show your best smile, your worst smile and your fake smile.
Pretend to be a waiter singing every order.
Act out an awkward elevator dance.
Do an impression of a famous comedian's laugh.
Pretend you are a squirrel who drank coffee.
Make a funny video message for someone not here.
Act out a scene from a cartoon.
Talk only in whispers and gestures for one minute.
Do a shoulder dance to the beat of a song.
Pretend you're a famous painter and paint the group.
Act like you're giving a wedding speech for the person on your left.
Do an impression of someone stuck in quicksand.
Sing a song about the snacks.
Pretend to be a reporter finding out who ate the last cookie.
Do a dramatic slow walk like in a movie.
Act like a kid opening birthday presents.
Do your best surprised face for ten seconds.
Pretend you're a very bad stand-up comedian.
Do a disco move from the seventies.
Let the group rename you for the rest of the game.
Pretend to be a hairdresser and style someone's hair.
Act out an argument between your left hand and right hand.
Do an impression of the group's most famous catchphrase.
Pretend to be a movie director shouting "action".
Do a dance that represents your mood right now.
Make a dramatic toast to the last person who spoke.
Act like a puppet on strings.
Do your best impression of a spinning bottle until you stop on someone.
Let the group choose a dance style for the next thirty seconds.
Talk like a sports announcer while the next person takes their turn.
Act out a cooking fail without saying a word.
Pretend you're a pop star signing autographs for everyone.
Sing your favourite song with your mouth full of air.
Do an impression of a very dramatic sneeze competition.
Pretend you're at a fashion show wearing a bin bag.
Narrate everything you do in third person until your next turn.
Do a dramatic reading of the ingredients on a snack packet.
Lead the group in a ten-second conga line.
Act like a famous dancer who has forgotten all the moves.
Pretend you're a mime stuck in a very small elevator.
Give the group a one-minute stand-up set about your morning.
Do a fake infomercial for a spoon.
Pretend to be an over-the-top host at a birthday party.
Act out your most dramatic reaction to a plot twist.
Do your best boy-band or girl-group pose.
Pretend you're a robot DJ and pick a song for everyone.
Lip-sync a song chosen by the person on your left.
Do your best impression of a cat walking on a keyboard.
Pretend you just won a dance competition and give a speech.
Act like a celebrity hiding from paparazzi.
Do a slow-motion victory lap around the room.
Pretend you're a coach hyping the person on your right before a big game.
Do your best fashion runway turn and pose.
Pretend you're a game show host revealing a grand prize.
Do a dramatic farewell speech before going to the kitchen.
"""

private const val EN_SPICY_T = """
What's the biggest secret you've kept from your best friend?
Who was the last person you stalked on social media?
What's the most jealous you've ever been?
Have you ever had a crush on a teacher?
What's the worst thing you've said about someone behind their back?
Who in this room would you go on a date with?
What's the most romantic thing you've ever done?
What's your biggest dating red flag?
Have you ever ghosted someone? Why?
What's the most awkward kiss you've had?
Have you ever lied to get out of a date?
What's the boldest move you've made on a crush?
Who was your celebrity crush growing up?
What's the longest you've had a crush on someone without telling them?
Have you ever read someone else's messages without permission?
What's a secret you've never told your parents?
What's the meanest thing you've done to a friend?
Have you ever cheated in a game or exam?
Who here do you think is the most attractive?
What's the most embarrassing thing you've done while flirting?
Have you ever been dumped over text?
What's your biggest regret in love?
What's the worst lie you've told a partner?
Have you ever had feelings for two people at once?
What's the most daring thing you've ever done?
Who would you call if you needed an alibi?
What's your most toxic trait?
Have you ever pretended to like someone to make another person jealous?
What's something you've done that you'd never put on social media?
Who in this room would you trust with your phone unlocked?
What's the worst breakup you've had?
Have you ever been caught lying by someone in this room?
What's a rumour about you that you never corrected?
What's the most impulsive thing you've done?
Have you ever had a crush on someone in this room?
What's your biggest insecurity?
What's the most expensive mistake you've made?
Have you ever snooped through someone's stuff?
What's something you're secretly jealous of?
What's the craziest thing you've done for love?
Have you ever said "I love you" without meaning it?
What's a text you regret sending?
Who here do you think talks about others the most?
What's your most controversial opinion?
Have you ever been in love with a best friend?
What's the last lie you told?
What's something you'd hate for your ex to know?
Have you ever broken someone's heart?
What's the most rebellious thing you did as a teenager?
Who was your first kiss and how was it?
What's a habit you hide from people?
Have you ever made a fake account?
What's your biggest fear in a relationship?
What's something you pretend to like but secretly hate?
Have you ever been caught checking someone out?
What's the most embarrassing message you've sent to a crush?
Who in this room would you not want to be stuck on an island with?
What's the strangest thing you've done out of jealousy?
Have you ever forgiven someone you shouldn't have?
What's a decision you made that you still think about?
What's the most trouble you got into at school?
Have you ever lied about your age online?
What's your worst habit when you're in a relationship?
Who in this room knows the most secrets about you?
What's something you've never admitted to anyone here?
Have you ever had a secret relationship?
What's the most awkward situation with an ex?
What's a lie you told that got out of hand?
Have you ever deleted a message before someone could read it?
What's your love language?
What's the pettiest thing you've done after a breakup?
Who here would you pick as your wingman or wingwoman?
What's the most daring outfit you've ever worn?
Have you ever had a crush on a friend's sibling?
What's the most embarrassing thing on your phone right now?
What's your biggest pet peeve about the people here?
Have you ever kept a gift from an ex?
What's the most you've spent on a date?
Who was your worst date ever?
What's the most attractive quality in a person?
Have you ever made the first move?
What's the biggest risk you've taken for someone?
What's something people always get wrong about you?
Have you ever lied in this game?
What's a secret talent you use to charm people?
Who in this room gives off main-character energy?
What's the longest you've gone without talking to your best friend after a fight?
Have you ever gone through a friend's phone?
What's the weirdest thing that attracts you to someone?
What's your guilty pleasure that you'd be embarrassed to admit?
Have you ever had a dream about someone in this room?
What's the most embarrassing thing an ex knows about you?
What's a time you were completely wrong but refused to admit it?
Who here would be the worst person to date?
Have you ever said something mean and regretted it instantly?
What's your type, honestly?
What's the cheesiest pickup line you've used?
Have you ever been friend-zoned?
What's a secret you think someone here is hiding?
What's the worst thing you've done when angry?
Have you ever sent a screenshot of a chat to someone else?
Who was the last person you texted at 2am?
What's a compliment you've never forgotten?
Have you ever had a crush on someone much older or younger?
What's the most attention-seeking thing you've done?
What's your biggest flirting fail?
Have you ever lied about having plans?
Who here do you think has the most interesting love life?
What's something you're scared people will find out?
Have you ever taken credit for someone else's work?
What's the most romantic place you'd love to be kissed?
What's the most embarrassing thing you've done to get someone's attention?
Have you ever been caught in a lie by your parents?
What's a moment you felt truly jealous of a friend?
Who in this room would you trust with your deepest secret?
What's a secret you've kept for over five years?
Have you ever had a crush you were embarrassed about?
What's your worst online argument?
What's a mistake you keep repeating?
Have you ever broken a promise to someone here?
What's something you'd do if no one would ever find out?
Who is the last person you thought about before sleeping?
What's the most private thing you've told a stranger?
Have you ever given someone a fake phone number?
What's the boldest text you've received?
What's a relationship deal-breaker for you?
Have you ever liked someone's photo by accident while stalking?
Who in this room would you call for relationship advice?
What's a white lie you tell often?
What's the most embarrassing reason you've cried?
What's your worst habit that you hide from your partner or family?
Have you ever eavesdropped on a private conversation?
Who here do you think will get married first?
What's the most dramatic thing you've done in a relationship?
Have you ever pretended to be busy to avoid someone?
What's something you've never forgiven yourself for?
What's the wildest thing on your bucket list?
Have you ever tried to make an ex jealous?
Who in this room would you like to swap lives with for a day?
What's the worst thing you've said to a sibling?
Have you ever been in a love triangle?
What's your most embarrassing crush from the past?
What's a secret skill you'd never put on a CV?
Have you ever faked an illness to get attention?
Who here would you trust to set you up on a blind date?
What's the riskiest message you've ever sent?
Have you ever hidden something from your best friend?
What's a confession you've been waiting to make?
What's the worst thing you've done to a sibling and never admitted?
Have you ever lied about liking a gift?
Who in this room do you think is the biggest flirt?
What's your most unpopular dating opinion?
Have you ever been caught talking about someone who was right behind you?
What's the most romantic message you've ever written?
What's something you did that you'd be shocked to see someone else do?
Have you ever had a secret crush on a coworker or classmate?
Who would you text first if you had big news?
What's your biggest relationship mistake?
Have you ever unfollowed someone out of jealousy?
What's the craziest excuse you've made to see someone?
What's a question you hope nobody asks you tonight?
Have you ever had a fight over something completely silly?
Who here do you think hides their feelings the most?
What's something you've done that made you feel guilty for weeks?
Have you ever stood someone up?
What's the most personal thing in your notes app?
What's the last thing you lied to your parents about?
Have you ever had feelings for someone you shouldn't?
Who in this room do you think would be the best kisser?
What's the most awkward thing you've overheard about yourself?
Have you ever been jealous of someone in this room?
What's the most dramatic text you've sent?
What's your biggest secret obsession?
Have you ever pretended to be someone else to impress someone?
Who here would you choose to keep your darkest secret?
What's your most shameless move to get noticed?
Have you ever posted something just to make someone jealous?
What's something you'd never tell your best friend?
What's the first thing you notice about someone you like?
Have you ever had a crush that lasted years?
Who in this room do you know the least, honestly?
What's the most daring photo you've taken?
Have you ever been caught snooping?
What's a promise you broke and never told anyone?
What's the meanest thought you've had today?
Have you ever wanted to text an ex but didn't?
Who here would you never lend money to?
What's something you've lied about on social media?
Have you ever kept a secret that wasn't yours to keep?
What's a relationship you regret not starting?
What's your biggest guilty secret from school?
Have you ever said something behind someone's back in this room?
Who in this group is most likely to have a secret admirer?
What's the most rebellious thing you've done recently?
Have you ever blamed a friend for something you did?
What's your most embarrassing emotional moment?
What's a truth you think nobody here is ready for?
Have you ever had a crush on someone's voice alone?
Who here do you think would survive a breakup the best?
What's one thing you'd change about your love life?
Have you ever had a crush on a stranger on public transport?
What's the last thing that made you really jealous?
What's the most daring thing you've done on a night out?
Who in this room would you call to help you bury a secret?
What's the longest grudge you've ever held?
Have you ever had a crush on your best friend's sibling?
What's the most romantic thing someone has done for you?
What's something about your past you're still embarrassed by?
Have you ever kept a love letter you never sent?
Who in this room do you think is the most mysterious?
What's the cheekiest thing you've done at work or school?
Have you ever been caught sneaking out?
What's the most awkward thing that happened when meeting a date's friends?
What's a decision you hope nobody here finds out about?
Who would you trust to choose your next partner?
Have you ever flirted to get something for free?
What's your biggest "what if" in love?
What's the most intense argument you've had with someone here?
Have you ever posted something and deleted it seconds later?
What's a secret crush you had at school?
Who in this room do you secretly admire the most?
Have you ever made a bet about someone's love life?
What's the worst thing you've done to get out of a relationship?
What's a message you typed but never sent?
Have you ever been jealous of a friend's relationship?
What's the bravest confession you've ever made?
Who here do you think would keep a secret the longest?
Have you ever lied to a friend to protect their feelings?
What's the most surprising thing about your love life?
What's something you've done that you'd never let your parents see?
Have you ever said yes to a date just to be polite?
Who in this room would you want to impress the most?
What's your biggest regret from last year?
Have you ever had a crush on a celebrity's character instead of the actor?
What's the boldest thing you've said to someone you liked?
What's a rule you broke and never got caught?
Have you ever pretended not to see a message?
Who here do you think has the biggest secret?
What's the most rebellious thing you've done for love?
Have you ever had a crush on someone you met only once?
What's the most awkward thing you've done in front of a crush's family?
What's a moment you were glad nobody saw?
Have you ever saved a photo of someone you liked?
Who in this room would you call when you need a brutally honest opinion?
What's a secret you'd only tell after midnight?
Have you ever practised a confession and never said it?
What's the most dramatic thing you've done to get attention?
"""

private const val EN_SPICY_D = """
Show the group your most recent search history (one screen only).
Read your last three sent messages out loud.
Let the person on your right send a meme to anyone in your chats.
Show the last photo you deleted, if it's still in your trash.
Call a friend and tell them you have a big secret, then hang up.
Let the group look through your playlists for one minute.
Post an old childhood photo on your story.
Send a voice note to your best friend singing their name.
Let the group write your next social media caption.
Show your most recent screenshot and explain it.
Reveal the last three people you texted.
Show the group your oldest photo with a friend.
Text your crush a random emoji and show the reply.
Let someone change your profile picture for one hour.
Read the last message you received in a dramatic voice.
Show the group the most embarrassing selfie on your phone.
Tell the person on your left one honest first impression you had of them.
Let the group choose someone for you to compliment by text.
Do your best flirty wink at everyone in the room.
Let the group read your notes app for thirty seconds.
Send "I miss you" to the fifth person in your contacts.
Show your screen time for today.
Let the group pick a song for your status.
Reveal your phone's lock-screen notifications right now.
Give a thirty-second honest review of the person on your right.
Text your mum "I have something to tell you" and explain later.
Show the last thing you liked on social media.
Do a serenade for the person opposite you.
Let someone else type one message to anyone from your phone (you approve it before sending).
Show the group your most-used emoji and the last chat you used it in.
Give the person on your left a dramatic love confession, acting only.
Reveal who you'd call first if you won the lottery.
Show the group the oldest message in your inbox.
Let the group pick a new contact name for your best friend.
Recreate your most recent selfie pose.
Tell everyone which person here you'd swap phones with for a day.
Do your best attempt at a sultry movie-star walk.
Reveal your most played song this month.
Call a random contact and ask for their favourite pizza topping.
Let the group read your bio aloud and roast it.
Text "guess what" to someone and don't reply for ten minutes.
Show your last online order.
Say something nice about every person in the room, one sentence each.
Do a dramatic slow-motion hair flip.
Let the group look at your recently used apps.
Tell an embarrassing story you've never told anyone here.
Show your camera roll from exactly one year ago.
Write a short love letter to your phone and read it out loud.
Reveal the funniest chat in your phone.
Let someone post a story on your account for ten minutes (you approve it).
Give a speech about why you're the best catch in the room.
Show the group your most liked post ever.
Let the person on your right choose your lock screen for a day.
Tell the group your worst date story in one minute.
Send a heart to the third person in your recent chats.
Reveal your most embarrassing autocorrect in your messages.
Read the first message ever sent in your group chat.
Let the group pick a funny voice for your next voice note.
Show your last search on a shopping app.
Do your best impression of yourself trying to flirt.
Reveal the last person who called you.
Give someone in the room a heartfelt compliment while holding eye contact.
Let the group rate your last three selfies.
Show the group your most cringe old social media post.
Send a voice note saying "I've been thinking about you" to someone the group picks (from close friends or family).
Tell the person on your left the moment you liked them the most.
Show your phone's wallpaper and explain why you chose it.
Reveal a secret about yourself the group doesn't know.
Text your sibling or cousin a compliment out of nowhere.
Let the group choose an emoji you must add to every message today.
Show your last saved post or bookmarked video.
Describe your ideal date in detail in thirty seconds.
Reveal your most recent dream about someone you know.
Let someone read your last WhatsApp status update aloud.
Act out your most awkward romantic moment.
Show the group which chat you have pinned and why.
Let the group decide a dare for you to post on your story.
Send a funny GIF to the last person who texted you.
Tell the group your honest opinion of your own dance moves.
Show your most recent voice note (or play the first five seconds).
Reveal which person in this room you text the most.
Give a dramatic reading of your own bio.
Let the group look at your photo gallery for ten seconds.
Text a friend "Can I tell you something?" and see their reply.
Show the group the last video you recorded.
Admit something you've lied about tonight.
Let someone choose a song for you to dedicate to someone in the room.
Reveal the app you spend the most time on.
Do a slow dance with an imaginary partner while everyone hums.
Read a random message from your sent folder.
Let the group give you a nickname that you must use all night.
Tell the person on your right what you'd change about them, kindly.
Reveal your phone's most embarrassing typo.
Show the group your oldest saved contact.
Send a cheesy pickup line to a friend and show the reply.
Let the group read your last comment on social media.
Do your best impression of someone in this room flirting.
Reveal which ex you still follow online.
Show the group your browser tabs right now.
Tell everyone the weirdest compliment you've received.
Let someone write a message for you to send to your best friend (you approve it).
Reveal the first thing you'd do if you swapped bodies with someone here.
Show a photo you would never post.
Give the group a two-minute summary of your love life.
Let someone go through your music app's recent plays.
Text your mum or dad "thank you for everything" right now.
Do your most confident catwalk to the door and back.
Reveal your favourite person in the room right now.
Let the group decide which friend you must call and say "I love you".
Show the group your highest score in a mobile game.
Make a heartfelt toast to someone you've never toasted before.
Let the group pick one photo to set as your profile for an hour.
Tell the group the last thing you Googled about relationships.
Act out how you'd ask someone out.
Show the group your last five liked videos.
Reveal your guilty-pleasure celebrity crush.
Send a voice note with your best evil laugh to a friend.
Let someone look through your emojis and pick your new favourite.
Tell the group your most embarrassing phone habit.
Show the last meme you saved.
Dedicate a song to someone in the room and explain why.
Reveal the strangest thing you've ever said in a text.
Let the group decide your mood status for the rest of the night.
Do your best dramatic reading of a romance novel line you make up.
Show the last photo someone sent you.
Tell the group who you think likes someone here.
Let someone pick a random chat for you to send "hey stranger".
Reveal a crush you've never mentioned.
Show your most-viewed video.
Give a thirty-second roast of yourself.
Let the person on your left text your best friend from your phone (you approve it).
Tell the group your worst habit honestly.
Show your last ten emojis used.
Reveal the most embarrassing nickname a family member calls you.
Make up a love song for the person to your right and sing it.
Show the group the oldest selfie on your phone.
Tell the room your biggest turn-off.
Let the group choose a filter for your next selfie and post it.
Reveal the most awkward message you've sent to a teacher or boss.
Do a dramatic confession of a fake crime.
Send "you're my favourite" to the second person in your chats.
Let someone review your last three outfits in photos.
Tell the group what you'd say to your ex in one sentence.
Show your saved addresses on a map app (just the names).
Reveal the oldest app on your phone.
Give a sincere compliment to the person you know least here.
Let someone choose an old photo for you to send to its subject.
Read your last diary or notes entry out loud (or make one up dramatically).
Show the group the funniest group chat name you're in.
Reveal one thing you've never told your best friend.
Let the group choose a song you must sing into your phone and send to a friend.
Tell everyone your most recent embarrassing moment.
Show the most recent call log entry and explain who it was.
Reveal the oldest unread message in your inbox.
Act like you're on a dating show introducing yourself.
Let the group ask you three yes-or-no questions; you must answer honestly.
Show the group your phone's battery health and explain why it's like that.
Tell the person on your right your favourite thing about their style.
Reveal the funniest voice note in your phone.
Send a random "good luck" to someone with no context.
Let someone change your ringtone for the rest of the night.
Show the last thing you copied to your clipboard.
Tell the group who your most recent crush was, even if it was years ago.
Let the group read the last message from your mum.
Reveal your secret talent and show it.
Do your best romantic movie line to the person opposite.
Show your three most recent emojis reactions.
Text "I owe you one" to the last person who helped you.
Tell the group one thing you've lied about to someone here.
Let someone else pick your next story post.
Reveal which friend you'd call at 3am.
Show your most edited photo.
Give a dramatic monologue about your phone battery.
Let someone see your recent purchases list.
Tell the group your most embarrassing music phase.
Reveal the last time you cried and why.
Let the group decide one thing you must post this week.
Show your most recent notification.
Admit the last time you stalked someone online.
Let the person on your right ask you any question; answer honestly.
Tell the room which celebrity you'd marry.
Show your phone's hidden album count, without opening it.
Reveal the funniest thing your parents have texted you.
Give the person on your left a heartfelt thank-you.
Let the group pick a new name for you in your best friend's phone.
Let the group read your last search on a video app.
Send a "thinking of you" text to someone the group chooses from your close friends.
Show the group the funniest photo of yourself you've never posted.
Read your oldest saved note out loud.
Let the person on your left choose a new profile bio for one hour.
Show the most recent sticker you received and who sent it.
Reveal the last song you listened to and explain the mood.
Compliment each player's best feature in under thirty seconds.
Let someone pick an old chat for you to send "remember this?".
Show the group your most liked comment.
Tell the person opposite one thing that surprised you about them.
Reveal a secret nickname you have for someone here.
Let the group read your last three notifications.
Send your best friend a voice note in a dramatic movie voice.
Show the group your weirdest saved video.
Let someone else choose a story highlight cover for you.
Reveal your most recent online purchase you regret.
Perform a thirty-second serenade to someone the group chooses.
Let someone scroll your gallery and pick one photo to show.
Text a family member "I need to tell you something funny later".
Show the group your most recent sent photo.
Admit the last thing you lied about in a text.
Let the group pick one chat you must mute for the rest of the night.
Reveal the most embarrassing thing in your notes app.
Give a dramatic monologue confessing your love for pizza.
Let the person on your right send one emoji to your best friend.
Show your top three most-used apps.
Text a friend "you were right" and see their reaction.
Reveal your most played song of all time.
Let the group ask you one question you must answer honestly.
Show your phone's last missed call and explain it.
Do your most flirtatious dance move for ten seconds.
Let the group choose a dramatic caption for your next post.
Reveal the oldest selfie on your social media.
Read the most recent compliment you received in a text.
Let someone set an alarm on your phone with a funny label.
Show the group your last saved recipe or video.
Send a GIF to a friend that perfectly describes tonight.
Reveal which group chat you'd leave first.
Let the group decide your next contact photo for someone.
Tell the group the most romantic thing you've ever planned.
Show the group your best "candid" photo.
Reveal your secret comfort video.
Let someone choose a person you must send a kind message to.
Show the group the last voice message you sent.
Reveal the strangest app on your phone.
Let the person on your left choose your phone wallpaper for a day.
Tell the group a story about your most awkward text exchange.
Show the oldest photo of your best friend on your phone.
Reveal which person in the room you'd call for an adventure.
Let the group read your last outgoing email subject line.
Text your best friend a random fact and wait for the reply.
Show the group your most recent "screenshot of a screenshot".
Reveal the funniest name in your contacts list.
Let someone play the first song in your recently played list.
Admit one thing you've judged someone here for, kindly.
Show the group a photo that makes you cringe.
Reveal the most adventurous plan you've never told anyone.
Let the group pick a word you can't say for the next round.
Send a funny selfie to a friend with the caption "missing you".
"""

private const val EN_COUPLES_T = """
What was the exact moment you knew you liked me?
What do you remember about our first conversation?
What's your favourite photo of us?
What's one thing I do that always makes you smile?
What's the best date we've ever been on?
What's a song that makes you think of me?
What's something you've always wanted to tell me?
What's your favourite physical feature of mine?
What's the most romantic thing I've done for you?
What's one thing you'd like us to do more often?
What's a place you'd love us to travel to together?
What's your favourite memory of us laughing together?
What's the cutest habit I have?
When did you first say "I love you" in your head?
What's a small gesture of mine that means a lot to you?
What's our funniest argument?
What do you think we do better than most couples?
What's a dream you have for our future?
What's your favourite thing to do together on a lazy day?
What was your first impression of my family or friends?
What's a movie that reminds you of us?
What's the most thoughtful gift I've given you?
How would you describe our relationship in three words?
What's a habit of mine you've picked up?
What's the hardest thing we've been through together?
What did you think the first time we held hands?
What's your favourite nickname for me?
What's something new you'd like to try together?
What's one way I could make you feel more loved?
What's your favourite way to spend an evening with me?
What's the most surprising thing you've learned about me?
What do you think our song is?
What's your favourite memory from our first year together?
What's one thing you admire about me?
What's a moment you felt really proud of me?
What do you love most about our conversations?
What would our perfect weekend away look like?
What's a tradition you'd like us to start?
What's one thing that always makes you miss me?
What's a quality of mine you hope never changes?
What was the first thing you noticed about me?
What's your favourite meal we've shared?
What's something you've never told me about our first date?
What's a fear you have about our future?
How do you like to be comforted when you're sad?
What's your favourite way for me to say I love you?
What's the silliest thing we've done together?
What's one word you'd use to describe me?
What do you think I was like as a kid?
What's a memory with me you'd like to relive?
What do you think is our biggest strength as a couple?
What's something I do that you find attractive?
What's a goal you'd like us to achieve together this year?
What's your favourite outfit I wear?
What's something about me that surprised you in a good way?
What's the best advice you'd give us as a couple?
How do you feel when we're apart for a long time?
What's the most fun trip we've taken?
What's something you want to learn from me?
What's your favourite way to wake up with me?
What's your favourite thing about our home or hangout spot?
What's one thing you'd change about how we spend time together?
What's a love story that inspires you?
What's the sweetest message I've ever sent you?
What would you name our future pet?
What's your favourite inside joke of ours?
What's one thing you'd like me to understand better about you?
What's a surprise you'd love to receive from me?
What made you fall for me?
How do you think we've grown since we met?
What's a moment you felt completely safe with me?
What's your favourite thing to cook or eat together?
What's your favourite season with me and why?
What's something you're grateful for in our relationship?
What's the best compliment I've ever given you?
What's the most adventurous thing you'd like us to do?
What's your favourite place we've been together?
What would our dream house look like?
What's the funniest thing I've said by accident?
What's the most romantic movie scene you'd like to recreate with me?
What's something that makes you feel connected to me?
What do you think is my love language?
What's your favourite way to celebrate our anniversary?
If we could live anywhere for a year, where would it be?
What's the first gift I gave you, and did you like it?
What's your favourite thing I do when you're stressed?
What do you think I'm most passionate about?
What's one thing you've never asked me but want to?
What's your favourite thing to talk about late at night?
What's a song you'd dedicate to me right now?
What's a challenge you think we handle well?
What's the best surprise we've planned together?
What's something you'd like us to do for others together?
What's your favourite thing about our first kiss?
What do you love about the way I care for you?
What's a habit of mine that annoyed you at first but you love now?
What's your favourite memory of us in the rain?
What's the best decision we've made as a couple?
What do you think our friends say about us?
What's one promise you want to make to me today?
What's a book or show we should experience together?
What's your favourite way to hold hands?
What's the most memorable night we've had?
What's one of your favourite things about my personality?
What would you write in a letter to me ten years from now?
What do you think makes a relationship last?
What's your favourite thing about our differences?
What's your favourite way to make up after a disagreement?
What's something I've taught you?
What's a moment you realised you could trust me?
What's your favourite thing I wear when we go out?
What's a goal you have that you'd like my support with?
What's your favourite photo of me alone?
What's the most beautiful thing you've seen with me?
What's a scent that reminds you of me?
What's one thing you'd do differently on our first date?
What's the best gift you'd love to give me?
What do you appreciate most about how I listen?
What's your favourite thing about our daily routine together?
What's a weird thing you love about me?
What's the best thing about being with me?
What's something you've learned about love from us?
What's your favourite memory from a holiday together?
What's an ideal anniversary surprise for you?
What's your favourite thing about my laugh?
What's one thing you'd love to hear me say more often?
How do you imagine us when we're old?
What's a quality you think I bring out in you?
What's something we should celebrate more?
What's your favourite thing about us as a team?
What's a dish you'd love me to learn to cook?
What's your favourite memory of us dancing?
What's a little thing I do that makes you feel appreciated?
What would you title the movie of our relationship?
What's your favourite thing about my voice?
What's your favourite way to spend a holiday with me?
What's a cosy activity you'd love to do with me this winter?
What's your favourite thing we've built or created together?
What's your favourite photo of us that no one else has seen?
What's something you hope we never stop doing?
What's your favourite memory of me being silly?
What's the best day you've had with me?
What's a way you'd like us to support each other more?
What's your favourite thing about our chats during the day?
What's one thing you love about my family or friends?
What's your favourite way for me to surprise you?
What's the first thing you'd pack for a trip with me?
What's a fun challenge we should try together?
What's the most romantic city you'd visit with me?
What's your favourite memory of us cooking together?
What's something I do that makes you feel at home?
What would you do if we had a whole day with no plans?
What's your favourite quality of mine when we're with friends?
What's something about me you'd like to know more about?
What's one moment you'd freeze in time with me?
What's a dream date you've never told me about?
What's your favourite memory of us being completely silly in public?
How do you show love when words aren't enough?
What's something small that would make your day today?
What's a memory with me that makes you emotional?
What's a quality you'd like our future kids or pets to get from me?
What's your favourite story to tell people about us?
What do you think was our turning point?
What's one thing you find impossible to stay mad at me for?
What's your favourite way to celebrate small wins together?
What's one of your favourite things to do in silence with me?
What's the most comforting thing I've said to you?
What would our perfect Sunday morning look like?
What's your favourite thing about how we argue fairly?
What's your favourite way I say goodnight?
What's a hobby you'd like us to pick up together?
What's a challenge you'd like us to overcome together?
What's your favourite place to sit next to me?
What's your favourite text I've ever sent?
What's one thing that always makes you proud to be with me?
What's a nickname you'd never let me call you?
What's your favourite way we spend birthdays?
What do you think makes us laugh so much together?
What's a memory you'd love to recreate this year?
What's your favourite photo of us from the beginning?
What's a moment you fell for me all over again?
What did you tell your friends about me after our first date?
What's the most romantic text you've ever received from me?
What's something about me you'd never change?
What's a habit we share that you love?
What's the best surprise I've ever given you?
How would you describe our first kiss to a stranger?
What's your favourite thing about how I look at you?
What's a memory of us that you've never told anyone?
What's one thing you'd love us to learn together?
What's the most fun we've had on a budget?
What do you love about my sense of humour?
What's a city that feels like "ours"?
What's a meal that reminds you of us?
What's something I do that instantly calms you down?
What's your favourite memory of us on a holiday morning?
What's the cheesiest thing you've done for me?
What's your favourite thing about how I hug you?
What's one thing you hope we always keep doing?
What would your vows to me include?
What's the most beautiful sunset we've watched together?
What's your favourite little ritual we have?
What do you think is the funniest thing about us as a couple?
What's a dream you'd like us to chase together?
What do you love most about the way I say your name?
What's the best decision you made because of me?
What's your favourite thing about our late-night talks?
What's a gift you'd love to give me but haven't yet?
What's your favourite memory of us being lazy together?
What's one thing you'd like to hear me say right now?
What's the most spontaneous thing we've done?
What's a place where you felt closest to me?
What's your favourite part of our love story?
What's something you find adorable when I'm sleepy?
What's a small detail about me you noticed early on?
What would a perfect anniversary weekend look like?
What's a song you'd want at our future celebration?
What's your favourite thing about our first apartment, room or hangout?
What made you say yes to our first date?
What's a weird date idea you'd love to try?
What do you love about how I support your dreams?
What's your favourite photo of us laughing?
What's a moment when I made you feel truly seen?
What's a romantic movie cliché you'd love to try with me?
What's your favourite way to celebrate our wins?
What's a habit of mine you'd miss if we were apart?
What's one adventure you want us to have before we're old?
What's something you've been meaning to thank me for?
What's your favourite memory of us in the kitchen?
What's the sweetest thing someone said about us?
What's a skill of mine you're secretly proud of?
What's your favourite memory of a long walk together?
What's one thing you'd love to plan for my birthday?
What's your favourite thing about our weekends?
What's the best "us" moment from this month?
What's one thing you wish we had done sooner?
What's your favourite thing to do when it's just the two of us?
What makes you feel most loved by me?
What's a playlist you'd make about our relationship?
What's a question you'd love me to ask you?
What's your favourite thing about how we make decisions together?
What's the best gift I could give you that costs nothing?
What's the funniest moment from our first months together?
What's a way you'd like us to celebrate this year?
What's your favourite thing about my hands?
What's the most romantic weather to spend with me?
What would be your perfect "us" day from morning to night?
"""

private const val EN_COUPLES_D = """
Give your partner a thirty-second hug without letting go.
Tell your partner three things you love about them while holding hands.
Recreate the pose from your favourite photo together.
Feed your partner a bite of something sweet.
Write your partner a short love note and read it out loud.
Dance together to the first song that plays.
Give your partner a gentle forehead kiss.
Do your best impression of how your partner laughs.
Look into each other's eyes and say one thing you're grateful for.
Hold your partner's hand for the next three turns.
Sing a few lines of "your song" to your partner.
Describe your first date in one minute from your point of view.
Give your partner a hand massage for one minute.
Whisper your partner's best quality in their ear.
Draw a portrait of your partner in thirty seconds.
Plan your next date out loud in sixty seconds.
Give your partner a new cute nickname and use it all night.
Compliment your partner's outfit like a fashion critic.
Recreate your first hug.
Tell your partner the moment you knew they were special.
Make a heart shape with your hands together.
Slow dance with no music for thirty seconds.
Act out how your partner reacts to good news.
Give your partner a piggyback ride, or pretend to.
Take a cute selfie together and set it as your wallpaper for a day.
Send your partner a voice note saying why you love them.
Tell a funny story about your partner they don't mind sharing.
Kiss your partner's hand like a royal.
Do your partner's hair however they like.
Make your partner laugh without touching them.
Tell your partner a secret dream you've never shared.
Hold a staring contest; the loser gives a compliment.
Do a dramatic movie-style reunion run across the room.
Sing a love song using your partner's name.
Promise your partner a small favour for tomorrow.
Recreate a famous movie couple's scene together.
Give your partner a ten-second shoulder rub.
Rate your partner's cooking from one to ten, then explain kindly.
Name five things you love about your partner in ten seconds.
Pretend you're meeting your partner for the first time.
Write your initials together on a piece of paper inside a heart.
Act out how your partner looks when they're sleepy.
Show the group your favourite photo of your partner.
Give your partner a kiss on the cheek.
Do a couple's yoga pose together.
Text your partner something sweet even though they're right here.
Tell your partner what you'd cook them for a special dinner.
Hum your partner's favourite song.
Do a trust fall with your partner.
Tell your partner a cheesy pickup line.
Take turns finishing the sentence "I love it when you...".
Make up a short poem about your partner's smile.
Wrap your partner in a blanket like a burrito.
Pretend to propose to your partner, very dramatically.
Act out your first meeting, swapping roles.
Give your partner three compliments in three different accents.
Describe your perfect holiday together in detail.
Recreate your partner's signature pose.
Show your partner how you'd comfort them on a bad day.
Do a slow-motion high five with your partner.
Tell the group your partner's best quality.
Let your partner choose a song for you to dance to alone.
Make a toast to your relationship.
Say "I love you" in three languages.
Give your partner a butterfly kiss.
Plan a surprise for your partner out loud (they can't hear it, cover their ears).
Hold your partner close and sway for one song.
Pretend to be your partner for one minute.
Tell your partner what you'd do on a perfect day together.
Draw a heart on your partner's hand.
Write down your partner's love language and see if you're right.
Act out the moment you first held hands.
Play a quick game of thumb war; the winner gets a kiss on the cheek.
Tell your partner about a moment they made you feel safe.
Share a snack using only one hand each.
Imitate your partner's morning routine.
Let your partner choose what you'll wear tomorrow.
Do your best romantic movie line to your partner.
Spin your partner around like in a dance.
Tell your partner one thing you've learned from them.
Make a funny video together for your memories.
Cuddle for one minute while everyone counts.
Create a secret handshake with your partner.
Whisper a sweet promise in your partner's ear.
Describe your partner using only food words.
Recreate your favourite couple photo pose.
Give your partner a gentle back scratch.
Sing a duet together.
Tell your partner what you'd write in their birthday card.
Kiss your partner's knuckles.
Let your partner pick a dare for you from the group's ideas.
Do a dramatic reading of your first text conversation (or make one up).
Hold hands and say one wish for your future.
Show your partner how you'd dance at your wedding.
Act out your partner's reaction to their favourite food.
Name a song for every year you've known each other.
Play rock paper scissors; the loser gives a compliment.
Do your best impression of your partner on a road trip.
Thank your partner for something they did this week.
Give your partner a cosy cheek squish.
Sit back to back and draw each other without looking.
Tell your partner what made you smile today.
Pretend to be a couple on a cooking show.
Let your partner choose a cute filter for a selfie together.
Make your partner a paper ring and give it to them.
Tell your partner your favourite memory with them in a whisper.
Give your partner a nickname based on their best trait.
Hold your partner's face and say something sweet.
Recreate your first kiss story in words only.
Do a dance move together that you'll use at every party.
Stand still while your partner tickles you for ten seconds.
Give your partner your best puppy eyes until they smile.
Tell your partner the most attractive thing they did today.
Let your partner design your next date.
Write three words describing your love on a napkin.
Sing your partner a lullaby.
Show your partner your favourite photo of yourself and explain why.
Give each other an eskimo kiss.
Act out how you'd spend your first morning in a new home together.
Name your future pet together right now.
Compliment your partner's laugh in a poetic way.
Do a romantic walk across the room, arm in arm.
Ask your partner one question you've never asked before.
Let your partner pick a song for your next road trip.
Recreate a cute moment from your relationship for the group.
Hug your partner from behind for ten seconds.
Tell your partner why they're your favourite person.
Make a pinky promise with your partner.
Share a dessert using one spoon.
Act out your partner trying to wake you up.
Draw your dream house together on a piece of paper.
Tell your partner a joke that only they would understand.
Do a dramatic love declaration like in a period drama.
Give your partner a slow, sweet kiss on the forehead.
Write your names together in the air with your fingers.
Hold your partner's hand and walk around the room together.
Send your partner a love emoji combination and explain it.
Let your partner fix your hair however they want.
Give your partner a compliment about their kindness.
Do a funny couple's dance move from a music video.
Tell your partner your favourite thing about your relationship right now.
Plan a dream holiday in one minute together.
Imitate your partner's laugh until they laugh.
Hum your first dance song together.
Give your partner a gentle cheek kiss and say thank you.
Recreate how you'd greet each other at an airport after months apart.
Look at your partner for twenty seconds without laughing.
Tell your partner what you'd like to be doing together in five years.
Let your partner choose a sweet emoji to send to you all week.
Make a two-person heart shape with your arms.
Write your partner a fortune-cookie message.
Make your partner a cup of their favourite drink.
Give your partner a long, warm hug.
Tell your partner what you love about their hands.
Act out how you'd celebrate your anniversary.
Write your partner a three-word love poem.
Recreate the first photo you ever took together.
Tell your partner one thing you'd like to do with them this weekend.
Give your partner a slow dance while the group hums.
Draw your partner as a cartoon superhero.
Whisper your favourite memory with your partner.
Give your partner a sincere compliment about their heart.
Hold your partner's hands and describe your perfect holiday together.
Make a funny face that only your partner understands.
Play "who's more likely to" with your partner for one minute.
Let your partner choose your next phone wallpaper.
Recreate your partner's best dance move.
Give your partner a gentle nose boop.
Tell your partner the first thing you'd do if you won a holiday.
Make up a song with your partner's name in the chorus.
Pretend to be a waiter and serve your partner a pretend five-course meal.
Tell your partner three things they did that made you proud.
Create a two-person pose for a magazine cover.
Write a short love note on your partner's hand with your finger.
Hug your partner and sway for fifteen seconds.
Describe your partner like a hero in a movie trailer.
Let your partner choose a nickname for you for the rest of the game.
Give your partner a slow motion kiss on the cheek.
Recreate the moment you said "I love you" for the first time, in words.
Make your partner a pretend bouquet and present it.
Tell your partner what you'd name your love story book.
Do a couple's dance from an old movie.
Let your partner choose a sweet message you must send them later.
Give your partner a five-second forehead touch.
Tell your partner a promise for next month.
Make your partner laugh using only facial expressions.
Show the group your favourite selfie together.
Hold your partner's hand and do a little twirl.
Give your partner a dramatic movie-style welcome home.
Feed your partner a grape or snack like royalty.
Tell your partner one thing they do that you find adorable.
Recreate your partner's morning face.
Make up a secret code word for "I love you".
Give your partner a shoulder squeeze and a compliment.
Plan a pretend picnic together in thirty seconds.
Tell your partner the best thing about today.
Act out your partner trying to choose a movie.
Write "us" on a paper heart and give it to your partner.
Give your partner a hand kiss and a bow.
Pretend to be your partner introducing you to their friends.
Do your best romantic movie run toward your partner.
Name three places you want to kiss your partner at (cities, not body parts).
Tell your partner what you'd do on a rainy day together.
Hum your partner's favourite song while holding their hand.
Give your partner your best compliment in a whisper.
Recreate a cute habit of your partner's.
Say "thank you" to your partner for three specific things.
Hold eye contact and say "I choose you".
Let your partner draw something on your palm.
Give your partner a pretend award and a speech.
Share the last photo of your partner you took.
Make up a dance for your relationship anthem.
Give your partner a gentle back rub for twenty seconds.
Tell your partner which song you'd play at your next anniversary.
Wink at your partner from across the room.
Swap one item of clothing with your partner until your next turn.
Pretend you're on your first date again and introduce yourself.
Let your partner fix your collar or hair like in old movies.
Tell your partner the moment you knew you trusted them.
Give your partner a cuddle until the next player finishes their turn.
Describe your partner's smile in one sentence.
Recreate your favourite memory with your partner using props.
Draw a map of your favourite date spot together.
Act out how your partner reacts when they're hungry.
Promise your partner a breakfast of their choice.
Give your partner a gentle cheek touch and a compliment.
Imitate your partner telling a story.
Let your partner choose a sweet ringtone name for your contact.
Do a two-person handstand or pretend one.
Tell your partner what you'd say to them on a billboard.
Write your partner's name inside a heart and keep it in your wallet.
Make your partner a pretend cup of tea with love.
Give your partner a slow, dramatic movie dip.
Share your partner's best quality with the group.
Let your partner choose the next song everyone listens to.
Tell your partner one dream you have for your next year together.
Kiss your partner's cheek and say goodnight like it's late.
Show your partner the most romantic thing on your phone.
Act out your partner falling asleep during a movie.
Give your partner a sweet compliment in your best poetic voice.
Hold hands and walk in slow motion across the room.
Give your partner a high five, a hug and a kiss on the cheek.
Tell your partner one thing you've never thanked them for.
Recreate your favourite couple moment from a film.
Make your partner a pretend crown and crown them.
"""

private const val EN_FAMILY_T = """
What's your favourite family holiday memory?
Who in the family is the best cook?
What's the funniest thing a family member has said?
If our family were a TV show, what would it be called?
What's your favourite family tradition?
Who in the family is most likely to become famous?
What's the best family recipe?
What's your favourite thing to do together on weekends?
Which family member gives the best hugs?
What's the silliest family argument you remember?
If you could take the whole family anywhere, where would it be?
Who in the family snores the loudest?
What's your favourite family photo?
What's one thing you love about each person here?
What's the best birthday you've ever had?
Who in the family is the best at board games?
What's your favourite family movie night film?
If you were a superhero, who in the family would be your sidekick?
What's the funniest family trip story?
Who is the most organised person in the family?
What's your favourite dessert that someone in the family makes?
Which family member are you most like?
What's the best advice a family member has given you?
What's your favourite memory with your grandparents?
Who in the family tells the best stories?
What's your favourite thing about our home?
If you could invent a family holiday, what would it be?
Who in the family would win a dance competition?
What's your favourite family game?
What's something new you'd like to try as a family?
Who in the family is the funniest?
What's the most adventurous thing our family has done?
What's your favourite family song?
Who in the family is the bravest?
What's your favourite memory from when you were little?
If our family had a mascot, what animal would it be?
Who in the family would survive the longest on a desert island?
What's your favourite family meal?
What's the best gift you've received from a family member?
Who in the family is the best at keeping secrets?
What's something you've learned from your parents?
What's the funniest nickname in the family?
Who in the family loves animals the most?
What's your favourite family celebration?
If you could have any pet, what would it be?
Who in the family would make the best teacher?
What's your favourite thing to do in the garden or park?
What's the most fun day you've had this year?
Who in the family is the most creative?
What's a family rule you think is funny?
What's the best thing about being part of this family?
Who in the family would be the best astronaut?
What's your favourite bedtime story?
What's your favourite thing to eat for breakfast on holidays?
Who in the family is always on time?
What's a skill you'd like a family member to teach you?
What's the funniest thing that happened at a family dinner?
If you could swap places with a family member for a day, who would it be?
What's your favourite outdoor activity with family?
Who in the family has the best laugh?
What's the best thing about your siblings or cousins?
What's your favourite memory of a family celebration?
Who in the family is the best singer?
What would you put in a family time capsule?
What's your favourite family car-ride game?
Who in the family would make the best detective?
What's something kind someone in the family did for you recently?
What's your favourite season to spend with family?
If you could design the perfect family day, what would it include?
Who in the family is the best at sports?
What's your favourite thing about family gatherings?
What's your favourite family joke?
Who in the family would be the best chef on TV?
What's something you're proud of about our family?
What's your favourite family holiday dish?
Who in the family is the most patient?
What's your favourite story about your parents when they were young?
If our family started a band, what would we call it?
Who in the family has the best handwriting?
What's your favourite place to visit with family?
What's the funniest thing a pet has done in the family?
Who in the family would win a spelling bee?
What's your favourite thing to build or make with family?
What's your favourite family photo pose?
Who in the family is the biggest foodie?
What do you love most about family movie nights?
If you could give the family a superpower, what would it be?
Who in the family would be the best at camping?
What's the most memorable gift you've given a family member?
What's your favourite thing to talk about at dinner?
Who in the family is the best at fixing things?
What's a family memory that always makes you laugh?
What's your favourite family video?
Who in the family is the early bird?
Who in the family is the night owl?
What's your favourite thing to bake with family?
What job would each family member be great at?
What's your favourite family road trip memory?
Who in the family is the best at giving advice?
What's your favourite thing about family holidays?
If you could rename our family, what would it be?
Who in the family has the coolest hobby?
What's a family story you love hearing again and again?
What's your favourite family board game moment?
Who in the family is the most stylish?
What's your favourite memory of a family pet?
What would you like to do together on your next birthday?
Who in the family would be best at running a shop?
What's your favourite family dance move?
What's the kindest thing you've seen a family member do?
If our family were a pizza, what toppings would each person be?
Who in the family is the best gamer?
What's your favourite family picnic food?
What's a family photo that makes you smile?
Who in the family would be the best inventor?
What's your favourite sound in our home?
What's one thing you want to say thank you for to the family?
Who in the family would be the best at magic tricks?
What's your favourite family festival food?
What's the funniest costume someone in the family has worn?
Who in the family is the most adventurous eater?
What's a promise you'd like to make to the family?
What's your favourite family video call memory?
Who in the family would be the best tour guide?
What's your favourite family saying or catchphrase?
What's your favourite way to help at home?
Who in the family is the best at drawing?
What's your favourite memory of learning something from family?
If you could cook one dinner for the family, what would it be?
Who in the family would be the best at a talent show?
What's your favourite thing about family mornings?
What's your favourite family-themed movie?
Who in the family is the calmest in a crisis?
What's a family moment you'd love to relive?
What's your favourite thing to do on a rainy day at home?
Who in the family would be the best captain of a ship?
What's your favourite family song to sing in the car?
What's the funniest mistake someone in the family made?
Who in the family has the best smile?
What would our family's motto be?
What's your favourite memory from a family wedding or event?
Who in the family would be the best president?
What's your favourite activity on family holidays?
What's a family goal we should set this year?
Who in the family is the most helpful?
What's your favourite thing about your cousins?
What's the best family surprise you remember?
Who in the family would be the best comedian?
What's your favourite memory of playing outside as a family?
What's your favourite way to celebrate good news at home?
Who in the family has the best memory?
What's your favourite sweet treat from family gatherings?
What's your favourite thing to do with grandparents?
Who in the family would win a cooking contest?
What's your favourite thing about family traditions?
If you could have a family day out anywhere, where would it be?
Who in the family would be the best zookeeper?
What's your favourite thing to share with your family?
What's a family memory you'd draw if you were an artist?
Who in the family is the best at planning trips?
What's your favourite part of family holidays?
What's one thing you want to learn from the oldest person here?
What's one thing you want to teach the youngest person here?
Who in the family would make the best superhero?
What's your favourite memory from a family celebration this year?
What's your favourite thing about Sunday family time?
What's your favourite thing about our family holidays at home?
Who in the family would be the best at a treasure hunt?
What's the funniest thing you did as a toddler?
If you could invent a new family game, what would it be called?
Who in the family has the most interesting job or hobby?
What's your favourite thing about visiting relatives?
What's your favourite family breakfast?
Who in the family would win a hide-and-seek championship?
What's something you'd like to do with the whole family this summer?
Who in the family laughs at their own jokes the most?
What's your favourite family memory from the beach or a park?
If you were the parent for a day, what would you change?
Who in the family would be the best pilot?
What's your favourite family tradition for birthdays?
What's the funniest thing you've seen a grandparent do?
Who in the family has the coolest collection?
What's your favourite meal to cook together?
If you could time travel with family, where would you go?
Who in the family is the best at puzzles?
What's your favourite thing about bedtime at home?
What's something you've never told the family that's funny?
Who in the family would be the best wildlife explorer?
What's your favourite family holiday photo?
What's a family rule you'd like to add?
Who in the family would make the best YouTuber?
What's your favourite memory from a family celebration dinner?
If our family had a theme song, what would it be?
Who in the family is the best at making people feel better?
What's your favourite memory of a snow day or rainy day?
What's your favourite toy you've shared with family?
Who in the family would be the best mayor of the town?
What's your favourite thing to do at a family barbecue or picnic?
What's a smell that reminds you of home?
Who in the family would be the best at a quiz show?
What's the best thing a sibling or cousin has done for you?
What's a family memory you'd put in a museum?
Who in the family is the best at building forts?
What's your favourite memory of a family holiday trip abroad or far away?
If you could choose the next family outing, what would it be?
Who in the family is the best at remembering birthdays?
What's your favourite story about how our family began?
What's your favourite family dessert night treat?
Who in the family has the funniest sneeze?
What's your favourite thing about family car trips?
If we had a family flag, what would be on it?
Who in the family would be the best at running a restaurant?
What's your favourite memory of helping a family member?
What's one fun fact about each family member?
Who in the family would win a pie-eating contest?
What's your favourite family movie quote?
What's your favourite memory of playing a sport together?
Who in the family would be the best at caring for a farm?
What's a dish you'd love grandma or grandpa to teach you?
If you could build anything with the family, what would it be?
Who in the family would be the best at dressing up?
What's your favourite memory from a family festival or holiday?
What's the most fun chore to do together?
Who in the family is most likely to become an artist?
What's your favourite thing about the youngest person here?
What's your favourite thing about the oldest person here?
Who in the family gives the best surprises?
What's your favourite game to play after dinner?
If you could give each family member a medal, what would it be for?
Who in the family would be the best at a spelling contest?
What's your favourite memory of laughing together until you cried?
What's a family trip you'd like to take again?
Who in the family is the best at telling bedtime stories?
What's your favourite thing about family birthdays?
If the family went on a reality show, which one would it be?
Who in the family would be the best gardener?
What's your favourite memory of baking or cooking together?
What's something you want to do with the family before the year ends?
Who in the family would be the best captain of a sports team?
What's your favourite thing to do with family on a holiday morning?
What's the best family nickname and how did it start?
Who in the family would make the best scientist?
What's your favourite memory of the family dancing together?
What's a family memory that makes you feel warm inside?
Who in the family would be the best at a talent show and what would they do?
What's your favourite thing about being together tonight?
"""

private const val EN_FAMILY_D = """
Do your best impression of a family member and let everyone guess.
Sing a family favourite song together with the person on your left.
Act like a monkey for thirty seconds.
Tell a knock-knock joke.
Do a silly walk around the room.
Pretend to be a news reporter interviewing the youngest player.
Make the funniest face you can and hold it for ten seconds.
Hop like a kangaroo across the room.
Do your best impression of a grandparent telling a story.
Pretend you're a chef making the family's favourite dish.
Draw a picture of the whole family in thirty seconds.
Do your best superhero pose and hold it.
Walk like a penguin to the door and back.
Pretend to be a puppy asking for a treat.
Say the alphabet while hopping on one foot.
Do your best elephant impression.
Give everyone in the room a high five.
Sing "Twinkle Twinkle Little Star" in a funny voice.
Pretend to be a teacher and give a one-minute lesson.
Do a happy dance for fifteen seconds.
Balance a cushion on your head and walk across the room.
Act out your favourite animal and let others guess.
Tell the group your favourite joke.
Pretend you're a robot vacuum cleaning the floor.
Do ten jumping jacks.
Make up a story about a flying cat in one minute.
Pretend to be a statue until someone makes you laugh.
Give a compliment to the person on your right.
Act like a frog until your next turn.
Do your best lion roar.
Spin around three times and point at someone to be your sidekick.
Pretend to be a waiter taking everyone's order.
Do your best impression of a baby.
Try to touch your toes for ten seconds.
Pretend you're a famous singer on stage.
Do a funny dance that everyone has to copy.
Make animal sounds for every word you say until your next turn.
Pretend you're a pirate looking for treasure.
Tell everyone your favourite thing about them.
Pretend to be a tree blowing in the wind.
Do your best impression of a cartoon character.
Act like you're walking on a tightrope.
Give the youngest player a piggyback ride, if safe.
Pretend to eat an invisible giant sandwich.
Do your best impression of a dinosaur.
Make a paper boat or paper plane.
Pretend to be a snowman melting in the sun.
Do a cartwheel or your best roly-poly.
Tell a story about your day using only animal noises.
Pretend you're a magician and make something "disappear".
Act like a cat chasing a laser dot.
Do your best robot dance.
Pretend to be a firefighter saving the day.
Hum a song and let everyone guess it.
Do your best impression of a teacher you know.
Act like a bird building a nest.
Pretend you're on a boat in a storm.
Stand on one foot for twenty seconds.
Pretend to be a lion tamer with an invisible lion.
Do the chicken dance.
Act like you're a giant walking through a tiny town.
Make up a song about breakfast.
Pretend you're a snake slithering on the floor.
Do your best impression of a superhero flying.
Pretend you're an astronaut floating in space.
Act out brushing your teeth in slow motion.
Make everyone laugh in thirty seconds.
Pretend to be a horse galloping around the room.
Do your best impression of a crying baby.
Pretend to be a clock ticking.
Act like a bunny looking for carrots.
Do a fake sneeze in the funniest way possible.
Pretend you're a weather presenter forecasting snow.
Walk across the room as quietly as a mouse.
Pretend to be a car and make all the sounds.
Do your best impression of a monster under the bed.
Tell a riddle and let everyone guess.
Pretend you're a tiny ant carrying a huge crumb.
Do your best impression of a penguin sliding on ice.
Act like you're stuck in jelly.
Sing the ABC song as fast as you can.
Pretend to be a dog that just heard the doorbell.
Do your best dance move from when you were little.
Pretend you're a zookeeper feeding all the animals.
Act like you're on a bouncy castle.
Pretend to be an owl hooting at night.
Do your best yoga tree pose.
Pretend to be a chef tasting soup that's too salty.
Act like a superhero who forgot their powers.
Draw a funny face on a piece of paper and show it.
Pretend to be a train going through a tunnel.
Do your best impression of a fish.
Pretend you're a king or queen giving a royal wave.
Act like a robot that needs batteries.
Count to ten in a squeaky voice.
Pretend you're building a sandcastle.
Do a silly victory dance.
Act like a bee buzzing around flowers.
Pretend you're a statue in a park.
Do your best impression of a race car driver.
Make up a new dance called the "family shuffle".
Pretend to be a crab walking sideways.
Act like you're in slow motion for thirty seconds.
Tell a funny story from your week.
Pretend to be a balloon floating away.
Do your best impression of a sleepy bear waking up.
Act like a jellyfish in the ocean.
Pretend to be a famous footballer scoring a goal.
Sing a song in a robot voice.
Do your best impression of a goat.
Pretend you're on a rollercoaster.
Act like you just saw a ghost.
Pretend to be a gardener planting flowers.
Do an impression of someone opening a birthday present.
Pretend you're the captain of a spaceship.
Hold a funny pose until everyone counts to ten.
Pretend to be a doctor checking everyone's heartbeat.
Act like a kitten that's sleepy.
Do your best impression of a squirrel.
Pretend you're a mail carrier delivering letters.
Make up a short song about the family.
Act like a dragon breathing fire.
Do your best impression of a wind-up toy.
Pretend you're painting a giant wall.
Do the floss dance.
Pretend to be a parrot repeating everything the next player says.
Act like you're walking through deep snow.
Pretend you're a ballerina doing a twirl.
Tell everyone something that made you happy today.
Pretend to be a famous explorer discovering a new land.
Act like a duck waddling to the pond.
Do your best impression of a toy soldier marching.
Pretend you're on a trampoline.
Draw your dream pet in thirty seconds.
Pretend to be a puppet with strings.
Act like a turtle racing a rabbit.
Do your best impression of someone sneezing three times.
Pretend you're a lifeguard blowing a whistle.
Make a funny hat out of paper and wear it.
Pretend to be a farmer milking a cow.
Act like an alien visiting Earth.
Do your best whale sound.
Pretend you're a detective looking for clues.
Act out your favourite fairy tale in thirty seconds.
Pretend to be a robot dancing to music.
Do a silly impression of a parent saying "bedtime".
Pretend you're a baker kneading dough.
Act like a superhero rescuing a cat from a tree.
Do your best impression of a cuckoo clock.
Pretend you're a scientist mixing potions.
Act like you're riding a bicycle.
Give the person on your left a silly nickname.
Pretend you're a tiny fairy flying around.
Do your best monkey dance.
Pretend to be a famous artist painting a masterpiece.
Act like you're playing in the rain.
Do your best impression of a happy dog wagging its tail.
Pretend you're a sports coach cheering the team.
Tell the family what superpower each person would have.
Pretend you're juggling invisible balls.
Do your best impression of a sneaky cat.
Act like you're ice skating.
Pretend to be a train conductor calling "all aboard".
Do your best impression of a dancing chicken.
Make a crown out of paper and give it to someone.
Pretend you're a caterpillar turning into a butterfly.
Act like you're climbing a mountain.
Do your best impression of a happy grandparent.
Do your best impression of a happy family pet.
Pretend to be a waiter serving the family's favourite dish.
Walk like a giraffe across the room.
Tell a funny story about the family in thirty seconds.
Pretend to be a tour guide showing your home.
Do a dance move and have the whole family copy it.
Pretend you're a rocket launching into space.
Act like a superhero cleaning your room at super speed.
Do your best impression of a cheerful mail carrier.
Pretend to be a lion cub learning to roar.
Make up a short poem about the person on your left.
Pretend you're an artist painting the family portrait.
Act like you're riding a horse in a race.
Do your best cartoon laugh.
Pretend to be a robot making breakfast.
Give the youngest player a royal title for the next round.
Pretend you're in a slow-motion pillow fight.
Act like a snowflake falling to the ground.
Do your best impression of a train whistle.
Pretend you're walking on hot sand.
Hop around the room like a frog for ten seconds.
Pretend you're a famous singer giving a concert in the living room.
Act like a penguin trying to fly.
Do your best impression of a baby elephant.
Make up a family cheer and lead everyone in it.
Pretend to be a crab doing a dance.
Act like you're tiptoeing past a sleeping giant.
Do your best impression of a superhero landing.
Pretend you're stuck in a big bubble gum bubble.
Act like a kangaroo with a full pouch.
Draw a silly animal and give it a funny name.
Pretend you're a famous footballer doing a goal celebration.
Act like a tiny chick hatching from an egg.
Do your best impression of a teacher saying "good morning class".
Pretend you're on a boat rowing to an island.
Act like a ghost saying "boo" very politely.
Do a fashion show walk with a cushion as a handbag.
Pretend to be a dog learning a new trick.
Act like you're tasting the sourest lemon ever.
Do your best impression of a happy monkey eating a banana.
Pretend you're a statue that slowly comes to life.
Sing a song while marching like a soldier.
Act like you're a snowman building a snow-person.
Do your best impression of a dolphin.
Pretend you're a pilot announcing a smooth landing.
Act like a tortoise winning a race in slow motion.
Do your best impression of a superhero with a cold.
Pretend you're a magician pulling scarves out of your sleeve.
Act like a cat stretching after a nap.
Make up a dance called "the spaghetti".
Pretend to be a news anchor reporting family news.
Act like a bear looking for honey.
Do your best impression of a rooster waking everyone up.
Pretend you're juggling three pretend eggs.
Act like you're a very tall giraffe eating leaves.
Do your best impression of a sleepy sloth.
Pretend you're a soccer goalkeeper making a big save.
Act like you're in a windy storm holding an umbrella.
Sing "Happy Birthday" to the person on your right, even if it's not their birthday.
Pretend you're a dinosaur discovering a cookie.
Act like a koala hugging a tree.
Do your best impression of a happy grandmother cooking.
Pretend to be a king or queen choosing a royal snack.
Act like a racing car going around the room.
Do your best impression of a funny clown.
Pretend you're a butterfly landing on everyone's nose.
Act like you're catching fireflies.
Do your best impression of an excited kid on holiday.
Pretend you're a weather vane spinning in the wind.
Act like you're the slowest waiter in the world.
Do your best impression of a cheerful parrot saying everyone's names.
Pretend you're building a giant snowman.
Act like a jellyfish dancing.
Give everyone a thumbs up and a big smile.
Pretend you're a famous chef making pancakes.
Act like a puppy chasing its tail.
Do your best impression of a squeaky door.
"""
