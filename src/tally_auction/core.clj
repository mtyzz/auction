(ns tally-auction.core
  (:require [tally-auction.auction :as ta]
            [clojure.string :as str])
  (:gen-class))

(defn try-parse-int
  "Avoids NPEs in the event a value is not present"
  [v]
  (if v
    (Integer/parseInt v)))

(defn build-bidder
  "Takes the input from the command line for a bidderand transforms it into the data structure used to determine the auction winner."
  [bidder]
  (let [[name start-bid max-bid increment] (vec (str/split (first bidder)  #" "))]
    {:name name
     :start-bid (try-parse-int start-bid)
     :max-bid (try-parse-int max-bid)
     :increment (try-parse-int increment)}))

(defn run-auction
  "Chooses a winner from the available bids."
  [bidders]
  (ta/pick-winner bidders))

(defn -main
  "Takes in and sends calculates the winner of any number of bids and the name of the item in the format below:
  Name start-bid max-bid increment,
  Name start-bid max-bid increment,
  Name start-bid max-bid increment,
  item-for-auction"
  [& args]
  (let [input (->> (slurp *in*)
                   (java.io.StringReader.)
                   (java.io.BufferedReader.)
                   (line-seq)
                   (map (fn [line]
                          (->> (str/split line #",")))))
        item (first (last input))
        bidders (drop-last input)
        bidders-list (for [bidder bidders]
                       (build-bidder bidder))
        results (run-auction item bidders-list)]
    (println "The winner of " item " was " (:name results) " at " (:bid results) " dollars.")))
