(set-env!
  :source-paths   #{"src"}
  :dependencies '[[org.clojure/clojure  "1.7.0" :scope "provided"]
                  [boot/core            "2.5.5" :scope "test"]
                  [org.clojure/test.check "0.9.0" :scope "test"]
                  [adzerk/boot-test     "1.1.1" :scope "test"]
                  [adzerk/bootlaces     "0.1.13" :scope "test"]])

(require '[adzerk.bootlaces :refer :all])
(require '[adzerk.boot-test :as bt])

(def +version+ "0.2.0")

(bootlaces! +version+)
(load-data-readers!)

(task-options!
 pom  {:project     'com.joshuadavey/vecset
       :version     +version+
       :description "Vecset: a vector with set-like lookup."
       :url         "https://github.com/jgdavey/vecset"
       :scm         {:url "https://github.com/jgdavey/vecset"}
       :license     {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask dev []
  (task-options!
    repl {:init-ns 'user})
  (set-env! :dependencies #(into % '[[criterium "0.4.4" :exclusions [org.clojure/clojure]]
                                     [org.clojure/tools.namespace "0.2.11"]
                                     [org.clojure/data.avl "0.0.13"]]))
  (set-env! :source-paths #(into % #{"dev" "test"}))
  identity)

(deftask test []
  (set-env! :source-paths #(into % #{"test"}))
  (bt/test))
