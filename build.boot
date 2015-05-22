(set-env!
  :source-paths   #{"src"}
  :dependencies '[[org.clojure/clojure  "1.6.0" :scope "provided"]
                  [boot/core            "2.0.0" :scope "test"]
                  [adzerk/boot-test     "1.0.4" :scope "test"]
                  [adzerk/bootlaces     "0.1.11" :scope "test"]])

(require '[adzerk.bootlaces :refer :all])
(require '[adzerk.boot-test :refer :all])

(def +version+ "0.2.0")

(bootlaces! +version+)

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
  (set-env! :dependencies '[[criterium                   "0.4.3"]
                            [org.clojure/tools.namespace "0.2.9"]
                            [org.clojure/data.avl        "0.0.12"]])
  (set-env! :source-paths #{"dev" "test"})
  identity)
