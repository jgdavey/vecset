(ns com.joshuadavey.vecset-test
  (:require [clojure.test :refer :all]
            [com.joshuadavey.vecset :refer :all])
  (:import [com.joshuadavey.vecset Vecset]))

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

(deftest vecset-can-have-meta
  (let [vecs (with-meta (vecset) {:foo "bar"})]
    (is (= {:foo "bar"} (meta vecs)))))

(deftest vecset-is-peekable
  (let [vecs (vecset)]
    (is (= 4 (peek (into vecs [1 2 3 4]))))))

(deftest printing-and-reading
  (let [s (vecset [1 2 9 8 7 5])]
    (is (= "#vecset/vecset [1 2 9 8 7 5]"
           (pr-str s)))
    (let [o (read-string (pr-str s))]
      (is (= Vecset (type o)))
      (is (= '(1 2 9 8 7 5)
             (seq o))))))
