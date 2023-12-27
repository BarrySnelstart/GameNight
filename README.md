classDiagram


Game "*" <--> "*" User
Game "0" --> "*" Review
Game "*" <--> "1" GameExpantion

class Game {
- int Id
- String name
- String manufacturer
- Date releaseDate
- Category category
- Theme theme
- Type type
- int iD
- int averageDuration
- int minimalPlayers
- int maxPlayers
- int ageAndUp
- int minimalDuration
- String averageStarValue
  }

class GameExpantion {
- String expantionName
- Date releaseDate
  GameId

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
User "1" <-->  "*" GameEventDate

class GameEventDate {
- Date gameEventDate
}

class Security {
Secuur dingetjes
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