#_(ns auction
  (:require [clojure.spec.alpha :as s]))

#_(s/def ::name string?)

#_(s/def ::start-bid int?)

#_(s/def ::max-bid int?)

#_(s/def ::increment int?)

#_(s/def ::client
  (s/keys :req [::name
                ::start-bid
                ::max-bid
                ::increment]))

