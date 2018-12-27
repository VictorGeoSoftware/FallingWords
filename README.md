Demo porject for training with
instrumental testing (this project doesn't have Unit testing 
since all frameworks depend on some Context)

Architecture following MVP pattern.
Frameworks: Dagger2, Room, Cucumber

Time invested: 9h
   - Concept: half an hour
   - Game mechanic: half an hour
   - Model layer: 2 hours
   - Game mechanic: 2 hours
   - Testing: 4 hours -> since I have trained and learnt many new thigs with Espresso and specially, with Cucumber
   
The first thig to be improved is the game logic. On this edition, it only perform a ramdomly search of translations,
and almost all the time are wrong translations, so it is needed to be implemented a more complete logic for provide
also right translations.
Also should be convenient a bigger testing cover, since I hadn't time to test the end-game dialog.
