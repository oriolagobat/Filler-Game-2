<h1 align="center">
Filler Game 🎮
</h1>

<h2 align = center> <img align="center" src="https://github.com/oriolagobat/Filler-Game/blob/main/images/gameplay.gif" height="288px" width="182px"/> </h2>

## What's new?
### Features:
- Game summaries.
- Persistence of game summaries, user can check the history of games.
- Queries regarding the summaries to be shown on the history.
- Spanish translation added to current ones (Catalan and English)
- Preferences are now stored, so there's no need to reset them each game.
- Possibility to completely disable the music

### Project design:
- Username validation.
- Use of fragments, viewmodels and other patterns (interfaces) to make the code more readable and decoupled.
- Recycler view for the game history
- Usage of Room database
- Support for tablet in landscape and portrait mode
- Background added in all activities
- Use of Material Design 3
- General improvements on the app appearance

## App features 📱

- There is a help section with a simple explanation of the game rules and objectives
- There is a configuration screen with options to change the game settings
- Both the game and configuration screens have a background music (a different one for each), and
  the result screen has different sound effects depending on the game outcome.
- The app can be used in portrait or landscape mode, and also offers the possibility to change the
  orientation at any moment
- The app offers language support in English, Catalan and Spanish
- A log is formed since the configuration screen, ending at the result screen. It can then be
  sent to the desired email address
- A different result screen for all 3 scenarios (win, lose, draw)
- Game scores are shown at the result screen
- User can choose if he wants music/sound or not

## Game features 🕹️

- Number of colors is customizable (default: 3)
- Board size is customizable (default: 3x3)
- User can choose between 3 difficulties:
    - Easy (AI will choose a random color between the best 3 options it has)
    - Medium (AI will choose a random color between the best 2 options it has)
    - Hard (AI will always choose the best option)
- By default the time is unlimited, but the user can choose to set a round time limit, which will
  determine how many seconds he has to choose a color. When the time is up, a random color is chosen
  as a punishment. If time control is enabled, this limit will be:
    - Easy: 10 seconds
    - Medium: 7 seconds
    - Hard: 3 seconds
- The user profile picture shown during the game, can be changed to any image. Either to the default
  one, one from the gallery, or one taken from the camera.
- Username is customizable.
