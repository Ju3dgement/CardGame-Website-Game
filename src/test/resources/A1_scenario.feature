Feature: A1
  Scenario: A1
    Given Game starts
    When P1 draws quest with 4 stages and declines
    Then P2 creates quest
    And completes stage 1
    And completes stage 2
    And completes stage 3
    And completes stage 4
    And game ends assert number of cards for p2

#Feature:
#  Scenario: 2winner_game_2winner_quest
#    Given
#    When
#    Then
#    And

#Feature:
#  Scenario: 1winner_game_with_events
#    Given
#    When
#    Then
#    And

#Feature:
#  Scenario: 0_winner_quest
#    Given
#    When
#    Then
#    And