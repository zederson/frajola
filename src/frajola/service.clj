(ns frajola.service
  (:require [frajola.twitter.service :as tweet]
            [frajola.camera.service :as dslr]
            [clojure.java.io :as io]))

(def response
  {:status 201 :headers {"Content-Type" "application/json"} :body { :status :ok }})

(defn delete
  [path]
    (io/delete-file path)
    (println "deleted " path))

(defn take-picture []
  (let [file (dslr/take-picture)]
    (future
      (tweet/update-status file)
      (delete file)))
  response)
