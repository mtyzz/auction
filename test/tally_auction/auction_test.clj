(ns tally-auction.auction-test
  (:require [tally-auction.auction :as ta]
            [clojure.test :refer :all]))

(def auction-skates {:item "Skates"
                     :clients [{:name "Alicia"
                                :start-bid 50
                                :max-bid 80
                                :increment 3}
                               {:name "Olivia"
                                :start-bid 60
                                :max-bid 82
                                :increment 2}
                               {:name "Mason"
                                :start-bid 55
                                :max-bid 85
                                :increment 5}]})

(def auction-unicycle {:item "Unicycle"
                       :clients [{:name "Alicia"
                                  :start-bid 700
                                  :max-bid 725
                                  :increment 2}
                                 {:name "Olivia"
                                  :start-bid 599
                                  :max-bid 725
                                  :increment 15}
                                 {:name "Mason"
                                  :start-bid 625
                                  :max-bid 725
                                  :increment 8}]})

(def auction-hover-board {:item "Hover Board"
                          :clients [{:name "Alicia"
                                     :start-bid 2500
                                     :max-bid 3000
                                     :increment 500}
                                    {:name "Olivia"
                                     :start-bid 2800
                                     :max-bid 3100
                                     :increment 201}
                                    {:name "Mason"
                                     :start-bid 2501
                                     :max-bid 3200
                                     :increment 247}]})

(def auction-magic-carpet {:item "Magic Carpet"
                          :clients [{:name "Alicia"
                                     :start-bid 25
                                     :max-bid 30
                                     :increment 5}
                                    {:name "Olivia"
                                     :start-bid 28
                                     :max-bid 310
                                     :increment 20}
                                    {:name "Mason, who is a warlock"
                                     :start-bid 25
                                     :max-bid 2500
                                     :increment 24}]})

(testing "pick-winner returns the correct winner for each auction"
  (is (= "Mason" (:name (ta/pick-winner (:clients auction-skates)))))
  (is (= "Alicia" (:name (ta/pick-winner (:clients auction-unicycle)))))
  (is (= "Mason" (:name (ta/pick-winner (:clients auction-hover-board))))))

(testing "bidders don't reach max bid unless necessary"
  (is (= 313 (:bid (ta/pick-winner (:clients auction-magic-carpet))))))

