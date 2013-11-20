(ns letterpress-bot.dict
  "Dictionaries to check valid words and get legal moves."
  (:require clojure.string))

(def- word-filename "/usr/share/dict/words")

(def all-words
  "A sorted set of every word in the dictionary file, except proper nouns."
  (apply sorted-set (filter
        #(nil? (re-seq #"[A-Z]" %))
        (clojure.string/split
         (slurp word-filename)
         #"\n"))))

; NOTES
; map sorted-word -> [original-word]
; make trie of sorted words?

(def all-words-trie
  ())

(defn words-containing
  "
  Returns a lazy sequence of words that can be formed from the given letter
  sequence.
  "
  ([letters]
   (words-containing letters all-words))
  ([letters word-set]))
