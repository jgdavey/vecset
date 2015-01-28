(defproject com.joshuadavey/vecset "0.1.0-SNAPSHOT"
  :description "Vecset: a vector with set-like lookup."
  :url "https://github.com/jgdavey/vecset"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies []
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.6.0"]
                                  [criterium "0.4.3"]
                                  [org.clojure/data.avl "0.0.12"]]
                   :source-paths ["dev"]}})
