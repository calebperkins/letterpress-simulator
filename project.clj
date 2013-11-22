(defproject letterpress-bot "0.1.0-SNAPSHOT"
  :description "A simulator for the game Letterpress."
  :url "https://github.com/rofflebuster/letterpress-simulator"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :main ^:skip-aot letterpress-bot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
