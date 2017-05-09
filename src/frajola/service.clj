(ns frajola.service
  (:require [frajola.twitter.service :as tweet]
            [frajola.camera.service :as dslr]
            [clojure.java.io :as io]))

(def response
  {:status 201 :headers {"Content-Type" "application/json"} :body { :status :ok }})

(defn delete
  [path]
  (if (.exists (io/as-file path))
    (io/delete-file path)))

(defn take-picture []
  (let [file (dslr/take-picture)]
    (future
      (tweet/update-status file)
      (delete file)))
  response)
