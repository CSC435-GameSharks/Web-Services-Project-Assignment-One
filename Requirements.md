GameShark's Project Requirements
===============================

Members
-------

- Kelly M
- Chaz S
- Mark W



Client Functionality
--------------------

1. Server Data (LoL, WoW, Diablo III) - Stateless
   1. Up/down
   2. Region
1. Champion Data (LoL) - Stateless
   1. General Base Stats
   2. counter champion
   3. recommended lane (database?)
1. Character Class (WoW, Diablo III) - Statefull
   1. General Base Stats
   2. Role (Healer, Tank)
   3. Skill List
1. Summoner Stats (LoL) - StatefulS
   1. Game History
   2. Win Ratio
1. Character/ User Data (WoW, Diablo) - Stateful
   1. Character List
      1. Character type
      2. race
      3. level
1. Build-a-build (LoL) - Stateless
   1. Choose Champion
   2. Choose Level
   3. Choose 1-6 items
   4. Choose runes
   5. Choose Masteries
   6. View modified character stats
1. Build-a-set (WoW, Diablo) - Stateless
   1. Choose existing character
   2. Choose items 
   3. View modified character
1. Matchup calculator - Stateful
   1. your summoner name
   2. your champion
   3. opponent summoner name
   4. opponent champion
   5. see how screwed you are
1. In game assistant - Stateful
   1. Monitor teams gold
   2. objectives
   3. recommend items categories bases on in game team stats


 
###Quality###
1. Doesn’t have to look pretty
2. Responds within 15 seconds from initial request
3. Reliable up time


###Platform###
1. Web based
2. Mobile friendly


###Process###
1. GitHub
2. Communication
3. Commented Code



Back End Functionality
----------------------

1. Use servlets that utilize the following:
   1. Riot API
   2. Blizzard API
   3. MySQL Database
   4. Custom end-points
1. Points of Contact:
   1. Each of the client functionality requirements from F.1 - F.9 above serves as a point of contact to the outside world
1. Web Tier:
   1. Simple web pages that use WAR files to serve data
   2. Utilize CSC 420 principles and best practices 


###Quality###
1. Error traps crashes and responds to client with an error code.


###Platform###
1. Glassfish
2. WAR files


###Process###
1. GitHub
2. Communication
3. Commented Code