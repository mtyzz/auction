(ns tally-auction.core-test
  (:require [clojure.test :refer :all]
            [tally-auction.core :refer :all]
            [tally-auction.auction :as ta]))

(deftest run-auction-test
  (testing "runs an auction and returns correct values"
    (let [bids (map build-bidder [["Jason 10 30 5"] ["Mia 20 40 2"] ["Mist 15 40 3"]])]
      (is (= "Mia" (:name (run-auction bids))))
      (is (= 40 (:bid (run-auction bids)))))))

