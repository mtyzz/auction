(ns tally-auction.auction)

(defn calc-winning-bid
  "Takes a sequence of preauthorized bids and determines the minimum bid needed by the person willing to spend the most money to win the auction."
  [bid lower]
  (let [start (:start-bid bid)
        increment (:increment bid)]
    (loop [s start]
      (if (> s lower)
        s
        (recur (+ s increment))))))

(defn pick-winner
  "Given a list of bidders, compares them by maximum bid. In the event of a tie, returns as the winner the bidder who entered information first."
  [bidders]
  (let [sorted-bidders (sort (comp - compare) (group-by :max-bid bidders))
        [high-bid-amount highest-bids] (first sorted-bidders)
        [next-bid-amount next-highest-bids] (first (next sorted-bidders))
        number-winners (count highest-bids)]
    (if (< 1 number-winners)
      (assoc (first highest-bids) :winner true :bid high-bid-amount)
      (assoc (first highest-bids) :winner true :bid (calc-winning-bid (first highest-bids) next-bid-amount)))))

