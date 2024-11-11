Feature: A1_scenario
  Scenario: A1_scenario
    Given Game starts
    When "p1" draws a card with "4 stages"
    Then "p1" decline to sponsor quest
    And "p2" accept to sponsor quest
    And "p2" builds quest "F5,H10,Quit,F15,S10,Quit,F15,D5,B15,Quit,F40,B15,Quit"
    And "p1" accept quest
    And "p1" hand over 12 discard "F5"
    And "p3" accept quest
    And "p3" hand over 12 discard "F5"
    And "p4" accept quest
    And "p4" hand over 12 discard "F5"
    And "p1" builds attack "D5,S10,Quit"
    And "p3" builds attack "S10,D5,Quit"
    And "p4" builds attack "D5,H10,Quit"
    And resolution stage 1
    And "p1" accept quest
    And "p3" accept quest
    And "p4" accept quest
    And "p1" builds attack "H10,S10,Quit"
    And "p3" builds attack "B15,S10,Quit"
    And "p4" builds attack "H10,B15,Quit"
    And resolution stage 2
    And "p1" validate hand "F5,F10,F15,F15,F30,H10,B15,B15,L20"
    And "p1" validate shield 0
    And "p3" accept quest
    And "p4" accept quest
    And "p3" builds attack "L20,H10,S10,Quit"
    And "p4" builds attack "B15,S10,L20,Quit"
    And resolution stage 3
    And "p3" accept quest
    And "p4" accept quest
    And "p3" builds attack "B15,H10,L20,Quit"
    And "p4" builds attack "D5,S10,L20,E30,Quit"
    And resolution stage 4
    And give shields stage 4
    And "p2" sponsor draws stage 4

    And "p1" validate number cards 9
    And "p1" validate hand "F5,F10,F15,F15,F30,H10,B15,B15,L20"
    And "p1" validate shield 0

    # Sponsor of quest #F70 is rigged at Given step so it'll always be there
    And "p2" hand over 12 discard "F5,H10,E30,F70"
    And "p2" validate number cards 12
    And "p2" validate shield 0

    And "p3" validate number cards 5
    And "p3" validate hand "F5,F5,F15,F30,S10"
    And "p3" validate shield 0

    And "p4" validate number cards 4
    And "p4" validate hand "F15,F15,F40,L20"
    And "p4" validate shield 4

  Scenario: 2winner_game_2winner_quest
    Given game starts 2winner_game_2winner_quest
    When "p1" draws a card with "4 stages"
    Then "p1" accept to sponsor quest
    And "p1" builds quest "F10,Quit,F5,S10,Quit,F20,Quit,F5,D5,B15,Quit"
    And "p2" accept quest
    And "p2" hand over 12 discard "F5"
    And "p3" accept quest
    And "p3" hand over 12 discard "F5"
    And "p4" accept quest
    And "p4" hand over 12 discard "F5"
    And "p2" builds attack "D5,S10,Quit"
    And "p3" builds attack "D5,Quit"
    And "p4" builds attack "D5,S10,Quit"
    And resolution stage 1
    And "p2" accept quest
    And "p4" accept quest
    And "p2" builds attack "S10,H10,Quit"
    And "p4" builds attack "S10,H10,Quit"
    And resolution stage 2
    And "p2" accept quest
    And "p4" accept quest
    And "p2" builds attack "S10,H10,Quit"
    And "p4" builds attack "S10,H10,Quit"
    And resolution stage 3
    And "p2" accept quest
    And "p4" accept quest
    And "p2" builds attack "S10,B15,Quit"
    And "p4" builds attack "E30,Quit"
    And resolution stage 4
    And give shields stage 4
    And "p2" draws a card with "3 stages"
    And "p2" decline to sponsor quest
    And "p3" accept to sponsor quest
    And "p3" builds quest "F5,Quit,F10,Quit,F15,Quit"
    And "p1" decline quest
    And "p2" accept quest
    And "p4" accept quest
    And "p2" builds attack "S10,Quit"
    And "p4" builds attack "S10,Quit"
    And resolution stage 1
    And "p2" accept quest
    And "p4" accept quest
    And "p2" builds attack "S10,H10,Quit"
    And "p4" builds attack "D5,S10,Quit"
    And resolution stage 2
    And "p2" accept quest
    And "p4" accept quest
    And "p2" builds attack "H10,B15,L20,Quit"
    And "p4" builds attack "H10,B15,L20,Quit"
    And resolution stage 3
    And give shields stage 3
    And "p2" validate shield 7
    And "p4" validate shield 7
    And validate winners "p2,p4"