classDiagram
Game : - int Id
Game : - String name
Game : - String manufacturer
Game : - int minimalPlayers
Game : - int maxPlayers
Game : - int ageAndUp
Game : - int minimalDuration
Game : - int averageDuration
Game : - Category category
Game : - Theme theme
Game : - Type type
Game : - String averageStarValue ?


Game "*" <--> "*" User
Game "*" --> "1" Expantion
Game "0" --> "*" Review
Expantion "0" --> "*" Review
User "*" --> "*" GameDate

class Expantion{
- int Id
- String expantionName
}

class Review {
- int Id
- String review
- int starRating
}

class User {
- int Id
- String userName
- String passWord
- UserRole userRole
}

class GameDate {
- Date gameDate
}
%%    enumeration
class UserRole {
<<enumeration>>
ADMIN
USER
GUEST
}

class Category{
<<enumeration>>
BORD
CARD
DICE
OTHER
}

class Type {
<<enumeration>>
COOP
FAMILY
KIDS
MODERNCLASSIC
CLASSIC
PARTY
SOLO
DUO
}