(ns letterpress-bot.board-test
  (:require [clojure.test :refer :all]
            [letterpress-bot.board :refer :all]))

(deftest scoring
  (is (zero? (score {} :b)))
  (is (= 1 (score {[0 0] :r, [3 4] :b, [1 2] :r} :b)))
  (is (= 2 (score {[0 0] :r, [3 4] :b, [1 2] :r} :r)))
  (is (zero? (score {} nil)))
  (is (zero? (score {[0 0] :r, [3 4] :b, [1 2] :r} nil))))

(deftest updating
  (is (empty? (update {} :b [])))
  (is (= {[0 0] :r, [3 4] :b, [1 2] :r}
         (update {[0 0] :r, [3 4] :b, [1 2] :r} :r [])))
  (is (= {[0 0] :r, [3 4] :b, [1 2] :r}
         (update {[0 0] :r, [1 2] :r} :b [[3 4]])))
  (is (= {[0 0] :b, [3 4] :b, [1 2] :b}
         (update {[0 0] :r, [3 4] :b, [1 2] :r} :b [[0 0], [1 2]]))))

(deftest defense
  (is (not (defended? {} [0 0])))
  (is (not (defended? {[0 0] :b, [0 1] :b} [0 0])))
  (is (not (defended? {[0 0] :b, [0 1] :b, [1 1] :b} [0 0])))
  (is (defended? {[0 0] :b, [0 1] :b, [1 0] :b} [0 0]))
  (is (defended? {[0 0] :b, [0 1] :b, [1 0] :b, [-1 0] :b} [0 0]))
  (testing "other color should not count"
    (is (not (defended? {[0 0] :r, [0 1] :b, [1 0] :b} [0 0])))))

