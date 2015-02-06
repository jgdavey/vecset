(set-env!
  :source-paths   #{"src"}
  :dependencies '[[org.clojure/clojure  "1.6.0"     :scope "provided"]
                  [boot/core            "2.0.0-rc9" :scope "test"]
                  [adzerk/boot-test     "1.0.3"     :scope "test"]
                  [adzerk/bootlaces     "0.1.9"     :scope "test"]
                  [criterium            "0.4.3"     :scope "test"]
                  [org.clojure/tools.namespace "0.2.9" :scope "test"]
                  [org.clojure/data.avl "0.0.12"    :scope "test"] ])

(require '[adzerk.bootlaces :refer :all])
(require '[adzerk.boot-test :refer :all])

(def +version+ "0.1.0-SNAPSHOT")

(bootlaces! +version+)

(task-options!
 repl {:init-ns 'user}
 pom  {:project     'com.joshuadavey/vecset
       :version     +version+
       :description "Vecset: a vector with set-like lookup."
       :url         "https://github.com/jgdavey/vecset"
       :scm         {:url "https://github.com/jgdavey/vecset"}
       :license     {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}})

(def bootrepl repl)

(deftask dev []
  (set-env! :source-paths #{"dev" "test"})
  identity)
