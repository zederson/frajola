(defproject frajola "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [twitter-api "1.8.0"]
                 [org.gphoto/gphoto2-java "1.5"]
                 [ring/ring-json "0.4.0"]
                 [clj-http "3.6.0"]
                 [org.clojure/data.json "0.2.6"]]
  :plugins [[lein-ring "0.9.7"]]
  :repositories {"baka.sk" "http://www.baka.sk/maven2/"}
  :resource-paths ["config", "resources"]
  :ring {:handler frajola.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
