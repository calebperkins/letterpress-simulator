(ns letterpress-bot.game
  "Main functions for simulating the game."
  (:require [letterpress-bot.board :as board]
            [letterpress-bot.bots.simple :as bot]))

(defn new-game
  "
  Initialize a new game state from a 2D matrix of letters.

  The game state is a map where:
  * :colors is a mapping from tiles to color (:r, :b, or nil)
  * :letters is the provided matrix
  * :player is the current player
  * :last-word is the last word played
  "
  [grid]
  {:letters grid
   :player :b
   :last-word []
   :colors {}})

(defn to-word
  "Map a sequence of tiles to its string representation."
  [letters s]
  (apply str
         (map #(get-in letters %) s)))

(defn print-state
  [game]
  (let [board (game :colors)]
    (println "Blue:" (board/score board :b))
    (println "Red:" (board/score board :r))
    (println "Last word:" (to-word nil))))

(defn over?
  "The game is over when all tiles are occupied."
  [game]
  false)

(defn play
  "Play the best word and return the new game state."
  [game]
  (let [tiles (bot/next-word game) ; TODO get best tile sequence, allow injection of strategy
        player (game :player)
        opponent (if (= player :b) :r :b)]
    (assoc game
      :colors (board/update player tiles)
      :player opponent
      :last-word tiles)))

