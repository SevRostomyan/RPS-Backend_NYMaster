## Reflektion-Inlämningsuppgift 2 - Del-VG

## Sevak Rostomyan

### Del 1 - G:
Arbetet började jag ganska sent i kursen då jag halkade efter på grund av att jag jobbar på heltid och gör en del annat 
och hade missat de flesta föreläsningar. Dessutom gjorde jag allt själv då min teammedlem behövde skjuta fram denna kurs. 
Därmed gick det ganska trögt under hela perioden jag jobbade på projektet men jag har alltid inställningen att svårigheter 
är alltid en möjlighet att bli bättre. Så jag lärde mig väldigt mycket genom att behöva söka det mesta av informationen 
på egen hand. 

I början var det oklart hur projektet skulle struktureras så jag fick tips från edda om hur jag skulle tänka. Det var
användbart och arbetet kom igång. Det mesta av tiden har jag spenderat på att lösa systemfel såsom att få servern att
fungera och få versionerna på olika dependencies att matcha och få deras grundläggande configs att bete sig som jag vill.
Så själva kod-skrivandet har inte varit lika tidskrävande. SExempelvis funkade servern inte för att jag hade satt samma 
portnummer på den och databasen vilket jag lärde mig var fel och jag behövde ha olika portar på dem.

Ett annat problem var att få entiteter att matcha med databasens variabeltyper då jag hade skapad tabellerna via SQL kod
först och behövde korrigera en del värden och annotationer. Annotationerna var ett annat problem som tog tid innan jag fick
rätt annotationer i de flesta ställen.

Ett annat förhinder i början var att jag behövde lära mig hur man skapar de olika requesten (inklusive Json body) baserad
på de endpoints jag hade skapat i mina controller klasserna. 

Jag la till en funktion som möjliggör beräkning av antalet spel man spelat och ange vinnaren efter 3 rundor. Detta
lyckades jag inte lösa med det krävda WIN i uppgiftsbeskrivningen så jag skapade en Win per person för att kunna beräkna
de separat och kunna ange vinnaren.

Samtliga anrop mappas via motsvarande DTO-s för ökad säkerhet, nämligen för att inte exponera informationen och 
variablerna i entiteten.

Samtliga begärda funktioner i uppgiftens del-G fungerar felfritt. 

OBS: Denna del av arbetet finns i Master grenen.

### Del 2 - VG

Jag valde att tillägga en Basic Spring Security på grund av brist på kunskap och tid för att lägga till på att lära mig
avancerade varianten med JWT token. Jag började med att skapa en separat gren av projektet som är nu pushat till GitLab 
i grenen som heter Fortsättning.

- Jag har gjort flera ändringar i min kod från del-G och angett det mesta som krävs för Springboot basic security 
aktivering och jag lyckas med att få getToken anropet att fungera med autentisering.
- Jag har separerat user från player och skapat separat entity, DTO, repository samt en config fil för user.
- Har även skapat en service och controller för ändring av username och password.
- Jag har hårdkodat username och password i UserConfig för två användare och angett i CommandLineRunner att det ska 
hindra det att skapa fler än 2 användare då systemet funkar inte om det skapas nya användare med samma namn vid varje 
update/omstart av applikationen.
- Orsaken till att jag har bara "update" och inte creat-drop är att den senare tar bort playerOneGame och PlayerTwoGame 
i playerEntity vid omstart av appen och skapar inte de på nytt. Detta händer för att hibernate har bestämt sig att player 
entity ska skapas först vilket gör att de sista två raderna - playerOneGame och PlayerTwoGame i tabellen inte skapas 
eftersom man behöver ha skapat GameEntity tabellen först innan man kan koppla de raderna med gameEntity.
- Mitt största problem är dock med post request-en. Postman visar error 401 då det saknas CSRF token i header-n som jag 
förstod via debug-ing. Dvs authentication funkar och är möjligt dock avsaknaden av denna token blockerar åtkomsten till 
önskade metoder. Inloggningsuppgifterna är angivna korrekt.
Jag har hittat ett sätt att få ut CSRF token via en cookie vid anrop och har lagt till motsvarande script och ställt till 
en environment i postman för att få ut det från servern via cookie-n XSRF-TOKEN cookie men det verkar som att cookie-n är 
inte aktiverat i mitt Spring projekt och jag lyckas inte aktivera det på ett enkelt och icke tidskrävande sätt för att 
kunna skicka tillbaka denna token vid anrop.

Sammanfattningsvis ger jag härmed upp med Spring Security och tycker att jag har lagt till det i projektet trots att 
det viktigaste inte fungerar - jag lyckas inte göra mina samtliga anrop. Jag är dock öppen för feedback om hur jag skulle 
ha kunnat lösa det sistnämnda problemet eller vad jag skulle ha undvikit att göra i mitt projekt som skulle ha kunnat 
skapa problemet. Jag kommer att förstå om det jag inte får betyget VG. Tack för den extra tid jag fick på mig :)
