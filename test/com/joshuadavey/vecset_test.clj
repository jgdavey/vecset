(ns com.joshuadavey.vecset-test
  (:require [clojure.test :refer :all]
            [com.joshuadavey.vecset :refer :all]))

(set! *warn-on-reflection* true)

(deftest vecset-from-vector-is-seqable
  (let [vecs (vecset [])]
    (is (nil? (seq vecs))))
  (let [vecs (vecset [1 2 3])]
    (is (= '(1 2 3) (seq vecs)))))

(deftest vecset-preserves-order
  (let [vecs (vecset [2 1 2 3 3 4])]
    (is (= '(2 1 2 3 3 4) (seq vecs)))))

(deftest vecset-is-conjable
  (let [vecs (conj (vecset []) 1)]
    (is (= [1] (into [] vecs))))
  (let [vecs (conj (vecset [1 2 3]) 4)]
    (is (= [1 2 3 4] (into [] vecs)))))

(deftest vecset-preserves-count
  (let [vecs (vecset [1 1 2 2])]
    (is (= 4 (count vecs)))))

(deftest vecset-detects-containment
  (let [vecs (vecset [2 1 2 3 3 4])]
    (is (contains? vecs 1))
    (is (not (contains? vecs 5)))))

(deftest vecset-as-function
  (let [vecs (vecset [2 1 2 3 3 4])]
    (is (= 3 (vecs 3)))
    (is (nil? (vecs 5)))
    (is (= 3 (apply vecs [3])))))

(deftest vecset-nth
  (let [vecs (vecset (range 1024))]
    (is (= 1 (nth vecs 1)))
    (is (= 999 (nth vecs 999)))))