(ns user
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application."
  (:require
   [clojure.java.io :as io]
   [clojure.java.javadoc :refer (javadoc)]
   [clojure.pprint :refer (pprint)]
   [clojure.reflect :refer (reflect)]
   [clojure.repl :refer (apropos dir doc find-doc pst source)]
   [clojure.set :as set]
   [clojure.string :as str]
   [clojure.test :as test]
   [clojure.tools.namespace.repl :refer (refresh refresh-all)]
   [criterium.core :as crit]
   [clojure.data.avl :as avl]
   [com.joshuadavey.vecset :refer :all]))

(def r (range 1000000))

(def set-index (set r))
(def vecset-index (vecset r))
(def avl-index (apply avl/sorted-set r))

(defn bench []
  (println "\n\n** nth **\n")
  (println "naive (set, seq)")
  (crit/bench
    (nth (seq set-index) 876452))
  (println "\nvecset")
  (crit/bench
    (nth vecset-index 876452))
  (println "\navl/sorted-set")
  (crit/bench
    (nth avl-index 876452))

  (println "\n\n** contains? **\n")
  (println "core set")
  (crit/bench
    (contains? set-index 876452))
  (println "\nvecset")
  (crit/bench
    (contains? vecset-index 876452))
  (println "\navl/sorted-set")
  (crit/bench
    (contains? avl-index 876452))

  (println "\n\n** conj **\n")
  (println "core set")
  (crit/bench
    (reduce conj #{} (range 10000)))
  (println "\nvecset")
  (crit/bench
    (reduce conj (vecset) (range 10000)))
  (println "\navl/sorted-set")
  (crit/bench
    (reduce conj (avl/sorted-set) (range 10000))))

;; nth        mean    stddev
;; set,seq    64.0ms  2.29ms
;; vecset     12.2ns  0.09ns
;; avl/sset   428ns   14.4ns
;;
;; contains?  mean    stddev
;; core set   84.0ns  0.81ms
;; vecset     80.4ns  0.33ns
;; avl/sset   262ns   1.61ns
