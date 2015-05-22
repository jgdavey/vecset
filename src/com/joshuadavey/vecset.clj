(ns com.joshuadavey.vecset
  (:import [clojure.lang
             Associative
             IObj
             IPersistentCollection
             IPersistentStack
             ISeq
             Indexed
             IFn]))

(declare ->Vecset)

(deftype Vecset [^clojure.lang.IPersistentVector v
                 ^clojure.lang.IPersistentSet s
                 ^clojure.lang.IPersistentMap _meta]

  IPersistentCollection
  (seq [this] (.seq v))
  (empty [this] (Vecset. [] #{} _meta))
  (cons [this obj]
    (Vecset. (conj v obj) (conj s obj) _meta))

  IObj
  (meta [this] _meta)
  (withMeta [this m] (Vecset. v s m))

  IFn
  (invoke [this obj] (.invoke s obj))
  (applyTo [this coll] (clojure.lang.AFn/applyToHelper this coll))

  Indexed
  (count [this] (.count v))
  (nth [this ^int n] (.nth v n))
  (nth [this ^int n not-found] (.nth v n not-found))

  IPersistentStack
  (pop [this] (throw (UnsupportedOperationException.)))
  (peek [this] (.peek v))

  Associative
  (assoc [this key val] (throw (UnsupportedOperationException.)))
  (entryAt [this obj] (throw (UnsupportedOperationException.)))
  (containsKey [this obj] (.contains s obj)))

(defn vecset
  "Given an ordered collection, returns an Vecset (collection
  usuable as both a vector and a set), as if by (set coll) and
  (vec coll).

  Collection behaves like a vector with regard to sequential
  operations, and like a set for set- and contains-like operations.

  The set serves as a simple equality index for the data in the
  vector. Duplicates are allowed in the vector."
  ([] (->Vecset [] #{} nil))
  ([coll]
   (->Vecset (vec coll) (set coll) (meta coll))))

(defmethod print-method com.joshuadavey.vecset.Vecset [^com.joshuadavey.vecset.Vecset r, ^java.io.Writer w]
  (#'clojure.core/print-meta r w)
  (.write w "#vecset/vecset ")
  (print-method (with-meta (.v r) nil) w))
