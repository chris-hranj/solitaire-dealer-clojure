(ns solitaire.core)

(def ranks (range 1 14)) ;;Define the cards in a suit

(def suits [:hearts :spades :clubs :diamonds]) ;;Define the suits in a deck

(def deck (into [] (for[s suits r ranks] [s r]))) ;;Create a full deck of cards

(defn shuffle-deck 
  "Shuffle the deck of cards into a random order. Returns a vector of maps"
  [deck-of-cards]
  (loop [i 51 out [] x (rand-int i) hand deck-of-cards] ;;loop from 51 to -1 to avoid IndexOutOfBounds
    (if (not= i -1)
      (recur (dec i) (conj out (get hand x)) (rand-int i) (into (subvec hand 0 x) (subvec hand (inc x))))
      out)))

(defn deal 
  "Deal a Solitaire game using a shuffled deck of cards. Returns a double-nested vector."
  [deck-of-cards]
  (loop [i 1 shuffled-cards deck-of-cards dealt-cards []] ;; loop from 1 to 8 to create 7 piles
    (if (not= i 8)
      (recur (inc i) (subvec shuffled-cards i) (conj dealt-cards (into [] (take i shuffled-cards))))
      (vector (conj dealt-cards shuffled-cards))))) ;; add undealt cards to eighth pile of and nest in vector

(defn -main[]
  (println "\nShuffling a Deck...")
  (println (shuffle-deck deck))
  (println "\nDealing a Deck...")
  (doall (map println (deal (shuffle-deck deck)))))