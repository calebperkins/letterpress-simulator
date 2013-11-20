(ns letterpress-bot.game-test
  (:require [clojure.test :refer :all]
            [letterpress-bot.game :refer :all]))

(deftest create-new-game
  (let [g (new-game [])]
    (is (empty? (g :letters)))
    (is (empty? (g :colors)))
    (is (empty? (g :last-word)))
    (is (= :b (g :player)))))

(deftest game-over
  (is (not (over? (new-game [])))))

